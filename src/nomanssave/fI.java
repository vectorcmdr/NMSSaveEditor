package nomanssave;

import java.io.IOException;

public class fI {
   private static final int mi = 2001;
   private static final int mj = 2002;
   private static final int mk = 2003;
   private static final int ml = 2004;
   private static final int mm = 6;
   private static final int mn = 2004;
   private static final int mo = 384;
   private final int mp;
   private final int lO;
   private int mq;
   private byte[] data;

   private static final boolean ai(int var0) {
      return var0 == 2001 || var0 == 2002 || var0 == 2003 || var0 == 2004;
   }

   private fI(int var1, int var2, int var3, byte[] var4) {
      this.mp = var1;
      this.lO = var2;
      this.mq = var3;
      this.data = var4;
   }

   public int cc() {
      return this.lO;
   }

   public int cd() {
      return this.mq;
   }

   public boolean ce() {
      if (this.mq == 2001) {
         return false;
      } else {
         if (this.data.length < 376) {
            byte[] var1 = new byte[376];
            System.arraycopy(this.data, 0, var1, 0, this.data.length);
            this.data = var1;
         }

         this.mq = 2004;
         return true;
      }
   }

   public byte[] cf() {
      return this.d(24, 32);
   }

   public void e(byte[] var1) {
      if (var1.length != 32) {
         throw new IllegalArgumentException("SHA-256 must be 32 bytes");
      } else {
         this.setBytes(24, var1);
      }
   }

   public byte[] cg() {
      return this.d(8, 16);
   }

   public void f(byte[] var1) {
      if (var1.length != 16) {
         throw new IllegalArgumentException("SpookyHash must be 16 bytes");
      } else {
         this.setBytes(8, var1);
      }
   }

   public int ch() {
      return this.getInt(56);
   }

   public void aj(int var1) {
      this.setInt(56, var1);
   }

   public int ci() {
      return this.getInt(60);
   }

   public void ak(int var1) {
      this.setInt(60, var1);
   }

   public int cj() {
      return this.getInt(76);
   }

   public void al(int var1) {
      this.setInt(76, var1);
   }

   public String ck() {
      switch(this.mq) {
      case 2002:
      case 2003:
      case 2004:
         return this.getString(88);
      default:
         return null;
      }
   }

   public void Y(String var1) {
      switch(this.mq) {
      case 2002:
      case 2003:
      case 2004:
         this.setString(216, var1);
         return;
      default:
      }
   }

   public String getDescription() {
      switch(this.mq) {
      case 2002:
      case 2003:
      case 2004:
         return this.getString(88);
      default:
         return null;
      }
   }

   public void setDescription(String var1) {
      switch(this.mq) {
      case 2002:
      case 2003:
      case 2004:
         this.setString(216, var1);
         return;
      default:
      }
   }

   public static fI am(int var0) {
      return new fI(6, var0, 2004, new byte[376]);
   }

   public static fI a(int var0, byte[] var1) {
      return a(var0, var1, 0, var1.length);
   }

   public static fI a(int var0, byte[] var1, int var2, int var3) {
      if (var3 >= 8 && var3 % 4 == 0) {
         int var4 = var3 == 104 ? 8 : 6;
         long[] var5 = a(var1, var2, var3);
         long var6 = 0L;

         int var8;
         for(var8 = 0; var8 < var4; ++var8) {
            var6 += 2654435769L;
            var6 &= 4294967295L;
         }

         var8 = var5.length - 1;
         long var9 = (long)(var0 + 2) ^ 337824652L;
         byte[] var11 = "NAESEVADNAYRTNRG".getBytes("US-ASCII");
         long[] var12 = g(var11);
         var12[0] = rotateLeft(var9, 13) * 5L + 3864292196L & 4294967295L;

         int var13;
         for(var13 = 0; var13 < var4; ++var13) {
            int var14 = (int)(var6 >>> 2 & 3L);
            long var15 = var5[0];
            int var17 = var8;
            long var18 = 0L;

            for(int var20 = var8; var20 > 0; --var20) {
               var18 = var15 >> 3 ^ (var5[var17 - 1] & 268435455L) << 4;
               var18 += var15 * 4L & 4294967295L ^ var5[var17 - 1] >> 5;
               var18 ^= (var5[var17 - 1] ^ var12[var20 & 3 ^ var14]) + (var15 ^ var6);
               var5[var17] = var5[var17] - var18 & 4294967295L;
               var15 = var5[var17--];
            }

            var18 = var15 >> 3 ^ (var5[var8] & 268435455L) << 4;
            var18 += var15 * 4L & 4294967295L ^ var5[var8] >> 5;
            var18 ^= (var5[var8] ^ var12[var14]) + (var15 ^ var6);
            var5[0] = var5[0] - var18 & 4294967295L;
            var6 += 1640531527L;
         }

         if (var5[0] != 4008636094L) {
            throw new IOException("Invalid metadata header: " + Long.toHexString(var5[0]));
         } else {
            var13 = (int)var5[1];
            if (!ai(var13)) {
               throw new IOException("Invalid or unsupported format in metadata header: " + Integer.toHexString(var13));
            } else {
               byte[] var21 = a((long[])var5, 2, var5.length - 2);
               return new fI(var4, var0, var13, var21);
            }
         }
      } else {
         throw new IOException("Invalid metadata length: " + var3);
      }
   }

   public byte[] encode() {
      long var1 = (long)(this.lO + 2) ^ 337824652L;
      byte[] var3 = "NAESEVADNAYRTNRG".getBytes("US-ASCII");
      long[] var4 = g(var3);
      var4[0] = rotateLeft(var1, 13) * 5L + 3864292196L & 4294967295L;
      long[] var5 = g(this.data);
      long[] var6 = new long[2 + var5.length];
      var6[0] = 4008636094L;
      var6[1] = (long)this.mq;
      System.arraycopy(var5, 0, var6, 2, var5.length);
      int var7 = var6.length - 1;
      long var8 = 0L;
      long var10 = 0L;

      for(int var12 = 0; var12 < this.mp; ++var12) {
         var8 += -1640531527L;
         int var13 = (int)(var8 >> 2 & 3L);
         int var14 = 0;
         long var15 = 0L;

         for(int var17 = 0; var17 < var7; ++var14) {
            var15 = var6[var14 + 1] >> 3 ^ (var10 & 268435455L) << 4;
            var15 += var6[var14 + 1] * 4L & 4294967295L ^ var10 >> 5;
            var15 ^= (var10 ^ var4[var17 & 3 ^ var13]) + (var6[var14 + 1] ^ var8);
            var6[var14] = var6[var14] + var15 & 4294967295L;
            var10 = var6[var14];
            ++var17;
         }

         var15 = var6[0] >> 3 ^ (var10 & 268435455L) << 4;
         var15 += var6[0] * 4L & 4294967295L ^ var10 >> 5;
         var15 ^= (var10 ^ var4[var7 & 3 ^ var13]) + (var6[0] ^ var8);
         var6[var7] = var6[var7] + var15 & 4294967295L;
         var10 = var6[var7];
      }

      return a((long[])var6, 0, var6.length);
   }

   private int getInt(int var1) {
      if (var1 >= 8 && var1 % 4 == 0) {
         var1 -= 8;
         return this.data[var1] & 255 | (this.data[var1 + 1] & 255) << 8 | (this.data[var1 + 2] & 255) << 16 | (this.data[var1 + 3] & 255) << 24;
      } else {
         throw new IllegalArgumentException("Invalid offset: " + var1);
      }
   }

   private void setInt(int var1, int var2) {
      if (var1 >= 8 && var1 % 4 == 0) {
         var1 -= 8;
         this.data[var1] = (byte)(var2 & 255);
         this.data[var1 + 1] = (byte)(var2 >> 8 & 255);
         this.data[var1 + 2] = (byte)(var2 >> 16 & 255);
         this.data[var1 + 3] = (byte)(var2 >> 24 & 255);
      } else {
         throw new IllegalArgumentException("Invalid offset: " + var1);
      }
   }

   private String getString(int var1) {
      if (var1 >= 8 && var1 % 4 == 0) {
         var1 -= 8;

         for(int var2 = var1; var2 < this.data.length; ++var2) {
            if (this.data[var2] == 0) {
               return new String(this.data, var1, var2 - var1);
            }
         }

         return "";
      } else {
         throw new IllegalArgumentException("Invalid offset: " + var1);
      }
   }

   private void setString(int var1, String var2) {
      if (var1 >= 8 && var1 % 4 == 0) {
         var1 -= 8;
         byte[] var3 = var2.getBytes();
         System.arraycopy(var3, 0, this.data, var1, var3.length);
         var1 += var3.length;

         for(int var4 = 4 - var3.length % 4; var4 > 0; --var4) {
            this.data[var1++] = 0;
         }

      } else {
         throw new IllegalArgumentException("Invalid offset: " + var1);
      }
   }

   private byte[] d(int var1, int var2) {
      if (var1 >= 8 && var1 % 4 == 0) {
         if (var2 % 4 != 0) {
            throw new IllegalArgumentException("Invalid length: " + var2);
         } else {
            var1 -= 8;
            byte[] var3 = new byte[var2];
            System.arraycopy(this.data, var1, var3, 0, var2);
            return var3;
         }
      } else {
         throw new IllegalArgumentException("Invalid offset: " + var1);
      }
   }

   private void setBytes(int var1, byte[] var2) {
      if (var1 >= 8 && var1 % 4 == 0) {
         if (var2.length % 4 != 0) {
            throw new IllegalArgumentException("Invalid length: " + var2.length);
         } else {
            var1 -= 8;
            System.arraycopy(var2, 0, this.data, var1, var2.length);
         }
      } else {
         throw new IllegalArgumentException("Invalid offset: " + var1);
      }
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("00000000    ");
      var1.append("## ## ## ## ## ## ## ## ");
      StringBuilder var2 = new StringBuilder();
      var2.append("########");
      byte var3 = 8;

      for(int var4 = 0; var4 < this.data.length; ++var4) {
         if ((var4 + var3) % 16 == 0) {
            var1.append(System.lineSeparator());

            String var5;
            for(var5 = Integer.toString((var4 + 1 + var3) / 16, 16) + "0"; var5.length() < 8; var5 = "0" + var5) {
            }

            var1.append(var5 + "    ");
         }

         var1.append(Integer.toString((this.data[var4] & 240) >> 4, 16));
         var1.append(Integer.toString(this.data[var4] & 15, 16));
         var1.append(' ');
         if (this.data[var4] == 32) {
            var2.append('.');
         } else if (this.data[var4] >= 32 && this.data[var4] < 127) {
            var2.append((char)(this.data[var4] & 255));
         } else {
            var2.append('?');
         }

         if ((var4 + var3) % 16 == 15) {
            var1.append("   ");
            var1.append(var2);
            var2 = new StringBuilder();
         }
      }

      if (var2.length() > 0) {
         while(var2.length() < 16) {
            var1.append("   ");
            var2.append(" ");
         }

         var1.append("   ");
         var1.append(var2);
      }

      return var1.toString();
   }

   private static long rotateLeft(long var0, int var2) {
      long var3 = (long)Math.pow(2.0D, (double)(32 - var2)) - 1L;
      return (var0 & var3) << var2 | var0 >>> 32 - var2;
   }

   private static byte[] a(long[] var0, int var1, int var2) {
      byte[] var3 = new byte[var2 * 4];

      for(int var4 = 0; var4 < var2; ++var4) {
         var3[var4 * 4] = (byte)((int)(var0[var1 + var4] & 255L));
         var3[var4 * 4 + 1] = (byte)((int)(var0[var1 + var4] >> 8 & 255L));
         var3[var4 * 4 + 2] = (byte)((int)(var0[var1 + var4] >> 16 & 255L));
         var3[var4 * 4 + 3] = (byte)((int)(var0[var1 + var4] >> 24 & 255L));
      }

      return var3;
   }

   private static long[] g(byte[] var0) {
      return a((byte[])var0, 0, var0.length);
   }

   private static long[] a(byte[] var0, int var1, int var2) {
      long[] var3 = new long[var2 / 4];

      for(int var4 = 0; var4 < var2; var4 += 4) {
         var3[var4 / 4] = (long)var0[var1 + var4] & 255L | ((long)var0[var1 + var4 + 1] & 255L) << 8 | ((long)var0[var1 + var4 + 2] & 255L) << 16 | ((long)var0[var1 + var4 + 3] & 255L) << 24;
      }

      return var3;
   }
}
