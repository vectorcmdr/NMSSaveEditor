/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import java.util.function.Function;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ListUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatListCellBorder
/*     */   extends FlatLineBorder
/*     */ {
/*  40 */   protected boolean showCellFocusIndicator = UIManager.getBoolean("List.showCellFocusIndicator");
/*     */   
/*     */   private Component c;
/*     */   
/*     */   protected FlatListCellBorder() {
/*  45 */     super(UIManager.getInsets("List.cellMargins"), UIManager.getColor("List.cellFocusColor"));
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getBorderInsets(Component c, Insets insets) {
/*  50 */     Insets m = getStyleFromListUI(c, ui -> ui.cellMargins);
/*  51 */     if (m != null) {
/*  52 */       return scaleInsets(c, insets, m.top, m.left, m.bottom, m.right);
/*     */     }
/*  54 */     return super.getBorderInsets(c, insets);
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getLineColor() {
/*  59 */     if (this.c != null) {
/*  60 */       Color color = getStyleFromListUI(this.c, ui -> ui.cellFocusColor);
/*  61 */       if (color != null)
/*  62 */         return color; 
/*     */     } 
/*  64 */     return super.getLineColor();
/*     */   }
/*     */ 
/*     */   
/*     */   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/*  69 */     this.c = c;
/*  70 */     super.paintBorder(c, g, x, y, width, height);
/*  71 */     this.c = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static <T> T getStyleFromListUI(Component c, Function<FlatListUI, T> f) {
/*  79 */     JList<?> list = (JList)SwingUtilities.getAncestorOfClass(JList.class, c);
/*  80 */     if (list != null) {
/*  81 */       ListUI ui = list.getUI();
/*  82 */       if (ui instanceof FlatListUI)
/*  83 */         return f.apply((FlatListUI)ui); 
/*     */     } 
/*  85 */     return null;
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
/*     */   public static class Default
/*     */     extends FlatListCellBorder
/*     */   {
/*     */     public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Focused
/*     */     extends FlatListCellBorder {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Selected
/*     */     extends FlatListCellBorder
/*     */   {
/*     */     public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 123 */       Boolean b = getStyleFromListUI(c, ui -> ui.showCellFocusIndicator);
/* 124 */       boolean showCellFocusIndicator = (b != null) ? b.booleanValue() : this.showCellFocusIndicator;
/* 125 */       if (!showCellFocusIndicator) {
/*     */         return;
/*     */       }
/*     */       
/* 129 */       JList<?> list = (JList)SwingUtilities.getAncestorOfClass(JList.class, c);
/* 130 */       if (list != null && list.getMinSelectionIndex() == list.getMaxSelectionIndex()) {
/*     */         return;
/*     */       }
/* 133 */       super.paintBorder(c, g, x, y, width, height);
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatListCellBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */