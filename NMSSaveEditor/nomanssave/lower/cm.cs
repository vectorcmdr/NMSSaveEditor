using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class cm : FileView {
   cl fI;

   cm(cl var1) {
      this.fI = var1;
   }

   public Icon getIcon(FileInfo var1) {
      string var2 = var1.Name;
      return (Icon)(!var2.EndsWith(".pb3") && !var2.EndsWith(".pb0") ? base.getIcon(var1) : cl.@as());
   }

   public string getName(FileInfo var1) {
      string var2 = var1.Name;
      return !var2.EndsWith(".pb3") && !var2.EndsWith(".pb0") ? var2 : var2.Substring(0, var2.length() - 4);
   }
}

}
