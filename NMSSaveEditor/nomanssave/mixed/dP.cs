using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class dP : G {
   dN ia;

   dP(dN var1) {
      this.ia = var1;
   }

   protected string g(string var1) {
      gH var2 = (gH)dN.p(this.ia).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         double var3 = var2.ec();

         try {
            double var5 = hf.a(var1, 0.0D, 1000.0D);
            if (var5 != var3) {
               var2.i(var5);
            }

            return Double.toString(var5);
         } catch (Exception var7) {
            return Double.toString(var3);
         }
      }
   }
}

}
