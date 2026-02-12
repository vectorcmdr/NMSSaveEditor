using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class cL : FileView {
   // PORT_TODO: public string Name => getName();
   public cK gl;

   public cL(cK var1) {
      this.gl = var1;
   }

   public string getName(FileInfo var1) {
      string var2 = var1.Name;
      return var2.EndsWith(".json") ? var2.Substring(0, var2.Length - 5) : var2;
   }
}



}
