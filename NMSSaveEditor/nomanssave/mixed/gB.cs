using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class gB {
   private eY oI;

   public static gB x(eY var0) {
      return new gB(var0);
   }

   private gB(eY var1) {
      this.oI = var1;
   }

   public int dU() {
      return this.oI.J("ActiveMultioolIndex");
   }

   public void aF(int var1) {
      eY var2 = this.oI.H("Multitools[" + var1 + "]");
      if (var2 != null && var2.M("Seed[0]")) {
         this.oI.b("ActiveMultioolIndex", (object)var1);
         this.oI.b("WeaponInventory", (object)var2.H("Store").bE());
         this.oI.b("CurrentWeapon.GenerationSeed[1]", (object)var2.I("Seed[1]"));
         this.oI.b("CurrentWeapon.Filename", (object)var2.getValueAsString("Resource.Filename"));
      } else {
         throw new Exception("Cannot set current multitool");
      }
   }
}

}
