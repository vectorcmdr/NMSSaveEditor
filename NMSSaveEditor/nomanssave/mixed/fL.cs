using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class fL : fs {
   int mb;
   File mc;
   string mu;
   string md;
   fn be;
   string mv;
   string description;
   fJ mt;

   fL(fJ var1, string var2, int var3) {
      this.mt = var1;
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
         this.mu = var6.getProperty("ManifestFile");
         this.md = var6.getProperty("StorageFile");
         if (this.mu == null || this.md == null) {
            throw new IOException("Invalid backup file");
         }

         string var7 = var6.getProperty("GameMode");
         this.be = var7 == null ? null : fn.valueOf(var7);
         this.mv = var6.getProperty("SaveName");
         this.description = var6.getProperty("Description");
      } catch (FormatException var11) {
         throw new IOException("Invalid backup file");
      } finally {
         var4.Close();
      }

   }

   public fn L() {
      return this.be;
   }

   public string K() {
      return this.mc.Name;
   }

   public long lastModified() {
      return this.mc.LastWriteTimeUtc.Ticks;
   }

   public string getName() {
      return this.mv;
   }

   public string getDescription() {
      return this.description;
   }

   public eY M() {
   }

   public string b(eY var1) {
      hc.info("Writing new save file...");
      string var2;
      if (fJ.b(this.mt)[this.mb] != null) {
         var2 = fJ.b(this.mt)[this.mb].b(var1);
      } else {
         fJ.b(this.mt)[this.mb] = new fM(this.mt, this.mb, var1);
         var2 = fJ.b(this.mt)[this.mb].filename;
      }

      hc.info("Finished.");
      return var2;
   }

   public string toString() {
      return this.mc.Name;
   }
}

}
