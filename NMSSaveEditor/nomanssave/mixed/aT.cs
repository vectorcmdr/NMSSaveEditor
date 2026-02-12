using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class aT : ActionListener {
   aQ dr;

   public aT(aQ var1) {
      this.dr = var1;
   }

   public void actionPerformed(EventArgs var1) {
      aQ.a(this.dr, new Size(int.Parse(aQ.a(this.dr).Text), int.Parse(aQ.e(this.dr).Text)));
      this.dr.Hide();
   }
}

}
