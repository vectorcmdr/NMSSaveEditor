using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public abstract class fX {
   fW mO;
   public FileInfo mX;
   public FileInfo mY;
   fS mZ;
   int mode;
   // $FF: synthetic field
   fT mN;

   fX(fT var1, fV var2) {
      this.mN = var1;
      int var3 = fT.c(var1).Count;

      for(int var4 = 0; var4 < fT.c(var1).Count; ++var4) {
         int var5 = ((fW)fT.c(var1).Get(var4)).name.CompareTo(var2.mO.name);
         if (var5 == 0) {
            fW var6 = (fW)fT.c(var1).Remove(var4);
            fT.i(new File(fT.d(var1), var6.mU));
         }

         if (var5 >= 0) {
            var3 = var4;
            break;
         }
      }

      this.mO = new fW(var1, var2.mO);
      FileInfo var9 = new File(fT.d(var1), this.mO.mU);
      if (!var9.Create()) {
         throw new IOException("Unable to create container path");
      } else {
         this.mX = new File(var9, "container." + this.mO.mT);
         this.mZ = new fS(new File(var9, var2.mP));
         this.mY = new File(var9, var2.mR);
         FileStream var10 = new FileStream(this.mX);

         try {
            var2.a(var10);
         } finally {
            var10.Close();
         }

         fT.c(var1).Add(var3, this.mO);
      }
   }

   fX(fT var1, string var2) {
      this.mN = var1;
      this.mO = fT.a(var1, var2);
      FileInfo var3 = new File(fT.d(var1), this.mO.mU);
      if (!var3.IsDirectory()) {
         throw new FileNotFoundException(this.mO.mU);
      } else {
         this.mX = new File(var3, "container." + this.mO.mT);
         hc.info(this.mO.filename);
         FileInfo var4 = null;
         FileInfo var5 = null;
         FileStream var6 = new FileStream(this.mX);

         try {
            int var7 = hk.readInt(var6);
            hc.debug("  header: " + Convert.ToString((int)var7));
            int var8 = hk.readInt(var6);

            for(int var9 = 0; var9 < var8; ++var9) {
               string var10 = gc.d(var6);
               hc.debug("  name: " + var10);
               string var11 = gc.a(var6);
               hc.debug("  filename: " + var11);
               string var12 = gc.a(var6);
               if (!var11.Equals(var12)) {
                  hc.debug("  filename2: " + var12);
               }

               if (var10.Equals("data")) {
                  var4 = new File(var3, var11);
                  if (!var4.Exists) {
                     var4 = new File(var3, var12);
                  }
               }

               if (var10.Equals("meta")) {
                  var5 = new File(var3, var11);
                  if (!var5.Exists) {
                     var5 = new File(var3, var12);
                  }
               }
            }
         } finally {
            var6.Close();
         }

         if (var4 != null && var5 != null) {
            long var16 = var5.Length + var4.Length;
            if (this.mO.mW != var16) {
               throw new IOException("data size mismatch: " + this.mO.mW);
            } else {
               this.mY = var4;
               this.mZ = new fS(var5);
               this.mZ.cn();
            }
         } else {
            throw new FileNotFoundException("data/meta file missing");
         }
      }
   }

   public string K() {
      return this.mO.filename;
   }

   public Stream getInputStream() {
      Stream var1 = fT.b(new FileStream(this.mY), this.mZ.ch());
      if (var1 is gX) {
         this.mode = fT.cv();
      } else if (var1 is hm) {
         this.mode = fT.cw();
      } else {
         this.mode = fT.cx();
      }

      return var1;
   }

   public Stream getOutputStream() {
      FileStream var1 = new FileStream(this.mY);

      try {
         if (this.mode == fT.cv()) {
            return new gZ(var1);
         } else if (this.mode == fT.cw()) {
            var1.Write(fT.cy());
            return new ho(var1);
         } else {
            return new hb(var1);
         }
      } catch (IOException var5) {
         try {
            var1.Close();
         } catch (IOException var4) {
         }

         throw var5;
      }
   }

   eY a(eG var1) {
      Exception var2 = null;
      Object var3 = null;

      try {
         ff var4 = new ff(this.getInputStream(), 2);

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

   byte[] ah(int var1) {
      MemoryStream var2 = new MemoryStream();
      Exception var3 = null;
      Object var4 = null;

      try {
         Stream var5 = this.getInputStream();

         try {
            byte[] var6 = new byte[4096];

            int var7;
            while((var7 = var5.read(var6)) >= 0) {
               var2.Write(var6, 0, var7);
               if (var2.Count >= var1) {
                  break;
               }
            }
         } finally {
            if (var5 != null) {
               var5.Close();
            }

         }
      } catch (Exception var13) {
         if (var3 == null) {
            var3 = var13;
         } else if (var3 != var13) {
            var3.addSuppressed(var13);
         }

         throw var3;
      }

      return var2.toByteArray();
   }

   void h(eY var1) {
      bool var2 = this.mode == fT.cw();
      MemoryStream var3 = new MemoryStream();
      Exception var4 = null;
      Exception var5 = null;

      fj var6;
      try {
         var6 = new fj(var3, var2 ? 0 : 2);

         try {
            var6.h(var1);
         } finally {
            if (var6 != null) {
               var6.Close();
            }

         }
      } catch (Exception var25) {
         if (var4 == null) {
            var4 = var25;
         } else if (var4 != var25) {
            var4.addSuppressed(var25);
         }

         throw var4;
      }

      byte[] var28 = var3.toByteArray();
      this.mZ.aj(var28.Length);
      var5 = null;
      var6 = null;

      try {
         Stream var7 = this.getOutputStream();

         try {
            var7.Write(var28);
            if (var2) {
               var7.Flush();
               var7.Write(0);
            }
         } finally {
            if (var7 != null) {
               var7.Close();
            }

         }
      } catch (Exception var27) {
         if (var5 == null) {
            var5 = var27;
         } else if (var5 != var27) {
            var5.addSuppressed(var27);
         }

         throw var5;
      }

      this.mZ.ak((int)this.mY.Length);
      this.mZ.Write();
      this.mO.timestamp = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds();
      this.mO.mW = this.mY.Length + this.mZ.Length;
      fT.e(this.mN);
   }

   void a(string var1, fn var2) {
      Properties var3 = new Properties();
      var3.setProperty("MetaFile", this.mZ.Name);
      var3.setProperty("DataFile", this.mY.Name);
      var3.setProperty("ContainerFile", this.mX.Name);
      if (var2 != null) {
         var3.setProperty("GameMode", var2.ToString());
      }

      var3.setProperty("IndexData", this.mO.cz());
      string var4 = var1 + "." + DateTimeOffset.UtcNow.ToUnixTimeMilliseconds() + ".zip";
      FileInfo var5 = new File(aH.cG, var4);
      ZipOutputStream var6 = new ZipOutputStream(new FileStream(var5));

      try {
         ZipEntry var8 = new ZipEntry(this.mZ.Name);
         var6.putNextEntry(var8);
         var6.Write(this.mZ.co());
         var8 = new ZipEntry(this.mY.Name);
         var6.putNextEntry(var8);
         byte[] var9 = new byte[1024];
         FileStream var10 = new FileStream(this.mY);

         int var7;
         try {
            while((var7 = var10.read(var9)) > 0) {
               var6.Write(var9, 0, var7);
            }
         } finally {
            var10.Close();
         }

         var8 = new ZipEntry(this.mX.Name);
         var6.putNextEntry(var8);
         var10 = new FileStream(this.mX);

         try {
            while((var7 = var10.read(var9)) > 0) {
               var6.Write(var9, 0, var7);
            }
         } finally {
            var10.Close();
         }

         var8 = new ZipEntry("saveinfo.txt");
         var6.putNextEntry(var8);
         var3.store(var6, "");
      } finally {
         var6.Close();
      }

      var5.setLastModified(this.mO.timestamp);
   }
}

}
