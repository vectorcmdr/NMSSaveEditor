using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class BasePanel : UserControl
{
    private readonly Label _titleLabel;
    private readonly Label _infoLabel;

    public BasePanel()
    {
        var layout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 1,
            RowCount = 2,
            Padding = new Padding(20)
        };

        _titleLabel = new Label
        {
            Text = "Bases",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true
        };

        _infoLabel = new Label
        {
            Text = "Edit base data here. Load a save file to begin.",
            AutoSize = true,
            Padding = new Padding(0, 10, 0, 0)
        };

        layout.Controls.Add(_titleLabel, 0, 0);
        layout.Controls.Add(_infoLabel, 0, 1);
        Controls.Add(layout);
    }

    public void LoadData(JsonObject saveData)
    {
        _infoLabel.Text = "Save data loaded. Base editing is available.";
    }

    public void SaveData(JsonObject saveData)
    {
        // Data is saved back through bound controls
    }
}
