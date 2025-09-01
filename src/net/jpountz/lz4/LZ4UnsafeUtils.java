package net.jpountz.lz4;

import java.nio.ByteOrder;
import net.jpountz.util.UnsafeUtils;
import net.jpountz.util.Utils;

enum LZ4UnsafeUtils {
   static void safeArraycopy(byte[] src, int srcOff, byte[] dest, int destOff, int len) {
      int fastLen = len & -8;
      wildArraycopy(src, srcOff, dest, destOff, fastLen);
      int i = 0;

      for(int slowLen = len & 7; i < slowLen; ++i) {
         UnsafeUtils.writeByte(dest, destOff + fastLen + i, UnsafeUtils.readByte(src, srcOff + fastLen + i));
      }

   }

   static void wildArraycopy(byte[] src, int srcOff, byte[] dest, int destOff, int len) {
      for(int i = 0; i < len; i += 8) {
         UnsafeUtils.writeLong(dest, destOff + i, UnsafeUtils.readLong(src, srcOff + i));
      }

   }

   static void wildIncrementalCopy(byte[] dest, int matchOff, int dOff, int matchCopyEnd) {
      if (dOff - matchOff >= 4) {
         if (dOff - matchOff < 8) {
            UnsafeUtils.writeLong(dest, dOff, UnsafeUtils.readLong(dest, matchOff));
            dOff += dOff - matchOff;
         }
      } else {
         for(int i = 0; i < 4; ++i) {
            UnsafeUtils.writeByte(dest, dOff + i, UnsafeUtils.readByte(dest, matchOff + i));
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

         UnsafeUtils.writeInt(dest, dOff, UnsafeUtils.readInt(dest, matchOff));
         dOff += 4;
         matchOff -= dec;
      }

      while(dOff < matchCopyEnd) {
         UnsafeUtils.writeLong(dest, dOff, UnsafeUtils.readLong(dest, matchOff));
         dOff += 8;
         matchOff += 8;
      }

   }

   static void safeIncrementalCopy(byte[] dest, int matchOff, int dOff, int matchLen) {
      for(int i = 0; i < matchLen; ++i) {
         dest[dOff + i] = dest[matchOff + i];
         UnsafeUtils.writeByte(dest, dOff + i, UnsafeUtils.readByte(dest, matchOff + i));
      }

   }

   static int readShortLittleEndian(byte[] src, int srcOff) {
      short s = UnsafeUtils.readShort(src, srcOff);
      if (Utils.NATIVE_BYTE_ORDER == ByteOrder.BIG_ENDIAN) {
         s = Short.reverseBytes(s);
      }

      return s & '\uffff';
   }

   static void writeShortLittleEndian(byte[] dest, int destOff, int value) {
      short s = (short)value;
      if (Utils.NATIVE_BYTE_ORDER == ByteOrder.BIG_ENDIAN) {
         s = Short.reverseBytes(s);
      }

      UnsafeUtils.writeShort(dest, destOff, s);
   }

   static boolean readIntEquals(byte[] src, int ref, int sOff) {
      return UnsafeUtils.readInt(src, ref) == UnsafeUtils.readInt(src, sOff);
   }

   static int commonBytes(byte[] src, int ref, int sOff, int srcLimit) {
      int matchLen;
      for(matchLen = 0; sOff <= srcLimit - 8; sOff += 8) {
         if (UnsafeUtils.readLong(src, sOff) != UnsafeUtils.readLong(src, ref)) {
            int zeroBits;
            if (Utils.NATIVE_BYTE_ORDER == ByteOrder.BIG_ENDIAN) {
               zeroBits = Long.numberOfLeadingZeros(UnsafeUtils.readLong(src, sOff) ^ UnsafeUtils.readLong(src, ref));
            } else {
               zeroBits = Long.numberOfTrailingZeros(UnsafeUtils.readLong(src, sOff) ^ UnsafeUtils.readLong(src, ref));
            }

            return matchLen + (zeroBits >>> 3);
         }

         matchLen += 8;
         ref += 8;
      }

      while(sOff < srcLimit && UnsafeUtils.readByte(src, ref++) == UnsafeUtils.readByte(src, sOff++)) {
         ++matchLen;
      }

      return matchLen;
   }

   static int writeLen(int len, byte[] dest, int dOff) {
      while(len >= 255) {
         UnsafeUtils.writeByte(dest, dOff++, (int)255);
         len -= 255;
      }

      UnsafeUtils.writeByte(dest, dOff++, len);
      return dOff;
   }

   static int encodeSequence(byte[] src, int anchor, int matchOff, int matchRef, int matchLen, byte[] dest, int dOff, int destEnd) {
      int runLen = matchOff - anchor;
      int tokenOff = dOff++;
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
      dest[dOff++] = (byte)matchDec;
      dest[dOff++] = (byte)(matchDec >>> 8);
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

         dest[tokenOff] = (byte)token;
         return dOff;
      }
   }

   static int commonBytesBackward(byte[] b, int o1, int o2, int l1, int l2) {
      int count;
      for(count = 0; o1 > l1 && o2 > l2; ++count) {
         --o1;
         byte var10000 = UnsafeUtils.readByte(b, o1);
         --o2;
         if (var10000 != UnsafeUtils.readByte(b, o2)) {
            break;
         }
      }

      return count;
   }

   static int lastLiterals(byte[] src, int sOff, int srcLen, byte[] dest, int dOff, int destEnd) {
      return LZ4SafeUtils.lastLiterals(src, sOff, srcLen, dest, dOff, destEnd);
   }
}
