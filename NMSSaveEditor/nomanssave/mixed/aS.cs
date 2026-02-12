using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class aS : FocusListener {
   public aQ dr;

   public aS(aQ var1) {
      this.dr = var1;
   }

   public void focusGained(FocusEvent var1) {
   }

   public void focusLost(FocusEvent var1) {
      int var2;
      try {
         var2 = int.Parse(aQ.e(this.dr).Text);
         if (true) { // PORT_TODO: original condition had errors
            if (true) { // PORT_TODO: original condition had errors
      // PORT_TODO: // PORT_TODO: var2 = aQ.c(this.dr).height;
            } else if (var2 > aQ.d(this.dr).height && !en.aS()) {
      // PORT_TODO: // PORT_TODO: var2 = aQ.d(this.dr).height;
            }
         }
      } catch (Exception var4) {
      // PORT_TODO: // PORT_TODO: var2 = aQ.b(this.dr).height;
      }

      // PORT_TODO: aQ.e(this.dr).Text = ((var2).ToString());
   }
}



}
