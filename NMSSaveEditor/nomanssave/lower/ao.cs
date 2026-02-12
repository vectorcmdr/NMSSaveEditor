using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class ao : EventHandler {
   aj cg;

   ao(aj var1) {
      this.cg = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.cg.setVisible(false);
   }
}

}
