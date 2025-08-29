/*     */ package net.jpountz.lz4;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import net.jpountz.util.ByteBufferUtils;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ enum LZ4ByteBufferUtils
/*     */ {
/*     */   static int hash(ByteBuffer buf, int i) {
/*  37 */     return LZ4Utils.hash(ByteBufferUtils.readInt(buf, i));
/*     */   }
/*     */   
/*     */   static int hash64k(ByteBuffer buf, int i) {
/*  41 */     return LZ4Utils.hash64k(ByteBufferUtils.readInt(buf, i));
/*     */   }
/*     */   
/*     */   static boolean readIntEquals(ByteBuffer buf, int i, int j) {
/*  45 */     return (buf.getInt(i) == buf.getInt(j));
/*     */   }
/*     */   
/*     */   static void safeIncrementalCopy(ByteBuffer dest, int matchOff, int dOff, int matchLen) {
/*  49 */     for (int i = 0; i < matchLen; i++) {
/*  50 */       dest.put(dOff + i, dest.get(matchOff + i));
/*     */     }
/*     */   }
/*     */   
/*     */   static void wildIncrementalCopy(ByteBuffer dest, int matchOff, int dOff, int matchCopyEnd) {
/*  55 */     if (dOff - matchOff < 4) {
/*  56 */       for (int i = 0; i < 4; i++) {
/*  57 */         ByteBufferUtils.writeByte(dest, dOff + i, ByteBufferUtils.readByte(dest, matchOff + i));
/*     */       }
/*  59 */       dOff += 4;
/*  60 */       matchOff += 4;
/*  61 */       int dec = 0;
/*  62 */       assert dOff >= matchOff && dOff - matchOff < 8;
/*  63 */       switch (dOff - matchOff) {
/*     */         case 1:
/*  65 */           matchOff -= 3;
/*     */           break;
/*     */         case 2:
/*  68 */           matchOff -= 2;
/*     */           break;
/*     */         case 3:
/*  71 */           matchOff -= 3;
/*  72 */           dec = -1;
/*     */           break;
/*     */         case 5:
/*  75 */           dec = 1;
/*     */           break;
/*     */         case 6:
/*  78 */           dec = 2;
/*     */           break;
/*     */         case 7:
/*  81 */           dec = 3;
/*     */           break;
/*     */       } 
/*     */ 
/*     */       
/*  86 */       ByteBufferUtils.writeInt(dest, dOff, ByteBufferUtils.readInt(dest, matchOff));
/*  87 */       dOff += 4;
/*  88 */       matchOff -= dec;
/*  89 */     } else if (dOff - matchOff < 8) {
/*  90 */       ByteBufferUtils.writeLong(dest, dOff, ByteBufferUtils.readLong(dest, matchOff));
/*  91 */       dOff += dOff - matchOff;
/*     */     } 
/*  93 */     while (dOff < matchCopyEnd) {
/*  94 */       ByteBufferUtils.writeLong(dest, dOff, ByteBufferUtils.readLong(dest, matchOff));
/*  95 */       dOff += 8;
/*  96 */       matchOff += 8;
/*     */     } 
/*     */   }
/*     */   
/*     */   static int commonBytes(ByteBuffer src, int ref, int sOff, int srcLimit) {
/* 101 */     int matchLen = 0;
/* 102 */     while (sOff <= srcLimit - 8) {
/* 103 */       int zeroBits; if (ByteBufferUtils.readLong(src, sOff) == ByteBufferUtils.readLong(src, ref)) {
/* 104 */         matchLen += 8;
/* 105 */         ref += 8;
/* 106 */         sOff += 8;
/*     */         continue;
/*     */       } 
/* 109 */       if (src.order() == ByteOrder.BIG_ENDIAN) {
/* 110 */         zeroBits = Long.numberOfLeadingZeros(ByteBufferUtils.readLong(src, sOff) ^ ByteBufferUtils.readLong(src, ref));
/*     */       } else {
/* 112 */         zeroBits = Long.numberOfTrailingZeros(ByteBufferUtils.readLong(src, sOff) ^ ByteBufferUtils.readLong(src, ref));
/*     */       } 
/* 114 */       return matchLen + (zeroBits >>> 3);
/*     */     } 
/*     */     
/* 117 */     while (sOff < srcLimit && ByteBufferUtils.readByte(src, ref++) == ByteBufferUtils.readByte(src, sOff++)) {
/* 118 */       matchLen++;
/*     */     }
/* 120 */     return matchLen;
/*     */   }
/*     */   
/*     */   static int commonBytesBackward(ByteBuffer b, int o1, int o2, int l1, int l2) {
/* 124 */     int count = 0;
/* 125 */     while (o1 > l1 && o2 > l2 && b.get(--o1) == b.get(--o2)) {
/* 126 */       count++;
/*     */     }
/* 128 */     return count;
/*     */   }
/*     */   
/*     */   static void safeArraycopy(ByteBuffer src, int sOff, ByteBuffer dest, int dOff, int len) {
/* 132 */     for (int i = 0; i < len; i++) {
/* 133 */       dest.put(dOff + i, src.get(sOff + i));
/*     */     }
/*     */   }
/*     */   
/*     */   static void wildArraycopy(ByteBuffer src, int sOff, ByteBuffer dest, int dOff, int len) {
/* 138 */     assert src.order().equals(dest.order());
/*     */     try {
/* 140 */       for (int i = 0; i < len; i += 8) {
/* 141 */         dest.putLong(dOff + i, src.getLong(sOff + i));
/*     */       }
/* 143 */     } catch (IndexOutOfBoundsException e) {
/* 144 */       throw new LZ4Exception("Malformed input at offset " + sOff);
/*     */     } 
/*     */   }
/*     */   
/*     */   static int encodeSequence(ByteBuffer src, int anchor, int matchOff, int matchRef, int matchLen, ByteBuffer dest, int dOff, int destEnd) {
/* 149 */     int token, runLen = matchOff - anchor;
/* 150 */     int tokenOff = dOff++;
/*     */     
/* 152 */     if (dOff + runLen + 8 + (runLen >>> 8) > destEnd) {
/* 153 */       throw new LZ4Exception("maxDestLen is too small");
/*     */     }
/*     */ 
/*     */     
/* 157 */     if (runLen >= 15) {
/* 158 */       token = -16;
/* 159 */       dOff = writeLen(runLen - 15, dest, dOff);
/*     */     } else {
/* 161 */       token = runLen << 4;
/*     */     } 
/*     */ 
/*     */     
/* 165 */     wildArraycopy(src, anchor, dest, dOff, runLen);
/* 166 */     dOff += runLen;
/*     */ 
/*     */     
/* 169 */     int matchDec = matchOff - matchRef;
/* 170 */     dest.put(dOff++, (byte)matchDec);
/* 171 */     dest.put(dOff++, (byte)(matchDec >>> 8));
/*     */ 
/*     */     
/* 174 */     matchLen -= 4;
/* 175 */     if (dOff + 6 + (matchLen >>> 8) > destEnd) {
/* 176 */       throw new LZ4Exception("maxDestLen is too small");
/*     */     }
/* 178 */     if (matchLen >= 15) {
/* 179 */       token |= 0xF;
/* 180 */       dOff = writeLen(matchLen - 15, dest, dOff);
/*     */     } else {
/* 182 */       token |= matchLen;
/*     */     } 
/*     */     
/* 185 */     dest.put(tokenOff, (byte)token);
/*     */     
/* 187 */     return dOff;
/*     */   }
/*     */   
/*     */   static int lastLiterals(ByteBuffer src, int sOff, int srcLen, ByteBuffer dest, int dOff, int destEnd) {
/* 191 */     int runLen = srcLen;
/*     */     
/* 193 */     if (dOff + runLen + 1 + (runLen + 255 - 15) / 255 > destEnd) {
/* 194 */       throw new LZ4Exception();
/*     */     }
/*     */     
/* 197 */     if (runLen >= 15) {
/* 198 */       dest.put(dOff++, (byte)-16);
/* 199 */       dOff = writeLen(runLen - 15, dest, dOff);
/*     */     } else {
/* 201 */       dest.put(dOff++, (byte)(runLen << 4));
/*     */     } 
/*     */     
/* 204 */     safeArraycopy(src, sOff, dest, dOff, runLen);
/* 205 */     dOff += runLen;
/*     */     
/* 207 */     return dOff;
/*     */   }
/*     */   
/*     */   static int writeLen(int len, ByteBuffer dest, int dOff) {
/* 211 */     while (len >= 255) {
/* 212 */       dest.put(dOff++, (byte)-1);
/* 213 */       len -= 255;
/*     */     } 
/* 215 */     dest.put(dOff++, (byte)len);
/* 216 */     return dOff;
/*     */   }
/*     */   
/*     */   static class Match {
/*     */     int start;
/*     */     
/*     */     void fix(int correction) {
/* 223 */       this.start += correction;
/* 224 */       this.ref += correction;
/* 225 */       this.len -= correction;
/*     */     }
/*     */     int ref; int len;
/*     */     int end() {
/* 229 */       return this.start + this.len;
/*     */     }
/*     */   }
/*     */   
/*     */   static void copyTo(Match m1, Match m2) {
/* 234 */     m2.len = m1.len;
/* 235 */     m2.start = m1.start;
/* 236 */     m2.ref = m1.ref;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4ByteBufferUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */