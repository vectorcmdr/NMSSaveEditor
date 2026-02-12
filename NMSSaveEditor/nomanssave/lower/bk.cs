using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class bk : ActionListener {
   bd dP;
   public Application bv;

   public bk(bd var1, Application var2) {
      this.dP = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      if (bd.a(this.dP) != null) {
         this.bv.b(bd.a(this.dP));
      }
   }
}

}
