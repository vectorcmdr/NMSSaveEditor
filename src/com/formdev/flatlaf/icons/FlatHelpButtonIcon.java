/*     */ package com.formdev.flatlaf.icons;
/*     */ 
/*     */ import com.formdev.flatlaf.ui.FlatButtonUI;
/*     */ import com.formdev.flatlaf.ui.FlatStylingSupport;
/*     */ import com.formdev.flatlaf.ui.FlatStylingSupport.Styleable;
/*     */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.Ellipse2D;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatHelpButtonIcon
/*     */   extends FlatAbstractIcon
/*     */ {
/*     */   @Styleable
/*  58 */   protected int focusWidth = UIManager.getInt("Component.focusWidth"); @Styleable
/*  59 */   protected Color focusColor = UIManager.getColor("Component.focusColor"); @Styleable
/*  60 */   protected float innerFocusWidth = FlatUIUtils.getUIFloat("HelpButton.innerFocusWidth", FlatUIUtils.getUIFloat("Component.innerFocusWidth", 0.0F)); @Styleable
/*  61 */   protected int borderWidth = FlatUIUtils.getUIInt("HelpButton.borderWidth", 1);
/*     */   @Styleable
/*  63 */   protected Color borderColor = UIManager.getColor("HelpButton.borderColor"); @Styleable
/*  64 */   protected Color disabledBorderColor = UIManager.getColor("HelpButton.disabledBorderColor"); @Styleable
/*  65 */   protected Color focusedBorderColor = UIManager.getColor("HelpButton.focusedBorderColor"); @Styleable
/*  66 */   protected Color hoverBorderColor = UIManager.getColor("HelpButton.hoverBorderColor"); @Styleable
/*  67 */   protected Color background = UIManager.getColor("HelpButton.background"); @Styleable
/*  68 */   protected Color disabledBackground = UIManager.getColor("HelpButton.disabledBackground"); @Styleable
/*  69 */   protected Color focusedBackground = UIManager.getColor("HelpButton.focusedBackground"); @Styleable
/*  70 */   protected Color hoverBackground = UIManager.getColor("HelpButton.hoverBackground"); @Styleable
/*  71 */   protected Color pressedBackground = UIManager.getColor("HelpButton.pressedBackground"); @Styleable
/*  72 */   protected Color questionMarkColor = UIManager.getColor("HelpButton.questionMarkColor"); @Styleable
/*  73 */   protected Color disabledQuestionMarkColor = UIManager.getColor("HelpButton.disabledQuestionMarkColor");
/*     */   
/*     */   public FlatHelpButtonIcon() {
/*  76 */     super(0, 0, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object applyStyleProperty(String key, Object value) {
/*  81 */     return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos() {
/*  86 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(String key) {
/*  91 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
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
/*     */   protected void paintIcon(Component c, Graphics2D g2) {
/* 107 */     boolean enabled = (c == null || c.isEnabled());
/* 108 */     boolean focused = (c != null && FlatUIUtils.isPermanentFocusOwner(c));
/*     */     
/* 110 */     float xy = 0.5F;
/* 111 */     float wh = (iconSize() - 1);
/*     */ 
/*     */     
/* 114 */     if (focused && FlatButtonUI.isFocusPainted(c)) {
/* 115 */       g2.setColor(this.focusColor);
/* 116 */       g2.fill(new Ellipse2D.Float(xy, xy, wh, wh));
/*     */     } 
/*     */     
/* 119 */     xy += this.focusWidth;
/* 120 */     wh -= (this.focusWidth * 2);
/*     */ 
/*     */     
/* 123 */     g2.setColor(FlatButtonUI.buttonStateColor(c, this.borderColor, this.disabledBorderColor, this.focusedBorderColor, this.hoverBorderColor, null));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     g2.fill(new Ellipse2D.Float(xy, xy, wh, wh));
/*     */     
/* 131 */     xy += this.borderWidth;
/* 132 */     wh -= (this.borderWidth * 2);
/*     */ 
/*     */     
/* 135 */     if (this.innerFocusWidth > 0.0F && focused && FlatButtonUI.isFocusPainted(c)) {
/* 136 */       g2.setColor(this.focusColor);
/* 137 */       g2.fill(new Ellipse2D.Float(xy, xy, wh, wh));
/*     */       
/* 139 */       xy += this.innerFocusWidth;
/* 140 */       wh -= this.innerFocusWidth * 2.0F;
/*     */     } 
/*     */ 
/*     */     
/* 144 */     g2.setColor(FlatUIUtils.deriveColor(FlatButtonUI.buttonStateColor(c, this.background, this.disabledBackground, this.focusedBackground, this.hoverBackground, this.pressedBackground), this.background));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 150 */     g2.fill(new Ellipse2D.Float(xy, xy, wh, wh));
/*     */ 
/*     */     
/* 153 */     Path2D q = new Path2D.Float(1, 10);
/* 154 */     q.moveTo(8.0D, 8.5D);
/* 155 */     q.curveTo(8.25D, 7.0D, 9.66585007D, 6.0D, 11.0D, 6.0D);
/* 156 */     q.curveTo(12.5D, 6.0D, 14.0D, 7.0D, 14.0D, 8.5D);
/* 157 */     q.curveTo(14.0D, 10.5D, 11.0D, 11.0D, 11.0D, 13.0D);
/*     */     
/* 159 */     g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
/* 160 */     g2.setStroke(new BasicStroke(2.0F, 1, 1));
/*     */     
/* 162 */     g2.translate(this.focusWidth, this.focusWidth);
/* 163 */     g2.setColor(enabled ? this.questionMarkColor : this.disabledQuestionMarkColor);
/* 164 */     g2.draw(q);
/* 165 */     g2.fill(new Ellipse2D.Float(9.8F, 14.8F, 2.4F, 2.4F));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIconWidth() {
/* 170 */     return UIScale.scale(iconSize());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getIconHeight() {
/* 175 */     return UIScale.scale(iconSize());
/*     */   }
/*     */   
/*     */   private int iconSize() {
/* 179 */     return 22 + this.focusWidth * 2;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatHelpButtonIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */