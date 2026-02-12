using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class cU : FileView {
   // PORT_TODO: public string Name => getName();
   public cT gw;

   public cU(cT var1) {
      this.gw = var1;
   }

   public Icon getIcon(FileInfo var1) {
      string var2 = var1.Name;
      // PORT_TODO: return (Icon)(var2.EndsWith(".sh0") ? cT.@as() : base.getIcon(var1));
      return default;
   }

   public string getName(FileInfo var1) {
      string var2 = var1.Name;
      return var2.EndsWith(".sh0") ? var2.Substring(0, var2.Length - 4) : var2;
   }
}



}
