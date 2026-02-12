using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class aY : ActionListener {
   public aW dy;

   public aY(aW var1) {
      this.dy = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.dy.Hide();
   }
}


#else

public class aY
{
   public aY() { }
   public aY(params object[] args) { }
   public aW dy = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
