/*     */ package net.jpountz.xxhash;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Random;
/*     */ import net.jpountz.util.Native;
/*     */ import net.jpountz.util.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class XXHashFactory
/*     */ {
/*     */   private static XXHashFactory NATIVE_INSTANCE;
/*     */   private static XXHashFactory JAVA_UNSAFE_INSTANCE;
/*     */   private static XXHashFactory JAVA_SAFE_INSTANCE;
/*     */   private final String impl;
/*     */   private final XXHash32 hash32;
/*     */   private final XXHash64 hash64;
/*     */   private final StreamingXXHash32.Factory streamingHash32Factory;
/*     */   private final StreamingXXHash64.Factory streamingHash64Factory;
/*     */   
/*     */   private static XXHashFactory instance(String impl) {
/*     */     try {
/*  49 */       return new XXHashFactory(impl);
/*  50 */     } catch (Exception e) {
/*  51 */       throw new AssertionError(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized XXHashFactory nativeInstance() {
/*  81 */     if (NATIVE_INSTANCE == null) {
/*  82 */       NATIVE_INSTANCE = instance("JNI");
/*     */     }
/*  84 */     return NATIVE_INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized XXHashFactory safeInstance() {
/*  95 */     if (JAVA_SAFE_INSTANCE == null) {
/*  96 */       JAVA_SAFE_INSTANCE = instance("JavaSafe");
/*     */     }
/*  98 */     return JAVA_SAFE_INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized XXHashFactory unsafeInstance() {
/* 109 */     if (JAVA_UNSAFE_INSTANCE == null) {
/* 110 */       JAVA_UNSAFE_INSTANCE = instance("JavaUnsafe");
/*     */     }
/* 112 */     return JAVA_UNSAFE_INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static XXHashFactory fastestJavaInstance() {
/* 126 */     if (Utils.isUnalignedAccessAllowed()) {
/*     */       try {
/* 128 */         return unsafeInstance();
/* 129 */       } catch (Throwable t) {
/* 130 */         return safeInstance();
/*     */       } 
/*     */     }
/* 133 */     return safeInstance();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static XXHashFactory fastestInstance() {
/* 150 */     if (Native.isLoaded() || Native.class.getClassLoader() == ClassLoader.getSystemClassLoader()) {
/*     */       
/*     */       try {
/* 153 */         return nativeInstance();
/* 154 */       } catch (Throwable t) {
/* 155 */         return fastestJavaInstance();
/*     */       } 
/*     */     }
/* 158 */     return fastestJavaInstance();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static <T> T classInstance(String cls) throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
/* 164 */     ClassLoader loader = XXHashFactory.class.getClassLoader();
/* 165 */     loader = (loader == null) ? ClassLoader.getSystemClassLoader() : loader;
/* 166 */     Class<?> c = loader.loadClass(cls);
/* 167 */     Field f = c.getField("INSTANCE");
/* 168 */     return (T)f.get(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private XXHashFactory(String impl) throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
/* 178 */     this.impl = impl;
/* 179 */     this.hash32 = classInstance("net.jpountz.xxhash.XXHash32" + impl);
/* 180 */     this.streamingHash32Factory = classInstance("net.jpountz.xxhash.StreamingXXHash32" + impl + "$Factory");
/* 181 */     this.hash64 = classInstance("net.jpountz.xxhash.XXHash64" + impl);
/* 182 */     this.streamingHash64Factory = classInstance("net.jpountz.xxhash.StreamingXXHash64" + impl + "$Factory");
/*     */ 
/*     */     
/* 185 */     byte[] bytes = new byte[100];
/* 186 */     Random random = new Random();
/* 187 */     random.nextBytes(bytes);
/* 188 */     int seed = random.nextInt();
/*     */     
/* 190 */     int h1 = this.hash32.hash(bytes, 0, bytes.length, seed);
/* 191 */     StreamingXXHash32 streamingHash32 = newStreamingHash32(seed);
/* 192 */     streamingHash32.update(bytes, 0, bytes.length);
/* 193 */     int h2 = streamingHash32.getValue();
/* 194 */     long h3 = this.hash64.hash(bytes, 0, bytes.length, seed);
/* 195 */     StreamingXXHash64 streamingHash64 = newStreamingHash64(seed);
/* 196 */     streamingHash64.update(bytes, 0, bytes.length);
/* 197 */     long h4 = streamingHash64.getValue();
/* 198 */     if (h1 != h2) {
/* 199 */       throw new AssertionError();
/*     */     }
/* 201 */     if (h3 != h4) {
/* 202 */       throw new AssertionError();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XXHash32 hash32() {
/* 212 */     return this.hash32;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XXHash64 hash64() {
/* 221 */     return this.hash64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StreamingXXHash32 newStreamingHash32(int seed) {
/* 231 */     return this.streamingHash32Factory.newStreamingHash(seed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StreamingXXHash64 newStreamingHash64(long seed) {
/* 241 */     return this.streamingHash64Factory.newStreamingHash(seed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 250 */     System.out.println("Fastest instance is " + fastestInstance());
/* 251 */     System.out.println("Fastest Java instance is " + fastestJavaInstance());
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 256 */     return getClass().getSimpleName() + ":" + this.impl;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\xxhash\XXHashFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */