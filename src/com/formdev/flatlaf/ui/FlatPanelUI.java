/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.Map;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicPanelUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatPanelUI
/*     */   extends BasicPanelUI
/*     */   implements FlatStylingSupport.StyleableUI, PropertyChangeListener
/*     */ {
/*     */   @Styleable
/*  51 */   protected int arc = -1;
/*     */   
/*     */   private final boolean shared;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  57 */     return FlatUIUtils.canUseSharedUI(c) ? 
/*  58 */       FlatUIUtils.createSharedUI(FlatPanelUI.class, () -> new FlatPanelUI(true)) : 
/*  59 */       new FlatPanelUI(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected FlatPanelUI(boolean shared) {
/*  64 */     this.shared = shared;
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  69 */     super.installUI(c);
/*     */     
/*  71 */     c.addPropertyChangeListener(this);
/*     */     
/*  73 */     installStyle((JPanel)c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void uninstallUI(JComponent c) {
/*  78 */     super.uninstallUI(c);
/*     */     
/*  80 */     c.removePropertyChangeListener(this);
/*     */     
/*  82 */     this.oldStyleValues = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void propertyChange(PropertyChangeEvent e) {
/*     */     JPanel c;
/*  88 */     switch (e.getPropertyName()) {
/*     */       case "FlatLaf.style":
/*     */       case "FlatLaf.styleClass":
/*  91 */         c = (JPanel)e.getSource();
/*  92 */         if (this.shared && FlatStylingSupport.hasStyleProperty(c)) {
/*     */ 
/*     */           
/*  95 */           c.updateUI();
/*     */         } else {
/*  97 */           installStyle(c);
/*  98 */         }  c.revalidate();
/*  99 */         c.repaint();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle(JPanel c) {
/*     */     try {
/* 107 */       applyStyle(c, FlatStylingSupport.getResolvedStyle(c, "Panel"));
/* 108 */     } catch (RuntimeException ex) {
/* 109 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(JPanel c, Object style) {
/* 115 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, (key, value) -> applyStyleProperty(c, key, value));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(JPanel c, String key, Object value) {
/* 121 */     return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, c, key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 127 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 133 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(Graphics g, JComponent c) {
/* 139 */     if (c.isOpaque()) {
/* 140 */       int width = c.getWidth();
/* 141 */       int height = c.getHeight();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 146 */       int arc = (this.arc >= 0) ? this.arc : ((c.getBorder() instanceof FlatLineBorder) ? ((FlatLineBorder)c.getBorder()).getArc() : 0);
/*     */ 
/*     */       
/* 149 */       if (arc > 0) {
/* 150 */         FlatUIUtils.paintParentBackground(g, c);
/*     */       }
/* 152 */       g.setColor(c.getBackground());
/* 153 */       if (arc > 0) {
/*     */         
/* 155 */         Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
/* 156 */         FlatUIUtils.paintComponentBackground((Graphics2D)g, 0, 0, width, height, 0.0F, 
/* 157 */             UIScale.scale(arc));
/* 158 */         FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
/*     */       } else {
/* 160 */         g.fillRect(0, 0, width, height);
/*     */       } 
/*     */     } 
/* 163 */     paint(g, c);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatPanelUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */