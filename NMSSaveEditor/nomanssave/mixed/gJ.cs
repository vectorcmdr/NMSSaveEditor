using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class gJ : gt {
   public gH rq;
   public int il;

   public gJ(gH var1, Function var2, eY var3, int var4, int var5, int var6, bool var7, bool var8, int var9) {
      base(var2, var3, var4, var5, var6, var7, var8);
      this.rq = var1;
      this.il = var9;
   }

   public int dj() {
      return gH.b(this.rq);
   }

   public string toString() {
      return this.rq.dZ() ? "Ship " + this.il + " - Organ Chamber" : base.ToString();
   }
}


#else

public class gJ
{
   public gJ() { }
   public gJ(params object[] args) { }
   public gH rq = default;
   public int il = 0;
   public int dj() { return 0; }
   public string toString() { return ""; }
}

#endif

}
