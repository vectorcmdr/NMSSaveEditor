using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class dh : ActionListener {
   public dd gW;

   public dh(dd var1) {
      this.gW = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.gW.Hide();
   }
}


#else

public class dh
{
   public dh() { }
   public dh(params object[] args) { }
   public dd gW = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
