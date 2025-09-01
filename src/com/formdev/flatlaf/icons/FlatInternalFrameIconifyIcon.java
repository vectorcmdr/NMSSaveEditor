package com.formdev.flatlaf.icons;

import java.awt.Component;
import java.awt.Graphics2D;

public class FlatInternalFrameIconifyIcon extends FlatInternalFrameAbstractIcon {
   protected void paintIcon(Component c, Graphics2D g) {
      this.paintBackground(c, g);
      g.setColor(c.getForeground());
      g.fillRect(this.width / 2 - 4, this.height / 2, 8, 1);
   }
}
