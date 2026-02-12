using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class gw : gv {
   public eY oI;

   public gw(eY var1, eY var2) {
      base(0, (eY)null, var2);
      this.oI = var1;
   }

   public string getName() {
      return this.oI.getValueAsString("PlayerWeaponName");
   }

   public void setName(string var1) {
      this.oI.b("PlayerWeaponName", (object)var1);
   }

   public string cT() {
      return gx.qH.K();
   }

   public void ag(string var1) {
      if (!gx.qH.K().Equals(var1)) {
         throw new Exception("Only standard types allowed");
      }
   }

   public gx dI() {
      return gx.qH;
   }

   public void a(gx var1) {
      if (var1 != gx.qH) {
         throw new Exception("Only standard types allowed");
      }
   }

   public string cK() {
      return this.oI.d("CurrentWeapon.GenerationSeed").X(1);
   }

   public void aa(string var1) {
      this.oI.d("CurrentWeapon.GenerationSeed").a(1, var1);
   }

   public string cW() {
      return this.oI.getValueAsString("WeaponInventory.Class.InventoryClass");
   }

   public void aj(string var1) {
      this.oI.b("WeaponInventory.Class.InventoryClass", (object)var1);
   }

   public string toString() {
      string var1 = this.Name;
      return var1 != null && var1.length() != 0 ? var1 : "Multitool";
   }
}

}
