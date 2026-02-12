using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class fK : fQ, fr {
   fJ mt;

   fK(fJ var1) {
      base(var1, "accountdata.hg", -1, true);
      this.mt = var1;
   }

   public eY M() {
      return this.a(eG.jW);
   }

   public void k(eY var1) {
      this.a("accountdata", (fn)null, (string)null, (string)null);
      this.a(var1, false);
   }
}

}
