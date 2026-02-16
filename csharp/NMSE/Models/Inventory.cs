namespace NMSE.Models;

public class Inventory
{
    private readonly JsonObject _data;
    private readonly InventoryType _type;

    public Inventory(JsonObject data, InventoryType type)
    {
        _data = data;
        _type = type;
    }

    public InventoryType Type => _type;

    public int Width
    {
        get => _data.Contains("Width") ? _data.GetInt("Width") : 0;
        set => _data.Set("Width", value);
    }

    public int Height
    {
        get => _data.Contains("Height") ? _data.GetInt("Height") : 0;
        set => _data.Set("Height", value);
    }

    public JsonArray? Slots => _data.GetArray("Slots");
    public JsonArray? ValidSlotIndices => _data.GetArray("ValidSlotIndices");

    public JsonObject Data => _data;

    public override string ToString() => $"{_type} ({Width}x{Height})";
}
