namespace NMSSaveEditor.IO;

/// <summary>
/// Chunked LZ4 compression stream with 1MB blocks. Port of Java class ho.
/// Each block has 8-byte header: uncompressedLen(4) + compressedLen(4).
/// </summary>
public class Lz4ChunkedCompressorStream : Stream
{
    private const int BufferSize = 1048576; // 1MB
    private readonly Stream _inner;
    private readonly byte[] _buffer = new byte[BufferSize];
    private int _bufferPos;
    private int _totalUncompressed;
    private int _totalCompressed;

    public int UncompressedSize => _totalUncompressed;
    public int CompressedSize => _totalCompressed;

    public Lz4ChunkedCompressorStream(Stream innerStream)
    {
        _inner = innerStream;
        _bufferPos = 0;
    }

    private void CompressAndFlushBlock()
    {
        if (_bufferPos == 0) return;

        int maxLen = Lz4Compressor.MaxCompressedLength(_bufferPos);
        byte[] compressed = new byte[maxLen];
        int compressedLen = Lz4Compressor.Compress(_buffer, 0, _bufferPos, compressed, 0, maxLen);

        // Write 8-byte header: uncompressed + compressed lengths
        BinaryIO.WriteInt32LE(_inner, _bufferPos);
        BinaryIO.WriteInt32LE(_inner, compressedLen);
        _inner.Write(compressed, 0, compressedLen);

        _totalUncompressed += _bufferPos;
        _totalCompressed += compressedLen + 8;
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
