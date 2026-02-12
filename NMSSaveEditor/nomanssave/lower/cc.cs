using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class cc : EventHandler {
   bS fk;

   cc(bS var1) {
      this.fk = var1;
   }

   public void actionPerformed(EventArgs var1) {
      if (bO.a(bS.j(this.fk)).dr()) {
         bO.c(bS.j(this.fk));
      }

   }
}

}
