using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class ao : ActionListener {
   aj cg;

   public ao(aj var1) {
      this.cg = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.cg.Hide();
   }
}

}
