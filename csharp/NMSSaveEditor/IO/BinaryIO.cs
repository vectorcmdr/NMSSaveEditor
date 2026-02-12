namespace NMSSaveEditor.IO;

public static class BinaryIO
{
    private const string Base64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public static int ReadInt32LE(Stream stream)
    {
        Span<byte> buf = stackalloc byte[4];
        ReadFully(stream, buf);
        return buf[0] | (buf[1] << 8) | (buf[2] << 16) | (buf[3] << 24);
    }

    public static void WriteInt32LE(Stream stream, int value)
    {
        stream.WriteByte((byte)(value & 0xFF));
        stream.WriteByte((byte)((value >> 8) & 0xFF));
        stream.WriteByte((byte)((value >> 16) & 0xFF));
        stream.WriteByte((byte)((value >> 24) & 0xFF));
    }

    public static long ReadInt64LE(Stream stream)
    {
        Span<byte> buf = stackalloc byte[8];
        ReadFully(stream, buf);
        return (long)buf[0] | ((long)buf[1] << 8) | ((long)buf[2] << 16) | ((long)buf[3] << 24) |
               ((long)buf[4] << 32) | ((long)buf[5] << 40) | ((long)buf[6] << 48) | ((long)buf[7] << 56);
    }

    public static void WriteInt64LE(Stream stream, long value)
    {
        for (int i = 0; i < 8; i++)
            stream.WriteByte((byte)((value >> (i * 8)) & 0xFF));
    }

    public static byte[] ReadAllBytes(Stream stream)
    {
        using var ms = new MemoryStream();
        stream.CopyTo(ms);
        return ms.ToArray();
    }

    public static byte[] ReadFileBytes(string path)
    {
        using var fs = File.OpenRead(path);
        return ReadAllBytes(fs);
    }

    public static void ReadFully(Stream stream, Span<byte> buffer)
    {
        int offset = 0;
        int remaining = buffer.Length;
        while (remaining > 0)
        {
            int read = stream.Read(buffer.Slice(offset, remaining));
            if (read <= 0) throw new IOException("Short read");
            offset += read;
            remaining -= read;
        }
    }

    public static void ReadFully(Stream stream, byte[] buffer, int offset, int count)
    {
        while (count > 0)
        {
            int read = stream.Read(buffer, offset, count);
            if (read <= 0) throw new IOException("Short read");
            offset += read;
            count -= read;
        }
    }

    // Base64 encode (port of method k)
    public static string Base64Encode(byte[] data)
    {
        // Use native .NET Base64
        return Convert.ToBase64String(data);
    }

    // Base64 decode (port of method aD)
    public static byte[] Base64Decode(string encoded)
    {
        // Use native .NET Base64
        return Convert.FromBase64String(encoded);
    }
}
