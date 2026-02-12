using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class bW : EventHandler {
   bS fk;
   private int fl;
   private int fm;

   bW(bS var1, int var2, int var3) {
      this.fk = var1;
      this.fl = var2;
      this.fm = var3;
   }

   public void actionPerformed(EventArgs var1) {
      if (bO.a(bS.j(this.fk)).g(this.fl, this.fm)) {
         bO.a(bS.j(this.fk)).g(this.fl, this.fm);
         bS.c(this.fk);
      }

   }
}

}
