using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class ct : FileView {
   public string Name => getName();
   public cs fP;

   public ct(cs var1) {
      this.fP = var1;
   }

   public Icon getIcon(FileInfo var1) {
      string var2 = var1.Name;
      return (Icon)(var2.EndsWith(".fb3") ? cs.@as() : base.getIcon(var1));
   }

   public string getName(FileInfo var1) {
      string var2 = var1.Name;
      return var2.EndsWith(".fb3") ? var2.Substring(0, var2.length() - 4) : var2;
   }
}


#else

public class ct
{
   public ct() { }
   public ct(params object[] args) { }
   public string Name = "";
   public cs fP = default;
   public Icon getIcon(FileInfo var1) { return default; }
   public string getName(FileInfo var1) { return ""; }
}

#endif

}
