namespace NMSSaveEditor.IO;

/// <summary>
/// LZ4 compression output stream with chunked blocks. Port of Java class gZ.
/// Each block has a 16-byte header: magic(4) + compressedLen(4) + uncompressedLen(4) + padding(4).
/// </summary>
public class Lz4CompressorStream : Stream
{
    private static readonly byte[] Magic = { 0xE5, 0xA1, 0xED, 0xFE };
    private const int BufferSize = 524288; // 512KB

    private readonly Stream _inner;
    private readonly byte[] _buffer = new byte[BufferSize];
    private int _bufferPos;
    private int _totalUncompressed;
    private int _totalCompressed;

    public int UncompressedSize => _totalUncompressed;
    public int CompressedSize => _totalCompressed;

    public Lz4CompressorStream(Stream innerStream)
    {
        _inner = innerStream;
        _bufferPos = 0;
        _totalUncompressed = 0;
        _totalCompressed = 0;
    }

    private void CompressAndFlushBlock()
    {
        if (_bufferPos == 0) return;

        int maxLen = Lz4Compressor.MaxCompressedLength(_bufferPos);
        byte[] compressed = new byte[maxLen];
        int compressedLen = Lz4Compressor.Compress(_buffer, 0, _bufferPos, compressed, 0, maxLen);

        // Write 16-byte header
        byte[] header = new byte[16];
        Magic.CopyTo(header.AsSpan());
        header[4] = (byte)(compressedLen & 0xFF);
        header[5] = (byte)((compressedLen >> 8) & 0xFF);
        header[6] = (byte)((compressedLen >> 16) & 0xFF);
        header[7] = (byte)((compressedLen >> 24) & 0xFF);
        header[8] = (byte)(_bufferPos & 0xFF);
        header[9] = (byte)((_bufferPos >> 8) & 0xFF);
        header[10] = (byte)((_bufferPos >> 16) & 0xFF);
        header[11] = (byte)((_bufferPos >> 24) & 0xFF);
        // bytes 12-15 are padding (zeros)

        _inner.Write(header, 0, 16);
        _inner.Write(compressed, 0, compressedLen);

        _totalUncompressed += _bufferPos;
        _totalCompressed += compressedLen + 16;
        _bufferPos = 0;
    }

    public override void Write(byte[] buffer, int offset, int count)
    {
        while (count > 0)
        {
            if (_bufferPos == BufferSize) CompressAndFlushBlock();
            int toCopy = Math.Min(count, BufferSize - _bufferPos);
            Buffer.BlockCopy(buffer, offset, _buffer, _bufferPos, toCopy);
            _bufferPos += toCopy;
            offset += toCopy;
            count -= toCopy;
        }
    }

    public override void WriteByte(byte value)
    {
        if (_bufferPos == BufferSize) CompressAndFlushBlock();
        _buffer[_bufferPos++] = value;
    }

    public override void Flush()
    {
        if (_bufferPos > 0) CompressAndFlushBlock();
        _inner.Flush();
    }

    protected override void Dispose(bool disposing)
    {
        if (disposing)
        {
            if (_bufferPos > 0) CompressAndFlushBlock();
            _inner.Dispose();
        }
        base.Dispose(disposing);
    }

    public override bool CanRead => false;
    public override bool CanSeek => false;
    public override bool CanWrite => true;
    public override long Length => throw new NotSupportedException();
    public override long Position { get => throw new NotSupportedException(); set => throw new NotSupportedException(); }
    public override int Read(byte[] buffer, int offset, int count) => throw new NotSupportedException();
    public override long Seek(long offset, SeekOrigin origin) => throw new NotSupportedException();
    public override void SetLength(long value) => throw new NotSupportedException();
}
