using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class gz {
   public eY oI;
   public eV rb;
   public eV rc;
   public List<object> gT;

   public static gz w(eY var0) {
      eV var1 = null;
      // PORT_TODO: eV var2 = var0.d("Stats");
      // PORT_TODO: if (var2 != null) {
         // PORT_TODO: for(int var3 = 0; var3 < var2.Count; ++var3) {
            // PORT_TODO: eY var4 = var2.V(var3);
            // PORT_TODO: if ("^GLOBAL_STATS".Equals(var4.getValueAsString("GroupId"))) {
               // PORT_TODO: var1 = var4.d("Stats");
               // PORT_TODO: break;
            // PORT_TODO: }
         // PORT_TODO: }
      // PORT_TODO: }

      // PORT_TODO: return new gz(var0, var1, var0.H("Inventory"), var0.H("Inventory_TechOnly"), var0.H("Inventory_Cargo"));
      return default;
   }

   public static Function au(string var0) {
      return (var1) => {
         return new string[]{"Exosuit", var0};
      };
   }

   public gz(eY var1, eV var2, eY var3, eY var4, eY var5) {
      this.oI = var1;
      this.rb = var2;
      // PORT_TODO: eV var6 = var1.d("KnownWords");
      // PORT_TODO: eV var7 = var1.d("KnownWordGroups");
      // PORT_TODO: if (var7 == null) {
         // PORT_TODO: var7 = new eV();
         // PORT_TODO: var1.b("KnownWordGroups", (object)var7);
      // PORT_TODO: }

      // PORT_TODO: if (var6.Count > 0) {
         // PORT_TODO: int var8 = 0;

         // PORT_TODO: label46:
         // PORT_TODO: while(true) {
            // PORT_TODO: while(true) {
               // PORT_TODO: if (var8 >= var6.Count) {
                  // PORT_TODO: goto label46;
               // PORT_TODO: }

               // PORT_TODO: eY var9 = var6.V(var8);
               // PORT_TODO: eS var10 = eS.A(var9.getValueAsString("id"));
               // PORT_TODO: if (var10 == null) {
                  // PORT_TODO: hc.warn("Could not build word groups: " + var9.getValueAsString("id"));
                  // PORT_TODO: ++var8;
               // PORT_TODO: } else {
                  // PORT_TODO: IEnumerator<object> var12 = var10.bw().GetEnumerator();

                  // PORT_TODO: while(var12.MoveNext()) {
                     // PORT_TODO: string var11 = (string)var12.Current;
                     // PORT_TODO: eU var13 = var10.z(var11);
                     // PORT_TODO: if (var13 != null) {
                        // PORT_TODO: eY var14 = new eY();
                        // PORT_TODO: var14.b("Group", (object)var11);
                        // PORT_TODO: eV var15 = new eV(new object[]{Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE});
                        // PORT_TODO: var15.a(var13.ordinal(), Boolean.TRUE);
                        // PORT_TODO: var14.b("Races", (object)var15);
                        // PORT_TODO: var7.f(var14);
                        // PORT_TODO: hc.debug("Creating word: " + var11 + "[" + var13.ordinal() + "] = true");
                     // PORT_TODO: }
                  // PORT_TODO: }

                  // PORT_TODO: var6.ac(var8);
                  // PORT_TODO: hc.debug("Removed old word: " + var9.getValueAsString("id"));
               // PORT_TODO: }
            // PORT_TODO: }
         // PORT_TODO: }
      // PORT_TODO: }

      // PORT_TODO: this.rc = var7;
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

      this.gT = new List<object>(var17);
   }

   public long dJ() {
      // PORT_TODO: return this.oI.K("Units") & 4294967295L;
      return 0;
   }

   public void e(long var1) {
      // PORT_TODO: this.oI.b("Units", (object)((int)(var1)));
   }

   public long dK() {
      // PORT_TODO: return this.oI.K("Nanites") & 4294967295L;
      return 0;
   }

   public void f(long var1) {
      // PORT_TODO: this.oI.b("Nanites", (object)((int)(var1)));
   }

   public long dL() {
      // PORT_TODO: return this.oI.K("Specials") & 4294967295L;
      return 0;
   }

   public void g(long var1) {
      // PORT_TODO: this.oI.b("Specials", (object)((int)(var1)));
   }

   public int dM() {
      // PORT_TODO: return this.oI.J("Health");
      return 0;
   }

   public void aB(int var1) {
      // PORT_TODO: this.oI.b("Health", (object)(((int)(var1))));
   }

   public int dN() {
      // PORT_TODO: return this.oI.J("Shield");
      return 0;
   }

   public void aC(int var1) {
      // PORT_TODO: this.oI.b("Shield", (object)(((int)(var1))));
   }

   public int dO() {
      // PORT_TODO: return this.oI.J("Energy");
      return 0;
   }

   public void aD(int var1) {
      // PORT_TODO: this.oI.b("Energy", (object)(((int)(var1))));
   }

   public List<object> cC() {
      return this.gT;
   }

   public int dP() {
      // PORT_TODO: return this.oI.J("KnownPortalRunes");
      return 0;
   }

   public void aE(int var1) {
      // PORT_TODO: this.oI.b("KnownPortalRunes", (object)(((int)(var1))));
   }

   public eV dQ() {
      // PORT_TODO: return this.oI.d("KnownTech");
      return null;
   }

   public eV dR() {
      // PORT_TODO: return this.oI.d("KnownProducts");
      return null;
   }

   public eV dS() {
      // PORT_TODO: return this.oI.d("KnownSpecials");
      return null;
   }

   public int bx() {
      byte var1 = 0;
      List<object> var3 = new List<object>();

      for(int var4 = 0; var4 < this.rc.Count; ++var4) {
         eY var2 = this.rc.V(var4);
         // PORT_TODO: eS var5 = eS.B(var2.getValueAsString("Group"));
         // PORT_TODO: if (var5 != null && !var3.Contains(var5.getID())) {
            // PORT_TODO: var3.Add(var5.getID());
         // PORT_TODO: }
      }

      return var1;
   }

   public int b(eU var1) {
      int var2 = 0;

      for(int var4 = 0; var4 < this.rc.Count; ++var4) {
         eY var3 = this.rc.V(var4);
         // PORT_TODO: if (var3.d("Races").ab(var1.ordinal())) {
            // PORT_TODO: ++var2;
         // PORT_TODO: }
      }

      return var2;
   }

   public gA a(eS var1) {
      return new gA(this, var1, (gA)null);
   }

   public bool d(string var1, int var2) {
      for(int var3 = 0; var3 < this.rc.Count; ++var3) {
         eY var4 = this.rc.V(var3);
         // PORT_TODO: if (var1.Equals(var4.getValueAsString("Group"))) {
            // PORT_TODO: return var4.d("Races").ab(var2);
         // PORT_TODO: }
      }

      return false;
   }

   public void a(string var1, int var2, bool var3) {
      eY var4;
      for(int var5 = 0; var5 < this.rc.Count; ++var5) {
         var4 = this.rc.V(var5);
         // PORT_TODO: if (var1.Equals(var4.getValueAsString("Group"))) {
            // PORT_TODO: hc.debug("Updating word: " + var1 + "[" + var2 + "] = " + var3);
            // PORT_TODO: eV var6 = var4.d("Races");

            // PORT_TODO: if (false) { // PORT_TODO: original while had errors
               // PORT_TODO: var6.Add(Boolean.FALSE);
            // PORT_TODO: }

            // PORT_TODO: var6.a(var2, new Boolean(var3));

            // PORT_TODO: for(int var7 = 0; var7 < var6.Count; ++var7) {
               // PORT_TODO: var3 |= var6.ab(var7);
            // PORT_TODO: }

            // PORT_TODO: if (var3 == null) {
               // PORT_TODO: hc.debug("Removing word: " + var1);
               // PORT_TODO: this.rc.ac(var5);
            // PORT_TODO: }

            // PORT_TODO: return;
         // PORT_TODO: }
      }

      if (var3) {
         hc.debug("Creating word: " + var1 + "[" + var2 + "] = " + var3);
         var4 = new eY();
         // PORT_TODO: var4.b("Group", (object)var1);
         eV var8 = new eV();

         if (false) { // PORT_TODO: original while had errors
            // PORT_TODO: var8.Add(Boolean.FALSE);
         }

         // PORT_TODO: var8.a(var2, new Boolean(var3));
         // PORT_TODO: var4.b("Races", (object)var8);
         this.rc.f(var4);
      }

   }

   public double dT() {
      // PORT_TODO: return new Double((double)Math.Round((double)this.oI.J("HazardTimeAlive") / 90.0D) / 10.0D);
      return default;
   }

   public void g(double var1) {
      // PORT_TODO: long var3 = Math.Round(var1 * 900.0D);
      if (true) { // PORT_TODO: original condition had errors
      long var3 = 0; // PORT_TODO: stub declaration
         // PORT_TODO: this.oI.b("HazardTimeAlive", (object)((int)(var3)));
      } else {
         throw new Exception("Stat value out of range");
      }
   }

   public int a(gs var1) {
      for(int var3 = 0; var3 < this.rb.Count; ++var3) {
         eY var2 = this.rb.V(var3);
         // PORT_TODO: if (var2.getValueAsString("Id").Equals(var1.id)) {
            // PORT_TODO: return var2.J("Value.IntValue");
         // PORT_TODO: }
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
            // PORT_TODO: if (var3.getValueAsString("Id").Equals(var1.id)) {
               // PORT_TODO: var3.b("Value.IntValue", (object)(((int)(var2))));
               // PORT_TODO: return;
            // PORT_TODO: }
         }

         var3 = new eY();
         // PORT_TODO: var3.b("Id", (object)var1.id);
         eY var5 = new eY();
         // PORT_TODO: var5.b("IntValue", (object)(((int)(var2))));
         // PORT_TODO: var5.b("FloatValue", (object)(new Double(0.0D)));
         // PORT_TODO: var5.b("Denominator", (object)(new Double(0.0D)));
         // PORT_TODO: var3.b("Value", (object)var5);
         this.rb.f(var3);
      }
   }

   public double b(gs var1) {
      for(int var3 = 0; var3 < this.rb.Count; ++var3) {
         eY var2 = this.rb.V(var3);
         // PORT_TODO: if (var2.getValueAsString("Id").Equals(var1.id)) {
            // PORT_TODO: return var2.L("Value.FloatValue");
         // PORT_TODO: }
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
            // PORT_TODO: if (var4.getValueAsString("Id").Equals(var1.id)) {
               // PORT_TODO: var4.b("Value.FloatValue", (object)(new Double(var2)));
               // PORT_TODO: return;
            // PORT_TODO: }
         }

         var4 = new eY();
         // PORT_TODO: var4.b("Id", (object)var1.id);
         eY var6 = new eY();
         // PORT_TODO: var6.b("IntValue", (object)(((int)(0))));
         // PORT_TODO: var6.b("FloatValue", (object)(new Double(var2)));
         // PORT_TODO: var6.b("Denominator", (object)(new Double(0.0D)));
         // PORT_TODO: var4.b("Value", (object)var6);
         this.rb.f(var4);
      }
   }
   public static bool a(gz var0, string var1, int var2) {
      return var0.d(var1, var2);
   }
   public static void a(gz var0, string var1, int var2, bool var3) {
      var0.a(var1, var2, var3);
   }
}



}
