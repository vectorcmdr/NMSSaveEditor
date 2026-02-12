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

public class gV {
   public static double[] a(eY var0, string var1) {
      eV var2 = var0.d(var1);
      if (var2.Count != 3) {
         throw new Exception("Invalid " + var1 + " coordinates");
      } else {
         return new double[]{var2.aa(0), var2.aa(1), var2.aa(2)};
      }
   }

   public static void a(eY var0, string var1, double[] var2) {
      var0.b(var1, (Object)(new eV(new Object[]{new Double(double.IsNaN(var2[0]) ? 0.0D : var2[0]), new Double(double.IsNaN(var2[1]) ? 0.0D : var2[1]), new Double(double.IsNaN(var2[2]) ? 0.0D : var2[2])})));
   }

   public static bool F(eY var0) {
      return b(var0, "^BUILDSIGNAL");
   }

   public static bool b(eY var0, string var1) {
      eV var2 = var0.d("Objects");
      eY var3 = null;
      eY var4 = null;

      for(int var5 = 0; var5 < var2.Count; ++var5) {
         eY var6 = var2.V(var5);
         if ("^BASE_FLAG".Equals(var6.getValueAsString("ObjectID"))) {
            if (var3 != null) {
               hc.warn("  multiple base computers found");
               return false;
            }

            var3 = var6;
         } else if (var1.Equals(var6.getValueAsString("ObjectID"))) {
            if (var4 != null) {
               hc.warn("  multiple " + var1 + " objects found");
               return false;
            }

            var4 = var6;
         }
      }

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
      eV var3 = var0.d("Objects");

      for(int var4 = 0; var4 < var3.Count; ++var4) {
         eY var5 = var3.V(var4);
         string var6 = var5.getValueAsString("ObjectID");
         if ("^BASE_FLAG".Equals(var5.getValueAsString("ObjectID"))) {
            var2 = true;
         } else if ("^BUILDSIGNAL".Equals(var6)) {
            var1.Add(var5);
         } else if ("^BP_ANALYSER".Equals(var6)) {
            var1.Add(var5);
         } else if ("^BUILDBEACON".Equals(var6)) {
            var1.Add(var5);
         }
      }

      return (List)(var2 ? var1 : new List<object>());
   }

   public static bool a(eY var0, eY var1) {
      eV var2 = var0.d("Objects");
      bool var3 = false;
      eY var4 = null;

      for(int var5 = 0; var5 < var2.Count; ++var5) {
         eY var6 = var2.V(var5);
         if ("^BASE_FLAG".Equals(var6.getValueAsString("ObjectID"))) {
            if (var4 != null) {
               hc.warn("  multiple base computers found");
               return false;
            }

            var4 = var6;
         } else if (var6 == var1) {
            var3 = true;
         }
      }

      if (var4 == null) {
         hc.warn("  no base computer found");
         return false;
      } else if (!var3) {
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
      eV var9 = var0.d("Objects");

      for(int var10 = 0; var10 < var9.Count; ++var10) {
         eY var11 = var9.V(var10);
         if (var11 != var1 && var11 != var2) {
            var7 = a(var11, "Position");
            var7[0] -= var5[0];
            var7[1] -= var5[1];
            var7[2] -= var5[2];
            a(var11, "Position", var7);
         }
      }

   }
}

}
