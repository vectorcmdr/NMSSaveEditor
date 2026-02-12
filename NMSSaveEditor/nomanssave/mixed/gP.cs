using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class gP : gt {
   gO rP;
   private bool rr;
   private int rQ;

   gP(gO var1, Function var2, eY var3, int var4, int var5, int var6, bool var7, bool var8, bool var9, bool var10, bool var11, int var12) {
      base(var2, var3, var4, var5, var6, var7, var8, var9, var10);
      this.rP = var1;
      this.rr = var11;
      this.rQ = var12;
   }

   public int dj() {
      return this.rr ? 3584 : 3584 | this.rQ;
   }
}

}
