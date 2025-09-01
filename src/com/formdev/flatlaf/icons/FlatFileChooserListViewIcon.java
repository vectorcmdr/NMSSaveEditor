package com.formdev.flatlaf.icons;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D.Float;
import javax.swing.UIManager;

public class FlatFileChooserListViewIcon extends FlatAbstractIcon {
   public FlatFileChooserListViewIcon() {
      super(16, 16, UIManager.getColor("Actions.Grey"));
   }

   protected void paintIcon(Component c, Graphics2D g) {
      g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      g.setStroke(new BasicStroke(1.0F, 1, 1));
      g.draw(new Float(2.5F, 2.5F, 4.0F, 4.0F, 2.0F, 2.0F));
      g.draw(new Float(2.5F, 9.5F, 4.0F, 4.0F, 2.0F, 2.0F));
      g.draw(new Float(9.5F, 9.5F, 4.0F, 4.0F, 2.0F, 2.0F));
      g.draw(new Float(9.5F, 2.5F, 4.0F, 4.0F, 2.0F, 2.0F));
   }
}
