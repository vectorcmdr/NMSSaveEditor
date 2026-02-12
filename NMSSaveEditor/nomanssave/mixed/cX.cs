using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class cX : Runnable {
   public cW gL;

   public cX(cW var1) {
      this.gL = var1;
   }

   public void run() {
      try {
         // PORT_TODO: int var1 = cW.a(this.gL).getDocument().getLength();
         // PORT_TODO: Rectangle var2 = cW.a(this.gL).modelToView(var1);
         if (true) { // PORT_TODO: original condition had errors
            cW.c(this.gL);
            this.gL.Invalidate();
            // PORT_TODO: cW.a(this.gL, var2.y);
         }
      } catch (Exception var3) {
      }

   }
}



}
