namespace NMSSaveEditor.Data;

/// <summary>
/// Maps obfuscated 3-character NMS save file JSON key names to human-readable names and vice versa.
/// Port of Java class eC/eD which loads db/jsonmap.txt.
/// NMS saves use hashed key names like "6f=" for "PlayerStateData", ";l5" for "Inventory", etc.
/// </summary>
public class JsonNameMapper
{
    private readonly Dictionary<string, string> _keyToName = new();
    private readonly Dictionary<string, string> _nameToKey = new();

    /// <summary>
    /// Load a mapping file (tab-separated: obfuscated_key\thuman_readable_name).
    /// </summary>
    public void Load(Stream stream)
    {
        using var reader = new StreamReader(stream);
        var unmapped = new List<string>();
        string? line;
        while ((line = reader.ReadLine()) != null)
        {
            if (string.IsNullOrEmpty(line)) continue;
            int tab = line.IndexOf('\t');
            if (tab < 0)
            {
                unmapped.Add(line);
                continue;
            }
            string key = line.Substring(0, tab);
            string name = line.Substring(tab + 1);
            if (!_keyToName.ContainsKey(key) && !_nameToKey.ContainsKey(name))
            {
                _keyToName[key] = name;
                _nameToKey[name] = key;
            }
        }
        // Lines without tabs that aren't already mapped get identity mapping
        foreach (string s in unmapped)
        {
            if (!_keyToName.ContainsKey(s) && !_nameToKey.ContainsKey(s))
            {
                _keyToName[s] = s;
                _nameToKey[s] = s;
            }
        }
    }

    /// <summary>
    /// Load a mapping file from a file path.
    /// </summary>
    public void Load(string filePath)
    {
        using var stream = File.OpenRead(filePath);
        Load(stream);
    }

    /// <summary>
    /// Translate an obfuscated key to its human-readable name.
    /// Returns the key unchanged if no mapping exists.
    /// </summary>
    public string ToName(string key)
    {
        return _keyToName.TryGetValue(key, out string? name) ? name : key;
    }

    /// <summary>
    /// Translate a human-readable name back to its obfuscated key.
    /// Returns the name unchanged if no mapping exists.
    /// </summary>
    public string ToKey(string name)
    {
        return _nameToKey.TryGetValue(name, out string? key) ? key : name;
    }

    /// <summary>
    /// Check if a given string is a known obfuscated key.
    /// </summary>
    public bool IsObfuscatedKey(string key)
    {
        return _keyToName.ContainsKey(key);
    }

    public int Count => _keyToName.Count;
}
