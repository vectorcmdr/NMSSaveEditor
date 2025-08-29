/*     */ package net.jpountz.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FilenameFilter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
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
/*     */ public enum Native
/*     */ {
/*     */   private static boolean loaded;
/*     */   
/*     */   private enum OS
/*     */   {
/*  31 */     WINDOWS("win32", "so"), LINUX("linux", "so"), MAC("darwin", "dylib"), SOLARIS("solaris", "so"); public final String libExtension;
/*     */     public final String name;
/*     */     
/*     */     OS(String name, String libExtension) {
/*  35 */       this.name = name;
/*  36 */       this.libExtension = libExtension;
/*     */     }
/*     */   }
/*     */   
/*     */   private static String arch() {
/*  41 */     return System.getProperty("os.arch");
/*     */   }
/*     */   
/*     */   private static OS os() {
/*  45 */     String osName = System.getProperty("os.name");
/*  46 */     if (osName.contains("Linux"))
/*  47 */       return OS.LINUX; 
/*  48 */     if (osName.contains("Mac"))
/*  49 */       return OS.MAC; 
/*  50 */     if (osName.contains("Windows"))
/*  51 */       return OS.WINDOWS; 
/*  52 */     if (osName.contains("Solaris") || osName.contains("SunOS")) {
/*  53 */       return OS.SOLARIS;
/*     */     }
/*  55 */     throw new UnsupportedOperationException("Unsupported operating system: " + osName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String resourceName() {
/*  61 */     OS os = os();
/*  62 */     String packagePrefix = Native.class.getPackage().getName().replace('.', '/');
/*     */     
/*  64 */     return "/" + packagePrefix + "/" + os.name + "/" + arch() + "/liblz4-java." + os.libExtension;
/*     */   }
/*     */   static {
/*  67 */     loaded = false;
/*     */   }
/*     */   public static synchronized boolean isLoaded() {
/*  70 */     return loaded;
/*     */   }
/*     */   
/*     */   private static void cleanupOldTempLibs() {
/*  74 */     String tempFolder = (new File(System.getProperty("java.io.tmpdir"))).getAbsolutePath();
/*  75 */     File dir = new File(tempFolder);
/*     */     
/*  77 */     File[] tempLibFiles = dir.listFiles(new FilenameFilter() {
/*  78 */           private final String searchPattern = "liblz4-java-";
/*     */           public boolean accept(File dir, String name) {
/*  80 */             return (name.startsWith("liblz4-java-") && !name.endsWith(".lck"));
/*     */           }
/*     */         });
/*  83 */     if (tempLibFiles != null) {
/*  84 */       for (File tempLibFile : tempLibFiles) {
/*  85 */         File lckFile = new File(tempLibFile.getAbsolutePath() + ".lck");
/*  86 */         if (!lckFile.exists()) {
/*     */           try {
/*  88 */             tempLibFile.delete();
/*     */           }
/*  90 */           catch (SecurityException e) {
/*  91 */             System.err.println("Failed to delete old temp lib" + e.getMessage());
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static synchronized void load() {
/*  99 */     if (loaded) {
/*     */       return;
/*     */     }
/*     */     
/* 103 */     cleanupOldTempLibs();
/*     */ 
/*     */     
/*     */     try {
/* 107 */       System.loadLibrary("lz4-java");
/* 108 */       loaded = true;
/*     */       return;
/* 110 */     } catch (UnsatisfiedLinkError unsatisfiedLinkError) {
/*     */ 
/*     */ 
/*     */       
/* 114 */       String resourceName = resourceName();
/* 115 */       InputStream is = Native.class.getResourceAsStream(resourceName);
/* 116 */       if (is == null) {
/* 117 */         throw new UnsupportedOperationException("Unsupported OS/arch, cannot find " + resourceName + ". Please try building from source.");
/*     */       }
/* 119 */       File tempLib = null;
/* 120 */       File tempLibLock = null;
/*     */ 
/*     */       
/*     */       try {
/* 124 */         tempLibLock = File.createTempFile("liblz4-java-", "." + (os()).libExtension + ".lck");
/* 125 */         tempLib = new File(tempLibLock.getAbsolutePath().replaceFirst(".lck$", ""));
/*     */         
/* 127 */         try (FileOutputStream out = new FileOutputStream(tempLib)) {
/* 128 */           byte[] buf = new byte[4096];
/*     */           while (true) {
/* 130 */             int read = is.read(buf);
/* 131 */             if (read == -1) {
/*     */               break;
/*     */             }
/* 134 */             out.write(buf, 0, read);
/*     */           } 
/*     */         } 
/* 137 */         System.load(tempLib.getAbsolutePath());
/* 138 */         loaded = true;
/* 139 */       } catch (IOException e) {
/* 140 */         throw new ExceptionInInitializerError("Cannot unpack liblz4-java: " + e);
/*     */       } finally {
/* 142 */         if (!loaded) {
/* 143 */           if (tempLib != null && tempLib.exists() && 
/* 144 */             !tempLib.delete()) {
/* 145 */             throw new ExceptionInInitializerError("Cannot unpack liblz4-java / cannot delete a temporary native library " + tempLib);
/*     */           }
/*     */           
/* 148 */           if (tempLibLock != null && tempLibLock.exists() && 
/* 149 */             !tempLibLock.delete()) {
/* 150 */             throw new ExceptionInInitializerError("Cannot unpack liblz4-java / cannot delete a temporary lock file " + tempLibLock);
/*     */           }
/*     */         } else {
/*     */           
/* 154 */           String keepEnv = System.getenv("LZ4JAVA_KEEP_TEMP_JNI_LIB");
/* 155 */           String keepProp = System.getProperty("lz4java.jnilib.temp.keep");
/* 156 */           if ((keepEnv == null || !keepEnv.equals("true")) && (keepProp == null || !keepProp.equals("true")))
/*     */           {
/* 158 */             tempLib.deleteOnExit(); } 
/* 159 */           tempLibLock.deleteOnExit();
/*     */         } 
/*     */       } 
/*     */       return;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\net\jpount\\util\Native.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */