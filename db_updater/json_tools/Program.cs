using System;
using System.IO;
using System.Text.Json;
using System.Xml.Linq;
using System.Collections.Generic;
using System.Linq;

// The worlds laziest, ugliest mbin JSON parser for xml output.
// Don't judge me, I just wanted to get this done quickly.

class CorvetteEntry
{
    public string BuildableShipTechID { get; set; }
    public string Name { get; set; }
    public string Id { get; set; }
    public string BuildableShipTechName { get; set; }
    public string Group { get; set; }
    public string AltDescription { get; set; }
}

class UpgradeEntry
{
    public string Id { get; set; }
    public string Name { get; set; }
    public string Group { get; set; }
    public string Description { get; set; }
}

class CuriosityEntry
{
    public string Id { get; set; }
    public string Name { get; set; }
    public string Group { get; set; }
    public string Description { get; set; }
}

class Program
{
    public static bool PreventDouble = true;

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

        ProcessCorvetteProcedurals();
        ProcessFossilCurios();
    }

    static void ProcessCorvetteProcedurals()
    {
        string upgradesJsonPath = "data/json/Upgrades.json";
        string xmlPath = "data/xml_out/output_corvette_procedurals.xml";

        var corvetteEntries = new List<CorvetteEntry>();
        var seenIds = new HashSet<string>();
        var corvetteTechIds = new HashSet<string>();

        // Load Upgrades.json and process CV_ entries
        var upgradeEntries = new List<UpgradeEntry>();
        if (File.Exists(upgradesJsonPath))
        {
            string upgradesJson = File.ReadAllText(upgradesJsonPath);
            if (!string.IsNullOrWhiteSpace(upgradesJson))
            {
                var doc = JsonDocument.Parse(upgradesJson);
                foreach (var element in doc.RootElement.EnumerateArray())
                {
                    if (element.TryGetProperty("Id", out var idProp) &&
                        idProp.ValueKind == JsonValueKind.String)
                    {
                        var idValue = idProp.GetString();
                        if (idValue != null && idValue.StartsWith("CV_"))
                        {
                            string name = element.TryGetProperty("Name", out var nameProp) && nameProp.ValueKind == JsonValueKind.String
                                ? nameProp.GetString() : idValue;
                            string group = element.TryGetProperty("Group", out var groupProp) && groupProp.ValueKind == JsonValueKind.String
                                ? groupProp.GetString() : "";
                            string description = element.TryGetProperty("Description", out var descProp) && descProp.ValueKind == JsonValueKind.String
                                ? descProp.GetString() : "";

                            // Only use up to the first "."
                            int dotIdx = description.IndexOf('.');
                            if (dotIdx >= 0)
                                description = description.Substring(0, dotIdx + 1);

                            upgradeEntries.Add(new UpgradeEntry
                            {
                                Id = idValue,
                                Name = name,
                                Group = group,
                                Description = description
                            });
                        }
                    }
                }
            }
        }

        // Helper to strip trailing digits from an id
        string StripTrailingNumber(string id)
        {
            if (string.IsNullOrEmpty(id)) return id;
            int i = id.Length - 1;
            while (i >= 0 && char.IsDigit(id[i])) i--;
            return id.Substring(0, i + 1);
        }

        // Build XML
        var xml = new XElement("procedural-technologies");
        var allEntries = new List<XElement>();

        // No Corvette.json entries

        // Upgrades.json CV_ entries
        foreach (var e in upgradeEntries)
        {
            string strippedId = StripTrailingNumber(e.Id);
            string icon = $"PRODUCT-{e.Id}.PNG";

            allEntries.Add(new XElement("procedural-technology",
                new XAttribute("category", "CORVETTE"),
                new XAttribute("icon", icon),
                new XAttribute("id", $"^{e.Id}"),
                new XAttribute("name", e.Name),
                new XAttribute("subtitle", e.Group),
                new XElement("description", e.Description ?? "")
            ));
        }

        // Sort by id attribute alphabetically
        foreach (var entry in allEntries.OrderBy(x => (string)x.Attribute("id")))
        {
            xml.Add(entry);
        }

        xml.Save(xmlPath);
        Console.WriteLine($"XML written to {xmlPath}");
    }

    static void ProcessFossilCurios()
    {
        string curiosJsonPath = "data/json/Curiosities.json";
        string xmlPath = "data/xml_out/output_fossils_curio.xml";

        var curiosityEntries = new List<CuriosityEntry>();
        var seenIds = new HashSet<string>();

        if (File.Exists(curiosJsonPath))
        {
            string curiosJson = File.ReadAllText(curiosJsonPath);
            if (!string.IsNullOrWhiteSpace(curiosJson))
            {
                var doc = JsonDocument.Parse(curiosJson);
                foreach (var element in doc.RootElement.EnumerateArray())
                {
                    if (element.TryGetProperty("Id", out var idProp) &&
                        idProp.ValueKind == JsonValueKind.String)
                    {
                        var idValue = idProp.GetString();
                        if (idValue != null && idValue.StartsWith("FOS_"))
                        {
                            // PreventDouble logic: skip if already seen
                            string idKey = idValue;
                            if (PreventDouble && seenIds.Contains(idKey))
                                continue;
                            seenIds.Add(idKey);

                            string name = element.TryGetProperty("Name", out var nameProp) && nameProp.ValueKind == JsonValueKind.String
                                ? nameProp.GetString() : idValue;
                            string group = element.TryGetProperty("Group", out var groupProp) && groupProp.ValueKind == JsonValueKind.String
                                ? groupProp.GetString() : "";
                            string description = element.TryGetProperty("Description", out var descProp) && descProp.ValueKind == JsonValueKind.String
                                ? descProp.GetString() : "";

                            curiosityEntries.Add(new CuriosityEntry
                            {
                                Id = idValue,
                                Name = name,
                                Group = group,
                                Description = description
                            });
                        }
                    }
                }
            }
        }

        var xmlRoot = new XElement("products");
        foreach (var entry in curiosityEntries)
        {
            var productElem = new XElement("product",
                new XAttribute("category", "CURIOSITY"),
                new XAttribute("icon", $"PRODUCT-{entry.Id}.PNG"),
                new XAttribute("id", $"^{entry.Id}"),
                new XAttribute("multiplier", "1"),
                new XAttribute("name", entry.Name),
                new XAttribute("subtitle", entry.Group),
                new XElement("description", entry.Description)
            );
            xmlRoot.Add(productElem);
        }

        Directory.CreateDirectory(Path.GetDirectoryName(xmlPath));
        var docOut = new XDocument(new XDeclaration("1.0", "utf-8", "yes"), xmlRoot);
        docOut.Save(xmlPath);
    }
}