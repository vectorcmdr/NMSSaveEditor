package nomanssave;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

class fL implements fs {
   final int mb;
   final File mc;
   final String mu;
   final String md;
   final fn be;
   final String mv;
   final String description;
   // $FF: synthetic field
   final fJ mt;

   fL(fJ var1, String var2, int var3) {
      this.mt = var1;
      this.mb = var3;
      this.mc = new File(aH.cG, var2);
      ZipFile var4 = new ZipFile(this.mc);

      try {
         ZipEntry var5 = var4.getEntry("saveinfo.txt");
         if (var5 == null) {
            throw new IOException("Invalid backup file");
         }

         Properties var6 = new Properties();
         var6.load(var4.getInputStream(var5));
         this.mu = var6.getProperty("ManifestFile");
         this.md = var6.getProperty("StorageFile");
         if (this.mu == null || this.md == null) {
            throw new IOException("Invalid backup file");
         }

         String var7 = var6.getProperty("GameMode");
         this.be = var7 == null ? null : fn.valueOf(var7);
         this.mv = var6.getProperty("SaveName");
         this.description = var6.getProperty("Description");
      } catch (NumberFormatException var11) {
         throw new IOException("Invalid backup file");
      } finally {
         var4.close();
      }

   }

   public fn L() {
      return this.be;
   }

   public String K() {
      return this.mc.getName();
   }

   public long lastModified() {
      return this.mc.lastModified();
   }

   public String getName() {
      return this.mv;
   }

   public String getDescription() {
      return this.description;
   }

   public eY M() {
      // $FF: Couldn't be decompiled
   }

   public String b(eY var1) {
      hc.info("Writing new save file...");
      String var2;
      if (fJ.b(this.mt)[this.mb] != null) {
         var2 = fJ.b(this.mt)[this.mb].b(var1);
      } else {
         fJ.b(this.mt)[this.mb] = new fM(this.mt, this.mb, var1);
         var2 = fJ.b(this.mt)[this.mb].filename;
      }

      hc.info("Finished.");
      return var2;
   }

   public String toString() {
      return this.mc.getName();
   }
}
