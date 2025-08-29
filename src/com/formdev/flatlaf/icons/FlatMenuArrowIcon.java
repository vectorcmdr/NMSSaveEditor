/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatStylingSupport;
/*    */ import com.formdev.flatlaf.ui.FlatStylingSupport.Styleable;
/*    */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.geom.Path2D;
/*    */ import java.util.Map;
/*    */ import javax.swing.JMenu;
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
/*    */ public class FlatMenuArrowIcon
/*    */   extends FlatAbstractIcon
/*    */ {
/*    */   @Styleable
/* 45 */   protected String arrowType = UIManager.getString("Component.arrowType"); @Styleable
/* 46 */   protected Color arrowColor = UIManager.getColor("Menu.icon.arrowColor"); @Styleable
/* 47 */   protected Color disabledArrowColor = UIManager.getColor("Menu.icon.disabledArrowColor"); @Styleable
/* 48 */   protected Color selectionForeground = UIManager.getColor("Menu.selectionForeground");
/*    */   
/*    */   public FlatMenuArrowIcon() {
/* 51 */     super(6, 10, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object applyStyleProperty(String key, Object value) {
/* 56 */     return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Class<?>> getStyleableInfos() {
/* 61 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getStyleableValue(String key) {
/* 66 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 71 */     if (c != null && !c.getComponentOrientation().isLeftToRight()) {
/* 72 */       g.rotate(Math.toRadians(180.0D), this.width / 2.0D, this.height / 2.0D);
/*    */     }
/* 74 */     g.setColor(getArrowColor(c));
/* 75 */     if (FlatUIUtils.isChevron(this.arrowType)) {
/*    */       
/* 77 */       Path2D path = FlatUIUtils.createPath(false, new double[] { 1.0D, 1.0D, 5.0D, 5.0D, 1.0D, 9.0D });
/* 78 */       g.setStroke(new BasicStroke(1.0F));
/* 79 */       g.draw(path);
/*    */     } else {
/*    */       
/* 82 */       g.fill(FlatUIUtils.createPath(new double[] { 0.0D, 0.5D, 5.0D, 5.0D, 0.0D, 9.5D }));
/*    */     } 
/*    */   }
/*    */   
/*    */   protected Color getArrowColor(Component c) {
/* 87 */     if (c instanceof JMenu && ((JMenu)c).isSelected() && !isUnderlineSelection()) {
/* 88 */       return this.selectionForeground;
/*    */     }
/* 90 */     return (c == null || c.isEnabled()) ? this.arrowColor : this.disabledArrowColor;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isUnderlineSelection() {
/* 95 */     return "underline".equals(UIManager.getString("MenuItem.selectionType"));
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatMenuArrowIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */