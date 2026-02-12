using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class dh : ActionListener {
   dd gW;

   public dh(dd var1) {
      this.gW = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.gW.Hide();
   }
}

}
