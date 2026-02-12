using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class gT {
   public static int rT = 12;
   public static double rU = 0.1D;
   public readonly double[] rV;
   public readonly double[] rW;
   public readonly double[] rX;

   public static double[] b(double[] var0) {
      double var1 = Math.Sqrt(var0[0] * var0[0] + var0[1] * var0[1] + var0[2] * var0[2]);
      if (var1 < 0.1D) {
         throw new Exception("vector cannot be normalized");
      } else {
         return new double[]{var0[0] / var1, var0[1] / var1, var0[2] / var1};
      }
   }

   public gT() {
      this.rX = new double[]{0.0D, 0.0D, 1.0D};
      this.rW = new double[]{0.0D, 1.0D, 0.0D};
      this.rV = new double[]{1.0D, 0.0D, 0.0D};
   }

   public gT(double[] var1, double[] var2) {
      double var3 = Math.Sqrt(var1[0] * var1[0] + var1[1] * var1[1] + var1[2] * var1[2]);
      if (var3 < 0.1D) {
         if (var2[0] != 0.0D || var2[1] != 0.0D || var2[2] != 1.0D) {
            throw new Exception("Unable to calculate base structures");
         }

         this.rX = new double[]{0.0D, 0.0D, 1.0D};
         this.rW = new double[]{0.0D, 1.0D, 0.0D};
         this.rV = new double[]{1.0D, 0.0D, 0.0D};
      } else {
         var1 = new double[]{var1[0] / var3, var1[1] / var3, var1[2] / var3};
         var2 = b(var2);
         double var5 = var2[0] * var1[0] + var2[1] * var1[1] + var2[2] * var1[2];
         this.rX = var2;
         this.rW = b(new double[]{var1[0] - var5 * var2[0], var1[1] - var5 * var2[1], var1[2] - var5 * var2[2]});
         this.rV = b(new double[]{this.rW[1] * var2[2] - this.rW[2] * var2[1], this.rW[2] * var2[0] - this.rW[0] * var2[2], this.rW[0] * var2[1] - this.rW[1] * var2[0]});
      }

   }

   public static double[] a(double var0, double[] var2, double[] var3) {
      double var4 = Math.Cos(var0);
      double var6 = -Math.Sin(var0);
      double var8 = var3[0];
      double var10 = var3[1];
      double var12 = var3[2];
      double[][] var14 = new double[3][];
      var14[0][0] = var8 * var8 * (1.0D - var4) + var4;
      var14[0][1] = var8 * var10 * (1.0D - var4) + var12 * var6;
      var14[0][2] = var8 * var12 * (1.0D - var4) - var10 * var6;
      var14[1][0] = var8 * var10 * (1.0D - var4) - var12 * var6;
      var14[1][1] = var10 * var10 * (1.0D - var4) + var4;
      var14[1][2] = var10 * var12 * (1.0D - var4) + var8 * var6;
      var14[2][0] = var8 * var12 * (1.0D - var4) + var10 * var6;
      var14[2][1] = var10 * var12 * (1.0D - var4) - var8 * var6;
      var14[2][2] = var12 * var12 * (1.0D - var4) + var4;
      double var15 = var2[0] * var14[0][0] + var2[1] * var14[1][0] + var2[2] * var14[2][0];
      double var17 = var2[0] * var14[0][1] + var2[1] * var14[1][1] + var2[2] * var14[2][1];
      double var19 = var2[0] * var14[0][2] + var2[1] * var14[1][2] + var2[2] * var14[2][2];
      double var21 = Math.Sqrt(var15 * var15 + var17 * var17 + var19 * var19);
      return new double[]{var15 / var21, var17 / var21, var19 / var21};
   }

   public double[] a(gU var1) {
      if (var1.rY.Equals("fr")) {
         return a(var1.rZ, this.rX, this.rV);
      } else if (var1.rY.Equals("fu")) {
         return a(var1.rZ, this.rX, this.rW);
      } else if (var1.rY.Equals("ur")) {
         return a(var1.rZ, this.rW, this.rV);
      } else if (var1.rY.Equals("uf")) {
         return a(var1.rZ, this.rW, this.rX);
      } else if (var1.rY.Equals("ru")) {
         return a(var1.rZ, this.rV, this.rW);
      } else if (var1.rY.Equals("rf")) {
         return a(var1.rZ, this.rV, this.rX);
      } else {
         throw new Exception("Unsupported rotation axis");
      }
   }

   public double[] c(double[] var1) {
      double var2 = var1[0] * this.rV[0] + var1[1] * this.rW[0] + var1[2] * this.rX[0];
      double var4 = var1[0] * this.rV[1] + var1[1] * this.rW[1] + var1[2] * this.rX[1];
      double var6 = var1[0] * this.rV[2] + var1[1] * this.rW[2] + var1[2] * this.rX[2];
      return new double[]{var2, var4, var6};
   }

   public double[] d(double[] var1) {
      double var2 = var1[0] * this.rV[0] + var1[1] * this.rV[1] + var1[2] * this.rV[2];
      double var4 = var1[0] * this.rW[0] + var1[1] * this.rW[1] + var1[2] * this.rW[2];
      double var6 = var1[0] * this.rX[0] + var1[1] * this.rX[1] + var1[2] * this.rX[2];
      return new double[]{var2, var4, var6};
   }

   public void a(double[] var1, StringBuilder var2, StringBuilder var3, StringBuilder var4, int var5) {
      int var6 = var2.Length;
      var2.Append(a(var1[0], var5));
      var3.Append(a(var1[1], var5));
      var4.Append(a(var1[2], var5));
      int var7 = Math.Max(Math.Max(var2.Length, var3.Length), var4.Length);

      while(var2.Length < var7) {
         var2.Insert(var6, ' ');
      }

      while(var3.Length < var7) {
         var3.Insert(var6, ' ');
      }

      while(var4.Length < var7) {
         var4.Insert(var6, ' ');
      }

   }

   public void a(StringBuilder var1, StringBuilder var2, StringBuilder var3, int var4) {
      int var5 = Math.Max(Math.Max(var1.Length, var2.Length), var3.Length);

      while(var1.Length < var5) {
         var1.Append(' ');
      }

      while(var2.Length < var5) {
         var2.Append(' ');
      }

      while(var3.Length < var5) {
         var3.Append(' ');
      }

      var1.Append("| ");
      var2.Append("| ");
      var3.Append("| ");
      this.a(this.rV, var1, var2, var3, var4);
      var1.Append(' ');
      var2.Append(' ');
      var3.Append(' ');
      this.a(this.rW, var1, var2, var3, var4);
      var1.Append(' ');
      var2.Append(' ');
      var3.Append(' ');
      this.a(this.rX, var1, var2, var3, var4);
      var1.Append(" |");
      var2.Append(" |");
      var3.Append(" |");
   }

   public string toString() {
      return this.ToString();
   }

   public string toString(int var1) {
      StringBuilder var2 = new StringBuilder();
      StringBuilder var3 = new StringBuilder();
      StringBuilder var4 = new StringBuilder();
      this.a(var2, var3, var4, var1);
      StringBuilder var5 = new StringBuilder();
      var5.Append(var2).Append("\n");
      var5.Append(var3).Append("\n");
      var5.Append(var4).Append("\n");
      return var5.ToString();
   }

   public static string e(double[] var0) {
      return a(var0, 12);
   }

   public static string a(double[] var0, int var1) {
      return "[ " + b(var0[0], var1) + " , " + b(var0[1], var1) + " , " + b(var0[2], var1) + " ]";
   }

   public static string f(double[] var0) {
      return b(var0, 12);
   }

   public static string b(double[] var0, int var1) {
      return "[ " + b(var0[0], var1) + " , " + b(var0[1], var1) + " , " + b(var0[2], var1) + " , " + b(var0[3], var1) + " ]";
   }

   public static string a(double var0, int var2) {
      if (Double.isInfinite(var0)) {
         return "Infinite";
      } else if (Double.isNaN(var0)) {
         return "NaN";
      } else {
         BigDecimal var3 = new BigDecimal(var0);
         var3 = var3.setScale(var2, /* RoundingMode */ 0);
         return var3.toPlainString();
      }
   }

   public static string b(double var0, int var2) {
      if (Double.isInfinite(var0)) {
         return "Infinite";
      } else if (Double.isNaN(var0)) {
         return "NaN";
      } else {
         BigDecimal var3 = new BigDecimal(var0);
         var3 = var3.setScale(var2, /* RoundingMode */ 0);
         string var4 = var3.toPlainString();
         if (var2 <= 0) {
            return var4;
         } else {
            while(var4.EndsWith("0") && !var4.EndsWith(".0")) {
               var4 = var4.Substring(0, var4.Length - 1);
            }

            return var4;
         }
      }
   }
}



}
