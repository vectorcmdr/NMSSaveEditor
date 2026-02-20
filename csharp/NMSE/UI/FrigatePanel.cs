using NMSE.Data;
using NMSE.Models;

namespace NMSE.UI;

public class FrigatePanel : UserControl
{
    private readonly DataGridView _frigateGrid;
    private readonly Label _countLabel;
    private GameItemDatabase? _database;

    // Frigate type names
    private static readonly string[] FrigateTypes =
    {
        "Combat", "Exploration", "Mining", "Diplomacy", "Support",
        "Normandy", "DeepSpace", "DeepSpaceCommon", "Pirate", "GhostShip"
    };

    // Frigate grade names
    private static readonly string[] FrigateGrades = { "C", "B", "A", "S" };

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
            SelectionMode = DataGridViewSelectionMode.FullRowSelect,
            RowHeadersVisible = false
        };
        _frigateGrid.Columns.Add("Index", "#");
        _frigateGrid.Columns.Add("Name", "Name");

        var typeCol = new DataGridViewComboBoxColumn
        {
            Name = "Type",
            HeaderText = "Type",
            FlatStyle = FlatStyle.Flat,
            DisplayStyle = DataGridViewComboBoxDisplayStyle.DropDownButton,
        };
        typeCol.Items.AddRange(FrigateTypes);
        _frigateGrid.Columns.Add(typeCol);

        var classCol = new DataGridViewComboBoxColumn
        {
            Name = "Class",
            HeaderText = "Class",
            FlatStyle = FlatStyle.Flat,
            DisplayStyle = DataGridViewComboBoxDisplayStyle.DropDownButton,
        };
        classCol.Items.AddRange(FrigateGrades);
        _frigateGrid.Columns.Add(classCol);

        _frigateGrid.Columns.Add("Level", "Level");

        _frigateGrid.Columns["Index"]!.Width = 40;
        _frigateGrid.Columns["Index"]!.AutoSizeMode = DataGridViewAutoSizeColumnMode.None;
        _frigateGrid.Columns["Index"]!.ReadOnly = true;
        _frigateGrid.Columns["Name"]!.ReadOnly = false;
        _frigateGrid.Columns["Level"]!.ReadOnly = true;
        layout.Controls.Add(_frigateGrid, 0, 2);

        Controls.Add(layout);
        ResumeLayout(false);
        PerformLayout();
    }

    public void SetDatabase(GameItemDatabase? database) => _database = database;

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

                    // Name: CustomName field
                    string name = frigate.GetString("CustomName") ?? $"Frigate {i + 1}";

                    // Type: FrigateClass.FrigateClass (string like "Combat", "Exploration", etc.)
                    string type = "";
                    try
                    {
                        type = frigate.GetString("FrigateClass.FrigateClass")
                            ?? frigate.GetObject("FrigateClass")?.GetString("FrigateClass")
                            ?? "";
                    }
                    catch { }

                    // Level/Grade: calculated from TraitIDs beneficial count
                    int gradeIndex = 0;
                    string cls = "";
                    try
                    {
                        var traits = frigate.GetArray("TraitIDs");
                        if (traits != null)
                        {
                            int beneficialCount = CountBeneficialTraits(traits);
                            gradeIndex = Math.Clamp(beneficialCount - 2, 0, 3);
                        }
                        cls = FrigateGrades[gradeIndex];
                    }
                    catch { cls = "C"; }

                    // Level: NumberOfTraits or TraitIDs length
                    string level = "";
                    try
                    {
                        var traits = frigate.GetArray("TraitIDs");
                        level = traits != null ? traits.Length.ToString() : "0";
                    }
                    catch { }

                    // Ensure type is a valid ComboBox item
                    if (!Array.Exists(FrigateTypes, t => t.Equals(type, StringComparison.OrdinalIgnoreCase)))
                        type = FrigateTypes.Length > 0 ? FrigateTypes[0] : "";
                    if (!Array.Exists(FrigateGrades, g => g.Equals(cls, StringComparison.OrdinalIgnoreCase)))
                        cls = FrigateGrades[0];

                    _frigateGrid.Rows.Add(i.ToString(), name, type, cls, level);
                }
                catch { }
            }

            _countLabel.Text = $"Total frigates: {frigates.Length}";
        }
        catch { _countLabel.Text = "Failed to load frigate data."; }
    }

    private int CountBeneficialTraits(JsonArray traitIds)
    {
        int count = 0;
        // Load frigate traits from frigates.xml via database
        for (int i = 0; i < traitIds.Length; i++)
        {
            try
            {
                string traitId = traitIds.GetString(i);
                // If the trait is in the database and is marked beneficial, count it
                // For now, use a heuristic: traits without "NEG" or "BAD" in the ID are beneficial
                if (!string.IsNullOrEmpty(traitId) && !traitId.Contains("NEG", StringComparison.OrdinalIgnoreCase)
                    && !traitId.Contains("BAD", StringComparison.OrdinalIgnoreCase))
                    count++;
            }
            catch { }
        }
        return count;
    }

    public void SaveData(JsonObject saveData)
    {
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            var frigates = playerState.GetArray("FleetFrigates");
            if (frigates == null) return;

            for (int i = 0; i < _frigateGrid.Rows.Count && i < frigates.Length; i++)
            {
                var row = _frigateGrid.Rows[i];
                var frigate = frigates.GetObject(i);

                // Save name
                string name = row.Cells["Name"].Value?.ToString() ?? "";
                frigate.Set("CustomName", name);

                // Save type (FrigateClass.FrigateClass)
                string type = row.Cells["Type"].Value?.ToString() ?? "";
                var frigateClassObj = frigate.GetObject("FrigateClass");
                if (frigateClassObj != null)
                    frigateClassObj.Set("FrigateClass", type);
            }
        }
        catch { }
    }
}
