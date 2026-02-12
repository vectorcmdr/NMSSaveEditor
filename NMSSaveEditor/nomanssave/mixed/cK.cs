using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class cK : JFileChooser {
   public static cK gk = null;
   public static string name = "JSON FileInfo";

   public static cK aA() {
      if (gk == null) {
         gk = new cK();
      }

      return gk;
   }

   public cK() {
      this.setFileSelectionMode(0);
      this.setAcceptAllFileFilterUsed(false);
      this.setFileView(new cL(this));
      this.setFileFilter(new cM(this));
      this.setDialogTitle("Choose JSON FileInfo");
   }
}



}
