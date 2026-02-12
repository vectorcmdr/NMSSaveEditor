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

public class cp  {
   public OpenFileDialog dialog = new OpenFileDialog();
   public static cp fJ = null;
   public static string name = "Companion Export File";
   public static Image fK = Application.a("UI-PET.PNG", 16, 16);
   public static Image fL = Application.a("UI-EGG.PNG", 16, 16);

   public static cp at() {
      if (fJ == null) {
         fJ = new cp();
      }

      return fJ;
   }

   public cp() {
      this.setFileSelectionMode(0);
      this.setAcceptAllFileFilterUsed(false);
      this.setFileView(new cq(this));
      this.setFileFilter(new cr(this));
      this.setDialogTitle("Choose Companion Export File");
      UIManager.addPropertyChangeListener((var1) => {
         if ("lookAndFeel".Equals(var1.getPropertyName())) {
            SwingUtilities.updateComponentTreeUI(this);
         }

      });
   }

   // $FF: synthetic method
   public static Image @as() {
      return fK;
   }

   // $FF: synthetic method
   public static Image au() {
      return fL;
   }
}

}
