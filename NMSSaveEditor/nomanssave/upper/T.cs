using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class T : EventHandler {
   Q bD;

   T(Q var1) {
      this.bD = var1;
   }

   public void actionPerformed(EventArgs var1) {
      Q.a(this.bD, new W(int.Parse(Q.a(this.bD).Text), int.Parse(Q.d(this.bD).Text)));
      this.bD.Hide();
   }
}

}
