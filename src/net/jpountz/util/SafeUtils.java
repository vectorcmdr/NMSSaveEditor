package net.jpountz.util;

import java.nio.ByteOrder;

public enum SafeUtils {
   public static void checkRange(byte[] buf, int off) {
      if (off < 0 || off >= buf.length) {
         throw new ArrayIndexOutOfBoundsException(off);
      }
   }

   public static void checkRange(byte[] buf, int off, int len) {
      checkLength(len);
      if (len > 0) {
         checkRange(buf, off);
         checkRange(buf, off + len - 1);
      }

   }

   public static void checkLength(int len) {
      if (len < 0) {
         throw new IllegalArgumentException("lengths must be >= 0");
      }
   }

   public static byte readByte(byte[] buf, int i) {
      return buf[i];
   }

   public static int readIntBE(byte[] buf, int i) {
      return (buf[i] & 255) << 24 | (buf[i + 1] & 255) << 16 | (buf[i + 2] & 255) << 8 | buf[i + 3] & 255;
   }

   public static int readIntLE(byte[] buf, int i) {
      return buf[i] & 255 | (buf[i + 1] & 255) << 8 | (buf[i + 2] & 255) << 16 | (buf[i + 3] & 255) << 24;
   }

   public static int readInt(byte[] buf, int i) {
      return Utils.NATIVE_BYTE_ORDER == ByteOrder.BIG_ENDIAN ? readIntBE(buf, i) : readIntLE(buf, i);
   }

   public static long readLongLE(byte[] buf, int i) {
      return (long)buf[i] & 255L | ((long)buf[i + 1] & 255L) << 8 | ((long)buf[i + 2] & 255L) << 16 | ((long)buf[i + 3] & 255L) << 24 | ((long)buf[i + 4] & 255L) << 32 | ((long)buf[i + 5] & 255L) << 40 | ((long)buf[i + 6] & 255L) << 48 | ((long)buf[i + 7] & 255L) << 56;
   }

   public static void writeShortLE(byte[] buf, int off, int v) {
      buf[off++] = (byte)v;
      buf[off++] = (byte)(v >>> 8);
   }

   public static void writeInt(int[] buf, int off, int v) {
      buf[off] = v;
   }

   public static int readInt(int[] buf, int off) {
      return buf[off];
   }

   public static void writeByte(byte[] dest, int off, int i) {
      dest[off] = (byte)i;
   }

   public static void writeShort(short[] buf, int off, int v) {
      buf[off] = (short)v;
   }

   public static int readShortLE(byte[] buf, int i) {
      return buf[i] & 255 | (buf[i + 1] & 255) << 8;
   }

   public static int readShort(short[] buf, int off) {
      return buf[off] & '\uffff';
   }
}
