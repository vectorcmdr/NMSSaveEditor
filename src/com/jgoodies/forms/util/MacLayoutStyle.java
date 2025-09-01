package com.jgoodies.forms.util;

import com.jgoodies.forms.layout.ConstantSize;
import com.jgoodies.forms.layout.Size;
import com.jgoodies.forms.layout.Sizes;

final class MacLayoutStyle extends LayoutStyle {
   static final MacLayoutStyle INSTANCE = new MacLayoutStyle();
   private static final Size BUTTON_WIDTH = Sizes.dluX(54);
   private static final Size BUTTON_HEIGHT = Sizes.dluY(14);
   private static final ConstantSize DIALOG_MARGIN_X;
   private static final ConstantSize DIALOG_MARGIN_Y;
   private static final ConstantSize TABBED_DIALOG_MARGIN_X;
   private static final ConstantSize TABBED_DIALOG_MARGIN_Y;
   private static final ConstantSize LABEL_COMPONENT_PADX;
   private static final ConstantSize RELATED_COMPONENTS_PADX;
   private static final ConstantSize UNRELATED_COMPONENTS_PADX;
   private static final ConstantSize RELATED_COMPONENTS_PADY;
   private static final ConstantSize UNRELATED_COMPONENTS_PADY;
   private static final ConstantSize NARROW_LINE_PAD;
   private static final ConstantSize LINE_PAD;
   private static final ConstantSize PARAGRAPH_PAD;
   private static final ConstantSize BUTTON_BAR_PAD;

   private MacLayoutStyle() {
   }

   public Size getDefaultButtonWidth() {
      return BUTTON_WIDTH;
   }

   public Size getDefaultButtonHeight() {
      return BUTTON_HEIGHT;
   }

   public ConstantSize getDialogMarginX() {
      return DIALOG_MARGIN_X;
   }

   public ConstantSize getDialogMarginY() {
      return DIALOG_MARGIN_Y;
   }

   public ConstantSize getTabbedDialogMarginX() {
      return TABBED_DIALOG_MARGIN_X;
   }

   public ConstantSize getTabbedDialogMarginY() {
      return TABBED_DIALOG_MARGIN_Y;
   }

   public ConstantSize getLabelComponentPadX() {
      return LABEL_COMPONENT_PADX;
   }

   public ConstantSize getRelatedComponentsPadX() {
      return RELATED_COMPONENTS_PADX;
   }

   public ConstantSize getRelatedComponentsPadY() {
      return RELATED_COMPONENTS_PADY;
   }

   public ConstantSize getUnrelatedComponentsPadX() {
      return UNRELATED_COMPONENTS_PADX;
   }

   public ConstantSize getUnrelatedComponentsPadY() {
      return UNRELATED_COMPONENTS_PADY;
   }

   public ConstantSize getNarrowLinePad() {
      return NARROW_LINE_PAD;
   }

   public ConstantSize getLinePad() {
      return LINE_PAD;
   }

   public ConstantSize getParagraphPad() {
      return PARAGRAPH_PAD;
   }

   public ConstantSize getButtonBarPad() {
      return BUTTON_BAR_PAD;
   }

   public boolean isLeftToRightButtonOrder() {
      return false;
   }

   static {
      DIALOG_MARGIN_X = Sizes.DLUX9;
      DIALOG_MARGIN_Y = Sizes.DLUY9;
      TABBED_DIALOG_MARGIN_X = Sizes.DLUX4;
      TABBED_DIALOG_MARGIN_Y = Sizes.DLUY4;
      LABEL_COMPONENT_PADX = Sizes.DLUX3;
      RELATED_COMPONENTS_PADX = Sizes.DLUX4;
      UNRELATED_COMPONENTS_PADX = Sizes.DLUX8;
      RELATED_COMPONENTS_PADY = Sizes.DLUY3;
      UNRELATED_COMPONENTS_PADY = Sizes.DLUY6;
      NARROW_LINE_PAD = Sizes.DLUY2;
      LINE_PAD = Sizes.DLUY3;
      PARAGRAPH_PAD = Sizes.DLUY9;
      BUTTON_BAR_PAD = Sizes.DLUY4;
   }
}
