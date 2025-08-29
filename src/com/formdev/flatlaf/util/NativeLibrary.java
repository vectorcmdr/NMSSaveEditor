/*     */ package com.formdev.flatlaf.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.nio.file.CopyOption;
/*     */ import java.nio.file.FileAlreadyExistsException;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.nio.file.StandardCopyOption;
/*     */ import java.nio.file.attribute.FileAttribute;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NativeLibrary
/*     */ {
/*     */   private static final String DELETE_SUFFIX = ".delete";
/*     */   private static boolean deletedTemporary;
/*     */   private final boolean loaded;
/*     */   
/*     */   public NativeLibrary(String libraryName, ClassLoader classLoader, boolean supported) {
/*  58 */     this
/*     */       
/*  60 */       .loaded = supported ? loadLibraryFromJar(libraryName, classLoader) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NativeLibrary(File libraryFile, boolean supported) {
/*  71 */     this
/*     */       
/*  73 */       .loaded = supported ? loadLibraryFromFile(libraryFile) : false;
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
/*     */   public NativeLibrary(String libraryName, boolean supported) {
/*  87 */     this
/*     */       
/*  89 */       .loaded = supported ? loadLibraryFromSystem(libraryName) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLoaded() {
/*  99 */     return this.loaded;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean loadLibraryFromJar(String libraryName, ClassLoader classLoader) {
/* 104 */     libraryName = decorateLibraryName(libraryName);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     URL libraryUrl = (classLoader != null) ? classLoader.getResource(libraryName) : NativeLibrary.class.getResource("/" + libraryName);
/* 110 */     if (libraryUrl == null) {
/* 111 */       LoggingFacade.INSTANCE.logSevere("Library '" + libraryName + "' not found", null);
/* 112 */       return false;
/*     */     } 
/*     */     
/* 115 */     File tempFile = null;
/*     */     
/*     */     try {
/* 118 */       if ("file".equals(libraryUrl.getProtocol())) {
/* 119 */         File libraryFile = new File(libraryUrl.getPath());
/* 120 */         if (libraryFile.isFile()) {
/*     */           
/* 122 */           System.load(libraryFile.getCanonicalPath());
/* 123 */           return true;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 128 */       Path tempPath = createTempFile(libraryName);
/* 129 */       tempFile = tempPath.toFile();
/*     */ 
/*     */       
/* 132 */       InputStream in = libraryUrl.openStream(); 
/* 133 */       try { Files.copy(in, tempPath, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/* 134 */         if (in != null) in.close();  } catch (Throwable throwable) { if (in != null)
/*     */           try { in.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*     */             throw throwable; }
/* 137 */        System.load(tempFile.getCanonicalPath());
/*     */ 
/*     */       
/* 140 */       deleteOrMarkForDeletion(tempFile);
/*     */       
/* 142 */       return true;
/* 143 */     } catch (Throwable ex) {
/* 144 */       LoggingFacade.INSTANCE.logSevere(ex.getMessage(), ex);
/*     */       
/* 146 */       if (tempFile != null)
/* 147 */         deleteOrMarkForDeletion(tempFile); 
/* 148 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean loadLibraryFromFile(File libraryFile) {
/*     */     try {
/* 154 */       System.load(libraryFile.getAbsolutePath());
/* 155 */       return true;
/* 156 */     } catch (Throwable ex) {
/* 157 */       LoggingFacade.INSTANCE.logSevere(ex.getMessage(), ex);
/* 158 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean loadLibraryFromSystem(String libraryName) {
/*     */     try {
/* 164 */       System.loadLibrary(libraryName);
/* 165 */       return true;
/* 166 */     } catch (Throwable ex) {
/* 167 */       String message = ex.getMessage();
/*     */ 
/*     */ 
/*     */       
/* 171 */       if (ex instanceof UnsatisfiedLinkError && message != null && message.contains("java.library.path")) {
/* 172 */         return false;
/*     */       }
/* 174 */       LoggingFacade.INSTANCE.logSevere(message, ex);
/* 175 */       return false;
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
/*     */   private static String decorateLibraryName(String libraryName) {
/* 188 */     int sep = libraryName.lastIndexOf('/');
/* 189 */     return (sep >= 0) ? (
/* 190 */       libraryName.substring(0, sep + 1) + System.mapLibraryName(libraryName.substring(sep + 1))) : 
/* 191 */       System.mapLibraryName(libraryName);
/*     */   }
/*     */   
/*     */   private static Path createTempFile(String libraryName) throws IOException {
/* 195 */     int sep = libraryName.lastIndexOf('/');
/* 196 */     String name = (sep >= 0) ? libraryName.substring(sep + 1) : libraryName;
/*     */     
/* 198 */     int dot = name.lastIndexOf('.');
/* 199 */     String prefix = ((dot >= 0) ? name.substring(0, dot) : name) + '-';
/* 200 */     String suffix = (dot >= 0) ? name.substring(dot) : "";
/*     */     
/* 202 */     Path tempDir = getTempDir();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 210 */     long nanoTime = System.nanoTime();
/* 211 */     for (int i = 0;; i++) {
/* 212 */       String s = prefix + Long.toUnsignedString(nanoTime) + i + suffix;
/*     */       try {
/* 214 */         return Files.createFile(tempDir.resolve(s), (FileAttribute<?>[])new FileAttribute[0]);
/* 215 */       } catch (FileAlreadyExistsException fileAlreadyExistsException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Path getTempDir() throws IOException {
/* 223 */     String tmpdir = System.getProperty("java.io.tmpdir");
/*     */     
/* 225 */     if (SystemInfo.isWindows)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 231 */       tmpdir = tmpdir + "\\flatlaf.temp";
/*     */     }
/*     */ 
/*     */     
/* 235 */     Path tempDir = Paths.get(tmpdir, new String[0]);
/* 236 */     Files.createDirectories(tempDir, (FileAttribute<?>[])new FileAttribute[0]);
/*     */ 
/*     */     
/* 239 */     if (SystemInfo.isWindows) {
/* 240 */       deleteTemporaryFiles(tempDir);
/*     */     }
/* 242 */     return tempDir;
/*     */   }
/*     */   
/*     */   private static void deleteTemporaryFiles(Path tempDir) {
/* 246 */     if (deletedTemporary)
/*     */       return; 
/* 248 */     deletedTemporary = true;
/*     */     
/* 250 */     File[] markerFiles = tempDir.toFile().listFiles((dir, name) -> name.endsWith(".delete"));
/* 251 */     if (markerFiles == null) {
/*     */       return;
/*     */     }
/* 254 */     for (File markerFile : markerFiles) {
/* 255 */       File toDeleteFile = new File(markerFile.getParent(), StringUtils.removeTrailing(markerFile.getName(), ".delete"));
/* 256 */       if (!toDeleteFile.exists() || toDeleteFile.delete()) {
/* 257 */         markerFile.delete();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void deleteOrMarkForDeletion(File file) {
/* 263 */     if (file.delete()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 269 */       File markFile = new File(file.getParent(), file.getName() + ".delete");
/* 270 */       markFile.createNewFile();
/* 271 */     } catch (IOException iOException) {}
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\util\NativeLibrary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */