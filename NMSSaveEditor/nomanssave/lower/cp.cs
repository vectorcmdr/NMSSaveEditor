using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class cp : JFileChooser {
   public static cp fJ = null;
   public static string name = "Companion Export FileInfo";
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
      this.setDialogTitle("Choose Companion Export FileInfo");
      /* TODO: port from Java - addPropertyChangeListener for lookAndFeel */
   }
   public static Image @as() {
      return fK;
   }
   public static Image au() {
      return fL;
   }
}



}
