using NMSE.Models;

namespace NMSE.UI;

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
        _healthField = new NumericUpDown { Maximum = 999999, Width = 150, Anchor = AnchorStyles.Left | AnchorStyles.Top };
        _shieldField = new NumericUpDown { Maximum = 999999, Width = 150, Anchor = AnchorStyles.Left | AnchorStyles.Top };
        _energyField = new NumericUpDown { Maximum = 999999, Width = 150, Anchor = AnchorStyles.Left | AnchorStyles.Top };
        _unitsField = new NumericUpDown { Maximum = uint.MaxValue, Width = 150, Anchor = AnchorStyles.Left | AnchorStyles.Top };
        _nanitesField = new NumericUpDown { Maximum = uint.MaxValue, Width = 150, Anchor = AnchorStyles.Left | AnchorStyles.Top };
        _quicksilverField = new NumericUpDown { Maximum = uint.MaxValue, Width = 150, Anchor = AnchorStyles.Left | AnchorStyles.Top };

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
        // Explicit row styles for consistent spacing
        for (int i = 0; i < 7; i++)
            layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));

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
        var lbl = new Label { Text = label, AutoSize = true, Anchor = AnchorStyles.Left | AnchorStyles.Top, Padding = new Padding(0, 5, 10, 0) };
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
        // Units/Nanites/Specials: store as signed int for NMS save format compatibility
        playerState.Set("Units", unchecked((int)(uint)_unitsField.Value));
        playerState.Set("Nanites", unchecked((int)(uint)_nanitesField.Value));
        playerState.Set("Specials", unchecked((int)(uint)_quicksilverField.Value));
    }

    private static void SetNumericValue(NumericUpDown field, JsonObject data, string key)
    {
        try
        {
            // Try direct Get first, then GetValue (which supports path resolution)
            var value = data.Get(key) ?? data.GetValue(key);
            if (value == null) return;

            // Handle JsonObject wrapping (some saves nest values)
            if (value is JsonObject jobj)
            {
                value = jobj.Get("Value") ?? jobj.Get("value");
                if (value == null) return;
            }

            // Convert the value, treating negative ints as unsigned 32-bit (Java: & 0xFFFFFFFFL)
            decimal numericValue;
            if (value is int i)
                numericValue = (decimal)(uint)i;
            else if (value is long l)
                numericValue = (decimal)(l & 0xFFFFFFFFL);
            else if (value is decimal d)
            {
                // The parser stores all numbers as decimal internally before narrowing
                // If the decimal represents a negative int (from signed NMS save), convert
                if (d < 0 && d >= int.MinValue)
                    numericValue = (decimal)(uint)(int)d;
                else
                    numericValue = d;
            }
            else
                numericValue = Convert.ToDecimal(value);

            if (numericValue < field.Minimum) numericValue = field.Minimum;
            if (numericValue > field.Maximum) numericValue = field.Maximum;
            field.Value = numericValue;
        }
        catch (Exception ex)
        {
            System.Diagnostics.Debug.WriteLine($"SetNumericValue({key}): {ex.Message}");
        }
    }
}
