using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public sealed class gG {
   public static gG[] Values() { return new gG[] { rg, rh, ri, rj, rk, rl, rm }; }
   public static gG valueOf(string name) { foreach (var v in Values()) if (v.ToString() == name) return v; return null; }

   public static readonly gG rg = new gG(200);
   public static readonly gG rh = new gG(100);
   public static readonly gG ri = new gG(1000000);
   public static readonly gG rj = new gG(1000000);
   public static readonly gG rk = new gG(100);
   public static readonly gG rl = new gG(10000000);
   public static readonly gG rm = new gG(100);


   public int rn;

   public gG(int var3) {
      this.rn = var3;
   }

   public int dY() {
      return this.rn;
   }
}

}
