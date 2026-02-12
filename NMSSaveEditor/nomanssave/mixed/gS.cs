using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class gS {
   private static byte[] lA = new byte[]{78, 77, 83, 66};
   private static byte[] rS = new byte[]{50, -99, -78, -55, 92, 88, -34, 74, -57, 17, 57, -108, -94, 127, 97, -79};

   private static double[] a(eY var0, string var1) {
      eV var2 = var0.d(var1);
      if (var2.Count != 3) {
         throw new Exception("Invalid " + var1 + " coordinates");
      } else {
         return new double[]{var2.aa(0), var2.aa(1), var2.aa(2)};
      }
   }

   private static void a(eY var0, string var1, double[] var2) {
      var0.b(var1, (object)(new eV(new object[]{new Double(Double.isNaN(var2[0]) ? 0.0D : var2[0]), new Double(Double.isNaN(var2[1]) ? 0.0D : var2[1]), new Double(Double.isNaN(var2[2]) ? 0.0D : var2[2])})));
   }

   public static void d(eY var0, FileInfo var1) {
      a(var0, Collections.emptyMap(), var1);
   }

   public static void a(eY var0, Dictionary<object, object> var1, FileInfo var2) {
      int var3 = var0.J("BaseVersion");
      eV var4 = var0.d("Objects").bA();
      if (var3 < 3) {
         double[] var5 = a(var0, "Position");
         double[] var6 = a(var0, "Forward");
         gT var7 = new gT(var5, var6);

         for(int var8 = 0; var8 < var4.Count; ++var8) {
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
      object var22 = new FileStream(var2);

      try {
         ((Stream)var22).Write(lA);
         ((Stream)var22).Write(new byte[]{0, 5, 0, 0});
         ((Stream)var22).Write(var19);
         var22 = new CipherOutputStream((Stream)var22, var21);
         ((Stream)var22).Write(new byte[]{84, 82, 85, 69});
         hk.a((Stream)var22, var17);
         byte[] var23 = fj.b(var4);
         hk.a((Stream)var22, var23.Length);
         ((Stream)var22).Write(var23);
         IEnumerator var13 = var1.entrySet().GetEnumerator();

         while(var13.MoveNext()) {
            Entry var24 = (Entry)var13.Current;
            var23 = ((string)var24.getKey()).GetBytes();
            if (var23.Length <= 255) {
               ((Stream)var22).Write(var23.Length);
               ((Stream)var22).Write(var23);
               var23 = fj.j(var24.getValue());
               hk.a((Stream)var22, var23.Length);
               ((Stream)var22).Write(var23);
            }
         }

         ((Stream)var22).Flush();
      } finally {
         ((Stream)var22).Close();
      }
   }

   public static void e(eY var0, FileInfo var1) {
      b(var0, Collections.emptyMap(), var1);
   }

   public static void b(eY var0, Dictionary<object, object> var1, FileInfo var2) {
      object var4 = new FileStream(var2);

      int var3;
      eV var5;
      try {
         byte[] var6 = new byte[8];
         if (((Stream)var4).read(var6) != 8) {
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
            if (((Stream)var4).read(var7) != 16) {
               throw new IOException("short read");
            }

            SecretKeySpec var8 = new SecretKeySpec(rS, "AES");
            IvParameterSpec var9 = new IvParameterSpec(var7);
            Cipher var10 = Cipher.getInstance("AES/CBC/PKCS5Padding");
            var10.init(2, var8, var9);
            var4 = new CipherInputStream((Stream)var4, var10);
            if (((Stream)var4).read(var6, 0, 4) != 4) {
               throw new IOException("short read");
            }

            if (var6[0] != 84 || var6[1] != 82 || var6[2] != 85 || var6[3] != 69) {
               throw new IOException("invalid base file");
            }

            int var11;
            if (var3 < 5) {
               if ((var11 = ((Stream)var4).ReadByte()) < 0) {
                  throw new IOException("short read");
               }

               int var12;
               if ((var12 = ((Stream)var4).ReadByte()) < 0) {
                  throw new IOException("short read");
               }

               int var13;
               if ((var13 = ((Stream)var4).ReadByte()) < 0) {
                  throw new IOException("short read");
               }

               int var14;
               if ((var14 = ((Stream)var4).ReadByte()) < 0) {
                  throw new IOException("short read");
               }

               var0.b("UserData", (object)(var11 << 24 | var12 << 16 | var13 << 8 | var14));
               MemoryStream var15 = new MemoryStream();
               byte[] var16 = new byte[8096];

               int var17;
               while((var17 = ((Stream)var4).read(var16)) >= 0) {
                  var15.Write(var16, 0, var17);
               }

               var5 = ff.c(var15.toByteArray());
            } else {
               var0.b("UserData", (object)hk.readInt((Stream)var4));
               var11 = hk.readInt((Stream)var4);
               byte[] var26 = new byte[var11];
               hk.readFully((Stream)var4, var26);
               var5 = ff.c(var26);

               while((var11 = ((Stream)var4).ReadByte()) >= 0) {
                  var26 = new byte[var11];
                  hk.readFully((Stream)var4, var26);
                  string var28 = new string(var26);
                  var11 = hk.readInt((Stream)var4);
                  var26 = new byte[var11];
                  hk.readFully((Stream)var4, var26);
                  object var29 = ff.a(var26);
                  var1.Put(var28, var29);
               }
            }
            break;
         default:
            throw new IOException("invalid base file");
         }
      } finally {
         ((Stream)var4).Close();
      }

      long var21 = var0.K("LastUpdateTimestamp");

      int var22;
      eY var23;
      for(var22 = 0; var22 < var5.Count; ++var22) {
         var23 = var5.V(var22);
         var23.Put("Timestamp", new Long(var21));
      }

      if (var3 == 3) {
         for(var22 = 0; var22 < var5.Count; ++var22) {
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
         var5.Add(0, a("^BASE_FLAG", var21, var22, new double[]{0.0D, 0.0D, 0.0D}, new double[]{0.0D, 1.0D, 0.0D}, new double[]{0.0D, 0.0D, 1.0D}));
         var5.Add(1, a("^MAINROOM", var21, var22, new double[]{-3.0D, 0.0D, 3.0D}, new double[]{0.0D, 1.0D, 0.0D}, new double[]{0.0D, 0.0D, -1.0D}));
         var5.Add(2, a("^TELEPORTER", var21, var22, new double[]{0.0D, 0.0D, 6.0D}, new double[]{0.0D, 1.0D, 0.0D}, new double[]{-0.7071069478988647D, 0.0D, -0.7071067094802856D}));
         var5.Add(3, a("^BUILDDOOR", var21, var22, new double[]{-9.005859375D, 0.2421875D, 2.98828125D}, new double[]{0.0D, 1.0D, 0.0D}, new double[]{-1.0D, 0.0D, 0.0D}));
         var5.Add(4, a("^BUILDRAMP", var21, var22, new double[]{-10.724609375D, 0.296875D, 2.98828125D}, new double[]{-0.2588191032409668D, 0.9659259915351868D, 2.9802322387695312E-8D}, new double[]{-0.9659258127212524D, -0.2588191628456116D, -3.2782554626464844E-7D}));
         var5.Add(5, a("^BUILDWINDOW", var21, var22, new double[]{-7.248046875D, 0.5D, -1.25D}, new double[]{0.0D, 1.0D, 0.0D}, new double[]{-0.7071069478988647D, 0.0D, -0.7071067094802856D}));
         var5.Add(6, a("^BUILDWINDOW", var21, var22, new double[]{-7.248046875D, 0.5D, 7.25D}, new double[]{0.0D, 1.0D, 0.0D}, new double[]{-0.7071069478988647D, 0.0D, 0.7071067094802856D}));
         var5.Add(7, a("^BUILDWINDOW", var21, var22, new double[]{1.248046875D, 0.5D, -1.25D}, new double[]{0.0D, 1.0D, 0.0D}, new double[]{0.7071069478988647D, 0.0D, -0.7071067094802856D}));
      }

      if (var3 < 5) {
         var0.b("BaseVersion", (int)3);
      }

      var0.b("Objects", (object)var5);
   }

   private static void a(double[] var0) {
      double var1 = var0[0];
      var0[0] = -var0[2];
      var0[2] = var1;
   }

   private static eY a(string var0, long var1, int var3, double[] var4, double[] var5, double[] var6) {
      eY var7 = new eY();
      var7.Put("Timestamp", new Long(var1));
      var7.Put("ObjectID", var0);
      var7.Put("UserData", var3);
      var7.Put("Position", new eV(new object[]{new Double(var4[0]), new Double(var4[1]), new Double(var4[2])}));
      var7.Put("Up", new eV(new object[]{new Double(var5[0]), new Double(var5[1]), new Double(var5[2])}));
      var7.Put("At", new eV(new object[]{new Double(var6[0]), new Double(var6[1]), new Double(var6[2])}));
      var7.Put("Message", "");
      return var7;
   }
}

}
