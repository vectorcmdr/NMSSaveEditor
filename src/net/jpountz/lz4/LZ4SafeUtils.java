/*     */ package net.jpountz.lz4;
/*     */ 
/*     */ import net.jpountz.util.SafeUtils;
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
/*     */ enum LZ4SafeUtils
/*     */ {
/*     */   static int hash(byte[] buf, int i) {
/*  29 */     return LZ4Utils.hash(SafeUtils.readInt(buf, i));
/*     */   }
/*     */   
/*     */   static int hash64k(byte[] buf, int i) {
/*  33 */     return LZ4Utils.hash64k(SafeUtils.readInt(buf, i));
/*     */   }
/*     */   
/*     */   static boolean readIntEquals(byte[] buf, int i, int j) {
/*  37 */     return (buf[i] == buf[j] && buf[i + 1] == buf[j + 1] && buf[i + 2] == buf[j + 2] && buf[i + 3] == buf[j + 3]);
/*     */   }
/*     */   
/*     */   static void safeIncrementalCopy(byte[] dest, int matchOff, int dOff, int matchLen) {
/*  41 */     for (int i = 0; i < matchLen; i++) {
/*  42 */       dest[dOff + i] = dest[matchOff + i];
/*     */     }
/*     */   }
/*     */   
/*     */   static void wildIncrementalCopy(byte[] dest, int matchOff, int dOff, int matchCopyEnd) {
/*     */     do {
/*  48 */       copy8Bytes(dest, matchOff, dest, dOff);
/*  49 */       matchOff += 8;
/*  50 */       dOff += 8;
/*  51 */     } while (dOff < matchCopyEnd);
/*     */   }
/*     */   
/*     */   static void copy8Bytes(byte[] src, int sOff, byte[] dest, int dOff) {
/*  55 */     for (int i = 0; i < 8; i++) {
/*  56 */       dest[dOff + i] = src[sOff + i];
/*     */     }
/*     */   }
/*     */   
/*     */   static int commonBytes(byte[] b, int o1, int o2, int limit) {
/*  61 */     int count = 0;
/*  62 */     while (o2 < limit && b[o1++] == b[o2++]) {
/*  63 */       count++;
/*     */     }
/*  65 */     return count;
/*     */   }
/*     */   
/*     */   static int commonBytesBackward(byte[] b, int o1, int o2, int l1, int l2) {
/*  69 */     int count = 0;
/*  70 */     while (o1 > l1 && o2 > l2 && b[--o1] == b[--o2]) {
/*  71 */       count++;
/*     */     }
/*  73 */     return count;
/*     */   }
/*     */   
/*     */   static void safeArraycopy(byte[] src, int sOff, byte[] dest, int dOff, int len) {
/*  77 */     System.arraycopy(src, sOff, dest, dOff, len);
/*     */   }
/*     */   
/*     */   static void wildArraycopy(byte[] src, int sOff, byte[] dest, int dOff, int len) {
/*     */     try {
/*  82 */       for (int i = 0; i < len; i += 8) {
/*  83 */         copy8Bytes(src, sOff + i, dest, dOff + i);
/*     */       }
/*  85 */     } catch (ArrayIndexOutOfBoundsException e) {
/*  86 */       throw new LZ4Exception("Malformed input at offset " + sOff);
/*     */     } 
/*     */   }
/*     */   
/*     */   static int encodeSequence(byte[] src, int anchor, int matchOff, int matchRef, int matchLen, byte[] dest, int dOff, int destEnd) {
/*  91 */     int token, runLen = matchOff - anchor;
/*  92 */     int tokenOff = dOff++;
/*     */     
/*  94 */     if (dOff + runLen + 8 + (runLen >>> 8) > destEnd) {
/*  95 */       throw new LZ4Exception("maxDestLen is too small");
/*     */     }
/*     */ 
/*     */     
/*  99 */     if (runLen >= 15) {
/* 100 */       token = -16;
/* 101 */       dOff = writeLen(runLen - 15, dest, dOff);
/*     */     } else {
/* 103 */       token = runLen << 4;
/*     */     } 
/*     */ 
/*     */     
/* 107 */     wildArraycopy(src, anchor, dest, dOff, runLen);
/* 108 */     dOff += runLen;
/*     */ 
/*     */     
/* 111 */     int matchDec = matchOff - matchRef;
/* 112 */     dest[dOff++] = (byte)matchDec;
/* 113 */     dest[dOff++] = (byte)(matchDec >>> 8);
/*     */ 
/*     */     
/* 116 */     matchLen -= 4;
/* 117 */     if (dOff + 6 + (matchLen >>> 8) > destEnd) {
/* 118 */       throw new LZ4Exception("maxDestLen is too small");
/*     */     }
/* 120 */     if (matchLen >= 15) {
/* 121 */       token |= 0xF;
/* 122 */       dOff = writeLen(matchLen - 15, dest, dOff);
/*     */     } else {
/* 124 */       token |= matchLen;
/*     */     } 
/*     */     
/* 127 */     dest[tokenOff] = (byte)token;
/*     */     
/* 129 */     return dOff;
/*     */   }
/*     */   
/*     */   static int lastLiterals(byte[] src, int sOff, int srcLen, byte[] dest, int dOff, int destEnd) {
/* 133 */     int runLen = srcLen;
/*     */     
/* 135 */     if (dOff + runLen + 1 + (runLen + 255 - 15) / 255 > destEnd) {
/* 136 */       throw new LZ4Exception();
/*     */     }
/*     */     
/* 139 */     if (runLen >= 15) {
/* 140 */       dest[dOff++] = -16;
/* 141 */       dOff = writeLen(runLen - 15, dest, dOff);
/*     */     } else {
/* 143 */       dest[dOff++] = (byte)(runLen << 4);
/*     */     } 
/*     */     
/* 146 */     System.arraycopy(src, sOff, dest, dOff, runLen);
/* 147 */     dOff += runLen;
/*     */     
/* 149 */     return dOff;
/*     */   }
/*     */   
/*     */   static int writeLen(int len, byte[] dest, int dOff) {
/* 153 */     while (len >= 255) {
/* 154 */       dest[dOff++] = -1;
/* 155 */       len -= 255;
/*     */     } 
/* 157 */     dest[dOff++] = (byte)len;
/* 158 */     return dOff;
/*     */   }
/*     */   
/*     */   static class Match {
/*     */     int start;
/*     */     
/*     */     void fix(int correction) {
/* 165 */       this.start += correction;
/* 166 */       this.ref += correction;
/* 167 */       this.len -= correction;
/*     */     }
/*     */     int ref; int len;
/*     */     int end() {
/* 171 */       return this.start + this.len;
/*     */     }
/*     */   }
/*     */   
/*     */   static void copyTo(Match m1, Match m2) {
/* 176 */     m2.len = m1.len;
/* 177 */     m2.start = m1.start;
/* 178 */     m2.ref = m1.ref;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4SafeUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */