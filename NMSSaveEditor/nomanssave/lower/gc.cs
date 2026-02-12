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

public class gc {
   private static long nc = -11644473600000L;

   public static string a(Stream var0) {
      byte[] var1 = new byte[16];
      hk.readFully(var0, var1);
      return h(var1);
   }

   public static string cA() {
      byte[] var0 = new byte[16];
      (new SecureRandom()).nextBytes(var0);
      return h(var0);
   }

   private static string h(byte[] var0) {
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
      return var1.ToString();
   }

   private static void a(byte var0, StringBuilder var1) {
      int var2 = (240 & var0) >> 4;
      int var3 = 15 & var0;
      var1.append("0123456789ABCDEF"[var2]);
      var1.append("0123456789ABCDEF"[var3]);
   }

   public static void a(Stream var0, string var1) {
      if (var1.Length > 32) {
         throw new IOException("invalid container path");
      } else {
         while(var1.Length < 32) {
            var1 = "0" + var1;
         }

         var1 = var1.ToLower();
         byte[] var2 = new byte[]{(byte)int.Parse(var1.Substring(6, NumberStyles.HexNumber), 16), (byte)int.Parse(var1.Substring(4, NumberStyles.HexNumber), 16), (byte)int.Parse(var1.Substring(2, NumberStyles.HexNumber), 16), (byte)int.Parse(var1.Substring(0, NumberStyles.HexNumber), 16), (byte)int.Parse(var1.Substring(10, NumberStyles.HexNumber), 16), (byte)int.Parse(var1.Substring(8, NumberStyles.HexNumber), 16), (byte)int.Parse(var1.Substring(14, NumberStyles.HexNumber), 16), (byte)int.Parse(var1.Substring(12, NumberStyles.HexNumber), 16), (byte)int.Parse(var1.Substring(16, NumberStyles.HexNumber), 16), (byte)int.Parse(var1.Substring(18, NumberStyles.HexNumber), 16), (byte)int.Parse(var1.Substring(20, NumberStyles.HexNumber), 16), (byte)int.Parse(var1.Substring(22, NumberStyles.HexNumber), 16), (byte)int.Parse(var1.Substring(24, NumberStyles.HexNumber), 16), (byte)int.Parse(var1.Substring(26, NumberStyles.HexNumber), 16), (byte)int.Parse(var1.Substring(28, NumberStyles.HexNumber), 16), (byte)int.Parse(var1.Substring(30, NumberStyles.HexNumber), 16)};
         var0.Write(var2);
      }
   }

   public static long b(Stream var0) {
      return hk.f(var0) / 10000L + -11644473600000L;
   }

   public static void a(Stream var0, long var1) {
      hk.b(var0, (var1 - -11644473600000L) * 10000L);
   }

   public static string c(Stream var0) {
      int var1 = hk.readInt(var0);
      if (var1 < 0) {
         throw new IOException("negative length");
      } else {
         byte[] var2 = new byte[var1 * 2];
         hk.readFully(var0, var2);
         return new string(var2, "UTF-16LE");
      }
   }

   public static void b(Stream var0, string var1) {
      hk.a(var0, var1.Length);
      var0.Write(var1.GetBytes("UTF-16LE"));
   }

   public static string d(Stream var0) {
      byte[] var1 = new byte[128];
      hk.readFully(var0, var1);

      int var2;
      for(var2 = 0; var2 < var1.length && (var1[var2] != 0 || var1[var2 + 1] != 0); var2 += 2) {
      }

      return new string(var1, 0, var2, "UTF-16LE");
   }

   public static string e(Stream var0) {
      byte[] var1 = new byte[128];
      hk.readFully(var0, var1);

      int var2;
      for(var2 = 0; var2 < var1.length && var1[var2] != 0; ++var2) {
      }

      return new string(var1, 0, var2, Encoding.UTF8);
   }

   public static void c(Stream var0, string var1) {
      byte[] var2 = var1.GetBytes(Encoding.UTF8);
      if (var2.length < 128) {
         byte[] var3 = new byte[128];
         Array.Copy(var2, 0, var3, 0, var2.length);
         var3[var2.length] = 0;
         var2 = var3;
      }

      var0.Write(var2, 0, 128);
   }
}

}
