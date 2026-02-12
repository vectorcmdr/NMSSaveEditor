using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class cV : FileFilter {
   cT gw;

   cV(cT var1) {
      this.gw = var1;
   }

   public string getDescription() {
      return "Ship Export FileInfo";
   }

   public bool accept(FileInfo var1) {
      if (var1.Attributes.HasFlag(FileAttributes.Directory)) {
         return !var1.isHidden();
      } else {
         return var1.Name.EndsWith(".sh0");
      }
   }
}

}
