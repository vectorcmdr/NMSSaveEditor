using NMSE.Data;
using NMSE.Models;

namespace NMSE.UI;

/// <summary>
/// Panel for managing player bases and storage containers.
/// Contains two inner tabbed panels: Bases and Storage.
/// </summary>
public class BasePanel : UserControl
{
    // Inner tab control with Bases and Storage sub-tabs
    private readonly TabControl _innerTabs;
    private readonly BasesSubPanel _basesSubPanel;
    private readonly ChestsSubPanel _chestsSubPanel;
    private readonly StorageSubPanel _storageSubPanel;

    public BasePanel()
    {
        SuspendLayout();

        _basesSubPanel = new BasesSubPanel { Dock = DockStyle.Fill };
        _chestsSubPanel = new ChestsSubPanel { Dock = DockStyle.Fill };
        _storageSubPanel = new StorageSubPanel { Dock = DockStyle.Fill };

        _innerTabs = new TabControl { Dock = DockStyle.Fill };
        var basesPage = new TabPage("Bases");
        basesPage.Controls.Add(_basesSubPanel);
        var chestsPage = new TabPage("Chests");
        chestsPage.Controls.Add(_chestsSubPanel);
        var storagePage = new TabPage("Storage");
        storagePage.Controls.Add(_storageSubPanel);
        _innerTabs.TabPages.Add(basesPage);
        _innerTabs.TabPages.Add(chestsPage);
        _innerTabs.TabPages.Add(storagePage);

        Controls.Add(_innerTabs);
        ResumeLayout(false);
        PerformLayout();
    }

    public void SetDatabase(GameItemDatabase? database)
    {
        _storageSubPanel.SetDatabase(database);
        _chestsSubPanel.SetDatabase(database);
    }

    public void SetIconManager(IconManager? iconManager)
    {
        _storageSubPanel.SetIconManager(iconManager);
        _chestsSubPanel.SetIconManager(iconManager);
    }

    public void LoadData(JsonObject saveData)
    {
        _basesSubPanel.LoadData(saveData);
        _storageSubPanel.LoadData(saveData);
        _chestsSubPanel.LoadData(saveData);
    }

    public void SaveData(JsonObject saveData)
    {
        _basesSubPanel.SaveData(saveData);
        _storageSubPanel.SaveData(saveData);
        _chestsSubPanel.SaveData(saveData);
    }
}

/// <summary>
/// NPC race lookup.
/// Maps NPC resource filenames to race names.
/// </summary>
internal static class NpcRace
{
    private static readonly Dictionary<string, string> RaceByFilename = new(StringComparer.OrdinalIgnoreCase)
    {
        { "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCVYKEEN.SCENE.MBIN", "Vy'keen" },
        { "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCKORVAX.SCENE.MBIN", "Korvax" },
        { "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCGEK.SCENE.MBIN", "Gek" },
        { "MODELS/COMMON/PLAYER/PLAYERCHARACTER/NPCFOURTH.SCENE.MBIN", "Fourth Race" },
        { "MODELS/PLANETS/NPCS/WARRIOR/WARRIOR.SCENE.MBIN", "Vy'keen (Old)" },
        { "MODELS/PLANETS/NPCS/EXPLORER/EXPLORERIPAD.SCENE.MBIN", "Korvax (Old)" },
        { "MODELS/PLANETS/NPCS/LOWERORDER/LOWERORDER.SCENE.MBIN", "Gek (Old)" },
        { "MODELS/PLANETS/NPCS/FOURTHRACE/FOURTHRACE.SCENE.MBIN", "Fourth Race (Old)" },
    };

    public static string Lookup(string? filename)
    {
        if (string.IsNullOrEmpty(filename)) return "";
        return RaceByFilename.TryGetValue(filename, out var race) ? race : "";
    }

    public static IReadOnlyDictionary<string, string> GetAll() => RaceByFilename;
    public static string? GetFilename(string raceName)
        => RaceByFilename.FirstOrDefault(kvp => kvp.Value == raceName).Key;
}

/// <summary>
/// Names for the five standard base NPC worker roles (indices 0–4 in NPCWorkers array).
/// </summary>
internal static class NpcWorkerNames
{
    public static string Get(int index) => index switch
    {
        0 => "Armorer",
        1 => "Farmer",
        2 => "Overseer",
        3 => "Technician",
        4 => "Scientist",
        _ => $"Worker {index}"
    };
}

/// <summary>
/// Bases sub-panel: base selector, name, items count, NPC management,
/// and backup/restore/move base computer buttons.
/// </summary>
internal class BasesSubPanel : UserControl
{
    // NPC section
    private readonly ComboBox _npcSelector;
    private readonly ComboBox _npcRaceCombo;
    private readonly TextBox _npcSeed;
    private readonly Button _generateNpcSeedBtn;

    // Base Info section
    private readonly ComboBox _baseSelector;
    private readonly TextBox _baseName;
    private readonly TextBox _baseItems;
    private string? _pendingBaseName;

    // Buttons
    private readonly Button _backupBtn;
    private readonly Button _restoreBtn;
    private readonly Button _moveBaseComputerBtn;

    // State
    private JsonObject? _playerState;
    private readonly List<NpcWorkerItem> _npcWorkers = new();
    private readonly List<BaseInfoItem> _baseInfoItems = new();
    private readonly Random _rng = new();

    public BasesSubPanel()
    {
        SuspendLayout();

        var layout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 3,
            RowCount = 11,
            Padding = new Padding(10)
        };
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize));
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100));
        layout.ColumnStyles.Add(new ColumnStyle(SizeType.AutoSize));
        for (int i = 0; i < 10; i++)
            layout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        layout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));

        int row = 0;

        // --- NPC Section ---
        var npcTitle = new Label
        {
            Text = "Base NPC",
            Font = new Font(Font.FontFamily, 11, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 4)
        };
        layout.Controls.Add(npcTitle, 0, row);
        layout.SetColumnSpan(npcTitle, 3);
        row++;

        _npcSelector = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _npcSelector.SelectedIndexChanged += OnNpcSelected;
        AddRow(layout, "Base NPC:", _npcSelector, row); row++;

        _npcRaceCombo = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        foreach (var kvp in NpcRace.GetAll())
            _npcRaceCombo.Items.Add(kvp.Value); // Display name
        _npcRaceCombo.SelectedIndexChanged += OnNpcRaceChanged;
        AddRow(layout, "Race:", _npcRaceCombo, row); row++;

        var seedPanel = new Panel { Dock = DockStyle.Fill, Height = 26 };
        _npcSeed = new TextBox { Dock = DockStyle.Fill };
        _generateNpcSeedBtn = new Button { Text = "Generate", Dock = DockStyle.Right, Width = 70 };
        _generateNpcSeedBtn.Click += OnGenerateNpcSeed;
        seedPanel.Controls.Add(_npcSeed);
        seedPanel.Controls.Add(_generateNpcSeedBtn);
        AddRow(layout, "Seed:", seedPanel, row); row++;

        // Separator
        var sep1 = new Label { AutoSize = false, Height = 8 };
        layout.Controls.Add(sep1, 0, row);
        layout.SetColumnSpan(sep1, 3);
        row++;

        // --- Base Info Section ---
        var baseTitle = new Label
        {
            Text = "Base Info",
            Font = new Font(Font.FontFamily, 11, FontStyle.Bold),
            AutoSize = true,
            Padding = new Padding(0, 0, 0, 4)
        };
        layout.Controls.Add(baseTitle, 0, row);
        layout.SetColumnSpan(baseTitle, 3);
        row++;

        _baseSelector = new ComboBox { Dock = DockStyle.Fill, DropDownStyle = ComboBoxStyle.DropDownList };
        _baseSelector.SelectedIndexChanged += OnBaseSelected;
        AddRow(layout, "Base:", _baseSelector, row); row++;

        _baseName = new TextBox { Dock = DockStyle.Fill };
        _baseName.Leave += OnBaseNameChanged;
        AddRow(layout, "Name:", _baseName, row); row++;

        _baseItems = new TextBox { Dock = DockStyle.Fill, ReadOnly = true };
        AddRow(layout, "Items:", _baseItems, row); row++;

        // Buttons panel
        var buttonPanel = new FlowLayoutPanel
        {
            Dock = DockStyle.Fill,
            AutoSize = true,
            FlowDirection = FlowDirection.LeftToRight,
            Padding = new Padding(0, 6, 0, 0)
        };
        _backupBtn = new Button { Text = "Backup", Width = 80, Enabled = false };
        _backupBtn.Click += OnBackup;
        _restoreBtn = new Button { Text = "Restore", Width = 80, Enabled = false };
        _restoreBtn.Click += OnRestore;
        _moveBaseComputerBtn = new Button { Text = "Move Base Computer", Width = 140, Enabled = false };
        _moveBaseComputerBtn.Click += OnMoveBaseComputer;
        buttonPanel.Controls.Add(_backupBtn);
        buttonPanel.Controls.Add(_restoreBtn);
        buttonPanel.Controls.Add(_moveBaseComputerBtn);
        layout.Controls.Add(buttonPanel, 0, row);
        layout.SetColumnSpan(buttonPanel, 3);
        row++;

        Controls.Add(layout);
        ResumeLayout(false);
        PerformLayout();
    }

    public void LoadData(JsonObject saveData)
    {
        _npcSelector.Items.Clear();
        _npcWorkers.Clear();
        _baseSelector.Items.Clear();
        _baseInfoItems.Clear();
        _npcSeed.Text = "";
        _baseName.Text = "";
        _baseItems.Text = "";
        _backupBtn.Enabled = false;
        _restoreBtn.Enabled = false;
        _moveBaseComputerBtn.Enabled = false;

        try
        {
            _playerState = saveData.GetObject("PlayerStateData");
            if (_playerState == null) return;

            // Load NPCWorkers (up to 5: Armorer, Farmer, Overseer, Technician, Scientist)
            var npcWorkers = _playerState.GetArray("NPCWorkers");
            if (npcWorkers != null)
            {
                for (int i = 0; i < npcWorkers.Length && i < 5; i++)
                {
                    try
                    {
                        var npc = npcWorkers.GetObject(i);
                        bool hired = false;
                        try { hired = npc.GetBool("HiredWorker"); } catch { }
                        if (hired)
                        {
                            string workerName = NpcWorkerNames.Get(i);
                            var item = new NpcWorkerItem(workerName, npc, i);
                            _npcWorkers.Add(item);
                            _npcSelector.Items.Add(item);
                        }
                    }
                    catch { }
                }
            }

            // Load PersistentPlayerBases (only HomePlanetBase with BaseVersion >= 3)
            var bases = _playerState.GetArray("PersistentPlayerBases");
            if (bases != null)
            {
                for (int i = 0; i < bases.Length; i++)
                {
                    try
                    {
                        var baseObj = bases.GetObject(i);
                        string? baseType = null;
                        try { baseType = baseObj.GetString("BaseType.PersistentBaseTypes") ?? baseObj.GetString("BaseType"); }
                        catch { try { baseType = baseObj.GetString("BaseType"); } catch { } }

                        int baseVersion = 0;
                        try { baseVersion = baseObj.GetInt("BaseVersion"); } catch { }

                        if ("HomePlanetBase".Equals(baseType, StringComparison.OrdinalIgnoreCase) && baseVersion >= 3)
                        {
                            string name = baseObj.GetString("Name") ?? $"Base {i + 1}";
                            int objectCount = 0;
                            try
                            {
                                var objects = baseObj.GetArray("Objects");
                                if (objects != null) objectCount = objects.Length;
                            }
                            catch { }

                            var item = new BaseInfoItem(name, baseObj, i, objectCount);
                            _baseInfoItems.Add(item);
                            _baseSelector.Items.Add(item);
                        }
                    }
                    catch { }
                }
            }

            if (_npcSelector.Items.Count > 0)
                _npcSelector.SelectedIndex = 0;
            if (_baseSelector.Items.Count > 0)
                _baseSelector.SelectedIndex = 0;
        }
        catch { }
    }

    public void SaveData(JsonObject saveData)
    {
        try
        {
            if (_npcSelector.SelectedItem is NpcWorkerItem npcItem && _npcRaceCombo.SelectedItem is string raceName)
            {
                string? filename = NpcRace.GetFilename(raceName);
                if (!string.IsNullOrEmpty(filename))
                {
                    var resourceElement = npcItem.Data.GetObject("ResourceElement");
                    if (resourceElement != null)
                        resourceElement.Set("Filename", filename);
                    else
                        npcItem.Data.Set("ResourceElement.Filename", filename);
                }
            }
        }
        catch { }

        // Save NPC seed changes
        try
        {
            if (_npcSelector.SelectedItem is NpcWorkerItem npcItem && !string.IsNullOrEmpty(_npcSeed.Text))
            {
                var seedArr = npcItem.Data.GetArray("ResourceElement.Seed")
                              ?? npcItem.Data.GetObject("ResourceElement")?.GetArray("Seed");
                if (seedArr != null && seedArr.Length > 1)
                    seedArr.Set(1, _npcSeed.Text);
            }
        }
        catch { }

        try
        {
            // Apply pending base name change
            if (_baseSelector.SelectedItem is BaseInfoItem item && !string.IsNullOrEmpty(_pendingBaseName))
            {
                string currentName = item.Data.GetString("Name") ?? "";
                if (_pendingBaseName != currentName)
                {
                    item.Data.Set("Name", _pendingBaseName);
                    item.DisplayName = _pendingBaseName;
                    // Update combo box display
                    int idx = _baseSelector.SelectedIndex;
                    _baseSelector.SelectedIndexChanged -= OnBaseSelected;
                    _baseSelector.Items.RemoveAt(idx);
                    _baseSelector.Items.Insert(idx, item);
                    _baseSelector.SelectedIndex = idx;
                    _baseSelector.SelectedIndexChanged += OnBaseSelected;
                }
            }
        }
        catch { }
    }

    private void OnNpcSelected(object? sender, EventArgs e)
    {
        if (_npcSelector.SelectedItem is not NpcWorkerItem item) return;
        try
        {
            // Race
            string filename = "";
            try
            {
                filename = item.Data.GetString("ResourceElement.Filename")
                           ?? item.Data.GetObject("ResourceElement")?.GetString("Filename")
                           ?? "";
            }
            catch { }
            string raceName = NpcRace.Lookup(filename);
            _npcRaceCombo.SelectedItem = raceName;

            // Seed
            string seed = "";
            try
            {
                var seedArr = item.Data.GetArray("ResourceElement.Seed")
                              ?? item.Data.GetObject("ResourceElement")?.GetArray("Seed");
                if (seedArr != null && seedArr.Length > 1)
                    seed = seedArr.Get(1)?.ToString() ?? "";
            }
            catch { }
            _npcSeed.Text = seed;
        }
        catch { }
    }

    private void OnNpcRaceChanged(object? sender, EventArgs e)
    {
        if (_npcSelector.SelectedItem is NpcWorkerItem item && _npcRaceCombo.SelectedItem is string raceName)
        {
            string? filename = NpcRace.GetFilename(raceName);
            if (!string.IsNullOrEmpty(filename))
            {
                var resourceElement = item.Data.GetObject("ResourceElement");
                if (resourceElement != null)
                    resourceElement.Set("Filename", filename);
                else
                    item.Data.Set("ResourceElement.Filename", filename);
            }
        }
    }

    private void OnGenerateNpcSeed(object? sender, EventArgs e)
    {
        byte[] bytes = new byte[8];
        _rng.NextBytes(bytes);
        string newSeed = "0x" + BitConverter.ToString(bytes).Replace("-", "");
        _npcSeed.Text = newSeed;

        // Apply immediately to the underlying data
        if (_npcSelector.SelectedItem is NpcWorkerItem item)
        {
            try
            {
                var seedArr = item.Data.GetArray("ResourceElement.Seed")
                              ?? item.Data.GetObject("ResourceElement")?.GetArray("Seed");
                if (seedArr != null && seedArr.Length > 1)
                    seedArr.Set(1, newSeed);
            }
            catch { }
        }
    }

    private void OnBaseSelected(object? sender, EventArgs e)
    {
        if (_baseSelector.SelectedItem is not BaseInfoItem item)
        {
            _baseName.Text = "";
            _baseName.Enabled = false;
            _baseItems.Text = "";
            _backupBtn.Enabled = false;
            _restoreBtn.Enabled = false;
            _moveBaseComputerBtn.Enabled = false;
            _pendingBaseName = null;
            return;
        }

        _baseName.Text = item.Data.GetString("Name") ?? "";
        _baseName.Enabled = true;
        _pendingBaseName = _baseName.Text;

        int objectCount = 0;
        try
        {
            var objects = item.Data.GetArray("Objects");
            if (objects != null) objectCount = objects.Length;
        }
        catch { }
        _baseItems.Text = objectCount.ToString();
        _backupBtn.Enabled = true;
        _restoreBtn.Enabled = true;
        _moveBaseComputerBtn.Enabled = true;
    }

    private void OnBaseNameChanged(object? sender, EventArgs e)
    {
        if (_baseSelector.SelectedItem is not BaseInfoItem item) return;
        _pendingBaseName = _baseName.Text.Trim();
    }

    private void OnBackup(object? sender, EventArgs e)
    {
        if (_baseSelector.SelectedItem is not BaseInfoItem item) return;
        try
        {
            string defaultName = item.Data.GetString("Name") ?? "Base";
            using var dialog = new SaveFileDialog
            {
                Filter = "NMS Base Backup (*.json)|*.json|All Files (*.*)|*.*",
                Title = "Backup Base",
                FileName = $"{defaultName}.json"
            };

            if (dialog.ShowDialog() == DialogResult.OK)
            {
                item.Data.ExportToFile(dialog.FileName);
                MessageBox.Show("Base backup saved successfully.", "Backup",
                    MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }
        catch (Exception ex)
        {
            MessageBox.Show($"Backup failed: {ex.Message}", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
    }

    private void OnRestore(object? sender, EventArgs e)
    {
        if (_baseSelector.SelectedItem is not BaseInfoItem item) return;
        try
        {
            using var dialog = new OpenFileDialog
            {
                Filter = "NMS Base Backup (*.json)|*.json|All Files (*.*)|*.*",
                Title = "Restore Base"
            };

            if (dialog.ShowDialog() != DialogResult.OK) return;

            var result = MessageBox.Show(
                "Are you sure you want to overwrite your existing base?",
                "Confirm Restore",
                MessageBoxButtons.YesNo, MessageBoxIcon.Warning);
            if (result != DialogResult.Yes) return;

            var imported = JsonObject.ImportFromFile(dialog.FileName);

            // Copy restored base data: primarily the Objects array
            if (imported.Contains("Objects"))
            {
                item.Data.Set("Objects", imported.Get("Objects"));
            }
            if (imported.Contains("BaseVersion"))
            {
                item.Data.Set("BaseVersion", imported.Get("BaseVersion"));
            }
            if (imported.Contains("UserData"))
            {
                item.Data.Set("UserData", imported.Get("UserData"));
            }

            // Refresh display
            OnBaseSelected(this, EventArgs.Empty);
            MessageBox.Show("Base restored successfully.", "Restore",
                MessageBoxButtons.OK, MessageBoxIcon.Information);
        }
        catch (Exception ex)
        {
            MessageBox.Show($"Restore failed: {ex.Message}", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
    }

    private void OnMoveBaseComputer(object? sender, EventArgs e)
    {
        if (_baseSelector.SelectedItem is not BaseInfoItem item) return;
        try
        {
            var objects = item.Data.GetArray("Objects");
            if (objects == null || objects.Length == 0)
            {
                MessageBox.Show(
                    "Cannot move base computer.\nPlease ensure that your base has a suitable Signal Booster / Blueprint Analyser / Beacon placed where you want your base computer to be.",
                    "Move Base Computer",
                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            // Build list of candidate objects that can swap with the base computer
            var candidates = new List<BaseObjectItem>();
            for (int i = 0; i < objects.Length; i++)
            {
                try
                {
                    var obj = objects.GetObject(i);
                    string objectId = obj.GetString("ObjectID") ?? "";
                    if (!string.IsNullOrEmpty(objectId) && objectId != "^BASE_FLAG")
                    {
                        candidates.Add(new BaseObjectItem(objectId, obj, i));
                    }
                }
                catch { }
            }

            if (candidates.Count == 0)
            {
                MessageBox.Show(
                    "Cannot move base computer.\nNo suitable objects found to swap with.",
                    "Move Base Computer",
                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            // Show selection dialog
            using var selectForm = new Form
            {
                Text = "Select Target Object",
                Size = new Size(400, 300),
                StartPosition = FormStartPosition.CenterParent,
                FormBorderStyle = FormBorderStyle.FixedDialog,
                MaximizeBox = false,
                MinimizeBox = false
            };
            var listBox = new ListBox { Dock = DockStyle.Fill };
            foreach (var c in candidates)
                listBox.Items.Add(c);
            listBox.SelectedIndex = 0;

            var okBtn = new Button { Text = "OK", DialogResult = DialogResult.OK, Dock = DockStyle.Bottom };
            selectForm.Controls.Add(listBox);
            selectForm.Controls.Add(okBtn);
            selectForm.AcceptButton = okBtn;

            if (selectForm.ShowDialog() != DialogResult.OK || listBox.SelectedItem is not BaseObjectItem target)
                return;

            // Find the base computer (^BASE_FLAG)
            JsonObject? baseFlag = null;
            for (int i = 0; i < objects.Length; i++)
            {
                try
                {
                    var obj = objects.GetObject(i);
                    if (obj.GetString("ObjectID") == "^BASE_FLAG")
                    {
                        baseFlag = obj;
                        break;
                    }
                }
                catch { }
            }

            if (baseFlag == null)
            {
                MessageBox.Show("Base computer (^BASE_FLAG) not found.", "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            // Swap positions between base computer and target object
            SwapPositions(baseFlag, target.Data);
            OnBaseSelected(this, EventArgs.Empty);
            MessageBox.Show("Base computer relocated.", "Success",
                MessageBoxButtons.OK, MessageBoxIcon.Information);
        }
        catch (Exception ex)
        {
            MessageBox.Show($"Move failed: {ex.Message}", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
    }

    private static void SwapPositions(JsonObject a, JsonObject b)
    {
        foreach (string field in new[] { "Position", "Up", "At" })
        {
            var aVal = a.Get(field);
            var bVal = b.Get(field);
            a.Set(field, bVal);
            b.Set(field, aVal);
        }
    }

    private static void AddRow(TableLayoutPanel layout, string label, Control field, int row)
    {
        var lbl = new Label { Text = label, AutoSize = true, Anchor = AnchorStyles.Left, Padding = new Padding(0, 5, 10, 0) };
        layout.Controls.Add(lbl, 0, row);
        layout.Controls.Add(field, 1, row);
    }

    private sealed class NpcWorkerItem
    {
        public string Name { get; }
        public JsonObject Data { get; }
        public int Index { get; }

        public NpcWorkerItem(string name, JsonObject data, int index)
        {
            Name = name;
            Data = data;
            Index = index;
        }

        public override string ToString() => Name;
    }

    private sealed class BaseInfoItem
    {
        public string DisplayName { get; set; }
        public JsonObject Data { get; }
        public int DataIndex { get; }
        public int ObjectCount { get; }

        public BaseInfoItem(string displayName, JsonObject data, int dataIndex, int objectCount)
        {
            DisplayName = displayName;
            Data = data;
            DataIndex = dataIndex;
            ObjectCount = objectCount;
        }

        public override string ToString() => DisplayName;
    }

    private sealed class BaseObjectItem
    {
        public string ObjectId { get; }
        public JsonObject Data { get; }
        public int Index { get; }

        public BaseObjectItem(string objectId, JsonObject data, int index)
        {
            ObjectId = objectId;
            Data = data;
            Index = index;
        }

        public override string ToString() => ObjectId;
    }
}

/// <summary>
/// Chests sub-panel: displays Chest 0–9 inventories
/// from PersistentPlayerBases, each in its own tab with an InventoryGridPanel.
/// </summary>
internal class ChestsSubPanel : UserControl
{
    private readonly TabControl _storageTabs;
    private readonly InventoryGridPanel[] _chestGrids;

    private GameItemDatabase? _database;
    private IconManager? _iconManager;

    public ChestsSubPanel()
    {
        SuspendLayout();

        var mainLayout = new TableLayoutPanel
        {
            Dock = DockStyle.Fill,
            ColumnCount = 1,
            RowCount = 2,
            Padding = new Padding(0),
        };
        mainLayout.RowStyles.Add(new RowStyle(SizeType.AutoSize));
        mainLayout.RowStyles.Add(new RowStyle(SizeType.Percent, 100));

        var warningLabel = new Label
        {
            Text = "Max size is 10 x 5 (50 slots) but internally it is actually 10 x 6. Edit the 6th row at your own risk.",
            ForeColor = Color.Red,
            AutoSize = true,
            Dock = DockStyle.Top,
            Padding = new Padding(0, 0, 0, 6)
        };
        mainLayout.Controls.Add(warningLabel, 0, 0);

        _storageTabs = new TabControl { Dock = DockStyle.Fill };
        _chestGrids = new InventoryGridPanel[10];

        for (int i = 0; i < 10; i++)
        {
            _chestGrids[i] = new InventoryGridPanel { Dock = DockStyle.Fill };
            _chestGrids[i].SetIsStorageInventory(true);
            var page = new TabPage($"Chest {i}");
            page.Controls.Add(_chestGrids[i]);
            _chestGrids[i].SetMaxSupportedLabel("");
            _storageTabs.TabPages.Add(page);
        }

        mainLayout.Controls.Add(_storageTabs, 0, 1);
        Controls.Add(mainLayout);

        ResumeLayout(false);
        PerformLayout();
    }

    public void SetDatabase(GameItemDatabase? database)
    {
        _database = database;
        for (int i = 0; i < 10; i++)
            _chestGrids[i].SetDatabase(database);
    }

    public void SetIconManager(IconManager? iconManager)
    {
        _iconManager = iconManager;
        for (int i = 0; i < 10; i++)
            _chestGrids[i].SetIconManager(iconManager);
    }

    public void LoadData(JsonObject saveData)
    {
        // Clear all grids first
        for (int i = 0; i < 10; i++)
            _chestGrids[i].LoadInventory(null);

        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            for (int i = 0; i < 10; i++)
            {
                string key = $"Chest{i + 1}Inventory";
                var chestInv = playerState.GetObject(key);
                if (chestInv != null)
                {
                    _chestGrids[i].LoadInventory(chestInv);
                }
            }
        }
        catch { }
    }

    public void SaveData(JsonObject saveData)
    {
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            for (int i = 0; i < 10; i++)
            {
                string key = $"Chest{i + 1}Inventory";
                var chestInv = playerState.GetObject(key);
                if (chestInv != null)
                {
                    _chestGrids[i].SaveInventory(chestInv);
                }
            }
        }
        catch { }
    }
}

/// <summary>
/// Storage sub-panel: Additional Storage inventories
/// from PersistentPlayerBases, each in its own tab with an InventoryGridPanel.
/// </summary>
internal class StorageSubPanel : UserControl
{
    private readonly TabControl _storageTabs;
    private readonly InventoryGridPanel _ingredientGrid;
    // New storage panels
    private readonly InventoryGridPanel _baseSalvageGrid;
    private readonly InventoryGridPanel _freighterRefundGrid;
    private readonly InventoryGridPanel _rocketGrid;
    private readonly InventoryGridPanel _fishingPlatformGrid;
    private readonly InventoryGridPanel _fishBaitGrid;
    private readonly InventoryGridPanel _foodUnitGrid;
    private readonly InventoryGridPanel _corvetteStorageGrid;

    private GameItemDatabase? _database;
    private IconManager? _iconManager;

    public StorageSubPanel()
    {
        SuspendLayout();

        _storageTabs = new TabControl { Dock = DockStyle.Fill };

        _ingredientGrid = new InventoryGridPanel { Dock = DockStyle.Fill };
        _ingredientGrid.SetIsStorageInventory(true);
        var ingredientPage = new TabPage("Ingredient Storage");
        ingredientPage.Controls.Add(_ingredientGrid);
        _ingredientGrid.SetMaxSupportedLabel("");
        _storageTabs.TabPages.Add(ingredientPage);

        // Add new storage tabs
        _corvetteStorageGrid = new InventoryGridPanel { Dock = DockStyle.Fill };
        _corvetteStorageGrid.SetIsStorageInventory(true);
        var corvetteStoragePage = new TabPage("Corvette Parts Cache");
        corvetteStoragePage.Controls.Add(_corvetteStorageGrid);
        _corvetteStorageGrid.SetMaxSupportedLabel("");
        _storageTabs.TabPages.Add(corvetteStoragePage);

        _baseSalvageGrid = new InventoryGridPanel { Dock = DockStyle.Fill };
        _baseSalvageGrid.SetIsStorageInventory(true);
        var baseSalvagePage = new TabPage("Base Salvage Capsule");
        baseSalvagePage.Controls.Add(_baseSalvageGrid);
        _baseSalvageGrid.SetMaxSupportedLabel("");
        _storageTabs.TabPages.Add(baseSalvagePage);

        _rocketGrid = new InventoryGridPanel { Dock = DockStyle.Fill };
        _rocketGrid.SetIsStorageInventory(true);
        var rocketPage = new TabPage("Rocket");
        rocketPage.Controls.Add(_rocketGrid);
        _rocketGrid.SetMaxSupportedLabel("");
        _storageTabs.TabPages.Add(rocketPage);

        _fishingPlatformGrid = new InventoryGridPanel { Dock = DockStyle.Fill };
        _fishingPlatformGrid.SetIsStorageInventory(true);
        var fishingPlatformPage = new TabPage("Fishing Platform");
        fishingPlatformPage.Controls.Add(_fishingPlatformGrid);
        _fishingPlatformGrid.SetMaxSupportedLabel("");
        _storageTabs.TabPages.Add(fishingPlatformPage);

        _fishBaitGrid = new InventoryGridPanel { Dock = DockStyle.Fill };
        _fishBaitGrid.SetIsStorageInventory(true);
        var fishBaitPage = new TabPage("Fish Bait");
        fishBaitPage.Controls.Add(_fishBaitGrid);
        _fishBaitGrid.SetMaxSupportedLabel("");
        _storageTabs.TabPages.Add(fishBaitPage);

        _foodUnitGrid = new InventoryGridPanel { Dock = DockStyle.Fill };
        _foodUnitGrid.SetIsStorageInventory(true);
        var foodUnitPage = new TabPage("Food Unit");
        foodUnitPage.Controls.Add(_foodUnitGrid);
        _foodUnitGrid.SetMaxSupportedLabel("");
        _storageTabs.TabPages.Add(foodUnitPage);

        _freighterRefundGrid = new InventoryGridPanel { Dock = DockStyle.Fill };
        _freighterRefundGrid.SetIsStorageInventory(true);

        // Add a panel with a red label above the grid for the Freighter Refund tab
        var freighterRefundPanel = new Panel { Dock = DockStyle.Fill };
        var freighterWarningLabel = new Label
        {
            Text = "This inventory is unused in the game, but present in the save data. Edit it at your own risk.",
            ForeColor = Color.Red,
            AutoSize = true,
            Dock = DockStyle.Top,
            Padding = new Padding(0, 0, 0, 6)
        };
        _freighterRefundGrid.Dock = DockStyle.Fill;
        freighterRefundPanel.Controls.Add(_freighterRefundGrid);
        freighterRefundPanel.Controls.Add(freighterWarningLabel);
        freighterRefundPanel.Controls.SetChildIndex(freighterWarningLabel, 0);
        _freighterRefundGrid.SetMaxSupportedLabel("");

        var freighterRefundPage = new TabPage("Freighter Refund (unused)");
        freighterRefundPage.Controls.Add(freighterRefundPanel);
        _storageTabs.TabPages.Add(freighterRefundPage);

        Controls.Add(_storageTabs);
        ResumeLayout(false);
        PerformLayout();
    }

    public void SetDatabase(GameItemDatabase? database)
    {
        _database = database;
        _ingredientGrid.SetDatabase(database);
        _baseSalvageGrid.SetDatabase(database);
        _freighterRefundGrid.SetDatabase(database);
        _rocketGrid.SetDatabase(database);
        _fishingPlatformGrid.SetDatabase(database);
        _fishBaitGrid.SetDatabase(database);
        _foodUnitGrid.SetDatabase(database);
        _corvetteStorageGrid.SetDatabase(database);
    }

    public void SetIconManager(IconManager? iconManager)
    {
        _iconManager = iconManager;
        _ingredientGrid.SetIconManager(iconManager);
        _baseSalvageGrid.SetIconManager(iconManager);
        _freighterRefundGrid.SetIconManager(iconManager);
        _rocketGrid.SetIconManager(iconManager);
        _fishingPlatformGrid.SetIconManager(iconManager);
        _fishBaitGrid.SetIconManager(iconManager);
        _foodUnitGrid.SetIconManager(iconManager);
        _corvetteStorageGrid.SetIconManager(iconManager);
    }

    public void LoadData(JsonObject saveData)
    {
        // Clear all grids first
        _ingredientGrid.LoadInventory(null);
        _baseSalvageGrid.LoadInventory(null);
        _freighterRefundGrid.LoadInventory(null);
        _rocketGrid.LoadInventory(null);
        _fishingPlatformGrid.LoadInventory(null);
        _fishBaitGrid.LoadInventory(null);
        _foodUnitGrid.LoadInventory(null);
        _corvetteStorageGrid.LoadInventory(null);

        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            var cookingInv = playerState.GetObject("CookingIngredientsInventory");
            if (cookingInv != null)
            {
                _ingredientGrid.LoadInventory(cookingInv);
            }

            // Load new storage inventories
            var baseSalvageInv = playerState.GetObject("ChestMagicInventory");
            if (baseSalvageInv != null)
                _baseSalvageGrid.LoadInventory(baseSalvageInv);

            var freighterRefundInv = playerState.GetObject("ChestMagic2Inventory");
            if (freighterRefundInv != null)
                _freighterRefundGrid.LoadInventory(freighterRefundInv);

            var rocketInv = playerState.GetObject("RocketLockerInventory");
            if (rocketInv != null)
                _rocketGrid.LoadInventory(rocketInv);

            var fishingPlatformInv = playerState.GetObject("FishPlatformInventory");
            if (fishingPlatformInv != null)
                _fishingPlatformGrid.LoadInventory(fishingPlatformInv);

            var fishBaitInv = playerState.GetObject("FishBaitBoxInventory");
            if (fishBaitInv != null)
                _fishBaitGrid.LoadInventory(fishBaitInv);

            var foodUnitInv = playerState.GetObject("FoodUnitInventory");
            if (foodUnitInv != null)
                _foodUnitGrid.LoadInventory(foodUnitInv);

            var corvetteStorageInv = playerState.GetObject("CorvetteStorageInventory");
            if (corvetteStorageInv != null)
                _corvetteStorageGrid.LoadInventory(corvetteStorageInv);
        }
        catch { }
    }

    public void SaveData(JsonObject saveData)
    {
        try
        {
            var playerState = saveData.GetObject("PlayerStateData");
            if (playerState == null) return;

            var cookingInv = playerState.GetObject("CookingIngredientsInventory");
            if (cookingInv != null)
                _ingredientGrid.SaveInventory(cookingInv);

            var baseSalvageInv = playerState.GetObject("ChestMagicInventory");
            if (baseSalvageInv != null)
                _baseSalvageGrid.SaveInventory(baseSalvageInv);

            var freighterRefundInv = playerState.GetObject("ChestMagic2Inventory");
            if (freighterRefundInv != null)
                _freighterRefundGrid.SaveInventory(freighterRefundInv);

            var rocketInv = playerState.GetObject("RocketLockerInventory");
            if (rocketInv != null)
                _rocketGrid.SaveInventory(rocketInv);

            var fishingPlatformInv = playerState.GetObject("FishingPlatformInventory");
            if (fishingPlatformInv != null)
                _fishingPlatformGrid.SaveInventory(fishingPlatformInv);

            var fishBaitInv = playerState.GetObject("FishBaitInventory");
            if (fishBaitInv != null)
                _fishBaitGrid.SaveInventory(fishBaitInv);

            var foodUnitInv = playerState.GetObject("FoodUnitInventory");
            if (foodUnitInv != null)
                _foodUnitGrid.SaveInventory(foodUnitInv);

            var corvetteStorageInv = playerState.GetObject("CorvetteStorageInventory");
            if (corvetteStorageInv != null)
                _corvetteStorageGrid.SaveInventory(corvetteStorageInv);
        }
        catch { }
    }
}