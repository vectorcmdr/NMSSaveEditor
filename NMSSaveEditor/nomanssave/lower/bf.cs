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

public class bf : G {
   // $FF: synthetic field
   bd dP;

   bf(bd var1) {
      this.dP = var1;
   }

   protected string g(string var1) {
      if (bd.a(this.dP) == null) {
         return "";
      } else {
         try {
            var1 = var1.Trim();
            if (!var1.Equals(bd.a(this.dP).cU())) {
               bd.a(this.dP).ah(var1);
               bd.c(this.dP).SetText(var1);
            }

            return var1;
         } catch (Exception var3) {
            return bd.a(this.dP).cU();
         }
      }
   }
}

}
