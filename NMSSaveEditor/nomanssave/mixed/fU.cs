using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class fU : fX, fr {
   public fT mN;

   public fU(fT var1) {
      base(var1, "AccountData");
      this.mN = var1;
   }

   public eY M() {
      return this.a(eG.jW);
   }

   public void k(eY var1) {
      this.a("msaccountdata", (fn)null);
      this.h(var1);
   }
}

}
