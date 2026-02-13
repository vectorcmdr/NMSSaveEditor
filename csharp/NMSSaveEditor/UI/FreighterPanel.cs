using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class FreighterPanel : UserControl
{
    private readonly TextBox _freighterName;
    private readonly TextBox _freighterClass;
    private readonly TextBox _freighterSeed;
    private readonly TabControl _invTabs;
    private readonly DataGridView _generalGrid;
    private readonly DataGridView _techGrid;
    private readonly DataGridView _cargoGrid;

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

        _generalGrid = CreateInventoryGrid();
        _techGrid = CreateInventoryGrid();
        _cargoGrid = CreateInventoryGrid();

        _invTabs = new TabControl { Dock = DockStyle.Fill };
        _invTabs.TabPages.Add(CreateGridTab("General", _generalGrid));
        _invTabs.TabPages.Add(CreateGridTab("Technology", _techGrid));
        _invTabs.TabPages.Add(CreateGridTab("Cargo", _cargoGrid));
        layout.Controls.Add(_invTabs, 0, 4);
        layout.SetColumnSpan(_invTabs, 2);

        Controls.Add(layout);
        ResumeLayout(false);
        PerformLayout();
    }

    private static void AddRow(TableLayoutPanel layout, string label, Control field, int row)
    {
        var lbl = new Label { Text = label, AutoSize = true, Anchor = AnchorStyles.Left, Padding = new Padding(0, 5, 10, 0) };
        layout.Controls.Add(lbl, 0, row);
        layout.Controls.Add(field, 1, row);
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

            LoadInventory(_generalGrid, playerState.GetObject("FreighterInventory"));
            LoadInventory(_techGrid, playerState.GetObject("FreighterInventory_TechOnly"));
            LoadInventory(_cargoGrid, playerState.GetObject("FreighterInventory_Cargo"));
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

            SaveInventory(_generalGrid, playerState.GetObject("FreighterInventory"));
            SaveInventory(_techGrid, playerState.GetObject("FreighterInventory_TechOnly"));
            SaveInventory(_cargoGrid, playerState.GetObject("FreighterInventory_Cargo"));
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
