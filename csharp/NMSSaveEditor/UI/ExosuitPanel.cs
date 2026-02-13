using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class ExosuitPanel : UserControl
{
    private readonly TabControl _invTabs;
    private readonly DataGridView _generalGrid;
    private readonly DataGridView _techGrid;
    private readonly DataGridView _cargoGrid;
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

        _generalGrid = CreateInventoryGrid();
        _techGrid = CreateInventoryGrid();
        _cargoGrid = CreateInventoryGrid();

        _invTabs = new TabControl { Dock = DockStyle.Fill };
        _invTabs.TabPages.Add(CreateGridTab("General", _generalGrid));
        _invTabs.TabPages.Add(CreateGridTab("Technology", _techGrid));
        _invTabs.TabPages.Add(CreateGridTab("Cargo", _cargoGrid));
        layout.Controls.Add(_invTabs, 0, 1);

        Controls.Add(layout);
        ResumeLayout(false);
        PerformLayout();
    }

    private static DataGridView CreateInventoryGrid()
    {
        var grid = new DataGridView
        {
            Dock = DockStyle.Fill,
            AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill,
            AllowUserToAddRows = false,
            AllowUserToDeleteRows = false,
            SelectionMode = DataGridViewSelectionMode.FullRowSelect,
            RowHeadersVisible = false
        };
        grid.Columns.Add("Slot", "Slot #");
        grid.Columns.Add("ItemId", "Item ID");
        grid.Columns.Add("Amount", "Amount");
        grid.Columns.Add("MaxAmount", "Max");
        grid.Columns["Slot"]!.ReadOnly = true;
        return grid;
    }

    private static TabPage CreateGridTab(string name, DataGridView grid)
    {
        var page = new TabPage(name);
        grid.Dock = DockStyle.Fill;
        page.Controls.Add(grid);
        return page;
    }

    public void LoadData(JsonObject saveData)
    {
        try
        {
            _playerState = saveData.GetObject("PlayerStateData");
            if (_playerState == null) return;

            LoadInventory(_generalGrid, _playerState.GetObject("Inventory"));
            LoadInventory(_techGrid, _playerState.GetObject("Inventory_TechOnly"));
            LoadInventory(_cargoGrid, _playerState.GetObject("Inventory_Cargo"));
        }
        catch { /* Save structure varies between versions */ }
    }

    public void SaveData(JsonObject saveData)
    {
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            SaveInventory(_generalGrid, playerState.GetObject("Inventory"));
            SaveInventory(_techGrid, playerState.GetObject("Inventory_TechOnly"));
            SaveInventory(_cargoGrid, playerState.GetObject("Inventory_Cargo"));
        }
        catch { }
    }

    private static void LoadInventory(DataGridView grid, JsonObject? inventory)
    {
        grid.Rows.Clear();
        if (inventory == null) return;

        var slots = inventory.GetArray("Slots");
        if (slots == null) return;

        for (int i = 0; i < slots.Length; i++)
        {
            try
            {
                var slot = slots.GetObject(i);
                string itemId = slot.GetString("Id") ?? slot.GetObject("Id")?.GetString("Id") ?? "";
                int amount = 0;
                int maxAmount = 0;
                try { amount = slot.GetInt("Amount"); } catch { }
                try { maxAmount = slot.GetInt("MaxAmount"); } catch { }
                grid.Rows.Add(i.ToString(), itemId, amount.ToString(), maxAmount.ToString());
            }
            catch { }
        }
    }

    private static void SaveInventory(DataGridView grid, JsonObject? inventory)
    {
        if (inventory == null) return;
        var slots = inventory.GetArray("Slots");
        if (slots == null) return;

        for (int i = 0; i < grid.Rows.Count && i < slots.Length; i++)
        {
            try
            {
                var slot = slots.GetObject(i);
                var row = grid.Rows[i];
                if (int.TryParse(row.Cells["Amount"].Value?.ToString(), out int amount))
                    slot.Set("Amount", amount);
                if (int.TryParse(row.Cells["MaxAmount"].Value?.ToString(), out int maxAmount))
                    slot.Set("MaxAmount", maxAmount);
            }
            catch { }
        }
    }
}
