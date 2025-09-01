package nomanssave;

public class hh {
   private static final long sx = 255L;
   private static final int sy = 12;
   private static final int sz = 96;
   private static final int sA = 48;
   private static final int sB = 12;
   private static final int sC = 192;
   private static final int sD = 96;
   private static final int sE = 24;
   private static final long sF = -2401053088876216593L;
   private final long sG;
   private final long sH;

   private static long a(byte[] var0, int var1) {
      return ((long)var0[var1 + 7] & 255L) << 56 | ((long)var0[var1 + 6] & 255L) << 48 | ((long)var0[var1 + 5] & 255L) << 40 | ((long)var0[var1 + 4] & 255L) << 32 | ((long)var0[var1 + 3] & 255L) << 24 | ((long)var0[var1 + 2] & 255L) << 16 | ((long)var0[var1 + 1] & 255L) << 8 | (long)var0[var1] & 255L;
   }

   private static long b(byte[] var0, int var1, int var2) {
      long var3 = 0L;
      switch(var2) {
      case 7:
         var3 += ((long)var0[var1 + 6] & 255L) << 48;
      case 6:
         var3 += ((long)var0[var1 + 5] & 255L) << 40;
      case 5:
         var3 += ((long)var0[var1 + 4] & 255L) << 32;
      case 4:
         var3 += ((long)var0[var1 + 3] & 255L) << 24;
      case 3:
         var3 += ((long)var0[var1 + 2] & 255L) << 16;
      case 2:
         var3 += ((long)var0[var1 + 1] & 255L) << 8;
      case 1:
         var3 += (long)var0[var1] & 255L;
      default:
         return var3;
      }
   }

   private static void a(byte[] var0, int var1, int var2, long[] var3) {
      long var4 = var3[0];
      long var6 = var3[1];
      long var8 = -2401053088876216593L;
      long var10 = -2401053088876216593L;
      int var12 = var2;

      int var13;
      for(var13 = var1; var12 >= 32; var12 -= 32) {
         var8 += a(var0, var13);
         var10 += a(var0, var13 + 8);
         var8 = var8 << 50 | var8 >>> 14;
         var8 += var10;
         var4 ^= var8;
         var10 = var10 << 52 | var10 >>> 12;
         var10 += var4;
         var6 ^= var10;
         var4 = var4 << 30 | var4 >>> 34;
         var4 += var6;
         var8 ^= var4;
         var6 = var6 << 41 | var6 >>> 23;
         var6 += var8;
         var10 ^= var6;
         var8 = var8 << 54 | var8 >>> 10;
         var8 += var10;
         var4 ^= var8;
         var10 = var10 << 48 | var10 >>> 16;
         var10 += var4;
         var6 ^= var10;
         var4 = var4 << 38 | var4 >>> 26;
         var4 += var6;
         var8 ^= var4;
         var6 = var6 << 37 | var6 >>> 27;
         var6 += var8;
         var10 ^= var6;
         var8 = var8 << 62 | var8 >>> 2;
         var8 += var10;
         var4 ^= var8;
         var10 = var10 << 34 | var10 >>> 30;
         var10 += var4;
         var6 ^= var10;
         var4 = var4 << 5 | var4 >>> 59;
         var4 += var6;
         var8 ^= var4;
         var6 = var6 << 36 | var6 >>> 28;
         var6 += var8;
         var10 ^= var6;
         var4 += a(var0, var13 + 16);
         var6 += a(var0, var13 + 24);
         var13 += 32;
      }

      if (var12 >= 16) {
         var8 += a(var0, var13);
         var10 += a(var0, var13 + 8);
         var13 += 16;
         var12 -= 16;
         var8 = var8 << 50 | var8 >>> 14;
         var8 += var10;
         var4 ^= var8;
         var10 = var10 << 52 | var10 >>> 12;
         var10 += var4;
         var6 ^= var10;
         var4 = var4 << 30 | var4 >>> 34;
         var4 += var6;
         var8 ^= var4;
         var6 = var6 << 41 | var6 >>> 23;
         var6 += var8;
         var10 ^= var6;
         var8 = var8 << 54 | var8 >>> 10;
         var8 += var10;
         var4 ^= var8;
         var10 = var10 << 48 | var10 >>> 16;
         var10 += var4;
         var6 ^= var10;
         var4 = var4 << 38 | var4 >>> 26;
         var4 += var6;
         var8 ^= var4;
         var6 = var6 << 37 | var6 >>> 27;
         var6 += var8;
         var10 ^= var6;
         var8 = var8 << 62 | var8 >>> 2;
         var8 += var10;
         var4 ^= var8;
         var10 = var10 << 34 | var10 >>> 30;
         var10 += var4;
         var6 ^= var10;
         var4 = var4 << 5 | var4 >>> 59;
         var4 += var6;
         var8 ^= var4;
         var6 = var6 << 36 | var6 >>> 28;
         var6 += var8;
         var10 ^= var6;
      }

      var10 += (long)var2 << 56;
      if (var12 >= 8) {
         var8 += a(var0, var13);
         var13 += 8;
         var12 -= 8;
         if (var12 > 0) {
            var10 += b(var0, var13, var12);
         }
      } else if (var12 > 0) {
         var8 += b(var0, var13, var12);
      } else {
         var8 += -2401053088876216593L;
         var10 += -2401053088876216593L;
      }

      var10 ^= var8;
      var8 = var8 << 15 | var8 >>> 49;
      var10 += var8;
      var4 ^= var10;
      var10 = var10 << 52 | var10 >>> 12;
      var4 += var10;
      var6 ^= var4;
      var4 = var4 << 26 | var4 >>> 38;
      var6 += var4;
      var8 ^= var6;
      var6 = var6 << 51 | var6 >>> 13;
      var8 += var6;
      var10 ^= var8;
      var8 = var8 << 28 | var8 >>> 36;
      var10 += var8;
      var4 ^= var10;
      var10 = var10 << 9 | var10 >>> 55;
      var4 += var10;
      var6 ^= var4;
      var4 = var4 << 47 | var4 >>> 17;
      var6 += var4;
      var8 ^= var6;
      var6 = var6 << 54 | var6 >>> 10;
      var8 += var6;
      var10 ^= var8;
      var8 = var8 << 32 | var8 >>> 32;
      var10 += var8;
      var4 ^= var10;
      var10 = var10 << 25 | var10 >>> 39;
      var4 += var10;
      var6 ^= var4;
      var4 = var4 << 63 | var4 >>> 1;
      var6 += var4;
      var3[0] = var4;
      var3[1] = var6;
   }

   public static long b(byte[] var0, int var1, int var2, long[] var3) {
      if (var2 < 192) {
         a(var0, var1, var2, var3);
         return var3[0];
      } else {
         long var10;
         long var16;
         long var22;
         long var4 = var10 = var16 = var22 = var3[0];
         long var12;
         long var18;
         long var24;
         long var6 = var12 = var18 = var24 = var3[1];
         long var26 = -2401053088876216593L;
         long var20 = -2401053088876216593L;
         long var14 = -2401053088876216593L;
         long var8 = -2401053088876216593L;
         int var28 = var2;

         int var29;
         for(var29 = var1; var28 >= 96; var29 += 96) {
            var4 += a(var0, var29);
            var8 ^= var24;
            var26 ^= var4;
            var4 = var4 << 11 | var4 >>> 53;
            var26 += var6;
            var6 += a(var0, var29 + 8);
            var10 ^= var26;
            var4 ^= var6;
            var6 = var6 << 32 | var6 >>> 32;
            var4 += var8;
            var8 += a(var0, var29 + 16);
            var12 ^= var4;
            var6 ^= var8;
            var8 = var8 << 43 | var8 >>> 21;
            var6 += var10;
            var10 += a(var0, var29 + 24);
            var14 ^= var6;
            var8 ^= var10;
            var10 = var10 << 31 | var10 >>> 33;
            var8 += var12;
            var12 += a(var0, var29 + 32);
            var16 ^= var8;
            var10 ^= var12;
            var12 = var12 << 17 | var12 >>> 47;
            var10 += var14;
            var14 += a(var0, var29 + 40);
            var18 ^= var10;
            var12 ^= var14;
            var14 = var14 << 28 | var14 >>> 36;
            var12 += var16;
            var16 += a(var0, var29 + 48);
            var20 ^= var12;
            var14 ^= var16;
            var16 = var16 << 39 | var16 >>> 25;
            var14 += var18;
            var18 += a(var0, var29 + 56);
            var22 ^= var14;
            var16 ^= var18;
            var18 = var18 << 57 | var18 >>> 7;
            var16 += var20;
            var20 += a(var0, var29 + 64);
            var24 ^= var16;
            var18 ^= var20;
            var20 = var20 << 55 | var20 >>> 9;
            var18 += var22;
            var22 += a(var0, var29 + 72);
            var26 ^= var18;
            var20 ^= var22;
            var22 = var22 << 54 | var22 >>> 10;
            var20 += var24;
            var24 += a(var0, var29 + 80);
            var4 ^= var20;
            var22 ^= var24;
            var24 = var24 << 22 | var24 >>> 42;
            var22 += var26;
            var26 += a(var0, var29 + 88);
            var6 ^= var22;
            var24 ^= var26;
            var26 = var26 << 46 | var26 >>> 18;
            var24 += var4;
            var28 -= 96;
         }

         int var30 = var28 & 7;
         int var31 = var28 >>> 3;
         if (var30 > 0) {
            long var32 = b(var0, var29 + (var31 << 3), var30);
            switch(var31) {
            case 0:
               var4 += var32;
               break;
            case 1:
               var6 += var32;
               break;
            case 2:
               var8 += var32;
               break;
            case 3:
               var10 += var32;
               break;
            case 4:
               var12 += var32;
               break;
            case 5:
               var14 += var32;
               break;
            case 6:
               var16 += var32;
               break;
            case 7:
               var18 += var32;
               break;
            case 8:
               var20 += var32;
               break;
            case 9:
               var22 += var32;
               break;
            case 10:
               var24 += var32;
               break;
            case 11:
               var26 += var32;
            }
         }

         switch(var31) {
         case 11:
            var24 += a(var0, var29 + 80);
         case 10:
            var22 += a(var0, var29 + 72);
         case 9:
            var20 += a(var0, var29 + 64);
         case 8:
            var18 += a(var0, var29 + 56);
         case 7:
            var16 += a(var0, var29 + 48);
         case 6:
            var14 += a(var0, var29 + 40);
         case 5:
            var12 += a(var0, var29 + 32);
         case 4:
            var10 += a(var0, var29 + 24);
         case 3:
            var8 += a(var0, var29 + 16);
         case 2:
            var6 += a(var0, var29 + 8);
         case 1:
            var4 += a(var0, var29);
         default:
            var26 += (long)var28 << 56;

            for(int var34 = 0; var34 < 3; ++var34) {
               var26 += var6;
               var8 ^= var26;
               var6 = var6 << 44 | var6 >>> 20;
               var4 += var8;
               var10 ^= var4;
               var8 = var8 << 15 | var8 >>> 49;
               var6 += var10;
               var12 ^= var6;
               var10 = var10 << 34 | var10 >>> 30;
               var8 += var12;
               var14 ^= var8;
               var12 = var12 << 21 | var12 >>> 43;
               var10 += var14;
               var16 ^= var10;
               var14 = var14 << 38 | var14 >>> 26;
               var12 += var16;
               var18 ^= var12;
               var16 = var16 << 33 | var16 >>> 31;
               var14 += var18;
               var20 ^= var14;
               var18 = var18 << 10 | var18 >>> 54;
               var16 += var20;
               var22 ^= var16;
               var20 = var20 << 13 | var20 >>> 51;
               var18 += var22;
               var24 ^= var18;
               var22 = var22 << 38 | var22 >>> 26;
               var20 += var24;
               var26 ^= var20;
               var24 = var24 << 53 | var24 >>> 11;
               var22 += var26;
               var4 ^= var22;
               var26 = var26 << 42 | var26 >>> 22;
               var24 += var4;
               var6 ^= var24;
               var4 = var4 << 54 | var4 >>> 10;
            }

            var3[0] = var4;
            var3[1] = var6;
            return var4;
         }
      }
   }

   private static long a(CharSequence var0, int var1) {
      return (long)var0.charAt(var1 + 3) << 48 | (long)var0.charAt(var1 + 2) << 32 | (long)var0.charAt(var1 + 1) << 16 | (long)var0.charAt(var1);
   }

   private static long a(CharSequence var0, int var1, int var2) {
      long var3 = 0L;
      switch(var2) {
      case 3:
         var3 += (long)var0.charAt(var1 + 2) << 32;
      case 2:
         var3 += (long)var0.charAt(var1 + 1) << 16;
      case 1:
         var3 += (long)var0.charAt(var1);
      default:
         return var3;
      }
   }

   private static void a(CharSequence var0, int var1, int var2, long[] var3) {
      long var4 = var3[0];
      long var6 = var3[1];
      long var8 = -2401053088876216593L;
      long var10 = -2401053088876216593L;
      int var12 = var2;

      int var13;
      for(var13 = var1; var12 >= 16; var12 -= 16) {
         var8 += a(var0, var13);
         var10 += a(var0, var13 + 4);
         var8 = var8 << 50 | var8 >>> 14;
         var8 += var10;
         var4 ^= var8;
         var10 = var10 << 52 | var10 >>> 12;
         var10 += var4;
         var6 ^= var10;
         var4 = var4 << 30 | var4 >>> 34;
         var4 += var6;
         var8 ^= var4;
         var6 = var6 << 41 | var6 >>> 23;
         var6 += var8;
         var10 ^= var6;
         var8 = var8 << 54 | var8 >>> 10;
         var8 += var10;
         var4 ^= var8;
         var10 = var10 << 48 | var10 >>> 16;
         var10 += var4;
         var6 ^= var10;
         var4 = var4 << 38 | var4 >>> 26;
         var4 += var6;
         var8 ^= var4;
         var6 = var6 << 37 | var6 >>> 27;
         var6 += var8;
         var10 ^= var6;
         var8 = var8 << 62 | var8 >>> 2;
         var8 += var10;
         var4 ^= var8;
         var10 = var10 << 34 | var10 >>> 30;
         var10 += var4;
         var6 ^= var10;
         var4 = var4 << 5 | var4 >>> 59;
         var4 += var6;
         var8 ^= var4;
         var6 = var6 << 36 | var6 >>> 28;
         var6 += var8;
         var10 ^= var6;
         var4 += a(var0, var13 + 8);
         var6 += a(var0, var13 + 12);
         var13 += 16;
      }

      if (var12 >= 8) {
         var8 += a(var0, var13);
         var10 += a(var0, var13 + 4);
         var13 += 8;
         var12 -= 8;
         var8 = var8 << 50 | var8 >>> 14;
         var8 += var10;
         var4 ^= var8;
         var10 = var10 << 52 | var10 >>> 12;
         var10 += var4;
         var6 ^= var10;
         var4 = var4 << 30 | var4 >>> 34;
         var4 += var6;
         var8 ^= var4;
         var6 = var6 << 41 | var6 >>> 23;
         var6 += var8;
         var10 ^= var6;
         var8 = var8 << 54 | var8 >>> 10;
         var8 += var10;
         var4 ^= var8;
         var10 = var10 << 48 | var10 >>> 16;
         var10 += var4;
         var6 ^= var10;
         var4 = var4 << 38 | var4 >>> 26;
         var4 += var6;
         var8 ^= var4;
         var6 = var6 << 37 | var6 >>> 27;
         var6 += var8;
         var10 ^= var6;
         var8 = var8 << 62 | var8 >>> 2;
         var8 += var10;
         var4 ^= var8;
         var10 = var10 << 34 | var10 >>> 30;
         var10 += var4;
         var6 ^= var10;
         var4 = var4 << 5 | var4 >>> 59;
         var4 += var6;
         var8 ^= var4;
         var6 = var6 << 36 | var6 >>> 28;
         var6 += var8;
         var10 ^= var6;
      }

      var10 += (long)(var2 << 1) << 56;
      if (var12 >= 4) {
         var8 += a(var0, var13);
         var13 += 4;
         var12 -= 4;
         if (var12 > 0) {
            var10 += a(var0, var13, var12);
         }
      } else if (var12 > 0) {
         var8 += a(var0, var13, var12);
      } else {
         var8 += -2401053088876216593L;
         var10 += -2401053088876216593L;
      }

      var10 ^= var8;
      var8 = var8 << 15 | var8 >>> 49;
      var10 += var8;
      var4 ^= var10;
      var10 = var10 << 52 | var10 >>> 12;
      var4 += var10;
      var6 ^= var4;
      var4 = var4 << 26 | var4 >>> 38;
      var6 += var4;
      var8 ^= var6;
      var6 = var6 << 51 | var6 >>> 13;
      var8 += var6;
      var10 ^= var8;
      var8 = var8 << 28 | var8 >>> 36;
      var10 += var8;
      var4 ^= var10;
      var10 = var10 << 9 | var10 >>> 55;
      var4 += var10;
      var6 ^= var4;
      var4 = var4 << 47 | var4 >>> 17;
      var6 += var4;
      var8 ^= var6;
      var6 = var6 << 54 | var6 >>> 10;
      var8 += var6;
      var10 ^= var8;
      var8 = var8 << 32 | var8 >>> 32;
      var10 += var8;
      var4 ^= var10;
      var10 = var10 << 25 | var10 >>> 39;
      var4 += var10;
      var6 ^= var4;
      var4 = var4 << 63 | var4 >>> 1;
      var6 += var4;
      var3[0] = var4;
      var3[1] = var6;
   }

   public static long b(CharSequence var0, int var1, int var2, long[] var3) {
      if (var2 < 96) {
         a(var0, var1, var2, var3);
         return var3[0];
      } else {
         long var10;
         long var16;
         long var22;
         long var4 = var10 = var16 = var22 = var3[0];
         long var12;
         long var18;
         long var24;
         long var6 = var12 = var18 = var24 = var3[1];
         long var26 = -2401053088876216593L;
         long var20 = -2401053088876216593L;
         long var14 = -2401053088876216593L;
         long var8 = -2401053088876216593L;
         int var28 = var2;

         int var29;
         for(var29 = var1; var28 >= 48; var29 += 48) {
            var4 += a(var0, var29);
            var8 ^= var24;
            var26 ^= var4;
            var4 = var4 << 11 | var4 >>> 53;
            var26 += var6;
            var6 += a(var0, var29 + 4);
            var10 ^= var26;
            var4 ^= var6;
            var6 = var6 << 32 | var6 >>> 32;
            var4 += var8;
            var8 += a(var0, var29 + 8);
            var12 ^= var4;
            var6 ^= var8;
            var8 = var8 << 43 | var8 >>> 21;
            var6 += var10;
            var10 += a(var0, var29 + 12);
            var14 ^= var6;
            var8 ^= var10;
            var10 = var10 << 31 | var10 >>> 33;
            var8 += var12;
            var12 += a(var0, var29 + 16);
            var16 ^= var8;
            var10 ^= var12;
            var12 = var12 << 17 | var12 >>> 47;
            var10 += var14;
            var14 += a(var0, var29 + 20);
            var18 ^= var10;
            var12 ^= var14;
            var14 = var14 << 28 | var14 >>> 36;
            var12 += var16;
            var16 += a(var0, var29 + 24);
            var20 ^= var12;
            var14 ^= var16;
            var16 = var16 << 39 | var16 >>> 25;
            var14 += var18;
            var18 += a(var0, var29 + 28);
            var22 ^= var14;
            var16 ^= var18;
            var18 = var18 << 57 | var18 >>> 7;
            var16 += var20;
            var20 += a(var0, var29 + 32);
            var24 ^= var16;
            var18 ^= var20;
            var20 = var20 << 55 | var20 >>> 9;
            var18 += var22;
            var22 += a(var0, var29 + 36);
            var26 ^= var18;
            var20 ^= var22;
            var22 = var22 << 54 | var22 >>> 10;
            var20 += var24;
            var24 += a(var0, var29 + 40);
            var4 ^= var20;
            var22 ^= var24;
            var24 = var24 << 22 | var24 >>> 42;
            var22 += var26;
            var26 += a(var0, var29 + 44);
            var6 ^= var22;
            var24 ^= var26;
            var26 = var26 << 46 | var26 >>> 18;
            var24 += var4;
            var28 -= 48;
         }

         int var30 = var28 & 3;
         int var31 = var28 >> 2;
         if (var30 > 0) {
            long var32 = a(var0, var29 + (var31 << 2), var30);
            switch(var31) {
            case 0:
               var4 += var32;
               break;
            case 1:
               var6 += var32;
               break;
            case 2:
               var8 += var32;
               break;
            case 3:
               var10 += var32;
               break;
            case 4:
               var12 += var32;
               break;
            case 5:
               var14 += var32;
               break;
            case 6:
               var16 += var32;
               break;
            case 7:
               var18 += var32;
               break;
            case 8:
               var20 += var32;
               break;
            case 9:
               var22 += var32;
               break;
            case 10:
               var24 += var32;
               break;
            case 11:
               var26 += var32;
            }
         }

         switch(var31) {
         case 11:
            var24 += a(var0, var29 + 40);
         case 10:
            var22 += a(var0, var29 + 36);
         case 9:
            var20 += a(var0, var29 + 32);
         case 8:
            var18 += a(var0, var29 + 28);
         case 7:
            var16 += a(var0, var29 + 24);
         case 6:
            var14 += a(var0, var29 + 20);
         case 5:
            var12 += a(var0, var29 + 16);
         case 4:
            var10 += a(var0, var29 + 12);
         case 3:
            var8 += a(var0, var29 + 8);
         case 2:
            var6 += a(var0, var29 + 4);
         case 1:
            var4 += a(var0, var29);
         default:
            var26 += (long)var28 << 1 << 56;

            for(int var34 = 0; var34 < 3; ++var34) {
               var26 += var6;
               var8 ^= var26;
               var6 = var6 << 44 | var6 >>> 20;
               var4 += var8;
               var10 ^= var4;
               var8 = var8 << 15 | var8 >>> 49;
               var6 += var10;
               var12 ^= var6;
               var10 = var10 << 34 | var10 >>> 30;
               var8 += var12;
               var14 ^= var8;
               var12 = var12 << 21 | var12 >>> 43;
               var10 += var14;
               var16 ^= var10;
               var14 = var14 << 38 | var14 >>> 26;
               var12 += var16;
               var18 ^= var12;
               var16 = var16 << 33 | var16 >>> 31;
               var14 += var18;
               var20 ^= var14;
               var18 = var18 << 10 | var18 >>> 54;
               var16 += var20;
               var22 ^= var16;
               var20 = var20 << 13 | var20 >>> 51;
               var18 += var22;
               var24 ^= var18;
               var22 = var22 << 38 | var22 >>> 26;
               var20 += var24;
               var26 ^= var20;
               var24 = var24 << 53 | var24 >>> 11;
               var22 += var26;
               var4 ^= var22;
               var26 = var26 << 42 | var26 >>> 22;
               var24 += var4;
               var6 ^= var24;
               var4 = var4 << 54 | var4 >>> 10;
            }

            var3[0] = var4;
            var3[1] = var6;
            return var4;
         }
      }
   }

   private static void a(long[] var0, int var1, int var2, long[] var3) {
      long var4 = var3[0];
      long var6 = var3[1];
      long var8 = -2401053088876216593L;
      long var10 = -2401053088876216593L;
      int var12 = var2;

      int var13;
      for(var13 = var1; var12 >= 4; var12 -= 4) {
         var8 += var0[var13];
         var10 += var0[var13 + 1];
         var8 = var8 << 50 | var8 >>> 14;
         var8 += var10;
         var4 ^= var8;
         var10 = var10 << 52 | var10 >>> 12;
         var10 += var4;
         var6 ^= var10;
         var4 = var4 << 30 | var4 >>> 34;
         var4 += var6;
         var8 ^= var4;
         var6 = var6 << 41 | var6 >>> 23;
         var6 += var8;
         var10 ^= var6;
         var8 = var8 << 54 | var8 >>> 10;
         var8 += var10;
         var4 ^= var8;
         var10 = var10 << 48 | var10 >>> 16;
         var10 += var4;
         var6 ^= var10;
         var4 = var4 << 38 | var4 >>> 26;
         var4 += var6;
         var8 ^= var4;
         var6 = var6 << 37 | var6 >>> 27;
         var6 += var8;
         var10 ^= var6;
         var8 = var8 << 62 | var8 >>> 2;
         var8 += var10;
         var4 ^= var8;
         var10 = var10 << 34 | var10 >>> 30;
         var10 += var4;
         var6 ^= var10;
         var4 = var4 << 5 | var4 >>> 59;
         var4 += var6;
         var8 ^= var4;
         var6 = var6 << 36 | var6 >>> 28;
         var6 += var8;
         var10 ^= var6;
         var4 += var0[var13 + 2];
         var6 += var0[var13 + 3];
         var13 += 4;
      }

      if (var12 >= 2) {
         var8 += var0[var13];
         var10 += var0[var13 + 1];
         var13 += 2;
         var12 -= 2;
         var8 = var8 << 50 | var8 >>> 14;
         var8 += var10;
         var4 ^= var8;
         var10 = var10 << 52 | var10 >>> 12;
         var10 += var4;
         var6 ^= var10;
         var4 = var4 << 30 | var4 >>> 34;
         var4 += var6;
         var8 ^= var4;
         var6 = var6 << 41 | var6 >>> 23;
         var6 += var8;
         var10 ^= var6;
         var8 = var8 << 54 | var8 >>> 10;
         var8 += var10;
         var4 ^= var8;
         var10 = var10 << 48 | var10 >>> 16;
         var10 += var4;
         var6 ^= var10;
         var4 = var4 << 38 | var4 >>> 26;
         var4 += var6;
         var8 ^= var4;
         var6 = var6 << 37 | var6 >>> 27;
         var6 += var8;
         var10 ^= var6;
         var8 = var8 << 62 | var8 >>> 2;
         var8 += var10;
         var4 ^= var8;
         var10 = var10 << 34 | var10 >>> 30;
         var10 += var4;
         var6 ^= var10;
         var4 = var4 << 5 | var4 >>> 59;
         var4 += var6;
         var8 ^= var4;
         var6 = var6 << 36 | var6 >>> 28;
         var6 += var8;
         var10 ^= var6;
      }

      var10 += (long)(var2 << 3) << 56;
      if (var12 > 0) {
         var8 += var0[var13];
      } else {
         var8 += -2401053088876216593L;
         var10 += -2401053088876216593L;
      }

      var10 ^= var8;
      var8 = var8 << 15 | var8 >>> 49;
      var10 += var8;
      var4 ^= var10;
      var10 = var10 << 52 | var10 >>> 12;
      var4 += var10;
      var6 ^= var4;
      var4 = var4 << 26 | var4 >>> 38;
      var6 += var4;
      var8 ^= var6;
      var6 = var6 << 51 | var6 >>> 13;
      var8 += var6;
      var10 ^= var8;
      var8 = var8 << 28 | var8 >>> 36;
      var10 += var8;
      var4 ^= var10;
      var10 = var10 << 9 | var10 >>> 55;
      var4 += var10;
      var6 ^= var4;
      var4 = var4 << 47 | var4 >>> 17;
      var6 += var4;
      var8 ^= var6;
      var6 = var6 << 54 | var6 >>> 10;
      var8 += var6;
      var10 ^= var8;
      var8 = var8 << 32 | var8 >>> 32;
      var10 += var8;
      var4 ^= var10;
      var10 = var10 << 25 | var10 >>> 39;
      var4 += var10;
      var6 ^= var4;
      var4 = var4 << 63 | var4 >>> 1;
      var6 += var4;
      var3[0] = var4;
      var3[1] = var6;
   }

   public static long b(long[] var0, int var1, int var2, long[] var3) {
      if (var2 < 24) {
         a(var0, var1, var2, var3);
         return var3[0];
      } else {
         long var10;
         long var16;
         long var22;
         long var4 = var10 = var16 = var22 = var3[0];
         long var12;
         long var18;
         long var24;
         long var6 = var12 = var18 = var24 = var3[1];
         long var26 = -2401053088876216593L;
         long var20 = -2401053088876216593L;
         long var14 = -2401053088876216593L;
         long var8 = -2401053088876216593L;
         int var28 = var1;

         int var29;
         for(var29 = var2; var29 >= 12; var29 -= 12) {
            var4 += var0[var28];
            var8 ^= var24;
            var26 ^= var4;
            var4 = var4 << 11 | var4 >>> 53;
            var26 += var6;
            var6 += var0[var28 + 1];
            var10 ^= var26;
            var4 ^= var6;
            var6 = var6 << 32 | var6 >>> 32;
            var4 += var8;
            var8 += var0[var28 + 2];
            var12 ^= var4;
            var6 ^= var8;
            var8 = var8 << 43 | var8 >>> 21;
            var6 += var10;
            var10 += var0[var28 + 3];
            var14 ^= var6;
            var8 ^= var10;
            var10 = var10 << 31 | var10 >>> 33;
            var8 += var12;
            var12 += var0[var28 + 4];
            var16 ^= var8;
            var10 ^= var12;
            var12 = var12 << 17 | var12 >>> 47;
            var10 += var14;
            var14 += var0[var28 + 5];
            var18 ^= var10;
            var12 ^= var14;
            var14 = var14 << 28 | var14 >>> 36;
            var12 += var16;
            var16 += var0[var28 + 6];
            var20 ^= var12;
            var14 ^= var16;
            var16 = var16 << 39 | var16 >>> 25;
            var14 += var18;
            var18 += var0[var28 + 7];
            var22 ^= var14;
            var16 ^= var18;
            var18 = var18 << 57 | var18 >>> 7;
            var16 += var20;
            var20 += var0[var28 + 8];
            var24 ^= var16;
            var18 ^= var20;
            var20 = var20 << 55 | var20 >>> 9;
            var18 += var22;
            var22 += var0[var28 + 9];
            var26 ^= var18;
            var20 ^= var22;
            var22 = var22 << 54 | var22 >>> 10;
            var20 += var24;
            var24 += var0[var28 + 10];
            var4 ^= var20;
            var22 ^= var24;
            var24 = var24 << 22 | var24 >>> 42;
            var22 += var26;
            var26 += var0[var28 + 11];
            var6 ^= var22;
            var24 ^= var26;
            var26 = var26 << 46 | var26 >>> 18;
            var24 += var4;
            var28 += 12;
         }

         switch(var29) {
         case 11:
            var24 += var0[var28 + 10];
         case 10:
            var22 += var0[var28 + 9];
         case 9:
            var20 += var0[var28 + 8];
         case 8:
            var18 += var0[var28 + 7];
         case 7:
            var16 += var0[var28 + 6];
         case 6:
            var14 += var0[var28 + 5];
         case 5:
            var12 += var0[var28 + 4];
         case 4:
            var10 += var0[var28 + 3];
         case 3:
            var8 += var0[var28 + 2];
         case 2:
            var6 += var0[var28 + 1];
         case 1:
            var4 += var0[var28];
         default:
            var26 += (long)(var29 << 3) << 56;

            for(int var30 = 0; var30 < 3; ++var30) {
               var26 += var6;
               var8 ^= var26;
               var6 = var6 << 44 | var6 >>> 20;
               var4 += var8;
               var10 ^= var4;
               var8 = var8 << 15 | var8 >>> 49;
               var6 += var10;
               var12 ^= var6;
               var10 = var10 << 34 | var10 >>> 30;
               var8 += var12;
               var14 ^= var8;
               var12 = var12 << 21 | var12 >>> 43;
               var10 += var14;
               var16 ^= var10;
               var14 = var14 << 38 | var14 >>> 26;
               var12 += var16;
               var18 ^= var12;
               var16 = var16 << 33 | var16 >>> 31;
               var14 += var18;
               var20 ^= var14;
               var18 = var18 << 10 | var18 >>> 54;
               var16 += var20;
               var22 ^= var16;
               var20 = var20 << 13 | var20 >>> 51;
               var18 += var22;
               var24 ^= var18;
               var22 = var22 << 38 | var22 >>> 26;
               var20 += var24;
               var26 ^= var20;
               var24 = var24 << 53 | var24 >>> 11;
               var22 += var26;
               var4 ^= var22;
               var26 = var26 << 42 | var26 >>> 22;
               var24 += var4;
               var6 ^= var24;
               var4 = var4 << 54 | var4 >>> 10;
            }

            var3[0] = var4;
            var3[1] = var6;
            return var4;
         }
      }
   }

   public hh() {
      this(0L, 0L);
   }

   public hh(long var1, long var3) {
      this.sG = var1;
      this.sH = var3;
   }

   public static long a(byte[] var0, long[] var1) {
      return b((byte[])var0, 0, var0.length, var1);
   }

   public long[] c(byte[] var1, int var2, int var3) {
      long[] var4 = new long[]{this.sG, this.sH};
      b(var1, var2, var3, var4);
      return var4;
   }

   public long[] j(byte[] var1) {
      return this.c(var1, 0, var1.length);
   }

   public static long a(CharSequence var0, long[] var1) {
      return b((CharSequence)var0, 0, var0.length(), var1);
   }

   public long[] b(CharSequence var1, int var2, int var3) {
      long[] var4 = new long[]{this.sG, this.sH};
      b(var1, var2, var3, var4);
      return var4;
   }

   public long[] a(CharSequence var1) {
      return this.b((CharSequence)var1, 0, var1.length());
   }

   public static long a(long[] var0, long[] var1) {
      return b((long[])var0, 0, var0.length, var1);
   }

   public long[] b(long[] var1, int var2, int var3) {
      long[] var4 = new long[]{this.sG, this.sH};
      b(var1, var2, var3, var4);
      return var4;
   }

   public long[] b(long[] var1) {
      return this.b((long[])var1, 0, var1.length);
   }
}
