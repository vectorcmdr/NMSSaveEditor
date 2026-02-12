using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class hk {
   public static string sM = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

   public static string k(byte[] var0) {
      StringBuilder var1 = new StringBuilder();

      int var2;
      int var3;
      for(var2 = 0; var2 + 3 <= var0.Length; var2 += 3) {
         var3 = (255 & var0[var2]) << 16 | (255 & var0[var2 + 1]) << 8 | 255 & var0[var2 + 2];
         var1.Append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"[(16515072 & var3) >> 18]);
         var1.Append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"[(258048 & var3) >> 12]);
         var1.Append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"[(4032 & var3) >> 6]);
         var1.Append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"[63 & var3]);
      }

      if (var2 + 2 == var0.Length) {
         var3 = (255 & var0[var2]) << 16 | (255 & var0[var2 + 1]) << 8;
         var1.Append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"[(16515072 & var3) >> 18]);
         var1.Append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"[(258048 & var3) >> 12]);
         var1.Append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"[(4032 & var3) >> 6]);
      }

      if (var2 + 1 == var0.Length) {
         var3 = (255 & var0[var2]) << 16;
         var1.Append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"[(16515072 & var3) >> 18]);
         var1.Append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"[(258048 & var3) >> 12]);
      }

      return var1.ToString();
   }

   public static byte[] aD(string var0) {
      MemoryStream var1 = new MemoryStream();

      int var2;
      int var3;
      int var4;
      int var5;
      for(var2 = 0; var2 + 4 <= var0.Length; var2 += 4) {
         var3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".IndexOf(var0[var2]);
         var4 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".IndexOf(var0[var2 + 1]);
         var5 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".IndexOf(var0[var2 + 2]);
         int var6 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".IndexOf(var0[var2 + 3]);
         if (var3 < 0 || var4 < 0 || var5 < 0 || var6 < 0) {
            throw new Exception("Invalid base64 character");
         }

         var1.Write(var3 << 2 | var4 >> 4);
         var1.Write((15 & var4) << 4 | var5 >> 2);
         var1.Write((3 & var5) << 6 | var6);
      }

      if (var2 + 3 == var0.Length) {
         var3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".IndexOf(var0[var2]);
         var4 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".IndexOf(var0[var2 + 1]);
         var5 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".IndexOf(var0[var2 + 2]);
         if (var3 < 0 || var4 < 0 || var5 < 0) {
            throw new Exception("Invalid base64 character");
         }

         var1.Write(var3 << 2 | var4 >> 4);
         var1.Write((15 & var4) << 4 | var5 >> 2);
      }

      if (var2 + 2 == var0.Length) {
         var3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".IndexOf(var0[var2]);
         var4 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".IndexOf(var0[var2 + 1]);
         if (var3 < 0 || var4 < 0) {
            throw new Exception("Invalid base64 character");
         }

         var1.Write(var3 << 2 | var4 >> 4);
      }

      if (var2 + 1 == var0.Length) {
         throw new Exception("Unfinished base64 data");
      } else {
         return var1.ToArray();
      }
   }

   public static int readInt(Stream var0) {
      byte[] var1 = new byte[4];
      readFully(var0, var1);
      return (255 & var1[3]) << 24 | (255 & var1[2]) << 16 | (255 & var1[1]) << 8 | 255 & var1[0];
   }

   public static void a(Stream var0, int var1) {
      var0.Write(255 & var1);
      var0.Write(255 & var1 >> 8);
      var0.Write(255 & var1 >> 16);
      var0.Write(255 & var1 >> 24);
   }

   public static long f(Stream var0) {
      byte[] var1 = new byte[8];
      readFully(var0, var1);
      return (255L & (long)var1[7]) << 56 | (255L & (long)var1[6]) << 48 | (255L & (long)var1[5]) << 40 | (255L & (long)var1[4]) << 32 | (255L & (long)var1[3]) << 24 | (255L & (long)var1[2]) << 16 | (255L & (long)var1[1]) << 8 | 255L & (long)var1[0];
   }

   public static void b(Stream var0, long var1) {
      var0.Write((int)(255L & var1));
      var0.Write((int)(255L & var1 >> 8));
      var0.Write((int)(255L & var1 >> 16));
      var0.Write((int)(255L & var1 >> 24));
      var0.Write((int)(255L & var1 >> 32));
      var0.Write((int)(255L & var1 >> 40));
      var0.Write((int)(255L & var1 >> 48));
      var0.Write((int)(255L & var1 >> 56));
   }

   public static byte[] l(FileInfo var0) {
      FileStream var1 = new FileStream((var0).ToString(), System.IO.FileMode.Open);

      byte[] var3;
      try {
         var3 = g(var1);
      } finally {
         var1.Close();
      }

      return var3;
   }

   public static byte[] g(Stream var0) {
      MemoryStream var1 = new MemoryStream();
      byte[] var2 = new byte[4096];

      int var3;
      while((var3 = var0.read(var2)) >= 0) {
         var1.Write(var2, 0, var3);
      }

      return var1.ToArray();
   }

   public static void readFully(Stream var0, byte[] var1) {
      readFully(var0, var1, 0, var1.Length);
   }

   public static void readFully(Stream var0, byte[] var1, int var2, int var3) {
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

}