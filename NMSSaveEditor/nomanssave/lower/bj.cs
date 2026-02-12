using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class bj : ActionListener {
   bd dP;
   private Application bv;

   bj(bd var1, Application var2) {
      this.dP = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      if (bd.a(this.dP) != null) {
         this.bv.a(bd.a(this.dP));
      }
   }
}

}
