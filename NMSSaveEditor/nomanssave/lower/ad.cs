using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class ad : G {
   public X bV;

   public ad(X var1) {
      this.bV = var1;
   }

   public override string g(string var1) {
      gj var2 = (gj)X.k(this.bV).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         var1 = var1.Trim();
         if (!var1.Equals(var2.cK())) {
            var2.aa(var1);
            X.d(this.bV).Text = (var1);
         }

         return var1;
      }
   }
}

}
