/*     */ package net.jpountz.lz4;
/*     */ 
/*     */ import java.io.FilterInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.util.Locale;
/*     */ import net.jpountz.xxhash.XXHash32;
/*     */ import net.jpountz.xxhash.XXHashFactory;
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
/*     */ public class LZ4FrameInputStream
/*     */   extends FilterInputStream
/*     */ {
/*     */   static final String PREMATURE_EOS = "Stream ended prematurely";
/*     */   static final String NOT_SUPPORTED = "Stream unsupported";
/*     */   static final String BLOCK_HASH_MISMATCH = "Block checksum mismatch";
/*     */   static final String DESCRIPTOR_HASH_MISMATCH = "Stream frame descriptor corrupted";
/*     */   static final int MAGIC_SKIPPABLE_BASE = 407710288;
/*     */   private final LZ4SafeDecompressor decompressor;
/*     */   private final XXHash32 checksum;
/*  51 */   private final byte[] headerArray = new byte[15];
/*  52 */   private final ByteBuffer headerBuffer = ByteBuffer.wrap(this.headerArray).order(ByteOrder.LITTLE_ENDIAN);
/*     */   private final boolean readSingleFrame;
/*     */   private byte[] compressedBuffer;
/*  55 */   private ByteBuffer buffer = null;
/*  56 */   private byte[] rawBuffer = null;
/*  57 */   private int maxBlockSize = -1;
/*  58 */   private long expectedContentSize = -1L;
/*  59 */   private long totalContentSize = 0L;
/*     */   
/*     */   private boolean firstFrameHeaderRead = false;
/*  62 */   private LZ4FrameOutputStream.FrameInfo frameInfo = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final ByteBuffer readNumberBuff;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LZ4FrameInputStream(InputStream in) throws IOException {
/*  76 */     this(in, LZ4Factory.fastestInstance().safeDecompressor(), XXHashFactory.fastestInstance().hash32());
/*     */   }
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
/*     */   public LZ4FrameInputStream(InputStream in, boolean readSingleFrame) throws IOException {
/*  91 */     this(in, LZ4Factory.fastestInstance().safeDecompressor(), XXHashFactory.fastestInstance().hash32(), readSingleFrame);
/*     */   }
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
/*     */   public LZ4FrameInputStream(InputStream in, LZ4SafeDecompressor decompressor, XXHash32 checksum) throws IOException {
/* 106 */     this(in, decompressor, checksum, false);
/*     */   } private boolean nextFrameInfo() throws IOException { while (true) { int size = 0; while (true) { int mySize = this.in.read(this.readNumberBuff.array(), size, 4 - size); if (mySize < 0) { if (this.firstFrameHeaderRead) { if (size > 0)
/*     */               throw new IOException("Stream ended prematurely");  return false; }
/*     */            throw new IOException("Stream ended prematurely"); }
/*     */          size += mySize; if (size >= 4) { int magic = this.readNumberBuff.getInt(0); if (magic == 407708164) { readHeader(); return true; }
/*     */            if (magic >>> 4 == 25481893)
/*     */             break;  throw new IOException("Stream unsupported"); }
/*     */          }
/*     */        skippableFrame(); }
/*     */      throw new IOException("Stream unsupported"); } private void skippableFrame() throws IOException { int skipSize = readInt(this.in); byte[] skipBuffer = new byte[1024]; while (skipSize > 0) {
/*     */       int mySize = this.in.read(skipBuffer, 0, Math.min(skipSize, skipBuffer.length)); if (mySize < 0)
/*     */         throw new IOException("Stream ended prematurely");  skipSize -= mySize;
/*     */     }  this.firstFrameHeaderRead = true; }
/* 119 */   public LZ4FrameInputStream(InputStream in, LZ4SafeDecompressor decompressor, XXHash32 checksum, boolean readSingleFrame) throws IOException { super(in);
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
/* 226 */     this.readNumberBuff = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
/*     */     this.decompressor = decompressor;
/*     */     this.checksum = checksum;
/* 229 */     this.readSingleFrame = readSingleFrame; } private long readLong(InputStream stream) throws IOException { int offset = 0;
/*     */     while (true)
/* 231 */     { int mySize = stream.read(this.readNumberBuff.array(), offset, 8 - offset);
/* 232 */       if (mySize < 0) {
/* 233 */         throw new IOException("Stream ended prematurely");
/*     */       }
/* 235 */       offset += mySize;
/* 236 */       if (offset >= 8)
/* 237 */         return this.readNumberBuff.getLong(0);  }  }
/*     */   private void readHeader() throws IOException { this.headerBuffer.rewind(); int flgRead = this.in.read(); if (flgRead < 0) throw new IOException("Stream ended prematurely");  int bdRead = this.in.read(); if (bdRead < 0)
/*     */       throw new IOException("Stream ended prematurely");  byte flgByte = (byte)(flgRead & 0xFF); LZ4FrameOutputStream.FLG flg = LZ4FrameOutputStream.FLG.fromByte(flgByte); this.headerBuffer.put(flgByte); byte bdByte = (byte)(bdRead & 0xFF); LZ4FrameOutputStream.BD bd = LZ4FrameOutputStream.BD.fromByte(bdByte); this.headerBuffer.put(bdByte); this.frameInfo = new LZ4FrameOutputStream.FrameInfo(flg, bd); if (flg.isEnabled(LZ4FrameOutputStream.FLG.Bits.CONTENT_SIZE)) { this.expectedContentSize = readLong(this.in); this.headerBuffer.putLong(this.expectedContentSize); }  this.totalContentSize = 0L; byte hash = (byte)(this.checksum.hash(this.headerArray, 0, this.headerBuffer.position(), 0) >> 8 & 0xFF); int expectedHash = this.in.read(); if (expectedHash < 0)
/*     */       throw new IOException("Stream ended prematurely");  if (hash != (byte)(expectedHash & 0xFF))
/* 241 */       throw new IOException("Stream frame descriptor corrupted");  this.maxBlockSize = this.frameInfo.getBD().getBlockMaximumSize(); this.compressedBuffer = new byte[this.maxBlockSize]; this.rawBuffer = new byte[this.maxBlockSize]; this.buffer = ByteBuffer.wrap(this.rawBuffer); this.buffer.limit(0); this.firstFrameHeaderRead = true; } private int readInt(InputStream stream) throws IOException { int offset = 0;
/*     */     while (true) {
/* 243 */       int mySize = stream.read(this.readNumberBuff.array(), offset, 4 - offset);
/* 244 */       if (mySize < 0) {
/* 245 */         throw new IOException("Stream ended prematurely");
/*     */       }
/* 247 */       offset += mySize;
/* 248 */       if (offset >= 4) {
/* 249 */         return this.readNumberBuff.getInt(0);
/*     */       }
/*     */     }  }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readBlock() throws IOException {
/*     */     byte[] tmpBuffer;
/* 259 */     int currentBufferSize, blockSize = readInt(this.in);
/* 260 */     boolean compressed = ((blockSize & Integer.MIN_VALUE) == 0);
/* 261 */     blockSize &= Integer.MAX_VALUE;
/*     */ 
/*     */     
/* 264 */     if (blockSize == 0) {
/* 265 */       if (this.frameInfo.isEnabled(LZ4FrameOutputStream.FLG.Bits.CONTENT_CHECKSUM)) {
/* 266 */         int contentChecksum = readInt(this.in);
/* 267 */         if (contentChecksum != this.frameInfo.currentStreamHash()) {
/* 268 */           throw new IOException("Content checksum mismatch");
/*     */         }
/*     */       } 
/* 271 */       if (this.frameInfo.isEnabled(LZ4FrameOutputStream.FLG.Bits.CONTENT_SIZE) && this.expectedContentSize != this.totalContentSize) {
/* 272 */         throw new IOException("Size check mismatch");
/*     */       }
/* 274 */       this.frameInfo.finish();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 279 */     if (compressed) {
/* 280 */       tmpBuffer = this.compressedBuffer;
/*     */     } else {
/* 282 */       tmpBuffer = this.rawBuffer;
/*     */     } 
/* 284 */     if (blockSize > this.maxBlockSize) {
/* 285 */       throw new IOException(String.format(Locale.ROOT, "Block size %s exceeded max: %s", new Object[] { Integer.valueOf(blockSize), Integer.valueOf(this.maxBlockSize) }));
/*     */     }
/*     */     
/* 288 */     int offset = 0;
/* 289 */     while (offset < blockSize) {
/* 290 */       int lastRead = this.in.read(tmpBuffer, offset, blockSize - offset);
/* 291 */       if (lastRead < 0) {
/* 292 */         throw new IOException("Stream ended prematurely");
/*     */       }
/* 294 */       offset += lastRead;
/*     */     } 
/*     */ 
/*     */     
/* 298 */     if (this.frameInfo.isEnabled(LZ4FrameOutputStream.FLG.Bits.BLOCK_CHECKSUM)) {
/* 299 */       int hashCheck = readInt(this.in);
/* 300 */       if (hashCheck != this.checksum.hash(tmpBuffer, 0, blockSize, 0)) {
/* 301 */         throw new IOException("Block checksum mismatch");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 306 */     if (compressed) {
/*     */       try {
/* 308 */         currentBufferSize = this.decompressor.decompress(tmpBuffer, 0, blockSize, this.rawBuffer, 0, this.rawBuffer.length);
/* 309 */       } catch (LZ4Exception e) {
/* 310 */         throw new IOException(e);
/*     */       } 
/*     */     } else {
/* 313 */       currentBufferSize = blockSize;
/*     */     } 
/* 315 */     if (this.frameInfo.isEnabled(LZ4FrameOutputStream.FLG.Bits.CONTENT_CHECKSUM)) {
/* 316 */       this.frameInfo.updateStreamHash(this.rawBuffer, 0, currentBufferSize);
/*     */     }
/* 318 */     this.totalContentSize += currentBufferSize;
/* 319 */     this.buffer.limit(currentBufferSize);
/* 320 */     this.buffer.rewind();
/*     */   }
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/* 325 */     while (!this.firstFrameHeaderRead || this.buffer.remaining() == 0) {
/* 326 */       if (!this.firstFrameHeaderRead || this.frameInfo.isFinished()) {
/* 327 */         if (this.firstFrameHeaderRead && this.readSingleFrame) {
/* 328 */           return -1;
/*     */         }
/* 330 */         if (!nextFrameInfo()) {
/* 331 */           return -1;
/*     */         }
/*     */       } 
/* 334 */       readBlock();
/*     */     } 
/* 336 */     return this.buffer.get() & 0xFF;
/*     */   }
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 341 */     if (off < 0 || len < 0 || off + len > b.length) {
/* 342 */       throw new IndexOutOfBoundsException();
/*     */     }
/* 344 */     while (!this.firstFrameHeaderRead || this.buffer.remaining() == 0) {
/* 345 */       if (!this.firstFrameHeaderRead || this.frameInfo.isFinished()) {
/* 346 */         if (this.firstFrameHeaderRead && this.readSingleFrame) {
/* 347 */           return -1;
/*     */         }
/* 349 */         if (!nextFrameInfo()) {
/* 350 */           return -1;
/*     */         }
/*     */       } 
/* 353 */       readBlock();
/*     */     } 
/* 355 */     len = Math.min(len, this.buffer.remaining());
/* 356 */     this.buffer.get(b, off, len);
/* 357 */     return len;
/*     */   }
/*     */ 
/*     */   
/*     */   public long skip(long n) throws IOException {
/* 362 */     if (n <= 0L) {
/* 363 */       return 0L;
/*     */     }
/* 365 */     while (!this.firstFrameHeaderRead || this.buffer.remaining() == 0) {
/* 366 */       if (!this.firstFrameHeaderRead || this.frameInfo.isFinished()) {
/* 367 */         if (this.firstFrameHeaderRead && this.readSingleFrame) {
/* 368 */           return 0L;
/*     */         }
/* 370 */         if (!nextFrameInfo()) {
/* 371 */           return 0L;
/*     */         }
/*     */       } 
/* 374 */       readBlock();
/*     */     } 
/* 376 */     n = Math.min(n, this.buffer.remaining());
/* 377 */     this.buffer.position(this.buffer.position() + (int)n);
/* 378 */     return n;
/*     */   }
/*     */ 
/*     */   
/*     */   public int available() throws IOException {
/* 383 */     return this.buffer.remaining();
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 388 */     super.close();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void mark(int readlimit) {
/* 393 */     throw new UnsupportedOperationException("mark not supported");
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void reset() throws IOException {
/* 398 */     throw new UnsupportedOperationException("reset not supported");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean markSupported() {
/* 403 */     return false;
/*     */   }
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
/*     */   public long getExpectedContentSize() throws IOException {
/* 417 */     if (!this.readSingleFrame) {
/* 418 */       throw new UnsupportedOperationException("Operation not permitted when multiple frames can be read");
/*     */     }
/* 420 */     if (!this.firstFrameHeaderRead && 
/* 421 */       !nextFrameInfo()) {
/* 422 */       return -1L;
/*     */     }
/*     */     
/* 425 */     return this.expectedContentSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isExpectedContentSizeDefined() throws IOException {
/* 435 */     if (this.readSingleFrame) {
/* 436 */       if (!this.firstFrameHeaderRead && 
/* 437 */         !nextFrameInfo()) {
/* 438 */         return false;
/*     */       }
/*     */       
/* 441 */       return (this.expectedContentSize >= 0L);
/*     */     } 
/* 443 */     return false;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4FrameInputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */