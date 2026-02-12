using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class cf : ActionListener {
   public bS fk;
   public int fl;
   public int fm;

   public cf(bS var1, int var2, int var3) {
      this.fk = var1;
      this.fl = var2;
      this.fm = var3;
   }

   public void actionPerformed(EventArgs var1) {
      gu var2 = bO.a(bS.j(this.fk)).f(this.fl, this.fm);
      if (var2 != null) {
         var2.c(0.0D);
         var2.e(true);
         bS.c(this.fk);
      }

   }
}


#else

public class cf
{
   public cf() { }
   public cf(params object[] args) { }
   public bS fk = default;
   public int fl = 0;
   public int fm = 0;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
