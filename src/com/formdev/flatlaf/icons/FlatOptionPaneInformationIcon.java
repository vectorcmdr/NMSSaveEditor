/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.Ellipse2D;
/*    */ import java.awt.geom.Path2D;
/*    */ import java.awt.geom.RoundRectangle2D;
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
/*    */ public class FlatOptionPaneInformationIcon
/*    */   extends FlatOptionPaneAbstractIcon
/*    */ {
/*    */   public FlatOptionPaneInformationIcon() {
/* 36 */     super("OptionPane.icon.informationColor", "Actions.Blue");
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
/* 56 */     Path2D inside = new Path2D.Float(0);
/* 57 */     inside.append(new RoundRectangle2D.Float(14.0F, 13.0F, 4.0F, 12.0F, 4.0F, 4.0F), false);
/* 58 */     inside.append(new Ellipse2D.Float(14.0F, 7.0F, 4.0F, 4.0F), false);
/* 59 */     return inside;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatOptionPaneInformationIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */