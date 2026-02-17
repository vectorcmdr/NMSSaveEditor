using System;
using System.IO;
using System.Text.Json;
using System.Xml.Linq;
using System.Collections.Generic;

// The worlds laziest, ugliest mbin JSON parser for xml output.
// Don't judge me, I just wanted to get this done quickly.

class CorvetteEntry
{
    public string BuildableShipTechID { get; set; }
    public string Name { get; set; }
    public string Id { get; set; }
    public string Group { get; set; }
    public string AltDescription { get; set; }
}

class Program
{
    public static bool PreventDouble = false;

    static void Main(string[] args)
    {
        // Argument parsing for -nodouble
        foreach (var arg in args)
        {
            if (arg.Equals("-nodouble", StringComparison.OrdinalIgnoreCase))
            {
                PreventDouble = true;
                break;
            }
        }

        // TODO: add parser for all json files to get xml db out in all formats.
        string jsonPath = "data/json/Corvette.json";
        string xmlPath = "data/xml_out/output.xml";
        try
        {
            string json = File.ReadAllText(jsonPath);
            if (string.IsNullOrWhiteSpace(json))
            {
                Console.WriteLine("JSON file is empty or null.");
                return;
            }

            var options = new JsonSerializerOptions { PropertyNameCaseInsensitive = true };
            var doc = JsonDocument.Parse(json);

            var entries = new List<CorvetteEntry>();
            var seenIds = new HashSet<string>();
            foreach (var element in doc.RootElement.EnumerateArray())
            {
                if (element.TryGetProperty("BuildableShipTechID", out var techId) &&
                    element.TryGetProperty("Name", out var name) &&
                    element.TryGetProperty("Id", out var id) &&
                    element.TryGetProperty("Group", out var group) &&
                    element.TryGetProperty("AltDescription", out var altDesc) &&
                    techId.ValueKind != JsonValueKind.Null &&
                    name.ValueKind != JsonValueKind.Null &&
                    id.ValueKind != JsonValueKind.Null &&
                    group.ValueKind != JsonValueKind.Null &&
                    altDesc.ValueKind != JsonValueKind.Null)
                {
                    if (PreventDouble)
                    {
                        var techIdValue = techId.GetString();
                        if (!seenIds.Contains(techIdValue))
                        {
                            entries.Add(new CorvetteEntry
                            {
                                BuildableShipTechID = techIdValue,
                                Name = name.GetString(),
                                Id = id.GetString(),
                                Group = group.GetString(),
                                AltDescription = altDesc.GetString()
                            });
                            seenIds.Add(techIdValue);
                        }
                    }
                    else
                    {
                        entries.Add(new CorvetteEntry
                        {
                            BuildableShipTechID = techId.GetString(),
                            Name = name.GetString(),
                            Id = id.GetString(),
                            Group = group.GetString(),
                            AltDescription = altDesc.GetString()
                        });
                    }
                }
            }

            var xml = new XElement("procedural-technologies",
                entries.ConvertAll(e =>
                    new XElement("procedural-technology",
                        new XAttribute("category", "CORVETTE"),
                        new XAttribute("icon", $"PRODUCT-{e.Id}.PNG"),
                        new XAttribute("id", $"^{e.BuildableShipTechID}"),
                        new XAttribute("name", e.Name),
                        new XAttribute("subtitle", e.Group),
                        new XElement("description", e.AltDescription ?? "")
                    )
                )
            );

            xml.Save(xmlPath);
            Console.WriteLine($"XML written to {xmlPath}");
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error reading or parsing JSON: {ex.Message}");
        }
    }
}