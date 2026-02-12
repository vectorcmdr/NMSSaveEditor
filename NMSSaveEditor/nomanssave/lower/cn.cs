using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class cn : FileFilter {
   cl fI;

   cn(cl var1) {
      this.fI = var1;
   }

   public string getDescription() {
      return "Planetary Base Backup File";
   }

   public bool accept(File var1) {
      if (var1.Attributes.HasFlag(FileAttributes.Directory)) {
         return !var1.isHidden();
      } else {
         return var1.Name.EndsWith(".pb3");
      }
   }
}

}
