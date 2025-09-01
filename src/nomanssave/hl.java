package nomanssave;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class hl {
   private static final Pattern sN = Pattern.compile("0x([0-9a-fA-F]{1,16})");
   private static final Pattern sO = Pattern.compile("[0-9a-fA-F]{12}");
   private static final Pattern sP = Pattern.compile("([0-9a-fA-F]{4}):([0-9a-fA-F]{4}):([0-9a-fA-F]{4}):([0-9a-fA-F]{4})");
   private int sQ;
   private int sR;
   private int sS;
   private int sT;
   private int sU;
   private int sV;

   private static long aE(String var0) {
      long var1 = 0L;

      for(int var4 = 0; var4 < var0.length(); ++var4) {
         char var3 = var0.charAt(var4);
         var1 <<= 4;
         if (var3 >= 'A' && var3 <= 'F') {
            var1 |= (long)(var3 - 55);
         } else if (var3 >= 'a' && var3 <= 'f') {
            var1 |= (long)(var3 - 87);
         } else {
            var1 |= (long)(var3 - 48);
         }
      }

      return var1;
   }

   private static int a(long var0, int var2) {
      int var3 = -1 >>> 32 - var2;
      int var4 = Integer.MIN_VALUE >>> 32 - var2;
      int var5 = (int)(var0 & (long)var3);
      if ((var5 & var4) == var4) {
         var5 |= ~var3;
      }

      return var5;
   }

   private static int b(long var0, int var2) {
      int var3 = -1 >>> 32 - var2;
      return (int)(var0 & (long)var3);
   }

   public static hl e(String var0, int var1) {
      Matcher var2;
      long var3;
      if ((var2 = sP.matcher(var0)).matches()) {
         var3 = aE(var2.group(1)) - 2047L;
         if (var3 > 2047L) {
            throw new RuntimeException("Invalid galactic coordinates");
         } else {
            long var11 = aE(var2.group(2)) - 127L;
            if (var11 > 127L) {
               throw new RuntimeException("Invalid galactic coordinates");
            } else {
               long var12 = aE(var2.group(3)) - 2047L;
               if (var12 > 2047L) {
                  throw new RuntimeException("Invalid galactic coordinates");
               } else {
                  long var13 = aE(var2.group(4));
                  if (var13 > 65535L) {
                     throw new RuntimeException("Invalid galactic coordinates");
                  } else {
                     return new hl((int)(var13 >> 12 & 15L), (int)(var13 & 4095L), var1, (int)var11, (int)var12, (int)var3);
                  }
               }
            }
         }
      } else if (sO.matcher(var0).matches()) {
         var3 = aE(var0);
         int var5 = b(var3 >> 44, 4);
         int var6 = b(var3 >> 32, 12);
         int var7 = a(var3 >> 24, 8);
         int var8 = a(var3 >> 12, 12);
         int var9 = a(var3, 12);
         return new hl(var5, var6, var1, var7, var8, var9);
      } else {
         throw new RuntimeException("Unable to decode value");
      }
   }

   public static hl n(Object var0) {
      if (var0 == null) {
         return null;
      } else if (var0 instanceof Number) {
         long var6 = ((Number)var0).longValue();
         return new hl(var6);
      } else if (var0 instanceof String) {
         String var5 = (String)var0;
         Matcher var2;
         if ((var2 = sN.matcher(var5)).matches()) {
            long var3 = aE(var2.group(1));
            return new hl(var3);
         } else {
            return e(var5, 0);
         }
      } else {
         if (var0 instanceof eY) {
            eY var1 = (eY)var0;
            if (var1.contains("GalacticAddress")) {
               return new hl((eY)var0);
            }
         }

         return null;
      }
   }

   private hl(eY var1) {
      this.sQ = var1.c("GalacticAddress.PlanetIndex", 0);
      this.sR = var1.c("GalacticAddress.SolarSystemIndex", 0);
      this.sS = var1.c("RealityIndex", 0);
      this.sT = var1.c("GalacticAddress.VoxelY", 0);
      this.sU = var1.c("GalacticAddress.VoxelZ", 0);
      this.sV = var1.c("GalacticAddress.VoxelX", 0);
   }

   public hl(long var1) {
      this.sQ = b(var1 >> 52, 12);
      this.sR = b(var1 >> 40, 12);
      this.sS = b(var1 >> 32, 8);
      this.sT = a(var1 >> 24, 8);
      this.sU = a(var1 >> 12, 12);
      this.sV = a(var1 >> 0, 12);
   }

   private hl(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.sQ = var1;
      this.sR = var2;
      this.sS = var3;
      this.sT = var4;
      this.sU = var5;
      this.sV = var6;
   }

   public int eq() {
      return this.sQ;
   }

   public void aL(int var1) {
      if (var1 >= 0 && var1 <= 15) {
         this.sQ = var1;
      } else {
         throw new RuntimeException("Invalid planet index: " + var1);
      }
   }

   public int er() {
      return this.sR;
   }

   public void aM(int var1) {
      if (var1 >= 0 && var1 <= 4095) {
         this.sR = var1;
      } else {
         throw new RuntimeException("Invalid solar system index: " + var1);
      }
   }

   public int es() {
      return this.sS;
   }

   public void aN(int var1) {
      if (var1 >= 0 && var1 <= 255) {
         this.sS = var1;
      } else {
         throw new RuntimeException("Invalid reality index: " + var1);
      }
   }

   public int et() {
      return this.sT;
   }

   public void aO(int var1) {
      if (var1 >= -127 && var1 <= 127) {
         this.sT = var1;
      } else {
         throw new RuntimeException("Invalid voxelY coordinate: " + var1);
      }
   }

   public int eu() {
      return this.sU;
   }

   public void aP(int var1) {
      if (var1 >= -2047 && var1 <= 2047) {
         this.sU = var1;
      } else {
         throw new RuntimeException("Invalid voxelZ coordinate: " + var1);
      }
   }

   public int ev() {
      return this.sV;
   }

   public void aQ(int var1) {
      if (var1 >= -2047 && var1 <= 2047) {
         this.sV = var1;
      } else {
         throw new RuntimeException("Invalid voxelX coordinate: " + var1);
      }
   }

   public eY ew() {
      return (new fa()).d("RealityIndex", this.sS).d("GalacticAddress", (new fa()).d("VoxelX", this.sV).d("VoxelY", this.sT).d("VoxelZ", this.sU).d("SolarSystemIndex", this.sR).d("PlanetIndex", this.sQ).bH()).bH();
   }

   public long ex() {
      return ((long)this.sQ & 15L) << 52 | ((long)this.sR & 4095L) << 40 | ((long)this.sS & 255L) << 32 | ((long)this.sT & 255L) << 24 | ((long)this.sU & 4095L) << 12 | (long)this.sV & 4095L;
   }

   public String ey() {
      StringBuilder var1 = new StringBuilder();
      var1.append(Integer.toString(this.sQ & 15, 16));
      var1.append(Integer.toString(this.sR & 4095, 16));

      while(var1.length() < 4) {
         var1.insert(1, '0');
      }

      var1.append(Integer.toString(this.sT & 255, 16));

      while(var1.length() < 6) {
         var1.insert(4, '0');
      }

      var1.append(Integer.toString(this.sU & 4095, 16));

      while(var1.length() < 9) {
         var1.insert(6, '0');
      }

      var1.append(Integer.toString(this.sV & 4095, 16));

      while(var1.length() < 12) {
         var1.insert(9, '0');
      }

      return var1.toString().toUpperCase();
   }

   public String ez() {
      StringBuilder var1 = new StringBuilder();
      var1.append(Integer.toString(this.sV + 2047, 16));

      while(var1.length() < 4) {
         var1.insert(0, '0');
      }

      var1.append(':');
      var1.append(Integer.toString(this.sT + 127, 16));

      while(var1.length() < 9) {
         var1.insert(5, '0');
      }

      var1.append(':');
      var1.append(Integer.toString(this.sU + 2047, 16));

      while(var1.length() < 14) {
         var1.insert(10, '0');
      }

      var1.append(':');
      var1.append(Integer.toString(this.sQ << 12 | this.sR, 16));

      while(var1.length() < 19) {
         var1.insert(15, '0');
      }

      return var1.toString().toUpperCase();
   }

   public boolean equals(Object var1) {
      if (var1 instanceof hl) {
         hl var2 = (hl)var1;
         if (this.sQ != var2.sQ) {
            return false;
         } else if (this.sR != var2.sR) {
            return false;
         } else if (this.sS != var2.sS) {
            return false;
         } else if (this.sT != var2.sT) {
            return false;
         } else if (this.sU != var2.sU) {
            return false;
         } else {
            return this.sV == var2.sV;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return (int)this.ex();
   }

   public String toString() {
      return "0x" + Long.toString(this.ex(), 16);
   }
}
