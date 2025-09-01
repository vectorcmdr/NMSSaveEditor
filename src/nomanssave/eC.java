package nomanssave;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

public class eC {
   private static eD[] jS = new eD[2];
   private final eD jT;
   private final eE jU;

   static {
      jS[0] = c("db/jsonmap.txt", "NMS 5.21 (savegame)");
      jS[1] = c("db/jsonmapac.txt", "NMS 5.21 (account)");
   }

   public static void main(String[] var0) {
      for(int var1 = 0; var1 < jS.length; ++var1) {
         if (jS[var1] != null) {
            Iterator var3 = jS[var1].iterator();

            while(var3.hasNext()) {
               eF var2 = (eF)var3.next();
               String var4 = hashName(var2.name);
               if (!var2.key.equals(var4)) {
                  System.out.println(var2.name + " = " + var2.key + " incorrect, should be " + var4);
               }
            }
         }
      }

   }

   private static String hashName(String var0) {
      long[] var1 = new long[]{8268756125562466087L, 8268756125562466087L};
      hh.a(var0.getBytes("UTF-8"), var1);
      String var2 = "0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxy";
      long var3 = 4294967295L & var1[0] >> 32;
      var3 = var3 % 68L << 32 | 4294967295L & var1[0];
      int var5 = (int)(var3 % 68L);
      int var6 = (int)((8796093022207L & var1[0] >> 21) % 68L);
      int var7 = (int)((4194303L & var1[0] >> 42) % 68L);
      return new String(new char[]{var2.charAt(var5), var2.charAt(var6), var2.charAt(var7)});
   }

   public static eC a(eG var0, String var1) {
      eD var2 = jS[var0.ordinal()];
      return var2 != null && var2.s(var1) ? new eC(var2) : null;
   }

   private static eD c(String var0, String var1) {
      InputStream var2 = Application.class.getResourceAsStream(var0);
      if (var2 == null) {
         return null;
      } else {
         try {
            return new eD(var2, var1, (eD)null);
         } catch (IOException var4) {
            hc.error("Could not load key mapping file: " + var0, var4);
            return null;
         }
      }
   }

   private eC(eD var1) {
      this.jT = var1;
      this.jU = new eE((eE)null, (eE)null);
   }

   public Map bp() {
      return (Map)this.jU.stream().collect(Collectors.toMap((var0) -> {
         return var0.key;
      }, (var0) -> {
         return var0.name;
      }));
   }

   public String q(String var1) {
      String var2;
      eF var3;
      if ((var3 = this.jU.t(var1)) != null) {
         var2 = var3.name;
      } else if ((var3 = this.jT.t(var1)) != null) {
         var2 = var3.name;
      } else {
         if ((var3 = this.jT.v(var1)) != null) {
            var2 = var3.name;
         } else {
            hc.warn("  name mapping not found: " + var1);
            var2 = var1;
         }

         this.jU.add(var1, var2);
      }

      return var2;
   }

   public String r(String var1) {
      String var2;
      eF var3;
      if ((var3 = this.jU.u(var1)) != null) {
         var2 = var3.key;
      } else if ((var3 = this.jT.u(var1)) != null) {
         var2 = var3.key;
      } else {
         var2 = var1;
         if (this.jT.t(var1) == null) {
            hc.warn("  reverse mapping not found: " + var1);
         }
      }

      return var2;
   }

   public String toString() {
      return this.jT.toString();
   }
}
