using NMSE.UI;

namespace NMSE;

static class Program
{
    /// <summary>
    /// The main entry point for the NMS Save Editor application.
    /// </summary>
    [STAThread]
    static void Main()
    {
        ApplicationConfiguration.Initialize();
        Application.Run(new MainFormResources());
    }
}