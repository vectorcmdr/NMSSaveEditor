package net.jpountz.lz4;

import java.nio.ByteBuffer;
import java.util.Arrays;

public abstract class LZ4SafeDecompressor implements LZ4UnknownSizeDecompressor {
   public abstract int decompress(byte[] var1, int var2, int var3, byte[] var4, int var5, int var6);

   public abstract int decompress(ByteBuffer var1, int var2, int var3, ByteBuffer var4, int var5, int var6);

   public final int decompress(byte[] src, int srcOff, int srcLen, byte[] dest, int destOff) {
      return this.decompress(src, srcOff, srcLen, dest, destOff, dest.length - destOff);
   }

   public final int decompress(byte[] src, byte[] dest) {
      return this.decompress(src, 0, src.length, dest, 0);
   }

   public final byte[] decompress(byte[] src, int srcOff, int srcLen, int maxDestLen) {
      byte[] decompressed = new byte[maxDestLen];
      int decompressedLength = this.decompress((byte[])src, srcOff, srcLen, (byte[])decompressed, 0, maxDestLen);
      if (decompressedLength != decompressed.length) {
         decompressed = Arrays.copyOf(decompressed, decompressedLength);
      }

      return decompressed;
   }

   public final byte[] decompress(byte[] src, int maxDestLen) {
      return this.decompress(src, 0, src.length, maxDestLen);
   }

   public final void decompress(ByteBuffer src, ByteBuffer dest) {
      int decompressed = this.decompress(src, src.position(), src.remaining(), dest, dest.position(), dest.remaining());
      src.position(src.limit());
      dest.position(dest.position() + decompressed);
   }

   public String toString() {
      return this.getClass().getSimpleName();
   }
}
