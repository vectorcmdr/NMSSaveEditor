package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import javax.swing.AbstractButton;
import javax.swing.UIManager;
import javax.swing.plaf.UIResource;

public class FlatButtonBorder extends FlatBorder {
   @FlatStylingSupport.Styleable
   protected int arc = UIManager.getInt("Button.arc");
   protected Color endBorderColor = UIManager.getColor("Button.endBorderColor");
   @FlatStylingSupport.Styleable
   protected Color hoverBorderColor = UIManager.getColor("Button.hoverBorderColor");
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected float defaultBorderWidth = FlatUIUtils.getUIFloat("Button.default.borderWidth", 1.0F);
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color defaultBorderColor = FlatUIUtils.getUIColor("Button.default.startBorderColor", "Button.default.borderColor");
   protected Color defaultEndBorderColor = UIManager.getColor("Button.default.endBorderColor");
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color defaultFocusedBorderColor = UIManager.getColor("Button.default.focusedBorderColor");
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color defaultFocusColor = UIManager.getColor("Button.default.focusColor");
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color defaultHoverBorderColor = UIManager.getColor("Button.default.hoverBorderColor");
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected float toolbarFocusWidth = FlatUIUtils.getUIFloat("Button.toolbar.focusWidth", 1.5F);
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color toolbarFocusColor = UIManager.getColor("Button.toolbar.focusColor");
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Insets toolbarMargin = UIManager.getInsets("Button.toolbar.margin");
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Insets toolbarSpacingInsets = UIManager.getInsets("Button.toolbar.spacingInsets");

   public FlatButtonBorder() {
      this.innerFocusWidth = FlatUIUtils.getUIFloat("Button.innerFocusWidth", this.innerFocusWidth);
      this.borderWidth = FlatUIUtils.getUIFloat("Button.borderWidth", this.borderWidth);
      this.borderColor = FlatUIUtils.getUIColor("Button.startBorderColor", "Button.borderColor");
      this.disabledBorderColor = UIManager.getColor("Button.disabledBorderColor");
      this.focusedBorderColor = UIManager.getColor("Button.focusedBorderColor");
   }

   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
      if (FlatButtonUI.isContentAreaFilled(c) && !FlatButtonUI.isToolBarButton(c) && (!FlatButtonUI.isBorderlessButton(c) || FlatUIUtils.isPermanentFocusOwner(c)) && !FlatButtonUI.isHelpButton(c) && !FlatToggleButtonUI.isTabButton(c)) {
         super.paintBorder(c, g, x, y, width, height);
      } else if (FlatButtonUI.isToolBarButton(c) && this.isFocused(c)) {
         this.paintToolBarFocus(c, g, x, y, width, height);
      }

   }

   protected void paintToolBarFocus(Component c, Graphics g, int x, int y, int width, int height) {
      Graphics2D g2 = (Graphics2D)g.create();

      try {
         FlatUIUtils.setRenderingHints(g2);
         float focusWidth = UIScale.scale(this.toolbarFocusWidth);
         float arc = UIScale.scale((float)this.getArc(c));
         Color outlineColor = this.getOutlineColor(c);
         Insets spacing = UIScale.scale(this.toolbarSpacingInsets);
         x += spacing.left;
         y += spacing.top;
         width -= spacing.left + spacing.right;
         height -= spacing.top + spacing.bottom;
         Color color = outlineColor != null ? outlineColor : this.getFocusColor(c);
         FlatUIUtils.paintOutlinedComponent(g2, x, y, width, height, 0.0F, 0.0F, 0.0F, focusWidth, arc, (Paint)null, color, (Paint)null);
      } finally {
         g2.dispose();
      }

   }

   protected Color getFocusColor(Component c) {
      return this.toolbarFocusColor != null && FlatButtonUI.isToolBarButton(c) ? this.toolbarFocusColor : (FlatButtonUI.isDefaultButton(c) ? this.defaultFocusColor : super.getFocusColor(c));
   }

   protected boolean isFocused(Component c) {
      return FlatButtonUI.isFocusPainted(c) && super.isFocused(c);
   }

   protected Paint getBorderColor(Component c) {
      boolean def = FlatButtonUI.isDefaultButton(c);
      Paint color = FlatButtonUI.buttonStateColor(c, def ? this.defaultBorderColor : this.borderColor, this.disabledBorderColor, def ? this.defaultFocusedBorderColor : this.focusedBorderColor, def ? this.defaultHoverBorderColor : this.hoverBorderColor, (Color)null);
      Color startBg = def ? this.defaultBorderColor : this.borderColor;
      Color endBg = def ? this.defaultEndBorderColor : this.endBorderColor;
      if (color == startBg && endBg != null && !startBg.equals(endBg)) {
         color = new GradientPaint(0.0F, 0.0F, startBg, 0.0F, (float)c.getHeight(), endBg);
      }

      return (Paint)color;
   }

   public Insets getBorderInsets(Component c, Insets insets) {
      if (FlatButtonUI.isToolBarButton(c)) {
         Insets margin = c instanceof AbstractButton ? ((AbstractButton)c).getMargin() : null;
         FlatUIUtils.setInsets(insets, UIScale.scale(FlatUIUtils.addInsets(this.toolbarSpacingInsets, margin != null && !(margin instanceof UIResource) ? margin : this.toolbarMargin)));
      } else {
         insets = super.getBorderInsets(c, insets);
         if (FlatButtonUI.isIconOnlyOrSingleCharacterButton(c) && ((AbstractButton)c).getMargin() instanceof UIResource) {
            insets.left = insets.right = Math.min(insets.top, insets.bottom);
         }
      }

      return insets;
   }

   protected int getFocusWidth(Component c) {
      return FlatToggleButtonUI.isTabButton(c) ? 0 : super.getFocusWidth(c);
   }

   protected float getBorderWidth(Component c) {
      return FlatButtonUI.isDefaultButton(c) ? this.defaultBorderWidth : this.borderWidth;
   }

   protected int getArc(Component c) {
      if (this.isCellEditor(c)) {
         return 0;
      } else {
         switch(FlatButtonUI.getButtonType(c)) {
         case 0:
            return 0;
         case 1:
            return 32767;
         default:
            return this.arc;
         }
      }
   }
}
