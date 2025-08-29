/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.RenderingHints;
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
/*    */ 
/*    */ 
/*    */ public class FlatFileChooserListViewIcon
/*    */   extends FlatAbstractIcon
/*    */ {
/*    */   public FlatFileChooserListViewIcon() {
/* 37 */     super(16, 16, UIManager.getColor("Actions.Grey"));
/*    */   }
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
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 53 */     g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
/* 54 */     g.setStroke(new BasicStroke(1.0F, 1, 1));
/*    */     
/* 56 */     g.draw(new RoundRectangle2D.Float(2.5F, 2.5F, 4.0F, 4.0F, 2.0F, 2.0F));
/* 57 */     g.draw(new RoundRectangle2D.Float(2.5F, 9.5F, 4.0F, 4.0F, 2.0F, 2.0F));
/* 58 */     g.draw(new RoundRectangle2D.Float(9.5F, 9.5F, 4.0F, 4.0F, 2.0F, 2.0F));
/* 59 */     g.draw(new RoundRectangle2D.Float(9.5F, 2.5F, 4.0F, 4.0F, 2.0F, 2.0F));
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatFileChooserListViewIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */