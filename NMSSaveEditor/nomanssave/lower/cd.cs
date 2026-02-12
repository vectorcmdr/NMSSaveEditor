using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class cd : ActionListener {
   public bS fk;
   public int fl;
   public int fm;

   public cd(bS var1, int var2, int var3) {
      this.fk = var1;
      this.fl = var2;
      this.fm = var3;
   }

   public void actionPerformed(EventArgs var1) {
      gu var2 = bO.a(bS.j(this.fk)).f(this.fl, this.fm);
      if (var2 != null) {
         cg.a((Container)bS.j(this.fk), (gQ)var2);
         bS.c(this.fk);
      }

   }
}


#else

public class cd
{
   public cd() { }
   public cd(params object[] args) { }
   public bS fk = default;
   public int fl = 0;
   public int fm = 0;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
