package nomanssave;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fT implements fq {
   private static final byte[] lA = "HGSAVEV2\u0000".getBytes();
   private static final Pattern lV = Pattern.compile("Slot(\\d+)((Auto)|(Manual))");
   private static final Pattern lW = Pattern.compile("wgsbackup(\\d*)\\.\\d*\\.zip");
   static final String mC = "containers.index";
   private final File lX;
   private final fR lE;
   private fU mD;
   private fY[] mE;
   private int header;
   private int lL;
   private String name;
   private int lM;
   private int lR;
   private int lS;
   private String mF;
   private int mG;
   private int mH;
   private List mI;
   private static final Pattern mJ = Pattern.compile("\"((?:<h0)|(?:CommonStateData))\":\\{\"((?:Pk4)|(?:SaveName))\":\"([^\"]+)\"");
   private static int mK = 1;
   private static int mL = 2;
   private static int mM = 3;

   fT(File var1, fR var2) {
      this.lX = var1.isDirectory() ? var1 : var1.getParentFile();
      this.lE = var2;
      this.cr();

      try {
         this.mD = new fU(this);
      } catch (FileNotFoundException var7) {
      } catch (IOException var8) {
         hc.a("Cannot read account data", var8);
      }

      this.mE = new fY[30];

      for(int var3 = 0; var3 < this.mE.length; ++var3) {
         try {
            this.mE[var3] = new fY(this, var3);
         } catch (FileNotFoundException var5) {
         } catch (IOException var6) {
            hc.a("Cannot read file data", var6);
         }
      }

      fl.a(this, this.lX);
   }

   protected void finalize() {
      fl.b(this);
   }

   public void X(String var1) {
      var1.equals("containers.index");
   }

   public File bS() {
      return this.lX;
   }

   private void cr() {
      hc.info("Reading Container Index");
      FileInputStream var1 = new FileInputStream(new File(this.lX, "containers.index"));

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

         this.mI = new ArrayList();

         for(int var3 = 0; var3 < var2; ++var3) {
            this.mI.add(new fW(this, var1));
         }

         if (var1.read() >= 0) {
            throw new IOException("Invalid footer");
         }
      } finally {
         var1.close();
      }

   }

   private void cs() {
      FileOutputStream var1 = new FileOutputStream(new File(this.lX, "containers.index"));

      try {
         hk.a(var1, this.header);
         hk.a(var1, this.mI.size());
         hk.a(var1, this.lL);
         gc.b(var1, this.name);
         hk.a(var1, this.lM);
         hk.a(var1, this.lR);
         hk.a(var1, this.lS);
         gc.b(var1, this.mF);
         hk.a(var1, this.mG);
         hk.a(var1, this.mH);
         Iterator var3 = this.mI.iterator();

         while(var3.hasNext()) {
            fW var2 = (fW)var3.next();
            var2.write(var1);
         }
      } finally {
         var1.close();
      }

   }

   private fW Z(String var1) {
      Iterator var3 = this.mI.iterator();

      while(var3.hasNext()) {
         fW var2 = (fW)var3.next();
         if (var2.name.equals(var1)) {
            return var2;
         }
      }

      throw new FileNotFoundException(var1);
   }

   private String ct() {
      boolean var1;
      File var2;
      String var3;
      do {
         var1 = true;
         var3 = gc.cA();

         fW var4;
         for(Iterator var5 = this.mI.iterator(); var5.hasNext(); var1 &= var4.mU.equals(var3)) {
            var4 = (fW)var5.next();
         }

         var2 = new File(this.lX, var3);
         var1 &= var2.exists();
      } while(!var1);

      if (!var2.mkdir()) {
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

   public int W(String var1) {
      Matcher var2 = lV.matcher(var1);
      return !var2.matches() ? -1 : Integer.parseInt(var2.group(1));
   }

   private static int an(int var0) {
      return 2147418112 & var0 | (3584 & var0) >> 9;
   }

   private static boolean h(File var0) {
      File[] var1 = var0.listFiles();
      if (var1 != null) {
         File[] var5 = var1;
         int var4 = var1.length;

         for(int var3 = 0; var3 < var4; ++var3) {
            File var2 = var5[var3];
            h(var2);
         }
      }

      return var0.delete();
   }

   private static InputStream a(InputStream var0, int var1) {
      try {
         boolean var2 = true;
         if (!((InputStream)var0).markSupported()) {
            var0 = new BufferedInputStream((InputStream)var0);
         }

         ((InputStream)var0).mark(lA.length);
         byte[] var3 = new byte[lA.length];
         hk.readFully((InputStream)var0, var3);

         for(int var4 = 0; var4 < lA.length; ++var4) {
            if (var3[var4] != lA[var4]) {
               var2 = false;
               break;
            }
         }

         if (var2) {
            return new hm((InputStream)var0);
         } else {
            ((InputStream)var0).reset();
            byte[] var7 = new byte[16];
            ((InputStream)var0).mark(var7.length);
            hk.readFully((InputStream)var0, var7);
            if ((255 & var7[0]) == 229 && (255 & var7[1]) == 161 && (255 & var7[2]) == 237 && (255 & var7[3]) == 254) {
               return new gX((InputStream)var0, var7);
            } else {
               ((InputStream)var0).reset();
               return new ha((InputStream)var0, var1);
            }
         }
      } catch (IOException var6) {
         try {
            ((InputStream)var0).close();
         } catch (IOException var5) {
         }

         throw var6;
      }
   }

   // $FF: synthetic method
   static String a(fT var0) {
      return var0.ct();
   }

   // $FF: synthetic method
   static fY[] b(fT var0) {
      return var0.mE;
   }

   // $FF: synthetic method
   static Pattern cu() {
      return lW;
   }

   // $FF: synthetic method
   static Pattern cl() {
      return mJ;
   }

   // $FF: synthetic method
   static int ao(int var0) {
      return an(var0);
   }

   // $FF: synthetic method
   static List c(fT var0) {
      return var0.mI;
   }

   // $FF: synthetic method
   static File d(fT var0) {
      return var0.lX;
   }

   // $FF: synthetic method
   static boolean i(File var0) {
      return h(var0);
   }

   // $FF: synthetic method
   static fW a(fT var0, String var1) {
      return var0.Z(var1);
   }

   // $FF: synthetic method
   static InputStream b(InputStream var0, int var1) {
      return a(var0, var1);
   }

   // $FF: synthetic method
   static int cv() {
      return mM;
   }

   // $FF: synthetic method
   static int cw() {
      return mL;
   }

   // $FF: synthetic method
   static int cx() {
      return mK;
   }

   // $FF: synthetic method
   static byte[] cy() {
      return lA;
   }

   // $FF: synthetic method
   static void e(fT var0) {
      var0.cs();
   }
}
