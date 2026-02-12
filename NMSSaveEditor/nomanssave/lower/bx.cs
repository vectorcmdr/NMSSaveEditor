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

public class bx : G {
   // $FF: synthetic field
   bl er;

   bx(bl var1) {
      this.er = var1;
   }

   protected string g(string var1) {
      if (bl.b(this.er) < 0) {
         return "";
      } else {
         try {
            var1 = var1.Trim();
            if (!var1.Equals(bl.c(this.er)[bl.b(this.er)].cU())) {
               bl.c(this.er)[bl.b(this.er)].ah(var1);
               bl.n(this.er).SetText(var1);
            }

            return var1;
         } catch (Exception var3) {
            return bl.c(this.er)[bl.b(this.er)].cU();
         }
      }
   }
}

}
