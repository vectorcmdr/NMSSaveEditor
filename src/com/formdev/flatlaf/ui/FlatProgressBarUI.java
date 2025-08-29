/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.FlatClientProperties;
/*     */ import com.formdev.flatlaf.util.HiDPIUtils;
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.RoundRectangle2D;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.Map;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicProgressBarUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatProgressBarUI
/*     */   extends BasicProgressBarUI
/*     */   implements FlatStylingSupport.StyleableUI
/*     */ {
/*     */   @Styleable
/*     */   protected int arc;
/*     */   @Styleable
/*     */   protected Dimension horizontalSize;
/*     */   @Styleable
/*     */   protected Dimension verticalSize;
/*     */   @Styleable
/*     */   protected boolean largeHeight;
/*     */   @Styleable
/*     */   protected boolean square;
/*     */   private PropertyChangeListener propertyChangeListener;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  79 */     return new FlatProgressBarUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  84 */     super.installUI(c);
/*     */     
/*  86 */     installStyle();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/*  91 */     super.installDefaults();
/*     */     
/*  93 */     LookAndFeel.installProperty(this.progressBar, "opaque", Boolean.valueOf(false));
/*     */     
/*  95 */     this.arc = UIManager.getInt("ProgressBar.arc");
/*  96 */     this.horizontalSize = UIManager.getDimension("ProgressBar.horizontalSize");
/*  97 */     this.verticalSize = UIManager.getDimension("ProgressBar.verticalSize");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults() {
/* 102 */     super.uninstallDefaults();
/*     */     
/* 104 */     this.oldStyleValues = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners() {
/* 109 */     super.installListeners();
/*     */     
/* 111 */     this.propertyChangeListener = (e -> {
/*     */         switch (e.getPropertyName()) {
/*     */           case "JProgressBar.largeHeight":
/*     */           case "JProgressBar.square":
/*     */             this.progressBar.revalidate();
/*     */             this.progressBar.repaint();
/*     */             break;
/*     */           
/*     */           case "FlatLaf.style":
/*     */           case "FlatLaf.styleClass":
/*     */             installStyle();
/*     */             this.progressBar.revalidate();
/*     */             this.progressBar.repaint();
/*     */             break;
/*     */         } 
/*     */       });
/* 127 */     this.progressBar.addPropertyChangeListener(this.propertyChangeListener);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners() {
/* 132 */     super.uninstallListeners();
/*     */     
/* 134 */     this.progressBar.removePropertyChangeListener(this.propertyChangeListener);
/* 135 */     this.propertyChangeListener = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle() {
/*     */     try {
/* 141 */       applyStyle(FlatStylingSupport.getResolvedStyle(this.progressBar, "ProgressBar"));
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
/* 154 */     return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, this.progressBar, key, value);
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
/* 171 */     Dimension size = super.getPreferredSize(c);
/*     */     
/* 173 */     if (this.progressBar.isStringPainted() || FlatClientProperties.clientPropertyBoolean(c, "JProgressBar.largeHeight", this.largeHeight)) {
/*     */       
/* 175 */       Insets insets = this.progressBar.getInsets();
/* 176 */       FontMetrics fm = this.progressBar.getFontMetrics(this.progressBar.getFont());
/* 177 */       if (this.progressBar.getOrientation() == 0) {
/* 178 */         size.height = Math.max(fm.getHeight() + insets.top + insets.bottom, (getPreferredInnerHorizontal()).height);
/*     */       } else {
/* 180 */         size.width = Math.max(fm.getHeight() + insets.left + insets.right, (getPreferredInnerVertical()).width);
/*     */       } 
/*     */     } 
/* 183 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Dimension getPreferredInnerHorizontal() {
/* 188 */     return UIScale.scale(this.horizontalSize);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Dimension getPreferredInnerVertical() {
/* 193 */     return UIScale.scale(this.verticalSize);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(Graphics g, JComponent c) {
/* 198 */     if (c.isOpaque()) {
/* 199 */       FlatUIUtils.paintParentBackground(g, c);
/*     */     }
/* 201 */     paint(g, c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 206 */     Insets insets = this.progressBar.getInsets();
/* 207 */     int x = insets.left;
/* 208 */     int y = insets.top;
/* 209 */     int width = this.progressBar.getWidth() - insets.right + insets.left;
/* 210 */     int height = this.progressBar.getHeight() - insets.top + insets.bottom;
/*     */     
/* 212 */     if (width <= 0 || height <= 0) {
/*     */       return;
/*     */     }
/* 215 */     boolean horizontal = (this.progressBar.getOrientation() == 0);
/*     */ 
/*     */     
/* 218 */     int arc = FlatClientProperties.clientPropertyBoolean(c, "JProgressBar.square", this.square) ? 0 : Math.min(UIScale.scale(this.arc), horizontal ? height : width);
/*     */     
/* 220 */     Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
/*     */ 
/*     */     
/* 223 */     RoundRectangle2D.Float trackShape = new RoundRectangle2D.Float(x, y, width, height, arc, arc);
/* 224 */     g.setColor(this.progressBar.getBackground());
/* 225 */     ((Graphics2D)g).fill(trackShape);
/*     */ 
/*     */     
/* 228 */     int amountFull = 0;
/* 229 */     if (this.progressBar.isIndeterminate()) {
/* 230 */       this.boxRect = getBox(this.boxRect);
/* 231 */       if (this.boxRect != null) {
/* 232 */         g.setColor(this.progressBar.getForeground());
/* 233 */         ((Graphics2D)g).fill(new RoundRectangle2D.Float(this.boxRect.x, this.boxRect.y, this.boxRect.width, this.boxRect.height, arc, arc));
/*     */       } 
/*     */     } else {
/*     */       
/* 237 */       amountFull = getAmountFull(insets, width, height);
/*     */       
/* 239 */       if (horizontal) {  }
/*     */       else
/*     */       {  }
/* 242 */        RoundRectangle2D.Float progressShape = new RoundRectangle2D.Float(x, (y + height - amountFull), width, amountFull, arc, arc);
/*     */       
/* 244 */       g.setColor(this.progressBar.getForeground());
/* 245 */       if (amountFull < (horizontal ? height : width)) {
/*     */         
/* 247 */         Area area = new Area(trackShape);
/* 248 */         area.intersect(new Area(progressShape));
/* 249 */         ((Graphics2D)g).fill(area);
/*     */       } else {
/* 251 */         ((Graphics2D)g).fill(progressShape);
/*     */       } 
/*     */     } 
/* 254 */     FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
/*     */     
/* 256 */     if (this.progressBar.isStringPainted()) {
/* 257 */       paintString(g, x, y, width, height, amountFull, insets);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void paintString(Graphics g, int x, int y, int width, int height, int amountFull, Insets b) {
/* 262 */     super.paintString(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)g), x, y, width, height, amountFull, b);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setAnimationIndex(int newValue) {
/* 267 */     super.setAnimationIndex(newValue);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 275 */     double systemScaleFactor = UIScale.getSystemScaleFactor(this.progressBar.getGraphicsConfiguration());
/* 276 */     if ((int)systemScaleFactor != systemScaleFactor)
/* 277 */       this.progressBar.repaint(); 
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatProgressBarUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */