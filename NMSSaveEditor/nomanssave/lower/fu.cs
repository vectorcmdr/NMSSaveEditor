using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace NMSSaveEditor
{

public class fu : fq {
   public static readonly byte[] lA = "NOMANSKY".GetBytes();
   public static readonly byte[] lB = new byte[]{126, -25, 85, -54, -47, 7, 0, 0};
   public static Pattern lC = Pattern.compile("\\{\"Version\":(\\d*),.*");
   public FileInfo lD;
   public fR lE;
   public fw[] lF;
   public fv lG;
   public fx[] lH;

   public static fn ag(int var0) {
      int var1 = (3584 & var0) >> 9;
      if (var1 <= 0 && var1 > fn.Values.Length) {
         throw new Exception("Unsupported version: " + var0);
      } else {
         return fn.Values[var1 - 1];
      }
   }

   public fu(FileInfo var1, fR var2) {
      this.lD = var1.Exists ? var1 : new FileInfo(System.IO.Path.Combine((var1).ToString(), ("memory.dat").ToString()));
      this.lE = var2;
      Console.WriteLine(this.lD.FullName);
      FileStream var3 = new FileStream((this.lD).ToString(), System.IO.FileMode.Open);

      try {
         long var4 = 0L;
         Console.WriteLine("Reading header");
         byte[] var6 = new byte[8];
         hk.readFully(var3, var6);
         var4 += (long)var6.Length;
         if (!a(var6, lA)) {
            throw new IOException("Invalid header");
         }

         int[] var7 = new int[14];

         int var8;
         for(var8 = 0; var8 < var7.Length; ++var8) {
            var7[var8] = hk.readInt(var3);
            var4 += 4L;
            if (var7[var8] != 0) {
               Console.WriteLine("  ints[" + var8 + "] = " + var7[var8] + " 0x" + Convert.ToString(var7[var8]) + " " + Integer.toBinaryString(var7[var8]));
            }
         }

         Console.WriteLine("Reading files");
         this.lF = new fw[32];

         for(var8 = 0; var8 < this.lF.Length; ++var8) {
            this.lF[var8] = new fw(this, var3);
            if (this.lF[var8].isValid()) {
               Console.WriteLine("file[" + var8 + "]");
               this.lF[var8].bZ();
            }

            var4 += 48L;
         }

         for(var8 = 0; var8 < this.lF.Length; ++var8) {
            if (this.lF[var8].isValid()) {
               var3.skip(this.lF[var8].lP - var4);
               var4 = this.lF[var8].lP;
               byte[] var9 = new byte[20];
               int var10 = var3.read(var9);
               string var11 = new string(var9, 0, var10, "ISO-8859-1");
               Matcher var12 = lC.matcher(var11);
               if (var12.Matches()) {
                  try {
                     this.lF[var8].be = ag(int.Parse(var12.group(1)));
                  } catch (Exception var17) {
                  }
               }

               var4 += (long)var10;
            }
         }
      } finally {
         var3.Close();
      }

      this.lG = null;
      this.lH = new fx[30];

      for(int var19 = 0; var19 < this.lF.Length; ++var19) {
         if (this.lF[var19].isValid()) {
            if (this.lF[var19].lN == 262144 && this.lG == null) {
               this.lG = new fv(this, this.lF[var19]);
            }

            if (this.lF[var19].lN == 3145728 && this.lF[var19].lO >= 2) {
               this.lH[this.lF[var19].lO - 2] = new fx(this, this.lF[var19]);
            }
         }
      }

      fl.a(this, this.lD.Directory);
   }

   public FileInfo bS() {
      return this.lD;
   }

   public fr bT() {
      return this.lG;
   }

   public ft[] bU() {
      ft[] var1 = new ft[15];

      for(int var2 = 0; var2 < 15; ++var2) {
         var1[var2] = new fy(this, var2);
      }

      return var1;
   }

   public int W(string var1) {
      return -1;
   }

   public void X(string var1) {
      var1.Equals(this.lD.Name);
   }

   public static void main(string[] var0) {
      new fu(new FileInfo("D:\\Temp\\PS4_NEW"), (fR)null);
   }

   public static bool a(byte[] var0, byte[] var1) {
      for(int var2 = 0; var2 < var0.Length; ++var2) {
         if (var0[var2] != var1[var2]) {
            return false;
         }
      }

      return true;
   }
   public static fx[] a(fu var0) {
      return var0.lH;
   }
   public static byte[] bY() {
      return lB;
   }
   public static bool b(byte[] var0, byte[] var1) {
      return a(var0, var1);
   }
   public static FileInfo b(fu var0) {
      return var0.lD;
   }
   public static fw[] c(fu var0) {
      return var0.lF;
   }
}

}
