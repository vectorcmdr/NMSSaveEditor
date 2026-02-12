using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class dB : ActionListener {
   public dz hu;

   public dB(dz var1) {
      this.hu = var1;
   }

   public void actionPerformed(EventArgs var1) {
      int var2 = dz.b(this.hu).SelectedIndex;
      if (true) { // PORT_TODO: original condition had errors
         dz.a(this.hu, var2);
         this.hu.Hide();
      }
   }
}



}
