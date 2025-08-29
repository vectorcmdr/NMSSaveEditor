/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatButtonUI;
/*    */ import com.formdev.flatlaf.util.SystemInfo;
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.geom.Path2D;
/*    */ import javax.swing.UIManager;
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
/*    */ public class FlatWindowCloseIcon
/*    */   extends FlatWindowAbstractIcon
/*    */ {
/* 41 */   private final Color hoverForeground = UIManager.getColor("TitlePane.closeHoverForeground");
/* 42 */   private final Color pressedForeground = UIManager.getColor("TitlePane.closePressedForeground");
/*    */   
/*    */   public FlatWindowCloseIcon() {
/* 45 */     super(UIManager.getDimension("TitlePane.buttonSize"), 
/* 46 */         UIManager.getColor("TitlePane.closeHoverBackground"), 
/* 47 */         UIManager.getColor("TitlePane.closePressedBackground"));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paintIconAt1x(Graphics2D g, int x, int y, int width, int height, double scaleFactor) {
/* 52 */     int iwh = (int)(10.0D * scaleFactor);
/* 53 */     int ix = x + (width - iwh) / 2;
/* 54 */     int iy = y + (height - iwh) / 2;
/* 55 */     int ix2 = ix + iwh - 1;
/* 56 */     int iy2 = iy + iwh - 1;
/* 57 */     float thickness = SystemInfo.isWindows_11_orLater ? (float)scaleFactor : (int)scaleFactor;
/*    */     
/* 59 */     Path2D path = new Path2D.Float(0, 4);
/* 60 */     path.moveTo(ix, iy);
/* 61 */     path.lineTo(ix2, iy2);
/* 62 */     path.moveTo(ix, iy2);
/* 63 */     path.lineTo(ix2, iy);
/* 64 */     g.setStroke(new BasicStroke(thickness));
/* 65 */     g.draw(path);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Color getForeground(Component c) {
/* 70 */     return FlatButtonUI.buttonStateColor(c, c.getForeground(), null, null, this.hoverForeground, this.pressedForeground);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatWindowCloseIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */