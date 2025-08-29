/*     */ package com.formdev.flatlaf.util;
/*     */ 
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.lang.invoke.MethodHandle;
/*     */ import java.lang.invoke.MethodHandles;
/*     */ import java.lang.invoke.MethodType;
/*     */ import javax.swing.JComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JavaCompatibility
/*     */ {
/*     */   private static MethodHandle drawStringUnderlineCharAtMethod;
/*     */   private static MethodHandle getClippedStringMethod;
/*     */   
/*     */   public static void drawStringUnderlineCharAt(JComponent c, Graphics g, String text, int underlinedIndex, int x, int y) {
/*  49 */     synchronized (JavaCompatibility.class) {
/*  50 */       if (drawStringUnderlineCharAtMethod == null) {
/*     */         try {
/*  52 */           Class<?> cls = Class.forName(SystemInfo.isJava_9_orLater ? 
/*  53 */               "javax.swing.plaf.basic.BasicGraphicsUtils" : 
/*  54 */               "sun.swing.SwingUtilities2");
/*     */           
/*  56 */           (new Class[6])[0] = JComponent.class; (new Class[6])[1] = Graphics2D.class; (new Class[6])[2] = String.class; (new Class[6])[3] = int.class; (new Class[6])[4] = float.class; (new Class[6])[5] = float.class;
/*  57 */           (new Class[6])[0] = JComponent.class; (new Class[6])[1] = Graphics.class; (new Class[6])[2] = String.class; (new Class[6])[3] = int.class; (new Class[6])[4] = int.class; (new Class[6])[5] = int.class; MethodType mt = MethodType.methodType(void.class, SystemInfo.isJava_9_orLater ? new Class[6] : new Class[6]);
/*  58 */           drawStringUnderlineCharAtMethod = MethodHandles.publicLookup().findStatic(cls, "drawStringUnderlineCharAt", mt);
/*  59 */         } catch (Exception ex) {
/*  60 */           LoggingFacade.INSTANCE.logSevere(null, ex);
/*  61 */           throw new RuntimeException(ex);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/*  67 */       if (SystemInfo.isJava_9_orLater)
/*  68 */       { drawStringUnderlineCharAtMethod.invoke(c, (Graphics2D)g, text, underlinedIndex, x, y); }
/*     */       else
/*  70 */       { drawStringUnderlineCharAtMethod.invoke(c, g, text, underlinedIndex, x, y); } 
/*  71 */     } catch (Throwable ex) {
/*  72 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*  73 */       throw new RuntimeException(ex);
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
/*     */   public static String getClippedString(JComponent c, FontMetrics fm, String string, int availTextWidth) {
/*  85 */     synchronized (JavaCompatibility.class) {
/*  86 */       if (getClippedStringMethod == null) {
/*     */         try {
/*  88 */           Class<?> cls = Class.forName(SystemInfo.isJava_9_orLater ? 
/*  89 */               "javax.swing.plaf.basic.BasicGraphicsUtils" : 
/*  90 */               "sun.swing.SwingUtilities2");
/*  91 */           MethodType mt = MethodType.methodType(String.class, JComponent.class, new Class[] { FontMetrics.class, String.class, int.class });
/*  92 */           getClippedStringMethod = MethodHandles.publicLookup().findStatic(cls, SystemInfo.isJava_9_orLater ? 
/*  93 */               "getClippedString" : 
/*  94 */               "clipStringIfNecessary", mt);
/*     */         }
/*  96 */         catch (Exception ex) {
/*  97 */           LoggingFacade.INSTANCE.logSevere(null, ex);
/*  98 */           throw new RuntimeException(ex);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/* 104 */       return getClippedStringMethod.invoke(c, fm, string, availTextWidth);
/* 105 */     } catch (Throwable ex) {
/* 106 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/* 107 */       throw new RuntimeException(ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\util\JavaCompatibility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */