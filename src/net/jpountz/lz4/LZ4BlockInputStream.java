package net.jpountz.lz4;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Checksum;
import net.jpountz.util.SafeUtils;
import net.jpountz.xxhash.XXHashFactory;

public class LZ4BlockInputStream extends FilterInputStream {
   private final LZ4FastDecompressor decompressor;
   private final Checksum checksum;
   private final boolean stopOnEmptyBlock;
   private byte[] buffer;
   private byte[] compressedBuffer;
   private int originalLen;
   private int o;
   private boolean finished;

   public LZ4BlockInputStream(InputStream in, LZ4FastDecompressor decompressor, Checksum checksum, boolean stopOnEmptyBlock) {
      super(in);
      this.decompressor = decompressor;
      this.checksum = checksum;
      this.stopOnEmptyBlock = stopOnEmptyBlock;
      this.buffer = new byte[0];
      this.compressedBuffer = new byte[LZ4BlockOutputStream.HEADER_LENGTH];
      this.o = this.originalLen = 0;
      this.finished = false;
   }

   public LZ4BlockInputStream(InputStream in, LZ4FastDecompressor decompressor, Checksum checksum) {
      this(in, decompressor, checksum, true);
   }

   public LZ4BlockInputStream(InputStream in, LZ4FastDecompressor decompressor) {
      this(in, decompressor, XXHashFactory.fastestInstance().newStreamingHash32(-1756908916).asChecksum(), true);
   }

   public LZ4BlockInputStream(InputStream in, boolean stopOnEmptyBlock) {
      this(in, LZ4Factory.fastestInstance().fastDecompressor(), XXHashFactory.fastestInstance().newStreamingHash32(-1756908916).asChecksum(), stopOnEmptyBlock);
   }

   public LZ4BlockInputStream(InputStream in) {
      this(in, LZ4Factory.fastestInstance().fastDecompressor());
   }

   public int available() throws IOException {
      return this.originalLen - this.o;
   }

   public int read() throws IOException {
      if (this.finished) {
         return -1;
      } else {
         if (this.o == this.originalLen) {
            this.refill();
         }

         return this.finished ? -1 : this.buffer[this.o++] & 255;
      }
   }

   public int read(byte[] b, int off, int len) throws IOException {
      SafeUtils.checkRange(b, off, len);
      if (this.finished) {
         return -1;
      } else {
         if (this.o == this.originalLen) {
            this.refill();
         }

         if (this.finished) {
            return -1;
         } else {
            len = Math.min(len, this.originalLen - this.o);
            System.arraycopy(this.buffer, this.o, b, off, len);
            this.o += len;
            return len;
         }
      }
   }

   public int read(byte[] b) throws IOException {
      return this.read(b, 0, b.length);
   }

   public long skip(long n) throws IOException {
      if (n > 0L && !this.finished) {
         if (this.o == this.originalLen) {
            this.refill();
         }

         if (this.finished) {
            return 0L;
         } else {
            int skipped = (int)Math.min(n, (long)(this.originalLen - this.o));
            this.o += skipped;
            return (long)skipped;
         }
      } else {
         return 0L;
      }
   }

   private void refill() throws IOException {
      if (!this.tryReadFully(this.compressedBuffer, LZ4BlockOutputStream.HEADER_LENGTH)) {
         if (!this.stopOnEmptyBlock) {
            this.finished = true;
         } else {
            throw new EOFException("Stream ended prematurely");
         }
      } else {
         int i;
         for(i = 0; i < LZ4BlockOutputStream.MAGIC_LENGTH; ++i) {
            if (this.compressedBuffer[i] != LZ4BlockOutputStream.MAGIC[i]) {
               throw new IOException("Stream is corrupted");
            }
         }

         i = this.compressedBuffer[LZ4BlockOutputStream.MAGIC_LENGTH] & 255;
         int compressionMethod = i & 240;
         int compressionLevel = 10 + (i & 15);
         if (compressionMethod != 16 && compressionMethod != 32) {
            throw new IOException("Stream is corrupted");
         } else {
            int compressedLen = SafeUtils.readIntLE(this.compressedBuffer, LZ4BlockOutputStream.MAGIC_LENGTH + 1);
            this.originalLen = SafeUtils.readIntLE(this.compressedBuffer, LZ4BlockOutputStream.MAGIC_LENGTH + 5);
            int check = SafeUtils.readIntLE(this.compressedBuffer, LZ4BlockOutputStream.MAGIC_LENGTH + 9);

            assert LZ4BlockOutputStream.HEADER_LENGTH == LZ4BlockOutputStream.MAGIC_LENGTH + 13;

            if (this.originalLen > 1 << compressionLevel || this.originalLen < 0 || compressedLen < 0 || this.originalLen == 0 && compressedLen != 0 || this.originalLen != 0 && compressedLen == 0 || compressionMethod == 16 && this.originalLen != compressedLen) {
               throw new IOException("Stream is corrupted");
            } else if (this.originalLen == 0 && compressedLen == 0) {
               if (check != 0) {
                  throw new IOException("Stream is corrupted");
               } else {
                  if (!this.stopOnEmptyBlock) {
                     this.refill();
                  } else {
                     this.finished = true;
                  }

               }
            } else {
               if (this.buffer.length < this.originalLen) {
                  this.buffer = new byte[Math.max(this.originalLen, this.buffer.length * 3 / 2)];
               }

               switch(compressionMethod) {
               case 16:
                  this.readFully(this.buffer, this.originalLen);
                  break;
               case 32:
                  if (this.compressedBuffer.length < compressedLen) {
                     this.compressedBuffer = new byte[Math.max(compressedLen, this.compressedBuffer.length * 3 / 2)];
                  }

                  this.readFully(this.compressedBuffer, compressedLen);

                  try {
                     int compressedLen2 = this.decompressor.decompress((byte[])this.compressedBuffer, 0, (byte[])this.buffer, 0, this.originalLen);
                     if (compressedLen != compressedLen2) {
                        throw new IOException("Stream is corrupted");
                     }
                     break;
                  } catch (LZ4Exception var7) {
                     throw new IOException("Stream is corrupted", var7);
                  }
               default:
                  throw new AssertionError();
               }

               this.checksum.reset();
               this.checksum.update(this.buffer, 0, this.originalLen);
               if ((int)this.checksum.getValue() != check) {
                  throw new IOException("Stream is corrupted");
               } else {
                  this.o = 0;
               }
            }
         }
      }
   }

   private boolean tryReadFully(byte[] b, int len) throws IOException {
      int read;
      int r;
      for(read = 0; read < len; read += r) {
         r = this.in.read(b, read, len - read);
         if (r < 0) {
            return false;
         }
      }

      assert len == read;

      return true;
   }

   private void readFully(byte[] b, int len) throws IOException {
      if (!this.tryReadFully(b, len)) {
         throw new EOFException("Stream ended prematurely");
      }
   }

   public boolean markSupported() {
      return false;
   }

   public void mark(int readlimit) {
   }

   public void reset() throws IOException {
      throw new IOException("mark/reset not supported");
   }

   public String toString() {
      return this.getClass().getSimpleName() + "(in=" + this.in + ", decompressor=" + this.decompressor + ", checksum=" + this.checksum + ")";
   }
}
