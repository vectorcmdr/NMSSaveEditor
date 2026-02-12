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

public class bv : G {
   // $FF: synthetic field
   public bl er;

   public bv(bl var1) {
      this.er = var1;
   }

   public string g(string var1) {
      if (bl.b(this.er) < 0) {
         return "";
      } else {
         var1 = var1.Trim();
         if (!var1.Equals(bl.c(this.er)[bl.b(this.er)].Name)) {
            bl.c(this.er)[bl.b(this.er)].setName(var1);
            bl.j(this.er).SetText(var1);
            bl.e(this.er).updateUI();
         }
          return var1;
      }
   }
}

}
