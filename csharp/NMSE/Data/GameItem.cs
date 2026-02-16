namespace NMSE.Data;

public class GameItem
{
    public string Id { get; set; } = "";
    public string Name { get; set; } = "";
    public string Subtitle { get; set; } = "";
    public string Description { get; set; } = "";
    public string Category { get; set; } = "";
    public string Icon { get; set; } = "";
    public string Symbol { get; set; } = "";
    public double Multiplier { get; set; } = 1.0;
    public bool IsCooking { get; set; }
    public string ItemType { get; set; } = ""; // substance, product, technology, proceduralTechnology, proceduralProduct

    public override string ToString() => $"{Name} ({Id})";
}
