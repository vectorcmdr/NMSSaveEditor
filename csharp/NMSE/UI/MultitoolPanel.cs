using NMSE.Data;
using NMSE.Models;

namespace NMSE.UI;

public class MultitoolPanel : UserControl
{
    private static readonly string[] ToolClasses = { "C", "B", "A", "S" };

    private readonly ComboBox _toolSelector;
    private readonly TextBox _toolName;
    private readonly ComboBox _toolClass;
    private readonly TextBox _toolSeed;
    private readonly Button _generateSeedBtn;
    private readonly Button _deleteBtn;
    private readonly Button _exportBtn;
    private readonly Button _importBtn;
    private readonly NumericUpDown _damageField;
    private readonly NumericUpDown _miningField;
    private readonly NumericUpDown _scanField;
    private readonly InventoryGridPanel _storeGrid;
    private JsonArray? _multitools;
    private JsonObject? _playerState;
    private GameItemDatabase? _database;
    private readonly Random _rng = new();
    // Track which indices in the Multitools array are valid (have Seed[0] == true)
    private readonly List<int> _validIndices = new();

    private static readonly (string Name, string Filename)[] ToolTypes = new[]
    {
        ("Standard", "MODELS/COMMON/WEAPONS/MULTITOOL/MULTITOOL.SCENE.MBIN"),
        ("Royal", "MODELS/COMMON/WEAPONS/MULTITOOL/ROYALMULTITOOL.SCENE.MBIN"),
        ("Sentinel", "MODELS/COMMON/WEAPONS/MULTITOOL/SENTINELMULTITOOL.SCENE.MBIN"),
        ("Sentinel B", "MODELS/COMMON/WEAPONS/MULTITOOL/SENTINELMULTITOOLB.SCENE.MBIN"),
        ("Switch", "MODELS/COMMON/WEAPONS/MULTITOOL/SWITCHMULTITOOL.SCENE.MBIN"),
        ("Staff", "MODELS/COMMON/WEAPONS/MULTITOOL/STAFFMULTITOOL.SCENE.MBIN"),
        ("Staff NPC", "MODELS/COMMON/WEAPONS/MULTITOOL/STAFFNPCMULTITOOL.SCENE.MBIN"),
        ("Atlas", "MODELS/COMMON/WEAPONS/MULTITOOL/ATLASMULTITOOL.SCENE.MBIN"),
        ("Atlas Scepter", "MODELS/COMMON/WEAPONS/MULTITOOL/STAFFMULTITOOLATLAS.SCENE.MBIN"),
    };
    private readonly ComboBox _toolType;

    public MultitoolPanel()
    {
        SuspendLayout();

        // Main layout: 1 column, stack everything vertically
        var rootLayout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 1,
            RowCount = 3,
            Padding = new Padding(10),
            AutoSize = true
        };

        // Title
        var titleLabel = new Label
        {
            Text = "Multitools",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 1)
        };
        rootLayout.Controls.Add(titleLabel, 0, 0);

        // Main two-column panel
        var mainPanel = new TableLayoutPanel
        {
            Dock = DockStyle.Top,
            ColumnCount = 2,
            RowCount = 1,
            AutoSize = true
        };
        mainPanel.ColumnStyles.Add(new ColumnStyle(SizeType.Percent) { Width = 50 });
        mainPanel.ColumnStyles.Add(new ColumnStyle(SizeType.Percent) { Width = 50 });

        // Left panel: selector, name, class, seed
        var leftPanel = new TableLayoutPanel
        {
            Dock = DockStyle.Top,
            ColumnCount = 2, // Changed from 1 to 2
            RowCount = 4,
            AutoSize = true
        };
        leftPanel.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize)); // Label column
        leftPanel.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100F)); // Field column
        int leftRow = 0;

        var detailsLabel = new Label
        {
            Text = "Multitool Details",
            Font = new Font(Font.FontFamily, 10, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 8, 0, 1)
        };
        // Span the detailsLabel across both columns
        leftPanel.Controls.Add(detailsLabel, 0, leftRow);
        leftPanel.SetColumnSpan(detailsLabel, 2);
        leftRow++;

        _toolSelector = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _toolSelector.SelectedIndexChanged += OnToolSelected;
        AddRow(leftPanel, "Select Multitool:", _toolSelector, leftRow++);

        _toolName = new TextBox { Dock = DockStyle.Fill };
        AddRow(leftPanel, "Name:", _toolName, leftRow++);

        _toolType = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _toolType.Items.AddRange(ToolTypes.Select(t => t.Name).ToArray());
        AddRow(leftPanel, "Type:", _toolType, leftRow++);

        _toolClass = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _toolClass.Items.AddRange(ToolClasses);
        AddRow(leftPanel, "Class:", _toolClass, leftRow++);

        var seedPanel = new Panel { Dock = DockStyle.Fill, Height = 26 };
        _toolSeed = new TextBox { Dock = DockStyle.Fill };
        _generateSeedBtn = new Button { Text = "Generate", Dock = DockStyle.Right, Width = 70 };
        _generateSeedBtn.Click += (s, e) =>
        {
            byte[] bytes = new byte[8];
            _rng.NextBytes(bytes);
            _toolSeed.Text = "0x" + BitConverter.ToString(bytes).Replace("-", "");
        };
        seedPanel.Controls.Add(_toolSeed);
        seedPanel.Controls.Add(_generateSeedBtn);
        AddRow(leftPanel, "Seed:", seedPanel, leftRow++);

        // Right panel: Base Stats
        var rightPanel = new TableLayoutPanel
        {
            Dock = DockStyle.Top,
            ColumnCount = 2, // Changed from 1 to 2
            RowCount = 4,
            AutoSize = true
        };
        rightPanel.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize)); // Label column
        rightPanel.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100F)); // Field column
        int rightRow = 0;

        var statsLabel = new Label
        {
            Text = "Base Stats",
            Font = new Font(Font.FontFamily, 10, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 8, 0, 1)
        };
        // Span the statsLabel across both columns
        rightPanel.Controls.Add(statsLabel, 0, rightRow);
        rightPanel.SetColumnSpan(statsLabel, 2);
        rightRow++;

        _damageField = new NumericUpDown { Dock = DockStyle.Fill, DecimalPlaces = 2, Minimum = 0, Maximum = 999999, Increment = 0.01m };
        AddRow(rightPanel, "Damage:", _damageField, rightRow++);

        _miningField = new NumericUpDown { Dock = DockStyle.Fill, DecimalPlaces = 2, Minimum = 0, Maximum = 999999, Increment = 0.01m };
        AddRow(rightPanel, "Mining:", _miningField, rightRow++);

        _scanField = new NumericUpDown { Dock = DockStyle.Fill, DecimalPlaces = 2, Minimum = 0, Maximum = 999999, Increment = 0.01m };
        AddRow(rightPanel, "Scan:", _scanField, rightRow++);

        mainPanel.Controls.Add(leftPanel, 0, 0);
        mainPanel.Controls.Add(rightPanel, 1, 0);

        rootLayout.Controls.Add(mainPanel, 0, 1);

        // Buttons panel
        var buttonPanel = new FlowLayoutPanel
        {
            Dock = DockStyle.Top,
            AutoSize = true,
            FlowDirection = FlowDirection.LeftToRight
        };
        _deleteBtn = new Button { Text = "Delete Multitool", Width = 110 };
        _deleteBtn.Click += OnDeleteTool;
        _exportBtn = new Button { Text = "Export", Width = 70 };
        _exportBtn.Click += OnExportTool;
        _importBtn = new Button { Text = "Import", Width = 70 };
        _importBtn.Click += OnImportTool;
        buttonPanel.Controls.Add(_deleteBtn);
        buttonPanel.Controls.Add(_exportBtn);
        buttonPanel.Controls.Add(_importBtn);

        rootLayout.Controls.Add(buttonPanel, 0, 2);

        // Inventory grid (fills remaining space)
        _storeGrid = new InventoryGridPanel { Dock = DockStyle.Fill };
        rootLayout.Controls.Add(_storeGrid);

        Controls.Add(rootLayout);

        // Set Max Supported label for multitool technology
        _storeGrid.SetMaxSupportedLabel("Max Supported: 10x6");

        ResumeLayout(false);
        PerformLayout();
    }

    public void SetDatabase(GameItemDatabase? database)
    {
        _database = database;
        _storeGrid.SetDatabase(database);
    }

    public void SetIconManager(IconManager? iconManager)
    {
        _storeGrid.SetIconManager(iconManager);
    }

    private static void AddRow(TableLayoutPanel layout, string label, Control field, int row)
    {
        var lbl = new Label { Text = label, AutoSize = true, Anchor = AnchorStyles.Left, Padding = new Padding(0, 5, 10, 0) };
        layout.Controls.Add(lbl, 0, row);
        layout.Controls.Add(field, 1, row);
    }

    /// <summary>Rebuilds the selector and valid-index list from the current _multitools array.</summary>
    private void RefreshToolList()
    {
        _toolSelector.Items.Clear();
        _validIndices.Clear();
        if (_multitools == null) return;

        for (int i = 0; i < _multitools.Length; i++)
        {
            try
            {
                var tool = _multitools.GetObject(i);
                var seedArr = tool?.GetArray("Seed");
                bool hasSeed = false;
                try { hasSeed = seedArr != null && seedArr.Length > 0 && seedArr.GetBool(0); }
                catch { }

                if (!hasSeed) continue;

                _validIndices.Add(i);
                string name = tool?.GetString("Name") ?? "";
                if (string.IsNullOrEmpty(name))
                    name = $"Multitool {i + 1}";
                _toolSelector.Items.Add(name);
            }
            catch
            {
                _validIndices.Add(i);
                _toolSelector.Items.Add($"Multitool {i + 1}");
            }
        }
    }

    public void LoadData(JsonObject saveData)
    {
        try
        {
            _playerState = saveData.GetObject("PlayerStateData");
            if (_playerState == null) return;

            _multitools = _playerState.GetArray("Multitools");
            _toolSelector.Items.Clear();
            _validIndices.Clear();

            if (_multitools != null && _multitools.Length > 0)
            {
                RefreshToolList();

                if (_toolSelector.Items.Count > 0)
                {
                    int current = 0;
                    try { current = _playerState.GetInt("ActiveMultioolIndex"); } catch { }
                    int selectIdx = _validIndices.IndexOf(current);
                    _toolSelector.SelectedIndex = Math.Clamp(selectIdx >= 0 ? selectIdx : 0, 0, _toolSelector.Items.Count - 1);
                }
            }
            else
            {
                // Older saves without Multitools array use WeaponInventory directly
                _multitools = null;
                var weaponInv = _playerState.GetObject("WeaponInventory");
                if (weaponInv != null)
                {
                    string name = _playerState.GetString("PlayerWeaponName") ?? "Primary Weapon";
                    _toolSelector.Items.Add(name);
                    _toolName.Text = name;

                    // Load seed from CurrentWeapon.GenerationSeed[1]
                    try
                    {
                        var genSeed = _playerState.GetObject("CurrentWeapon")?.GetArray("GenerationSeed");
                        if (genSeed != null && genSeed.Length > 1)
                            _toolSeed.Text = genSeed.Get(1)?.ToString() ?? "";
                    }
                    catch { }

                    _storeGrid.LoadInventory(weaponInv);
                    _toolSelector.SelectedIndex = 0;
                }
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

            var multitools = playerState.GetArray("Multitools");
            if (multitools != null && _toolSelector.SelectedIndex >= 0 && _validIndices.Count > 0)
            {
                int idx = _validIndices[_toolSelector.SelectedIndex];
                if (idx >= multitools.Length) return;

                var tool = multitools.GetObject(idx);

                // Save name
                if (!string.IsNullOrEmpty(_toolName.Text))
                    tool.Set("Name", _toolName.Text);

                // Save class to Store.Class.InventoryClass
                if (_toolClass.SelectedIndex >= 0)
                {
                    var store = tool.GetObject("Store");
                    var classObj = store?.GetObject("Class");
                    classObj?.Set("InventoryClass", ToolClasses[_toolClass.SelectedIndex]);
                }

                if (_toolType.SelectedIndex >= 0)
                {
                    tool.Set("Filename", ToolTypes[_toolType.SelectedIndex].Filename);
                }

                // Save seed to Seed[1]
                try
                {
                    var seedArr = tool.GetArray("Seed");
                    if (seedArr != null && seedArr.Length > 1 && !string.IsNullOrEmpty(_toolSeed.Text))
                        seedArr.Set(1, _toolSeed.Text);
                }
                catch { }

                _storeGrid.SaveInventory(tool.GetObject("Store"));

                // Save base stats to Store.BaseStatValues
                var toolStore = tool.GetObject("Store");
                WriteBaseStatValue(toolStore, "^WEAPON_DAMAGE", (double)_damageField.Value);
                WriteBaseStatValue(toolStore, "^WEAPON_MINING", (double)_miningField.Value);
                WriteBaseStatValue(toolStore, "^WEAPON_SCAN", (double)_scanField.Value);
            }
            else
            {
                // Old-format save
                var weaponInv = playerState.GetObject("WeaponInventory");
                _storeGrid.SaveInventory(weaponInv);
            }
        }
        catch { }
    }

    private void OnToolSelected(object? sender, EventArgs e)
    {
        try
        {
            if (_toolSelector.SelectedIndex < 0) return;

            // New-format multitools
            if (_multitools != null && _validIndices.Count > 0)
            {
                int idx = _validIndices[_toolSelector.SelectedIndex];
                if (idx >= _multitools.Length) return;

                var tool = _multitools.GetObject(idx);
                _toolName.Text = tool.GetString("Name") ?? "";

                // Type / filename
                string scene = "";
                try
                {
                    scene = tool.GetString("Filename") ?? "";
                }
                catch { }
                int typeIdx = Array.FindIndex(ToolTypes, t => t.Filename.Equals(scene, StringComparison.OrdinalIgnoreCase));
                _toolType.SelectedIndex = typeIdx >= 0 ? typeIdx : 0;

                // Class from Store.Class.InventoryClass
                string cls = "";
                try
                {
                    var store = tool.GetObject("Store");
                    var classObj = store?.GetObject("Class");
                    cls = classObj?.GetString("InventoryClass") ?? "";
                }
                catch { }
                int classIdx = Array.IndexOf(ToolClasses, cls);
                _toolClass.SelectedIndex = classIdx >= 0 ? classIdx : -1;

                // Seed from Seed[1]
                string seed = "";
                try
                {
                    var seedArr = tool.GetArray("Seed");
                    if (seedArr != null && seedArr.Length > 1)
                        seed = seedArr.Get(1)?.ToString() ?? "";
                }
                catch { }
                _toolSeed.Text = seed;

                _storeGrid.LoadInventory(tool.GetObject("Store"));

                // Load base stats from Store.BaseStatValues
                var toolStore = tool.GetObject("Store");
                try { _damageField.Value = (decimal)ReadBaseStatValue(toolStore, "^WEAPON_DAMAGE"); } catch { _damageField.Value = 0; }
                try { _miningField.Value = (decimal)ReadBaseStatValue(toolStore, "^WEAPON_MINING"); } catch { _miningField.Value = 0; }
                try { _scanField.Value = (decimal)ReadBaseStatValue(toolStore, "^WEAPON_SCAN"); } catch { _scanField.Value = 0; }
            }
        }
        catch { }
    }

    private void OnDeleteTool(object? sender, EventArgs e)
    {
        try
        {
            if (_multitools == null || _toolSelector.SelectedIndex < 0 || _validIndices.Count == 0) return;

            var result = MessageBox.Show(
                "Are you sure you want to delete this multitool?",
                "Delete Multitool",
                MessageBoxButtons.YesNo,
                MessageBoxIcon.Warning);

            if (result != DialogResult.Yes) return;

            int idx = _validIndices[_toolSelector.SelectedIndex];
            if (idx >= _multitools.Length) return;

            var tool = _multitools.GetObject(idx);
            var seedArr = tool.GetArray("Seed");
            if (seedArr != null && seedArr.Length > 1)
            {
                seedArr.Set(0, false);
                seedArr.Set(1, "0x0");
            }

            // Remove from tracking and refresh
            int selIdx = _toolSelector.SelectedIndex;
            _validIndices.RemoveAt(selIdx);
            _toolSelector.Items.RemoveAt(selIdx);
            if (_toolSelector.Items.Count > 0)
                _toolSelector.SelectedIndex = Math.Min(selIdx, _toolSelector.Items.Count - 1);
            else
                _storeGrid.LoadInventory(null);
        }
        catch { }
    }

    private void OnExportTool(object? sender, EventArgs e)
    {
        try
        {
            if (_multitools == null || _toolSelector.SelectedIndex < 0 || _validIndices.Count == 0) return;

            int idx = _validIndices[_toolSelector.SelectedIndex];
            if (idx >= _multitools.Length) return;

            var tool = _multitools.GetObject(idx);

            using var dialog = new SaveFileDialog
            {
                Filter = "JSON files (*.json)|*.json|All files (*.*)|*.*",
                DefaultExt = "json",
                FileName = $"multitool_{_toolName.Text}.json"
            };

            if (dialog.ShowDialog() == DialogResult.OK)
                tool.ExportToFile(dialog.FileName);
        }
        catch (Exception ex)
        {
            MessageBox.Show($"Export failed: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
    }

    private void OnImportTool(object? sender, EventArgs e)
    {
        try
        {
            if (_multitools == null || _playerState == null) return;

            using var dialog = new OpenFileDialog
            {
                Filter = "JSON files (*.json)|*.json|All files (*.*)|*.*"
            };

            if (dialog.ShowDialog() != DialogResult.OK) return;

            var imported = JsonObject.ImportFromFile(dialog.FileName);

            // Find first empty slot (Seed[0] == false)
            int emptyIdx = -1;
            for (int i = 0; i < _multitools.Length; i++)
            {
                try
                {
                    var slot = _multitools.GetObject(i);
                    var seedArr = slot?.GetArray("Seed");
                    bool hasSeed = false;
                    try { hasSeed = seedArr != null && seedArr.Length > 0 && seedArr.GetBool(0); }
                    catch { }
                    if (!hasSeed)
                    {
                        emptyIdx = i;
                        break;
                    }
                }
                catch { }
            }

            if (emptyIdx < 0)
            {
                MessageBox.Show("No empty multitool slots available.", "Import", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            var target = _multitools.GetObject(emptyIdx);
            foreach (var name in imported.Names())
                target.Set(name, imported.Get(name));

            // Refresh the list by reloading
            int prevSel = _toolSelector.SelectedIndex;
            RefreshToolList();

            if (_toolSelector.Items.Count > 0)
            {
                int newSelIdx = _validIndices.IndexOf(emptyIdx);
                _toolSelector.SelectedIndex = newSelIdx >= 0 ? newSelIdx : Math.Clamp(prevSel, 0, _toolSelector.Items.Count - 1);
            }
        }
        catch (Exception ex)
        {
            MessageBox.Show($"Import failed: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
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
}
