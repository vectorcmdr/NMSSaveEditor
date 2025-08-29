/*     */ package net.jpountz.lz4;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.FilterInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
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
/*     */ public class LZ4BlockInputStream
/*     */   extends FilterInputStream
/*     */ {
/*     */   private final LZ4FastDecompressor decompressor;
/*     */   private final Checksum checksum;
/*     */   private final boolean stopOnEmptyBlock;
/*     */   private byte[] buffer;
/*     */   private byte[] compressedBuffer;
/*     */   private int originalLen;
/*     */   private int o;
/*     */   private boolean finished;
/*     */   
/*     */   public LZ4BlockInputStream(InputStream in, LZ4FastDecompressor decompressor, Checksum checksum, boolean stopOnEmptyBlock) {
/*  67 */     super(in);
/*  68 */     this.decompressor = decompressor;
/*  69 */     this.checksum = checksum;
/*  70 */     this.stopOnEmptyBlock = stopOnEmptyBlock;
/*  71 */     this.buffer = new byte[0];
/*  72 */     this.compressedBuffer = new byte[LZ4BlockOutputStream.HEADER_LENGTH];
/*  73 */     this.o = this.originalLen = 0;
/*  74 */     this.finished = false;
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
/*     */   public LZ4BlockInputStream(InputStream in, LZ4FastDecompressor decompressor, Checksum checksum) {
/*  90 */     this(in, decompressor, checksum, true);
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
/*     */   public LZ4BlockInputStream(InputStream in, LZ4FastDecompressor decompressor) {
/* 104 */     this(in, decompressor, XXHashFactory.fastestInstance().newStreamingHash32(-1756908916).asChecksum(), true);
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
/*     */   public LZ4BlockInputStream(InputStream in, boolean stopOnEmptyBlock) {
/* 118 */     this(in, LZ4Factory.fastestInstance().fastDecompressor(), XXHashFactory.fastestInstance().newStreamingHash32(-1756908916).asChecksum(), stopOnEmptyBlock);
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
/*     */   public LZ4BlockInputStream(InputStream in) {
/* 130 */     this(in, LZ4Factory.fastestInstance().fastDecompressor());
/*     */   }
/*     */ 
/*     */   
/*     */   public int available() throws IOException {
/* 135 */     return this.originalLen - this.o;
/*     */   }
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/* 140 */     if (this.finished) {
/* 141 */       return -1;
/*     */     }
/* 143 */     if (this.o == this.originalLen) {
/* 144 */       refill();
/*     */     }
/* 146 */     if (this.finished) {
/* 147 */       return -1;
/*     */     }
/* 149 */     return this.buffer[this.o++] & 0xFF;
/*     */   }
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 154 */     SafeUtils.checkRange(b, off, len);
/* 155 */     if (this.finished) {
/* 156 */       return -1;
/*     */     }
/* 158 */     if (this.o == this.originalLen) {
/* 159 */       refill();
/*     */     }
/* 161 */     if (this.finished) {
/* 162 */       return -1;
/*     */     }
/* 164 */     len = Math.min(len, this.originalLen - this.o);
/* 165 */     System.arraycopy(this.buffer, this.o, b, off, len);
/* 166 */     this.o += len;
/* 167 */     return len;
/*     */   }
/*     */ 
/*     */   
/*     */   public int read(byte[] b) throws IOException {
/* 172 */     return read(b, 0, b.length);
/*     */   }
/*     */ 
/*     */   
/*     */   public long skip(long n) throws IOException {
/* 177 */     if (n <= 0L || this.finished) {
/* 178 */       return 0L;
/*     */     }
/* 180 */     if (this.o == this.originalLen) {
/* 181 */       refill();
/*     */     }
/* 183 */     if (this.finished) {
/* 184 */       return 0L;
/*     */     }
/* 186 */     int skipped = (int)Math.min(n, (this.originalLen - this.o));
/* 187 */     this.o += skipped;
/* 188 */     return skipped;
/*     */   }
/*     */   
/*     */   private void refill() throws IOException {
/* 192 */     if (!tryReadFully(this.compressedBuffer, LZ4BlockOutputStream.HEADER_LENGTH)) {
/* 193 */       if (!this.stopOnEmptyBlock) {
/* 194 */         this.finished = true;
/*     */       } else {
/* 196 */         throw new EOFException("Stream ended prematurely");
/*     */       } 
/*     */       return;
/*     */     } 
/* 200 */     for (int i = 0; i < LZ4BlockOutputStream.MAGIC_LENGTH; i++) {
/* 201 */       if (this.compressedBuffer[i] != LZ4BlockOutputStream.MAGIC[i]) {
/* 202 */         throw new IOException("Stream is corrupted");
/*     */       }
/*     */     } 
/* 205 */     int token = this.compressedBuffer[LZ4BlockOutputStream.MAGIC_LENGTH] & 0xFF;
/* 206 */     int compressionMethod = token & 0xF0;
/* 207 */     int compressionLevel = 10 + (token & 0xF);
/* 208 */     if (compressionMethod != 16 && compressionMethod != 32) {
/* 209 */       throw new IOException("Stream is corrupted");
/*     */     }
/* 211 */     int compressedLen = SafeUtils.readIntLE(this.compressedBuffer, LZ4BlockOutputStream.MAGIC_LENGTH + 1);
/* 212 */     this.originalLen = SafeUtils.readIntLE(this.compressedBuffer, LZ4BlockOutputStream.MAGIC_LENGTH + 5);
/* 213 */     int check = SafeUtils.readIntLE(this.compressedBuffer, LZ4BlockOutputStream.MAGIC_LENGTH + 9);
/* 214 */     assert LZ4BlockOutputStream.HEADER_LENGTH == LZ4BlockOutputStream.MAGIC_LENGTH + 13;
/* 215 */     if (this.originalLen > 1 << compressionLevel || this.originalLen < 0 || compressedLen < 0 || (this.originalLen == 0 && compressedLen != 0) || (this.originalLen != 0 && compressedLen == 0) || (compressionMethod == 16 && this.originalLen != compressedLen))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 221 */       throw new IOException("Stream is corrupted");
/*     */     }
/* 223 */     if (this.originalLen == 0 && compressedLen == 0) {
/* 224 */       if (check != 0) {
/* 225 */         throw new IOException("Stream is corrupted");
/*     */       }
/* 227 */       if (!this.stopOnEmptyBlock) {
/* 228 */         refill();
/*     */       } else {
/* 230 */         this.finished = true;
/*     */       } 
/*     */       return;
/*     */     } 
/* 234 */     if (this.buffer.length < this.originalLen) {
/* 235 */       this.buffer = new byte[Math.max(this.originalLen, this.buffer.length * 3 / 2)];
/*     */     }
/* 237 */     switch (compressionMethod) {
/*     */       case 16:
/* 239 */         readFully(this.buffer, this.originalLen);
/*     */         break;
/*     */       case 32:
/* 242 */         if (this.compressedBuffer.length < compressedLen) {
/* 243 */           this.compressedBuffer = new byte[Math.max(compressedLen, this.compressedBuffer.length * 3 / 2)];
/*     */         }
/* 245 */         readFully(this.compressedBuffer, compressedLen);
/*     */         try {
/* 247 */           int compressedLen2 = this.decompressor.decompress(this.compressedBuffer, 0, this.buffer, 0, this.originalLen);
/* 248 */           if (compressedLen != compressedLen2) {
/* 249 */             throw new IOException("Stream is corrupted");
/*     */           }
/* 251 */         } catch (LZ4Exception e) {
/* 252 */           throw new IOException("Stream is corrupted", e);
/*     */         } 
/*     */         break;
/*     */       default:
/* 256 */         throw new AssertionError();
/*     */     } 
/* 258 */     this.checksum.reset();
/* 259 */     this.checksum.update(this.buffer, 0, this.originalLen);
/* 260 */     if ((int)this.checksum.getValue() != check) {
/* 261 */       throw new IOException("Stream is corrupted");
/*     */     }
/* 263 */     this.o = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean tryReadFully(byte[] b, int len) throws IOException {
/* 269 */     int read = 0;
/* 270 */     while (read < len) {
/* 271 */       int r = this.in.read(b, read, len - read);
/* 272 */       if (r < 0) {
/* 273 */         return false;
/*     */       }
/* 275 */       read += r;
/*     */     } 
/* 277 */     assert len == read;
/* 278 */     return true;
/*     */   }
/*     */   
/*     */   private void readFully(byte[] b, int len) throws IOException {
/* 282 */     if (!tryReadFully(b, len)) {
/* 283 */       throw new EOFException("Stream ended prematurely");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean markSupported() {
/* 289 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mark(int readlimit) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() throws IOException {
/* 301 */     throw new IOException("mark/reset not supported");
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 306 */     return getClass().getSimpleName() + "(in=" + this.in + ", decompressor=" + this.decompressor + ", checksum=" + this.checksum + ")";
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4BlockInputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */