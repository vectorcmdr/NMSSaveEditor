namespace NMSSaveEditor.Models;

public class JsonArray
{
    private const int GrowthIncrement = 10;
    private object?[] _values;
    public int Length { get; private set; }
    internal object? Parent { get; set; }
    public IPropertyChangeListener? Listener { get; set; }

    public JsonArray()
    {
        _values = new object?[GrowthIncrement];
        Length = 0;
    }

    private void EnsureCapacity(int needed)
    {
        if (_values.Length >= needed) return;
        var newValues = new object?[needed + GrowthIncrement];
        Array.Copy(_values, newValues, Length);
        _values = newValues;
    }

    public void Add(object? value)
    {
        ValidateType(value);
        EnsureCapacity(Length + 1);
        _values[Length] = value;
        SetParent(value, this);
        Length++;
    }

    public void Insert(int index, object? value)
    {
        ValidateType(value);
        EnsureCapacity(Length + 1);
        Array.Copy(_values, index, _values, index + 1, Length - index);
        _values[index] = value;
        SetParent(value, this);
        Length++;
    }

    public object? Get(int index)
    {
        if (index < 0 || index >= Length) throw new IndexOutOfRangeException();
        return _values[index];
    }

    public void Set(int index, object? value)
    {
        if (index < 0 || index >= Length) throw new IndexOutOfRangeException();
        ValidateType(value);
        var old = _values[index];
        ClearParent(old);
        _values[index] = value;
        SetParent(value, this);
    }

    public void RemoveAt(int index)
    {
        if (index < 0 || index >= Length) throw new IndexOutOfRangeException();
        ClearParent(_values[index]);
        Array.Copy(_values, index + 1, _values, index, Length - index - 1);
        _values[--Length] = null;
    }

    public void Clear()
    {
        for (int i = 0; i < Length; i++)
        {
            ClearParent(_values[i]);
            _values[i] = null;
        }
        Length = 0;
    }

    public int IndexOf(object? value)
    {
        for (int i = 0; i < Length; i++)
            if (Equals(_values[i], value)) return i;
        return -1;
    }

    // Type-safe accessors (ported from Java V/W/X/Y/Z/aa/ab methods)
    public JsonObject GetObject(int index) => (JsonObject)Get(index)!;
    public JsonArray GetArray(int index) => (JsonArray)Get(index)!;
    public string GetString(int index) => (string)Get(index)!;
    public int GetInt(int index) => Convert.ToInt32(Get(index));
    public long GetLong(int index) => Convert.ToInt64(Get(index));
    public double GetDouble(int index) => Convert.ToDouble(Get(index));
    public bool GetBool(int index) => Convert.ToBoolean(Get(index));
    public decimal GetDecimal(int index) => Convert.ToDecimal(Get(index));

    public JsonArray DeepClone()
    {
        var clone = new JsonArray();
        clone._values = new object?[_values.Length];
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

    public IEnumerable<JsonObject> FilterObjects()
    {
        for (int i = 0; i < Length; i++)
            if (_values[i] is JsonObject obj) yield return obj;
    }

    internal object?[] GetRawValues() => _values;

    public override string ToString() => JsonParser.Serialize(this, false);
    public string ToFormattedString() => JsonParser.Serialize(this, true);

    private static void ValidateType(object? value)
    {
        if (value is null or bool or int or long or float or double or decimal or string or JsonObject or JsonArray or BinaryData)
            return;
        throw new ArgumentException($"Unsupported JSON value type: {value.GetType().Name}");
    }

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
