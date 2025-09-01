package nomanssave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class gz {
   private final eY oI;
   private final eV rb;
   private final eV rc;
   private final List gT;

   public static gz w(eY var0) {
      eV var1 = null;
      eV var2 = var0.d("Stats");
      if (var2 != null) {
         for(int var3 = 0; var3 < var2.size(); ++var3) {
            eY var4 = var2.V(var3);
            if ("^GLOBAL_STATS".equals(var4.getValueAsString("GroupId"))) {
               var1 = var4.d("Stats");
               break;
            }
         }
      }

      return new gz(var0, var1, var0.H("Inventory"), var0.H("Inventory_TechOnly"), var0.H("Inventory_Cargo"));
   }

   private static Function au(String var0) {
      return (var1) -> {
         return new String[]{"Exosuit", var0};
      };
   }

   private gz(eY var1, eV var2, eY var3, eY var4, eY var5) {
      this.oI = var1;
      this.rb = var2;
      eV var6 = var1.d("KnownWords");
      eV var7 = var1.d("KnownWordGroups");
      if (var7 == null) {
         var7 = new eV();
         var1.b("KnownWordGroups", (Object)var7);
      }

      if (var6.size() > 0) {
         int var8 = 0;

         label46:
         while(true) {
            while(true) {
               if (var8 >= var6.size()) {
                  break label46;
               }

               eY var9 = var6.V(var8);
               eS var10 = eS.A(var9.getValueAsString("id"));
               if (var10 == null) {
                  hc.warn("Could not build word groups: " + var9.getValueAsString("id"));
                  ++var8;
               } else {
                  Iterator var12 = var10.bw().iterator();

                  while(var12.hasNext()) {
                     String var11 = (String)var12.next();
                     eU var13 = var10.z(var11);
                     if (var13 != null) {
                        eY var14 = new eY();
                        var14.b("Group", (Object)var11);
                        eV var15 = new eV(new Object[]{Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE});
                        var15.a(var13.ordinal(), Boolean.TRUE);
                        var14.b("Races", (Object)var15);
                        var7.f(var14);
                        hc.debug("Creating word: " + var11 + "[" + var13.ordinal() + "] = true");
                     }
                  }

                  var6.ac(var8);
                  hc.debug("Removed old word: " + var9.getValueAsString("id"));
               }
            }
         }
      }

      this.rc = var7;
      boolean var20 = false;
      boolean var22 = false;
      short var24 = 0;
      byte var25 = 8;
      byte var26 = 6;
      byte var27 = 7;
      byte var16 = 2;
      String var18;
      String var19;
      short var21;
      byte var23;
      if (Application.e().D()) {
         var21 = 3584;
         var23 = 1;
         var25 = 10;
         var26 = 12;
         var27 = 10;
         var16 = 6;
         var18 = "Cargo";
         var19 = "Unused";
         var5 = null;
      } else {
         var21 = 3585;
         var23 = 1;
         var24 = 3584;
         var18 = "General";
         var19 = "Cargo";
      }

      ArrayList var17 = new ArrayList();
      var17.add(new gt(au(var18), var3, var21, var25, var26, false, true));
      if (var4 != null) {
         var17.add(new gt(au("Technology"), var4, var23, var27, var16, true, true));
      }

      if (var5 != null) {
         var17.add(new gt(au(var19), var5, var24, 8, 6, false, true));
      }

      this.gT = Collections.unmodifiableList(var17);
   }

   public long dJ() {
      return this.oI.K("Units") & 4294967295L;
   }

   public void e(long var1) {
      this.oI.b("Units", (Object)(new Integer((int)var1)));
   }

   public long dK() {
      return this.oI.K("Nanites") & 4294967295L;
   }

   public void f(long var1) {
      this.oI.b("Nanites", (Object)(new Integer((int)var1)));
   }

   public long dL() {
      return this.oI.K("Specials") & 4294967295L;
   }

   public void g(long var1) {
      this.oI.b("Specials", (Object)(new Integer((int)var1)));
   }

   public int dM() {
      return this.oI.J("Health");
   }

   public void aB(int var1) {
      this.oI.b("Health", (Object)(new Integer(var1)));
   }

   public int dN() {
      return this.oI.J("Shield");
   }

   public void aC(int var1) {
      this.oI.b("Shield", (Object)(new Integer(var1)));
   }

   public int dO() {
      return this.oI.J("Energy");
   }

   public void aD(int var1) {
      this.oI.b("Energy", (Object)(new Integer(var1)));
   }

   public List cC() {
      return this.gT;
   }

   public int dP() {
      return this.oI.J("KnownPortalRunes");
   }

   public void aE(int var1) {
      this.oI.b("KnownPortalRunes", (Object)(new Integer(var1)));
   }

   public eV dQ() {
      return this.oI.d("KnownTech");
   }

   public eV dR() {
      return this.oI.d("KnownProducts");
   }

   public eV dS() {
      return this.oI.d("KnownSpecials");
   }

   public int bx() {
      byte var1 = 0;
      ArrayList var3 = new ArrayList();

      for(int var4 = 0; var4 < this.rc.size(); ++var4) {
         eY var2 = this.rc.V(var4);
         eS var5 = eS.B(var2.getValueAsString("Group"));
         if (var5 != null && !var3.contains(var5.getID())) {
            var3.add(var5.getID());
         }
      }

      return var1;
   }

   public int b(eU var1) {
      int var2 = 0;

      for(int var4 = 0; var4 < this.rc.size(); ++var4) {
         eY var3 = this.rc.V(var4);
         if (var3.d("Races").ab(var1.ordinal())) {
            ++var2;
         }
      }

      return var2;
   }

   public gA a(eS var1) {
      return new gA(this, var1, (gA)null);
   }

   private boolean d(String var1, int var2) {
      for(int var3 = 0; var3 < this.rc.size(); ++var3) {
         eY var4 = this.rc.V(var3);
         if (var1.equals(var4.getValueAsString("Group"))) {
            return var4.d("Races").ab(var2);
         }
      }

      return false;
   }

   private void a(String var1, int var2, boolean var3) {
      eY var4;
      for(int var5 = 0; var5 < this.rc.size(); ++var5) {
         var4 = this.rc.V(var5);
         if (var1.equals(var4.getValueAsString("Group"))) {
            hc.debug("Updating word: " + var1 + "[" + var2 + "] = " + var3);
            eV var6 = var4.d("Races");

            while(var6.size() < eU.values().length) {
               var6.add(Boolean.FALSE);
            }

            var6.a(var2, new Boolean(var3));

            for(int var7 = 0; var7 < var6.size(); ++var7) {
               var3 |= var6.ab(var7);
            }

            if (!var3) {
               hc.debug("Removing word: " + var1);
               this.rc.ac(var5);
            }

            return;
         }
      }

      if (var3) {
         hc.debug("Creating word: " + var1 + "[" + var2 + "] = " + var3);
         var4 = new eY();
         var4.b("Group", (Object)var1);
         eV var8 = new eV();

         while(var8.size() < eU.values().length) {
            var8.add(Boolean.FALSE);
         }

         var8.a(var2, new Boolean(var3));
         var4.b("Races", (Object)var8);
         this.rc.f(var4);
      }

   }

   public double dT() {
      return new Double((double)Math.round((double)this.oI.J("HazardTimeAlive") / 90.0D) / 10.0D);
   }

   public void g(double var1) {
      long var3 = Math.round(var1 * 900.0D);
      if (var3 >= 0L && var3 <= 2147483647L) {
         this.oI.b("HazardTimeAlive", (Object)(new Integer((int)var3)));
      } else {
         throw new RuntimeException("Stat value out of range");
      }
   }

   public int a(gs var1) {
      for(int var3 = 0; var3 < this.rb.size(); ++var3) {
         eY var2 = this.rb.V(var3);
         if (var2.getValueAsString("Id").equals(var1.id)) {
            return var2.J("Value.IntValue");
         }
      }

      return 0;
   }

   public void a(gs var1, int var2) {
      if (var2 < 0) {
         throw new RuntimeException("Stat value out of range");
      } else {
         eY var3;
         for(int var4 = 0; var4 < this.rb.size(); ++var4) {
            var3 = this.rb.V(var4);
            if (var3.getValueAsString("Id").equals(var1.id)) {
               var3.b("Value.IntValue", (Object)(new Integer(var2)));
               return;
            }
         }

         var3 = new eY();
         var3.b("Id", (Object)var1.id);
         eY var5 = new eY();
         var5.b("IntValue", (Object)(new Integer(var2)));
         var5.b("FloatValue", (Object)(new Double(0.0D)));
         var5.b("Denominator", (Object)(new Double(0.0D)));
         var3.b("Value", (Object)var5);
         this.rb.f(var3);
      }
   }

   public double b(gs var1) {
      for(int var3 = 0; var3 < this.rb.size(); ++var3) {
         eY var2 = this.rb.V(var3);
         if (var2.getValueAsString("Id").equals(var1.id)) {
            return var2.L("Value.FloatValue");
         }
      }

      return 0.0D;
   }

   public void a(gs var1, double var2) {
      if (var2 < 0.0D) {
         throw new RuntimeException("Stat value out of range");
      } else {
         eY var4;
         for(int var5 = 0; var5 < this.rb.size(); ++var5) {
            var4 = this.rb.V(var5);
            if (var4.getValueAsString("Id").equals(var1.id)) {
               var4.b("Value.FloatValue", (Object)(new Double(var2)));
               return;
            }
         }

         var4 = new eY();
         var4.b("Id", (Object)var1.id);
         eY var6 = new eY();
         var6.b("IntValue", (Object)(new Integer(0)));
         var6.b("FloatValue", (Object)(new Double(var2)));
         var6.b("Denominator", (Object)(new Double(0.0D)));
         var4.b("Value", (Object)var6);
         this.rb.f(var4);
      }
   }

   // $FF: synthetic method
   static boolean a(gz var0, String var1, int var2) {
      return var0.d(var1, var2);
   }

   // $FF: synthetic method
   static void a(gz var0, String var1, int var2, boolean var3) {
      var0.a(var1, var2, var3);
   }
}
