/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.geom.Area;
/*    */ import java.awt.geom.Ellipse2D;
/*    */ import java.awt.geom.Path2D;
/*    */ import java.awt.geom.Rectangle2D;
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
/*    */ public class FlatRevealIcon
/*    */   extends FlatAbstractIcon
/*    */ {
/*    */   public FlatRevealIcon() {
/* 39 */     super(16, 16, UIManager.getColor("PasswordField.revealIconColor"));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 44 */     Path2D path = new Path2D.Float(0);
/* 45 */     path.append(new Ellipse2D.Float(5.15F, 6.15F, 5.7F, 5.7F), false);
/* 46 */     path.append(new Ellipse2D.Float(6.0F, 7.0F, 4.0F, 4.0F), false);
/* 47 */     g.fill(path);
/*    */     
/* 49 */     Path2D path2 = new Path2D.Float(0);
/* 50 */     path2.append(new Ellipse2D.Float(2.15F, 4.15F, 11.7F, 11.7F), false);
/* 51 */     path2.append(new Ellipse2D.Float(3.0F, 5.0F, 10.0F, 10.0F), false);
/* 52 */     Area area = new Area(path2);
/* 53 */     area.subtract(new Area(new Rectangle2D.Float(0.0F, 9.5F, 16.0F, 16.0F)));
/* 54 */     g.fill(area);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatRevealIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */