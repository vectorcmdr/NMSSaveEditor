using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class cw : FileView {
   public string Name => getName();
   public cv fR;

   public cw(cv var1) {
      this.fR = var1;
   }

   public Icon getIcon(FileInfo var1) {
      string var2 = var1.Name;
      return (Icon)(var2.EndsWith(".wp0") ? cv.@as() : base.getIcon(var1));
   }

   public string getName(FileInfo var1) {
      string var2 = var1.Name;
      return var2.EndsWith(".wp0") ? var2.Substring(0, var2.length() - 4) : var2;
   }
}


#else

public class cw
{
   public cw() { }
   public cw(params object[] args) { }
   public string Name = "";
   public cv fR = default;
   public Icon getIcon(FileInfo var1) { return default; }
   public string getName(FileInfo var1) { return ""; }
}

#endif

}
