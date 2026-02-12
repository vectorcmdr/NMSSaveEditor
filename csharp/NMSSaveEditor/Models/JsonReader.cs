namespace NMSSaveEditor.Models;

internal sealed class JsonReader : IDisposable
{
    private readonly string _source;
    private int _pos;
    public int Line { get; private set; } = 1;
    public int Column { get; private set; } = 1;

    public JsonReader(string source) => _source = source;

    public int Read()
    {
        if (_pos >= _source.Length) return -1;
        char c = _source[_pos++];
        if (c == '\n') { Line++; Column = 1; }
        else Column++;
        return c;
    }

    // Read next non-whitespace character (port of bI())
    public int ReadSkipWhitespace()
    {
        int c;
        do { c = Read(); }
        while (c == ' ' || c == '\t' || c == '\n' || c == '\r');
        return c;
    }

    // Conditional read - reads if predicate matches, otherwise unreads (port of static a(fi, Predicate))
    public static int ReadIf(JsonReader reader, Func<int, bool> predicate)
    {
        int c = reader.Read();
        if (c >= 0 && predicate(c)) return c;
        if (c >= 0) reader.Unread();
        return -1;
    }

    private void Unread()
    {
        if (_pos > 0)
        {
            _pos--;
            if (_pos < _source.Length && _source[_pos] == '\n')
            {
                Line--;
                // Column is approximate after unread, but it's fine for error reporting
            }
            else Column--;
        }
    }

    public void Dispose() { /* No resources to dispose */ }
}
