package nomanssave;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class fH {
   final File mh;
   byte[] lK;
   // $FF: synthetic field
   final fA ma;

   fH(fA var1, String var2, boolean var3) {
      this.ma = var1;
      this.mh = new File(fA.a(var1), var2);
      if (var3) {
         FileInputStream var4 = new FileInputStream(this.mh);

         try {
            this.lK = new byte[112];
            hk.readFully(var4, this.lK);

            for(int var5 = 0; var5 < fA.bY().length; ++var5) {
               if (this.lK[var5] != fA.bY()[var5]) {
                  throw new IOException("Invalid header");
               }
            }
         } finally {
            var4.close();
         }
      }

   }

   byte[] readBytes() {
      long var1 = (255L & (long)this.lK[95]) << 24 | (255L & (long)this.lK[94]) << 16 | (255L & (long)this.lK[93]) << 8 | 255L & (long)this.lK[92];
      FileInputStream var3 = new FileInputStream(new File(fA.a(this.ma), this.K()));

      byte[] var6;
      try {
         byte[] var4 = new byte[(int)var1];
         var3.skip(112L);
         hk.readFully(var3, var4);
         var6 = var4;
      } finally {
         var3.close();
      }

      return var6;
   }

   byte[] ah(int var1) {
      long var2 = (255L & (long)this.lK[95]) << 24 | (255L & (long)this.lK[94]) << 16 | (255L & (long)this.lK[93]) << 8 | 255L & (long)this.lK[92];
      FileInputStream var4 = new FileInputStream(new File(fA.a(this.ma), this.K()));

      byte[] var7;
      try {
         var1 = (int)Math.min((long)var1, var2);
         byte[] var5 = new byte[var1];
         var4.skip(112L);
         hk.readFully(var4, var5);
         var7 = var5;
      } finally {
         var4.close();
      }

      return var7;
   }

   void writeBytes(byte[] var1) {
      this.lK[92] = (byte)var1.length;
      this.lK[93] = (byte)(var1.length >> 8);
      this.lK[94] = (byte)(var1.length >> 16);
      this.lK[95] = (byte)(var1.length >> 24);
      FileOutputStream var2 = new FileOutputStream(new File(fA.a(this.ma), this.K()));

      try {
         var2.write(this.lK);
         var2.write(var1);
      } finally {
         var2.close();
      }

   }

   void a(String var1, fn var2, String var3, String var4) {
      Properties var5 = new Properties();
      var5.setProperty("StorageFile", this.mh.getName());
      var5.setProperty("LastModified", Long.toString(this.mh.lastModified()));
      if (var2 != null) {
         var5.setProperty("GameMode", var2.name());
      }

      if (var3 != null) {
         var5.setProperty("SaveName", var3);
      }

      if (var4 != null) {
         var5.setProperty("Description", var4);
      }

      String var6 = var1 + "." + System.currentTimeMillis() + ".zip";
      File var7 = new File(aH.cG, var6);
      ZipOutputStream var8 = new ZipOutputStream(new FileOutputStream(var7));

      try {
         byte[] var10 = new byte[4096];
         ZipEntry var11 = new ZipEntry(this.mh.getName());
         var8.putNextEntry(var11);
         FileInputStream var12 = new FileInputStream(this.mh);

         int var9;
         try {
            while((var9 = var12.read(var10)) >= 0) {
               var8.write(var10, 0, var9);
            }
         } finally {
            var12.close();
         }

         var11 = new ZipEntry("saveinfo.txt");
         var8.putNextEntry(var11);
         var5.store(var8, "");
      } finally {
         var8.close();
      }

      var7.setLastModified(this.mh.lastModified());
   }

   public String K() {
      return this.mh.getName();
   }
}
