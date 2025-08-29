/*     */ package com.jgoodies.forms.util;
/*     */ 
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.UIManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class FormUtils
/*     */ {
/*     */   private static LookAndFeel cachedLookAndFeel;
/*     */   private static Boolean cachedIsLafAqua;
/*     */   
/*     */   public static void assertNotBlank(String text, String description) {
/*  70 */     if (text == null) {
/*  71 */       throw new NullPointerException("The " + description + " must not be null.");
/*     */     }
/*  73 */     if (isBlank(text)) {
/*  74 */       throw new IllegalArgumentException("The " + description + " must not be empty, or whitespace. " + "See FormUtils.isBlank(String)");
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
/*     */   public static void assertNotNull(Object object, String description) {
/*  91 */     if (object == null) {
/*  92 */       throw new NullPointerException("The " + description + " must not be null.");
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
/*     */   public static boolean equals(Object o1, Object o2) {
/* 115 */     return ((o1 != null && o2 != null && o1.equals(o2)) || (o1 == null && o2 == null));
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
/*     */   public static boolean isBlank(String str) {
/*     */     int length;
/* 138 */     if (str == null || (length = str.length()) == 0) {
/* 139 */       return true;
/*     */     }
/* 141 */     for (int i = length - 1; i >= 0; i--) {
/* 142 */       if (!Character.isWhitespace(str.charAt(i))) {
/* 143 */         return false;
/*     */       }
/*     */     } 
/* 146 */     return true;
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
/*     */   public static boolean isNotBlank(String str) {
/*     */     int length;
/* 169 */     if (str == null || (length = str.length()) == 0) {
/* 170 */       return false;
/*     */     }
/* 172 */     for (int i = length - 1; i >= 0; i--) {
/* 173 */       if (!Character.isWhitespace(str.charAt(i))) {
/* 174 */         return true;
/*     */       }
/*     */     } 
/* 177 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isLafAqua() {
/* 187 */     ensureValidCache();
/* 188 */     if (cachedIsLafAqua == null) {
/* 189 */       cachedIsLafAqua = Boolean.valueOf(computeIsLafAqua());
/*     */     }
/* 191 */     return cachedIsLafAqua.booleanValue();
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
/*     */   public static void clearLookAndFeelBasedCaches() {
/* 211 */     cachedIsLafAqua = null;
/* 212 */     DefaultUnitConverter.getInstance().clearCache();
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
/*     */   private static boolean computeIsLafAqua() {
/* 239 */     return UIManager.getLookAndFeel().getID().equals("Aqua");
/*     */   }
/*     */ 
/*     */   
/*     */   static void ensureValidCache() {
/* 244 */     LookAndFeel currentLookAndFeel = UIManager.getLookAndFeel();
/* 245 */     if (currentLookAndFeel != cachedLookAndFeel) {
/* 246 */       clearLookAndFeelBasedCaches();
/* 247 */       cachedLookAndFeel = currentLookAndFeel;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\form\\util\FormUtils.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */