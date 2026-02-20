using System.Windows.Forms;
using System.Drawing;
using System.Collections.Generic;

public class ItemPickerDialog : Form
{
    public string? SelectedId { get; private set; }
    private readonly DataGridView _grid;
    private readonly Button _addButton;
    private readonly TextBox _manualIdBox;
    private readonly Button _addManualButton;

    public ItemPickerDialog(string title, List<(Image? icon, string name, string id, string category)> items)
    {
        Text = title;
        Size = new Size(760, 460);
        StartPosition = FormStartPosition.CenterParent;
        FormBorderStyle = FormBorderStyle.FixedDialog;
        MaximizeBox = false;
        MinimizeBox = false;

        _grid = new DataGridView
        {
            Dock = DockStyle.Fill,
            ReadOnly = true,
            SelectionMode = DataGridViewSelectionMode.FullRowSelect,
            AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill,
            RowHeadersVisible = false,
            AllowUserToAddRows = false,
            AllowUserToDeleteRows = false,
        };

        _grid.Columns.Add(new DataGridViewImageColumn
        {
            Name = "Icon",
            HeaderText = "Icon",
            FillWeight = 16,
            ImageLayout = DataGridViewImageCellLayout.Zoom
        });
        _grid.Columns.Add("Name", "Name");
        _grid.Columns.Add("Category", "Category");
        _grid.Columns.Add("ID", "ID");
        _grid.RowTemplate.Height = 28;

        foreach (var (icon, name, id, category) in items)
        {
            if (icon == null || name == null || id == null || category == null)
                continue;
            _grid.Rows.Add(icon, name, category, id);
        }

        _addButton = new Button { Text = "Add", Dock = DockStyle.Right, DialogResult = DialogResult.OK, Enabled = false };
        _addButton.Click += (s, e) =>
        {
            if (_grid.SelectedRows.Count > 0)
                SelectedId = _grid.SelectedRows[0].Cells["ID"].Value as string;
        };

        _grid.SelectionChanged += (s, e) => _addButton.Enabled = _grid.SelectedRows.Count > 0;

        // Manual entry controls with padding and vertical layout
        _manualIdBox = new TextBox
        {
            Width = 200,
            Margin = new Padding(12, 12, 12, 4),
            PlaceholderText = "Enter ID manually..."
        };
        _addManualButton = new Button
        {
            Text = "Add Manual",
            Width = 120,
            Margin = new Padding(12, 0, 12, 4)
        };
        _addManualButton.Click += (s, e) =>
        {
            var id = _manualIdBox.Text.Trim();
            if (!string.IsNullOrEmpty(id))
            {
                SelectedId = id;
                DialogResult = DialogResult.OK;
                Close();
            }
        };

        var warningLabel = new Label
        {
            Text = "Beware: manual entry will cause issues if incorrect.",
            ForeColor = Color.Red,
            AutoSize = true,
            Margin = new Padding(12, 0, 12, 8),
            TextAlign = ContentAlignment.MiddleLeft
        };

        var manualPanel = new FlowLayoutPanel
        {
            Dock = DockStyle.Bottom,
            FlowDirection = FlowDirection.TopDown,
            AutoSize = true,
            WrapContents = false,
            Padding = new Padding(0, 0, 0, 8)
        };
        manualPanel.Controls.Add(_manualIdBox);
        manualPanel.Controls.Add(_addManualButton);
        manualPanel.Controls.Add(warningLabel);

        var buttonPanel = new Panel { Dock = DockStyle.Bottom, Height = 36 };
        _addButton.Width = 120;
        _addButton.Location = new Point(buttonPanel.Width - _addButton.Width - 8, 4);
        buttonPanel.Controls.Add(_addButton);

        Controls.Add(_grid);
        Controls.Add(manualPanel);
        Controls.Add(buttonPanel);
        AcceptButton = _addButton;
    }
}