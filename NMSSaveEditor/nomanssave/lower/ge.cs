using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class ge {
   public List<object> gT;
   public List<object> nh;
   public List<object> ni;

   public static ge m(eY var0) {
      return new ge(var0);
   }

   public static Function ap(int var0) {
      // PORT_TODO: return (var1) => {
         // PORT_TODO: string var2 = var1.Name;
         // PORT_TODO: if (true) { // PORT_TODO: original condition had errors
            // PORT_TODO: var2 = "Chest " + var0;
         // PORT_TODO: }

// PORT_TODO: 
         // PORT_TODO: return new string[]{var2};
      // PORT_TODO: };
      return default;
   }

   public static Function cB() {
      return (var0) => {
         return new string[]{"Ingredient Storage"};
      };
   }

   public ge(eY var1) {
      byte var2 = 8;
      byte var3 = 6;
      if (Application.e().D()) {
         var2 = 10;
         var3 = 8;
      }

      List<object> var4 = new List<object>();

      for(int var5 = 0; var5 < 10; ++var5) {
         // PORT_TODO: var4.Add(new gt(ap(var5), var1.H("Chest" + (var5 + 1) + "Inventory"), 3584, var2, var3, false, false));
      }

      // PORT_TODO: eY var14 = var1.H("CookingIngredientsInventory");
      // PORT_TODO: if (var14 != null) {
         // PORT_TODO: var4.Add(new gt(cB(), var14, 36352, var2, var3, false, false));
      // PORT_TODO: }

      this.gT = new List<object>(var4);
      List<object> var6 = new List<object>();
      // PORT_TODO: eV var7 = var1.d("NPCWorkers");
      string var8 = "";

      // PORT_TODO: for(int var10 = 0; var10 < var7.Count && var10 < 5; ++var10) {
         // PORT_TODO: eY var9 = var7.V(var10);
         // PORT_TODO: if (var9.M("HiredWorker")) {
            // PORT_TODO: switch(var10) {
            // PORT_TODO: case 0:
               // PORT_TODO: var8 = "Armorer";
               // PORT_TODO: break;
            // PORT_TODO: case 1:
               // PORT_TODO: var8 = "Farmer";
               // PORT_TODO: break;
            // PORT_TODO: case 2:
               // PORT_TODO: var8 = "Overseer";
               // PORT_TODO: break;
            // PORT_TODO: case 3:
               // PORT_TODO: var8 = "Technician";
               // PORT_TODO: break;
            // PORT_TODO: case 4:
               // PORT_TODO: var8 = "Scientist";
               // PORT_TODO: return default;
            // PORT_TODO: }

            // PORT_TODO: var6.Add(new gh(this, var8, var9, (gh)null));
         // PORT_TODO: }
      // PORT_TODO: }

      this.nh = new List<object>(var6);
      List<object> var15 = new List<object>();
      // PORT_TODO: eV var11 = var1.d("PersistentPlayerBases");

      // PORT_TODO: for(int var13 = 0; var13 < var11.Count; ++var13) {
         // PORT_TODO: eY var12 = var11.V(var13);
         // PORT_TODO: if ("HomePlanetBase".Equals(var12.getValueAsString("BaseType.PersistentBaseTypes")) && var12.J("BaseVersion") >= 3) {
            // PORT_TODO: var15.Add(new gf(this, var12, (gf)null));
         // PORT_TODO: }
      // PORT_TODO: }

      this.ni = new List<object>(var15);
   }

   public List<object> cC() {
      return this.gT;
   }

   public List<object> cD() {
      return this.nh;
   }

   public List<object> cE() {
      return this.ni;
   }
}



}
