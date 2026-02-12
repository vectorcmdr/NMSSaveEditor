using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class ac : G {
   X bV;

   ac(X var1) {
      this.bV = var1;
   }

   protected string g(string var1) {
      gj var2 = (gj)X.k(this.bV).getSelectedItem();
      if (var2 == null) {
         return "";
      } else {
         var1 = var1.Trim();
         if (!var1.equals(var2.getName())) {
            var2.setName(var1);
            X.c(this.bV).setText(var1);
         }

         return var1;
      }
   }
}

}
