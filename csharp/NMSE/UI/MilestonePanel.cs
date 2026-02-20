using NMSE.Data;
using NMSE.Models;

namespace NMSE.UI;

public class MilestonePanel : UserControl
{
    private readonly Dictionary<string, NumericUpDown> _fields = new();
    private IconManager? _iconManager;

    // Icon file mappings for sections
    private readonly Dictionary<string, PictureBox> _sectionIcons = new();

    private static readonly Dictionary<string, string> SectionIconMap = new()
{
    { "Milestones", "UI-MILESTONES.PNG" },
    { "Kills", "UI-CREATURES.PNG" },
    { "Gek", "UI-GEK.PNG" },
    { "Vy'keen", "UI-VYKEEN.PNG" },
    { "Korvax", "UI-KORVAX.PNG" },
    { "Traders", "UI-TRADERS.PNG" },
    { "Warriors", "UI-WARRIORS.PNG" },
    { "Explorers", "UI-EXPLORERS.PNG" },
    { "Autophage", "UI-BUILDERS.PNG" },
    { "Pirate", "UI-PIRATE.PNG" }
};

    public MilestonePanel()
    {
        SuspendLayout();

        var outerLayout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 3,
            RowCount = 1,
            Padding = new Padding(5),
        };
        outerLayout.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 33.33f));
        outerLayout.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 33.33f));
        outerLayout.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 33.34f));
        outerLayout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));

        // Column 1: Milestones
        var col1 = CreateColumnPanel();
        var col1Inner = (TableLayoutPanel)col1.Controls[0];
        AddSectionTitle(col1Inner, "Milestones");
        AddField(col1Inner, "On Foot Exploration", "^DIST_WALKED");
        AddField(col1Inner, "Alien Encounters", "^ALIENS_MET");
        AddField(col1Inner, "Words Collected", "^WORDS_LEARNT");
        AddField(col1Inner, "Most Units Accrued", "^MONEY");
        AddField(col1Inner, "Ships Destroyed", "^ENEMIES_KILLED");
        AddField(col1Inner, "Sentinels Destroyed", "^SENTINEL_KILLS");
        AddField(col1Inner, "Space Exploration", "^DIST_WARP");
        AddField(col1Inner, "Planet Zoology Scanned", "^DISC_ALL_CREATU");
        AddSectionTitle(col1Inner, "Kills");
        AddField(col1Inner, "Predators", "^PREDS_KILLED");
        AddField(col1Inner, "Sentinel Drones", "^DRONES_KILLED");
        AddField(col1Inner, "Sentinel Quads", "^QUADS_KILLED");
        AddField(col1Inner, "Sentinel Walkers", "^WALKERS_KILLED");
        AddField(col1Inner, "Pirates", "^PIRATES_KILLED");
        AddField(col1Inner, "Police", "^POLICE_KILLED");

        // Column 2: Alien Factions
        var col2 = CreateColumnPanel();
        var col2Inner = (TableLayoutPanel)col2.Controls[0];
        AddSectionTitle(col2Inner, "Alien Factions");
        AddSectionTitle(col2Inner, "Gek");
        AddField(col2Inner, "Standing", "^TRA_STANDING");
        AddField(col2Inner, "Missions", "^TDONE_MISSIONS");
        AddField(col2Inner, "Systems Visited", "^TSEEN_SYSTEMS");
        AddSectionTitle(col2Inner, "Vy'keen");
        AddField(col2Inner, "Standing", "^WAR_STANDING");
        AddField(col2Inner, "Missions", "^WDONE_MISSIONS");
        AddField(col2Inner, "Systems Visited", "^WSEEN_SYSTEMS");
        AddSectionTitle(col2Inner, "Korvax");
        AddField(col2Inner, "Standing", "^EXP_STANDING");
        AddField(col2Inner, "Missions", "^EDONE_MISSIONS");
        AddField(col2Inner, "Systems Visited", "^ESEEN_SYSTEMS");
        AddSectionTitle(col2Inner, "Autophage");
        AddField(col2Inner, "Standing", "^BUI_STANDING");
        AddField(col2Inner, "Missions", "^BDONE_MISSIONS");
        AddField(col2Inner, "Systems Visited", "^DISC_SYSTEMS");

        // Column 3: Guilds
        var col3 = CreateColumnPanel();
        var col3Inner = (TableLayoutPanel)col3.Controls[0];
        AddSectionTitle(col3Inner, "Guilds");
        AddSectionTitle(col3Inner, "Traders");
        AddField(col3Inner, "Standing", "^TGUILD_STAND");
        AddField(col3Inner, "Missions", "^TGDONE_MISSIONS");
        AddField(col3Inner, "Plants Farmed", "^PLANTS_PLANTED");
        AddSectionTitle(col3Inner, "Warriors");
        AddField(col3Inner, "Standing", "^WGUILD_STAND");
        AddField(col3Inner, "Missions", "^WGDONE_MISSIONS");
        AddSectionTitle(col3Inner, "Explorers");
        AddField(col3Inner, "Standing", "^EGUILD_STAND");
        AddField(col3Inner, "Missions", "^EGDONE_MISSIONS");
        AddField(col3Inner, "Rare Creatures", "^RARE_SCANNED");
        AddSectionTitle(col3Inner, "Pirate");
        AddField(col3Inner, "Standing", "^PIRATE_STAND");
        AddField(col3Inner, "Missions", "^PDONE_MISSIONS");
        AddField(col3Inner, "Systems Visited", "^PIRATE_SYSTEMS");

        outerLayout.Controls.Add(col1, 0, 0);
        outerLayout.Controls.Add(col2, 1, 0);
        outerLayout.Controls.Add(col3, 2, 0);

        Controls.Add(outerLayout);
        ResumeLayout(false);
        PerformLayout();
    }

    public void SetIconManager(IconManager? iconManager)
    {
        _iconManager = iconManager;
        LoadSectionIcons();
    }

    private void LoadSectionIcons()
    {
        if (_iconManager == null) return;
        foreach (var kvp in _sectionIcons)
        {
            if (SectionIconMap.TryGetValue(kvp.Key, out string? filename))
            {
                var icon = _iconManager.GetIcon(filename);
                if (icon != null)
                    kvp.Value.Image = icon;
            }
        }
    }

    private static Panel CreateColumnPanel()
    {
        var panel = new Panel { Dock = DockStyle.Fill, AutoScroll = true };
        var inner = new TableLayoutPanel
        {
            Dock = DockStyle.Top,
            AutoSize = true,
            ColumnCount = 2,
            RowCount = 0,
            Padding = new Padding(5),
        };
        inner.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 50));
        inner.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 50));
        panel.Controls.Add(inner);
        return panel;
    }

    private void AddSectionTitle(TableLayoutPanel panel, string title)
    {
        int row = panel.RowCount++;
        panel.RowStyles.Add(new RowStyle(SizeType.AutoSize));

        var container = new FlowLayoutPanel
        {
            FlowDirection = FlowDirection.LeftToRight,
            AutoSize = true,
            WrapContents = false,
            Margin = Padding.Empty,
        };

        // Add icon placeholder (loaded when SetIconManager is called)
        if (SectionIconMap.ContainsKey(title))
        {
            var iconBox = new PictureBox
            {
                Size = new Size(24, 24),
                SizeMode = PictureBoxSizeMode.Zoom,
                Margin = new Padding(0, 4, 4, 0),
            };
            _sectionIcons[title] = iconBox;
            container.Controls.Add(iconBox);
        }

        var label = new Label
        {
            Text = title,
            Font = new Font(Control.DefaultFont.FontFamily, 10, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 6, 0, 2),
        };
        container.Controls.Add(label);

        panel.Controls.Add(container, 0, row);
        panel.SetColumnSpan(container, 2);
    }

    private void AddField(TableLayoutPanel panel, string labelText, string statId)
    {
        int row = panel.RowCount++;
        panel.RowStyles.Add(new RowStyle(SizeType.AutoSize));

        var label = new Label
        {
            Text = labelText,
            AutoSize = true,
            Anchor = AnchorStyles.Left,
            Padding = new Padding(10, 3, 0, 0),
        };
        panel.Controls.Add(label, 0, row);

        var nud = new NumericUpDown
        {
            Minimum = int.MinValue,
            Maximum = int.MaxValue,
            Dock = DockStyle.Fill,
        };
        panel.Controls.Add(nud, 1, row);
        _fields[statId] = nud;
    }

    private static JsonArray? FindGlobalStats(JsonObject saveData)
    {
        var playerState = saveData.GetObject("PlayerStateData");
        if (playerState == null) return null;
        var statsArr = playerState.GetArray("Stats");
        if (statsArr == null) return null;
        for (int i = 0; i < statsArr.Length; i++)
        {
            var group = statsArr.GetObject(i);
            if (group != null && (group.GetString("GroupId") ?? "") == "^GLOBAL_STATS")
                return group.GetArray("Stats");
        }
        return null;
    }

    public void LoadData(JsonObject saveData)
    {
        foreach (var nud in _fields.Values)
            nud.Value = 0;

        var entries = FindGlobalStats(saveData);
        if (entries == null) return;

        for (int i = 0; i < entries.Length; i++)
        {
            var entry = entries.GetObject(i);
            if (entry == null) continue;
            string? id = entry.GetString("Id");
            if (id == null || !_fields.TryGetValue(id, out var nud)) continue;

            int val = 0;
            try
            {
                var valueObj = entry.GetObject("Value");
                if (valueObj != null)
                    val = valueObj.GetInt("IntValue");
            }
            catch { }
            nud.Value = Math.Max(nud.Minimum, Math.Min(nud.Maximum, val));
        }
    }

    public void SaveData(JsonObject saveData)
    {
        var entries = FindGlobalStats(saveData);
        if (entries == null) return;

        for (int i = 0; i < entries.Length; i++)
        {
            var entry = entries.GetObject(i);
            if (entry == null) continue;
            string? id = entry.GetString("Id");
            if (id == null || !_fields.TryGetValue(id, out var nud)) continue;

            var valueObj = entry.GetObject("Value");
            if (valueObj != null)
                valueObj.Set("IntValue", (int)nud.Value);
        }
    }
}
