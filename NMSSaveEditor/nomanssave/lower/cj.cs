using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class cj : ActionListener {
   public cg fF;

   public cj(cg var1) {
      this.fF = var1;
   }

   public void actionPerformed(EventArgs var1) {
      cg.fE.Hide();
   }
}


#else

public class cj
{
   public cj() { }
   public cj(params object[] args) { }
   public cg fF = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
