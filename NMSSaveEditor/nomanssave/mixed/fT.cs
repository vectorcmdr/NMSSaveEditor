using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace NMSSaveEditor
{



public class fT : fq {
   // PORT_TODO: public static readonly byte[] lA = "HGSAVEV2\u0000".GetBytes(System.Text.Encoding.UTF8);
   public static Pattern lV = Pattern.compile("Slot(\\d+)((Auto)|(Manual))");
   public static Pattern lW = Pattern.compile("wgsbackup(\\d*)\\.\\d*\\.zip");
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
   public static Pattern mJ = Pattern.compile("\"((?:<h0)|(?:CommonStateData))\":\\{\"((?:Pk4)|(?:SaveName))\":\"([^\"]+)\"");
   public static int mK = 1;
   public static int mL = 2;
   public static int mM = 3;

   public fT(FileInfo var1, fR var2) {
      // PORT_TODO: this.lX = var1.Attributes.HasFlag(FileAttributes.Directory) ? var1 : var1.Directory;
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
      // PORT_TODO: FileStream var1 = new FileStream((new FileInfo(System.IO.Path.Combine((this.lX).ToString(), System.IO.FileMode.Open).ToString(), ("containers.index").ToString())));

      try {
      int var2 = 0; // PORT_TODO: stub declaration
         // PORT_TODO: this.header = hk.readInt(var1);
         hc.debug("  header: " + this.header);
         // PORT_TODO: int var2 = hk.readInt(var1);
         hc.debug("  count: " + var2);
         // PORT_TODO: this.lL = hk.readInt(var1);
         if (this.lL != 0) {
            hc.debug("  unknown1: " + this.lL);
         }

         // PORT_TODO: this.name = gc.c(var1);
         hc.debug("  name: " + this.name);
         // PORT_TODO: this.lM = hk.readInt(var1);
         if (this.lM != 0) {
            hc.debug("  unknown2: " + this.lM);
         }

         // PORT_TODO: this.lR = hk.readInt(var1);
         if (this.lR != 0) {
            hc.debug("  unknown3: " + this.lR);
         }

         // PORT_TODO: this.lS = hk.readInt(var1);
         if (this.lS != 0) {
            hc.debug("  unknown4: " + this.lS);
         }

         // PORT_TODO: this.mF = gc.c(var1);
         hc.debug("  appid: " + this.mF);
         // PORT_TODO: this.mG = hk.readInt(var1);
         if (this.mG != 0) {
            hc.debug("  unknown5: " + this.mG);
         }

         // PORT_TODO: this.mH = hk.readInt(var1);
         if (this.mH != 0) {
            hc.debug("  unknown6: " + this.mH);
         }

         this.mI = new List<object>();

         for(int var3 = 0; var3 < var2; ++var3) {
      // PORT_TODO: var2 = null; // PORT_TODO: stub declaration
            // PORT_TODO: this.mI.Add(new fW(this, var1));
         }

         if (true) { // PORT_TODO: original condition had errors
            throw new IOException("Invalid footer");
         }
      } finally {
         // PORT_TODO: var1.Close();
      }

   }

   public void cs() {
      // PORT_TODO: FileStream var1 = new FileStream((new FileInfo(System.IO.Path.Combine((this.lX).ToString(), System.IO.FileMode.Open).ToString(), ("containers.index").ToString())));

      try {
         // PORT_TODO: hk.a(var1, this.header);
         // PORT_TODO: hk.a(var1, this.mI.Count);
         // PORT_TODO: hk.a(var1, this.lL);
         // PORT_TODO: gc.b(var1, this.name);
         // PORT_TODO: hk.a(var1, this.lM);
         // PORT_TODO: hk.a(var1, this.lR);
         // PORT_TODO: hk.a(var1, this.lS);
         // PORT_TODO: gc.b(var1, this.mF);
         // PORT_TODO: hk.a(var1, this.mG);
         // PORT_TODO: hk.a(var1, this.mH);
         IEnumerator<object> var3 = this.mI.GetEnumerator();

         while(var3.MoveNext()) {
            fW var2 = (fW)var3.Current;
            // PORT_TODO: var2.Write(var1);
         }
      } finally {
         // PORT_TODO: var1.Close();
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

         var2 = new FileInfo(System.IO.Path.Combine((this.lX).ToString(), (var3).ToString()));
         var1 &= var2.Exists;
      } while(!var1);

      if ((var2.Create()) == null) {
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
      Matcher var2 = lV.matcher(var1);
      return !var2.matches() ? -1 : int.Parse(var2.group(1));
   }

   public static int an(int var0) {
      return 2147418112 & var0 | (3584 & var0) >> 9;
   }

   public static bool h(FileInfo var0) {
      // PORT_TODO: FileInfo[] var1 = var0.GetFiles();
      if (true) { // PORT_TODO: original condition had errors
         // PORT_TODO: FileInfo[] var5 = var1;
         // PORT_TODO: int var4 = var1.Length;

         // PORT_TODO: for(int var3 = 0; var3 < var4; ++var3) {
      // PORT_TODO: int var4 = 0; // PORT_TODO: stub declaration
      // PORT_TODO: object var5 = null; // PORT_TODO: stub declaration
            // PORT_TODO: FileInfo var2 = var5[var3];
            // PORT_TODO: h(var2);
            // PORT_TODO: return default;
         // PORT_TODO: }
      }

      // PORT_TODO: return var0.Delete();
      return false;
   }

   public static Stream a(Stream var0, int var1) {
      try {
         bool var2 = true;
         if (true) { // PORT_TODO: original condition had errors
            // PORT_TODO: var0 = new BufferedInputStream((Stream)var0);
         }

         // PORT_TODO: ((Stream)var0).mark(lA.Length);
         // PORT_TODO: byte[] var3 = new byte[lA.Length];
         // PORT_TODO: hk.readFully((Stream)var0, var3);

         if (false) { // PORT_TODO: original loop had errors
            // PORT_TODO: if (var3[var4] != lA[var4]) {
      // PORT_TODO: object var4 = null; // PORT_TODO: stub declaration
               // PORT_TODO: var2 = false;
               // PORT_TODO: break;
            // PORT_TODO: }
         }

         if (var2) {
            return new hm((Stream)var0);
         } else {
            // PORT_TODO: ((Stream)var0).reset();
            byte[] var7 = new byte[16];
            ((Stream)var0).mark(var7.Length);
            hk.readFully((Stream)var0, var7);
            if ((255 & var7[0]) == 229 && (255 & var7[1]) == 161 && (255 & var7[2]) == 237 && (255 & var7[3]) == 254) {
               return new gX((Stream)var0, var7);
            } else {
               // PORT_TODO: ((Stream)var0).reset();
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
   public static string a(fT var0) {
      return var0.ct();
   }
   public static fY[] b(fT var0) {
      return var0.mE;
   }
   public static Pattern cu() {
      return lW;
   }
   public static Pattern cl() {
      return mJ;
   }
   public static int ao(int var0) {
      return an(var0);
   }
   public static List<object> c(fT var0) {
      return var0.mI;
   }
   public static FileInfo d(fT var0) {
      return var0.lX;
   }
   public static bool i(FileInfo var0) {
      return h(var0);
   }
   public static fW a(fT var0, string var1) {
      return var0.Z(var1);
   }
   public static Stream b(Stream var0, int var1) {
      return a(var0, var1);
   }
   public static int cv() {
      return mM;
   }
   public static int cw() {
      return mL;
   }
   public static int cx() {
      return mK;
   }
   public static byte[] cy() {
      // PORT_TODO: return lA;
      return null;
   }
   public static void e(fT var0) {
      var0.cs();
   }
}



}
