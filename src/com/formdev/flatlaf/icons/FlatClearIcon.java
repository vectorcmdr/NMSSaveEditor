/*     */ package com.formdev.flatlaf.icons;
/*     */ 
/*     */ import com.formdev.flatlaf.ui.FlatStylingSupport;
/*     */ import com.formdev.flatlaf.ui.FlatStylingSupport.Styleable;
/*     */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Path2D;
/*     */ import java.util.Map;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.ButtonModel;
/*     */ import javax.swing.UIManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatClearIcon
/*     */   extends FlatAbstractIcon
/*     */ {
/*     */   @Styleable
/*  45 */   protected Color clearIconColor = UIManager.getColor("SearchField.clearIconColor"); @Styleable
/*  46 */   protected Color clearIconHoverColor = UIManager.getColor("SearchField.clearIconHoverColor"); @Styleable
/*  47 */   protected Color clearIconPressedColor = UIManager.getColor("SearchField.clearIconPressedColor");
/*     */   
/*     */   private final boolean ignoreButtonState;
/*     */   
/*     */   public FlatClearIcon() {
/*  52 */     this(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public FlatClearIcon(boolean ignoreButtonState) {
/*  57 */     super(16, 16, null);
/*  58 */     this.ignoreButtonState = ignoreButtonState;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object applyStyleProperty(String key, Object value) {
/*  63 */     return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos() {
/*  68 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(String key) {
/*  73 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintIcon(Component c, Graphics2D g) {
/*  78 */     if (!this.ignoreButtonState && c instanceof AbstractButton) {
/*  79 */       ButtonModel model = ((AbstractButton)c).getModel();
/*  80 */       if (model.isPressed() || model.isRollover()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  88 */         g.setColor(model.isPressed() ? this.clearIconPressedColor : this.clearIconHoverColor);
/*  89 */         Path2D path2D = new Path2D.Float(0);
/*  90 */         path2D.append(new Ellipse2D.Float(1.75F, 1.75F, 12.5F, 12.5F), false);
/*  91 */         path2D.append(FlatUIUtils.createPath(new double[] { 4.5D, 5.5D, 5.5D, 4.5D, 8.0D, 7.0D, 10.5D, 4.5D, 11.5D, 5.5D, 9.0D, 8.0D, 11.5D, 10.5D, 10.5D, 11.5D, 8.0D, 9.0D, 5.5D, 11.5D, 4.5D, 10.5D, 7.0D, 8.0D }, ), false);
/*  92 */         g.fill(path2D);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     g.setColor(this.clearIconColor);
/* 105 */     Path2D path = new Path2D.Float(0, 4);
/* 106 */     path.moveTo(5.0D, 5.0D);
/* 107 */     path.lineTo(11.0D, 11.0D);
/* 108 */     path.moveTo(5.0D, 11.0D);
/* 109 */     path.lineTo(11.0D, 5.0D);
/* 110 */     g.draw(path);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatClearIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */