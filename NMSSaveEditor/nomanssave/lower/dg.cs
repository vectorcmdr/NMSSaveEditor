using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class dg : ActionListener {
   public dd gW;

   public dg(dd var1) {
      this.gW = var1;
   }

   public void actionPerformed(EventArgs var1) {
      dd.a(this.gW, dd.b(this.gW).SelectedIndex);
      this.gW.Hide();
   }
}



}
