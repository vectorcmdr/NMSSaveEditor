using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class MainStatsPanel : UserControl
{
    private readonly NumericUpDown _healthField;
    private readonly NumericUpDown _shieldField;
    private readonly NumericUpDown _energyField;
    private readonly NumericUpDown _unitsField;
    private readonly NumericUpDown _nanitesField;
    private readonly NumericUpDown _quicksilverField;

    public MainStatsPanel()
    {
        SuspendLayout();
        _healthField = new NumericUpDown { Maximum = 999999, Width = 150 };
        _shieldField = new NumericUpDown { Maximum = 999999, Width = 150 };
        _energyField = new NumericUpDown { Maximum = 999999, Width = 150 };
        _unitsField = new NumericUpDown { Maximum = int.MaxValue, Width = 150 };
        _nanitesField = new NumericUpDown { Maximum = int.MaxValue, Width = 150 };
        _quicksilverField = new NumericUpDown { Maximum = int.MaxValue, Width = 150 };

        InitializeLayout();
        ResumeLayout(false);
        PerformLayout();
    }

    private void InitializeLayout()
    {
        var layout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 2,
            RowCount = 7,
            Padding = new Padding(20),
            AutoScroll = true
        };
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize));
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100));

        var titleLabel = new Label
        {
            Text = "Player Statistics",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 10)
        };
        layout.Controls.Add(titleLabel, 0, 0);
        layout.SetColumnSpan(titleLabel, 2);

        AddRow(layout, "Health:", _healthField, 1);
        AddRow(layout, "Shield:", _shieldField, 2);
        AddRow(layout, "Energy:", _energyField, 3);
        AddRow(layout, "Units:", _unitsField, 4);
        AddRow(layout, "Nanites:", _nanitesField, 5);
        AddRow(layout, "Quicksilver:", _quicksilverField, 6);

        Controls.Add(layout);
    }

    private static void AddRow(TableLayoutPanel layout, string label, Control field, int row)
    {
        var lbl = new Label { Text = label, AutoSize = true, Anchor = AnchorStyles.Left, Padding = new Padding(0, 5, 10, 0) };
        layout.Controls.Add(lbl, 0, row);
        layout.Controls.Add(field, 1, row);
    }

    public void LoadData(JsonObject saveData)
    {
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            SetNumericValue(_healthField, playerState, "Health");
            SetNumericValue(_shieldField, playerState, "Shield");
            SetNumericValue(_energyField, playerState, "Energy");
            SetNumericValue(_unitsField, playerState, "Units");
            SetNumericValue(_nanitesField, playerState, "Nanites");
            SetNumericValue(_quicksilverField, playerState, "Specials");
        }
        catch { /* Ignore missing fields */ }
    }

    public void SaveData(JsonObject saveData)
    {
        var playerState = saveData.GetObject("PlayerStateData");
        if (playerState == null) return;

        playerState.Set("Health", (int)_healthField.Value);
        playerState.Set("Shield", (int)_shieldField.Value);
        playerState.Set("Energy", (int)_energyField.Value);
        playerState.Set("Units", (int)_unitsField.Value);
        playerState.Set("Nanites", (int)_nanitesField.Value);
        playerState.Set("Specials", (int)_quicksilverField.Value);
    }

    private static void SetNumericValue(NumericUpDown field, JsonObject data, string key)
    {
        if (data.Contains(key))
        {
            try
            {
                var value = data.GetValue(key);
                long numericValue = Convert.ToInt64(value);
                // Clamp to field's Maximum (which is int.MaxValue for units/nanites)
                field.Value = Math.Min(numericValue, (long)field.Maximum);
            }
            catch { }
        }
    }
}
