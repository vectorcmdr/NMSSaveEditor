package net.jpountz.xxhash;

import net.jpountz.util.SafeUtils;
import net.jpountz.util.UnsafeUtils;

final class StreamingXXHash64JavaUnsafe extends AbstractStreamingXXHash64Java {
   StreamingXXHash64JavaUnsafe(long seed) {
      super(seed);
   }

   public long getValue() {
      long h64;
      if (this.totalLen >= 32L) {
         long v1 = this.v1;
         long v2 = this.v2;
         long v3 = this.v3;
         long v4 = this.v4;
         h64 = Long.rotateLeft(v1, 1) + Long.rotateLeft(v2, 7) + Long.rotateLeft(v3, 12) + Long.rotateLeft(v4, 18);
         v1 *= -4417276706812531889L;
         v1 = Long.rotateLeft(v1, 31);
         v1 *= -7046029288634856825L;
         h64 ^= v1;
         h64 = h64 * -7046029288634856825L + -8796714831421723037L;
         v2 *= -4417276706812531889L;
         v2 = Long.rotateLeft(v2, 31);
         v2 *= -7046029288634856825L;
         h64 ^= v2;
         h64 = h64 * -7046029288634856825L + -8796714831421723037L;
         v3 *= -4417276706812531889L;
         v3 = Long.rotateLeft(v3, 31);
         v3 *= -7046029288634856825L;
         h64 ^= v3;
         h64 = h64 * -7046029288634856825L + -8796714831421723037L;
         v4 *= -4417276706812531889L;
         v4 = Long.rotateLeft(v4, 31);
         v4 *= -7046029288634856825L;
         h64 ^= v4;
         h64 = h64 * -7046029288634856825L + -8796714831421723037L;
      } else {
         h64 = this.seed + 2870177450012600261L;
      }

      h64 += this.totalLen;

      int off;
      for(off = 0; off <= this.memSize - 8; off += 8) {
         long k1 = UnsafeUtils.readLongLE(this.memory, off);
         k1 *= -4417276706812531889L;
         k1 = Long.rotateLeft(k1, 31);
         k1 *= -7046029288634856825L;
         h64 ^= k1;
         h64 = Long.rotateLeft(h64, 27) * -7046029288634856825L + -8796714831421723037L;
      }

      if (off <= this.memSize - 4) {
         h64 ^= ((long)UnsafeUtils.readIntLE(this.memory, off) & 4294967295L) * -7046029288634856825L;
         h64 = Long.rotateLeft(h64, 23) * -4417276706812531889L + 1609587929392839161L;
         off += 4;
      }

      while(off < this.memSize) {
         h64 ^= (long)(this.memory[off] & 255) * 2870177450012600261L;
         h64 = Long.rotateLeft(h64, 11) * -7046029288634856825L;
         ++off;
      }

      h64 ^= h64 >>> 33;
      h64 *= -4417276706812531889L;
      h64 ^= h64 >>> 29;
      h64 *= 1609587929392839161L;
      h64 ^= h64 >>> 32;
      return h64;
   }

   public void update(byte[] buf, int off, int len) {
      SafeUtils.checkRange(buf, off, len);
      this.totalLen += (long)len;
      if (this.memSize + len < 32) {
         System.arraycopy(buf, off, this.memory, this.memSize, len);
         this.memSize += len;
      } else {
         int end = off + len;
         if (this.memSize > 0) {
            System.arraycopy(buf, off, this.memory, this.memSize, 32 - this.memSize);
            this.v1 += UnsafeUtils.readLongLE(this.memory, 0) * -4417276706812531889L;
            this.v1 = Long.rotateLeft(this.v1, 31);
            this.v1 *= -7046029288634856825L;
            this.v2 += UnsafeUtils.readLongLE(this.memory, 8) * -4417276706812531889L;
            this.v2 = Long.rotateLeft(this.v2, 31);
            this.v2 *= -7046029288634856825L;
            this.v3 += UnsafeUtils.readLongLE(this.memory, 16) * -4417276706812531889L;
            this.v3 = Long.rotateLeft(this.v3, 31);
            this.v3 *= -7046029288634856825L;
            this.v4 += UnsafeUtils.readLongLE(this.memory, 24) * -4417276706812531889L;
            this.v4 = Long.rotateLeft(this.v4, 31);
            this.v4 *= -7046029288634856825L;
            off += 32 - this.memSize;
            this.memSize = 0;
         }

         int limit = end - 32;
         long v1 = this.v1;
         long v2 = this.v2;
         long v3 = this.v3;

         long v4;
         for(v4 = this.v4; off <= limit; off += 8) {
            v1 += UnsafeUtils.readLongLE(buf, off) * -4417276706812531889L;
            v1 = Long.rotateLeft(v1, 31);
            v1 *= -7046029288634856825L;
            off += 8;
            v2 += UnsafeUtils.readLongLE(buf, off) * -4417276706812531889L;
            v2 = Long.rotateLeft(v2, 31);
            v2 *= -7046029288634856825L;
            off += 8;
            v3 += UnsafeUtils.readLongLE(buf, off) * -4417276706812531889L;
            v3 = Long.rotateLeft(v3, 31);
            v3 *= -7046029288634856825L;
            off += 8;
            v4 += UnsafeUtils.readLongLE(buf, off) * -4417276706812531889L;
            v4 = Long.rotateLeft(v4, 31);
            v4 *= -7046029288634856825L;
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

   static class Factory implements StreamingXXHash64.Factory {
      public static final StreamingXXHash64.Factory INSTANCE = new StreamingXXHash64JavaUnsafe.Factory();

      public StreamingXXHash64 newStreamingHash(long seed) {
         return new StreamingXXHash64JavaUnsafe(seed);
      }
   }
}
