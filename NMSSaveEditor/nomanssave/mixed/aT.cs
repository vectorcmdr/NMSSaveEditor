using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class aT : EventHandler {
   aQ dr;

   aT(aQ var1) {
      this.dr = var1;
   }

   public void actionPerformed(EventArgs var1) {
      aQ.a(this.dr, new Size(int.Parse(aQ.a(this.dr).getText()), int.Parse(aQ.e(this.dr).getText())));
      this.dr.setVisible(false);
   }
}

}
