using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class dQ : ActionListener {
   public dN ia;
   public Application bv;

   public dQ(dN var1, Application var2) {
      this.ia = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      int var2 = dN.p(this.ia).SelectedIndex;
      if (var2 >= 0 && var2 < dN.a(this.ia).Length) {
         if (dN.a(this.ia).Length == 1) {
            this.bv.c("You cannot delete the only ship you have!");
         } else if (MessageBox.Show(this.ia, "Are you sure you want to delete this ship?\nAll items and technology in the ship inventory will be lost!", "Delete", 2) == 0) {
            this.bv.i(dN.a(this.ia)[var2].getIndex());
         }
      }
   }
}



}
