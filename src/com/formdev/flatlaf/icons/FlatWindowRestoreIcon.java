package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D.Float;

public class FlatWindowRestoreIcon extends FlatWindowAbstractIcon {
   protected void paintIconAt1x(Graphics2D g, int x, int y, int width, int height, double scaleFactor) {
      int iwh = (int)(10.0D * scaleFactor);
      int ix = x + (width - iwh) / 2;
      int iy = y + (height - iwh) / 2;
      float thickness = SystemInfo.isWindows_11_orLater ? (float)scaleFactor : (float)((int)scaleFactor);
      int arc = Math.max((int)(1.5D * scaleFactor), 2);
      int arcOuter = (int)((double)arc + 1.5D * scaleFactor);
      int rwh = (int)(8.0D * scaleFactor);
      int ro2 = iwh - rwh;
      Path2D r1 = SystemInfo.isWindows_11_orLater ? FlatUIUtils.createRoundRectangle((float)(ix + ro2), (float)iy, (float)rwh, (float)rwh, thickness, (float)arc, (float)arcOuter, (float)arc, (float)arc) : FlatUIUtils.createRectangle((float)(ix + ro2), (float)iy, (float)rwh, (float)rwh, thickness);
      Path2D r2 = SystemInfo.isWindows_11_orLater ? FlatUIUtils.createRoundRectangle((float)ix, (float)(iy + ro2), (float)rwh, (float)rwh, thickness, (float)arc, (float)arc, (float)arc, (float)arc) : FlatUIUtils.createRectangle((float)ix, (float)(iy + ro2), (float)rwh, (float)rwh, thickness);
      Area area = new Area(r1);
      if (SystemInfo.isWindows_11_orLater) {
         area.subtract(new Area(new Float((float)ix, (float)((double)iy + scaleFactor), (float)rwh, (float)rwh)));
         area.subtract(new Area(new Float((float)((double)ix + scaleFactor), (float)(iy + ro2), (float)rwh, (float)rwh)));
      } else {
         area.subtract(new Area(new Float((float)ix, (float)(iy + ro2), (float)rwh, (float)rwh)));
      }

      g.fill(area);
      g.fill(r2);
   }
}
