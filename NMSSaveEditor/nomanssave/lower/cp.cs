using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class cp : OpenFileDialog {
   private static cp fJ = null;
   private static string name = "Companion Export FileInfo";
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
      this.setDialogTitle("Choose Companion Export FileInfo");
      SystemInformation.addPropertyChangeListener((var1) => {
         if ("lookAndFeel".Equals(var1.getPropertyName())) {
            Control.updateComponentTreeUI(this);
         }

      });
   }
   static Image @as() {
      return fK;
   }
   static Image au() {
      return fL;
   }
}

}
