using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class ct : FileView {
   cs fP;

   ct(cs var1) {
      this.fP = var1;
   }

   public Icon getIcon(File var1) {
      string var2 = var1.getName();
      return (Icon)(var2.EndsWith(".fb3") ? cs.as() : base.getIcon(var1));
   }

   public string getName(File var1) {
      string var2 = var1.getName();
      return var2.EndsWith(".fb3") ? var2.Substring(0, var2.length() - 4) : var2;
   }
}

}
