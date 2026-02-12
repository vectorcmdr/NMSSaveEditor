using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class cq : FileView {
   public string Name => getName();
   public cp fM;

   public cq(cp var1) {
      this.fM = var1;
   }

   public Icon getIcon(FileInfo var1) {
      string var2 = var1.Name;
      if (var2.EndsWith(".pet")) {
         return cp.@as();
      } else {
         return (Icon)(var2.EndsWith(".egg") ? cp.au() : base.getIcon(var1));
      }
   }

   public string getName(FileInfo var1) {
      string var2 = var1.Name;
      if (var2.EndsWith(".pet")) {
         return var2.Substring(0, var2.length() - 4);
      } else {
         return var2.EndsWith(".egg") ? var2.Substring(0, var2.length() - 4) : var2;
      }
   }
}


#else

public class cq
{
   public cq() { }
   public cq(params object[] args) { }
   public string Name = "";
   public cp fM = default;
   public Icon getIcon(FileInfo var1) { return default; }
   public string getName(FileInfo var1) { return ""; }
}

#endif

}
