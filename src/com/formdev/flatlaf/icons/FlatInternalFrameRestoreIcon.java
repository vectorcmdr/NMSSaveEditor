/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*    */ import java.awt.Component;
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
/*    */ public class FlatInternalFrameRestoreIcon
/*    */   extends FlatInternalFrameAbstractIcon
/*    */ {
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 39 */     paintBackground(c, g);
/*    */     
/* 41 */     g.setColor(c.getForeground());
/*    */     
/* 43 */     int x = this.width / 2 - 4;
/* 44 */     int y = this.height / 2 - 4;
/* 45 */     Path2D r1 = FlatUIUtils.createRectangle((x + 1), (y - 1), 8.0F, 8.0F, 1.0F);
/* 46 */     Path2D r2 = FlatUIUtils.createRectangle((x - 1), (y + 1), 8.0F, 8.0F, 1.0F);
/*    */     
/* 48 */     Area area = new Area(r1);
/* 49 */     area.subtract(new Area(new Rectangle2D.Float((x - 1), (y + 1), 8.0F, 8.0F)));
/* 50 */     g.fill(area);
/*    */     
/* 52 */     g.fill(r2);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatInternalFrameRestoreIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */