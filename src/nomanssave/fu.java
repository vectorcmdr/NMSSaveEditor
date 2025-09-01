package nomanssave;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fu implements fq {
   private static final byte[] lA = "NOMANSKY".getBytes();
   private static final byte[] lB = new byte[]{126, -25, 85, -54, -47, 7, 0, 0};
   private static final Pattern lC = Pattern.compile("\\{\"Version\":(\\d*),.*");
   private final File lD;
   private fR lE;
   private fw[] lF;
   private fv lG;
   private fx[] lH;

   public static fn ag(int var0) {
      int var1 = (3584 & var0) >> 9;
      if (var1 <= 0 && var1 > fn.values().length) {
         throw new RuntimeException("Unsupported version: " + var0);
      } else {
         return fn.values()[var1 - 1];
      }
   }

   fu(File var1, fR var2) {
      this.lD = var1.isFile() ? var1 : new File(var1, "memory.dat");
      this.lE = var2;
      System.out.println(this.lD.getAbsolutePath());
      FileInputStream var3 = new FileInputStream(this.lD);

      try {
         long var4 = 0L;
         System.out.println("Reading header");
         byte[] var6 = new byte[8];
         hk.readFully(var3, var6);
         var4 += (long)var6.length;
         if (!a(var6, lA)) {
            throw new IOException("Invalid header");
         }

         int[] var7 = new int[14];

         int var8;
         for(var8 = 0; var8 < var7.length; ++var8) {
            var7[var8] = hk.readInt(var3);
            var4 += 4L;
            if (var7[var8] != 0) {
               System.out.println("  ints[" + var8 + "] = " + var7[var8] + " 0x" + Integer.toHexString(var7[var8]) + " " + Integer.toBinaryString(var7[var8]));
            }
         }

         System.out.println("Reading files");
         this.lF = new fw[32];

         for(var8 = 0; var8 < this.lF.length; ++var8) {
            this.lF[var8] = new fw(this, var3);
            if (this.lF[var8].isValid()) {
               System.out.println("file[" + var8 + "]");
               this.lF[var8].bZ();
            }

            var4 += 48L;
         }

         for(var8 = 0; var8 < this.lF.length; ++var8) {
            if (this.lF[var8].isValid()) {
               var3.skip(this.lF[var8].lP - var4);
               var4 = this.lF[var8].lP;
               byte[] var9 = new byte[20];
               int var10 = var3.read(var9);
               String var11 = new String(var9, 0, var10, "ISO-8859-1");
               Matcher var12 = lC.matcher(var11);
               if (var12.matches()) {
                  try {
                     this.lF[var8].be = ag(Integer.parseInt(var12.group(1)));
                  } catch (RuntimeException var17) {
                  }
               }

               var4 += (long)var10;
            }
         }
      } finally {
         var3.close();
      }

      this.lG = null;
      this.lH = new fx[30];

      for(int var19 = 0; var19 < this.lF.length; ++var19) {
         if (this.lF[var19].isValid()) {
            if (this.lF[var19].lN == 262144 && this.lG == null) {
               this.lG = new fv(this, this.lF[var19]);
            }

            if (this.lF[var19].lN == 3145728 && this.lF[var19].lO >= 2) {
               this.lH[this.lF[var19].lO - 2] = new fx(this, this.lF[var19]);
            }
         }
      }

      fl.a(this, this.lD.getParentFile());
   }

   public File bS() {
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

   public int W(String var1) {
      return -1;
   }

   public void X(String var1) {
      var1.equals(this.lD.getName());
   }

   public static void main(String[] var0) {
      new fu(new File("D:\\Temp\\PS4_NEW"), (fR)null);
   }

   private static boolean a(byte[] var0, byte[] var1) {
      for(int var2 = 0; var2 < var0.length; ++var2) {
         if (var0[var2] != var1[var2]) {
            return false;
         }
      }

      return true;
   }

   // $FF: synthetic method
   static fx[] a(fu var0) {
      return var0.lH;
   }

   // $FF: synthetic method
   static byte[] bY() {
      return lB;
   }

   // $FF: synthetic method
   static boolean b(byte[] var0, byte[] var1) {
      return a(var0, var1);
   }

   // $FF: synthetic method
   static File b(fu var0) {
      return var0.lD;
   }

   // $FF: synthetic method
   static fw[] c(fu var0) {
      return var0.lF;
   }
}
