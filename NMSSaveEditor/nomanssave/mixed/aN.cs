using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class aN : G {
   aJ dj;
   private Application bv;

   aN(aJ var1, Application var2) {
      this.dj = var1;
      this.bv = var2;
   }

   protected override string g(string var1) {
      if (aJ.a(this.dj) == null) {
         return "";
      } else {
         long var2 = aJ.a(this.dj).dJ();

         try {
            long var4 = hf.a(var1, 0L, 4294967295L);
            if (var4 != var2) {
               aJ.a(this.dj).e(var4);
               this.bv.C();
            }

            return Long.toString(var4);
         } catch (Exception var6) {
            return Long.toString(var2);
         }
      }
   }
}

}
