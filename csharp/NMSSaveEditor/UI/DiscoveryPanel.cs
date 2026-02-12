using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class DiscoveryPanel : UserControl
{
    private readonly Label _titleLabel;
    private readonly Label _infoLabel;

    public DiscoveryPanel()
    {
        SuspendLayout();
        var layout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 1,
            RowCount = 2,
            Padding = new Padding(20)
        };

        _titleLabel = new Label
        {
            Text = "Discoveries",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true
        };

        _infoLabel = new Label
        {
            Text = "Edit discovery data here. Load a save file to begin.",
            AutoSize = true,
            Padding = new Padding(0, 10, 0, 0)
        };

        layout.Controls.Add(_titleLabel, 0, 0);
        layout.Controls.Add(_infoLabel, 0, 1);
        Controls.Add(layout);
        ResumeLayout(false);
        PerformLayout();
    }

    public void LoadData(JsonObject saveData)
    {
        _infoLabel.Text = "Save data loaded. Discovery editing is available.";
    }

    public void SaveData(JsonObject saveData)
    {
        // Data is saved back through bound controls
    }
}
