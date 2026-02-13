using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class MilestonePanel : UserControl
{
    private readonly DataGridView _milestoneGrid;
    private readonly Label _countLabel;

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
        _milestoneGrid.Columns.Add("Category", "Category");
        _milestoneGrid.Columns.Add("Progress", "Progress");
        _milestoneGrid.Columns["Category"]!.ReadOnly = true;
        layout.Controls.Add(_milestoneGrid, 0, 2);

        Controls.Add(layout);
        ResumeLayout(false);
        PerformLayout();
    }

    public void LoadData(JsonObject saveData)
    {
        _milestoneGrid.Rows.Clear();
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            // Try newer MilestoneStates first, then MilestoneData
            var milestones = playerState.GetObject("MilestoneStates")
                             ?? playerState.GetObject("MilestoneData");

            if (milestones != null)
            {
                var names = milestones.Names();
                foreach (var name in names)
                {
                    try
                    {
                        var val = milestones.Get(name);
                        string progress;
                        if (val is JsonObject obj)
                        {
                            // Try to get a progress/value field from the milestone object
                            string? p = null;
                            try { p = obj.Get("Value")?.ToString(); } catch { }
                            if (p == null) try { p = obj.Get("Progress")?.ToString(); } catch { }
                            if (p == null) try { p = obj.Get("Amount")?.ToString(); } catch { }
                            progress = p ?? obj.Length.ToString() + " fields";
                        }
                        else
                        {
                            progress = val?.ToString() ?? "";
                        }
                        _milestoneGrid.Rows.Add(name, progress);
                    }
                    catch { }
                }
                _countLabel.Text = $"Total milestones: {names.Count}";
                return;
            }

            // Try array-based milestone data
            var milestoneArr = playerState.GetArray("MilestoneStates")
                               ?? playerState.GetArray("MilestoneData");
            if (milestoneArr != null)
            {
                for (int i = 0; i < milestoneArr.Length; i++)
                {
                    try
                    {
                        var milestone = milestoneArr.GetObject(i);
                        string category = milestone.GetString("Id") ?? milestone.GetString("Name") ?? $"Milestone {i}";
                        string progress = "";
                        try { progress = milestone.Get("Value")?.ToString() ?? milestone.Get("Progress")?.ToString() ?? ""; }
                        catch { }
                        _milestoneGrid.Rows.Add(category, progress);
                    }
                    catch { }
                }
                _countLabel.Text = $"Total milestones: {milestoneArr.Length}";
                return;
            }

            _countLabel.Text = "No milestone data found.";
        }
        catch { _countLabel.Text = "Failed to load milestone data."; }
    }

    public void SaveData(JsonObject saveData)
    {
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            var milestones = playerState.GetObject("MilestoneStates")
                             ?? playerState.GetObject("MilestoneData");
            if (milestones == null) return;

            for (int i = 0; i < _milestoneGrid.Rows.Count; i++)
            {
                try
                {
                    var row = _milestoneGrid.Rows[i];
                    string? category = row.Cells["Category"].Value?.ToString();
                    string? progress = row.Cells["Progress"].Value?.ToString();
                    if (category == null || progress == null) continue;

                    var val = milestones.Get(category);
                    if (val is JsonObject obj)
                    {
                        if (obj.Contains("Value") && int.TryParse(progress, out int intVal))
                            obj.Set("Value", intVal);
                        else if (obj.Contains("Progress") && int.TryParse(progress, out int progVal))
                            obj.Set("Progress", progVal);
                    }
                    else if (int.TryParse(progress, out int directVal))
                    {
                        milestones.Set(category, directVal);
                    }
                }
                catch { }
            }
        }
        catch { }
    }
}
