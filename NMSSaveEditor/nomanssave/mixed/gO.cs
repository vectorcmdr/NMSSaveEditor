using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class gO {
   public int index;
   public eY rO;
   public List<object> gT;

   public static gO[] E(eY var0) {
      eV var1 = var0.d("VehicleOwnership");
      if (var1 != null && var1.Count != 0) {
         List<object> var2 = new List<object>();

         eY var4;
         for(int var3 = 0; var3 < var1.Count; ++var3) {
            var4 = var1.V(var3);
            if (var3 != 4) {
               var2.Add(new gO(var3, var4, var4.H("Inventory"), var4.H("Inventory_TechOnly")));
            }
         }

         eY var5 = var0.H("FishPlatformLayout");
         var4 = var0.H("FishPlatformInventory");
         if (var5 != null && var4 != null) {
            var2.Add(new gO(var5, var4));
         }

         return (gO[])var2.ToArray();
      } else {
         return new gO[0];
      }
   }

   public static Function a(gO var0, string var1) {
      return (var2) => {
         return new string[]{var0.getType(), var1};
      };
   }

   public gO(int var1, eY var2, eY var3, eY var4) {
      this.index = var1;
      this.rO = var2;
      short var5;
      if (var1 == 5) {
         var5 = 32;
      } else if (var1 == 6) {
         var5 = 128;
      } else {
         var5 = 16;
      }

      bool var6 = Application.e().D();
      byte var7 = 8;
      byte var8 = 6;
      byte var9 = 8;
      byte var10 = 6;
      string var11;
      if (var6) {
         var7 = 10;
         var8 = 5;
         var9 = 10;
         var10 = 3;
         var11 = "Cargo";
      } else {
         var11 = "General";
      }

      List<object> var12 = new List<object>();
      var12.Add(new gP(this, a(this, var11), var3, 0, var7, var8, false, false, false, false, var6, var5));
      if (var4 != null) {
         var12.Add(new gt(a(this, "Technology"), var4, var5, var9, var10, true, false, false, false));
      }

      this.gT = new List<object>(var12);
   }

   public gO(eY var1, eY var2) {
      this.index = 1000;
      this.rO = var1;
      // PORT_TODO: var2.a((var1x, var2x, var3x) => {
         // PORT_TODO: if ("ValidSlotIndices".Equals(var1x) && var3x is eV) {
            // PORT_TODO: int var4 = ((eV)var3x).Count;
            // PORT_TODO: var1.b("Slots", (object)var4);
         // PORT_TODO: }

// PORT_TODO: 
      // PORT_TODO: });
      // PORT_TODO: byte var3 = 8;
      // PORT_TODO: byte var4 = 6;
      // PORT_TODO: List<object> var5 = new List<object>();
      // PORT_TODO: var5.Add(new gt(a(this, "Cold Storage"), var2, 2048, var3, var4, false, false, true, false));
      // PORT_TODO: this.gT = new List<object>(var5);
   }

   public string getType() {
      if (this.index == 0) {
         return "Roamer";
      } else if (this.index == 1) {
         return "Nomad";
      } else if (this.index == 2) {
         return "Colossus";
      } else if (this.index == 3) {
         return "Pilgrim";
      } else if (this.index == 5) {
         return "Nautilon";
      } else if (this.index == 6) {
         return "Minotaur";
      } else {
         return this.index == 1000 ? "Skiff" : "Vehicle " + this.index;
      }
   }

   public List<object> cC() {
      return this.gT;
   }

   public string toString() {
      return this.getType();
   }
}



}
