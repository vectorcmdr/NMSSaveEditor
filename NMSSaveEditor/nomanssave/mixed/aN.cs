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

public class aN : G {
   // $FF: synthetic field
   public aJ dj;
   // $FF: synthetic field
   public Application bv;

   public aN(aJ var1, Application var2) {
      this.dj = var1;
      this.bv = var2;
   }

   public string g(string var1) {
      if (aJ.a(this.dj) == null) {
         return "";
      } else {
         long var2 = aJ.a(this.dj).dJ();

         try {
            long var4 = hf.a(var1, 0L, 4294967295L);
            if (var4 != var2) {
               aJ.a(this.dj).e(var4);
               this.bv.C();
            }

            return Convert.ToString(var4);
         } catch (Exception var6) {
            return Convert.ToString(var2);
         }
      }
   }
}

}
