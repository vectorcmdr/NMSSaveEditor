using System.ComponentModel;
using NMSSaveEditor.Data;
using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

/// <summary>
/// A reusable inventory grid panel that displays slots as a visual grid of cells
/// with item icons and a side detail panel for viewing and editing slot properties.
/// Mirrors the Java original's grid-based inventory display (bO.java / bS.java).
/// </summary>
public class InventoryGridPanel : UserControl
{
    private const int GridColumns = 10;
    private const int CellSize = 56;
    private const int CellPadding = 2;

    private readonly Panel _gridContainer;
    private readonly Panel _detailPanel;
    private readonly PictureBox _detailIcon;
    private readonly Label _detailItemName;
    private readonly Label _detailItemType;
    private readonly Label _detailItemCategory;
    private readonly TextBox _detailItemId;
    private readonly NumericUpDown _detailAmount;
    private readonly NumericUpDown _detailMaxAmount;
    private readonly Button _applyButton;
    private readonly Label _detailDescription;

    private readonly List<SlotCell> _cells = new();
    private SlotCell? _selectedCell;
    private JsonArray? _slots;
    private GameItemDatabase? _database;
    private IconManager? _iconManager;

    public InventoryGridPanel()
    {
        SuspendLayout();

        var splitContainer = new SplitContainer
        {
            Dock = DockStyle.Fill,
            Orientation = Orientation.Vertical,
            SplitterDistance = 400,
            FixedPanel = FixedPanel.Panel2
        };

        // Left: grid of slot cells
        _gridContainer = new Panel
        {
            Dock = DockStyle.Fill,
            AutoScroll = true,
            BackColor = Color.FromArgb(30, 30, 30)
        };
        splitContainer.Panel1.Controls.Add(_gridContainer);

        // Right: detail panel for selected slot
        _detailPanel = new Panel
        {
            Dock = DockStyle.Fill,
            Padding = new Padding(8),
            AutoScroll = true,
            BackColor = SystemColors.Control
        };

        var detailLayout = new TableLayoutPanel
        {
            Dock = DockStyle.Top,
            ColumnCount = 2,
            AutoSize = true,
            Padding = new Padding(4)
        };
        detailLayout.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize));
        detailLayout.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100));

        int row = 0;

        // Title
        var detailTitle = new Label
        {
            Text = "Slot Details",
            Font = new Font(Font.FontFamily, 11, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 8)
        };
        detailLayout.Controls.Add(detailTitle, 0, row);
        detailLayout.SetColumnSpan(detailTitle, 2);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Icon preview
        _detailIcon = new PictureBox
        {
            Size = new Size(64, 64),
            SizeMode = PictureBoxSizeMode.Zoom,
            BackColor = Color.FromArgb(40, 40, 40),
            BorderStyle = BorderStyle.FixedSingle,
            Padding = new Padding(0, 0, 0, 4)
        };
        detailLayout.Controls.Add(_detailIcon, 0, row);
        detailLayout.SetColumnSpan(_detailIcon, 2);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Item Name (display only, resolved from database)
        _detailItemName = new Label
        {
            Text = "(empty)",
            Font = new Font(Font.FontFamily, 10, FontStyle.Bold),
            AutoSize = true,
            ForeColor = Color.DarkBlue
        };
        detailLayout.Controls.Add(new Label { Text = "Name:", AutoSize = true, Padding = new Padding(0, 4, 6, 0) }, 0, row);
        detailLayout.Controls.Add(_detailItemName, 1, row);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Item Type
        _detailItemType = new Label { Text = "", AutoSize = true };
        detailLayout.Controls.Add(new Label { Text = "Type:", AutoSize = true, Padding = new Padding(0, 4, 6, 0) }, 0, row);
        detailLayout.Controls.Add(_detailItemType, 1, row);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Item Category
        _detailItemCategory = new Label { Text = "", AutoSize = true };
        detailLayout.Controls.Add(new Label { Text = "Category:", AutoSize = true, Padding = new Padding(0, 4, 6, 0) }, 0, row);
        detailLayout.Controls.Add(_detailItemCategory, 1, row);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Item ID (editable)
        _detailItemId = new TextBox { Dock = DockStyle.Fill };
        detailLayout.Controls.Add(new Label { Text = "Item ID:", AutoSize = true, Padding = new Padding(0, 4, 6, 0) }, 0, row);
        detailLayout.Controls.Add(_detailItemId, 1, row);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Amount (editable)
        _detailAmount = new NumericUpDown { Dock = DockStyle.Fill, Minimum = 0, Maximum = int.MaxValue };
        detailLayout.Controls.Add(new Label { Text = "Amount:", AutoSize = true, Padding = new Padding(0, 4, 6, 0) }, 0, row);
        detailLayout.Controls.Add(_detailAmount, 1, row);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Max Amount (editable)
        _detailMaxAmount = new NumericUpDown { Dock = DockStyle.Fill, Minimum = 0, Maximum = int.MaxValue };
        detailLayout.Controls.Add(new Label { Text = "Max:", AutoSize = true, Padding = new Padding(0, 4, 6, 0) }, 0, row);
        detailLayout.Controls.Add(_detailMaxAmount, 1, row);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Apply button
        _applyButton = new Button
        {
            Text = "Apply Changes",
            Dock = DockStyle.Fill,
            Height = 30,
            Enabled = false
        };
        _applyButton.Click += OnApplyChanges;
        detailLayout.Controls.Add(_applyButton, 0, row);
        detailLayout.SetColumnSpan(_applyButton, 2);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Description
        _detailDescription = new Label
        {
            Text = "",
            AutoSize = true,
            MaximumSize = new Size(250, 0),
            ForeColor = Color.Gray,
            Padding = new Padding(0, 8, 0, 0)
        };
        detailLayout.Controls.Add(_detailDescription, 0, row);
        detailLayout.SetColumnSpan(_detailDescription, 2);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));

        _detailPanel.Controls.Add(detailLayout);
        splitContainer.Panel2.Controls.Add(_detailPanel);

        Controls.Add(splitContainer);
        ResumeLayout(false);
        PerformLayout();
    }

    public void SetDatabase(GameItemDatabase? database)
    {
        _database = database;
    }

    public void SetIconManager(IconManager? iconManager)
    {
        _iconManager = iconManager;
    }

    public void LoadInventory(JsonObject? inventory)
    {
        _gridContainer.Controls.Clear();
        _cells.Clear();
        _selectedCell = null;
        ClearDetailPanel();
        _slots = null;

        if (inventory == null) return;
        _slots = inventory.GetArray("Slots");
        if (_slots == null) return;

        // Determine grid dimensions from inventory metadata
        int width = GridColumns;
        int height = (_slots.Length + width - 1) / width;
        try
        {
            var validSlots = inventory.GetArray("ValidSlotIndices");
            if (validSlots != null && validSlots.Length > 0)
            {
                int maxX = 0, maxY = 0;
                for (int i = 0; i < validSlots.Length; i++)
                {
                    var idx = validSlots.GetObject(i);
                    if (idx != null)
                    {
                        try { maxX = Math.Max(maxX, idx.GetInt("X")); } catch { }
                        try { maxY = Math.Max(maxY, idx.GetInt("Y")); } catch { }
                    }
                }
                if (maxX > 0 || maxY > 0)
                {
                    width = maxX + 1;
                    height = maxY + 1;
                }
            }
        }
        catch { }

        // Build slot lookup by X,Y position
        var slotMap = new Dictionary<(int x, int y), (int index, JsonObject slot)>();
        for (int i = 0; i < _slots.Length; i++)
        {
            try
            {
                var slot = _slots.GetObject(i);
                int x = 0, y = 0;
                try
                {
                    var idx = slot.GetObject("Index");
                    if (idx != null)
                    {
                        x = idx.GetInt("X");
                        y = idx.GetInt("Y");
                    }
                }
                catch { x = i % width; y = i / width; }
                slotMap[(x, y)] = (i, slot);
            }
            catch { }
        }

        // Create grid cells
        for (int row = 0; row < height; row++)
        {
            for (int col = 0; col < width; col++)
            {
                var cell = new SlotCell(col, row, CellSize);
                cell.Location = new Point(
                    col * (CellSize + CellPadding) + CellPadding,
                    row * (CellSize + CellPadding) + CellPadding
                );

                if (slotMap.TryGetValue((col, row), out var slotData))
                {
                    cell.SlotIndex = slotData.index;
                    cell.SlotData = slotData.slot;
                    LoadCellData(cell);
                }
                else
                {
                    cell.IsEmpty = true;
                }

                cell.Click += OnCellClicked;
                _gridContainer.Controls.Add(cell);
                _cells.Add(cell);
            }
        }
    }

    private void LoadCellData(SlotCell cell)
    {
        if (cell.SlotData == null) return;

        string itemId = "";
        try
        {
            // NMS uses nested Id.Id for item identification
            var idObj = cell.SlotData.GetObject("Id");
            if (idObj != null)
                itemId = idObj.GetString("Id") ?? "";
            else
                itemId = cell.SlotData.GetString("Id") ?? "";
        }
        catch { }

        cell.ItemId = itemId;

        int amount = 0, maxAmount = 0;
        try { amount = cell.SlotData.GetInt("Amount"); } catch { }
        try { maxAmount = cell.SlotData.GetInt("MaxAmount"); } catch { }
        cell.Amount = amount;
        cell.MaxAmount = maxAmount;

        // Look up display name and type from database
        if (_database != null && !string.IsNullOrEmpty(itemId))
        {
            var gameItem = _database.GetItem(itemId) ?? _database.GetItem("^" + itemId);
            if (gameItem != null)
            {
                cell.DisplayName = gameItem.Name;
                cell.ItemType = gameItem.ItemType;
                cell.Category = gameItem.Category;

                // Load icon image
                if (_iconManager != null && !string.IsNullOrEmpty(gameItem.Icon))
                    cell.IconImage = _iconManager.GetIcon(gameItem.Icon);
            }
        }

        cell.UpdateDisplay();
    }

    private void OnCellClicked(object? sender, EventArgs e)
    {
        if (sender is not SlotCell cell || cell.IsEmpty) return;

        // Deselect previous
        if (_selectedCell != null)
            _selectedCell.IsSelected = false;

        cell.IsSelected = true;
        _selectedCell = cell;

        // Populate detail panel
        _detailItemId.Text = cell.ItemId;
        _detailAmount.Value = Math.Clamp(cell.Amount, (int)_detailAmount.Minimum, (int)_detailAmount.Maximum);
        _detailMaxAmount.Value = Math.Clamp(cell.MaxAmount, (int)_detailMaxAmount.Minimum, (int)_detailMaxAmount.Maximum);
        _applyButton.Enabled = true;

        if (!string.IsNullOrEmpty(cell.DisplayName))
            _detailItemName.Text = cell.DisplayName;
        else if (!string.IsNullOrEmpty(cell.ItemId))
            _detailItemName.Text = cell.ItemId;
        else
            _detailItemName.Text = "(empty slot)";

        _detailItemType.Text = cell.ItemType;
        _detailItemCategory.Text = cell.Category;

        // Show icon in detail panel
        _detailIcon.Image = cell.IconImage;

        // Show description from database
        if (_database != null && !string.IsNullOrEmpty(cell.ItemId))
        {
            var item = _database.GetItem(cell.ItemId) ?? _database.GetItem("^" + cell.ItemId);
            _detailDescription.Text = item?.Description ?? "";
        }
        else
        {
            _detailDescription.Text = "";
        }
    }

    private void OnApplyChanges(object? sender, EventArgs e)
    {
        if (_selectedCell?.SlotData == null || _slots == null) return;

        var slot = _selectedCell.SlotData;

        // Update Item ID
        string newItemId = _detailItemId.Text.Trim();
        try
        {
            var idObj = slot.GetObject("Id");
            if (idObj != null)
                idObj.Set("Id", newItemId);
            else
                slot.Set("Id", newItemId);
        }
        catch { }

        // Update Amount / MaxAmount
        slot.Set("Amount", (int)_detailAmount.Value);
        slot.Set("MaxAmount", (int)_detailMaxAmount.Value);

        // Refresh cell display
        _selectedCell.ItemId = newItemId;
        _selectedCell.Amount = (int)_detailAmount.Value;
        _selectedCell.MaxAmount = (int)_detailMaxAmount.Value;

        if (_database != null && !string.IsNullOrEmpty(newItemId))
        {
            var gameItem = _database.GetItem(newItemId) ?? _database.GetItem("^" + newItemId);
            if (gameItem != null)
            {
                _selectedCell.DisplayName = gameItem.Name;
                _selectedCell.ItemType = gameItem.ItemType;
                _selectedCell.Category = gameItem.Category;
                _detailItemName.Text = gameItem.Name;
                _detailItemType.Text = gameItem.ItemType;
                _detailItemCategory.Text = gameItem.Category;
                _detailDescription.Text = gameItem.Description;

                // Update icon
                if (_iconManager != null && !string.IsNullOrEmpty(gameItem.Icon))
                {
                    _selectedCell.IconImage = _iconManager.GetIcon(gameItem.Icon);
                    _detailIcon.Image = _selectedCell.IconImage;
                }
            }
        }

        _selectedCell.UpdateDisplay();
    }

    private void ClearDetailPanel()
    {
        _detailItemName.Text = "(no slot selected)";
        _detailItemType.Text = "";
        _detailItemCategory.Text = "";
        _detailItemId.Text = "";
        _detailAmount.Value = 0;
        _detailMaxAmount.Value = 0;
        _detailDescription.Text = "";
        _detailIcon.Image = null;
        _applyButton.Enabled = false;
    }

    /// <summary>
    /// Saves edited slot data back to the JSON inventory.
    /// Changes are applied immediately via OnApplyChanges, so this is a no-op.
    /// </summary>
    public void SaveInventory(JsonObject? inventory)
    {
        // Changes are written directly to slot JsonObjects via OnApplyChanges
    }

    /// <summary>
    /// Individual slot cell in the inventory grid. Renders with an icon image
    /// (if available) and an amount label overlay. Color indicates item type:
    /// Technology = blue, Product = orange, Substance = teal, empty = dark gray.
    /// </summary>
    private class SlotCell : Panel
    {
        private readonly PictureBox _iconBox;
        private readonly Label _nameLabel;
        private readonly Label _amountLabel;
        private readonly ToolTip _toolTip;

        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public int GridX { get; }
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public int GridY { get; }
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public int SlotIndex { get; set; } = -1;
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public JsonObject? SlotData { get; set; }
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public string ItemId { get; set; } = "";
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public string DisplayName { get; set; } = "";
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public string ItemType { get; set; } = "";
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public string Category { get; set; } = "";
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public int Amount { get; set; }
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public int MaxAmount { get; set; }
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public bool IsEmpty { get; set; }
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public Image? IconImage { get; set; }

        private bool _isSelected;
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public bool IsSelected
        {
            get => _isSelected;
            set { _isSelected = value; UpdateDisplay(); }
        }

        public SlotCell(int x, int y, int size)
        {
            GridX = x;
            GridY = y;
            Size = new Size(size, size);
            BorderStyle = BorderStyle.FixedSingle;
            Cursor = Cursors.Hand;
            BackColor = Color.FromArgb(50, 50, 50);

            _nameLabel = new Label
            {
                Dock = DockStyle.Top,
                ForeColor = Color.White,
                BackColor = Color.FromArgb(160, 0, 0, 0),
                Font = new Font("Segoe UI", 5.5f, FontStyle.Regular),
                TextAlign = ContentAlignment.MiddleCenter,
                Height = 13,
                AutoEllipsis = true
            };

            _iconBox = new PictureBox
            {
                Dock = DockStyle.Fill,
                SizeMode = PictureBoxSizeMode.Zoom,
                BackColor = Color.Transparent
            };

            _amountLabel = new Label
            {
                Dock = DockStyle.Bottom,
                ForeColor = Color.White,
                BackColor = Color.FromArgb(128, 0, 0, 0),
                Font = new Font("Segoe UI", 6.5f, FontStyle.Bold),
                TextAlign = ContentAlignment.MiddleCenter,
                Height = 14,
                AutoEllipsis = true
            };

            _toolTip = new ToolTip();

            // Add in z-order: amount (bottom), icon (fill), name (top)
            Controls.Add(_amountLabel);
            Controls.Add(_iconBox);
            Controls.Add(_nameLabel);

            // Forward child clicks to this panel
            _iconBox.Click += (s, e) => OnClick(e);
            _amountLabel.Click += (s, e) => OnClick(e);
            _nameLabel.Click += (s, e) => OnClick(e);
        }

        public void UpdateDisplay()
        {
            if (IsEmpty)
            {
                BackColor = Color.FromArgb(25, 25, 25);
                _iconBox.Image = null;
                _nameLabel.Text = "";
                _nameLabel.Visible = false;
                _amountLabel.Text = "";
                _amountLabel.Visible = false;
                Cursor = Cursors.Default;
                _toolTip.SetToolTip(this, "");
                _toolTip.SetToolTip(_iconBox, "");
                return;
            }

            // Set background color by item type
            if (_isSelected)
                BackColor = Color.FromArgb(80, 120, 200);
            else if (ItemType.Contains("technology", StringComparison.OrdinalIgnoreCase))
                BackColor = Color.FromArgb(40, 60, 120);
            else if (ItemType.Contains("product", StringComparison.OrdinalIgnoreCase))
                BackColor = Color.FromArgb(120, 80, 30);
            else if (ItemType.Contains("substance", StringComparison.OrdinalIgnoreCase))
                BackColor = Color.FromArgb(30, 100, 100);
            else if (!string.IsNullOrEmpty(ItemId))
                BackColor = Color.FromArgb(60, 60, 60);
            else
                BackColor = Color.FromArgb(50, 50, 50);

            // Display icon
            _iconBox.Image = IconImage;

            // Display item name at top
            string nameText = !string.IsNullOrEmpty(DisplayName) ? DisplayName : ItemId;
            if (!string.IsNullOrEmpty(nameText))
            {
                _nameLabel.Text = nameText;
                _nameLabel.Visible = true;
            }
            else
            {
                _nameLabel.Text = "";
                _nameLabel.Visible = false;
            }

            // Display amount overlay at bottom
            if (Amount > 0 || MaxAmount > 0)
            {
                _amountLabel.Text = Amount.ToString();
                _amountLabel.Visible = true;
            }
            else
            {
                _amountLabel.Text = "";
                _amountLabel.Visible = false;
            }

            // Tooltip with full item info
            string tip = !string.IsNullOrEmpty(DisplayName) ? DisplayName : ItemId;
            if (Amount > 0)
                tip += $" ({Amount}/{MaxAmount})";
            _toolTip.SetToolTip(this, tip);
            _toolTip.SetToolTip(_iconBox, tip);
            _toolTip.SetToolTip(_amountLabel, tip);
            _toolTip.SetToolTip(_nameLabel, tip);
        }
    }
}
