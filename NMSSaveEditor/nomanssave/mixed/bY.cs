using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class bY : ActionListener {
   bS fk;

   public bY(bS var1) {
      this.fk = var1;
   }

   public void actionPerformed(EventArgs var1) {
      if (bO.a(bS.j(this.fk)).dp() || en.aS()) {
         if (bO.a(bS.j(this.fk)).dv()) {
            bO.c(bS.j(this.fk));
         }

      }
   }
}

}
