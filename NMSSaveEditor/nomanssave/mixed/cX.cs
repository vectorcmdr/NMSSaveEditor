using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class cX : Runnable {
   cW gL;

   cX(cW var1) {
      this.gL = var1;
   }

   public void run() {
      try {
         int var1 = cW.a(this.gL).getDocument().getLength();
         Rectangle var2 = cW.a(this.gL).modelToView(var1);
         if (var2 != null && var2.y != cW.b(this.gL)) {
            cW.c(this.gL);
            this.gL.repaint();
            cW.a(this.gL, var2.y);
         }
      } catch (Exception var3) {
      }

   }
}

}
