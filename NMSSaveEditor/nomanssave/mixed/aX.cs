using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class aX : ActionListener {
   aW dy;
   public cy dz;

   public aX(aW var1, cy var2) {
      this.dy = var1;
      this.dz = var2;
   }

   public void actionPerformed(EventArgs var1) {
      string var2 = aW.a(this.dy).Text;
      if (var2.length() > 0) {
         this.dz.a(var2, aW.b(this.dy).Checked, aW.c(this.dy).Checked, aW.d(this.dy).Checked);
      }

   }
}

}
