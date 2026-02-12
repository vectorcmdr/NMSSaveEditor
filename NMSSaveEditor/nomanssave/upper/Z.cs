using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class Z : ActionListener {
   public X bV;
   public Application bv;

   public Z(X var1, Application var2) {
      this.bV = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      gj var2 = (gj)X.k(this.bV).SelectedItem;
      if (var2 != null) {
         this.bv.a(var2);
      }

   }
}


#else

public class Z
{
   public Z() { }
   public Z(params object[] args) { }
   public X bV = default;
   public Application bv = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
