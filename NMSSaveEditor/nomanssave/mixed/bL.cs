using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class bL : TextBox {
   bK eB;
   bE ey;

   bL(bE var1, bK var2, bool var3) {
      this.ey = var1;
      this.eB = var2;
      this.setEnabled(var3);
      this.addFocusListener(new bM(this, var2));
   }

   void ac() {
      string var1;
      if (bE.a(this.ey) == null) {
         var1 = "";
      } else {
         var1 = this.eB.ab();
      }

      this.setText(var1);
   }
   static bE a(bL var0) {
      return var0.ey;
   }
}

}
