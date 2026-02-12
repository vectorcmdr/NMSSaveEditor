using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

public class fB : fH, fr {
   public fA ma;

   public fB(fA var1) {
      base(var1, "savedata00.hg", true);
      this.ma = var1;
   }

   public eY M() {
      return fA.b(this.readBytes(), eG.jW);
   }

   public void k(eY var1) {
      this.a("ps4_accountdata", (fn)null, null, null);
      this.writeBytes(fA.l(var1));
   }
}


#else

public class fB
{
   public fB() { }
   public fB(params object[] args) { }
   public fA ma = default;
   public eY M() { return default; }
   public void k(eY var1) { }
}

#endif

}
