using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class R : FocusListener {
   public Q bD;

   public R(Q var1) {
      this.bD = var1;
   }

   public void focusGained(FocusEvent var1) {
   }

   public void focusLost(FocusEvent var1) {
      int var2;
      try {
         // PORT_TODO: var2 = int.Parse(Q.a(this.bD).Text);
         if (true) { // PORT_TODO: original condition had errors
            var2 = (int)Math.Round((double)var2 / 250.0D);
         }

         if (var2 < Q.b(this.bD)) {
            var2 = Q.b(this.bD);
         }
      } catch (Exception var4) {
         var2 = Q.c(this.bD).bE;
      }

      // PORT_TODO: Q.a(this.bD).Text = ((var2).ToString());
   }
}



}
