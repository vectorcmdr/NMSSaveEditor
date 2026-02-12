using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class cl  {
   public OpenFileDialog dialog = new OpenFileDialog();
   public static cl fG = null;
   public static string name = "Planetary Base Backup File";
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
      this.setDialogTitle("Choose Backup File");
      UIManager.addPropertyChangeListener((var1) => {
         if ("lookAndFeel".Equals(var1.getPropertyName())) {
            SwingUtilities.updateComponentTreeUI(this);
         }

      });
   }

   // $FF: synthetic method
   static Image @as() {
      return fH;
   }
}

}
