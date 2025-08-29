/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatUIUtils;
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
/*    */ public class FlatInternalFrameMaximizeIcon
/*    */   extends FlatInternalFrameAbstractIcon
/*    */ {
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 36 */     paintBackground(c, g);
/*    */     
/* 38 */     g.setColor(c.getForeground());
/* 39 */     g.fill(FlatUIUtils.createRectangle((this.width / 2 - 4), (this.height / 2 - 4), 8.0F, 8.0F, 1.0F));
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatInternalFrameMaximizeIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */