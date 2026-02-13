using NMSSaveEditor.Data;
using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class ShipPanel : UserControl
{
    private static readonly string[] ShipClasses = { "C", "B", "A", "S" };

    private readonly ComboBox _shipSelector;
    private readonly TextBox _shipName;
    private readonly ComboBox _shipClass;
    private readonly TextBox _shipType;
    private readonly TextBox _shipSeed;
    private readonly Button _generateSeedBtn;
    private readonly CheckBox _useOldColours;
    private readonly NumericUpDown _damageField;
    private readonly NumericUpDown _shieldField;
    private readonly NumericUpDown _hyperdriveField;
    private readonly NumericUpDown _maneuverField;
    private readonly Button _deleteBtn;
    private readonly Button _exportBtn;
    private readonly Button _importBtn;
    private readonly TabControl _invTabs;
    private readonly InventoryGridPanel _inventoryGrid;
    private readonly InventoryGridPanel _techGrid;
    private JsonArray? _shipOwnership;
    private JsonObject? _playerState;
    private GameItemDatabase? _database;
    private readonly Random _rng = new();

    public ShipPanel()
    {
        SuspendLayout();

        var layout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 3,
            RowCount = 14,
            Padding = new Padding(10)
        };
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize));
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100));
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize));
        for (int i = 0; i < 13; i++)
            layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));

        int row = 0;
        var titleLabel = new Label
        {
            Text = "Ships",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 5)
        };
        layout.Controls.Add(titleLabel, 0, row);
        layout.SetColumnSpan(titleLabel, 3);
        row++;

        // Ship selector
        _shipSelector = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _shipSelector.SelectedIndexChanged += OnShipSelected;
        AddRow(layout, "Select Ship:", _shipSelector, row); row++;

        // Name
        _shipName = new TextBox { Dock = DockStyle.Fill };
        AddRow(layout, "Name:", _shipName, row); row++;

        // Type (read-only)
        _shipType = new TextBox { Dock = DockStyle.Fill, ReadOnly = true };
        AddRow(layout, "Type:", _shipType, row); row++;

        // Class
        _shipClass = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _shipClass.Items.AddRange(ShipClasses);
        AddRow(layout, "Class:", _shipClass, row); row++;

        // Seed with Generate button
        var seedPanel = new Panel { Dock = DockStyle.Fill, Height = 26 };
        _shipSeed = new TextBox { Dock = DockStyle.Fill };
        _generateSeedBtn = new Button { Text = "Generate", Dock = DockStyle.Right, Width = 70 };
        _generateSeedBtn.Click += (s, e) =>
        {
            byte[] bytes = new byte[8];
            _rng.NextBytes(bytes);
            _shipSeed.Text = "0x" + BitConverter.ToString(bytes).Replace("-", "");
        };
        seedPanel.Controls.Add(_shipSeed);
        seedPanel.Controls.Add(_generateSeedBtn);
        AddRow(layout, "Seed:", seedPanel, row); row++;

        // Use Old Colours
        _useOldColours = new CheckBox { Text = "Use Old Colours", AutoSize = true };
        layout.Controls.Add(new Label(), 0, row);
        layout.Controls.Add(_useOldColours, 1, row);
        row++;

        // Base Stats separator
        var statsLabel = new Label
        {
            Text = "Base Stats",
            Font = new Font(Font.FontFamily, 10, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 8, 0, 4)
        };
        layout.Controls.Add(statsLabel, 0, row);
        layout.SetColumnSpan(statsLabel, 3);
        row++;

        _damageField = new NumericUpDown { Dock = DockStyle.Fill, DecimalPlaces = 2, Minimum = 0, Maximum = 999999, Increment = 0.01m };
        AddRow(layout, "Damage:", _damageField, row); row++;

        _shieldField = new NumericUpDown { Dock = DockStyle.Fill, DecimalPlaces = 2, Minimum = 0, Maximum = 999999, Increment = 0.01m };
        AddRow(layout, "Shield:", _shieldField, row); row++;

        _hyperdriveField = new NumericUpDown { Dock = DockStyle.Fill, DecimalPlaces = 2, Minimum = 0, Maximum = 999999, Increment = 0.01m };
        AddRow(layout, "Hyperdrive:", _hyperdriveField, row); row++;

        _maneuverField = new NumericUpDown { Dock = DockStyle.Fill, DecimalPlaces = 2, Minimum = 0, Maximum = 999999, Increment = 0.01m };
        AddRow(layout, "Maneuverability:", _maneuverField, row); row++;

        // Buttons panel
        var buttonPanel = new FlowLayoutPanel
        {
            Dock = DockStyle.Fill,
            AutoSize = true,
            FlowDirection = FlowDirection.LeftToRight
        };
        _deleteBtn = new Button { Text = "Delete Ship", Width = 90 };
        _deleteBtn.Click += OnDeleteShip;
        _exportBtn = new Button { Text = "Export", Width = 70 };
        _exportBtn.Click += OnExportShip;
        _importBtn = new Button { Text = "Import", Width = 70 };
        _importBtn.Click += OnImportShip;
        buttonPanel.Controls.Add(_deleteBtn);
        buttonPanel.Controls.Add(_exportBtn);
        buttonPanel.Controls.Add(_importBtn);
        layout.Controls.Add(buttonPanel, 0, row);
        layout.SetColumnSpan(buttonPanel, 3);
        row++;

        // Inventory tabs (2 tabs: Cargo, Technology)
        _inventoryGrid = new InventoryGridPanel { Dock = DockStyle.Fill };
        _techGrid = new InventoryGridPanel { Dock = DockStyle.Fill };

        _invTabs = new TabControl { Dock = DockStyle.Fill };
        var invPage = new TabPage("Cargo");
        invPage.Controls.Add(_inventoryGrid);
        var techPage = new TabPage("Technology");
        techPage.Controls.Add(_techGrid);
        _invTabs.TabPages.Add(invPage);
        _invTabs.TabPages.Add(techPage);

        layout.Controls.Add(_invTabs, 0, row);
        layout.SetColumnSpan(_invTabs, 3);

        Controls.Add(layout);
        ResumeLayout(false);
        PerformLayout();
    }

    public void SetDatabase(GameItemDatabase? database)
    {
        _database = database;
        _inventoryGrid.SetDatabase(database);
        _techGrid.SetDatabase(database);
    }

    public void SetIconManager(IconManager? iconManager)
    {
        _inventoryGrid.SetIconManager(iconManager);
        _techGrid.SetIconManager(iconManager);
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
                    var resource = ship.GetObject("Resource");
                    bool hasSeed = false;
                    try
                    {
                        var seedArr = resource?.GetArray("Seed");
                        if (seedArr != null && seedArr.Length > 0)
                            hasSeed = seedArr.GetBool(0);
                    }
                    catch { }

                    if (!hasSeed) continue;

                    string name = ship.GetString("Name") ?? "";
                    if (string.IsNullOrEmpty(name))
                        name = $"Ship {i + 1}";
                    _shipSelector.Items.Add(new ShipListItem(name, i));
                }
                catch
                {
                    _shipSelector.Items.Add(new ShipListItem($"Ship {i + 1}", i));
                }
            }

            if (_shipSelector.Items.Count > 0)
            {
                // Find the item matching PrimaryShip index
                int selectIdx = 0;
                for (int i = 0; i < _shipSelector.Items.Count; i++)
                {
                    if (((ShipListItem)_shipSelector.Items[i]!).DataIndex == primaryShip)
                    {
                        selectIdx = i;
                        break;
                    }
                }
                _shipSelector.SelectedIndex = selectIdx;
            }
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

            var item = (ShipListItem)_shipSelector.Items[_shipSelector.SelectedIndex]!;
            int idx = item.DataIndex;
            if (idx >= ships.Length) return;

            var ship = ships.GetObject(idx);

            // Save name
            if (!string.IsNullOrEmpty(_shipName.Text))
                ship.Set("Name", _shipName.Text);

            // Save class to all three inventories
            if (_shipClass.SelectedIndex >= 0)
            {
                string cls = ShipClasses[_shipClass.SelectedIndex];
                SetInventoryClass(ship.GetObject("Inventory"), cls);
                SetInventoryClass(ship.GetObject("Inventory_TechOnly"), cls);
                SetInventoryClass(ship.GetObject("Inventory_Cargo"), cls);
            }

            // Save seed
            try
            {
                var resource = ship.GetObject("Resource");
                var seedArr = resource?.GetArray("Seed");
                if (seedArr != null && seedArr.Length > 1 && !string.IsNullOrEmpty(_shipSeed.Text))
                    seedArr.Set(1, _shipSeed.Text);
            }
            catch { }

            // Save use old colours
            try { playerState.Set("ShipUsesLegacyColours", _useOldColours.Checked); } catch { }

            // Save base stats to ship's Inventory.BaseStatValues
            var shipInv = ship.GetObject("Inventory");
            WriteBaseStatValue(shipInv, "^SHIP_DAMAGE", (double)_damageField.Value);
            WriteBaseStatValue(shipInv, "^SHIP_SHIELD", (double)_shieldField.Value);
            WriteBaseStatValue(shipInv, "^SHIP_HYPERDRIVE", (double)_hyperdriveField.Value);
            WriteBaseStatValue(shipInv, "^SHIP_AGILE", (double)_maneuverField.Value);

            // Save inventories
            _inventoryGrid.SaveInventory(ship.GetObject("Inventory"));
            _techGrid.SaveInventory(ship.GetObject("Inventory_TechOnly"));
        }
        catch { }
    }

    private void OnShipSelected(object? sender, EventArgs e)
    {
        try
        {
            if (_shipOwnership == null || _shipSelector.SelectedIndex < 0) return;
            var item = (ShipListItem)_shipSelector.Items[_shipSelector.SelectedIndex]!;
            int idx = item.DataIndex;
            if (idx >= _shipOwnership.Length) return;

            var ship = _shipOwnership.GetObject(idx);
            _shipName.Text = ship.GetString("Name") ?? "";

            // Type (filename)
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

            // Class from Inventory.Class.InventoryClass
            string cls = "";
            try
            {
                var inv = ship.GetObject("Inventory");
                var classObj = inv?.GetObject("Class");
                cls = classObj?.GetString("InventoryClass") ?? "";
            }
            catch { }
            int classIdx = Array.IndexOf(ShipClasses, cls);
            _shipClass.SelectedIndex = classIdx >= 0 ? classIdx : -1;

            // Use Old Colours
            try
            {
                if (_playerState != null)
                    _useOldColours.Checked = _playerState.GetBool("ShipUsesLegacyColours");
            }
            catch { _useOldColours.Checked = false; }

            // Load all inventories
            _inventoryGrid.LoadInventory(ship.GetObject("Inventory"));
            _techGrid.LoadInventory(ship.GetObject("Inventory_TechOnly"));

            // Load base stats from ship's Inventory.BaseStatValues
            var shipInv = ship.GetObject("Inventory");
            try { _damageField.Value = (decimal)ReadBaseStatValue(shipInv, "^SHIP_DAMAGE"); } catch { _damageField.Value = 0; }
            try { _shieldField.Value = (decimal)ReadBaseStatValue(shipInv, "^SHIP_SHIELD"); } catch { _shieldField.Value = 0; }
            try { _hyperdriveField.Value = (decimal)ReadBaseStatValue(shipInv, "^SHIP_HYPERDRIVE"); } catch { _hyperdriveField.Value = 0; }
            try { _maneuverField.Value = (decimal)ReadBaseStatValue(shipInv, "^SHIP_AGILE"); } catch { _maneuverField.Value = 0; }
        }
        catch { }
    }

    private void OnDeleteShip(object? sender, EventArgs e)
    {
        try
        {
            if (_shipOwnership == null || _shipSelector.SelectedIndex < 0) return;

            var result = MessageBox.Show(
                "Are you sure you want to delete this ship?",
                "Delete Ship",
                MessageBoxButtons.YesNo,
                MessageBoxIcon.Warning);

            if (result != DialogResult.Yes) return;

            var item = (ShipListItem)_shipSelector.Items[_shipSelector.SelectedIndex]!;
            int idx = item.DataIndex;
            if (idx >= _shipOwnership.Length) return;

            var ship = _shipOwnership.GetObject(idx);
            var resource = ship.GetObject("Resource");
            if (resource != null)
            {
                resource.Set("Filename", "");
                var seedArr = resource.GetArray("Seed");
                if (seedArr != null && seedArr.Length > 1)
                {
                    seedArr.Set(0, false);
                    seedArr.Set(1, "0x0");
                }
            }

            // Refresh the list
            int selIdx = _shipSelector.SelectedIndex;
            _shipSelector.Items.RemoveAt(selIdx);
            if (_shipSelector.Items.Count > 0)
                _shipSelector.SelectedIndex = Math.Min(selIdx, _shipSelector.Items.Count - 1);
        }
        catch { }
    }

    private void OnExportShip(object? sender, EventArgs e)
    {
        try
        {
            if (_shipOwnership == null || _shipSelector.SelectedIndex < 0) return;

            var item = (ShipListItem)_shipSelector.Items[_shipSelector.SelectedIndex]!;
            int idx = item.DataIndex;
            if (idx >= _shipOwnership.Length) return;

            var ship = _shipOwnership.GetObject(idx);

            using var dialog = new SaveFileDialog
            {
                Filter = "JSON files (*.json)|*.json|All files (*.*)|*.*",
                DefaultExt = "json",
                FileName = $"ship_{_shipName.Text}.json"
            };

            if (dialog.ShowDialog() == DialogResult.OK)
                ship.ExportToFile(dialog.FileName);
        }
        catch (Exception ex)
        {
            MessageBox.Show($"Export failed: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
    }

    private void OnImportShip(object? sender, EventArgs e)
    {
        try
        {
            if (_shipOwnership == null || _shipSelector.SelectedIndex < 0) return;

            using var dialog = new OpenFileDialog
            {
                Filter = "JSON files (*.json)|*.json|All files (*.*)|*.*"
            };

            if (dialog.ShowDialog() != DialogResult.OK) return;

            var imported = JsonObject.ImportFromFile(dialog.FileName);
            var item = (ShipListItem)_shipSelector.Items[_shipSelector.SelectedIndex]!;
            int idx = item.DataIndex;
            if (idx >= _shipOwnership.Length) return;

            var ship = _shipOwnership.GetObject(idx);

            // Copy all properties from imported ship to current slot
            foreach (var name in imported.Names())
                ship.Set(name, imported.Get(name));

            // Refresh display
            OnShipSelected(this, EventArgs.Empty);
        }
        catch (Exception ex)
        {
            MessageBox.Show($"Import failed: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
    }

    private static void SetInventoryClass(JsonObject? inventory, string cls)
    {
        if (inventory == null) return;
        try
        {
            var classObj = inventory.GetObject("Class");
            classObj?.Set("InventoryClass", cls);
        }
        catch { }
    }

    private static double ReadBaseStatValue(JsonObject? inventory, string statId)
    {
        if (inventory == null) return 0.0;
        try
        {
            var baseStatValues = inventory.GetArray("BaseStatValues");
            if (baseStatValues == null) return 0.0;
            for (int i = 0; i < baseStatValues.Length; i++)
            {
                var entry = baseStatValues.GetObject(i);
                if (entry.GetString("BaseStatID") == statId)
                    return entry.GetDouble("Value");
            }
        }
        catch { }
        return 0.0;
    }

    private static void WriteBaseStatValue(JsonObject? inventory, string statId, double value)
    {
        if (inventory == null) return;
        try
        {
            var baseStatValues = inventory.GetArray("BaseStatValues");
            if (baseStatValues == null) return;
            for (int i = 0; i < baseStatValues.Length; i++)
            {
                var entry = baseStatValues.GetObject(i);
                if (entry.GetString("BaseStatID") == statId)
                {
                    entry.Set("Value", value);
                    return;
                }
            }
        }
        catch { }
    }

    /// <summary>Helper to track the original array index for each ship in the selector.</summary>
    private sealed class ShipListItem
    {
        public string DisplayName { get; }
        public int DataIndex { get; }

        public ShipListItem(string displayName, int dataIndex)
        {
            DisplayName = displayName;
            DataIndex = dataIndex;
        }

        public override string ToString() => DisplayName;
    }
}
