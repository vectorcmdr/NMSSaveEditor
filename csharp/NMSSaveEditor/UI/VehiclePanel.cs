using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class VehiclePanel : UserControl
{
    private static readonly (int Index, string Name)[] VehicleTypes =
    [
        (0, "Roamer"),
        (1, "Nomad"),
        (2, "Colossus"),
        (3, "Pilgrim"),
        (5, "Nautilon"),
        (6, "Minotaur")
    ];

    private readonly ComboBox _vehicleSelector;
    private readonly DataGridView _inventoryGrid;
    private JsonArray? _vehicleOwnership;

    public VehiclePanel()
    {
        SuspendLayout();

        var layout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 2,
            RowCount = 3,
            Padding = new Padding(10)
        };
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize));
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));

        var titleLabel = new Label
        {
            Text = "Vehicles",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 5)
        };
        layout.Controls.Add(titleLabel, 0, 0);
        layout.SetColumnSpan(titleLabel, 2);

        _vehicleSelector = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _vehicleSelector.SelectedIndexChanged += OnVehicleSelected;
        var lbl = new Label { Text = "Vehicle:", AutoSize = true, Anchor = AnchorStyles.Left, Padding = new Padding(0, 5, 10, 0) };
        layout.Controls.Add(lbl, 0, 1);
        layout.Controls.Add(_vehicleSelector, 1, 1);

        _inventoryGrid = new DataGridView
        {
            Dock = DockStyle.Fill,
            AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill,
            AllowUserToAddRows = false,
            AllowUserToDeleteRows = false,
            SelectionMode = DataGridViewSelectionMode.FullRowSelect,
            RowHeadersVisible = false
        };
        _inventoryGrid.Columns.Add("Slot", "Slot #");
        _inventoryGrid.Columns.Add("ItemId", "Item ID");
        _inventoryGrid.Columns.Add("Amount", "Amount");
        _inventoryGrid.Columns.Add("MaxAmount", "Max");
        _inventoryGrid.Columns["Slot"]!.ReadOnly = true;
        layout.Controls.Add(_inventoryGrid, 0, 2);
        layout.SetColumnSpan(_inventoryGrid, 2);

        Controls.Add(layout);
        ResumeLayout(false);
        PerformLayout();
    }

    public void LoadData(JsonObject saveData)
    {
        _vehicleSelector.Items.Clear();
        _inventoryGrid.Rows.Clear();
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            _vehicleOwnership = playerState.GetArray("VehicleOwnership");
            if (_vehicleOwnership == null) return;

            foreach (var (index, name) in VehicleTypes)
            {
                if (index < _vehicleOwnership.Length)
                    _vehicleSelector.Items.Add(name);
            }

            if (_vehicleSelector.Items.Count > 0)
                _vehicleSelector.SelectedIndex = 0;
        }
        catch { }
    }

    public void SaveData(JsonObject saveData)
    {
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            var vehicles = playerState.GetArray("VehicleOwnership");
            if (vehicles == null || _vehicleSelector.SelectedIndex < 0) return;

            int selIdx = _vehicleSelector.SelectedIndex;
            if (selIdx >= VehicleTypes.Length) return;
            int arrIdx = VehicleTypes[selIdx].Index;
            if (arrIdx >= vehicles.Length) return;

            var vehicle = vehicles.GetObject(arrIdx);
            SaveInventory(_inventoryGrid, vehicle.GetObject("Inventory"));
        }
        catch { }
    }

    private void OnVehicleSelected(object? sender, EventArgs e)
    {
        _inventoryGrid.Rows.Clear();
        try
        {
            if (_vehicleOwnership == null || _vehicleSelector.SelectedIndex < 0) return;
            int selIdx = _vehicleSelector.SelectedIndex;
            if (selIdx >= VehicleTypes.Length) return;
            int arrIdx = VehicleTypes[selIdx].Index;
            if (arrIdx >= _vehicleOwnership.Length) return;

            var vehicle = _vehicleOwnership.GetObject(arrIdx);
            LoadInventory(_inventoryGrid, vehicle.GetObject("Inventory"));
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
