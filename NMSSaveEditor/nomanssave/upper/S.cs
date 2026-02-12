using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class S : FocusListener {
   public Q bD;

   public S(Q var1) {
      this.bD = var1;
   }

   public void focusGained(FocusEvent var1) {
   }

   public void focusLost(FocusEvent var1) {
      int var2;
      try {
         var2 = int.Parse(Q.d(this.bD).Text);
         if (var2 < Q.e(this.bD)) {
            var2 = Q.e(this.bD);
         }
      } catch (Exception var4) {
         var2 = Q.c(this.bD).bF;
      }

      Q.d(this.bD).Text = ((var2).ToString());
   }
}



}
