package nomanssave;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;

public class gc {
   private static final long nc = -11644473600000L;

   public static String a(InputStream var0) {
      byte[] var1 = new byte[16];
      hk.readFully(var0, var1);
      return h(var1);
   }

   public static String cA() {
      byte[] var0 = new byte[16];
      (new SecureRandom()).nextBytes(var0);
      return h(var0);
   }

   private static String h(byte[] var0) {
      StringBuilder var1 = new StringBuilder();
      a(var0[3], var1);
      a(var0[2], var1);
      a(var0[1], var1);
      a(var0[0], var1);
      a(var0[5], var1);
      a(var0[4], var1);
      a(var0[7], var1);
      a(var0[6], var1);
      a(var0[8], var1);
      a(var0[9], var1);
      a(var0[10], var1);
      a(var0[11], var1);
      a(var0[12], var1);
      a(var0[13], var1);
      a(var0[14], var1);
      a(var0[15], var1);
      return var1.toString();
   }

   private static void a(byte var0, StringBuilder var1) {
      int var2 = (240 & var0) >> 4;
      int var3 = 15 & var0;
      var1.append("0123456789ABCDEF".charAt(var2));
      var1.append("0123456789ABCDEF".charAt(var3));
   }

   public static void a(OutputStream var0, String var1) {
      if (var1.length() > 32) {
         throw new IOException("invalid container path");
      } else {
         while(var1.length() < 32) {
            var1 = "0" + var1;
         }

         var1 = var1.toLowerCase();
         byte[] var2 = new byte[]{(byte)Integer.parseInt(var1.substring(6, 8), 16), (byte)Integer.parseInt(var1.substring(4, 6), 16), (byte)Integer.parseInt(var1.substring(2, 4), 16), (byte)Integer.parseInt(var1.substring(0, 2), 16), (byte)Integer.parseInt(var1.substring(10, 12), 16), (byte)Integer.parseInt(var1.substring(8, 10), 16), (byte)Integer.parseInt(var1.substring(14, 16), 16), (byte)Integer.parseInt(var1.substring(12, 14), 16), (byte)Integer.parseInt(var1.substring(16, 18), 16), (byte)Integer.parseInt(var1.substring(18, 20), 16), (byte)Integer.parseInt(var1.substring(20, 22), 16), (byte)Integer.parseInt(var1.substring(22, 24), 16), (byte)Integer.parseInt(var1.substring(24, 26), 16), (byte)Integer.parseInt(var1.substring(26, 28), 16), (byte)Integer.parseInt(var1.substring(28, 30), 16), (byte)Integer.parseInt(var1.substring(30, 32), 16)};
         var0.write(var2);
      }
   }

   public static long b(InputStream var0) {
      return hk.f(var0) / 10000L + -11644473600000L;
   }

   public static void a(OutputStream var0, long var1) {
      hk.b(var0, (var1 - -11644473600000L) * 10000L);
   }

   public static String c(InputStream var0) {
      int var1 = hk.readInt(var0);
      if (var1 < 0) {
         throw new IOException("negative length");
      } else {
         byte[] var2 = new byte[var1 * 2];
         hk.readFully(var0, var2);
         return new String(var2, "UTF-16LE");
      }
   }

   public static void b(OutputStream var0, String var1) {
      hk.a(var0, var1.length());
      var0.write(var1.getBytes("UTF-16LE"));
   }

   public static String d(InputStream var0) {
      byte[] var1 = new byte[128];
      hk.readFully(var0, var1);

      int var2;
      for(var2 = 0; var2 < var1.length && (var1[var2] != 0 || var1[var2 + 1] != 0); var2 += 2) {
      }

      return new String(var1, 0, var2, "UTF-16LE");
   }

   public static String e(InputStream var0) {
      byte[] var1 = new byte[128];
      hk.readFully(var0, var1);

      int var2;
      for(var2 = 0; var2 < var1.length && var1[var2] != 0; ++var2) {
      }

      return new String(var1, 0, var2, "UTF-8");
   }

   public static void c(OutputStream var0, String var1) {
      byte[] var2 = var1.getBytes("UTF-8");
      if (var2.length < 128) {
         byte[] var3 = new byte[128];
         System.arraycopy(var2, 0, var3, 0, var2.length);
         var3[var2.length] = 0;
         var2 = var3;
      }

      var0.write(var2, 0, 128);
   }
}
