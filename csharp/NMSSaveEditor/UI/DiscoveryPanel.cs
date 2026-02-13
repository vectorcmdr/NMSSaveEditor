using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class DiscoveryPanel : UserControl
{
    private readonly TreeView _discoveryTree;
    private readonly Label _countLabel;

    public DiscoveryPanel()
    {
        SuspendLayout();

        var layout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 1,
            RowCount = 3,
            Padding = new Padding(10)
        };
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));

        var titleLabel = new Label
        {
            Text = "Discoveries",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 5)
        };
        layout.Controls.Add(titleLabel, 0, 0);

        _countLabel = new Label { Text = "No discovery data loaded.", AutoSize = true };
        layout.Controls.Add(_countLabel, 0, 1);

        _discoveryTree = new TreeView
        {
            Dock = DockStyle.Fill,
            ShowLines = true,
            ShowPlusMinus = true,
            ShowRootLines = true
        };
        layout.Controls.Add(_discoveryTree, 0, 2);

        Controls.Add(layout);
        ResumeLayout(false);
        PerformLayout();
    }

    public void LoadData(JsonObject saveData)
    {
        _discoveryTree.Nodes.Clear();
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            var discoveryData = playerState?.GetObject("DiscoveryManagerData")
                                ?? saveData.GetObject("DiscoveryManagerData");

            if (discoveryData == null)
            {
                _countLabel.Text = "No discovery data found.";
                return;
            }

            var stores = discoveryData.GetArray("DiscoveryData-v1")
                         ?? discoveryData.GetArray("Store")
                         ?? discoveryData.GetArray("ReserveStore");

            if (stores == null || stores.Length == 0)
            {
                // Try nested structure
                var store = discoveryData.GetObject("Store");
                if (store != null)
                {
                    var records = store.GetArray("Record");
                    if (records != null)
                        stores = records;
                }
            }

            if (stores == null || stores.Length == 0)
            {
                // Fallback: show raw keys
                var rootNode = new TreeNode("DiscoveryManagerData");
                foreach (var key in discoveryData.Names())
                {
                    var child = new TreeNode(key);
                    var val = discoveryData.Get(key);
                    if (val is JsonArray arr)
                        child.Text = $"{key} [{arr.Length} items]";
                    else if (val is JsonObject obj)
                        child.Text = $"{key} ({obj.Length} keys)";
                    rootNode.Nodes.Add(child);
                }
                _discoveryTree.Nodes.Add(rootNode);
                rootNode.Expand();
                _countLabel.Text = $"Discovery data: {discoveryData.Length} entries";
                return;
            }

            int systemCount = 0, planetCount = 0, faunaCount = 0, floraCount = 0, otherCount = 0;

            var systemsNode = new TreeNode("Star Systems");
            var planetsNode = new TreeNode("Planets");
            var faunaNode = new TreeNode("Fauna");
            var floraNode = new TreeNode("Flora");
            var otherNode = new TreeNode("Other");

            for (int i = 0; i < stores.Length; i++)
            {
                try
                {
                    var record = stores.Get(i) as JsonObject;
                    if (record == null) continue;

                    string discType = record.GetString("DD") ?? record.GetString("DT")
                                      ?? record.GetString("DiscoveryType") ?? "";
                    string name = record.GetString("N") ?? record.GetString("Name")
                                  ?? record.GetString("CustomName") ?? $"Discovery {i}";

                    var node = new TreeNode(name);

                    switch (discType)
                    {
                        case "SolarSystem":
                        case "System":
                        case "1":
                            systemsNode.Nodes.Add(node);
                            systemCount++;
                            break;
                        case "Planet":
                        case "2":
                            planetsNode.Nodes.Add(node);
                            planetCount++;
                            break;
                        case "Animal":
                        case "Fauna":
                        case "3":
                            faunaNode.Nodes.Add(node);
                            faunaCount++;
                            break;
                        case "Flora":
                        case "4":
                            floraNode.Nodes.Add(node);
                            floraCount++;
                            break;
                        default:
                            otherNode.Nodes.Add(node);
                            otherCount++;
                            break;
                    }
                }
                catch { }
            }

            if (systemsNode.Nodes.Count > 0) { systemsNode.Text = $"Star Systems ({systemCount})"; _discoveryTree.Nodes.Add(systemsNode); }
            if (planetsNode.Nodes.Count > 0) { planetsNode.Text = $"Planets ({planetCount})"; _discoveryTree.Nodes.Add(planetsNode); }
            if (faunaNode.Nodes.Count > 0) { faunaNode.Text = $"Fauna ({faunaCount})"; _discoveryTree.Nodes.Add(faunaNode); }
            if (floraNode.Nodes.Count > 0) { floraNode.Text = $"Flora ({floraCount})"; _discoveryTree.Nodes.Add(floraNode); }
            if (otherNode.Nodes.Count > 0) { otherNode.Text = $"Other ({otherCount})"; _discoveryTree.Nodes.Add(otherNode); }

            int total = systemCount + planetCount + faunaCount + floraCount + otherCount;
            _countLabel.Text = $"Total discoveries: {total} (Systems: {systemCount}, Planets: {planetCount}, Fauna: {faunaCount}, Flora: {floraCount})";
        }
        catch { _countLabel.Text = "Failed to load discovery data."; }
    }

    public void SaveData(JsonObject saveData)
    {
        // Discoveries are read-only in this panel
    }
}
