/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.Map;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JSeparator;
/*     */ import javax.swing.JToolBar;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicToolBarSeparatorUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatToolBarSeparatorUI
/*     */   extends BasicToolBarSeparatorUI
/*     */   implements FlatStylingSupport.StyleableUI, PropertyChangeListener
/*     */ {
/*     */   private static final int LINE_WIDTH = 1;
/*     */   @Styleable
/*     */   protected int separatorWidth;
/*     */   @Styleable
/*     */   protected Color separatorColor;
/*     */   private final boolean shared;
/*     */   private boolean defaults_initialized = false;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  64 */     return FlatUIUtils.canUseSharedUI(c) ? 
/*  65 */       FlatUIUtils.createSharedUI(FlatToolBarSeparatorUI.class, () -> new FlatToolBarSeparatorUI(true)) : 
/*  66 */       new FlatToolBarSeparatorUI(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected FlatToolBarSeparatorUI(boolean shared) {
/*  71 */     this.shared = shared;
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  76 */     super.installUI(c);
/*     */     
/*  78 */     installStyle((JSeparator)c);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults(JSeparator c) {
/*  83 */     super.installDefaults(c);
/*     */     
/*  85 */     if (!this.defaults_initialized) {
/*  86 */       this.separatorWidth = UIManager.getInt("ToolBar.separatorWidth");
/*  87 */       this.separatorColor = UIManager.getColor("ToolBar.separatorColor");
/*     */       
/*  89 */       this.defaults_initialized = true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  94 */     c.setAlignmentX(0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults(JSeparator s) {
/*  99 */     super.uninstallDefaults(s);
/*     */     
/* 101 */     this.defaults_initialized = false;
/* 102 */     this.oldStyleValues = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners(JSeparator s) {
/* 107 */     super.installListeners(s);
/*     */     
/* 109 */     s.addPropertyChangeListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners(JSeparator s) {
/* 114 */     super.uninstallListeners(s);
/*     */     
/* 116 */     s.removePropertyChangeListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void propertyChange(PropertyChangeEvent e) {
/*     */     JSeparator s;
/* 122 */     switch (e.getPropertyName()) {
/*     */       case "FlatLaf.style":
/*     */       case "FlatLaf.styleClass":
/* 125 */         s = (JSeparator)e.getSource();
/* 126 */         if (this.shared && FlatStylingSupport.hasStyleProperty(s)) {
/*     */ 
/*     */           
/* 129 */           s.updateUI();
/*     */         } else {
/* 131 */           installStyle(s);
/* 132 */         }  s.revalidate();
/* 133 */         s.repaint();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle(JSeparator s) {
/*     */     try {
/* 141 */       applyStyle(FlatStylingSupport.getResolvedStyle(s, "ToolBarSeparator"));
/* 142 */     } catch (RuntimeException ex) {
/* 143 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 149 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 154 */     return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 160 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 166 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(JComponent c) {
/* 171 */     Dimension size = ((JToolBar.Separator)c).getSeparatorSize();
/*     */     
/* 173 */     if (size != null) {
/* 174 */       return UIScale.scale(size);
/*     */     }
/*     */     
/* 177 */     int sepWidth = UIScale.scale((this.separatorWidth - 1) / 2) * 2 + UIScale.scale(1);
/*     */     
/* 179 */     boolean vertical = isVertical(c);
/* 180 */     return new Dimension(vertical ? sepWidth : 0, vertical ? 0 : sepWidth);
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize(JComponent c) {
/* 185 */     Dimension size = getPreferredSize(c);
/* 186 */     if (isVertical(c)) {
/* 187 */       return new Dimension(size.width, 32767);
/*     */     }
/* 189 */     return new Dimension(32767, size.height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 194 */     int width = c.getWidth();
/* 195 */     int height = c.getHeight();
/* 196 */     float lineWidth = UIScale.scale(1.0F);
/* 197 */     float offset = UIScale.scale(2.0F);
/*     */     
/* 199 */     Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
/* 200 */     g.setColor(this.separatorColor);
/*     */     
/* 202 */     if (isVertical(c)) {
/* 203 */       ((Graphics2D)g).fill(new Rectangle2D.Float(Math.round((width - lineWidth) / 2.0F), offset, lineWidth, height - offset * 2.0F));
/*     */     } else {
/* 205 */       ((Graphics2D)g).fill(new Rectangle2D.Float(offset, Math.round((height - lineWidth) / 2.0F), width - offset * 2.0F, lineWidth));
/*     */     } 
/* 207 */     FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
/*     */   }
/*     */   
/*     */   private boolean isVertical(JComponent c) {
/* 211 */     return (((JToolBar.Separator)c).getOrientation() == 1);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatToolBarSeparatorUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */