using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class af : G {
   public X bV;

   public af(X var1) {
      this.bV = var1;
   }

   protected override string g(string var1) {
      gj var2 = (gj)X.k(this.bV).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         var1 = var1.Trim();
         if (!var1.Equals(var2.cO())) {
            var2.ac(var1);
            X.f(this.bV).Text = (var1);
         }

         return var1;
      }
   }
}

}
