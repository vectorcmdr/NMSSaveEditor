using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class cc : ActionListener {
   public bS fk;

   public cc(bS var1) {
      this.fk = var1;
   }

   public void actionPerformed(EventArgs var1) {
      if (bO.a(bS.j(this.fk)).dr()) {
         bO.c(bS.j(this.fk));
      }

   }
}


#else

public class cc
{
   public cc() { }
   public cc(params object[] args) { }
   public bS fk = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
