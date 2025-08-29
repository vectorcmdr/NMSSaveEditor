/*     */ package com.formdev.flatlaf;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatPropertiesLaf
/*     */   extends FlatLaf
/*     */ {
/*     */   private final String name;
/*     */   private final String baseTheme;
/*     */   private final boolean dark;
/*     */   private final Properties properties;
/*     */   
/*     */   public FlatPropertiesLaf(String name, File propertiesFile) throws IOException {
/*  49 */     this(name, new FileInputStream(propertiesFile));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FlatPropertiesLaf(String name, InputStream in) throws IOException {
/*  55 */     this(name, loadProperties(in));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Properties loadProperties(InputStream in) throws IOException {
/*  61 */     Properties properties = new Properties();
/*  62 */     InputStream in2 = in; 
/*  63 */     try { properties.load(in2);
/*  64 */       if (in2 != null) in2.close();  } catch (Throwable throwable) { if (in2 != null)
/*  65 */         try { in2.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  return properties;
/*     */   }
/*     */   
/*     */   public FlatPropertiesLaf(String name, Properties properties) {
/*  69 */     this.name = name;
/*  70 */     this.properties = properties;
/*     */     
/*  72 */     this.baseTheme = properties.getProperty("@baseTheme", "light");
/*  73 */     this.dark = ("dark".equalsIgnoreCase(this.baseTheme) || "darcula".equalsIgnoreCase(this.baseTheme));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  78 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  83 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDark() {
/*  88 */     return this.dark;
/*     */   }
/*     */   
/*     */   public Properties getProperties() {
/*  92 */     return this.properties;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ArrayList<Class<?>> getLafClassesForDefaultsLoading() {
/*  97 */     ArrayList<Class<?>> lafClasses = new ArrayList<>();
/*  98 */     lafClasses.add(FlatLaf.class);
/*  99 */     switch (this.baseTheme.toLowerCase())
/*     */     
/*     */     { default:
/* 102 */         lafClasses.add(FlatLightLaf.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 119 */         return lafClasses;case "dark": lafClasses.add(FlatDarkLaf.class); return lafClasses;case "intellij": lafClasses.add(FlatLightLaf.class); lafClasses.add(FlatIntelliJLaf.class); return lafClasses;case "darcula": break; }  lafClasses.add(FlatDarkLaf.class); lafClasses.add(FlatDarculaLaf.class); return lafClasses;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Properties getAdditionalDefaults() {
/* 124 */     return this.properties;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\FlatPropertiesLaf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */