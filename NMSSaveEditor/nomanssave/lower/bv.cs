using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class bv : G {
   bl er;

   bv(bl var1) {
      this.er = var1;
   }

   protected override string g(string var1) {
      if (bl.b(this.er) < 0) {
         return "";
      } else {
         var1 = var1.Trim();
         if (!var1.Equals(bl.c(this.er)[bl.b(this.er)].Name)) {
            bl.c(this.er)[bl.b(this.er)].setName(var1);
            bl.j(this.er).Text = (var1);
            bl.e(this.er).Refresh();
         }

         return var1;
      }
   }
}

}
