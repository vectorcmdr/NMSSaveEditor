using System.Text.RegularExpressions;

namespace NMSSaveEditor.Models;

public partial class JsonObject
{
    private const int GrowthIncrement = 10;
    private string[] _names;
    private object?[] _values;
    public int Length { get; private set; }
    internal object? Parent { get; set; }
    public IPropertyChangeListener? Listener { get; set; }
    private readonly Dictionary<string, Func<object?, object?>> _transforms = new();

    /// <summary>
    /// The name mapper used to translate between obfuscated and human-readable keys.
    /// Set on the root object during parsing if the save uses obfuscated names.
    /// Used during serialization to reverse-map names back to obfuscated form.
    /// </summary>
    public NMSSaveEditor.Data.JsonNameMapper? NameMapper { get; set; }

    [GeneratedRegex(@"([^\.\[\]]+)|(?:\.([^\.\[\]]+))|(?:\[(\d+)\])")]
    private static partial Regex PathPattern();

    [GeneratedRegex(@"[^""\.\[\]]+")]
    private static partial Regex NamePattern();

    public JsonObject()
    {
        _names = new string[GrowthIncrement];
        _values = new object?[GrowthIncrement];
        Length = 0;
    }

    public static JsonObject Parse(string json) => JsonParser.ParseObject(json);

    public void RegisterTransform(string key, Func<object?, object?> transform) =>
        _transforms[key] = transform;

    public void Add(string name, object? value)
    {
        for (int i = 0; i < Length; i++)
            if (_names[i] == name) throw new InvalidOperationException($"Duplicate key: {name}");

        EnsureCapacity(Length + 1);
        _names[Length] = name;
        _values[Length] = value;
        SetParent(value, this);
        Length++;
    }

    private void EnsureCapacity(int needed)
    {
        if (_values.Length >= needed) return;
        var newNames = new string[needed + GrowthIncrement];
        var newValues = new object?[needed + GrowthIncrement];
        Array.Copy(_names, newNames, Length);
        Array.Copy(_values, newValues, Length);
        _names = newNames;
        _values = newValues;
    }

    public int Size() => Length;

    public IReadOnlyList<string> Names()
    {
        var result = new string[Length];
        Array.Copy(_names, result, Length);
        return result;
    }

    public bool Contains(string name)
    {
        for (int i = 0; i < Length; i++)
            if (_names[i] == name) return true;
        return false;
    }

    public object? Get(string name)
    {
        for (int i = 0; i < Length; i++)
            if (_names[i] == name) return _values[i];
        return null;
    }

    public void Set(string name, object? value)
    {
        for (int i = 0; i < Length; i++)
        {
            if (_names[i] == name)
            {
                var old = _values[i];
                ClearParent(old);
                _values[i] = value;
                SetParent(value, this);
                return;
            }
        }
        Add(name, value);
    }

    public void Remove(string name)
    {
        for (int i = 0; i < Length; i++)
        {
            if (_names[i] == name)
            {
                ClearParent(_values[i]);
                Array.Copy(_names, i + 1, _names, i, Length - i - 1);
                Array.Copy(_values, i + 1, _values, i, Length - i - 1);
                _names[--Length] = null!;
                _values[Length] = null;
                return;
            }
        }
    }

    // Type-safe getters - use GetValue for path/transform support
    public JsonObject? GetObject(string name) => GetValue(name) as JsonObject;
    public JsonArray? GetArray(string name) => GetValue(name) as JsonArray;
    public string? GetString(string name) => GetValue(name) as string;
    public int GetInt(string name) => Convert.ToInt32(GetValue(name));
    public long GetLong(string name) => Convert.ToInt64(GetValue(name));
    public double GetDouble(string name) => Convert.ToDouble(GetValue(name));
    public bool GetBool(string name) => Convert.ToBoolean(GetValue(name));
    public decimal GetDecimal(string name) => Convert.ToDecimal(GetValue(name));

    // Path-based access (ported from Java getValue method)
    // Supports dotted paths like "PlayerStateData.Health" and transforms
    public object? GetValue(string path)
    {
        // Check transforms first (port of Java G() method)
        string resolvedPath = ResolveTransforms(path);

        object? current = this;
        var matches = PathPattern().Matches(resolvedPath);
        foreach (Match match in matches)
        {
            if (current is null) return null;

            string? key = match.Groups[1].Success ? match.Groups[1].Value :
                          match.Groups[2].Success ? match.Groups[2].Value : null;
            string? indexStr = match.Groups[3].Success ? match.Groups[3].Value : null;

            if (key is not null && current is JsonObject obj)
                current = obj.Get(key);
            else if (indexStr is not null && current is JsonArray arr)
                current = arr.Get(int.Parse(indexStr));
            else
                return null;
        }
        return current;
    }

    private string ResolveTransforms(string path)
    {
        foreach (var kvp in _transforms)
        {
            if (path == kvp.Key)
            {
                var result = kvp.Value(this);
                if (result is string s) return s;
            }
            else if (path.StartsWith(kvp.Key + ".") || path.StartsWith(kvp.Key + "["))
            {
                var result = kvp.Value(this);
                if (result is string s)
                    return s + path.Substring(kvp.Key.Length);
            }
        }
        return path;
    }

    public JsonObject DeepClone()
    {
        var clone = new JsonObject();
        clone._names = new string[_values.Length];
        clone._values = new object?[_values.Length];
        Array.Copy(_names, clone._names, Length);
        for (int i = 0; i < Length; i++)
        {
            clone._values[i] = _values[i] switch
            {
                JsonObject obj => obj.DeepClone(),
                JsonArray arr => arr.DeepClone(),
                _ => _values[i]
            };
            SetParent(clone._values[i], clone);
        }
        clone.Length = Length;
        return clone;
    }

    // File I/O
    public void ExportToFile(string filePath)
    {
        var json = JsonParser.Serialize(this, true);
        File.WriteAllText(filePath, json);
    }

    public static JsonObject ImportFromFile(string filePath)
    {
        var json = File.ReadAllText(filePath);
        return Parse(json);
    }

    // For serialization
    internal string[] GetRawNames() => _names;
    internal object?[] GetRawValues() => _values;

    public override string ToString() => JsonParser.Serialize(this, false);
    public string ToFormattedString() => JsonParser.Serialize(this, true);
    /// <summary>
    /// Serialize with human-readable names (no reverse mapping), for display purposes.
    /// </summary>
    public string ToDisplayString() => JsonParser.Serialize(this, true, skipReverseMapping: true);

    private static void SetParent(object? value, object parent)
    {
        if (value is JsonObject obj) obj.Parent = parent;
        else if (value is JsonArray arr) arr.Parent = parent;
    }

    private static void ClearParent(object? value)
    {
        if (value is JsonObject obj) obj.Parent = null;
        else if (value is JsonArray arr) arr.Parent = null;
    }
}
