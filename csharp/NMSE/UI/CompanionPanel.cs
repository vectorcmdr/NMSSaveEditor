using NMSE.Models;

namespace NMSE.UI;

public class CompanionPanel : UserControl
{
    private readonly DataGridView _companionGrid;
    private readonly Label _countLabel;

    public CompanionPanel()
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
            Text = "Companions",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 5)
        };
        layout.Controls.Add(titleLabel, 0, 0);

        _countLabel = new Label { Text = "No companions loaded.", AutoSize = true };
        layout.Controls.Add(_countLabel, 0, 1);

        _companionGrid = new DataGridView
        {
            Dock = DockStyle.Fill,
            AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill,
            AllowUserToAddRows = false,
            AllowUserToDeleteRows = false,
            ReadOnly = true,
            SelectionMode = DataGridViewSelectionMode.FullRowSelect,
            RowHeadersVisible = false
        };
        _companionGrid.Columns.Add("Index", "#");
        _companionGrid.Columns.Add("Name", "Name");
        _companionGrid.Columns.Add("Species", "Species");
        _companionGrid.Columns.Add("Trust", "Trust");
        _companionGrid.Columns.Add("Helpfulness", "Helpfulness");
        _companionGrid.Columns["Index"]!.Width = 40;
        _companionGrid.Columns["Index"]!.AutoSizeMode = DataGridViewAutoSizeColumnMode.None;
        layout.Controls.Add(_companionGrid, 0, 2);

        Controls.Add(layout);
        ResumeLayout(false);
        PerformLayout();
    }

    public void LoadData(JsonObject saveData)
    {
        _companionGrid.Rows.Clear();
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            var companions = playerState.GetArray("Companions") ?? playerState.GetArray("Pets");
            if (companions == null || companions.Length == 0)
            {
                _countLabel.Text = "No companions found.";
                return;
            }

            for (int i = 0; i < companions.Length; i++)
            {
                try
                {
                    var comp = companions.GetObject(i);
                    string name = comp.GetString("CustomName") ?? comp.GetString("Name") ?? $"Companion {i + 1}";
                    string species = comp.GetString("CreatureID") ?? comp.GetString("Species") ?? "";
                    string trust = "";
                    string helpfulness = "";
                    try { trust = comp.GetInt("Trust").ToString(); } catch { }
                    try { helpfulness = comp.GetInt("Helpfulness").ToString(); } catch { }
                    _companionGrid.Rows.Add(i.ToString(), name, species, trust, helpfulness);
                }
                catch { }
            }

            _countLabel.Text = $"Total companions: {companions.Length}";
        }
        catch { _countLabel.Text = "Failed to load companion data."; }
    }

    public void SaveData(JsonObject saveData)
    {
        // Companions are read-only in this panel
    }
}
