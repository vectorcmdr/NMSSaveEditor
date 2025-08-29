/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.HiDPIUtils;
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.util.Map;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicTextPaneUI;
/*     */ import javax.swing.text.Caret;
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
/*     */ public class FlatTextPaneUI
/*     */   extends BasicTextPaneUI
/*     */   implements FlatStylingSupport.StyleableUI
/*     */ {
/*     */   @Styleable
/*     */   protected int minimumWidth;
/*     */   protected boolean isIntelliJTheme;
/*     */   private Color background;
/*     */   @Styleable
/*     */   protected Color disabledBackground;
/*     */   @Styleable
/*     */   protected Color inactiveBackground;
/*     */   @Styleable
/*     */   protected Color focusedBackground;
/*     */   private Color oldDisabledBackground;
/*     */   private Color oldInactiveBackground;
/*     */   private Insets defaultMargin;
/*     */   private Object oldHonorDisplayProperties;
/*     */   private FocusListener focusListener;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  85 */     return new FlatTextPaneUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  90 */     super.installUI(c);
/*     */     
/*  92 */     installStyle();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/*  97 */     super.installDefaults();
/*     */     
/*  99 */     String prefix = getPropertyPrefix();
/* 100 */     this.minimumWidth = UIManager.getInt("Component.minimumWidth");
/* 101 */     this.isIntelliJTheme = UIManager.getBoolean("Component.isIntelliJTheme");
/* 102 */     this.background = UIManager.getColor(prefix + ".background");
/* 103 */     this.disabledBackground = UIManager.getColor(prefix + ".disabledBackground");
/* 104 */     this.inactiveBackground = UIManager.getColor(prefix + ".inactiveBackground");
/* 105 */     this.focusedBackground = UIManager.getColor(prefix + ".focusedBackground");
/*     */     
/* 107 */     this.defaultMargin = UIManager.getInsets(prefix + ".margin");
/*     */ 
/*     */     
/* 110 */     this.oldHonorDisplayProperties = getComponent().getClientProperty("JEditorPane.honorDisplayProperties");
/* 111 */     getComponent().putClientProperty("JEditorPane.honorDisplayProperties", Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults() {
/* 116 */     super.uninstallDefaults();
/*     */     
/* 118 */     this.background = null;
/* 119 */     this.disabledBackground = null;
/* 120 */     this.inactiveBackground = null;
/* 121 */     this.focusedBackground = null;
/*     */     
/* 123 */     this.oldDisabledBackground = null;
/* 124 */     this.oldInactiveBackground = null;
/*     */     
/* 126 */     this.oldStyleValues = null;
/*     */     
/* 128 */     getComponent().putClientProperty("JEditorPane.honorDisplayProperties", this.oldHonorDisplayProperties);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners() {
/* 133 */     super.installListeners();
/*     */ 
/*     */     
/* 136 */     this.focusListener = new FlatUIUtils.RepaintFocusListener(getComponent(), c -> (this.focusedBackground != null));
/* 137 */     getComponent().addFocusListener(this.focusListener);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners() {
/* 142 */     super.uninstallListeners();
/*     */     
/* 144 */     getComponent().removeFocusListener(this.focusListener);
/* 145 */     this.focusListener = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Caret createCaret() {
/* 150 */     return new FlatCaret(null, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void propertyChange(PropertyChangeEvent e) {
/* 156 */     String propertyName = e.getPropertyName();
/* 157 */     if ("editable".equals(propertyName) || "enabled".equals(propertyName)) {
/* 158 */       updateBackground();
/*     */     }
/* 160 */     super.propertyChange(e);
/* 161 */     FlatEditorPaneUI.propertyChange(getComponent(), e, this::installStyle);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle() {
/*     */     try {
/* 167 */       applyStyle(FlatStylingSupport.getResolvedStyle(getComponent(), "TextPane"));
/* 168 */     } catch (RuntimeException ex) {
/* 169 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 175 */     this.oldDisabledBackground = this.disabledBackground;
/* 176 */     this.oldInactiveBackground = this.inactiveBackground;
/*     */     
/* 178 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*     */     
/* 180 */     updateBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 185 */     return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, getComponent(), key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 191 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 197 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */   
/*     */   private void updateBackground() {
/* 201 */     FlatTextFieldUI.updateBackground(getComponent(), this.background, this.disabledBackground, this.inactiveBackground, this.oldDisabledBackground, this.oldInactiveBackground);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(JComponent c) {
/* 208 */     return FlatEditorPaneUI.applyMinimumWidth(c, super.getPreferredSize(c), this.minimumWidth, this.defaultMargin);
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize(JComponent c) {
/* 213 */     return FlatEditorPaneUI.applyMinimumWidth(c, super.getMinimumSize(c), this.minimumWidth, this.defaultMargin);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintSafely(Graphics g) {
/* 218 */     super.paintSafely(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)g));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintBackground(Graphics g) {
/* 223 */     FlatEditorPaneUI.paintBackground(g, getComponent(), this.isIntelliJTheme, this.focusedBackground);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatTextPaneUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */