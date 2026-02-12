using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class fV : fs {
   fW mO;
   int mb;
   File mc;
   string mP;
   fS mQ;
   string mR;
   string mS;
   fn be;
   fT mN;

   fV(fT var1, string var2, int var3) {
      this.mN = var1;
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
         this.mP = var6.getProperty("MetaFile");
         this.mR = var6.getProperty("DataFile");
         this.mS = var6.getProperty("ContainerFile");
         string var7 = var6.getProperty("IndexData");
         if (this.mP == null || this.mR == null || this.mS == null || var7 == null) {
            throw new IOException("Invalid backup file");
         }

         string var8 = var6.getProperty("GameMode");
         this.be = var8 == null ? null : fn.valueOf(var8);
         this.mO = new fW(var1, var7);
         var5 = var4.getEntry(this.mP);
         if (var5 == null) {
            throw new IOException("Invalid backup file");
         }

         this.mQ = new fS((File)null);
         this.mQ.read(var4.getInputStream(var5));
      } catch (FormatException var12) {
         throw new IOException("Invalid backup file");
      } finally {
         var4.Close();
      }

   }

   void a(FileStream var1) {
      ZipFile var2 = new ZipFile(this.mc);

      try {
         ZipEntry var3 = var2.getEntry(this.mS);
         if (var3 == null) {
            throw new IOException("Invalid backup file");
         }

         Stream var4 = var2.getInputStream(var3);

         try {
            byte[] var5 = new byte[1024];

            int var6;
            while((var6 = var4.read(var5)) > 0) {
               var1.Write(var5, 0, var6);
            }
         } finally {
            var4.Close();
         }
      } finally {
         var2.Close();
      }

   }

   public string K() {
      return this.mO.filename;
   }

   public fn L() {
      return this.be;
   }

   public eY M() {
   }

   public string b(eY var1) {
      hc.info("Writing new save file...");
      string var2;
      if (fT.b(this.mN)[this.mb] != null) {
         fT.b(this.mN)[this.mb].mZ.a(this.mQ);
         var2 = fT.b(this.mN)[this.mb].b(var1);
      } else {
         fT.b(this.mN)[this.mb] = new fY(this.mN, this, var1);
         var2 = fT.b(this.mN)[this.mb].K();
      }

      hc.info("Finished.");
      return var2;
   }

   public long lastModified() {
      return this.mO.timestamp;
   }

   public string toString() {
      return this.mc.Name;
   }

   public string getName() {
      return this.mQ.Name;
   }

   public string getDescription() {
      return this.mQ.getDescription();
   }
}

}
