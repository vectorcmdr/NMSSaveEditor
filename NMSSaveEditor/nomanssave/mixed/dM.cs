using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class dM : G {
   public gG hH;
   dE hE;

   public dM(dE var1, gG var2) {
      this.hE = var1;
      this.hH = var2;
   }

   protected override string g(string var1) {
      gE var2 = (gE)dE.a(this.hE).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         int var3 = var2.aq(this.hH.ordinal());

         try {
            int var4 = hf.b(var1, 0, this.hH.dY());
            if (var4 != var3) {
               var2.e(this.hH.ordinal(), var4);
            }

            return Integer.toString(var4);
         } catch (Exception var5) {
            return Integer.toString(var3);
         }
      }
   }
   public dM(dE var1, gG var2, dM var3) {
      this(var1, var2);
   }
}

}
