using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class ce : EventHandler {
   bS fk;
   private int fl;
   private int fm;

   ce(bS var1, int var2, int var3) {
      this.fk = var1;
      this.fl = var2;
      this.fm = var3;
   }

   public void actionPerformed(EventArgs var1) {
      if (bO.a(bS.j(this.fk)).f(this.fl, this.fm) == null) {
         bO.a(bS.j(this.fk), this.fk);
      }

   }
}

}
