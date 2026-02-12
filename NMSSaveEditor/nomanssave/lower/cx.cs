using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class cx : FileFilter {
   cv fR;

   cx(cv var1) {
      this.fR = var1;
   }

   public string getDescription() {
      return "Weapon Export File";
   }

   public bool accept(File var1) {
      if (var1.isDirectory()) {
         return !var1.isHidden();
      } else {
         return var1.getName().EndsWith(".wp0");
      }
   }
}

}
