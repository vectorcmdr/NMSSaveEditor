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

public class fF : FileFilter {
   // $FF: synthetic field
   public fE mf;
   // $FF: synthetic field
   public List<object> mg;

   public fF(fE var1, List<object> var2) {
      this.mf = var1;
      this.mg = var2;
   }

   public bool accept(FileInfo var1) {
      Matcher var2 = fA.cb().Match(var1.Name);
      if (var2.Matches()) {
         int var3 = var2.Groups[1].Length == 0 ? 0 : int.Parse(var2.Groups[1]) - 1;
         if (var3 / 2 == this.mf.lT) {
            try {
               this.mg.Add(new fC(fE.a(this.mf), var1.Name, var3));
            } catch (IOException var5) {
               hc.a("Cannot load " + var1.Name, var5);
            }
         }
      }
       return false;
   }
}

}
