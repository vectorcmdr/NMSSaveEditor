using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class dR : ActionListener {
   public dN ia;
   public Application bv;

   public dR(dN var1, Application var2) {
      this.ia = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      int var2 = dN.p(this.ia).SelectedIndex;
      if (var2 >= 0 && var2 < dN.a(this.ia).Length) {
         this.bv.a(dN.a(this.ia)[var2]);
      }
   }
}


#else

public class dR
{
   public dR() { }
   public dR(params object[] args) { }
   public dN ia = default;
   public Application bv = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
