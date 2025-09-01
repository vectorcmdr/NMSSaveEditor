package nomanssave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class gV {
   private static double[] a(eY var0, String var1) {
      eV var2 = var0.d(var1);
      if (var2.size() != 3) {
         throw new RuntimeException("Invalid " + var1 + " coordinates");
      } else {
         return new double[]{var2.aa(0), var2.aa(1), var2.aa(2)};
      }
   }

   private static void a(eY var0, String var1, double[] var2) {
      var0.b(var1, (Object)(new eV(new Object[]{new Double(Double.isNaN(var2[0]) ? 0.0D : var2[0]), new Double(Double.isNaN(var2[1]) ? 0.0D : var2[1]), new Double(Double.isNaN(var2[2]) ? 0.0D : var2[2])})));
   }

   public static boolean F(eY var0) {
      return b(var0, "^BUILDSIGNAL");
   }

   public static boolean b(eY var0, String var1) {
      eV var2 = var0.d("Objects");
      eY var3 = null;
      eY var4 = null;

      for(int var5 = 0; var5 < var2.size(); ++var5) {
         eY var6 = var2.V(var5);
         if ("^BASE_FLAG".equals(var6.getValueAsString("ObjectID"))) {
            if (var3 != null) {
               hc.warn("  multiple base computers found");
               return false;
            }

            var3 = var6;
         } else if (var1.equals(var6.getValueAsString("ObjectID"))) {
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

   public static List G(eY var0) {
      ArrayList var1 = new ArrayList();
      boolean var2 = false;
      eV var3 = var0.d("Objects");

      for(int var4 = 0; var4 < var3.size(); ++var4) {
         eY var5 = var3.V(var4);
         String var6 = var5.getValueAsString("ObjectID");
         if ("^BASE_FLAG".equals(var5.getValueAsString("ObjectID"))) {
            var2 = true;
         } else if ("^BUILDSIGNAL".equals(var6)) {
            var1.add(var5);
         } else if ("^BP_ANALYSER".equals(var6)) {
            var1.add(var5);
         } else if ("^BUILDBEACON".equals(var6)) {
            var1.add(var5);
         }
      }

      return (List)(var2 ? var1 : Collections.emptyList());
   }

   public static boolean a(eY var0, eY var1) {
      eV var2 = var0.d("Objects");
      boolean var3 = false;
      eY var4 = null;

      for(int var5 = 0; var5 < var2.size(); ++var5) {
         eY var6 = var2.V(var5);
         if ("^BASE_FLAG".equals(var6.getValueAsString("ObjectID"))) {
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

   private static void a(eY var0, eY var1, eY var2) {
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

      for(int var10 = 0; var10 < var9.size(); ++var10) {
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
