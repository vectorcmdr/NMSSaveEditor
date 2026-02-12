using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class ed : EventHandler {
   ec ik;
   private int il;

   ed(ec var1, int var2) {
      this.ik = var1;
      this.il = var2;
   }

   public void actionPerformed(EventArgs var1) {
      if (ec.a(this.ik).isSelected() ^ eb.a(ec.h(this.ik))[this.il].isEnabled()) {
         bool var2 = ec.a(this.ik).isSelected();
         eb.a(ec.h(this.ik))[this.il].setEnabled(var2);
         ec.b(this.ik).setEnabled(var2);
         ec.c(this.ik).setEnabled(var2);
         ec.d(this.ik).setEnabled(var2);
         ec.e(this.ik).setEnabled(var2);
         ec.f(this.ik).setEnabled(var2);
      }

   }
}

}
