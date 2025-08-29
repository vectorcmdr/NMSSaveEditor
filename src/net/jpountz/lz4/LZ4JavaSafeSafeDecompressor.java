/*     */ package net.jpountz.lz4;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import net.jpountz.util.ByteBufferUtils;
/*     */ import net.jpountz.util.SafeUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class LZ4JavaSafeSafeDecompressor
/*     */   extends LZ4SafeDecompressor
/*     */ {
/*  17 */   public static final LZ4SafeDecompressor INSTANCE = new LZ4JavaSafeSafeDecompressor();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int decompress(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff, int destLen) {
/*  24 */     SafeUtils.checkRange(src, srcOff, srcLen);
/*  25 */     SafeUtils.checkRange(dest, destOff, destLen);
/*     */     
/*  27 */     if (destLen == 0) {
/*  28 */       if (srcLen != 1 || SafeUtils.readByte(src, srcOff) != 0) {
/*  29 */         throw new LZ4Exception("Output buffer too small");
/*     */       }
/*  31 */       return 0;
/*     */     } 
/*     */     
/*  34 */     int srcEnd = srcOff + srcLen;
/*     */ 
/*     */     
/*  37 */     int destEnd = destOff + destLen;
/*     */     
/*  39 */     int sOff = srcOff;
/*  40 */     int dOff = destOff;
/*     */     
/*     */     while (true) {
/*  43 */       int token = SafeUtils.readByte(src, sOff) & 0xFF;
/*  44 */       sOff++;
/*     */ 
/*     */       
/*  47 */       int literalLen = token >>> 4;
/*  48 */       if (literalLen == 15) {
/*  49 */         byte len = -1;
/*  50 */         while (sOff < srcEnd && (len = SafeUtils.readByte(src, sOff++)) == -1) {
/*  51 */           literalLen += 255;
/*     */         }
/*  53 */         literalLen += len & 0xFF;
/*     */       } 
/*     */       
/*  56 */       int literalCopyEnd = dOff + literalLen;
/*     */       
/*  58 */       if (literalCopyEnd > destEnd - 8 || sOff + literalLen > srcEnd - 8) {
/*  59 */         if (literalCopyEnd > destEnd)
/*  60 */           throw new LZ4Exception(); 
/*  61 */         if (sOff + literalLen != srcEnd) {
/*  62 */           throw new LZ4Exception("Malformed input at " + sOff);
/*     */         }
/*     */         
/*  65 */         LZ4SafeUtils.safeArraycopy(src, sOff, dest, dOff, literalLen);
/*  66 */         sOff += literalLen;
/*  67 */         dOff = literalCopyEnd;
/*     */         
/*     */         break;
/*     */       } 
/*     */       
/*  72 */       LZ4SafeUtils.wildArraycopy(src, sOff, dest, dOff, literalLen);
/*  73 */       sOff += literalLen;
/*  74 */       dOff = literalCopyEnd;
/*     */ 
/*     */       
/*  77 */       int matchDec = SafeUtils.readShortLE(src, sOff);
/*  78 */       sOff += 2;
/*  79 */       int matchOff = dOff - matchDec;
/*     */       
/*  81 */       if (matchOff < destOff) {
/*  82 */         throw new LZ4Exception("Malformed input at " + sOff);
/*     */       }
/*     */       
/*  85 */       int matchLen = token & 0xF;
/*  86 */       if (matchLen == 15) {
/*  87 */         byte len = -1;
/*  88 */         while (sOff < srcEnd && (len = SafeUtils.readByte(src, sOff++)) == -1) {
/*  89 */           matchLen += 255;
/*     */         }
/*  91 */         matchLen += len & 0xFF;
/*     */       } 
/*  93 */       matchLen += 4;
/*     */       
/*  95 */       int matchCopyEnd = dOff + matchLen;
/*     */       
/*  97 */       if (matchCopyEnd > destEnd - 8) {
/*  98 */         if (matchCopyEnd > destEnd) {
/*  99 */           throw new LZ4Exception("Malformed input at " + sOff);
/*     */         }
/* 101 */         LZ4SafeUtils.safeIncrementalCopy(dest, matchOff, dOff, matchLen);
/*     */       } else {
/* 103 */         LZ4SafeUtils.wildIncrementalCopy(dest, matchOff, dOff, matchCopyEnd);
/*     */       } 
/* 105 */       dOff = matchCopyEnd;
/*     */     } 
/*     */ 
/*     */     
/* 109 */     return dOff - destOff;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int decompress(ByteBuffer src, int srcOff, int srcLen, ByteBuffer dest, int destOff, int destLen) {
/* 117 */     if (src.hasArray() && dest.hasArray()) {
/* 118 */       return decompress(src.array(), srcOff + src.arrayOffset(), srcLen, dest.array(), destOff + dest.arrayOffset(), destLen);
/*     */     }
/* 120 */     src = ByteBufferUtils.inNativeByteOrder(src);
/* 121 */     dest = ByteBufferUtils.inNativeByteOrder(dest);
/*     */ 
/*     */     
/* 124 */     ByteBufferUtils.checkRange(src, srcOff, srcLen);
/* 125 */     ByteBufferUtils.checkRange(dest, destOff, destLen);
/*     */     
/* 127 */     if (destLen == 0) {
/* 128 */       if (srcLen != 1 || ByteBufferUtils.readByte(src, srcOff) != 0) {
/* 129 */         throw new LZ4Exception("Output buffer too small");
/*     */       }
/* 131 */       return 0;
/*     */     } 
/*     */     
/* 134 */     int srcEnd = srcOff + srcLen;
/*     */ 
/*     */     
/* 137 */     int destEnd = destOff + destLen;
/*     */     
/* 139 */     int sOff = srcOff;
/* 140 */     int dOff = destOff;
/*     */     
/*     */     while (true) {
/* 143 */       int token = ByteBufferUtils.readByte(src, sOff) & 0xFF;
/* 144 */       sOff++;
/*     */ 
/*     */       
/* 147 */       int literalLen = token >>> 4;
/* 148 */       if (literalLen == 15) {
/* 149 */         byte len = -1;
/* 150 */         while (sOff < srcEnd && (len = ByteBufferUtils.readByte(src, sOff++)) == -1) {
/* 151 */           literalLen += 255;
/*     */         }
/* 153 */         literalLen += len & 0xFF;
/*     */       } 
/*     */       
/* 156 */       int literalCopyEnd = dOff + literalLen;
/*     */       
/* 158 */       if (literalCopyEnd > destEnd - 8 || sOff + literalLen > srcEnd - 8) {
/* 159 */         if (literalCopyEnd > destEnd)
/* 160 */           throw new LZ4Exception(); 
/* 161 */         if (sOff + literalLen != srcEnd) {
/* 162 */           throw new LZ4Exception("Malformed input at " + sOff);
/*     */         }
/*     */         
/* 165 */         LZ4ByteBufferUtils.safeArraycopy(src, sOff, dest, dOff, literalLen);
/* 166 */         sOff += literalLen;
/* 167 */         dOff = literalCopyEnd;
/*     */         
/*     */         break;
/*     */       } 
/*     */       
/* 172 */       LZ4ByteBufferUtils.wildArraycopy(src, sOff, dest, dOff, literalLen);
/* 173 */       sOff += literalLen;
/* 174 */       dOff = literalCopyEnd;
/*     */ 
/*     */       
/* 177 */       int matchDec = ByteBufferUtils.readShortLE(src, sOff);
/* 178 */       sOff += 2;
/* 179 */       int matchOff = dOff - matchDec;
/*     */       
/* 181 */       if (matchOff < destOff) {
/* 182 */         throw new LZ4Exception("Malformed input at " + sOff);
/*     */       }
/*     */       
/* 185 */       int matchLen = token & 0xF;
/* 186 */       if (matchLen == 15) {
/* 187 */         byte len = -1;
/* 188 */         while (sOff < srcEnd && (len = ByteBufferUtils.readByte(src, sOff++)) == -1) {
/* 189 */           matchLen += 255;
/*     */         }
/* 191 */         matchLen += len & 0xFF;
/*     */       } 
/* 193 */       matchLen += 4;
/*     */       
/* 195 */       int matchCopyEnd = dOff + matchLen;
/*     */       
/* 197 */       if (matchCopyEnd > destEnd - 8) {
/* 198 */         if (matchCopyEnd > destEnd) {
/* 199 */           throw new LZ4Exception("Malformed input at " + sOff);
/*     */         }
/* 201 */         LZ4ByteBufferUtils.safeIncrementalCopy(dest, matchOff, dOff, matchLen);
/*     */       } else {
/* 203 */         LZ4ByteBufferUtils.wildIncrementalCopy(dest, matchOff, dOff, matchCopyEnd);
/*     */       } 
/* 205 */       dOff = matchCopyEnd;
/*     */     } 
/*     */ 
/*     */     
/* 209 */     return dOff - destOff;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4JavaSafeSafeDecompressor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */