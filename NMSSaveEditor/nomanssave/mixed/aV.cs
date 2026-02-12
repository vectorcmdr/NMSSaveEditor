using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class aV : ActionListener {
   public aQ dr;

   public aV(aQ var1) {
      this.dr = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.dr.Hide();
   }
}


#else

public class aV
{
   public aV() { }
   public aV(params object[] args) { }
   public aQ dr = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
