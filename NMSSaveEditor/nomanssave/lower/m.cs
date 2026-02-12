using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class m : EventHandler {
   h z;

   m(h var1) {
      this.z = var1;
   }

   public void actionPerformed(EventArgs var1) {
      h.a(this.z, (ey)h.j(this.z).getSelectedItem());
      this.z.setVisible(false);
   }
}

}
