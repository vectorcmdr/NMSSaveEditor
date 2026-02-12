using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

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

            return Integer.toString(var3);
         } catch (Exception var4) {
            return Integer.toString(var2);
         }
      }
   }
}


#else

public class dW
{
   public dW() { }
   public dW(params object[] args) { }
   public dN ia = default;
   public string g(string var1) { return ""; }
}

#endif

}
