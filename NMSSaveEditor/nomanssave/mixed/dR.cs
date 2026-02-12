using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class dR : EventHandler {
   dN ia;
   private Application bv;

   dR(dN var1, Application var2) {
      this.ia = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      int var2 = dN.p(this.ia).getSelectedIndex();
      if (var2 >= 0 && var2 < dN.a(this.ia).Length) {
         this.bv.a(dN.a(this.ia)[var2]);
      }
   }
}

}
