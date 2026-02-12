using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class cS : gD {
   public string filename;
   public int index;
   cN gt;

   public cS(cN var1, string var2) {
      this.gt = var1;
      this.filename = var2;
      this.index = cN.a(var1).Count + 1;
   }

   public string K() {
      return this.filename;
   }

   public string toString() {
      return "Unknown " + this.index;
   }
}

}
