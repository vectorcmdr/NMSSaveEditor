using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class dG : G {
   dE hE;

   dG(dE var1) {
      this.hE = var1;
   }

   protected string g(string var1) {
      gE var2 = (gE)dE.a(this.hE).getSelectedItem();
      if (var2 == null) {
         return "";
      } else {
         var1 = var1.Trim();
         if (!var1.equals(var2.getName())) {
            var2.setName(var1);
            dE.c(this.hE).setText(var1);
         }

         return var1;
      }
   }
}

}
