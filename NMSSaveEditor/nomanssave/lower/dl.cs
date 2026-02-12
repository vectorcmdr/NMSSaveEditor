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

   protected override string g(string var1) {
      gv var2 = (gv)dj.j(this.hl).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         var1 = var1.Trim();
         if (!var1.Equals(var2.Name)) {
            var2.setName(var1);
            dj.b(this.hl).Text = (var1);
         }

         return var1;
      }
   }
}

}
