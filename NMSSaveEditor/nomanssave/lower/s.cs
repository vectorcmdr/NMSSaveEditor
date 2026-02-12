using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class s : ActionListener {
   p I;

   s(p var1) {
      this.I = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.I.Hide();
   }
}

}
