using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{


public class gB {
   public eY oI;

   public static gB x(eY var0) {
      return new gB(var0);
   }

   public gB(eY var1) {
      this.oI = var1;
   }

   public int dU() {
      // PORT_TODO: return this.oI.J("ActiveMultioolIndex");
      return 0;
   }

   public void aF(int var1) {
      // PORT_TODO: eY var2 = this.oI.H("Multitools[" + var1 + "]");
      // PORT_TODO: if (var2 != null && var2.M("Seed[0]")) {
         // PORT_TODO: this.oI.b("ActiveMultioolIndex", (object)var1);
         // PORT_TODO: this.oI.b("WeaponInventory", (object)var2.H("Store").bE());
         // PORT_TODO: this.oI.b("CurrentWeapon.GenerationSeed[1]", (object)var2.I("Seed[1]"));
         // PORT_TODO: this.oI.b("CurrentWeapon.Filename", (object)var2.getValueAsString("Resource.Filename"));
      // PORT_TODO: } else {
         // PORT_TODO: throw new Exception("Cannot set current multitool");
      // PORT_TODO: }
   }
}



}
