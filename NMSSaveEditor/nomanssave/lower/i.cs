using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class i : EventHandler {
   h z;

   i(h var1) {
      this.z = var1;
   }

   public void actionPerformed(EventArgs var1) {
      string var2 = h.a(this.z).Text;
      h.a(this.z, ey.b(h.b(this.z), var2));
      h.c(this.z);
      if (h.d(this.z).Count == 0) {
         MessageBox.showOptionDialog(this.z, "Item not found.", "Warning", 0, 2, (Icon)null, new object[]{"OK"}, (object)null);
      }

   }
}

}
