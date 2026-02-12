using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class cj : EventHandler {
   cg fF;

   cj(cg var1) {
      this.fF = var1;
   }

   public void actionPerformed(EventArgs var1) {
      cg.fE.setVisible(false);
   }
}

}
