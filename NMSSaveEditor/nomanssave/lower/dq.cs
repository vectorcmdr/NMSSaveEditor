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

public class dq : ActionListener {
   // $FF: synthetic field
   public dj hl;
   // $FF: synthetic field
   public Application bv;

   public dq(dj var1, Application var2) {
      this.hl = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      int var2 = dj.j(this.hl).SelectedIndex;
      if (var2 >= 0 && var2 < dj.a(this.hl).length) {
         if (dj.a(this.hl).length == 1) {
            this.bv.c("You cannot delete the only multitool you have!");
         } else if (MessageBox.Show("Are you sure you want to delete this multitool?\nAll technology in the multitool will be lost!".ToString(), "Delete".ToString(), MessageBoxButtons.YesNo) == 0) {
            this.bv.h(dj.a(this.hl)[var2].getIndex());
         }
      }
   }
}

}
