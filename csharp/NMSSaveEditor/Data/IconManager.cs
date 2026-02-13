using System.Drawing;

namespace NMSSaveEditor.Data;

/// <summary>
/// Loads and caches item icon images from the Resources/icons/ directory.
/// Icon filenames are stored in items.xml (e.g., "SUBSTANCE-FUEL1.PNG").
/// </summary>
public class IconManager : IDisposable
{
    private readonly Dictionary<string, Image?> _cache = new(StringComparer.OrdinalIgnoreCase);
    private readonly string _iconsDirectory;
    private bool _disposed;

    public IconManager(string iconsDirectory)
    {
        _iconsDirectory = iconsDirectory;
    }

    /// <summary>
    /// Gets an icon image for the given filename. Returns null if not found.
    /// Results are cached for performance.
    /// </summary>
    public Image? GetIcon(string? iconFilename)
    {
        if (string.IsNullOrEmpty(iconFilename)) return null;

        if (_cache.TryGetValue(iconFilename, out var cached))
            return cached;

        Image? image = null;
        try
        {
            string path = Path.Combine(_iconsDirectory, iconFilename);
            if (File.Exists(path))
            {
                // Load into memory so file isn't locked
                using var stream = File.OpenRead(path);
                image = Image.FromStream(stream, useEmbeddedColorManagement: false, validateImageData: false);
            }
        }
        catch
        {
            image = null;
        }

        _cache[iconFilename] = image;
        return image;
    }

    /// <summary>
    /// Gets an icon for an item by looking up the icon filename from the database.
    /// </summary>
    public Image? GetIconForItem(string? itemId, GameItemDatabase? database)
    {
        if (database == null || string.IsNullOrEmpty(itemId)) return null;

        var item = database.GetItem(itemId) ?? database.GetItem("^" + itemId);
        return item != null ? GetIcon(item.Icon) : null;
    }

    public void Dispose()
    {
        if (_disposed) return;
        _disposed = true;

        foreach (var img in _cache.Values)
            img?.Dispose();

        _cache.Clear();
    }
}
