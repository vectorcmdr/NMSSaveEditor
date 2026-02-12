using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class ce : ActionListener {
   public bS fk;
   public int fl;
   public int fm;

   public ce(bS var1, int var2, int var3) {
      this.fk = var1;
      this.fl = var2;
      this.fm = var3;
   }

   public void actionPerformed(EventArgs var1) {
      if (bO.a(bS.j(this.fk)).f(this.fl, this.fm) == null) {
         bO.a(bS.j(this.fk), this.fk);
      }

   }
}


#else

public class ce
{
   public ce() { }
   public ce(params object[] args) { }
   public bS fk = default;
   public int fl = 0;
   public int fm = 0;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
