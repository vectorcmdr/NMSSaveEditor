/*     */ package net.jpountz.xxhash;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import net.jpountz.util.ByteBufferUtils;
/*     */ import net.jpountz.util.UnsafeUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class XXHash32JavaUnsafe
/*     */   extends XXHash32
/*     */ {
/*  18 */   public static final XXHash32 INSTANCE = new XXHash32JavaUnsafe();
/*     */ 
/*     */ 
/*     */   
/*     */   public int hash(byte[] buf, int off, int len, int seed) {
/*     */     int h32;
/*  24 */     UnsafeUtils.checkRange(buf, off, len);
/*     */     
/*  26 */     int end = off + len;
/*     */ 
/*     */     
/*  29 */     if (len >= 16) {
/*  30 */       int limit = end - 16;
/*  31 */       int v1 = seed + -1640531535 + -2048144777;
/*  32 */       int v2 = seed + -2048144777;
/*  33 */       int v3 = seed + 0;
/*  34 */       int v4 = seed - -1640531535;
/*     */       do {
/*  36 */         v1 += UnsafeUtils.readIntLE(buf, off) * -2048144777;
/*  37 */         v1 = Integer.rotateLeft(v1, 13);
/*  38 */         v1 *= -1640531535;
/*  39 */         off += 4;
/*     */         
/*  41 */         v2 += UnsafeUtils.readIntLE(buf, off) * -2048144777;
/*  42 */         v2 = Integer.rotateLeft(v2, 13);
/*  43 */         v2 *= -1640531535;
/*  44 */         off += 4;
/*     */         
/*  46 */         v3 += UnsafeUtils.readIntLE(buf, off) * -2048144777;
/*  47 */         v3 = Integer.rotateLeft(v3, 13);
/*  48 */         v3 *= -1640531535;
/*  49 */         off += 4;
/*     */         
/*  51 */         v4 += UnsafeUtils.readIntLE(buf, off) * -2048144777;
/*  52 */         v4 = Integer.rotateLeft(v4, 13);
/*  53 */         v4 *= -1640531535;
/*  54 */         off += 4;
/*  55 */       } while (off <= limit);
/*     */       
/*  57 */       h32 = Integer.rotateLeft(v1, 1) + Integer.rotateLeft(v2, 7) + Integer.rotateLeft(v3, 12) + Integer.rotateLeft(v4, 18);
/*     */     } else {
/*  59 */       h32 = seed + 374761393;
/*     */     } 
/*     */     
/*  62 */     h32 += len;
/*     */     
/*  64 */     while (off <= end - 4) {
/*  65 */       h32 += UnsafeUtils.readIntLE(buf, off) * -1028477379;
/*  66 */       h32 = Integer.rotateLeft(h32, 17) * 668265263;
/*  67 */       off += 4;
/*     */     } 
/*     */     
/*  70 */     while (off < end) {
/*  71 */       h32 += (UnsafeUtils.readByte(buf, off) & 0xFF) * 374761393;
/*  72 */       h32 = Integer.rotateLeft(h32, 11) * -1640531535;
/*  73 */       off++;
/*     */     } 
/*     */     
/*  76 */     h32 ^= h32 >>> 15;
/*  77 */     h32 *= -2048144777;
/*  78 */     h32 ^= h32 >>> 13;
/*  79 */     h32 *= -1028477379;
/*  80 */     h32 ^= h32 >>> 16;
/*     */     
/*  82 */     return h32;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hash(ByteBuffer buf, int off, int len, int seed) {
/*     */     int h32;
/*  89 */     if (buf.hasArray()) {
/*  90 */       return hash(buf.array(), off + buf.arrayOffset(), len, seed);
/*     */     }
/*  92 */     ByteBufferUtils.checkRange(buf, off, len);
/*  93 */     buf = ByteBufferUtils.inLittleEndianOrder(buf);
/*     */     
/*  95 */     int end = off + len;
/*     */ 
/*     */     
/*  98 */     if (len >= 16) {
/*  99 */       int limit = end - 16;
/* 100 */       int v1 = seed + -1640531535 + -2048144777;
/* 101 */       int v2 = seed + -2048144777;
/* 102 */       int v3 = seed + 0;
/* 103 */       int v4 = seed - -1640531535;
/*     */       do {
/* 105 */         v1 += ByteBufferUtils.readIntLE(buf, off) * -2048144777;
/* 106 */         v1 = Integer.rotateLeft(v1, 13);
/* 107 */         v1 *= -1640531535;
/* 108 */         off += 4;
/*     */         
/* 110 */         v2 += ByteBufferUtils.readIntLE(buf, off) * -2048144777;
/* 111 */         v2 = Integer.rotateLeft(v2, 13);
/* 112 */         v2 *= -1640531535;
/* 113 */         off += 4;
/*     */         
/* 115 */         v3 += ByteBufferUtils.readIntLE(buf, off) * -2048144777;
/* 116 */         v3 = Integer.rotateLeft(v3, 13);
/* 117 */         v3 *= -1640531535;
/* 118 */         off += 4;
/*     */         
/* 120 */         v4 += ByteBufferUtils.readIntLE(buf, off) * -2048144777;
/* 121 */         v4 = Integer.rotateLeft(v4, 13);
/* 122 */         v4 *= -1640531535;
/* 123 */         off += 4;
/* 124 */       } while (off <= limit);
/*     */       
/* 126 */       h32 = Integer.rotateLeft(v1, 1) + Integer.rotateLeft(v2, 7) + Integer.rotateLeft(v3, 12) + Integer.rotateLeft(v4, 18);
/*     */     } else {
/* 128 */       h32 = seed + 374761393;
/*     */     } 
/*     */     
/* 131 */     h32 += len;
/*     */     
/* 133 */     while (off <= end - 4) {
/* 134 */       h32 += ByteBufferUtils.readIntLE(buf, off) * -1028477379;
/* 135 */       h32 = Integer.rotateLeft(h32, 17) * 668265263;
/* 136 */       off += 4;
/*     */     } 
/*     */     
/* 139 */     while (off < end) {
/* 140 */       h32 += (ByteBufferUtils.readByte(buf, off) & 0xFF) * 374761393;
/* 141 */       h32 = Integer.rotateLeft(h32, 11) * -1640531535;
/* 142 */       off++;
/*     */     } 
/*     */     
/* 145 */     h32 ^= h32 >>> 15;
/* 146 */     h32 *= -2048144777;
/* 147 */     h32 ^= h32 >>> 13;
/* 148 */     h32 *= -1028477379;
/* 149 */     h32 ^= h32 >>> 16;
/*     */     
/* 151 */     return h32;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\xxhash\XXHash32JavaUnsafe.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */