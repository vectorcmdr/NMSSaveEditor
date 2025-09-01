package net.jpountz.lz4;

/** @deprecated */
@Deprecated
public interface LZ4UnknownSizeDecompressor {
   int decompress(byte[] var1, int var2, int var3, byte[] var4, int var5, int var6);

   int decompress(byte[] var1, int var2, int var3, byte[] var4, int var5);
}
