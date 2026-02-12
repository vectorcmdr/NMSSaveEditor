using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class t : ActionListener {
   public p I;

   public t(p var1) {
      this.I = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.I.Hide();
   }
}


#else

public class t
{
   public t() { }
   public t(params object[] args) { }
   public p I = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
