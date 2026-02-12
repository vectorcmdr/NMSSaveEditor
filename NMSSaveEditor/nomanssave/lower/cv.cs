using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class cv : OpenFileDialog {
   private static cv fQ = null;
   private static string name = "Weapon Export FileInfo";
   private static Image fH = Application.a("UI-WEAPONICON.PNG", 16, 16);

   public static cv ax() {
      if (fQ == null) {
         fQ = new cv();
      }

      return fQ;
   }

   private cv() {
      this.setFileSelectionMode(0);
      this.setAcceptAllFileFilterUsed(false);
      this.setFileView(new cw(this));
      this.setFileFilter(new cx(this));
      this.setDialogTitle("Choose Weapon Export FileInfo");
      SystemInformation.addPropertyChangeListener((var1) => {
         if ("lookAndFeel".Equals(var1.getPropertyName())) {
            Control.updateComponentTreeUI(this);
         }

      });
   }
   static Image @as() {
      return fH;
   }
}

}
