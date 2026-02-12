using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class el : FileView {
   // PORT_TODO: public string Name => getName();
   public ej @is;

   public el(ej var1) {
      this.@is = var1;
   }

   public Icon getIcon(FileInfo var1) {
      string var2;
      if (var1.Exists) {
         var2 = var1.Name;
         if (var2.EndsWith(".hg") && !var2.StartsWith("mf_")) {
            // PORT_TODO: return ej.@as();
         } else {
            // PORT_TODO: return var2.Equals("containers.index") ? ej.au() : null;
            return default;
         }
      } else {
         var2 = ej.a(this.@is, var1);
         // PORT_TODO: return var2 == null ? null : ej.aR();
      }
      return default;
   }

   public string getName(FileInfo var1) {
      if (var1.Exists) {
         return var1.Name;
      } else {
         string var2 = ej.a(this.@is, var1);
         return var2 == null ? var1.Name : "[" + var2 + "] " + var1.Name;
      }
   }
}



}
