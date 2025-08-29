/*    */ package com.formdev.flatlaf.ui;
/*    */ 
/*    */ import com.formdev.flatlaf.util.UIScale;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Insets;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FlatLineBorder
/*    */   extends FlatEmptyBorder
/*    */ {
/*    */   private final Color lineColor;
/*    */   private final float lineThickness;
/*    */   private final int arc;
/*    */   
/*    */   public FlatLineBorder(Insets insets, Color lineColor) {
/* 43 */     this(insets, lineColor, 1.0F, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public FlatLineBorder(Insets insets, Color lineColor, float lineThickness, int arc) {
/* 48 */     super(insets);
/* 49 */     this.lineColor = lineColor;
/* 50 */     this.lineThickness = lineThickness;
/* 51 */     this.arc = arc;
/*    */   }
/*    */   
/*    */   public Color getLineColor() {
/* 55 */     return this.lineColor;
/*    */   }
/*    */   
/*    */   public float getLineThickness() {
/* 59 */     return this.lineThickness;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getArc() {
/* 64 */     return this.arc;
/*    */   }
/*    */ 
/*    */   
/*    */   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 69 */     Graphics2D g2 = (Graphics2D)g.create();
/*    */     try {
/* 71 */       FlatUIUtils.setRenderingHints(g2);
/* 72 */       FlatUIUtils.paintOutlinedComponent(g2, x, y, width, height, 0.0F, 0.0F, 0.0F, 
/* 73 */           UIScale.scale(getLineThickness()), UIScale.scale(getArc()), null, getLineColor(), null);
/*    */     } finally {
/* 75 */       g2.dispose();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatLineBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */