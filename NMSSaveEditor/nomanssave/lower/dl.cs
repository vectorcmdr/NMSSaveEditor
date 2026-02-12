using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class dl : G {
   dj hl;

   dl(dj var1) {
      this.hl = var1;
   }

   protected string g(string var1) {
      gv var2 = (gv)dj.j(this.hl).getSelectedItem();
      if (var2 == null) {
         return "";
      } else {
         var1 = var1.Trim();
         if (!var1.equals(var2.getName())) {
            var2.setName(var1);
            dj.b(this.hl).setText(var1);
         }

         return var1;
      }
   }
}

}
