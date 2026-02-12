using System.Xml;

namespace NMSSaveEditor.Data;

/// <summary>
/// Loads and manages game item data from XML database files.
/// Port of the Java XML loading logic that reads items.xml, inventory.xml, etc.
/// </summary>
public class GameItemDatabase
{
    private readonly Dictionary<string, GameItem> _items = new(StringComparer.OrdinalIgnoreCase);
    private readonly Dictionary<string, Dictionary<string, int>> _stackSizes = new();

    public IReadOnlyDictionary<string, GameItem> Items => _items;

    public void LoadItems(string xmlPath)
    {
        if (!File.Exists(xmlPath)) return;

        var doc = new XmlDocument();
        doc.Load(xmlPath);

        var root = doc.DocumentElement;
        if (root == null) return;

        foreach (XmlNode node in root.ChildNodes)
        {
            if (node is not XmlElement element) continue;

            var item = new GameItem
            {
                ItemType = element.Name,
                Id = element.GetAttribute("id"),
                Name = element.GetAttribute("name"),
                Subtitle = element.GetAttribute("subtitle"),
                Category = element.GetAttribute("category"),
                Icon = element.GetAttribute("icon"),
                Symbol = element.GetAttribute("symbol"),
                IsCooking = element.GetAttribute("cooking") == "true"
            };

            if (double.TryParse(element.GetAttribute("multiplier"), out double mult))
                item.Multiplier = mult;

            // Read description from child element
            var descNode = element.SelectSingleNode("description");
            if (descNode != null)
                item.Description = descNode.InnerText.Trim();

            if (!string.IsNullOrEmpty(item.Id))
                _items[item.Id] = item;
        }
    }

    public void LoadInventoryData(string xmlPath)
    {
        if (!File.Exists(xmlPath)) return;

        var doc = new XmlDocument();
        doc.Load(xmlPath);

        var root = doc.DocumentElement;
        if (root == null) return;

        foreach (XmlNode node in root.ChildNodes)
        {
            if (node is not XmlElement element) continue;

            string itemId = element.GetAttribute("id");
            if (string.IsNullOrEmpty(itemId)) continue;

            var sizes = new Dictionary<string, int>();
            foreach (XmlAttribute attr in element.Attributes)
            {
                if (attr.Name != "id" && int.TryParse(attr.Value, out int size))
                    sizes[attr.Name] = size;
            }

            _stackSizes[itemId] = sizes;
        }
    }

    public GameItem? GetItem(string id) => _items.GetValueOrDefault(id);

    public int GetStackSize(string itemId, string inventoryType)
    {
        if (_stackSizes.TryGetValue(itemId, out var sizes) &&
            sizes.TryGetValue(inventoryType, out int size))
            return size;
        return 0;
    }

    public IEnumerable<GameItem> GetItemsByCategory(string category) =>
        _items.Values.Where(i => i.Category.Equals(category, StringComparison.OrdinalIgnoreCase));

    public IEnumerable<GameItem> GetItemsByType(string type) =>
        _items.Values.Where(i => i.ItemType.Equals(type, StringComparison.OrdinalIgnoreCase));

    public IEnumerable<GameItem> Search(string query)
    {
        var q = query.ToLowerInvariant();
        return _items.Values.Where(i =>
            i.Name.Contains(q, StringComparison.OrdinalIgnoreCase) ||
            i.Id.Contains(q, StringComparison.OrdinalIgnoreCase) ||
            i.Description.Contains(q, StringComparison.OrdinalIgnoreCase));
    }
}
