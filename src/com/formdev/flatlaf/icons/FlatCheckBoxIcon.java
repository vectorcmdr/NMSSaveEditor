/*     */ package com.formdev.flatlaf.icons;
/*     */ 
/*     */ import com.formdev.flatlaf.FlatClientProperties;
/*     */ import com.formdev.flatlaf.ui.FlatButtonUI;
/*     */ import com.formdev.flatlaf.ui.FlatStylingSupport;
/*     */ import com.formdev.flatlaf.ui.FlatStylingSupport.Styleable;
/*     */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Path2D;
/*     */ import java.awt.geom.RoundRectangle2D;
/*     */ import java.util.Map;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.JComponent;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatCheckBoxIcon
/*     */   extends FlatAbstractIcon
/*     */ {
/*  89 */   protected final String style = UIManager.getString(getPropertyPrefix() + "icon.style"); @Styleable
/*  90 */   protected float focusWidth = getUIFloat("CheckBox.icon.focusWidth", UIManager.getInt("Component.focusWidth"), this.style); @Styleable
/*  91 */   protected Color focusColor = FlatUIUtils.getUIColor("CheckBox.icon.focusColor", UIManager.getColor("Component.focusColor")); @Styleable
/*  92 */   protected float borderWidth = getUIFloat("CheckBox.icon.borderWidth", FlatUIUtils.getUIFloat("Component.borderWidth", 1.0F), this.style); @Styleable
/*  93 */   protected float selectedBorderWidth = getUIFloat("CheckBox.icon.selectedBorderWidth", Float.MIN_VALUE, this.style); @Styleable
/*  94 */   protected float disabledSelectedBorderWidth = getUIFloat("CheckBox.icon.disabledSelectedBorderWidth", Float.MIN_VALUE, this.style); @Styleable
/*  95 */   protected int arc = FlatUIUtils.getUIInt("CheckBox.arc", 2);
/*     */   
/*     */   @Styleable
/*  98 */   protected Color borderColor = getUIColor("CheckBox.icon.borderColor", this.style); @Styleable
/*  99 */   protected Color background = getUIColor("CheckBox.icon.background", this.style); @Styleable
/* 100 */   protected Color selectedBorderColor = getUIColor("CheckBox.icon.selectedBorderColor", this.style); @Styleable
/* 101 */   protected Color selectedBackground = getUIColor("CheckBox.icon.selectedBackground", this.style); @Styleable
/* 102 */   protected Color checkmarkColor = getUIColor("CheckBox.icon.checkmarkColor", this.style);
/*     */   
/*     */   @Styleable
/* 105 */   protected Color disabledBorderColor = getUIColor("CheckBox.icon.disabledBorderColor", this.style); @Styleable
/* 106 */   protected Color disabledBackground = getUIColor("CheckBox.icon.disabledBackground", this.style); @Styleable
/* 107 */   protected Color disabledSelectedBorderColor = getUIColor("CheckBox.icon.disabledSelectedBorderColor", this.style); @Styleable
/* 108 */   protected Color disabledSelectedBackground = getUIColor("CheckBox.icon.disabledSelectedBackground", this.style); @Styleable
/* 109 */   protected Color disabledCheckmarkColor = getUIColor("CheckBox.icon.disabledCheckmarkColor", this.style);
/*     */   
/*     */   @Styleable
/* 112 */   protected Color focusedBorderColor = getUIColor("CheckBox.icon.focusedBorderColor", this.style); @Styleable
/* 113 */   protected Color focusedBackground = getUIColor("CheckBox.icon.focusedBackground", this.style); @Styleable
/* 114 */   protected Color focusedSelectedBorderColor = getUIColor("CheckBox.icon.focusedSelectedBorderColor", this.style); @Styleable
/* 115 */   protected Color focusedSelectedBackground = getUIColor("CheckBox.icon.focusedSelectedBackground", this.style); @Styleable
/* 116 */   protected Color focusedCheckmarkColor = getUIColor("CheckBox.icon.focusedCheckmarkColor", this.style);
/*     */   
/*     */   @Styleable
/* 119 */   protected Color hoverBorderColor = getUIColor("CheckBox.icon.hoverBorderColor", this.style); @Styleable
/* 120 */   protected Color hoverBackground = getUIColor("CheckBox.icon.hoverBackground", this.style); @Styleable
/* 121 */   protected Color hoverSelectedBorderColor = getUIColor("CheckBox.icon.hoverSelectedBorderColor", this.style); @Styleable
/* 122 */   protected Color hoverSelectedBackground = getUIColor("CheckBox.icon.hoverSelectedBackground", this.style); @Styleable
/* 123 */   protected Color hoverCheckmarkColor = getUIColor("CheckBox.icon.hoverCheckmarkColor", this.style);
/*     */   
/*     */   @Styleable
/* 126 */   protected Color pressedBorderColor = getUIColor("CheckBox.icon.pressedBorderColor", this.style); @Styleable
/* 127 */   protected Color pressedBackground = getUIColor("CheckBox.icon.pressedBackground", this.style); @Styleable
/* 128 */   protected Color pressedSelectedBorderColor = getUIColor("CheckBox.icon.pressedSelectedBorderColor", this.style); @Styleable
/* 129 */   protected Color pressedSelectedBackground = getUIColor("CheckBox.icon.pressedSelectedBackground", this.style); @Styleable
/* 130 */   protected Color pressedCheckmarkColor = getUIColor("CheckBox.icon.pressedCheckmarkColor", this.style); static final int ICON_SIZE = 15;
/*     */   
/*     */   protected String getPropertyPrefix() {
/* 133 */     return "CheckBox.";
/*     */   }
/*     */   
/*     */   protected static Color getUIColor(String key, String style) {
/* 137 */     if (style != null) {
/* 138 */       Color color = UIManager.getColor(styleKey(key, style));
/* 139 */       if (color != null)
/* 140 */         return color; 
/*     */     } 
/* 142 */     return UIManager.getColor(key);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static float getUIFloat(String key, float defaultValue, String style) {
/* 147 */     if (style != null) {
/* 148 */       float value = FlatUIUtils.getUIFloat(styleKey(key, style), Float.MIN_VALUE);
/* 149 */       if (value != Float.MIN_VALUE)
/* 150 */         return value; 
/*     */     } 
/* 152 */     return FlatUIUtils.getUIFloat(key, defaultValue);
/*     */   }
/*     */   
/*     */   private static String styleKey(String key, String style) {
/* 156 */     return key.replace(".icon.", ".icon[" + style + "].");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FlatCheckBoxIcon() {
/* 162 */     super(15, 15, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object applyStyleProperty(String key, Object value) {
/* 167 */     return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos() {
/* 172 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(String key) {
/* 177 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintIcon(Component c, Graphics2D g) {
/* 182 */     boolean indeterminate = isIndeterminate(c);
/* 183 */     boolean selected = (indeterminate || isSelected(c));
/* 184 */     boolean isFocused = FlatUIUtils.isPermanentFocusOwner(c);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 189 */     float bw = selected ? ((this.disabledSelectedBorderWidth != Float.MIN_VALUE && !c.isEnabled()) ? this.disabledSelectedBorderWidth : ((this.selectedBorderWidth != Float.MIN_VALUE) ? this.selectedBorderWidth : this.borderWidth)) : this.borderWidth;
/*     */ 
/*     */     
/* 192 */     if (isFocused && this.focusWidth > 0.0F && FlatButtonUI.isFocusPainted(c)) {
/* 193 */       g.setColor(getFocusColor(c));
/* 194 */       paintFocusBorder(c, g);
/*     */     } 
/*     */ 
/*     */     
/* 198 */     g.setColor(getBorderColor(c, selected));
/* 199 */     paintBorder(c, g, bw);
/*     */ 
/*     */     
/* 202 */     Color bg = FlatUIUtils.deriveColor(getBackground(c, selected), 
/* 203 */         selected ? this.selectedBackground : this.background);
/* 204 */     if (bg.getAlpha() < 255) {
/*     */       
/* 206 */       g.setColor(selected ? this.selectedBackground : this.background);
/* 207 */       paintBackground(c, g, bw);
/*     */     } 
/* 209 */     g.setColor(bg);
/* 210 */     paintBackground(c, g, bw);
/*     */ 
/*     */     
/* 213 */     if (selected) {
/* 214 */       g.setColor(getCheckmarkColor(c));
/* 215 */       if (indeterminate) {
/* 216 */         paintIndeterminate(c, g);
/*     */       } else {
/* 218 */         paintCheckmark(c, g);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void paintFocusBorder(Component c, Graphics2D g) {
/* 224 */     float wh = 14.0F + this.focusWidth * 2.0F;
/* 225 */     float arcwh = this.arc + this.focusWidth * 2.0F;
/* 226 */     g.fill(new RoundRectangle2D.Float(-this.focusWidth + 1.0F, -this.focusWidth, wh, wh, arcwh, arcwh));
/*     */   }
/*     */   
/*     */   protected void paintBorder(Component c, Graphics2D g, float borderWidth) {
/* 230 */     if (borderWidth == 0.0F) {
/*     */       return;
/*     */     }
/* 233 */     int arcwh = this.arc;
/* 234 */     g.fillRoundRect(1, 0, 14, 14, arcwh, arcwh);
/*     */   }
/*     */   
/*     */   protected void paintBackground(Component c, Graphics2D g, float borderWidth) {
/* 238 */     float xy = borderWidth;
/* 239 */     float wh = 14.0F - borderWidth * 2.0F;
/* 240 */     float arcwh = this.arc - borderWidth;
/* 241 */     g.fill(new RoundRectangle2D.Float(1.0F + xy, xy, wh, wh, arcwh, arcwh));
/*     */   }
/*     */   
/*     */   protected void paintCheckmark(Component c, Graphics2D g) {
/* 245 */     Path2D.Float path = new Path2D.Float(1, 3);
/* 246 */     path.moveTo(4.5F, 7.5F);
/* 247 */     path.lineTo(6.6F, 10.0F);
/* 248 */     path.lineTo(11.25F, 3.5F);
/*     */     
/* 250 */     g.setStroke(new BasicStroke(1.9F, 1, 1));
/* 251 */     g.draw(path);
/*     */   }
/*     */   
/*     */   protected void paintIndeterminate(Component c, Graphics2D g) {
/* 255 */     g.fill(new RoundRectangle2D.Float(3.75F, 5.75F, 8.5F, 2.5F, 2.0F, 2.0F));
/*     */   }
/*     */   
/*     */   protected boolean isIndeterminate(Component c) {
/* 259 */     return (c instanceof JComponent && FlatClientProperties.clientPropertyEquals((JComponent)c, "JButton.selectedState", "indeterminate"));
/*     */   }
/*     */   
/*     */   protected boolean isSelected(Component c) {
/* 263 */     return (c instanceof AbstractButton && ((AbstractButton)c).isSelected());
/*     */   }
/*     */ 
/*     */   
/*     */   public float getFocusWidth() {
/* 268 */     return this.focusWidth;
/*     */   }
/*     */   
/*     */   protected Color getFocusColor(Component c) {
/* 272 */     return this.focusColor;
/*     */   }
/*     */   
/*     */   protected Color getBorderColor(Component c, boolean selected) {
/* 276 */     return FlatButtonUI.buttonStateColor(c, 
/* 277 */         selected ? this.selectedBorderColor : this.borderColor, 
/* 278 */         (selected && this.disabledSelectedBorderColor != null) ? this.disabledSelectedBorderColor : this.disabledBorderColor, 
/* 279 */         (selected && this.focusedSelectedBorderColor != null) ? this.focusedSelectedBorderColor : this.focusedBorderColor, 
/* 280 */         (selected && this.hoverSelectedBorderColor != null) ? this.hoverSelectedBorderColor : this.hoverBorderColor, 
/* 281 */         (selected && this.pressedSelectedBorderColor != null) ? this.pressedSelectedBorderColor : this.pressedBorderColor);
/*     */   }
/*     */   
/*     */   protected Color getBackground(Component c, boolean selected) {
/* 285 */     return FlatButtonUI.buttonStateColor(c, 
/* 286 */         selected ? this.selectedBackground : this.background, 
/* 287 */         (selected && this.disabledSelectedBackground != null) ? this.disabledSelectedBackground : this.disabledBackground, 
/* 288 */         (selected && this.focusedSelectedBackground != null) ? this.focusedSelectedBackground : this.focusedBackground, 
/* 289 */         (selected && this.hoverSelectedBackground != null) ? this.hoverSelectedBackground : this.hoverBackground, 
/* 290 */         (selected && this.pressedSelectedBackground != null) ? this.pressedSelectedBackground : this.pressedBackground);
/*     */   }
/*     */   
/*     */   protected Color getCheckmarkColor(Component c) {
/* 294 */     return FlatButtonUI.buttonStateColor(c, this.checkmarkColor, this.disabledCheckmarkColor, this.focusedCheckmarkColor, this.hoverCheckmarkColor, this.pressedCheckmarkColor);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatCheckBoxIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */