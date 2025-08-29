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
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicTextAreaUI;
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
/*     */ public class FlatTextAreaUI
/*     */   extends BasicTextAreaUI
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
/*     */   private FocusListener focusListener;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  84 */     return new FlatTextAreaUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  89 */     if (FlatUIUtils.needsLightAWTPeer(c)) {
/*  90 */       FlatUIUtils.runWithLightAWTPeerUIDefaults(() -> installUIImpl(c));
/*     */     } else {
/*  92 */       installUIImpl(c);
/*     */     } 
/*     */   }
/*     */   private void installUIImpl(JComponent c) {
/*  96 */     super.installUI(c);
/*     */     
/*  98 */     installStyle();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/* 103 */     super.installDefaults();
/*     */     
/* 105 */     this.minimumWidth = UIManager.getInt("Component.minimumWidth");
/* 106 */     this.isIntelliJTheme = UIManager.getBoolean("Component.isIntelliJTheme");
/* 107 */     this.background = UIManager.getColor("TextArea.background");
/* 108 */     this.disabledBackground = UIManager.getColor("TextArea.disabledBackground");
/* 109 */     this.inactiveBackground = UIManager.getColor("TextArea.inactiveBackground");
/* 110 */     this.focusedBackground = UIManager.getColor("TextArea.focusedBackground");
/*     */     
/* 112 */     this.defaultMargin = UIManager.getInsets("TextArea.margin");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults() {
/* 117 */     super.uninstallDefaults();
/*     */     
/* 119 */     this.background = null;
/* 120 */     this.disabledBackground = null;
/* 121 */     this.inactiveBackground = null;
/* 122 */     this.focusedBackground = null;
/*     */     
/* 124 */     this.oldDisabledBackground = null;
/* 125 */     this.oldInactiveBackground = null;
/*     */     
/* 127 */     this.oldStyleValues = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners() {
/* 132 */     super.installListeners();
/*     */ 
/*     */     
/* 135 */     this.focusListener = new FlatUIUtils.RepaintFocusListener(getComponent(), c -> (this.focusedBackground != null));
/* 136 */     getComponent().addFocusListener(this.focusListener);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners() {
/* 141 */     super.uninstallListeners();
/*     */     
/* 143 */     getComponent().removeFocusListener(this.focusListener);
/* 144 */     this.focusListener = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Caret createCaret() {
/* 149 */     return new FlatCaret(null, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void propertyChange(PropertyChangeEvent e) {
/* 155 */     String propertyName = e.getPropertyName();
/* 156 */     if ("editable".equals(propertyName) || "enabled".equals(propertyName)) {
/* 157 */       updateBackground();
/*     */     }
/* 159 */     super.propertyChange(e);
/* 160 */     FlatEditorPaneUI.propertyChange(getComponent(), e, this::installStyle);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle() {
/*     */     try {
/* 166 */       applyStyle(FlatStylingSupport.getResolvedStyle(getComponent(), "TextArea"));
/* 167 */     } catch (RuntimeException ex) {
/* 168 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 174 */     this.oldDisabledBackground = this.disabledBackground;
/* 175 */     this.oldInactiveBackground = this.inactiveBackground;
/*     */     
/* 177 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*     */     
/* 179 */     updateBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 184 */     return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, getComponent(), key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 190 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 196 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */   
/*     */   private void updateBackground() {
/* 200 */     FlatTextFieldUI.updateBackground(getComponent(), this.background, this.disabledBackground, this.inactiveBackground, this.oldDisabledBackground, this.oldInactiveBackground);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(JComponent c) {
/* 207 */     return applyMinimumWidth(c, super.getPreferredSize(c));
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize(JComponent c) {
/* 212 */     return applyMinimumWidth(c, super.getMinimumSize(c));
/*     */   }
/*     */ 
/*     */   
/*     */   private Dimension applyMinimumWidth(JComponent c, Dimension size) {
/* 217 */     if (c instanceof JTextArea && ((JTextArea)c).getColumns() > 0) {
/* 218 */       return size;
/*     */     }
/* 220 */     return FlatEditorPaneUI.applyMinimumWidth(c, size, this.minimumWidth, this.defaultMargin);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintSafely(Graphics g) {
/* 225 */     super.paintSafely(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)g));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintBackground(Graphics g) {
/* 230 */     FlatEditorPaneUI.paintBackground(g, getComponent(), this.isIntelliJTheme, this.focusedBackground);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatTextAreaUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */