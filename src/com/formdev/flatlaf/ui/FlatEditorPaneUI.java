/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.HiDPIUtils;
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.UIScale;
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
/*     */ import javax.swing.plaf.basic.BasicEditorPaneUI;
/*     */ import javax.swing.text.Caret;
/*     */ import javax.swing.text.JTextComponent;
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
/*     */ public class FlatEditorPaneUI
/*     */   extends BasicEditorPaneUI
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
/*  88 */     return new FlatEditorPaneUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  93 */     super.installUI(c);
/*     */     
/*  95 */     installStyle();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/* 100 */     super.installDefaults();
/*     */     
/* 102 */     String prefix = getPropertyPrefix();
/* 103 */     this.minimumWidth = UIManager.getInt("Component.minimumWidth");
/* 104 */     this.isIntelliJTheme = UIManager.getBoolean("Component.isIntelliJTheme");
/* 105 */     this.background = UIManager.getColor(prefix + ".background");
/* 106 */     this.disabledBackground = UIManager.getColor(prefix + ".disabledBackground");
/* 107 */     this.inactiveBackground = UIManager.getColor(prefix + ".inactiveBackground");
/* 108 */     this.focusedBackground = UIManager.getColor(prefix + ".focusedBackground");
/*     */     
/* 110 */     this.defaultMargin = UIManager.getInsets(prefix + ".margin");
/*     */ 
/*     */     
/* 113 */     this.oldHonorDisplayProperties = getComponent().getClientProperty("JEditorPane.honorDisplayProperties");
/* 114 */     getComponent().putClientProperty("JEditorPane.honorDisplayProperties", Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults() {
/* 119 */     super.uninstallDefaults();
/*     */     
/* 121 */     this.background = null;
/* 122 */     this.disabledBackground = null;
/* 123 */     this.inactiveBackground = null;
/* 124 */     this.focusedBackground = null;
/*     */     
/* 126 */     this.oldDisabledBackground = null;
/* 127 */     this.oldInactiveBackground = null;
/*     */     
/* 129 */     this.oldStyleValues = null;
/*     */     
/* 131 */     getComponent().putClientProperty("JEditorPane.honorDisplayProperties", this.oldHonorDisplayProperties);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners() {
/* 136 */     super.installListeners();
/*     */ 
/*     */     
/* 139 */     this.focusListener = new FlatUIUtils.RepaintFocusListener(getComponent(), c -> (this.focusedBackground != null));
/* 140 */     getComponent().addFocusListener(this.focusListener);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners() {
/* 145 */     super.uninstallListeners();
/*     */     
/* 147 */     getComponent().removeFocusListener(this.focusListener);
/* 148 */     this.focusListener = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Caret createCaret() {
/* 153 */     return new FlatCaret(null, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void propertyChange(PropertyChangeEvent e) {
/* 159 */     String propertyName = e.getPropertyName();
/* 160 */     if ("editable".equals(propertyName) || "enabled".equals(propertyName)) {
/* 161 */       updateBackground();
/*     */     }
/* 163 */     super.propertyChange(e);
/* 164 */     propertyChange(getComponent(), e, this::installStyle);
/*     */   }
/*     */   
/*     */   static void propertyChange(JTextComponent c, PropertyChangeEvent e, Runnable installStyle) {
/* 168 */     switch (e.getPropertyName()) {
/*     */       case "JComponent.minimumWidth":
/* 170 */         c.revalidate();
/*     */         break;
/*     */       
/*     */       case "FlatLaf.style":
/*     */       case "FlatLaf.styleClass":
/* 175 */         installStyle.run();
/* 176 */         c.revalidate();
/* 177 */         c.repaint();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle() {
/*     */     try {
/* 185 */       applyStyle(FlatStylingSupport.getResolvedStyle(getComponent(), "EditorPane"));
/* 186 */     } catch (RuntimeException ex) {
/* 187 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 193 */     this.oldDisabledBackground = this.disabledBackground;
/* 194 */     this.oldInactiveBackground = this.inactiveBackground;
/*     */     
/* 196 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*     */     
/* 198 */     updateBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 203 */     return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, getComponent(), key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 209 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 215 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */   
/*     */   private void updateBackground() {
/* 219 */     FlatTextFieldUI.updateBackground(getComponent(), this.background, this.disabledBackground, this.inactiveBackground, this.oldDisabledBackground, this.oldInactiveBackground);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(JComponent c) {
/* 226 */     return applyMinimumWidth(c, super.getPreferredSize(c), this.minimumWidth, this.defaultMargin);
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize(JComponent c) {
/* 231 */     return applyMinimumWidth(c, super.getMinimumSize(c), this.minimumWidth, this.defaultMargin);
/*     */   }
/*     */ 
/*     */   
/*     */   static Dimension applyMinimumWidth(JComponent c, Dimension size, int minimumWidth, Insets defaultMargin) {
/* 236 */     if (!FlatTextFieldUI.hasDefaultMargins(c, defaultMargin)) {
/* 237 */       return size;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 243 */     minimumWidth = FlatUIUtils.minimumWidth(c, minimumWidth);
/* 244 */     size.width = Math.max(size.width, UIScale.scale(minimumWidth) - UIScale.scale(1) * 2);
/* 245 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintSafely(Graphics g) {
/* 250 */     super.paintSafely(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)g));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintBackground(Graphics g) {
/* 255 */     paintBackground(g, getComponent(), this.isIntelliJTheme, this.focusedBackground);
/*     */   }
/*     */   
/*     */   static void paintBackground(Graphics g, JTextComponent c, boolean isIntelliJTheme, Color focusedBackground) {
/* 259 */     g.setColor(FlatTextFieldUI.getBackground(c, isIntelliJTheme, focusedBackground));
/* 260 */     g.fillRect(0, 0, c.getWidth(), c.getHeight());
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatEditorPaneUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */