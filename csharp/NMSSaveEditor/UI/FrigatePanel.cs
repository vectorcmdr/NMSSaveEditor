using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class FrigatePanel : UserControl
{
    private readonly DataGridView _frigateGrid;
    private readonly Label _countLabel;

    public FrigatePanel()
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
            Text = "Fleet Frigates",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 5)
        };
        layout.Controls.Add(titleLabel, 0, 0);

        _countLabel = new Label { Text = "No frigates loaded.", AutoSize = true };
        layout.Controls.Add(_countLabel, 0, 1);

        _frigateGrid = new DataGridView
        {
            Dock = DockStyle.Fill,
            AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill,
            AllowUserToAddRows = false,
            AllowUserToDeleteRows = false,
            ReadOnly = true,
            SelectionMode = DataGridViewSelectionMode.FullRowSelect,
            RowHeadersVisible = false
        };
        _frigateGrid.Columns.Add("Index", "#");
        _frigateGrid.Columns.Add("Name", "Name");
        _frigateGrid.Columns.Add("Type", "Type");
        _frigateGrid.Columns.Add("Class", "Class");
        _frigateGrid.Columns.Add("Level", "Level");
        _frigateGrid.Columns["Index"]!.Width = 40;
        _frigateGrid.Columns["Index"]!.AutoSizeMode = DataGridViewAutoSizeColumnMode.None;
        layout.Controls.Add(_frigateGrid, 0, 2);

        Controls.Add(layout);
        ResumeLayout(false);
        PerformLayout();
    }

    public void LoadData(JsonObject saveData)
    {
        _frigateGrid.Rows.Clear();
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            var frigates = playerState.GetArray("FleetFrigates");
            if (frigates == null || frigates.Length == 0)
            {
                _countLabel.Text = "No frigates found.";
                return;
            }

            for (int i = 0; i < frigates.Length; i++)
            {
                try
                {
                    var frigate = frigates.GetObject(i);
                    string name = frigate.GetString("Name") ?? frigate.GetString("CustomName") ?? $"Frigate {i + 1}";
                    string type = frigate.GetString("FrigateType") ?? frigate.GetString("Type") ?? "";
                    string cls = "";
                    try
                    {
                        var classObj = frigate.GetObject("Class");
                        cls = classObj?.GetString("FrigateClass") ?? frigate.GetString("Class") ?? "";
                    }
                    catch { cls = frigate.GetString("Class") ?? ""; }

                    string level = "";
                    try { level = frigate.GetInt("Level").ToString(); } catch { }

                    _frigateGrid.Rows.Add(i.ToString(), name, type, cls, level);
                }
                catch { }
            }

            _countLabel.Text = $"Total frigates: {frigates.Length}";
        }
        catch { _countLabel.Text = "Failed to load frigate data."; }
    }

    public void SaveData(JsonObject saveData)
    {
        // Frigates are read-only in this panel
    }
}
