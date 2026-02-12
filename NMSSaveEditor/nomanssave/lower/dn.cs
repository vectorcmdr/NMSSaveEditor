using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class dn : G {
   dj hl;

   dn(dj var1) {
      this.hl = var1;
   }

   protected override string g(string var1) {
      gv var2 = (gv)dj.j(this.hl).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         double var3 = var2.dF();

         try {
            double var5 = hf.a(var1, 0.0D, 1000.0D);
            if (var5 != var3) {
               var2.d(var5);
            }

            return Double.toString(var5);
         } catch (Exception var7) {
            return Double.toString(var3);
         }
      }
   }
}

}
