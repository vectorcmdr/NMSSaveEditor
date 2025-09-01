package nomanssave;

import java.security.SecureRandom;

public class hg {
   private static final SecureRandom sv = new SecureRandom();
   private final long sw;

   public static hg aB(String var0) {
      var0 = var0.trim();
      if (!var0.startsWith("0x")) {
         throw new RuntimeException("Invalid seed: " + var0);
      } else {
         long var1 = Long.parseUnsignedLong(var0.substring(2), 16);
         return new hg(var1);
      }
   }

   public static hg eo() {
      return new hg(sv.nextLong());
   }

   public hg(long var1) {
      this.sw = var1;
   }

   public String toString() {
      return "0x" + Long.toHexString(this.sw).toUpperCase();
   }
}
