using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class M : G {
   public I bt;

   public M(I var1) {
      this.bt = var1;
   }

   public override string g(string var1) {
      gf var2 = (gf)I.j(this.bt).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         var1 = var1.Trim();
         if (!var1.Equals(var2.Name)) {
            var2.setName(var1);
            I.f(this.bt).Text = (var1);
         }

         return var1;
      }
   }
}

}
