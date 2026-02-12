using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class fU : fX, fr {
   // $FF: synthetic field
   fT mN;

   fU(fT var1) {
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
