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

public class fK : fQ, fr {
   // $FF: synthetic field
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
