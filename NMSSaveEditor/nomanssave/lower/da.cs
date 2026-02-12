using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class da : ActionListener {
   public cY gR;

   public da(cY var1) {
      this.gR = var1;
   }

   public void actionPerformed(EventArgs var1) {
      cY.a(this.gR, cY.b(this.gR).SelectedIndex);
      this.gR.Hide();
   }
}



}
