/*     */ package net.jpountz.lz4;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Arrays;
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
/*     */ 
/*     */ 
/*     */ public final class LZ4Factory
/*     */ {
/*     */   private static LZ4Factory NATIVE_INSTANCE;
/*     */   private static LZ4Factory JAVA_UNSAFE_INSTANCE;
/*     */   private static LZ4Factory JAVA_SAFE_INSTANCE;
/*     */   private final String impl;
/*     */   private final LZ4Compressor fastCompressor;
/*     */   private final LZ4Compressor highCompressor;
/*     */   private final LZ4FastDecompressor fastDecompressor;
/*     */   private final LZ4SafeDecompressor safeDecompressor;
/*     */   
/*     */   private static LZ4Factory instance(String impl) {
/*     */     try {
/*  53 */       return new LZ4Factory(impl);
/*  54 */     } catch (Exception e) {
/*  55 */       throw new AssertionError(e);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized LZ4Factory nativeInstance() {
/*  92 */     if (NATIVE_INSTANCE == null) {
/*  93 */       NATIVE_INSTANCE = instance("JNI");
/*     */     }
/*  95 */     return NATIVE_INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized LZ4Factory safeInstance() {
/* 106 */     if (JAVA_SAFE_INSTANCE == null) {
/* 107 */       JAVA_SAFE_INSTANCE = instance("JavaSafe");
/*     */     }
/* 109 */     return JAVA_SAFE_INSTANCE;
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
/*     */   public static synchronized LZ4Factory unsafeInstance() {
/* 122 */     if (JAVA_UNSAFE_INSTANCE == null) {
/* 123 */       JAVA_UNSAFE_INSTANCE = instance("JavaUnsafe");
/*     */     }
/* 125 */     return JAVA_UNSAFE_INSTANCE;
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
/*     */   public static LZ4Factory fastestJavaInstance() {
/* 139 */     if (Utils.isUnalignedAccessAllowed()) {
/*     */       try {
/* 141 */         return unsafeInstance();
/* 142 */       } catch (Throwable t) {
/* 143 */         return safeInstance();
/*     */       } 
/*     */     }
/* 146 */     return safeInstance();
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
/*     */   public static LZ4Factory fastestInstance() {
/* 163 */     if (Native.isLoaded() || Native.class.getClassLoader() == ClassLoader.getSystemClassLoader()) {
/*     */       
/*     */       try {
/* 166 */         return nativeInstance();
/* 167 */       } catch (Throwable t) {
/* 168 */         return fastestJavaInstance();
/*     */       } 
/*     */     }
/* 171 */     return fastestJavaInstance();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static <T> T classInstance(String cls) throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
/* 177 */     ClassLoader loader = LZ4Factory.class.getClassLoader();
/* 178 */     loader = (loader == null) ? ClassLoader.getSystemClassLoader() : loader;
/* 179 */     Class<?> c = loader.loadClass(cls);
/* 180 */     Field f = c.getField("INSTANCE");
/* 181 */     return (T)f.get(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   private final LZ4Compressor[] highCompressors = new LZ4Compressor[18];
/*     */   
/*     */   private LZ4Factory(String impl) throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
/* 192 */     this.impl = impl;
/* 193 */     this.fastCompressor = classInstance("net.jpountz.lz4.LZ4" + impl + "Compressor");
/* 194 */     this.highCompressor = classInstance("net.jpountz.lz4.LZ4HC" + impl + "Compressor");
/* 195 */     this.fastDecompressor = classInstance("net.jpountz.lz4.LZ4" + impl + "FastDecompressor");
/* 196 */     this.safeDecompressor = classInstance("net.jpountz.lz4.LZ4" + impl + "SafeDecompressor");
/* 197 */     Constructor<? extends LZ4Compressor> highConstructor = (Constructor)this.highCompressor.getClass().getDeclaredConstructor(new Class[] { int.class });
/* 198 */     this.highCompressors[9] = this.highCompressor;
/* 199 */     for (int level = 1; level <= 17; level++) {
/* 200 */       if (level != 9) {
/* 201 */         this.highCompressors[level] = highConstructor.newInstance(new Object[] { Integer.valueOf(level) });
/*     */       }
/*     */     } 
/*     */     
/* 205 */     byte[] original = { 97, 98, 99, 100, 32, 32, 32, 32, 32, 32, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106 };
/* 206 */     for (LZ4Compressor compressor : Arrays.<LZ4Compressor>asList(new LZ4Compressor[] { this.fastCompressor, this.highCompressor })) {
/* 207 */       int maxCompressedLength = compressor.maxCompressedLength(original.length);
/* 208 */       byte[] compressed = new byte[maxCompressedLength];
/* 209 */       int compressedLength = compressor.compress(original, 0, original.length, compressed, 0, maxCompressedLength);
/* 210 */       byte[] restored = new byte[original.length];
/* 211 */       this.fastDecompressor.decompress(compressed, 0, restored, 0, original.length);
/* 212 */       if (!Arrays.equals(original, restored)) {
/* 213 */         throw new AssertionError();
/*     */       }
/* 215 */       Arrays.fill(restored, (byte)0);
/* 216 */       int decompressedLength = this.safeDecompressor.decompress(compressed, 0, compressedLength, restored, 0);
/* 217 */       if (decompressedLength != original.length || !Arrays.equals(original, restored)) {
/* 218 */         throw new AssertionError();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LZ4Compressor fastCompressor() {
/* 230 */     return this.fastCompressor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LZ4Compressor highCompressor() {
/* 241 */     return this.highCompressor;
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
/*     */   public LZ4Compressor highCompressor(int compressionLevel) {
/* 264 */     if (compressionLevel > 17) {
/* 265 */       compressionLevel = 17;
/* 266 */     } else if (compressionLevel < 1) {
/* 267 */       compressionLevel = 9;
/*     */     } 
/* 269 */     return this.highCompressors[compressionLevel];
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
/*     */   public LZ4FastDecompressor fastDecompressor() {
/* 281 */     return this.fastDecompressor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LZ4SafeDecompressor safeDecompressor() {
/* 290 */     return this.safeDecompressor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LZ4UnknownSizeDecompressor unknownSizeDecompressor() {
/* 300 */     return safeDecompressor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LZ4Decompressor decompressor() {
/* 310 */     return fastDecompressor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 319 */     System.out.println("Fastest instance is " + fastestInstance());
/* 320 */     System.out.println("Fastest Java instance is " + fastestJavaInstance());
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 325 */     return getClass().getSimpleName() + ":" + this.impl;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpountz\lz4\LZ4Factory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */