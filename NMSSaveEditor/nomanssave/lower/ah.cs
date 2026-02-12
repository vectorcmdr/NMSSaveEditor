using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class ah : ActionListener {
   X bV;

   ah(X var1) {
      this.bV = var1;
   }

   public void actionPerformed(EventArgs var1) {
      gj var2 = (gj)X.k(this.bV).SelectedItem;
      if (var2 != null) {
         if (X.h(this.bV).Checked ^ var2.cQ()) {
            var2.d(X.h(this.bV).Checked);
         }

      }
   }
}

}
