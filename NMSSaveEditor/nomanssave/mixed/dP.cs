using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{


public class dP : G {
   public dN ia;

   public dP(dN var1) {
      this.ia = var1;
   }

   public override string g(string var1) {
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

            return (var5).ToString();
         } catch (Exception var7) {
            return (var3).ToString();
         }
      }
   }
}



}
