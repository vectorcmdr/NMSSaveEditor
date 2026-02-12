using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class ai : ActionListener {
   public X bV;
   public Application bv;

   public ai(X var1, Application var2) {
      this.bV = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      int var2 = X.k(this.bV).SelectedIndex;
      if (var2 >= 0 && var2 < X.a(this.bV).Length) {
         if (MessageBox.Show(this.bV, "Are you sure you want to delete this companion?", "Delete", 2) == 0) {
            this.bv.a(X.a(this.bV)[var2].cL(), X.a(this.bV)[var2].getIndex());
         }
      }
   }
}



}
