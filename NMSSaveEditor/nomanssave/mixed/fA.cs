using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace NMSSaveEditor
{

public class fA : fq {
   public static readonly byte[] lA = "NOMANSKY".GetBytes();
   public static Pattern lV = Pattern.compile("savedata(\\d{2})\\.hg", 2);
   public static Pattern lW = Pattern.compile("ps4_backup(\\d*)\\.\\d*\\.zip", 2);
   public FileInfo lX;
   public fR lE;
   public fB lY;
   public fD[] lZ;

   public fA(FileInfo var1, fR var2) {
      this.lX = var1;
      this.lE = var2;

      try {
         this.lY = new fB(this);
      } catch (FileNotFoundException var7) {
      } catch (IOException var8) {
         hc.a("cannot read file metadata: savedata00.hg", var8);
      }

      this.lZ = new fD[30];

      for(int var3 = 0; var3 < this.lZ.Length; ++var3) {
         try {
            this.lZ[var3] = new fD(this, var3);
         } catch (FileNotFoundException var9) {
         } catch (IOException var10) {
            int var5 = var3 + 2;
            string var6 = "savedata" + (var5 < 10 ? "0" : "") + Integer.toString(var5) + ".hg";
            hc.a("cannot read file metadata: " + var6, var10);
         }
      }

      fl.a(this, var1);
   }

   public static eY a(byte[] var0, eG var1) {
      Exception var2 = null;
      object var3 = null;

      try {
         ff var4 = new ff(new MemoryStream(var0), 2);

         Exception var10000;
         label173: {
            eY var17;
            bool var10001;
            try {
               var17 = var4.a(var1);
            } catch (Exception var15) {
               var10000 = var15;
               var10001 = false;
               goto label173;
            }

            if (var4 != null) {
               var4.Close();
            }

            label162:
            try {
               return var17;
            } catch (Exception var14) {
               var10000 = var14;
               var10001 = false;
               goto label162;
            }
         }

         var2 = var10000;
         if (var4 != null) {
            var4.Close();
         }

         throw var2;
      } catch (Exception var16) {
         if (var2 == null) {
            var2 = var16;
         } else if (var2 != var16) {
            var2.addSuppressed(var16);
         }

         throw var2;
      }
   }

   public static byte[] g(eY var0) {
      MemoryStream var1 = new MemoryStream();
      Exception var2 = null;
      object var3 = null;

      try {
         fj var4 = new fj(var1, 2);

         try {
            var4.h(var0);
         } finally {
            if (var4 != null) {
               var4.Close();
            }

         }
      } catch (Exception var10) {
         if (var2 == null) {
            var2 = var10;
         } else if (var2 != var10) {
            var2.addSuppressed(var10);
         }

         throw var2;
      }

      return var1.toByteArray();
   }

   public FileInfo bS() {
      return this.lX;
   }

   public fr bT() {
      return this.lY;
   }

   public ft[] bU() {
      ft[] var1 = new ft[15];

      for(int var2 = 0; var2 < 15; ++var2) {
         var1[var2] = new fE(this, var2);
      }

      return var1;
   }

   public int W(string var1) {
      Matcher var2 = lV.matcher(var1);
      if (!var2.Matches()) {
         return -1;
      } else {
         int var3 = int.Parse(var2.group(1)) - 2;
         return var3 >= 0 ? var3 / 2 : -1;
      }
   }

   public void X(string var1) {
      Matcher var2 = lV.matcher(var1);
      if (var2.Matches()) {
         int var3 = int.Parse(var2.group(1)) - 2;
         if (var3 == -2) {
            try {
               this.lY = new fB(this);
               hc.info("Account data reloaded from storage.");
            } catch (FileNotFoundException var7) {
               this.lY = null;
               hc.info("Account data deleted from storage.");
            } catch (IOException var8) {
               this.lY = null;
               hc.a("cannot read file metadata: " + var1, var8);
            }

            this.lE.a(this);
         } else if (var3 >= 0) {
            try {
               this.lZ[var3] = new fD(this, var3);
               hc.info("Save file reloaded from storage: " + var1);
            } catch (FileNotFoundException var5) {
               this.lZ[var3] = null;
               hc.info("Save file deleted from storage: " + var1);
            } catch (IOException var6) {
               this.lZ[var3] = null;
               hc.a("cannot read file metadata: " + var1, var6);
            }

            this.lE.a(this, var3 / 2, var1);
         }
      }

   }
   public static FileInfo a(fA var0) {
      return var0.lX;
   }
   public static byte[] bY() {
      return lA;
   }
   public static eY b(byte[] var0, eG var1) {
      return a(var0, var1);
   }
   public static byte[] l(eY var0) {
      return g(var0);
   }
   public static fD[] b(fA var0) {
      return var0.lZ;
   }
   public static Pattern cb() {
      return lW;
   }
}

}
