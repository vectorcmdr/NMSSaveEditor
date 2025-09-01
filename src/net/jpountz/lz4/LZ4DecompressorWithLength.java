package net.jpountz.lz4;

import java.nio.ByteBuffer;

public class LZ4DecompressorWithLength {
   private final LZ4FastDecompressor fastDecompressor;
   private final LZ4SafeDecompressor safeDecompressor;

   public static int getDecompressedLength(byte[] src) {
      return getDecompressedLength((byte[])src, 0);
   }

   public static int getDecompressedLength(byte[] src, int srcOff) {
      return src[srcOff] & 255 | (src[srcOff + 1] & 255) << 8 | (src[srcOff + 2] & 255) << 16 | src[srcOff + 3] << 24;
   }

   public static int getDecompressedLength(ByteBuffer src) {
      return getDecompressedLength(src, src.position());
   }

   public static int getDecompressedLength(ByteBuffer src, int srcOff) {
      return src.get(srcOff) & 255 | (src.get(srcOff + 1) & 255) << 8 | (src.get(srcOff + 2) & 255) << 16 | src.get(srcOff + 3) << 24;
   }

   public LZ4DecompressorWithLength(LZ4FastDecompressor fastDecompressor) {
      this.fastDecompressor = fastDecompressor;
      this.safeDecompressor = null;
   }

   public LZ4DecompressorWithLength(LZ4SafeDecompressor safeDecompressor) {
      this.fastDecompressor = null;
      this.safeDecompressor = safeDecompressor;
   }

   public int decompress(byte[] src, byte[] dest) {
      return this.decompress((byte[])src, 0, (byte[])dest, 0);
   }

   public int decompress(byte[] src, int srcOff, byte[] dest, int destOff) {
      if (this.safeDecompressor != null) {
         return this.decompress(src, srcOff, src.length - srcOff, dest, destOff);
      } else {
         int destLen = getDecompressedLength(src, srcOff);
         return this.fastDecompressor.decompress(src, srcOff + 4, dest, destOff, destLen) + 4;
      }
   }

   public int decompress(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff) {
      if (this.safeDecompressor == null) {
         return this.decompress(src, srcOff, dest, destOff);
      } else {
         int destLen = getDecompressedLength(src, srcOff);
         return this.safeDecompressor.decompress(src, srcOff + 4, srcLen - 4, dest, destOff, destLen);
      }
   }

   public byte[] decompress(byte[] src) {
      return this.decompress(src, 0);
   }

   public byte[] decompress(byte[] src, int srcOff) {
      if (this.safeDecompressor != null) {
         return this.decompress(src, srcOff, src.length - srcOff);
      } else {
         int destLen = getDecompressedLength(src, srcOff);
         return this.fastDecompressor.decompress(src, srcOff + 4, destLen);
      }
   }

   public byte[] decompress(byte[] src, int srcOff, int srcLen) {
      if (this.safeDecompressor == null) {
         return this.decompress(src, srcOff);
      } else {
         int destLen = getDecompressedLength(src, srcOff);
         return this.safeDecompressor.decompress(src, srcOff + 4, srcLen - 4, destLen);
      }
   }

   public void decompress(ByteBuffer src, ByteBuffer dest) {
      int destLen = getDecompressedLength(src, src.position());
      int read;
      if (this.safeDecompressor == null) {
         read = this.fastDecompressor.decompress(src, src.position() + 4, dest, dest.position(), destLen);
         src.position(src.position() + 4 + read);
         dest.position(dest.position() + destLen);
      } else {
         read = this.safeDecompressor.decompress(src, src.position() + 4, src.remaining() - 4, dest, dest.position(), destLen);
         src.position(src.limit());
         dest.position(dest.position() + read);
      }

   }

   public int decompress(ByteBuffer src, int srcOff, ByteBuffer dest, int destOff) {
      if (this.safeDecompressor != null) {
         return this.decompress(src, srcOff, src.remaining() - srcOff, dest, destOff);
      } else {
         int destLen = getDecompressedLength(src, srcOff);
         return this.fastDecompressor.decompress(src, srcOff + 4, dest, destOff, destLen) + 4;
      }
   }

   public int decompress(ByteBuffer src, int srcOff, int srcLen, ByteBuffer dest, int destOff) {
      if (this.safeDecompressor == null) {
         return this.decompress(src, srcOff, dest, destOff);
      } else {
         int destLen = getDecompressedLength(src, srcOff);
         return this.safeDecompressor.decompress(src, srcOff + 4, srcLen - 4, dest, destOff, destLen);
      }
   }
}
