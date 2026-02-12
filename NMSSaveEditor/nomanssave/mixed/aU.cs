using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class aU : ActionListener {
   public aQ dr;

   public aU(aQ var1) {
      this.dr = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.dr.Hide();
   }
}


#else

public class aU
{
   public aU() { }
   public aU(params object[] args) { }
   public aQ dr = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
