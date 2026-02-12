using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class dB : ActionListener {
   dz hu;

   dB(dz var1) {
      this.hu = var1;
   }

   public void actionPerformed(EventArgs var1) {
      int var2 = dz.b(this.hu).SelectedIndex;
      if (var2 < 0 || dz.a(this.hu)[var2].Count == 0 || MessageBox.Show(this.hu, "You are about to overwrite this save slot, are you sure you want to do this?", "Warning", 2) == 0) {
         dz.a(this.hu, var2);
         this.hu.Hide();
      }
   }
}

}
