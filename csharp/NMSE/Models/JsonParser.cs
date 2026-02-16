using System.Text;

namespace NMSE.Models;

public static class JsonParser
{
    private const string HexChars = "0123456789ABCDEFabcdef";

    /// <summary>
    /// Default name mapper loaded from jsonmap.txt, used to auto-detect and translate
    /// obfuscated NMS save file key names during parsing.
    /// </summary>
    private static NMSE.Data.JsonNameMapper? _defaultSaveMapper;

    /// <summary>
    /// Set the default name mapper for save file parsing.
    /// Called at application startup with the mapper loaded from jsonmap.txt.
    /// </summary>
    public static void SetDefaultMapper(NMSE.Data.JsonNameMapper mapper) =>
        _defaultSaveMapper = mapper;

    // SERIALIZATION

    public static string Serialize(object? value, bool formatted, bool skipReverseMapping = false)
    {
        string? newline = formatted ? Environment.NewLine : null;
        bool spaces = formatted;
        // Extract the mapper from the root object if it's a JsonObject
        var mapper = skipReverseMapping ? null : (value as JsonObject)?.NameMapper;
        return SerializeValue(value, newline, spaces, mapper, skipReverseMapping);
    }

    private static string SerializeValue(object? value, string? newline, bool spaces, NMSE.Data.JsonNameMapper? mapper = null, bool skipReverseMapping = false)
    {
        return value switch
        {
            null => "null",
            bool b => b ? "true" : "false",
            decimal d => d.ToString("G"),
            int i => i.ToString(),
            long l => l.ToString(),
            float f => f.ToString("G"),
            double d => d.ToString("G"),
            string s => QuoteString(s),
            JsonObject obj => SerializeObject(obj, newline, spaces, mapper, skipReverseMapping),
            JsonArray arr => SerializeArray(arr, newline, spaces, mapper, skipReverseMapping),
            BinaryData bin => QuoteBinaryData(bin),
            _ => throw new InvalidOperationException($"Unsupported type: {value.GetType().Name}")
        };
    }

    private static string SerializeObject(JsonObject obj, string? newline, bool spaces, NMSE.Data.JsonNameMapper? mapper = null, bool skipReverseMapping = false)
    {
        var sb = new StringBuilder();
        sb.Append('{');
        var names = obj.GetRawNames();
        var values = obj.GetRawValues();
        // Use mapper from the object itself, or fall back to the one passed from the parent.
        // When skipReverseMapping is true, never reverse-map (display mode).
        var activeMapper = skipReverseMapping ? null : (obj.NameMapper ?? mapper);
        for (int i = 0; i < obj.Length; i++)
        {
            if (i > 0) sb.Append(',');
            if (newline != null) sb.Append(newline + "\t");
            // Reverse-map human-readable name back to obfuscated key for saving
            string name = activeMapper != null ? activeMapper.ToKey(names[i]) : names[i];
            sb.Append(QuoteString(name));
            sb.Append(':');
            if (spaces) sb.Append(' ');
            sb.Append(SerializeValue(values[i], newline == null ? null : newline + "\t", spaces, activeMapper, skipReverseMapping));
        }
        if (obj.Length > 0 && newline != null) sb.Append(newline);
        sb.Append('}');
        return sb.ToString();
    }

    private static string SerializeArray(JsonArray arr, string? newline, bool spaces, NMSE.Data.JsonNameMapper? mapper = null, bool skipReverseMapping = false)
    {
        var sb = new StringBuilder();
        sb.Append('[');
        var values = arr.GetRawValues();
        for (int i = 0; i < arr.Length; i++)
        {
            if (i > 0) sb.Append(',');
            if (newline != null) sb.Append(newline + "\t");
            sb.Append(SerializeValue(values[i], newline == null ? null : newline + "\t", spaces, mapper, skipReverseMapping));
        }
        if (arr.Length > 0 && newline != null) sb.Append(newline);
        sb.Append(']');
        return sb.ToString();
    }

    private static string EscapeString(string s)
    {
        var sb = new StringBuilder();
        foreach (char c in s)
        {
            switch (c)
            {
                case '\r': sb.Append("\\r"); break;
                case '\n': sb.Append("\\n"); break;
                case '\t': sb.Append("\\t"); break;
                case '\f': sb.Append("\\f"); break;
                case '\b': sb.Append("\\b"); break;
                case '"': sb.Append("\\\""); break;
                case '\\': sb.Append("\\\\"); break;
                default:
                    if (c >= ' ')
                        sb.Append(c);
                    else
                    {
                        sb.Append("\\u");
                        sb.Append(HexChars[(c >> 12) & 0xF]);
                        sb.Append(HexChars[(c >> 8) & 0xF]);
                        sb.Append(HexChars[(c >> 4) & 0xF]);
                        sb.Append(HexChars[c & 0xF]);
                    }
                    break;
            }
        }
        return sb.ToString();
    }

    private static string QuoteString(string s) => $"\"{EscapeString(s)}\"";

    private static string EscapeBinaryData(BinaryData data)
    {
        var sb = new StringBuilder();
        foreach (byte b in data.ToByteArray())
        {
            int v = b & 0xFF;
            switch (v)
            {
                case 13: sb.Append("\\r"); break;
                case 10: sb.Append("\\n"); break;
                case 9: sb.Append("\\t"); break;
                case 12: sb.Append("\\f"); break;
                case 8: sb.Append("\\b"); break;
                case 11: sb.Append("\\v"); break;
                case 0: sb.Append("\\0"); break;
                case 34: sb.Append("\\\""); break;
                case 92: sb.Append("\\\\"); break;
                default:
                    if (v >= 32 && v < 128)
                        sb.Append((char)v);
                    else
                    {
                        sb.Append("\\x");
                        sb.Append(HexChars[(v >> 4) & 0xF]);
                        sb.Append(HexChars[v & 0xF]);
                    }
                    break;
            }
        }
        return sb.ToString();
    }

    private static string QuoteBinaryData(BinaryData data) => $"\"{EscapeBinaryData(data)}\"";

    // PARSING

    public static object? ParseValue(string json)
    {
        using var reader = new JsonReader(json);
        var result = ParseValue(reader, reader.ReadSkipWhitespace(), null);
        if (reader.ReadSkipWhitespace() >= 0)
            throw new JsonException("Invalid trailing data", reader.Line, reader.Column);
        return result;
    }

    public static JsonObject ParseObject(string json, NMSE.Data.JsonNameMapper? mapper = null)
    {
        using var reader = new JsonReader(json);
        if (reader.ReadSkipWhitespace() != '{')
            throw new JsonException("Invalid object string", reader.Line, reader.Column);
        var result = ParseObjectBody(reader, mapper);
        if (reader.ReadSkipWhitespace() >= 0)
            throw new JsonException("Invalid trailing data", reader.Line, reader.Column);
        return result;
    }

    public static JsonArray ParseArray(string json)
    {
        using var reader = new JsonReader(json);
        if (reader.ReadSkipWhitespace() != '[')
            throw new JsonException("Invalid array string", reader.Line, reader.Column);
        var result = ParseArrayBody(reader, null);
        if (reader.ReadSkipWhitespace() >= 0)
            throw new JsonException("Invalid trailing data", reader.Line, reader.Column);
        return result;
    }

    private static object? ParseValue(JsonReader reader, int c, NMSE.Data.JsonNameMapper? mapper)
    {
        if (c < 0) throw new JsonException("Short read", reader.Line, reader.Column);

        return c switch
        {
            '{' => ParseObjectBody(reader, mapper),
            '[' => ParseArrayBody(reader, mapper),
            '"' => ParseString(reader),
            'f' => ParseFalse(reader),
            't' => ParseTrue(reader),
            'n' => ParseNull(reader),
            'd' => ParseDataLiteral(reader),
            '-' or (>= '0' and <= '9') => ParseNumber(reader, c),
            _ => throw new JsonException("Invalid token", reader.Line, reader.Column)
        };
    }

    private static JsonObject ParseObjectBody(JsonReader reader, NMSE.Data.JsonNameMapper? mapper)
    {
        var obj = new JsonObject();
        NMSE.Data.JsonNameMapper? activeMapper = mapper;
        int c = reader.ReadSkipWhitespace();
        if (c == '"')
        {
            bool shouldAutoDetect = (mapper == null); // Only auto-detect on first key if no mapper provided
            while (true)
            {
                string key = ParseStringValue(reader);

                // Auto-detect obfuscated keys on the first key of the root object
                if (shouldAutoDetect && activeMapper == null)
                {
                    shouldAutoDetect = false;
                    if (mapper == null && _defaultSaveMapper != null && _defaultSaveMapper.IsObfuscatedKey(key))
                        activeMapper = _defaultSaveMapper;
                }

                // Translate obfuscated key to human-readable name
                if (activeMapper != null)
                    key = activeMapper.ToName(key);

                if (reader.ReadSkipWhitespace() != ':')
                    throw new JsonException("Invalid token", reader.Line, reader.Column);
                object? value = ParseValue(reader, reader.ReadSkipWhitespace(), activeMapper);
                obj.Add(key, value);
                c = reader.ReadSkipWhitespace();
                if (c == '}') break;
                if (c != ',') throw new JsonException("Invalid token", reader.Line, reader.Column);
                c = reader.ReadSkipWhitespace();
                if (c != '"') throw new JsonException("Invalid token", reader.Line, reader.Column);
            }
        }
        else if (c != '}')
        {
            throw new JsonException("Invalid token", reader.Line, reader.Column);
        }

        // Store the mapper on the root object for use during serialization
        if (activeMapper != null)
            obj.NameMapper = activeMapper;

        return obj;
    }

    private static JsonArray ParseArrayBody(JsonReader reader, NMSE.Data.JsonNameMapper? mapper)
    {
        var arr = new JsonArray();
        int c = reader.ReadSkipWhitespace();
        if (c != ']')
        {
            while (true)
            {
                arr.Add(ParseValue(reader, c, mapper));
                c = reader.ReadSkipWhitespace();
                if (c == ']') break;
                if (c != ',') throw new JsonException("Invalid token", reader.Line, reader.Column);
                c = reader.ReadSkipWhitespace();
            }
        }
        return arr;
    }

    private static object ParseFalse(JsonReader reader)
    {
        ExpectChar(reader, 'a'); ExpectChar(reader, 'l');
        ExpectChar(reader, 's'); ExpectChar(reader, 'e');
        return false;
    }

    private static object ParseTrue(JsonReader reader)
    {
        ExpectChar(reader, 'r'); ExpectChar(reader, 'u'); ExpectChar(reader, 'e');
        return true;
    }

    private static object? ParseNull(JsonReader reader)
    {
        ExpectChar(reader, 'u'); ExpectChar(reader, 'l'); ExpectChar(reader, 'l');
        return null;
    }

    private static object ParseDataLiteral(JsonReader reader)
    {
        ExpectChar(reader, 'a'); ExpectChar(reader, 't'); ExpectChar(reader, 'a');
        ExpectChar(reader, '(');
        if (reader.ReadSkipWhitespace() != '"')
            throw new JsonException("Invalid token", reader.Line, reader.Column);
        var data = ParseHexData(reader);
        if (reader.ReadSkipWhitespace() != ')')
            throw new JsonException("Invalid token", reader.Line, reader.Column);
        return data;
    }

    private static void ExpectChar(JsonReader reader, char expected)
    {
        if (reader.Read() != expected)
            throw new JsonException("Invalid token", reader.Line, reader.Column);
    }

    private static string ParseStringValue(JsonReader reader)
    {
        var result = ParseString(reader);
        if (result is string s) return s;
        throw new JsonException("Invalid string", reader.Line, reader.Column);
    }

    private static object ParseString(JsonReader reader)
    {
        var sb = new StringBuilder();
        MemoryStream? byteStream = new MemoryStream();
        bool hasStringContent = true;
        bool hasHighBytes = false; // Track bytes >= 0x80 (non-ASCII) that signal binary data

        int c;
        while ((c = reader.Read()) != '"')
        {
            if (c < 0) throw new JsonException("Short read");

            if (c == '\\')
            {
                c = reader.Read();
                if (c < 0) throw new JsonException("Short read");
                switch (c)
                {
                    case '0': c = 0; break;
                    case 'b': c = 8; break;
                    case 'f': c = 12; break;
                    case 'n': c = 10; break;
                    case 'r': c = 13; break;
                    case 't': c = 9; break;
                    case 'v': c = 11; break;
                    case 'u':
                        c = (ParseHexDigit(reader.Read()) << 12) | (ParseHexDigit(reader.Read()) << 8) |
                            (ParseHexDigit(reader.Read()) << 4) | ParseHexDigit(reader.Read());
                        if (c <= 255)
                        {
                            if (hasStringContent) sb.Append((char)c);
                            byteStream?.WriteByte((byte)c);
                            if (c >= 0x80 && c <= 0xFF) hasHighBytes = true;
                        }
                        else
                        {
                            if (!hasStringContent)
                                throw new JsonException("Mixed encodings detected in string");
                            byteStream = null;
                            sb.Append((char)c);
                        }
                        continue;
                    case 'x':
                        c = (ParseHexDigit(reader.Read()) << 4) | ParseHexDigit(reader.Read());
                        if (byteStream == null)
                            throw new JsonException("Mixed encodings detected in string");
                        byteStream.WriteByte((byte)c);
                        hasStringContent = false;
                        continue;
                    // default: c stays as-is (for \\, \", etc.)
                }
            }

            if (hasStringContent) sb.Append((char)c);
            byteStream?.WriteByte((byte)c);
            // Detect raw bytes >= 0x80 which indicate binary data in the string.
            // These come from Latin-1 decoded save file bytes that would fail strict
            // UTF-8 decoding (matching the Java parser's CharacterCodingException path).
            if (c >= 0x80 && c <= 0xFF) hasHighBytes = true;
        }

        // If any high bytes (0x80-0xFF) were found, this is binary data, not a valid
        // UTF-8 string.  Return as BinaryData to mirror the Java fg/bP() handling.
        if (hasHighBytes && byteStream != null)
            return new BinaryData(byteStream.ToArray());

        return hasStringContent ? sb.ToString() : new BinaryData(byteStream!.ToArray());
    }

    private static BinaryData ParseHexData(JsonReader reader)
    {
        if (reader.Read() != '0') throw new JsonException("Invalid hex data", reader.Line, reader.Column);
        if (reader.Read() != 'x') throw new JsonException("Invalid hex data", reader.Line, reader.Column);

        using var ms = new MemoryStream();
        int c;
        while ((c = reader.Read()) != '"')
        {
            if (c < 0) throw new JsonException("Short read", reader.Line, reader.Column);
            int d = reader.Read();
            if (d < 0) throw new JsonException("Short read", reader.Line, reader.Column);

            int hi = HexChars.IndexOf((char)c);
            if (hi < 0) throw new JsonException("Invalid hex data", reader.Line, reader.Column);
            if (hi >= 16) hi -= 6;

            int lo = HexChars.IndexOf((char)d);
            if (lo < 0) throw new JsonException("Invalid hex data", reader.Line, reader.Column);
            if (lo >= 16) lo -= 6;

            ms.WriteByte((byte)((hi << 4) | lo));
        }
        return new BinaryData(ms.ToArray());
    }

    private static int ParseHexDigit(int c)
    {
        if (c < 0) throw new IOException("short read");
        int index = HexChars.IndexOf((char)c);
        if (index < 0) throw new IOException("invalid hex char");
        if (index >= 16) index -= 6;
        return index;
    }

    private static object ParseNumber(JsonReader reader, int first)
    {
        bool negative = false;
        if (first == '-')
        {
            int next = JsonReader.ReadIf(reader, c => c >= '0' && c <= '9');
            if (next < 0) throw new JsonException("Invalid token", reader.Line, reader.Column);
            first = next;
            negative = true;
        }

        decimal value = first - '0';
        if (first != '0')
        {
            int d;
            while ((d = JsonReader.ReadIf(reader, c => c >= '0' && c <= '9')) >= 0)
                value = value * 10 + (d - '0');
        }

        bool isInteger = true;

        // Fractional part
        if (JsonReader.ReadIf(reader, c => c == '.') >= 0)
        {
            isInteger = false;
            int d = JsonReader.ReadIf(reader, c => c >= '0' && c <= '9');
            if (d < 0) throw new JsonException("Invalid token", reader.Line, reader.Column);
            int scale = 0;
            do
            {
                scale--;
                value += (d - '0') * PowerOf10(scale);
            } while ((d = JsonReader.ReadIf(reader, c => c >= '0' && c <= '9')) >= 0);
        }

        // Exponent
        if (JsonReader.ReadIf(reader, c => c == 'e' || c == 'E') >= 0)
        {
            isInteger = false;
            int d = JsonReader.ReadIf(reader, c => c >= '0' && c <= '9' || c == '+' || c == '-');
            bool negExp = false;
            if (d == '+' || d == '-')
            {
                negExp = d == '-';
                d = JsonReader.ReadIf(reader, c => c >= '0' && c <= '9');
            }
            if (d < 0) throw new JsonException("Invalid token", reader.Line, reader.Column);
            int exp = 0;
            do { exp = exp * 10 + (d - '0'); }
            while ((d = JsonReader.ReadIf(reader, c => c >= '0' && c <= '9')) >= 0);
            if (negExp) exp = -exp;
            value *= PowerOf10(exp);
        }

        if (negative) value = -value;

        if (isInteger)
        {
            if (value >= int.MinValue && value <= int.MaxValue)
                return (int)value;
            if (value >= long.MinValue && value <= long.MaxValue)
                return (long)value;
        }

        return value;
    }

    private static decimal PowerOf10(int exp)
    {
        if (exp == 0) return 1m;
        decimal result = 1m;
        if (exp > 0)
        {
            for (int i = 0; i < exp; i++) result *= 10m;
        }
        else
        {
            for (int i = 0; i < -exp; i++) result /= 10m;
        }
        return result;
    }
}
