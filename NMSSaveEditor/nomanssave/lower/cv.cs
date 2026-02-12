using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class cv : JFileChooser {
   public static cv fQ = null;
   public static string name = "Weapon Export FileInfo";
   public static Image fH = Application.a("UI-WEAPONICON.PNG", 16, 16);

   public static cv ax() {
      if (fQ == null) {
         fQ = new cv();
      }

      return fQ;
   }

   public cv() {
      // this.setFileSelectionMode - WinForms uses separate dialog types
      // this.setAcceptAllFileFilterUsed - not needed in WinForms
      this.setFileView(new cw(this));
      this.setFileFilter(new cx(this));
      this.setDialogTitle("Choose Weapon Export FileInfo");
      /* TODO: port from Java - addPropertyChangeListener for lookAndFeel */
   }
   public static Image @as() {
      return fH;
   }
}



}
