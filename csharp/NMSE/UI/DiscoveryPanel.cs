using NMSE.Data;
using NMSE.Models;

namespace NMSE.UI;

public class DiscoveryPanel : UserControl
{
    private static readonly Bitmap PlaceholderIcon = new(24, 24);

    private readonly Dictionary<string, Image> _scaledIconCache = new(StringComparer.OrdinalIgnoreCase);
    private GameItemDatabase? _database;
    private IconManager? _iconManager;
    private WordDatabase? _wordDatabase;

    // Reference to save data's KnownWordGroups for word state operations
    private JsonArray? _knownWordGroups;

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
    private PictureBox[] _raceIcons = Array.Empty<PictureBox>();
    private Label[] _raceLabels = Array.Empty<Label>();

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
        ("Autophage", 8),
    };

    // Maps race prefix patterns to race index (used for fallback when no WordDatabase)
    private static readonly (string Prefix, int RaceIndex)[] RacePrefixes =
    {
        ("^TRA_", 0),    // Gek (Traders)
        ("^WAR_", 1),    // Vy'keen (Warriors)
        ("^EXP_", 2),    // Korvax (Explorers)
        ("^ATLAS_", 4),  // Atlas
        ("^ROBOT_", 3),  // Robot
        ("^AUTO_", 8),   // Autophage
    };

    // Total race count in the Races boolean array (matches Java eU enum length)
    private const int TotalRaceCount = 9;

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
            RowCount = 3,
        };
        wordLayout.RowStyles.Add(new RowStyle(SizeType.Absolute, 70));
        wordLayout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));
        wordLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));

        // Race icons header panel - uses absolute positioning to align over grid columns
        var raceIconPanel = new Panel
        {
            Dock = DockStyle.Fill,
            Height = 70,
        };
        string[] raceIconFiles = { "UI-GEK.PNG", "UI-VYKEEN.PNG", "UI-KORVAX.PNG", "UI-GEK.PNG", "UI-KORVAX.PNG" };
        string[] raceLabels = { "Gek", "Vy'keen", "Korvax", "Atlas", "Autophage" };
        _raceIcons = new PictureBox[raceLabels.Length];
        _raceLabels = new Label[raceLabels.Length];
        for (int i = 0; i < raceLabels.Length; i++)
        {
            _raceIcons[i] = new PictureBox
            {
                Size = new Size(32, 32),
                SizeMode = PictureBoxSizeMode.Zoom,
            };
            _raceLabels[i] = new Label
            {
                Text = raceLabels[i],
                AutoSize = true,
                Font = new Font(Font.FontFamily, 9, FontStyle.Bold),
            };
            raceIconPanel.Controls.Add(_raceIcons[i]);
            raceIconPanel.Controls.Add(_raceLabels[i]);
        }
        wordLayout.Controls.Add(raceIconPanel, 0, 0);

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
        _wordGrid.Columns.Add(new DataGridViewTextBoxColumn { Name = "Word", HeaderText = "Word", ReadOnly = true, FillWeight = 40 });
        _wordGrid.Columns.Add(new DataGridViewTextBoxColumn { Name = "IndvWordId", HeaderText = "Indv Word ID", ReadOnly = true, FillWeight = 40 });
        foreach (var (name, _) in RaceColumns)
        {
            _wordGrid.Columns.Add(new DataGridViewCheckBoxColumn { Name = name, HeaderText = name, FillWeight = 20 });
        }
        // Align race icons over their column headers when layout changes
        _wordGrid.Layout += (_, _) => AlignRaceIcons();
        _wordGrid.ColumnWidthChanged += (_, _) => AlignRaceIcons();
        wordLayout.Controls.Add(_wordGrid, 0, 1);

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
        wordLayout.Controls.Add(wordButtonPanel, 0, 2);

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

        // 4×4 grid layout for glyphs
        var glyphGrid = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 4,
            RowCount = 4,
            Padding = new Padding(20),
        };
        for (int c = 0; c < 4; c++)
            glyphGrid.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 25));
        for (int r = 0; r < 4; r++)
            glyphGrid.RowStyles.Add(new RowStyle(SizeType.Percent, 25));

        for (int i = 0; i < 16; i++)
        {
            var container = new FlowLayoutPanel
            {
                FlowDirection = FlowDirection.TopDown,
                AutoSize = true,
                Anchor = AnchorStyles.None,
                Margin = new Padding(5),
            };
            _glyphIcons[i] = new PictureBox
            {
                Size = new Size(64, 64),
                SizeMode = PictureBoxSizeMode.Zoom,
                Margin = new Padding(4, 4, 4, 2),
            };
            _glyphCheckBoxes[i] = new CheckBox
            {
                Text = $"Glyph {i + 1}",
                AutoSize = true,
                Margin = new Padding(8, 0, 0, 0),
            };
            container.Controls.Add(_glyphIcons[i]);
            container.Controls.Add(_glyphCheckBoxes[i]);
            int row = i / 4;
            int col = i % 4;
            glyphGrid.Controls.Add(container, col, row);
        }
        glyphLayout.Controls.Add(glyphGrid, 0, 0);

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

    public void SetWordDatabase(WordDatabase? wordDatabase)
    {
        _wordDatabase = wordDatabase;
    }

    public void SetIconManager(IconManager? iconManager)
    {
        _iconManager = iconManager;
        LoadGlyphIcons();
        LoadRaceIcons();
    }

    private void LoadRaceIcons()
    {
        if (_iconManager == null || _raceIcons.Length == 0) return;
        string[] raceIconFiles = { "UI-GEK.PNG", "UI-VYKEEN.PNG", "UI-KORVAX.PNG", "UI-GEK.PNG", "UI-KORVAX.PNG" };
        for (int i = 0; i < _raceIcons.Length && i < raceIconFiles.Length; i++)
        {
            var icon = _iconManager.GetIcon(raceIconFiles[i]);
            if (icon != null)
                _raceIcons[i].Image = icon;
        }
    }

    /// <summary>
    /// Align race icons and labels over their corresponding DataGridView column headers.
    /// </summary>
    private void AlignRaceIcons()
    {
        if (_wordGrid == null || _raceIcons.Length == 0) return;

        // Column order: Word, IndvWordId, Gek, Vy'keen, Korvax, Atlas, Autophage
        // Race columns start at index 2
        for (int i = 0; i < _raceIcons.Length && i < _raceLabels.Length; i++)
        {
            int colIdx = i + 2; // Skip Word and IndvWordId columns
            if (colIdx >= _wordGrid.Columns.Count) break;

            var rect = _wordGrid.GetColumnDisplayRectangle(colIdx, true);
            if (rect.Width == 0) continue;

            int centerX = _wordGrid.Left + rect.Left + (rect.Width / 2);

            // Position icon centered above column
            _raceIcons[i].Left = centerX - _raceIcons[i].Width / 2;
            _raceIcons[i].Top = 2;

            // Position label below icon, also centered
            _raceLabels[i].Left = centerX - _raceLabels[i].Width / 2;
            _raceLabels[i].Top = 36;
        }
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

    /// <summary>
    /// Checks if a word group is known for a specific race in KnownWordGroups.
    /// Matches Java gz.d(String, int) method.
    /// </summary>
    private bool IsWordKnown(string groupName, int raceOrdinal)
    {
        if (_knownWordGroups == null) return false;
        for (int i = 0; i < _knownWordGroups.Length; i++)
        {
            var entry = _knownWordGroups.GetObject(i);
            if (entry != null && groupName.Equals(entry.GetString("Group"), StringComparison.Ordinal))
            {
                var races = entry.GetArray("Races");
                return races != null && raceOrdinal < races.Length && races.GetBool(raceOrdinal);
            }
        }
        return false;
    }

    /// <summary>
    /// Sets a word group's known state for a specific race in KnownWordGroups.
    /// Matches Java gz.a(String, int, boolean) method.
    /// Creates entries as needed, removes entries with no known races.
    /// </summary>
    private void SetWordKnown(string groupName, int raceOrdinal, bool known)
    {
        if (_knownWordGroups == null) return;

        // Look for existing entry
        for (int i = 0; i < _knownWordGroups.Length; i++)
        {
            var entry = _knownWordGroups.GetObject(i);
            if (entry != null && groupName.Equals(entry.GetString("Group"), StringComparison.Ordinal))
            {
                var races = entry.GetArray("Races");
                if (races == null) return;

                // Ensure array is large enough
                for (int ri = races.Length; ri < TotalRaceCount; ri++)
                    races.Add(false);

                races.Set(raceOrdinal, known);

                // If no race is true, remove the entry entirely
                bool anyKnown = false;
                for (int r = 0; r < races.Length; r++)
                {
                    if (races.GetBool(r)) { anyKnown = true; break; }
                }
                if (!anyKnown)
                    _knownWordGroups.RemoveAt(i);

                return;
            }
        }

        // Entry doesn't exist - create it if setting to true
        if (known)
        {
            var newEntry = new JsonObject();
            newEntry.Set("Group", groupName);
            var races = new JsonArray();
            for (int r = 0; r < TotalRaceCount; r++)
                races.Add(false);
            races.Set(raceOrdinal, true);
            newEntry.Set("Races", races);
            _knownWordGroups.Add(newEntry);
        }
    }

    private void LoadKnownWords(JsonObject playerState)
    {
        _wordGrid.Rows.Clear();
        _wordGrid.CellValueChanged -= WordGrid_CellValueChanged;

        _knownWordGroups = playerState.GetArray("KnownWordGroups");
        if (_knownWordGroups == null)
        {
            _knownWordGroups = new JsonArray();
            playerState.Set("KnownWordGroups", _knownWordGroups);
        }

        if (_wordDatabase == null || _wordDatabase.Words.Count == 0) return;

        // Show ALL possible words from the database
        foreach (var word in _wordDatabase.Words)
        {
            var rowValues = new object[2 + RaceColumns.Length];
            rowValues[0] = word.Text;
            rowValues[1] = word.Id;
            for (int c = 0; c < RaceColumns.Length; c++)
            {
                int raceOrdinal = RaceColumns[c].Index;
                string? groupForRace = word.GetGroupForRace(raceOrdinal);
                bool known = groupForRace != null && IsWordKnown(groupForRace, raceOrdinal);
                rowValues[2 + c] = known;
            }
            int rowIdx = _wordGrid.Rows.Add(rowValues);

            // Make non-applicable race cells read-only (word doesn't have a group for that race)
            var row = _wordGrid.Rows[rowIdx];
            for (int c = 0; c < RaceColumns.Length; c++)
            {
                int raceOrdinal = RaceColumns[c].Index;
                bool hasGroup = word.HasRace(raceOrdinal);
                row.Cells[RaceColumns[c].Name].ReadOnly = !hasGroup;
                if (!hasGroup)
                {
                    row.Cells[RaceColumns[c].Name].Style.BackColor = Color.FromArgb(240, 240, 240);
                    row.Cells[RaceColumns[c].Name].Style.ForeColor = Color.LightGray;
                }
            }

            // Store the word entry reference in the row's Tag for quick lookup
            row.Tag = word;
        }

        _wordGrid.CellValueChanged += WordGrid_CellValueChanged;
    }

    /// <summary>
    /// When a race checkbox is toggled, immediately update KnownWordGroups in the save data.
    /// This matches the Java app behavior where changes are written immediately.
    /// </summary>
    private void WordGrid_CellValueChanged(object? sender, DataGridViewCellEventArgs e)
    {
        if (e.RowIndex < 0 || e.ColumnIndex < 2) return;

        var row = _wordGrid.Rows[e.RowIndex];
        if (row.Tag is not WordEntry word) return;

        int colOffset = e.ColumnIndex - 2;
        if (colOffset < 0 || colOffset >= RaceColumns.Length) return;

        int raceOrdinal = RaceColumns[colOffset].Index;
        string? groupName = word.GetGroupForRace(raceOrdinal);
        if (groupName == null) return;

        bool value = row.Cells[e.ColumnIndex].Value is true;
        SetWordKnown(groupName, raceOrdinal, value);
    }

    private void SaveKnownWords(JsonObject playerState)
    {
        // Changes are written immediately via WordGrid_CellValueChanged,
        // but ensure the reference is set
        if (_knownWordGroups != null)
            playerState.Set("KnownWordGroups", _knownWordGroups);
    }

    private void SetAllWordFlags(bool value)
    {
        _wordGrid.CellValueChanged -= WordGrid_CellValueChanged;
        try
        {
            foreach (DataGridViewRow row in _wordGrid.Rows)
            {
                if (row.Tag is not WordEntry word) continue;
                for (int c = 0; c < RaceColumns.Length; c++)
                {
                    int raceOrdinal = RaceColumns[c].Index;
                    string? groupName = word.GetGroupForRace(raceOrdinal);
                    if (groupName != null)
                    {
                        row.Cells[RaceColumns[c].Name].Value = value;
                        SetWordKnown(groupName, raceOrdinal, value);
                    }
                }
            }
        }
        finally
        {
            _wordGrid.CellValueChanged += WordGrid_CellValueChanged;
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
            var icon = _iconManager.GetIcon(filename);
            if (icon != null)
            {
                // Dispose previous image to prevent GDI resource leaks
                _glyphIcons[i].Image?.Dispose();

                // Draw glyph icon on dark grey circle background for visibility
                int size = 64;
                var composite = new Bitmap(size, size);
                using (var g = Graphics.FromImage(composite))
                {
                    g.SmoothingMode = System.Drawing.Drawing2D.SmoothingMode.AntiAlias;
                    // Draw dark grey filled circle
                    using var brush = new SolidBrush(Color.FromArgb(60, 60, 60));
                    g.FillEllipse(brush, 0, 0, size - 1, size - 1);
                    // Draw the glyph icon centered on the circle
                    int iconSize = 48;
                    int offset = (size - iconSize) / 2;
                    g.InterpolationMode = System.Drawing.Drawing2D.InterpolationMode.HighQualityBicubic;
                    g.DrawImage(icon, offset, offset, iconSize, iconSize);
                }
                _glyphIcons[i].Image = composite;
                _glyphIcons[i].Size = new Size(size, size);
            }
        }
    }

    private void LoadKnownGlyphs(JsonObject playerState)
    {
        // Java stores KnownPortalRunes as a single integer bitfield (each bit = one glyph)
        int runesBitfield = 0;
        try
        {
            var val = playerState.Get("KnownPortalRunes");
            if (val is int i) runesBitfield = i;
            else if (val is long l) runesBitfield = (int)l;
            else if (val != null) runesBitfield = Convert.ToInt32(val);
        }
        catch { }

        for (int i = 0; i < 16; i++)
        {
            int mask = 1 << i;
            _glyphCheckBoxes[i].Checked = (runesBitfield & mask) == mask;
        }
    }

    private void SaveKnownGlyphs(JsonObject playerState)
    {
        // Write back as integer bitfield (matching Java format)
        int runesBitfield = 0;
        for (int i = 0; i < 16; i++)
        {
            if (_glyphCheckBoxes[i].Checked)
                runesBitfield |= (1 << i);
        }
        playerState.Set("KnownPortalRunes", runesBitfield);
    }

    private void SetAllGlyphs(bool value)
    {
        for (int i = 0; i < 16; i++)
            _glyphCheckBoxes[i].Checked = value;
    }

    private void LearnAllGlyphs_Click(object? sender, EventArgs e) => SetAllGlyphs(true);
    private void UnlearnAllGlyphs_Click(object? sender, EventArgs e) => SetAllGlyphs(false);
}
