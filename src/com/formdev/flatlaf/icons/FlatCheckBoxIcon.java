package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.UIManager;

public class FlatCheckBoxIcon extends FlatAbstractIcon {
   protected final String style = UIManager.getString(this.getPropertyPrefix() + "icon.style");
   @FlatStylingSupport.Styleable
   protected float focusWidth;
   @FlatStylingSupport.Styleable
   protected Color focusColor;
   @FlatStylingSupport.Styleable
   protected float borderWidth;
   @FlatStylingSupport.Styleable
   protected float selectedBorderWidth;
   @FlatStylingSupport.Styleable
   protected float disabledSelectedBorderWidth;
   @FlatStylingSupport.Styleable
   protected int arc;
   @FlatStylingSupport.Styleable
   protected Color borderColor;
   @FlatStylingSupport.Styleable
   protected Color background;
   @FlatStylingSupport.Styleable
   protected Color selectedBorderColor;
   @FlatStylingSupport.Styleable
   protected Color selectedBackground;
   @FlatStylingSupport.Styleable
   protected Color checkmarkColor;
   @FlatStylingSupport.Styleable
   protected Color disabledBorderColor;
   @FlatStylingSupport.Styleable
   protected Color disabledBackground;
   @FlatStylingSupport.Styleable
   protected Color disabledSelectedBorderColor;
   @FlatStylingSupport.Styleable
   protected Color disabledSelectedBackground;
   @FlatStylingSupport.Styleable
   protected Color disabledCheckmarkColor;
   @FlatStylingSupport.Styleable
   protected Color focusedBorderColor;
   @FlatStylingSupport.Styleable
   protected Color focusedBackground;
   @FlatStylingSupport.Styleable
   protected Color focusedSelectedBorderColor;
   @FlatStylingSupport.Styleable
   protected Color focusedSelectedBackground;
   @FlatStylingSupport.Styleable
   protected Color focusedCheckmarkColor;
   @FlatStylingSupport.Styleable
   protected Color hoverBorderColor;
   @FlatStylingSupport.Styleable
   protected Color hoverBackground;
   @FlatStylingSupport.Styleable
   protected Color hoverSelectedBorderColor;
   @FlatStylingSupport.Styleable
   protected Color hoverSelectedBackground;
   @FlatStylingSupport.Styleable
   protected Color hoverCheckmarkColor;
   @FlatStylingSupport.Styleable
   protected Color pressedBorderColor;
   @FlatStylingSupport.Styleable
   protected Color pressedBackground;
   @FlatStylingSupport.Styleable
   protected Color pressedSelectedBorderColor;
   @FlatStylingSupport.Styleable
   protected Color pressedSelectedBackground;
   @FlatStylingSupport.Styleable
   protected Color pressedCheckmarkColor;
   static final int ICON_SIZE = 15;

   protected String getPropertyPrefix() {
      return "CheckBox.";
   }

   protected static Color getUIColor(String key, String style) {
      if (style != null) {
         Color color = UIManager.getColor(styleKey(key, style));
         if (color != null) {
            return color;
         }
      }

      return UIManager.getColor(key);
   }

   protected static float getUIFloat(String key, float defaultValue, String style) {
      if (style != null) {
         float value = FlatUIUtils.getUIFloat(styleKey(key, style), Float.MIN_VALUE);
         if (value != Float.MIN_VALUE) {
            return value;
         }
      }

      return FlatUIUtils.getUIFloat(key, defaultValue);
   }

   private static String styleKey(String key, String style) {
      return key.replace(".icon.", ".icon[" + style + "].");
   }

   public FlatCheckBoxIcon() {
      super(15, 15, (Color)null);
      this.focusWidth = getUIFloat("CheckBox.icon.focusWidth", (float)UIManager.getInt("Component.focusWidth"), this.style);
      this.focusColor = FlatUIUtils.getUIColor("CheckBox.icon.focusColor", UIManager.getColor("Component.focusColor"));
      this.borderWidth = getUIFloat("CheckBox.icon.borderWidth", FlatUIUtils.getUIFloat("Component.borderWidth", 1.0F), this.style);
      this.selectedBorderWidth = getUIFloat("CheckBox.icon.selectedBorderWidth", Float.MIN_VALUE, this.style);
      this.disabledSelectedBorderWidth = getUIFloat("CheckBox.icon.disabledSelectedBorderWidth", Float.MIN_VALUE, this.style);
      this.arc = FlatUIUtils.getUIInt("CheckBox.arc", 2);
      this.borderColor = getUIColor("CheckBox.icon.borderColor", this.style);
      this.background = getUIColor("CheckBox.icon.background", this.style);
      this.selectedBorderColor = getUIColor("CheckBox.icon.selectedBorderColor", this.style);
      this.selectedBackground = getUIColor("CheckBox.icon.selectedBackground", this.style);
      this.checkmarkColor = getUIColor("CheckBox.icon.checkmarkColor", this.style);
      this.disabledBorderColor = getUIColor("CheckBox.icon.disabledBorderColor", this.style);
      this.disabledBackground = getUIColor("CheckBox.icon.disabledBackground", this.style);
      this.disabledSelectedBorderColor = getUIColor("CheckBox.icon.disabledSelectedBorderColor", this.style);
      this.disabledSelectedBackground = getUIColor("CheckBox.icon.disabledSelectedBackground", this.style);
      this.disabledCheckmarkColor = getUIColor("CheckBox.icon.disabledCheckmarkColor", this.style);
      this.focusedBorderColor = getUIColor("CheckBox.icon.focusedBorderColor", this.style);
      this.focusedBackground = getUIColor("CheckBox.icon.focusedBackground", this.style);
      this.focusedSelectedBorderColor = getUIColor("CheckBox.icon.focusedSelectedBorderColor", this.style);
      this.focusedSelectedBackground = getUIColor("CheckBox.icon.focusedSelectedBackground", this.style);
      this.focusedCheckmarkColor = getUIColor("CheckBox.icon.focusedCheckmarkColor", this.style);
      this.hoverBorderColor = getUIColor("CheckBox.icon.hoverBorderColor", this.style);
      this.hoverBackground = getUIColor("CheckBox.icon.hoverBackground", this.style);
      this.hoverSelectedBorderColor = getUIColor("CheckBox.icon.hoverSelectedBorderColor", this.style);
      this.hoverSelectedBackground = getUIColor("CheckBox.icon.hoverSelectedBackground", this.style);
      this.hoverCheckmarkColor = getUIColor("CheckBox.icon.hoverCheckmarkColor", this.style);
      this.pressedBorderColor = getUIColor("CheckBox.icon.pressedBorderColor", this.style);
      this.pressedBackground = getUIColor("CheckBox.icon.pressedBackground", this.style);
      this.pressedSelectedBorderColor = getUIColor("CheckBox.icon.pressedSelectedBorderColor", this.style);
      this.pressedSelectedBackground = getUIColor("CheckBox.icon.pressedSelectedBackground", this.style);
      this.pressedCheckmarkColor = getUIColor("CheckBox.icon.pressedCheckmarkColor", this.style);
   }

   public Object applyStyleProperty(String key, Object value) {
      return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
   }

   public Map<String, Class<?>> getStyleableInfos() {
      return FlatStylingSupport.getAnnotatedStyleableInfos(this);
   }

   public Object getStyleableValue(String key) {
      return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
   }

   protected void paintIcon(Component c, Graphics2D g) {
      boolean indeterminate = this.isIndeterminate(c);
      boolean selected = indeterminate || this.isSelected(c);
      boolean isFocused = FlatUIUtils.isPermanentFocusOwner(c);
      float bw = selected ? (this.disabledSelectedBorderWidth != Float.MIN_VALUE && !c.isEnabled() ? this.disabledSelectedBorderWidth : (this.selectedBorderWidth != Float.MIN_VALUE ? this.selectedBorderWidth : this.borderWidth)) : this.borderWidth;
      if (isFocused && this.focusWidth > 0.0F && FlatButtonUI.isFocusPainted(c)) {
         g.setColor(this.getFocusColor(c));
         this.paintFocusBorder(c, g);
      }

      g.setColor(this.getBorderColor(c, selected));
      this.paintBorder(c, g, bw);
      Color bg = FlatUIUtils.deriveColor(this.getBackground(c, selected), selected ? this.selectedBackground : this.background);
      if (bg.getAlpha() < 255) {
         g.setColor(selected ? this.selectedBackground : this.background);
         this.paintBackground(c, g, bw);
      }

      g.setColor(bg);
      this.paintBackground(c, g, bw);
      if (selected) {
         g.setColor(this.getCheckmarkColor(c));
         if (indeterminate) {
            this.paintIndeterminate(c, g);
         } else {
            this.paintCheckmark(c, g);
         }
      }

   }

   protected void paintFocusBorder(Component c, Graphics2D g) {
      float wh = 14.0F + this.focusWidth * 2.0F;
      float arcwh = (float)this.arc + this.focusWidth * 2.0F;
      g.fill(new java.awt.geom.RoundRectangle2D.Float(-this.focusWidth + 1.0F, -this.focusWidth, wh, wh, arcwh, arcwh));
   }

   protected void paintBorder(Component c, Graphics2D g, float borderWidth) {
      if (borderWidth != 0.0F) {
         int arcwh = this.arc;
         g.fillRoundRect(1, 0, 14, 14, arcwh, arcwh);
      }
   }

   protected void paintBackground(Component c, Graphics2D g, float borderWidth) {
      float wh = 14.0F - borderWidth * 2.0F;
      float arcwh = (float)this.arc - borderWidth;
      g.fill(new java.awt.geom.RoundRectangle2D.Float(1.0F + borderWidth, borderWidth, wh, wh, arcwh, arcwh));
   }

   protected void paintCheckmark(Component c, Graphics2D g) {
      java.awt.geom.Path2D.Float path = new java.awt.geom.Path2D.Float(1, 3);
      path.moveTo(4.5F, 7.5F);
      path.lineTo(6.6F, 10.0F);
      path.lineTo(11.25F, 3.5F);
      g.setStroke(new BasicStroke(1.9F, 1, 1));
      g.draw(path);
   }

   protected void paintIndeterminate(Component c, Graphics2D g) {
      g.fill(new java.awt.geom.RoundRectangle2D.Float(3.75F, 5.75F, 8.5F, 2.5F, 2.0F, 2.0F));
   }

   protected boolean isIndeterminate(Component c) {
      return c instanceof JComponent && FlatClientProperties.clientPropertyEquals((JComponent)c, "JButton.selectedState", "indeterminate");
   }

   protected boolean isSelected(Component c) {
      return c instanceof AbstractButton && ((AbstractButton)c).isSelected();
   }

   public float getFocusWidth() {
      return this.focusWidth;
   }

   protected Color getFocusColor(Component c) {
      return this.focusColor;
   }

   protected Color getBorderColor(Component c, boolean selected) {
      return FlatButtonUI.buttonStateColor(c, selected ? this.selectedBorderColor : this.borderColor, selected && this.disabledSelectedBorderColor != null ? this.disabledSelectedBorderColor : this.disabledBorderColor, selected && this.focusedSelectedBorderColor != null ? this.focusedSelectedBorderColor : this.focusedBorderColor, selected && this.hoverSelectedBorderColor != null ? this.hoverSelectedBorderColor : this.hoverBorderColor, selected && this.pressedSelectedBorderColor != null ? this.pressedSelectedBorderColor : this.pressedBorderColor);
   }

   protected Color getBackground(Component c, boolean selected) {
      return FlatButtonUI.buttonStateColor(c, selected ? this.selectedBackground : this.background, selected && this.disabledSelectedBackground != null ? this.disabledSelectedBackground : this.disabledBackground, selected && this.focusedSelectedBackground != null ? this.focusedSelectedBackground : this.focusedBackground, selected && this.hoverSelectedBackground != null ? this.hoverSelectedBackground : this.hoverBackground, selected && this.pressedSelectedBackground != null ? this.pressedSelectedBackground : this.pressedBackground);
   }

   protected Color getCheckmarkColor(Component c) {
      return FlatButtonUI.buttonStateColor(c, this.checkmarkColor, this.disabledCheckmarkColor, this.focusedCheckmarkColor, this.hoverCheckmarkColor, this.pressedCheckmarkColor);
   }
}
