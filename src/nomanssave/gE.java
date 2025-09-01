package nomanssave;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class gE {
   private final int index;
   private final eY bf;

   public static gE[] z(eY var0) {
      eV var1 = var0.d("TeleportEndpoints");
      List var2 = (List)var1.bB().filter((var0x) -> {
         return "Settlement".equals(var0x.getValueAsString("TeleporterType"));
      }).map((var0x) -> {
         return hl.n(var0x.H("UniverseAddress"));
      }).collect(Collectors.toList());
      eV var3 = var0.d("SettlementStatesV2");
      if (var3 != null && var3.size() != 0) {
         ArrayList var4 = new ArrayList();

         for(int var5 = 0; var5 < var3.size(); ++var5) {
            eY var6 = var3.V(var5);
            hl var7 = hl.n(var6.getValue("UniverseAddress"));
            if (var2.contains(var7)) {
               var4.add(new gE(var5, var6));
            }
         }

         return (gE[])var4.toArray(new gE[0]);
      } else {
         return new gE[0];
      }
   }

   private gE(int var1, eY var2) {
      this.index = var1;
      this.bf = var2;
   }

   public int getIndex() {
      return this.index;
   }

   public String getName() {
      return this.bf.getValueAsString("Name");
   }

   public void setName(String var1) {
      this.bf.b("Name", (Object)var1);
   }

   public int aq(int var1) {
      return this.bf.d("Stats").Y(var1);
   }

   public void e(int var1, int var2) {
      this.bf.d("Stats").a(var1, var2);
   }

   public int a(gG var1) {
      return this.bf.d("Stats").Y(var1.ordinal());
   }

   public void a(gG var1, int var2) {
      this.bf.d("Stats").a(var1.ordinal(), var2);
   }

   public int dW() {
      return this.bf.d("Perks").size();
   }

   public String aH(int var1) {
      return this.bf.d("Perks").X(var1);
   }

   public void c(int var1, String var2) {
      this.bf.d("Perks").a(var1, var2);
   }

   public String cK() {
      return this.bf.I("SeedValue");
   }

   public void aa(String var1) {
      this.bf.b("SeedValue", (Object)var1);
   }

   public gF[] dX() {
      eV var1 = this.bf.d("ProductionState");
      if (var1 == null) {
         return new gF[0];
      } else {
         ArrayList var2 = new ArrayList();

         for(int var3 = 0; var3 < var1.size(); ++var3) {
            eY var4 = var1.V(var3);
            var2.add(new gF(this, var4, (gF)null));
         }

         return (gF[])var2.toArray(new gF[0]);
      }
   }

   public String toString() {
      return this.getName();
   }
}
