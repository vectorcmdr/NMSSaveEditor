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

public class ai : ActionListener {
   // $FF: synthetic field
   public X bV;
   // $FF: synthetic field
   public Application bv;

   public ai(X var1, Application var2) {
      this.bV = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      int var2 = X.k(this.bV).SelectedIndex;
      if (var2 >= 0 && var2 < X.a(this.bV).Length) {
         if (MessageBox.Show("Are you sure you want to delete this companion?".ToString(), "Delete".ToString(), MessageBoxButtons.YesNo) == 0) {
            this.bv.a(X.a(this.bV)[var2].cL(), X.a(this.bV)[var2].getIndex());
         }
      }
   }
}

}
