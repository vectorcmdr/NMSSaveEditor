namespace NMSE.Models;

public class Multitool
{
    private readonly JsonObject _data;
    private readonly int _index;

    public Multitool(JsonObject data, int index)
    {
        _data = data;
        _index = index;
    }

    public int Index => _index;

    public string Name
    {
        get => _data.GetString("Name") ?? $"Multitool {_index + 1}";
        set => _data.Set("Name", value);
    }

    public string? Seed
    {
        get => _data.GetString("Seed");
        set => _data.Set("Seed", value);
    }

    public MultitoolType Type
    {
        get
        {
            var typeStr = _data.GetString("MultitoolType");
            return Enum.TryParse<MultitoolType>(typeStr, out var t) ? t : MultitoolType.Unknown;
        }
        set => _data.Set("MultitoolType", value.ToString());
    }

    public ShipClass Class
    {
        get
        {
            var classStr = _data.GetString("MultitoolClass");
            return Enum.TryParse<ShipClass>(classStr, out var c) ? c : ShipClass.C;
        }
        set => _data.Set("MultitoolClass", value.ToString());
    }

    public JsonObject? Inventory => _data.GetObject("Inventory");
    public JsonObject Data => _data;

    public override string ToString() => Name;
}
