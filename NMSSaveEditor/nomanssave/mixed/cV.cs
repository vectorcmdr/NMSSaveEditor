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

public class cV : FileFilter {
   // $FF: synthetic field
   cT gw;

   cV(cT var1) {
      this.gw = var1;
   }

   public string getDescription() {
      return "Ship Export File";
   }

   public bool accept(FileInfo var1) {
      if (var1.IsDirectory()) {
         return !var1.isHidden();
      } else {
         return var1.Name.EndsWith(".sh0");
      }
   }
}

}
