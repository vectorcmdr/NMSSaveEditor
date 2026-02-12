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

public class R : FocusListener {
   // $FF: synthetic field
   Q bD;

   R(Q var1) {
      this.bD = var1;
   }

   public void focusGained(FocusEvent var1) {
   }

   public void focusLost(FocusEvent var1) {
      int var2;
      try {
         var2 = int.Parse(Q.a(this.bD).GetText());
         if (var2 % 250 != 0) {
            var2 = (int)Math.Round((double)var2 / 250.0D);
         }

         if (var2 < Q.b(this.bD)) {
            var2 = Q.b(this.bD);
         }
      } catch (Exception var4) {
         var2 = Q.c(this.bD).bE;
      }

      Q.a(this.bD).SetText(Convert.ToString(var2));
   }
}

}
