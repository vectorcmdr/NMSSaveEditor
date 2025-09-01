package net.jpountz.lz4;

import java.nio.ByteBuffer;
import net.jpountz.util.ByteBufferUtils;
import net.jpountz.util.UnsafeUtils;

final class LZ4JavaUnsafeSafeDecompressor extends LZ4SafeDecompressor {
   public static final LZ4SafeDecompressor INSTANCE = new LZ4JavaUnsafeSafeDecompressor();

   public int decompress(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff, int destLen) {
      UnsafeUtils.checkRange(src, srcOff, srcLen);
      UnsafeUtils.checkRange(dest, destOff, destLen);
      if (destLen == 0) {
         if (srcLen == 1 && UnsafeUtils.readByte(src, srcOff) == 0) {
            return 0;
         } else {
            throw new LZ4Exception("Output buffer too small");
         }
      } else {
         int srcEnd = srcOff + srcLen;
         int destEnd = destOff + destLen;
         int sOff = srcOff;
         int dOff = destOff;

         while(true) {
            int token = UnsafeUtils.readByte(src, sOff) & 255;
            ++sOff;
            int literalLen = token >>> 4;
            if (literalLen == 15) {
               byte len;
               for(len = -1; sOff < srcEnd && (len = UnsafeUtils.readByte(src, sOff++)) == -1; literalLen += 255) {
               }

               literalLen += len & 255;
            }

            int literalCopyEnd = dOff + literalLen;
            if (literalCopyEnd > destEnd - 8 || sOff + literalLen > srcEnd - 8) {
               if (literalCopyEnd > destEnd) {
                  throw new LZ4Exception();
               } else if (sOff + literalLen != srcEnd) {
                  throw new LZ4Exception("Malformed input at " + sOff);
               } else {
                  LZ4UnsafeUtils.safeArraycopy(src, sOff, dest, dOff, literalLen);
                  int var10000 = sOff + literalLen;
                  return literalCopyEnd - destOff;
               }
            }

            LZ4UnsafeUtils.wildArraycopy(src, sOff, dest, dOff, literalLen);
            sOff += literalLen;
            int matchDec = UnsafeUtils.readShortLE(src, sOff);
            sOff += 2;
            int matchOff = literalCopyEnd - matchDec;
            if (matchOff < destOff) {
               throw new LZ4Exception("Malformed input at " + sOff);
            }

            int matchLen = token & 15;
            if (matchLen == 15) {
               byte len;
               for(len = -1; sOff < srcEnd && (len = UnsafeUtils.readByte(src, sOff++)) == -1; matchLen += 255) {
               }

               matchLen += len & 255;
            }

            matchLen += 4;
            int matchCopyEnd = literalCopyEnd + matchLen;
            if (matchCopyEnd > destEnd - 8) {
               if (matchCopyEnd > destEnd) {
                  throw new LZ4Exception("Malformed input at " + sOff);
               }

               LZ4UnsafeUtils.safeIncrementalCopy(dest, matchOff, literalCopyEnd, matchLen);
            } else {
               LZ4UnsafeUtils.wildIncrementalCopy(dest, matchOff, literalCopyEnd, matchCopyEnd);
            }

            dOff = matchCopyEnd;
         }
      }
   }

   public int decompress(ByteBuffer src, int srcOff, int srcLen, ByteBuffer dest, int destOff, int destLen) {
      if (src.hasArray() && dest.hasArray()) {
         return this.decompress(src.array(), srcOff + src.arrayOffset(), srcLen, dest.array(), destOff + dest.arrayOffset(), destLen);
      } else {
         src = ByteBufferUtils.inNativeByteOrder(src);
         dest = ByteBufferUtils.inNativeByteOrder(dest);
         ByteBufferUtils.checkRange(src, srcOff, srcLen);
         ByteBufferUtils.checkRange(dest, destOff, destLen);
         if (destLen == 0) {
            if (srcLen == 1 && ByteBufferUtils.readByte(src, srcOff) == 0) {
               return 0;
            } else {
               throw new LZ4Exception("Output buffer too small");
            }
         } else {
            int srcEnd = srcOff + srcLen;
            int destEnd = destOff + destLen;
            int sOff = srcOff;
            int dOff = destOff;

            while(true) {
               int token = ByteBufferUtils.readByte(src, sOff) & 255;
               ++sOff;
               int literalLen = token >>> 4;
               if (literalLen == 15) {
                  byte len;
                  for(len = -1; sOff < srcEnd && (len = ByteBufferUtils.readByte(src, sOff++)) == -1; literalLen += 255) {
                  }

                  literalLen += len & 255;
               }

               int literalCopyEnd = dOff + literalLen;
               if (literalCopyEnd > destEnd - 8 || sOff + literalLen > srcEnd - 8) {
                  if (literalCopyEnd > destEnd) {
                     throw new LZ4Exception();
                  } else if (sOff + literalLen != srcEnd) {
                     throw new LZ4Exception("Malformed input at " + sOff);
                  } else {
                     LZ4ByteBufferUtils.safeArraycopy(src, sOff, dest, dOff, literalLen);
                     int var10000 = sOff + literalLen;
                     return literalCopyEnd - destOff;
                  }
               }

               LZ4ByteBufferUtils.wildArraycopy(src, sOff, dest, dOff, literalLen);
               sOff += literalLen;
               int matchDec = ByteBufferUtils.readShortLE(src, sOff);
               sOff += 2;
               int matchOff = literalCopyEnd - matchDec;
               if (matchOff < destOff) {
                  throw new LZ4Exception("Malformed input at " + sOff);
               }

               int matchLen = token & 15;
               if (matchLen == 15) {
                  byte len;
                  for(len = -1; sOff < srcEnd && (len = ByteBufferUtils.readByte(src, sOff++)) == -1; matchLen += 255) {
                  }

                  matchLen += len & 255;
               }

               matchLen += 4;
               int matchCopyEnd = literalCopyEnd + matchLen;
               if (matchCopyEnd > destEnd - 8) {
                  if (matchCopyEnd > destEnd) {
                     throw new LZ4Exception("Malformed input at " + sOff);
                  }

                  LZ4ByteBufferUtils.safeIncrementalCopy(dest, matchOff, literalCopyEnd, matchLen);
               } else {
                  LZ4ByteBufferUtils.wildIncrementalCopy(dest, matchOff, literalCopyEnd, matchCopyEnd);
               }

               dOff = matchCopyEnd;
            }
         }
      }
   }
}
