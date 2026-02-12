using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class dU : G {
   dN ia;

   dU(dN var1) {
      this.ia = var1;
   }

   protected string g(string var1) {
      gH var2 = (gH)dN.p(this.ia).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         try {
            var1 = hg.aB(var1).ToString();
            if (!var1.Equals(var2.cK())) {
               var2.aa(var1);
            }

            return var1;
         } catch (Exception var4) {
            return var2.cK();
         }
      }
   }
}

}
