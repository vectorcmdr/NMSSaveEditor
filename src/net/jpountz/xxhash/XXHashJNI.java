package net.jpountz.xxhash;

import java.nio.ByteBuffer;
import net.jpountz.util.Native;

enum XXHashJNI {
   private static native void init();

   static native int XXH32(byte[] var0, int var1, int var2, int var3);

   static native int XXH32BB(ByteBuffer var0, int var1, int var2, int var3);

   static native long XXH32_init(int var0);

   static native void XXH32_update(long var0, byte[] var2, int var3, int var4);

   static native int XXH32_digest(long var0);

   static native void XXH32_free(long var0);

   static native long XXH64(byte[] var0, int var1, int var2, long var3);

   static native long XXH64BB(ByteBuffer var0, int var1, int var2, long var3);

   static native long XXH64_init(long var0);

   static native void XXH64_update(long var0, byte[] var2, int var3, int var4);

   static native long XXH64_digest(long var0);

   static native void XXH64_free(long var0);

   static {
      Native.load();
      init();
   }
}
