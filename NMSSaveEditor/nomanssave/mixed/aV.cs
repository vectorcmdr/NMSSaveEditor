using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class aV : ActionListener {
   aQ dr;

   public aV(aQ var1) {
      this.dr = var1;
   }

   public void actionPerformed(EventArgs var1) {
      this.dr.Hide();
   }
}

}
