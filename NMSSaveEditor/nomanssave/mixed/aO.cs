using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{


public class aO : G {
   public aJ dj;

   public aO(aJ var1) {
      this.dj = var1;
   }

   public override string g(string var1) {
      if (aJ.a(this.dj) == null) {
         return "";
      } else {
         long var2 = aJ.a(this.dj).dK();

         try {
            long var4 = hf.a(var1, 0L, 4294967295L);
            if (var4 != var2) {
               aJ.a(this.dj).f(var4);
            }

            return (var4).ToString();
         } catch (Exception var6) {
            return (var2).ToString();
         }
      }
   }
}



}
