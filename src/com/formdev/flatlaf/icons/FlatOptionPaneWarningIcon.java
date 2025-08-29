/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatUIUtils;
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
/*    */ public class FlatOptionPaneWarningIcon
/*    */   extends FlatOptionPaneAbstractIcon
/*    */ {
/*    */   public FlatOptionPaneWarningIcon() {
/* 37 */     super("OptionPane.icon.warningColor", "Actions.Yellow");
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
/* 52 */     return FlatUIUtils.createRoundTrianglePath(16.0F, 0.0F, 32.0F, 28.0F, 0.0F, 28.0F, 4.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Shape createInside() {
/* 58 */     Path2D inside = new Path2D.Float(0);
/* 59 */     inside.append(new RoundRectangle2D.Float(14.0F, 8.0F, 4.0F, 11.0F, 4.0F, 4.0F), false);
/* 60 */     inside.append(new Ellipse2D.Float(14.0F, 21.0F, 4.0F, 4.0F), false);
/* 61 */     return inside;
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatOptionPaneWarningIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */