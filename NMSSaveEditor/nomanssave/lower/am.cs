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

public class am : ActionListener {
   // $FF: synthetic field
   public aj cg;

   public am(aj var1) {
      this.cg = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      int var2 = aj.b(this.cg).SelectedIndex;
      if (var2 < 0) {
         JavaCompat.ShowOptionDialog(this.cg, "Invalid galaxy selected, please try again.", "Error", 0, 0, (Icon)null, new Object[]{"Cancel"}, (Object)null);
      } else {
         if (JavaCompat.ShowOptionDialog(this.cg, "This will warp your character and ship to the specified system (not the portal itself).", "Confirm", 2, 1, (Icon)null, new string[]{"OK", "Cancel"}, (Object)null) == 0) {
            aj.a(this.cg, true);
            this.cg.SetVisible(false);
         }
       }
   }
}

}
