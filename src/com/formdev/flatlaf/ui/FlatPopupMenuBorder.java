/*    */ package com.formdev.flatlaf.ui;
/*    */ 
/*    */ import com.formdev.flatlaf.util.UIScale;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Container;
/*    */ import java.awt.Insets;
/*    */ import java.util.Map;
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
/*    */ public class FlatPopupMenuBorder
/*    */   extends FlatLineBorder
/*    */   implements FlatStylingSupport.StyleableBorder
/*    */ {
/*    */   private Color borderColor;
/*    */   
/*    */   public FlatPopupMenuBorder() {
/* 45 */     super(UIManager.getInsets("PopupMenu.borderInsets"), 
/* 46 */         UIManager.getColor("PopupMenu.borderColor"));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object applyStyleProperty(String key, Object value) {
/*    */     Object oldValue;
/* 53 */     switch (key) { case "borderInsets":
/* 54 */         return applyStyleProperty((Insets)value);
/* 55 */       case "borderColor": oldValue = getLineColor(); this.borderColor = (Color)value; return oldValue; }
/*    */     
/* 57 */     throw new FlatStylingSupport.UnknownStyleException(key);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<String, Class<?>> getStyleableInfos() {
/* 63 */     Map<String, Class<?>> infos = new FlatStylingSupport.StyleableInfosMap<>();
/* 64 */     infos.put("borderInsets", Insets.class);
/* 65 */     infos.put("borderColor", Color.class);
/* 66 */     return infos;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getStyleableValue(String key) {
/* 72 */     switch (key) { case "borderInsets":
/* 73 */         return getStyleableValue();
/* 74 */       case "borderColor": return this.borderColor; }
/*    */     
/* 76 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public Color getLineColor() {
/* 81 */     return (this.borderColor != null) ? this.borderColor : super.getLineColor();
/*    */   }
/*    */ 
/*    */   
/*    */   public Insets getBorderInsets(Component c, Insets insets) {
/* 86 */     if (c instanceof Container && ((Container)c)
/* 87 */       .getComponentCount() > 0 && ((Container)c)
/* 88 */       .getComponent(0) instanceof javax.swing.JScrollPane) {
/*    */ 
/*    */       
/* 91 */       insets.left = insets.top = insets.right = insets.bottom = UIScale.scale(1);
/* 92 */       return insets;
/*    */     } 
/*    */     
/* 95 */     return super.getBorderInsets(c, insets);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatPopupMenuBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */