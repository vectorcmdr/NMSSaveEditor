/*     */ package net.jpountz.util;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.nio.ByteOrder;
/*     */ import sun.misc.Unsafe;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum UnsafeUtils
/*     */ {
/*     */   private static final Unsafe UNSAFE;
/*     */   private static final long BYTE_ARRAY_OFFSET;
/*     */   private static final int BYTE_ARRAY_SCALE;
/*     */   private static final long INT_ARRAY_OFFSET;
/*     */   private static final int INT_ARRAY_SCALE;
/*     */   private static final long SHORT_ARRAY_OFFSET;
/*     */   private static final int SHORT_ARRAY_SCALE;
/*     */   
/*     */   static {
/*     */     try {
/*  39 */       Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
/*  40 */       theUnsafe.setAccessible(true);
/*  41 */       UNSAFE = (Unsafe)theUnsafe.get((Object)null);
/*  42 */       BYTE_ARRAY_OFFSET = UNSAFE.arrayBaseOffset(byte[].class);
/*  43 */       BYTE_ARRAY_SCALE = UNSAFE.arrayIndexScale(byte[].class);
/*  44 */       INT_ARRAY_OFFSET = UNSAFE.arrayBaseOffset(int[].class);
/*  45 */       INT_ARRAY_SCALE = UNSAFE.arrayIndexScale(int[].class);
/*  46 */       SHORT_ARRAY_OFFSET = UNSAFE.arrayBaseOffset(short[].class);
/*  47 */       SHORT_ARRAY_SCALE = UNSAFE.arrayIndexScale(short[].class);
/*  48 */     } catch (IllegalAccessException e) {
/*  49 */       throw new ExceptionInInitializerError("Cannot access Unsafe");
/*  50 */     } catch (NoSuchFieldException e) {
/*  51 */       throw new ExceptionInInitializerError("Cannot access Unsafe");
/*  52 */     } catch (SecurityException e) {
/*  53 */       throw new ExceptionInInitializerError("Cannot access Unsafe");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void checkRange(byte[] buf, int off) {
/*  58 */     SafeUtils.checkRange(buf, off);
/*     */   }
/*     */   
/*     */   public static void checkRange(byte[] buf, int off, int len) {
/*  62 */     SafeUtils.checkRange(buf, off, len);
/*     */   }
/*     */   
/*     */   public static void checkLength(int len) {
/*  66 */     SafeUtils.checkLength(len);
/*     */   }
/*     */   
/*     */   public static byte readByte(byte[] src, int srcOff) {
/*  70 */     return UNSAFE.getByte(src, BYTE_ARRAY_OFFSET + (BYTE_ARRAY_SCALE * srcOff));
/*     */   }
/*     */   
/*     */   public static void writeByte(byte[] src, int srcOff, byte value) {
/*  74 */     UNSAFE.putByte(src, BYTE_ARRAY_OFFSET + (BYTE_ARRAY_SCALE * srcOff), value);
/*     */   }
/*     */   
/*     */   public static void writeByte(byte[] src, int srcOff, int value) {
/*  78 */     writeByte(src, srcOff, (byte)value);
/*     */   }
/*     */   
/*     */   public static long readLong(byte[] src, int srcOff) {
/*  82 */     return UNSAFE.getLong(src, BYTE_ARRAY_OFFSET + srcOff);
/*     */   }
/*     */   
/*     */   public static long readLongLE(byte[] src, int srcOff) {
/*  86 */     long i = readLong(src, srcOff);
/*  87 */     if (Utils.NATIVE_BYTE_ORDER == ByteOrder.BIG_ENDIAN) {
/*  88 */       i = Long.reverseBytes(i);
/*     */     }
/*  90 */     return i;
/*     */   }
/*     */   
/*     */   public static void writeLong(byte[] dest, int destOff, long value) {
/*  94 */     UNSAFE.putLong(dest, BYTE_ARRAY_OFFSET + destOff, value);
/*     */   }
/*     */   
/*     */   public static int readInt(byte[] src, int srcOff) {
/*  98 */     return UNSAFE.getInt(src, BYTE_ARRAY_OFFSET + srcOff);
/*     */   }
/*     */   
/*     */   public static int readIntLE(byte[] src, int srcOff) {
/* 102 */     int i = readInt(src, srcOff);
/* 103 */     if (Utils.NATIVE_BYTE_ORDER == ByteOrder.BIG_ENDIAN) {
/* 104 */       i = Integer.reverseBytes(i);
/*     */     }
/* 106 */     return i;
/*     */   }
/*     */   
/*     */   public static void writeInt(byte[] dest, int destOff, int value) {
/* 110 */     UNSAFE.putInt(dest, BYTE_ARRAY_OFFSET + destOff, value);
/*     */   }
/*     */   
/*     */   public static short readShort(byte[] src, int srcOff) {
/* 114 */     return UNSAFE.getShort(src, BYTE_ARRAY_OFFSET + srcOff);
/*     */   }
/*     */   
/*     */   public static int readShortLE(byte[] src, int srcOff) {
/* 118 */     short s = readShort(src, srcOff);
/* 119 */     if (Utils.NATIVE_BYTE_ORDER == ByteOrder.BIG_ENDIAN) {
/* 120 */       s = Short.reverseBytes(s);
/*     */     }
/* 122 */     return s & 0xFFFF;
/*     */   }
/*     */   
/*     */   public static void writeShort(byte[] dest, int destOff, short value) {
/* 126 */     UNSAFE.putShort(dest, BYTE_ARRAY_OFFSET + destOff, value);
/*     */   }
/*     */   
/*     */   public static void writeShortLE(byte[] buf, int off, int v) {
/* 130 */     writeByte(buf, off, (byte)v);
/* 131 */     writeByte(buf, off + 1, (byte)(v >>> 8));
/*     */   }
/*     */   
/*     */   public static int readInt(int[] src, int srcOff) {
/* 135 */     return UNSAFE.getInt(src, INT_ARRAY_OFFSET + (INT_ARRAY_SCALE * srcOff));
/*     */   }
/*     */   
/*     */   public static void writeInt(int[] dest, int destOff, int value) {
/* 139 */     UNSAFE.putInt(dest, INT_ARRAY_OFFSET + (INT_ARRAY_SCALE * destOff), value);
/*     */   }
/*     */   
/*     */   public static int readShort(short[] src, int srcOff) {
/* 143 */     return UNSAFE.getShort(src, SHORT_ARRAY_OFFSET + (SHORT_ARRAY_SCALE * srcOff)) & 0xFFFF;
/*     */   }
/*     */   
/*     */   public static void writeShort(short[] dest, int destOff, int value) {
/* 147 */     UNSAFE.putShort(dest, SHORT_ARRAY_OFFSET + (SHORT_ARRAY_SCALE * destOff), (short)value);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpount\\util\UnsafeUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */