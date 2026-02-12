using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class el : FileView {
   ej is;

   el(ej var1) {
      this.is = var1;
   }

   public Icon getIcon(File var1) {
      string var2;
      if (var1.isFile()) {
         var2 = var1.getName();
         if (var2.EndsWith(".hg") && !var2.StartsWith("mf_")) {
            return ej.as();
         } else {
            return var2.equals("containers.index") ? ej.au() : null;
         }
      } else {
         var2 = ej.a(this.is, var1);
         return var2 == null ? null : ej.aR();
      }
   }

   public string getName(File var1) {
      if (var1.isFile()) {
         return var1.getName();
      } else {
         string var2 = ej.a(this.is, var1);
         return var2 == null ? var1.getName() : "[" + var2 + "] " + var1.getName();
      }
   }
}

}
