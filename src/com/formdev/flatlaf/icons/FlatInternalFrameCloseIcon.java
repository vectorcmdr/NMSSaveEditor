/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatButtonUI;
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
/*    */ 
/*    */ public class FlatInternalFrameCloseIcon
/*    */   extends FlatInternalFrameAbstractIcon
/*    */ {
/* 41 */   private final Color hoverForeground = UIManager.getColor("InternalFrame.closeHoverForeground");
/* 42 */   private final Color pressedForeground = UIManager.getColor("InternalFrame.closePressedForeground");
/*    */   
/*    */   public FlatInternalFrameCloseIcon() {
/* 45 */     super(UIManager.getDimension("InternalFrame.buttonSize"), 
/* 46 */         UIManager.getColor("InternalFrame.closeHoverBackground"), 
/* 47 */         UIManager.getColor("InternalFrame.closePressedBackground"));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 52 */     paintBackground(c, g);
/*    */     
/* 54 */     g.setColor(FlatButtonUI.buttonStateColor(c, c.getForeground(), null, null, this.hoverForeground, this.pressedForeground));
/*    */     
/* 56 */     float mx = (this.width / 2);
/* 57 */     float my = (this.height / 2);
/* 58 */     float r = 3.25F;
/*    */     
/* 60 */     Path2D path = new Path2D.Float(0, 4);
/* 61 */     path.moveTo((mx - r), (my - r));
/* 62 */     path.lineTo((mx + r), (my + r));
/* 63 */     path.moveTo((mx - r), (my + r));
/* 64 */     path.lineTo((mx + r), (my - r));
/* 65 */     g.setStroke(new BasicStroke(1.0F));
/* 66 */     g.draw(path);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatInternalFrameCloseIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */