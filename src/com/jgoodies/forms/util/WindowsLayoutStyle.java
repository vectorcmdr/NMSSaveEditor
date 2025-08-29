/*     */ package com.jgoodies.forms.util;
/*     */ 
/*     */ import com.jgoodies.forms.layout.ConstantSize;
/*     */ import com.jgoodies.forms.layout.Size;
/*     */ import com.jgoodies.forms.layout.Sizes;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class WindowsLayoutStyle
/*     */   extends LayoutStyle
/*     */ {
/*  46 */   static final WindowsLayoutStyle INSTANCE = new WindowsLayoutStyle();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   private static final Size BUTTON_WIDTH = (Size)Sizes.dluX(50);
/*  56 */   private static final Size BUTTON_HEIGHT = (Size)Sizes.dluY(14);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   private static final ConstantSize DIALOG_MARGIN_X = Sizes.DLUX7;
/*  62 */   private static final ConstantSize DIALOG_MARGIN_Y = Sizes.DLUY7;
/*     */   
/*  64 */   private static final ConstantSize TABBED_DIALOG_MARGIN_X = Sizes.DLUX4;
/*  65 */   private static final ConstantSize TABBED_DIALOG_MARGIN_Y = Sizes.DLUY4;
/*     */   
/*  67 */   private static final ConstantSize LABEL_COMPONENT_PADX = Sizes.DLUX3;
/*  68 */   private static final ConstantSize RELATED_COMPONENTS_PADX = Sizes.DLUX4;
/*  69 */   private static final ConstantSize UNRELATED_COMPONENTS_PADX = Sizes.DLUX7;
/*     */   
/*  71 */   private static final ConstantSize RELATED_COMPONENTS_PADY = Sizes.DLUY4;
/*  72 */   private static final ConstantSize UNRELATED_COMPONENTS_PADY = Sizes.DLUY7;
/*  73 */   private static final ConstantSize NARROW_LINE_PAD = Sizes.DLUY2;
/*  74 */   private static final ConstantSize LINE_PAD = Sizes.DLUY3;
/*  75 */   private static final ConstantSize PARAGRAPH_PAD = Sizes.DLUY9;
/*  76 */   private static final ConstantSize BUTTON_BAR_PAD = Sizes.DLUY5;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Size getDefaultButtonWidth() {
/*  89 */     return BUTTON_WIDTH;
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
/*     */   public Size getDefaultButtonHeight() {
/* 101 */     return BUTTON_HEIGHT;
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
/*     */   public ConstantSize getDialogMarginX() {
/* 114 */     return DIALOG_MARGIN_X;
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
/*     */   public ConstantSize getDialogMarginY() {
/* 127 */     return DIALOG_MARGIN_Y;
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
/*     */   public ConstantSize getTabbedDialogMarginX() {
/* 143 */     return TABBED_DIALOG_MARGIN_X;
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
/*     */   public ConstantSize getTabbedDialogMarginY() {
/* 159 */     return TABBED_DIALOG_MARGIN_Y;
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
/*     */   public ConstantSize getLabelComponentPadX() {
/* 172 */     return LABEL_COMPONENT_PADX;
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
/*     */   public ConstantSize getRelatedComponentsPadX() {
/* 186 */     return RELATED_COMPONENTS_PADX;
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
/*     */   public ConstantSize getRelatedComponentsPadY() {
/* 199 */     return RELATED_COMPONENTS_PADY;
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
/*     */   public ConstantSize getUnrelatedComponentsPadX() {
/* 213 */     return UNRELATED_COMPONENTS_PADX;
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
/*     */   public ConstantSize getUnrelatedComponentsPadY() {
/* 226 */     return UNRELATED_COMPONENTS_PADY;
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
/*     */   public ConstantSize getNarrowLinePad() {
/* 239 */     return NARROW_LINE_PAD;
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
/*     */   public ConstantSize getLinePad() {
/* 252 */     return LINE_PAD;
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
/*     */   public ConstantSize getParagraphPad() {
/* 265 */     return PARAGRAPH_PAD;
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
/*     */   public ConstantSize getButtonBarPad() {
/* 280 */     return BUTTON_BAR_PAD;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLeftToRightButtonOrder() {
/* 306 */     return true;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\form\\util\WindowsLayoutStyle.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */