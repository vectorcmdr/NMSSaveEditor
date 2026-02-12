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

public class aX : ActionListener {
   // $FF: synthetic field
   public aW dy;
   // $FF: synthetic field
   public cy dz;

   public aX(aW var1, cy var2) {
      this.dy = var1;
      this.dz = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      string var2 = aW.a(this.dy).GetText();
      if (var2.Length > 0) {
         this.dz.a(var2, aW.b(this.dy).isSelected(), aW.c(this.dy).isSelected(), aW.d(this.dy).isSelected());
      }
    }
}

}
