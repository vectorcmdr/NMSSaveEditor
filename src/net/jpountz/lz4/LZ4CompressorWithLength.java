package net.jpountz.lz4;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class LZ4CompressorWithLength {
   private final LZ4Compressor compressor;

   public LZ4CompressorWithLength(LZ4Compressor compressor) {
      this.compressor = compressor;
   }

   private void putOriginalLength(byte[] dest, int destOff, int originalLength) {
      dest[destOff] = (byte)originalLength;
      dest[destOff + 1] = (byte)(originalLength >> 8);
      dest[destOff + 2] = (byte)(originalLength >> 16);
      dest[destOff + 3] = (byte)(originalLength >> 24);
   }

   private void putOriginalLength(ByteBuffer dest, int destOff, int originalLength) {
      dest.put(destOff, (byte)originalLength);
      dest.put(destOff + 1, (byte)(originalLength >> 8));
      dest.put(destOff + 2, (byte)(originalLength >> 16));
      dest.put(destOff + 3, (byte)(originalLength >> 24));
   }

   public int maxCompressedLength(int length) {
      return this.compressor.maxCompressedLength(length) + 4;
   }

   public byte[] compress(byte[] src) {
      return this.compress(src, 0, src.length);
   }

   public byte[] compress(byte[] src, int srcOff, int srcLen) {
      int maxCompressedLength = this.maxCompressedLength(srcLen);
      byte[] compressed = new byte[maxCompressedLength];
      int compressedLength = this.compress(src, srcOff, srcLen, compressed, 0);
      return Arrays.copyOf(compressed, compressedLength);
   }

   public int compress(byte[] src, byte[] dest) {
      return this.compress(src, 0, src.length, dest, 0);
   }

   public int compress(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff) {
      return this.compress(src, srcOff, srcLen, dest, destOff, dest.length - destOff);
   }

   public int compress(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff, int maxDestLen) {
      int compressedLength = this.compressor.compress(src, srcOff, srcLen, dest, destOff + 4, maxDestLen - 4);
      this.putOriginalLength(dest, destOff, srcLen);
      return compressedLength + 4;
   }

   public void compress(ByteBuffer src, ByteBuffer dest) {
      int compressedLength = this.compress(src, src.position(), src.remaining(), dest, dest.position(), dest.remaining());
      src.position(src.limit());
      dest.position(dest.position() + compressedLength);
   }

   public int compress(ByteBuffer src, int srcOff, int srcLen, ByteBuffer dest, int destOff, int maxDestLen) {
      int compressedLength = this.compressor.compress(src, srcOff, srcLen, dest, destOff + 4, maxDestLen - 4);
      this.putOriginalLength(dest, destOff, srcLen);
      return compressedLength + 4;
   }
}
