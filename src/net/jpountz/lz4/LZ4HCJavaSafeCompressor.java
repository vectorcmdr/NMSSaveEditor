/*     */ package net.jpountz.lz4;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Arrays;
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
/*     */ 
/*     */ 
/*     */ final class LZ4HCJavaSafeCompressor
/*     */   extends LZ4Compressor
/*     */ {
/*  20 */   public static final LZ4Compressor INSTANCE = new LZ4HCJavaSafeCompressor();
/*     */   private final int maxAttempts;
/*     */   final int compressionLevel;
/*     */   
/*     */   LZ4HCJavaSafeCompressor() {
/*  25 */     this(9);
/*     */   } LZ4HCJavaSafeCompressor(int compressionLevel) {
/*  27 */     this.maxAttempts = 1 << compressionLevel - 1;
/*  28 */     this.compressionLevel = compressionLevel;
/*     */   }
/*     */   
/*     */   private class HashTable
/*     */   {
/*     */     static final int MASK = 65535;
/*     */     int nextToUpdate;
/*     */     private final int base;
/*     */     private final int[] hashTable;
/*     */     private final short[] chainTable;
/*     */     
/*     */     HashTable(int base) {
/*  40 */       this.base = base;
/*  41 */       this.nextToUpdate = base;
/*  42 */       this.hashTable = new int[32768];
/*  43 */       Arrays.fill(this.hashTable, -1);
/*  44 */       this.chainTable = new short[65536];
/*     */     }
/*     */     
/*     */     private int hashPointer(byte[] bytes, int off) {
/*  48 */       int v = SafeUtils.readInt(bytes, off);
/*  49 */       return hashPointer(v);
/*     */     }
/*     */     
/*     */     private int hashPointer(ByteBuffer bytes, int off) {
/*  53 */       int v = ByteBufferUtils.readInt(bytes, off);
/*  54 */       return hashPointer(v);
/*     */     }
/*     */     
/*     */     private int hashPointer(int v) {
/*  58 */       int h = LZ4Utils.hashHC(v);
/*  59 */       return this.hashTable[h];
/*     */     }
/*     */     
/*     */     private int next(int off) {
/*  63 */       return off - (this.chainTable[off & 0xFFFF] & 0xFFFF);
/*     */     }
/*     */     
/*     */     private void addHash(byte[] bytes, int off) {
/*  67 */       int v = SafeUtils.readInt(bytes, off);
/*  68 */       addHash(v, off);
/*     */     }
/*     */     
/*     */     private void addHash(ByteBuffer bytes, int off) {
/*  72 */       int v = ByteBufferUtils.readInt(bytes, off);
/*  73 */       addHash(v, off);
/*     */     }
/*     */     
/*     */     private void addHash(int v, int off) {
/*  77 */       int h = LZ4Utils.hashHC(v);
/*  78 */       int delta = off - this.hashTable[h];
/*  79 */       assert delta > 0 : delta;
/*  80 */       if (delta >= 65536) {
/*  81 */         delta = 65535;
/*     */       }
/*  83 */       this.chainTable[off & 0xFFFF] = (short)delta;
/*  84 */       this.hashTable[h] = off;
/*     */     }
/*     */     
/*     */     void insert(int off, byte[] bytes) {
/*  88 */       for (; this.nextToUpdate < off; this.nextToUpdate++) {
/*  89 */         addHash(bytes, this.nextToUpdate);
/*     */       }
/*     */     }
/*     */     
/*     */     void insert(int off, ByteBuffer bytes) {
/*  94 */       for (; this.nextToUpdate < off; this.nextToUpdate++) {
/*  95 */         addHash(bytes, this.nextToUpdate);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     boolean insertAndFindBestMatch(byte[] buf, int off, int matchLimit, LZ4Utils.Match match) {
/* 102 */       match.start = off;
/* 103 */       match.len = 0;
/* 104 */       int delta = 0;
/* 105 */       int repl = 0;
/*     */       
/* 107 */       insert(off, buf);
/*     */       
/* 109 */       int ref = hashPointer(buf, off);
/*     */       
/* 111 */       if (ref >= off - 4 && ref <= off && ref >= this.base) {
/* 112 */         if (LZ4SafeUtils.readIntEquals(buf, ref, off)) {
/* 113 */           delta = off - ref;
/* 114 */           repl = match.len = 4 + LZ4SafeUtils.commonBytes(buf, ref + 4, off + 4, matchLimit);
/* 115 */           match.ref = ref;
/*     */         } 
/* 117 */         ref = next(ref);
/*     */       } 
/*     */       
/* 120 */       for (int i = 0; i < LZ4HCJavaSafeCompressor.this.maxAttempts && 
/* 121 */         ref >= Math.max(this.base, off - 65536 + 1) && ref <= off; i++) {
/*     */ 
/*     */         
/* 124 */         if (LZ4SafeUtils.readIntEquals(buf, ref, off)) {
/* 125 */           int matchLen = 4 + LZ4SafeUtils.commonBytes(buf, ref + 4, off + 4, matchLimit);
/* 126 */           if (matchLen > match.len) {
/* 127 */             match.ref = ref;
/* 128 */             match.len = matchLen;
/*     */           } 
/*     */         } 
/* 131 */         ref = next(ref);
/*     */       } 
/*     */       
/* 134 */       if (repl != 0) {
/* 135 */         int ptr = off;
/* 136 */         int end = off + repl - 3;
/* 137 */         while (ptr < end - delta) {
/* 138 */           this.chainTable[ptr & 0xFFFF] = (short)delta;
/* 139 */           ptr++;
/*     */         } 
/*     */         while (true) {
/* 142 */           this.chainTable[ptr & 0xFFFF] = (short)delta;
/* 143 */           this.hashTable[LZ4Utils.hashHC(SafeUtils.readInt(buf, ptr))] = ptr;
/* 144 */           ptr++;
/* 145 */           if (ptr >= end) {
/* 146 */             this.nextToUpdate = end; break;
/*     */           } 
/*     */         } 
/* 149 */       }  return (match.len != 0);
/*     */     }
/*     */     
/*     */     boolean insertAndFindWiderMatch(byte[] buf, int off, int startLimit, int matchLimit, int minLen, LZ4Utils.Match match) {
/* 153 */       match.len = minLen;
/*     */       
/* 155 */       insert(off, buf);
/*     */       
/* 157 */       int delta = off - startLimit;
/* 158 */       int ref = hashPointer(buf, off);
/* 159 */       for (int i = 0; i < LZ4HCJavaSafeCompressor.this.maxAttempts && 
/* 160 */         ref >= Math.max(this.base, off - 65536 + 1) && ref <= off; i++) {
/*     */ 
/*     */         
/* 163 */         if (LZ4SafeUtils.readIntEquals(buf, ref, off)) {
/* 164 */           int matchLenForward = 4 + LZ4SafeUtils.commonBytes(buf, ref + 4, off + 4, matchLimit);
/* 165 */           int matchLenBackward = LZ4SafeUtils.commonBytesBackward(buf, ref, off, this.base, startLimit);
/* 166 */           int matchLen = matchLenBackward + matchLenForward;
/* 167 */           if (matchLen > match.len) {
/* 168 */             match.len = matchLen;
/* 169 */             match.ref = ref - matchLenBackward;
/* 170 */             match.start = off - matchLenBackward;
/*     */           } 
/*     */         } 
/* 173 */         ref = next(ref);
/*     */       } 
/*     */       
/* 176 */       return (match.len > minLen);
/*     */     }
/*     */ 
/*     */     
/*     */     boolean insertAndFindBestMatch(ByteBuffer buf, int off, int matchLimit, LZ4Utils.Match match) {
/* 181 */       match.start = off;
/* 182 */       match.len = 0;
/* 183 */       int delta = 0;
/* 184 */       int repl = 0;
/*     */       
/* 186 */       insert(off, buf);
/*     */       
/* 188 */       int ref = hashPointer(buf, off);
/*     */       
/* 190 */       if (ref >= off - 4 && ref <= off && ref >= this.base) {
/* 191 */         if (LZ4ByteBufferUtils.readIntEquals(buf, ref, off)) {
/* 192 */           delta = off - ref;
/* 193 */           repl = match.len = 4 + LZ4ByteBufferUtils.commonBytes(buf, ref + 4, off + 4, matchLimit);
/* 194 */           match.ref = ref;
/*     */         } 
/* 196 */         ref = next(ref);
/*     */       } 
/*     */       
/* 199 */       for (int i = 0; i < LZ4HCJavaSafeCompressor.this.maxAttempts && 
/* 200 */         ref >= Math.max(this.base, off - 65536 + 1) && ref <= off; i++) {
/*     */ 
/*     */         
/* 203 */         if (LZ4ByteBufferUtils.readIntEquals(buf, ref, off)) {
/* 204 */           int matchLen = 4 + LZ4ByteBufferUtils.commonBytes(buf, ref + 4, off + 4, matchLimit);
/* 205 */           if (matchLen > match.len) {
/* 206 */             match.ref = ref;
/* 207 */             match.len = matchLen;
/*     */           } 
/*     */         } 
/* 210 */         ref = next(ref);
/*     */       } 
/*     */       
/* 213 */       if (repl != 0) {
/* 214 */         int ptr = off;
/* 215 */         int end = off + repl - 3;
/* 216 */         while (ptr < end - delta) {
/* 217 */           this.chainTable[ptr & 0xFFFF] = (short)delta;
/* 218 */           ptr++;
/*     */         } 
/*     */         while (true) {
/* 221 */           this.chainTable[ptr & 0xFFFF] = (short)delta;
/* 222 */           this.hashTable[LZ4Utils.hashHC(ByteBufferUtils.readInt(buf, ptr))] = ptr;
/* 223 */           ptr++;
/* 224 */           if (ptr >= end) {
/* 225 */             this.nextToUpdate = end; break;
/*     */           } 
/*     */         } 
/* 228 */       }  return (match.len != 0);
/*     */     }
/*     */     
/*     */     boolean insertAndFindWiderMatch(ByteBuffer buf, int off, int startLimit, int matchLimit, int minLen, LZ4Utils.Match match) {
/* 232 */       match.len = minLen;
/*     */       
/* 234 */       insert(off, buf);
/*     */       
/* 236 */       int delta = off - startLimit;
/* 237 */       int ref = hashPointer(buf, off);
/* 238 */       for (int i = 0; i < LZ4HCJavaSafeCompressor.this.maxAttempts && 
/* 239 */         ref >= Math.max(this.base, off - 65536 + 1) && ref <= off; i++) {
/*     */ 
/*     */         
/* 242 */         if (LZ4ByteBufferUtils.readIntEquals(buf, ref, off)) {
/* 243 */           int matchLenForward = 4 + LZ4ByteBufferUtils.commonBytes(buf, ref + 4, off + 4, matchLimit);
/* 244 */           int matchLenBackward = LZ4ByteBufferUtils.commonBytesBackward(buf, ref, off, this.base, startLimit);
/* 245 */           int matchLen = matchLenBackward + matchLenForward;
/* 246 */           if (matchLen > match.len) {
/* 247 */             match.len = matchLen;
/* 248 */             match.ref = ref - matchLenBackward;
/* 249 */             match.start = off - matchLenBackward;
/*     */           } 
/*     */         } 
/* 252 */         ref = next(ref);
/*     */       } 
/*     */       
/* 255 */       return (match.len > minLen);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compress(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff, int maxDestLen) {
/* 265 */     SafeUtils.checkRange(src, srcOff, srcLen);
/* 266 */     SafeUtils.checkRange(dest, destOff, maxDestLen);
/*     */     
/* 268 */     int srcEnd = srcOff + srcLen;
/* 269 */     int destEnd = destOff + maxDestLen;
/* 270 */     int mfLimit = srcEnd - 12;
/* 271 */     int matchLimit = srcEnd - 5;
/*     */     
/* 273 */     int sOff = srcOff;
/* 274 */     int dOff = destOff;
/* 275 */     int anchor = sOff++;
/*     */     
/* 277 */     HashTable ht = new HashTable(srcOff);
/* 278 */     LZ4Utils.Match match0 = new LZ4Utils.Match();
/* 279 */     LZ4Utils.Match match1 = new LZ4Utils.Match();
/* 280 */     LZ4Utils.Match match2 = new LZ4Utils.Match();
/* 281 */     LZ4Utils.Match match3 = new LZ4Utils.Match();
/*     */ 
/*     */     
/* 284 */     label65: while (sOff < mfLimit) {
/* 285 */       if (!ht.insertAndFindBestMatch(src, sOff, matchLimit, match1)) {
/* 286 */         sOff++;
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 291 */       LZ4Utils.copyTo(match1, match0);
/*     */ 
/*     */       
/*     */       label63: while (true) {
/* 295 */         assert match1.start >= anchor;
/* 296 */         if (match1.end() >= mfLimit || !ht.insertAndFindWiderMatch(src, match1.end() - 2, match1.start + 1, matchLimit, match1.len, match2)) {
/*     */ 
/*     */           
/* 299 */           dOff = LZ4SafeUtils.encodeSequence(src, anchor, match1.start, match1.ref, match1.len, dest, dOff, destEnd);
/* 300 */           anchor = sOff = match1.end();
/*     */           
/*     */           continue label65;
/*     */         } 
/* 304 */         if (match0.start < match1.start && 
/* 305 */           match2.start < match1.start + match0.len) {
/* 306 */           LZ4Utils.copyTo(match0, match1);
/*     */         }
/*     */         
/* 309 */         assert match2.start > match1.start;
/*     */         
/* 311 */         if (match2.start - match1.start < 3) {
/* 312 */           LZ4Utils.copyTo(match2, match1);
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/*     */         while (true) {
/* 318 */           if (match2.start - match1.start < 18) {
/* 319 */             int newMatchLen = match1.len;
/* 320 */             if (newMatchLen > 18) {
/* 321 */               newMatchLen = 18;
/*     */             }
/* 323 */             if (match1.start + newMatchLen > match2.end() - 4) {
/* 324 */               newMatchLen = match2.start - match1.start + match2.len - 4;
/*     */             }
/* 326 */             int correction = newMatchLen - match2.start - match1.start;
/* 327 */             if (correction > 0) {
/* 328 */               match2.fix(correction);
/*     */             }
/*     */           } 
/*     */           
/* 332 */           if (match2.start + match2.len >= mfLimit || !ht.insertAndFindWiderMatch(src, match2.end() - 3, match2.start, matchLimit, match2.len, match3)) {
/*     */ 
/*     */             
/* 335 */             if (match2.start < match1.end()) {
/* 336 */               match1.len = match2.start - match1.start;
/*     */             }
/*     */             
/* 339 */             dOff = LZ4SafeUtils.encodeSequence(src, anchor, match1.start, match1.ref, match1.len, dest, dOff, destEnd);
/* 340 */             anchor = sOff = match1.end();
/*     */             
/* 342 */             dOff = LZ4SafeUtils.encodeSequence(src, anchor, match2.start, match2.ref, match2.len, dest, dOff, destEnd);
/* 343 */             anchor = sOff = match2.end();
/*     */             
/*     */             continue label65;
/*     */           } 
/* 347 */           if (match3.start < match1.end() + 3) {
/* 348 */             if (match3.start >= match1.end()) {
/* 349 */               if (match2.start < match1.end()) {
/* 350 */                 int correction = match1.end() - match2.start;
/* 351 */                 match2.fix(correction);
/* 352 */                 if (match2.len < 4) {
/* 353 */                   LZ4Utils.copyTo(match3, match2);
/*     */                 }
/*     */               } 
/*     */               
/* 357 */               dOff = LZ4SafeUtils.encodeSequence(src, anchor, match1.start, match1.ref, match1.len, dest, dOff, destEnd);
/* 358 */               anchor = sOff = match1.end();
/*     */               
/* 360 */               LZ4Utils.copyTo(match3, match1);
/* 361 */               LZ4Utils.copyTo(match2, match0);
/*     */               
/*     */               continue label63;
/*     */             } 
/*     */             
/* 366 */             LZ4Utils.copyTo(match3, match2);
/*     */             
/*     */             continue;
/*     */           } 
/*     */           
/* 371 */           if (match2.start < match1.end()) {
/* 372 */             if (match2.start - match1.start < 15) {
/* 373 */               if (match1.len > 18) {
/* 374 */                 match1.len = 18;
/*     */               }
/* 376 */               if (match1.end() > match2.end() - 4) {
/* 377 */                 match1.len = match2.end() - match1.start - 4;
/*     */               }
/* 379 */               int correction = match1.end() - match2.start;
/* 380 */               match2.fix(correction);
/*     */             } else {
/* 382 */               match1.len = match2.start - match1.start;
/*     */             } 
/*     */           }
/*     */           
/* 386 */           dOff = LZ4SafeUtils.encodeSequence(src, anchor, match1.start, match1.ref, match1.len, dest, dOff, destEnd);
/* 387 */           anchor = sOff = match1.end();
/*     */           
/* 389 */           LZ4Utils.copyTo(match2, match1);
/* 390 */           LZ4Utils.copyTo(match3, match2);
/*     */         } 
/*     */ 
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 399 */     dOff = LZ4SafeUtils.lastLiterals(src, anchor, srcEnd - anchor, dest, dOff, destEnd);
/* 400 */     return dOff - destOff;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compress(ByteBuffer src, int srcOff, int srcLen, ByteBuffer dest, int destOff, int maxDestLen) {
/* 408 */     if (src.hasArray() && dest.hasArray()) {
/* 409 */       return compress(src.array(), srcOff + src.arrayOffset(), srcLen, dest.array(), destOff + dest.arrayOffset(), maxDestLen);
/*     */     }
/* 411 */     src = ByteBufferUtils.inNativeByteOrder(src);
/* 412 */     dest = ByteBufferUtils.inNativeByteOrder(dest);
/*     */     
/* 414 */     ByteBufferUtils.checkRange(src, srcOff, srcLen);
/* 415 */     ByteBufferUtils.checkRange(dest, destOff, maxDestLen);
/*     */     
/* 417 */     int srcEnd = srcOff + srcLen;
/* 418 */     int destEnd = destOff + maxDestLen;
/* 419 */     int mfLimit = srcEnd - 12;
/* 420 */     int matchLimit = srcEnd - 5;
/*     */     
/* 422 */     int sOff = srcOff;
/* 423 */     int dOff = destOff;
/* 424 */     int anchor = sOff++;
/*     */     
/* 426 */     HashTable ht = new HashTable(srcOff);
/* 427 */     LZ4Utils.Match match0 = new LZ4Utils.Match();
/* 428 */     LZ4Utils.Match match1 = new LZ4Utils.Match();
/* 429 */     LZ4Utils.Match match2 = new LZ4Utils.Match();
/* 430 */     LZ4Utils.Match match3 = new LZ4Utils.Match();
/*     */ 
/*     */     
/* 433 */     label68: while (sOff < mfLimit) {
/* 434 */       if (!ht.insertAndFindBestMatch(src, sOff, matchLimit, match1)) {
/* 435 */         sOff++;
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 440 */       LZ4Utils.copyTo(match1, match0);
/*     */ 
/*     */       
/*     */       label66: while (true) {
/* 444 */         assert match1.start >= anchor;
/* 445 */         if (match1.end() >= mfLimit || !ht.insertAndFindWiderMatch(src, match1.end() - 2, match1.start + 1, matchLimit, match1.len, match2)) {
/*     */ 
/*     */           
/* 448 */           dOff = LZ4ByteBufferUtils.encodeSequence(src, anchor, match1.start, match1.ref, match1.len, dest, dOff, destEnd);
/* 449 */           anchor = sOff = match1.end();
/*     */           
/*     */           continue label68;
/*     */         } 
/* 453 */         if (match0.start < match1.start && 
/* 454 */           match2.start < match1.start + match0.len) {
/* 455 */           LZ4Utils.copyTo(match0, match1);
/*     */         }
/*     */         
/* 458 */         assert match2.start > match1.start;
/*     */         
/* 460 */         if (match2.start - match1.start < 3) {
/* 461 */           LZ4Utils.copyTo(match2, match1);
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/*     */         while (true) {
/* 467 */           if (match2.start - match1.start < 18) {
/* 468 */             int newMatchLen = match1.len;
/* 469 */             if (newMatchLen > 18) {
/* 470 */               newMatchLen = 18;
/*     */             }
/* 472 */             if (match1.start + newMatchLen > match2.end() - 4) {
/* 473 */               newMatchLen = match2.start - match1.start + match2.len - 4;
/*     */             }
/* 475 */             int correction = newMatchLen - match2.start - match1.start;
/* 476 */             if (correction > 0) {
/* 477 */               match2.fix(correction);
/*     */             }
/*     */           } 
/*     */           
/* 481 */           if (match2.start + match2.len >= mfLimit || !ht.insertAndFindWiderMatch(src, match2.end() - 3, match2.start, matchLimit, match2.len, match3)) {
/*     */ 
/*     */             
/* 484 */             if (match2.start < match1.end()) {
/* 485 */               match1.len = match2.start - match1.start;
/*     */             }
/*     */             
/* 488 */             dOff = LZ4ByteBufferUtils.encodeSequence(src, anchor, match1.start, match1.ref, match1.len, dest, dOff, destEnd);
/* 489 */             anchor = sOff = match1.end();
/*     */             
/* 491 */             dOff = LZ4ByteBufferUtils.encodeSequence(src, anchor, match2.start, match2.ref, match2.len, dest, dOff, destEnd);
/* 492 */             anchor = sOff = match2.end();
/*     */             
/*     */             continue label68;
/*     */           } 
/* 496 */           if (match3.start < match1.end() + 3) {
/* 497 */             if (match3.start >= match1.end()) {
/* 498 */               if (match2.start < match1.end()) {
/* 499 */                 int correction = match1.end() - match2.start;
/* 500 */                 match2.fix(correction);
/* 501 */                 if (match2.len < 4) {
/* 502 */                   LZ4Utils.copyTo(match3, match2);
/*     */                 }
/*     */               } 
/*     */               
/* 506 */               dOff = LZ4ByteBufferUtils.encodeSequence(src, anchor, match1.start, match1.ref, match1.len, dest, dOff, destEnd);
/* 507 */               anchor = sOff = match1.end();
/*     */               
/* 509 */               LZ4Utils.copyTo(match3, match1);
/* 510 */               LZ4Utils.copyTo(match2, match0);
/*     */               
/*     */               continue label66;
/*     */             } 
/*     */             
/* 515 */             LZ4Utils.copyTo(match3, match2);
/*     */             
/*     */             continue;
/*     */           } 
/*     */           
/* 520 */           if (match2.start < match1.end()) {
/* 521 */             if (match2.start - match1.start < 15) {
/* 522 */               if (match1.len > 18) {
/* 523 */                 match1.len = 18;
/*     */               }
/* 525 */               if (match1.end() > match2.end() - 4) {
/* 526 */                 match1.len = match2.end() - match1.start - 4;
/*     */               }
/* 528 */               int correction = match1.end() - match2.start;
/* 529 */               match2.fix(correction);
/*     */             } else {
/* 531 */               match1.len = match2.start - match1.start;
/*     */             } 
/*     */           }
/*     */           
/* 535 */           dOff = LZ4ByteBufferUtils.encodeSequence(src, anchor, match1.start, match1.ref, match1.len, dest, dOff, destEnd);
/* 536 */           anchor = sOff = match1.end();
/*     */           
/* 538 */           LZ4Utils.copyTo(match2, match1);
/* 539 */           LZ4Utils.copyTo(match3, match2);
/*     */         } 
/*     */ 
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 548 */     dOff = LZ4ByteBufferUtils.lastLiterals(src, anchor, srcEnd - anchor, dest, dOff, destEnd);
/* 549 */     return dOff - destOff;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4HCJavaSafeCompressor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */