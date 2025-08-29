/*     */ package com.jgoodies.forms.factories;
/*     */ 
/*     */ import com.jgoodies.forms.layout.ConstantSize;
/*     */ import com.jgoodies.forms.layout.Sizes;
/*     */ import com.jgoodies.forms.util.LayoutStyle;
/*     */ import java.awt.Component;
/*     */ import java.awt.Insets;
/*     */ import javax.swing.border.AbstractBorder;
/*     */ import javax.swing.border.Border;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Borders
/*     */ {
/*  72 */   public static final Border EMPTY_BORDER = new javax.swing.border.EmptyBorder(0, 0, 0, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public static final Border DLU2_BORDER = createEmptyBorder(Sizes.DLUY2, Sizes.DLUX2, Sizes.DLUY2, Sizes.DLUX2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public static final Border DLU4_BORDER = createEmptyBorder(Sizes.DLUY4, Sizes.DLUX4, Sizes.DLUY4, Sizes.DLUX4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   public static final Border DLU7_BORDER = createEmptyBorder(Sizes.DLUY7, Sizes.DLUX7, Sizes.DLUY7, Sizes.DLUX7);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public static final Border DLU14_BORDER = createEmptyBorder(Sizes.DLUY14, Sizes.DLUX14, Sizes.DLUY14, Sizes.DLUX14);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public static final Border DLU21_BORDER = createEmptyBorder(Sizes.DLUY21, Sizes.DLUX21, Sizes.DLUY21, Sizes.DLUX21);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public static final Border BUTTON_BAR_GAP_BORDER = createEmptyBorder(LayoutStyle.getCurrent().getButtonBarPad(), Sizes.dluX(0), Sizes.dluY(0), Sizes.dluX(0));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public static final Border DIALOG_BORDER = createEmptyBorder(LayoutStyle.getCurrent().getDialogMarginY(), LayoutStyle.getCurrent().getDialogMarginX(), LayoutStyle.getCurrent().getDialogMarginY(), LayoutStyle.getCurrent().getDialogMarginX());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 161 */   public static final Border TABBED_DIALOG_BORDER = createEmptyBorder(LayoutStyle.getCurrent().getTabbedDialogMarginY(), LayoutStyle.getCurrent().getTabbedDialogMarginX(), LayoutStyle.getCurrent().getTabbedDialogMarginY(), LayoutStyle.getCurrent().getTabbedDialogMarginX());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Border createEmptyBorder(ConstantSize top, ConstantSize left, ConstantSize bottom, ConstantSize right) {
/* 188 */     return new EmptyBorder(top, left, bottom, right);
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
/*     */   public static Border createEmptyBorder(String encodedSizes) {
/* 202 */     String[] token = encodedSizes.split("\\s*,\\s*");
/* 203 */     int tokenCount = token.length;
/* 204 */     if (token.length != 4) {
/* 205 */       throw new IllegalArgumentException("The border requires 4 sizes, but \"" + encodedSizes + "\" has " + tokenCount + ".");
/*     */     }
/*     */ 
/*     */     
/* 209 */     ConstantSize top = Sizes.constant(token[0], false);
/* 210 */     ConstantSize left = Sizes.constant(token[1], true);
/* 211 */     ConstantSize bottom = Sizes.constant(token[2], false);
/* 212 */     ConstantSize right = Sizes.constant(token[3], true);
/* 213 */     return createEmptyBorder(top, left, bottom, right);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class EmptyBorder
/*     */     extends AbstractBorder
/*     */   {
/*     */     private final ConstantSize top;
/*     */ 
/*     */     
/*     */     private final ConstantSize left;
/*     */ 
/*     */     
/*     */     private final ConstantSize bottom;
/*     */     
/*     */     private final ConstantSize right;
/*     */ 
/*     */     
/*     */     private EmptyBorder(ConstantSize top, ConstantSize left, ConstantSize bottom, ConstantSize right) {
/* 233 */       if (top == null || left == null || bottom == null || right == null)
/*     */       {
/*     */ 
/*     */         
/* 237 */         throw new NullPointerException("The top, left, bottom, and right must not be null.");
/*     */       }
/* 239 */       this.top = top;
/* 240 */       this.left = left;
/* 241 */       this.bottom = bottom;
/* 242 */       this.right = right;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Insets getBorderInsets(Component c, Insets insets) {
/* 253 */       insets.top = this.top.getPixelSize(c);
/* 254 */       insets.left = this.left.getPixelSize(c);
/* 255 */       insets.bottom = this.bottom.getPixelSize(c);
/* 256 */       insets.right = this.right.getPixelSize(c);
/* 257 */       return insets;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Insets getBorderInsets(Component c) {
/* 267 */       return getBorderInsets(c, new Insets(0, 0, 0, 0));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ConstantSize top() {
/* 276 */       return this.top;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ConstantSize left() {
/* 285 */       return this.left;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ConstantSize bottom() {
/* 294 */       return this.bottom;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ConstantSize right() {
/* 303 */       return this.right;
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\factories\Borders.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */