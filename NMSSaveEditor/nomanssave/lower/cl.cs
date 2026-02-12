using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class cl : JFileChooser {
   public static cl fG = null;
   public static string name = "Planetary Base Backup FileInfo";
   public static Image fH = Application.a("UI-BASEICON.PNG", 16, 16);

   public static cl ar() {
      if (fG == null) {
         fG = new cl();
      }

      return fG;
   }

   public cl() {
      this.setFileSelectionMode(0);
      this.setAcceptAllFileFilterUsed(false);
      this.setFileView(new cm(this));
      this.setFileFilter(new cn(this));
      this.addChoosableFileFilter(new co(this));
      this.setDialogTitle("Choose Backup FileInfo");
      /* TODO: port from Java - addPropertyChangeListener for lookAndFeel */
   }
   public static Image @as() {
      return fH;
   }
}


#else

public class cl
{
   public cl() { }
   public cl(params object[] args) { }
   public static cl fG = default;
   public static string name = "";
   public static Image fH = default;
}

#endif

}
