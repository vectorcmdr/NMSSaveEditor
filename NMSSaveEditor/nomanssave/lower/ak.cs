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

public class ak : ActionListener {
   // $FF: synthetic field
   public aj cg;

   public ak(aj var1) {
      this.cg = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      try {
         aj.a(this.cg, hl.e(aj.a(this.cg).GetText().Trim(), aj.b(this.cg).SelectedIndex));
         aj.c(this.cg);
      } catch (Exception var3) {
         JavaCompat.ShowOptionDialog(this.cg, "Invalid address value, please try again.", "Error", 0, 0, (Icon)null, new Object[]{"Cancel"}, (Object)null);
      }
   }
}

}
