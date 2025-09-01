package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import javax.swing.UIManager;

public class FlatFileViewDirectoryIcon extends FlatAbstractIcon {
   private Path2D path;

   public FlatFileViewDirectoryIcon() {
      super(16, 16, UIManager.getColor("Objects.Grey"));
   }

   protected void paintIcon(Component c, Graphics2D g) {
      g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      if (this.path == null) {
         this.path = createFolderPath();
      }

      g.draw(this.path);
   }

   static Path2D createFolderPath() {
      double arc = 1.5D;
      double arc2 = 0.5D;
      return FlatUIUtils.createPath(14.5D, 13.5D - arc, -1.000000000002E12D, 14.5D, 13.5D, 14.5D - arc, 13.5D, 1.5D + arc, 13.5D, -1.000000000002E12D, 1.5D, 13.5D, 1.5D, 13.5D - arc, 1.5D, 2.5D + arc, -1.000000000002E12D, 1.5D, 2.5D, 1.5D + arc, 2.5D, 6.5D - arc2, 2.5D, -1.000000000002E12D, 6.5D, 2.5D, 6.5D + arc2, 2.5D + arc2, 8.5D, 4.5D, 14.5D - arc, 4.5D, -1.000000000002E12D, 14.5D, 4.5D, 14.5D, 4.5D + arc);
   }
}
