/*     */ package net.jpountz.util;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.ReadOnlyBufferException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum ByteBufferUtils
/*     */ {
/*     */   public static void checkRange(ByteBuffer buf, int off, int len) {
/*  27 */     SafeUtils.checkLength(len);
/*  28 */     if (len > 0) {
/*  29 */       checkRange(buf, off);
/*  30 */       checkRange(buf, off + len - 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void checkRange(ByteBuffer buf, int off) {
/*  35 */     if (off < 0 || off >= buf.capacity()) {
/*  36 */       throw new ArrayIndexOutOfBoundsException(off);
/*     */     }
/*     */   }
/*     */   
/*     */   public static ByteBuffer inLittleEndianOrder(ByteBuffer buf) {
/*  41 */     if (buf.order().equals(ByteOrder.LITTLE_ENDIAN)) {
/*  42 */       return buf;
/*     */     }
/*  44 */     return buf.duplicate().order(ByteOrder.LITTLE_ENDIAN);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ByteBuffer inNativeByteOrder(ByteBuffer buf) {
/*  49 */     if (buf.order().equals(Utils.NATIVE_BYTE_ORDER)) {
/*  50 */       return buf;
/*     */     }
/*  52 */     return buf.duplicate().order(Utils.NATIVE_BYTE_ORDER);
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte readByte(ByteBuffer buf, int i) {
/*  57 */     return buf.get(i);
/*     */   }
/*     */   
/*     */   public static void writeInt(ByteBuffer buf, int i, int v) {
/*  61 */     assert buf.order() == Utils.NATIVE_BYTE_ORDER;
/*  62 */     buf.putInt(i, v);
/*     */   }
/*     */   
/*     */   public static int readInt(ByteBuffer buf, int i) {
/*  66 */     assert buf.order() == Utils.NATIVE_BYTE_ORDER;
/*  67 */     return buf.getInt(i);
/*     */   }
/*     */   
/*     */   public static int readIntLE(ByteBuffer buf, int i) {
/*  71 */     assert buf.order() == ByteOrder.LITTLE_ENDIAN;
/*  72 */     return buf.getInt(i);
/*     */   }
/*     */   
/*     */   public static void writeLong(ByteBuffer buf, int i, long v) {
/*  76 */     assert buf.order() == Utils.NATIVE_BYTE_ORDER;
/*  77 */     buf.putLong(i, v);
/*     */   }
/*     */   
/*     */   public static long readLong(ByteBuffer buf, int i) {
/*  81 */     assert buf.order() == Utils.NATIVE_BYTE_ORDER;
/*  82 */     return buf.getLong(i);
/*     */   }
/*     */   
/*     */   public static long readLongLE(ByteBuffer buf, int i) {
/*  86 */     assert buf.order() == ByteOrder.LITTLE_ENDIAN;
/*  87 */     return buf.getLong(i);
/*     */   }
/*     */   
/*     */   public static void writeByte(ByteBuffer dest, int off, int i) {
/*  91 */     dest.put(off, (byte)i);
/*     */   }
/*     */   
/*     */   public static void writeShortLE(ByteBuffer dest, int off, int i) {
/*  95 */     dest.put(off, (byte)i);
/*  96 */     dest.put(off + 1, (byte)(i >>> 8));
/*     */   }
/*     */   
/*     */   public static void checkNotReadOnly(ByteBuffer buffer) {
/* 100 */     if (buffer.isReadOnly()) {
/* 101 */       throw new ReadOnlyBufferException();
/*     */     }
/*     */   }
/*     */   
/*     */   public static int readShortLE(ByteBuffer buf, int i) {
/* 106 */     return buf.get(i) & 0xFF | (buf.get(i + 1) & 0xFF) << 8;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpount\\util\ByteBufferUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */