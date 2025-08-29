/*    */ package net.jpountz.util;
/*    */ 
/*    */ import java.nio.ByteOrder;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum SafeUtils
/*    */ {
/*    */   public static void checkRange(byte[] buf, int off) {
/* 25 */     if (off < 0 || off >= buf.length) {
/* 26 */       throw new ArrayIndexOutOfBoundsException(off);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void checkRange(byte[] buf, int off, int len) {
/* 31 */     checkLength(len);
/* 32 */     if (len > 0) {
/* 33 */       checkRange(buf, off);
/* 34 */       checkRange(buf, off + len - 1);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void checkLength(int len) {
/* 39 */     if (len < 0) {
/* 40 */       throw new IllegalArgumentException("lengths must be >= 0");
/*    */     }
/*    */   }
/*    */   
/*    */   public static byte readByte(byte[] buf, int i) {
/* 45 */     return buf[i];
/*    */   }
/*    */   
/*    */   public static int readIntBE(byte[] buf, int i) {
/* 49 */     return (buf[i] & 0xFF) << 24 | (buf[i + 1] & 0xFF) << 16 | (buf[i + 2] & 0xFF) << 8 | buf[i + 3] & 0xFF;
/*    */   }
/*    */   
/*    */   public static int readIntLE(byte[] buf, int i) {
/* 53 */     return buf[i] & 0xFF | (buf[i + 1] & 0xFF) << 8 | (buf[i + 2] & 0xFF) << 16 | (buf[i + 3] & 0xFF) << 24;
/*    */   }
/*    */   
/*    */   public static int readInt(byte[] buf, int i) {
/* 57 */     if (Utils.NATIVE_BYTE_ORDER == ByteOrder.BIG_ENDIAN) {
/* 58 */       return readIntBE(buf, i);
/*    */     }
/* 60 */     return readIntLE(buf, i);
/*    */   }
/*    */ 
/*    */   
/*    */   public static long readLongLE(byte[] buf, int i) {
/* 65 */     return buf[i] & 0xFFL | (buf[i + 1] & 0xFFL) << 8L | (buf[i + 2] & 0xFFL) << 16L | (buf[i + 3] & 0xFFL) << 24L | (buf[i + 4] & 0xFFL) << 32L | (buf[i + 5] & 0xFFL) << 40L | (buf[i + 6] & 0xFFL) << 48L | (buf[i + 7] & 0xFFL) << 56L;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void writeShortLE(byte[] buf, int off, int v) {
/* 70 */     buf[off++] = (byte)v;
/* 71 */     buf[off++] = (byte)(v >>> 8);
/*    */   }
/*    */   
/*    */   public static void writeInt(int[] buf, int off, int v) {
/* 75 */     buf[off] = v;
/*    */   }
/*    */   
/*    */   public static int readInt(int[] buf, int off) {
/* 79 */     return buf[off];
/*    */   }
/*    */   
/*    */   public static void writeByte(byte[] dest, int off, int i) {
/* 83 */     dest[off] = (byte)i;
/*    */   }
/*    */   
/*    */   public static void writeShort(short[] buf, int off, int v) {
/* 87 */     buf[off] = (short)v;
/*    */   }
/*    */   
/*    */   public static int readShortLE(byte[] buf, int i) {
/* 91 */     return buf[i] & 0xFF | (buf[i + 1] & 0xFF) << 8;
/*    */   }
/*    */   
/*    */   public static int readShort(short[] buf, int off) {
/* 95 */     return buf[off] & 0xFFFF;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpount\\util\SafeUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */