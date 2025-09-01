package net.jpountz.xxhash;

import java.nio.ByteBuffer;
import net.jpountz.util.ByteBufferUtils;
import net.jpountz.util.SafeUtils;

final class XXHash64JNI extends XXHash64 {
   public static final XXHash64 INSTANCE = new XXHash64JNI();
   private static XXHash64 SAFE_INSTANCE;

   public long hash(byte[] buf, int off, int len, long seed) {
      SafeUtils.checkRange(buf, off, len);
      return XXHashJNI.XXH64(buf, off, len, seed);
   }

   public long hash(ByteBuffer buf, int off, int len, long seed) {
      if (buf.isDirect()) {
         ByteBufferUtils.checkRange(buf, off, len);
         return XXHashJNI.XXH64BB(buf, off, len, seed);
      } else if (buf.hasArray()) {
         return this.hash(buf.array(), off + buf.arrayOffset(), len, seed);
      } else {
         XXHash64 safeInstance = SAFE_INSTANCE;
         if (safeInstance == null) {
            safeInstance = SAFE_INSTANCE = XXHashFactory.safeInstance().hash64();
         }

         return safeInstance.hash(buf, off, len, seed);
      }
   }
}
