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

public class cq : FileView {
   // $FF: synthetic field
   cp fM;

   cq(cp var1) {
      this.fM = var1;
   }

   public Image getIcon(FileInfo var1) {
      string var2 = var1.Name;
      if (var2.EndsWith(".pet")) {
         return cp.@as();
      } else {
         return (Icon)(var2.EndsWith(".egg") ? cp.au() : base.getIcon(var1));
      }
   }

   public string getName(FileInfo var1) {
      string var2 = var1.Name;
      if (var2.EndsWith(".pet")) {
         return var2.Substring(0, var2.Length - 4);
      } else {
         return var2.EndsWith(".egg") ? var2.Substring(0, var2.Length - 4) : var2;
      }
   }
}

}
