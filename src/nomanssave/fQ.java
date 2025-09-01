package nomanssave;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class fQ {
   final String filename;
   final int lO;
   fI mx;
   // $FF: synthetic field
   final fJ mt;

   fQ(fJ var1, String var2, int var3, boolean var4) {
      this.mt = var1;
      this.filename = var2;
      this.lO = var3;
      if (var4) {
         FileInputStream var5 = new FileInputStream(new File(fJ.a(var1), "mf_" + var2));

         int var7;
         try {
            hc.info("Reading metadata for " + var2);
            byte[] var6 = new byte[1024];
            var7 = var5.read(var6);
            this.mx = fI.a(var3, var6, 0, var7);
         } finally {
            var5.close();
         }

         int var11 = this.mx.ch();
         if (var11 != 0) {
            hc.debug("  DecompressedSize: " + var11);
         }

         var7 = this.mx.ci();
         if (var7 != 0) {
            hc.debug("  CompressedSize: " + var7);
         }

         int var8 = this.mx.cj();
         if (var8 != 0) {
            hc.info("  TotalPlayTime: " + fq.c((long)var8));
         }
      } else {
         hc.info("Creating new metadata for " + var2);
         this.mx = fI.am(var3);
      }

   }

   public String K() {
      return this.filename;
   }

   public long lastModified() {
      return (new File(fJ.a(this.mt), "mf_" + this.filename)).lastModified();
   }

   eY a(eG param1) {
      // $FF: Couldn't be decompiled
   }

   byte[] ah(int var1) {
      Object var2 = new FileInputStream(new File(fJ.a(this.mt), this.filename));

      try {
         ByteArrayOutputStream var3 = new ByteArrayOutputStream();
         byte[] var4 = new byte[1024];
         hk.readFully((InputStream)var2, var4, 0, 16);
         if ((255 & var4[0]) == 229 && (255 & var4[1]) == 161 && (255 & var4[2]) == 237 && (255 & var4[3]) == 254) {
            var2 = new gX((InputStream)var2, var4);
         } else {
            var3.write(var4, 0, 16);
         }

         while(true) {
            int var5;
            if ((var5 = ((InputStream)var2).read(var4)) >= 0) {
               var3.write(var4, 0, var5);
               if (var3.size() < var1) {
                  continue;
               }
            }

            byte[] var7 = var3.toByteArray();
            return var7;
         }
      } finally {
         ((InputStream)var2).close();
      }
   }

   void a(String var1, fn var2, String var3, String var4) {
      File var5 = new File(fJ.a(this.mt), "mf_" + this.filename);
      File var6 = new File(fJ.a(this.mt), this.filename);
      Properties var7 = new Properties();
      var7.setProperty("ArchiveNumber", Integer.toString(this.lO));
      var7.setProperty("ManifestFile", "mf_" + this.filename);
      var7.setProperty("StorageFile", this.filename);
      var7.setProperty("LastModified", Long.toString(var5.lastModified()));
      if (var2 != null) {
         var7.setProperty("GameMode", var2.name());
      }

      if (var3 != null) {
         var7.setProperty("SaveName", var3);
      }

      if (var4 != null) {
         var7.setProperty("Description", var4);
      }

      String var8 = var1 + "." + System.currentTimeMillis() + ".zip";
      File var9 = new File(aH.cG, var8);
      ZipOutputStream var10 = new ZipOutputStream(new FileOutputStream(var9));

      try {
         byte[] var12 = new byte[1024];
         ZipEntry var13 = new ZipEntry(var5.getName());
         var10.putNextEntry(var13);
         FileInputStream var14 = new FileInputStream(var5);

         int var11;
         try {
            while((var11 = var14.read(var12)) >= 0) {
               var10.write(var12, 0, var11);
            }
         } finally {
            var14.close();
         }

         var13 = new ZipEntry(var6.getName());
         var10.putNextEntry(var13);
         FileInputStream var15 = new FileInputStream(var6);

         try {
            while((var11 = var15.read(var12)) >= 0) {
               var10.write(var12, 0, var11);
            }
         } finally {
            var15.close();
         }

         var13 = new ZipEntry("saveinfo.txt");
         var10.putNextEntry(var13);
         var7.store(var10, "");
      } finally {
         var10.close();
      }

      var9.setLastModified(var5.lastModified());
   }

   void a(eY var1, boolean var2) {
      ByteArrayOutputStream var3 = new ByteArrayOutputStream();
      Throwable var4 = null;
      Object var5 = null;

      try {
         fj var6 = new fj(var3, 2);

         try {
            var6.h(var1);
         } finally {
            if (var6 != null) {
               var6.close();
            }

         }
      } catch (Throwable var33) {
         if (var4 == null) {
            var4 = var33;
         } else if (var4 != var33) {
            var4.addSuppressed(var33);
         }

         throw var4;
      }

      byte[] var35 = var3.toByteArray();
      int var36 = 0;
      Object var37 = new FileOutputStream(new File(fJ.a(this.mt), this.filename));

      try {
         if (var2) {
            var37 = new gZ((OutputStream)var37);
         }

         ((OutputStream)var37).write(var35);
         if (var2) {
            var36 = ((gZ)var37).ci();
         }
      } finally {
         ((OutputStream)var37).close();
      }

      if (!this.mx.ce()) {
         hc.warn("Metadata version could not be upgraded");
      }

      byte[] var7 = new byte[32];
      byte[] var8 = new byte[16];
      if (!var2) {
         try {
            MessageDigest var9 = MessageDigest.getInstance("SHA-256");
            var7 = var9.digest(var35);
            var8 = fJ.d(var7, var35);
         } catch (NoSuchAlgorithmException var31) {
            hc.a("Error generating SHA-256 hash", var31);
         }
      }

      this.mx.e(var7);
      this.mx.f(var8);
      this.mx.ak(var36);
      this.mx.aj(var35.length);
      FileOutputStream var38 = new FileOutputStream(new File(fJ.a(this.mt), "mf_" + this.filename));

      try {
         var38.write(this.mx.encode());
      } finally {
         var38.close();
      }

   }
}
