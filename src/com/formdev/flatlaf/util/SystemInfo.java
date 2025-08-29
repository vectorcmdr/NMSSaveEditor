/*     */ package com.formdev.flatlaf.util;
/*     */ 
/*     */ import com.formdev.flatlaf.ui.FlatNativeWindowsLibrary;
/*     */ import java.util.Locale;
/*     */ import java.util.StringTokenizer;
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
/*     */ public class SystemInfo
/*     */ {
/*     */   public static final boolean isWindows;
/*     */   public static final boolean isMacOS;
/*     */   public static final boolean isLinux;
/*     */   
/*     */   static {
/*  74 */     String osName = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
/*  75 */     isWindows = osName.startsWith("windows");
/*  76 */     isMacOS = osName.startsWith("mac");
/*  77 */     isLinux = osName.startsWith("linux");
/*     */   }
/*     */   
/*  80 */   public static final long osVersion = scanVersion(System.getProperty("os.version"));
/*  81 */   public static final boolean isWindows_10_orLater = (isWindows && osVersion >= toVersion(10, 0, 0, 0)); public static final boolean isWindows_11_orLater;
/*  82 */   public static final boolean isMacOS_10_11_ElCapitan_orLater = (isMacOS && osVersion >= toVersion(10, 11, 0, 0));
/*  83 */   public static final boolean isMacOS_10_14_Mojave_orLater = (isMacOS && osVersion >= toVersion(10, 14, 0, 0));
/*  84 */   public static final boolean isMacOS_10_15_Catalina_orLater = (isMacOS && osVersion >= toVersion(10, 15, 0, 0)); public static final boolean isX86;
/*     */   
/*     */   static {
/*  87 */     String osArch = System.getProperty("os.arch");
/*  88 */     isX86 = osArch.equals("x86");
/*  89 */     isX86_64 = (osArch.equals("amd64") || osArch.equals("x86_64"));
/*  90 */     isAARCH64 = osArch.equals("aarch64");
/*     */   }
/*     */   public static final boolean isX86_64; public static final boolean isAARCH64;
/*  93 */   public static final long javaVersion = scanVersion(System.getProperty("java.version"));
/*  94 */   public static final boolean isJava_9_orLater = (javaVersion >= toVersion(9, 0, 0, 0));
/*  95 */   public static final boolean isJava_11_orLater = (javaVersion >= toVersion(11, 0, 0, 0));
/*  96 */   public static final boolean isJava_12_orLater = (javaVersion >= toVersion(12, 0, 0, 0));
/*  97 */   public static final boolean isJava_15_orLater = (javaVersion >= toVersion(15, 0, 0, 0));
/*  98 */   public static final boolean isJava_17_orLater = (javaVersion >= toVersion(17, 0, 0, 0));
/*  99 */   public static final boolean isJava_18_orLater = (javaVersion >= toVersion(18, 0, 0, 0));
/*     */ 
/*     */   
/* 102 */   public static final boolean isJetBrainsJVM = System.getProperty("java.vm.vendor", "Unknown")
/* 103 */     .toLowerCase(Locale.ENGLISH).contains("jetbrains");
/* 104 */   public static final boolean isJetBrainsJVM_11_orLater = (isJetBrainsJVM && isJava_11_orLater);
/*     */ 
/*     */   
/* 107 */   public static final boolean isKDE = (isLinux && System.getenv("KDE_FULL_SESSION") != null);
/*     */ 
/*     */   
/* 110 */   public static final boolean isProjector = Boolean.getBoolean("org.jetbrains.projector.server.enable");
/* 111 */   public static final boolean isWebswing = (System.getProperty("webswing.rootDir") != null);
/* 112 */   public static final boolean isWinPE = (isWindows && "X:\\Windows\\System32".equalsIgnoreCase(System.getProperty("user.dir")));
/*     */ 
/*     */ 
/*     */   
/* 116 */   public static final boolean isMacFullWindowContentSupported = (isMacOS && (javaVersion >= 
/* 117 */     toVersion(11, 0, 8, 0) || (javaVersion >= 
/* 118 */     toVersion(1, 8, 0, 292) && !isJava_9_orLater)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 127 */     boolean isWin_11_orLater = false;
/*     */ 
/*     */     
/*     */     try {
/* 131 */       isWin_11_orLater = (isWindows_10_orLater && (scanVersion(StringUtils.removeLeading(osName, "windows ")) >= toVersion(11, 0, 0, 0) || (FlatNativeWindowsLibrary.isLoaded() && FlatNativeWindowsLibrary.getOSBuildNumber() >= 22000L)));
/* 132 */     } catch (Throwable ex) {
/*     */       
/* 134 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/* 136 */     isWindows_11_orLater = isWin_11_orLater;
/*     */   }
/*     */   
/*     */   public static long scanVersion(String version) {
/* 140 */     int major = 1;
/* 141 */     int minor = 0;
/* 142 */     int micro = 0;
/* 143 */     int patch = 0;
/*     */     try {
/* 145 */       StringTokenizer st = new StringTokenizer(version, "._-+");
/* 146 */       major = Integer.parseInt(st.nextToken());
/* 147 */       minor = Integer.parseInt(st.nextToken());
/* 148 */       micro = Integer.parseInt(st.nextToken());
/* 149 */       patch = Integer.parseInt(st.nextToken());
/* 150 */     } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */     
/* 154 */     return toVersion(major, minor, micro, patch);
/*     */   }
/*     */   
/*     */   public static long toVersion(int major, int minor, int micro, int patch) {
/* 158 */     return (major << 48L) + (minor << 32L) + (micro << 16L) + patch;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\util\SystemInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */