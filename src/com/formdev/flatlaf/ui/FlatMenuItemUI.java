/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.lang.invoke.MethodHandles;
/*     */ import java.util.Map;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicMenuItemUI;
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
/*     */ 
/*     */ 
/*     */ @StyleableFields({@StyleableField(cls = BasicMenuItemUI.class, key = "selectionBackground"), @StyleableField(cls = BasicMenuItemUI.class, key = "selectionForeground"), @StyleableField(cls = BasicMenuItemUI.class, key = "disabledForeground"), @StyleableField(cls = BasicMenuItemUI.class, key = "acceleratorForeground"), @StyleableField(cls = BasicMenuItemUI.class, key = "acceleratorSelectionForeground")})
/*     */ public class FlatMenuItemUI
/*     */   extends BasicMenuItemUI
/*     */   implements FlatStylingSupport.StyleableUI, FlatStylingSupport.StyleableLookupProvider
/*     */ {
/*     */   private FlatMenuItemRenderer renderer;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  78 */     return new FlatMenuItemUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  83 */     super.installUI(c);
/*     */     
/*  85 */     installStyle();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/*  90 */     super.installDefaults();
/*     */     
/*  92 */     LookAndFeel.installProperty(this.menuItem, "iconTextGap", Integer.valueOf(FlatUIUtils.getUIInt("MenuItem.iconTextGap", 4)));
/*     */     
/*  94 */     this.renderer = createRenderer();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults() {
/*  99 */     super.uninstallDefaults();
/*     */     
/* 101 */     FlatMenuItemRenderer.clearClientProperties(this.menuItem.getParent());
/* 102 */     this.renderer = null;
/* 103 */     this.oldStyleValues = null;
/*     */   }
/*     */   
/*     */   protected FlatMenuItemRenderer createRenderer() {
/* 107 */     return new FlatMenuItemRenderer(this.menuItem, this.checkIcon, this.arrowIcon, this.acceleratorFont, this.acceleratorDelimiter);
/*     */   }
/*     */ 
/*     */   
/*     */   protected PropertyChangeListener createPropertyChangeListener(JComponent c) {
/* 112 */     return FlatStylingSupport.createPropertyChangeListener(c, this::installStyle, super.createPropertyChangeListener(c));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle() {
/*     */     try {
/* 118 */       applyStyle(FlatStylingSupport.getResolvedStyle(this.menuItem, "MenuItem"));
/* 119 */     } catch (RuntimeException ex) {
/* 120 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 126 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 131 */     return applyStyleProperty(this.menuItem, this, this.renderer, key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static Object applyStyleProperty(JMenuItem menuItem, BasicMenuItemUI ui, FlatMenuItemRenderer renderer, String key, Object value) {
/*     */     try {
/* 138 */       return renderer.applyStyleProperty(key, value);
/* 139 */     } catch (UnknownStyleException unknownStyleException) {
/*     */ 
/*     */ 
/*     */       
/* 143 */       return FlatStylingSupport.applyToAnnotatedObjectOrComponent(ui, menuItem, key, value);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 149 */     return getStyleableInfos(this, this.renderer);
/*     */   }
/*     */   
/*     */   static Map<String, Class<?>> getStyleableInfos(BasicMenuItemUI ui, FlatMenuItemRenderer renderer) {
/* 153 */     Map<String, Class<?>> infos = FlatStylingSupport.getAnnotatedStyleableInfos(ui);
/* 154 */     infos.putAll(renderer.getStyleableInfos());
/* 155 */     return infos;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 161 */     return getStyleableValue(this, this.renderer, key);
/*     */   }
/*     */   
/*     */   static Object getStyleableValue(BasicMenuItemUI ui, FlatMenuItemRenderer renderer, String key) {
/* 165 */     Object value = renderer.getStyleableValue(key);
/* 166 */     if (value == null)
/* 167 */       value = FlatStylingSupport.getAnnotatedStyleableValue(ui, key); 
/* 168 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MethodHandles.Lookup getLookupForStyling() {
/* 176 */     return MethodHandles.lookup();
/*     */   }
/*     */ 
/*     */   
/*     */   protected Dimension getPreferredMenuItemSize(JComponent c, Icon checkIcon, Icon arrowIcon, int defaultTextIconGap) {
/* 181 */     return this.renderer.getPreferredMenuItemSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 186 */     this.renderer.paintMenuItem(g, this.selectionBackground, this.selectionForeground, this.disabledForeground, this.acceleratorForeground, this.acceleratorSelectionForeground);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatMenuItemUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */