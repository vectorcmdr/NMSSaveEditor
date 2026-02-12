using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class RawJsonPanel : UserControl
{
    private readonly TextBox _jsonTextBox;
    private readonly Label _titleLabel;
    private readonly Button _formatButton;
    private readonly Button _validateButton;
    private readonly Label _statusLabel;

    public RawJsonPanel()
    {
        var layout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 3,
            RowCount = 3,
            Padding = new Padding(10)
        };
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize));
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize));
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));

        _titleLabel = new Label
        {
            Text = "Raw JSON Editor",
            Font = new Font(Font.FontFamily, 14, FontStyle.Bold),
            AutoSize = true
        };

        _formatButton = new Button { Text = "Format", Width = 80 };
        _formatButton.Click += OnFormat;

        _validateButton = new Button { Text = "Validate", Width = 80 };
        _validateButton.Click += OnValidate;

        _statusLabel = new Label { Text = "", AutoSize = true, ForeColor = Color.Gray };

        _jsonTextBox = new TextBox
        {
            Multiline = true,
            ScrollBars = ScrollBars.Both,
            Dock = DockStyle.Fill,
            Font = new Font("Consolas", 10),
            WordWrap = false,
            MaxLength = int.MaxValue
        };

        layout.Controls.Add(_titleLabel, 0, 0);
        layout.SetColumnSpan(_titleLabel, 3);
        layout.Controls.Add(_formatButton, 0, 1);
        layout.Controls.Add(_validateButton, 1, 1);
        layout.Controls.Add(_statusLabel, 2, 1);
        layout.Controls.Add(_jsonTextBox, 0, 2);
        layout.SetColumnSpan(_jsonTextBox, 3);

        Controls.Add(layout);
    }

    public void LoadData(JsonObject saveData)
    {
        _jsonTextBox.Text = saveData.ToFormattedString();
        _statusLabel.Text = $"Loaded {_jsonTextBox.Text.Length:N0} characters";
        _statusLabel.ForeColor = Color.Gray;
    }

    public void SaveData(JsonObject saveData)
    {
        // If user edited JSON, try to parse and replace the save data
        // The actual replacement happens via the main form
    }

    public JsonObject? GetEditedData()
    {
        try
        {
            return JsonObject.Parse(_jsonTextBox.Text);
        }
        catch (JsonException ex)
        {
            MessageBox.Show($"Invalid JSON: {ex.Message}", "Validation Error",
                MessageBoxButtons.OK, MessageBoxIcon.Warning);
            return null;
        }
    }

    private void OnFormat(object? sender, EventArgs e)
    {
        try
        {
            var obj = JsonObject.Parse(_jsonTextBox.Text);
            _jsonTextBox.Text = obj.ToFormattedString();
            _statusLabel.Text = "Formatted successfully";
            _statusLabel.ForeColor = Color.Green;
        }
        catch (JsonException ex)
        {
            _statusLabel.Text = $"Error: {ex.Message}";
            _statusLabel.ForeColor = Color.Red;
        }
    }

    private void OnValidate(object? sender, EventArgs e)
    {
        try
        {
            JsonObject.Parse(_jsonTextBox.Text);
            _statusLabel.Text = "JSON is valid";
            _statusLabel.ForeColor = Color.Green;
        }
        catch (JsonException ex)
        {
            _statusLabel.Text = $"Invalid: {ex.Message}";
            _statusLabel.ForeColor = Color.Red;
        }
    }
}
