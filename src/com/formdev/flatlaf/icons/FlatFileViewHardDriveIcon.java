/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.geom.Ellipse2D;
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
/*    */ public class FlatFileViewHardDriveIcon
/*    */   extends FlatAbstractIcon
/*    */ {
/*    */   public FlatFileViewHardDriveIcon() {
/* 38 */     super(16, 16, UIManager.getColor("Objects.Grey"));
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
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 53 */     g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
/* 54 */     g.setStroke(new BasicStroke(1.0F, 1, 1));
/*    */     
/* 56 */     g.draw(new RoundRectangle2D.Float(2.5F, 5.5F, 11.0F, 5.0F, 2.0F, 2.0F));
/* 57 */     g.fill(new Ellipse2D.Float(10.8F, 7.8F, 1.4F, 1.4F));
/* 58 */     g.fill(new Ellipse2D.Float(8.8F, 7.8F, 1.4F, 1.4F));
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatFileViewHardDriveIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */