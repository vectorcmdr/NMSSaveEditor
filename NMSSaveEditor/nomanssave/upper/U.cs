using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class U : ActionListener {
   public Q bD;

   public U(Q var1) {
      this.bD = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.bD.Hide();
   }
}

}
