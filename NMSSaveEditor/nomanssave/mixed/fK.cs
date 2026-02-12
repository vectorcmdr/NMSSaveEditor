using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{


public class fK : fQ, fr {
   public fJ mt;

public fK(fJ var1) : base(var1, "accountdata.hg", -1, true) {
      this.mt = var1;
   }

   public eY M() {
      return this.a(eG.jW);
   }

   public void k(eY var1) {
      this.a("accountdata", (fn)null, null, null);
      this.a(var1, false);
   }
}



}
