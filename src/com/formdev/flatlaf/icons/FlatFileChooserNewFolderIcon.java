package com.formdev.flatlaf.icons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D.Float;
import javax.swing.UIManager;

public class FlatFileChooserNewFolderIcon extends FlatAbstractIcon {
   private final Color greenColor = UIManager.getColor("Actions.Green");

   public FlatFileChooserNewFolderIcon() {
      super(16, 16, UIManager.getColor("Actions.Grey"));
   }

   protected void paintIcon(Component c, Graphics2D g) {
      g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      g.setStroke(new BasicStroke(1.0F, 1, 1));
      g.draw(FlatFileViewDirectoryIcon.createFolderPath());
      g.setColor(this.greenColor);
      g.draw(new Float(5.5F, 9.0F, 10.5F, 9.0F));
      g.draw(new Float(8.0F, 6.5F, 8.0F, 11.5F));
   }
}
