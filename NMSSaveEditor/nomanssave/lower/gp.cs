using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class gp {
   public string Name => getName();
   public int index;
   public eY oR;

   public static gp[] q(eY var0) {
      // PORT_TODO: eV var1 = var0.d("FleetFrigates");
      // PORT_TODO: return var1 == null ? new gp[0] : d(var1);
      return null;
   }

   public static gp[] d(eV var0) {
      if (var0.Count == 0) {
         return new gp[0];
      } else {
         List<object> var1 = new List<object>();
         gp[] var2 = new gp[var0 == null ? 0 : var0.Count];

         for(int var3 = 0; var3 < var2.Length; ++var3) {
            eY var4 = var0.V(var3);
            var1.Add(new gp(var3, var4));
         }

         return (gp[])var1.ToArray();
      }
   }

   public gp(int var1, eY var2) {
      this.index = var1;
      this.oR = var2;
   }

   public int getIndex() {
      return this.index;
   }

   public string getName() {
      // PORT_TODO: return this.oR.getValueAsString("CustomName");
      return default;
   }

   public void setName(string var1) {
      // PORT_TODO: this.oR.b("CustomName", (object)var1);
   }

   public gr da() {
      // PORT_TODO: return gr.an(this.oR.getValueAsString("FrigateClass.FrigateClass"));
      return default;
   }

   public void c(gr var1) {
      // PORT_TODO: this.oR.b("FrigateClass.FrigateClass", (object)var1.ToString());
   }

   public string cW() {
      int var1 = -2;
      // PORT_TODO: eV var2 = this.oR.d("TraitIDs");

      // PORT_TODO: for(int var3 = 0; var3 < var2.Count; ++var3) {
         // PORT_TODO: string var4 = var2.X(var3);
         // PORT_TODO: er var5 = er.o(var4);
         // PORT_TODO: if (var5 != null && var5.aW()) {
            // PORT_TODO: ++var1;
            // PORT_TODO: return default;
         // PORT_TODO: }
      // PORT_TODO: }

      if (var1 < 0) {
         var1 = 0;
      }

      if (var1 > 3) {
         var1 = 3;
      }

      // PORT_TODO: return gN.Values[var1].Name;
      return default;
   }

   public string cU() {
      // PORT_TODO: return this.oR.d("HomeSystemSeed").X(1);
      return default;
   }

   public void ah(string var1) {
      // PORT_TODO: this.oR.d("HomeSystemSeed").a(1, var1);
   }

   public string cV() {
      // PORT_TODO: return this.oR.d("ResourceSeed").X(1);
      return default;
   }

   public void ai(string var1) {
      // PORT_TODO: this.oR.d("ResourceSeed").a(1, var1);
   }

   public string db() {
      // PORT_TODO: return this.oR.getValueAsString("Race.AlienRace");
      return default;
   }

   public void am(string var1) {
      // PORT_TODO: this.oR.b("Race.AlienRace", (object)var1);
   }

   public int aq(int var1) {
      // PORT_TODO: eV var2 = this.oR.d("Stats");
      // PORT_TODO: return var2.Count <= var1 ? 0 : var2.Y(var1);
      return 0;
   }

   public void e(int var1, int var2) {
      // PORT_TODO: eV var3 = this.oR.d("Stats");

      // PORT_TODO: while(var3.Count <= var1) {
         // PORT_TODO: var3.f(0);
      // PORT_TODO: }

      // PORT_TODO: var3.a(var1, ((int)(var2)));
   }

   public er ar(int var1) {
      // PORT_TODO: eV var2 = this.oR.d("TraitIDs");
      // PORT_TODO: if (var1 < var2.Count) {
         // PORT_TODO: string var3 = var2.X(var1);
         // PORT_TODO: return er.o(var3);
      // PORT_TODO: } else {
         // PORT_TODO: return null;
      // PORT_TODO: }
      return default;
   }

   public void a(int var1, er var2) {
      // PORT_TODO: eV var3 = this.oR.d("TraitIDs");
      // PORT_TODO: if (var1 < var3.Count) {
         // PORT_TODO: var3.a(var1, var2 == null ? "^" : var2.getID());
      // PORT_TODO: }

   }

   public int dc() {
      // PORT_TODO: return this.oR.J("TotalNumberOfExpeditions");
      return 0;
   }

   public void @as(int var1) {
      // PORT_TODO: this.oR.b("TotalNumberOfExpeditions", (object)(((int)(var1))));
   }

   public int dd() {
      // PORT_TODO: return this.oR.J("TotalNumberOfSuccessfulEvents");
      return 0;
   }

   public void at(int var1) {
      // PORT_TODO: this.oR.b("TotalNumberOfSuccessfulEvents", (object)(((int)(var1))));
   }

   public int de() {
      // PORT_TODO: return this.oR.J("TotalNumberOfFailedEvents");
      return 0;
   }

   public void au(int var1) {
      // PORT_TODO: this.oR.b("TotalNumberOfFailedEvents", (object)(((int)(var1))));
   }

   public int df() {
      // PORT_TODO: return this.oR.J("NumberOfTimesDamaged");
      return 0;
   }

   public void av(int var1) {
      // PORT_TODO: this.oR.b("NumberOfTimesDamaged", (object)(((int)(var1))));
   }

   public int dg() {
      // PORT_TODO: return this.oR.J("RepairsMade");
      return 0;
   }

   public void aw(int var1) {
      // PORT_TODO: this.oR.b("RepairsMade", (object)(((int)(var1))));
   }

   public int dh() {
      // PORT_TODO: return this.oR.J("DamageTaken");
      return 0;
   }

   public void ax(int var1) {
      // PORT_TODO: this.oR.b("DamageTaken", (object)(((int)(var1))));
   }

   public string toString() {
      string var1 = this.Name;
      if (var1 != null && var1.Length != 0) {
         return var1;
      } else {
         gr var2 = this.da();
         return var2 == null ? "Unknown [" + this.index + "]" : var2 + " [" + this.index + "]";
      }
   }
}



}
