using NMSSaveEditor.Data;
using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class MultitoolPanel : UserControl
{
    private readonly ComboBox _toolSelector;
    private readonly TextBox _toolName;
    private readonly TextBox _toolClass;
    private readonly TabControl _invTabs;
    private readonly InventoryGridPanel _inventoryGrid;
    private readonly InventoryGridPanel _techGrid;
    private JsonArray? _multitools;
    private JsonObject? _playerState;
    private GameItemDatabase? _database;

    public MultitoolPanel()
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
            Text = "Multitools",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 5)
        };
        layout.Controls.Add(titleLabel, 0, 0);
        layout.SetColumnSpan(titleLabel, 2);

        _toolSelector = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _toolSelector.SelectedIndexChanged += OnToolSelected;
        AddRow(layout, "Select Multitool:", _toolSelector, 1);

        _toolName = new TextBox { Dock = DockStyle.Fill };
        AddRow(layout, "Name:", _toolName, 2);

        _toolClass = new TextBox { Dock = DockStyle.Fill, ReadOnly = true };
        AddRow(layout, "Class:", _toolClass, 3);

        _inventoryGrid = new InventoryGridPanel { Dock = DockStyle.Fill };
        _techGrid = new InventoryGridPanel { Dock = DockStyle.Fill };

        _invTabs = new TabControl { Dock = DockStyle.Fill };
        var invPage = new TabPage("Inventory");
        invPage.Controls.Add(_inventoryGrid);
        var techPage = new TabPage("Technology");
        techPage.Controls.Add(_techGrid);
        _invTabs.TabPages.Add(invPage);
        _invTabs.TabPages.Add(techPage);

        layout.Controls.Add(_invTabs, 0, 4);
        layout.SetColumnSpan(_invTabs, 2);

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

            _multitools = _playerState.GetArray("Multitools");
            _toolSelector.Items.Clear();

            if (_multitools != null)
            {
                for (int i = 0; i < _multitools.Length; i++)
                {
                    try
                    {
                        var tool = _multitools.GetObject(i);
                        string name = tool.GetString("Name") ?? $"Multitool {i + 1}";
                        _toolSelector.Items.Add(name);
                    }
                    catch
                    {
                        _toolSelector.Items.Add($"Multitool {i + 1}");
                    }
                }
            }

            if (_toolSelector.Items.Count == 0)
            {
                // Older saves use WeaponInventory directly
                var weaponInv = _playerState.GetObject("WeaponInventory");
                if (weaponInv != null)
                {
                    _toolSelector.Items.Add("Primary Weapon");
                    _toolName.Text = "Primary Weapon";
                    _inventoryGrid.LoadInventory(weaponInv);
                }
            }
            else
            {
                int current = 0;
                try { current = _playerState.GetInt("CurrentWeapon"); } catch { }
                _toolSelector.SelectedIndex = Math.Min(current, _toolSelector.Items.Count - 1);
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
            if (multitools != null && _toolSelector.SelectedIndex >= 0 && _toolSelector.SelectedIndex < multitools.Length)
            {
                var tool = multitools.GetObject(_toolSelector.SelectedIndex);
                if (!string.IsNullOrEmpty(_toolName.Text))
                    tool.Set("Name", _toolName.Text);
                _inventoryGrid.SaveInventory(tool.GetObject("Inventory"));
                _techGrid.SaveInventory(tool.GetObject("Inventory_TechOnly"));
            }
            else
            {
                var weaponInv = playerState.GetObject("WeaponInventory");
                _inventoryGrid.SaveInventory(weaponInv);
            }
        }
        catch { }
    }

    private void OnToolSelected(object? sender, EventArgs e)
    {
        try
        {
            if (_multitools == null || _toolSelector.SelectedIndex < 0) return;
            int idx = _toolSelector.SelectedIndex;
            if (idx >= _multitools.Length) return;

            var tool = _multitools.GetObject(idx);
            _toolName.Text = tool.GetString("Name") ?? "";

            string cls = "";
            try
            {
                var inv = tool.GetObject("Inventory");
                var classObj = inv?.GetObject("Class");
                cls = classObj?.GetString("InventoryClass") ?? "";
            }
            catch { }
            _toolClass.Text = cls;

            _inventoryGrid.LoadInventory(tool.GetObject("Inventory"));
            _techGrid.LoadInventory(tool.GetObject("Inventory_TechOnly"));
        }
        catch { }
    }
}
