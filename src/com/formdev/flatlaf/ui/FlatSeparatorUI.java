/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.Map;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JSeparator;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicSeparatorUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatSeparatorUI
/*     */   extends BasicSeparatorUI
/*     */   implements FlatStylingSupport.StyleableUI, PropertyChangeListener
/*     */ {
/*     */   @Styleable
/*     */   protected int height;
/*     */   @Styleable
/*     */   protected int stripeWidth;
/*     */   @Styleable
/*     */   protected int stripeIndent;
/*     */   private final boolean shared;
/*     */   private boolean defaults_initialized = false;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  66 */     return FlatUIUtils.canUseSharedUI(c) ? 
/*  67 */       FlatUIUtils.createSharedUI(FlatSeparatorUI.class, () -> new FlatSeparatorUI(true)) : 
/*  68 */       new FlatSeparatorUI(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected FlatSeparatorUI(boolean shared) {
/*  73 */     this.shared = shared;
/*     */   }
/*     */   
/*     */   protected String getPropertyPrefix() {
/*  77 */     return "Separator";
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  82 */     super.installUI(c);
/*     */     
/*  84 */     installStyle((JSeparator)c);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults(JSeparator s) {
/*  89 */     super.installDefaults(s);
/*     */     
/*  91 */     if (!this.defaults_initialized) {
/*  92 */       String prefix = getPropertyPrefix();
/*  93 */       this.height = UIManager.getInt(prefix + ".height");
/*  94 */       this.stripeWidth = UIManager.getInt(prefix + ".stripeWidth");
/*  95 */       this.stripeIndent = UIManager.getInt(prefix + ".stripeIndent");
/*     */       
/*  97 */       this.defaults_initialized = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults(JSeparator s) {
/* 103 */     super.uninstallDefaults(s);
/*     */     
/* 105 */     this.defaults_initialized = false;
/* 106 */     this.oldStyleValues = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners(JSeparator s) {
/* 111 */     super.installListeners(s);
/*     */     
/* 113 */     s.addPropertyChangeListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners(JSeparator s) {
/* 118 */     super.uninstallListeners(s);
/*     */     
/* 120 */     s.removePropertyChangeListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void propertyChange(PropertyChangeEvent e) {
/*     */     JSeparator s;
/* 126 */     switch (e.getPropertyName()) {
/*     */       case "FlatLaf.style":
/*     */       case "FlatLaf.styleClass":
/* 129 */         s = (JSeparator)e.getSource();
/* 130 */         if (this.shared && FlatStylingSupport.hasStyleProperty(s)) {
/*     */ 
/*     */           
/* 133 */           s.updateUI();
/*     */         } else {
/* 135 */           installStyle(s);
/* 136 */         }  s.revalidate();
/* 137 */         s.repaint();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle(JSeparator s) {
/*     */     try {
/* 145 */       applyStyle(s, FlatStylingSupport.getResolvedStyle(s, getStyleType()));
/* 146 */     } catch (RuntimeException ex) {
/* 147 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   String getStyleType() {
/* 153 */     return "Separator";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(JSeparator s, Object style) {
/* 158 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, (key, value) -> applyStyleProperty(s, key, value));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(JSeparator s, String key, Object value) {
/* 164 */     return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, s, key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 170 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 176 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 181 */     Graphics2D g2 = (Graphics2D)g.create();
/*     */     
/* 183 */     try { FlatUIUtils.setRenderingHints(g2);
/* 184 */       g2.setColor(c.getForeground());
/*     */       
/* 186 */       float width = UIScale.scale(this.stripeWidth);
/* 187 */       float indent = UIScale.scale(this.stripeIndent);
/*     */       
/* 189 */       if (((JSeparator)c).getOrientation() == 1) {
/* 190 */         g2.fill(new Rectangle2D.Float(indent, 0.0F, width, c.getHeight()));
/*     */       } else {
/* 192 */         g2.fill(new Rectangle2D.Float(0.0F, indent, c.getWidth(), width));
/*     */       }  }
/* 194 */     finally { g2.dispose(); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(JComponent c) {
/* 200 */     if (((JSeparator)c).getOrientation() == 1) {
/* 201 */       return new Dimension(UIScale.scale(this.height), 0);
/*     */     }
/* 203 */     return new Dimension(0, UIScale.scale(this.height));
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatSeparatorUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */