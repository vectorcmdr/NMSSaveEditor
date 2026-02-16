namespace NMSE.Models;

public class Companion
{
    private readonly JsonObject _data;
    private readonly int _index;

    public Companion(JsonObject data, int index)
    {
        _data = data;
        _index = index;
    }

    public int Index => _index;

    public string? Type
    {
        get => _data.GetString("Type");
        set => _data.Set("Type", value);
    }

    public string? Seed
    {
        get => _data.GetString("Seed");
        set => _data.Set("Seed", value);
    }

    public JsonObject Data => _data;

    public override string ToString() => $"Companion {_index + 1} ({Type ?? "Unknown"})";
}
