using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

public class aP : G {
   public aJ dj;

   public aP(aJ var1) {
      this.dj = var1;
   }

   public override string g(string var1) {
      if (aJ.a(this.dj) == null) {
         return "";
      } else {
         long var2 = aJ.a(this.dj).dL();

         try {
            long var4 = hf.a(var1, 0L, 4294967295L);
            if (var4 != var2) {
               aJ.a(this.dj).g(var4);
            }

            return Long.toString(var4);
         } catch (Exception var6) {
            return Long.toString(var2);
         }
      }
   }
}


#else

public class aP
{
   public aP() { }
   public aP(params object[] args) { }
   public aJ dj = default;
   public string g(string var1) { return ""; }
}

#endif

}
