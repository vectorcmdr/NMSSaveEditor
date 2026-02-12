namespace NMSSaveEditor.Models;

public class JsonException : Exception
{
    public int Line { get; }
    public int Column { get; }

    public JsonException(string message) : base(message) { }
    public JsonException(string message, int line, int column) : base($"{message} at line {line}, column {column}")
    {
        Line = line;
        Column = column;
    }
    public JsonException(string message, Exception inner, int line, int column) : base($"{message} at line {line}, column {column}", inner)
    {
        Line = line;
        Column = column;
    }
}
