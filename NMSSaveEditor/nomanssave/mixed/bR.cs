using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class bR : ActionListener {
   public bO eX;

   public bR(bO var1) {
      this.eX = var1;
   }

   public void actionPerformed(EventArgs var1) {
      if (bO.a(this.eX) != null) {
         Size var2 = aQ.a(this.eX, bO.a(this.eX).getSize(), bO.a(this.eX).dm(), bO.a(this.eX).dn());
         if (var2 != null && bO.a(this.eX).a(var2)) {
            bO.c(this.eX);
         }

      }
   }
}


#else

public class bR
{
   public bR() { }
   public bR(params object[] args) { }
   public bO eX = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
