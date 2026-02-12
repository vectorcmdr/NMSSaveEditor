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

   protected string g(string var1) {
      if (bd.a(this.dP) == null) {
         return "";
      } else {
         var1 = var1.Trim();
         if (!var1.equals(bd.a(this.dP).getName())) {
            bd.a(this.dP).setName(var1);
            bd.b(this.dP).setText(var1);
         }

         return var1;
      }
   }
}

}
