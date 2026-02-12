using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class cM : FileFilter {
   cK gl;

   cM(cK var1) {
      this.gl = var1;
   }

   public string getDescription() {
      return "JSON File";
   }

   public bool accept(File var1) {
      if (var1.isDirectory()) {
         return !var1.isHidden();
      } else {
         return var1.getName().EndsWith(".json");
      }
   }
}

}
