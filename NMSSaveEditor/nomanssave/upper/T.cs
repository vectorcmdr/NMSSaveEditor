using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class T : ActionListener {
   public Q bD;

   public T(Q var1) {
      this.bD = var1;
   }

   public void actionPerformed(EventArgs var1) {
      // PORT_TODO: Q.a(this.bD, new W(int.Parse(Q.a(this.bD).Text), int.Parse(Q.d(this.bD).Text)));
      this.bD.Hide();
   }
}



}
