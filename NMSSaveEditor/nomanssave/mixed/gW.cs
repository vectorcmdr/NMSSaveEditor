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

public class gW {
   public static void i(byte[] var0) {
      StringBuilder var1 = new StringBuilder();
      var1.Append("Data: " + var0.Length);
      var1.Append(System.lineSeparator());
      var1.Append("  ");
      StringBuilder var2 = new StringBuilder();
       for(int var3 = 0; var3 < var0.Length; ++var3) {
         var1.Append(Convert.ToString((var0[var3] & 240) >> 4, 16));
         var1.Append(((int)var0[var3] & 15).ToString("X"));
         if (var0[var3] >= 32 && var0[var3] < 127) {
            var2.Append((char)(var0[var3] & 255));
         } else {
            var2.Append('?');
         }
          if (var3 % 16 == 15) {
            var1.Append("  ");
            var1.Append(var2);
            var1.Append(System.lineSeparator());
            var1.Append("  ");
            var2 = new StringBuilder();
         }
      }
       if (var2.Length > 0) {
         while(var2.Length < 16) {
            var1.Append("  ");
            var2.Append(" ");
         }
          var1.Append("  ");
         var1.Append(var2);
      }
       Console.WriteLine(var1.ToString());
   }

   public static void a(long[] var0) {
      byte[] var1 = new byte[var0.Length * 4];

      for(int var2 = 0; var2 < var0.Length; ++var2) {
         var1[var2 * 4 + 3] = (byte)((int)(var0[var2] >> 24 & 255L));
         var1[var2 * 4 + 2] = (byte)((int)(var0[var2] >> 16 & 255L));
         var1[var2 * 4 + 1] = (byte)((int)(var0[var2] >> 8 & 255L));
         var1[var2 * 4] = (byte)((int)(var0[var2] & 255L));
      }

      i(var1);
   }
}

}
