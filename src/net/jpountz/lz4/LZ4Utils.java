package net.jpountz.lz4;

enum LZ4Utils {
   private static final int MAX_INPUT_SIZE = 2113929216;

   static int maxCompressedLength(int length) {
      if (length < 0) {
         throw new IllegalArgumentException("length must be >= 0, got " + length);
      } else if (length >= 2113929216) {
         throw new IllegalArgumentException("length must be < 2113929216");
      } else {
         return length + length / 255 + 16;
      }
   }

   static int hash(int i) {
      return i * -1640531535 >>> 20;
   }

   static int hash64k(int i) {
      return i * -1640531535 >>> 19;
   }

   static int hashHC(int i) {
      return i * -1640531535 >>> 17;
   }

   static void copyTo(LZ4Utils.Match m1, LZ4Utils.Match m2) {
      m2.len = m1.len;
      m2.start = m1.start;
      m2.ref = m1.ref;
   }

   static class Match {
      int start;
      int ref;
      int len;

      void fix(int correction) {
         this.start += correction;
         this.ref += correction;
         this.len -= correction;
      }

      int end() {
         return this.start + this.len;
      }
   }
}
