using NMSE.Data;
using NMSE.Models;

namespace NMSE.UI;

public class FreighterPanel : UserControl
{
    private static readonly string[] FreighterClasses = { "C", "B", "A", "S" };

    private readonly TextBox _freighterName;
    private readonly ComboBox _freighterType;
    private readonly ComboBox _freighterClass;
    private readonly TextBox _homeSeed;
    private readonly Button _generateHomeSeedBtn;
    private readonly TextBox _modelSeed;
    private readonly Button _generateModelSeedBtn;
    private readonly NumericUpDown _hyperdriveField;
    private readonly NumericUpDown _fleetField;
    private readonly TextBox _baseItemsField;
    private readonly Button _backupBtn;
    private readonly Button _restoreBtn;
    private readonly TabControl _invTabs;
    private readonly InventoryGridPanel _generalGrid;
    private readonly InventoryGridPanel _techGrid;
    private JsonObject? _playerState;
    private JsonObject? _freighterBase;
    private readonly Random _rng = new();

    private static readonly Dictionary<string, string> FreighterTypes = new(StringComparer.OrdinalIgnoreCase)
    {
        { "Tiny",    "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/FREIGHTERTINY_PROC.SCENE.MBIN" },
        { "Small",   "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/FREIGHTERSMALL_PROC.SCENE.MBIN" },
        { "Normal",  "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/FREIGHTER_PROC.SCENE.MBIN" },
        { "Capital", "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/CAPITALFREIGHTER_PROC.SCENE.MBIN" },
        { "Pirate",  "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/PIRATEFREIGHTER.SCENE.MBIN" }
    };

    public FreighterPanel()
    {
        SuspendLayout();

        // Main layout: 1 column, 3 rows (title, details+stats+buttons, inventory)
        var mainLayout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 1,
            RowCount = 3,
            Padding = new Padding(10)
        };
        mainLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize)); // title
        mainLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize)); // details+stats+buttons
        mainLayout.RowStyles.Add(new RowStyle(SizeType.Percent, 100)); // inventory

        // Title
        var titleLabel = new Label
        {
            Text = "Freighter",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 5)
        };
        mainLayout.Controls.Add(titleLabel, 0, 0);

        // Details+Stats+Buttons layout: 2 columns, 3 rows (details, stats, buttons)
        var detailsStatsLayout = new TableLayoutPanel
        {
            Dock = DockStyle.Top,
            ColumnCount = 2,
            RowCount = 3,
            AutoSize = true
        };
        detailsStatsLayout.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 50));
        detailsStatsLayout.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 50));

        // Details panel (left)
        var detailsPanel = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 2, // FIX: 2 columns for label and field
            RowCount = 5,
            AutoSize = true
        };
        detailsPanel.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize)); // Label
        detailsPanel.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100)); // Field
        detailsPanel.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        detailsPanel.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        detailsPanel.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        detailsPanel.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        detailsPanel.RowStyles.Add(new RowStyle(SizeType.AutoSize));

        _freighterName = new TextBox { Dock = DockStyle.Fill };
        AddRow(detailsPanel, "Name:", _freighterName, 0);

        _freighterType = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _freighterType.Items.AddRange(FreighterTypes.Keys.ToArray());
        AddRow(detailsPanel, "Type:", _freighterType, 1);

        _freighterClass = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _freighterClass.Items.AddRange(FreighterClasses);
        AddRow(detailsPanel, "Class:", _freighterClass, 2);

        var homeSeedPanel = new Panel { Dock = DockStyle.Fill, Height = 26 };
        _homeSeed = new TextBox { Dock = DockStyle.Fill };
        _generateHomeSeedBtn = new Button { Text = "Generate", Dock = DockStyle.Right, Width = 70 };
        _generateHomeSeedBtn.Click += (s, e) =>
        {
            byte[] bytes = new byte[8];
            _rng.NextBytes(bytes);
            _homeSeed.Text = "0x" + BitConverter.ToString(bytes).Replace("-", "");
        };
        homeSeedPanel.Controls.Add(_homeSeed);
        homeSeedPanel.Controls.Add(_generateHomeSeedBtn);
        AddRow(detailsPanel, "Home Seed:", homeSeedPanel, 3);

        detailsStatsLayout.Controls.Add(detailsPanel, 0, 0);

        // Stats panel (right)
        var statsPanel = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 2, // FIX: 2 columns for label and field
            RowCount = 4,
            AutoSize = true
        };
        statsPanel.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize)); // Label
        statsPanel.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100)); // Field
        statsPanel.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        statsPanel.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        statsPanel.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        statsPanel.RowStyles.Add(new RowStyle(SizeType.AutoSize));

        _hyperdriveField = new NumericUpDown { Dock = DockStyle.Fill, DecimalPlaces = 2, Minimum = 0, Maximum = 999999, Increment = 0.01m };
        AddRow(statsPanel, "Hyperdrive:", _hyperdriveField, 0);

        _fleetField = new NumericUpDown { Dock = DockStyle.Fill, DecimalPlaces = 2, Minimum = 0, Maximum = 999999, Increment = 0.01m };
        AddRow(statsPanel, "Fleet Coordination:", _fleetField, 1);

        _baseItemsField = new TextBox { Dock = DockStyle.Fill, ReadOnly = true };
        AddRow(statsPanel, "Items:", _baseItemsField, 2);

        var modelSeedPanel = new Panel { Dock = DockStyle.Fill, Height = 26 };
        _modelSeed = new TextBox { Dock = DockStyle.Fill };
        _generateModelSeedBtn = new Button { Text = "Generate", Dock = DockStyle.Right, Width = 70 };
        _generateModelSeedBtn.Click += (s, e) =>
        {
            byte[] bytes = new byte[8];
            _rng.NextBytes(bytes);
            _modelSeed.Text = "0x" + BitConverter.ToString(bytes).Replace("-", "");
        };
        modelSeedPanel.Controls.Add(_modelSeed);
        modelSeedPanel.Controls.Add(_generateModelSeedBtn);
        AddRow(statsPanel, "Model Seed:", modelSeedPanel, 3);

        detailsStatsLayout.Controls.Add(statsPanel, 1, 0);

        // Backup/Restore buttons (span both columns)
        var buttonPanel = new FlowLayoutPanel
        {
            Dock = DockStyle.Fill,
            AutoSize = true,
            FlowDirection = FlowDirection.LeftToRight
        };
        _backupBtn = new Button { Text = "Backup", Width = 80 };
        _backupBtn.Click += OnBackup;
        _restoreBtn = new Button { Text = "Restore", Width = 80 };
        _restoreBtn.Click += OnRestore;
        buttonPanel.Controls.Add(_backupBtn);
        buttonPanel.Controls.Add(_restoreBtn);
        detailsStatsLayout.Controls.Add(buttonPanel, 0, 1);
        detailsStatsLayout.SetColumnSpan(buttonPanel, 2);

        mainLayout.Controls.Add(detailsStatsLayout, 0, 1);

        // Inventory tabs (fill remaining space)
        _generalGrid = new InventoryGridPanel { Dock = DockStyle.Fill };
        _techGrid = new InventoryGridPanel { Dock = DockStyle.Fill };

        _invTabs = new TabControl { Dock = DockStyle.Fill };
        var generalPage = new TabPage("Cargo");
        generalPage.Controls.Add(_generalGrid);
        var techPage = new TabPage("Technology");
        techPage.Controls.Add(_techGrid);
        _invTabs.TabPages.Add(generalPage);
        _invTabs.TabPages.Add(techPage);

        mainLayout.Controls.Add(_invTabs, 0, 2);

        _generalGrid.SetMaxSupportedLabel("");
        _techGrid.SetMaxSupportedLabel("");

        Controls.Add(mainLayout);
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

            // Name
            _freighterName.Text = _playerState.GetString("PlayerFreighterName") ?? "";

            // Type (filename)
            try
            {
                var currentFreighter = _playerState.GetObject("CurrentFreighter");
                string filename = currentFreighter?.GetString("Filename") ?? "";
                // Find display name by filename
                string? displayName = FreighterTypes.FirstOrDefault(x => x.Value.Equals(filename, StringComparison.OrdinalIgnoreCase)).Key;
                if (displayName != null)
                    _freighterType.SelectedItem = displayName;
                else
                    _freighterType.SelectedIndex = -1;
            }
            catch { _freighterType.SelectedIndex = -1; }

            // Class from FreighterInventory.Class.InventoryClass
            try
            {
                var freighterInv = _playerState.GetObject("FreighterInventory");
                var classObj = freighterInv?.GetObject("Class");
                string cls = classObj?.GetString("InventoryClass") ?? "";
                int classIdx = Array.IndexOf(FreighterClasses, cls);
                _freighterClass.SelectedIndex = classIdx >= 0 ? classIdx : -1;
            }
            catch { _freighterClass.SelectedIndex = -1; }

            // Home Seed
            try
            {
                var homeSeedArr = _playerState.GetArray("CurrentFreighterHomeSystemSeed");
                if (homeSeedArr != null && homeSeedArr.Length > 1)
                    _homeSeed.Text = homeSeedArr.Get(1)?.ToString() ?? "";
                else
                    _homeSeed.Text = "";
            }
            catch { _homeSeed.Text = ""; }

            // Model Seed
            try
            {
                var currentFreighter = _playerState.GetObject("CurrentFreighter");
                var seedArr = currentFreighter?.GetArray("Seed");
                if (seedArr != null && seedArr.Length > 1)
                    _modelSeed.Text = seedArr.Get(1)?.ToString() ?? "";
                else
                    _modelSeed.Text = "";
            }
            catch { _modelSeed.Text = ""; }

            // Base Stats: read from FreighterInventory.BaseStatValues
            _hyperdriveField.Value = (decimal)ReadStatBonus(_playerState.GetObject("FreighterInventory"), "^FREI_HYPERDRIVE");
            _fleetField.Value = (decimal)ReadStatBonus(_playerState.GetObject("FreighterInventory"), "^FREI_FLEET");

            // Freighter base info
            _freighterBase = FindFreighterBase(_playerState);
            if (_freighterBase != null)
            {
                try
                {
                    var objects = _freighterBase.GetArray("Objects");
                    _baseItemsField.Text = objects != null ? objects.Length.ToString() : "0";
                }
                catch { _baseItemsField.Text = "0"; }
            }
            else
            {
                _baseItemsField.Text = "N/A";
            }

            _backupBtn.Enabled = _freighterBase != null;

            // Load inventories
            _generalGrid.LoadInventory(_playerState.GetObject("FreighterInventory"));
            _techGrid.LoadInventory(_playerState.GetObject("FreighterInventory_TechOnly"));
        }
        catch { }
    }

    public void SaveData(JsonObject saveData)
    {
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            // Name
            if (!string.IsNullOrEmpty(_freighterName.Text))
                playerState.Set("PlayerFreighterName", _freighterName.Text);

            // Save type (filename) if needed
            if (_freighterType.SelectedItem is string selectedType && FreighterTypes.TryGetValue(selectedType, out var filename))
            {
                var currentFreighter = playerState.GetObject("CurrentFreighter");
                if (currentFreighter != null)
                    currentFreighter.Set("Filename", filename);
            }

            // Class - write to both inventories
            if (_freighterClass.SelectedIndex >= 0)
            {
                string cls = FreighterClasses[_freighterClass.SelectedIndex];
                SetInventoryClass(playerState.GetObject("FreighterInventory"), cls);
                SetInventoryClass(playerState.GetObject("FreighterInventory_TechOnly"), cls);
            }

            // Home Seed
            try
            {
                var homeSeedArr = playerState.GetArray("CurrentFreighterHomeSystemSeed");
                if (homeSeedArr != null && homeSeedArr.Length > 1 && !string.IsNullOrEmpty(_homeSeed.Text))
                    homeSeedArr.Set(1, _homeSeed.Text);
            }
            catch { }

            // Model Seed
            try
            {
                var currentFreighter = playerState.GetObject("CurrentFreighter");
                var seedArr = currentFreighter?.GetArray("Seed");
                if (seedArr != null && seedArr.Length > 1 && !string.IsNullOrEmpty(_modelSeed.Text))
                    seedArr.Set(1, _modelSeed.Text);
            }
            catch { }

            // Save base stats
            WriteStatBonus(playerState.GetObject("FreighterInventory"), "^FREI_HYPERDRIVE", (double)_hyperdriveField.Value);
            WriteStatBonus(playerState.GetObject("FreighterInventory"), "^FREI_FLEET", (double)_fleetField.Value);

            // Save inventories
            _generalGrid.SaveInventory(playerState.GetObject("FreighterInventory"));
            _techGrid.SaveInventory(playerState.GetObject("FreighterInventory_TechOnly"));
        }
        catch { }
    }

    private static double ReadStatBonus(JsonObject? inventory, string statId)
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

    private static void WriteStatBonus(JsonObject? inventory, string statId, double value)
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

    private static JsonObject? FindFreighterBase(JsonObject playerState)
    {
        try
        {
            var bases = playerState.GetArray("PersistentPlayerBases");
            if (bases == null) return null;
            for (int i = 0; i < bases.Length; i++)
            {
                var b = bases.GetObject(i);
                try
                {
                    var baseType = b.GetObject("BaseType");
                    if (baseType != null
                        && "FreighterBase" == baseType.GetString("PersistentBaseTypes")
                        && b.GetInt("BaseVersion") >= 3)
                        return b;
                }
                catch { }
            }
        }
        catch { }
        return null;
    }

    private void OnBackup(object? sender, EventArgs e)
    {
        try
        {
            if (_freighterBase == null)
            {
                MessageBox.Show("No freighter base found.", "Backup", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }

            using var dialog = new SaveFileDialog
            {
                Filter = "JSON files (*.json)|*.json|All files (*.*)|*.*",
                DefaultExt = "json",
                FileName = "freighter_base.json"
            };

            if (dialog.ShowDialog() == DialogResult.OK)
                _freighterBase.ExportToFile(dialog.FileName);
        }
        catch (Exception ex)
        {
            MessageBox.Show($"Backup failed: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
    }

    private void OnRestore(object? sender, EventArgs e)
    {
        try
        {
            if (_playerState == null) return;

            using var dialog = new OpenFileDialog
            {
                Filter = "JSON files (*.json)|*.json|All files (*.*)|*.*"
            };

            if (dialog.ShowDialog() != DialogResult.OK) return;

            var imported = JsonObject.ImportFromFile(dialog.FileName);
            var bases = _playerState.GetArray("PersistentPlayerBases");
            if (bases == null) return;

            // Find existing freighter base and replace it
            for (int i = 0; i < bases.Length; i++)
            {
                try
                {
                    var b = bases.GetObject(i);
                    var baseType = b.GetObject("BaseType");
                    if (baseType != null
                        && "FreighterBase" == baseType.GetString("PersistentBaseTypes")
                        && b.GetInt("BaseVersion") >= 3)
                    {
                        // Replace all properties from imported base
                        foreach (var name in imported.Names())
                            b.Set(name, imported.Get(name));

                        _freighterBase = b;

                        // Update items count
                        try
                        {
                            var objects = _freighterBase.GetArray("Objects");
                            _baseItemsField.Text = objects != null ? objects.Length.ToString() : "0";
                        }
                        catch { _baseItemsField.Text = "0"; }

                        MessageBox.Show("Freighter base restored.", "Restore", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        return;
                    }
                }
                catch { }
            }

            MessageBox.Show("No existing freighter base slot found to restore into.", "Restore", MessageBoxButtons.OK, MessageBoxIcon.Warning);
        }
        catch (Exception ex)
        {
            MessageBox.Show($"Restore failed: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
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
}