using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class ak : ActionListener {
   public aj cg;

   public ak(aj var1) {
      this.cg = var1;
   }

   public void actionPerformed(EventArgs var1) {
      try {
         // PORT_TODO: aj.a(this.cg, hl.e(aj.a(this.cg).Text.Trim(), aj.b(this.cg).SelectedIndex));
         aj.c(this.cg);
      } catch (Exception var3) {
         MessageBox.Show("Invalid address value, please try again.", "Error");
      }
   }
}



}
