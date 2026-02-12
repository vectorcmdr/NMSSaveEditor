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

public class fB : fH, fr {
   // $FF: synthetic field
   fA ma;

   fB(fA var1) {
      base(var1, "savedata00.hg", true);
      this.ma = var1;
   }

   public eY M() {
      return fA.b(this.readBytes(), eG.jW);
   }

   public void k(eY var1) {
      this.a("ps4_accountdata", (fn)null, (string)null, (string)null);
      this.writeBytes(fA.l(var1));
   }
}

}
