using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



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
      // this.setFileSelectionMode - WinForms uses separate dialog types
      // this.setAcceptAllFileFilterUsed - not needed in WinForms
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



}
