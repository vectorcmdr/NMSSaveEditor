using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class cu : FileFilter {
   cs fP;

   cu(cs var1) {
      this.fP = var1;
   }

   public string getDescription() {
      return "Freighter Backup File";
   }

   public bool accept(File var1) {
      if (var1.isDirectory()) {
         return !var1.isHidden();
      } else {
         return var1.getName().EndsWith(".fb3");
      }
   }
}

}
