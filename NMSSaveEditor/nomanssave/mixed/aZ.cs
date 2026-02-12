using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class aZ : ActionListener {
   public aW dy;

   public aZ(aW var1) {
      this.dy = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.dy.Hide();
   }
}


#else

public class aZ
{
   public aZ() { }
   public aZ(params object[] args) { }
   public aW dy = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
