package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D.Float;
import javax.swing.UIManager;

public class FlatFileChooserUpFolderIcon extends FlatAbstractIcon {
   private final Color blueColor = UIManager.getColor("Actions.Blue");

   public FlatFileChooserUpFolderIcon() {
      super(16, 16, UIManager.getColor("Actions.Grey"));
   }

   protected void paintIcon(Component c, Graphics2D g) {
      g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      g.setStroke(new BasicStroke(1.0F, 1, 1));
      g.draw(FlatFileViewDirectoryIcon.createFolderPath());
      g.setColor(this.blueColor);
      g.draw(new Float(8.0F, 6.5F, 8.0F, 11.5F));
      g.draw(FlatUIUtils.createPath(false, 5.5D, 9.0D, 8.0D, 6.5D, 10.5D, 9.0D));
   }
}
