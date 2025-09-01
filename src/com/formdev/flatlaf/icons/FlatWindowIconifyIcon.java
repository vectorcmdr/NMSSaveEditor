package com.formdev.flatlaf.icons;

import java.awt.Graphics2D;

public class FlatWindowIconifyIcon extends FlatWindowAbstractIcon {
   protected void paintIconAt1x(Graphics2D g, int x, int y, int width, int height, double scaleFactor) {
      int iw = (int)(10.0D * scaleFactor);
      int ih = (int)scaleFactor;
      int ix = x + (width - iw) / 2;
      int iy = y + (height - ih) / 2;
      g.fillRect(ix, iy, iw, ih);
   }
}
