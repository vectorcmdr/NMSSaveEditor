/*    */ package com.formdev.flatlaf.ui;
/*    */ 
/*    */ import com.formdev.flatlaf.util.UIScale;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Insets;
/*    */ import java.util.Map;
/*    */ import javax.swing.JMenuBar;
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
/*    */ public class FlatMenuBarBorder
/*    */   extends FlatMarginBorder
/*    */   implements FlatStylingSupport.StyleableBorder
/*    */ {
/*    */   @Styleable
/* 41 */   protected Color borderColor = UIManager.getColor("MenuBar.borderColor");
/*    */ 
/*    */ 
/*    */   
/*    */   public Object applyStyleProperty(String key, Object value) {
/* 46 */     return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Class<?>> getStyleableInfos() {
/* 51 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getStyleableValue(String key) {
/* 57 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*    */   }
/*    */ 
/*    */   
/*    */   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 62 */     if (!showBottomSeparator(c)) {
/*    */       return;
/*    */     }
/* 65 */     float lineHeight = UIScale.scale(1.0F);
/* 66 */     FlatUIUtils.paintFilledRectangle(g, this.borderColor, x, (y + height) - lineHeight, width, lineHeight);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Insets getBorderInsets(Component c, Insets insets) {
/* 72 */     Insets margin = (c instanceof JMenuBar) ? ((JMenuBar)c).getMargin() : new Insets(0, 0, 0, 0);
/*    */     
/* 74 */     insets.top = UIScale.scale(margin.top);
/* 75 */     insets.left = UIScale.scale(margin.left);
/* 76 */     insets.bottom = UIScale.scale(margin.bottom + 1);
/* 77 */     insets.right = UIScale.scale(margin.right);
/* 78 */     return insets;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean showBottomSeparator(Component c) {
/* 83 */     return !FlatMenuBarUI.useUnifiedBackground(c);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatMenuBarBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */