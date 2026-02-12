using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class eW {
   eV kE = new eV();

   public eW h(Object var1) {
      if (var1 != null && !fh.a(var1.GetType())) {
         throw new Exception("Unsupported type: " + var1.GetType().getSimpleName());
      } else {
         this.kE.e(var1);
         return this;
      }
   }

   public eV bC() {
      return this.kE;
   }
}

}
