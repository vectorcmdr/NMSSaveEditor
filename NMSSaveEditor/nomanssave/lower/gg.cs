using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class gg {
   public eY nl;
   public gf nm;

   public gg(gf var1, eY var2) {
      this.nm = var1;
      this.nl = var2;
   }

   public string toString() {
      return this.nl.getValueAsString("ObjectID");
   }
}

}
