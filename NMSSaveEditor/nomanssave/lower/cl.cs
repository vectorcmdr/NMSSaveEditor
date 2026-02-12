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
      this.setFileSelectionMode(0);
      this.setAcceptAllFileFilterUsed(false);
      this.setFileView(new cm(this));
      this.setFileFilter(new cn(this));
      this.addChoosableFileFilter(new co(this));
      this.setDialogTitle("Choose Backup FileInfo");
      SystemInformation.addPropertyChangeListener((var1) => {
         if ("lookAndFeel".Equals(var1.getPropertyName())) {
            Control.updateComponentTreeUI(this);
         }

      });
   }
   public static Image @as() {
      return fH;
   }
}

}
