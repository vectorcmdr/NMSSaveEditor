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

public class aK : G {
   // $FF: synthetic field
   aJ dj;

   aK(aJ var1) {
      this.dj = var1;
   }

   protected string g(string var1) {
      if (aJ.a(this.dj) == null) {
         return "";
      } else {
         int var2 = aJ.a(this.dj).dM();

         try {
            int var3 = hf.b(var1, 1, 200);
            if (var3 != var2) {
               aJ.a(this.dj).aB(var3);
            }

            return Convert.ToString(var3);
         } catch (Exception var4) {
            return Convert.ToString(var2);
         }
      }
   }
}

}
