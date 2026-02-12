using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class bx : G {
   bl er;

   bx(bl var1) {
      this.er = var1;
   }

   protected override string g(string var1) {
      if (bl.b(this.er) < 0) {
         return "";
      } else {
         try {
            var1 = var1.Trim();
            if (!var1.Equals(bl.c(this.er)[bl.b(this.er)].cU())) {
               bl.c(this.er)[bl.b(this.er)].ah(var1);
               bl.n(this.er).Text = (var1);
            }

            return var1;
         } catch (Exception var3) {
            return bl.c(this.er)[bl.b(this.er)].cU();
         }
      }
   }
}

}
