using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class ac : G {
   public X bV;

   public ac(X var1) {
      this.bV = var1;
   }

   public override string g(string var1) {
      gj var2 = (gj)X.k(this.bV).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         var1 = var1.Trim();
         if (!var1.Equals(var2.Name)) {
            var2.setName(var1);
            X.c(this.bV).Text = (var1);
         }

         return var1;
      }
   }
}

}
