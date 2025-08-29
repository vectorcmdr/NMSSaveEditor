/*     */ package net.jpountz.lz4;
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
/*     */ final class LZ4JavaUnsafeFastDecompressor
/*     */   extends LZ4FastDecompressor
/*     */ {
/*  17 */   public static final LZ4FastDecompressor INSTANCE = new LZ4JavaUnsafeFastDecompressor();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int decompress(byte[] src, int srcOff, byte[] dest, int destOff, int destLen) {
/*  24 */     UnsafeUtils.checkRange(src, srcOff);
/*  25 */     UnsafeUtils.checkRange(dest, destOff, destLen);
/*     */     
/*  27 */     if (destLen == 0) {
/*  28 */       if (UnsafeUtils.readByte(src, srcOff) != 0) {
/*  29 */         throw new LZ4Exception("Malformed input at " + srcOff);
/*     */       }
/*  31 */       return 1;
/*     */     } 
/*     */ 
/*     */     
/*  35 */     int destEnd = destOff + destLen;
/*     */     
/*  37 */     int sOff = srcOff;
/*  38 */     int dOff = destOff;
/*     */     
/*     */     while (true) {
/*  41 */       int token = UnsafeUtils.readByte(src, sOff) & 0xFF;
/*  42 */       sOff++;
/*     */ 
/*     */       
/*  45 */       int literalLen = token >>> 4;
/*  46 */       if (literalLen == 15) {
/*  47 */         byte len = -1;
/*  48 */         while ((len = UnsafeUtils.readByte(src, sOff++)) == -1) {
/*  49 */           literalLen += 255;
/*     */         }
/*  51 */         literalLen += len & 0xFF;
/*     */       } 
/*     */       
/*  54 */       int literalCopyEnd = dOff + literalLen;
/*     */       
/*  56 */       if (literalCopyEnd > destEnd - 8) {
/*  57 */         if (literalCopyEnd != destEnd) {
/*  58 */           throw new LZ4Exception("Malformed input at " + sOff);
/*     */         }
/*     */         
/*  61 */         LZ4UnsafeUtils.safeArraycopy(src, sOff, dest, dOff, literalLen);
/*  62 */         sOff += literalLen;
/*  63 */         dOff = literalCopyEnd;
/*     */         
/*     */         break;
/*     */       } 
/*     */       
/*  68 */       LZ4UnsafeUtils.wildArraycopy(src, sOff, dest, dOff, literalLen);
/*  69 */       sOff += literalLen;
/*  70 */       dOff = literalCopyEnd;
/*     */ 
/*     */       
/*  73 */       int matchDec = UnsafeUtils.readShortLE(src, sOff);
/*  74 */       sOff += 2;
/*  75 */       int matchOff = dOff - matchDec;
/*     */       
/*  77 */       if (matchOff < destOff) {
/*  78 */         throw new LZ4Exception("Malformed input at " + sOff);
/*     */       }
/*     */       
/*  81 */       int matchLen = token & 0xF;
/*  82 */       if (matchLen == 15) {
/*  83 */         byte len = -1;
/*  84 */         while ((len = UnsafeUtils.readByte(src, sOff++)) == -1) {
/*  85 */           matchLen += 255;
/*     */         }
/*  87 */         matchLen += len & 0xFF;
/*     */       } 
/*  89 */       matchLen += 4;
/*     */       
/*  91 */       int matchCopyEnd = dOff + matchLen;
/*     */       
/*  93 */       if (matchCopyEnd > destEnd - 8) {
/*  94 */         if (matchCopyEnd > destEnd) {
/*  95 */           throw new LZ4Exception("Malformed input at " + sOff);
/*     */         }
/*  97 */         LZ4UnsafeUtils.safeIncrementalCopy(dest, matchOff, dOff, matchLen);
/*     */       } else {
/*  99 */         LZ4UnsafeUtils.wildIncrementalCopy(dest, matchOff, dOff, matchCopyEnd);
/*     */       } 
/* 101 */       dOff = matchCopyEnd;
/*     */     } 
/*     */ 
/*     */     
/* 105 */     return sOff - srcOff;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int decompress(ByteBuffer src, int srcOff, ByteBuffer dest, int destOff, int destLen) {
/* 113 */     if (src.hasArray() && dest.hasArray()) {
/* 114 */       return decompress(src.array(), srcOff + src.arrayOffset(), dest.array(), destOff + dest.arrayOffset(), destLen);
/*     */     }
/* 116 */     src = ByteBufferUtils.inNativeByteOrder(src);
/* 117 */     dest = ByteBufferUtils.inNativeByteOrder(dest);
/*     */ 
/*     */     
/* 120 */     ByteBufferUtils.checkRange(src, srcOff);
/* 121 */     ByteBufferUtils.checkRange(dest, destOff, destLen);
/*     */     
/* 123 */     if (destLen == 0) {
/* 124 */       if (ByteBufferUtils.readByte(src, srcOff) != 0) {
/* 125 */         throw new LZ4Exception("Malformed input at " + srcOff);
/*     */       }
/* 127 */       return 1;
/*     */     } 
/*     */ 
/*     */     
/* 131 */     int destEnd = destOff + destLen;
/*     */     
/* 133 */     int sOff = srcOff;
/* 134 */     int dOff = destOff;
/*     */     
/*     */     while (true) {
/* 137 */       int token = ByteBufferUtils.readByte(src, sOff) & 0xFF;
/* 138 */       sOff++;
/*     */ 
/*     */       
/* 141 */       int literalLen = token >>> 4;
/* 142 */       if (literalLen == 15) {
/* 143 */         byte len = -1;
/* 144 */         while ((len = ByteBufferUtils.readByte(src, sOff++)) == -1) {
/* 145 */           literalLen += 255;
/*     */         }
/* 147 */         literalLen += len & 0xFF;
/*     */       } 
/*     */       
/* 150 */       int literalCopyEnd = dOff + literalLen;
/*     */       
/* 152 */       if (literalCopyEnd > destEnd - 8) {
/* 153 */         if (literalCopyEnd != destEnd) {
/* 154 */           throw new LZ4Exception("Malformed input at " + sOff);
/*     */         }
/*     */         
/* 157 */         LZ4ByteBufferUtils.safeArraycopy(src, sOff, dest, dOff, literalLen);
/* 158 */         sOff += literalLen;
/* 159 */         dOff = literalCopyEnd;
/*     */         
/*     */         break;
/*     */       } 
/*     */       
/* 164 */       LZ4ByteBufferUtils.wildArraycopy(src, sOff, dest, dOff, literalLen);
/* 165 */       sOff += literalLen;
/* 166 */       dOff = literalCopyEnd;
/*     */ 
/*     */       
/* 169 */       int matchDec = ByteBufferUtils.readShortLE(src, sOff);
/* 170 */       sOff += 2;
/* 171 */       int matchOff = dOff - matchDec;
/*     */       
/* 173 */       if (matchOff < destOff) {
/* 174 */         throw new LZ4Exception("Malformed input at " + sOff);
/*     */       }
/*     */       
/* 177 */       int matchLen = token & 0xF;
/* 178 */       if (matchLen == 15) {
/* 179 */         byte len = -1;
/* 180 */         while ((len = ByteBufferUtils.readByte(src, sOff++)) == -1) {
/* 181 */           matchLen += 255;
/*     */         }
/* 183 */         matchLen += len & 0xFF;
/*     */       } 
/* 185 */       matchLen += 4;
/*     */       
/* 187 */       int matchCopyEnd = dOff + matchLen;
/*     */       
/* 189 */       if (matchCopyEnd > destEnd - 8) {
/* 190 */         if (matchCopyEnd > destEnd) {
/* 191 */           throw new LZ4Exception("Malformed input at " + sOff);
/*     */         }
/* 193 */         LZ4ByteBufferUtils.safeIncrementalCopy(dest, matchOff, dOff, matchLen);
/*     */       } else {
/* 195 */         LZ4ByteBufferUtils.wildIncrementalCopy(dest, matchOff, dOff, matchCopyEnd);
/*     */       } 
/* 197 */       dOff = matchCopyEnd;
/*     */     } 
/*     */ 
/*     */     
/* 201 */     return sOff - srcOff;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4JavaUnsafeFastDecompressor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */