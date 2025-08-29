/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*    */ import com.formdev.flatlaf.util.SystemInfo;
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
/*    */ public class FlatWindowMaximizeIcon
/*    */   extends FlatWindowAbstractIcon
/*    */ {
/*    */   protected void paintIconAt1x(Graphics2D g, int x, int y, int width, int height, double scaleFactor) {
/* 36 */     int iwh = (int)(10.0D * scaleFactor);
/* 37 */     int ix = x + (width - iwh) / 2;
/* 38 */     int iy = y + (height - iwh) / 2;
/* 39 */     float thickness = SystemInfo.isWindows_11_orLater ? (float)scaleFactor : (int)scaleFactor;
/* 40 */     int arc = Math.max((int)(1.5D * scaleFactor), 2);
/*    */     
/* 42 */     g.fill(SystemInfo.isWindows_11_orLater ? 
/* 43 */         FlatUIUtils.createRoundRectangle(ix, iy, iwh, iwh, thickness, arc, arc, arc, arc) : 
/* 44 */         FlatUIUtils.createRectangle(ix, iy, iwh, iwh, thickness));
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatWindowMaximizeIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */