using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class aK : G {
   aJ dj;

   public aK(aJ var1) {
      this.dj = var1;
   }

   protected override string g(string var1) {
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

}
