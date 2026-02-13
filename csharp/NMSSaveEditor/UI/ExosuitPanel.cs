using NMSSaveEditor.Data;
using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class ExosuitPanel : UserControl
{
    private readonly TabControl _invTabs;
    private readonly InventoryGridPanel _generalGrid;
    private readonly InventoryGridPanel _techGrid;
    private JsonObject? _playerState;

    public ExosuitPanel()
    {
        SuspendLayout();

        var layout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 1,
            RowCount = 2,
            Padding = new Padding(10)
        };
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));

        var titleLabel = new Label
        {
            Text = "Exosuit Inventory",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 5)
        };
        layout.Controls.Add(titleLabel, 0, 0);

        _generalGrid = new InventoryGridPanel { Dock = DockStyle.Fill };
        _techGrid = new InventoryGridPanel { Dock = DockStyle.Fill };

        _invTabs = new TabControl { Dock = DockStyle.Fill };
        var generalPage = new TabPage("General");
        generalPage.Controls.Add(_generalGrid);
        var techPage = new TabPage("Technology");
        techPage.Controls.Add(_techGrid);
        _invTabs.TabPages.Add(generalPage);
        _invTabs.TabPages.Add(techPage);
        layout.Controls.Add(_invTabs, 0, 1);

        Controls.Add(layout);
        ResumeLayout(false);
        PerformLayout();
    }

    public void SetDatabase(GameItemDatabase? database)
    {
        _generalGrid.SetDatabase(database);
        _techGrid.SetDatabase(database);
    }

    public void SetIconManager(IconManager? iconManager)
    {
        _generalGrid.SetIconManager(iconManager);
        _techGrid.SetIconManager(iconManager);
    }

    public void LoadData(JsonObject saveData)
    {
        try
        {
            _playerState = saveData.GetObject("PlayerStateData");
            if (_playerState == null) return;

            _generalGrid.LoadInventory(_playerState.GetObject("Inventory"));
            _techGrid.LoadInventory(_playerState.GetObject("Inventory_TechOnly"));
        }
        catch { /* Save structure varies between versions */ }
    }

    public void SaveData(JsonObject saveData)
    {
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            _generalGrid.SaveInventory(playerState.GetObject("Inventory"));
            _techGrid.SaveInventory(playerState.GetObject("Inventory_TechOnly"));
        }
        catch { }
    }
}
