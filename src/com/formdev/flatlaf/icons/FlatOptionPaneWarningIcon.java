package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Float;

public class FlatOptionPaneWarningIcon extends FlatOptionPaneAbstractIcon {
   public FlatOptionPaneWarningIcon() {
      super("OptionPane.icon.warningColor", "Actions.Yellow");
   }

   protected Shape createOutside() {
      return FlatUIUtils.createRoundTrianglePath(16.0F, 0.0F, 32.0F, 28.0F, 0.0F, 28.0F, 4.0F);
   }

   protected Shape createInside() {
      Path2D inside = new Float(0);
      inside.append(new java.awt.geom.RoundRectangle2D.Float(14.0F, 8.0F, 4.0F, 11.0F, 4.0F, 4.0F), false);
      inside.append(new java.awt.geom.Ellipse2D.Float(14.0F, 21.0F, 4.0F, 4.0F), false);
      return inside;
   }
}
