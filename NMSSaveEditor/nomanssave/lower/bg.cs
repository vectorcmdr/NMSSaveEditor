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

public class bg : G {
   // $FF: synthetic field
   bd dP;

   bg(bd var1) {
      this.dP = var1;
   }

   protected string g(string var1) {
      if (bd.a(this.dP) == null) {
         return "";
      } else {
         try {
            var1 = hg.aB(var1).ToString();
            if (!var1.Equals(bd.a(this.dP).cV())) {
               bd.a(this.dP).ai(var1);
            }

            return var1;
         } catch (Exception var3) {
            return bd.a(this.dP).cV();
         }
      }
   }
}

}
