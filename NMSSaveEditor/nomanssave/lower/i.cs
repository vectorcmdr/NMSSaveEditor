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

public class i : ActionListener {
   // $FF: synthetic field
   h z;

   i(h var1) {
      this.z = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      string var2 = h.a(this.z).GetText();
      h.a(this.z, ey.b(h.b(this.z), var2));
      h.c(this.z);
      if (h.d(this.z).Count == 0) {
         JavaCompat.ShowOptionDialog(this.z, "Item not found.", "Warning", 0, 2, (Icon)null, new Object[]{"OK"}, (Object)null);
      }

   }
}

}
