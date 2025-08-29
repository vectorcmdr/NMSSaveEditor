/*     */ package com.formdev.flatlaf;
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
/*     */ public interface FlatSystemProperties
/*     */ {
/*     */   public static final String UI_SCALE = "flatlaf.uiScale";
/*     */   public static final String UI_SCALE_ENABLED = "flatlaf.uiScale.enabled";
/*     */   public static final String UI_SCALE_ALLOW_SCALE_DOWN = "flatlaf.uiScale.allowScaleDown";
/*     */   public static final String USE_UBUNTU_FONT = "flatlaf.useUbuntuFont";
/*     */   public static final String USE_WINDOW_DECORATIONS = "flatlaf.useWindowDecorations";
/*     */   public static final String USE_JETBRAINS_CUSTOM_DECORATIONS = "flatlaf.useJetBrainsCustomDecorations";
/*     */   public static final String MENUBAR_EMBEDDED = "flatlaf.menuBarEmbedded";
/*     */   public static final String ANIMATION = "flatlaf.animation";
/*     */   public static final String USE_TEXT_Y_CORRECTION = "flatlaf.useTextYCorrection";
/*     */   public static final String UPDATE_UI_ON_SYSTEM_FONT_CHANGE = "flatlaf.updateUIOnSystemFontChange";
/*     */   public static final String NATIVE_LIBRARY_PATH = "flatlaf.nativeLibraryPath";
/*     */   
/*     */   static boolean getBoolean(String key, boolean defaultValue) {
/* 182 */     String value = System.getProperty(key);
/* 183 */     return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Boolean getBooleanStrict(String key, Boolean defaultValue) {
/* 192 */     String value = System.getProperty(key);
/* 193 */     if ("true".equalsIgnoreCase(value))
/* 194 */       return Boolean.TRUE; 
/* 195 */     if ("false".equalsIgnoreCase(value))
/* 196 */       return Boolean.FALSE; 
/* 197 */     return defaultValue;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\FlatSystemProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */