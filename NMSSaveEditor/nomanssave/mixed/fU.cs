using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

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


#else

public class fU
{
   public fU() { }
   public fU(params object[] args) { }
   public fT mN = default;
   public eY M() { return default; }
   public void k(eY var1) { }
}

#endif

}
