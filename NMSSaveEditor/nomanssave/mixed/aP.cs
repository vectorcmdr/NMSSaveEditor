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

public class aP : G {
   // $FF: synthetic field
   aJ dj;

   aP(aJ var1) {
      this.dj = var1;
   }

   protected string g(string var1) {
      if (aJ.a(this.dj) == null) {
         return "";
      } else {
         long var2 = aJ.a(this.dj).dL();

         try {
            long var4 = hf.a(var1, 0L, 4294967295L);
            if (var4 != var2) {
               aJ.a(this.dj).g(var4);
            }

            return Convert.ToString(var4);
         } catch (Exception var6) {
            return Convert.ToString(var2);
         }
      }
   }
}

}
