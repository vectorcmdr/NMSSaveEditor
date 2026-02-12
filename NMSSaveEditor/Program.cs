namespace NMSSaveEditor;

static class Program
{
    /// <summary>
    ///  The main entry point for the application.
    /// </summary>
    [STAThread]
    static void Main(string[] args)
    {
        System.Windows.Forms.Application.EnableVisualStyles();
        System.Windows.Forms.Application.SetCompatibleTextRenderingDefault(false);

        // Parse args
        bool autoUpdate = args.Length > 0 && args[0].Equals("-autoupdate");

        // Initialize
        NMSSaveEditor.aH.init(!autoUpdate);
        hc.info("Starting Editor...");

        // Start background initialization
        new System.Threading.Thread(() => { cK.aA(); }).Start();

        // Create application and main form
        try
        {
            var app = new Application(autoUpdate, (Application)null);
            Application.g(app);
            var mainForm = Application.h(app);
            if (mainForm != null)
            {
                System.Windows.Forms.Application.Run(mainForm);
            }
            else
            {
                // Fallback: show a basic form
                var form = new System.Windows.Forms.Form();
                form.Text = "NMS Save Editor v" + Application.VERSION;
                form.Size = new System.Drawing.Size(1200, 800);
                System.Windows.Forms.Application.Run(form);
            }
        }
        catch (System.Exception ex)
        {
            System.Windows.Forms.MessageBox.Show(
                "Error starting application: " + ex.Message + "\n\n" + ex.StackTrace,
                "NMS Save Editor Error",
                System.Windows.Forms.MessageBoxButtons.OK,
                System.Windows.Forms.MessageBoxIcon.Error);
        }
    }
}