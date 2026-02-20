using NMSE.Config;
using NMSE.Data;
using NMSE.IO;
using NMSE.Models;

namespace NMSE.UI;

public partial class MainFormResources : Form
{
    public const string AppName = "NMSE (NO MAN'S SAVE EDITOR)";
    public const string VerMajor = "1";
    public const string VerMinor = "0";
    public const string SuppGameRel = "6.20 Remanant";
    public const string IconResource = "NMSE.Resources.app.NMSE.ico";
    public const string GitHubUrl = "https://github.com/vectorcmdr/NMSE";
    public const string SponsorUrl = "https://github.com/sponsors/vectorcmdr";
    public const string GitHubCreatorUrl = "https://github.com/vectorcmdr";

    // Strips + buttons
    private readonly MenuStrip _menuStrip;
    private readonly ToolStrip _toolStrip;
    private readonly StatusStrip _statusStrip;
    private readonly TabControl _tabControl;
    private readonly ToolStripStatusLabel _statusLabel;
    private ToolStripComboBox _directoryCombo;
    private ToolStripComboBox _saveSlotCombo;
    private readonly ToolStripButton _loadButton;
    private readonly ToolStripButton _saveButton;

    // Tab panels
    private readonly MainStatsPanel _mainStatsPanel;
    private readonly ExosuitPanel _exosuitPanel;
    private readonly MultitoolPanel _multitoolPanel;
    private readonly StarshipPanel _shipPanel;
    private readonly FreighterPanel _freighterPanel;
    private readonly FrigatePanel _frigatePanel;
    private readonly VehiclePanel _vehiclePanel;
    private readonly CompanionPanel _companionPanel;
    private readonly BasePanel _basePanel;
    private readonly DiscoveryPanel _discoveryPanel;
    private readonly MilestonePanel _milestonePanel;
    private readonly SettlementPanel _settlementPanel;
    private readonly AccountPanel _accountPanel;
    private readonly RawJsonPanel _rawJsonPanel;

    // Data
    private readonly GameItemDatabase _database = new();
    private IconManager? _iconManager;
    private List<string> _saveSlotFiles = new();
    private JsonObject? _currentSaveData;
    private string? _currentFilePath;

    public MainFormResources()
    {
        SuspendLayout();

        // Initialize components
        _menuStrip = new MenuStrip();
        _toolStrip = new ToolStrip();
        _statusStrip = new StatusStrip();
        _tabControl = new TabControl();
        _statusLabel = new ToolStripStatusLabel("Ready");
        _directoryCombo = new ToolStripComboBox { AutoSize = false, Width = 448 };
        _saveSlotCombo = new ToolStripComboBox { AutoSize = false, Width = 164 };
        _loadButton = new ToolStripButton("Load");
        _saveButton = new ToolStripButton("Save") { Enabled = false };

        // Create panels
        _mainStatsPanel = new MainStatsPanel();
        _exosuitPanel = new ExosuitPanel();
        _multitoolPanel = new MultitoolPanel();
        _shipPanel = new StarshipPanel();
        _freighterPanel = new FreighterPanel();
        _frigatePanel = new FrigatePanel();
        _vehiclePanel = new VehiclePanel();
        _companionPanel = new CompanionPanel();
        _basePanel = new BasePanel();
        _discoveryPanel = new DiscoveryPanel();
        _milestonePanel = new MilestonePanel();
        _settlementPanel = new SettlementPanel();
        _accountPanel = new AccountPanel();
        _rawJsonPanel = new RawJsonPanel();

        InitializeForm();
        InitializeMenus();
        InitializeToolbar();
        InitializeStatusBar();
        InitializeTabs();

        ResumeLayout(false);
        PerformLayout();

        LoadConfig();
        LoadDatabase();
        PopulateSaveSlots();
    }

    private void InitializeForm()
    {
        AutoScaleMode = AutoScaleMode.Font;
        Text = $"{AppName} - Build {VerMajor}.{VerMinor}.{Build} ({SuppGameRel})";
        ClientSize = new Size(1200, 800);
        StartPosition = FormStartPosition.CenterScreen;
        MinimumSize = new Size(800, 600);
        FormClosing += OnFormClosing;

        try
        {
            var asm = typeof(MainFormResources).Assembly;
            // Manifest resource name: <DefaultNamespace>.Resources.app.NMSE.ico
            using var stream = asm.GetManifestResourceStream(IconResource);
            if (stream != null)
            {
                Icon = new Icon(stream);
            }
        }
        catch
        {
            // Ignore — icon is non-critical
        }

        // Set dock styles before adding controls
        _tabControl.Dock = DockStyle.Fill;

        // Add controls in proper z-order for WinForms docking engine.
        // Controls are processed in reverse z-order (last added = back = processed first).
        // Order: TabControl (Fill, front) -> ToolStrip (Top) -> MenuStrip (Top) -> StatusStrip (Bottom, back)
        Controls.Add(_tabControl);
        Controls.Add(_toolStrip);
        Controls.Add(_menuStrip);
        Controls.Add(_statusStrip);
        MainMenuStrip = _menuStrip;
    }

    private void InitializeMenus()
    {
        // File menu
        var fileMenu = new ToolStripMenuItem("&File");
        fileMenu.DropDownItems.Add(new ToolStripMenuItem("&Open Save Directory...", null, OnOpenDirectory, Keys.Control | Keys.O));
        fileMenu.DropDownItems.Add(new ToolStripMenuItem("&Load Save File...", null, OnLoadFile, Keys.Control | Keys.L));
        fileMenu.DropDownItems.Add(new ToolStripSeparator());
        fileMenu.DropDownItems.Add(new ToolStripMenuItem("&Save", null, OnSave, Keys.Control | Keys.S) { Enabled = false });
        fileMenu.DropDownItems.Add(new ToolStripMenuItem("Save &As...", null, OnSaveAs, Keys.Control | Keys.Shift | Keys.S) { Enabled = false });
        fileMenu.DropDownItems.Add(new ToolStripSeparator());
        fileMenu.DropDownItems.Add(new ToolStripMenuItem("E&xit", null, (_, _) => Close(), Keys.Alt | Keys.F4));
        _menuStrip.Items.Add(fileMenu);

        // Edit menu
        var editMenu = new ToolStripMenuItem("&Edit");
        editMenu.DropDownItems.Add(new ToolStripMenuItem("&Reload", null, OnReload, Keys.F5) { Enabled = false });
        editMenu.DropDownItems.Add(new ToolStripMenuItem("&Restore Backup", null, OnRestoreBackup) { Enabled = false });
        _menuStrip.Items.Add(editMenu);

        // Tools menu
        var toolsMenu = new ToolStripMenuItem("&Tools");
        toolsMenu.DropDownItems.Add(new ToolStripMenuItem("&Export JSON...", null, OnExportJson) { Enabled = false });
        toolsMenu.DropDownItems.Add(new ToolStripMenuItem("&Import JSON...", null, OnImportJson) { Enabled = false });
        _menuStrip.Items.Add(toolsMenu);

        // Help menu
        var helpMenu = new ToolStripMenuItem("&Help");
        helpMenu.DropDownItems.Add(new ToolStripMenuItem("&GitHub Page", null, OnGitHub));
        helpMenu.DropDownItems.Add(new ToolStripSeparator());
        helpMenu.DropDownItems.Add(new ToolStripMenuItem("&Sponsor Development", null, OnSponsor));
        helpMenu.DropDownItems.Add(new ToolStripSeparator());
        helpMenu.DropDownItems.Add(new ToolStripMenuItem("&About", null, OnAbout));
        _menuStrip.Items.Add(helpMenu);
    }

    private void InitializeToolbar()
    {
        _toolStrip.Items.Add(new ToolStripLabel("Directory:"));
        _toolStrip.Items.Add(_directoryCombo);
        _toolStrip.Items.Add(new ToolStripButton("Browse...", null, OnBrowseDirectory));
        _toolStrip.Items.Add(new ToolStripSeparator());
        _toolStrip.Items.Add(new ToolStripLabel("Save Slot:"));
        _toolStrip.Items.Add(_saveSlotCombo);
        _toolStrip.Items.Add(new ToolStripSeparator());
        _toolStrip.Items.Add(_loadButton);
        _toolStrip.Items.Add(_saveButton);

        _loadButton.Click += OnLoadSlot;
        _saveButton.Click += OnSave;
        _directoryCombo.SelectedIndexChanged += (_, _) => PopulateSaveSlots();
    }

    private readonly ToolStripProgressBar _progressBar = new ToolStripProgressBar() { Visible = false, Minimum = 0, Maximum = 100 };

    private void InitializeStatusBar()
    {
        _statusStrip.Items.Add(_statusLabel);
        _statusStrip.Items.Add(_progressBar);
    }

    private void InitializeTabs()
    {
        _tabControl.TabPages.Add(CreateTab("Main Stats", _mainStatsPanel));
        _tabControl.TabPages.Add(CreateTab("Exosuit", _exosuitPanel));
        _tabControl.TabPages.Add(CreateTab("Multitools", _multitoolPanel));
        _tabControl.TabPages.Add(CreateTab("Starships", _shipPanel));
        _tabControl.TabPages.Add(CreateTab("Freighter", _freighterPanel));
        _tabControl.TabPages.Add(CreateTab("Frigates", _frigatePanel));
        _tabControl.TabPages.Add(CreateTab("Vehicles", _vehiclePanel));
        _tabControl.TabPages.Add(CreateTab("Companions", _companionPanel));
        _tabControl.TabPages.Add(CreateTab("Bases & Storage", _basePanel));
        _tabControl.TabPages.Add(CreateTab("Discoveries", _discoveryPanel));
        _tabControl.TabPages.Add(CreateTab("Milestones", _milestonePanel));
        _tabControl.TabPages.Add(CreateTab("Settlements", _settlementPanel));
        _tabControl.TabPages.Add(CreateTab("Account", _accountPanel));
        _tabControl.TabPages.Add(CreateTab("Raw JSON", _rawJsonPanel));
    }

    private static TabPage CreateTab(string text, Control content)
    {
        var page = new TabPage(text);
        content.Dock = DockStyle.Fill;
        page.Controls.Add(content);
        return page;
    }

    private void LoadConfig()
    {
        var config = AppConfig.Instance;
        config.Initialize();

        if (config.MainFrameWidth > 0 && config.MainFrameHeight > 0)
        {
            Location = new Point(config.MainFrameX, config.MainFrameY);
            Size = new Size(config.MainFrameWidth, config.MainFrameHeight);
        }

        // Try to find default save directory
        string? defaultDir = config.LastDirectory ?? SaveFileManager.FindDefaultSaveDirectory();
        if (defaultDir != null)
        {
            _directoryCombo.Items.Add(defaultDir);
            _directoryCombo.SelectedIndex = 0;
        }
    }

    // (Partial file — full file retained; only LoadDatabase shown here for clarity)
    private void LoadDatabase()
    {
        try
        {
            string basePath = AppDomain.CurrentDomain.BaseDirectory;
            string dbPath = Path.Combine(basePath, "Resources", "db");

            // Load items from JSON first, fall back to XML
            string jsonPath = Path.Combine(basePath, "Resources", "json");
            bool jsonLoaded = _database.LoadItemsFromJsonDirectory(jsonPath);
            if (!jsonLoaded)
            {
                string xmlItemsPath = Path.Combine(dbPath, "items.xml");
                if (File.Exists(xmlItemsPath))
                    _database.LoadItems(xmlItemsPath);
            }

            _database.LoadInventoryData(Path.Combine(dbPath, "inventory.xml"));

            // Load word database for Known Words feature
            var wordDbPath = Path.Combine(dbPath, "words.xml");
            if (File.Exists(wordDbPath))
            {
                var wordDb = new WordDatabase();
                wordDb.Load(wordDbPath);
                _discoveryPanel.SetWordDatabase(wordDb);
            }

            // Load icon images from Resources/images
            string iconsPath = Path.Combine(basePath, "Resources", "images");
            if (Directory.Exists(iconsPath))
            {
                _iconManager = new IconManager(iconsPath);
            }

            // Pass item database and icons to inventory panels
            _exosuitPanel.SetDatabase(_database);
            _shipPanel.SetDatabase(_database);
            _freighterPanel.SetDatabase(_database);
            _multitoolPanel.SetDatabase(_database);
            _vehiclePanel.SetDatabase(_database);
            _discoveryPanel.SetDatabase(_database);
            _settlementPanel.SetDatabase(_database);
            _frigatePanel.SetDatabase(_database);
            _basePanel.SetDatabase(_database);

            _exosuitPanel.SetIconManager(_iconManager);
            _shipPanel.SetIconManager(_iconManager);
            _freighterPanel.SetIconManager(_iconManager);
            _multitoolPanel.SetIconManager(_iconManager);
            _vehiclePanel.SetIconManager(_iconManager);
            _discoveryPanel.SetIconManager(_iconManager);
            _milestonePanel.SetIconManager(_iconManager);
            _basePanel.SetIconManager(_iconManager);

            // Load rewards database for Account panel
            _accountPanel.LoadRewardsDatabase(dbPath);

            // Load JSON name mapper for obfuscated NMS save file keys (JSON only)
            var mapperJsonPath = Path.Combine(dbPath, "mapping.json");

            if (File.Exists(mapperJsonPath))
            {
                var mapper = new JsonNameMapper();
                mapper.Load(mapperJsonPath);
                JsonParser.SetDefaultMapper(mapper);
                _statusLabel.Text = $"Loaded {_database.Items.Count} game items, {mapper.Count} name mappings";
            }
            else
            {
                _statusLabel.Text = $"Loaded {_database.Items.Count} game items (mapping.json not found)";
            }
        }
        catch (Exception ex)
        {
            _statusLabel.Text = $"Warning: Could not load game database: {ex.Message}";
        }
    }

    private void PopulateSaveSlots()
    {
        _saveSlotCombo.Items.Clear();

        if (_directoryCombo.SelectedItem is not string dir || !Directory.Exists(dir))
            return;

        // NMS Steam saves: save.hg, save2.hg, save3.hg, ... (up to 30 files for 15 slots)
        // Each slot has 2 files: manual save and auto save
        // Slot 1: save.hg (manual), save2.hg (auto)
        // Slot 2: save3.hg (manual), save4.hg (auto)  etc.
        var saveFiles = new List<string>();
        for (int i = 0; i < 15; i++)
        {
            string manualSave = i == 0 ? "save.hg" : $"save{i * 2 + 1}.hg";
            string autoSave = $"save{i * 2 + 2}.hg";

            string manualPath = Path.Combine(dir, manualSave);
            string autoPath = Path.Combine(dir, autoSave);

            bool hasManual = File.Exists(manualPath);
            bool hasAuto = File.Exists(autoPath);

            if (hasManual || hasAuto)
            {
                // Pick most recent between manual and auto
                string? bestPath = null;
                if (hasManual && hasAuto)
                    bestPath = File.GetLastWriteTime(manualPath) >= File.GetLastWriteTime(autoPath) ? manualPath : autoPath;
                else if (hasManual)
                    bestPath = manualPath;
                else
                    bestPath = autoPath;

                saveFiles.Add(bestPath);
                string difficulty = DetectDifficulty(bestPath);
                string label = string.IsNullOrEmpty(difficulty)
                    ? $"Slot {i + 1}"
                    : $"Slot {i + 1} - {difficulty}";
                _saveSlotCombo.Items.Add(label);
            }
        }

        // Also check for PS4-style saves (savedata00.hg, savedata02.hg, etc.)
        if (_saveSlotCombo.Items.Count == 0)
        {
            var ps4Files = Directory.GetFiles(dir, "savedata*.hg")
                .OrderBy(f => f)
                .ToArray();
            for (int i = 0; i < ps4Files.Length; i++)
            {
                saveFiles.Add(ps4Files[i]);
                string difficulty = DetectDifficulty(ps4Files[i]);
                string label = string.IsNullOrEmpty(difficulty)
                    ? $"Save {i + 1}"
                    : $"Save {i + 1} - {difficulty}";
                _saveSlotCombo.Items.Add(label);
            }
        }

        // Store file paths for later use
        _saveSlotFiles = saveFiles;

        // Load account data (accountdata.hg is in the same directory)
        _accountPanel.LoadAccountFile(dir);

        if (_saveSlotCombo.Items.Count > 0)
        {
            _saveSlotCombo.SelectedIndex = 0;
            _statusLabel.Text = $"Found {_saveSlotCombo.Items.Count} save slot(s) in {Path.GetFileName(dir)}";
        }
        else
        {
            _statusLabel.Text = "No save files found in selected directory";
        }
    }

    /// <summary>
    /// Detect the difficulty/game mode from a save file by scanning the raw JSON text.
    /// Uses regex patterns on the decompressed JSON.
    /// Game modes (1-indexed): Normal, Survival, Permadeath, Creative, Custom, ...
    /// </summary>
    private static string DetectDifficulty(string filePath)
    {
        try
        {
            // Load and decompress the save to get raw JSON text
            var data = SaveFileManager.LoadSaveFile(filePath);

            // Try via parsed data: check ActiveContext → BaseContext/ExpeditionContext → GameMode
            string? activeContext = null;
            try { activeContext = data.GetString("ActiveContext"); } catch { }

            int gameMode = 0;
            if ("Main".Equals(activeContext, StringComparison.Ordinal))
            {
                try { gameMode = data.GetInt("BaseContext.PresetGameMode"); } catch { }
            }
            else if ("Season".Equals(activeContext, StringComparison.Ordinal))
            {
                try { gameMode = data.GetInt("ExpeditionContext.PresetGameMode"); } catch { }
            }

            if (gameMode > 0)
                return GameModeToString(gameMode);

            // Fallback: check DifficultyPresetType
            string? preset = null;
            try { preset = data.GetString("PlayerStateData.DifficultyState.Preset.DifficultyPresetType"); } catch { }
            if (!string.IsNullOrEmpty(preset))
                return preset.ToUpperInvariant();
        }
        catch { }
        return "";
    }

    /// <summary>
    /// Map a 1-based game mode integer to a display string.
    /// </summary>
    private static string GameModeToString(int mode)
    {
        return mode switch
        {
            1 => "NORMAL",
            2 => "SURVIVAL",
            3 => "PERMADEATH",
            4 => "CREATIVE",
            5 => "CUSTOM",
            6 => "SEASONAL",
            7 => "RELAXED",
            8 => "HARDCORE",
            _ => $"MODE {mode}"
        };
    }

    private async void LoadSaveData(string filePath)
    {
        try
        {
            _progressBar.Visible = true;
            _progressBar.Value = 0;
            _statusLabel.Text = "Backing up and loading save file...";

            // Simulate progress for backup (10%)
            await Task.Run(() => { System.Threading.Thread.Sleep(100); });
            _progressBar.Value = 10;

            // Load file in background and report progress
            var progress = new Progress<int>(v => _progressBar.Value = v);

            _currentSaveData = await Task.Run(() =>
            {
                // Step 1: Read file (simulate 30%)
                System.Threading.Thread.Sleep(100);
                ((IProgress<int>)progress).Report(30);

                var data = SaveFileManager.LoadSaveFile(filePath);

                // Step 2: Parse and transform (simulate 60%)
                System.Threading.Thread.Sleep(100);
                ((IProgress<int>)progress).Report(60);

                return data;
            });

            _currentFilePath = filePath;

            // Step 3: Update panels (simulate 90%)
            _progressBar.Value = 90;
            _mainStatsPanel.LoadData(_currentSaveData);
            _exosuitPanel.LoadData(_currentSaveData);
            _multitoolPanel.LoadData(_currentSaveData);
            _shipPanel.LoadData(_currentSaveData);
            _freighterPanel.LoadData(_currentSaveData);
            _frigatePanel.LoadData(_currentSaveData);
            _vehiclePanel.LoadData(_currentSaveData);
            _companionPanel.LoadData(_currentSaveData);
            _basePanel.LoadData(_currentSaveData);
            _discoveryPanel.LoadData(_currentSaveData);
            _milestonePanel.LoadData(_currentSaveData);
            _settlementPanel.LoadData(_currentSaveData);
            _accountPanel.LoadData(_currentSaveData);
            string? saveDir = Path.GetDirectoryName(filePath);
            if (saveDir != null) _accountPanel.LoadAccountFile(saveDir);
            _rawJsonPanel.LoadData(_currentSaveData);

            // Step 4: Done (100%)
            _progressBar.Value = 100;
            await Task.Delay(200); // Let user see 100% for a moment
            _progressBar.Visible = false;

            // Enable save controls
            // Enable save controls
            _saveButton.Enabled = true;

            // File menu
            if (_menuStrip.Items.Count > 0 && _menuStrip.Items[0] is ToolStripMenuItem fileMenu)
            {
                foreach (ToolStripItem item in fileMenu.DropDownItems)
                {
                    if (item is ToolStripMenuItem mi && (mi.Text?.StartsWith("&Save") == true || mi.Text?.StartsWith("Save") == true))
                        mi.Enabled = true;
                }
            }
            // Edit menu
            if (_menuStrip.Items.Count > 1 && _menuStrip.Items[1] is ToolStripMenuItem editMenu)
            {
                foreach (ToolStripItem item in editMenu.DropDownItems)
                {
                    if (item is ToolStripMenuItem mi)
                        mi.Enabled = true;
                }
            }
            // Tools menu
            if (_menuStrip.Items.Count > 2 && _menuStrip.Items[2] is ToolStripMenuItem toolsMenu)
            {
                foreach (ToolStripItem item in toolsMenu.DropDownItems)
                {
                    if (item is ToolStripMenuItem mi)
                        mi.Enabled = true;
                }
            }

            _statusLabel.Text = $"Loaded: {Path.GetFileName(filePath)}";
        }
        catch (Exception ex)
        {
            _progressBar.Visible = false;
            MessageBox.Show($"Failed to load save file:\n{ex.Message}", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
            _statusLabel.Text = "Failed to load save file";
        }
    }

    // Event handlers
    private void OnOpenDirectory(object? sender, EventArgs e) => OnBrowseDirectory(sender, e);

    private void OnBrowseDirectory(object? sender, EventArgs e)
    {
        using var dialog = new FolderBrowserDialog
        {
            Description = "Select NMS save directory",
            UseDescriptionForTitle = true
        };

        if (dialog.ShowDialog() == DialogResult.OK)
        {
            if (!_directoryCombo.Items.Contains(dialog.SelectedPath))
                _directoryCombo.Items.Add(dialog.SelectedPath);
            _directoryCombo.SelectedItem = dialog.SelectedPath;
            AppConfig.Instance.LastDirectory = dialog.SelectedPath;
            AppConfig.Instance.Save();
        }
    }

    private void OnLoadSlot(object? sender, EventArgs e)
    {
        int slotIndex = _saveSlotCombo.SelectedIndex;
        if (slotIndex >= 0 && slotIndex < _saveSlotFiles.Count)
        {
            string filePath = _saveSlotFiles[slotIndex];
            string? saveDir = Path.GetDirectoryName(filePath);
            if (saveDir != null)
            {
                try
                {
                    SaveFileManager.BackupSaveDirectory(saveDir);
                }
                catch (Exception ex)
                {
                    // Log or show a warning, but do not block loading
                    _statusLabel.Text = $"Backup failed: {ex.Message}";
                }
            }
            LoadSaveData(filePath);
        }
        else
        {
            MessageBox.Show("No save slot selected. Please select a directory with save files first.", "Info",
                MessageBoxButtons.OK, MessageBoxIcon.Information);
        }
    }

    private void OnLoadFile(object? sender, EventArgs e)
    {
        using var dialog = new OpenFileDialog
        {
            Filter = "NMS Save Files (*.hg)|*.hg|JSON Files (*.json)|*.json|All Files (*.*)|*.*",
            Title = "Open NMS Save File"
        };

        if (dialog.ShowDialog() == DialogResult.OK)
        {
            string? saveDir = Path.GetDirectoryName(dialog.FileName);
            if (saveDir != null)
            {
                try
                {
                    SaveFileManager.BackupSaveDirectory(saveDir);
                }
                catch (Exception ex)
                {
                    // Log or show a warning, but do not block loading
                    _statusLabel.Text = $"Backup failed: {ex.Message}";
                }
            }
                
            LoadSaveData(dialog.FileName);
        }
    }

    private void OnSave(object? sender, EventArgs e)
    {
        if (_currentSaveData == null || _currentFilePath == null) return;

        try
        {
            // Collect data from all panels
            _mainStatsPanel.SaveData(_currentSaveData);
            _exosuitPanel.SaveData(_currentSaveData);
            _multitoolPanel.SaveData(_currentSaveData);
            _shipPanel.SaveData(_currentSaveData);
            _freighterPanel.SaveData(_currentSaveData);
            _frigatePanel.SaveData(_currentSaveData);
            _vehiclePanel.SaveData(_currentSaveData);
            _companionPanel.SaveData(_currentSaveData);
            _basePanel.SaveData(_currentSaveData);
            _discoveryPanel.SaveData(_currentSaveData);
            _milestonePanel.SaveData(_currentSaveData);
            _settlementPanel.SaveData(_currentSaveData);
            _accountPanel.SaveData(_currentSaveData);
            _rawJsonPanel.SaveData(_currentSaveData);

            SaveFileManager.SaveToFile(_currentFilePath, _currentSaveData);
            _statusLabel.Text = $"Saved: {Path.GetFileName(_currentFilePath)}";
            MessageBox.Show("Save file written successfully!", "Success",
                MessageBoxButtons.OK, MessageBoxIcon.Information);
        }
        catch (Exception ex)
        {
            MessageBox.Show($"Failed to save:\n{ex.Message}", "Error",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
    }

    private void OnSaveAs(object? sender, EventArgs e)
    {
        if (_currentSaveData == null) return;

        using var dialog = new SaveFileDialog
        {
            Filter = "NMS Save Files (*.hg)|*.hg|JSON Files (*.json)|*.json",
            Title = "Save As"
        };

        if (dialog.ShowDialog() == DialogResult.OK)
        {
            _currentFilePath = dialog.FileName;
            OnSave(sender, e);
        }
    }

    private void OnReload(object? sender, EventArgs e)
    {
        if (_currentFilePath != null) LoadSaveData(_currentFilePath);
    }

    private void OnRestoreBackup(object? sender, EventArgs e)
    {
        if (_currentFilePath == null) return;
        string backupPath = _currentFilePath + ".backup";
        if (File.Exists(backupPath))
        {
            File.Copy(backupPath, _currentFilePath, true);
            LoadSaveData(_currentFilePath);
            _statusLabel.Text = "Backup restored";
        }
        else
        {
            MessageBox.Show("No backup file found.", "Info", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }
    }

    private void OnExportJson(object? sender, EventArgs e)
    {
        if (_currentSaveData == null) return;

        using var dialog = new SaveFileDialog
        {
            Filter = "JSON Files (*.json)|*.json",
            Title = "Export JSON"
        };

        if (dialog.ShowDialog() == DialogResult.OK)
        {
            _currentSaveData.ExportToFile(dialog.FileName);
            _statusLabel.Text = $"Exported to {Path.GetFileName(dialog.FileName)}";
        }
    }

    private void OnImportJson(object? sender, EventArgs e)
    {
        using var dialog = new OpenFileDialog
        {
            Filter = "JSON Files (*.json)|*.json",
            Title = "Import JSON"
        };

        if (dialog.ShowDialog() == DialogResult.OK)
        {
            try
            {
                _currentSaveData = JsonObject.ImportFromFile(dialog.FileName);
                _mainStatsPanel.LoadData(_currentSaveData);
                _exosuitPanel.LoadData(_currentSaveData);
                _multitoolPanel.LoadData(_currentSaveData);
                _shipPanel.LoadData(_currentSaveData);
                _freighterPanel.LoadData(_currentSaveData);
                _frigatePanel.LoadData(_currentSaveData);
                _vehiclePanel.LoadData(_currentSaveData);
                _companionPanel.LoadData(_currentSaveData);
                _basePanel.LoadData(_currentSaveData);
                _discoveryPanel.LoadData(_currentSaveData);
                _milestonePanel.LoadData(_currentSaveData);
                _settlementPanel.LoadData(_currentSaveData);
                _accountPanel.LoadData(_currentSaveData);
                _rawJsonPanel.LoadData(_currentSaveData);
                _statusLabel.Text = $"Imported from {Path.GetFileName(dialog.FileName)}";
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Failed to import:\n{ex.Message}", "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
    }

    private void OnGitHub(object? sender, EventArgs e)
    {
        try
        {
            System.Diagnostics.Process.Start(new System.Diagnostics.ProcessStartInfo
            {
                FileName = GitHubUrl,
                UseShellExecute = true
            });
        }
        catch { }
    }

    private void OnSponsor(object? sender, EventArgs e)
    {
        try
        {
            System.Diagnostics.Process.Start(new System.Diagnostics.ProcessStartInfo
            {
                FileName = SponsorUrl,
                UseShellExecute = true
            });
        }
        catch { }
    }

    private void OnAbout(object? sender, EventArgs e)
    {
        using var aboutForm = new Form
        {
            Text = $"About",
            FormBorderStyle = FormBorderStyle.FixedDialog,
            StartPosition = FormStartPosition.CenterParent,
            ClientSize = new Size(340, 160),
            MaximizeBox = false,
            MinimizeBox = false
        };

        var label = new Label
        {
            Text = $"{AppName}\n{VerMajor}.{VerMinor}.{Build} ({SuppGameRel})\n\nby vector_cmdr",
            AutoSize = true,
            Location = new Point(16, 16)
        };

        var link = new LinkLabel
        {
            Text = GitHubCreatorUrl,
            AutoSize = true,
            Location = new Point(16, 80)
        };
        link.Links[0].LinkData = GitHubCreatorUrl;
        link.LinkClicked += (s, args) =>
        {
            try
            {
                var linkData = link.Links[0].LinkData?.ToString();
                if (!string.IsNullOrEmpty(linkData))
                {
                    System.Diagnostics.Process.Start(new System.Diagnostics.ProcessStartInfo
                    {
                        FileName = linkData,
                        UseShellExecute = true
                    });
                }
            }
            catch { }
        };

        var okButton = new System.Windows.Forms.Button
        {
            Text = "OK",
            DialogResult = DialogResult.OK,
            Anchor = AnchorStyles.Bottom | AnchorStyles.Right,
            Location = new Point(aboutForm.ClientSize.Width - 90, aboutForm.ClientSize.Height - 40),
            Size = new Size(75, 25)
        };

        aboutForm.Controls.Add(label);
        aboutForm.Controls.Add(link);
        aboutForm.Controls.Add(okButton);
        aboutForm.AcceptButton = okButton;
        aboutForm.ShowDialog(this);
    }

    private void OnFormClosing(object? sender, FormClosingEventArgs e)
    {
        var config = AppConfig.Instance;
        if (WindowState == FormWindowState.Normal)
        {
            config.MainFrameX = Location.X;
            config.MainFrameY = Location.Y;
            config.MainFrameWidth = Size.Width;
            config.MainFrameHeight = Size.Height;
        }
        config.Save();
    }
}
