/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatStylingSupport;
/*    */ import com.formdev.flatlaf.ui.FlatStylingSupport.Styleable;
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.geom.Path2D;
/*    */ import java.util.Map;
/*    */ import javax.swing.AbstractButton;
/*    */ import javax.swing.JMenuItem;
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
/*    */ public class FlatCheckBoxMenuItemIcon
/*    */   extends FlatAbstractIcon
/*    */ {
/*    */   @Styleable
/* 44 */   protected Color checkmarkColor = UIManager.getColor("CheckBoxMenuItem.icon.checkmarkColor"); @Styleable
/* 45 */   protected Color disabledCheckmarkColor = UIManager.getColor("CheckBoxMenuItem.icon.disabledCheckmarkColor"); @Styleable
/* 46 */   protected Color selectionForeground = UIManager.getColor("MenuItem.selectionForeground");
/*    */   
/*    */   public FlatCheckBoxMenuItemIcon() {
/* 49 */     super(15, 15, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object applyStyleProperty(String key, Object value) {
/* 54 */     return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Class<?>> getStyleableInfos() {
/* 59 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getStyleableValue(String key) {
/* 64 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paintIcon(Component c, Graphics2D g2) {
/* 69 */     boolean selected = (c instanceof AbstractButton && ((AbstractButton)c).isSelected());
/*    */ 
/*    */     
/* 72 */     if (selected) {
/* 73 */       g2.setColor(getCheckmarkColor(c));
/* 74 */       paintCheckmark(g2);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected void paintCheckmark(Graphics2D g2) {
/* 79 */     Path2D.Float path = new Path2D.Float(1, 3);
/* 80 */     path.moveTo(4.5F, 7.5F);
/* 81 */     path.lineTo(6.6F, 10.0F);
/* 82 */     path.lineTo(11.25F, 3.5F);
/*    */     
/* 84 */     g2.setStroke(new BasicStroke(1.9F, 1, 1));
/* 85 */     g2.draw(path);
/*    */   }
/*    */   
/*    */   protected Color getCheckmarkColor(Component c) {
/* 89 */     if (c instanceof JMenuItem && ((JMenuItem)c).isArmed() && !isUnderlineSelection()) {
/* 90 */       return this.selectionForeground;
/*    */     }
/* 92 */     return c.isEnabled() ? this.checkmarkColor : this.disabledCheckmarkColor;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isUnderlineSelection() {
/* 97 */     return "underline".equals(UIManager.getString("MenuItem.selectionType"));
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatCheckBoxMenuItemIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */