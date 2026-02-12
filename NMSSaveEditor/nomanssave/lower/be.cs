using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class be : G {
   bd dP;

   be(bd var1) {
      this.dP = var1;
   }

   protected override string g(string var1) {
      if (bd.a(this.dP) == null) {
         return "";
      } else {
         var1 = var1.Trim();
         if (!var1.Equals(bd.a(this.dP).Name)) {
            bd.a(this.dP).setName(var1);
            bd.b(this.dP).Text = (var1);
         }

         return var1;
      }
   }
}

}
