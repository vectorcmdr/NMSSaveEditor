package net.jpountz.xxhash;

import java.nio.ByteBuffer;
import net.jpountz.util.ByteBufferUtils;
import net.jpountz.util.UnsafeUtils;

final class XXHash64JavaUnsafe extends XXHash64 {
   public static final XXHash64 INSTANCE = new XXHash64JavaUnsafe();

   public long hash(byte[] buf, int off, int len, long seed) {
      UnsafeUtils.checkRange(buf, off, len);
      int end = off + len;
      long h64;
      if (len >= 32) {
         int limit = end - 32;
         long v1 = seed + -7046029288634856825L + -4417276706812531889L;
         long v2 = seed + -4417276706812531889L;
         long v3 = seed + 0L;
         long v4 = seed - -7046029288634856825L;

         do {
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
            off += 8;
         } while(off <= limit);

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
         h64 = seed + 2870177450012600261L;
      }

      for(h64 += (long)len; off <= end - 8; off += 8) {
         long k1 = UnsafeUtils.readLongLE(buf, off);
         k1 *= -4417276706812531889L;
         k1 = Long.rotateLeft(k1, 31);
         k1 *= -7046029288634856825L;
         h64 ^= k1;
         h64 = Long.rotateLeft(h64, 27) * -7046029288634856825L + -8796714831421723037L;
      }

      if (off <= end - 4) {
         h64 ^= ((long)UnsafeUtils.readIntLE(buf, off) & 4294967295L) * -7046029288634856825L;
         h64 = Long.rotateLeft(h64, 23) * -4417276706812531889L + 1609587929392839161L;
         off += 4;
      }

      while(off < end) {
         h64 ^= (long)(UnsafeUtils.readByte(buf, off) & 255) * 2870177450012600261L;
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

   public long hash(ByteBuffer buf, int off, int len, long seed) {
      if (buf.hasArray()) {
         return this.hash(buf.array(), off + buf.arrayOffset(), len, seed);
      } else {
         ByteBufferUtils.checkRange(buf, off, len);
         buf = ByteBufferUtils.inLittleEndianOrder(buf);
         int end = off + len;
         long h64;
         if (len >= 32) {
            int limit = end - 32;
            long v1 = seed + -7046029288634856825L + -4417276706812531889L;
            long v2 = seed + -4417276706812531889L;
            long v3 = seed + 0L;
            long v4 = seed - -7046029288634856825L;

            do {
               v1 += ByteBufferUtils.readLongLE(buf, off) * -4417276706812531889L;
               v1 = Long.rotateLeft(v1, 31);
               v1 *= -7046029288634856825L;
               off += 8;
               v2 += ByteBufferUtils.readLongLE(buf, off) * -4417276706812531889L;
               v2 = Long.rotateLeft(v2, 31);
               v2 *= -7046029288634856825L;
               off += 8;
               v3 += ByteBufferUtils.readLongLE(buf, off) * -4417276706812531889L;
               v3 = Long.rotateLeft(v3, 31);
               v3 *= -7046029288634856825L;
               off += 8;
               v4 += ByteBufferUtils.readLongLE(buf, off) * -4417276706812531889L;
               v4 = Long.rotateLeft(v4, 31);
               v4 *= -7046029288634856825L;
               off += 8;
            } while(off <= limit);

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
            h64 = seed + 2870177450012600261L;
         }

         for(h64 += (long)len; off <= end - 8; off += 8) {
            long k1 = ByteBufferUtils.readLongLE(buf, off);
            k1 *= -4417276706812531889L;
            k1 = Long.rotateLeft(k1, 31);
            k1 *= -7046029288634856825L;
            h64 ^= k1;
            h64 = Long.rotateLeft(h64, 27) * -7046029288634856825L + -8796714831421723037L;
         }

         if (off <= end - 4) {
            h64 ^= ((long)ByteBufferUtils.readIntLE(buf, off) & 4294967295L) * -7046029288634856825L;
            h64 = Long.rotateLeft(h64, 23) * -4417276706812531889L + 1609587929392839161L;
            off += 4;
         }

         while(off < end) {
            h64 ^= (long)(ByteBufferUtils.readByte(buf, off) & 255) * 2870177450012600261L;
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
   }
}
