/*     */ package com.jgoodies.forms.builder;
/*     */ 
/*     */ import com.jgoodies.forms.layout.CellConstraints;
/*     */ import com.jgoodies.forms.layout.FormLayout;
/*     */ import java.awt.Component;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
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
/*     */ public abstract class AbstractI15dPanelBuilder
/*     */   extends PanelBuilder
/*     */ {
/*     */   private static final String DEBUG_TOOL_TIPS_ENABLED_KEY = "I15dPanelBuilder.debugToolTipsEnabled";
/*  79 */   private static boolean debugToolTipsEnabled = getDebugToolTipSystemProperty();
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
/*     */   protected AbstractI15dPanelBuilder(FormLayout layout) {
/*  92 */     super(layout);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractI15dPanelBuilder(FormLayout layout, JPanel panel) {
/* 104 */     super(layout, panel);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean getDebugToolTipSystemProperty() {
/*     */     try {
/* 112 */       String value = System.getProperty("I15dPanelBuilder.debugToolTipsEnabled");
/* 113 */       return "true".equalsIgnoreCase(value);
/* 114 */     } catch (SecurityException e) {
/* 115 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isDebugToolTipsEnabled() {
/* 126 */     return debugToolTipsEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setDebugToolTipsEnabled(boolean b) {
/* 136 */     debugToolTipsEnabled = b;
/*     */   }
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
/*     */   public final JLabel addI15dLabel(String resourceKey, CellConstraints constraints) {
/* 151 */     JLabel label = addLabel(getI15dString(resourceKey), constraints);
/* 152 */     if (isDebugToolTipsEnabled()) {
/* 153 */       label.setToolTipText(resourceKey);
/*     */     }
/* 155 */     return label;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final JLabel addI15dLabel(String resourceKey, String encodedConstraints) {
/* 167 */     return addI15dLabel(resourceKey, new CellConstraints(encodedConstraints));
/*     */   }
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
/*     */   public final JLabel addI15dLabel(String resourceKey, CellConstraints labelConstraints, Component component, CellConstraints componentConstraints) {
/* 216 */     JLabel label = addLabel(getI15dString(resourceKey), labelConstraints, component, componentConstraints);
/*     */     
/* 218 */     if (isDebugToolTipsEnabled()) {
/* 219 */       label.setToolTipText(resourceKey);
/*     */     }
/* 221 */     return label;
/*     */   }
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
/*     */   public final JLabel addI15dROLabel(String resourceKey, CellConstraints constraints) {
/* 238 */     JLabel label = addROLabel(getI15dString(resourceKey), constraints);
/* 239 */     if (isDebugToolTipsEnabled()) {
/* 240 */       label.setToolTipText(resourceKey);
/*     */     }
/* 242 */     return label;
/*     */   }
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
/*     */   public final JLabel addI15dROLabel(String resourceKey, String encodedConstraints) {
/* 257 */     return addI15dROLabel(resourceKey, new CellConstraints(encodedConstraints));
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final JLabel addI15dROLabel(String resourceKey, CellConstraints labelConstraints, Component component, CellConstraints componentConstraints) {
/* 318 */     JLabel label = addROLabel(getI15dString(resourceKey), labelConstraints, component, componentConstraints);
/*     */ 
/*     */     
/* 321 */     if (isDebugToolTipsEnabled()) {
/* 322 */       label.setToolTipText(resourceKey);
/*     */     }
/* 324 */     return label;
/*     */   }
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
/*     */   public final JComponent addI15dSeparator(String resourceKey, CellConstraints constraints) {
/* 339 */     JComponent component = addSeparator(getI15dString(resourceKey), constraints);
/* 340 */     if (isDebugToolTipsEnabled()) {
/* 341 */       component.setToolTipText(resourceKey);
/*     */     }
/* 343 */     return component;
/*     */   }
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
/*     */   public final JComponent addI15dSeparator(String resourceKey, String encodedConstraints) {
/* 356 */     return addI15dSeparator(resourceKey, new CellConstraints(encodedConstraints));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final JLabel addI15dTitle(String resourceKey, CellConstraints constraints) {
/* 368 */     JLabel label = addTitle(getI15dString(resourceKey), constraints);
/* 369 */     if (isDebugToolTipsEnabled()) {
/* 370 */       label.setToolTipText(resourceKey);
/*     */     }
/* 372 */     return label;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final JLabel addI15dTitle(String resourceKey, String encodedConstraints) {
/* 384 */     return addI15dTitle(resourceKey, new CellConstraints(encodedConstraints));
/*     */   }
/*     */   
/*     */   protected abstract String getI15dString(String paramString);
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\builder\AbstractI15dPanelBuilder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */