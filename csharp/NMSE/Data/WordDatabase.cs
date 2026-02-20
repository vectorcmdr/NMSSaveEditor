using System.Xml;

namespace NMSE.Data;

/// <summary>
/// A single word from words.xml with its display text and per-race group mappings.
/// Each word can have groups for different races (e.g., ^TRA_ATLAS for Gek, ^WAR_ATLAS for Vy'keen).
/// </summary>
public class WordEntry
{
    public string Id { get; }
    public string Text { get; }

    /// <summary>
    /// Maps group name (e.g., "^TRA_ATLAS") to race ordinal (0=Gek, 1=Vy'keen, 2=Korvax, 4=Atlas, 8=Autophage).
    /// </summary>
    public Dictionary<string, int> Groups { get; } = new(StringComparer.Ordinal);

    /// <summary>
    /// Reverse mapping from race ordinal to group name for O(1) lookups.
    /// </summary>
    private readonly Dictionary<int, string> _raceToGroup = new();

    public WordEntry(string id, string text)
    {
        Id = id;
        Text = text;
    }

    /// <summary>
    /// Call after populating Groups to build the reverse lookup.
    /// </summary>
    public void BuildReverseLookup()
    {
        _raceToGroup.Clear();
        foreach (var kvp in Groups)
            _raceToGroup.TryAdd(kvp.Value, kvp.Key);
    }

    /// <summary>
    /// Returns true if this word has a group for the given race ordinal.
    /// </summary>
    public bool HasRace(int raceOrdinal) => _raceToGroup.ContainsKey(raceOrdinal);

    /// <summary>
    /// Returns the group name for the given race ordinal, or null if not available.
    /// </summary>
    public string? GetGroupForRace(int raceOrdinal)
        => _raceToGroup.TryGetValue(raceOrdinal, out var group) ? group : null;
}

/// <summary>
/// Loads and manages the complete word database from words.xml.
/// Maps race name strings from XML (TRADERS, WARRIORS, etc.) to ordinals matching the NMS save format.
/// </summary>
public class WordDatabase
{
    // Race name → ordinal mapping (enum ordinals)
    private static readonly Dictionary<string, int> RaceNameToOrdinal = new(StringComparer.OrdinalIgnoreCase)
    {
        ["TRADERS"] = 0,    // Gek
        ["WARRIORS"] = 1,   // Vy'keen
        ["EXPLORERS"] = 2,  // Korvax
        ["ROBOTS"] = 3,     // Robot
        ["ATLAS"] = 4,      // Atlas
        ["DIPLOMATS"] = 5,
        ["EXOTICS"] = 6,
        ["NONE"] = 7,
        ["BUILDERS"] = 8,   // Autophage
    };

    private readonly List<WordEntry> _words = new();

    public IReadOnlyList<WordEntry> Words => _words;

    public void Load(string xmlPath)
    {
        _words.Clear();
        if (!File.Exists(xmlPath)) return;

        var doc = new XmlDocument();
        doc.Load(xmlPath);

        var wordNodes = doc.SelectNodes("/words/word");
        if (wordNodes == null) return;

        foreach (XmlElement wordElem in wordNodes)
        {
            string id = wordElem.GetAttribute("id");
            string text = wordElem.GetAttribute("text");

            var entry = new WordEntry(id, text);

            var groupNodes = wordElem.GetElementsByTagName("group");
            foreach (XmlElement groupElem in groupNodes)
            {
                string groupName = groupElem.GetAttribute("group");
                string raceName = groupElem.GetAttribute("race");

                if (RaceNameToOrdinal.TryGetValue(raceName, out int ordinal))
                {
                    entry.Groups[groupName] = ordinal;
                }
            }

            entry.BuildReverseLookup();
            _words.Add(entry);
        }

        // Sort alphabetically by display text
        _words.Sort((a, b) => string.Compare(a.Text, b.Text, StringComparison.OrdinalIgnoreCase));
    }
}
