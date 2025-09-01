package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Graphics2D;

public class FlatWindowMaximizeIcon extends FlatWindowAbstractIcon {
   protected void paintIconAt1x(Graphics2D g, int x, int y, int width, int height, double scaleFactor) {
      int iwh = (int)(10.0D * scaleFactor);
      int ix = x + (width - iwh) / 2;
      int iy = y + (height - iwh) / 2;
      float thickness = SystemInfo.isWindows_11_orLater ? (float)scaleFactor : (float)((int)scaleFactor);
      int arc = Math.max((int)(1.5D * scaleFactor), 2);
      g.fill(SystemInfo.isWindows_11_orLater ? FlatUIUtils.createRoundRectangle((float)ix, (float)iy, (float)iwh, (float)iwh, thickness, (float)arc, (float)arc, (float)arc, (float)arc) : FlatUIUtils.createRectangle((float)ix, (float)iy, (float)iwh, (float)iwh, thickness));
   }
}
