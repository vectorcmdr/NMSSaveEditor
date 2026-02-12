using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class gE {
   private int index;
   private eY bf;

   public static gE[] z(eY var0) {
      eV var1 = var0.d("TeleportEndpoints");
      List var2 = (List)var1.bB().filter((var0x) -> {
         return "Settlement".equals(var0x.getValueAsString("TeleporterType"));
      }).map((var0x) -> {
         return hl.n(var0x.H("UniverseAddress"));
      }).collect(Collectors.toList());
      eV var3 = var0.d("SettlementStatesV2");
      if (var3 != null && var3.Count != 0) {
         List<object> var4 = new List<object>();

         for(int var5 = 0; var5 < var3.Count; ++var5) {
            eY var6 = var3.V(var5);
            hl var7 = hl.n(var6.getValue("UniverseAddress"));
            if (var2.Contains(var7)) {
               var4.Add(new gE(var5, var6));
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

   public string getName() {
      return this.bf.getValueAsString("Name");
   }

   public void setName(string var1) {
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
      return this.bf.d("Perks").Count;
   }

   public string aH(int var1) {
      return this.bf.d("Perks").X(var1);
   }

   public void c(int var1, string var2) {
      this.bf.d("Perks").a(var1, var2);
   }

   public string cK() {
      return this.bf.I("SeedValue");
   }

   public void aa(string var1) {
      this.bf.b("SeedValue", (Object)var1);
   }

   public gF[] dX() {
      eV var1 = this.bf.d("ProductionState");
      if (var1 == null) {
         return new gF[0];
      } else {
         List<object> var2 = new List<object>();

         for(int var3 = 0; var3 < var1.Count; ++var3) {
            eY var4 = var1.V(var3);
            var2.Add(new gF(this, var4, (gF)null));
         }

         return (gF[])var2.toArray(new gF[0]);
      }
   }

   public string toString() {
      return this.getName();
   }
}

}
