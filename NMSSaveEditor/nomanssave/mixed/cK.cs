using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class cK : JFileChooser {
   private static cK gk = null;
   private static string name = "JSON FileInfo";

   public static cK aA() {
      if (gk == null) {
         gk = new cK();
      }

      return gk;
   }

   private cK() {
      this.setFileSelectionMode(0);
      this.setAcceptAllFileFilterUsed(false);
      this.setFileView(new cL(this));
      this.setFileFilter(new cM(this));
      this.setDialogTitle("Choose JSON FileInfo");
   }
}

}
