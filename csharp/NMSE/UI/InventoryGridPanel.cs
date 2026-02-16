using System.ComponentModel;
using System.Text.RegularExpressions;
using NMSE.Data;
using NMSE.Models;

namespace NMSE.UI;

/// <summary>
/// A reusable inventory grid panel that displays slots as a visual grid of cells
/// with item icons and a side detail panel for viewing and editing slot properties.
/// Supports right-click context menu for adding/removing items, and a cascading
/// item picker (Type → Category → Item) for selecting items from the game database.
/// Mirrors the Java original's grid-based inventory display (bO.java / bS.java).
/// </summary>
public class InventoryGridPanel : UserControl
{
    private const int GridColumns = 10;
    // Doubled cell size/padding to increase grid icon & UI scale by 2x
    // Split width/height so we can make cells taller than they are wide.
    private const int CellWidth = 112;
    private const int CellHeight = 156;
    private const int CellPadding = 4;
    private const string SuperchargeIndicator = "⚡ ";

    // Grid area
    private readonly Panel _gridContainer;

    // Detail/editor panel controls
    private readonly Panel _detailPanel;
    private readonly PictureBox _detailIcon;
    private readonly Label _detailItemName;
    private readonly Label _detailSlotPosition;
    private readonly Label _detailItemType;
    private readonly Label _detailItemCategory;
    private readonly TextBox _detailItemId;
    private readonly NumericUpDown _detailAmount;
    private readonly NumericUpDown _detailMaxAmount;
    private readonly NumericUpDown _detailDamageFactor;
    private readonly Button _applyButton;
    private readonly Label _detailDescription;

    // Item picker controls
    private readonly ComboBox _typeFilter;
    private readonly ComboBox _categoryFilter;
    private readonly ComboBox _itemPicker;
    private readonly TextBox _searchBox;
    private readonly Button _searchButton;

    // Resize controls
    private readonly NumericUpDown _resizeWidth;
    private readonly NumericUpDown _resizeHeight;
    private readonly Button _resizeButton;

    // Context menu
    private readonly ContextMenuStrip _cellContextMenu;
    private readonly ToolStripMenuItem _addItemMenuItem;
    private readonly ToolStripMenuItem _removeItemMenuItem;
    private readonly ToolStripMenuItem _enableSlotMenuItem;
    private readonly ToolStripMenuItem _enableAllSlotsMenuItem;
    private readonly ToolStripMenuItem _repairSlotMenuItem;
    private readonly ToolStripMenuItem _repairAllSlotsMenuItem;
    private readonly ToolStripMenuItem _superchargeSlotMenuItem;
    private readonly ToolStripMenuItem _superchargeAllSlotsMenuItem;
    private readonly ToolStripMenuItem _fillStackMenuItem;

    // State
    private readonly List<SlotCell> _cells = new();
    private SlotCell? _selectedCell;
    private SlotCell? _contextCell; // Cell that was right-clicked
    private JsonArray? _slots;
    private JsonObject? _currentInventory;
    private GameItemDatabase? _database;
    private IconManager? _iconManager;

    // Cached item lists for filtering
    private List<GameItem> _allItems = new();
    private bool _suppressFilterEvents;

    public InventoryGridPanel()
    {
        SuspendLayout();

        var splitContainer = new SplitContainer
        {
            Dock = DockStyle.Fill,
            Orientation = Orientation.Vertical,
            FixedPanel = FixedPanel.Panel2,
            SplitterDistance = 280
        };

        // Set SplitterDistance after the control is sized so Panel2 gets a proper initial width
        splitContainer.SizeChanged += (s, e) =>
        {
            if (splitContainer.Width > 300 && splitContainer.SplitterDistance > splitContainer.Width - 290)
                splitContainer.SplitterDistance = splitContainer.Width - 290;
        };

        // Left: resize controls above the grid
        var resizePanel = new Panel
        {
            Dock = DockStyle.Top,
            Height = 32,
            Padding = new Padding(4, 4, 4, 0)
        };
        var resizeWidthLabel = new Label { Text = "Width:", AutoSize = true, Dock = DockStyle.Left, Padding = new Padding(0, 4, 2, 0) };
        _resizeWidth = new NumericUpDown { Minimum = 1, Maximum = 20, Value = 10, Width = 50, Dock = DockStyle.Left };
        var resizeHeightLabel = new Label { Text = "Height:", AutoSize = true, Dock = DockStyle.Left, Padding = new Padding(8, 4, 2, 0) };
        _resizeHeight = new NumericUpDown { Minimum = 1, Maximum = 20, Value = 6, Width = 50, Dock = DockStyle.Left };
        _resizeButton = new Button { Text = "Resize", Width = 60, Dock = DockStyle.Left };
        _resizeButton.Click += OnResizeInventory;
        resizePanel.Controls.Add(_resizeButton);
        resizePanel.Controls.Add(resizeHeightLabel);
        resizePanel.Controls.Add(_resizeHeight);
        resizePanel.Controls.Add(resizeWidthLabel);
        resizePanel.Controls.Add(_resizeWidth);
        // Note: Dock=Left controls are added in reverse visual order

        // Left: grid of slot cells
        _gridContainer = new Panel
        {
            Dock = DockStyle.Fill,
            AutoScroll = true,
            BackColor = Color.FromArgb(30, 30, 30)
        };
        splitContainer.Panel1.Controls.Add(_gridContainer);
        splitContainer.Panel1.Controls.Add(resizePanel);

        // Right: detail/editor panel
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

        // === SLOT DETAILS SECTION ===
        var detailTitle = new Label
        {
            Text = "Slot Details",
            Font = new Font(Font.FontFamily, 11, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 4)
        };
        detailLayout.Controls.Add(detailTitle, 0, row);
        detailLayout.SetColumnSpan(detailTitle, 2);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Icon preview (scaled up 2x)
        _detailIcon = new PictureBox
        {
            Size = new Size(128, 128),
            SizeMode = PictureBoxSizeMode.Zoom,
            BackColor = Color.FromArgb(40, 40, 40),
            BorderStyle = BorderStyle.FixedSingle
        };
        detailLayout.Controls.Add(_detailIcon, 0, row);
        detailLayout.SetColumnSpan(_detailIcon, 2);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Item Name
        _detailItemName = new Label
        {
            Text = "(no slot selected)",
            Font = new Font(Font.FontFamily, 10, FontStyle.Bold),
            AutoSize = true,
            ForeColor = Color.DarkBlue
        };
        detailLayout.Controls.Add(CreateLabel("Name:"), 0, row);
        detailLayout.Controls.Add(_detailItemName, 1, row);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Slot Position
        _detailSlotPosition = new Label { Text = "", AutoSize = true };
        detailLayout.Controls.Add(CreateLabel("Position:"), 0, row);
        detailLayout.Controls.Add(_detailSlotPosition, 1, row);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Item Type
        _detailItemType = new Label { Text = "", AutoSize = true };
        detailLayout.Controls.Add(CreateLabel("Type:"), 0, row);
        detailLayout.Controls.Add(_detailItemType, 1, row);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Item Category
        _detailItemCategory = new Label { Text = "", AutoSize = true };
        detailLayout.Controls.Add(CreateLabel("Category:"), 0, row);
        detailLayout.Controls.Add(_detailItemCategory, 1, row);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Item ID (editable)
        _detailItemId = new TextBox { Dock = DockStyle.Fill };
        detailLayout.Controls.Add(CreateLabel("Item ID:"), 0, row);
        detailLayout.Controls.Add(_detailItemId, 1, row);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Amount
        _detailAmount = new NumericUpDown { Dock = DockStyle.Fill, Minimum = 0, Maximum = int.MaxValue };
        detailLayout.Controls.Add(CreateLabel("Amount:"), 0, row);
        detailLayout.Controls.Add(_detailAmount, 1, row);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Max Amount
        _detailMaxAmount = new NumericUpDown { Dock = DockStyle.Fill, Minimum = 0, Maximum = int.MaxValue };
        detailLayout.Controls.Add(CreateLabel("Max:"), 0, row);
        detailLayout.Controls.Add(_detailMaxAmount, 1, row);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Damage Factor
        _detailDamageFactor = new NumericUpDown
        {
            Dock = DockStyle.Fill,
            Minimum = 0,
            Maximum = 1,
            DecimalPlaces = 4,
            Increment = 0.01m
        };
        detailLayout.Controls.Add(CreateLabel("Damage:"), 0, row);
        detailLayout.Controls.Add(_detailDamageFactor, 1, row);
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
            Padding = new Padding(0, 4, 0, 0)
        };
        detailLayout.Controls.Add(_detailDescription, 0, row);
        detailLayout.SetColumnSpan(_detailDescription, 2);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // === ITEM PICKER SECTION ===
        var separator = new Label
        {
            Text = "",
            BorderStyle = BorderStyle.Fixed3D,
            AutoSize = false,
            Height = 2,
            Dock = DockStyle.Fill,
            Margin = new Padding(0, 8, 0, 8)
        };
        detailLayout.Controls.Add(separator, 0, row);
        detailLayout.SetColumnSpan(separator, 2);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        var pickerTitle = new Label
        {
            Text = "Item Picker",
            Font = new Font(Font.FontFamily, 10, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 4)
        };
        detailLayout.Controls.Add(pickerTitle, 0, row);
        detailLayout.SetColumnSpan(pickerTitle, 2);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Search box
        var searchPanel = new Panel { Dock = DockStyle.Fill, Height = 26 };
        _searchBox = new TextBox { Dock = DockStyle.Fill, PlaceholderText = "Search items..." };
        _searchButton = new Button { Text = "Search", Dock = DockStyle.Right, Width = 60 };
        _searchButton.Click += OnSearch;
        _searchBox.KeyDown += (s, e) => { if (e.KeyCode == Keys.Enter) OnSearch(s, e); };
        searchPanel.Controls.Add(_searchBox);
        searchPanel.Controls.Add(_searchButton);
        detailLayout.Controls.Add(CreateLabel("Search:"), 0, row);
        detailLayout.Controls.Add(searchPanel, 1, row);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Type filter
        _typeFilter = new ComboBox
        {
            Dock = DockStyle.Fill,
            DropDownStyle = ComboBoxStyle.DropDownList
        };
        _typeFilter.SelectedIndexChanged += OnTypeFilterChanged;
        detailLayout.Controls.Add(CreateLabel("Type:"), 0, row);
        detailLayout.Controls.Add(_typeFilter, 1, row);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Category filter
        _categoryFilter = new ComboBox
        {
            Dock = DockStyle.Fill,
            DropDownStyle = ComboBoxStyle.DropDownList
        };
        _categoryFilter.SelectedIndexChanged += OnCategoryFilterChanged;
        detailLayout.Controls.Add(CreateLabel("Category:"), 0, row);
        detailLayout.Controls.Add(_categoryFilter, 1, row);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        // Item picker
        _itemPicker = new ComboBox
        {
            Dock = DockStyle.Fill,
            DropDownStyle = ComboBoxStyle.DropDownList,
            MaxDropDownItems = 20
        };
        _itemPicker.SelectedIndexChanged += OnItemPickerChanged;
        detailLayout.Controls.Add(CreateLabel("Item:"), 0, row);
        detailLayout.Controls.Add(_itemPicker, 1, row);
        detailLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        row++;

        _detailPanel.Controls.Add(detailLayout);
        splitContainer.Panel2.Controls.Add(_detailPanel);

        // Context menu
        _cellContextMenu = new ContextMenuStrip();
        _addItemMenuItem = new ToolStripMenuItem("Add Item", null, OnAddItem);
        _removeItemMenuItem = new ToolStripMenuItem("Remove Item", null, OnRemoveItem);
        _enableSlotMenuItem = new ToolStripMenuItem("Enable/Disable Slot", null, OnEnableSlot);
        _enableAllSlotsMenuItem = new ToolStripMenuItem("Enable All Slots", null, OnEnableAllSlots);
        _repairSlotMenuItem = new ToolStripMenuItem("Repair Slot", null, OnRepairSlot);
        _repairAllSlotsMenuItem = new ToolStripMenuItem("Repair All Slots", null, OnRepairAllSlots);
        _superchargeSlotMenuItem = new ToolStripMenuItem("Supercharge Slot", null, OnSuperchargeSlot);
        _superchargeAllSlotsMenuItem = new ToolStripMenuItem("Supercharge All Slots", null, OnSuperchargeAllSlots);
        _fillStackMenuItem = new ToolStripMenuItem("Fill Stack", null, OnFillStack);
        _cellContextMenu.Items.Add(_addItemMenuItem);
        _cellContextMenu.Items.Add(_removeItemMenuItem);
        _cellContextMenu.Items.Add(new ToolStripSeparator());
        _cellContextMenu.Items.Add(_enableSlotMenuItem);
        _cellContextMenu.Items.Add(_enableAllSlotsMenuItem);
        _cellContextMenu.Items.Add(new ToolStripSeparator());
        _cellContextMenu.Items.Add(_repairSlotMenuItem);
        _cellContextMenu.Items.Add(_repairAllSlotsMenuItem);
        _cellContextMenu.Items.Add(new ToolStripSeparator());
        _cellContextMenu.Items.Add(_superchargeSlotMenuItem);
        _cellContextMenu.Items.Add(_superchargeAllSlotsMenuItem);
        _cellContextMenu.Items.Add(new ToolStripSeparator());
        _cellContextMenu.Items.Add(_fillStackMenuItem);
        _cellContextMenu.Opening += OnContextMenuOpening;

        Controls.Add(splitContainer);
        ResumeLayout(false);
        PerformLayout();
    }

    private static Label CreateLabel(string text) =>
        new Label { Text = text, AutoSize = true, Padding = new Padding(0, 4, 6, 0) };

    public void SetDatabase(GameItemDatabase? database)
    {
        _database = database;
        if (database != null)
        {
            _allItems = database.Items.Values.ToList();
            PopulateTypeFilter();
        }
    }

    public void SetIconManager(IconManager? iconManager)
    {
        _iconManager = iconManager;
    }

    private void PopulateTypeFilter()
    {
        _suppressFilterEvents = true;
        _typeFilter.Items.Clear();
        _typeFilter.Items.Add("(All Types)");

        var types = _allItems
            .Select(i => i.ItemType)
            .Where(t => !string.IsNullOrEmpty(t))
            .Distinct(StringComparer.OrdinalIgnoreCase)
            .OrderBy(t => t)
            .ToList();
        foreach (var t in types)
            _typeFilter.Items.Add(t);

        _typeFilter.SelectedIndex = 0;
        _suppressFilterEvents = false;
        UpdateCategoryFilter();
    }

    private void UpdateCategoryFilter()
    {
        _suppressFilterEvents = true;
        _categoryFilter.Items.Clear();
        _categoryFilter.Items.Add("(All Categories)");

        var filtered = GetTypeFilteredItems();
        var categories = filtered
            .Select(i => i.Category)
            .Where(c => !string.IsNullOrEmpty(c))
            .Distinct(StringComparer.OrdinalIgnoreCase)
            .OrderBy(c => c)
            .ToList();
        foreach (var c in categories)
            _categoryFilter.Items.Add(c);

        _categoryFilter.SelectedIndex = 0;
        _suppressFilterEvents = false;
        UpdateItemPicker();
    }

    private void UpdateItemPicker()
    {
        _suppressFilterEvents = true;
        _itemPicker.Items.Clear();
        _itemPicker.Items.Add("(Select an item)");

        var items = GetFilteredItems().OrderBy(i => i.Name).ToList();
        foreach (var item in items)
            _itemPicker.Items.Add(item);

        _itemPicker.SelectedIndex = 0;
        _suppressFilterEvents = false;
    }

    private IEnumerable<GameItem> GetTypeFilteredItems()
    {
        var items = (IEnumerable<GameItem>)_allItems;
        if (_typeFilter.SelectedIndex > 0)
        {
            var type = _typeFilter.SelectedItem?.ToString() ?? "";
            items = items.Where(i => i.ItemType.Equals(type, StringComparison.OrdinalIgnoreCase));
        }
        return items;
    }

    private IEnumerable<GameItem> GetFilteredItems()
    {
        var items = GetTypeFilteredItems();
        if (_categoryFilter.SelectedIndex > 0)
        {
            var cat = _categoryFilter.SelectedItem?.ToString() ?? "";
            items = items.Where(i => i.Category.Equals(cat, StringComparison.OrdinalIgnoreCase));
        }
        return items;
    }

    private void OnSearch(object? sender, EventArgs e)
    {
        string query = _searchBox.Text.Trim();
        if (string.IsNullOrEmpty(query) || _database == null)
        {
            PopulateTypeFilter();
            return;
        }

        _allItems = _database.Search(query).ToList();
        PopulateTypeFilter();
    }

    private void OnTypeFilterChanged(object? sender, EventArgs e)
    {
        if (_suppressFilterEvents) return;
        UpdateCategoryFilter();
    }

    private void OnCategoryFilterChanged(object? sender, EventArgs e)
    {
        if (_suppressFilterEvents) return;
        UpdateItemPicker();
    }

    private void OnItemPickerChanged(object? sender, EventArgs e)
    {
        if (_suppressFilterEvents) return;
        if (_itemPicker.SelectedItem is not GameItem selectedItem) return;

        // Populate detail fields with selected item
        _detailItemId.Text = selectedItem.Id;
        _detailItemName.Text = selectedItem.Name;
        _detailItemType.Text = selectedItem.ItemType;
        _detailItemCategory.Text = selectedItem.Category;
        _detailDescription.Text = selectedItem.Description;

        // Set icon
        if (_iconManager != null && !string.IsNullOrEmpty(selectedItem.Icon))
            _detailIcon.Image = _iconManager.GetIcon(selectedItem.Icon);

        // Set sensible defaults for amount based on item type
        if (selectedItem.ItemType.Equals("substance", StringComparison.OrdinalIgnoreCase))
        {
            _detailAmount.Value = 9999;
            _detailMaxAmount.Value = 9999;
            _detailAmount.Enabled = true;
            _detailMaxAmount.Enabled = true;
        }
        else if (selectedItem.ItemType.Equals("product", StringComparison.OrdinalIgnoreCase))
        {
            _detailAmount.Value = 10;
            _detailMaxAmount.Value = 10;
            _detailAmount.Enabled = true;
            _detailMaxAmount.Enabled = true;
        }
        else
        {
            // Technology and other single-item types => treat as "single item"
            _detailAmount.Value = 1;
            _detailMaxAmount.Value = 1;
            _detailAmount.Enabled = false;
            _detailMaxAmount.Enabled = false;
        }

        _applyButton.Enabled = _selectedCell != null;
    }

    public void LoadInventory(JsonObject? inventory)
    {
        _gridContainer.Controls.Clear();
        _cells.Clear();
        _selectedCell = null;
        ClearDetailPanel();
        _slots = null;
        _currentInventory = inventory;

        if (inventory == null) return;
        _slots = inventory.GetArray("Slots");
        if (_slots == null) return;

        // Determine grid dimensions from inventory Width/Height fields (matching Java gt.java)
        int width = GridColumns;
        int height = 1;
        try
        {
            int invWidth = 0, invHeight = 0;
            try { invWidth = inventory.GetInt("Width"); } catch { }
            try { invHeight = inventory.GetInt("Height"); } catch { }
            if (invWidth > 0 && invHeight > 0)
            {
                width = invWidth;
                height = invHeight;
            }
            else
            {
                // Fallback: infer from ValidSlotIndices + Slots
                var validSlots = inventory.GetArray("ValidSlotIndices");
                int maxX = 0, maxY = 0;
                if (validSlots != null)
                {
                    for (int i = 0; i < validSlots.Length; i++)
                    {
                        var idx = validSlots.GetObject(i);
                        if (idx != null)
                        {
                            try { maxX = Math.Max(maxX, idx.GetInt("X")); } catch { }
                            try { maxY = Math.Max(maxY, idx.GetInt("Y")); } catch { }
                        }
                    }
                }
                // Also check Slots for items beyond ValidSlotIndices
                for (int i = 0; i < _slots.Length; i++)
                {
                    try
                    {
                        var slot = _slots.GetObject(i);
                        var si = slot?.GetObject("Index");
                        if (si != null)
                        {
                            maxX = Math.Max(maxX, si.GetInt("X"));
                            maxY = Math.Max(maxY, si.GetInt("Y"));
                        }
                    }
                    catch { }
                }
                if (maxX > 0 || maxY > 0)
                {
                    width = maxX + 1;
                    height = maxY + 1;
                }
                else
                {
                    height = (_slots.Length + width - 1) / width;
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

        // Build valid slot set
        var validSet = new HashSet<(int, int)>();
        try
        {
            var validSlots = inventory.GetArray("ValidSlotIndices");
            if (validSlots != null)
            {
                for (int i = 0; i < validSlots.Length; i++)
                {
                    var idx = validSlots.GetObject(i);
                    if (idx != null)
                    {
                        try { validSet.Add((idx.GetInt("X"), idx.GetInt("Y"))); } catch { }
                    }
                }
            }
        }
        catch { }

        // Create grid cells
        for (int r = 0; r < height; r++)
        {
            for (int col = 0; col < width; col++)
            {
                var cell = new SlotCell(col, r, CellWidth, CellHeight);
                cell.Location = new Point(
                    col * (CellWidth + CellPadding) + CellPadding,
                    r * (CellHeight + CellPadding) + CellPadding
                );

                if (slotMap.TryGetValue((col, r), out var slotData))
                {
                    cell.SlotIndex = slotData.index;
                    cell.SlotData = slotData.slot;
                    LoadCellData(cell);
                }
                else if (validSet.Contains((col, r)) || validSet.Count == 0)
                {
                    // Valid slot but empty - can add items here
                    cell.IsValidEmpty = true;
                    cell.IsActivated = true; // In ValidSlotIndices = enabled
                    cell.UpdateDisplay();
                }
                else
                {
                    // Not in ValidSlotIndices and no data = disabled slot
                    cell.IsEmpty = true;
                    cell.IsActivated = false;
                    cell.UpdateDisplay();
                }

                cell.Click += OnCellClicked;
                AttachRightClickHandler(cell);
                _gridContainer.Controls.Add(cell);
                _cells.Add(cell);
            }
        }

        // Reset item list from full database
        if (_database != null)
        {
            _allItems = _database.Items.Values.ToList();
            PopulateTypeFilter();
        }
    }

    /// <summary>
    /// Convert a BinaryData item ID to its display string form,
    /// matching the Java fg.bP() method used for TechBox items.
    /// Binary format: ^(hex-encoded-bytes)#(variant-digits)
    /// </summary>
    private static string BinaryDataToItemId(BinaryData data)
    {
        var bytes = data.ToByteArray();
        var sb = new System.Text.StringBuilder();
        bool afterHash = false;
        for (int i = 0; i < bytes.Length; i++)
        {
            int b = bytes[i] & 0xFF;
            if (i == 0)
            {
                if (b != 0x5E) // '^'
                    return data.ToString();
                sb.Append('^');
            }
            else if (b == 0x23) // '#'
            {
                sb.Append('#');
                afterHash = true;
            }
            else if (afterHash)
            {
                sb.Append((char)b);
            }
            else
            {
                const string hexChars = "0123456789ABCDEF";
                sb.Append(hexChars[(b >> 4) & 0xF]);
                sb.Append(hexChars[b & 0xF]);
            }
        }
        return sb.ToString();
    }

    /// <summary>
    /// Extract an item ID string from a raw value that may be a string or BinaryData.
    /// </summary>
    private static string ExtractItemId(object? rawId)
    {
        if (rawId is BinaryData binData)
            return BinaryDataToItemId(binData);
        return rawId as string ?? "";
    }

    private void LoadCellData(SlotCell cell)
    {
        if (cell.SlotData == null) return;

        string itemId = "";
        try
        {
            var idObj = cell.SlotData.GetObject("Id");
            if (idObj != null)
                itemId = ExtractItemId(idObj.Get("Id"));
            else
                itemId = ExtractItemId(cell.SlotData.Get("Id"));
        }
        catch { }

        cell.ItemId = itemId;

        int amount = 0, maxAmount = 0;
        double damageFactor = 0;
        try { amount = cell.SlotData.GetInt("Amount"); } catch { }
        try { maxAmount = cell.SlotData.GetInt("MaxAmount"); } catch { }
        try { damageFactor = cell.SlotData.GetDouble("DamageFactor"); } catch { }
        cell.Amount = amount;
        cell.MaxAmount = maxAmount;
        cell.DamageFactor = damageFactor;

        // Check activation status (whether position is in ValidSlotIndices)
        cell.IsActivated = IsSlotInValidIndices(cell.GridX, cell.GridY);

        // Check supercharged status (SpecialSlots entry with matching X,Y and TechBonus type)
        cell.IsSupercharged = IsSlotSupercharged(cell.GridX, cell.GridY);

        // Check if slot has an actual item or is empty-with-position
        if (string.IsNullOrEmpty(itemId) || itemId == "^" || itemId == "^YOURSLOTITEM")
        {
            cell.IsValidEmpty = true;
            cell.SlotData = cell.SlotData; // Keep slot data for position
            cell.UpdateDisplay();
            return;
        }

        // Look up display name and type from database, handling variant IDs like ^UP_JETX#76842
        if (_database != null && !string.IsNullOrEmpty(itemId))
        {
            var (gameItem, displayName) = ResolveItemAndDisplayName(itemId);
            if (gameItem != null)
            {
                cell.DisplayName = displayName;
                cell.ItemType = gameItem.ItemType;
                cell.Category = gameItem.Category;

                if (_iconManager != null && !string.IsNullOrEmpty(gameItem.Icon))
                    cell.IconImage = _iconManager.GetIcon(gameItem.Icon);
            }
            else
            {
                // Fallback: show raw ID (with variant appended)
                cell.DisplayName = displayNameFromId(itemId);
            }
        }

        cell.UpdateDisplay();
    }

    // Helper: build a simple display when no DB entry exists
    private static string displayNameFromId(string id)
    {
        var m = Regex.Match(id, @"#\d{5,}$");
        if (m.Success)
        {
            var baseId = id.Substring(0, m.Index);
            var variant = m.Value;
            return baseId + " [" + variant + "]";
        }
        return id;
    }

    /// <summary>
    /// Resolve a GameItem and a display name for an item id that may include a variant suffix.
    /// Variant format recognized: '#'+5+digits at the end (e.g. ^UP_JETX#76842).
    /// Lookup tries the exact id first, then falls back to the base id (without the #digits).
    /// The returned display name uses the base GameItem.Name with " [#digits]" appended when a variant is present.
    /// </summary>
    private (GameItem? gameItem, string displayName) ResolveItemAndDisplayName(string itemId)
    {
        if (_database == null) return (null, displayNameFromId(itemId));

        // Detect variant suffix (#12345)
        var m = Regex.Match(itemId, @"#\d{5,}$");
        string baseId = itemId;
        string variant = "";
        if (m.Success)
        {
            variant = m.Value; // includes the leading '#'
            baseId = itemId.Substring(0, m.Index);
        }

        // Try lookup: exact id, with optional ^ prefix, then fall back to base id forms
        GameItem? gi = _database.GetItem(itemId) ?? _database.GetItem("^" + itemId);
        if (gi == null && !string.IsNullOrEmpty(variant))
        {
            gi = _database.GetItem(baseId) ?? _database.GetItem("^" + baseId);
        }

        string displayBase = gi?.Name ?? baseId;
        string displayName = string.IsNullOrEmpty(variant) ? displayBase : $"{displayBase} [{variant}]";
        return (gi, displayName);
    }

    private bool IsSlotInValidIndices(int x, int y)
    {
        if (_currentInventory == null) return false;
        try
        {
            var validSlots = _currentInventory.GetArray("ValidSlotIndices");
            if (validSlots == null) return false;
            for (int i = 0; i < validSlots.Length; i++)
            {
                var idx = validSlots.GetObject(i);
                if (idx != null && idx.GetInt("X") == x && idx.GetInt("Y") == y)
                    return true;
            }
        }
        catch { }
        return false;
    }

    private bool IsSlotSupercharged(int x, int y)
    {
        if (_currentInventory == null) return false;
        try
        {
            var specialSlots = _currentInventory.GetArray("SpecialSlots");
            if (specialSlots == null) return false;
            for (int i = 0; i < specialSlots.Length; i++)
            {
                var entry = specialSlots.GetObject(i);
                if (entry == null) continue;
                var typeObj = entry.GetObject("Type");
                if (typeObj == null) continue;
                string slotType = typeObj.GetString("InventorySpecialSlotType") ?? "";
                if (slotType != "TechBonus") continue;
                var idxObj = entry.GetObject("Index");
                if (idxObj == null) continue;
                if (idxObj.GetInt("X") == x && idxObj.GetInt("Y") == y)
                    return true;
            }
        }
        catch { }
        return false;
    }

    private void OnCellClicked(object? sender, EventArgs e)
    {
        if (sender is not SlotCell cell) return;
        SelectCell(cell);
    }

    /// <summary>
    /// Attach explicit MouseUp handlers to a cell and all its children so that
    /// right-click reliably shows the context menu regardless of which child
    /// control the cursor is over.
    /// </summary>
    private void AttachRightClickHandler(SlotCell cell)
    {
        void Handler(object? s, MouseEventArgs e)
        {
            if (e.Button != MouseButtons.Right) return;
            _contextCell = cell;
            ConfigureContextMenuItems(cell);
            var screenPoint = (s as Control)?.PointToScreen(e.Location) ?? Cursor.Position;
            _cellContextMenu.Show(screenPoint);
        }

        // Attach to the cell and recursively to all descendants so inner elements (like marquee inner label) are covered
        void Attach(Control ctrl)
        {
            ctrl.MouseUp += Handler;
            foreach (Control child in ctrl.Controls)
                Attach(child);
        }

        Attach(cell);
    }

    /// <summary>
    /// Configure context menu item visibility based on the cell state.
    /// </summary>
    private void ConfigureContextMenuItems(SlotCell cell)
    {
        bool hasItem = cell.SlotData != null && !string.IsNullOrEmpty(cell.ItemId) && !cell.IsValidEmpty;
        bool canAdd = cell.IsValidEmpty || (!cell.IsEmpty);
        bool isActivated = cell.IsActivated;

        _addItemMenuItem.Visible = canAdd;
        _addItemMenuItem.Text = hasItem ? "Replace Item" : "Add Item";
        _removeItemMenuItem.Visible = hasItem;

        _enableSlotMenuItem.Visible = true;
        _enableSlotMenuItem.Text = isActivated ? "Disable Slot" : "Enable Slot";
        _enableAllSlotsMenuItem.Visible = _currentInventory != null;

        _repairSlotMenuItem.Visible = hasItem;
        _repairAllSlotsMenuItem.Visible = _currentInventory != null;

        _superchargeSlotMenuItem.Visible = true;
        _superchargeSlotMenuItem.Text = cell.IsSupercharged ? "Remove Supercharge" : "Supercharge Slot";
        _superchargeAllSlotsMenuItem.Visible = _currentInventory != null;

        // Hide Fill Stack for "single item" types (Amount == -1 or 1/1 or 100/100 or special 1/100 procedural tech)
        _fillStackMenuItem.Visible = hasItem && cell.MaxAmount > 0 && !cell.IsSingleItem;
    }

    private void OnContextMenuOpening(object? sender, CancelEventArgs e)
    {
        if (_contextCell == null || _currentInventory == null)
        {
            e.Cancel = true;
            return;
        }
    }

    private void SelectCell(SlotCell cell)
    {
        // Deselect previous
        if (_selectedCell != null)
            _selectedCell.IsSelected = false;

        cell.IsSelected = true;
        _selectedCell = cell;

        if (cell.IsEmpty)
        {
            ClearDetailPanel();
            return;
        }

        if (cell.IsValidEmpty && string.IsNullOrEmpty(cell.ItemId))
        {
            // Empty valid slot - show position only
            _detailItemName.Text = "(empty slot)";
            _detailSlotPosition.Text = $"({cell.GridX}, {cell.GridY})";
            _detailItemType.Text = "";
            _detailItemCategory.Text = "";
            _detailItemId.Text = "";
            _detailAmount.Value = 0;
            _detailMaxAmount.Value = 0;
            _detailDamageFactor.Value = 0;
            _detailDescription.Text = "Right-click to add an item.";
            _detailIcon.Image = null;
            _applyButton.Enabled = false;
            return;
        }

        // Populate detail panel with slot data
        _detailItemId.Text = cell.ItemId;

        // If slot represents a single-item (Amount == -1 OR 1/1 OR 100/100 OR special procedural-tech 1/100), disable editing of counts and show a neutral value.
        if (cell.IsSingleItem)
        {
            // Show '1' as neutral display value but disable editing; JSON representation will keep -1 where appropriate.
            _detailAmount.Value = 1;
            _detailMaxAmount.Value = 1;
            _detailAmount.Enabled = false;
            _detailMaxAmount.Enabled = false;
        }
        else
        {
            _detailAmount.Value = Math.Clamp(cell.Amount, (int)_detailAmount.Minimum, (int)_detailAmount.Maximum);
            _detailMaxAmount.Value = Math.Clamp(cell.MaxAmount, (int)_detailMaxAmount.Minimum, (int)_detailMaxAmount.Maximum);
            _detailAmount.Enabled = true;
            _detailMaxAmount.Enabled = true;
        }

        _detailDamageFactor.Value = (decimal)Math.Clamp(cell.DamageFactor, 0, 1);
        _detailSlotPosition.Text = $"({cell.GridX}, {cell.GridY})";
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

        // Show description from database (use base item when variant present)
        if (_database != null && !string.IsNullOrEmpty(cell.ItemId))
        {
            var (item, _) = ResolveItemAndDisplayName(cell.ItemId);
            _detailDescription.Text = item?.Description ?? "";
        }
        else
        {
            _detailDescription.Text = "";
        }
    }

    private void OnApplyChanges(object? sender, EventArgs e)
    {
        if (_selectedCell == null || _slots == null) return;

        string newItemId = _detailItemId.Text.Trim();
        if (string.IsNullOrEmpty(newItemId))
        {
            MessageBox.Show("Enter or select an Item ID first.", "Apply Changes",
                MessageBoxButtons.OK, MessageBoxIcon.Information);
            return;
        }

        // Determine if this item should be represented as a "single item"
        GameItem? gi = null;
        string giCategory = "";
        string giItemType = "";
        if (_database != null)
        {
            gi = _database.GetItem(newItemId) ?? _database.GetItem("^" + newItemId);
            if (gi == null)
            {
                // if not found, try base id (handle variant)
                var m = Regex.Match(newItemId, @"#\d{5,}$");
                if (m.Success)
                {
                    var baseId = newItemId.Substring(0, m.Index);
                    gi = _database.GetItem(baseId) ?? _database.GetItem("^" + baseId);
                }
            }
            if (gi != null)
            {
                giCategory = gi.Category ?? "";
                giItemType = gi.ItemType ?? "";
            }
        }

        // Read values from detail controls first so we can apply special-case logic that depends on the numeric values (e.g. 1/100 procedural tech)
        int amount = (int)_detailAmount.Value;
        int maxAmount = (int)_detailMaxAmount.Value;

        bool shouldBeSingle = false;
        if (gi != null)
        {
            shouldBeSingle = gi.ItemType.Equals("technology", StringComparison.OrdinalIgnoreCase)
                             || gi.ItemType.Equals("proceduralTechnology", StringComparison.OrdinalIgnoreCase)
                             || gi.ItemType.Equals("procedural-technology", StringComparison.OrdinalIgnoreCase);

            // Special-case: ships inventory procedural tech items stored as 1/100 and category ALLSHIPSEXCEPTALIEN should be treated as single items
            if (!shouldBeSingle &&
                giCategory.Equals("ALLSHIPSEXCEPTALIEN", StringComparison.OrdinalIgnoreCase) &&
                (giItemType.Equals("proceduralTechnology", StringComparison.OrdinalIgnoreCase)
                 || giItemType.Equals("procedural-technology", StringComparison.OrdinalIgnoreCase))
                && amount == 1 && maxAmount == 100)
            {
                shouldBeSingle = true;
            }
        }

        // If the detail numeric controls are disabled (single-item), or the item type indicates single,
        // use the save-format sentinel for single items (Amount = -1) and MaxAmount = 1.
        if (!_detailAmount.Enabled || !_detailMaxAmount.Enabled || shouldBeSingle)
        {
            amount = -1;
            maxAmount = 1;
        }
        else
        {
            if (amount <= 0) amount = 1;
            if (maxAmount <= 0) maxAmount = amount;
        }

        // If the cell has no existing slot data (valid empty slot), create a new slot
        if (_selectedCell.SlotData == null)
        {
            string invType = "Product";
            if (_database != null)
            {
                var gameItem = _database.GetItem(newItemId) ?? _database.GetItem("^" + newItemId);
                if (gameItem == null)
                {
                    var m = Regex.Match(newItemId, @"#\d{5,}$");
                    if (m.Success)
                    {
                        var baseId = newItemId.Substring(0, m.Index);
                        gameItem = _database.GetItem(baseId) ?? _database.GetItem("^" + baseId);
                    }
                }
                if (gameItem != null)
                    invType = gameItem.ItemType switch
                    {
                        "substance" => "Substance",
                        "product" => "Product",
                        "technology" or "proceduralTechnology" => "Technology",
                        _ => "Product"
                    };
            }

            var newSlot = new JsonObject();
            var typeObj = new JsonObject();
            typeObj.Add("InventoryType", invType);
            newSlot.Add("Type", typeObj);
            var idObj = new JsonObject();
            idObj.Add("Id", newItemId);
            newSlot.Add("Id", idObj);
            newSlot.Add("Amount", amount);
            newSlot.Add("MaxAmount", maxAmount);
            newSlot.Add("DamageFactor", (double)_detailDamageFactor.Value);
            newSlot.Add("FullyInstalled", true);
            var indexObj = new JsonObject();
            indexObj.Add("X", _selectedCell.GridX);
            indexObj.Add("Y", _selectedCell.GridY);
            newSlot.Add("Index", indexObj);

            _slots.Add(newSlot);
            _selectedCell.SlotIndex = _slots.Length - 1;
            _selectedCell.SlotData = newSlot;
        }
        else
        {
            var slot = _selectedCell.SlotData;

            // Update Item ID
            try
            {
                var idObj = slot.GetObject("Id");
                if (idObj != null)
                    idObj.Set("Id", newItemId);
                else
                    slot.Set("Id", newItemId);
            }
            catch { }

            // Update Amount / MaxAmount (use single-item sentinel if appropriate)
            slot.Set("Amount", amount);
            slot.Set("MaxAmount", maxAmount);

            // Update DamageFactor
            try { slot.Set("DamageFactor", (double)_detailDamageFactor.Value); } catch { }

            // Update inventory type based on item
            if (_database != null && !string.IsNullOrEmpty(newItemId))
            {
                var gameItem = _database.GetItem(newItemId) ?? _database.GetItem("^" + newItemId);
                if (gameItem == null)
                {
                    var m = Regex.Match(newItemId, @"#\d{5,}$");
                    if (m.Success)
                    {
                        var baseId = newItemId.Substring(0, m.Index);
                        gameItem = _database.GetItem(baseId) ?? _database.GetItem("^" + baseId);
                    }
                }

                if (gameItem != null)
                {
                    try
                    {
                        var typeObj = slot.GetObject("Type");
                        if (typeObj != null)
                        {
                            string invType = gameItem.ItemType switch
                            {
                                "substance" => "Substance",
                                "product" => "Product",
                                "technology" or "proceduralTechnology" => "Technology",
                                _ => "Product"
                            };
                            typeObj.Set("InventoryType", invType);
                        }
                    }
                    catch { }
                }
            }
        }

        // Refresh cell display
        _selectedCell.ItemId = newItemId;
        _selectedCell.Amount = amount;
        _selectedCell.MaxAmount = maxAmount;
        _selectedCell.DamageFactor = (double)_detailDamageFactor.Value;
        _selectedCell.IsValidEmpty = false;
        _selectedCell.IsEmpty = false;

        if (_database != null && !string.IsNullOrEmpty(newItemId))
        {
            var (gameItem, displayName) = ResolveItemAndDisplayName(newItemId);
            if (gameItem != null)
            {
                _selectedCell.DisplayName = displayName;
                _selectedCell.ItemType = gameItem.ItemType;
                _selectedCell.Category = gameItem.Category;
                _detailItemName.Text = displayName;
                _detailItemType.Text = gameItem.ItemType;
                _detailItemCategory.Text = gameItem.Category;
                _detailDescription.Text = gameItem.Description;

                if (_iconManager != null && !string.IsNullOrEmpty(gameItem.Icon))
                {
                    _selectedCell.IconImage = _iconManager.GetIcon(gameItem.Icon);
                    _detailIcon.Image = _selectedCell.IconImage;
                }
            }
            else
            {
                _selectedCell.DisplayName = displayNameFromId(newItemId);
                _detailItemName.Text = _selectedCell.DisplayName;
                _detailDescription.Text = "";
            }
        }

        _selectedCell.UpdateDisplay();
    }

    private void OnAddItem(object? sender, EventArgs e)
    {
        if (_contextCell == null || _currentInventory == null) return;

        // Get the selected item from the picker or the ID field
        string itemId = _detailItemId.Text.Trim();

        if (_itemPicker.SelectedItem is GameItem pickedItem)
            itemId = pickedItem.Id;

        if (string.IsNullOrEmpty(itemId))
        {
            MessageBox.Show("Select an item from the Item Picker first, or enter an Item ID.",
                "Add Item", MessageBoxButtons.OK, MessageBoxIcon.Information);
            return;
        }

        // Determine if this item should be represented as a "single item"
        GameItem? gi = null;
        string giCategory = "";
        string giItemType = "";
        if (_database != null)
        {
            gi = _database.GetItem(itemId) ?? _database.GetItem("^" + itemId);
            if (gi == null)
            {
                var m = Regex.Match(itemId, @"#\d{5,}$");
                if (m.Success)
                {
                    var baseId = itemId.Substring(0, m.Index);
                    gi = _database.GetItem(baseId) ?? _database.GetItem("^" + baseId);
                }
            }
            if (gi != null)
            {
                giCategory = gi.Category ?? "";
                giItemType = gi.ItemType ?? "";
            }
        }

        // Read the numeric values from detail controls to check for the special 1/100 procedural-tech case
        int amount;
        int maxAmount;
        // If the detail numeric controls are disabled (single-item) or type implies single, use sentinel
        if (!_detailAmount.Enabled || !_detailMaxAmount.Enabled)
        {
            amount = -1;
            maxAmount = 1;
        }
        else
        {
            amount = (int)_detailAmount.Value;
            maxAmount = (int)_detailMaxAmount.Value;
            if (amount <= 0) amount = 1;
            if (maxAmount <= 0) maxAmount = amount;
        }

        bool shouldBeSingle = false;
        if (gi != null)
        {
            shouldBeSingle = gi.ItemType.Equals("technology", StringComparison.OrdinalIgnoreCase)
                             || gi.ItemType.Equals("proceduralTechnology", StringComparison.OrdinalIgnoreCase)
                             || gi.ItemType.Equals("procedural-technology", StringComparison.OrdinalIgnoreCase);

            // Special-case: ships inventory procedural tech items stored as 1/100 and category ALLSHIPSEXCEPTALIEN should be treated as single items
            if (!shouldBeSingle &&
                giCategory.Equals("ALLSHIPSEXCEPTALIEN", StringComparison.OrdinalIgnoreCase) &&
                (giItemType.Equals("proceduralTechnology", StringComparison.OrdinalIgnoreCase)
                 || giItemType.Equals("procedural-technology", StringComparison.OrdinalIgnoreCase))
                && amount == 1 && maxAmount == 100)
            {
                shouldBeSingle = true;
            }
        }

        if (shouldBeSingle)
        {
            amount = -1;
            maxAmount = 1;
        }

        // Determine inventory type
        string invType = "Product";
        if (_database != null)
        {
            var gameItem = _database.GetItem(itemId) ?? _database.GetItem("^" + itemId);
            if (gameItem == null)
            {
                var m = Regex.Match(itemId, @"#\d{5,}$");
                if (m.Success)
                {
                    var baseId = itemId.Substring(0, m.Index);
                    gameItem = _database.GetItem(baseId) ?? _database.GetItem("^" + baseId);
                }
            }
            if (gameItem != null)
            {
                invType = gameItem.ItemType switch
                {
                    "substance" => "Substance",
                    "product" => "Product",
                    "technology" or "proceduralTechnology" => "Technology",
                    _ => "Product"
                };
            }
        }

        // Create a new slot JSON object
        var newSlot = new JsonObject();

        var typeObj = new JsonObject();
        typeObj.Add("InventoryType", invType);
        newSlot.Add("Type", typeObj);

        var idObj = new JsonObject();
        idObj.Add("Id", itemId);
        newSlot.Add("Id", idObj);

        newSlot.Add("Amount", amount);
        newSlot.Add("MaxAmount", maxAmount);
        newSlot.Add("DamageFactor", 0.0);
        newSlot.Add("FullyInstalled", true);

        var indexObj = new JsonObject();
        indexObj.Add("X", _contextCell.GridX);
        indexObj.Add("Y", _contextCell.GridY);
        newSlot.Add("Index", indexObj);

        // If cell already has slot data, replace it in the array
        if (_contextCell.SlotData != null && _contextCell.SlotIndex >= 0 && _slots != null)
        {
            _slots.Set(_contextCell.SlotIndex, newSlot);
        }
        else if (_slots != null)
        {
            // Add new slot to the array
            _slots.Add(newSlot);
            _contextCell.SlotIndex = _slots.Length - 1;
        }

        // Update cell
        _contextCell.SlotData = newSlot;
        _contextCell.IsValidEmpty = false;
        _contextCell.IsEmpty = false;
        LoadCellData(_contextCell);
        _contextCell.UpdateDisplay();

        // Select the newly added cell
        SelectCell(_contextCell);
    }

    private void OnRemoveItem(object? sender, EventArgs e)
    {
        if (_contextCell?.SlotData == null || _slots == null) return;

        var result = MessageBox.Show(
            $"Remove '{_contextCell.DisplayName ?? _contextCell.ItemId}' from slot ({_contextCell.GridX}, {_contextCell.GridY})?",
            "Remove Item", MessageBoxButtons.YesNo, MessageBoxIcon.Question);

        if (result != DialogResult.Yes) return;

        // Clear the slot data: reset to empty
        // Rather than removing the slot object (which would change indices), we clear its data
        var slot = _contextCell.SlotData;
        try
        {
            var idObj = slot.GetObject("Id");
            if (idObj != null)
                idObj.Set("Id", "");
            else
                slot.Set("Id", "");
        }
        catch { }
        slot.Set("Amount", 0);
        slot.Set("MaxAmount", 0);
        slot.Set("DamageFactor", 0.0);
        try
        {
            var typeObj = slot.GetObject("Type");
            if (typeObj != null)
                typeObj.Set("InventoryType", "Product");
        }
        catch { }

        // Update cell display
        _contextCell.ItemId = "";
        _contextCell.DisplayName = "";
        _contextCell.ItemType = "";
        _contextCell.Category = "";
        _contextCell.Amount = 0;
        _contextCell.MaxAmount = 0;
        _contextCell.DamageFactor = 0;
        _contextCell.IconImage = null;
        _contextCell.IsValidEmpty = true;
        _contextCell.UpdateDisplay();

        // Clear detail panel
        if (_selectedCell == _contextCell)
            ClearDetailPanel();
    }

    private void OnEnableSlot(object? sender, EventArgs e)
    {
        if (_contextCell == null || _currentInventory == null) return;
        var validSlots = _currentInventory.GetArray("ValidSlotIndices");
        if (validSlots == null)
        {
            validSlots = new JsonArray();
            _currentInventory.Set("ValidSlotIndices", validSlots);
        }

        int x = _contextCell.GridX, y = _contextCell.GridY;

        if (_contextCell.IsActivated)
        {
            // Disable: only allowed if slot has no item (matches Java gt.java j() method)
            if (_contextCell.SlotData != null && !string.IsNullOrEmpty(_contextCell.ItemId)
                && _contextCell.ItemId != "^" && _contextCell.ItemId != "^YOURSLOTITEM")
            {
                MessageBox.Show("Cannot disable a slot that contains an item. Remove the item first.",
                    "Cannot Disable", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }
            // Remove from ValidSlotIndices
            for (int i = 0; i < validSlots.Length; i++)
            {
                var idx = validSlots.GetObject(i);
                if (idx != null && idx.GetInt("X") == x && idx.GetInt("Y") == y)
                {
                    validSlots.RemoveAt(i);
                    break;
                }
            }
            _contextCell.IsActivated = false;
        }
        else
        {
            // Enable: add to ValidSlotIndices (matches Java gt.java i() method)
            var newIdx = new JsonObject();
            newIdx.Add("X", x);
            newIdx.Add("Y", y);
            validSlots.Add(newIdx);
            _contextCell.IsActivated = true;
            _contextCell.IsEmpty = false;
            _contextCell.IsValidEmpty = true;
        }
        _contextCell.UpdateDisplay();
    }

    private void OnEnableAllSlots(object? sender, EventArgs e)
    {
        if (_currentInventory == null) return;
        var validSlots = _currentInventory.GetArray("ValidSlotIndices");
        if (validSlots == null)
        {
            validSlots = new JsonArray();
            _currentInventory.Set("ValidSlotIndices", validSlots);
        }

        var existing = new HashSet<(int, int)>();
        for (int i = 0; i < validSlots.Length; i++)
        {
            try
            {
                var idx = validSlots.GetObject(i);
                if (idx != null) existing.Add((idx.GetInt("X"), idx.GetInt("Y")));
            }
            catch { }
        }

        foreach (var cell in _cells)
        {
            if (!existing.Contains((cell.GridX, cell.GridY)))
            {
                var newIdx = new JsonObject();
                newIdx.Add("X", cell.GridX);
                newIdx.Add("Y", cell.GridY);
                validSlots.Add(newIdx);
            }
            cell.IsActivated = true;
            cell.UpdateDisplay();
        }
    }

    private void RepairSlotData(SlotCell cell)
    {
        if (cell.SlotData == null || _currentInventory == null) return;
        try { cell.SlotData.Set("DamageFactor", 0.0); } catch { }
        cell.DamageFactor = 0;

        // Remove BlockedByBrokenTech from SpecialSlots
        try
        {
            var specialSlots = _currentInventory.GetArray("SpecialSlots");
            if (specialSlots != null)
            {
                for (int i = specialSlots.Length - 1; i >= 0; i--)
                {
                    var entry = specialSlots.GetObject(i);
                    if (entry == null) continue;
                    var typeObj = entry.GetObject("Type");
                    if (typeObj == null) continue;
                    string slotType = typeObj.GetString("InventorySpecialSlotType") ?? "";
                    if (slotType != "BlockedByBrokenTech") continue;
                    var idxObj = entry.GetObject("Index");
                    if (idxObj == null) continue;
                    if (idxObj.GetInt("X") == cell.GridX && idxObj.GetInt("Y") == cell.GridY)
                        specialSlots.RemoveAt(i);
                }
            }
        }
        catch { }
    }

    private void OnRepairSlot(object? sender, EventArgs e)
    {
        if (_contextCell == null) return;
        RepairSlotData(_contextCell);
        _contextCell.UpdateDisplay();
    }

    private void OnRepairAllSlots(object? sender, EventArgs e)
    {
        foreach (var cell in _cells)
        {
            RepairSlotData(cell);
            cell.UpdateDisplay();
        }
    }

    private void ToggleSupercharge(SlotCell cell, bool forceAdd)
    {
        if (_currentInventory == null) return;
        var specialSlots = _currentInventory.GetArray("SpecialSlots");
        if (specialSlots == null)
        {
            specialSlots = new JsonArray();
            _currentInventory.Set("SpecialSlots", specialSlots);
        }

        int x = cell.GridX, y = cell.GridY;
        // Check if already supercharged
        if (!forceAdd)
        {
            for (int i = specialSlots.Length - 1; i >= 0; i--)
            {
                try
                {
                    var entry = specialSlots.GetObject(i);
                    if (entry == null) continue;
                    var typeObj = entry.GetObject("Type");
                    if (typeObj == null) continue;
                    string slotType = typeObj.GetString("InventorySpecialSlotType") ?? "";
                    if (slotType != "TechBonus") continue;
                    var idxObj = entry.GetObject("Index");
                    if (idxObj == null) continue;
                    if (idxObj.GetInt("X") == x && idxObj.GetInt("Y") == y)
                    {
                        specialSlots.RemoveAt(i);
                        cell.IsSupercharged = false;
                        return;
                    }
                }
                catch { }
            }
        }

        // Not found or forceAdd - check not already present when forceAdd
        if (forceAdd && IsSlotSupercharged(x, y))
        {
            cell.IsSupercharged = true;
            return;
        }

        var newEntry = new JsonObject();
        var newType = new JsonObject();
        newType.Add("InventorySpecialSlotType", "TechBonus");
        newEntry.Add("Type", newType);
        var newIndex = new JsonObject();
        newIndex.Add("X", x);
        newIndex.Add("Y", y);
        newEntry.Add("Index", newIndex);
        specialSlots.Add(newEntry);
        cell.IsSupercharged = true;
    }

    private void OnSuperchargeSlot(object? sender, EventArgs e)
    {
        if (_contextCell == null) return;
        ToggleSupercharge(_contextCell, false);
        _contextCell.UpdateDisplay();
    }

    private void OnSuperchargeAllSlots(object? sender, EventArgs e)
    {
        foreach (var cell in _cells)
        {
            ToggleSupercharge(cell, true);
            cell.UpdateDisplay();
        }
    }

    private void OnFillStack(object? sender, EventArgs e)
    {
        if (_contextCell?.SlotData == null) return;
        // Do not fill stacks for single-item slots
        if (_contextCell.IsSingleItem) return;
        int max = _contextCell.MaxAmount;
        if (max <= 0) return;
        _contextCell.SlotData.Set("Amount", max);
        _contextCell.Amount = max;
        _contextCell.UpdateDisplay();
        if (_selectedCell == _contextCell)
            _detailAmount.Value = max;
    }

    private void OnResizeInventory(object? sender, EventArgs e)
    {
        if (_currentInventory == null) return;

        int newWidth = (int)_resizeWidth.Value;
        int newHeight = (int)_resizeHeight.Value;

        var validSlots = _currentInventory.GetArray("ValidSlotIndices");
        if (validSlots == null)
        {
            validSlots = new JsonArray();
            _currentInventory.Set("ValidSlotIndices", validSlots);
        }
        var slots = _currentInventory.GetArray("Slots");
        if (slots == null)
        {
            slots = new JsonArray();
            _currentInventory.Set("Slots", slots);
        }

        // Build sets of existing valid indices and slot positions
        var existingValid = new HashSet<(int, int)>();
        for (int i = 0; i < validSlots.Length; i++)
        {
            try
            {
                var idx = validSlots.GetObject(i);
                if (idx != null) existingValid.Add((idx.GetInt("X"), idx.GetInt("Y")));
            }
            catch { }
        }

        var existingSlots = new HashSet<(int, int)>();
        for (int i = 0; i < slots.Length; i++)
        {
            try
            {
                var slot = slots.GetObject(i);
                var idx = slot.GetObject("Index");
                if (idx != null) existingSlots.Add((idx.GetInt("X"), idx.GetInt("Y")));
            }
            catch { }
        }

        // Remove ValidSlotIndices outside new dimensions
        for (int i = validSlots.Length - 1; i >= 0; i--)
        {
            try
            {
                var idx = validSlots.GetObject(i);
                if (idx != null)
                {
                    int x = idx.GetInt("X"), y = idx.GetInt("Y");
                    if (x >= newWidth || y >= newHeight)
                        validSlots.RemoveAt(i);
                }
            }
            catch { }
        }

        // Add ValidSlotIndices and empty slots for new positions
        for (int y = 0; y < newHeight; y++)
        {
            for (int x = 0; x < newWidth; x++)
            {
                if (!existingValid.Contains((x, y)))
                {
                    var newIdx = new JsonObject();
                    newIdx.Add("X", x);
                    newIdx.Add("Y", y);
                    validSlots.Add(newIdx);
                }
                if (!existingSlots.Contains((x, y)))
                {
                    var newSlot = new JsonObject();
                    var typeObj = new JsonObject();
                    typeObj.Add("InventoryType", "Product");
                    newSlot.Add("Type", typeObj);
                    var idObj = new JsonObject();
                    idObj.Add("Id", "");
                    newSlot.Add("Id", idObj);
                    newSlot.Add("Amount", 0);
                    newSlot.Add("MaxAmount", 0);
                    newSlot.Add("DamageFactor", 0.0);
                    newSlot.Add("FullyInstalled", false);
                    var indexObj = new JsonObject();
                    indexObj.Add("X", x);
                    indexObj.Add("Y", y);
                    newSlot.Add("Index", indexObj);
                    slots.Add(newSlot);
                }
            }
        }

        // Reload the grid
        LoadInventory(_currentInventory);
    }

    private void ClearDetailPanel()
    {
        _detailItemName.Text = "(no slot selected)";
        _detailSlotPosition.Text = "";
        _detailItemType.Text = "";
        _detailItemCategory.Text = "";
        _detailItemId.Text = "";
        _detailAmount.Value = 0;
        _detailMaxAmount.Value = 0;
        _detailDamageFactor.Value = 0;
        _detailDescription.Text = "";
        _detailIcon.Image = null;
        _detailAmount.Enabled = true;
        _detailMaxAmount.Enabled = true;
        _applyButton.Enabled = false;
    }

    /// <summary>
    /// Changes are applied immediately via OnApplyChanges/OnAddItem/OnRemoveItem.
    /// </summary>
    public void SaveInventory(JsonObject? inventory)
    {
        // Changes are written directly to slot JsonObjects
    }

    /// <summary>
    /// MarqueeLabel replacement copied from existing marquee.cs implementation.
    /// Provides a label that horizontally scrolls when the text width exceeds the control width.
    /// </summary>
    public class MarqueeLabel : Label
    {
        private System.Windows.Forms.Timer? _timer;
        private int _offset;
        private bool _shouldScroll;

        [DefaultValue(60)]
        public int ScrollSpeed { get; set; } = 60; // ms per tick

        public MarqueeLabel()
        {
            DoubleBuffered = true;
            SetStyle(ControlStyles.OptimizedDoubleBuffer | ControlStyles.AllPaintingInWmPaint, true);
            _timer = new System.Windows.Forms.Timer();
            _timer.Interval = ScrollSpeed;
            _timer.Tick += (s, e) =>
            {
                _offset += 2;
                Invalidate();
            };
        }

        protected override void OnTextChanged(EventArgs e)
        {
            base.OnTextChanged(e);
            CheckScroll();
        }

        protected override void OnSizeChanged(EventArgs e)
        {
            base.OnSizeChanged(e);
            CheckScroll();
        }

        private void CheckScroll()
        {
            using var g = CreateGraphics();
            var textWidth = (int)g.MeasureString(Text, Font).Width;
            _shouldScroll = textWidth > Width * 0.9;
            if (_shouldScroll)
            {
                _offset = 0;
                _timer?.Start();
            }
            else
            {
                _timer?.Stop();
                _offset = 0;
            }
            Invalidate();
        }

        protected override void OnPaint(PaintEventArgs e)
        {
            e.Graphics.Clear(BackColor);
            var textWidth = (int)e.Graphics.MeasureString(Text, Font).Width;
            if (_shouldScroll)
            {
                int x = -_offset;
                while (x < Width)
                {
                    e.Graphics.DrawString(Text, Font, new SolidBrush(ForeColor), x, 0);
                    x += textWidth + 40; // gap between repeats
                }
                if (_offset > textWidth + 40)
                    _offset = 0;
            }
            else
            {
                TextRenderer.DrawText(e.Graphics, Text, Font, ClientRectangle, ForeColor, BackColor, TextFormatFlags.HorizontalCenter | TextFormatFlags.VerticalCenter | TextFormatFlags.EndEllipsis);
            }
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
                _timer?.Dispose();
            base.Dispose(disposing);
        }
    }

    /// <summary>
    /// Individual slot cell in the inventory grid.
    /// </summary>
    private class SlotCell : Panel
    {
        private readonly Panel _iconContainer;
        private readonly PictureBox _iconBox; // inner PictureBox that we offset upward
        private readonly MarqueeLabel _nameLabel;
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
        public double DamageFactor { get; set; }
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public bool IsEmpty { get; set; }
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public bool IsValidEmpty { get; set; }
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public Image? IconImage { get; set; }
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public bool IsActivated { get; set; }
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public bool IsSupercharged { get; set; }

        // New helper property to identify "single item" slots:
        // - Amount == -1 is the save-file sentinel for single/no-count items
        // - OR Amount == 1 && MaxAmount == 1 should be treated the same
        // - OR Amount == 100 && MaxAmount == 100 for certain tech items that use 100/100 in saves but are effectively single items in-game
        // - OR special case: Amount == 1 && MaxAmount == 100 for ships inventory procedural tech items in category ALLSHIPSEXCEPTALIEN
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public bool IsSingleItem =>
            Amount == -1
            || (Amount == 1 && MaxAmount == 1)
            || (Amount == 100 && MaxAmount == 100)
            || (Amount == 1 && MaxAmount == 100
                && Category.Equals("ALLSHIPSEXCEPTALIEN", StringComparison.OrdinalIgnoreCase)
                && (ItemType.Equals("procedural-technology", StringComparison.OrdinalIgnoreCase)
                    || ItemType.Equals("proceduralTechnology", StringComparison.OrdinalIgnoreCase)))
            || (Amount == 1 && MaxAmount == 100
                && Category.Equals("CORVETTE", StringComparison.OrdinalIgnoreCase)
                && (ItemType.Equals("procedural-technology", StringComparison.OrdinalIgnoreCase)
                    || ItemType.Equals("proceduralTechnology", StringComparison.OrdinalIgnoreCase)))
            || (Amount == 1 && MaxAmount == 100
                && Category.Equals("SHIP", StringComparison.OrdinalIgnoreCase)
                && ItemType.Equals("technology", StringComparison.OrdinalIgnoreCase))
            || (Amount == 1 && MaxAmount == 100
                && Category.Equals("ALLSHIPSEXCEPTALIEN", StringComparison.OrdinalIgnoreCase)
                && ItemType.Equals("technology", StringComparison.OrdinalIgnoreCase));

        private bool _isSelected;
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public bool IsSelected
        {
            get => _isSelected;
            set { _isSelected = value; UpdateDisplay(); }
        }

        public SlotCell(int x, int y, int width, int height)
        {
            GridX = x;
            GridY = y;
            Size = new Size(width, height);
            BorderStyle = BorderStyle.FixedSingle;
            Cursor = Cursors.Hand;
            BackColor = Color.FromArgb(50, 50, 50);

            // Name label replaced with a MarqueeLabel that scrolls long text
            _nameLabel = new MarqueeLabel
            {
                Dock = DockStyle.Top,
                BackColor = Color.FromArgb(160, 0, 0, 0),
                Font = new Font("Segoe UI", 11f, FontStyle.Regular),
                ForeColor = Color.White,
                Height = 26,
                TextAlign = ContentAlignment.MiddleCenter,
                Visible = false
            };

            // Container for icon (fills middle area)
            _iconContainer = new Panel
            {
                Dock = DockStyle.Fill,
                BackColor = Color.Transparent
            };

            // Inner PictureBox that we will size slightly taller and shift up
            _iconBox = new PictureBox
            {
                SizeMode = PictureBoxSizeMode.Zoom,
                BackColor = Color.Transparent,
                Location = new Point(0, -16) // initial upward offset
            };

            // Ensure inner picturebox resizes when the container changes
            _iconContainer.Resize += (s, e) => UpdateIconLayout();
            _iconContainer.Controls.Add(_iconBox);

            _amountLabel = new Label
            {
                Dock = DockStyle.Bottom,
                ForeColor = Color.White,
                BackColor = Color.FromArgb(128, 0, 0, 0),
                Font = new Font("Segoe UI", 11f, FontStyle.Bold),
                TextAlign = ContentAlignment.MiddleCenter,
                Height = 22,
                AutoEllipsis = true
            };

            _toolTip = new ToolTip();

            // Add controls in z-order: amount (bottom), icon container (middle), name (top)
            Controls.Add(_amountLabel);
            Controls.Add(_iconContainer);
            Controls.Add(_nameLabel);

            // Forward child clicks to this panel for cell selection
            _iconBox.Click += (s, e) => OnClick(e);
            _amountLabel.Click += (s, e) => OnClick(e);
            _nameLabel.Click += (s, e) => OnClick(e);

            // Initial layout
            UpdateIconLayout();
        }

        private void UpdateIconLayout()
        {
            const int lift = 16; // how many pixels to lift icon up
            if (_iconContainer == null || _iconBox == null) return;

            // Make the inner picturebox slightly taller than the container and move it up
            var w = Math.Max(1, _iconContainer.ClientSize.Width);
            var h = Math.Max(1, _iconContainer.ClientSize.Height + lift);
            _iconBox.Size = new Size(w, h);
            _iconBox.Location = new Point(0, -lift);
        }

        public void UpdateDisplay()
        {
            if (IsEmpty)
            {
                // Disabled slot - not in ValidSlotIndices and no data
                Color bg = _isSelected ? Color.FromArgb(70, 80, 100) : Color.FromArgb(25, 25, 25);
                if (!IsActivated) bg = Color.FromArgb(80, 20, 20); // Red tint for disabled
                BackColor = bg;
                BorderStyle = BorderStyle.FixedSingle;
                _iconBox.Image = null;
                _nameLabel.Text = "";
                _nameLabel.Visible = false;
                _amountLabel.Text = "";
                _amountLabel.Visible = false;
                Cursor = Cursors.Hand; // Allow right-click to enable
                string tip = IsActivated ? $"Empty slot ({GridX}, {GridY})" : $"Disabled slot ({GridX}, {GridY}) - Right-click to enable";
                _toolTip.SetToolTip(this, tip);
                _toolTip.SetToolTip(_iconBox, tip);
                return;
            }

            if (IsValidEmpty && string.IsNullOrEmpty(ItemId))
            {
                // Valid empty slot - available for adding items
                Color bg = _isSelected ? Color.FromArgb(70, 100, 160) : Color.FromArgb(45, 45, 50);
                if (!IsActivated) bg = Color.FromArgb(80, 30, 30); // Inactive = transparent red tint
                BackColor = bg;
                BorderStyle = IsSupercharged ? BorderStyle.Fixed3D : BorderStyle.FixedSingle;
                _iconBox.Image = null;
                _nameLabel.Text = IsSupercharged ? SuperchargeIndicator : "";
                _nameLabel.ForeColor = IsSupercharged ? Color.Gold : Color.White;
                _nameLabel.Visible = IsSupercharged;
                _amountLabel.Text = "";
                _amountLabel.Visible = false;
                Cursor = Cursors.Hand;
                string tip = $"Empty slot ({GridX}, {GridY}) - Right-click to add item";
                _toolTip.SetToolTip(this, tip);
                _toolTip.SetToolTip(_iconBox, tip);
                _toolTip.SetToolTip(_nameLabel, tip);
                return;
            }

            // Set background color by item type
            Color baseColor;
            if (_isSelected)
                baseColor = Color.FromArgb(80, 120, 200);
            else if (ItemType.Contains("technology", StringComparison.OrdinalIgnoreCase))
                baseColor = Color.FromArgb(40, 60, 120);
            else if (ItemType.Contains("product", StringComparison.OrdinalIgnoreCase))
                baseColor = Color.FromArgb(120, 80, 30);
            else if (ItemType.Contains("substance", StringComparison.OrdinalIgnoreCase))
                baseColor = Color.FromArgb(30, 100, 100);
            else if (!string.IsNullOrEmpty(ItemId))
                baseColor = Color.FromArgb(60, 60, 60);
            else
                baseColor = Color.FromArgb(50, 50, 50);

            // Non-activated slots get a red tint overlay for clear visual distinction
            if (!IsActivated && !_isSelected)
                baseColor = Color.FromArgb(
                    Math.Min(255, baseColor.R / 2 + 60),
                    baseColor.G / 4,
                    baseColor.B / 4);

            BackColor = baseColor;
            BorderStyle = IsSupercharged ? BorderStyle.Fixed3D : BorderStyle.FixedSingle;

            // Display icon
            _iconBox.Image = IconImage;

            // Display item name at top (with supercharge indicator)
            string nameText = !string.IsNullOrEmpty(DisplayName) ? DisplayName : ItemId;
            if (IsSupercharged && !string.IsNullOrEmpty(nameText))
                nameText = SuperchargeIndicator + nameText;
            if (!string.IsNullOrEmpty(nameText))
            {
                _nameLabel.Text = nameText;
                _nameLabel.ForeColor = IsSupercharged ? Color.Gold : Color.White;
                _nameLabel.Visible = true;
            }
            else
            {
                _nameLabel.Text = "";
                _nameLabel.Visible = false;
            }

            // Display amount overlay at bottom
            if (IsSingleItem)
            {
                _amountLabel.Text = "";
                _amountLabel.Visible = true;
            }
            else if (Amount > 0 || MaxAmount > 0)
            {
                _amountLabel.Text = $"{Amount}/{MaxAmount}";
                _amountLabel.Visible = true;
            }
            else
            {
                _amountLabel.Text = "";
                _amountLabel.Visible = false;
            }

            // Tooltip
            string tip2 = !string.IsNullOrEmpty(DisplayName) ? DisplayName : ItemId;
            if (IsSupercharged) tip2 = SuperchargeIndicator + " " + tip2;
            if (!IsActivated) tip2 += " [disabled]";
            if (IsSingleItem)
                tip2 += " (Single Item)";
            else if (Amount > 0)
                tip2 += $" ({Amount}/{MaxAmount})";
            _toolTip.SetToolTip(this, tip2);
            _toolTip.SetToolTip(_iconBox, tip2);
            _toolTip.SetToolTip(_amountLabel, tip2);
            _toolTip.SetToolTip(_nameLabel, tip2);
        }
    }
}