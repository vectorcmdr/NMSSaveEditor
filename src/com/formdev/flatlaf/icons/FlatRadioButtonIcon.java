package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatStylingSupport;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D.Float;

public class FlatRadioButtonIcon extends FlatCheckBoxIcon {
   @FlatStylingSupport.Styleable
   protected float centerDiameter;

   public FlatRadioButtonIcon() {
      this.centerDiameter = getUIFloat("RadioButton.icon.centerDiameter", 8.0F, this.style);
   }

   protected String getPropertyPrefix() {
      return "RadioButton.";
   }

   protected void paintFocusBorder(Component c, Graphics2D g) {
      float wh = 15.0F + this.focusWidth * 2.0F;
      g.fill(new Float(-this.focusWidth, -this.focusWidth, wh, wh));
   }

   protected void paintBorder(Component c, Graphics2D g, float borderWidth) {
      if (borderWidth != 0.0F) {
         g.fillOval(0, 0, 15, 15);
      }
   }

   protected void paintBackground(Component c, Graphics2D g, float borderWidth) {
      float wh = 15.0F - borderWidth * 2.0F;
      g.fill(new Float(borderWidth, borderWidth, wh, wh));
   }

   protected void paintCheckmark(Component c, Graphics2D g) {
      float xy = (15.0F - this.centerDiameter) / 2.0F;
      g.fill(new Float(xy, xy, this.centerDiameter, this.centerDiameter));
   }
}
