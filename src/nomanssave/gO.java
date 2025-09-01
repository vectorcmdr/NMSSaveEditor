package nomanssave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class gO {
   private final int index;
   private final eY rO;
   private final List gT;

   public static gO[] E(eY var0) {
      eV var1 = var0.d("VehicleOwnership");
      if (var1 != null && var1.size() != 0) {
         ArrayList var2 = new ArrayList();

         eY var4;
         for(int var3 = 0; var3 < var1.size(); ++var3) {
            var4 = var1.V(var3);
            if (var3 != 4) {
               var2.add(new gO(var3, var4, var4.H("Inventory"), var4.H("Inventory_TechOnly")));
            }
         }

         eY var5 = var0.H("FishPlatformLayout");
         var4 = var0.H("FishPlatformInventory");
         if (var5 != null && var4 != null) {
            var2.add(new gO(var5, var4));
         }

         return (gO[])var2.toArray(new gO[0]);
      } else {
         return new gO[0];
      }
   }

   private static Function a(gO var0, String var1) {
      return (var2) -> {
         return new String[]{var0.getType(), var1};
      };
   }

   private gO(int var1, eY var2, eY var3, eY var4) {
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

      boolean var6 = Application.e().D();
      byte var7 = 8;
      byte var8 = 6;
      byte var9 = 8;
      byte var10 = 6;
      String var11;
      if (var6) {
         var7 = 10;
         var8 = 5;
         var9 = 10;
         var10 = 3;
         var11 = "Cargo";
      } else {
         var11 = "General";
      }

      ArrayList var12 = new ArrayList();
      var12.add(new gP(this, a(this, var11), var3, 0, var7, var8, false, false, false, false, var6, var5));
      if (var4 != null) {
         var12.add(new gt(a(this, "Technology"), var4, var5, var9, var10, true, false, false, false));
      }

      this.gT = Collections.unmodifiableList(var12);
   }

   private gO(eY var1, eY var2) {
      this.index = 1000;
      this.rO = var1;
      var2.a((var1x, var2x, var3x) -> {
         if ("ValidSlotIndices".equals(var1x) && var3x instanceof eV) {
            int var4 = ((eV)var3x).size();
            var1.b("Slots", (Object)var4);
         }

      });
      byte var3 = 8;
      byte var4 = 6;
      ArrayList var5 = new ArrayList();
      var5.add(new gt(a(this, "Cold Storage"), var2, 2048, var3, var4, false, false, true, false));
      this.gT = Collections.unmodifiableList(var5);
   }

   public String getType() {
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

   public List cC() {
      return this.gT;
   }

   public String toString() {
      return this.getType();
   }
}
