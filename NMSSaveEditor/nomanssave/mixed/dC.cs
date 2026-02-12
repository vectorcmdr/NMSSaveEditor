using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class dC : ActionListener {
   public dz hu;

   public dC(dz var1) {
      this.hu = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.hu.Hide();
   }
}


#else

public class dC
{
   public dC() { }
   public dC(params object[] args) { }
   public dz hu = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
