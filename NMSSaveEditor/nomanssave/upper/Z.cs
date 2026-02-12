using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class Z : ActionListener {
   X bV;
   private Application bv;

   Z(X var1, Application var2) {
      this.bV = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      gj var2 = (gj)X.k(this.bV).SelectedItem;
      if (var2 != null) {
         this.bv.a(var2);
      }

   }
}

}
