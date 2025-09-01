package net.jpountz.lz4;

import java.nio.ByteBuffer;
import java.util.Arrays;
import net.jpountz.util.ByteBufferUtils;
import net.jpountz.util.UnsafeUtils;

final class LZ4JavaUnsafeCompressor extends LZ4Compressor {
   public static final LZ4Compressor INSTANCE = new LZ4JavaUnsafeCompressor();

   static int compress64k(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff, int destEnd) {
      int srcEnd = srcOff + srcLen;
      int srcLimit = srcEnd - 5;
      int mflimit = srcEnd - 12;
      int dOff = destOff;
      int anchor = srcOff;
      if (srcLen >= 13) {
         short[] hashTable = new short[8192];
         int sOff = srcOff + 1;

         label53:
         while(true) {
            int forwardOff = sOff;
            int step = 1;
            int var16 = 1 << LZ4Constants.SKIP_STRENGTH;

            int ref;
            int excess;
            do {
               sOff = forwardOff;
               forwardOff += step;
               step = var16++ >>> LZ4Constants.SKIP_STRENGTH;
               if (forwardOff > mflimit) {
                  break label53;
               }

               excess = LZ4Utils.hash64k(UnsafeUtils.readInt(src, sOff));
               ref = srcOff + UnsafeUtils.readShort(hashTable, excess);
               UnsafeUtils.writeShort(hashTable, excess, sOff - srcOff);
            } while(!LZ4UnsafeUtils.readIntEquals(src, ref, sOff));

            excess = LZ4UnsafeUtils.commonBytesBackward(src, ref, sOff, srcOff, anchor);
            sOff -= excess;
            ref -= excess;
            int runLen = sOff - anchor;
            int tokenOff = dOff++;
            if (dOff + runLen + 8 + (runLen >>> 8) > destEnd) {
               throw new LZ4Exception("maxDestLen is too small");
            }

            if (runLen >= 15) {
               UnsafeUtils.writeByte(dest, tokenOff, (int)240);
               dOff = LZ4UnsafeUtils.writeLen(runLen - 15, dest, dOff);
            } else {
               UnsafeUtils.writeByte(dest, tokenOff, runLen << 4);
            }

            LZ4UnsafeUtils.wildArraycopy(src, anchor, dest, dOff, runLen);
            dOff += runLen;

            while(true) {
               UnsafeUtils.writeShortLE(dest, dOff, (short)(sOff - ref));
               dOff += 2;
               sOff += 4;
               ref += 4;
               int matchLen = LZ4UnsafeUtils.commonBytes(src, ref, sOff, srcLimit);
               if (dOff + 6 + (matchLen >>> 8) > destEnd) {
                  throw new LZ4Exception("maxDestLen is too small");
               }

               sOff += matchLen;
               if (matchLen >= 15) {
                  UnsafeUtils.writeByte(dest, tokenOff, UnsafeUtils.readByte(dest, tokenOff) | 15);
                  dOff = LZ4UnsafeUtils.writeLen(matchLen - 15, dest, dOff);
               } else {
                  UnsafeUtils.writeByte(dest, tokenOff, UnsafeUtils.readByte(dest, tokenOff) | matchLen);
               }

               if (sOff > mflimit) {
                  anchor = sOff;
                  break label53;
               }

               UnsafeUtils.writeShort(hashTable, LZ4Utils.hash64k(UnsafeUtils.readInt(src, sOff - 2)), sOff - 2 - srcOff);
               int h = LZ4Utils.hash64k(UnsafeUtils.readInt(src, sOff));
               ref = srcOff + UnsafeUtils.readShort(hashTable, h);
               UnsafeUtils.writeShort(hashTable, h, sOff - srcOff);
               if (!LZ4UnsafeUtils.readIntEquals(src, sOff, ref)) {
                  anchor = sOff++;
                  break;
               }

               tokenOff = dOff++;
               UnsafeUtils.writeByte(dest, tokenOff, (int)0);
            }
         }
      }

      dOff = LZ4UnsafeUtils.lastLiterals(src, anchor, srcEnd - anchor, dest, dOff, destEnd);
      return dOff - destOff;
   }

   public int compress(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff, int maxDestLen) {
      UnsafeUtils.checkRange(src, srcOff, srcLen);
      UnsafeUtils.checkRange(dest, destOff, maxDestLen);
      int destEnd = destOff + maxDestLen;
      if (srcLen < 65547) {
         return compress64k(src, srcOff, srcLen, dest, destOff, destEnd);
      } else {
         int srcEnd = srcOff + srcLen;
         int srcLimit = srcEnd - 5;
         int mflimit = srcEnd - 12;
         int dOff = destOff;
         int sOff = srcOff + 1;
         int anchor = srcOff;
         int[] hashTable = new int[4096];
         Arrays.fill(hashTable, srcOff);

         label63:
         while(true) {
            int forwardOff = sOff;
            int step = 1;
            int var18 = 1 << LZ4Constants.SKIP_STRENGTH;

            while(true) {
               sOff = forwardOff;
               forwardOff += step;
               step = var18++ >>> LZ4Constants.SKIP_STRENGTH;
               if (forwardOff <= mflimit) {
                  int excess = LZ4Utils.hash(UnsafeUtils.readInt(src, sOff));
                  int ref = UnsafeUtils.readInt(hashTable, excess);
                  int back = sOff - ref;
                  UnsafeUtils.writeInt(hashTable, excess, sOff);
                  if (back >= 65536 || !LZ4UnsafeUtils.readIntEquals(src, ref, sOff)) {
                     continue;
                  }

                  excess = LZ4UnsafeUtils.commonBytesBackward(src, ref, sOff, srcOff, anchor);
                  sOff -= excess;
                  ref -= excess;
                  int runLen = sOff - anchor;
                  int tokenOff = dOff++;
                  if (dOff + runLen + 8 + (runLen >>> 8) > destEnd) {
                     throw new LZ4Exception("maxDestLen is too small");
                  }

                  if (runLen >= 15) {
                     UnsafeUtils.writeByte(dest, tokenOff, (int)240);
                     dOff = LZ4UnsafeUtils.writeLen(runLen - 15, dest, dOff);
                  } else {
                     UnsafeUtils.writeByte(dest, tokenOff, runLen << 4);
                  }

                  LZ4UnsafeUtils.wildArraycopy(src, anchor, dest, dOff, runLen);
                  dOff += runLen;

                  while(true) {
                     UnsafeUtils.writeShortLE(dest, dOff, back);
                     dOff += 2;
                     sOff += 4;
                     int matchLen = LZ4UnsafeUtils.commonBytes(src, ref + 4, sOff, srcLimit);
                     if (dOff + 6 + (matchLen >>> 8) > destEnd) {
                        throw new LZ4Exception("maxDestLen is too small");
                     }

                     sOff += matchLen;
                     if (matchLen >= 15) {
                        UnsafeUtils.writeByte(dest, tokenOff, UnsafeUtils.readByte(dest, tokenOff) | 15);
                        dOff = LZ4UnsafeUtils.writeLen(matchLen - 15, dest, dOff);
                     } else {
                        UnsafeUtils.writeByte(dest, tokenOff, UnsafeUtils.readByte(dest, tokenOff) | matchLen);
                     }

                     if (sOff > mflimit) {
                        anchor = sOff;
                        break;
                     }

                     UnsafeUtils.writeInt(hashTable, LZ4Utils.hash(UnsafeUtils.readInt(src, sOff - 2)), sOff - 2);
                     int h = LZ4Utils.hash(UnsafeUtils.readInt(src, sOff));
                     ref = UnsafeUtils.readInt(hashTable, h);
                     UnsafeUtils.writeInt(hashTable, h, sOff);
                     back = sOff - ref;
                     if (back >= 65536 || !LZ4UnsafeUtils.readIntEquals(src, ref, sOff)) {
                        anchor = sOff++;
                        continue label63;
                     }

                     tokenOff = dOff++;
                     UnsafeUtils.writeByte(dest, tokenOff, (int)0);
                  }
               }

               dOff = LZ4UnsafeUtils.lastLiterals(src, anchor, srcEnd - anchor, dest, dOff, destEnd);
               return dOff - destOff;
            }
         }
      }
   }

   static int compress64k(ByteBuffer src, int srcOff, int srcLen, ByteBuffer dest, int destOff, int destEnd) {
      int srcEnd = srcOff + srcLen;
      int srcLimit = srcEnd - 5;
      int mflimit = srcEnd - 12;
      int dOff = destOff;
      int anchor = srcOff;
      if (srcLen >= 13) {
         short[] hashTable = new short[8192];
         int sOff = srcOff + 1;

         label53:
         while(true) {
            int forwardOff = sOff;
            int step = 1;
            int var16 = 1 << LZ4Constants.SKIP_STRENGTH;

            int ref;
            int excess;
            do {
               sOff = forwardOff;
               forwardOff += step;
               step = var16++ >>> LZ4Constants.SKIP_STRENGTH;
               if (forwardOff > mflimit) {
                  break label53;
               }

               excess = LZ4Utils.hash64k(ByteBufferUtils.readInt(src, sOff));
               ref = srcOff + UnsafeUtils.readShort(hashTable, excess);
               UnsafeUtils.writeShort(hashTable, excess, sOff - srcOff);
            } while(!LZ4ByteBufferUtils.readIntEquals(src, ref, sOff));

            excess = LZ4ByteBufferUtils.commonBytesBackward(src, ref, sOff, srcOff, anchor);
            sOff -= excess;
            ref -= excess;
            int runLen = sOff - anchor;
            int tokenOff = dOff++;
            if (dOff + runLen + 8 + (runLen >>> 8) > destEnd) {
               throw new LZ4Exception("maxDestLen is too small");
            }

            if (runLen >= 15) {
               ByteBufferUtils.writeByte(dest, tokenOff, 240);
               dOff = LZ4ByteBufferUtils.writeLen(runLen - 15, dest, dOff);
            } else {
               ByteBufferUtils.writeByte(dest, tokenOff, runLen << 4);
            }

            LZ4ByteBufferUtils.wildArraycopy(src, anchor, dest, dOff, runLen);
            dOff += runLen;

            while(true) {
               ByteBufferUtils.writeShortLE(dest, dOff, (short)(sOff - ref));
               dOff += 2;
               sOff += 4;
               ref += 4;
               int matchLen = LZ4ByteBufferUtils.commonBytes(src, ref, sOff, srcLimit);
               if (dOff + 6 + (matchLen >>> 8) > destEnd) {
                  throw new LZ4Exception("maxDestLen is too small");
               }

               sOff += matchLen;
               if (matchLen >= 15) {
                  ByteBufferUtils.writeByte(dest, tokenOff, ByteBufferUtils.readByte(dest, tokenOff) | 15);
                  dOff = LZ4ByteBufferUtils.writeLen(matchLen - 15, dest, dOff);
               } else {
                  ByteBufferUtils.writeByte(dest, tokenOff, ByteBufferUtils.readByte(dest, tokenOff) | matchLen);
               }

               if (sOff > mflimit) {
                  anchor = sOff;
                  break label53;
               }

               UnsafeUtils.writeShort(hashTable, LZ4Utils.hash64k(ByteBufferUtils.readInt(src, sOff - 2)), sOff - 2 - srcOff);
               int h = LZ4Utils.hash64k(ByteBufferUtils.readInt(src, sOff));
               ref = srcOff + UnsafeUtils.readShort(hashTable, h);
               UnsafeUtils.writeShort(hashTable, h, sOff - srcOff);
               if (!LZ4ByteBufferUtils.readIntEquals(src, sOff, ref)) {
                  anchor = sOff++;
                  break;
               }

               tokenOff = dOff++;
               ByteBufferUtils.writeByte(dest, tokenOff, 0);
            }
         }
      }

      dOff = LZ4ByteBufferUtils.lastLiterals(src, anchor, srcEnd - anchor, dest, dOff, destEnd);
      return dOff - destOff;
   }

   public int compress(ByteBuffer src, int srcOff, int srcLen, ByteBuffer dest, int destOff, int maxDestLen) {
      if (src.hasArray() && dest.hasArray()) {
         return this.compress(src.array(), srcOff + src.arrayOffset(), srcLen, dest.array(), destOff + dest.arrayOffset(), maxDestLen);
      } else {
         src = ByteBufferUtils.inNativeByteOrder(src);
         dest = ByteBufferUtils.inNativeByteOrder(dest);
         ByteBufferUtils.checkRange(src, srcOff, srcLen);
         ByteBufferUtils.checkRange(dest, destOff, maxDestLen);
         int destEnd = destOff + maxDestLen;
         if (srcLen < 65547) {
            return compress64k(src, srcOff, srcLen, dest, destOff, destEnd);
         } else {
            int srcEnd = srcOff + srcLen;
            int srcLimit = srcEnd - 5;
            int mflimit = srcEnd - 12;
            int dOff = destOff;
            int sOff = srcOff + 1;
            int anchor = srcOff;
            int[] hashTable = new int[4096];
            Arrays.fill(hashTable, srcOff);

            label69:
            while(true) {
               int forwardOff = sOff;
               int step = 1;
               int var18 = 1 << LZ4Constants.SKIP_STRENGTH;

               while(true) {
                  sOff = forwardOff;
                  forwardOff += step;
                  step = var18++ >>> LZ4Constants.SKIP_STRENGTH;
                  if (forwardOff <= mflimit) {
                     int excess = LZ4Utils.hash(ByteBufferUtils.readInt(src, sOff));
                     int ref = UnsafeUtils.readInt(hashTable, excess);
                     int back = sOff - ref;
                     UnsafeUtils.writeInt(hashTable, excess, sOff);
                     if (back >= 65536 || !LZ4ByteBufferUtils.readIntEquals(src, ref, sOff)) {
                        continue;
                     }

                     excess = LZ4ByteBufferUtils.commonBytesBackward(src, ref, sOff, srcOff, anchor);
                     sOff -= excess;
                     ref -= excess;
                     int runLen = sOff - anchor;
                     int tokenOff = dOff++;
                     if (dOff + runLen + 8 + (runLen >>> 8) > destEnd) {
                        throw new LZ4Exception("maxDestLen is too small");
                     }

                     if (runLen >= 15) {
                        ByteBufferUtils.writeByte(dest, tokenOff, 240);
                        dOff = LZ4ByteBufferUtils.writeLen(runLen - 15, dest, dOff);
                     } else {
                        ByteBufferUtils.writeByte(dest, tokenOff, runLen << 4);
                     }

                     LZ4ByteBufferUtils.wildArraycopy(src, anchor, dest, dOff, runLen);
                     dOff += runLen;

                     while(true) {
                        ByteBufferUtils.writeShortLE(dest, dOff, back);
                        dOff += 2;
                        sOff += 4;
                        int matchLen = LZ4ByteBufferUtils.commonBytes(src, ref + 4, sOff, srcLimit);
                        if (dOff + 6 + (matchLen >>> 8) > destEnd) {
                           throw new LZ4Exception("maxDestLen is too small");
                        }

                        sOff += matchLen;
                        if (matchLen >= 15) {
                           ByteBufferUtils.writeByte(dest, tokenOff, ByteBufferUtils.readByte(dest, tokenOff) | 15);
                           dOff = LZ4ByteBufferUtils.writeLen(matchLen - 15, dest, dOff);
                        } else {
                           ByteBufferUtils.writeByte(dest, tokenOff, ByteBufferUtils.readByte(dest, tokenOff) | matchLen);
                        }

                        if (sOff > mflimit) {
                           anchor = sOff;
                           break;
                        }

                        UnsafeUtils.writeInt(hashTable, LZ4Utils.hash(ByteBufferUtils.readInt(src, sOff - 2)), sOff - 2);
                        int h = LZ4Utils.hash(ByteBufferUtils.readInt(src, sOff));
                        ref = UnsafeUtils.readInt(hashTable, h);
                        UnsafeUtils.writeInt(hashTable, h, sOff);
                        back = sOff - ref;
                        if (back >= 65536 || !LZ4ByteBufferUtils.readIntEquals(src, ref, sOff)) {
                           anchor = sOff++;
                           continue label69;
                        }

                        tokenOff = dOff++;
                        ByteBufferUtils.writeByte(dest, tokenOff, 0);
                     }
                  }

                  dOff = LZ4ByteBufferUtils.lastLiterals(src, anchor, srcEnd - anchor, dest, dOff, destEnd);
                  return dOff - destOff;
               }
            }
         }
      }
   }
}
