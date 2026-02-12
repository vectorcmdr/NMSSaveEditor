using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class bz : EventHandler {
   bl er;

   bz(bl var1) {
      this.er = var1;
   }

   public void actionPerformed(EventArgs var1) {
      if (bl.b(this.er) >= 0) {
         hc.info("Repairing frigate damage");
         bl.c(this.er)[bl.b(this.er)].aw(0);
         bl.c(this.er)[bl.b(this.er)].ax(0);
         bl.y(this.er).Text = ("");
         bl.z(this.er).Hide();
      }
   }
}

}
