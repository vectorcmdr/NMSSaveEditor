using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{


public class dM : G {
   public gG hH;
   public dE hE;

   public dM(dE var1, gG var2) {
      this.hE = var1;
      this.hH = var2;
   }

   public override string g(string var1) {
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

            return (var4).ToString();
         } catch (Exception var5) {
            return (var3).ToString();
         }
      }
   }
   public dM(dE var1, gG var2, dM var3) {
      this(var1, var2);
   }
}



}
