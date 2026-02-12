using NMSSaveEditor.Config;
using NMSSaveEditor.Data;
using NMSSaveEditor.IO;
using NMSSaveEditor.Models;

namespace NMSSaveEditor.UI;

public class MainForm : Form
{
    public const string Version = "1.19.14";
    public const string Release = "BREACH";
    public const string GitHubUrl = "https://github.com/goatfungus/NMSSaveEditor";

    private readonly MenuStrip _menuStrip;
    private readonly ToolStrip _toolStrip;
    private readonly StatusStrip _statusStrip;
    private readonly TabControl _tabControl;
    private readonly ToolStripStatusLabel _statusLabel;
    private readonly ToolStripComboBox _directoryCombo;
    private readonly ToolStripComboBox _saveSlotCombo;
    private readonly ToolStripButton _loadButton;
    private readonly ToolStripButton _saveButton;

    // Tab panels
    private readonly MainStatsPanel _mainStatsPanel;
    private readonly ExosuitPanel _exosuitPanel;
    private readonly MultitoolPanel _multitoolPanel;
    private readonly ShipPanel _shipPanel;
    private readonly FreighterPanel _freighterPanel;
    private readonly FrigatePanel _frigatePanel;
    private readonly VehiclePanel _vehiclePanel;
    private readonly CompanionPanel _companionPanel;
    private readonly BasePanel _basePanel;
    private readonly DiscoveryPanel _discoveryPanel;
    private readonly MilestonePanel _milestonePanel;
    private readonly SettlementPanel _settlementPanel;
    private readonly RawJsonPanel _rawJsonPanel;

    // Data
    private readonly GameItemDatabase _database = new();
    private JsonObject? _currentSaveData;
    private string? _currentFilePath;

    public MainForm()
    {
        // Initialize components
        _menuStrip = new MenuStrip();
        _toolStrip = new ToolStrip();
        _statusStrip = new StatusStrip();
        _tabControl = new TabControl();
        _statusLabel = new ToolStripStatusLabel("Ready");
        _directoryCombo = new ToolStripComboBox { Width = 300 };
        _saveSlotCombo = new ToolStripComboBox { Width = 150 };
        _loadButton = new ToolStripButton("Load");
        _saveButton = new ToolStripButton("Save") { Enabled = false };

        // Create panels
        _mainStatsPanel = new MainStatsPanel();
        _exosuitPanel = new ExosuitPanel();
        _multitoolPanel = new MultitoolPanel();
        _shipPanel = new ShipPanel();
        _freighterPanel = new FreighterPanel();
        _frigatePanel = new FrigatePanel();
        _vehiclePanel = new VehiclePanel();
        _companionPanel = new CompanionPanel();
        _basePanel = new BasePanel();
        _discoveryPanel = new DiscoveryPanel();
        _milestonePanel = new MilestonePanel();
        _settlementPanel = new SettlementPanel();
        _rawJsonPanel = new RawJsonPanel();

        InitializeForm();
        InitializeMenus();
        InitializeToolbar();
        InitializeStatusBar();
        InitializeTabs();
        LoadConfig();
        LoadDatabase();
    }

    private void InitializeForm()
    {
        Text = $"No Man's Sky Save Editor - {Version} ({Release})";
        Size = new Size(1200, 800);
        StartPosition = FormStartPosition.CenterScreen;
        MinimumSize = new Size(800, 600);
        FormClosing += OnFormClosing;

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
    }

    private void InitializeStatusBar()
    {
        _statusStrip.Items.Add(_statusLabel);
    }

    private void InitializeTabs()
    {
        _tabControl.Dock = DockStyle.Fill;
        _tabControl.TabPages.Add(CreateTab("Main Stats", _mainStatsPanel));
        _tabControl.TabPages.Add(CreateTab("Exosuit", _exosuitPanel));
        _tabControl.TabPages.Add(CreateTab("Multitools", _multitoolPanel));
        _tabControl.TabPages.Add(CreateTab("Ships", _shipPanel));
        _tabControl.TabPages.Add(CreateTab("Freighter", _freighterPanel));
        _tabControl.TabPages.Add(CreateTab("Frigates", _frigatePanel));
        _tabControl.TabPages.Add(CreateTab("Vehicles", _vehiclePanel));
        _tabControl.TabPages.Add(CreateTab("Companions", _companionPanel));
        _tabControl.TabPages.Add(CreateTab("Bases", _basePanel));
        _tabControl.TabPages.Add(CreateTab("Discoveries", _discoveryPanel));
        _tabControl.TabPages.Add(CreateTab("Milestones", _milestonePanel));
        _tabControl.TabPages.Add(CreateTab("Settlements", _settlementPanel));
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

    private void LoadDatabase()
    {
        try
        {
            string basePath = AppDomain.CurrentDomain.BaseDirectory;
            string dbPath = Path.Combine(basePath, "Resources", "db");
            _database.LoadItems(Path.Combine(dbPath, "items.xml"));
            _database.LoadInventoryData(Path.Combine(dbPath, "inventory.xml"));
            _statusLabel.Text = $"Loaded {_database.Items.Count} game items";
        }
        catch (Exception ex)
        {
            _statusLabel.Text = $"Warning: Could not load game database: {ex.Message}";
        }
    }

    private void LoadSaveData(string filePath)
    {
        try
        {
            _statusLabel.Text = "Loading save file...";
            Application.DoEvents();

            _currentSaveData = SaveFileManager.LoadSaveFile(filePath);
            _currentFilePath = filePath;

            // Update all panels
            _mainStatsPanel.LoadData(_currentSaveData);
            _rawJsonPanel.LoadData(_currentSaveData);

            // Enable save controls
            _saveButton.Enabled = true;
            foreach (ToolStripItem item in ((ToolStripMenuItem)_menuStrip.Items[0]).DropDownItems)
            {
                if (item is ToolStripMenuItem mi && (mi.Text.StartsWith("&Save") || mi.Text.StartsWith("Save")))
                    mi.Enabled = true;
            }
            foreach (ToolStripItem item in ((ToolStripMenuItem)_menuStrip.Items[1]).DropDownItems)
            {
                if (item is ToolStripMenuItem mi) mi.Enabled = true;
            }
            foreach (ToolStripItem item in ((ToolStripMenuItem)_menuStrip.Items[2]).DropDownItems)
            {
                if (item is ToolStripMenuItem mi) mi.Enabled = true;
            }

            _statusLabel.Text = $"Loaded: {Path.GetFileName(filePath)}";
        }
        catch (Exception ex)
        {
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

    private void OnLoadFile(object? sender, EventArgs e)
    {
        using var dialog = new OpenFileDialog
        {
            Filter = "NMS Save Files (*.hg)|*.hg|JSON Files (*.json)|*.json|All Files (*.*)|*.*",
            Title = "Open NMS Save File"
        };

        if (dialog.ShowDialog() == DialogResult.OK)
            LoadSaveData(dialog.FileName);
    }

    private void OnLoadSlot(object? sender, EventArgs e)
    {
        if (_directoryCombo.SelectedItem is string dir && Directory.Exists(dir))
        {
            var saveFiles = Directory.GetFiles(dir, "save*.hg");
            if (saveFiles.Length > 0)
            {
                int slotIndex = _saveSlotCombo.SelectedIndex;
                if (slotIndex >= 0 && slotIndex < saveFiles.Length)
                    LoadSaveData(saveFiles[slotIndex]);
                else if (saveFiles.Length > 0)
                    LoadSaveData(saveFiles[0]);
            }
            else
            {
                MessageBox.Show("No save files found in the selected directory.", "Info",
                    MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }
    }

    private void OnSave(object? sender, EventArgs e)
    {
        if (_currentSaveData == null || _currentFilePath == null) return;

        try
        {
            // Collect data from all panels
            _mainStatsPanel.SaveData(_currentSaveData);
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

    private void OnAbout(object? sender, EventArgs e)
    {
        MessageBox.Show(
            $"No Man's Sky Save Editor\nVersion {Version} ({Release})\n\nPorted to C# .NET 10\nOriginal: {GitHubUrl}",
            "About NMS Save Editor",
            MessageBoxButtons.OK, MessageBoxIcon.Information);
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
