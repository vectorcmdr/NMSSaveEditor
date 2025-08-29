/*     */ package com.formdev.flatlaf.util;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.FontFormatException;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.swing.text.StyleContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FontUtils
/*     */ {
/*     */   private static Map<String, Runnable> loadersMap;
/*     */   
/*     */   public static Font getCompositeFont(String family, int style, int size) {
/*  50 */     loadFontFamily(family);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  55 */     Font font = StyleContext.getDefaultStyleContext().getFont(family, style, size);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     if (font instanceof javax.swing.plaf.UIResource) {
/*  61 */       font = font.deriveFont(font.getStyle());
/*     */     }
/*  63 */     return font;
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
/*     */   public static void registerFontFamilyLoader(String family, Runnable loader) {
/*  76 */     if (loadersMap == null)
/*  77 */       loadersMap = new HashMap<>(); 
/*  78 */     loadersMap.put(family, loader);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void loadFontFamily(String family) {
/*  86 */     if (!hasLoaders()) {
/*     */       return;
/*     */     }
/*  89 */     Runnable loader = loadersMap.remove(family);
/*  90 */     if (loader != null) {
/*  91 */       loader.run();
/*     */     }
/*  93 */     if (loadersMap.isEmpty()) {
/*  94 */       loadersMap = null;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean installFont(URL url) {
/*     */     
/* 102 */     try { InputStream in = url.openStream(); 
/* 103 */       try { Font font = Font.createFont(0, in);
/* 104 */         boolean bool = GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
/* 105 */         if (in != null) in.close();  return bool; } catch (Throwable throwable) { if (in != null) try { in.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (FontFormatException|java.io.IOException ex)
/* 106 */     { LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to install font " + url, ex);
/* 107 */       return false; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] getAvailableFontFamilyNames() {
/* 118 */     String[] availableFontFamilyNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
/* 119 */     if (!hasLoaders()) {
/* 120 */       return availableFontFamilyNames;
/*     */     }
/*     */     
/* 123 */     ArrayList<String> result = new ArrayList<>(availableFontFamilyNames.length + loadersMap.size());
/* 124 */     for (String name : availableFontFamilyNames)
/* 125 */       result.add(name); 
/* 126 */     for (String name : loadersMap.keySet()) {
/* 127 */       if (!result.contains(name)) {
/* 128 */         result.add(name);
/*     */       }
/*     */     } 
/* 131 */     return result.<String>toArray(new String[result.size()]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Font[] getAllFonts() {
/* 140 */     if (hasLoaders()) {
/*     */       
/* 142 */       String[] families = (String[])loadersMap.keySet().toArray((Object[])new String[loadersMap.size()]);
/* 143 */       for (String family : families) {
/* 144 */         loadFontFamily(family);
/*     */       }
/*     */     } 
/* 147 */     return GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
/*     */   }
/*     */   
/*     */   private static boolean hasLoaders() {
/* 151 */     return (loadersMap != null && !loadersMap.isEmpty());
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\util\FontUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */