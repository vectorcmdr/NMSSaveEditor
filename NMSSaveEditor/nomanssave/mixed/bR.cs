using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class bR : ActionListener {
   public bO eX;

   public bR(bO var1) {
      this.eX = var1;
   }

   public void actionPerformed(EventArgs var1) {
      if (bO.a(this.eX) != null) {
         // PORT_TODO: Size var2 = aQ.a(this.eX, bO.a(this.eX).getSize(), bO.a(this.eX).dm(), bO.a(this.eX).dn());
         if (true) { // PORT_TODO: original condition had errors
            bO.c(this.eX);
         }

      }
   }
}



}
