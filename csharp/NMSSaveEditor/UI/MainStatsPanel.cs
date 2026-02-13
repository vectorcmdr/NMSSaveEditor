using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class MainStatsPanel : UserControl
{
    private readonly NumericUpDown _healthField;
    private readonly NumericUpDown _shieldField;
    private readonly NumericUpDown _energyField;
    private readonly NumericUpDown _unitsField;
    private readonly NumericUpDown _nanitesField;
    private readonly NumericUpDown _quicksilverField;
    private readonly DataGridView _globalStatsGrid;

    private static readonly (string Id, string DisplayName)[] GlobalStatDefinitions =
    {
        ("^TRA_STANDING", "Gek Standing"),
        ("^TDONE_MISSIONS", "Gek Missions"),
        ("^TSEEN_SYSTEMS", "Gek Systems"),
        ("^TGUILD_STAND", "Traders Guild Standing"),
        ("^TGDONE_MISSIONS", "Traders Guild Missions"),
        ("^PLANTS_PLANTED", "Plants Farmed"),
        ("^WAR_STANDING", "Vy'keen Standing"),
        ("^WDONE_MISSIONS", "Vy'keen Missions"),
        ("^WSEEN_SYSTEMS", "Vy'keen Systems"),
        ("^WGUILD_STAND", "Warriors Guild Standing"),
        ("^WGDONE_MISSIONS", "Warriors Guild Missions"),
        ("^EXP_STANDING", "Korvax Standing"),
        ("^EDONE_MISSIONS", "Korvax Missions"),
        ("^ESEEN_SYSTEMS", "Korvax Systems"),
        ("^EGUILD_STAND", "Explorers Guild Standing"),
        ("^EGDONE_MISSIONS", "Explorers Guild Missions"),
        ("^RARE_SCANNED", "Rare Creatures Scanned"),
        ("^PREDS_KILLED", "Predators Killed"),
        ("^DRONES_KILLED", "Drones Killed"),
        ("^WALKERS_KILLED", "Walkers Killed"),
        ("^QUADS_KILLED", "Quads Killed"),
        ("^PIRATES_KILLED", "Pirates Killed"),
        ("^POLICE_KILLED", "Police Killed"),
        ("^DIST_WALKED", "Distance Walked"),
        ("^ALIENS_MET", "Aliens Met"),
        ("^WORDS_LEARNT", "Words Learnt"),
        ("^MONEY", "Money"),
        ("^ENEMIES_KILLED", "Ships Destroyed"),
        ("^SENTINEL_KILLS", "Sentinels Destroyed"),
        ("^DIST_WARP", "Distance Warped"),
        ("^DISC_ALL_CREATU", "Planet Zoology Scanned"),
    };

    public MainStatsPanel()
    {
        SuspendLayout();
        _healthField = new NumericUpDown { Maximum = 999999, Width = 150, Anchor = AnchorStyles.Left | AnchorStyles.Top };
        _shieldField = new NumericUpDown { Maximum = 999999, Width = 150, Anchor = AnchorStyles.Left | AnchorStyles.Top };
        _energyField = new NumericUpDown { Maximum = 999999, Width = 150, Anchor = AnchorStyles.Left | AnchorStyles.Top };
        _unitsField = new NumericUpDown { Maximum = int.MaxValue, Width = 150, Anchor = AnchorStyles.Left | AnchorStyles.Top };
        _nanitesField = new NumericUpDown { Maximum = int.MaxValue, Width = 150, Anchor = AnchorStyles.Left | AnchorStyles.Top };
        _quicksilverField = new NumericUpDown { Maximum = int.MaxValue, Width = 150, Anchor = AnchorStyles.Left | AnchorStyles.Top };

        _globalStatsGrid = new DataGridView
        {
            AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill,
            AllowUserToAddRows = false,
            AllowUserToDeleteRows = false,
            AllowUserToResizeRows = false,
            RowHeadersVisible = false,
            SelectionMode = DataGridViewSelectionMode.FullRowSelect,
            Dock = DockStyle.Fill,
            Height = 400,
        };
        _globalStatsGrid.Columns.Add(new DataGridViewTextBoxColumn
        {
            Name = "Stat",
            HeaderText = "Stat",
            ReadOnly = true,
            FillWeight = 60,
        });
        _globalStatsGrid.Columns.Add(new DataGridViewTextBoxColumn
        {
            Name = "Value",
            HeaderText = "Value",
            ReadOnly = false,
            FillWeight = 40,
        });
        // Hidden column to store the stat Id for save-back
        _globalStatsGrid.Columns.Add(new DataGridViewTextBoxColumn
        {
            Name = "StatId",
            Visible = false,
        });

        InitializeLayout();
        ResumeLayout(false);
        PerformLayout();
    }

    private void InitializeLayout()
    {
        var layout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 2,
            RowCount = 8,
            Padding = new Padding(20),
            AutoScroll = true
        };
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize));
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100));

        var titleLabel = new Label
        {
            Text = "Player Statistics",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 10)
        };
        layout.Controls.Add(titleLabel, 0, 0);
        layout.SetColumnSpan(titleLabel, 2);

        AddRow(layout, "Health:", _healthField, 1);
        AddRow(layout, "Shield:", _shieldField, 2);
        AddRow(layout, "Energy:", _energyField, 3);
        AddRow(layout, "Units:", _unitsField, 4);
        AddRow(layout, "Nanites:", _nanitesField, 5);
        AddRow(layout, "Quicksilver:", _quicksilverField, 6);

        layout.Controls.Add(_globalStatsGrid, 0, 7);
        layout.SetColumnSpan(_globalStatsGrid, 2);

        Controls.Add(layout);
    }

    private static void AddRow(TableLayoutPanel layout, string label, Control field, int row)
    {
        var lbl = new Label { Text = label, AutoSize = true, Anchor = AnchorStyles.Left, Padding = new Padding(0, 6, 10, 0) };
        layout.Controls.Add(lbl, 0, row);
        layout.Controls.Add(field, 1, row);
    }

    public void LoadData(JsonObject saveData)
    {
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            SetNumericValue(_healthField, playerState, "Health");
            SetNumericValue(_shieldField, playerState, "Shield");
            SetNumericValue(_energyField, playerState, "Energy");
            SetNumericValue(_unitsField, playerState, "Units");
            SetNumericValue(_nanitesField, playerState, "Nanites");
            SetNumericValue(_quicksilverField, playerState, "Specials");

            LoadGlobalStats(playerState);
        }
        catch { /* Ignore missing fields */ }
    }

    private void LoadGlobalStats(JsonObject playerState)
    {
        _globalStatsGrid.Rows.Clear();

        var globalStats = FindGlobalStats(playerState);
        if (globalStats == null) return;

        // Build a lookup from stat Id to its IntValue
        var statValues = new Dictionary<string, int>();
        for (int i = 0; i < globalStats.Length; i++)
        {
            var entry = globalStats.GetObject(i);
            if (entry == null) continue;
            var id = entry.GetString("Id");
            if (id == null) continue;
            var valueObj = entry.GetObject("Value");
            int intValue = 0;
            if (valueObj != null && valueObj.Contains("IntValue"))
                intValue = valueObj.GetInt("IntValue");
            statValues[id] = intValue;
        }

        foreach (var (id, displayName) in GlobalStatDefinitions)
        {
            statValues.TryGetValue(id, out int val);
            _globalStatsGrid.Rows.Add(displayName, val, id);
        }
    }

    private static JsonArray? FindGlobalStats(JsonObject playerState)
    {
        var statsArray = playerState.GetArray("Stats");
        if (statsArray == null) return null;

        for (int i = 0; i < statsArray.Length; i++)
        {
            var entry = statsArray.GetObject(i);
            if (entry != null && entry.GetString("GroupId") == "^GLOBAL_STATS")
                return entry.GetArray("Stats");
        }
        return null;
    }

    public void SaveData(JsonObject saveData)
    {
        var playerState = saveData.GetObject("PlayerStateData");
        if (playerState == null) return;

        playerState.Set("Health", (int)_healthField.Value);
        playerState.Set("Shield", (int)_shieldField.Value);
        playerState.Set("Energy", (int)_energyField.Value);
        playerState.Set("Units", (int)_unitsField.Value);
        playerState.Set("Nanites", (int)_nanitesField.Value);
        playerState.Set("Specials", (int)_quicksilverField.Value);

        SaveGlobalStats(playerState);
    }

    private void SaveGlobalStats(JsonObject playerState)
    {
        var globalStats = FindGlobalStats(playerState);
        if (globalStats == null) return;

        // Build a lookup from stat Id to its entry for fast updates
        var statEntries = new Dictionary<string, JsonObject>();
        for (int i = 0; i < globalStats.Length; i++)
        {
            var entry = globalStats.GetObject(i);
            if (entry == null) continue;
            var id = entry.GetString("Id");
            if (id != null)
                statEntries[id] = entry;
        }

        foreach (DataGridViewRow row in _globalStatsGrid.Rows)
        {
            var statId = row.Cells["StatId"].Value as string;
            if (statId == null) continue;

            if (!int.TryParse(row.Cells["Value"].Value?.ToString(), out int newValue))
                continue;

            if (statEntries.TryGetValue(statId, out var entry))
            {
                var valueObj = entry.GetObject("Value");
                if (valueObj != null)
                    valueObj.Set("IntValue", newValue);
            }
        }
    }

    private static void SetNumericValue(NumericUpDown field, JsonObject data, string key)
    {
        if (data.Contains(key))
        {
            try
            {
                var value = data.GetValue(key);
                long numericValue = Convert.ToInt64(value) & 0xFFFFFFFFL; // unsigned 32-bit mask
                field.Value = Math.Min(numericValue, (long)field.Maximum);
            }
            catch { }
        }
    }
}
