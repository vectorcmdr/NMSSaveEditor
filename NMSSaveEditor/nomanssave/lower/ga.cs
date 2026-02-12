using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace NMSSaveEditor
{

class ga : FileFilter {
   fZ nb;
   private List<object> mg;

   ga(fZ var1, List<object> var2) {
      this.nb = var1;
      this.mg = var2;
   }

   public bool accept(File var1) {
      Matcher var2 = fT.cu().matcher(var1.Name);
      if (var2.Matches()) {
         int var3 = var2.group(1).length() == 0 ? 0 : int.Parse(var2.group(1)) - 1;
         if (var3 / 2 == this.nb.lT) {
            try {
               this.mg.Add(new fV(fZ.a(this.nb), var1.Name, var3));
            } catch (IOException var5) {
               hc.a("Cannot load " + var1.Name, var5);
            }
         }
      }

      return false;
   }
}

}
