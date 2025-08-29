/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.util.AnimatedIcon;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
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
/*    */ public abstract class FlatAnimatedIcon
/*    */   extends FlatAbstractIcon
/*    */   implements AnimatedIcon
/*    */ {
/*    */   public FlatAnimatedIcon(int width, int height, Color color) {
/* 42 */     super(width, height, color);
/*    */   }
/*    */ 
/*    */   
/*    */   public void paintIcon(Component c, Graphics g, int x, int y) {
/* 47 */     super.paintIcon(c, g, x, y);
/* 48 */     AnimatedIcon.AnimationSupport.saveIconLocation(this, c, x, y);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 53 */     AnimatedIcon.AnimationSupport.paintIcon(this, c, g, 0, 0);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatAnimatedIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */