using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class dw : EventHandler {
   du hp;
   private gF hq;

   dw(du var1, gF var2) {
      this.hp = var1;
      this.hq = var2;
   }

   public void actionPerformed(EventArgs var1) {
      if (this.hq != null) {
         dt.a(du.d(this.hp), this.hp);
      }

   }
}

}
