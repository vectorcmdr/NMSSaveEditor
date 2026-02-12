using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

public class aL : G {
   public aJ dj;

   public aL(aJ var1) {
      this.dj = var1;
   }

   public override string g(string var1) {
      if (aJ.a(this.dj) == null) {
         return "";
      } else {
         int var2 = aJ.a(this.dj).dN();

         try {
            int var3 = hf.b(var1, 0, 100);
            if (var3 != var2) {
               aJ.a(this.dj).aC(var3);
            }

            return Integer.toString(var3);
         } catch (Exception var4) {
            return Integer.toString(var2);
         }
      }
   }
}


#else

public class aL
{
   public aL() { }
   public aL(params object[] args) { }
   public aJ dj = default;
   public string g(string var1) { return ""; }
}

#endif

}
