package nomanssave;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

public interface fq {
   int lz = 15;

   static String c(long var0) {
      int var2 = (int)(var0 % 60L);
      var0 /= 60L;
      int var3 = (int)(var0 % 60L);
      var0 /= 60L;
      int var4 = (int)var0;
      StringBuilder var5 = new StringBuilder();
      var5.append(var4);
      var5.append(':');
      if (var3 < 10) {
         var5.append(0);
      }

      var5.append(var3);
      return var5.toString();
   }

   static String c(fq var0) {
      if (var0 instanceof fJ) {
         return "Steam";
      } else if (var0 instanceof fT) {
         return "Xbox Game Pass";
      } else {
         return var0 instanceof fA ? "PS4 - Save Wizard" : null;
      }
   }

   static fq a(File var0, fR var1) {
      if (!var0.exists()) {
         return null;
      } else {
         try {
            if (var0.isDirectory()) {
               if (var0.listFiles((var0x) -> {
                  return var0x.getName().equalsIgnoreCase("containers.index");
               }).length > 0) {
                  return new fT(var0, var1);
               }

               if (var0.listFiles((var0x) -> {
                  return var0x.getName().equalsIgnoreCase("accountdata.hg") || Pattern.matches("save\\d*.hg", var0x.getName().toLowerCase());
               }).length > 0) {
                  return new fJ(var0, var1);
               }

               if (var0.listFiles((var0x) -> {
                  return Pattern.matches("savedata\\d{2}.hg", var0x.getName().toLowerCase());
               }).length > 0) {
                  return new fA(var0, var1);
               }
            } else {
               if (var0.getName().equalsIgnoreCase("containers.index")) {
                  return new fT(var0.getParentFile(), var1);
               }

               if (var0.getName().equalsIgnoreCase("accountdata.hg") || Pattern.matches("save\\d*.hg", var0.getName().toLowerCase())) {
                  return new fJ(var0.getParentFile(), var1);
               }

               if (Pattern.matches("savedata\\d{2}.hg", var0.getName().toLowerCase())) {
                  return new fA(var0.getParentFile(), var1);
               }
            }
         } catch (IOException var3) {
            hc.error("cannot load storage", var3);
         }

         return null;
      }
   }

   static fq a(String var0, File var1, fR var2) {
      if (!var1.exists()) {
         return null;
      } else if (var0 == null) {
         return a(var1, var2);
      } else {
         try {
            if ("Steam".equals(var0)) {
               return new fJ(var1, var2);
            }

            if ("Xbox Game Pass".equals(var0)) {
               return new fT(var1, var2);
            }

            if ("PS4 - Save Wizard".equals(var0)) {
               return new fA(var1, var2);
            }
         } catch (IOException var4) {
            hc.error("cannot load storage", var4);
         }

         return null;
      }
   }

   File bS();

   fr bT();

   ft[] bU();

   default ft[] bV() {
      return (ft[])Arrays.asList(this.bU()).stream().filter((var0) -> {
         return !var0.isEmpty();
      }).toArray((var0) -> {
         return new ft[var0];
      });
   }

   int W(String var1);

   default boolean bW() {
      return false;
   }

   default String a(int var1, eY var2) {
      throw new IOException("cannot create slot " + (var1 + 1));
   }

   void X(String var1);
}
