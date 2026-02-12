using NMSSaveEditor.Models;

namespace NMSSaveEditor.Config;

/// <summary>
/// Application configuration manager. Port of Java class aH.
/// Manages app settings, window state, and user preferences via JSON config file.
/// </summary>
public class AppConfig
{
    private const string ConfigFileName = "NMSSaveEditor.conf";
    private static AppConfig? _instance;
    private readonly Dictionary<string, string> _properties = new();
    private string? _configPath;

    public static AppConfig Instance => _instance ??= new AppConfig();

    public string? LastDirectory
    {
        get => GetProperty("LastDirectory");
        set => SetProperty("LastDirectory", value);
    }

    public string? Theme
    {
        get => GetProperty("Theme");
        set => SetProperty("Theme", value);
    }

    public int MainFrameX
    {
        get => int.TryParse(GetProperty("MainFrame.X"), out int v) ? v : 100;
        set => SetProperty("MainFrame.X", value.ToString());
    }

    public int MainFrameY
    {
        get => int.TryParse(GetProperty("MainFrame.Y"), out int v) ? v : 100;
        set => SetProperty("MainFrame.Y", value.ToString());
    }

    public int MainFrameWidth
    {
        get => int.TryParse(GetProperty("MainFrame.Width"), out int v) ? v : 1200;
        set => SetProperty("MainFrame.Width", value.ToString());
    }

    public int MainFrameHeight
    {
        get => int.TryParse(GetProperty("MainFrame.Height"), out int v) ? v : 800;
        set => SetProperty("MainFrame.Height", value.ToString());
    }

    public void Initialize()
    {
        string appData = Environment.GetFolderPath(Environment.SpecialFolder.ApplicationData);
        string configDir = Path.Combine(appData, "NMSSaveEditor");
        Directory.CreateDirectory(configDir);
        _configPath = Path.Combine(configDir, ConfigFileName);

        if (File.Exists(_configPath))
            Load();
    }

    public string? GetProperty(string key) =>
        _properties.GetValueOrDefault(key);

    public void SetProperty(string key, string? value)
    {
        if (value is null)
            _properties.Remove(key);
        else
            _properties[key] = value;
    }

    public void Load()
    {
        if (_configPath is null || !File.Exists(_configPath)) return;

        try
        {
            var json = File.ReadAllText(_configPath);
            var obj = JsonObject.Parse(json);
            foreach (var name in obj.Names())
            {
                var value = obj.Get(name);
                if (value is not null)
                    _properties[name] = value.ToString()!;
            }
        }
        catch (Exception ex)
        {
            System.Diagnostics.Debug.WriteLine($"Failed to load config: {ex.Message}");
        }
    }

    public void Save()
    {
        if (_configPath is null) return;

        try
        {
            var obj = new JsonObject();
            foreach (var kvp in _properties.OrderBy(k => k.Key))
                obj.Add(kvp.Key, kvp.Value);
            File.WriteAllText(_configPath, obj.ToFormattedString());
        }
        catch (Exception ex)
        {
            System.Diagnostics.Debug.WriteLine($"Failed to save config: {ex.Message}");
        }
    }
}
