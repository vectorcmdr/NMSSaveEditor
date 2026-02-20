using NMSE.Data;
using NMSE.Models;

namespace NMSE.UI;

public class StarshipPanel : UserControl
{
    private static readonly string[] ShipClasses = { "C", "B", "A", "S" };

    private readonly ComboBox _shipSelector;
    private readonly TextBox _shipName;
    private readonly ComboBox _shipClass;
    private readonly ComboBox _shipType;
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

    // Unified ship info dictionary, keyed by MBIN filename
    private static readonly Dictionary<string, (string DisplayName, string[] Keywords, string CargoLabel, string TechLabel)> ShipInfo =
        new(StringComparer.OrdinalIgnoreCase)
        {
            ["MODELS/COMMON/SPACECRAFT/DROPSHIPS/DROPSHIP_PROC.SCENE.MBIN"] = ("Hauler", new[] { "DROPSHIP" }, "Max Supported: 10x12", "Max Supported: 10x6"),
            ["MODELS/COMMON/SPACECRAFT/SCIENTIFIC/SCIENTIFIC_PROC.SCENE.MBIN"] = ("Explorer", new[] { "SCIENTIFIC" }, "Max Supported: 10x11", "Max Supported: 10x6"),
            ["MODELS/COMMON/SPACECRAFT/SHUTTLE/SHUTTLE_PROC.SCENE.MBIN"] = ("Shuttle", new[] { "SHUTTLE" }, "Max Supported: 10x11", "Max Supported: 10x6"),
            ["MODELS/COMMON/SPACECRAFT/FIGHTERS/FIGHTER_PROC.SCENE.MBIN"] = ("Fighter", new[] { "FIGHTER" }, "Max Supported: 10x10", "Max Supported: 10x6"),
            ["MODELS/COMMON/SPACECRAFT/S-CLASS/S-CLASS_PROC.SCENE.MBIN"] = ("Exotic", new[] { "EXOTIC" }, "Max Supported: 10x10 + 5", "Max Supported: 10x6"),
            ["MODELS/COMMON/SPACECRAFT/S-CLASS/BIOPARTS/BIOSHIP_PROC.SCENE.MBIN"] = ("Living Ship", new[] { "BIOSHIP" }, "Max Supported: 10x12", "Max Supported: 10x6"),
            ["MODELS/COMMON/SPACECRAFT/SAILSHIP/SAILSHIP_PROC.SCENE.MBIN"] = ("Solar", new[] { "SAILSHIP" }, "Max Supported: 10x11", "Max Supported: 10x6"),
            ["MODELS/COMMON/SPACECRAFT/FIGHTERS/VRSPEEDER.SCENE.MBIN"] = ("Utopia Speeder", new[] { "VRSPEEDER" }, "Max Supported: 10x10", "Max Supported: 10x6"),
            ["MODELS/COMMON/SPACECRAFT/FIGHTERS/FIGHTERCLASSICGOLD.SCENE.MBIN"] = ("Golden Vector", new[] { "FIGHTERCLASSICGOLD" }, "Max Supported: 10x10", "Max Supported: 10x6"),
            ["MODELS/COMMON/SPACECRAFT/FIGHTERS/FIGHTERSPECIALSWITCH.SCENE.MBIN"] = ("Horizon Vector NX (Switch)", new[] { "FIGHTERSPECIALSWITCH" }, "Max Supported: 10x10", "Max Supported: 10x6"),
            ["MODELS/COMMON/SPACECRAFT/SENTINELSHIP/SENTINELSHIP_PROC.SCENE.MBIN"] = ("Sentinel", new[] { "SENTINEL" }, "Max Supported: 10x12", "Max Supported: 10x6"),
            ["MODELS/COMMON/SPACECRAFT/FIGHTERS/WRACER.SCENE.MBIN"] = ("Starborn Runner", new[] { "WRACER.SCENE" }, "Max Supported: 10x10 + 5", "Max Supported: 10x6"),
            ["MODELS/COMMON/SPACECRAFT/FIGHTERS/WRACERSE.SCENE.MBIN"] = ("Starborn Phoenix", new[] { "WRACERSE" }, "Max Supported: 10x10 + 5", "Max Supported: 10x6"),
            ["MODELS/COMMON/SPACECRAFT/BIGGS/BIGGS.SCENE.MBIN"] = ("Corvette", new[] { "BIGGS" }, "Max Supported: 10x12", "Max Supported: 10x6"),
            ["MODELS/COMMON/SPACECRAFT/FIGHTERS/SPOOKSHIP.SCENE.MBIN"] = ("Boundary Herald", new[] { "SPOOKSHIP" }, "Max Supported: 10x10", "Max Supported: 10x6"),
            ["MODELS/COMMON/SPACECRAFT/S-CLASS/BIOPARTS/BIOFIGHTER.SCENE.MBIN"] = ("The Wraith", new[] { "BIOFIGHTER" }, "Max Supported: 10x12", "Max Supported: 10x6"),
        };

    // Lookup by filename, fallback to keyword search if not found
    private static (string DisplayName, string CargoLabel, string TechLabel) GetShipInfo(string filename)
    {
        if (!string.IsNullOrEmpty(filename) && ShipInfo.TryGetValue(filename, out var info))
            return (info.DisplayName, info.CargoLabel, info.TechLabel);

        // Fallback: keyword search
        if (!string.IsNullOrEmpty(filename))
        {
            foreach (var entry in ShipInfo.Values)
            {
                if (entry.Keywords.Any(k => filename.Contains(k, StringComparison.OrdinalIgnoreCase)))
                    return (entry.DisplayName, entry.CargoLabel, entry.TechLabel);
            }
        }
        return ("Unknown", "Max Supported: ?", "Max Supported: 10x6");
    }

    private void SetStarshipMaxSupportedLabels(string filename)
    {
        var (_, cargoLabel, techLabel) = GetShipInfo(filename);
        _inventoryGrid.SetMaxSupportedLabel(cargoLabel);
        _techGrid.SetMaxSupportedLabel(techLabel);
    }

    // For display name in OnShipSelected:
    private static string LookupShipTypeName(string filename)
    {
        var (displayName, _, _) = GetShipInfo(filename);
        return displayName;
    }

    private void OnShipTypeChanged(object? sender, EventArgs e)
    {
        // Find the filename key in ShipInfo that matches the selected display name
        string? selectedType = _shipType.SelectedItem as string;
        if (string.IsNullOrEmpty(selectedType))
            return;

        // Find the first ShipInfo entry with this display name
        string filename = ShipInfo.FirstOrDefault(
            kvp => kvp.Value.DisplayName.Equals(selectedType, StringComparison.OrdinalIgnoreCase)
        ).Key ?? "Unknown";

        SetStarshipMaxSupportedLabels(filename);
    }

    public StarshipPanel()
    {
        SuspendLayout();

        // Main layout: 1 column, 3 rows (title, details/stats+buttons, inventory)
        var mainLayout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 1,
            RowCount = 3,
            Padding = new Padding(10),
            AutoSize = true
        };

        mainLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize)); // title
        mainLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize)); // details/stats+buttons
        mainLayout.RowStyles.Add(new RowStyle(SizeType.Percent, 100)); // inventory

        var titleLabel = new Label
        {
            Text = "Starships",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 2)
        };
        mainLayout.Controls.Add(titleLabel, 0, 0);

        // Details+Stats layout: 2 columns, 2 rows (details/stats, buttons)
        var detailsStatsLayout = new TableLayoutPanel
        {
            Dock = DockStyle.Top,
            ColumnCount = 2,
            RowCount = 2,
            AutoSize = true
        };
        detailsStatsLayout.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 50));
        detailsStatsLayout.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 50));
        detailsStatsLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize)); // details/stats
        detailsStatsLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize)); // buttons

        // Left panel for selection and properties
        var leftPanel = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 2,
            RowCount = 7,
            AutoSize = true
        };
        for (int i = 0; i < 7; i++)
            leftPanel.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        leftPanel.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize));
        leftPanel.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100));

        int row = 0;
        var detailsLabel = new Label
        {
            Text = "Details",
            Font = new Font(Font.FontFamily, 10, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 8, 0, 2)
        };
        leftPanel.Controls.Add(detailsLabel, 0, row);
        leftPanel.SetColumnSpan(detailsLabel, 2);
        row++;

        _shipSelector = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _shipSelector.SelectedIndexChanged += OnShipSelected;
        AddRow(leftPanel, "Select Ship:", _shipSelector, row++);

        _shipName = new TextBox { Dock = DockStyle.Fill };
        AddRow(leftPanel, "Name:", _shipName, row++);

        _shipType = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _shipType.SelectedIndexChanged += OnShipTypeChanged;
        AddRow(leftPanel, "Type:", _shipType, row++);

        _shipClass = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _shipClass.Items.AddRange(ShipClasses);
        AddRow(leftPanel, "Class:", _shipClass, row++);

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
        AddRow(leftPanel, "Seed:", seedPanel, row++);

        // Right panel for base stats
        var rightPanel = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 2,
            RowCount = 5,
            AutoSize = true
        };
        for (int i = 0; i < 5; i++)
            rightPanel.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        rightPanel.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize));
        rightPanel.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100));

        int statRow = 0;
        var statsLabel = new Label
        {
            Text = "Base Stats",
            Font = new Font(Font.FontFamily, 10, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 8, 0, 2)
        };
        rightPanel.Controls.Add(statsLabel, 0, statRow);
        rightPanel.SetColumnSpan(statsLabel, 2);
        statRow++;

        _damageField = new NumericUpDown { Dock = DockStyle.Fill, DecimalPlaces = 2, Minimum = 0, Maximum = 999999, Increment = 0.01m };
        AddRow(rightPanel, "Damage:", _damageField, statRow++);

        _shieldField = new NumericUpDown { Dock = DockStyle.Fill, DecimalPlaces = 2, Minimum = 0, Maximum = 999999, Increment = 0.01m };
        AddRow(rightPanel, "Shield:", _shieldField, statRow++);

        _hyperdriveField = new NumericUpDown { Dock = DockStyle.Fill, DecimalPlaces = 2, Minimum = 0, Maximum = 999999, Increment = 0.01m };
        AddRow(rightPanel, "Hyperdrive:", _hyperdriveField, statRow++);

        _maneuverField = new NumericUpDown { Dock = DockStyle.Fill, DecimalPlaces = 2, Minimum = 0, Maximum = 999999, Increment = 0.01m };
        AddRow(rightPanel, "Maneuverability:", _maneuverField, statRow++);

        _useOldColours = new CheckBox { Text = "Use Old Color", AutoSize = true };
        rightPanel.Controls.Add(new Label(), 0, statRow);
        rightPanel.Controls.Add(_useOldColours, 1, statRow++);

        detailsStatsLayout.Controls.Add(leftPanel, 0, 0);
        detailsStatsLayout.Controls.Add(rightPanel, 1, 0);

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
        detailsStatsLayout.Controls.Add(buttonPanel, 0, 1);
        detailsStatsLayout.SetColumnSpan(buttonPanel, 2);

        mainLayout.Controls.Add(detailsStatsLayout, 0, 1);

        // Inventory tabs (fill remaining space)
        _inventoryGrid = new InventoryGridPanel { Dock = DockStyle.Fill };
        _techGrid = new InventoryGridPanel { Dock = DockStyle.Fill };

        _invTabs = new TabControl { Dock = DockStyle.Fill };
        var invPage = new TabPage("Cargo");
        invPage.Controls.Add(_inventoryGrid);
        var techPage = new TabPage("Technology");
        techPage.Controls.Add(_techGrid);
        _invTabs.TabPages.Add(invPage);
        _invTabs.TabPages.Add(techPage);

        mainLayout.Controls.Add(_invTabs, 0, 2);

        Controls.Add(mainLayout);

        // Set initial Max Supported labels (will be updated on ship selection)
        SetStarshipMaxSupportedLabels("Unknown");

        ResumeLayout(false);
        PerformLayout();
    }

    private static void AddRow(TableLayoutPanel layout, string label, Control field, int row)
    {
        var lbl = new Label { Text = label, AutoSize = true, Anchor = AnchorStyles.Left, Padding = new Padding(0, 5, 10, 0) };
        layout.Controls.Add(lbl, 0, row);
        layout.Controls.Add(field, 1, row);
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

    public void LoadData(JsonObject saveData)
    {
        try
        {
            // Populate ship types here
            _shipType.Items.Clear();
            var shipTypeNames = ShipInfo.Values.Select(info => info.DisplayName).Distinct().OrderBy(n => n).ToArray();
            _shipType.Items.AddRange(shipTypeNames);

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

            // Save ship type (filename) to Resource.Filename
            if (_shipType.SelectedIndex >= 0)
            {
                string? selectedType = _shipType.SelectedItem as string;
                if (!string.IsNullOrEmpty(selectedType))
                {
                    string filename = ShipInfo.FirstOrDefault(
                        kvp => kvp.Value.DisplayName.Equals(selectedType, StringComparison.OrdinalIgnoreCase)
                    ).Key ?? "";
                    var resource = ship.GetObject("Resource");
                    if (resource != null && !string.IsNullOrEmpty(filename))
                        resource.Set("Filename", filename);
                }
            }

            // Save class to Inventory.Class.InventoryClass
            if (_shipClass.SelectedIndex >= 0)
            {
                var inventory = ship.GetObject("Inventory");
                var classObj = inventory?.GetObject("Class");
                classObj?.Set("InventoryClass", ShipClasses[_shipClass.SelectedIndex]);
            }

            // Save seed to Resource.Seed[1]
            try
            {
                var resource = ship.GetObject("Resource");
                var seedArr = resource?.GetArray("Seed");
                if (seedArr != null && seedArr.Length > 1 && !string.IsNullOrEmpty(_shipSeed.Text))
                    seedArr.Set(1, _shipSeed.Text);
            }
            catch { }

            // Save base stats to Inventory.BaseStatValues
            var shipInventory = ship.GetObject("Inventory");
            WriteBaseStatValue(shipInventory, "^SHIP_DAMAGE", (double)_damageField.Value);
            WriteBaseStatValue(shipInventory, "^SHIP_SHIELD", (double)_shieldField.Value);
            WriteBaseStatValue(shipInventory, "^SHIP_HYPERDRIVE", (double)_hyperdriveField.Value);
            WriteBaseStatValue(shipInventory, "^SHIP_MANEUVER", (double)_maneuverField.Value);

            _inventoryGrid.SaveInventory(shipInventory);
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

            string filename = "";
            string seed = "";
            try
            {
                var resource = ship.GetObject("Resource");
                filename = resource?.GetString("Filename") ?? "";
                seed = resource?.GetArray("Seed")?.Get(1)?.ToString() ?? "";
            }
            catch { }

            _shipType.SelectedItem = LookupShipTypeName(filename);

            // Determine ship type for max supported label
            string type = filename;
            SetStarshipMaxSupportedLabels(type);

            _shipSeed.Text = seed;

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

            try
            {
                if (_playerState != null)
                    _useOldColours.Checked = _playerState.GetBool("ShipUsesLegacyColours");
            }
            catch { _useOldColours.Checked = false; }

            _inventoryGrid.LoadInventory(ship.GetObject("Inventory"));
            _techGrid.LoadInventory(ship.GetObject("Inventory_TechOnly"));

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