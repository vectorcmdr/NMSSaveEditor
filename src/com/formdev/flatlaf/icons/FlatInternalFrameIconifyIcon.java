/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import java.awt.Component;
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
/*    */ public class FlatInternalFrameIconifyIcon
/*    */   extends FlatInternalFrameAbstractIcon
/*    */ {
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 35 */     paintBackground(c, g);
/*    */     
/* 37 */     g.setColor(c.getForeground());
/* 38 */     g.fillRect(this.width / 2 - 4, this.height / 2, 8, 1);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatInternalFrameIconifyIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */