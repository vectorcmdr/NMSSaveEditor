/*     */ package com.formdev.flatlaf.icons;
/*     */ 
/*     */ import com.formdev.flatlaf.ui.FlatButtonUI;
/*     */ import com.formdev.flatlaf.ui.FlatStylingSupport;
/*     */ import com.formdev.flatlaf.ui.FlatStylingSupport.Styleable;
/*     */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Path2D;
/*     */ import java.util.Map;
/*     */ import javax.swing.UIManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatTabbedPaneCloseIcon
/*     */   extends FlatAbstractIcon
/*     */ {
/*     */   @Styleable
/*  52 */   protected Dimension closeSize = UIManager.getDimension("TabbedPane.closeSize"); @Styleable
/*  53 */   protected int closeArc = UIManager.getInt("TabbedPane.closeArc"); @Styleable
/*  54 */   protected float closeCrossPlainSize = FlatUIUtils.getUIFloat("TabbedPane.closeCrossPlainSize", 7.5F); @Styleable
/*  55 */   protected float closeCrossFilledSize = FlatUIUtils.getUIFloat("TabbedPane.closeCrossFilledSize", this.closeCrossPlainSize); @Styleable
/*  56 */   protected float closeCrossLineWidth = FlatUIUtils.getUIFloat("TabbedPane.closeCrossLineWidth", 1.0F); @Styleable
/*  57 */   protected Color closeBackground = UIManager.getColor("TabbedPane.closeBackground"); @Styleable
/*  58 */   protected Color closeForeground = UIManager.getColor("TabbedPane.closeForeground"); @Styleable
/*  59 */   protected Color closeHoverBackground = UIManager.getColor("TabbedPane.closeHoverBackground"); @Styleable
/*  60 */   protected Color closeHoverForeground = UIManager.getColor("TabbedPane.closeHoverForeground"); @Styleable
/*  61 */   protected Color closePressedBackground = UIManager.getColor("TabbedPane.closePressedBackground"); @Styleable
/*  62 */   protected Color closePressedForeground = UIManager.getColor("TabbedPane.closePressedForeground");
/*     */   
/*     */   public FlatTabbedPaneCloseIcon() {
/*  65 */     super(16, 16, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object applyStyleProperty(String key, Object value) {
/*  70 */     return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos() {
/*  75 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(String key) {
/*  80 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintIcon(Component c, Graphics2D g) {
/*  86 */     Color bg = FlatButtonUI.buttonStateColor(c, this.closeBackground, null, null, this.closeHoverBackground, this.closePressedBackground);
/*  87 */     if (bg != null) {
/*  88 */       g.setColor(FlatUIUtils.deriveColor(bg, c.getBackground()));
/*  89 */       g.fillRoundRect((this.width - this.closeSize.width) / 2, (this.height - this.closeSize.height) / 2, this.closeSize.width, this.closeSize.height, this.closeArc, this.closeArc);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  94 */     Color fg = FlatButtonUI.buttonStateColor(c, this.closeForeground, null, null, this.closeHoverForeground, this.closePressedForeground);
/*  95 */     g.setColor(FlatUIUtils.deriveColor(fg, c.getForeground()));
/*     */     
/*  97 */     float mx = (this.width / 2);
/*  98 */     float my = (this.height / 2);
/*  99 */     float r = ((bg != null) ? this.closeCrossFilledSize : this.closeCrossPlainSize) / 2.0F;
/*     */ 
/*     */     
/* 102 */     Path2D path = new Path2D.Float(0, 4);
/* 103 */     path.moveTo((mx - r), (my - r));
/* 104 */     path.lineTo((mx + r), (my + r));
/* 105 */     path.moveTo((mx - r), (my + r));
/* 106 */     path.lineTo((mx + r), (my - r));
/* 107 */     g.setStroke(new BasicStroke(this.closeCrossLineWidth));
/* 108 */     g.draw(path);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatTabbedPaneCloseIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */