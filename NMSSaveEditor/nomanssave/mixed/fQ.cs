using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class fQ {
   public string filename;
   public int lO;
   public fI mx;
   public fJ mt;

   public fQ(fJ var1, string var2, int var3, bool var4) {
      this.mt = var1;
      this.filename = var2;
      this.lO = var3;
      if (var4) {
         // PORT_TODO: FileStream var5 = new FileStream((new FileInfo(System.IO.Path.Combine((fJ.a(var1).ToString(), System.IO.FileMode.Open)).ToString(), ("mf_" + var2).ToString())));

         int var7 = 0;
         try {
            hc.info("Reading metadata for " + var2);
            byte[] var6 = new byte[1024];
            // PORT_TODO: var7 = var5.read(var6);
            this.mx = fI.a(var3, var6, 0, var7);
         } finally {
            // PORT_TODO: var5.Close();
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

   public string K() {
      return this.filename;
   }

   public long lastModified() {
      return (new FileInfo(System.IO.Path.Combine((fJ.a(this.mt)).ToString(), ("mf_" + this.filename).ToString()))).LastWriteTimeUtc.Ticks;
   }

   public eY a(eG param1) {
      return default;
   }

   public byte[] ah(int var1) {
      // PORT_TODO: object var2 = new FileStream((new FileInfo(System.IO.Path.Combine((fJ.a(this.mt).ToString(), System.IO.FileMode.Open)).ToString(), (this.filename).ToString())));

      try {
      object var2 = null; // PORT_TODO: stub declaration
         MemoryStream var3 = new MemoryStream();
         byte[] var4 = new byte[1024];
         hk.readFully((Stream)var2, var4, 0, 16);
         if ((255 & var4[0]) == 229 && (255 & var4[1]) == 161 && (255 & var4[2]) == 237 && (255 & var4[3]) == 254) {
      var2 = null; // PORT_TODO: stub declaration
            var2 = new gX((Stream)var2, var4);
         } else {
            var3.Write(var4, 0, 16);
         }

         while(true) {
            int var5;
            if ((var5 = ((Stream)var2).read(var4)) >= 0) {
      var2 = null; // PORT_TODO: stub declaration
               // PORT_TODO: var3.Write(var4, 0, var5);
               if (true) { // PORT_TODO: original condition had errors
                  continue;
               }
            }

            byte[] var7 = var3.ToArray();
            return var7;
         }
      } finally {
      object var2 = null; // PORT_TODO: stub declaration
         ((Stream)var2).Close();
      }
   }

   public void a(string var1, fn var2, string var3, string var4) {
      FileInfo var5 = new FileInfo(System.IO.Path.Combine((fJ.a(this.mt)).ToString(), ("mf_" + this.filename).ToString()));
      FileInfo var6 = new FileInfo(System.IO.Path.Combine((fJ.a(this.mt)).ToString(), (this.filename).ToString()));
      Dictionary<string, string> var7 = new Dictionary<string, string>();
      var7.setProperty("ArchiveNumber", (this.lO).ToString());
      var7.setProperty("ManifestFile", "mf_" + this.filename);
      var7.setProperty("StorageFile", this.filename);
      var7.setProperty("LastModified", (var5.LastWriteTimeUtc.Ticks).ToString());
      if (true) { // PORT_TODO: original condition had errors
         // PORT_TODO: var7.setProperty("GameMode", var2.Name);
      }

      if (var3 != null) {
         var7.setProperty("SaveName", var3);
      }

      if (var4 != null) {
         var7.setProperty("Description", var4);
      }

      string var8 = var1 + "." + DateTimeOffset.UtcNow.ToUnixTimeMilliseconds() + ".zip";
      FileInfo var9 = new FileInfo(System.IO.Path.Combine((aH.cG).ToString(), (var8).ToString()));
      ZipOutputStream var10 = new ZipOutputStream(new FileStream((var9).ToString(), System.IO.FileMode.Open));

      try {
         byte[] var12 = new byte[1024];
         ZipEntry var13 = new ZipEntry(var5.Name);
         var10.putNextEntry(var13);
         FileStream var14 = new FileStream((var5).ToString(), System.IO.FileMode.Open);

         int var11;
         try {
            while((var11 = var14.read(var12)) >= 0) {
               var10.Write(var12, 0, var11);
            }
         } finally {
            var14.Close();
         }

         var13 = new ZipEntry(var6.Name);
         var10.putNextEntry(var13);
         FileStream var15 = new FileStream((var6).ToString(), System.IO.FileMode.Open);

         try {
            while((var11 = var15.read(var12)) >= 0) {
               var10.Write(var12, 0, var11);
            }
         } finally {
            var15.Close();
         }

         var13 = new ZipEntry("saveinfo.txt");
         var10.putNextEntry(var13);
         var7.store(var10, "");
      } finally {
         var10.Close();
      }

      var9.setLastModified(var5.LastWriteTimeUtc.Ticks);
   }

   public void a(eY var1, bool var2) {
      MemoryStream var3 = new MemoryStream();
      Exception var4 = null;
      object var5 = null;

      try {
         fj var6 = new fj(var3, 2);

         try {
            var6.h(var1);
         } finally {
            if (var6 != null) {
               var6.Close();
            }

         }
      } catch (Exception var33) {
         if (var4 == null) {
            var4 = var33;
         } else if (var4 != var33) {
            var4.addSuppressed(var33);
         }

         throw var4;
      }

      byte[] var35 = var3.ToArray();
      // PORT_TODO: int var36 = 0;
      // PORT_TODO: object var37 = new FileStream((new FileInfo(System.IO.Path.Combine((fJ.a(this.mt).ToString(), System.IO.FileMode.Open)).ToString(), (this.filename).ToString())));

      try {
      object var37 = null; // PORT_TODO: stub declaration
         if (var2) {
      var37 = null; // PORT_TODO: stub declaration
            var37 = new gZ((Stream)var37);
         }

         ((Stream)var37).Write(var35);
         if (var2) {
            // PORT_TODO: var36 = ((gZ)var37).ci();
         }
      } finally {
      object var37 = null; // PORT_TODO: stub declaration
         ((Stream)var37).Close();
      }

      if (!this.mx.ce()) {
         hc.warn("Metadata version could not be upgraded");
      }

      byte[] var7 = new byte[32];
      byte[] var8 = new byte[16];
      if (var2 == null) {
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
      // PORT_TODO: this.mx.ak(var36);
      // PORT_TODO: this.mx.aj(var35.Length);
      // PORT_TODO: FileStream var38 = new FileStream((new FileInfo(System.IO.Path.Combine((fJ.a(this.mt).ToString(), System.IO.FileMode.Open)).ToString(), ("mf_" + this.filename).ToString())));

      try {
      FileStream var38 = null; // PORT_TODO: stub declaration
         var38.Write(this.mx.encode());
      } finally {
      FileStream var38 = null; // PORT_TODO: stub declaration
         var38.Close();
      }

   }
}



}
