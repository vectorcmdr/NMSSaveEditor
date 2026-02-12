using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class bm : EventHandler {
   bl er;
   private Application bv;

   bm(bl var1, Application var2) {
      this.er = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      if (bl.b(this.er) >= 0) {
         if (MessageBox.showConfirmDialog(this.er, "Are you sure you want to delete this frigate?", "Delete", 2) == 0) {
            bl.a(this.er, this.bv.k(bl.c(this.er)[bl.b(this.er)].getIndex()));
            if (bl.c(this.er).Length > 0) {
               bl.e(this.er).setRowSelectionInterval(0, 0);
            } else {
               bl.e(this.er).clearSelection();
            }

            bl.e(this.er).updateUI();
         }
      }
   }
}

}
