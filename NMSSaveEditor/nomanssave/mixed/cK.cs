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

public class cK  {
   public OpenFileDialog dialog = new OpenFileDialog();
   public static cK gk = null;
   public static string name = "JSON File";

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
      this.setDialogTitle("Choose JSON File");
   }
}

}
