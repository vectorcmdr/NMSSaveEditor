/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatStylingSupport;
/*    */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.geom.Area;
/*    */ import java.awt.geom.Path2D;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.awt.geom.RoundRectangle2D;
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
/*    */ public class FlatCapsLockIcon
/*    */   extends FlatAbstractIcon
/*    */ {
/*    */   private Path2D path;
/*    */   
/*    */   public FlatCapsLockIcon() {
/* 45 */     super(16, 16, UIManager.getColor("PasswordField.capsLockIconColor"));
/*    */   }
/*    */ 
/*    */   
/*    */   public Object applyStyleProperty(String key, Object value) {
/*    */     Object oldValue;
/* 51 */     switch (key) { case "capsLockIconColor":
/* 52 */         oldValue = this.color; this.color = (Color)value; return oldValue; }
/* 53 */      throw new FlatStylingSupport.UnknownStyleException(key);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getStyleableValue(String key) {
/* 59 */     switch (key) { case "capsLockIconColor":
/* 60 */         return this.color; }
/* 61 */      return null;
/*    */   }
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
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 77 */     g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
/* 78 */     BasicStroke stroke = new BasicStroke(1.0F, 2, 1);
/*    */     
/* 80 */     if (this.path == null) {
/* 81 */       this.path = new Path2D.Float(0);
/* 82 */       this.path.append(new RoundRectangle2D.Float(0.0F, 0.0F, 16.0F, 16.0F, 6.0F, 6.0F), false);
/* 83 */       this.path.append(new Area(stroke.createStrokedShape(new Rectangle2D.Float(5.5F, 11.5F, 5.0F, 2.0F))), false);
/* 84 */       this.path.append(new Area(stroke.createStrokedShape(FlatUIUtils.createPath(new double[] { 2.5D, 7.5D, 8.0D, 2.0D, 13.5D, 7.5D, 10.5D, 7.5D, 10.5D, 9.5D, 5.5D, 9.5D, 5.5D, 7.5D, 2.5D, 7.5D }, ))), false);
/*    */     } 
/*    */     
/* 87 */     g.fill(this.path);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatCapsLockIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */