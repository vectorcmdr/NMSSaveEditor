/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatStylingSupport.Styleable;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.geom.Ellipse2D;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FlatRadioButtonIcon
/*    */   extends FlatCheckBoxIcon
/*    */ {
/*    */   @Styleable
/* 40 */   protected float centerDiameter = getUIFloat("RadioButton.icon.centerDiameter", 8.0F, this.style);
/*    */ 
/*    */   
/*    */   protected String getPropertyPrefix() {
/* 44 */     return "RadioButton.";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void paintFocusBorder(Component c, Graphics2D g) {
/* 50 */     float wh = 15.0F + this.focusWidth * 2.0F;
/* 51 */     g.fill(new Ellipse2D.Float(-this.focusWidth, -this.focusWidth, wh, wh));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paintBorder(Component c, Graphics2D g, float borderWidth) {
/* 56 */     if (borderWidth == 0.0F) {
/*    */       return;
/*    */     }
/* 59 */     g.fillOval(0, 0, 15, 15);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paintBackground(Component c, Graphics2D g, float borderWidth) {
/* 64 */     float xy = borderWidth;
/* 65 */     float wh = 15.0F - borderWidth * 2.0F;
/* 66 */     g.fill(new Ellipse2D.Float(xy, xy, wh, wh));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paintCheckmark(Component c, Graphics2D g) {
/* 71 */     float xy = (15.0F - this.centerDiameter) / 2.0F;
/* 72 */     g.fill(new Ellipse2D.Float(xy, xy, this.centerDiameter, this.centerDiameter));
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatRadioButtonIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */