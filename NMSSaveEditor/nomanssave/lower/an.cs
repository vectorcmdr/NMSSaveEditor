using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class an : ActionListener {
   public aj cg;

   public an(aj var1) {
      this.cg = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.cg.Hide();
   }
}


#else

public class an
{
   public an() { }
   public an(params object[] args) { }
   public aj cg = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
