package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class FlatDescendingSortIcon extends FlatAscendingSortIcon {
   protected void paintArrow(Component c, Graphics2D g, boolean chevron) {
      if (chevron) {
         Path2D path = FlatUIUtils.createPath(false, 1.0D, 0.0D, 5.0D, 4.0D, 9.0D, 0.0D);
         g.setStroke(new BasicStroke(1.0F));
         g.draw(path);
      } else {
         g.fill(FlatUIUtils.createPath(0.5D, 0.0D, 5.0D, 5.0D, 9.5D, 0.0D));
      }

   }
}
