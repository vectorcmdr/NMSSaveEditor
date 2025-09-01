package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import javax.swing.UIManager;

public class FlatFileViewFileIcon extends FlatAbstractIcon {
   private Path2D path;

   public FlatFileViewFileIcon() {
      super(16, 16, UIManager.getColor("Objects.Grey"));
   }

   protected void paintIcon(Component c, Graphics2D g) {
      g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      g.setStroke(new BasicStroke(1.0F, 1, 1));
      if (this.path == null) {
         double arc = 1.5D;
         this.path = FlatUIUtils.createPath(false, 2.5D, 1.5D + arc, -1.000000000002E12D, 2.5D, 1.5D, 2.5D + arc, 1.5D, 8.8D, 1.5D, 13.5D, 6.2D, 13.5D, 14.5D - arc, -1.000000000002E12D, 13.5D, 14.5D, 13.5D - arc, 14.5D, 2.5D + arc, 14.5D, -1.000000000002E12D, 2.5D, 14.5D, 2.5D, 14.5D - arc, -1.000000000005E12D, -1.000000000001E12D, 8.5D, 2.0D, 8.5D, 6.5D - arc, -1.000000000002E12D, 8.5D, 6.5D, 8.5D + arc, 6.5D, 13.0D, 6.5D);
      }

      g.draw(this.path);
   }
}
