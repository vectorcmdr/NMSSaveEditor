/*     */ package net.jpountz.lz4;
/*     */ 
/*     */ import java.nio.ByteOrder;
/*     */ import net.jpountz.util.UnsafeUtils;
/*     */ import net.jpountz.util.Utils;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ enum LZ4UnsafeUtils
/*     */ {
/*     */   static void safeArraycopy(byte[] src, int srcOff, byte[] dest, int destOff, int len) {
/*  40 */     int fastLen = len & 0xFFFFFFF8;
/*  41 */     wildArraycopy(src, srcOff, dest, destOff, fastLen);
/*  42 */     for (int i = 0, slowLen = len & 0x7; i < slowLen; i++) {
/*  43 */       UnsafeUtils.writeByte(dest, destOff + fastLen + i, UnsafeUtils.readByte(src, srcOff + fastLen + i));
/*     */     }
/*     */   }
/*     */   
/*     */   static void wildArraycopy(byte[] src, int srcOff, byte[] dest, int destOff, int len) {
/*  48 */     for (int i = 0; i < len; i += 8) {
/*  49 */       UnsafeUtils.writeLong(dest, destOff + i, UnsafeUtils.readLong(src, srcOff + i));
/*     */     }
/*     */   }
/*     */   
/*     */   static void wildIncrementalCopy(byte[] dest, int matchOff, int dOff, int matchCopyEnd) {
/*  54 */     if (dOff - matchOff < 4) {
/*  55 */       for (int i = 0; i < 4; i++) {
/*  56 */         UnsafeUtils.writeByte(dest, dOff + i, UnsafeUtils.readByte(dest, matchOff + i));
/*     */       }
/*  58 */       dOff += 4;
/*  59 */       matchOff += 4;
/*  60 */       int dec = 0;
/*  61 */       assert dOff >= matchOff && dOff - matchOff < 8;
/*  62 */       switch (dOff - matchOff) {
/*     */         case 1:
/*  64 */           matchOff -= 3;
/*     */           break;
/*     */         case 2:
/*  67 */           matchOff -= 2;
/*     */           break;
/*     */         case 3:
/*  70 */           matchOff -= 3;
/*  71 */           dec = -1;
/*     */           break;
/*     */         case 5:
/*  74 */           dec = 1;
/*     */           break;
/*     */         case 6:
/*  77 */           dec = 2;
/*     */           break;
/*     */         case 7:
/*  80 */           dec = 3;
/*     */           break;
/*     */       } 
/*     */ 
/*     */       
/*  85 */       UnsafeUtils.writeInt(dest, dOff, UnsafeUtils.readInt(dest, matchOff));
/*  86 */       dOff += 4;
/*  87 */       matchOff -= dec;
/*  88 */     } else if (dOff - matchOff < 8) {
/*  89 */       UnsafeUtils.writeLong(dest, dOff, UnsafeUtils.readLong(dest, matchOff));
/*  90 */       dOff += dOff - matchOff;
/*     */     } 
/*  92 */     while (dOff < matchCopyEnd) {
/*  93 */       UnsafeUtils.writeLong(dest, dOff, UnsafeUtils.readLong(dest, matchOff));
/*  94 */       dOff += 8;
/*  95 */       matchOff += 8;
/*     */     } 
/*     */   }
/*     */   
/*     */   static void safeIncrementalCopy(byte[] dest, int matchOff, int dOff, int matchLen) {
/* 100 */     for (int i = 0; i < matchLen; i++) {
/* 101 */       dest[dOff + i] = dest[matchOff + i];
/* 102 */       UnsafeUtils.writeByte(dest, dOff + i, UnsafeUtils.readByte(dest, matchOff + i));
/*     */     } 
/*     */   }
/*     */   
/*     */   static int readShortLittleEndian(byte[] src, int srcOff) {
/* 107 */     short s = UnsafeUtils.readShort(src, srcOff);
/* 108 */     if (Utils.NATIVE_BYTE_ORDER == ByteOrder.BIG_ENDIAN) {
/* 109 */       s = Short.reverseBytes(s);
/*     */     }
/* 111 */     return s & 0xFFFF;
/*     */   }
/*     */   
/*     */   static void writeShortLittleEndian(byte[] dest, int destOff, int value) {
/* 115 */     short s = (short)value;
/* 116 */     if (Utils.NATIVE_BYTE_ORDER == ByteOrder.BIG_ENDIAN) {
/* 117 */       s = Short.reverseBytes(s);
/*     */     }
/* 119 */     UnsafeUtils.writeShort(dest, destOff, s);
/*     */   }
/*     */   
/*     */   static boolean readIntEquals(byte[] src, int ref, int sOff) {
/* 123 */     return (UnsafeUtils.readInt(src, ref) == UnsafeUtils.readInt(src, sOff));
/*     */   }
/*     */   
/*     */   static int commonBytes(byte[] src, int ref, int sOff, int srcLimit) {
/* 127 */     int matchLen = 0;
/* 128 */     while (sOff <= srcLimit - 8) {
/* 129 */       int zeroBits; if (UnsafeUtils.readLong(src, sOff) == UnsafeUtils.readLong(src, ref)) {
/* 130 */         matchLen += 8;
/* 131 */         ref += 8;
/* 132 */         sOff += 8;
/*     */         continue;
/*     */       } 
/* 135 */       if (Utils.NATIVE_BYTE_ORDER == ByteOrder.BIG_ENDIAN) {
/* 136 */         zeroBits = Long.numberOfLeadingZeros(UnsafeUtils.readLong(src, sOff) ^ UnsafeUtils.readLong(src, ref));
/*     */       } else {
/* 138 */         zeroBits = Long.numberOfTrailingZeros(UnsafeUtils.readLong(src, sOff) ^ UnsafeUtils.readLong(src, ref));
/*     */       } 
/* 140 */       return matchLen + (zeroBits >>> 3);
/*     */     } 
/*     */     
/* 143 */     while (sOff < srcLimit && UnsafeUtils.readByte(src, ref++) == UnsafeUtils.readByte(src, sOff++)) {
/* 144 */       matchLen++;
/*     */     }
/* 146 */     return matchLen;
/*     */   }
/*     */   
/*     */   static int writeLen(int len, byte[] dest, int dOff) {
/* 150 */     while (len >= 255) {
/* 151 */       UnsafeUtils.writeByte(dest, dOff++, 255);
/* 152 */       len -= 255;
/*     */     } 
/* 154 */     UnsafeUtils.writeByte(dest, dOff++, len);
/* 155 */     return dOff;
/*     */   }
/*     */   
/*     */   static int encodeSequence(byte[] src, int anchor, int matchOff, int matchRef, int matchLen, byte[] dest, int dOff, int destEnd) {
/* 159 */     int token, runLen = matchOff - anchor;
/* 160 */     int tokenOff = dOff++;
/*     */ 
/*     */     
/* 163 */     if (runLen >= 15) {
/* 164 */       token = -16;
/* 165 */       dOff = writeLen(runLen - 15, dest, dOff);
/*     */     } else {
/* 167 */       token = runLen << 4;
/*     */     } 
/*     */ 
/*     */     
/* 171 */     wildArraycopy(src, anchor, dest, dOff, runLen);
/* 172 */     dOff += runLen;
/*     */ 
/*     */     
/* 175 */     int matchDec = matchOff - matchRef;
/* 176 */     dest[dOff++] = (byte)matchDec;
/* 177 */     dest[dOff++] = (byte)(matchDec >>> 8);
/*     */ 
/*     */     
/* 180 */     matchLen -= 4;
/* 181 */     if (dOff + 6 + (matchLen >>> 8) > destEnd) {
/* 182 */       throw new LZ4Exception("maxDestLen is too small");
/*     */     }
/* 184 */     if (matchLen >= 15) {
/* 185 */       token |= 0xF;
/* 186 */       dOff = writeLen(matchLen - 15, dest, dOff);
/*     */     } else {
/* 188 */       token |= matchLen;
/*     */     } 
/*     */     
/* 191 */     dest[tokenOff] = (byte)token;
/*     */     
/* 193 */     return dOff;
/*     */   }
/*     */   
/*     */   static int commonBytesBackward(byte[] b, int o1, int o2, int l1, int l2) {
/* 197 */     int count = 0;
/* 198 */     while (o1 > l1 && o2 > l2 && UnsafeUtils.readByte(b, --o1) == UnsafeUtils.readByte(b, --o2)) {
/* 199 */       count++;
/*     */     }
/* 201 */     return count;
/*     */   }
/*     */   
/*     */   static int lastLiterals(byte[] src, int sOff, int srcLen, byte[] dest, int dOff, int destEnd) {
/* 205 */     return LZ4SafeUtils.lastLiterals(src, sOff, srcLen, dest, dOff, destEnd);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4UnsafeUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */