package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.geom.Ellipse2D.Float;
import java.util.Map;
import javax.swing.UIManager;

public class FlatHelpButtonIcon extends FlatAbstractIcon {
   @FlatStylingSupport.Styleable
   protected int focusWidth = UIManager.getInt("Component.focusWidth");
   @FlatStylingSupport.Styleable
   protected Color focusColor = UIManager.getColor("Component.focusColor");
   @FlatStylingSupport.Styleable
   protected float innerFocusWidth = FlatUIUtils.getUIFloat("HelpButton.innerFocusWidth", FlatUIUtils.getUIFloat("Component.innerFocusWidth", 0.0F));
   @FlatStylingSupport.Styleable
   protected int borderWidth = FlatUIUtils.getUIInt("HelpButton.borderWidth", 1);
   @FlatStylingSupport.Styleable
   protected Color borderColor = UIManager.getColor("HelpButton.borderColor");
   @FlatStylingSupport.Styleable
   protected Color disabledBorderColor = UIManager.getColor("HelpButton.disabledBorderColor");
   @FlatStylingSupport.Styleable
   protected Color focusedBorderColor = UIManager.getColor("HelpButton.focusedBorderColor");
   @FlatStylingSupport.Styleable
   protected Color hoverBorderColor = UIManager.getColor("HelpButton.hoverBorderColor");
   @FlatStylingSupport.Styleable
   protected Color background = UIManager.getColor("HelpButton.background");
   @FlatStylingSupport.Styleable
   protected Color disabledBackground = UIManager.getColor("HelpButton.disabledBackground");
   @FlatStylingSupport.Styleable
   protected Color focusedBackground = UIManager.getColor("HelpButton.focusedBackground");
   @FlatStylingSupport.Styleable
   protected Color hoverBackground = UIManager.getColor("HelpButton.hoverBackground");
   @FlatStylingSupport.Styleable
   protected Color pressedBackground = UIManager.getColor("HelpButton.pressedBackground");
   @FlatStylingSupport.Styleable
   protected Color questionMarkColor = UIManager.getColor("HelpButton.questionMarkColor");
   @FlatStylingSupport.Styleable
   protected Color disabledQuestionMarkColor = UIManager.getColor("HelpButton.disabledQuestionMarkColor");

   public FlatHelpButtonIcon() {
      super(0, 0, (Color)null);
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

   protected void paintIcon(Component c, Graphics2D g2) {
      boolean enabled = c == null || c.isEnabled();
      boolean focused = c != null && FlatUIUtils.isPermanentFocusOwner(c);
      float xy = 0.5F;
      float wh = (float)(this.iconSize() - 1);
      if (focused && FlatButtonUI.isFocusPainted(c)) {
         g2.setColor(this.focusColor);
         g2.fill(new Float(xy, xy, wh, wh));
      }

      xy += (float)this.focusWidth;
      wh -= (float)(this.focusWidth * 2);
      g2.setColor(FlatButtonUI.buttonStateColor(c, this.borderColor, this.disabledBorderColor, this.focusedBorderColor, this.hoverBorderColor, (Color)null));
      g2.fill(new Float(xy, xy, wh, wh));
      xy += (float)this.borderWidth;
      wh -= (float)(this.borderWidth * 2);
      if (this.innerFocusWidth > 0.0F && focused && FlatButtonUI.isFocusPainted(c)) {
         g2.setColor(this.focusColor);
         g2.fill(new Float(xy, xy, wh, wh));
         xy += this.innerFocusWidth;
         wh -= this.innerFocusWidth * 2.0F;
      }

      g2.setColor(FlatUIUtils.deriveColor(FlatButtonUI.buttonStateColor(c, this.background, this.disabledBackground, this.focusedBackground, this.hoverBackground, this.pressedBackground), this.background));
      g2.fill(new Float(xy, xy, wh, wh));
      Path2D q = new java.awt.geom.Path2D.Float(1, 10);
      q.moveTo(8.0D, 8.5D);
      q.curveTo(8.25D, 7.0D, 9.66585007D, 6.0D, 11.0D, 6.0D);
      q.curveTo(12.5D, 6.0D, 14.0D, 7.0D, 14.0D, 8.5D);
      q.curveTo(14.0D, 10.5D, 11.0D, 11.0D, 11.0D, 13.0D);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      g2.setStroke(new BasicStroke(2.0F, 1, 1));
      g2.translate(this.focusWidth, this.focusWidth);
      g2.setColor(enabled ? this.questionMarkColor : this.disabledQuestionMarkColor);
      g2.draw(q);
      g2.fill(new Float(9.8F, 14.8F, 2.4F, 2.4F));
   }

   public int getIconWidth() {
      return UIScale.scale(this.iconSize());
   }

   public int getIconHeight() {
      return UIScale.scale(this.iconSize());
   }

   private int iconSize() {
      return 22 + this.focusWidth * 2;
   }
}
