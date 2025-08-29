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
/*     */ final class XXHash64JavaUnsafe
/*     */   extends XXHash64
/*     */ {
/*  18 */   public static final XXHash64 INSTANCE = new XXHash64JavaUnsafe();
/*     */ 
/*     */ 
/*     */   
/*     */   public long hash(byte[] buf, int off, int len, long seed) {
/*     */     long h64;
/*  24 */     UnsafeUtils.checkRange(buf, off, len);
/*     */     
/*  26 */     int end = off + len;
/*     */ 
/*     */     
/*  29 */     if (len >= 32) {
/*  30 */       int limit = end - 32;
/*  31 */       long v1 = seed + -7046029288634856825L + -4417276706812531889L;
/*  32 */       long v2 = seed + -4417276706812531889L;
/*  33 */       long v3 = seed + 0L;
/*  34 */       long v4 = seed - -7046029288634856825L;
/*     */       do {
/*  36 */         v1 += UnsafeUtils.readLongLE(buf, off) * -4417276706812531889L;
/*  37 */         v1 = Long.rotateLeft(v1, 31);
/*  38 */         v1 *= -7046029288634856825L;
/*  39 */         off += 8;
/*     */         
/*  41 */         v2 += UnsafeUtils.readLongLE(buf, off) * -4417276706812531889L;
/*  42 */         v2 = Long.rotateLeft(v2, 31);
/*  43 */         v2 *= -7046029288634856825L;
/*  44 */         off += 8;
/*     */         
/*  46 */         v3 += UnsafeUtils.readLongLE(buf, off) * -4417276706812531889L;
/*  47 */         v3 = Long.rotateLeft(v3, 31);
/*  48 */         v3 *= -7046029288634856825L;
/*  49 */         off += 8;
/*     */         
/*  51 */         v4 += UnsafeUtils.readLongLE(buf, off) * -4417276706812531889L;
/*  52 */         v4 = Long.rotateLeft(v4, 31);
/*  53 */         v4 *= -7046029288634856825L;
/*  54 */         off += 8;
/*  55 */       } while (off <= limit);
/*     */       
/*  57 */       h64 = Long.rotateLeft(v1, 1) + Long.rotateLeft(v2, 7) + Long.rotateLeft(v3, 12) + Long.rotateLeft(v4, 18);
/*     */       
/*  59 */       v1 *= -4417276706812531889L; v1 = Long.rotateLeft(v1, 31); v1 *= -7046029288634856825L; h64 ^= v1;
/*  60 */       h64 = h64 * -7046029288634856825L + -8796714831421723037L;
/*     */       
/*  62 */       v2 *= -4417276706812531889L; v2 = Long.rotateLeft(v2, 31); v2 *= -7046029288634856825L; h64 ^= v2;
/*  63 */       h64 = h64 * -7046029288634856825L + -8796714831421723037L;
/*     */       
/*  65 */       v3 *= -4417276706812531889L; v3 = Long.rotateLeft(v3, 31); v3 *= -7046029288634856825L; h64 ^= v3;
/*  66 */       h64 = h64 * -7046029288634856825L + -8796714831421723037L;
/*     */       
/*  68 */       v4 *= -4417276706812531889L; v4 = Long.rotateLeft(v4, 31); v4 *= -7046029288634856825L; h64 ^= v4;
/*  69 */       h64 = h64 * -7046029288634856825L + -8796714831421723037L;
/*     */     } else {
/*  71 */       h64 = seed + 2870177450012600261L;
/*     */     } 
/*     */     
/*  74 */     h64 += len;
/*     */     
/*  76 */     while (off <= end - 8) {
/*  77 */       long k1 = UnsafeUtils.readLongLE(buf, off);
/*  78 */       k1 *= -4417276706812531889L; k1 = Long.rotateLeft(k1, 31); k1 *= -7046029288634856825L; h64 ^= k1;
/*  79 */       h64 = Long.rotateLeft(h64, 27) * -7046029288634856825L + -8796714831421723037L;
/*  80 */       off += 8;
/*     */     } 
/*     */     
/*  83 */     if (off <= end - 4) {
/*  84 */       h64 ^= (UnsafeUtils.readIntLE(buf, off) & 0xFFFFFFFFL) * -7046029288634856825L;
/*  85 */       h64 = Long.rotateLeft(h64, 23) * -4417276706812531889L + 1609587929392839161L;
/*  86 */       off += 4;
/*     */     } 
/*     */     
/*  89 */     while (off < end) {
/*  90 */       h64 ^= (UnsafeUtils.readByte(buf, off) & 0xFF) * 2870177450012600261L;
/*  91 */       h64 = Long.rotateLeft(h64, 11) * -7046029288634856825L;
/*  92 */       off++;
/*     */     } 
/*     */     
/*  95 */     h64 ^= h64 >>> 33L;
/*  96 */     h64 *= -4417276706812531889L;
/*  97 */     h64 ^= h64 >>> 29L;
/*  98 */     h64 *= 1609587929392839161L;
/*  99 */     h64 ^= h64 >>> 32L;
/*     */     
/* 101 */     return h64;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long hash(ByteBuffer buf, int off, int len, long seed) {
/*     */     long h64;
/* 108 */     if (buf.hasArray()) {
/* 109 */       return hash(buf.array(), off + buf.arrayOffset(), len, seed);
/*     */     }
/* 111 */     ByteBufferUtils.checkRange(buf, off, len);
/* 112 */     buf = ByteBufferUtils.inLittleEndianOrder(buf);
/*     */     
/* 114 */     int end = off + len;
/*     */ 
/*     */     
/* 117 */     if (len >= 32) {
/* 118 */       int limit = end - 32;
/* 119 */       long v1 = seed + -7046029288634856825L + -4417276706812531889L;
/* 120 */       long v2 = seed + -4417276706812531889L;
/* 121 */       long v3 = seed + 0L;
/* 122 */       long v4 = seed - -7046029288634856825L;
/*     */       do {
/* 124 */         v1 += ByteBufferUtils.readLongLE(buf, off) * -4417276706812531889L;
/* 125 */         v1 = Long.rotateLeft(v1, 31);
/* 126 */         v1 *= -7046029288634856825L;
/* 127 */         off += 8;
/*     */         
/* 129 */         v2 += ByteBufferUtils.readLongLE(buf, off) * -4417276706812531889L;
/* 130 */         v2 = Long.rotateLeft(v2, 31);
/* 131 */         v2 *= -7046029288634856825L;
/* 132 */         off += 8;
/*     */         
/* 134 */         v3 += ByteBufferUtils.readLongLE(buf, off) * -4417276706812531889L;
/* 135 */         v3 = Long.rotateLeft(v3, 31);
/* 136 */         v3 *= -7046029288634856825L;
/* 137 */         off += 8;
/*     */         
/* 139 */         v4 += ByteBufferUtils.readLongLE(buf, off) * -4417276706812531889L;
/* 140 */         v4 = Long.rotateLeft(v4, 31);
/* 141 */         v4 *= -7046029288634856825L;
/* 142 */         off += 8;
/* 143 */       } while (off <= limit);
/*     */       
/* 145 */       h64 = Long.rotateLeft(v1, 1) + Long.rotateLeft(v2, 7) + Long.rotateLeft(v3, 12) + Long.rotateLeft(v4, 18);
/*     */       
/* 147 */       v1 *= -4417276706812531889L; v1 = Long.rotateLeft(v1, 31); v1 *= -7046029288634856825L; h64 ^= v1;
/* 148 */       h64 = h64 * -7046029288634856825L + -8796714831421723037L;
/*     */       
/* 150 */       v2 *= -4417276706812531889L; v2 = Long.rotateLeft(v2, 31); v2 *= -7046029288634856825L; h64 ^= v2;
/* 151 */       h64 = h64 * -7046029288634856825L + -8796714831421723037L;
/*     */       
/* 153 */       v3 *= -4417276706812531889L; v3 = Long.rotateLeft(v3, 31); v3 *= -7046029288634856825L; h64 ^= v3;
/* 154 */       h64 = h64 * -7046029288634856825L + -8796714831421723037L;
/*     */       
/* 156 */       v4 *= -4417276706812531889L; v4 = Long.rotateLeft(v4, 31); v4 *= -7046029288634856825L; h64 ^= v4;
/* 157 */       h64 = h64 * -7046029288634856825L + -8796714831421723037L;
/*     */     } else {
/* 159 */       h64 = seed + 2870177450012600261L;
/*     */     } 
/*     */     
/* 162 */     h64 += len;
/*     */     
/* 164 */     while (off <= end - 8) {
/* 165 */       long k1 = ByteBufferUtils.readLongLE(buf, off);
/* 166 */       k1 *= -4417276706812531889L; k1 = Long.rotateLeft(k1, 31); k1 *= -7046029288634856825L; h64 ^= k1;
/* 167 */       h64 = Long.rotateLeft(h64, 27) * -7046029288634856825L + -8796714831421723037L;
/* 168 */       off += 8;
/*     */     } 
/*     */     
/* 171 */     if (off <= end - 4) {
/* 172 */       h64 ^= (ByteBufferUtils.readIntLE(buf, off) & 0xFFFFFFFFL) * -7046029288634856825L;
/* 173 */       h64 = Long.rotateLeft(h64, 23) * -4417276706812531889L + 1609587929392839161L;
/* 174 */       off += 4;
/*     */     } 
/*     */     
/* 177 */     while (off < end) {
/* 178 */       h64 ^= (ByteBufferUtils.readByte(buf, off) & 0xFF) * 2870177450012600261L;
/* 179 */       h64 = Long.rotateLeft(h64, 11) * -7046029288634856825L;
/* 180 */       off++;
/*     */     } 
/*     */     
/* 183 */     h64 ^= h64 >>> 33L;
/* 184 */     h64 *= -4417276706812531889L;
/* 185 */     h64 ^= h64 >>> 29L;
/* 186 */     h64 *= 1609587929392839161L;
/* 187 */     h64 ^= h64 >>> 32L;
/*     */     
/* 189 */     return h64;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\xxhash\XXHash64JavaUnsafe.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */