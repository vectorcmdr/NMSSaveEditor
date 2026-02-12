using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class br : EventHandler {
   bl er;
   private Application bv;

   br(bl var1, Application var2) {
      this.er = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      if (bl.b(this.er) >= 0) {
         string var2 = hg.eo().ToString();
         bl.a(this.er, this.bv.a(bl.c(this.er)[bl.b(this.er)].getIndex(), var2));
         bl.g(this.er).Enabled = (bl.c(this.er).Length < 30 || en.aS());
         bl.e(this.er).Refresh();
      }
   }
}

}
