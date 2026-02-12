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

public class ek : FileFilter {
   // $FF: synthetic field
   ej @is;

   ek(ej var1) {
      this.@is = var1;
   }

   public string getDescription() {
      return "Saved Game";
   }

   public bool accept(FileInfo var1) {
      if (var1.IsDirectory()) {
         return true;
      } else {
         string var2 = var1.Name;
         if (var2.EndsWith(".hg") && !var2.StartsWith("mf_")) {
            return true;
         } else {
            return var2.Equals("containers.index");
         }
      }
   }
}

}
