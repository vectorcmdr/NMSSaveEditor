package net.jpountz.xxhash;

import java.nio.ByteBuffer;
import net.jpountz.util.ByteBufferUtils;
import net.jpountz.util.SafeUtils;

final class XXHash32JNI extends XXHash32 {
   public static final XXHash32 INSTANCE = new XXHash32JNI();
   private static XXHash32 SAFE_INSTANCE;

   public int hash(byte[] buf, int off, int len, int seed) {
      SafeUtils.checkRange(buf, off, len);
      return XXHashJNI.XXH32(buf, off, len, seed);
   }

   public int hash(ByteBuffer buf, int off, int len, int seed) {
      if (buf.isDirect()) {
         ByteBufferUtils.checkRange(buf, off, len);
         return XXHashJNI.XXH32BB(buf, off, len, seed);
      } else if (buf.hasArray()) {
         return this.hash(buf.array(), off + buf.arrayOffset(), len, seed);
      } else {
         XXHash32 safeInstance = SAFE_INSTANCE;
         if (safeInstance == null) {
            safeInstance = SAFE_INSTANCE = XXHashFactory.safeInstance().hash32();
         }

         return safeInstance.hash(buf, off, len, seed);
      }
   }
}
