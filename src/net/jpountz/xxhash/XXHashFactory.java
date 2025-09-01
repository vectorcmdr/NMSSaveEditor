package net.jpountz.xxhash;

import java.lang.reflect.Field;
import java.util.Random;
import net.jpountz.util.Native;
import net.jpountz.util.Utils;

public final class XXHashFactory {
   private static XXHashFactory NATIVE_INSTANCE;
   private static XXHashFactory JAVA_UNSAFE_INSTANCE;
   private static XXHashFactory JAVA_SAFE_INSTANCE;
   private final String impl;
   private final XXHash32 hash32;
   private final XXHash64 hash64;
   private final StreamingXXHash32.Factory streamingHash32Factory;
   private final StreamingXXHash64.Factory streamingHash64Factory;

   private static XXHashFactory instance(String impl) {
      try {
         return new XXHashFactory(impl);
      } catch (Exception var2) {
         throw new AssertionError(var2);
      }
   }

   public static synchronized XXHashFactory nativeInstance() {
      if (NATIVE_INSTANCE == null) {
         NATIVE_INSTANCE = instance("JNI");
      }

      return NATIVE_INSTANCE;
   }

   public static synchronized XXHashFactory safeInstance() {
      if (JAVA_SAFE_INSTANCE == null) {
         JAVA_SAFE_INSTANCE = instance("JavaSafe");
      }

      return JAVA_SAFE_INSTANCE;
   }

   public static synchronized XXHashFactory unsafeInstance() {
      if (JAVA_UNSAFE_INSTANCE == null) {
         JAVA_UNSAFE_INSTANCE = instance("JavaUnsafe");
      }

      return JAVA_UNSAFE_INSTANCE;
   }

   public static XXHashFactory fastestJavaInstance() {
      if (Utils.isUnalignedAccessAllowed()) {
         try {
            return unsafeInstance();
         } catch (Throwable var1) {
            return safeInstance();
         }
      } else {
         return safeInstance();
      }
   }

   public static XXHashFactory fastestInstance() {
      if (!Native.isLoaded() && Native.class.getClassLoader() != ClassLoader.getSystemClassLoader()) {
         return fastestJavaInstance();
      } else {
         try {
            return nativeInstance();
         } catch (Throwable var1) {
            return fastestJavaInstance();
         }
      }
   }

   private static <T> T classInstance(String cls) throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
      ClassLoader loader = XXHashFactory.class.getClassLoader();
      loader = loader == null ? ClassLoader.getSystemClassLoader() : loader;
      Class<?> c = loader.loadClass(cls);
      Field f = c.getField("INSTANCE");
      return f.get((Object)null);
   }

   private XXHashFactory(String impl) throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
      this.impl = impl;
      this.hash32 = (XXHash32)classInstance("net.jpountz.xxhash.XXHash32" + impl);
      this.streamingHash32Factory = (StreamingXXHash32.Factory)classInstance("net.jpountz.xxhash.StreamingXXHash32" + impl + "$Factory");
      this.hash64 = (XXHash64)classInstance("net.jpountz.xxhash.XXHash64" + impl);
      this.streamingHash64Factory = (StreamingXXHash64.Factory)classInstance("net.jpountz.xxhash.StreamingXXHash64" + impl + "$Factory");
      byte[] bytes = new byte[100];
      Random random = new Random();
      random.nextBytes(bytes);
      int seed = random.nextInt();
      int h1 = this.hash32.hash((byte[])bytes, 0, bytes.length, seed);
      StreamingXXHash32 streamingHash32 = this.newStreamingHash32(seed);
      streamingHash32.update(bytes, 0, bytes.length);
      int h2 = streamingHash32.getValue();
      long h3 = this.hash64.hash((byte[])bytes, 0, bytes.length, (long)seed);
      StreamingXXHash64 streamingHash64 = this.newStreamingHash64((long)seed);
      streamingHash64.update(bytes, 0, bytes.length);
      long h4 = streamingHash64.getValue();
      if (h1 != h2) {
         throw new AssertionError();
      } else if (h3 != h4) {
         throw new AssertionError();
      }
   }

   public XXHash32 hash32() {
      return this.hash32;
   }

   public XXHash64 hash64() {
      return this.hash64;
   }

   public StreamingXXHash32 newStreamingHash32(int seed) {
      return this.streamingHash32Factory.newStreamingHash(seed);
   }

   public StreamingXXHash64 newStreamingHash64(long seed) {
      return this.streamingHash64Factory.newStreamingHash(seed);
   }

   public static void main(String[] args) {
      System.out.println("Fastest instance is " + fastestInstance());
      System.out.println("Fastest Java instance is " + fastestJavaInstance());
   }

   public String toString() {
      return this.getClass().getSimpleName() + ":" + this.impl;
   }
}
