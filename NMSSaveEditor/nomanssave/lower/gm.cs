using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class gm {
   public string Name => getName();
   public eY oI;
   public List<object> gT;
   public gn oJ;

   public static gm p(eY var0) {
      // PORT_TODO: return var0.d("CurrentFreighter.Seed").ab(0) && !isEmpty(var0.getValueAsString("CurrentFreighter.Filename")) ? new gm(var0, var0.H("FreighterInventory"), var0.H("FreighterInventory_TechOnly"), var0.H("FreighterInventory_Cargo")) : null;
      return default;
   }

   public static bool isEmpty(string var0) {
      return var0 == null || var0.Length == 0;
   }

   public static Function a(gm var0, string var1) {
      return (var1x) => {
         return new string[]{"Freighter", var1};
      };
   }

   public gm(eY var1, eY var2, eY var3, eY var4) {
      this.oI = var1;
      bool var7 = false;
      bool var8 = false;
      short var9 = 0;
      byte var10 = 8;
      byte var11 = 6;
      byte var12 = 7;
      byte var13 = 2;
      string var5;
      string var6;
      short var19;
      byte var20;
      if (Application.e().D()) {
         var19 = 3584;
         var20 = 8;
         var10 = 10;
         var11 = 12;
         var12 = 10;
         var13 = 6;
         var5 = "Cargo";
         var6 = "Unused";
         var4 = null;
      } else {
         var19 = 3592;
         var20 = 8;
         var9 = 3584;
         var5 = "General";
         var6 = "Cargo";
      }

      List<object> var14 = new List<object>();
      var14.Add(new gt(a(this, var5), var2, var19, var10, var11, false, true));
      if (var3 != null) {
         var14.Add(new gt(a(this, "Technology"), var3, var20, var12, var13, true, true));
      }

      if (var4 != null) {
         var14.Add(new gt(a(this, var6), var4, var9, 8, 6, false, true));
      }

      this.gT = new List<object>(var14);
      // PORT_TODO: eV var15 = var1.d("PersistentPlayerBases");
      eY var17 = null;

      // PORT_TODO: for(int var18 = 0; var18 < var15.Count; ++var18) {
         // PORT_TODO: eY var16 = var15.V(var18);
         // PORT_TODO: if ("FreighterBase".Equals(var16.getValueAsString("BaseType.PersistentBaseTypes")) && var16.J("BaseVersion") >= 3) {
            // PORT_TODO: var17 = var16;
            // PORT_TODO: break;
         // PORT_TODO: }
      // PORT_TODO: }

      this.oJ = var17 == null ? null : new gn(this, var17, (gn)null);
   }

   public string getName() {
      // PORT_TODO: return this.oI.getValueAsString("PlayerFreighterName");
      return default;
   }

   public void setName(string var1) {
      // PORT_TODO: this.oI.b("PlayerFreighterName", (object)var1);
   }

   public string cT() {
      // PORT_TODO: return this.oI.getValueAsString("CurrentFreighter.Filename");
      return default;
   }

   public void ag(string var1) {
      // PORT_TODO: this.oI.b("CurrentFreighter.Filename", (object)var1);
   }

   public string cU() {
      // PORT_TODO: eV var1 = this.oI.d("CurrentFreighterHomeSystemSeed");
      // PORT_TODO: if (var1 != null && var1.ab(0)) {
         // PORT_TODO: string var2 = var1.X(1);
         // PORT_TODO: return "0x0".Equals(var2) ? "" : var2;
      // PORT_TODO: } else {
         // PORT_TODO: return "";
      // PORT_TODO: }
      return default;
   }

   public void ah(string var1) {
      // PORT_TODO: eV var2 = this.oI.d("CurrentFreighterHomeSystemSeed");
      // PORT_TODO: if (var2 == null) {
         // PORT_TODO: var2 = new eV(new object[]{Boolean.FALSE, "0x0"});
         // PORT_TODO: this.oI.b("CurrentFreighterHomeSystemSeed", (object)var2);
      // PORT_TODO: }

      // PORT_TODO: var2.a(0, Boolean.TRUE);
      // PORT_TODO: var2.a(1, var1.Length == 0 ? "0x0" : var1);
   }

   public string cV() {
      // PORT_TODO: return this.oI.d("CurrentFreighter.Seed").X(1);
      return default;
   }

   public void ai(string var1) {
      // PORT_TODO: this.oI.d("CurrentFreighter.Seed").a(1, var1);
   }

   public string cW() {
      // PORT_TODO: return this.oI.getValueAsString("FreighterInventory.Class.InventoryClass");
      return default;
   }

   public void aj(string var1) {
      // PORT_TODO: this.oI.b("FreighterInventory.Class.InventoryClass", (object)var1);
      // PORT_TODO: eY var2 = this.oI.H("FreighterInventory_TechOnly.Class");
      // PORT_TODO: if (var2 != null) {
         // PORT_TODO: var2.b("InventoryClass", (object)var1);
      // PORT_TODO: }

      // PORT_TODO: var2 = this.oI.H("FreighterInventory_Cargo.Class");
      // PORT_TODO: if (var2 != null) {
         // PORT_TODO: var2.b("InventoryClass", (object)var1);
      // PORT_TODO: }

   }

   public List<object> cC() {
      return this.gT;
   }

   public double ak(string var1) {
      return ((gt)this.gT[0]).ak(var1);
   }

   public void d(string var1, double var2) {
      this.gT.stream().forEach((var3) => {
         // PORT_TODO: var3.d(var1, var2);
      });
   }

   public double cX() {
      return this.ak("^FREI_HYPERDRIVE");
   }

   public void a(double var1) {
      this.d("^FREI_HYPERDRIVE", var1);
   }

   public double cY() {
      return this.ak("^FREI_FLEET");
   }

   public void b(double var1) {
      this.d("^FREI_FLEET", var1);
   }

   public gn cZ() {
      return this.oJ;
   }

   public string toString() {
      string var1 = this.Name;
      return var1 != null && var1.Length != 0 ? var1 : "Freighter";
   }
}



}
