using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class ed : ActionListener {
   ec ik;
   public int il;

   public ed(ec var1, int var2) {
      this.ik = var1;
      this.il = var2;
   }

   public void actionPerformed(EventArgs var1) {
      if (ec.a(this.ik).Checked ^ eb.a(ec.h(this.ik))[this.il].Enabled) {
         bool var2 = ec.a(this.ik).Checked;
         eb.a(ec.h(this.ik))[this.il].Enabled = (var2);
         ec.b(this.ik).Enabled = (var2);
         ec.c(this.ik).Enabled = (var2);
         ec.d(this.ik).Enabled = (var2);
         ec.e(this.ik).Enabled = (var2);
         ec.f(this.ik).Enabled = (var2);
      }

   }
}

}
