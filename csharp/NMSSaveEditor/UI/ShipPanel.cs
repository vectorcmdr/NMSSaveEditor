using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class ShipPanel : UserControl
{
    private readonly ComboBox _shipSelector;
    private readonly TextBox _shipName;
    private readonly TextBox _shipSeed;
    private readonly TextBox _shipType;
    private readonly DataGridView _inventoryGrid;
    private JsonArray? _shipOwnership;
    private JsonObject? _playerState;

    public ShipPanel()
    {
        SuspendLayout();

        var layout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 2,
            RowCount = 6,
            Padding = new Padding(10)
        };
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize));
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));

        var titleLabel = new Label
        {
            Text = "Ships",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 5)
        };
        layout.Controls.Add(titleLabel, 0, 0);
        layout.SetColumnSpan(titleLabel, 2);

        _shipSelector = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _shipSelector.SelectedIndexChanged += OnShipSelected;
        AddRow(layout, "Select Ship:", _shipSelector, 1);

        _shipName = new TextBox { Dock = DockStyle.Fill };
        AddRow(layout, "Name:", _shipName, 2);

        _shipType = new TextBox { Dock = DockStyle.Fill, ReadOnly = true };
        AddRow(layout, "Type:", _shipType, 3);

        _shipSeed = new TextBox { Dock = DockStyle.Fill, ReadOnly = true };
        AddRow(layout, "Seed:", _shipSeed, 4);

        _inventoryGrid = CreateInventoryGrid();
        layout.Controls.Add(_inventoryGrid, 0, 5);
        layout.SetColumnSpan(_inventoryGrid, 2);

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

    public void LoadData(JsonObject saveData)
    {
        try
        {
            _playerState = saveData.GetObject("PlayerStateData");
            if (_playerState == null) return;

            _shipOwnership = _playerState.GetArray("ShipOwnership");
            _shipSelector.Items.Clear();

            if (_shipOwnership == null) return;

            int primaryShip = 0;
            try { primaryShip = _playerState.GetInt("PrimaryShip"); } catch { }

            for (int i = 0; i < _shipOwnership.Length; i++)
            {
                try
                {
                    var ship = _shipOwnership.GetObject(i);
                    string name = ship.GetString("Name") ?? $"Ship {i + 1}";
                    _shipSelector.Items.Add(name);
                }
                catch
                {
                    _shipSelector.Items.Add($"Ship {i + 1}");
                }
            }

            if (_shipSelector.Items.Count > 0)
                _shipSelector.SelectedIndex = Math.Min(primaryShip, _shipSelector.Items.Count - 1);
        }
        catch { }
    }

    public void SaveData(JsonObject saveData)
    {
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            var ships = playerState.GetArray("ShipOwnership");
            if (ships == null || _shipSelector.SelectedIndex < 0) return;

            int idx = _shipSelector.SelectedIndex;
            if (idx >= ships.Length) return;

            var ship = ships.GetObject(idx);
            if (!string.IsNullOrEmpty(_shipName.Text))
                ship.Set("Name", _shipName.Text);

            SaveInventory(_inventoryGrid, ship.GetObject("Inventory"));
        }
        catch { }
    }

    private void OnShipSelected(object? sender, EventArgs e)
    {
        try
        {
            if (_shipOwnership == null || _shipSelector.SelectedIndex < 0) return;
            int idx = _shipSelector.SelectedIndex;
            if (idx >= _shipOwnership.Length) return;

            var ship = _shipOwnership.GetObject(idx);
            _shipName.Text = ship.GetString("Name") ?? "";

            string filename = "";
            string seed = "";
            try
            {
                var resource = ship.GetObject("Resource");
                if (resource != null)
                {
                    filename = resource.GetString("Filename") ?? "";
                    var seedArr = resource.GetArray("Seed");
                    if (seedArr != null && seedArr.Length > 1)
                        seed = seedArr.Get(1)?.ToString() ?? "";
                }
            }
            catch { }
            _shipType.Text = filename;
            _shipSeed.Text = seed;

            LoadInventory(_inventoryGrid, ship.GetObject("Inventory"));
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
