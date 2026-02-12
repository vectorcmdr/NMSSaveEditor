using System.Text;

namespace NMSSaveEditor.Models;

public class BinaryData : IEquatable<BinaryData>
{
    private static readonly Encoding Windows1252 = Encoding.GetEncoding(1252);
    private readonly byte[] _data;

    public BinaryData(byte[] data) => _data = data ?? throw new ArgumentNullException(nameof(data));

    public byte[] ToByteArray() => _data;

    public int IndexOf(byte value)
    {
        for (int i = 0; i < _data.Length; i++)
            if (_data[i] == value) return i;
        return -1;
    }

    public string Substring(int start, int length) =>
        Windows1252.GetString(_data, start, length);

    public string ToHexString()
    {
        var sb = new StringBuilder(_data.Length * 2);
        foreach (byte b in _data)
            sb.Append(b.ToString("X2"));
        return sb.ToString();
    }

    public override string ToString() => Windows1252.GetString(_data);
    public bool Equals(BinaryData? other) => other is not null && _data.AsSpan().SequenceEqual(other._data);
    public override bool Equals(object? obj) => obj is BinaryData other && Equals(other);
    public override int GetHashCode()
    {
        var hash = new HashCode();
        foreach (byte b in _data) hash.Add(b);
        return hash.ToHashCode();
    }
}
