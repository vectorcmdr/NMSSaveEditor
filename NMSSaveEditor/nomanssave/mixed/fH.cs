using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class fH {
   File mh;
   byte[] lK;
   fA ma;

   fH(fA var1, string var2, bool var3) {
      this.ma = var1;
      this.mh = new File(fA.a(var1), var2);
      if (var3) {
         FileStream var4 = new FileStream(this.mh);

         try {
            this.lK = new byte[112];
            hk.readFully(var4, this.lK);

            for(int var5 = 0; var5 < fA.bY().Length; ++var5) {
               if (this.lK[var5] != fA.bY()[var5]) {
                  throw new IOException("Invalid header");
               }
            }
         } finally {
            var4.Close();
         }
      }

   }

   byte[] readBytes() {
      long var1 = (255L & (long)this.lK[95]) << 24 | (255L & (long)this.lK[94]) << 16 | (255L & (long)this.lK[93]) << 8 | 255L & (long)this.lK[92];
      FileStream var3 = new FileStream(new File(fA.a(this.ma), this.K()));

      byte[] var6;
      try {
         byte[] var4 = new byte[(int)var1];
         var3.skip(112L);
         hk.readFully(var3, var4);
         var6 = var4;
      } finally {
         var3.Close();
      }

      return var6;
   }

   byte[] ah(int var1) {
      long var2 = (255L & (long)this.lK[95]) << 24 | (255L & (long)this.lK[94]) << 16 | (255L & (long)this.lK[93]) << 8 | 255L & (long)this.lK[92];
      FileStream var4 = new FileStream(new File(fA.a(this.ma), this.K()));

      byte[] var7;
      try {
         var1 = (int)Math.Min((long)var1, var2);
         byte[] var5 = new byte[var1];
         var4.skip(112L);
         hk.readFully(var4, var5);
         var7 = var5;
      } finally {
         var4.Close();
      }

      return var7;
   }

   void writeBytes(byte[] var1) {
      this.lK[92] = (byte)var1.Length;
      this.lK[93] = (byte)(var1.Length >> 8);
      this.lK[94] = (byte)(var1.Length >> 16);
      this.lK[95] = (byte)(var1.Length >> 24);
      FileStream var2 = new FileStream(new File(fA.a(this.ma), this.K()));

      try {
         var2.Write(this.lK);
         var2.Write(var1);
      } finally {
         var2.Close();
      }

   }

   void a(string var1, fn var2, string var3, string var4) {
      Dictionary<string, string> var5 = new Dictionary<string, string>();
      var5.setProperty("StorageFile", this.mh.Name);
      var5.setProperty("LastModified", Long.toString(this.mh.LastWriteTimeUtc.Ticks));
      if (var2 != null) {
         var5.setProperty("GameMode", var2.name());
      }

      if (var3 != null) {
         var5.setProperty("SaveName", var3);
      }

      if (var4 != null) {
         var5.setProperty("Description", var4);
      }

      string var6 = var1 + "." + DateTimeOffset.UtcNow.ToUnixTimeMilliseconds() + ".zip";
      File var7 = new File(aH.cG, var6);
      ZipOutputStream var8 = new ZipOutputStream(new FileStream(var7));

      try {
         byte[] var10 = new byte[4096];
         ZipEntry var11 = new ZipEntry(this.mh.Name);
         var8.putNextEntry(var11);
         FileStream var12 = new FileStream(this.mh);

         int var9;
         try {
            while((var9 = var12.read(var10)) >= 0) {
               var8.Write(var10, 0, var9);
            }
         } finally {
            var12.Close();
         }

         var11 = new ZipEntry("saveinfo.txt");
         var8.putNextEntry(var11);
         var5.store(var8, "");
      } finally {
         var8.Close();
      }

      var7.setLastModified(this.mh.LastWriteTimeUtc.Ticks);
   }

   public string K() {
      return this.mh.Name;
   }
}

}
