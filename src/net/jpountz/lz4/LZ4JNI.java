package net.jpountz.lz4;

import java.nio.ByteBuffer;
import net.jpountz.util.Native;

enum LZ4JNI {
   static native void init();

   static native int LZ4_compress_limitedOutput(byte[] var0, ByteBuffer var1, int var2, int var3, byte[] var4, ByteBuffer var5, int var6, int var7);

   static native int LZ4_compressHC(byte[] var0, ByteBuffer var1, int var2, int var3, byte[] var4, ByteBuffer var5, int var6, int var7, int var8);

   static native int LZ4_decompress_fast(byte[] var0, ByteBuffer var1, int var2, byte[] var3, ByteBuffer var4, int var5, int var6);

   static native int LZ4_decompress_safe(byte[] var0, ByteBuffer var1, int var2, int var3, byte[] var4, ByteBuffer var5, int var6, int var7);

   static native int LZ4_compressBound(int var0);

   static {
      Native.load();
      init();
   }
}
