using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace NMSSaveEditor
{

public interface fq {
   int lz = 15;

   static string c(long var0) {
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
      return var5.ToString();
   }

   static string c(fq var0) {
      if (var0 is fJ) {
         return "Steam";
      } else if (var0 is fT) {
         return "Xbox Game Pass";
      } else {
         return var0 is fA ? "PS4 - Save Wizard" : null;
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
               }).Length > 0) {
                  return new fT(var0, var1);
               }

               if (var0.listFiles((var0x) -> {
                  return var0x.getName().equalsIgnoreCase("accountdata.hg") || Pattern.matches("save\\d*.hg", var0x.getName().ToLower());
               }).Length > 0) {
                  return new fJ(var0, var1);
               }

               if (var0.listFiles((var0x) -> {
                  return Pattern.matches("savedata\\d{2}.hg", var0x.getName().ToLower());
               }).Length > 0) {
                  return new fA(var0, var1);
               }
            } else {
               if (var0.getName().equalsIgnoreCase("containers.index")) {
                  return new fT(var0.getParentFile(), var1);
               }

               if (var0.getName().equalsIgnoreCase("accountdata.hg") || Pattern.matches("save\\d*.hg", var0.getName().ToLower())) {
                  return new fJ(var0.getParentFile(), var1);
               }

               if (Pattern.matches("savedata\\d{2}.hg", var0.getName().ToLower())) {
                  return new fA(var0.getParentFile(), var1);
               }
            }
         } catch (IOException var3) {
            hc.error("cannot load storage", var3);
         }

         return null;
      }
   }

   static fq a(string var0, File var1, fR var2) {
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
      return (ft[])new List<object>(new object[]{this.bU()).stream().filter((var0) -> {
         return !var0.isEmpty();
      }).toArray((var0) -> {
         return new ft[var0];
      });
   }

   int W(string var1);

   default bool bW() {
      return false;
   }

   default string a(int var1, eY var2) {
      throw new IOException("cannot create slot " + (var1 + 1));
   }

   void X(string var1);
}

}
