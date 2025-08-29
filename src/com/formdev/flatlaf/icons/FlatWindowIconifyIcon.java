/*    */ package com.formdev.flatlaf.icons;
/*    */ 
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
/*    */ public class FlatWindowIconifyIcon
/*    */   extends FlatWindowAbstractIcon
/*    */ {
/*    */   protected void paintIconAt1x(Graphics2D g, int x, int y, int width, int height, double scaleFactor) {
/* 34 */     int iw = (int)(10.0D * scaleFactor);
/* 35 */     int ih = (int)scaleFactor;
/* 36 */     int ix = x + (width - iw) / 2;
/* 37 */     int iy = y + (height - ih) / 2;
/*    */     
/* 39 */     g.fillRect(ix, iy, iw, ih);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatWindowIconifyIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */