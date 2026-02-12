using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{


public class gw : gv {
   public string Name => getName();
   public eY oI;

public gw(eY var1, eY var2) : base(0, (eY)null, var2) {
      this.oI = var1;
   }

   public string getName() {
      // PORT_TODO: return this.oI.getValueAsString("PlayerWeaponName");
      return default;
   }

   public void setName(string var1) {
      // PORT_TODO: this.oI.b("PlayerWeaponName", (object)var1);
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
      // PORT_TODO: return this.oI.d("CurrentWeapon.GenerationSeed").X(1);
      return default;
   }

   public void aa(string var1) {
      // PORT_TODO: this.oI.d("CurrentWeapon.GenerationSeed").a(1, var1);
   }

   public string cW() {
      // PORT_TODO: return this.oI.getValueAsString("WeaponInventory.Class.InventoryClass");
      return default;
   }

   public void aj(string var1) {
      // PORT_TODO: this.oI.b("WeaponInventory.Class.InventoryClass", (object)var1);
   }

   public string toString() {
      string var1 = this.Name;
      return var1 != null && var1.Length != 0 ? var1 : "Multitool";
   }
}



}
