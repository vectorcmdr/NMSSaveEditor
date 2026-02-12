using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class eO {
   string id;

   eO(string var1) {
      this.id = var1;
   }

   public bool equals(Object var1) {
      eM var2 = (eM)var1;
      return var2.jY ? this.id.StartsWith(var2.id + "#") : this.id.equals(var2.id);
   }
}

}
