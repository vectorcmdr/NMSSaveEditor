using NMSE.Data;
using NMSE.Models;

namespace NMSE.UI;

public class SettlementPanel : UserControl
{
    // Max values for Stats[0..6], matching gG enum ordinals
    private static readonly int[] StatMaxValues = { 200, 100, 1000000, 1000000, 100, 10000000, 100 };
    private static readonly string[] StatLabels = { "Productivity", "Happiness", "Population", "Resources", "Maintenance", "Trade Value", "Debt Level" };
    private const int StatCount = 7;
    private const int ProductionMaxAmount = 999;

    private readonly ComboBox _settlementSelector;
    private readonly TextBox _settlementName;
    private readonly TextBox _ownerUsnField;
    private readonly TextBox _ownerUidField;
    private readonly TextBox _seedField;
    private readonly Button _generateSeedBtn;
    private readonly NumericUpDown[] _statFields;
    private readonly DataGridView _perksGrid;
    private readonly DataGridView _productionGrid;
    private readonly Label _infoLabel;

    // Filtered settlement data: indices into SettlementStatesV2
    private readonly List<int> _filteredIndices = new();
    private JsonArray? _settlements;
    private readonly Random _rng = new();
    private GameItemDatabase? _database;

    public void SetDatabase(GameItemDatabase? database) => _database = database;

    public SettlementPanel()
    {
        SuspendLayout();

        // Create a scrollable container panel
        var scrollPanel = new Panel
        {
            Dock = DockStyle.Fill,
            AutoScroll = true,
            Padding = new Padding(0)
        };

        var layout = new TableLayoutPanel
        {
            Dock = DockStyle.Top, // Important: use Top so it doesn't stretch vertically
            ColumnCount = 1,
            AutoSize = true,
            Padding = new Padding(10)
        };

        int row = 0;

        // Title
        var titleLabel = new Label
        {
            Text = "Settlements",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 5)
        };
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.Controls.Add(titleLabel, 0, row++);

        // --- Top section layout ---
        var topPanel = new TableLayoutPanel
        {
            Dock = DockStyle.Top,
            ColumnCount = 2,
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 10)
        };
        topPanel.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 50));
        topPanel.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 50));

        // Left stack for basic info
        var infoPanel = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 2,
            AutoSize = true
        };
        int infoRow = 0;
        _settlementSelector = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _settlementSelector.SelectedIndexChanged += OnSettlementSelected;
        AddRow(infoPanel, "Settlement:", _settlementSelector, infoRow++);
        _settlementName = new TextBox { Dock = DockStyle.Fill };
        AddRow(infoPanel, "Name:", _settlementName, infoRow++);
        _ownerUsnField = new TextBox { Dock = DockStyle.Fill, ReadOnly = true, TabStop = false };
        AddRow(infoPanel, "Owner USN:", _ownerUsnField, infoRow++);
        _ownerUidField = new TextBox { Dock = DockStyle.Fill, ReadOnly = true, TabStop = false };
        AddRow(infoPanel, "Owner UID:", _ownerUidField, infoRow++);
        _seedField = new TextBox { Dock = DockStyle.Fill };
        AddRow(infoPanel, "Seed:", _seedField, infoRow++);
        _generateSeedBtn = new Button
        {
            Text = "Generate",
            AutoSize = true,
            Width = 30,
            Height = 9
        };
        _generateSeedBtn.Click += (s, e) =>
        {
            byte[] bytes = new byte[8];
            _rng.NextBytes(bytes);
            _seedField.Text = "0x" + BitConverter.ToString(bytes).Replace("-", "");
        };
        AddRow(infoPanel, "", _generateSeedBtn, infoRow++);

        // Right stack for stats
        var statsPanel = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 2,
            AutoSize = true
        };
        _statFields = new NumericUpDown[StatCount];
        for (int i = 0; i < StatCount; i++)
        {
            _statFields[i] = new NumericUpDown
            {
                Dock = DockStyle.Fill,
                Maximum = StatMaxValues[i],
                Minimum = 0,
            };
            AddRow(statsPanel, $"{StatLabels[i]} (max {StatMaxValues[i]}):", _statFields[i], i);
        }

        // Add both panels to topPanel
        topPanel.Controls.Add(infoPanel, 0, 0);
        topPanel.Controls.Add(statsPanel, 1, 0);

        // Add topPanel to main layout
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.Controls.Add(topPanel, 0, row++);

        // Perks section header
        var perksLabel = new Label
        {
            Text = "Perks",
            Font = new Font(Font.FontFamily, 10, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 8, 0, 4)
        };
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.Controls.Add(perksLabel, 0, row++);

        // Perks DataGridView
        _perksGrid = new DataGridView
        {
            AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill,
            AllowUserToAddRows = false,
            AllowUserToDeleteRows = false,
            AllowUserToResizeRows = false,
            RowHeadersVisible = false,
            SelectionMode = DataGridViewSelectionMode.FullRowSelect,
            Dock = DockStyle.Fill,
            Height = 150,
        };
        _perksGrid.Columns.Add(new DataGridViewTextBoxColumn
        {
            Name = "Index",
            HeaderText = "Index",
            ReadOnly = true,
            FillWeight = 20,
        });
        _perksGrid.Columns.Add(new DataGridViewTextBoxColumn
        {
            Name = "PerkId",
            HeaderText = "Perk ID",
            ReadOnly = false,
            FillWeight = 80,
        });
        layout.RowStyles.Add(new RowStyle(SizeType.Absolute, 160));
        layout.Controls.Add(_perksGrid, 0, row++);

        // Production section header
        var productionLabel = new Label
        {
            Text = "Production",
            Font = new Font(Font.FontFamily, 10, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 8, 0, 4)
        };
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.Controls.Add(productionLabel, 0, row++);

        // Production DataGridView
        _productionGrid = new DataGridView
        {
            AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill,
            AllowUserToAddRows = false,
            AllowUserToDeleteRows = false,
            AllowUserToResizeRows = false,
            RowHeadersVisible = false,
            SelectionMode = DataGridViewSelectionMode.FullRowSelect,
            Dock = DockStyle.Fill,
            Height = 150,
        };
        _productionGrid.Columns.Add(new DataGridViewTextBoxColumn
        {
            Name = "ItemName",
            HeaderText = "Name",
            ReadOnly = true,
            FillWeight = 30,
        });
        _productionGrid.Columns.Add(new DataGridViewTextBoxColumn
        {
            Name = "ElementId",
            HeaderText = "Element ID",
            ReadOnly = false,
            FillWeight = 30,
        });
        _productionGrid.Columns.Add(new DataGridViewTextBoxColumn
        {
            Name = "Amount",
            HeaderText = "Amount",
            ReadOnly = false,
            FillWeight = 20,
        });
        _productionGrid.Columns.Add(new DataGridViewTextBoxColumn
        {
            Name = "Timestamp",
            HeaderText = "Last Change",
            ReadOnly = true,
            FillWeight = 20,
        });
        layout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));
        layout.Controls.Add(_productionGrid, 0, row++);

        // Info label
        _infoLabel = new Label
        {
            Text = "Load a save file to view settlement data.",
            AutoSize = true,
            Padding = new Padding(0, 10, 0, 0)
        };
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.Controls.Add(_infoLabel, 0, row);

        layout.RowCount = row + 1;
        scrollPanel.Controls.Add(layout);
        Controls.Add(scrollPanel);

        ResumeLayout(false);
        PerformLayout();
    }

    private static void AddRow(TableLayoutPanel layout, string label, Control field, int row)
    {
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        var lbl = new Label { Text = label, AutoSize = true, Anchor = AnchorStyles.Left, Padding = new Padding(0, 5, 10, 0) };
        layout.Controls.Add(lbl, 0, row);
        layout.Controls.Add(field, 1, row);
    }

    public void LoadData(JsonObject saveData)
    {
        _settlementSelector.Items.Clear();
        _filteredIndices.Clear();
        ClearFields();
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            _settlements = playerState.GetArray("SettlementStatesV2");
            if (_settlements == null || _settlements.Length == 0)
            {
                _infoLabel.Text = "No settlements found in this save.";
                return;
            }

            // Build set of UniverseAddress values from TeleportEndpoints with TeleporterType == "Settlement"
            var settlementAddresses = new HashSet<string>();
            var endpoints = playerState.GetArray("TeleportEndpoints");
            if (endpoints != null)
            {
                for (int i = 0; i < endpoints.Length; i++)
                {
                    try
                    {
                        var ep = endpoints.GetObject(i);
                        if ("Settlement".Equals(ep.GetString("TeleporterType"), StringComparison.Ordinal))
                        {
                            var addr = ep.Get("UniverseAddress")?.ToString();
                            if (addr != null)
                                settlementAddresses.Add(addr);
                        }
                    }
                    catch { }
                }
            }

            // Filter SettlementStatesV2 by matching UniverseAddress
            for (int i = 0; i < _settlements.Length; i++)
            {
                try
                {
                    var settlement = _settlements.GetObject(i);
                    var addr = settlement.Get("UniverseAddress")?.ToString();
                    if (addr != null && settlementAddresses.Contains(addr))
                    {
                        _filteredIndices.Add(i);
                        string name = settlement.GetString("Name") ?? $"Settlement {i + 1}";
                        _settlementSelector.Items.Add(name);
                    }
                }
                catch
                {
                    // If filtering fails, still include the settlement
                }
            }

            // If filtering yielded no results but settlements exist, show all (fallback)
            if (_filteredIndices.Count == 0 && _settlements.Length > 0)
            {
                for (int i = 0; i < _settlements.Length; i++)
                {
                    try
                    {
                        _filteredIndices.Add(i);
                        var settlement = _settlements.GetObject(i);
                        string name = settlement.GetString("Name") ?? $"Settlement {i + 1}";
                        _settlementSelector.Items.Add(name);
                    }
                    catch
                    {
                        _filteredIndices.Add(i);
                        _settlementSelector.Items.Add($"Settlement {i + 1}");
                    }
                }
            }

            if (_settlementSelector.Items.Count > 0)
                _settlementSelector.SelectedIndex = 0;

            _infoLabel.Text = $"Found {_filteredIndices.Count} settlement(s).";
        }
        catch { _infoLabel.Text = "Failed to load settlement data."; }
    }

    public void SaveData(JsonObject saveData)
    {
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            var settlements = playerState.GetArray("SettlementStatesV2");
            if (settlements == null || _settlementSelector.SelectedIndex < 0 || _filteredIndices.Count == 0) return;

            int dataIdx = _filteredIndices[_settlementSelector.SelectedIndex];
            if (dataIdx >= settlements.Length) return;

            var settlement = settlements.GetObject(dataIdx);

            // Save name
            settlement.Set("Name", _settlementName.Text);

            // Save seed
            if (!string.IsNullOrEmpty(_seedField.Text))
                settlement.Set("SeedValue", _seedField.Text);

            // Save stats
            var statsArr = settlement.GetArray("Stats");
            if (statsArr != null)
            {
                for (int i = 0; i < StatCount && i < statsArr.Length; i++)
                    statsArr.Set(i, (int)_statFields[i].Value);
            }

            // Save perks
            var perksArr = settlement.GetArray("Perks");
            if (perksArr != null)
            {
                for (int i = 0; i < _perksGrid.Rows.Count && i < perksArr.Length; i++)
                {
                    var val = _perksGrid.Rows[i].Cells["PerkId"].Value?.ToString() ?? "";
                    perksArr.Set(i, val);
                }
            }

            // Save production state
            var prodArr = settlement.GetArray("ProductionState");
            if (prodArr != null)
            {
                for (int i = 0; i < _productionGrid.Rows.Count && i < prodArr.Length; i++)
                {
                    var prodObj = prodArr.GetObject(i);
                    var elementId = _productionGrid.Rows[i].Cells["ElementId"].Value?.ToString() ?? "";
                    prodObj.Set("ElementId", elementId);

                    if (int.TryParse(_productionGrid.Rows[i].Cells["Amount"].Value?.ToString(), out int amount))
                    {
                        amount = Math.Clamp(amount, 0, ProductionMaxAmount);
                        prodObj.Set("Amount", amount);
                    }
                }
            }
        }
        catch { }
    }

    private void OnSettlementSelected(object? sender, EventArgs e)
    {
        ClearFields();
        try
        {
            if (_settlements == null || _settlementSelector.SelectedIndex < 0 || _filteredIndices.Count == 0) return;
            int dataIdx = _filteredIndices[_settlementSelector.SelectedIndex];
            if (dataIdx >= _settlements.Length) return;

            var settlement = _settlements.GetObject(dataIdx);

            // Name
            _settlementName.Text = settlement.GetString("Name") ?? "";

            // USN
            var ownerObj = settlement.GetObject("Owner");
            _ownerUsnField.Text = ownerObj?.GetString("USN") ?? "";

            // UID
            _ownerUidField.Text = ownerObj?.GetString("UID") ?? "";

            // Seed
            _seedField.Text = settlement.GetString("SeedValue") ?? "";

            // Stats array
            var statsArr = settlement.GetArray("Stats");
            if (statsArr != null)
            {
                for (int i = 0; i < StatCount && i < statsArr.Length; i++)
                {
                    try
                    {
                        int val = statsArr.GetInt(i);
                        _statFields[i].Value = Math.Clamp(val, 0, StatMaxValues[i]);
                    }
                    catch { _statFields[i].Value = 0; }
                }
            }

            // Perks array
            _perksGrid.Rows.Clear();
            var perksArr = settlement.GetArray("Perks");
            if (perksArr != null)
            {
                for (int i = 0; i < perksArr.Length; i++)
                {
                    try
                    {
                        string perkId = perksArr.GetString(i) ?? "";
                        _perksGrid.Rows.Add(i, perkId);
                    }
                    catch { _perksGrid.Rows.Add(i, ""); }
                }
            }

            // Production state
            _productionGrid.Rows.Clear();
            var prodArr = settlement.GetArray("ProductionState");
            if (prodArr != null)
            {
                for (int i = 0; i < prodArr.Length; i++)
                {
                    try
                    {
                        var prodObj = prodArr.GetObject(i);
                        string elementId = prodObj.GetString("ElementId") ?? prodObj.Get("ElementId")?.ToString() ?? "";
                        string itemName = _database?.GetItem(elementId)?.Name ?? elementId;
                        int amount = 0;
                        try { amount = prodObj.GetInt("Amount"); } catch { }
                        string timestamp = "";
                        try { timestamp = prodObj.Get("LastChangeTimestamp")?.ToString() ?? ""; } catch { }
                        _productionGrid.Rows.Add(itemName, elementId, amount, timestamp);
                    }
                    catch { }
                }
            }
        }
        catch { }
    }

    private void ClearFields()
    {
        _settlementName.Text = "";
        _ownerUsnField.Text = "";
        _ownerUidField.Text = "";
        _seedField.Text = "";
        for (int i = 0; i < StatCount; i++)
            _statFields[i].Value = 0;
        _perksGrid.Rows.Clear();
        _productionGrid.Rows.Clear();
    }
}
