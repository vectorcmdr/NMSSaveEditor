using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{


public class dW : G {
   public dN ia;

   public dW(dN var1) {
      this.ia = var1;
   }

   public override string g(string var1) {
      if (dN.o(this.ia) == null) {
         return "";
      } else {
         int var2 = dN.o(this.ia).dM();

         try {
            int var3 = hf.b(var1, 1, 500);
            if (var3 != var2) {
               dN.o(this.ia).aB(var3);
            }

            return (var3).ToString();
         } catch (Exception var4) {
            return (var2).ToString();
         }
      }
   }
}



}
