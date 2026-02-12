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

public class cU : FileView {
   // $FF: synthetic field
   cT gw;

   cU(cT var1) {
      this.gw = var1;
   }

   public Image getIcon(FileInfo var1) {
      string var2 = var1.Name;
      return (Icon)(var2.EndsWith(".sh0") ? cT.as() : base.getIcon(var1));
   }

   public string getName(FileInfo var1) {
      string var2 = var1.Name;
      return var2.EndsWith(".sh0") ? var2.Substring(0, var2.Length - 4) : var2;
   }
}

}
