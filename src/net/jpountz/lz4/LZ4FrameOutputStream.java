/*     */ package net.jpountz.lz4;
/*     */ 
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.util.Arrays;
/*     */ import java.util.BitSet;
/*     */ import java.util.Locale;
/*     */ import net.jpountz.xxhash.StreamingXXHash32;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LZ4FrameOutputStream
/*     */   extends FilterOutputStream
/*     */ {
/*     */   static final int INTEGER_BYTES = 4;
/*     */   static final int LONG_BYTES = 8;
/*     */   static final int MAGIC = 407708164;
/*     */   static final int LZ4_MAX_HEADER_LENGTH = 15;
/*     */   static final int LZ4_FRAME_INCOMPRESSIBLE_MASK = -2147483648;
/*  58 */   static final FLG.Bits[] DEFAULT_FEATURES = new FLG.Bits[] { FLG.Bits.BLOCK_INDEPENDENCE }; static final String CLOSED_STREAM = "The stream is already closed"; private final LZ4Compressor compressor; private final XXHash32 checksum; private final ByteBuffer buffer;
/*     */   private final byte[] compressedBuffer;
/*     */   private final int maxBlockSize;
/*     */   private final long knownSize;
/*     */   
/*  63 */   public enum BLOCKSIZE { SIZE_64KB(4), SIZE_256KB(5), SIZE_1MB(6), SIZE_4MB(7); private final int indicator;
/*     */     
/*     */     BLOCKSIZE(int indicator) {
/*  66 */       this.indicator = indicator;
/*     */     }
/*     */     public int getIndicator() {
/*  69 */       return this.indicator;
/*     */     } }
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
/*  88 */   private final ByteBuffer intLEBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
/*     */   
/*  90 */   private FrameInfo frameInfo = null;
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
/*     */   public LZ4FrameOutputStream(OutputStream out, BLOCKSIZE blockSize, FLG.Bits... bits) throws IOException {
/* 104 */     this(out, blockSize, -1L, bits);
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
/*     */   public LZ4FrameOutputStream(OutputStream out, BLOCKSIZE blockSize, long knownSize, FLG.Bits... bits) throws IOException {
/* 117 */     this(out, blockSize, knownSize, LZ4Factory.fastestInstance().fastCompressor(), XXHashFactory.fastestInstance().hash32(), bits);
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
/*     */   public LZ4FrameOutputStream(OutputStream out, BLOCKSIZE blockSize, long knownSize, LZ4Compressor compressor, XXHash32 checksum, FLG.Bits... bits) throws IOException {
/* 134 */     super(out);
/* 135 */     this.compressor = compressor;
/* 136 */     this.checksum = checksum;
/* 137 */     this.frameInfo = new FrameInfo(new FLG(1, bits), new BD(blockSize));
/* 138 */     this.maxBlockSize = this.frameInfo.getBD().getBlockMaximumSize();
/* 139 */     this.buffer = ByteBuffer.allocate(this.maxBlockSize).order(ByteOrder.LITTLE_ENDIAN);
/* 140 */     this.compressedBuffer = new byte[this.compressor.maxCompressedLength(this.maxBlockSize)];
/* 141 */     if (this.frameInfo.getFLG().isEnabled(FLG.Bits.CONTENT_SIZE) && knownSize < 0L) {
/* 142 */       throw new IllegalArgumentException("Known size must be greater than zero in order to use the known size feature");
/*     */     }
/* 144 */     this.knownSize = knownSize;
/* 145 */     writeHeader();
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
/*     */   public LZ4FrameOutputStream(OutputStream out, BLOCKSIZE blockSize) throws IOException {
/* 158 */     this(out, blockSize, DEFAULT_FEATURES);
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
/*     */   public LZ4FrameOutputStream(OutputStream out) throws IOException {
/* 170 */     this(out, BLOCKSIZE.SIZE_4MB);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeHeader() throws IOException {
/* 179 */     ByteBuffer headerBuffer = ByteBuffer.allocate(15).order(ByteOrder.LITTLE_ENDIAN);
/* 180 */     headerBuffer.putInt(407708164);
/* 181 */     headerBuffer.put(this.frameInfo.getFLG().toByte());
/* 182 */     headerBuffer.put(this.frameInfo.getBD().toByte());
/* 183 */     if (this.frameInfo.isEnabled(FLG.Bits.CONTENT_SIZE)) {
/* 184 */       headerBuffer.putLong(this.knownSize);
/*     */     }
/*     */     
/* 187 */     int hash = this.checksum.hash(headerBuffer.array(), 4, headerBuffer.position() - 4, 0) >> 8 & 0xFF;
/* 188 */     headerBuffer.put((byte)hash);
/*     */     
/* 190 */     this.out.write(headerBuffer.array(), 0, headerBuffer.position());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeBlock() throws IOException {
/*     */     byte[] bufferToWrite;
/*     */     int compressMethod;
/* 200 */     if (this.buffer.position() == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 204 */     Arrays.fill(this.compressedBuffer, (byte)0);
/*     */     
/* 206 */     if (this.frameInfo.isEnabled(FLG.Bits.CONTENT_CHECKSUM)) {
/* 207 */       this.frameInfo.updateStreamHash(this.buffer.array(), 0, this.buffer.position());
/*     */     }
/*     */     
/* 210 */     int compressedLength = this.compressor.compress(this.buffer.array(), 0, this.buffer.position(), this.compressedBuffer, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 215 */     if (compressedLength >= this.buffer.position()) {
/* 216 */       compressedLength = this.buffer.position();
/* 217 */       bufferToWrite = Arrays.copyOf(this.buffer.array(), compressedLength);
/* 218 */       compressMethod = Integer.MIN_VALUE;
/*     */     } else {
/* 220 */       bufferToWrite = this.compressedBuffer;
/* 221 */       compressMethod = 0;
/*     */     } 
/*     */ 
/*     */     
/* 225 */     this.intLEBuffer.putInt(0, compressedLength | compressMethod);
/* 226 */     this.out.write(this.intLEBuffer.array());
/* 227 */     this.out.write(bufferToWrite, 0, compressedLength);
/*     */ 
/*     */     
/* 230 */     if (this.frameInfo.isEnabled(FLG.Bits.BLOCK_CHECKSUM)) {
/* 231 */       this.intLEBuffer.putInt(0, this.checksum.hash(bufferToWrite, 0, compressedLength, 0));
/* 232 */       this.out.write(this.intLEBuffer.array());
/*     */     } 
/* 234 */     this.buffer.rewind();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeEndMark() throws IOException {
/* 244 */     this.intLEBuffer.putInt(0, 0);
/* 245 */     this.out.write(this.intLEBuffer.array());
/* 246 */     if (this.frameInfo.isEnabled(FLG.Bits.CONTENT_CHECKSUM)) {
/* 247 */       this.intLEBuffer.putInt(0, this.frameInfo.currentStreamHash());
/* 248 */       this.out.write(this.intLEBuffer.array());
/*     */     } 
/* 250 */     this.frameInfo.finish();
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(int b) throws IOException {
/* 255 */     ensureNotFinished();
/* 256 */     if (this.buffer.position() == this.maxBlockSize) {
/* 257 */       writeBlock();
/*     */     }
/* 259 */     this.buffer.put((byte)b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/* 264 */     if (off < 0 || len < 0 || off + len > b.length) {
/* 265 */       throw new IndexOutOfBoundsException();
/*     */     }
/* 267 */     ensureNotFinished();
/*     */ 
/*     */     
/* 270 */     while (len > this.buffer.remaining()) {
/* 271 */       int sizeWritten = this.buffer.remaining();
/*     */       
/* 273 */       this.buffer.put(b, off, sizeWritten);
/* 274 */       writeBlock();
/*     */       
/* 276 */       off += sizeWritten;
/* 277 */       len -= sizeWritten;
/*     */     } 
/* 279 */     this.buffer.put(b, off, len);
/*     */   }
/*     */ 
/*     */   
/*     */   public void flush() throws IOException {
/* 284 */     if (!this.frameInfo.isFinished()) {
/* 285 */       writeBlock();
/*     */     }
/* 287 */     super.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ensureNotFinished() {
/* 294 */     if (this.frameInfo.isFinished()) {
/* 295 */       throw new IllegalStateException("The stream is already closed");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 301 */     if (!this.frameInfo.isFinished()) {
/* 302 */       flush();
/* 303 */       writeEndMark();
/*     */     } 
/* 305 */     super.close();
/*     */   }
/*     */   
/*     */   public static class FLG
/*     */   {
/*     */     private static final int DEFAULT_VERSION = 1;
/*     */     private final BitSet bitSet;
/*     */     private final int version;
/*     */     
/*     */     public enum Bits {
/* 315 */       RESERVED_0(0),
/* 316 */       RESERVED_1(1),
/* 317 */       CONTENT_CHECKSUM(2),
/* 318 */       CONTENT_SIZE(3),
/* 319 */       BLOCK_CHECKSUM(4),
/* 320 */       BLOCK_INDEPENDENCE(5);
/*     */       private final int position;
/*     */       
/*     */       Bits(int position) {
/* 324 */         this.position = position;
/*     */       }
/*     */     }
/*     */     
/*     */     public FLG(int version, Bits... bits) {
/* 329 */       this.bitSet = new BitSet(8);
/* 330 */       this.version = version;
/* 331 */       if (bits != null) {
/* 332 */         for (Bits bit : bits) {
/* 333 */           this.bitSet.set(bit.position);
/*     */         }
/*     */       }
/* 336 */       validate();
/*     */     }
/*     */     
/*     */     private FLG(int version, byte b) {
/* 340 */       this.bitSet = BitSet.valueOf(new byte[] { b });
/* 341 */       this.version = version;
/* 342 */       validate();
/*     */     }
/*     */     
/*     */     public static FLG fromByte(byte flg) {
/* 346 */       byte versionMask = (byte)(flg & 0xC0);
/* 347 */       return new FLG(versionMask >>> 6, (byte)(flg ^ versionMask));
/*     */     }
/*     */     
/*     */     public byte toByte() {
/* 351 */       return (byte)(this.bitSet.toByteArray()[0] | (this.version & 0x3) << 6);
/*     */     }
/*     */     
/*     */     private void validate() {
/* 355 */       if (this.bitSet.get(Bits.RESERVED_0.position)) {
/* 356 */         throw new RuntimeException("Reserved0 field must be 0");
/*     */       }
/* 358 */       if (this.bitSet.get(Bits.RESERVED_1.position)) {
/* 359 */         throw new RuntimeException("Reserved1 field must be 0");
/*     */       }
/* 361 */       if (!this.bitSet.get(Bits.BLOCK_INDEPENDENCE.position)) {
/* 362 */         throw new RuntimeException("Dependent block stream is unsupported (BLOCK_INDEPENDENCE must be set)");
/*     */       }
/* 364 */       if (this.version != 1) {
/* 365 */         throw new RuntimeException(String.format(Locale.ROOT, "Version %d is unsupported", new Object[] { Integer.valueOf(this.version) }));
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean isEnabled(Bits bit) {
/* 370 */       return this.bitSet.get(bit.position);
/*     */     }
/*     */     
/*     */     public int getVersion() {
/* 374 */       return this.version;
/*     */     }
/*     */   } public enum Bits { RESERVED_0(0),
/*     */     RESERVED_1(1),
/*     */     CONTENT_CHECKSUM(2),
/*     */     CONTENT_SIZE(3),
/*     */     BLOCK_CHECKSUM(4),
/*     */     BLOCK_INDEPENDENCE(5); private final int position; Bits(int position) {
/*     */       this.position = position;
/*     */     } } public static class BD { private static final int RESERVED_MASK = 143; private BD(LZ4FrameOutputStream.BLOCKSIZE blockSizeValue) {
/* 384 */       this.blockSizeValue = blockSizeValue;
/*     */     }
/*     */     private final LZ4FrameOutputStream.BLOCKSIZE blockSizeValue;
/*     */     public static BD fromByte(byte bd) {
/* 388 */       int blockMaximumSize = bd >>> 4 & 0x7;
/* 389 */       if ((bd & 0x8F) > 0) {
/* 390 */         throw new RuntimeException("Reserved fields must be 0");
/*     */       }
/*     */       
/* 393 */       return new BD(LZ4FrameOutputStream.BLOCKSIZE.valueOf(blockMaximumSize));
/*     */     }
/*     */ 
/*     */     
/*     */     public int getBlockMaximumSize() {
/* 398 */       return 1 << 2 * this.blockSizeValue.getIndicator() + 8;
/*     */     }
/*     */     
/*     */     public byte toByte() {
/* 402 */       return (byte)((this.blockSizeValue.getIndicator() & 0x7) << 4);
/*     */     } }
/*     */ 
/*     */   
/*     */   static class FrameInfo {
/*     */     private final LZ4FrameOutputStream.FLG flg;
/*     */     private final LZ4FrameOutputStream.BD bd;
/*     */     private final StreamingXXHash32 streamHash;
/*     */     private boolean finished = false;
/*     */     
/*     */     public FrameInfo(LZ4FrameOutputStream.FLG flg, LZ4FrameOutputStream.BD bd) {
/* 413 */       this.flg = flg;
/* 414 */       this.bd = bd;
/* 415 */       this.streamHash = flg.isEnabled(LZ4FrameOutputStream.FLG.Bits.CONTENT_CHECKSUM) ? XXHashFactory.fastestInstance().newStreamingHash32(0) : null;
/*     */     }
/*     */     
/*     */     public boolean isEnabled(LZ4FrameOutputStream.FLG.Bits bit) {
/* 419 */       return this.flg.isEnabled(bit);
/*     */     }
/*     */     
/*     */     public LZ4FrameOutputStream.FLG getFLG() {
/* 423 */       return this.flg;
/*     */     }
/*     */     
/*     */     public LZ4FrameOutputStream.BD getBD() {
/* 427 */       return this.bd;
/*     */     }
/*     */     
/*     */     public void updateStreamHash(byte[] buff, int off, int len) {
/* 431 */       this.streamHash.update(buff, off, len);
/*     */     }
/*     */     
/*     */     public int currentStreamHash() {
/* 435 */       return this.streamHash.getValue();
/*     */     }
/*     */     
/*     */     public void finish() {
/* 439 */       this.finished = true;
/*     */     }
/*     */     
/*     */     public boolean isFinished() {
/* 443 */       return this.finished;
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4FrameOutputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */