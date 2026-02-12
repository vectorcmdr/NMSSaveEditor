using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{


public class bh : G {
   public bd dP;

   public bh(bd var1) {
      this.dP = var1;
   }

   public override string g(string var1) {
      if (bd.a(this.dP) == null) {
         return "";
      } else {
         double var2 = bd.a(this.dP).cX();

         try {
            double var4 = hf.a(var1, 0.0D, 1000.0D);
            if (var4 != var2) {
               bd.a(this.dP).a(var4);
            }

            return (var4).ToString();
         } catch (Exception var6) {
            return (var2).ToString();
         }
      }
   }
}



}
