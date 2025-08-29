/*     */ package net.jpountz.xxhash;
/*     */ 
/*     */ import net.jpountz.util.SafeUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class StreamingXXHash32JavaSafe
/*     */   extends AbstractStreamingXXHash32Java
/*     */ {
/*     */   static class Factory
/*     */     implements StreamingXXHash32.Factory
/*     */   {
/*  17 */     public static final StreamingXXHash32.Factory INSTANCE = new Factory();
/*     */ 
/*     */     
/*     */     public StreamingXXHash32 newStreamingHash(int seed) {
/*  21 */       return new StreamingXXHash32JavaSafe(seed);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   StreamingXXHash32JavaSafe(int seed) {
/*  27 */     super(seed);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getValue() {
/*  33 */     if (this.totalLen >= 16L) {
/*  34 */       h32 = Integer.rotateLeft(this.v1, 1) + Integer.rotateLeft(this.v2, 7) + Integer.rotateLeft(this.v3, 12) + Integer.rotateLeft(this.v4, 18);
/*     */     } else {
/*  36 */       h32 = this.seed + 374761393;
/*     */     } 
/*     */     
/*  39 */     int h32 = (int)(h32 + this.totalLen);
/*     */     
/*  41 */     int off = 0;
/*  42 */     while (off <= this.memSize - 4) {
/*  43 */       h32 += SafeUtils.readIntLE(this.memory, off) * -1028477379;
/*  44 */       h32 = Integer.rotateLeft(h32, 17) * 668265263;
/*  45 */       off += 4;
/*     */     } 
/*     */     
/*  48 */     while (off < this.memSize) {
/*  49 */       h32 += (SafeUtils.readByte(this.memory, off) & 0xFF) * 374761393;
/*  50 */       h32 = Integer.rotateLeft(h32, 11) * -1640531535;
/*  51 */       off++;
/*     */     } 
/*     */     
/*  54 */     h32 ^= h32 >>> 15;
/*  55 */     h32 *= -2048144777;
/*  56 */     h32 ^= h32 >>> 13;
/*  57 */     h32 *= -1028477379;
/*  58 */     h32 ^= h32 >>> 16;
/*     */     
/*  60 */     return h32;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(byte[] buf, int off, int len) {
/*  65 */     SafeUtils.checkRange(buf, off, len);
/*     */     
/*  67 */     this.totalLen += len;
/*     */     
/*  69 */     if (this.memSize + len < 16) {
/*  70 */       System.arraycopy(buf, off, this.memory, this.memSize, len);
/*  71 */       this.memSize += len;
/*     */       
/*     */       return;
/*     */     } 
/*  75 */     int end = off + len;
/*     */     
/*  77 */     if (this.memSize > 0) {
/*  78 */       System.arraycopy(buf, off, this.memory, this.memSize, 16 - this.memSize);
/*     */       
/*  80 */       this.v1 += SafeUtils.readIntLE(this.memory, 0) * -2048144777;
/*  81 */       this.v1 = Integer.rotateLeft(this.v1, 13);
/*  82 */       this.v1 *= -1640531535;
/*     */       
/*  84 */       this.v2 += SafeUtils.readIntLE(this.memory, 4) * -2048144777;
/*  85 */       this.v2 = Integer.rotateLeft(this.v2, 13);
/*  86 */       this.v2 *= -1640531535;
/*     */       
/*  88 */       this.v3 += SafeUtils.readIntLE(this.memory, 8) * -2048144777;
/*  89 */       this.v3 = Integer.rotateLeft(this.v3, 13);
/*  90 */       this.v3 *= -1640531535;
/*     */       
/*  92 */       this.v4 += SafeUtils.readIntLE(this.memory, 12) * -2048144777;
/*  93 */       this.v4 = Integer.rotateLeft(this.v4, 13);
/*  94 */       this.v4 *= -1640531535;
/*     */       
/*  96 */       off += 16 - this.memSize;
/*  97 */       this.memSize = 0;
/*     */     } 
/*     */ 
/*     */     
/* 101 */     int limit = end - 16;
/* 102 */     int v1 = this.v1;
/* 103 */     int v2 = this.v2;
/* 104 */     int v3 = this.v3;
/* 105 */     int v4 = this.v4;
/*     */     
/* 107 */     while (off <= limit) {
/* 108 */       v1 += SafeUtils.readIntLE(buf, off) * -2048144777;
/* 109 */       v1 = Integer.rotateLeft(v1, 13);
/* 110 */       v1 *= -1640531535;
/* 111 */       off += 4;
/*     */       
/* 113 */       v2 += SafeUtils.readIntLE(buf, off) * -2048144777;
/* 114 */       v2 = Integer.rotateLeft(v2, 13);
/* 115 */       v2 *= -1640531535;
/* 116 */       off += 4;
/*     */       
/* 118 */       v3 += SafeUtils.readIntLE(buf, off) * -2048144777;
/* 119 */       v3 = Integer.rotateLeft(v3, 13);
/* 120 */       v3 *= -1640531535;
/* 121 */       off += 4;
/*     */       
/* 123 */       v4 += SafeUtils.readIntLE(buf, off) * -2048144777;
/* 124 */       v4 = Integer.rotateLeft(v4, 13);
/* 125 */       v4 *= -1640531535;
/* 126 */       off += 4;
/*     */     } 
/*     */     
/* 129 */     this.v1 = v1;
/* 130 */     this.v2 = v2;
/* 131 */     this.v3 = v3;
/* 132 */     this.v4 = v4;
/*     */ 
/*     */     
/* 135 */     if (off < end) {
/* 136 */       System.arraycopy(buf, off, this.memory, 0, end - off);
/* 137 */       this.memSize = end - off;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\xxhash\StreamingXXHash32JavaSafe.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */