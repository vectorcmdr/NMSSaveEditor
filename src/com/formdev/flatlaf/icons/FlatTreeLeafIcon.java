package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.util.ColorFunctions;
import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D.Float;
import javax.swing.UIManager;

public class FlatTreeLeafIcon extends FlatAbstractIcon {
   public FlatTreeLeafIcon() {
      super(16, 16, UIManager.getColor("Tree.icon.leafColor"));
   }

   protected void paintIcon(Component c, Graphics2D g) {
      FlatTreeCollapsedIcon.setStyleColorFromTreeUI(c, g, (ui) -> {
         return ui.iconLeafColor;
      });
      g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      g.setStroke(new BasicStroke(1.0F, 1, 1));
      g.draw(new Float(2.5F, 1.5F, 11.0F, 13.0F, 3.0F, 3.0F));
      g.setColor(ColorFunctions.fade(g.getColor(), 0.6F));
      g.draw(new java.awt.geom.Line2D.Float(5.5F, 5.5F, 10.5F, 5.5F));
      g.draw(new java.awt.geom.Line2D.Float(5.5F, 8.0F, 10.5F, 8.0F));
      g.draw(new java.awt.geom.Line2D.Float(5.5F, 10.5F, 10.5F, 10.5F));
   }
}
