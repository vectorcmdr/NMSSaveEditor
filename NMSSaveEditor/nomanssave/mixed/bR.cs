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

public class bR : ActionListener {
   // $FF: synthetic field
   bO eX;

   bR(bO var1) {
      this.eX = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      if (bO.a(this.eX) != null) {
         Size var2 = aQ.a(this.eX, bO.a(this.eX).Size, bO.a(this.eX).dm(), bO.a(this.eX).dn());
         if (var2 != null && bO.a(this.eX).a(var2)) {
            bO.c(this.eX);
         }

      }
   }
}

}
