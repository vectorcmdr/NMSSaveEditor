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

public class be : G {
   // $FF: synthetic field
   public bd dP;

   public be(bd var1) {
      this.dP = var1;
   }

   public string g(string var1) {
      if (bd.a(this.dP) == null) {
         return "";
      } else {
         var1 = var1.Trim();
         if (!var1.Equals(bd.a(this.dP).Name)) {
            bd.a(this.dP).setName(var1);
            bd.b(this.dP).SetText(var1);
         }

         return var1;
      }
   }
}

}
