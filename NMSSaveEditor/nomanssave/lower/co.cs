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

public class co : FileFilter {
   // $FF: synthetic field
   cl fI;

   co(cl var1) {
      this.fI = var1;
   }

   public string getDescription() {
      return "All Base Backup Files";
   }

   public bool accept(FileInfo var1) {
      if (var1.IsDirectory()) {
         return !var1.isHidden();
      } else {
         string var2 = var1.Name;
         return var2.EndsWith(".pb3") || var2.EndsWith(".pb0");
      }
   }
}

}
