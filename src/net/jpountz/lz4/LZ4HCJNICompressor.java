package net.jpountz.lz4;

import java.nio.ByteBuffer;
import net.jpountz.util.ByteBufferUtils;
import net.jpountz.util.SafeUtils;

final class LZ4HCJNICompressor extends LZ4Compressor {
   public static final LZ4HCJNICompressor INSTANCE = new LZ4HCJNICompressor();
   private static LZ4Compressor SAFE_INSTANCE;
   private final int compressionLevel;

   LZ4HCJNICompressor() {
      this(9);
   }

   LZ4HCJNICompressor(int compressionLevel) {
      this.compressionLevel = compressionLevel;
   }

   public int compress(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff, int maxDestLen) {
      SafeUtils.checkRange(src, srcOff, srcLen);
      SafeUtils.checkRange(dest, destOff, maxDestLen);
      int result = LZ4JNI.LZ4_compressHC(src, (ByteBuffer)null, srcOff, srcLen, dest, (ByteBuffer)null, destOff, maxDestLen, this.compressionLevel);
      if (result <= 0) {
         throw new LZ4Exception();
      } else {
         return result;
      }
   }

   public int compress(ByteBuffer src, int srcOff, int srcLen, ByteBuffer dest, int destOff, int maxDestLen) {
      ByteBufferUtils.checkNotReadOnly(dest);
      ByteBufferUtils.checkRange(src, srcOff, srcLen);
      ByteBufferUtils.checkRange(dest, destOff, maxDestLen);
      if (!src.hasArray() && !src.isDirect() || !dest.hasArray() && !dest.isDirect()) {
         LZ4Compressor safeInstance = SAFE_INSTANCE;
         if (safeInstance == null) {
            safeInstance = SAFE_INSTANCE = LZ4Factory.safeInstance().highCompressor(this.compressionLevel);
         }

         return safeInstance.compress(src, srcOff, srcLen, dest, destOff, maxDestLen);
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

         int result = LZ4JNI.LZ4_compressHC(srcArr, srcBuf, srcOff, srcLen, destArr, destBuf, destOff, maxDestLen, this.compressionLevel);
         if (result <= 0) {
            throw new LZ4Exception();
         } else {
            return result;
         }
      }
   }
}
