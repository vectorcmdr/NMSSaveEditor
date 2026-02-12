using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{


public class dX : G {
   public dN ia;

   public dX(dN var1) {
      this.ia = var1;
   }

   public override string g(string var1) {
      if (dN.o(this.ia) == null) {
         return "";
      } else {
         int var2 = dN.o(this.ia).dN();

         try {
            int var3 = hf.b(var1, 1, 200);
            if (var3 != var2) {
               dN.o(this.ia).aC(var3);
            }

            return Integer.toString(var3);
         } catch (Exception var4) {
            return Integer.toString(var2);
         }
      }
   }
}



}
