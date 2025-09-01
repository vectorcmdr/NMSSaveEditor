package com.formdev.flatlaf.icons;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Float;
import javax.swing.UIManager;

public class FlatRevealIcon extends FlatAbstractIcon {
   public FlatRevealIcon() {
      super(16, 16, UIManager.getColor("PasswordField.revealIconColor"));
   }

   protected void paintIcon(Component c, Graphics2D g) {
      Path2D path = new Float(0);
      path.append(new java.awt.geom.Ellipse2D.Float(5.15F, 6.15F, 5.7F, 5.7F), false);
      path.append(new java.awt.geom.Ellipse2D.Float(6.0F, 7.0F, 4.0F, 4.0F), false);
      g.fill(path);
      Path2D path2 = new Float(0);
      path2.append(new java.awt.geom.Ellipse2D.Float(2.15F, 4.15F, 11.7F, 11.7F), false);
      path2.append(new java.awt.geom.Ellipse2D.Float(3.0F, 5.0F, 10.0F, 10.0F), false);
      Area area = new Area(path2);
      area.subtract(new Area(new java.awt.geom.Rectangle2D.Float(0.0F, 9.5F, 16.0F, 16.0F)));
      g.fill(area);
   }
}
