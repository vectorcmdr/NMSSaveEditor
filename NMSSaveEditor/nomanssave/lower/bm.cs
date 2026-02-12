using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class bm : ActionListener {
   public bl er;
   public Application bv;

   public bm(bl var1, Application var2) {
      this.er = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      if (bl.b(this.er) >= 0) {
         if (true) { // PORT_TODO: original condition had errors
            bl.a(this.er, this.bv.k(bl.c(this.er)[bl.b(this.er)].getIndex()));
            if (bl.c(this.er).Length > 0) {
               bl.e(this.er).setRowSelectionInterval(0, 0);
            } else {
               bl.e(this.er).clearSelection();
            }

            bl.e(this.er).Refresh();
         }
      }
   }
}



}
