using NMSSaveEditor.Data;
using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class FreighterPanel : UserControl
{
    private static readonly string[] FreighterClasses = { "C", "B", "A", "S" };

    private readonly TextBox _freighterName;
    private readonly TextBox _freighterType;
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

    public FreighterPanel()
    {
        SuspendLayout();

        var layout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 2,
            RowCount = 13,
            Padding = new Padding(10)
        };
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize));
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100));
        for (int i = 0; i < 12; i++)
            layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));

        int row = 0;

        // Row 0: Title
        var titleLabel = new Label
        {
            Text = "Freighter",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 5)
        };
        layout.Controls.Add(titleLabel, 0, row);
        layout.SetColumnSpan(titleLabel, 2);
        row++;

        // Row 1: Name
        _freighterName = new TextBox { Dock = DockStyle.Fill };
        AddRow(layout, "Name:", _freighterName, row); row++;

        // Row 2: Type (read-only)
        _freighterType = new TextBox { Dock = DockStyle.Fill, ReadOnly = true };
        AddRow(layout, "Type:", _freighterType, row); row++;

        // Row 3: Class (editable ComboBox)
        _freighterClass = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _freighterClass.Items.AddRange(FreighterClasses);
        AddRow(layout, "Class:", _freighterClass, row); row++;

        // Row 4: Home Seed with Generate button
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
        AddRow(layout, "Home Seed:", homeSeedPanel, row); row++;

        // Row 5: Model Seed with Generate button
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
        AddRow(layout, "Model Seed:", modelSeedPanel, row); row++;

        // Row 6: Base Stats separator
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

        // Row 7: Hyperdrive
        _hyperdriveField = new NumericUpDown { Dock = DockStyle.Fill, DecimalPlaces = 2, Minimum = 0, Maximum = 999999, Increment = 0.01m };
        AddRow(layout, "Hyperdrive:", _hyperdriveField, row); row++;

        // Row 8: Fleet Coordination
        _fleetField = new NumericUpDown { Dock = DockStyle.Fill, DecimalPlaces = 2, Minimum = 0, Maximum = 999999, Increment = 0.01m };
        AddRow(layout, "Fleet Coordination:", _fleetField, row); row++;

        // Row 9: Base Info separator
        var baseInfoLabel = new Label
        {
            Text = "Base Info",
            Font = new Font(Font.FontFamily, 10, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 8, 0, 4)
        };
        layout.Controls.Add(baseInfoLabel, 0, row);
        layout.SetColumnSpan(baseInfoLabel, 2);
        row++;

        // Row 10: Items
        _baseItemsField = new TextBox { Dock = DockStyle.Fill, ReadOnly = true };
        AddRow(layout, "Items:", _baseItemsField, row); row++;

        // Row 11: Backup/Restore buttons
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
        layout.Controls.Add(buttonPanel, 0, row);
        layout.SetColumnSpan(buttonPanel, 2);
        row++;

        // Row 12: Inventory tabs (fill)
        _generalGrid = new InventoryGridPanel { Dock = DockStyle.Fill };
        _techGrid = new InventoryGridPanel { Dock = DockStyle.Fill };

        _invTabs = new TabControl { Dock = DockStyle.Fill };
        var generalPage = new TabPage("Cargo");
        generalPage.Controls.Add(_generalGrid);
        var techPage = new TabPage("Technology");
        techPage.Controls.Add(_techGrid);
        _invTabs.TabPages.Add(generalPage);
        _invTabs.TabPages.Add(techPage);
        layout.Controls.Add(_invTabs, 0, row);
        layout.SetColumnSpan(_invTabs, 2);

        Controls.Add(layout);
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
                _freighterType.Text = currentFreighter?.GetString("Filename") ?? "";
            }
            catch { _freighterType.Text = ""; }

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
