using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class bT : ActionListener {
   bS fk;
   private int fl;
   private int fm;

   bT(bS var1, int var2, int var3) {
      this.fk = var1;
      this.fl = var2;
      this.fm = var3;
   }

   public void actionPerformed(EventArgs var1) {
      if (bO.a(bS.j(this.fk)).dp() || en.aS()) {
         if (bS.b(this.fk).Checked) {
            bO.a(bS.j(this.fk)).i(this.fl, this.fm);
         } else {
            if (bO.a(bS.j(this.fk)).f(this.fl, this.fm) != null) {
               bS.b(this.fk).Checked = (true);
               bO.b(bS.j(this.fk)).c("Cannot disable slots that are in use!");
               return;
            }

            bO.a(bS.j(this.fk)).j(this.fl, this.fm);
         }

         bS.c(this.fk);
      }
   }
}

}
