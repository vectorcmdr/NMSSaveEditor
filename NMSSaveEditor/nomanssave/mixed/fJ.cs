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

public class fJ : fq {
   public static Pattern lV = new Regex("save(\\d*)\\.hg");
   public static Pattern lW = new Regex("backup(\\d*)\\.\\d*\\.zip");
   public FileInfo lX;
   public fR lE;
   public fK mr;
   public fM[] ms;

   public fJ(FileInfo var1, fR var2) {
      this.lX = var1;
      this.lE = var2;

      try {
         this.mr = new fK(this);
      } catch (FileNotFoundException var5) {
      } catch (IOException var6) {
         hc.a("cannot read file metadata: mf_accountdata.hg", var6);
      }

      this.ms = new fM[30];

      for(int var3 = 0; var3 < this.ms.length; ++var3) {
         try {
            this.ms[var3] = new fM(this, var3);
         } catch (FileNotFoundException var7) {
         } catch (IOException var8) {
            hc.a("cannot read file metadata: mf_save" + (var3 == 0 ? "" : Convert.ToString(var3 + 1)) + ".hg", var8);
         }
      }

      fl.a(this, var1);
   }

   public void finalize() {
      fl.b(this);
   }

   public void X(string var1) {
      if (var1.Equals("accountdata.hg")) {
         try {
            this.mr = new fK(this);
            hc.info("Account data reloaded from storage.");
         } catch (FileNotFoundException var5) {
            this.mr = null;
            hc.info("Account data deleted from storage.");
         } catch (IOException var6) {
            this.mr = null;
            hc.a("cannot read file metadata: mf_accountdata.hg", var6);
         }

         this.lE.a(this);
      }

      Matcher var2 = lV.Match(var1);
      if (var2.Matches()) {
         int var3 = var2.Groups[1].Length == 0 ? 0 : int.Parse(var2.Groups[1]) - 1;

         try {
            this.ms[var3] = new fM(this, var3);
            hc.info("Save file reloaded from storage: " + var1);
         } catch (FileNotFoundException var7) {
            this.ms[var3] = null;
            hc.info("Save file deleted from storage: " + var1);
         } catch (IOException var8) {
            this.ms[var3] = null;
            hc.a("cannot read file metadata: mf_save" + (var3 == 0 ? "" : Convert.ToString(var3 + 1)) + ".hg", var8);
         }

         this.lE.a(this, var3 / 2, var1);
      }

   }

   public FileInfo bS() {
      return this.lX;
   }

   public fr bT() {
      return this.mr;
   }

   public ft[] bU() {
      ft[] var1 = new ft[15];

      for(int var2 = 0; var2 < 15; ++var2) {
         var1[var2] = new fN(this, var2);
      }

      return var1;
   }

   public int W(string var1) {
      Matcher var2 = lV.Match(var1);
      if (!var2.Matches()) {
         return -1;
      } else {
         int var3 = var2.Groups[1].Length == 0 ? 0 : int.Parse(var2.Groups[1]) - 1;
         return var3 / 2;
      }
   }

   public bool bW() {
      return true;
   }

   public string a(int var1, eY var2) {
      if (this.ms[var1 * 2] != null) {
         this.ms[var1 * 2].cm();
         this.ms[var1 * 2] = null;
      }

      if (this.ms[var1 * 2 + 1] != null) {
         this.ms[var1 * 2 + 1].cm();
         this.ms[var1 * 2 + 1] = null;
      }

      this.ms[var1 * 2] = new fM(this, var1 * 2, var2);
      return this.ms[var1 * 2].filename;
   }

   public static byte[] a(long[] var0, int var1, int var2) {
      byte[] var3 = new byte[var2 * 4];

      for(int var4 = 0; var4 < var2; ++var4) {
         var3[var4 * 4] = (byte)((int)(var0[var1 + var4] & 255L));
         var3[var4 * 4 + 1] = (byte)((int)(var0[var1 + var4] >> 8 & 255L));
         var3[var4 * 4 + 2] = (byte)((int)(var0[var1 + var4] >> 16 & 255L));
         var3[var4 * 4 + 3] = (byte)((int)(var0[var1 + var4] >> 24 & 255L));
      }

      return var3;
   }

   public static byte[] c(byte[] var0, byte[] var1) {
      MemoryStream var2 = new MemoryStream();
      var2.Write(var0, 0, var0.length);
      var2.Write(var1, 0, var1.length);
      long[] var3 = new long[]{96176015842230784L, -8446744073709551617L};
      hh.a(var2.toByteArray(), var3);
      long[] var4 = new long[]{var3[0] & 4294967295L, var3[0] >>> 32 & 4294967295L, var3[1] & 4294967295L, var3[1] >>> 32 & 4294967295L};
      return a(var4, 0, 4);
   }

   // $FF: synthetic method
   static FileInfo a(fJ var0) {
      return var0.lX;
   }

   // $FF: synthetic method
   static byte[] d(byte[] var0, byte[] var1) {
      return c(var0, var1);
   }

   // $FF: synthetic method
   static fM[] b(fJ var0) {
      return var0.ms;
   }

   // $FF: synthetic method
   static Pattern cl() {
      return lW;
   }
}

}
