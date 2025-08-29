/*     */ package com.jgoodies.forms.util;
/*     */ 
/*     */ import com.jgoodies.forms.layout.ConstantSize;
/*     */ import com.jgoodies.forms.layout.Size;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class LayoutStyle
/*     */ {
/*  61 */   private static LayoutStyle current = initialLayoutStyle();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static LayoutStyle initialLayoutStyle() {
/*  74 */     if (isOSMac())
/*  75 */       return MacLayoutStyle.INSTANCE; 
/*  76 */     return WindowsLayoutStyle.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isOSMac() {
/*  87 */     return getSystemProperty("os.name").startsWith("Mac");
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
/*     */   private static String getSystemProperty(String key) {
/*     */     try {
/* 102 */       return System.getProperty(key);
/* 103 */     } catch (SecurityException e) {
/* 104 */       Logger.getLogger(LayoutStyle.class.getName()).warning("Can't read the System property " + key + ".");
/*     */       
/* 106 */       return "";
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
/*     */   public static LayoutStyle getCurrent() {
/* 119 */     return current;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setCurrent(LayoutStyle newLayoutStyle) {
/* 129 */     current = newLayoutStyle;
/*     */   }
/*     */   
/*     */   public abstract Size getDefaultButtonWidth();
/*     */   
/*     */   public abstract Size getDefaultButtonHeight();
/*     */   
/*     */   public abstract ConstantSize getDialogMarginX();
/*     */   
/*     */   public abstract ConstantSize getDialogMarginY();
/*     */   
/*     */   public abstract ConstantSize getTabbedDialogMarginX();
/*     */   
/*     */   public abstract ConstantSize getTabbedDialogMarginY();
/*     */   
/*     */   public abstract ConstantSize getLabelComponentPadX();
/*     */   
/*     */   public abstract ConstantSize getRelatedComponentsPadX();
/*     */   
/*     */   public abstract ConstantSize getRelatedComponentsPadY();
/*     */   
/*     */   public abstract ConstantSize getUnrelatedComponentsPadX();
/*     */   
/*     */   public abstract ConstantSize getUnrelatedComponentsPadY();
/*     */   
/*     */   public abstract ConstantSize getNarrowLinePad();
/*     */   
/*     */   public abstract ConstantSize getLinePad();
/*     */   
/*     */   public abstract ConstantSize getParagraphPad();
/*     */   
/*     */   public abstract ConstantSize getButtonBarPad();
/*     */   
/*     */   public abstract boolean isLeftToRightButtonOrder();
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\form\\util\LayoutStyle.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */