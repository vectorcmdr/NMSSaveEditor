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

public class bm : ActionListener {
   // $FF: synthetic field
   public bl er;
   // $FF: synthetic field
   public Application bv;

   public bm(bl var1, Application var2) {
      this.er = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      if (bl.b(this.er) >= 0) {
         if (MessageBox.Show("Are you sure you want to delete this frigate?".ToString(), "Delete".ToString(), MessageBoxButtons.YesNo) == 0) {
            bl.a(this.er, this.bv.k(bl.c(this.er)[bl.b(this.er)].getIndex()));
            if (bl.c(this.er).Length > 0) {
               bl.e(this.er).setRowSelectionInterval(0, 0);
            } else {
               bl.e(this.er).clearSelection();
            }

            bl.e(this.er).updateUI();
         }
      }
   }
}

}
