package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatButtonUI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Float;
import javax.swing.UIManager;

public class FlatInternalFrameCloseIcon extends FlatInternalFrameAbstractIcon {
   private final Color hoverForeground = UIManager.getColor("InternalFrame.closeHoverForeground");
   private final Color pressedForeground = UIManager.getColor("InternalFrame.closePressedForeground");

   public FlatInternalFrameCloseIcon() {
      super(UIManager.getDimension("InternalFrame.buttonSize"), UIManager.getColor("InternalFrame.closeHoverBackground"), UIManager.getColor("InternalFrame.closePressedBackground"));
   }

   protected void paintIcon(Component c, Graphics2D g) {
      this.paintBackground(c, g);
      g.setColor(FlatButtonUI.buttonStateColor(c, c.getForeground(), (Color)null, (Color)null, this.hoverForeground, this.pressedForeground));
      float mx = (float)(this.width / 2);
      float my = (float)(this.height / 2);
      float r = 3.25F;
      Path2D path = new Float(0, 4);
      path.moveTo((double)(mx - r), (double)(my - r));
      path.lineTo((double)(mx + r), (double)(my + r));
      path.moveTo((double)(mx - r), (double)(my + r));
      path.lineTo((double)(mx + r), (double)(my - r));
      g.setStroke(new BasicStroke(1.0F));
      g.draw(path);
   }
}
