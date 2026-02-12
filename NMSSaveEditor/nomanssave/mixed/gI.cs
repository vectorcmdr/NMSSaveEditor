using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class gI : gt {
   gH rq;
   public bool rr;
   public int il;

   public gI(gH var1, Function var2, eY var3, int var4, int var5, int var6, bool var7, bool var8, bool var9, int var10) {
      base(var2, var3, var4, var5, var6, var7, var8);
      this.rq = var1;
      this.rr = var9;
      this.il = var10;
   }

   public int dj() {
      return this.rr ? 3584 : 3584 | gH.b(this.rq);
   }

   public string toString() {
      return this.rq.dZ() ? "Ship " + this.il + " - Storage Sacs" : base.ToString();
   }
}

}
