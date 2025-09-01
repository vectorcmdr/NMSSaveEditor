package net.jpountz.lz4;

import java.nio.ByteBuffer;
import net.jpountz.util.ByteBufferUtils;
import net.jpountz.util.SafeUtils;

final class LZ4JNISafeDecompressor extends LZ4SafeDecompressor {
   public static final LZ4JNISafeDecompressor INSTANCE = new LZ4JNISafeDecompressor();
   private static LZ4SafeDecompressor SAFE_INSTANCE;

   public final int decompress(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff, int maxDestLen) {
      SafeUtils.checkRange(src, srcOff, srcLen);
      SafeUtils.checkRange(dest, destOff, maxDestLen);
      int result = LZ4JNI.LZ4_decompress_safe(src, (ByteBuffer)null, srcOff, srcLen, dest, (ByteBuffer)null, destOff, maxDestLen);
      if (result < 0) {
         throw new LZ4Exception("Error decoding offset " + (srcOff - result) + " of input buffer");
      } else {
         return result;
      }
   }

   public int decompress(ByteBuffer src, int srcOff, int srcLen, ByteBuffer dest, int destOff, int maxDestLen) {
      ByteBufferUtils.checkNotReadOnly(dest);
      ByteBufferUtils.checkRange(src, srcOff, srcLen);
      ByteBufferUtils.checkRange(dest, destOff, maxDestLen);
      if (!src.hasArray() && !src.isDirect() || !dest.hasArray() && !dest.isDirect()) {
         LZ4SafeDecompressor safeInstance = SAFE_INSTANCE;
         if (safeInstance == null) {
            safeInstance = SAFE_INSTANCE = LZ4Factory.safeInstance().safeDecompressor();
         }

         return safeInstance.decompress(src, srcOff, srcLen, dest, destOff, maxDestLen);
      } else {
         byte[] srcArr = null;
         byte[] destArr = null;
         ByteBuffer srcBuf = null;
         ByteBuffer destBuf = null;
         if (src.hasArray()) {
            srcArr = src.array();
            srcOff += src.arrayOffset();
         } else {
            assert src.isDirect();

            srcBuf = src;
         }

         if (dest.hasArray()) {
            destArr = dest.array();
            destOff += dest.arrayOffset();
         } else {
            assert dest.isDirect();

            destBuf = dest;
         }

         int result = LZ4JNI.LZ4_decompress_safe(srcArr, srcBuf, srcOff, srcLen, destArr, destBuf, destOff, maxDestLen);
         if (result < 0) {
            throw new LZ4Exception("Error decoding offset " + (srcOff - result) + " of input buffer");
         } else {
            return result;
         }
      }
   }
}
