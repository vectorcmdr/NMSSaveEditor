using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class cq : FileView {
   // PORT_TODO: public string Name => getName();
   public cp fM;

   public cq(cp var1) {
      this.fM = var1;
   }

   public Icon getIcon(FileInfo var1) {
      string var2 = var1.Name;
      if (var2.EndsWith(".pet")) {
         // PORT_TODO: return cp.@as();
      } else {
         // PORT_TODO: return (Icon)(var2.EndsWith(".egg") ? cp.au() : base.getIcon(var1));
         return default;
      }
      return default; // PORT_TODO: auto-added
      return default; // PORT_TODO: auto-added
   }

   public string getName(FileInfo var1) {
      string var2 = var1.Name;
      if (var2.EndsWith(".pet")) {
         return var2.Substring(0, var2.Length - 4);
      } else {
         return var2.EndsWith(".egg") ? var2.Substring(0, var2.Length - 4) : var2;
      }
   }
}



}
