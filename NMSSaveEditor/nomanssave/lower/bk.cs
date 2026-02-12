using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class bk : ActionListener {
   public bd dP;
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


#else

public class bk
{
   public bk() { }
   public bk(params object[] args) { }
   public bd dP = default;
   public Application bv = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
