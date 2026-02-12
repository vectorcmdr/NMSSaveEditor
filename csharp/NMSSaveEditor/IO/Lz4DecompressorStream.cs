namespace NMSSaveEditor.IO;

/// <summary>
/// LZ4 decompression stream. Port of Java class ha.
/// Reads LZ4-compressed blocks and decompresses them on the fly.
/// </summary>
public class Lz4DecompressorStream : Stream
{
    private const int MaxDecompressedSize = 256 * 1024 * 1024; // 256MB safety limit
    private readonly Stream _inner;
    private readonly bool _dynamicSize;
    private byte[] _buffer;
    private int _readPos;
    private int _writePos;
    private bool _eof;

    public Lz4DecompressorStream(Stream innerStream, int uncompressedSize)
    {
        _inner = innerStream;
        if (uncompressedSize == 0)
        {
            _dynamicSize = true;
            _buffer = new byte[1048576]; // 1MB initial
        }
        else
        {
            if (uncompressedSize > MaxDecompressedSize)
                throw new IOException($"Decompressed size {uncompressedSize} exceeds maximum allowed {MaxDecompressedSize}");
            _dynamicSize = false;
            _buffer = new byte[uncompressedSize];
        }
        _readPos = 0;
        _writePos = 0;
        _eof = false;
    }

    private void EnsureCapacity(int additional)
    {
        if (_writePos + additional <= _buffer.Length) return;
        if (!_dynamicSize) throw new IOException("Buffer exceeded");

        long newSizeL = _buffer.Length;
        do { newSizeL += 1048576; }
        while (_writePos + additional > newSizeL);

        if (newSizeL > MaxDecompressedSize)
            throw new IOException($"Decompressed data exceeds maximum allowed size of {MaxDecompressedSize} bytes");

        int newSize = (int)newSizeL;
        var newBuffer = new byte[newSize];
        Buffer.BlockCopy(_buffer, 0, newBuffer, 0, _writePos);
        _buffer = newBuffer;
    }

    private bool DecompressBlock()
    {
        if (_eof) return false;

        int token = _inner.ReadByte();
        if (token < 0)
        {
            if (_dynamicSize) { _eof = true; return false; }
            throw new EndOfStreamException("Unexpected end of stream");
        }

        int literalLength = token >> 4;
        int matchLength = token & 0x0F;

        // Read extended literal length
        if (literalLength == 15)
        {
            int b;
            do
            {
                b = _inner.ReadByte();
                if (b < 0) throw new EndOfStreamException("Unexpected end of literal length");
                literalLength += b;
            } while (b == 255);
        }

        // Read literal data
        if (literalLength > 0)
        {
            EnsureCapacity(literalLength);
            int remaining = literalLength;
            while (remaining > 0)
            {
                int read = _inner.Read(_buffer, _writePos, remaining);
                if (read <= 0) throw new EndOfStreamException("Unexpected end of literal value");
                _writePos += read;
                remaining -= read;
            }
        }

        // Check if we've reached the end
        if (_writePos == _buffer.Length && !_dynamicSize)
        {
            _eof = true;
            return true;
        }

        // Read offset
        int offsetLo = _inner.ReadByte();
        if (offsetLo < 0)
        {
            if (_dynamicSize) { _eof = true; return true; }
            throw new EndOfStreamException("Unexpected end of offset");
        }
        int offsetHi = _inner.ReadByte();
        if (offsetHi < 0) throw new EndOfStreamException("Unexpected end of offset");
        int offset = offsetLo | (offsetHi << 8);

        // Read extended match length
        if (matchLength == 15)
        {
            int b;
            do
            {
                b = _inner.ReadByte();
                if (b < 0) throw new EndOfStreamException("Unexpected end of match length");
                matchLength += b;
            } while (b == 255);
        }
        matchLength += 4; // MinMatch

        if (offset == 0) throw new EndOfStreamException("Offset is zero!");
        if (offset > _writePos) throw new EndOfStreamException("Buffer too small");

        // Copy match data (may overlap)
        EnsureCapacity(matchLength);
        if (matchLength > offset)
        {
            int srcPos = _writePos - offset;
            do
            {
                Buffer.BlockCopy(_buffer, srcPos, _buffer, _writePos, offset);
                matchLength -= offset;
                _writePos += offset;
            } while (matchLength > offset);
            Buffer.BlockCopy(_buffer, srcPos, _buffer, _writePos, matchLength);
            _writePos += matchLength;
        }
        else
        {
            Buffer.BlockCopy(_buffer, _writePos - offset, _buffer, _writePos, matchLength);
            _writePos += matchLength;
        }

        return true;
    }

    public override int Read(byte[] buffer, int offset, int count)
    {
        if (_readPos == _writePos && !DecompressBlock()) return 0;
        int available = Math.Min(count, _writePos - _readPos);
        Buffer.BlockCopy(_buffer, _readPos, buffer, offset, available);
        _readPos += available;
        return available;
    }

    public override int ReadByte()
    {
        if (_readPos == _writePos && !DecompressBlock()) return -1;
        return _buffer[_readPos++];
    }

    public override bool CanRead => true;
    public override bool CanSeek => false;
    public override bool CanWrite => false;
    public override long Length => throw new NotSupportedException();
    public override long Position { get => throw new NotSupportedException(); set => throw new NotSupportedException(); }
    public override void Flush() { }
    public override long Seek(long offset, SeekOrigin origin) => throw new NotSupportedException();
    public override void SetLength(long value) => throw new NotSupportedException();
    public override void Write(byte[] buffer, int offset, int count) => throw new NotSupportedException();

    protected override void Dispose(bool disposing)
    {
        if (disposing) _inner.Dispose();
        base.Dispose(disposing);
    }
}
