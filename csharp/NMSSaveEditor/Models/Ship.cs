namespace NMSSaveEditor.Models;

public class Ship
{
    private readonly JsonObject _data;
    private readonly int _index;

    public Ship(JsonObject data, int index)
    {
        _data = data;
        _index = index;
    }

    public int Index => _index;

    public string Name
    {
        get => _data.GetString("Name") ?? $"Ship {_index + 1}";
        set => _data.Set("Name", value);
    }

    public string? Seed
    {
        get => _data.GetString("Seed");
        set => _data.Set("Seed", value);
    }

    public ShipType Type
    {
        get
        {
            var typeStr = _data.GetString("ShipType");
            return Enum.TryParse<ShipType>(typeStr, out var t) ? t : ShipType.Unknown;
        }
        set => _data.Set("ShipType", value.ToString());
    }

    public ShipClass Class
    {
        get
        {
            var classStr = _data.GetString("ShipClass");
            return Enum.TryParse<ShipClass>(classStr, out var c) ? c : ShipClass.C;
        }
        set => _data.Set("ShipClass", value.ToString());
    }

    public JsonObject? Inventory => _data.GetObject("Inventory");
    public JsonObject? TechInventory => _data.GetObject("Inventory_TechOnly");
    public JsonObject? CargoInventory => _data.GetObject("Inventory_Cargo");

    public JsonObject Data => _data;

    public override string ToString() => Name;
}
