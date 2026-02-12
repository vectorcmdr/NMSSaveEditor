using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class aG : ActionListener {
   public aD cB;

   public aG(aD var1) {
      this.cB = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.cB.Hide();
   }
}


#else

public class aG
{
   public aG() { }
   public aG(params object[] args) { }
   public aD cB = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
