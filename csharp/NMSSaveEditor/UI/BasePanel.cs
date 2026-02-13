using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class BasePanel : UserControl
{
    private readonly DataGridView _baseGrid;
    private readonly Label _countLabel;

    public BasePanel()
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
            Text = "Bases",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 5)
        };
        layout.Controls.Add(titleLabel, 0, 0);

        _countLabel = new Label { Text = "No bases loaded.", AutoSize = true };
        layout.Controls.Add(_countLabel, 0, 1);

        _baseGrid = new DataGridView
        {
            Dock = DockStyle.Fill,
            AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill,
            AllowUserToAddRows = false,
            AllowUserToDeleteRows = false,
            ReadOnly = true,
            SelectionMode = DataGridViewSelectionMode.FullRowSelect,
            RowHeadersVisible = false
        };
        _baseGrid.Columns.Add("Index", "#");
        _baseGrid.Columns.Add("Name", "Name");
        _baseGrid.Columns.Add("Planet", "Planet");
        _baseGrid.Columns.Add("Galaxy", "Galaxy");
        _baseGrid.Columns["Index"]!.Width = 40;
        _baseGrid.Columns["Index"]!.AutoSizeMode = DataGridViewAutoSizeColumnMode.None;
        layout.Controls.Add(_baseGrid, 0, 2);

        Controls.Add(layout);
        ResumeLayout(false);
        PerformLayout();
    }

    public void LoadData(JsonObject saveData)
    {
        _baseGrid.Rows.Clear();
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            var bases = playerState.GetArray("PersistentPlayerBases");
            if (bases == null || bases.Length == 0)
            {
                _countLabel.Text = "No bases found.";
                return;
            }

            for (int i = 0; i < bases.Length; i++)
            {
                try
                {
                    var baseObj = bases.GetObject(i);
                    string name = baseObj.GetString("Name") ?? $"Base {i + 1}";
                    string planet = "";
                    string galaxy = "";
                    try
                    {
                        var galacticAddr = baseObj.GetObject("GalacticAddress");
                        if (galacticAddr != null)
                        {
                            planet = galacticAddr.GetString("PlanetIndex") ?? galacticAddr.Get("PlanetIndex")?.ToString() ?? "";
                            galaxy = galacticAddr.GetString("RealityIndex") ?? galacticAddr.Get("RealityIndex")?.ToString() ?? "";
                        }
                    }
                    catch { }

                    if (string.IsNullOrEmpty(planet))
                    {
                        try { planet = baseObj.GetString("BaseType") ?? ""; } catch { }
                    }

                    _baseGrid.Rows.Add(i.ToString(), name, planet, galaxy);
                }
                catch { }
            }

            _countLabel.Text = $"Total bases: {bases.Length}";
        }
        catch { _countLabel.Text = "Failed to load base data."; }
    }

    public void SaveData(JsonObject saveData)
    {
        // Bases are read-only in this panel
    }
}
