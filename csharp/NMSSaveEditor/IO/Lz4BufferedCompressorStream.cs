namespace NMSSaveEditor.IO;

/// <summary>
/// Buffered LZ4 compression stream. Port of Java class hb.
/// Accumulates all data and compresses it in a single block on close/dispose.
/// </summary>
public class Lz4BufferedCompressorStream : Stream
{
    private const int BlockSize = 65536;
    private readonly Stream _inner;
    private byte[] _buffer;
    private int _bufferPos;
    private int _compressedSize;

    public int UncompressedSize => _bufferPos;
    public int CompressedSize => _compressedSize;

    public Lz4BufferedCompressorStream(Stream innerStream)
    {
        _inner = innerStream;
        _buffer = new byte[BlockSize];
        _bufferPos = 0;
        _compressedSize = 0;
    }

    private void EnsureCapacity(int additional)
    {
        if (_bufferPos + additional <= _buffer.Length) return;
        int needed = _buffer.Length + additional;
        int blocks = (needed + BlockSize - 1) / BlockSize;
        var newBuffer = new byte[blocks * BlockSize];
        Buffer.BlockCopy(_buffer, 0, newBuffer, 0, _bufferPos);
        _buffer = newBuffer;
    }

    public override void WriteByte(byte value)
    {
        EnsureCapacity(1);
        _buffer[_bufferPos++] = value;
    }

    public override void Write(byte[] buffer, int offset, int count)
    {
        EnsureCapacity(count);
        Buffer.BlockCopy(buffer, offset, _buffer, _bufferPos, count);
        _bufferPos += count;
    }

    public override void Flush() => _inner.Flush();

    protected override void Dispose(bool disposing)
    {
        if (disposing)
        {
            try
            {
                if (_bufferPos > 0)
                {
                    int maxLen = Lz4Compressor.MaxCompressedLength(_bufferPos);
                    byte[] compressed = new byte[maxLen];
                    _compressedSize = Lz4Compressor.Compress(_buffer, 0, _bufferPos, compressed, 0, maxLen);
                    _inner.Write(compressed, 0, _compressedSize);
                }
            }
            finally
            {
                _inner.Dispose();
            }
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
