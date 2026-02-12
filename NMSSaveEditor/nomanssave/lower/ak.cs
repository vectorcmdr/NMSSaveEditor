using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class ak : EventHandler {
   aj cg;

   ak(aj var1) {
      this.cg = var1;
   }

   public void actionPerformed(EventArgs var1) {
      try {
         aj.a(this.cg, hl.e(aj.a(this.cg).Text.Trim(), aj.b(this.cg).SelectedIndex));
         aj.c(this.cg);
      } catch (Exception var3) {
         MessageBox.showOptionDialog(this.cg, "Invalid address value, please try again.", "Error", 0, 0, (Icon)null, new object[]{"Cancel"}, (object)null);
      }
   }
}

}
