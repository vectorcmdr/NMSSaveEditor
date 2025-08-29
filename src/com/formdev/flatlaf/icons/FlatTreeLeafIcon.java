/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatTreeUI;
/*    */ import com.formdev.flatlaf.util.ColorFunctions;
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.geom.Line2D;
/*    */ import java.awt.geom.RoundRectangle2D;
/*    */ import javax.swing.UIManager;
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
/*    */ public class FlatTreeLeafIcon
/*    */   extends FlatAbstractIcon
/*    */ {
/*    */   public FlatTreeLeafIcon() {
/* 39 */     super(16, 16, UIManager.getColor("Tree.icon.leafColor"));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 44 */     FlatTreeCollapsedIcon.setStyleColorFromTreeUI(c, g, ui -> ui.iconLeafColor);
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
/* 57 */     g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
/* 58 */     g.setStroke(new BasicStroke(1.0F, 1, 1));
/*    */     
/* 60 */     g.draw(new RoundRectangle2D.Float(2.5F, 1.5F, 11.0F, 13.0F, 3.0F, 3.0F));
/*    */     
/* 62 */     g.setColor(ColorFunctions.fade(g.getColor(), 0.6F));
/* 63 */     g.draw(new Line2D.Float(5.5F, 5.5F, 10.5F, 5.5F));
/* 64 */     g.draw(new Line2D.Float(5.5F, 8.0F, 10.5F, 8.0F));
/* 65 */     g.draw(new Line2D.Float(5.5F, 10.5F, 10.5F, 10.5F));
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatTreeLeafIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */