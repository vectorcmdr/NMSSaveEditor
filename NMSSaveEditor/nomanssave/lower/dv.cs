using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class dv : ActionListener {
   public du hp;
   public gF hq;

   public dv(du var1, gF var2) {
      this.hp = var1;
      this.hq = var2;
   }

   public void actionPerformed(EventArgs var1) {
      if (this.hq != null) {
      // PORT_TODO: // PORT_TODO: cg.a((Container)du.d(this.hp), (gQ)this.hq);
         du.c(this.hp);
      }

   }
}



}
