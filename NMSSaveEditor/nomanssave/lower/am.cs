using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class am : ActionListener {
   aj cg;

   public am(aj var1) {
      this.cg = var1;
   }

   public void actionPerformed(EventArgs var1) {
      int var2 = aj.b(this.cg).SelectedIndex;
      if (var2 < 0) {
         MessageBox.Show("Invalid galaxy selected, please try again.", "Error");
      } else {
         if (MessageBox.Show("This will warp your character and ship to the specified system (not the portal itself).", "Confirm", MessageBoxButtons.OKCancel) == DialogResult.OK) {
            aj.a(this.cg, true);
            this.cg.Hide();
         }

      }
   }
}

}
