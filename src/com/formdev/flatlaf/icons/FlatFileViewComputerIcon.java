package com.formdev.flatlaf.icons;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D.Float;
import javax.swing.UIManager;

public class FlatFileViewComputerIcon extends FlatAbstractIcon {
   public FlatFileViewComputerIcon() {
      super(16, 16, UIManager.getColor("Objects.Grey"));
   }

   protected void paintIcon(Component c, Graphics2D g) {
      g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      g.setStroke(new BasicStroke(1.0F, 1, 1));
      g.draw(new Float(2.5F, 3.5F, 11.0F, 7.0F, 2.0F, 2.0F));
      g.drawLine(8, 11, 8, 12);
      g.draw(new java.awt.geom.Line2D.Float(4.5F, 12.5F, 11.5F, 12.5F));
   }
}
