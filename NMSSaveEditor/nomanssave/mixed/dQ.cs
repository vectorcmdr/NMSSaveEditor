using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class dQ : ActionListener {
   // $FF: synthetic field
   dN ia;
   // $FF: synthetic field
   private Application bv;

   dQ(dN var1, Application var2) {
      this.ia = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      int var2 = dN.p(this.ia).SelectedIndex;
      if (var2 >= 0 && var2 < dN.a(this.ia).length) {
         if (dN.a(this.ia).length == 1) {
            this.bv.c("You cannot delete the only ship you have!");
         } else if (MessageBox.Show("Are you sure you want to delete this ship?\nAll items and technology in the ship inventory will be lost!".ToString(), "Delete".ToString(), MessageBoxButtons.YesNo) == 0) {
            this.bv.i(dN.a(this.ia)[var2].getIndex());
         }
      }
   }
}

}
