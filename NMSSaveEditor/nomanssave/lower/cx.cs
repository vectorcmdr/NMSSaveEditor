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
      return "Weapon Export FileInfo";
   }

   public bool accept(FileInfo var1) {
      if (var1.Attributes.HasFlag(FileAttributes.Directory)) {
         return !var1.isHidden();
      } else {
         return var1.Name.EndsWith(".wp0");
      }
   }
}

}
