using NMSSaveEditor.Data;
using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

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

    public MultitoolPanel()
    {
        SuspendLayout();

        var layout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 2,
            RowCount = 11,
            Padding = new Padding(10)
        };
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize));
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100));
        for (int i = 0; i < 10; i++)
            layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));

        int row = 0;

        // Row 0: Title
        var titleLabel = new Label
        {
            Text = "Multitools",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 5)
        };
        layout.Controls.Add(titleLabel, 0, row);
        layout.SetColumnSpan(titleLabel, 2);
        row++;

        // Row 1: Selector
        _toolSelector = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _toolSelector.SelectedIndexChanged += OnToolSelected;
        AddRow(layout, "Select Multitool:", _toolSelector, row); row++;

        // Row 2: Name
        _toolName = new TextBox { Dock = DockStyle.Fill };
        AddRow(layout, "Name:", _toolName, row); row++;

        // Row 3: Class
        _toolClass = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _toolClass.Items.AddRange(ToolClasses);
        AddRow(layout, "Class:", _toolClass, row); row++;

        // Row 4: Seed with Generate button
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
        AddRow(layout, "Seed:", seedPanel, row); row++;

        // Row 5: Base Stats separator
        var statsLabel = new Label
        {
            Text = "Base Stats",
            Font = new Font(Font.FontFamily, 10, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 8, 0, 4)
        };
        layout.Controls.Add(statsLabel, 0, row);
        layout.SetColumnSpan(statsLabel, 2);
        row++;

        // Row 6: Damage
        _damageField = new NumericUpDown { Dock = DockStyle.Fill, DecimalPlaces = 2, Minimum = 0, Maximum = 999999, Increment = 0.01m };
        AddRow(layout, "Damage:", _damageField, row); row++;

        // Row 7: Mining
        _miningField = new NumericUpDown { Dock = DockStyle.Fill, DecimalPlaces = 2, Minimum = 0, Maximum = 999999, Increment = 0.01m };
        AddRow(layout, "Mining:", _miningField, row); row++;

        // Row 8: Scan
        _scanField = new NumericUpDown { Dock = DockStyle.Fill, DecimalPlaces = 2, Minimum = 0, Maximum = 999999, Increment = 0.01m };
        AddRow(layout, "Scan:", _scanField, row); row++;

        // Row 9: Buttons panel
        var buttonPanel = new FlowLayoutPanel
        {
            Dock = DockStyle.Fill,
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
        layout.Controls.Add(buttonPanel, 0, row);
        layout.SetColumnSpan(buttonPanel, 2);
        row++;

        // Row 10 (fill): Store inventory grid
        _storeGrid = new InventoryGridPanel { Dock = DockStyle.Fill };
        layout.Controls.Add(_storeGrid, 0, row);
        layout.SetColumnSpan(_storeGrid, 2);

        Controls.Add(layout);
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
                var store2 = tool.GetObject("Store");
                WriteBaseStatValue(store2, "^MT_DAMAGE", (double)_damageField.Value);
                WriteBaseStatValue(store2, "^MT_MINING", (double)_miningField.Value);
                WriteBaseStatValue(store2, "^MT_SCAN", (double)_scanField.Value);
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
                var store2 = tool.GetObject("Store");
                try { _damageField.Value = (decimal)ReadBaseStatValue(store2, "^MT_DAMAGE"); } catch { _damageField.Value = 0; }
                try { _miningField.Value = (decimal)ReadBaseStatValue(store2, "^MT_MINING"); } catch { _miningField.Value = 0; }
                try { _scanField.Value = (decimal)ReadBaseStatValue(store2, "^MT_SCAN"); } catch { _scanField.Value = 0; }
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
