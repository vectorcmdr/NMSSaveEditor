using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class O : ActionListener {
   I bt;
   private Application bv;

   O(I var1, Application var2) {
      this.bt = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      gf var2 = (gf)I.j(this.bt).SelectedItem;
      if (var2 != null && this.bv.b(var2)) {
         I.e(this.bt).Text = (Integer.toString(var2.cG()));
      }

   }
}

}
