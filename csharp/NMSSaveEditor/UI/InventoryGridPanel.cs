using System.ComponentModel;
using NMSSaveEditor.Data;
using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

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
    private const int CellSize = 56;
    private const int CellPadding = 2;

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

    // Context menu
    private readonly ContextMenuStrip _cellContextMenu;
    private readonly ToolStripMenuItem _addItemMenuItem;
    private readonly ToolStripMenuItem _removeItemMenuItem;

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
            Panel2MinSize = 280
        };
        // Set SplitterDistance after the control is sized so Panel2 gets a proper initial width
        splitContainer.SizeChanged += (s, e) =>
        {
            if (splitContainer.Width > 300 && splitContainer.SplitterDistance > splitContainer.Width - 290)
                splitContainer.SplitterDistance = splitContainer.Width - 290;
        };

        // Left: grid of slot cells
        _gridContainer = new Panel
        {
            Dock = DockStyle.Fill,
            AutoScroll = true,
            BackColor = Color.FromArgb(30, 30, 30)
        };
        splitContainer.Panel1.Controls.Add(_gridContainer);

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

        // Icon preview
        _detailIcon = new PictureBox
        {
            Size = new Size(64, 64),
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
        _cellContextMenu.Items.Add(_addItemMenuItem);
        _cellContextMenu.Items.Add(_removeItemMenuItem);
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
        if (selectedItem.ItemType.Equals("substance", StringComparison.OrdinalIgnoreCase) ||
            selectedItem.ItemType.Equals("product", StringComparison.OrdinalIgnoreCase))
        {
            int maxStack = _database?.GetStackSize(selectedItem.Id, "normal") ?? 250;
            if (maxStack <= 0) maxStack = 250;
            _detailAmount.Value = maxStack;
            _detailMaxAmount.Value = maxStack;
        }
        else
        {
            // Technology
            _detailAmount.Value = 1;
            _detailMaxAmount.Value = 1;
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
                var cell = new SlotCell(col, r, CellSize);
                cell.Location = new Point(
                    col * (CellSize + CellPadding) + CellPadding,
                    r * (CellSize + CellPadding) + CellPadding
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
                }
                else
                {
                    cell.IsEmpty = true;
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

    private void LoadCellData(SlotCell cell)
    {
        if (cell.SlotData == null) return;

        string itemId = "";
        try
        {
            var idObj = cell.SlotData.GetObject("Id");
            if (idObj != null)
                itemId = idObj.GetString("Id") ?? "";
            else
                itemId = cell.SlotData.GetString("Id") ?? "";
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

        // Check if slot has an actual item or is empty-with-position
        if (string.IsNullOrEmpty(itemId) || itemId == "^" || itemId == "^YOURSLOTITEM")
        {
            cell.IsValidEmpty = true;
            cell.SlotData = cell.SlotData; // Keep slot data for position
            return;
        }

        // Look up display name and type from database
        if (_database != null && !string.IsNullOrEmpty(itemId))
        {
            var gameItem = _database.GetItem(itemId) ?? _database.GetItem("^" + itemId);
            if (gameItem != null)
            {
                cell.DisplayName = gameItem.Name;
                cell.ItemType = gameItem.ItemType;
                cell.Category = gameItem.Category;

                if (_iconManager != null && !string.IsNullOrEmpty(gameItem.Icon))
                    cell.IconImage = _iconManager.GetIcon(gameItem.Icon);
            }
        }

        cell.UpdateDisplay();
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

        cell.MouseUp += Handler;
        foreach (Control child in cell.Controls)
            child.MouseUp += Handler;
    }

    /// <summary>
    /// Configure context menu item visibility based on the cell state.
    /// </summary>
    private void ConfigureContextMenuItems(SlotCell cell)
    {
        bool hasItem = cell.SlotData != null && !string.IsNullOrEmpty(cell.ItemId) && !cell.IsValidEmpty;
        bool canAdd = cell.IsValidEmpty || (!cell.IsEmpty);

        _addItemMenuItem.Visible = canAdd;
        _addItemMenuItem.Text = hasItem ? "Replace Item" : "Add Item";
        _removeItemMenuItem.Visible = hasItem;
    }

    private void OnContextMenuOpening(object? sender, CancelEventArgs e)
    {
        // Cell already tracked by AttachRightClickHandler / ConfigureContextMenuItems
        if (_contextCell == null)
        {
            e.Cancel = true;
            return;
        }

        // Cancel if there's nothing useful to show
        if (!_addItemMenuItem.Visible && !_removeItemMenuItem.Visible)
            e.Cancel = true;
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
        _detailAmount.Value = Math.Clamp(cell.Amount, (int)_detailAmount.Minimum, (int)_detailAmount.Maximum);
        _detailMaxAmount.Value = Math.Clamp(cell.MaxAmount, (int)_detailMaxAmount.Minimum, (int)_detailMaxAmount.Maximum);
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

        // Update DamageFactor
        try { slot.Set("DamageFactor", (double)_detailDamageFactor.Value); } catch { }

        // Update inventory type based on item
        if (_database != null && !string.IsNullOrEmpty(newItemId))
        {
            var gameItem = _database.GetItem(newItemId) ?? _database.GetItem("^" + newItemId);
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

        // Refresh cell display
        _selectedCell.ItemId = newItemId;
        _selectedCell.Amount = (int)_detailAmount.Value;
        _selectedCell.MaxAmount = (int)_detailMaxAmount.Value;
        _selectedCell.DamageFactor = (double)_detailDamageFactor.Value;
        _selectedCell.IsValidEmpty = false;

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

                if (_iconManager != null && !string.IsNullOrEmpty(gameItem.Icon))
                {
                    _selectedCell.IconImage = _iconManager.GetIcon(gameItem.Icon);
                    _detailIcon.Image = _selectedCell.IconImage;
                }
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

        int amount = (int)_detailAmount.Value;
        int maxAmount = (int)_detailMaxAmount.Value;

        if (amount <= 0) amount = 1;
        if (maxAmount <= 0) maxAmount = amount;

        // Determine inventory type
        string invType = "Product";
        if (_database != null)
        {
            var gameItem = _database.GetItem(itemId) ?? _database.GetItem("^" + itemId);
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
    /// Individual slot cell in the inventory grid.
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
        public double DamageFactor { get; set; }
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public bool IsEmpty { get; set; }
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public bool IsValidEmpty { get; set; }
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

            Controls.Add(_amountLabel);
            Controls.Add(_iconBox);
            Controls.Add(_nameLabel);

            // Forward child clicks to this panel for cell selection
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

            if (IsValidEmpty && string.IsNullOrEmpty(ItemId))
            {
                // Valid empty slot - available for adding items
                BackColor = _isSelected ? Color.FromArgb(70, 100, 160) : Color.FromArgb(45, 45, 50);
                _iconBox.Image = null;
                _nameLabel.Text = "";
                _nameLabel.Visible = false;
                _amountLabel.Text = "";
                _amountLabel.Visible = false;
                Cursor = Cursors.Hand;
                string tip = $"Empty slot ({GridX}, {GridY}) - Right-click to add item";
                _toolTip.SetToolTip(this, tip);
                _toolTip.SetToolTip(_iconBox, tip);
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

            // Tooltip
            string tip2 = !string.IsNullOrEmpty(DisplayName) ? DisplayName : ItemId;
            if (Amount > 0)
                tip2 += $" ({Amount}/{MaxAmount})";
            _toolTip.SetToolTip(this, tip2);
            _toolTip.SetToolTip(_iconBox, tip2);
            _toolTip.SetToolTip(_amountLabel, tip2);
            _toolTip.SetToolTip(_nameLabel, tip2);
        }
    }
}
