using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class gV {
   public static double[] a(eY var0, string var1) {
      // PORT_TODO: eV var2 = var0.d(var1);
      // PORT_TODO: if (var2.Count != 3) {
         // PORT_TODO: throw new Exception("Invalid " + var1 + " coordinates");
      // PORT_TODO: } else {
         // PORT_TODO: return new double[]{var2.aa(0), var2.aa(1), var2.aa(2)};
      // PORT_TODO: }
      return null;
   }

   public static void a(eY var0, string var1, double[] var2) {
      // PORT_TODO: var0.b(var1, (object)(new eV(new object[]{new Double(Double.isNaN(var2[0]) ? 0.0D : var2[0]), new Double(Double.isNaN(var2[1]) ? 0.0D : var2[1]), new Double(Double.isNaN(var2[2]) ? 0.0D : var2[2])})));
   }

   public static bool F(eY var0) {
      return b(var0, "^BUILDSIGNAL");
   }

   public static bool b(eY var0, string var1) {
      // PORT_TODO: eV var2 = var0.d("Objects");
      eY var3 = null;
      eY var4 = null;

      // PORT_TODO: for(int var5 = 0; var5 < var2.Count; ++var5) {
         // PORT_TODO: eY var6 = var2.V(var5);
         // PORT_TODO: if ("^BASE_FLAG".Equals(var6.getValueAsString("ObjectID"))) {
            // PORT_TODO: if (var3 != null) {
               // PORT_TODO: hc.warn("  multiple base computers found");
               // PORT_TODO: return false;
            // PORT_TODO: }

            // PORT_TODO: var3 = var6;
         // PORT_TODO: } else if (var1.Equals(var6.getValueAsString("ObjectID"))) {
            // PORT_TODO: if (var4 != null) {
               // PORT_TODO: hc.warn("  multiple " + var1 + " objects found");
               // PORT_TODO: return false;
            // PORT_TODO: }

            // PORT_TODO: var4 = var6;
         // PORT_TODO: }
      // PORT_TODO: }

      if (var3 == null) {
         hc.warn("  no base computer found");
         return false;
      } else if (var4 == null) {
         hc.warn("  no " + var1 + " object found");
         return false;
      } else {
         a(var0, var3, var4);
         return true;
      }
   }

   public static List<object> G(eY var0) {
      List<object> var1 = new List<object>();
      bool var2 = false;
      // PORT_TODO: eV var3 = var0.d("Objects");

      // PORT_TODO: for(int var4 = 0; var4 < var3.Count; ++var4) {
         // PORT_TODO: eY var5 = var3.V(var4);
         // PORT_TODO: string var6 = var5.getValueAsString("ObjectID");
         // PORT_TODO: if ("^BASE_FLAG".Equals(var5.getValueAsString("ObjectID"))) {
            // PORT_TODO: var2 = true;
         // PORT_TODO: } else if ("^BUILDSIGNAL".Equals(var6)) {
            // PORT_TODO: var1.Add(var5);
         // PORT_TODO: } else if ("^BP_ANALYSER".Equals(var6)) {
            // PORT_TODO: var1.Add(var5);
         // PORT_TODO: } else if ("^BUILDBEACON".Equals(var6)) {
            // PORT_TODO: var1.Add(var5);
         // PORT_TODO: }
      // PORT_TODO: }

      return (List<object>)(var2 ? var1 : new List<object>());
   }

   public static bool a(eY var0, eY var1) {
      // PORT_TODO: eV var2 = var0.d("Objects");
      bool var3 = false;
      eY var4 = null;

      // PORT_TODO: for(int var5 = 0; var5 < var2.Count; ++var5) {
         // PORT_TODO: eY var6 = var2.V(var5);
         // PORT_TODO: if ("^BASE_FLAG".Equals(var6.getValueAsString("ObjectID"))) {
            // PORT_TODO: if (var4 != null) {
               // PORT_TODO: hc.warn("  multiple base computers found");
               // PORT_TODO: return false;
            // PORT_TODO: }

            // PORT_TODO: var4 = var6;
         // PORT_TODO: } else if (var6 == var1) {
            // PORT_TODO: var3 = true;
         // PORT_TODO: }
      // PORT_TODO: }

      if (var4 == null) {
         hc.warn("  no base computer found");
         return false;
      } else if (var3 == null) {
         hc.warn("  replacement object found");
         return false;
      } else {
         a(var0, var4, var1);
         return true;
      }
   }

   public static void a(eY var0, eY var1, eY var2) {
      double[] var3 = a(var0, "Position");
      double[] var4 = a(var0, "Forward");
      double[] var5 = a(var2, "Position");
      gT var6 = new gT(var3, var4);
      double[] var7 = var6.c(var5);
      var7[0] += var3[0];
      var7[1] += var3[1];
      var7[2] += var3[2];
      a(var0, "Position", var7);
      var7 = a(var1, "At");
      double[] var8 = a(var2, "At");
      a(var1, "At", var8);
      a(var2, "At", var7);
      var7 = new double[]{-var5[0], -var5[1], -var5[2]};
      a(var2, "Position", var7);
      // PORT_TODO: eV var9 = var0.d("Objects");

      // PORT_TODO: for(int var10 = 0; var10 < var9.Count; ++var10) {
         // PORT_TODO: eY var11 = var9.V(var10);
         // PORT_TODO: if (var11 != var1 && var11 != var2) {
            // PORT_TODO: var7 = a(var11, "Position");
            // PORT_TODO: var7[0] -= var5[0];
            // PORT_TODO: var7[1] -= var5[1];
            // PORT_TODO: var7[2] -= var5[2];
            // PORT_TODO: a(var11, "Position", var7);
         // PORT_TODO: }
      // PORT_TODO: }

   }
}



}
