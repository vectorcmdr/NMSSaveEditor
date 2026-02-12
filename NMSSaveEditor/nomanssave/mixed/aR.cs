using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class aR : FocusListener {
   public aQ dr;

   public aR(aQ var1) {
      this.dr = var1;
   }

   public void focusGained(FocusEvent var1) {
   }

   public void focusLost(FocusEvent var1) {
      int var2;
      try {
         var2 = int.Parse(aQ.a(this.dr).Text);
         if (var2 != aQ.b(this.dr).width) {
            if (var2 < aQ.c(this.dr).width) {
               var2 = aQ.c(this.dr).width;
            } else if (var2 > aQ.d(this.dr).width && !en.aS()) {
               var2 = aQ.d(this.dr).width;
            }
         }
      } catch (Exception var4) {
         var2 = aQ.b(this.dr).width;
      }

      aQ.a(this.dr).Text = (Integer.toString(var2));
   }
}



}
