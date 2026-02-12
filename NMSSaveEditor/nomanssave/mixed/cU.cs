using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class cU : FileView {
   cT gw;

   cU(cT var1) {
      this.gw = var1;
   }

   public Icon getIcon(File var1) {
      string var2 = var1.Name;
      return (Icon)(var2.EndsWith(".sh0") ? cT.@as() : base.getIcon(var1));
   }

   public string getName(File var1) {
      string var2 = var1.Name;
      return var2.EndsWith(".sh0") ? var2.Substring(0, var2.length() - 4) : var2;
   }
}

}
