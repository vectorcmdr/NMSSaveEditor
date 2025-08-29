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
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicMenuItemUI;
/*     */ import javax.swing.plaf.basic.BasicRadioButtonMenuItemUI;
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
/*     */ public class FlatRadioButtonMenuItemUI
/*     */   extends BasicRadioButtonMenuItemUI
/*     */   implements FlatStylingSupport.StyleableUI, FlatStylingSupport.StyleableLookupProvider
/*     */ {
/*     */   private FlatMenuItemRenderer renderer;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  77 */     return new FlatRadioButtonMenuItemUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  82 */     super.installUI(c);
/*     */     
/*  84 */     installStyle();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/*  89 */     super.installDefaults();
/*     */     
/*  91 */     LookAndFeel.installProperty(this.menuItem, "iconTextGap", Integer.valueOf(FlatUIUtils.getUIInt("MenuItem.iconTextGap", 4)));
/*     */     
/*  93 */     this.renderer = createRenderer();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults() {
/*  98 */     super.uninstallDefaults();
/*     */     
/* 100 */     FlatMenuItemRenderer.clearClientProperties(this.menuItem.getParent());
/* 101 */     this.renderer = null;
/* 102 */     this.oldStyleValues = null;
/*     */   }
/*     */   
/*     */   protected FlatMenuItemRenderer createRenderer() {
/* 106 */     return new FlatMenuItemRenderer(this.menuItem, this.checkIcon, this.arrowIcon, this.acceleratorFont, this.acceleratorDelimiter);
/*     */   }
/*     */ 
/*     */   
/*     */   protected PropertyChangeListener createPropertyChangeListener(JComponent c) {
/* 111 */     return FlatStylingSupport.createPropertyChangeListener(c, this::installStyle, super.createPropertyChangeListener(c));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle() {
/*     */     try {
/* 117 */       applyStyle(FlatStylingSupport.getResolvedStyle(this.menuItem, "RadioButtonMenuItem"));
/* 118 */     } catch (RuntimeException ex) {
/* 119 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 125 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 130 */     return FlatMenuItemUI.applyStyleProperty(this.menuItem, this, this.renderer, key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 136 */     return FlatMenuItemUI.getStyleableInfos(this, this.renderer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 142 */     return FlatMenuItemUI.getStyleableValue(this, this.renderer, key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MethodHandles.Lookup getLookupForStyling() {
/* 150 */     return MethodHandles.lookup();
/*     */   }
/*     */ 
/*     */   
/*     */   protected Dimension getPreferredMenuItemSize(JComponent c, Icon checkIcon, Icon arrowIcon, int defaultTextIconGap) {
/* 155 */     return this.renderer.getPreferredMenuItemSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 160 */     this.renderer.paintMenuItem(g, this.selectionBackground, this.selectionForeground, this.disabledForeground, this.acceleratorForeground, this.acceleratorSelectionForeground);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatRadioButtonMenuItemUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */