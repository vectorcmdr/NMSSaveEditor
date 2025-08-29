/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.NativeLibrary;
/*     */ import com.formdev.flatlaf.util.SystemInfo;
/*     */ import java.io.File;
/*     */ import java.net.URL;
/*     */ import java.security.CodeSource;
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
/*     */ class FlatNativeLibrary
/*     */ {
/*     */   private static NativeLibrary nativeLibrary;
/*     */   
/*     */   static synchronized boolean isLoaded() {
/*  43 */     initialize();
/*  44 */     return (nativeLibrary != null) ? nativeLibrary.isLoaded() : false;
/*     */   }
/*     */   private static void initialize() {
/*     */     String classifier, ext;
/*  48 */     if (nativeLibrary != null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  53 */     if (SystemInfo.isWindows_10_orLater && (SystemInfo.isX86 || SystemInfo.isX86_64)) {
/*     */ 
/*     */       
/*  56 */       classifier = SystemInfo.isX86_64 ? "windows-x86_64" : "windows-x86";
/*  57 */       ext = "dll";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  65 */       loadJAWT();
/*  66 */     } else if (SystemInfo.isLinux && SystemInfo.isX86_64) {
/*     */ 
/*     */       
/*  69 */       classifier = "linux-x86_64";
/*  70 */       ext = "so";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  77 */       loadJAWT();
/*     */     } else {
/*     */       return;
/*     */     } 
/*     */     
/*  82 */     nativeLibrary = createNativeLibrary(classifier, ext);
/*     */   }
/*     */   
/*     */   private static NativeLibrary createNativeLibrary(String classifier, String ext) {
/*  86 */     String libraryName = "flatlaf-" + classifier;
/*     */ 
/*     */     
/*  89 */     String libraryPath = System.getProperty("flatlaf.nativeLibraryPath");
/*  90 */     if (libraryPath != null) {
/*  91 */       if ("system".equals(libraryPath)) {
/*  92 */         NativeLibrary library = new NativeLibrary(libraryName, true);
/*  93 */         if (library.isLoaded()) {
/*  94 */           return library;
/*     */         }
/*  96 */         LoggingFacade.INSTANCE.logSevere("Did not find library " + libraryName + " in java.library.path, using extracted library instead", null);
/*     */       } else {
/*  98 */         File file = new File(libraryPath, System.mapLibraryName(libraryName));
/*  99 */         if (file.exists()) {
/* 100 */           return new NativeLibrary(file, true);
/*     */         }
/* 102 */         LoggingFacade.INSTANCE.logSevere("Did not find external library " + file + ", using extracted library instead", null);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 108 */     File libraryFile = findLibraryBesideJar(classifier, ext);
/* 109 */     if (libraryFile != null) {
/* 110 */       return new NativeLibrary(libraryFile, true);
/*     */     }
/*     */     
/* 113 */     return new NativeLibrary("com/formdev/flatlaf/natives/" + libraryName, null, true);
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
/*     */   private static File findLibraryBesideJar(String classifier, String ext) {
/*     */     try {
/* 132 */       CodeSource codeSource = FlatNativeLibrary.class.getProtectionDomain().getCodeSource();
/* 133 */       URL jarUrl = (codeSource != null) ? codeSource.getLocation() : null;
/* 134 */       if (jarUrl == null) {
/* 135 */         return null;
/*     */       }
/*     */       
/* 138 */       if (!"file".equals(jarUrl.getProtocol())) {
/* 139 */         return null;
/*     */       }
/* 141 */       File jarFile = new File(jarUrl.toURI());
/*     */ 
/*     */       
/* 144 */       if (!jarFile.isFile()) {
/* 145 */         return null;
/*     */       }
/*     */       
/* 148 */       String jarName = jarFile.getName();
/* 149 */       String jarBasename = jarName.substring(0, jarName.lastIndexOf('.'));
/* 150 */       File parent = jarFile.getParentFile();
/*     */       
/* 152 */       String libraryName = jarBasename + (jarBasename.contains("flatlaf") ? "" : "-flatlaf") + '-' + classifier + '.' + ext;
/*     */ 
/*     */ 
/*     */       
/* 156 */       File libraryFile = new File(parent, libraryName);
/* 157 */       if (libraryFile.isFile()) {
/* 158 */         return libraryFile;
/*     */       }
/*     */ 
/*     */       
/* 162 */       if (parent.getName().equalsIgnoreCase("lib")) {
/* 163 */         libraryFile = new File(parent.getParentFile(), "bin/" + libraryName);
/* 164 */         if (libraryFile.isFile())
/* 165 */           return libraryFile; 
/*     */       } 
/* 167 */     } catch (Exception ex) {
/* 168 */       LoggingFacade.INSTANCE.logSevere(ex.getMessage(), ex);
/*     */     } 
/*     */     
/* 171 */     return null;
/*     */   }
/*     */   
/*     */   private static void loadJAWT() {
/*     */     try {
/* 176 */       System.loadLibrary("jawt");
/* 177 */     } catch (UnsatisfiedLinkError ex) {
/*     */       
/* 179 */       String message = ex.getMessage();
/* 180 */       if (message == null || !message.contains("already loaded in another classloader"))
/* 181 */         LoggingFacade.INSTANCE.logSevere(message, ex); 
/* 182 */     } catch (Exception ex) {
/* 183 */       LoggingFacade.INSTANCE.logSevere(ex.getMessage(), ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatNativeLibrary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */