using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class M : G {
   I bt;

   M(I var1) {
      this.bt = var1;
   }

   protected string g(string var1) {
      gf var2 = (gf)I.j(this.bt).getSelectedItem();
      if (var2 == null) {
         return "";
      } else {
         var1 = var1.Trim();
         if (!var1.equals(var2.getName())) {
            var2.setName(var1);
            I.f(this.bt).setText(var1);
         }

         return var1;
      }
   }
}

}
