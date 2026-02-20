using NMSE.Data;
using NMSE.Models;

namespace NMSE.UI;

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
    private readonly TabControl _invTabs;
    private readonly InventoryGridPanel _inventoryGrid;
    private readonly InventoryGridPanel _techGrid;
    private JsonArray? _vehicleOwnership;
    private readonly List<int> _addedVehicleIndices = new();

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

        _inventoryGrid = new InventoryGridPanel { Dock = DockStyle.Fill };
        _techGrid = new InventoryGridPanel { Dock = DockStyle.Fill };

        _invTabs = new TabControl { Dock = DockStyle.Fill };
        var invPage = new TabPage("Inventory");
        invPage.Controls.Add(_inventoryGrid);
        var techPage = new TabPage("Technology");
        techPage.Controls.Add(_techGrid);
        _invTabs.TabPages.Add(invPage);
        _invTabs.TabPages.Add(techPage);

        layout.Controls.Add(_invTabs, 0, 2);
        layout.SetColumnSpan(_invTabs, 2);

        Controls.Add(layout);

        _inventoryGrid.SetMaxSupportedLabel("");
        _techGrid.SetMaxSupportedLabel("");

        ResumeLayout(false);
        PerformLayout();
    }

    public void SetDatabase(GameItemDatabase? database)
    {
        _inventoryGrid.SetDatabase(database);
        _techGrid.SetDatabase(database);
    }

    public void SetIconManager(IconManager? iconManager)
    {
        _inventoryGrid.SetIconManager(iconManager);
        _techGrid.SetIconManager(iconManager);
    }

    public void LoadData(JsonObject saveData)
    {
        _vehicleSelector.Items.Clear();
        _addedVehicleIndices.Clear();
        _inventoryGrid.LoadInventory(null);
        _techGrid.LoadInventory(null);
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            _vehicleOwnership = playerState.GetArray("VehicleOwnership");
            if (_vehicleOwnership == null) return;

            foreach (var (index, name) in VehicleTypes)
            {
                if (index < _vehicleOwnership.Length)
                {
                    _vehicleSelector.Items.Add(name);
                    _addedVehicleIndices.Add(index);
                }
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
            if (selIdx < 0 || selIdx >= _addedVehicleIndices.Count) return;
            int arrIdx = _addedVehicleIndices[selIdx];

            var vehicle = vehicles.GetObject(arrIdx);
            _inventoryGrid.SaveInventory(vehicle.GetObject("Inventory"));
            _techGrid.SaveInventory(vehicle.GetObject("Inventory_TechOnly"));
        }
        catch { }
    }

    private void OnVehicleSelected(object? sender, EventArgs e)
    {
        try
        {
            if (_vehicleOwnership == null || _vehicleSelector.SelectedIndex < 0) return;
            int selIdx = _vehicleSelector.SelectedIndex;
            if (selIdx >= _addedVehicleIndices.Count) return;
            int arrIdx = _addedVehicleIndices[selIdx];

            var vehicle = _vehicleOwnership.GetObject(arrIdx);
            _inventoryGrid.LoadInventory(vehicle.GetObject("Inventory"));
            _techGrid.LoadInventory(vehicle.GetObject("Inventory_TechOnly"));
        }
        catch { }
    }
}
