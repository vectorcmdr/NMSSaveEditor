using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class bp : G {
   bl er;

   public bp(bl var1) {
      this.er = var1;
   }

   protected override string g(string var1) {
      if (bl.b(this.er) < 0) {
         return "";
      } else {
         int var2 = bl.c(this.er)[bl.b(this.er)].de();

         try {
            int var3 = hf.b(var1, 0, int.MaxValue);
            if (var3 != var2) {
               bl.c(this.er)[bl.b(this.er)].au(var3);
            }

            return Integer.toString(var3);
         } catch (Exception var4) {
            return Integer.toString(var2);
         }
      }
   }
}

}
