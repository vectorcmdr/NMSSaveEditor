using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class ek : FileFilter {
   ej @is;

   public ek(ej var1) {
      this.@is = var1;
   }

   public string getDescription() {
      return "Saved Game";
   }

   public bool accept(FileInfo var1) {
      if (var1.Attributes.HasFlag(FileAttributes.Directory)) {
         return true;
      } else {
         string var2 = var1.Name;
         if (var2.EndsWith(".hg") && !var2.StartsWith("mf_")) {
            return true;
         } else {
            return var2.Equals("containers.index");
         }
      }
   }
}

}
