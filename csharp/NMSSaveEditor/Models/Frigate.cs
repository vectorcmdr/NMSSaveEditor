namespace NMSSaveEditor.Models;

public class Frigate
{
    private readonly JsonObject _data;
    private readonly int _index;

    public Frigate(JsonObject data, int index)
    {
        _data = data;
        _index = index;
    }

    public int Index => _index;

    public bool IsEnabled
    {
        get => _data.Contains("Enabled") && _data.GetBool("Enabled");
    }

    public string? NpcType
    {
        get => _data.GetString("Type");
    }

    public string? ShipType
    {
        get => _data.GetString("ShipType");
    }

    public string? Rank
    {
        get => _data.GetString("Rank");
    }

    public JsonObject Data => _data;

    public override string ToString() => $"Frigate {_index + 1} ({NpcType ?? "Unknown"})";
}
