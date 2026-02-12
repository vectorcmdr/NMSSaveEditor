using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

public class aK : G {
   public aJ dj;

   public aK(aJ var1) {
      this.dj = var1;
   }

   public override string g(string var1) {
      if (aJ.a(this.dj) == null) {
         return "";
      } else {
         int var2 = aJ.a(this.dj).dM();

         try {
            int var3 = hf.b(var1, 1, 200);
            if (var3 != var2) {
               aJ.a(this.dj).aB(var3);
            }

            return Integer.toString(var3);
         } catch (Exception var4) {
            return Integer.toString(var2);
         }
      }
   }
}


#else

public class aK
{
   public aK() { }
   public aK(params object[] args) { }
   public aJ dj = default;
   public string g(string var1) { return ""; }
}

#endif

}
