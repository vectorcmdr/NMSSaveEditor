using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace NMSSaveEditor
{

class fO : FileFilter {
   fN mw;
   private List<object> mg;

   fO(fN var1, List<object> var2) {
      this.mw = var1;
      this.mg = var2;
   }

   public bool accept(File var1) {
      Matcher var2 = fJ.cl().matcher(var1.Name);
      if (var2.Matches()) {
         int var3 = var2.group(1).length() == 0 ? 0 : int.Parse(var2.group(1)) - 1;
         if (var3 / 2 == this.mw.lT) {
            try {
               this.mg.Add(new fL(fN.a(this.mw), var1.Name, var3));
            } catch (IOException var5) {
               hc.a("Cannot load " + var1.Name, var5);
            }
         }
      }

      return false;
   }
}

}
