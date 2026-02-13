using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class SettlementPanel : UserControl
{
    private readonly ComboBox _settlementSelector;
    private readonly TextBox _settlementName;
    private readonly TextBox _population;
    private readonly TextBox _happiness;
    private readonly TextBox _productivity;
    private readonly Label _infoLabel;
    private JsonArray? _settlements;

    public SettlementPanel()
    {
        SuspendLayout();

        var layout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 2,
            RowCount = 7,
            Padding = new Padding(10)
        };
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize));
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));

        var titleLabel = new Label
        {
            Text = "Settlements",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 5)
        };
        layout.Controls.Add(titleLabel, 0, 0);
        layout.SetColumnSpan(titleLabel, 2);

        _settlementSelector = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _settlementSelector.SelectedIndexChanged += OnSettlementSelected;
        AddRow(layout, "Settlement:", _settlementSelector, 1);

        _settlementName = new TextBox { Dock = DockStyle.Fill };
        AddRow(layout, "Name:", _settlementName, 2);

        _population = new TextBox { Dock = DockStyle.Fill };
        AddRow(layout, "Population:", _population, 3);

        _happiness = new TextBox { Dock = DockStyle.Fill };
        AddRow(layout, "Happiness:", _happiness, 4);

        _productivity = new TextBox { Dock = DockStyle.Fill };
        AddRow(layout, "Productivity:", _productivity, 5);

        _infoLabel = new Label
        {
            Text = "Load a save file to view settlement data.",
            AutoSize = true,
            Padding = new Padding(0, 10, 0, 0)
        };
        layout.Controls.Add(_infoLabel, 0, 6);
        layout.SetColumnSpan(_infoLabel, 2);

        Controls.Add(layout);
        ResumeLayout(false);
        PerformLayout();
    }

    private static void AddRow(TableLayoutPanel layout, string label, Control field, int row)
    {
        var lbl = new Label { Text = label, AutoSize = true, Anchor = AnchorStyles.Left, Padding = new Padding(0, 5, 10, 0) };
        layout.Controls.Add(lbl, 0, row);
        layout.Controls.Add(field, 1, row);
    }

    public void LoadData(JsonObject saveData)
    {
        _settlementSelector.Items.Clear();
        ClearFields();
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            _settlements = playerState.GetArray("SettlementStatesV2");
            if (_settlements == null || _settlements.Length == 0)
            {
                _infoLabel.Text = "No settlements found in this save.";
                return;
            }

            for (int i = 0; i < _settlements.Length; i++)
            {
                try
                {
                    var settlement = _settlements.GetObject(i);
                    string name = settlement.GetString("Name") ?? settlement.GetString("SettlementName") ?? $"Settlement {i + 1}";
                    _settlementSelector.Items.Add(name);
                }
                catch
                {
                    _settlementSelector.Items.Add($"Settlement {i + 1}");
                }
            }

            if (_settlementSelector.Items.Count > 0)
                _settlementSelector.SelectedIndex = 0;

            _infoLabel.Text = $"Found {_settlements.Length} settlement(s).";
        }
        catch { _infoLabel.Text = "Failed to load settlement data."; }
    }

    public void SaveData(JsonObject saveData)
    {
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            var settlements = playerState.GetArray("SettlementStatesV2");
            if (settlements == null || _settlementSelector.SelectedIndex < 0) return;

            int idx = _settlementSelector.SelectedIndex;
            if (idx >= settlements.Length) return;

            var settlement = settlements.GetObject(idx);
            if (!string.IsNullOrEmpty(_settlementName.Text))
            {
                if (settlement.Contains("Name"))
                    settlement.Set("Name", _settlementName.Text);
                else if (settlement.Contains("SettlementName"))
                    settlement.Set("SettlementName", _settlementName.Text);
            }

            if (int.TryParse(_population.Text, out int pop))
            {
                if (settlement.Contains("Population"))
                    settlement.Set("Population", pop);
            }
            if (int.TryParse(_happiness.Text, out int happy))
            {
                if (settlement.Contains("Happiness"))
                    settlement.Set("Happiness", happy);
            }
            if (int.TryParse(_productivity.Text, out int prod))
            {
                if (settlement.Contains("Productivity"))
                    settlement.Set("Productivity", prod);
            }
        }
        catch { }
    }

    private void OnSettlementSelected(object? sender, EventArgs e)
    {
        ClearFields();
        try
        {
            if (_settlements == null || _settlementSelector.SelectedIndex < 0) return;
            int idx = _settlementSelector.SelectedIndex;
            if (idx >= _settlements.Length) return;

            var settlement = _settlements.GetObject(idx);
            _settlementName.Text = settlement.GetString("Name") ?? settlement.GetString("SettlementName") ?? "";

            try { _population.Text = settlement.Get("Population")?.ToString() ?? ""; } catch { }
            try { _happiness.Text = settlement.Get("Happiness")?.ToString() ?? ""; } catch { }
            try { _productivity.Text = settlement.Get("Productivity")?.ToString() ?? ""; } catch { }
        }
        catch { }
    }

    private void ClearFields()
    {
        _settlementName.Text = "";
        _population.Text = "";
        _happiness.Text = "";
        _productivity.Text = "";
    }
}
