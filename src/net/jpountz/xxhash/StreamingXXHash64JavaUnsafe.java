/*     */ package net.jpountz.xxhash;
/*     */ 
/*     */ import net.jpountz.util.SafeUtils;
/*     */ import net.jpountz.util.UnsafeUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class StreamingXXHash64JavaUnsafe
/*     */   extends AbstractStreamingXXHash64Java
/*     */ {
/*     */   static class Factory
/*     */     implements StreamingXXHash64.Factory
/*     */   {
/*  17 */     public static final StreamingXXHash64.Factory INSTANCE = new Factory();
/*     */ 
/*     */     
/*     */     public StreamingXXHash64 newStreamingHash(long seed) {
/*  21 */       return new StreamingXXHash64JavaUnsafe(seed);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   StreamingXXHash64JavaUnsafe(long seed) {
/*  27 */     super(seed);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getValue() {
/*     */     long h64;
/*  33 */     if (this.totalLen >= 32L) {
/*  34 */       long v1 = this.v1;
/*  35 */       long v2 = this.v2;
/*  36 */       long v3 = this.v3;
/*  37 */       long v4 = this.v4;
/*     */       
/*  39 */       h64 = Long.rotateLeft(v1, 1) + Long.rotateLeft(v2, 7) + Long.rotateLeft(v3, 12) + Long.rotateLeft(v4, 18);
/*     */       
/*  41 */       v1 *= -4417276706812531889L; v1 = Long.rotateLeft(v1, 31); v1 *= -7046029288634856825L; h64 ^= v1;
/*  42 */       h64 = h64 * -7046029288634856825L + -8796714831421723037L;
/*     */       
/*  44 */       v2 *= -4417276706812531889L; v2 = Long.rotateLeft(v2, 31); v2 *= -7046029288634856825L; h64 ^= v2;
/*  45 */       h64 = h64 * -7046029288634856825L + -8796714831421723037L;
/*     */       
/*  47 */       v3 *= -4417276706812531889L; v3 = Long.rotateLeft(v3, 31); v3 *= -7046029288634856825L; h64 ^= v3;
/*  48 */       h64 = h64 * -7046029288634856825L + -8796714831421723037L;
/*     */       
/*  50 */       v4 *= -4417276706812531889L; v4 = Long.rotateLeft(v4, 31); v4 *= -7046029288634856825L; h64 ^= v4;
/*  51 */       h64 = h64 * -7046029288634856825L + -8796714831421723037L;
/*     */     } else {
/*  53 */       h64 = this.seed + 2870177450012600261L;
/*     */     } 
/*     */     
/*  56 */     h64 += this.totalLen;
/*     */     
/*  58 */     int off = 0;
/*  59 */     while (off <= this.memSize - 8) {
/*  60 */       long k1 = UnsafeUtils.readLongLE(this.memory, off);
/*  61 */       k1 *= -4417276706812531889L; k1 = Long.rotateLeft(k1, 31); k1 *= -7046029288634856825L; h64 ^= k1;
/*  62 */       h64 = Long.rotateLeft(h64, 27) * -7046029288634856825L + -8796714831421723037L;
/*  63 */       off += 8;
/*     */     } 
/*     */     
/*  66 */     if (off <= this.memSize - 4) {
/*  67 */       h64 ^= (UnsafeUtils.readIntLE(this.memory, off) & 0xFFFFFFFFL) * -7046029288634856825L;
/*  68 */       h64 = Long.rotateLeft(h64, 23) * -4417276706812531889L + 1609587929392839161L;
/*  69 */       off += 4;
/*     */     } 
/*     */     
/*  72 */     while (off < this.memSize) {
/*  73 */       h64 ^= (this.memory[off] & 0xFF) * 2870177450012600261L;
/*  74 */       h64 = Long.rotateLeft(h64, 11) * -7046029288634856825L;
/*  75 */       off++;
/*     */     } 
/*     */     
/*  78 */     h64 ^= h64 >>> 33L;
/*  79 */     h64 *= -4417276706812531889L;
/*  80 */     h64 ^= h64 >>> 29L;
/*  81 */     h64 *= 1609587929392839161L;
/*  82 */     h64 ^= h64 >>> 32L;
/*     */     
/*  84 */     return h64;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(byte[] buf, int off, int len) {
/*  89 */     SafeUtils.checkRange(buf, off, len);
/*     */     
/*  91 */     this.totalLen += len;
/*     */     
/*  93 */     if (this.memSize + len < 32) {
/*  94 */       System.arraycopy(buf, off, this.memory, this.memSize, len);
/*  95 */       this.memSize += len;
/*     */       
/*     */       return;
/*     */     } 
/*  99 */     int end = off + len;
/*     */     
/* 101 */     if (this.memSize > 0) {
/* 102 */       System.arraycopy(buf, off, this.memory, this.memSize, 32 - this.memSize);
/*     */       
/* 104 */       this.v1 += UnsafeUtils.readLongLE(this.memory, 0) * -4417276706812531889L;
/* 105 */       this.v1 = Long.rotateLeft(this.v1, 31);
/* 106 */       this.v1 *= -7046029288634856825L;
/*     */       
/* 108 */       this.v2 += UnsafeUtils.readLongLE(this.memory, 8) * -4417276706812531889L;
/* 109 */       this.v2 = Long.rotateLeft(this.v2, 31);
/* 110 */       this.v2 *= -7046029288634856825L;
/*     */       
/* 112 */       this.v3 += UnsafeUtils.readLongLE(this.memory, 16) * -4417276706812531889L;
/* 113 */       this.v3 = Long.rotateLeft(this.v3, 31);
/* 114 */       this.v3 *= -7046029288634856825L;
/*     */       
/* 116 */       this.v4 += UnsafeUtils.readLongLE(this.memory, 24) * -4417276706812531889L;
/* 117 */       this.v4 = Long.rotateLeft(this.v4, 31);
/* 118 */       this.v4 *= -7046029288634856825L;
/*     */       
/* 120 */       off += 32 - this.memSize;
/* 121 */       this.memSize = 0;
/*     */     } 
/*     */ 
/*     */     
/* 125 */     int limit = end - 32;
/* 126 */     long v1 = this.v1;
/* 127 */     long v2 = this.v2;
/* 128 */     long v3 = this.v3;
/* 129 */     long v4 = this.v4;
/*     */     
/* 131 */     while (off <= limit) {
/* 132 */       v1 += UnsafeUtils.readLongLE(buf, off) * -4417276706812531889L;
/* 133 */       v1 = Long.rotateLeft(v1, 31);
/* 134 */       v1 *= -7046029288634856825L;
/* 135 */       off += 8;
/*     */       
/* 137 */       v2 += UnsafeUtils.readLongLE(buf, off) * -4417276706812531889L;
/* 138 */       v2 = Long.rotateLeft(v2, 31);
/* 139 */       v2 *= -7046029288634856825L;
/* 140 */       off += 8;
/*     */       
/* 142 */       v3 += UnsafeUtils.readLongLE(buf, off) * -4417276706812531889L;
/* 143 */       v3 = Long.rotateLeft(v3, 31);
/* 144 */       v3 *= -7046029288634856825L;
/* 145 */       off += 8;
/*     */       
/* 147 */       v4 += UnsafeUtils.readLongLE(buf, off) * -4417276706812531889L;
/* 148 */       v4 = Long.rotateLeft(v4, 31);
/* 149 */       v4 *= -7046029288634856825L;
/* 150 */       off += 8;
/*     */     } 
/*     */     
/* 153 */     this.v1 = v1;
/* 154 */     this.v2 = v2;
/* 155 */     this.v3 = v3;
/* 156 */     this.v4 = v4;
/*     */ 
/*     */     
/* 159 */     if (off < end) {
/* 160 */       System.arraycopy(buf, off, this.memory, 0, end - off);
/* 161 */       this.memSize = end - off;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\xxhash\StreamingXXHash64JavaUnsafe.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */