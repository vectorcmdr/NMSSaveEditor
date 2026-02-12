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

public class gp {
   private int index;
   private eY oR;

   public static gp[] q(eY var0) {
      eV var1 = var0.d("FleetFrigates");
      return var1 == null ? new gp[0] : d(var1);
   }

   public static gp[] d(eV var0) {
      if (var0.Count == 0) {
         return new gp[0];
      } else {
         List<object> var1 = new List<object>();
         gp[] var2 = new gp[var0 == null ? 0 : var0.Count];

         for(int var3 = 0; var3 < var2.length; ++var3) {
            eY var4 = var0.V(var3);
            var1.Add(new gp(var3, var4));
         }

         return (gp[])var1.ToArray(new gp[0]);
      }
   }

   private gp(int var1, eY var2) {
      this.index = var1;
      this.oR = var2;
   }

   public int getIndex() {
      return this.index;
   }

   public string getName() {
      return this.oR.getValueAsString("CustomName");
   }

   public void setName(string var1) {
      this.oR.b("CustomName", (Object)var1);
   }

   public gr da() {
      return gr.an(this.oR.getValueAsString("FrigateClass.FrigateClass"));
   }

   public void c(gr var1) {
      this.oR.b("FrigateClass.FrigateClass", (Object)var1.ToString());
   }

   public string cW() {
      int var1 = -2;
      eV var2 = this.oR.d("TraitIDs");

      for(int var3 = 0; var3 < var2.Count; ++var3) {
         string var4 = var2.X(var3);
         er var5 = er.o(var4);
         if (var5 != null && var5.aW()) {
            ++var1;
         }
      }

      if (var1 < 0) {
         var1 = 0;
      }

      if (var1 > 3) {
         var1 = 3;
      }

      return gN.Values[var1].ToString();
   }

   public string cU() {
      return this.oR.d("HomeSystemSeed").X(1);
   }

   public void ah(string var1) {
      this.oR.d("HomeSystemSeed").a(1, var1);
   }

   public string cV() {
      return this.oR.d("ResourceSeed").X(1);
   }

   public void ai(string var1) {
      this.oR.d("ResourceSeed").a(1, var1);
   }

   public string db() {
      return this.oR.getValueAsString("Race.AlienRace");
   }

   public void am(string var1) {
      this.oR.b("Race.AlienRace", (Object)var1);
   }

   public int aq(int var1) {
      eV var2 = this.oR.d("Stats");
      return var2.Count <= var1 ? 0 : var2.Y(var1);
   }

   public void e(int var1, int var2) {
      eV var3 = this.oR.d("Stats");

      while(var3.Count <= var1) {
         var3.f(0);
      }

      var3.a(var1, new Integer(var2));
   }

   public er ar(int var1) {
      eV var2 = this.oR.d("TraitIDs");
      if (var1 < var2.Count) {
         string var3 = var2.X(var1);
         return er.o(var3);
      } else {
         return null;
      }
   }

   public void a(int var1, er var2) {
      eV var3 = this.oR.d("TraitIDs");
      if (var1 < var3.Count) {
         var3.a(var1, var2 == null ? "^" : var2.getID());
      }

   }

   public int dc() {
      return this.oR.J("TotalNumberOfExpeditions");
   }

   public void @as(int var1) {
      this.oR.b("TotalNumberOfExpeditions", (Object)(new Integer(var1)));
   }

   public int dd() {
      return this.oR.J("TotalNumberOfSuccessfulEvents");
   }

   public void at(int var1) {
      this.oR.b("TotalNumberOfSuccessfulEvents", (Object)(new Integer(var1)));
   }

   public int de() {
      return this.oR.J("TotalNumberOfFailedEvents");
   }

   public void au(int var1) {
      this.oR.b("TotalNumberOfFailedEvents", (Object)(new Integer(var1)));
   }

   public int df() {
      return this.oR.J("NumberOfTimesDamaged");
   }

   public void av(int var1) {
      this.oR.b("NumberOfTimesDamaged", (Object)(new Integer(var1)));
   }

   public int dg() {
      return this.oR.J("RepairsMade");
   }

   public void aw(int var1) {
      this.oR.b("RepairsMade", (Object)(new Integer(var1)));
   }

   public int dh() {
      return this.oR.J("DamageTaken");
   }

   public void ax(int var1) {
      this.oR.b("DamageTaken", (Object)(new Integer(var1)));
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
