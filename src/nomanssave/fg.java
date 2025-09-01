package nomanssave;

import java.nio.charset.Charset;

public class fg {
   private static final Charset kT = Charset.forName("Windows-1252");
   final byte[] bytes;

   public fg(byte[] var1) {
      this.bytes = var1;
   }

   byte[] toByteArray() {
      byte[] var1 = new byte[this.bytes.length];
      System.arraycopy(this.bytes, 0, var1, 0, this.bytes.length);
      return var1;
   }

   public int indexOf(int var1) {
      return this.indexOf(var1, 0);
   }

   public int indexOf(int var1, int var2) {
      for(int var3 = var2; var3 < this.bytes.length; ++var3) {
         if (var1 == (this.bytes[var3] & 255)) {
            return var3;
         }
      }

      return -1;
   }

   public String substring(int var1) {
      return this.substring(var1, this.bytes.length - var1);
   }

   public String substring(int var1, int var2) {
      return new String(this.bytes, var1, var2, kT);
   }

   public String bP() {
      StringBuilder var1 = new StringBuilder();
      boolean var2 = false;

      for(int var3 = 0; var3 < this.bytes.length; ++var3) {
         int var4 = this.bytes[var3] & 255;
         if (var3 == 0) {
            if (var4 != 94) {
               return this.toString();
            }

            var1.append('^');
         } else if (var4 == 35) {
            var1.append('#');
            var2 = true;
         } else if (var2) {
            var1.append((char)var4);
         } else {
            var1.append("0123456789ABCDEFabcdef".charAt((this.bytes[var3] & 240) >> 4));
            var1.append("0123456789ABCDEFabcdef".charAt(this.bytes[var3] & 15));
         }
      }

      return var1.toString();
   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof fg)) {
         return false;
      } else {
         fg var2 = (fg)var1;
         if (this.bytes.length != var2.bytes.length) {
            return false;
         } else {
            for(int var3 = 0; var3 < this.bytes.length; ++var3) {
               if (this.bytes[var3] != var2.bytes[var3]) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public String toString() {
      return new String(this.bytes, kT);
   }
}
