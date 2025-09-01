package com.formdev.flatlaf.icons;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Ellipse2D.Float;

public class FlatOptionPaneInformationIcon extends FlatOptionPaneAbstractIcon {
   public FlatOptionPaneInformationIcon() {
      super("OptionPane.icon.informationColor", "Actions.Blue");
   }

   protected Shape createOutside() {
      return new Float(2.0F, 2.0F, 28.0F, 28.0F);
   }

   protected Shape createInside() {
      Path2D inside = new java.awt.geom.Path2D.Float(0);
      inside.append(new java.awt.geom.RoundRectangle2D.Float(14.0F, 13.0F, 4.0F, 12.0F, 4.0F, 4.0F), false);
      inside.append(new Float(14.0F, 7.0F, 4.0F, 4.0F), false);
      return inside;
   }
}
