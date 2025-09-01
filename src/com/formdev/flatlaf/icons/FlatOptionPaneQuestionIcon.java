package com.formdev.flatlaf.icons;

import java.awt.BasicStroke;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Ellipse2D.Float;

public class FlatOptionPaneQuestionIcon extends FlatOptionPaneAbstractIcon {
   public FlatOptionPaneQuestionIcon() {
      super("OptionPane.icon.questionColor", "Actions.Blue");
   }

   protected Shape createOutside() {
      return new Float(2.0F, 2.0F, 28.0F, 28.0F);
   }

   protected Shape createInside() {
      Path2D q = new java.awt.geom.Path2D.Float(1, 10);
      q.moveTo(11.5D, 11.75D);
      q.curveTo(11.75D, 9.5D, 13.75D, 8.0D, 16.0D, 8.0D);
      q.curveTo(18.25D, 8.0D, 20.5D, 9.5D, 20.5D, 11.75D);
      q.curveTo(20.5D, 14.75D, 16.0D, 15.5D, 16.0D, 19.0D);
      BasicStroke stroke = new BasicStroke(3.0F, 1, 0);
      Path2D inside = new java.awt.geom.Path2D.Float(0);
      inside.append(new Float(14.3F, 22.3F, 3.4F, 3.4F), false);
      inside.append(stroke.createStrokedShape(q), false);
      return inside;
   }
}
