using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class fC : fs {
   int mb;
   File mc;
   string md;
   final byte[] lK;
   fn be;
   fA ma;

   fC(fA var1, string var2, int var3) {
      this.ma = var1;
      this.mb = var3;
      this.mc = new File(aH.cG, var2);
      ZipFile var4 = new ZipFile(this.mc);

      try {
         ZipEntry var5 = var4.getEntry("saveinfo.txt");
         if (var5 == null) {
            throw new IOException("Invalid backup file");
         }

         Dictionary<string, string> var6 = new Dictionary<string, string>();
         var6.load(var4.getInputStream(var5));
         this.md = var6.getProperty("StorageFile");
         if (this.md == null) {
            throw new IOException("Invalid backup file");
         }

         string var7 = var6.getProperty("GameMode");
         this.be = var7 == null ? null : fn.valueOf(var7);
         var5 = var4.getEntry(this.md);
         Stream var8 = var4.getInputStream(var5);

         try {
            this.lK = new byte[112];
            hk.readFully(var8, this.lK);

            for(int var9 = 0; var9 < fA.bY().Length; ++var9) {
               if (this.lK[var9] != fA.bY()[var9]) {
                  throw new IOException("Invalid header");
               }
            }
         } finally {
            var8.close();
         }
      } catch (FormatException var19) {
         throw new IOException("Invalid backup file");
      } finally {
         var4.close();
      }

   }

   public fn L() {
      return this.be;
   }

   public string K() {
      return this.mc.getName();
   }

   public long lastModified() {
      return this.mc.lastModified();
   }

   public eY M() {
      MemoryStream var1 = new MemoryStream();
      Throwable var2 = null;
      Object var3 = null;

      try {
         ZipFile var4 = new ZipFile(this.mc);

         try {
            ZipEntry var5 = var4.getEntry(this.md);
            if (var5 == null) {
               throw new IOException("Invalid backup file");
            }

            Stream var6 = var4.getInputStream(var5);

            try {
               var6.skip(112L);
               byte[] var7 = new byte[4096];

               int var8;
               while((var8 = var6.read(var7)) >= 0) {
                  var1.write(var7, 0, var8);
               }
            } finally {
               var6.close();
            }
         } finally {
            if (var4 != null) {
               var4.close();
            }

         }
      } catch (Throwable var21) {
         if (var2 == null) {
            var2 = var21;
         } else if (var2 != var21) {
            var2.addSuppressed(var21);
         }

         throw var2;
      }

      return fA.b(var1.toByteArray(), eG.jV);
   }

   public string b(eY var1) {
      hc.info("Writing new save file...");
      string var2;
      if (fA.b(this.ma)[this.mb] != null) {
         var2 = fA.b(this.ma)[this.mb].b(var1);
      } else {
         fA.b(this.ma)[this.mb] = new fD(this.ma, this.mb, this.lK, var1);
         var2 = fA.b(this.ma)[this.mb].K();
      }

      hc.info("Finished.");
      return var2;
   }

   public string toString() {
      return this.mc.getName();
   }
}

}
