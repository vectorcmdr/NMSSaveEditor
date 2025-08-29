/*     */ package net.jpountz.lz4;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Arrays;
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
/*     */ final class LZ4JavaUnsafeCompressor
/*     */   extends LZ4Compressor
/*     */ {
/*  19 */   public static final LZ4Compressor INSTANCE = new LZ4JavaUnsafeCompressor();
/*     */ 
/*     */   
/*     */   static int compress64k(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff, int destEnd) {
/*  23 */     int srcEnd = srcOff + srcLen;
/*  24 */     int srcLimit = srcEnd - 5;
/*  25 */     int mflimit = srcEnd - 12;
/*     */     
/*  27 */     int sOff = srcOff, dOff = destOff;
/*     */     
/*  29 */     int anchor = sOff;
/*     */     
/*  31 */     if (srcLen >= 13) {
/*     */       
/*  33 */       short[] hashTable = new short[8192];
/*     */       
/*  35 */       sOff++;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       while (true) {
/*  41 */         int forwardOff = sOff;
/*     */ 
/*     */         
/*  44 */         int step = 1;
/*  45 */         int searchMatchNb = 1 << LZ4Constants.SKIP_STRENGTH;
/*     */         while (true) {
/*  47 */           sOff = forwardOff;
/*  48 */           forwardOff += step;
/*  49 */           step = searchMatchNb++ >>> LZ4Constants.SKIP_STRENGTH;
/*     */           
/*  51 */           if (forwardOff > mflimit) {
/*     */             break;
/*     */           }
/*     */           
/*  55 */           int h = LZ4Utils.hash64k(UnsafeUtils.readInt(src, sOff));
/*  56 */           int ref = srcOff + UnsafeUtils.readShort(hashTable, h);
/*  57 */           UnsafeUtils.writeShort(hashTable, h, sOff - srcOff);
/*  58 */           if (LZ4UnsafeUtils.readIntEquals(src, ref, sOff)) {
/*     */ 
/*     */             
/*  61 */             int excess = LZ4UnsafeUtils.commonBytesBackward(src, ref, sOff, srcOff, anchor);
/*  62 */             sOff -= excess;
/*  63 */             ref -= excess;
/*     */ 
/*     */             
/*  66 */             int runLen = sOff - anchor;
/*     */ 
/*     */             
/*  69 */             int tokenOff = dOff++;
/*     */             
/*  71 */             if (dOff + runLen + 8 + (runLen >>> 8) > destEnd) {
/*  72 */               throw new LZ4Exception("maxDestLen is too small");
/*     */             }
/*     */             
/*  75 */             if (runLen >= 15) {
/*  76 */               UnsafeUtils.writeByte(dest, tokenOff, 240);
/*  77 */               dOff = LZ4UnsafeUtils.writeLen(runLen - 15, dest, dOff);
/*     */             } else {
/*  79 */               UnsafeUtils.writeByte(dest, tokenOff, runLen << 4);
/*     */             } 
/*     */ 
/*     */             
/*  83 */             LZ4UnsafeUtils.wildArraycopy(src, anchor, dest, dOff, runLen);
/*  84 */             dOff += runLen;
/*     */ 
/*     */             
/*     */             while (true)
/*  88 */             { UnsafeUtils.writeShortLE(dest, dOff, (short)(sOff - ref));
/*  89 */               dOff += 2;
/*     */ 
/*     */               
/*  92 */               sOff += 4;
/*  93 */               ref += 4;
/*  94 */               int matchLen = LZ4UnsafeUtils.commonBytes(src, ref, sOff, srcLimit);
/*  95 */               if (dOff + 6 + (matchLen >>> 8) > destEnd) {
/*  96 */                 throw new LZ4Exception("maxDestLen is too small");
/*     */               }
/*  98 */               sOff += matchLen;
/*     */ 
/*     */               
/* 101 */               if (matchLen >= 15) {
/* 102 */                 UnsafeUtils.writeByte(dest, tokenOff, UnsafeUtils.readByte(dest, tokenOff) | 0xF);
/* 103 */                 dOff = LZ4UnsafeUtils.writeLen(matchLen - 15, dest, dOff);
/*     */               } else {
/* 105 */                 UnsafeUtils.writeByte(dest, tokenOff, UnsafeUtils.readByte(dest, tokenOff) | matchLen);
/*     */               } 
/*     */ 
/*     */               
/* 109 */               if (sOff > mflimit) {
/* 110 */                 anchor = sOff;
/*     */                 
/*     */                 break;
/*     */               } 
/*     */               
/* 115 */               UnsafeUtils.writeShort(hashTable, LZ4Utils.hash64k(UnsafeUtils.readInt(src, sOff - 2)), sOff - 2 - srcOff);
/*     */ 
/*     */               
/* 118 */               int i = LZ4Utils.hash64k(UnsafeUtils.readInt(src, sOff));
/* 119 */               ref = srcOff + UnsafeUtils.readShort(hashTable, i);
/* 120 */               UnsafeUtils.writeShort(hashTable, i, sOff - srcOff);
/*     */               
/* 122 */               if (!LZ4UnsafeUtils.readIntEquals(src, sOff, ref))
/*     */               
/*     */               { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 131 */                 anchor = sOff++; continue; }  tokenOff = dOff++; UnsafeUtils.writeByte(dest, tokenOff, 0); }  break;
/*     */           } 
/*     */         }  break;
/*     */       } 
/* 135 */     }  dOff = LZ4UnsafeUtils.lastLiterals(src, anchor, srcEnd - anchor, dest, dOff, destEnd);
/* 136 */     return dOff - destOff;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compress(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff, int maxDestLen) {
/* 142 */     UnsafeUtils.checkRange(src, srcOff, srcLen);
/* 143 */     UnsafeUtils.checkRange(dest, destOff, maxDestLen);
/* 144 */     int destEnd = destOff + maxDestLen;
/*     */     
/* 146 */     if (srcLen < 65547) {
/* 147 */       return compress64k(src, srcOff, srcLen, dest, destOff, destEnd);
/*     */     }
/*     */     
/* 150 */     int srcEnd = srcOff + srcLen;
/* 151 */     int srcLimit = srcEnd - 5;
/* 152 */     int mflimit = srcEnd - 12;
/*     */     
/* 154 */     int sOff = srcOff, dOff = destOff;
/* 155 */     int anchor = sOff++;
/*     */     
/* 157 */     int[] hashTable = new int[4096];
/* 158 */     Arrays.fill(hashTable, anchor);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     label43: while (true) {
/* 164 */       int forwardOff = sOff;
/*     */ 
/*     */       
/* 167 */       int step = 1;
/* 168 */       int searchMatchNb = 1 << LZ4Constants.SKIP_STRENGTH;
/*     */       
/*     */       while (true) {
/* 171 */         sOff = forwardOff;
/* 172 */         forwardOff += step;
/* 173 */         step = searchMatchNb++ >>> LZ4Constants.SKIP_STRENGTH;
/*     */         
/* 175 */         if (forwardOff > mflimit) {
/*     */           break;
/*     */         }
/*     */         
/* 179 */         int h = LZ4Utils.hash(UnsafeUtils.readInt(src, sOff));
/* 180 */         int ref = UnsafeUtils.readInt(hashTable, h);
/* 181 */         int back = sOff - ref;
/* 182 */         UnsafeUtils.writeInt(hashTable, h, sOff);
/* 183 */         if (back < 65536 && LZ4UnsafeUtils.readIntEquals(src, ref, sOff)) {
/*     */ 
/*     */           
/* 186 */           int excess = LZ4UnsafeUtils.commonBytesBackward(src, ref, sOff, srcOff, anchor);
/* 187 */           sOff -= excess;
/* 188 */           ref -= excess;
/*     */ 
/*     */           
/* 191 */           int runLen = sOff - anchor;
/*     */ 
/*     */           
/* 194 */           int tokenOff = dOff++;
/*     */           
/* 196 */           if (dOff + runLen + 8 + (runLen >>> 8) > destEnd) {
/* 197 */             throw new LZ4Exception("maxDestLen is too small");
/*     */           }
/*     */           
/* 200 */           if (runLen >= 15) {
/* 201 */             UnsafeUtils.writeByte(dest, tokenOff, 240);
/* 202 */             dOff = LZ4UnsafeUtils.writeLen(runLen - 15, dest, dOff);
/*     */           } else {
/* 204 */             UnsafeUtils.writeByte(dest, tokenOff, runLen << 4);
/*     */           } 
/*     */ 
/*     */           
/* 208 */           LZ4UnsafeUtils.wildArraycopy(src, anchor, dest, dOff, runLen);
/* 209 */           dOff += runLen;
/*     */ 
/*     */           
/*     */           while (true)
/* 213 */           { UnsafeUtils.writeShortLE(dest, dOff, back);
/* 214 */             dOff += 2;
/*     */ 
/*     */             
/* 217 */             sOff += 4;
/* 218 */             int matchLen = LZ4UnsafeUtils.commonBytes(src, ref + 4, sOff, srcLimit);
/* 219 */             if (dOff + 6 + (matchLen >>> 8) > destEnd) {
/* 220 */               throw new LZ4Exception("maxDestLen is too small");
/*     */             }
/* 222 */             sOff += matchLen;
/*     */ 
/*     */             
/* 225 */             if (matchLen >= 15) {
/* 226 */               UnsafeUtils.writeByte(dest, tokenOff, UnsafeUtils.readByte(dest, tokenOff) | 0xF);
/* 227 */               dOff = LZ4UnsafeUtils.writeLen(matchLen - 15, dest, dOff);
/*     */             } else {
/* 229 */               UnsafeUtils.writeByte(dest, tokenOff, UnsafeUtils.readByte(dest, tokenOff) | matchLen);
/*     */             } 
/*     */ 
/*     */             
/* 233 */             if (sOff > mflimit) {
/* 234 */               anchor = sOff;
/*     */               
/*     */               break;
/*     */             } 
/*     */             
/* 239 */             UnsafeUtils.writeInt(hashTable, LZ4Utils.hash(UnsafeUtils.readInt(src, sOff - 2)), sOff - 2);
/*     */ 
/*     */             
/* 242 */             int i = LZ4Utils.hash(UnsafeUtils.readInt(src, sOff));
/* 243 */             ref = UnsafeUtils.readInt(hashTable, i);
/* 244 */             UnsafeUtils.writeInt(hashTable, i, sOff);
/* 245 */             back = sOff - ref;
/*     */             
/* 247 */             if (back < 65536) { if (!LZ4UnsafeUtils.readIntEquals(src, ref, sOff)) {
/*     */                 continue label43;
/*     */               }
/*     */               
/* 251 */               tokenOff = dOff++;
/* 252 */               UnsafeUtils.writeByte(dest, tokenOff, 0);
/*     */               
/*     */               continue; }
/*     */             
/* 256 */             anchor = sOff++; }  break;
/*     */         } 
/*     */       }  break;
/* 259 */     }  dOff = LZ4UnsafeUtils.lastLiterals(src, anchor, srcEnd - anchor, dest, dOff, destEnd);
/* 260 */     return dOff - destOff;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static int compress64k(ByteBuffer src, int srcOff, int srcLen, ByteBuffer dest, int destOff, int destEnd) {
/* 266 */     int srcEnd = srcOff + srcLen;
/* 267 */     int srcLimit = srcEnd - 5;
/* 268 */     int mflimit = srcEnd - 12;
/*     */     
/* 270 */     int sOff = srcOff, dOff = destOff;
/*     */     
/* 272 */     int anchor = sOff;
/*     */     
/* 274 */     if (srcLen >= 13) {
/*     */       
/* 276 */       short[] hashTable = new short[8192];
/*     */       
/* 278 */       sOff++;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       while (true) {
/* 284 */         int forwardOff = sOff;
/*     */ 
/*     */         
/* 287 */         int step = 1;
/* 288 */         int searchMatchNb = 1 << LZ4Constants.SKIP_STRENGTH;
/*     */         while (true) {
/* 290 */           sOff = forwardOff;
/* 291 */           forwardOff += step;
/* 292 */           step = searchMatchNb++ >>> LZ4Constants.SKIP_STRENGTH;
/*     */           
/* 294 */           if (forwardOff > mflimit) {
/*     */             break;
/*     */           }
/*     */           
/* 298 */           int h = LZ4Utils.hash64k(ByteBufferUtils.readInt(src, sOff));
/* 299 */           int ref = srcOff + UnsafeUtils.readShort(hashTable, h);
/* 300 */           UnsafeUtils.writeShort(hashTable, h, sOff - srcOff);
/* 301 */           if (LZ4ByteBufferUtils.readIntEquals(src, ref, sOff)) {
/*     */ 
/*     */             
/* 304 */             int excess = LZ4ByteBufferUtils.commonBytesBackward(src, ref, sOff, srcOff, anchor);
/* 305 */             sOff -= excess;
/* 306 */             ref -= excess;
/*     */ 
/*     */             
/* 309 */             int runLen = sOff - anchor;
/*     */ 
/*     */             
/* 312 */             int tokenOff = dOff++;
/*     */             
/* 314 */             if (dOff + runLen + 8 + (runLen >>> 8) > destEnd) {
/* 315 */               throw new LZ4Exception("maxDestLen is too small");
/*     */             }
/*     */             
/* 318 */             if (runLen >= 15) {
/* 319 */               ByteBufferUtils.writeByte(dest, tokenOff, 240);
/* 320 */               dOff = LZ4ByteBufferUtils.writeLen(runLen - 15, dest, dOff);
/*     */             } else {
/* 322 */               ByteBufferUtils.writeByte(dest, tokenOff, runLen << 4);
/*     */             } 
/*     */ 
/*     */             
/* 326 */             LZ4ByteBufferUtils.wildArraycopy(src, anchor, dest, dOff, runLen);
/* 327 */             dOff += runLen;
/*     */ 
/*     */             
/*     */             while (true)
/* 331 */             { ByteBufferUtils.writeShortLE(dest, dOff, (short)(sOff - ref));
/* 332 */               dOff += 2;
/*     */ 
/*     */               
/* 335 */               sOff += 4;
/* 336 */               ref += 4;
/* 337 */               int matchLen = LZ4ByteBufferUtils.commonBytes(src, ref, sOff, srcLimit);
/* 338 */               if (dOff + 6 + (matchLen >>> 8) > destEnd) {
/* 339 */                 throw new LZ4Exception("maxDestLen is too small");
/*     */               }
/* 341 */               sOff += matchLen;
/*     */ 
/*     */               
/* 344 */               if (matchLen >= 15) {
/* 345 */                 ByteBufferUtils.writeByte(dest, tokenOff, ByteBufferUtils.readByte(dest, tokenOff) | 0xF);
/* 346 */                 dOff = LZ4ByteBufferUtils.writeLen(matchLen - 15, dest, dOff);
/*     */               } else {
/* 348 */                 ByteBufferUtils.writeByte(dest, tokenOff, ByteBufferUtils.readByte(dest, tokenOff) | matchLen);
/*     */               } 
/*     */ 
/*     */               
/* 352 */               if (sOff > mflimit) {
/* 353 */                 anchor = sOff;
/*     */                 
/*     */                 break;
/*     */               } 
/*     */               
/* 358 */               UnsafeUtils.writeShort(hashTable, LZ4Utils.hash64k(ByteBufferUtils.readInt(src, sOff - 2)), sOff - 2 - srcOff);
/*     */ 
/*     */               
/* 361 */               int i = LZ4Utils.hash64k(ByteBufferUtils.readInt(src, sOff));
/* 362 */               ref = srcOff + UnsafeUtils.readShort(hashTable, i);
/* 363 */               UnsafeUtils.writeShort(hashTable, i, sOff - srcOff);
/*     */               
/* 365 */               if (!LZ4ByteBufferUtils.readIntEquals(src, sOff, ref))
/*     */               
/*     */               { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 374 */                 anchor = sOff++; continue; }  tokenOff = dOff++; ByteBufferUtils.writeByte(dest, tokenOff, 0); }  break;
/*     */           } 
/*     */         }  break;
/*     */       } 
/* 378 */     }  dOff = LZ4ByteBufferUtils.lastLiterals(src, anchor, srcEnd - anchor, dest, dOff, destEnd);
/* 379 */     return dOff - destOff;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compress(ByteBuffer src, int srcOff, int srcLen, ByteBuffer dest, int destOff, int maxDestLen) {
/* 385 */     if (src.hasArray() && dest.hasArray()) {
/* 386 */       return compress(src.array(), srcOff + src.arrayOffset(), srcLen, dest.array(), destOff + dest.arrayOffset(), maxDestLen);
/*     */     }
/* 388 */     src = ByteBufferUtils.inNativeByteOrder(src);
/* 389 */     dest = ByteBufferUtils.inNativeByteOrder(dest);
/*     */     
/* 391 */     ByteBufferUtils.checkRange(src, srcOff, srcLen);
/* 392 */     ByteBufferUtils.checkRange(dest, destOff, maxDestLen);
/* 393 */     int destEnd = destOff + maxDestLen;
/*     */     
/* 395 */     if (srcLen < 65547) {
/* 396 */       return compress64k(src, srcOff, srcLen, dest, destOff, destEnd);
/*     */     }
/*     */     
/* 399 */     int srcEnd = srcOff + srcLen;
/* 400 */     int srcLimit = srcEnd - 5;
/* 401 */     int mflimit = srcEnd - 12;
/*     */     
/* 403 */     int sOff = srcOff, dOff = destOff;
/* 404 */     int anchor = sOff++;
/*     */     
/* 406 */     int[] hashTable = new int[4096];
/* 407 */     Arrays.fill(hashTable, anchor);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     label46: while (true) {
/* 413 */       int forwardOff = sOff;
/*     */ 
/*     */       
/* 416 */       int step = 1;
/* 417 */       int searchMatchNb = 1 << LZ4Constants.SKIP_STRENGTH;
/*     */       
/*     */       while (true) {
/* 420 */         sOff = forwardOff;
/* 421 */         forwardOff += step;
/* 422 */         step = searchMatchNb++ >>> LZ4Constants.SKIP_STRENGTH;
/*     */         
/* 424 */         if (forwardOff > mflimit) {
/*     */           break;
/*     */         }
/*     */         
/* 428 */         int h = LZ4Utils.hash(ByteBufferUtils.readInt(src, sOff));
/* 429 */         int ref = UnsafeUtils.readInt(hashTable, h);
/* 430 */         int back = sOff - ref;
/* 431 */         UnsafeUtils.writeInt(hashTable, h, sOff);
/* 432 */         if (back < 65536 && LZ4ByteBufferUtils.readIntEquals(src, ref, sOff)) {
/*     */ 
/*     */           
/* 435 */           int excess = LZ4ByteBufferUtils.commonBytesBackward(src, ref, sOff, srcOff, anchor);
/* 436 */           sOff -= excess;
/* 437 */           ref -= excess;
/*     */ 
/*     */           
/* 440 */           int runLen = sOff - anchor;
/*     */ 
/*     */           
/* 443 */           int tokenOff = dOff++;
/*     */           
/* 445 */           if (dOff + runLen + 8 + (runLen >>> 8) > destEnd) {
/* 446 */             throw new LZ4Exception("maxDestLen is too small");
/*     */           }
/*     */           
/* 449 */           if (runLen >= 15) {
/* 450 */             ByteBufferUtils.writeByte(dest, tokenOff, 240);
/* 451 */             dOff = LZ4ByteBufferUtils.writeLen(runLen - 15, dest, dOff);
/*     */           } else {
/* 453 */             ByteBufferUtils.writeByte(dest, tokenOff, runLen << 4);
/*     */           } 
/*     */ 
/*     */           
/* 457 */           LZ4ByteBufferUtils.wildArraycopy(src, anchor, dest, dOff, runLen);
/* 458 */           dOff += runLen;
/*     */ 
/*     */           
/*     */           while (true)
/* 462 */           { ByteBufferUtils.writeShortLE(dest, dOff, back);
/* 463 */             dOff += 2;
/*     */ 
/*     */             
/* 466 */             sOff += 4;
/* 467 */             int matchLen = LZ4ByteBufferUtils.commonBytes(src, ref + 4, sOff, srcLimit);
/* 468 */             if (dOff + 6 + (matchLen >>> 8) > destEnd) {
/* 469 */               throw new LZ4Exception("maxDestLen is too small");
/*     */             }
/* 471 */             sOff += matchLen;
/*     */ 
/*     */             
/* 474 */             if (matchLen >= 15) {
/* 475 */               ByteBufferUtils.writeByte(dest, tokenOff, ByteBufferUtils.readByte(dest, tokenOff) | 0xF);
/* 476 */               dOff = LZ4ByteBufferUtils.writeLen(matchLen - 15, dest, dOff);
/*     */             } else {
/* 478 */               ByteBufferUtils.writeByte(dest, tokenOff, ByteBufferUtils.readByte(dest, tokenOff) | matchLen);
/*     */             } 
/*     */ 
/*     */             
/* 482 */             if (sOff > mflimit) {
/* 483 */               anchor = sOff;
/*     */               
/*     */               break;
/*     */             } 
/*     */             
/* 488 */             UnsafeUtils.writeInt(hashTable, LZ4Utils.hash(ByteBufferUtils.readInt(src, sOff - 2)), sOff - 2);
/*     */ 
/*     */             
/* 491 */             int i = LZ4Utils.hash(ByteBufferUtils.readInt(src, sOff));
/* 492 */             ref = UnsafeUtils.readInt(hashTable, i);
/* 493 */             UnsafeUtils.writeInt(hashTable, i, sOff);
/* 494 */             back = sOff - ref;
/*     */             
/* 496 */             if (back < 65536) { if (!LZ4ByteBufferUtils.readIntEquals(src, ref, sOff))
/*     */               
/*     */               { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 505 */                 anchor = sOff++; continue; }  tokenOff = dOff++; ByteBufferUtils.writeByte(dest, tokenOff, 0); continue; }  continue label46; }  break;
/*     */         } 
/*     */       }  break;
/* 508 */     }  dOff = LZ4ByteBufferUtils.lastLiterals(src, anchor, srcEnd - anchor, dest, dOff, destEnd);
/* 509 */     return dOff - destOff;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4JavaUnsafeCompressor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */