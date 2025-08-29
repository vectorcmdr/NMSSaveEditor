/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Insets;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.function.Function;
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
/*     */ public class MigLayoutVisualPadding
/*     */ {
/*  43 */   public static String VISUAL_PADDING_PROPERTY = "visualPadding";
/*     */   
/*  45 */   private static final FlatMigInsets ZERO = new FlatMigInsets(0, 0, 0, 0);
/*     */   
/*     */   private static final boolean migLayoutAvailable;
/*     */   
/*     */   static {
/*  50 */     boolean available = false;
/*     */     try {
/*  52 */       Class.forName("net.miginfocom.swing.MigLayout");
/*  53 */       available = true;
/*  54 */     } catch (ClassNotFoundException classNotFoundException) {}
/*     */ 
/*     */     
/*  57 */     migLayoutAvailable = available;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void install(JComponent c, Insets insets) {
/*  64 */     if (!migLayoutAvailable) {
/*     */       return;
/*     */     }
/*  67 */     setVisualPadding(c, insets);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void install(JComponent c) {
/*  74 */     if (!migLayoutAvailable) {
/*     */       return;
/*     */     }
/*  77 */     install(c, c2 -> { FlatBorder border = FlatUIUtils.getOutsideFlatBorder(c2); if (border != null) { int focusWidth = border.getFocusWidth(c2); return new Insets(focusWidth, focusWidth, focusWidth, focusWidth); }  return null; }new String[] { "border", "FlatLaf.style", "FlatLaf.styleClass" });
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
/*     */   public static void install(JComponent c, Function<JComponent, Insets> getPaddingFunction, String... propertyNames) {
/*  93 */     if (!migLayoutAvailable) {
/*     */       return;
/*     */     }
/*     */     
/*  97 */     setVisualPadding(c, getPaddingFunction.apply(c));
/*     */ 
/*     */     
/* 100 */     c.addPropertyChangeListener(e -> {
/*     */           String propertyName = e.getPropertyName();
/*     */           for (String name : propertyNames) {
/*     */             if (name.equals(propertyName)) {
/*     */               setVisualPadding(c, getPaddingFunction.apply(c));
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         });
/*     */   }
/*     */   
/*     */   private static void setVisualPadding(JComponent c, Insets visualPadding) {
/* 112 */     Object oldPadding = c.getClientProperty(VISUAL_PADDING_PROPERTY);
/* 113 */     if (oldPadding == null || oldPadding instanceof FlatMigInsets) {
/*     */ 
/*     */ 
/*     */       
/* 117 */       FlatMigInsets flatVisualPadding = (visualPadding != null) ? new FlatMigInsets(UIScale.scale2(visualPadding.top), UIScale.scale2(visualPadding.left), UIScale.scale2(visualPadding.bottom), UIScale.scale2(visualPadding.right)) : ZERO;
/*     */       
/* 119 */       c.putClientProperty(VISUAL_PADDING_PROPERTY, flatVisualPadding);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void uninstall(JComponent c) {
/* 127 */     if (!migLayoutAvailable) {
/*     */       return;
/*     */     }
/*     */     
/* 131 */     for (PropertyChangeListener l : c.getPropertyChangeListeners()) {
/* 132 */       if (l instanceof FlatMigListener) {
/* 133 */         c.removePropertyChangeListener(l);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 139 */     if (c.getClientProperty(VISUAL_PADDING_PROPERTY) instanceof FlatMigInsets) {
/* 140 */       c.putClientProperty(VISUAL_PADDING_PROPERTY, (Object)null);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static interface FlatMigListener
/*     */     extends PropertyChangeListener {}
/*     */ 
/*     */   
/*     */   private static class FlatMigInsets
/*     */     extends Insets
/*     */   {
/*     */     FlatMigInsets(int top, int left, int bottom, int right) {
/* 153 */       super(top, left, bottom, right);
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\MigLayoutVisualPadding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */