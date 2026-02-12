using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class V : ActionListener {
   public Q bD;

   public V(Q var1) {
      this.bD = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.bD.Hide();
   }
}


#else

public class V
{
   public V() { }
   public V(params object[] args) { }
   public Q bD = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
