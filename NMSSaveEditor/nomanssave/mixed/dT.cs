using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class dT : G {
   dN ia;

   public dT(dN var1) {
      this.ia = var1;
   }

   protected override string g(string var1) {
      gH var2 = (gH)dN.p(this.ia).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         var1 = var1.Trim();
         if (!var1.Equals(var2.Name)) {
            var2.setName(var1);
            dN.b(this.ia).Text = (var1);
         }

         return var1;
      }
   }
}

}
