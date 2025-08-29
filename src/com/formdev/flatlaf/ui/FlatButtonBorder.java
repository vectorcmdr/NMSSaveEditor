/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Paint;
/*     */ import javax.swing.AbstractButton;
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
/*     */ public class FlatButtonBorder
/*     */   extends FlatBorder
/*     */ {
/*     */   @Styleable
/*  64 */   protected int arc = UIManager.getInt("Button.arc");
/*     */   
/*  66 */   protected Color endBorderColor = UIManager.getColor("Button.endBorderColor"); @Styleable
/*  67 */   protected Color hoverBorderColor = UIManager.getColor("Button.hoverBorderColor");
/*     */   @Styleable(dot = true)
/*  69 */   protected float defaultBorderWidth = FlatUIUtils.getUIFloat("Button.default.borderWidth", 1.0F); @Styleable(dot = true)
/*  70 */   protected Color defaultBorderColor = FlatUIUtils.getUIColor("Button.default.startBorderColor", "Button.default.borderColor");
/*  71 */   protected Color defaultEndBorderColor = UIManager.getColor("Button.default.endBorderColor"); @Styleable(dot = true)
/*  72 */   protected Color defaultFocusedBorderColor = UIManager.getColor("Button.default.focusedBorderColor"); @Styleable(dot = true)
/*  73 */   protected Color defaultFocusColor = UIManager.getColor("Button.default.focusColor"); @Styleable(dot = true)
/*  74 */   protected Color defaultHoverBorderColor = UIManager.getColor("Button.default.hoverBorderColor");
/*     */   @Styleable(dot = true)
/*  76 */   protected float toolbarFocusWidth = FlatUIUtils.getUIFloat("Button.toolbar.focusWidth", 1.5F); @Styleable(dot = true)
/*  77 */   protected Color toolbarFocusColor = UIManager.getColor("Button.toolbar.focusColor"); @Styleable(dot = true)
/*  78 */   protected Insets toolbarMargin = UIManager.getInsets("Button.toolbar.margin"); @Styleable(dot = true)
/*  79 */   protected Insets toolbarSpacingInsets = UIManager.getInsets("Button.toolbar.spacingInsets");
/*     */   
/*     */   public FlatButtonBorder() {
/*  82 */     this.innerFocusWidth = FlatUIUtils.getUIFloat("Button.innerFocusWidth", this.innerFocusWidth);
/*  83 */     this.borderWidth = FlatUIUtils.getUIFloat("Button.borderWidth", this.borderWidth);
/*     */     
/*  85 */     this.borderColor = FlatUIUtils.getUIColor("Button.startBorderColor", "Button.borderColor");
/*  86 */     this.disabledBorderColor = UIManager.getColor("Button.disabledBorderColor");
/*  87 */     this.focusedBorderColor = UIManager.getColor("Button.focusedBorderColor");
/*     */   }
/*     */ 
/*     */   
/*     */   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/*  92 */     if (FlatButtonUI.isContentAreaFilled(c) && 
/*  93 */       !FlatButtonUI.isToolBarButton(c) && (
/*  94 */       !FlatButtonUI.isBorderlessButton(c) || FlatUIUtils.isPermanentFocusOwner(c)) && 
/*  95 */       !FlatButtonUI.isHelpButton(c) && 
/*  96 */       !FlatToggleButtonUI.isTabButton(c)) {
/*  97 */       super.paintBorder(c, g, x, y, width, height);
/*  98 */     } else if (FlatButtonUI.isToolBarButton(c) && isFocused(c)) {
/*  99 */       paintToolBarFocus(c, g, x, y, width, height);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void paintToolBarFocus(Component c, Graphics g, int x, int y, int width, int height) {
/* 104 */     Graphics2D g2 = (Graphics2D)g.create();
/*     */     try {
/* 106 */       FlatUIUtils.setRenderingHints(g2);
/*     */       
/* 108 */       float focusWidth = UIScale.scale(this.toolbarFocusWidth);
/* 109 */       float arc = UIScale.scale(getArc(c));
/* 110 */       Color outlineColor = getOutlineColor(c);
/*     */       
/* 112 */       Insets spacing = UIScale.scale(this.toolbarSpacingInsets);
/* 113 */       x += spacing.left;
/* 114 */       y += spacing.top;
/* 115 */       width -= spacing.left + spacing.right;
/* 116 */       height -= spacing.top + spacing.bottom;
/*     */       
/* 118 */       Color color = (outlineColor != null) ? outlineColor : getFocusColor(c);
/*     */ 
/*     */       
/* 121 */       FlatUIUtils.paintOutlinedComponent(g2, x, y, width, height, 0.0F, 0.0F, 0.0F, focusWidth, arc, null, color, null);
/*     */     } finally {
/* 123 */       g2.dispose();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Color getFocusColor(Component c) {
/* 129 */     return (this.toolbarFocusColor != null && FlatButtonUI.isToolBarButton(c)) ? 
/* 130 */       this.toolbarFocusColor : (
/* 131 */       FlatButtonUI.isDefaultButton(c) ? this.defaultFocusColor : super.getFocusColor(c));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isFocused(Component c) {
/* 136 */     return (FlatButtonUI.isFocusPainted(c) && super.isFocused(c));
/*     */   }
/*     */ 
/*     */   
/*     */   protected Paint getBorderColor(Component c) {
/* 141 */     boolean def = FlatButtonUI.isDefaultButton(c);
/* 142 */     Paint color = FlatButtonUI.buttonStateColor(c, 
/* 143 */         def ? this.defaultBorderColor : this.borderColor, this.disabledBorderColor, 
/*     */         
/* 145 */         def ? this.defaultFocusedBorderColor : this.focusedBorderColor, 
/* 146 */         def ? this.defaultHoverBorderColor : this.hoverBorderColor, null);
/*     */ 
/*     */ 
/*     */     
/* 150 */     Color startBg = def ? this.defaultBorderColor : this.borderColor;
/* 151 */     Color endBg = def ? this.defaultEndBorderColor : this.endBorderColor;
/* 152 */     if (color == startBg && endBg != null && !startBg.equals(endBg)) {
/* 153 */       color = new GradientPaint(0.0F, 0.0F, startBg, 0.0F, c.getHeight(), endBg);
/*     */     }
/* 155 */     return color;
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getBorderInsets(Component c, Insets insets) {
/* 160 */     if (FlatButtonUI.isToolBarButton(c)) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 165 */       Insets margin = (c instanceof AbstractButton) ? ((AbstractButton)c).getMargin() : null;
/*     */       
/* 167 */       FlatUIUtils.setInsets(insets, UIScale.scale(FlatUIUtils.addInsets(this.toolbarSpacingInsets, (
/* 168 */               margin != null && !(margin instanceof javax.swing.plaf.UIResource)) ? margin : this.toolbarMargin)));
/*     */     } else {
/* 170 */       insets = super.getBorderInsets(c, insets);
/*     */ 
/*     */       
/* 173 */       if (FlatButtonUI.isIconOnlyOrSingleCharacterButton(c) && ((AbstractButton)c).getMargin() instanceof javax.swing.plaf.UIResource) {
/* 174 */         insets.left = insets.right = Math.min(insets.top, insets.bottom);
/*     */       }
/*     */     } 
/* 177 */     return insets;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getFocusWidth(Component c) {
/* 182 */     return FlatToggleButtonUI.isTabButton(c) ? 0 : super.getFocusWidth(c);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getBorderWidth(Component c) {
/* 187 */     return FlatButtonUI.isDefaultButton(c) ? this.defaultBorderWidth : this.borderWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getArc(Component c) {
/* 192 */     if (isCellEditor(c)) {
/* 193 */       return 0;
/*     */     }
/* 195 */     switch (FlatButtonUI.getButtonType(c)) { case 0:
/* 196 */         return 0;
/* 197 */       case 1: return 32767; }
/* 198 */      return this.arc;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatButtonBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */