using NMSSaveEditor.IO;
using NMSSaveEditor.Models;
using System.Xml;

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

    // Rewards database from rewards.xml
    private readonly List<(string Id, string Name)> _seasonRewardsDb = new();
    private readonly List<(string Id, string Name)> _twitchRewardsDb = new();
    private readonly List<(string Id, string Name)> _platformRewardsDb = new();

    public AccountPanel()
    {
        SuspendLayout();

        var mainLayout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 1,
            RowCount = 4,
        };
        mainLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        mainLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        mainLayout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));
        mainLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));

        // Top: account file info
        _statusLabel = new Label
        {
            Text = "Account data: Not loaded. Select a save directory to auto-detect accountdata.hg.",
            AutoSize = true,
            Padding = new Padding(5),
            Dock = DockStyle.Fill,
        };
        mainLayout.Controls.Add(_statusLabel, 0, 0);

        // Warning label about Twitch drops
        var warningLabel = new Label
        {
            Text = "NOTE: To use twitch drops, you must go offline before you start the game. You can claim them at the Synthesis vendor in the Anomaly.",
            AutoSize = true,
            Padding = new Padding(5, 2, 5, 5),
            Dock = DockStyle.Fill,
            ForeColor = Color.DarkOrange,
            Font = new Font(Font.FontFamily, 9, FontStyle.Bold),
        };
        mainLayout.Controls.Add(warningLabel, 0, 1);

        // Middle: tabs
        _tabControl = new TabControl { Dock = DockStyle.Fill };

        _seasonGrid = CreateRewardGrid();
        _twitchGrid = CreateRewardGrid();
        _platformGrid = CreateRewardGrid();

        _tabControl.TabPages.Add(CreateTab("Season Rewards", _seasonGrid));
        _tabControl.TabPages.Add(CreateTab("Twitch Rewards", _twitchGrid));
        _tabControl.TabPages.Add(CreateTab("Platform Rewards", _platformGrid));

        mainLayout.Controls.Add(_tabControl, 0, 2);

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
        mainLayout.Controls.Add(bottomPanel, 0, 3);

        Controls.Add(mainLayout);

        ResumeLayout(false);
        PerformLayout();
    }

    /// <summary>
    /// Load the rewards database from rewards.xml so we can show names and all possible rewards.
    /// </summary>
    public void LoadRewardsDatabase(string dbPath)
    {
        _seasonRewardsDb.Clear();
        _twitchRewardsDb.Clear();
        _platformRewardsDb.Clear();

        string rewardsPath = Path.Combine(dbPath, "rewards.xml");
        if (!File.Exists(rewardsPath)) return;

        try
        {
            var doc = new XmlDocument();
            doc.Load(rewardsPath);
            var root = doc.DocumentElement;
            if (root == null) return;

            foreach (XmlNode node in root.ChildNodes)
            {
                if (node is not XmlElement elem) continue;
                string id = elem.GetAttribute("id");
                string name = elem.GetAttribute("name");
                if (string.IsNullOrEmpty(id)) continue;

                var entry = (id, name);
                switch (elem.Name.ToLowerInvariant())
                {
                    case "season": _seasonRewardsDb.Add(entry); break;
                    case "twitch": _twitchRewardsDb.Add(entry); break;
                    case "platform": _platformRewardsDb.Add(entry); break;
                }
            }
        }
        catch { /* ignore parse errors */ }
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
            Name = "RewardId",
            HeaderText = "Reward ID",
            ReadOnly = true,
            FillWeight = 40,
        });
        grid.Columns.Add(new DataGridViewTextBoxColumn
        {
            Name = "RewardName",
            HeaderText = "Name",
            ReadOnly = true,
            FillWeight = 40,
        });
        grid.Columns.Add(new DataGridViewCheckBoxColumn
        {
            Name = "Unlocked",
            HeaderText = "Unlocked",
            FillWeight = 20,
        });
        return grid;
    }

    private static TabPage CreateTab(string title, DataGridView grid)
    {
        var page = new TabPage(title);
        page.Controls.Add(grid);
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

            var seasonUnlocked = GetUnlockedSet(userSettings.GetArray("UnlockedSeasonRewards"));
            var twitchUnlocked = GetUnlockedSet(userSettings.GetArray("UnlockedTwitchRewards"));
            var platformUnlocked = GetUnlockedSet(userSettings.GetArray("UnlockedPlatformRewards"));

            PopulateRewardGrid(_seasonGrid, _seasonRewardsDb, seasonUnlocked);
            PopulateRewardGrid(_twitchGrid, _twitchRewardsDb, twitchUnlocked);
            PopulateRewardGrid(_platformGrid, _platformRewardsDb, platformUnlocked);

            int total = seasonUnlocked.Count + twitchUnlocked.Count + platformUnlocked.Count;
            _statusLabel.Text = $"Account data: Loaded from {Path.GetFileName(accountPath)} ({total} unlocked rewards)";
        }
        catch (Exception ex)
        {
            _statusLabel.Text = $"Account data: Error loading {accountPath}: {ex.Message}";
        }
    }

    private static HashSet<string> GetUnlockedSet(JsonArray? array)
    {
        var set = new HashSet<string>(StringComparer.OrdinalIgnoreCase);
        if (array == null) return set;
        for (int i = 0; i < array.Length; i++)
        {
            var val = array.GetString(i);
            if (!string.IsNullOrEmpty(val))
                set.Add(val);
        }
        return set;
    }

    private static void PopulateRewardGrid(DataGridView grid, List<(string Id, string Name)> rewardsDb, HashSet<string> unlocked)
    {
        grid.Rows.Clear();

        if (rewardsDb.Count > 0)
        {
            // Show all possible rewards from database with unlock status
            foreach (var (id, name) in rewardsDb)
            {
                bool isUnlocked = unlocked.Contains(id);
                grid.Rows.Add(id, name, isUnlocked);
            }

            // Also add any unlocked rewards that aren't in the database
            foreach (var id in unlocked)
            {
                bool found = false;
                foreach (var (dbId, _) in rewardsDb)
                {
                    if (string.Equals(dbId, id, StringComparison.OrdinalIgnoreCase))
                    {
                        found = true;
                        break;
                    }
                }
                if (!found)
                    grid.Rows.Add(id, "(unknown)", true);
            }
        }
        else
        {
            // No database loaded - just show unlocked rewards
            foreach (var id in unlocked)
                grid.Rows.Add(id, "", true);
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
            bool unlocked = row.Cells["Unlocked"].Value is true;
            if (unlocked)
            {
                var rewardId = row.Cells["RewardId"].Value?.ToString() ?? "";
                if (!string.IsNullOrEmpty(rewardId))
                    array.Add(rewardId);
            }
        }
    }
}
