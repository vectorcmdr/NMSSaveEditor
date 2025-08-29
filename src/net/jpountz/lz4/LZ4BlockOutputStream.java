/*     */ package net.jpountz.lz4;
/*     */ 
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.zip.Checksum;
/*     */ import net.jpountz.util.SafeUtils;
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
/*     */ public class LZ4BlockOutputStream
/*     */   extends FilterOutputStream
/*     */ {
/*  39 */   static final byte[] MAGIC = new byte[] { 76, 90, 52, 66, 108, 111, 99, 107 };
/*  40 */   static final int MAGIC_LENGTH = MAGIC.length;
/*     */   
/*  42 */   static final int HEADER_LENGTH = MAGIC_LENGTH + 1 + 4 + 4 + 4;
/*     */   
/*     */   static final int COMPRESSION_LEVEL_BASE = 10;
/*     */   
/*     */   static final int MIN_BLOCK_SIZE = 64;
/*     */   
/*     */   static final int MAX_BLOCK_SIZE = 33554432;
/*     */   
/*     */   static final int COMPRESSION_METHOD_RAW = 16;
/*     */   
/*     */   static final int COMPRESSION_METHOD_LZ4 = 32;
/*     */   
/*     */   static final int DEFAULT_SEED = -1756908916;
/*     */   
/*     */   private final int blockSize;
/*     */   
/*     */   private static int compressionLevel(int blockSize) {
/*  59 */     if (blockSize < 64)
/*  60 */       throw new IllegalArgumentException("blockSize must be >= 64, got " + blockSize); 
/*  61 */     if (blockSize > 33554432) {
/*  62 */       throw new IllegalArgumentException("blockSize must be <= 33554432, got " + blockSize);
/*     */     }
/*  64 */     int compressionLevel = 32 - Integer.numberOfLeadingZeros(blockSize - 1);
/*  65 */     assert 1 << compressionLevel >= blockSize;
/*  66 */     assert blockSize * 2 > 1 << compressionLevel;
/*  67 */     compressionLevel = Math.max(0, compressionLevel - 10);
/*  68 */     assert compressionLevel >= 0 && compressionLevel <= 15;
/*  69 */     return compressionLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final int compressionLevel;
/*     */ 
/*     */   
/*     */   private final LZ4Compressor compressor;
/*     */ 
/*     */   
/*     */   private final Checksum checksum;
/*     */ 
/*     */   
/*     */   private final byte[] buffer;
/*     */ 
/*     */   
/*     */   private final byte[] compressedBuffer;
/*     */ 
/*     */   
/*     */   private final boolean syncFlush;
/*     */   
/*     */   private boolean finished;
/*     */   
/*     */   private int o;
/*     */ 
/*     */   
/*     */   public LZ4BlockOutputStream(OutputStream out, int blockSize, LZ4Compressor compressor, Checksum checksum, boolean syncFlush) {
/*  97 */     super(out);
/*  98 */     this.blockSize = blockSize;
/*  99 */     this.compressor = compressor;
/* 100 */     this.checksum = checksum;
/* 101 */     this.compressionLevel = compressionLevel(blockSize);
/* 102 */     this.buffer = new byte[blockSize];
/* 103 */     int compressedBlockSize = HEADER_LENGTH + compressor.maxCompressedLength(blockSize);
/* 104 */     this.compressedBuffer = new byte[compressedBlockSize];
/* 105 */     this.syncFlush = syncFlush;
/* 106 */     this.o = 0;
/* 107 */     this.finished = false;
/* 108 */     System.arraycopy(MAGIC, 0, this.compressedBuffer, 0, MAGIC_LENGTH);
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
/*     */ 
/*     */   
/*     */   public LZ4BlockOutputStream(OutputStream out, int blockSize, LZ4Compressor compressor) {
/* 125 */     this(out, blockSize, compressor, XXHashFactory.fastestInstance().newStreamingHash32(-1756908916).asChecksum(), false);
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
/*     */   public LZ4BlockOutputStream(OutputStream out, int blockSize) {
/* 140 */     this(out, blockSize, LZ4Factory.fastestInstance().fastCompressor());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LZ4BlockOutputStream(OutputStream out) {
/* 151 */     this(out, 65536);
/*     */   }
/*     */   
/*     */   private void ensureNotFinished() {
/* 155 */     if (this.finished) {
/* 156 */       throw new IllegalStateException("This stream is already closed");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(int b) throws IOException {
/* 162 */     ensureNotFinished();
/* 163 */     if (this.o == this.blockSize) {
/* 164 */       flushBufferedData();
/*     */     }
/* 166 */     this.buffer[this.o++] = (byte)b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/* 171 */     SafeUtils.checkRange(b, off, len);
/* 172 */     ensureNotFinished();
/*     */     
/* 174 */     while (this.o + len > this.blockSize) {
/* 175 */       int l = this.blockSize - this.o;
/* 176 */       System.arraycopy(b, off, this.buffer, this.o, this.blockSize - this.o);
/* 177 */       this.o = this.blockSize;
/* 178 */       flushBufferedData();
/* 179 */       off += l;
/* 180 */       len -= l;
/*     */     } 
/* 182 */     System.arraycopy(b, off, this.buffer, this.o, len);
/* 183 */     this.o += len;
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/* 188 */     ensureNotFinished();
/* 189 */     write(b, 0, b.length);
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 194 */     if (!this.finished) {
/* 195 */       finish();
/*     */     }
/* 197 */     if (this.out != null) {
/* 198 */       this.out.close();
/* 199 */       this.out = null;
/*     */     } 
/*     */   }
/*     */   private void flushBufferedData() throws IOException {
/*     */     int compressMethod;
/* 204 */     if (this.o == 0) {
/*     */       return;
/*     */     }
/* 207 */     this.checksum.reset();
/* 208 */     this.checksum.update(this.buffer, 0, this.o);
/* 209 */     int check = (int)this.checksum.getValue();
/* 210 */     int compressedLength = this.compressor.compress(this.buffer, 0, this.o, this.compressedBuffer, HEADER_LENGTH);
/*     */     
/* 212 */     if (compressedLength >= this.o) {
/* 213 */       compressMethod = 16;
/* 214 */       compressedLength = this.o;
/* 215 */       System.arraycopy(this.buffer, 0, this.compressedBuffer, HEADER_LENGTH, this.o);
/*     */     } else {
/* 217 */       compressMethod = 32;
/*     */     } 
/*     */     
/* 220 */     this.compressedBuffer[MAGIC_LENGTH] = (byte)(compressMethod | this.compressionLevel);
/* 221 */     writeIntLE(compressedLength, this.compressedBuffer, MAGIC_LENGTH + 1);
/* 222 */     writeIntLE(this.o, this.compressedBuffer, MAGIC_LENGTH + 5);
/* 223 */     writeIntLE(check, this.compressedBuffer, MAGIC_LENGTH + 9);
/* 224 */     assert MAGIC_LENGTH + 13 == HEADER_LENGTH;
/* 225 */     this.out.write(this.compressedBuffer, 0, HEADER_LENGTH + compressedLength);
/* 226 */     this.o = 0;
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
/*     */   public void flush() throws IOException {
/* 241 */     if (this.out != null) {
/* 242 */       if (this.syncFlush) {
/* 243 */         flushBufferedData();
/*     */       }
/* 245 */       this.out.flush();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void finish() throws IOException {
/* 256 */     ensureNotFinished();
/* 257 */     flushBufferedData();
/* 258 */     this.compressedBuffer[MAGIC_LENGTH] = (byte)(0x10 | this.compressionLevel);
/* 259 */     writeIntLE(0, this.compressedBuffer, MAGIC_LENGTH + 1);
/* 260 */     writeIntLE(0, this.compressedBuffer, MAGIC_LENGTH + 5);
/* 261 */     writeIntLE(0, this.compressedBuffer, MAGIC_LENGTH + 9);
/* 262 */     assert MAGIC_LENGTH + 13 == HEADER_LENGTH;
/* 263 */     this.out.write(this.compressedBuffer, 0, HEADER_LENGTH);
/* 264 */     this.finished = true;
/* 265 */     this.out.flush();
/*     */   }
/*     */   
/*     */   private static void writeIntLE(int i, byte[] buf, int off) {
/* 269 */     buf[off++] = (byte)i;
/* 270 */     buf[off++] = (byte)(i >>> 8);
/* 271 */     buf[off++] = (byte)(i >>> 16);
/* 272 */     buf[off++] = (byte)(i >>> 24);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 277 */     return getClass().getSimpleName() + "(out=" + this.out + ", blockSize=" + this.blockSize + ", compressor=" + this.compressor + ", checksum=" + this.checksum + ")";
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4BlockOutputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */