/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*    */ import com.formdev.flatlaf.util.SystemInfo;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.geom.Area;
/*    */ import java.awt.geom.Path2D;
/*    */ import java.awt.geom.Rectangle2D;
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
/*    */ public class FlatWindowRestoreIcon
/*    */   extends FlatWindowAbstractIcon
/*    */ {
/*    */   protected void paintIconAt1x(Graphics2D g, int x, int y, int width, int height, double scaleFactor) {
/* 39 */     int iwh = (int)(10.0D * scaleFactor);
/* 40 */     int ix = x + (width - iwh) / 2;
/* 41 */     int iy = y + (height - iwh) / 2;
/* 42 */     float thickness = SystemInfo.isWindows_11_orLater ? (float)scaleFactor : (int)scaleFactor;
/* 43 */     int arc = Math.max((int)(1.5D * scaleFactor), 2);
/* 44 */     int arcOuter = (int)(arc + 1.5D * scaleFactor);
/*    */     
/* 46 */     int rwh = (int)(8.0D * scaleFactor);
/* 47 */     int ro2 = iwh - rwh;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 52 */     Path2D r1 = SystemInfo.isWindows_11_orLater ? FlatUIUtils.createRoundRectangle((ix + ro2), iy, rwh, rwh, thickness, arc, arcOuter, arc, arc) : FlatUIUtils.createRectangle((ix + ro2), iy, rwh, rwh, thickness);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 57 */     Path2D r2 = SystemInfo.isWindows_11_orLater ? FlatUIUtils.createRoundRectangle(ix, (iy + ro2), rwh, rwh, thickness, arc, arc, arc, arc) : FlatUIUtils.createRectangle(ix, (iy + ro2), rwh, rwh, thickness);
/*    */ 
/*    */     
/* 60 */     Area area = new Area(r1);
/* 61 */     if (SystemInfo.isWindows_11_orLater) {
/* 62 */       area.subtract(new Area(new Rectangle2D.Float(ix, (float)(iy + scaleFactor), rwh, rwh)));
/* 63 */       area.subtract(new Area(new Rectangle2D.Float((float)(ix + scaleFactor), (iy + ro2), rwh, rwh)));
/*    */     } else {
/* 65 */       area.subtract(new Area(new Rectangle2D.Float(ix, (iy + ro2), rwh, rwh)));
/* 66 */     }  g.fill(area);
/*    */ 
/*    */     
/* 69 */     g.fill(r2);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatWindowRestoreIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */