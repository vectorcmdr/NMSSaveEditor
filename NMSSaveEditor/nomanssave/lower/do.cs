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

public class do : G {
   // $FF: synthetic field
   dj hl;

   do(dj var1) {
      this.hl = var1;
   }

   protected string g(string var1) {
      gv var2 = (gv)dj.j(this.hl).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         double var3 = var2.dG();

         try {
            double var5 = hf.a(var1, 0.0D, 1000.0D);
            if (var5 != var3) {
               var2.e(var5);
            }

            return Double.toString(var5);
         } catch (Exception var7) {
            return Double.toString(var3);
         }
      }
   }
}

}
