using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class MilestonePanel : UserControl
{
    private readonly DataGridView _milestoneGrid;
    private readonly Label _countLabel;
    private enum DataSource { None, MilestoneStates, GlobalStats }
    private DataSource _source = DataSource.None;

    public MilestonePanel()
    {
        SuspendLayout();

        var layout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 1,
            RowCount = 3,
            Padding = new Padding(10)
        };
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));

        var titleLabel = new Label
        {
            Text = "Milestones",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 5)
        };
        layout.Controls.Add(titleLabel, 0, 0);

        _countLabel = new Label { Text = "No milestone data loaded.", AutoSize = true };
        layout.Controls.Add(_countLabel, 0, 1);

        _milestoneGrid = new DataGridView
        {
            Dock = DockStyle.Fill,
            AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill,
            AllowUserToAddRows = false,
            AllowUserToDeleteRows = false,
            SelectionMode = DataGridViewSelectionMode.FullRowSelect,
            RowHeadersVisible = false
        };
        _milestoneGrid.Columns.Add("MilestoneId", "Milestone ID");
        _milestoneGrid.Columns.Add("Value", "Value");
        _milestoneGrid.Columns["MilestoneId"]!.ReadOnly = true;
        layout.Controls.Add(_milestoneGrid, 0, 2);

        Controls.Add(layout);
        ResumeLayout(false);
        PerformLayout();
    }

    public void LoadData(JsonObject saveData)
    {
        _milestoneGrid.Rows.Clear();
        _source = DataSource.None;
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            // Try MilestoneStates array first
            var milestoneArr = playerState.GetArray("MilestoneStates")
                               ?? playerState.GetArray("MilestoneData");
            if (milestoneArr != null && milestoneArr.Length > 0)
            {
                _source = DataSource.MilestoneStates;
                for (int i = 0; i < milestoneArr.Length; i++)
                {
                    try
                    {
                        var milestone = milestoneArr.GetObject(i);
                        string id = milestone.GetString("Id") ?? milestone.GetString("Name") ?? $"Milestone {i}";
                        string value = "";
                        try { value = (milestone.Get("AmountCompleted") ?? milestone.Get("Value") ?? milestone.Get("Progress"))?.ToString() ?? ""; }
                        catch { }
                        _milestoneGrid.Rows.Add(id, value);
                    }
                    catch { }
                }
                _countLabel.Text = $"Total milestones: {milestoneArr.Length}";
                return;
            }

            // Fallback: Stats array with ^GLOBAL_STATS group
            var statsArr = playerState.GetArray("Stats");
            if (statsArr != null)
            {
                for (int i = 0; i < statsArr.Length; i++)
                {
                    try
                    {
                        var statGroup = statsArr.GetObject(i);
                        var groupId = statGroup.GetString("GroupId") ?? "";
                        if (groupId != "^GLOBAL_STATS") continue;

                        _source = DataSource.GlobalStats;
                        var entries = statGroup.GetArray("Stats") ?? statGroup.GetArray("Entries");
                        if (entries == null) continue;
                        for (int j = 0; j < entries.Length; j++)
                        {
                            try
                            {
                                var entry = entries.GetObject(j);
                                string id = entry.GetString("Id") ?? entry.GetString("Name") ?? $"Stat {j}";
                                string value = "";
                                try { value = (entry.Get("Value") ?? entry.Get("IntValue") ?? entry.Get("FloatValue"))?.ToString() ?? ""; }
                                catch { }
                                _milestoneGrid.Rows.Add(id, value);
                            }
                            catch { }
                        }
                        break;
                    }
                    catch { }
                }
                if (_milestoneGrid.Rows.Count > 0)
                {
                    _countLabel.Text = $"Total milestones: {_milestoneGrid.Rows.Count}";
                    return;
                }
            }

            _countLabel.Text = "No milestone data found.";
        }
        catch { _countLabel.Text = "Failed to load milestone data."; }
    }

    public void SaveData(JsonObject saveData)
    {
        if (_source == DataSource.None) return;
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            if (_source == DataSource.MilestoneStates)
            {
                var milestoneArr = playerState.GetArray("MilestoneStates")
                                   ?? playerState.GetArray("MilestoneData");
                if (milestoneArr == null) return;

                for (int i = 0; i < _milestoneGrid.Rows.Count && i < milestoneArr.Length; i++)
                {
                    try
                    {
                        var row = _milestoneGrid.Rows[i];
                        string? valStr = row.Cells["Value"].Value?.ToString();
                        if (valStr == null) continue;
                        if (!int.TryParse(valStr, out int intVal)) continue;

                        var milestone = milestoneArr.GetObject(i);
                        if (milestone.Contains("AmountCompleted"))
                            milestone.Set("AmountCompleted", intVal);
                        else if (milestone.Contains("Value"))
                            milestone.Set("Value", intVal);
                        else if (milestone.Contains("Progress"))
                            milestone.Set("Progress", intVal);
                    }
                    catch { }
                }
            }
            else if (_source == DataSource.GlobalStats)
            {
                var statsArr = playerState.GetArray("Stats");
                if (statsArr == null) return;

                for (int i = 0; i < statsArr.Length; i++)
                {
                    try
                    {
                        var statGroup = statsArr.GetObject(i);
                        if ((statGroup.GetString("GroupId") ?? "") != "^GLOBAL_STATS") continue;

                        var entries = statGroup.GetArray("Stats") ?? statGroup.GetArray("Entries");
                        if (entries == null) break;

                        for (int j = 0; j < _milestoneGrid.Rows.Count && j < entries.Length; j++)
                        {
                            try
                            {
                                var row = _milestoneGrid.Rows[j];
                                string? valStr = row.Cells["Value"].Value?.ToString();
                                if (valStr == null) continue;

                                var entry = entries.GetObject(j);
                                if (int.TryParse(valStr, out int intVal))
                                {
                                    if (entry.Contains("Value")) entry.Set("Value", intVal);
                                    else if (entry.Contains("IntValue")) entry.Set("IntValue", intVal);
                                }
                                else if (double.TryParse(valStr, out double dblVal))
                                {
                                    if (entry.Contains("FloatValue")) entry.Set("FloatValue", dblVal);
                                    else if (entry.Contains("Value")) entry.Set("Value", dblVal);
                                }
                            }
                            catch { }
                        }
                        break;
                    }
                    catch { }
                }
            }
        }
        catch { }
    }
}
