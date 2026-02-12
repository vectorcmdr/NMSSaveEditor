using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class dD : ActionListener {
   public dz hu;

   public dD(dz var1) {
      this.hu = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.hu.Hide();
   }
}


#else

public class dD
{
   public dD() { }
   public dD(params object[] args) { }
   public dz hu = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
