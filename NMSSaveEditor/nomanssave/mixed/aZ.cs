using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class aZ : ActionListener {
   aW dy;

   aZ(aW var1) {
      this.dy = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.dy.Hide();
   }
}

}
