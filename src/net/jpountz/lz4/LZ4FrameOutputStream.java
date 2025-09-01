package net.jpountz.lz4;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Locale;
import net.jpountz.xxhash.StreamingXXHash32;
import net.jpountz.xxhash.XXHash32;
import net.jpountz.xxhash.XXHashFactory;

public class LZ4FrameOutputStream extends FilterOutputStream {
   static final int INTEGER_BYTES = 4;
   static final int LONG_BYTES = 8;
   static final int MAGIC = 407708164;
   static final int LZ4_MAX_HEADER_LENGTH = 15;
   static final int LZ4_FRAME_INCOMPRESSIBLE_MASK = Integer.MIN_VALUE;
   static final LZ4FrameOutputStream.FLG.Bits[] DEFAULT_FEATURES;
   static final String CLOSED_STREAM = "The stream is already closed";
   private final LZ4Compressor compressor;
   private final XXHash32 checksum;
   private final ByteBuffer buffer;
   private final byte[] compressedBuffer;
   private final int maxBlockSize;
   private final long knownSize;
   private final ByteBuffer intLEBuffer;
   private LZ4FrameOutputStream.FrameInfo frameInfo;

   public LZ4FrameOutputStream(OutputStream out, LZ4FrameOutputStream.BLOCKSIZE blockSize, LZ4FrameOutputStream.FLG.Bits... bits) throws IOException {
      this(out, blockSize, -1L, bits);
   }

   public LZ4FrameOutputStream(OutputStream out, LZ4FrameOutputStream.BLOCKSIZE blockSize, long knownSize, LZ4FrameOutputStream.FLG.Bits... bits) throws IOException {
      this(out, blockSize, knownSize, LZ4Factory.fastestInstance().fastCompressor(), XXHashFactory.fastestInstance().hash32(), bits);
   }

   public LZ4FrameOutputStream(OutputStream out, LZ4FrameOutputStream.BLOCKSIZE blockSize, long knownSize, LZ4Compressor compressor, XXHash32 checksum, LZ4FrameOutputStream.FLG.Bits... bits) throws IOException {
      super(out);
      this.intLEBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
      this.frameInfo = null;
      this.compressor = compressor;
      this.checksum = checksum;
      this.frameInfo = new LZ4FrameOutputStream.FrameInfo(new LZ4FrameOutputStream.FLG(1, bits), new LZ4FrameOutputStream.BD(blockSize));
      this.maxBlockSize = this.frameInfo.getBD().getBlockMaximumSize();
      this.buffer = ByteBuffer.allocate(this.maxBlockSize).order(ByteOrder.LITTLE_ENDIAN);
      this.compressedBuffer = new byte[this.compressor.maxCompressedLength(this.maxBlockSize)];
      if (this.frameInfo.getFLG().isEnabled(LZ4FrameOutputStream.FLG.Bits.CONTENT_SIZE) && knownSize < 0L) {
         throw new IllegalArgumentException("Known size must be greater than zero in order to use the known size feature");
      } else {
         this.knownSize = knownSize;
         this.writeHeader();
      }
   }

   public LZ4FrameOutputStream(OutputStream out, LZ4FrameOutputStream.BLOCKSIZE blockSize) throws IOException {
      this(out, blockSize, DEFAULT_FEATURES);
   }

   public LZ4FrameOutputStream(OutputStream out) throws IOException {
      this(out, LZ4FrameOutputStream.BLOCKSIZE.SIZE_4MB);
   }

   private void writeHeader() throws IOException {
      ByteBuffer headerBuffer = ByteBuffer.allocate(15).order(ByteOrder.LITTLE_ENDIAN);
      headerBuffer.putInt(407708164);
      headerBuffer.put(this.frameInfo.getFLG().toByte());
      headerBuffer.put(this.frameInfo.getBD().toByte());
      if (this.frameInfo.isEnabled(LZ4FrameOutputStream.FLG.Bits.CONTENT_SIZE)) {
         headerBuffer.putLong(this.knownSize);
      }

      int hash = this.checksum.hash((byte[])headerBuffer.array(), 4, headerBuffer.position() - 4, 0) >> 8 & 255;
      headerBuffer.put((byte)hash);
      this.out.write(headerBuffer.array(), 0, headerBuffer.position());
   }

   private void writeBlock() throws IOException {
      if (this.buffer.position() != 0) {
         Arrays.fill(this.compressedBuffer, (byte)0);
         if (this.frameInfo.isEnabled(LZ4FrameOutputStream.FLG.Bits.CONTENT_CHECKSUM)) {
            this.frameInfo.updateStreamHash(this.buffer.array(), 0, this.buffer.position());
         }

         int compressedLength = this.compressor.compress(this.buffer.array(), 0, this.buffer.position(), this.compressedBuffer, 0);
         byte[] bufferToWrite;
         int compressMethod;
         if (compressedLength >= this.buffer.position()) {
            compressedLength = this.buffer.position();
            bufferToWrite = Arrays.copyOf(this.buffer.array(), compressedLength);
            compressMethod = Integer.MIN_VALUE;
         } else {
            bufferToWrite = this.compressedBuffer;
            compressMethod = 0;
         }

         this.intLEBuffer.putInt(0, compressedLength | compressMethod);
         this.out.write(this.intLEBuffer.array());
         this.out.write(bufferToWrite, 0, compressedLength);
         if (this.frameInfo.isEnabled(LZ4FrameOutputStream.FLG.Bits.BLOCK_CHECKSUM)) {
            this.intLEBuffer.putInt(0, this.checksum.hash((byte[])bufferToWrite, 0, compressedLength, 0));
            this.out.write(this.intLEBuffer.array());
         }

         this.buffer.rewind();
      }
   }

   private void writeEndMark() throws IOException {
      this.intLEBuffer.putInt(0, 0);
      this.out.write(this.intLEBuffer.array());
      if (this.frameInfo.isEnabled(LZ4FrameOutputStream.FLG.Bits.CONTENT_CHECKSUM)) {
         this.intLEBuffer.putInt(0, this.frameInfo.currentStreamHash());
         this.out.write(this.intLEBuffer.array());
      }

      this.frameInfo.finish();
   }

   public void write(int b) throws IOException {
      this.ensureNotFinished();
      if (this.buffer.position() == this.maxBlockSize) {
         this.writeBlock();
      }

      this.buffer.put((byte)b);
   }

   public void write(byte[] b, int off, int len) throws IOException {
      if (off >= 0 && len >= 0 && off + len <= b.length) {
         this.ensureNotFinished();

         while(len > this.buffer.remaining()) {
            int sizeWritten = this.buffer.remaining();
            this.buffer.put(b, off, sizeWritten);
            this.writeBlock();
            off += sizeWritten;
            len -= sizeWritten;
         }

         this.buffer.put(b, off, len);
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void flush() throws IOException {
      if (!this.frameInfo.isFinished()) {
         this.writeBlock();
      }

      super.flush();
   }

   private void ensureNotFinished() {
      if (this.frameInfo.isFinished()) {
         throw new IllegalStateException("The stream is already closed");
      }
   }

   public void close() throws IOException {
      if (!this.frameInfo.isFinished()) {
         this.flush();
         this.writeEndMark();
      }

      super.close();
   }

   static {
      DEFAULT_FEATURES = new LZ4FrameOutputStream.FLG.Bits[]{LZ4FrameOutputStream.FLG.Bits.BLOCK_INDEPENDENCE};
   }

   static class FrameInfo {
      private final LZ4FrameOutputStream.FLG flg;
      private final LZ4FrameOutputStream.BD bd;
      private final StreamingXXHash32 streamHash;
      private boolean finished = false;

      public FrameInfo(LZ4FrameOutputStream.FLG flg, LZ4FrameOutputStream.BD bd) {
         this.flg = flg;
         this.bd = bd;
         this.streamHash = flg.isEnabled(LZ4FrameOutputStream.FLG.Bits.CONTENT_CHECKSUM) ? XXHashFactory.fastestInstance().newStreamingHash32(0) : null;
      }

      public boolean isEnabled(LZ4FrameOutputStream.FLG.Bits bit) {
         return this.flg.isEnabled(bit);
      }

      public LZ4FrameOutputStream.FLG getFLG() {
         return this.flg;
      }

      public LZ4FrameOutputStream.BD getBD() {
         return this.bd;
      }

      public void updateStreamHash(byte[] buff, int off, int len) {
         this.streamHash.update(buff, off, len);
      }

      public int currentStreamHash() {
         return this.streamHash.getValue();
      }

      public void finish() {
         this.finished = true;
      }

      public boolean isFinished() {
         return this.finished;
      }
   }

   public static class BD {
      private static final int RESERVED_MASK = 143;
      private final LZ4FrameOutputStream.BLOCKSIZE blockSizeValue;

      private BD(LZ4FrameOutputStream.BLOCKSIZE blockSizeValue) {
         this.blockSizeValue = blockSizeValue;
      }

      public static LZ4FrameOutputStream.BD fromByte(byte bd) {
         int blockMaximumSize = bd >>> 4 & 7;
         if ((bd & 143) > 0) {
            throw new RuntimeException("Reserved fields must be 0");
         } else {
            return new LZ4FrameOutputStream.BD(LZ4FrameOutputStream.BLOCKSIZE.valueOf(blockMaximumSize));
         }
      }

      public int getBlockMaximumSize() {
         return 1 << 2 * this.blockSizeValue.getIndicator() + 8;
      }

      public byte toByte() {
         return (byte)((this.blockSizeValue.getIndicator() & 7) << 4);
      }

      // $FF: synthetic method
      BD(LZ4FrameOutputStream.BLOCKSIZE x0, Object x1) {
         this(x0);
      }
   }

   public static class FLG {
      private static final int DEFAULT_VERSION = 1;
      private final BitSet bitSet;
      private final int version;

      public FLG(int version, LZ4FrameOutputStream.FLG.Bits... bits) {
         this.bitSet = new BitSet(8);
         this.version = version;
         if (bits != null) {
            LZ4FrameOutputStream.FLG.Bits[] arr$ = bits;
            int len$ = bits.length;

            for(int i$ = 0; i$ < len$; ++i$) {
               LZ4FrameOutputStream.FLG.Bits bit = arr$[i$];
               this.bitSet.set(bit.position);
            }
         }

         this.validate();
      }

      private FLG(int version, byte b) {
         this.bitSet = BitSet.valueOf(new byte[]{b});
         this.version = version;
         this.validate();
      }

      public static LZ4FrameOutputStream.FLG fromByte(byte flg) {
         byte versionMask = (byte)(flg & 192);
         return new LZ4FrameOutputStream.FLG(versionMask >>> 6, (byte)(flg ^ versionMask));
      }

      public byte toByte() {
         return (byte)(this.bitSet.toByteArray()[0] | (this.version & 3) << 6);
      }

      private void validate() {
         if (this.bitSet.get(LZ4FrameOutputStream.FLG.Bits.RESERVED_0.position)) {
            throw new RuntimeException("Reserved0 field must be 0");
         } else if (this.bitSet.get(LZ4FrameOutputStream.FLG.Bits.RESERVED_1.position)) {
            throw new RuntimeException("Reserved1 field must be 0");
         } else if (!this.bitSet.get(LZ4FrameOutputStream.FLG.Bits.BLOCK_INDEPENDENCE.position)) {
            throw new RuntimeException("Dependent block stream is unsupported (BLOCK_INDEPENDENCE must be set)");
         } else if (this.version != 1) {
            throw new RuntimeException(String.format(Locale.ROOT, "Version %d is unsupported", this.version));
         }
      }

      public boolean isEnabled(LZ4FrameOutputStream.FLG.Bits bit) {
         return this.bitSet.get(bit.position);
      }

      public int getVersion() {
         return this.version;
      }

      public static enum Bits {
         RESERVED_0(0),
         RESERVED_1(1),
         CONTENT_CHECKSUM(2),
         CONTENT_SIZE(3),
         BLOCK_CHECKSUM(4),
         BLOCK_INDEPENDENCE(5);

         private final int position;

         private Bits(int position) {
            this.position = position;
         }
      }
   }

   public static enum BLOCKSIZE {
      SIZE_64KB(4),
      SIZE_256KB(5),
      SIZE_1MB(6),
      SIZE_4MB(7);

      private final int indicator;

      private BLOCKSIZE(int indicator) {
         this.indicator = indicator;
      }

      public int getIndicator() {
         return this.indicator;
      }

      public static LZ4FrameOutputStream.BLOCKSIZE valueOf(int indicator) {
         switch(indicator) {
         case 4:
            return SIZE_64KB;
         case 5:
            return SIZE_256KB;
         case 6:
            return SIZE_1MB;
         case 7:
            return SIZE_4MB;
         default:
            throw new IllegalArgumentException(String.format(Locale.ROOT, "Block size must be 4-7. Cannot use value of [%d]", indicator));
         }
      }
   }
}
