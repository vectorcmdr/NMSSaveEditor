using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class cU : FileView {
   public string Name => getName();
   public cT gw;

   public cU(cT var1) {
      this.gw = var1;
   }

   public Icon getIcon(FileInfo var1) {
      string var2 = var1.Name;
      return (Icon)(var2.EndsWith(".sh0") ? cT.@as() : base.getIcon(var1));
   }

   public string getName(FileInfo var1) {
      string var2 = var1.Name;
      return var2.EndsWith(".sh0") ? var2.Substring(0, var2.length() - 4) : var2;
   }
}


#else

public class cU
{
   public cU() { }
   public cU(params object[] args) { }
   public string Name = "";
   public cT gw = default;
   public Icon getIcon(FileInfo var1) { return default; }
   public string getName(FileInfo var1) { return ""; }
}

#endif

}
