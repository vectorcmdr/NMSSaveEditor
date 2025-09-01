package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.UIManager;

public class FlatFileChooserHomeFolderIcon extends FlatAbstractIcon {
   public FlatFileChooserHomeFolderIcon() {
      super(16, 16, UIManager.getColor("Actions.Grey"));
   }

   protected void paintIcon(Component c, Graphics2D g) {
      g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      g.setStroke(new BasicStroke(1.0F, 1, 1));
      g.draw(FlatUIUtils.createPath(false, 6.5D, 13.0D, 6.5D, 9.5D, 9.5D, 9.5D, 9.5D, 13.0D));
      g.draw(FlatUIUtils.createPath(false, 3.5D, 6.5D, 3.5D, 12.5D, -1.000000000002E12D, 3.5D, 13.5D, 4.5D, 13.5D, 11.5D, 13.5D, -1.000000000002E12D, 12.5D, 13.5D, 12.5D, 12.5D, 12.5D, 6.5D));
      g.draw(FlatUIUtils.createPath(false, 1.5D, 8.5D, 8.0D, 2.0D, 14.5D, 8.5D));
   }
}
