using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public sealed class eU {
   public static readonly eU kr = new eU("Gek");
   public static readonly eU ks = new eU("Vy'keen");
   public static readonly eU kt = new eU("Korvax");
   public static readonly eU ku = new eU("Robot");
   public static readonly eU kv = new eU("Atlas");
   public static readonly eU kw = new eU("Diplomats");
   public static readonly eU kx = new eU("Exotics");
   public static readonly eU ky = new eU("None");
   public static readonly eU kz = new eU("Autophage");


   private string name;

   private eU(string var3) {
      this.name = var3;
   }

   public string toString() {
      return this.name;
   }

   public static eU C(string var0) {
      eU[] var4;
      int var3 = (var4 = values()).Length;

      for(int var2 = 0; var2 < var3; ++var2) {
         eU var1 = var4[var2];
         if (var1.ToString().Equals(var0)) {
            return var1;
         }
      }

      return null;
   }
}

}
