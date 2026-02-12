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

public class by : G {
   // $FF: synthetic field
   public bl er;

   public by(bl var1) {
      this.er = var1;
   }

   public string g(string var1) {
      if (bl.b(this.er) < 0) {
         return "";
      } else {
         try {
            var1 = hg.aB(var1).ToString();
            if (!var1.Equals(bl.c(this.er)[bl.b(this.er)].cV())) {
               bl.c(this.er)[bl.b(this.er)].ai(var1);
            }

            return var1;
         } catch (Exception var3) {
            return bl.c(this.er)[bl.b(this.er)].cV();
         }
      }
   }
}

}
