/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.DerivedColor;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Paint;
/*     */ import java.util.Map;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSpinner;
/*     */ import javax.swing.JViewport;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.basic.BasicBorders;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatBorder
/*     */   extends BasicBorders.MarginBorder
/*     */   implements FlatStylingSupport.StyleableBorder
/*     */ {
/*     */   @Styleable
/*  71 */   protected int focusWidth = UIManager.getInt("Component.focusWidth"); @Styleable
/*  72 */   protected float innerFocusWidth = FlatUIUtils.getUIFloat("Component.innerFocusWidth", 0.0F); @Styleable
/*  73 */   protected float innerOutlineWidth = FlatUIUtils.getUIFloat("Component.innerOutlineWidth", 0.0F); @Styleable
/*  74 */   protected float borderWidth = FlatUIUtils.getUIFloat("Component.borderWidth", 1.0F);
/*     */   @Styleable
/*  76 */   protected Color focusColor = UIManager.getColor("Component.focusColor"); @Styleable
/*  77 */   protected Color borderColor = UIManager.getColor("Component.borderColor"); @Styleable
/*  78 */   protected Color disabledBorderColor = UIManager.getColor("Component.disabledBorderColor"); @Styleable
/*  79 */   protected Color focusedBorderColor = UIManager.getColor("Component.focusedBorderColor");
/*     */   @Styleable(dot = true)
/*  81 */   protected Color errorBorderColor = UIManager.getColor("Component.error.borderColor"); @Styleable(dot = true)
/*  82 */   protected Color errorFocusedBorderColor = UIManager.getColor("Component.error.focusedBorderColor"); @Styleable(dot = true)
/*  83 */   protected Color warningBorderColor = UIManager.getColor("Component.warning.borderColor"); @Styleable(dot = true)
/*  84 */   protected Color warningFocusedBorderColor = UIManager.getColor("Component.warning.focusedBorderColor"); @Styleable(dot = true)
/*  85 */   protected Color customBorderColor = UIManager.getColor("Component.custom.borderColor");
/*     */   
/*     */   @Styleable
/*     */   protected String outline;
/*     */   @Styleable
/*     */   protected Color outlineColor;
/*     */   @Styleable
/*     */   protected Color outlineFocusedColor;
/*     */   
/*     */   public Object applyStyleProperty(String key, Object value) {
/*  95 */     return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos() {
/* 101 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(String key) {
/* 107 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */ 
/*     */   
/*     */   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 112 */     Graphics2D g2 = (Graphics2D)g.create();
/*     */     try {
/* 114 */       FlatUIUtils.setRenderingHints(g2);
/*     */       
/* 116 */       float focusWidth = UIScale.scale(getFocusWidth(c));
/* 117 */       float focusInnerWidth = 0.0F;
/* 118 */       float borderWidth = UIScale.scale(getBorderWidth(c));
/* 119 */       float arc = UIScale.scale(getArc(c));
/* 120 */       Color outlineColor = getOutlineColor(c);
/* 121 */       Color focusColor = null;
/*     */ 
/*     */       
/* 124 */       if (outlineColor != null || isFocused(c)) {
/*     */ 
/*     */         
/* 127 */         float innerWidth = (!isCellEditor(c) && !(c instanceof JScrollPane)) ? ((outlineColor != null) ? this.innerOutlineWidth : getInnerFocusWidth(c)) : 0.0F;
/*     */         
/* 129 */         if (focusWidth > 0.0F || innerWidth > 0.0F) {
/* 130 */           focusColor = (outlineColor != null) ? outlineColor : getFocusColor(c);
/* 131 */           focusInnerWidth = borderWidth + UIScale.scale(innerWidth);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 136 */       Paint borderColor = (outlineColor != null) ? outlineColor : getBorderColor(c);
/* 137 */       FlatUIUtils.paintOutlinedComponent(g2, x, y, width, height, focusWidth, 1.0F, focusInnerWidth, borderWidth, arc, focusColor, borderColor, null);
/*     */     }
/*     */     finally {
/*     */       
/* 141 */       g2.dispose();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Color getOutlineColor(Component c) {
/* 150 */     if (!(c instanceof JComponent)) {
/* 151 */       return null;
/*     */     }
/* 153 */     Object outline = ((JComponent)c).getClientProperty("JComponent.outline");
/* 154 */     if (outline == null)
/* 155 */       outline = this.outline; 
/* 156 */     if (outline == null) {
/* 157 */       if (this.outlineColor != null && this.outlineFocusedColor != null) {
/* 158 */         outline = new Color[] { this.outlineFocusedColor, this.outlineColor };
/* 159 */       } else if (this.outlineColor != null) {
/* 160 */         outline = this.outlineColor;
/* 161 */       } else if (this.outlineFocusedColor != null) {
/* 162 */         outline = this.outlineFocusedColor;
/*     */       } 
/*     */     }
/* 165 */     if (outline instanceof String)
/* 166 */     { switch ((String)outline) {
/*     */         case "error":
/* 168 */           return isFocused(c) ? this.errorFocusedBorderColor : this.errorBorderColor;
/*     */         
/*     */         case "warning":
/* 171 */           return isFocused(c) ? this.warningFocusedBorderColor : this.warningBorderColor;
/*     */       }  }
/* 173 */     else { if (outline instanceof Color) {
/* 174 */         Color color = (Color)outline;
/*     */         
/* 176 */         if (!isFocused(c) && this.customBorderColor instanceof DerivedColor)
/* 177 */           color = ((DerivedColor)this.customBorderColor).derive(color); 
/* 178 */         return color;
/* 179 */       }  if (outline instanceof Color[] && ((Color[])outline).length >= 2)
/* 180 */         return ((Color[])outline)[isFocused(c) ? 0 : 1];  }
/*     */     
/* 182 */     return null;
/*     */   }
/*     */   
/*     */   protected Color getFocusColor(Component c) {
/* 186 */     return this.focusColor;
/*     */   }
/*     */   
/*     */   protected Paint getBorderColor(Component c) {
/* 190 */     return isEnabled(c) ? (
/* 191 */       isFocused(c) ? this.focusedBorderColor : this.borderColor) : 
/* 192 */       this.disabledBorderColor;
/*     */   }
/*     */   
/*     */   protected boolean isEnabled(Component c) {
/* 196 */     if (c instanceof JScrollPane) {
/*     */       
/* 198 */       JViewport viewport = ((JScrollPane)c).getViewport();
/* 199 */       Component view = (viewport != null) ? viewport.getView() : null;
/* 200 */       if (view != null && !isEnabled(view)) {
/* 201 */         return false;
/*     */       }
/*     */     } 
/* 204 */     return c.isEnabled();
/*     */   }
/*     */   
/*     */   protected boolean isFocused(Component c) {
/* 208 */     if (c instanceof JScrollPane)
/* 209 */       return FlatScrollPaneUI.isPermanentFocusOwner((JScrollPane)c); 
/* 210 */     if (c instanceof JComboBox)
/* 211 */       return FlatComboBoxUI.isPermanentFocusOwner((JComboBox)c); 
/* 212 */     if (c instanceof JSpinner) {
/* 213 */       return FlatSpinnerUI.isPermanentFocusOwner((JSpinner)c);
/*     */     }
/* 215 */     return FlatUIUtils.isPermanentFocusOwner(c);
/*     */   }
/*     */   
/*     */   protected boolean isCellEditor(Component c) {
/* 219 */     return FlatUIUtils.isCellEditor(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getBorderInsets(Component c, Insets insets) {
/* 224 */     float focusWidth = UIScale.scale(getFocusWidth(c));
/* 225 */     int ow = Math.round(focusWidth + UIScale.scale(getLineWidth(c)));
/*     */     
/* 227 */     insets = super.getBorderInsets(c, insets);
/*     */     
/* 229 */     insets.top = UIScale.scale(insets.top) + ow;
/* 230 */     insets.left = UIScale.scale(insets.left) + ow;
/* 231 */     insets.bottom = UIScale.scale(insets.bottom) + ow;
/* 232 */     insets.right = UIScale.scale(insets.right) + ow;
/*     */     
/* 234 */     if (isCellEditor(c)) {
/*     */       
/* 236 */       insets.top = insets.bottom = 0;
/*     */ 
/*     */       
/* 239 */       if (c.getComponentOrientation().isLeftToRight()) {
/* 240 */         insets.right = 0;
/*     */       } else {
/* 242 */         insets.left = 0;
/*     */       } 
/*     */     } 
/* 245 */     return insets;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getFocusWidth(Component c) {
/* 252 */     if (isCellEditor(c)) {
/* 253 */       return 0;
/*     */     }
/* 255 */     return this.focusWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getInnerFocusWidth(Component c) {
/* 262 */     return this.innerFocusWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getLineWidth(Component c) {
/* 270 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getBorderWidth(Component c) {
/* 278 */     return this.borderWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getArc(Component c) {
/* 285 */     return 0;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */