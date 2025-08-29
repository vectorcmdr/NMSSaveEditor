/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.util.function.Function;
/*     */ import javax.swing.JToolBar;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ToolBarUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatToolBarBorder
/*     */   extends FlatMarginBorder
/*     */ {
/*     */   private static final int DOT_COUNT = 4;
/*     */   private static final int DOT_SIZE = 2;
/*     */   private static final int GRIP_SIZE = 6;
/*  47 */   protected Color gripColor = UIManager.getColor("ToolBar.gripColor");
/*     */   
/*     */   public FlatToolBarBorder() {
/*  50 */     super(UIManager.getInsets("ToolBar.borderMargins"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/*  56 */     if (c instanceof JToolBar && ((JToolBar)c).isFloatable()) {
/*  57 */       Graphics2D g2 = (Graphics2D)g.create();
/*     */       try {
/*  59 */         FlatUIUtils.setRenderingHints(g2);
/*     */         
/*  61 */         Color color = getStyleFromToolBarUI(c, ui -> ui.gripColor);
/*  62 */         g2.setColor((color != null) ? color : this.gripColor);
/*  63 */         paintGrip(c, g2, x, y, width, height);
/*     */       } finally {
/*  65 */         g2.dispose();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void paintGrip(Component c, Graphics g, int x, int y, int width, int height) {
/*  71 */     Rectangle r = calculateGripBounds(c, x, y, width, height);
/*  72 */     FlatUIUtils.paintGrip(g, r.x, r.y, r.width, r.height, 
/*  73 */         (((JToolBar)c).getOrientation() == 1), 4, 2, 2, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Rectangle calculateGripBounds(Component c, int x, int y, int width, int height) {
/*  79 */     Insets insets = super.getBorderInsets(c, new Insets(0, 0, 0, 0));
/*  80 */     Rectangle r = FlatUIUtils.subtractInsets(new Rectangle(x, y, width, height), insets);
/*     */ 
/*     */     
/*  83 */     int gripSize = UIScale.scale(6);
/*  84 */     if (((JToolBar)c).getOrientation() == 0) {
/*  85 */       if (!c.getComponentOrientation().isLeftToRight())
/*  86 */         r.x = r.x + r.width - gripSize; 
/*  87 */       r.width = gripSize;
/*     */     } else {
/*  89 */       r.height = gripSize;
/*     */     } 
/*  91 */     return r;
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getBorderInsets(Component c, Insets insets) {
/*  96 */     Insets m = getStyleFromToolBarUI(c, ui -> ui.borderMargins);
/*  97 */     if (m != null) {
/*  98 */       int t = this.top, l = this.left, b = this.bottom, r = this.right;
/*  99 */       this.top = m.top; this.left = m.left; this.bottom = m.bottom; this.right = m.right;
/* 100 */       insets = super.getBorderInsets(c, insets);
/* 101 */       this.top = t; this.left = l; this.bottom = b; this.right = r;
/*     */     } else {
/* 103 */       insets = super.getBorderInsets(c, insets);
/*     */     } 
/*     */     
/* 106 */     if (c instanceof JToolBar && ((JToolBar)c).isFloatable()) {
/* 107 */       int gripInset = UIScale.scale(6);
/* 108 */       if (((JToolBar)c).getOrientation() == 0)
/* 109 */       { if (c.getComponentOrientation().isLeftToRight()) {
/* 110 */           insets.left += gripInset;
/*     */         } else {
/* 112 */           insets.right += gripInset;
/*     */         }  }
/* 114 */       else { insets.top += gripInset; }
/*     */     
/*     */     } 
/* 117 */     return insets;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static <T> T getStyleFromToolBarUI(Component c, Function<FlatToolBarUI, T> f) {
/* 125 */     if (c instanceof JToolBar) {
/* 126 */       ToolBarUI ui = ((JToolBar)c).getUI();
/* 127 */       if (ui instanceof FlatToolBarUI)
/* 128 */         return f.apply((FlatToolBarUI)ui); 
/*     */     } 
/* 130 */     return null;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatToolBarBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */