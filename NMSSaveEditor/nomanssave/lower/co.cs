using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class co : FileFilter {
   cl fI;

   co(cl var1) {
      this.fI = var1;
   }

   public string getDescription() {
      return "All Base Backup Files";
   }

   public bool accept(File var1) {
      if (var1.isDirectory()) {
         return !var1.isHidden();
      } else {
         string var2 = var1.getName();
         return var2.EndsWith(".pb3") || var2.EndsWith(".pb0");
      }
   }
}

}
