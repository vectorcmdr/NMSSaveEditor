/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Shape;
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
/*    */ public abstract class FlatOptionPaneAbstractIcon
/*    */   extends FlatAbstractIcon
/*    */ {
/* 37 */   protected final Color foreground = UIManager.getColor("OptionPane.icon.foreground");
/*    */   
/*    */   protected FlatOptionPaneAbstractIcon(String colorKey, String defaultColorKey) {
/* 40 */     super(32, 32, FlatUIUtils.getUIColor(colorKey, defaultColorKey));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 45 */     if (this.foreground != null) {
/* 46 */       g.fill(createOutside());
/*    */       
/* 48 */       g.setColor(this.foreground);
/* 49 */       g.fill(createInside());
/*    */     } else {
/* 51 */       Path2D path = new Path2D.Float(0);
/* 52 */       path.append(createOutside(), false);
/* 53 */       path.append(createInside(), false);
/* 54 */       g.fill(path);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected abstract Shape createOutside();
/*    */   
/*    */   protected abstract Shape createInside();
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatOptionPaneAbstractIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */