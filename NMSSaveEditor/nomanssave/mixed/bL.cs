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

public class bL : TextBox {
   bK eB;
   // $FF: synthetic field
   bE ey;

   bL(bE var1, bK var2, bool var3) {
      this.ey = var1;
      this.eB = var2;
      this.SetEnabled(var3);
      this.addFocusListener(new bM(this, var2));
   }

   void ac() {
      string var1;
      if (bE.a(this.ey) == null) {
         var1 = "";
      } else {
         var1 = this.eB.ab();
      }

      this.SetText(var1);
   }

   // $FF: synthetic method
   static bE a(bL var0) {
      return var0.ey;
   }
}

}
