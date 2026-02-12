using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class cb : ActionListener {
   public bS fk;
   public int fl;
   public int fm;

   public cb(bS var1, int var2, int var3) {
      this.fk = var1;
      this.fl = var2;
      this.fm = var3;
   }

   public void actionPerformed(EventArgs var1) {
      bool var2 = bS.d(this.fk).getState();
      bO.a(bS.j(this.fk)).a(this.fl, this.fm, var2);
      bS.c(this.fk);
   }
}


#else

public class cb
{
   public cb() { }
   public cb(params object[] args) { }
   public bS fk = default;
   public int fl = 0;
   public int fm = 0;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
