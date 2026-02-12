using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class ae : G {
   X bV;

   ae(X var1) {
      this.bV = var1;
   }

   protected string g(string var1) {
      gj var2 = (gj)X.k(this.bV).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         var1 = var1.Trim();
         if (!var1.Equals(var2.cN())) {
            var2.ab(var1);
            X.e(this.bV).Text = (var1);
         }

         return var1;
      }
   }
}

}
