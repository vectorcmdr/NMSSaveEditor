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

public class cp : OpenFileDialog {
   private static cp fJ = null;
   private static string name = "Companion Export File";
   private static Image fK = Application.a("UI-PET.PNG", 16, 16);
   private static Image fL = Application.a("UI-EGG.PNG", 16, 16);

   public static cp at() {
      if (fJ == null) {
         fJ = new cp();
      }

      return fJ;
   }

   private cp() {
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
   static Image as() {
      return fK;
   }

   // $FF: synthetic method
   static Image au() {
      return fL;
   }
}

}
