using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class gz {
   public eY oI;
   public eV rb;
   public eV rc;
   public List<object> gT;

   public static gz w(eY var0) {
      eV var1 = null;
      eV var2 = var0.d("Stats");
      if (var2 != null) {
         for(int var3 = 0; var3 < var2.Count; ++var3) {
            eY var4 = var2.V(var3);
            if ("^GLOBAL_STATS".Equals(var4.getValueAsString("GroupId"))) {
               var1 = var4.d("Stats");
               break;
            }
         }
      }

      return new gz(var0, var1, var0.H("Inventory"), var0.H("Inventory_TechOnly"), var0.H("Inventory_Cargo"));
   }

   public static Function au(string var0) {
      return (var1) => {
         return new string[]{"Exosuit", var0};
      };
   }

   public gz(eY var1, eV var2, eY var3, eY var4, eY var5) {
      this.oI = var1;
      this.rb = var2;
      eV var6 = var1.d("KnownWords");
      eV var7 = var1.d("KnownWordGroups");
      if (var7 == null) {
         var7 = new eV();
         var1.b("KnownWordGroups", (Object)var7);
      }

      if (var6.Count > 0) {
         int var8 = 0;

         label46:
         while(true) {
            while(true) {
               if (var8 >= var6.Count) {
                  goto label46;
               }

               eY var9 = var6.V(var8);
               eS var10 = eS.A(var9.getValueAsString("id"));
               if (var10 == null) {
                  hc.warn("Could not build word groups: " + var9.getValueAsString("id"));
                  ++var8;
               } else {
                  IEnumerator<object> var12 = var10.bw().GetEnumerator();

                  while(var12.MoveNext()) {
                     string var11 = (string)var12.Current;
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
      bool var20 = false;
      bool var22 = false;
      short var24 = 0;
      byte var25 = 8;
      byte var26 = 6;
      byte var27 = 7;
      byte var16 = 2;
      string var18;
      string var19;
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

      List<object> var17 = new List<object>();
      var17.Add(new gt(au(var18), var3, var21, var25, var26, false, true));
      if (var4 != null) {
         var17.Add(new gt(au("Technology"), var4, var23, var27, var16, true, true));
      }

      if (var5 != null) {
         var17.Add(new gt(au(var19), var5, var24, 8, 6, false, true));
      }

      this.gT = JavaCollections.UnmodifiableList(var17);
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

   public List<object> cC() {
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
      List<object> var3 = new List<object>();

      for(int var4 = 0; var4 < this.rc.Count; ++var4) {
         eY var2 = this.rc.V(var4);
         eS var5 = eS.B(var2.getValueAsString("Group"));
         if (var5 != null && !var3.Contains(var5.getID())) {
            var3.Add(var5.getID());
         }
      }

      return var1;
   }

   public int b(eU var1) {
      int var2 = 0;

      for(int var4 = 0; var4 < this.rc.Count; ++var4) {
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

   public bool d(string var1, int var2) {
      for(int var3 = 0; var3 < this.rc.Count; ++var3) {
         eY var4 = this.rc.V(var3);
         if (var1.Equals(var4.getValueAsString("Group"))) {
            return var4.d("Races").ab(var2);
         }
      }

      return false;
   }

   public void a(string var1, int var2, bool var3) {
      eY var4;
      for(int var5 = 0; var5 < this.rc.Count; ++var5) {
         var4 = this.rc.V(var5);
         if (var1.Equals(var4.getValueAsString("Group"))) {
            hc.debug("Updating word: " + var1 + "[" + var2 + "] = " + var3);
            eV var6 = var4.d("Races");

            while(var6.Count < eU.Values.length) {
               var6.Add(Boolean.FALSE);
            }

            var6.a(var2, new Boolean(var3));

            for(int var7 = 0; var7 < var6.Count; ++var7) {
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

         while(var8.Count < eU.Values.length) {
            var8.Add(Boolean.FALSE);
         }

         var8.a(var2, new Boolean(var3));
         var4.b("Races", (Object)var8);
         this.rc.f(var4);
      }

   }

   public double dT() {
      return new Double((double)Math.Round((double)this.oI.J("HazardTimeAlive") / 90.0D) / 10.0D);
   }

   public void g(double var1) {
      long var3 = Math.Round(var1 * 900.0D);
      if (var3 >= 0L && var3 <= 2147483647L) {
         this.oI.b("HazardTimeAlive", (Object)(new Integer((int)var3)));
      } else {
         throw new Exception("Stat value out of range");
      }
   }

   public int a(gs var1) {
      for(int var3 = 0; var3 < this.rb.Count; ++var3) {
         eY var2 = this.rb.V(var3);
         if (var2.getValueAsString("Id").Equals(var1.id)) {
            return var2.J("Value.IntValue");
         }
      }

      return 0;
   }

   public void a(gs var1, int var2) {
      if (var2 < 0) {
         throw new Exception("Stat value out of range");
      } else {
         eY var3;
         for(int var4 = 0; var4 < this.rb.Count; ++var4) {
            var3 = this.rb.V(var4);
            if (var3.getValueAsString("Id").Equals(var1.id)) {
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
      for(int var3 = 0; var3 < this.rb.Count; ++var3) {
         eY var2 = this.rb.V(var3);
         if (var2.getValueAsString("Id").Equals(var1.id)) {
            return var2.L("Value.FloatValue");
         }
      }

      return 0.0D;
   }

   public void a(gs var1, double var2) {
      if (var2 < 0.0D) {
         throw new Exception("Stat value out of range");
      } else {
         eY var4;
         for(int var5 = 0; var5 < this.rb.Count; ++var5) {
            var4 = this.rb.V(var5);
            if (var4.getValueAsString("Id").Equals(var1.id)) {
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
   static bool a(gz var0, string var1, int var2) {
      return var0.d(var1, var2);
   }

   // $FF: synthetic method
   static void a(gz var0, string var1, int var2, bool var3) {
      var0.a(var1, var2, var3);
   }
}

}
