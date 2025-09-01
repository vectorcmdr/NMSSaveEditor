package nomanssave;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class gS {
   private static byte[] lA = new byte[]{78, 77, 83, 66};
   private static byte[] rS = new byte[]{50, -99, -78, -55, 92, 88, -34, 74, -57, 17, 57, -108, -94, 127, 97, -79};

   private static double[] a(eY var0, String var1) {
      eV var2 = var0.d(var1);
      if (var2.size() != 3) {
         throw new RuntimeException("Invalid " + var1 + " coordinates");
      } else {
         return new double[]{var2.aa(0), var2.aa(1), var2.aa(2)};
      }
   }

   private static void a(eY var0, String var1, double[] var2) {
      var0.b(var1, (Object)(new eV(new Object[]{new Double(Double.isNaN(var2[0]) ? 0.0D : var2[0]), new Double(Double.isNaN(var2[1]) ? 0.0D : var2[1]), new Double(Double.isNaN(var2[2]) ? 0.0D : var2[2])})));
   }

   public static void d(eY var0, File var1) {
      a(var0, Collections.emptyMap(), var1);
   }

   public static void a(eY var0, Map var1, File var2) {
      int var3 = var0.J("BaseVersion");
      eV var4 = var0.d("Objects").bA();
      if (var3 < 3) {
         double[] var5 = a(var0, "Position");
         double[] var6 = a(var0, "Forward");
         gT var7 = new gT(var5, var6);

         for(int var8 = 0; var8 < var4.size(); ++var8) {
            eY var9 = var4.V(var8);
            double[] var10 = a(var9, "Position");
            double[] var11 = a(var9, "Up");
            double[] var12 = a(var9, "At");
            a(var9, "Position", var7.d(var10));
            a(var9, "Up", var7.d(var11));
            a(var9, "At", var7.d(var12));
         }
      }

      int var17 = var0.J("UserData");
      SecretKeySpec var18 = new SecretKeySpec(rS, "AES");
      byte[] var19 = new byte[16];
      (new SecureRandom()).nextBytes(var19);
      IvParameterSpec var20 = new IvParameterSpec(var19);
      Cipher var21 = Cipher.getInstance("AES/CBC/PKCS5Padding");
      var21.init(1, var18, var20);
      Object var22 = new FileOutputStream(var2);

      try {
         ((OutputStream)var22).write(lA);
         ((OutputStream)var22).write(new byte[]{0, 5, 0, 0});
         ((OutputStream)var22).write(var19);
         var22 = new CipherOutputStream((OutputStream)var22, var21);
         ((OutputStream)var22).write(new byte[]{84, 82, 85, 69});
         hk.a((OutputStream)var22, var17);
         byte[] var23 = fj.b(var4);
         hk.a((OutputStream)var22, var23.length);
         ((OutputStream)var22).write(var23);
         Iterator var13 = var1.entrySet().iterator();

         while(var13.hasNext()) {
            Entry var24 = (Entry)var13.next();
            var23 = ((String)var24.getKey()).getBytes();
            if (var23.length <= 255) {
               ((OutputStream)var22).write(var23.length);
               ((OutputStream)var22).write(var23);
               var23 = fj.j(var24.getValue());
               hk.a((OutputStream)var22, var23.length);
               ((OutputStream)var22).write(var23);
            }
         }

         ((OutputStream)var22).flush();
      } finally {
         ((OutputStream)var22).close();
      }
   }

   public static void e(eY var0, File var1) {
      b(var0, Collections.emptyMap(), var1);
   }

   public static void b(eY var0, Map var1, File var2) {
      Object var4 = new FileInputStream(var2);

      int var3;
      eV var5;
      try {
         byte[] var6 = new byte[8];
         if (((InputStream)var4).read(var6) != 8) {
            throw new IOException("short read");
         }

         if (var6[0] != lA[0] || var6[1] != lA[1] || var6[2] != lA[2] || var6[3] != lA[3]) {
            throw new IOException("invalid base file");
         }

         var3 = (var6[4] & 255) << 8 | var6[5] & 255;
         switch(var3) {
         case 2:
            throw new IOException("unsupported base file");
         case 3:
         case 4:
         case 5:
            byte[] var7 = new byte[16];
            if (((InputStream)var4).read(var7) != 16) {
               throw new IOException("short read");
            }

            SecretKeySpec var8 = new SecretKeySpec(rS, "AES");
            IvParameterSpec var9 = new IvParameterSpec(var7);
            Cipher var10 = Cipher.getInstance("AES/CBC/PKCS5Padding");
            var10.init(2, var8, var9);
            var4 = new CipherInputStream((InputStream)var4, var10);
            if (((InputStream)var4).read(var6, 0, 4) != 4) {
               throw new IOException("short read");
            }

            if (var6[0] != 84 || var6[1] != 82 || var6[2] != 85 || var6[3] != 69) {
               throw new IOException("invalid base file");
            }

            int var11;
            if (var3 < 5) {
               if ((var11 = ((InputStream)var4).read()) < 0) {
                  throw new IOException("short read");
               }

               int var12;
               if ((var12 = ((InputStream)var4).read()) < 0) {
                  throw new IOException("short read");
               }

               int var13;
               if ((var13 = ((InputStream)var4).read()) < 0) {
                  throw new IOException("short read");
               }

               int var14;
               if ((var14 = ((InputStream)var4).read()) < 0) {
                  throw new IOException("short read");
               }

               var0.b("UserData", (Object)(var11 << 24 | var12 << 16 | var13 << 8 | var14));
               ByteArrayOutputStream var15 = new ByteArrayOutputStream();
               byte[] var16 = new byte[8096];

               int var17;
               while((var17 = ((InputStream)var4).read(var16)) >= 0) {
                  var15.write(var16, 0, var17);
               }

               var5 = ff.c(var15.toByteArray());
            } else {
               var0.b("UserData", (Object)hk.readInt((InputStream)var4));
               var11 = hk.readInt((InputStream)var4);
               byte[] var26 = new byte[var11];
               hk.readFully((InputStream)var4, var26);
               var5 = ff.c(var26);

               while((var11 = ((InputStream)var4).read()) >= 0) {
                  var26 = new byte[var11];
                  hk.readFully((InputStream)var4, var26);
                  String var28 = new String(var26);
                  var11 = hk.readInt((InputStream)var4);
                  var26 = new byte[var11];
                  hk.readFully((InputStream)var4, var26);
                  Object var29 = ff.a(var26);
                  var1.put(var28, var29);
               }
            }
            break;
         default:
            throw new IOException("invalid base file");
         }
      } finally {
         ((InputStream)var4).close();
      }

      long var21 = var0.K("LastUpdateTimestamp");

      int var22;
      eY var23;
      for(var22 = 0; var22 < var5.size(); ++var22) {
         var23 = var5.V(var22);
         var23.put("Timestamp", new Long(var21));
      }

      if (var3 == 3) {
         for(var22 = 0; var22 < var5.size(); ++var22) {
            var23 = var5.V(var22);
            double[] var24 = a(var23, "Position");
            var24[0] += 3.0D;
            var24[2] += 3.0D;
            a(var24);
            a(var23, "Position", var24);
            double[] var25 = a(var23, "Up");
            a(var25);
            a(var23, "Up", var25);
            double[] var27 = a(var23, "At");
            a(var27);
            a(var23, "At", var27);
         }

         var22 = var0.J("UserData");
         var5.add(0, a("^BASE_FLAG", var21, var22, new double[]{0.0D, 0.0D, 0.0D}, new double[]{0.0D, 1.0D, 0.0D}, new double[]{0.0D, 0.0D, 1.0D}));
         var5.add(1, a("^MAINROOM", var21, var22, new double[]{-3.0D, 0.0D, 3.0D}, new double[]{0.0D, 1.0D, 0.0D}, new double[]{0.0D, 0.0D, -1.0D}));
         var5.add(2, a("^TELEPORTER", var21, var22, new double[]{0.0D, 0.0D, 6.0D}, new double[]{0.0D, 1.0D, 0.0D}, new double[]{-0.7071069478988647D, 0.0D, -0.7071067094802856D}));
         var5.add(3, a("^BUILDDOOR", var21, var22, new double[]{-9.005859375D, 0.2421875D, 2.98828125D}, new double[]{0.0D, 1.0D, 0.0D}, new double[]{-1.0D, 0.0D, 0.0D}));
         var5.add(4, a("^BUILDRAMP", var21, var22, new double[]{-10.724609375D, 0.296875D, 2.98828125D}, new double[]{-0.2588191032409668D, 0.9659259915351868D, 2.9802322387695312E-8D}, new double[]{-0.9659258127212524D, -0.2588191628456116D, -3.2782554626464844E-7D}));
         var5.add(5, a("^BUILDWINDOW", var21, var22, new double[]{-7.248046875D, 0.5D, -1.25D}, new double[]{0.0D, 1.0D, 0.0D}, new double[]{-0.7071069478988647D, 0.0D, -0.7071067094802856D}));
         var5.add(6, a("^BUILDWINDOW", var21, var22, new double[]{-7.248046875D, 0.5D, 7.25D}, new double[]{0.0D, 1.0D, 0.0D}, new double[]{-0.7071069478988647D, 0.0D, 0.7071067094802856D}));
         var5.add(7, a("^BUILDWINDOW", var21, var22, new double[]{1.248046875D, 0.5D, -1.25D}, new double[]{0.0D, 1.0D, 0.0D}, new double[]{0.7071069478988647D, 0.0D, -0.7071067094802856D}));
      }

      if (var3 < 5) {
         var0.b("BaseVersion", (int)3);
      }

      var0.b("Objects", (Object)var5);
   }

   private static void a(double[] var0) {
      double var1 = var0[0];
      var0[0] = -var0[2];
      var0[2] = var1;
   }

   private static eY a(String var0, long var1, int var3, double[] var4, double[] var5, double[] var6) {
      eY var7 = new eY();
      var7.put("Timestamp", new Long(var1));
      var7.put("ObjectID", var0);
      var7.put("UserData", var3);
      var7.put("Position", new eV(new Object[]{new Double(var4[0]), new Double(var4[1]), new Double(var4[2])}));
      var7.put("Up", new eV(new Object[]{new Double(var5[0]), new Double(var5[1]), new Double(var5[2])}));
      var7.put("At", new eV(new Object[]{new Double(var6[0]), new Double(var6[1]), new Double(var6[2])}));
      var7.put("Message", "");
      return var7;
   }
}
