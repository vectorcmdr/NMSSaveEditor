using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class gc {
   public static long nc = -11644473600000L;

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

   public static string h(byte[] var0) {
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

   public static void a(byte var0, StringBuilder var1) {
      int var2 = (240 & var0) >> 4;
      int var3 = 15 & var0;
      var1.Append("0123456789ABCDEF"[var2]);
      var1.Append("0123456789ABCDEF"[var3]);
   }

   public static void a(Stream var0, string var1) {
      if (var1.Length > 32) {
         throw new IOException("invalid container path");
      } else {
         while(var1.Length < 32) {
            var1 = "0" + var1;
         }

         var1 = var1.ToLower();
      // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: // PORT_TODO: byte[] var2 = new byte[]{(byte)int.Parse(var1.Substring(6, 8), 16), (byte)int.Parse(var1.Substring(4, 6), 16), (byte)int.Parse(var1.Substring(2, 4), 16), (byte)int.Parse(var1.Substring(0, 2), 16), (byte)int.Parse(var1.Substring(10, 12), 16), (byte)int.Parse(var1.Substring(8, 10), 16), (byte)int.Parse(var1.Substring(14, System.Globalization.NumberStyles.HexNumber), 16), (byte)int.Parse(var1.Substring(12, 14), 16), (byte)int.Parse(var1.Substring(16, 18), 16), (byte)int.Parse(var1.Substring(18, 20), 16), (byte)int.Parse(var1.Substring(20, 22), 16), (byte)int.Parse(var1.Substring(22, 24), 16), (byte)int.Parse(var1.Substring(24, 26), 16), (byte)int.Parse(var1.Substring(26, 28), 16), (byte)int.Parse(var1.Substring(28, 30), 16), (byte)int.Parse(var1.Substring(30, 32), 16)};
         // PORT_TODO: var0.Write(var2);
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
         // PORT_TODO: return new string(var2, "UTF-16LE");
         return default;
      }
   }

   public static void b(Stream var0, string var1) {
      hk.a(var0, var1.Length);
      var0.Write(var1.getBytes("UTF-16LE"));
   }

   public static string d(Stream var0) {
      byte[] var1 = new byte[128];
      hk.readFully(var0, var1);

      int var2;
      for(var2 = 0; var2 < var1.Length && (var1[var2] != 0 || var1[var2 + 1] != 0); var2 += 2) {
      }

      // PORT_TODO: return new string(var1, 0, var2, "UTF-16LE");
      return default;
   }

   public static string e(Stream var0) {
      byte[] var1 = new byte[128];
      hk.readFully(var0, var1);

      int var2;
      for(var2 = 0; var2 < var1.Length && var1[var2] != 0; ++var2) {
      }

      // PORT_TODO: return new string(var1, 0, var2, Encoding.UTF8);
      return default;
   }

   public static void c(Stream var0, string var1) {
      object var2 = null; // PORT_TODO: stub declaration
      // PORT_TODO: byte[] var2 = var1.GetBytes(System.Text.Encoding.UTF8);
      // PORT_TODO: if (var2.Length < 128) {
      // PORT_TODO: var2 = null; // PORT_TODO: stub declaration
      // PORT_TODO: object var3 = null; // PORT_TODO: stub declaration
         // PORT_TODO: byte[] var3 = new byte[128];
         // PORT_TODO: Array.Copy(var2, 0, var3, 0, var2.Length);
         // PORT_TODO: var3[var2.Length] = 0;
         // PORT_TODO: var2 = var3;
      // PORT_TODO: }

      // PORT_TODO: var0.Write(var2, 0, 128);
   }
}



}
