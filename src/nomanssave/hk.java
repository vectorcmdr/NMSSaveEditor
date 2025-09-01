package nomanssave;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class hk {
   private static final String sM = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

   public static String k(byte[] var0) {
      StringBuilder var1 = new StringBuilder();

      int var2;
      int var3;
      for(var2 = 0; var2 + 3 <= var0.length; var2 += 3) {
         var3 = (255 & var0[var2]) << 16 | (255 & var0[var2 + 1]) << 8 | 255 & var0[var2 + 2];
         var1.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((16515072 & var3) >> 18));
         var1.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((258048 & var3) >> 12));
         var1.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((4032 & var3) >> 6));
         var1.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(63 & var3));
      }

      if (var2 + 2 == var0.length) {
         var3 = (255 & var0[var2]) << 16 | (255 & var0[var2 + 1]) << 8;
         var1.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((16515072 & var3) >> 18));
         var1.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((258048 & var3) >> 12));
         var1.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((4032 & var3) >> 6));
      }

      if (var2 + 1 == var0.length) {
         var3 = (255 & var0[var2]) << 16;
         var1.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((16515072 & var3) >> 18));
         var1.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((258048 & var3) >> 12));
      }

      return var1.toString();
   }

   public static byte[] aD(String var0) {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();

      int var2;
      int var3;
      int var4;
      int var5;
      for(var2 = 0; var2 + 4 <= var0.length(); var2 += 4) {
         var3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(var0.charAt(var2));
         var4 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(var0.charAt(var2 + 1));
         var5 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(var0.charAt(var2 + 2));
         int var6 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(var0.charAt(var2 + 3));
         if (var3 < 0 || var4 < 0 || var5 < 0 || var6 < 0) {
            throw new RuntimeException("Invalid base64 character");
         }

         var1.write(var3 << 2 | var4 >> 4);
         var1.write((15 & var4) << 4 | var5 >> 2);
         var1.write((3 & var5) << 6 | var6);
      }

      if (var2 + 3 == var0.length()) {
         var3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(var0.charAt(var2));
         var4 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(var0.charAt(var2 + 1));
         var5 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(var0.charAt(var2 + 2));
         if (var3 < 0 || var4 < 0 || var5 < 0) {
            throw new RuntimeException("Invalid base64 character");
         }

         var1.write(var3 << 2 | var4 >> 4);
         var1.write((15 & var4) << 4 | var5 >> 2);
      }

      if (var2 + 2 == var0.length()) {
         var3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(var0.charAt(var2));
         var4 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(var0.charAt(var2 + 1));
         if (var3 < 0 || var4 < 0) {
            throw new RuntimeException("Invalid base64 character");
         }

         var1.write(var3 << 2 | var4 >> 4);
      }

      if (var2 + 1 == var0.length()) {
         throw new RuntimeException("Unfinished base64 data");
      } else {
         return var1.toByteArray();
      }
   }

   public static int readInt(InputStream var0) {
      byte[] var1 = new byte[4];
      readFully(var0, var1);
      return (255 & var1[3]) << 24 | (255 & var1[2]) << 16 | (255 & var1[1]) << 8 | 255 & var1[0];
   }

   public static void a(OutputStream var0, int var1) {
      var0.write(255 & var1);
      var0.write(255 & var1 >> 8);
      var0.write(255 & var1 >> 16);
      var0.write(255 & var1 >> 24);
   }

   public static long f(InputStream var0) {
      byte[] var1 = new byte[8];
      readFully(var0, var1);
      return (255L & (long)var1[7]) << 56 | (255L & (long)var1[6]) << 48 | (255L & (long)var1[5]) << 40 | (255L & (long)var1[4]) << 32 | (255L & (long)var1[3]) << 24 | (255L & (long)var1[2]) << 16 | (255L & (long)var1[1]) << 8 | 255L & (long)var1[0];
   }

   public static void b(OutputStream var0, long var1) {
      var0.write((int)(255L & var1));
      var0.write((int)(255L & var1 >> 8));
      var0.write((int)(255L & var1 >> 16));
      var0.write((int)(255L & var1 >> 24));
      var0.write((int)(255L & var1 >> 32));
      var0.write((int)(255L & var1 >> 40));
      var0.write((int)(255L & var1 >> 48));
      var0.write((int)(255L & var1 >> 56));
   }

   public static byte[] l(File var0) {
      FileInputStream var1 = new FileInputStream(var0);

      byte[] var3;
      try {
         var3 = g(var1);
      } finally {
         var1.close();
      }

      return var3;
   }

   public static byte[] g(InputStream var0) {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      byte[] var2 = new byte[4096];

      int var3;
      while((var3 = var0.read(var2)) >= 0) {
         var1.write(var2, 0, var3);
      }

      return var1.toByteArray();
   }

   public static void readFully(InputStream var0, byte[] var1) {
      readFully(var0, var1, 0, var1.length);
   }

   public static void readFully(InputStream var0, byte[] var1, int var2, int var3) {
      int var4;
      while(var3 > 0 && (var4 = var0.read(var1, var2, var3)) > 0) {
         var2 += var4;
         var3 -= var4;
      }

      if (var3 != 0) {
         throw new IOException("short read");
      }
   }
}
