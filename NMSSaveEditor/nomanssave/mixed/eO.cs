using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class eO {
   public string id;

   public eO(string var1) {
      this.id = var1;
   }

   public bool equals(object var1) {
      eM var2 = (eM)var1;
      return var2.jY ? this.id.StartsWith(var2.id + "#") : this.id.Equals(var2.id);
   }
}

}
