using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class bf : G {
   bd dP;

   bf(bd var1) {
      this.dP = var1;
   }

   protected string g(string var1) {
      if (bd.a(this.dP) == null) {
         return "";
      } else {
         try {
            var1 = var1.Trim();
            if (!var1.equals(bd.a(this.dP).cU())) {
               bd.a(this.dP).ah(var1);
               bd.c(this.dP).setText(var1);
            }

            return var1;
         } catch (Exception var3) {
            return bd.a(this.dP).cU();
         }
      }
   }
}

}
