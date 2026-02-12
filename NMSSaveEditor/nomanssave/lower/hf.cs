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

public class hf {
   public static int b(string var0, int var1, int var2) {
      var0 = var0.Trim();
      if (var0.Length == 0) {
         throw new Exception("No digits found");
      } else {
         long var3 = 0L;

         for(int var6 = 0; var6 < var0.Length; ++var6) {
            var3 *= 10L;
            char var5 = var0[var6];
            if (var5 < '0' || var5 > '9') {
               throw new Exception("Invalid digit: " + var5);
            }

            var3 += (long)(var5 - 48);
            if (var3 > (long)var2) {
               return var2;
            }
         }

         if (var3 < (long)var1) {
            return var1;
         } else {
            return (int)var3;
         }
      }
   }

   public static long a(string var0, long var1, long var3) {
      var0 = var0.Trim();
      if (var0.Length == 0) {
         throw new Exception("No digits found");
      } else {
         long var5 = 0L;

         for(int var8 = 0; var8 < var0.Length; ++var8) {
            var5 *= 10L;
            char var7 = var0[var8];
            if (var7 < '0' || var7 > '9') {
               throw new Exception("Invalid digit: " + var7);
            }

            var5 += (long)(var7 - 48);
            if (var5 > var3) {
               return var3;
            }
         }

         if (var5 < var1) {
            return var1;
         } else {
            return var5;
         }
      }
   }

   public static double a(string var0, double var1, double var3) {
      var0 = var0.Trim();
      double var5 = double.Parse(var0);
      if (var5 < var1) {
         return var1;
      } else {
         return var5 > var3 ? var3 : var5;
      }
   }
}

}
