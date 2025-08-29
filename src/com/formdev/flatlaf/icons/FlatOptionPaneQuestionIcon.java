/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.Ellipse2D;
/*    */ import java.awt.geom.Path2D;
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
/*    */ public class FlatOptionPaneQuestionIcon
/*    */   extends FlatOptionPaneAbstractIcon
/*    */ {
/*    */   public FlatOptionPaneQuestionIcon() {
/* 36 */     super("OptionPane.icon.questionColor", "Actions.Blue");
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
/*    */   protected Shape createOutside() {
/* 51 */     return new Ellipse2D.Float(2.0F, 2.0F, 28.0F, 28.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Shape createInside() {
/* 56 */     Path2D q = new Path2D.Float(1, 10);
/* 57 */     q.moveTo(11.5D, 11.75D);
/* 58 */     q.curveTo(11.75D, 9.5D, 13.75D, 8.0D, 16.0D, 8.0D);
/* 59 */     q.curveTo(18.25D, 8.0D, 20.5D, 9.5D, 20.5D, 11.75D);
/* 60 */     q.curveTo(20.5D, 14.75D, 16.0D, 15.5D, 16.0D, 19.0D);
/*    */     
/* 62 */     BasicStroke stroke = new BasicStroke(3.0F, 1, 0);
/*    */     
/* 64 */     Path2D inside = new Path2D.Float(0);
/* 65 */     inside.append(new Ellipse2D.Float(14.3F, 22.3F, 3.4F, 3.4F), false);
/* 66 */     inside.append(stroke.createStrokedShape(q), false);
/* 67 */     return inside;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatOptionPaneQuestionIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */