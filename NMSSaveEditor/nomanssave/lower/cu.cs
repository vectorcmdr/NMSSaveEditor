using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class cu : FileFilter {
   public cs fP;

   public cu(cs var1) {
      this.fP = var1;
   }

   public string getDescription() {
      return "Freighter Backup FileInfo";
   }

   public bool accept(FileInfo var1) {
      if (var1.Attributes.HasFlag(FileAttributes.Directory)) {
         return !var1.isHidden();
      } else {
         return var1.Name.EndsWith(".fb3");
      }
   }
}

}
