using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class i : ActionListener {
   public h z;

   public i(h var1) {
      this.z = var1;
   }

   public void actionPerformed(EventArgs var1) {
      // PORT_TODO: string var2 = h.a(this.z).Text;
      // PORT_TODO: h.a(this.z, ey.b(h.b(this.z), var2));
      // PORT_TODO: h.c(this.z);
      if (h.d(this.z).Count == 0) {
         MessageBox.Show("Item not found.", "Warning");
      }

   }
}



}
