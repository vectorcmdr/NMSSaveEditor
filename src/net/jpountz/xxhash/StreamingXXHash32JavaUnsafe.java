package net.jpountz.xxhash;

import net.jpountz.util.SafeUtils;
import net.jpountz.util.UnsafeUtils;

final class StreamingXXHash32JavaUnsafe extends AbstractStreamingXXHash32Java {
   StreamingXXHash32JavaUnsafe(int seed) {
      super(seed);
   }

   public int getValue() {
      int h32;
      if (this.totalLen >= 16L) {
         h32 = Integer.rotateLeft(this.v1, 1) + Integer.rotateLeft(this.v2, 7) + Integer.rotateLeft(this.v3, 12) + Integer.rotateLeft(this.v4, 18);
      } else {
         h32 = this.seed + 374761393;
      }

      h32 = (int)((long)h32 + this.totalLen);

      int off;
      for(off = 0; off <= this.memSize - 4; off += 4) {
         h32 += UnsafeUtils.readIntLE(this.memory, off) * -1028477379;
         h32 = Integer.rotateLeft(h32, 17) * 668265263;
      }

      while(off < this.memSize) {
         h32 += (UnsafeUtils.readByte(this.memory, off) & 255) * 374761393;
         h32 = Integer.rotateLeft(h32, 11) * -1640531535;
         ++off;
      }

      h32 ^= h32 >>> 15;
      h32 *= -2048144777;
      h32 ^= h32 >>> 13;
      h32 *= -1028477379;
      h32 ^= h32 >>> 16;
      return h32;
   }

   public void update(byte[] buf, int off, int len) {
      SafeUtils.checkRange(buf, off, len);
      this.totalLen += (long)len;
      if (this.memSize + len < 16) {
         System.arraycopy(buf, off, this.memory, this.memSize, len);
         this.memSize += len;
      } else {
         int end = off + len;
         if (this.memSize > 0) {
            System.arraycopy(buf, off, this.memory, this.memSize, 16 - this.memSize);
            this.v1 += UnsafeUtils.readIntLE(this.memory, 0) * -2048144777;
            this.v1 = Integer.rotateLeft(this.v1, 13);
            this.v1 *= -1640531535;
            this.v2 += UnsafeUtils.readIntLE(this.memory, 4) * -2048144777;
            this.v2 = Integer.rotateLeft(this.v2, 13);
            this.v2 *= -1640531535;
            this.v3 += UnsafeUtils.readIntLE(this.memory, 8) * -2048144777;
            this.v3 = Integer.rotateLeft(this.v3, 13);
            this.v3 *= -1640531535;
            this.v4 += UnsafeUtils.readIntLE(this.memory, 12) * -2048144777;
            this.v4 = Integer.rotateLeft(this.v4, 13);
            this.v4 *= -1640531535;
            off += 16 - this.memSize;
            this.memSize = 0;
         }

         int limit = end - 16;
         int v1 = this.v1;
         int v2 = this.v2;
         int v3 = this.v3;

         int v4;
         for(v4 = this.v4; off <= limit; off += 4) {
            v1 += UnsafeUtils.readIntLE(buf, off) * -2048144777;
            v1 = Integer.rotateLeft(v1, 13);
            v1 *= -1640531535;
            off += 4;
            v2 += UnsafeUtils.readIntLE(buf, off) * -2048144777;
            v2 = Integer.rotateLeft(v2, 13);
            v2 *= -1640531535;
            off += 4;
            v3 += UnsafeUtils.readIntLE(buf, off) * -2048144777;
            v3 = Integer.rotateLeft(v3, 13);
            v3 *= -1640531535;
            off += 4;
            v4 += UnsafeUtils.readIntLE(buf, off) * -2048144777;
            v4 = Integer.rotateLeft(v4, 13);
            v4 *= -1640531535;
         }

         this.v1 = v1;
         this.v2 = v2;
         this.v3 = v3;
         this.v4 = v4;
         if (off < end) {
            System.arraycopy(buf, off, this.memory, 0, end - off);
            this.memSize = end - off;
         }

      }
   }

   static class Factory implements StreamingXXHash32.Factory {
      public static final StreamingXXHash32.Factory INSTANCE = new StreamingXXHash32JavaUnsafe.Factory();

      public StreamingXXHash32 newStreamingHash(int seed) {
         return new StreamingXXHash32JavaUnsafe(seed);
      }
   }
}
