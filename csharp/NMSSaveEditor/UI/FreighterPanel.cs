using NMSSaveEditor.Data;
using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class FreighterPanel : UserControl
{
    private readonly TextBox _freighterName;
    private readonly TextBox _freighterClass;
    private readonly TextBox _freighterSeed;
    private readonly TabControl _invTabs;
    private readonly InventoryGridPanel _generalGrid;
    private readonly InventoryGridPanel _techGrid;
    private readonly InventoryGridPanel _cargoGrid;

    public FreighterPanel()
    {
        SuspendLayout();

        var layout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 2,
            RowCount = 5,
            Padding = new Padding(10)
        };
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize));
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));

        var titleLabel = new Label
        {
            Text = "Freighter",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 5)
        };
        layout.Controls.Add(titleLabel, 0, 0);
        layout.SetColumnSpan(titleLabel, 2);

        _freighterName = new TextBox { Dock = DockStyle.Fill };
        AddRow(layout, "Name:", _freighterName, 1);

        _freighterClass = new TextBox { Dock = DockStyle.Fill, ReadOnly = true };
        AddRow(layout, "Class:", _freighterClass, 2);

        _freighterSeed = new TextBox { Dock = DockStyle.Fill, ReadOnly = true };
        AddRow(layout, "Seed:", _freighterSeed, 3);

        _generalGrid = new InventoryGridPanel { Dock = DockStyle.Fill };
        _techGrid = new InventoryGridPanel { Dock = DockStyle.Fill };
        _cargoGrid = new InventoryGridPanel { Dock = DockStyle.Fill };

        _invTabs = new TabControl { Dock = DockStyle.Fill };
        var generalPage = new TabPage("General");
        generalPage.Controls.Add(_generalGrid);
        var techPage = new TabPage("Technology");
        techPage.Controls.Add(_techGrid);
        var cargoPage = new TabPage("Cargo");
        cargoPage.Controls.Add(_cargoGrid);
        _invTabs.TabPages.Add(generalPage);
        _invTabs.TabPages.Add(techPage);
        _invTabs.TabPages.Add(cargoPage);
        layout.Controls.Add(_invTabs, 0, 4);
        layout.SetColumnSpan(_invTabs, 2);

        Controls.Add(layout);
        ResumeLayout(false);
        PerformLayout();
    }

    public void SetDatabase(GameItemDatabase? database)
    {
        _generalGrid.SetDatabase(database);
        _techGrid.SetDatabase(database);
        _cargoGrid.SetDatabase(database);
    }

    public void SetIconManager(IconManager? iconManager)
    {
        _generalGrid.SetIconManager(iconManager);
        _techGrid.SetIconManager(iconManager);
        _cargoGrid.SetIconManager(iconManager);
    }

    private static void AddRow(TableLayoutPanel layout, string label, Control field, int row)
    {
        var lbl = new Label { Text = label, AutoSize = true, Anchor = AnchorStyles.Left, Padding = new Padding(0, 5, 10, 0) };
        layout.Controls.Add(lbl, 0, row);
        layout.Controls.Add(field, 1, row);
    }

    public void LoadData(JsonObject saveData)
    {
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            _freighterName.Text = playerState.GetString("PlayerFreighterName") ?? "";

            try
            {
                var freighterInv = playerState.GetObject("FreighterInventory");
                var classObj = freighterInv?.GetObject("Class");
                _freighterClass.Text = classObj?.GetString("InventoryClass") ?? "";
            }
            catch { _freighterClass.Text = ""; }

            try
            {
                var currentFreighter = playerState.GetObject("CurrentFreighter");
                if (currentFreighter != null)
                {
                    var seedArr = currentFreighter.GetArray("Seed");
                    if (seedArr != null && seedArr.Length > 1)
                        _freighterSeed.Text = seedArr.Get(1)?.ToString() ?? "";
                    else
                        _freighterSeed.Text = currentFreighter.GetString("Filename") ?? "";
                }
            }
            catch { _freighterSeed.Text = ""; }

            _generalGrid.LoadInventory(playerState.GetObject("FreighterInventory"));
            _techGrid.LoadInventory(playerState.GetObject("FreighterInventory_TechOnly"));
            _cargoGrid.LoadInventory(playerState.GetObject("FreighterInventory_Cargo"));
        }
        catch { }
    }

    public void SaveData(JsonObject saveData)
    {
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            if (!string.IsNullOrEmpty(_freighterName.Text))
                playerState.Set("PlayerFreighterName", _freighterName.Text);

            _generalGrid.SaveInventory(playerState.GetObject("FreighterInventory"));
            _techGrid.SaveInventory(playerState.GetObject("FreighterInventory_TechOnly"));
            _cargoGrid.SaveInventory(playerState.GetObject("FreighterInventory_Cargo"));
        }
        catch { }
    }
}
