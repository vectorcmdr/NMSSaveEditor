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

public class ct : FileView {
   // $FF: synthetic field
   cs fP;

   ct(cs var1) {
      this.fP = var1;
   }

   public Image getIcon(FileInfo var1) {
      string var2 = var1.Name;
      return (Icon)(var2.EndsWith(".fb3") ? cs.as() : base.getIcon(var1));
   }

   public string getName(FileInfo var1) {
      string var2 = var1.Name;
      return var2.EndsWith(".fb3") ? var2.Substring(0, var2.Length - 4) : var2;
   }
}

}
