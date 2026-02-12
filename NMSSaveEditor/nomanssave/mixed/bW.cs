using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class bW : ActionListener {
   public bS fk;
   public int fl;
   public int fm;

   public bW(bS var1, int var2, int var3) {
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


#else

public class bW
{
   public bW() { }
   public bW(params object[] args) { }
   public bS fk = default;
   public int fl = 0;
   public int fm = 0;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
