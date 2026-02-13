using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class AccountPanel : UserControl
{
    private readonly TabControl _tabControl;
    private readonly DataGridView _seasonGrid;
    private readonly DataGridView _twitchGrid;
    private readonly DataGridView _platformGrid;

    public AccountPanel()
    {
        SuspendLayout();

        _tabControl = new TabControl { Dock = DockStyle.Fill };

        _seasonGrid = CreateRewardGrid();
        _twitchGrid = CreateRewardGrid();
        _platformGrid = CreateRewardGrid();

        _tabControl.TabPages.Add(CreateTab("Season Rewards", _seasonGrid));
        _tabControl.TabPages.Add(CreateTab("Twitch Rewards", _twitchGrid));
        _tabControl.TabPages.Add(CreateTab("Platform Rewards", _platformGrid));

        Controls.Add(_tabControl);

        ResumeLayout(false);
        PerformLayout();
    }

    private static DataGridView CreateRewardGrid()
    {
        var grid = new DataGridView
        {
            AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill,
            AllowUserToAddRows = false,
            AllowUserToDeleteRows = false,
            AllowUserToResizeRows = false,
            RowHeadersVisible = false,
            SelectionMode = DataGridViewSelectionMode.FullRowSelect,
            Dock = DockStyle.Fill,
        };
        grid.Columns.Add(new DataGridViewTextBoxColumn
        {
            Name = "Index",
            HeaderText = "Index",
            ReadOnly = true,
            FillWeight = 20,
        });
        grid.Columns.Add(new DataGridViewTextBoxColumn
        {
            Name = "RewardId",
            HeaderText = "Reward ID",
            ReadOnly = false,
            FillWeight = 80,
        });
        return grid;
    }

    private TabPage CreateTab(string title, DataGridView grid)
    {
        var page = new TabPage(title);
        var panel = new Panel { Dock = DockStyle.Fill };

        var buttonPanel = new FlowLayoutPanel
        {
            Dock = DockStyle.Bottom,
            Height = 35,
            FlowDirection = FlowDirection.LeftToRight,
            Padding = new Padding(5),
        };

        var addButton = new Button { Text = "Add Reward", AutoSize = true };
        var removeButton = new Button { Text = "Remove Selected", AutoSize = true };

        addButton.Click += (_, _) =>
        {
            int index = grid.Rows.Count;
            grid.Rows.Add(index, "");
        };

        removeButton.Click += (_, _) =>
        {
            if (grid.SelectedRows.Count == 0) return;
            foreach (DataGridViewRow row in grid.SelectedRows)
                if (!row.IsNewRow) grid.Rows.Remove(row);
            // Re-index
            for (int i = 0; i < grid.Rows.Count; i++)
                grid.Rows[i].Cells["Index"].Value = i;
        };

        buttonPanel.Controls.Add(addButton);
        buttonPanel.Controls.Add(removeButton);

        panel.Controls.Add(grid);
        panel.Controls.Add(buttonPanel);
        page.Controls.Add(panel);
        return page;
    }

    public void LoadData(JsonObject saveData)
    {
        try
        {
            var userSettings = saveData.GetObject("UserSettingsData");
            if (userSettings == null) return;

            LoadRewardList(_seasonGrid, userSettings.GetArray("UnlockedSeasonRewards"));
            LoadRewardList(_twitchGrid, userSettings.GetArray("UnlockedTwitchRewards"));
            LoadRewardList(_platformGrid, userSettings.GetArray("UnlockedPlatformRewards"));
        }
        catch { /* Ignore missing fields */ }
    }

    private static void LoadRewardList(DataGridView grid, JsonArray? rewards)
    {
        grid.Rows.Clear();
        if (rewards == null) return;

        for (int i = 0; i < rewards.Length; i++)
        {
            var rewardId = rewards.GetString(i) ?? "";
            grid.Rows.Add(i, rewardId);
        }
    }

    public void SaveData(JsonObject saveData)
    {
        var userSettings = saveData.GetObject("UserSettingsData");
        if (userSettings == null) return;

        SaveRewardList(_seasonGrid, userSettings, "UnlockedSeasonRewards");
        SaveRewardList(_twitchGrid, userSettings, "UnlockedTwitchRewards");
        SaveRewardList(_platformGrid, userSettings, "UnlockedPlatformRewards");
    }

    private static void SaveRewardList(DataGridView grid, JsonObject userSettings, string key)
    {
        var array = userSettings.GetArray(key);
        if (array == null) return;

        array.Clear();
        foreach (DataGridViewRow row in grid.Rows)
        {
            var rewardId = row.Cells["RewardId"].Value?.ToString() ?? "";
            array.Add(rewardId);
        }
    }
}
