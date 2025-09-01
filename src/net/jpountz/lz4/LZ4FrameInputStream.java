package net.jpountz.lz4;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;
import net.jpountz.xxhash.XXHash32;
import net.jpountz.xxhash.XXHashFactory;

public class LZ4FrameInputStream extends FilterInputStream {
   static final String PREMATURE_EOS = "Stream ended prematurely";
   static final String NOT_SUPPORTED = "Stream unsupported";
   static final String BLOCK_HASH_MISMATCH = "Block checksum mismatch";
   static final String DESCRIPTOR_HASH_MISMATCH = "Stream frame descriptor corrupted";
   static final int MAGIC_SKIPPABLE_BASE = 407710288;
   private final LZ4SafeDecompressor decompressor;
   private final XXHash32 checksum;
   private final byte[] headerArray;
   private final ByteBuffer headerBuffer;
   private final boolean readSingleFrame;
   private byte[] compressedBuffer;
   private ByteBuffer buffer;
   private byte[] rawBuffer;
   private int maxBlockSize;
   private long expectedContentSize;
   private long totalContentSize;
   private boolean firstFrameHeaderRead;
   private LZ4FrameOutputStream.FrameInfo frameInfo;
   private final ByteBuffer readNumberBuff;

   public LZ4FrameInputStream(InputStream in) throws IOException {
      this(in, LZ4Factory.fastestInstance().safeDecompressor(), XXHashFactory.fastestInstance().hash32());
   }

   public LZ4FrameInputStream(InputStream in, boolean readSingleFrame) throws IOException {
      this(in, LZ4Factory.fastestInstance().safeDecompressor(), XXHashFactory.fastestInstance().hash32(), readSingleFrame);
   }

   public LZ4FrameInputStream(InputStream in, LZ4SafeDecompressor decompressor, XXHash32 checksum) throws IOException {
      this(in, decompressor, checksum, false);
   }

   public LZ4FrameInputStream(InputStream in, LZ4SafeDecompressor decompressor, XXHash32 checksum, boolean readSingleFrame) throws IOException {
      super(in);
      this.headerArray = new byte[15];
      this.headerBuffer = ByteBuffer.wrap(this.headerArray).order(ByteOrder.LITTLE_ENDIAN);
      this.buffer = null;
      this.rawBuffer = null;
      this.maxBlockSize = -1;
      this.expectedContentSize = -1L;
      this.totalContentSize = 0L;
      this.firstFrameHeaderRead = false;
      this.frameInfo = null;
      this.readNumberBuff = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
      this.decompressor = decompressor;
      this.checksum = checksum;
      this.readSingleFrame = readSingleFrame;
   }

   private boolean nextFrameInfo() throws IOException {
      while(true) {
         int size = 0;

         int magic;
         do {
            magic = this.in.read(this.readNumberBuff.array(), size, 4 - size);
            if (magic < 0) {
               if (this.firstFrameHeaderRead) {
                  if (size > 0) {
                     throw new IOException("Stream ended prematurely");
                  }

                  return false;
               }

               throw new IOException("Stream ended prematurely");
            }

            size += magic;
         } while(size < 4);

         magic = this.readNumberBuff.getInt(0);
         if (magic == 407708164) {
            this.readHeader();
            return true;
         }

         if (magic >>> 4 != 25481893) {
            throw new IOException("Stream unsupported");
         }

         this.skippableFrame();
      }
   }

   private void skippableFrame() throws IOException {
      int skipSize = this.readInt(this.in);

      int mySize;
      for(byte[] skipBuffer = new byte[1024]; skipSize > 0; skipSize -= mySize) {
         mySize = this.in.read(skipBuffer, 0, Math.min(skipSize, skipBuffer.length));
         if (mySize < 0) {
            throw new IOException("Stream ended prematurely");
         }
      }

      this.firstFrameHeaderRead = true;
   }

   private void readHeader() throws IOException {
      this.headerBuffer.rewind();
      int flgRead = this.in.read();
      if (flgRead < 0) {
         throw new IOException("Stream ended prematurely");
      } else {
         int bdRead = this.in.read();
         if (bdRead < 0) {
            throw new IOException("Stream ended prematurely");
         } else {
            byte flgByte = (byte)(flgRead & 255);
            LZ4FrameOutputStream.FLG flg = LZ4FrameOutputStream.FLG.fromByte(flgByte);
            this.headerBuffer.put(flgByte);
            byte bdByte = (byte)(bdRead & 255);
            LZ4FrameOutputStream.BD bd = LZ4FrameOutputStream.BD.fromByte(bdByte);
            this.headerBuffer.put(bdByte);
            this.frameInfo = new LZ4FrameOutputStream.FrameInfo(flg, bd);
            if (flg.isEnabled(LZ4FrameOutputStream.FLG.Bits.CONTENT_SIZE)) {
               this.expectedContentSize = this.readLong(this.in);
               this.headerBuffer.putLong(this.expectedContentSize);
            }

            this.totalContentSize = 0L;
            byte hash = (byte)(this.checksum.hash((byte[])this.headerArray, 0, this.headerBuffer.position(), 0) >> 8 & 255);
            int expectedHash = this.in.read();
            if (expectedHash < 0) {
               throw new IOException("Stream ended prematurely");
            } else if (hash != (byte)(expectedHash & 255)) {
               throw new IOException("Stream frame descriptor corrupted");
            } else {
               this.maxBlockSize = this.frameInfo.getBD().getBlockMaximumSize();
               this.compressedBuffer = new byte[this.maxBlockSize];
               this.rawBuffer = new byte[this.maxBlockSize];
               this.buffer = ByteBuffer.wrap(this.rawBuffer);
               this.buffer.limit(0);
               this.firstFrameHeaderRead = true;
            }
         }
      }
   }

   private long readLong(InputStream stream) throws IOException {
      int offset = 0;

      do {
         int mySize = stream.read(this.readNumberBuff.array(), offset, 8 - offset);
         if (mySize < 0) {
            throw new IOException("Stream ended prematurely");
         }

         offset += mySize;
      } while(offset < 8);

      return this.readNumberBuff.getLong(0);
   }

   private int readInt(InputStream stream) throws IOException {
      int offset = 0;

      do {
         int mySize = stream.read(this.readNumberBuff.array(), offset, 4 - offset);
         if (mySize < 0) {
            throw new IOException("Stream ended prematurely");
         }

         offset += mySize;
      } while(offset < 4);

      return this.readNumberBuff.getInt(0);
   }

   private void readBlock() throws IOException {
      int blockSize = this.readInt(this.in);
      boolean compressed = (blockSize & Integer.MIN_VALUE) == 0;
      blockSize &= Integer.MAX_VALUE;
      if (blockSize == 0) {
         if (this.frameInfo.isEnabled(LZ4FrameOutputStream.FLG.Bits.CONTENT_CHECKSUM)) {
            int contentChecksum = this.readInt(this.in);
            if (contentChecksum != this.frameInfo.currentStreamHash()) {
               throw new IOException("Content checksum mismatch");
            }
         }

         if (this.frameInfo.isEnabled(LZ4FrameOutputStream.FLG.Bits.CONTENT_SIZE) && this.expectedContentSize != this.totalContentSize) {
            throw new IOException("Size check mismatch");
         } else {
            this.frameInfo.finish();
         }
      } else {
         byte[] tmpBuffer;
         if (compressed) {
            tmpBuffer = this.compressedBuffer;
         } else {
            tmpBuffer = this.rawBuffer;
         }

         if (blockSize > this.maxBlockSize) {
            throw new IOException(String.format(Locale.ROOT, "Block size %s exceeded max: %s", blockSize, this.maxBlockSize));
         } else {
            int currentBufferSize;
            for(int offset = 0; offset < blockSize; offset += currentBufferSize) {
               currentBufferSize = this.in.read(tmpBuffer, offset, blockSize - offset);
               if (currentBufferSize < 0) {
                  throw new IOException("Stream ended prematurely");
               }
            }

            if (this.frameInfo.isEnabled(LZ4FrameOutputStream.FLG.Bits.BLOCK_CHECKSUM)) {
               currentBufferSize = this.readInt(this.in);
               if (currentBufferSize != this.checksum.hash((byte[])tmpBuffer, 0, blockSize, 0)) {
                  throw new IOException("Block checksum mismatch");
               }
            }

            if (compressed) {
               try {
                  currentBufferSize = this.decompressor.decompress((byte[])tmpBuffer, 0, blockSize, (byte[])this.rawBuffer, 0, this.rawBuffer.length);
               } catch (LZ4Exception var7) {
                  throw new IOException(var7);
               }
            } else {
               currentBufferSize = blockSize;
            }

            if (this.frameInfo.isEnabled(LZ4FrameOutputStream.FLG.Bits.CONTENT_CHECKSUM)) {
               this.frameInfo.updateStreamHash(this.rawBuffer, 0, currentBufferSize);
            }

            this.totalContentSize += (long)currentBufferSize;
            this.buffer.limit(currentBufferSize);
            this.buffer.rewind();
         }
      }
   }

   public int read() throws IOException {
      for(; !this.firstFrameHeaderRead || this.buffer.remaining() == 0; this.readBlock()) {
         if (!this.firstFrameHeaderRead || this.frameInfo.isFinished()) {
            if (this.firstFrameHeaderRead && this.readSingleFrame) {
               return -1;
            }

            if (!this.nextFrameInfo()) {
               return -1;
            }
         }
      }

      return this.buffer.get() & 255;
   }

   public int read(byte[] b, int off, int len) throws IOException {
      if (off >= 0 && len >= 0 && off + len <= b.length) {
         for(; !this.firstFrameHeaderRead || this.buffer.remaining() == 0; this.readBlock()) {
            if (!this.firstFrameHeaderRead || this.frameInfo.isFinished()) {
               if (this.firstFrameHeaderRead && this.readSingleFrame) {
                  return -1;
               }

               if (!this.nextFrameInfo()) {
                  return -1;
               }
            }
         }

         len = Math.min(len, this.buffer.remaining());
         this.buffer.get(b, off, len);
         return len;
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public long skip(long n) throws IOException {
      if (n <= 0L) {
         return 0L;
      } else {
         for(; !this.firstFrameHeaderRead || this.buffer.remaining() == 0; this.readBlock()) {
            if (!this.firstFrameHeaderRead || this.frameInfo.isFinished()) {
               if (this.firstFrameHeaderRead && this.readSingleFrame) {
                  return 0L;
               }

               if (!this.nextFrameInfo()) {
                  return 0L;
               }
            }
         }

         n = Math.min(n, (long)this.buffer.remaining());
         this.buffer.position(this.buffer.position() + (int)n);
         return n;
      }
   }

   public int available() throws IOException {
      return this.buffer.remaining();
   }

   public void close() throws IOException {
      super.close();
   }

   public synchronized void mark(int readlimit) {
      throw new UnsupportedOperationException("mark not supported");
   }

   public synchronized void reset() throws IOException {
      throw new UnsupportedOperationException("reset not supported");
   }

   public boolean markSupported() {
      return false;
   }

   public long getExpectedContentSize() throws IOException {
      if (!this.readSingleFrame) {
         throw new UnsupportedOperationException("Operation not permitted when multiple frames can be read");
      } else {
         return !this.firstFrameHeaderRead && !this.nextFrameInfo() ? -1L : this.expectedContentSize;
      }
   }

   public boolean isExpectedContentSizeDefined() throws IOException {
      if (this.readSingleFrame) {
         if (!this.firstFrameHeaderRead && !this.nextFrameInfo()) {
            return false;
         } else {
            return this.expectedContentSize >= 0L;
         }
      } else {
         return false;
      }
   }
}
