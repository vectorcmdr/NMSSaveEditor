package net.jpountz.lz4;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import net.jpountz.util.ByteBufferUtils;

enum LZ4ByteBufferUtils {
   static int hash(ByteBuffer buf, int i) {
      return LZ4Utils.hash(ByteBufferUtils.readInt(buf, i));
   }

   static int hash64k(ByteBuffer buf, int i) {
      return LZ4Utils.hash64k(ByteBufferUtils.readInt(buf, i));
   }

   static boolean readIntEquals(ByteBuffer buf, int i, int j) {
      return buf.getInt(i) == buf.getInt(j);
   }

   static void safeIncrementalCopy(ByteBuffer dest, int matchOff, int dOff, int matchLen) {
      for(int i = 0; i < matchLen; ++i) {
         dest.put(dOff + i, dest.get(matchOff + i));
      }

   }

   static void wildIncrementalCopy(ByteBuffer dest, int matchOff, int dOff, int matchCopyEnd) {
      if (dOff - matchOff >= 4) {
         if (dOff - matchOff < 8) {
            ByteBufferUtils.writeLong(dest, dOff, ByteBufferUtils.readLong(dest, matchOff));
            dOff += dOff - matchOff;
         }
      } else {
         for(int i = 0; i < 4; ++i) {
            ByteBufferUtils.writeByte(dest, dOff + i, ByteBufferUtils.readByte(dest, matchOff + i));
         }

         dOff += 4;
         matchOff += 4;
         int dec = 0;

         assert dOff >= matchOff && dOff - matchOff < 8;

         switch(dOff - matchOff) {
         case 1:
            matchOff -= 3;
            break;
         case 2:
            matchOff -= 2;
            break;
         case 3:
            matchOff -= 3;
            dec = -1;
         case 4:
         default:
            break;
         case 5:
            dec = 1;
            break;
         case 6:
            dec = 2;
            break;
         case 7:
            dec = 3;
         }

         ByteBufferUtils.writeInt(dest, dOff, ByteBufferUtils.readInt(dest, matchOff));
         dOff += 4;
         matchOff -= dec;
      }

      while(dOff < matchCopyEnd) {
         ByteBufferUtils.writeLong(dest, dOff, ByteBufferUtils.readLong(dest, matchOff));
         dOff += 8;
         matchOff += 8;
      }

   }

   static int commonBytes(ByteBuffer src, int ref, int sOff, int srcLimit) {
      int matchLen;
      for(matchLen = 0; sOff <= srcLimit - 8; sOff += 8) {
         if (ByteBufferUtils.readLong(src, sOff) != ByteBufferUtils.readLong(src, ref)) {
            int zeroBits;
            if (src.order() == ByteOrder.BIG_ENDIAN) {
               zeroBits = Long.numberOfLeadingZeros(ByteBufferUtils.readLong(src, sOff) ^ ByteBufferUtils.readLong(src, ref));
            } else {
               zeroBits = Long.numberOfTrailingZeros(ByteBufferUtils.readLong(src, sOff) ^ ByteBufferUtils.readLong(src, ref));
            }

            return matchLen + (zeroBits >>> 3);
         }

         matchLen += 8;
         ref += 8;
      }

      while(sOff < srcLimit && ByteBufferUtils.readByte(src, ref++) == ByteBufferUtils.readByte(src, sOff++)) {
         ++matchLen;
      }

      return matchLen;
   }

   static int commonBytesBackward(ByteBuffer b, int o1, int o2, int l1, int l2) {
      int count;
      for(count = 0; o1 > l1 && o2 > l2; ++count) {
         --o1;
         byte var10000 = b.get(o1);
         --o2;
         if (var10000 != b.get(o2)) {
            break;
         }
      }

      return count;
   }

   static void safeArraycopy(ByteBuffer src, int sOff, ByteBuffer dest, int dOff, int len) {
      for(int i = 0; i < len; ++i) {
         dest.put(dOff + i, src.get(sOff + i));
      }

   }

   static void wildArraycopy(ByteBuffer src, int sOff, ByteBuffer dest, int dOff, int len) {
      assert src.order().equals(dest.order());

      try {
         for(int i = 0; i < len; i += 8) {
            dest.putLong(dOff + i, src.getLong(sOff + i));
         }

      } catch (IndexOutOfBoundsException var6) {
         throw new LZ4Exception("Malformed input at offset " + sOff);
      }
   }

   static int encodeSequence(ByteBuffer src, int anchor, int matchOff, int matchRef, int matchLen, ByteBuffer dest, int dOff, int destEnd) {
      int runLen = matchOff - anchor;
      int tokenOff = dOff++;
      if (dOff + runLen + 8 + (runLen >>> 8) > destEnd) {
         throw new LZ4Exception("maxDestLen is too small");
      } else {
         int token;
         if (runLen >= 15) {
            token = -16;
            dOff = writeLen(runLen - 15, dest, dOff);
         } else {
            token = runLen << 4;
         }

         wildArraycopy(src, anchor, dest, dOff, runLen);
         dOff += runLen;
         int matchDec = matchOff - matchRef;
         dest.put(dOff++, (byte)matchDec);
         dest.put(dOff++, (byte)(matchDec >>> 8));
         matchLen -= 4;
         if (dOff + 6 + (matchLen >>> 8) > destEnd) {
            throw new LZ4Exception("maxDestLen is too small");
         } else {
            if (matchLen >= 15) {
               token |= 15;
               dOff = writeLen(matchLen - 15, dest, dOff);
            } else {
               token |= matchLen;
            }

            dest.put(tokenOff, (byte)token);
            return dOff;
         }
      }
   }

   static int lastLiterals(ByteBuffer src, int sOff, int srcLen, ByteBuffer dest, int dOff, int destEnd) {
      if (dOff + srcLen + 1 + (srcLen + 255 - 15) / 255 > destEnd) {
         throw new LZ4Exception();
      } else {
         if (srcLen >= 15) {
            dest.put(dOff++, (byte)-16);
            dOff = writeLen(srcLen - 15, dest, dOff);
         } else {
            dest.put(dOff++, (byte)(srcLen << 4));
         }

         safeArraycopy(src, sOff, dest, dOff, srcLen);
         dOff += srcLen;
         return dOff;
      }
   }

   static int writeLen(int len, ByteBuffer dest, int dOff) {
      while(len >= 255) {
         dest.put(dOff++, (byte)-1);
         len -= 255;
      }

      dest.put(dOff++, (byte)len);
      return dOff;
   }

   static void copyTo(LZ4ByteBufferUtils.Match m1, LZ4ByteBufferUtils.Match m2) {
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
