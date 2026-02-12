using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class bY : ActionListener {
   public bS fk;

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


#else

public class bY
{
   public bY() { }
   public bY(params object[] args) { }
   public bS fk = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
