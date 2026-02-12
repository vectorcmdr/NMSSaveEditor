using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class aX : EventHandler {
   aW dy;
   private cy dz;

   aX(aW var1, cy var2) {
      this.dy = var1;
      this.dz = var2;
   }

   public void actionPerformed(EventArgs var1) {
      string var2 = aW.a(this.dy).getText();
      if (var2.length() > 0) {
         this.dz.a(var2, aW.b(this.dy).isSelected(), aW.c(this.dy).isSelected(), aW.d(this.dy).isSelected());
      }

   }
}

}
