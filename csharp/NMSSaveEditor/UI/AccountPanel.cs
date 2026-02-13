using NMSSaveEditor.IO;
using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class AccountPanel : UserControl
{
    private readonly TabControl _tabControl;
    private readonly DataGridView _seasonGrid;
    private readonly DataGridView _twitchGrid;
    private readonly DataGridView _platformGrid;
    private readonly Label _statusLabel;
    private JsonObject? _accountData;
    private string? _accountFilePath;

    public AccountPanel()
    {
        SuspendLayout();

        var mainLayout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 1,
            RowCount = 3,
        };
        mainLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        mainLayout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));
        mainLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));

        // Top: account file info
        var infoPanel = new FlowLayoutPanel
        {
            Dock = DockStyle.Fill,
            AutoSize = true,
            FlowDirection = FlowDirection.LeftToRight,
            Padding = new Padding(5),
        };
        _statusLabel = new Label
        {
            Text = "Account data: Not loaded. Select a save directory to auto-detect accountdata.hg.",
            AutoSize = true,
            Padding = new Padding(0, 5, 0, 5),
        };
        infoPanel.Controls.Add(_statusLabel);
        mainLayout.Controls.Add(infoPanel, 0, 0);

        // Middle: tabs
        _tabControl = new TabControl { Dock = DockStyle.Fill };

        _seasonGrid = CreateRewardGrid();
        _twitchGrid = CreateRewardGrid();
        _platformGrid = CreateRewardGrid();

        _tabControl.TabPages.Add(CreateTab("Season Rewards", _seasonGrid));
        _tabControl.TabPages.Add(CreateTab("Twitch Rewards", _twitchGrid));
        _tabControl.TabPages.Add(CreateTab("Platform Rewards", _platformGrid));

        mainLayout.Controls.Add(_tabControl, 0, 1);

        // Bottom: save button
        var bottomPanel = new FlowLayoutPanel
        {
            Dock = DockStyle.Fill,
            AutoSize = true,
            FlowDirection = FlowDirection.LeftToRight,
            Padding = new Padding(5),
        };
        var saveAccountButton = new Button { Text = "Save Account Data", AutoSize = true };
        saveAccountButton.Click += OnSaveAccount;
        bottomPanel.Controls.Add(saveAccountButton);
        mainLayout.Controls.Add(bottomPanel, 0, 2);

        Controls.Add(mainLayout);

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

    /// <summary>
    /// Load account data from accountdata.hg in the given directory.
    /// NMS stores account rewards in a separate file from save data.
    /// </summary>
    public void LoadAccountFile(string saveDirectory)
    {
        _seasonGrid.Rows.Clear();
        _twitchGrid.Rows.Clear();
        _platformGrid.Rows.Clear();
        _accountData = null;
        _accountFilePath = null;

        string accountPath = Path.Combine(saveDirectory, "accountdata.hg");
        if (!File.Exists(accountPath))
        {
            _statusLabel.Text = "Account data: accountdata.hg not found in save directory.";
            return;
        }

        try
        {
            _accountData = SaveFileManager.LoadSaveFile(accountPath);
            _accountFilePath = accountPath;

            var userSettings = _accountData.GetObject("UserSettingsData")
                            ?? _accountData; // fallback: root may be the settings

            LoadRewardList(_seasonGrid, userSettings.GetArray("UnlockedSeasonRewards"));
            LoadRewardList(_twitchGrid, userSettings.GetArray("UnlockedTwitchRewards"));
            LoadRewardList(_platformGrid, userSettings.GetArray("UnlockedPlatformRewards"));

            int total = _seasonGrid.Rows.Count + _twitchGrid.Rows.Count + _platformGrid.Rows.Count;
            _statusLabel.Text = $"Account data: Loaded from {Path.GetFileName(accountPath)} ({total} rewards)";
        }
        catch (Exception ex)
        {
            _statusLabel.Text = $"Account data: Error loading {accountPath}: {ex.Message}";
        }
    }

    /// <summary>
    /// Legacy LoadData for compatibility - tries to load from save data (won't find rewards there).
    /// The real loading is done via LoadAccountFile() called from MainForm.
    /// </summary>
    public void LoadData(JsonObject saveData)
    {
        // Account rewards are in accountdata.hg, not in the save file.
        // LoadAccountFile() handles the actual loading.
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
        // Account data is saved separately via OnSaveAccount
    }

    private void OnSaveAccount(object? sender, EventArgs e)
    {
        if (_accountData == null || _accountFilePath == null)
        {
            MessageBox.Show("No account data loaded.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            return;
        }

        try
        {
            var userSettings = _accountData.GetObject("UserSettingsData")
                            ?? _accountData;

            SaveRewardList(_seasonGrid, userSettings, "UnlockedSeasonRewards");
            SaveRewardList(_twitchGrid, userSettings, "UnlockedTwitchRewards");
            SaveRewardList(_platformGrid, userSettings, "UnlockedPlatformRewards");

            SaveFileManager.SaveToFile(_accountFilePath, _accountData);
            MessageBox.Show("Account data saved successfully.", "Success", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }
        catch (Exception ex)
        {
            MessageBox.Show($"Error saving account data: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
    }

    private static void SaveRewardList(DataGridView grid, JsonObject userSettings, string key)
    {
        var array = userSettings.GetArray(key);
        if (array == null)
        {
            array = new JsonArray();
            userSettings.Set(key, array);
        }

        array.Clear();
        foreach (DataGridViewRow row in grid.Rows)
        {
            var rewardId = row.Cells["RewardId"].Value?.ToString() ?? "";
            if (!string.IsNullOrEmpty(rewardId))
                array.Add(rewardId);
        }
    }
}
