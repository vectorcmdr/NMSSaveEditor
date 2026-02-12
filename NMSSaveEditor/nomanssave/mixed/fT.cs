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

public class fT : fq {
   public static byte[] lA = "HGSAVEV2\u0000".GetBytes();
   public static Pattern lV = new Regex("Slot(\\d+)((Auto)|(Manual))");
   public static Pattern lW = new Regex("wgsbackup(\\d*)\\.\\d*\\.zip");
   public static string mC = "containers.index";
   public FileInfo lX;
   public fR lE;
   public fU mD;
   public fY[] mE;
   public int header;
   public int lL;
   public string name;
   public int lM;
   public int lR;
   public int lS;
   public string mF;
   public int mG;
   public int mH;
   public List<object> mI;
   public static Pattern mJ = new Regex("\"((?:<h0)|(?:CommonStateData))\":\\{\"((?:Pk4)|(?:SaveName))\":\"([^\"]+)\"");
   public static int mK = 1;
   public static int mL = 2;
   public static int mM = 3;
    public fT(FileInfo var1, fR var2) {
      this.lX = var1.IsDirectory() ? var1 : var1.Directory;
      this.lE = var2;
      this.cr();
       try {
         this.mD = new fU(this);
      } catch (FileNotFoundException var7) {
      } catch (IOException var8) {
         hc.a("Cannot read account data", var8);
      }
       this.mE = new fY[30];
       for(int var3 = 0; var3 < this.mE.Length; ++var3) {
         try {
            this.mE[var3] = new fY(this, var3);
         } catch (FileNotFoundException var5) {
         } catch (IOException var6) {
            hc.a("Cannot read file data", var6);
         }
      }
       fl.a(this, this.lX);
   }
    public void finalize() {
      fl.b(this);
   }
    public void X(string var1) {
      var1.Equals("containers.index");
   }
    public FileInfo bS() {
      return this.lX;
   }
    public void cr() {
      hc.info("Reading Container Index");
      FileStream var1 = new FileStream(new FileInfo(this.lX, "containers.index"));
       try {
         this.header = hk.readInt(var1);
         hc.debug("  header: " + this.header);
         int var2 = hk.readInt(var1);
         hc.debug("  count: " + var2);
         this.lL = hk.readInt(var1);
         if (this.lL != 0) {
            hc.debug("  unknown1: " + this.lL);
         }
          this.name = gc.c(var1);
         hc.debug("  name: " + this.name);
         this.lM = hk.readInt(var1);
         if (this.lM != 0) {
            hc.debug("  unknown2: " + this.lM);
         }
          this.lR = hk.readInt(var1);
         if (this.lR != 0) {
            hc.debug("  unknown3: " + this.lR);
         }
          this.lS = hk.readInt(var1);
         if (this.lS != 0) {
            hc.debug("  unknown4: " + this.lS);
         }
          this.mF = gc.c(var1);
         hc.debug("  appid: " + this.mF);
         this.mG = hk.readInt(var1);
         if (this.mG != 0) {
            hc.debug("  unknown5: " + this.mG);
         }
          this.mH = hk.readInt(var1);
         if (this.mH != 0) {
            hc.debug("  unknown6: " + this.mH);
         }
          this.mI = new List<object>();
          for(int var3 = 0; var3 < var2; ++var3) {
            this.mI.Add(new fW(this, var1));
         }
          if (var1.ReadByte() >= 0) {
            throw new IOException("Invalid footer");
         }
      } finally {
         var1.Close();
      }
    }
    public void cs() {
      FileStream var1 = new FileStream(new FileInfo(this.lX, "containers.index"));
       try {
         hk.a(var1, this.header);
         hk.a(var1, this.mI.Count);
         hk.a(var1, this.lL);
         gc.b(var1, this.name);
         hk.a(var1, this.lM);
         hk.a(var1, this.lR);
         hk.a(var1, this.lS);
         gc.b(var1, this.mF);
         hk.a(var1, this.mG);
         hk.a(var1, this.mH);
         IEnumerator<object> var3 = this.mI.GetEnumerator();
          while(var3.MoveNext()) {
            fW var2 = (fW)var3.Current;
            var2.Write(var1);
         }
      } finally {
         var1.Close();
      }
    }
    public fW Z(string var1) {
      IEnumerator<object> var3 = this.mI.GetEnumerator();
       while(var3.MoveNext()) {
         fW var2 = (fW)var3.Current;
         if (var2.name.Equals(var1)) {
            return var2;
         }
      }
       throw new FileNotFoundException(var1);
   }
    public string ct() {
      bool var1;
      FileInfo var2;
      string var3;
      do {
         var1 = true;
         var3 = gc.cA();
          fW var4;
         for(IEnumerator<object> var5 = this.mI.GetEnumerator(); var5.MoveNext(); var1 &= var4.mU.Equals(var3)) {
            var4 = (fW)var5.Current;
         }
          var2 = new FileInfo(this.lX, var3);
         var1 &= var2.Exists;
      } while(!var1);
       if (!var2.Create()) {
         throw new FileNotFoundException(var3);
      } else {
         return var3;
      }
   }
    public fr bT() {
      return this.mD;
   }
    public ft[] bU() {
      ft[] var1 = new ft[15];
       for(int var2 = 0; var2 < 15; ++var2) {
         var1[var2] = new fZ(this, var2);
      }
       return var1;
   }
    public int W(string var1) {
      Matcher var2 = lV.Match(var1);
      return !var2.Matches() ? -1 : int.Parse(var2.Groups[1]);
   }
    public static int an(int var0) {
      return 2147418112 & var0 | (3584 & var0) >> 9;
   }
    public static bool h(FileInfo var0) {
      File[] var1 = var0.ListFiles();
      if (var1 != null) {
         File[] var5 = var1;
         int var4 = var1.Length;
          for(int var3 = 0; var3 < var4; ++var3) {
            FileInfo var2 = var5[var3];
            h(var2);
         }
      }
       return var0.Delete();
   }
    public static Stream a(Stream var0, int var1) {
      try {
         bool var2 = true;
         if (!((Stream)var0).markSupported()) {
            var0 = new BufferedInputStream((Stream)var0);
         }
          ((Stream)var0).mark(lA.Length);
         byte[] var3 = new byte[lA.Length];
         hk.readFully((Stream)var0, var3);
          for(int var4 = 0; var4 < lA.Length; ++var4) {
            if (var3[var4] != lA[var4]) {
               var2 = false;
               break;
            }
         }
          if (var2) {
            return new hm((Stream)var0);
         } else {
            ((Stream)var0).reset();
            byte[] var7 = new byte[16];
            ((Stream)var0).mark(var7.Length);
            hk.readFully((Stream)var0, var7);
            if ((255 & var7[0]) == 229 && (255 & var7[1]) == 161 && (255 & var7[2]) == 237 && (255 & var7[3]) == 254) {
               return new gX((Stream)var0, var7);
            } else {
               ((Stream)var0).reset();
               return new ha((Stream)var0, var1);
            }
         }
      } catch (IOException var6) {
         try {
            ((Stream)var0).Close();
         } catch (IOException var5) {
         }
          throw var6;
      }
   }
    // $FF: synthetic method
   public static string a(fT var0) {
      return var0.ct();
   }
    // $FF: synthetic method
   public static fY[] b(fT var0) {
      return var0.mE;
   }
    // $FF: synthetic method
   public static Pattern cu() {
      return lW;
   }
    // $FF: synthetic method
   public static Pattern cl() {
      return mJ;
   }
    // $FF: synthetic method
   public static int ao(int var0) {
      return an(var0);
   }
    // $FF: synthetic method
   public static List<object> c(fT var0) {
      return var0.mI;
   }
    // $FF: synthetic method
   public static FileInfo d(fT var0) {
      return var0.lX;
   }
    // $FF: synthetic method
   public static bool i(FileInfo var0) {
      return h(var0);
   }
    // $FF: synthetic method
   public static fW a(fT var0, string var1) {
      return var0.Z(var1);
   }
    // $FF: synthetic method
   public static Stream b(Stream var0, int var1) {
      return a(var0, var1);
   }
    // $FF: synthetic method
   public static int cv() {
      return mM;
   }
    // $FF: synthetic method
   public static int cw() {
      return mL;
   }
    // $FF: synthetic method
   public static int cx() {
      return mK;
   }
    // $FF: synthetic method
   public static byte[] cy() {
      return lA;
   }
    // $FF: synthetic method
   public static void e(fT var0) {
      var0.cs();
   }

}


}