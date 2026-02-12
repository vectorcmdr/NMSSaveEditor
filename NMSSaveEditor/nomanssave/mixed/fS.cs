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

public class fS {
   public FileInfo mh;
   public int lL;
   public int version;
   public long my;
   public int mz;
   public int mA;
   public byte[] mB;
   public string name;
   public string description;
   public int lM;

   public fS(FileInfo var1) {
      this.mh = var1;
   }

   public void cn() {
      Exception var1 = null;
      Object var2 = null;
       try {
         FileStream var3 = new FileStream(this.mh);
          try {
            this.read(var3);
         } finally {
            if (var3 != null) {
               var3.Close();
            }
          }
       } catch (Exception var9) {
         if (var1 == null) {
            var1 = var9;
         } else if (var1 != var9) {
            var1.addSuppressed(var9);
         }
          throw var1;
      }
   }

   public void read(Stream var1) {
      this.lL = hk.readInt(var1);
      if (this.lL != 0) {
         hc.debug("  unknown1: " + Convert.ToString((int)this.lL));
      }

      this.version = hk.readInt(var1);
      if (this.version != 0) {
         hc.info("  version: " + this.version);
      }

      this.my = hk.f(var1);
      if (this.my != 0L) {
         hc.info("  totalPlayTime: " + fq.c(this.my));
      }

      if (this.lL == 1) {
         this.mz = hk.readInt(var1);
         if (this.mz != 0) {
            hc.debug("  decompressed: " + this.mz);
         }

         this.mA = 0;
         this.mB = new byte[128];
         hk.readFully(var1, this.mB);
      } else {
         this.mz = 0;
         this.mA = hk.readInt(var1);
         if (this.mA != 0) {
            hc.debug("  compressed: " + this.mA);
         }

         this.mB = null;
         this.name = gc.e(var1);
         if (this.name.Length != 0) {
            hc.debug("  name: " + this.name);
         }

         this.description = gc.e(var1);
         if (this.description.Length != 0) {
            hc.debug("  description: " + this.description);
         }
      }

      this.lM = hk.readInt(var1);
      if (this.lM != 0) {
         hc.debug("  unknown2: " + Convert.ToString((int)this.lM));
      }

   }

   public void write() {
      Exception var1 = null;
      Object var2 = null;
       try {
         FileStream var3 = new FileStream(this.mh);
          try {
            this.Write(var3);
         } finally {
            if (var3 != null) {
               var3.Close();
            }
          }
       } catch (Exception var9) {
         if (var1 == null) {
            var1 = var9;
         } else if (var1 != var9) {
            var1.addSuppressed(var9);
         }
          throw var1;
      }
   }

   public void write(Stream var1) {
      hk.a(var1, this.lL);
      hk.a(var1, this.version);
      hk.b(var1, this.my);
      if (this.mB != null) {
         hk.a(var1, this.mz);
         var1.Write(this.mB);
      } else {
         hk.a(var1, this.mA);
         gc.c(var1, this.name);
         gc.c(var1, this.description);
      }

      hk.a(var1, this.lM);
   }

   public byte[] co() {
      MemoryStream var1 = new MemoryStream();
      this.Write(var1);
      return var1.toByteArray();
   }

   public bool cp() {
      return this.mB == null;
   }

   public int getVersion() {
      return this.version;
   }

   public void setVersion(int var1) {
      this.version = var1;
   }

   public int ch() {
      return this.mz;
   }

   public void aj(int var1) {
      if (this.mB != null) {
         this.mz = var1;
      }

   }

   public int ci() {
      return this.mA;
   }

   public void ak(int var1) {
      if (this.mB == null) {
         this.mA = var1;
      }

   }

   public string ck() {
      return this.name;
   }

   public void Y(string var1) {
      this.name = var1;
   }

   public string getDescription() {
      return this.description;
   }

   public long cq() {
      return this.my;
   }

   public void d(long var1) {
      this.my = var1;
   }

   public string getName() {
      return this.mh.Name;
   }

   public long length() {
      return this.mh.Length;
   }

   public long Length => length();

   public void a(fS var1) {
      this.lL = var1.lL;
      this.version = var1.version;
      this.my = var1.my;
      this.mA = var1.mA;
      this.mz = var1.mz;
      this.mB = var1.mB;
      this.name = var1.name;
      this.description = var1.description;
      this.lM = var1.lM;
   }
}

}
