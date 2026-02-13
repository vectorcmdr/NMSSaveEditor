using NMSSaveEditor.Data;
using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class DiscoveryPanel : UserControl
{
    private static readonly Bitmap PlaceholderIcon = new(24, 24);

    private readonly Dictionary<string, Image> _scaledIconCache = new(StringComparer.OrdinalIgnoreCase);
    private GameItemDatabase? _database;
    private IconManager? _iconManager;

    private readonly TabControl _tabControl;

    // Tab 1: Known Technologies
    private readonly DataGridView _techGrid;
    private readonly Button _addTechButton;
    private readonly Button _removeTechButton;

    // Tab 2: Known Products
    private readonly DataGridView _productGrid;
    private readonly Button _addProductButton;
    private readonly Button _removeProductButton;

    // Tab 3: Known Words
    private readonly DataGridView _wordGrid;
    private readonly Button _learnAllWordsButton;
    private readonly Button _unlearnAllWordsButton;

    // Tab 4: Known Glyphs
    private readonly CheckBox[] _glyphCheckBoxes = new CheckBox[16];
    private readonly PictureBox[] _glyphIcons = new PictureBox[16];
    private readonly Button _learnAllGlyphsButton;
    private readonly Button _unlearnAllGlyphsButton;

    // Race index mapping for Known Words columns
    private static readonly (string Name, int Index)[] RaceColumns =
    {
        ("Gek", 0),
        ("Vy'keen", 1),
        ("Korvax", 2),
        ("Atlas", 4),
        ("Autophage", 7),
    };

    public DiscoveryPanel()
    {
        SuspendLayout();

        _tabControl = new TabControl { Dock = DockStyle.Fill };

        // --- Tab 1: Known Technologies ---
        var techTab = new TabPage("Known Technologies");
        var techLayout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 1,
            RowCount = 2,
        };
        techLayout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));
        techLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));

        _techGrid = CreateItemGrid();
        techLayout.Controls.Add(_techGrid, 0, 0);

        var techButtonPanel = new FlowLayoutPanel
        {
            Dock = DockStyle.Fill,
            AutoSize = true,
            FlowDirection = FlowDirection.LeftToRight,
        };
        _addTechButton = new Button { Text = "Add Technology", AutoSize = true };
        _removeTechButton = new Button { Text = "Remove Selected", AutoSize = true };
        _addTechButton.Click += AddTech_Click;
        _removeTechButton.Click += RemoveTech_Click;
        techButtonPanel.Controls.Add(_addTechButton);
        techButtonPanel.Controls.Add(_removeTechButton);
        techLayout.Controls.Add(techButtonPanel, 0, 1);

        techTab.Controls.Add(techLayout);
        _tabControl.TabPages.Add(techTab);

        // --- Tab 2: Known Products ---
        var productTab = new TabPage("Known Products");
        var productLayout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 1,
            RowCount = 2,
        };
        productLayout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));
        productLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));

        _productGrid = CreateItemGrid();
        productLayout.Controls.Add(_productGrid, 0, 0);

        var productButtonPanel = new FlowLayoutPanel
        {
            Dock = DockStyle.Fill,
            AutoSize = true,
            FlowDirection = FlowDirection.LeftToRight,
        };
        _addProductButton = new Button { Text = "Add Product", AutoSize = true };
        _removeProductButton = new Button { Text = "Remove Selected", AutoSize = true };
        _addProductButton.Click += AddProduct_Click;
        _removeProductButton.Click += RemoveProduct_Click;
        productButtonPanel.Controls.Add(_addProductButton);
        productButtonPanel.Controls.Add(_removeProductButton);
        productLayout.Controls.Add(productButtonPanel, 0, 1);

        productTab.Controls.Add(productLayout);
        _tabControl.TabPages.Add(productTab);

        // --- Tab 3: Known Words ---
        var wordTab = new TabPage("Known Words");
        var wordLayout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 1,
            RowCount = 2,
        };
        wordLayout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));
        wordLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));

        _wordGrid = new DataGridView
        {
            Dock = DockStyle.Fill,
            AllowUserToAddRows = false,
            AllowUserToDeleteRows = false,
            AllowUserToResizeRows = false,
            AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill,
            SelectionMode = DataGridViewSelectionMode.FullRowSelect,
            RowHeadersVisible = false,
            ReadOnly = false,
        };
        _wordGrid.Columns.Add(new DataGridViewTextBoxColumn { Name = "Word", HeaderText = "Word", ReadOnly = true });
        _wordGrid.Columns.Add(new DataGridViewTextBoxColumn { Name = "WordIndex", HeaderText = "Word ID", ReadOnly = true });
        foreach (var (name, _) in RaceColumns)
        {
            _wordGrid.Columns.Add(new DataGridViewCheckBoxColumn { Name = name, HeaderText = name });
        }
        wordLayout.Controls.Add(_wordGrid, 0, 0);

        var wordButtonPanel = new FlowLayoutPanel
        {
            Dock = DockStyle.Fill,
            AutoSize = true,
            FlowDirection = FlowDirection.LeftToRight,
        };
        _learnAllWordsButton = new Button { Text = "Learn All", AutoSize = true };
        _unlearnAllWordsButton = new Button { Text = "Unlearn All", AutoSize = true };
        _learnAllWordsButton.Click += LearnAllWords_Click;
        _unlearnAllWordsButton.Click += UnlearnAllWords_Click;
        wordButtonPanel.Controls.Add(_learnAllWordsButton);
        wordButtonPanel.Controls.Add(_unlearnAllWordsButton);
        wordLayout.Controls.Add(wordButtonPanel, 0, 1);

        wordTab.Controls.Add(wordLayout);
        _tabControl.TabPages.Add(wordTab);

        // --- Tab 4: Known Glyphs ---
        var glyphTab = new TabPage("Known Glyphs");
        var glyphLayout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 1,
            RowCount = 2,
        };
        glyphLayout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));
        glyphLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));

        var glyphPanel = new FlowLayoutPanel
        {
            Dock = DockStyle.Fill,
            AutoScroll = true,
            FlowDirection = FlowDirection.LeftToRight,
            WrapContents = true,
            Padding = new Padding(10),
        };

        for (int i = 0; i < 16; i++)
        {
            var container = new FlowLayoutPanel
            {
                FlowDirection = FlowDirection.LeftToRight,
                AutoSize = true,
                Margin = new Padding(5),
            };
            _glyphIcons[i] = new PictureBox
            {
                Size = new Size(32, 32),
                SizeMode = PictureBoxSizeMode.Zoom,
                Margin = new Padding(0, 4, 4, 0),
            };
            _glyphCheckBoxes[i] = new CheckBox
            {
                Text = $"Glyph {i + 1}",
                AutoSize = true,
                Margin = new Padding(0, 8, 0, 0),
            };
            container.Controls.Add(_glyphIcons[i]);
            container.Controls.Add(_glyphCheckBoxes[i]);
            glyphPanel.Controls.Add(container);
        }
        glyphLayout.Controls.Add(glyphPanel, 0, 0);

        var glyphButtonPanel = new FlowLayoutPanel
        {
            Dock = DockStyle.Fill,
            AutoSize = true,
            FlowDirection = FlowDirection.LeftToRight,
        };
        _learnAllGlyphsButton = new Button { Text = "Learn All", AutoSize = true };
        _unlearnAllGlyphsButton = new Button { Text = "Unlearn All", AutoSize = true };
        _learnAllGlyphsButton.Click += LearnAllGlyphs_Click;
        _unlearnAllGlyphsButton.Click += UnlearnAllGlyphs_Click;
        glyphButtonPanel.Controls.Add(_learnAllGlyphsButton);
        glyphButtonPanel.Controls.Add(_unlearnAllGlyphsButton);
        glyphLayout.Controls.Add(glyphButtonPanel, 0, 1);

        glyphTab.Controls.Add(glyphLayout);
        _tabControl.TabPages.Add(glyphTab);

        Controls.Add(_tabControl);
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
        LoadGlyphIcons();
    }

    public void LoadData(JsonObject saveData)
    {
        var playerState = saveData.GetObject("PlayerStateData");
        if (playerState == null) return;

        LoadKnownItems(playerState, "KnownTech", _techGrid);
        LoadKnownItems(playerState, "KnownProducts", _productGrid);
        LoadKnownWords(playerState);
        LoadKnownGlyphs(playerState);
    }

    public void SaveData(JsonObject saveData)
    {
        var playerState = saveData.GetObject("PlayerStateData");
        if (playerState == null) return;

        SaveKnownItems(playerState, "KnownTech", _techGrid);
        SaveKnownItems(playerState, "KnownProducts", _productGrid);
        SaveKnownWords(playerState);
        SaveKnownGlyphs(playerState);
    }

    // --- Shared helpers ---

    private static DataGridView CreateItemGrid()
    {
        var grid = new DataGridView
        {
            Dock = DockStyle.Fill,
            AllowUserToAddRows = false,
            AllowUserToDeleteRows = false,
            AllowUserToResizeRows = false,
            AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill,
            SelectionMode = DataGridViewSelectionMode.FullRowSelect,
            RowHeadersVisible = false,
            ReadOnly = true,
        };
        var iconCol = new DataGridViewImageColumn
        {
            Name = "Icon",
            HeaderText = "Icon",
            Width = 30,
            ImageLayout = DataGridViewImageCellLayout.Zoom,
            AutoSizeMode = DataGridViewAutoSizeColumnMode.None,
        };
        grid.Columns.Add(iconCol);
        grid.Columns.Add(new DataGridViewTextBoxColumn { Name = "Name", HeaderText = "Name" });
        grid.Columns.Add(new DataGridViewTextBoxColumn { Name = "Category", HeaderText = "Category" });
        grid.Columns.Add(new DataGridViewTextBoxColumn { Name = "ID", HeaderText = "ID" });
        grid.RowTemplate.Height = 28;
        return grid;
    }

    private void LoadKnownItems(JsonObject playerState, string arrayName, DataGridView grid)
    {
        grid.Rows.Clear();
        var items = playerState.GetArray(arrayName);
        if (items == null) return;

        for (int i = 0; i < items.Length; i++)
        {
            string id = items.GetString(i);
            var dbItem = _database?.GetItem(id);
            string name = dbItem?.Name ?? id;
            string category = dbItem?.Category ?? "";
            Image? icon = GetScaledIcon(id);
            grid.Rows.Add(icon ?? (object)PlaceholderIcon, name, category, id);
        }
    }

    private static void SaveKnownItems(JsonObject playerState, string arrayName, DataGridView grid)
    {
        var items = playerState.GetArray(arrayName);
        if (items == null)
        {
            items = new JsonArray();
            playerState.Set(arrayName, items);
        }

        items.Clear();
        foreach (DataGridViewRow row in grid.Rows)
        {
            string id = row.Cells["ID"].Value as string ?? "";
            items.Add(id);
        }
    }

    private Image? GetScaledIcon(string itemId)
    {
        if (_iconManager == null) return null;
        if (_scaledIconCache.TryGetValue(itemId, out var cached)) return cached;

        var icon = _iconManager.GetIconForItem(itemId, _database);
        if (icon == null) return null;

        var scaled = new Bitmap(24, 24);
        using (var g = Graphics.FromImage(scaled))
        {
            g.InterpolationMode = System.Drawing.Drawing2D.InterpolationMode.HighQualityBicubic;
            g.DrawImage(icon, 0, 0, 24, 24);
        }
        _scaledIconCache[itemId] = scaled;
        return scaled;
    }

    private void AddItemToGrid(DataGridView grid)
    {
        using var dialog = new Form
        {
            Text = "Add Item",
            Size = new Size(300, 130),
            StartPosition = FormStartPosition.CenterParent,
            FormBorderStyle = FormBorderStyle.FixedDialog,
            MaximizeBox = false,
            MinimizeBox = false,
        };
        var textBox = new TextBox { Dock = DockStyle.Top };
        var okButton = new Button { Text = "OK", DialogResult = DialogResult.OK, Dock = DockStyle.Bottom };
        dialog.Controls.Add(textBox);
        dialog.Controls.Add(okButton);
        dialog.AcceptButton = okButton;

        if (dialog.ShowDialog(this) != DialogResult.OK) return;

        string id = textBox.Text.Trim();
        if (string.IsNullOrEmpty(id)) return;

        // Check for duplicates
        foreach (DataGridViewRow row in grid.Rows)
        {
            if (string.Equals(row.Cells["ID"].Value as string, id, StringComparison.OrdinalIgnoreCase))
                return;
        }

        var dbItem = _database?.GetItem(id);
        string name = dbItem?.Name ?? id;
        string category = dbItem?.Category ?? "";
        Image? icon = GetScaledIcon(id);
        grid.Rows.Add(icon ?? (object)PlaceholderIcon, name, category, id);
    }

    private static void RemoveSelectedFromGrid(DataGridView grid)
    {
        if (grid.SelectedRows.Count == 0) return;
        var indices = new List<int>();
        foreach (DataGridViewRow row in grid.SelectedRows)
            indices.Add(row.Index);
        indices.Sort();
        for (int i = indices.Count - 1; i >= 0; i--)
            grid.Rows.RemoveAt(indices[i]);
    }

    // --- Tab 1: Known Technologies events ---

    private void AddTech_Click(object? sender, EventArgs e) => AddItemToGrid(_techGrid);
    private void RemoveTech_Click(object? sender, EventArgs e) => RemoveSelectedFromGrid(_techGrid);

    // --- Tab 2: Known Products events ---

    private void AddProduct_Click(object? sender, EventArgs e) => AddItemToGrid(_productGrid);
    private void RemoveProduct_Click(object? sender, EventArgs e) => RemoveSelectedFromGrid(_productGrid);

    // --- Tab 3: Known Words ---

    private void LoadKnownWords(JsonObject playerState)
    {
        _wordGrid.Rows.Clear();
        var wordGroups = playerState.GetArray("KnownWordGroups");
        if (wordGroups == null) return;

        for (int i = 0; i < wordGroups.Length; i++)
        {
            var group = wordGroups.GetObject(i);
            string word = group.GetString("Group") ?? "";
            var races = group.GetArray("Races");

            var rowValues = new object[2 + RaceColumns.Length];
            rowValues[0] = word;
            rowValues[1] = i.ToString();
            for (int c = 0; c < RaceColumns.Length; c++)
            {
                int raceIdx = RaceColumns[c].Index;
                bool known = races != null && raceIdx < races.Length && races.GetBool(raceIdx);
                rowValues[2 + c] = known;
            }
            _wordGrid.Rows.Add(rowValues);
        }
    }

    private void SaveKnownWords(JsonObject playerState)
    {
        var wordGroups = playerState.GetArray("KnownWordGroups");
        if (wordGroups == null) return;

        for (int i = 0; i < _wordGrid.Rows.Count && i < wordGroups.Length; i++)
        {
            var group = wordGroups.GetObject(i);
            var races = group.GetArray("Races");
            if (races == null) continue;

            var row = _wordGrid.Rows[i];
            for (int c = 0; c < RaceColumns.Length; c++)
            {
                int raceIdx = RaceColumns[c].Index;
                if (raceIdx < races.Length)
                {
                    bool val = row.Cells[RaceColumns[c].Name].Value is true;
                    races.Set(raceIdx, val);
                }
            }
        }
    }

    private void SetAllWordFlags(bool value)
    {
        foreach (DataGridViewRow row in _wordGrid.Rows)
        {
            for (int c = 0; c < RaceColumns.Length; c++)
                row.Cells[RaceColumns[c].Name].Value = value;
        }
    }

    private void LearnAllWords_Click(object? sender, EventArgs e) => SetAllWordFlags(true);
    private void UnlearnAllWords_Click(object? sender, EventArgs e) => SetAllWordFlags(false);

    // --- Tab 4: Known Glyphs ---

    private void LoadGlyphIcons()
    {
        if (_iconManager == null) return;
        for (int i = 0; i < 16; i++)
        {
            string filename = $"UI-GLYPH{i + 1}.PNG";
            _glyphIcons[i].Image = _iconManager.GetIcon(filename);
        }
    }

    private void LoadKnownGlyphs(JsonObject playerState)
    {
        var glyphData = playerState.GetArray("PortalGlyphData");
        for (int i = 0; i < 16; i++)
        {
            bool known = false;
            if (glyphData != null && i < glyphData.Length)
            {
                try { known = glyphData.GetBool(i); }
                catch { /* treat non-boolean as false */ }
            }
            _glyphCheckBoxes[i].Checked = known;
        }
    }

    private void SaveKnownGlyphs(JsonObject playerState)
    {
        var glyphData = playerState.GetArray("PortalGlyphData");
        if (glyphData == null)
        {
            glyphData = new JsonArray();
            playerState.Set("PortalGlyphData", glyphData);
        }

        // Ensure the array has at least 16 entries
        while (glyphData.Length < 16)
            glyphData.Add(false);

        for (int i = 0; i < 16; i++)
            glyphData.Set(i, _glyphCheckBoxes[i].Checked);
    }

    private void SetAllGlyphs(bool value)
    {
        for (int i = 0; i < 16; i++)
            _glyphCheckBoxes[i].Checked = value;
    }

    private void LearnAllGlyphs_Click(object? sender, EventArgs e) => SetAllGlyphs(true);
    private void UnlearnAllGlyphs_Click(object? sender, EventArgs e) => SetAllGlyphs(false);
}
