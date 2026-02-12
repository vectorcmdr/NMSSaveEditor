using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class cr : FileFilter {
   cp fM;

   cr(cp var1) {
      this.fM = var1;
   }

   public string getDescription() {
      return "Companion Export File";
   }

   public bool accept(File var1) {
      if (var1.Attributes.HasFlag(FileAttributes.Directory)) {
         return !var1.isHidden();
      } else {
         return var1.Name.EndsWith(".pet") || var1.Name.EndsWith(".egg");
      }
   }
}

}
