/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*    */ import com.formdev.flatlaf.util.UIScale;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import javax.swing.Icon;
/*    */ import javax.swing.plaf.UIResource;
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
/*    */ public abstract class FlatAbstractIcon
/*    */   implements Icon, UIResource
/*    */ {
/*    */   protected final int width;
/*    */   protected final int height;
/*    */   protected Color color;
/*    */   
/*    */   public FlatAbstractIcon(int width, int height, Color color) {
/* 45 */     this.width = width;
/* 46 */     this.height = height;
/* 47 */     this.color = color;
/*    */   }
/*    */ 
/*    */   
/*    */   public void paintIcon(Component c, Graphics g, int x, int y) {
/* 52 */     Graphics2D g2 = (Graphics2D)g.create();
/*    */     try {
/* 54 */       FlatUIUtils.setRenderingHints(g2);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 60 */       g2.translate(x, y);
/* 61 */       UIScale.scaleGraphics(g2);
/*    */       
/* 63 */       if (this.color != null) {
/* 64 */         g2.setColor(this.color);
/*    */       }
/* 66 */       paintIcon(c, g2);
/*    */     } finally {
/* 68 */       g2.dispose();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected abstract void paintIcon(Component paramComponent, Graphics2D paramGraphics2D);
/*    */   
/*    */   public int getIconWidth() {
/* 76 */     return UIScale.scale(this.width);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getIconHeight() {
/* 81 */     return UIScale.scale(this.height);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatAbstractIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */