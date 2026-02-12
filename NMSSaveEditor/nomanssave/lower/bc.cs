using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class bc : EventHandler {
   ba dB;
   private Button dD;
   private G dC;

   bc(ba var1, Button var2, G var3) {
      this.dB = var1;
      this.dD = var2;
      this.dC = var3;
   }

   public void propertyChange(EventArgs var1) {
      this.dD.Enabled = (this.dC.Enabled);
   }
}

}
