using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class cQ {
   string value;
   cN gt;

   cQ(cN var1, string var2) {
      this.gt = var1;
      this.value = var2;
   }

   public bool equals(Object var1) {
      if (var1 is string) {
         return this.value.Equals(var1);
      } else {
         return var1 is cS ? this.value.Equals(((cS)var1).filename) : base.Equals(var1);
      }
   }
}

}
