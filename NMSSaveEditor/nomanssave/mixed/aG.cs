using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class aG : EventHandler {
   aD cB;

   aG(aD var1) {
      this.cB = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.cB.Hide();
   }
}

}
