package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Float;
import java.util.Map;
import javax.swing.UIManager;

public class FlatTabbedPaneCloseIcon extends FlatAbstractIcon {
   @FlatStylingSupport.Styleable
   protected Dimension closeSize = UIManager.getDimension("TabbedPane.closeSize");
   @FlatStylingSupport.Styleable
   protected int closeArc = UIManager.getInt("TabbedPane.closeArc");
   @FlatStylingSupport.Styleable
   protected float closeCrossPlainSize = FlatUIUtils.getUIFloat("TabbedPane.closeCrossPlainSize", 7.5F);
   @FlatStylingSupport.Styleable
   protected float closeCrossFilledSize;
   @FlatStylingSupport.Styleable
   protected float closeCrossLineWidth;
   @FlatStylingSupport.Styleable
   protected Color closeBackground;
   @FlatStylingSupport.Styleable
   protected Color closeForeground;
   @FlatStylingSupport.Styleable
   protected Color closeHoverBackground;
   @FlatStylingSupport.Styleable
   protected Color closeHoverForeground;
   @FlatStylingSupport.Styleable
   protected Color closePressedBackground;
   @FlatStylingSupport.Styleable
   protected Color closePressedForeground;

   public FlatTabbedPaneCloseIcon() {
      super(16, 16, (Color)null);
      this.closeCrossFilledSize = FlatUIUtils.getUIFloat("TabbedPane.closeCrossFilledSize", this.closeCrossPlainSize);
      this.closeCrossLineWidth = FlatUIUtils.getUIFloat("TabbedPane.closeCrossLineWidth", 1.0F);
      this.closeBackground = UIManager.getColor("TabbedPane.closeBackground");
      this.closeForeground = UIManager.getColor("TabbedPane.closeForeground");
      this.closeHoverBackground = UIManager.getColor("TabbedPane.closeHoverBackground");
      this.closeHoverForeground = UIManager.getColor("TabbedPane.closeHoverForeground");
      this.closePressedBackground = UIManager.getColor("TabbedPane.closePressedBackground");
      this.closePressedForeground = UIManager.getColor("TabbedPane.closePressedForeground");
   }

   public Object applyStyleProperty(String key, Object value) {
      return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
   }

   public Map<String, Class<?>> getStyleableInfos() {
      return FlatStylingSupport.getAnnotatedStyleableInfos(this);
   }

   public Object getStyleableValue(String key) {
      return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
   }

   protected void paintIcon(Component c, Graphics2D g) {
      Color bg = FlatButtonUI.buttonStateColor(c, this.closeBackground, (Color)null, (Color)null, this.closeHoverBackground, this.closePressedBackground);
      if (bg != null) {
         g.setColor(FlatUIUtils.deriveColor(bg, c.getBackground()));
         g.fillRoundRect((this.width - this.closeSize.width) / 2, (this.height - this.closeSize.height) / 2, this.closeSize.width, this.closeSize.height, this.closeArc, this.closeArc);
      }

      Color fg = FlatButtonUI.buttonStateColor(c, this.closeForeground, (Color)null, (Color)null, this.closeHoverForeground, this.closePressedForeground);
      g.setColor(FlatUIUtils.deriveColor(fg, c.getForeground()));
      float mx = (float)(this.width / 2);
      float my = (float)(this.height / 2);
      float r = (bg != null ? this.closeCrossFilledSize : this.closeCrossPlainSize) / 2.0F;
      Path2D path = new Float(0, 4);
      path.moveTo((double)(mx - r), (double)(my - r));
      path.lineTo((double)(mx + r), (double)(my + r));
      path.moveTo((double)(mx - r), (double)(my + r));
      path.lineTo((double)(mx + r), (double)(my - r));
      g.setStroke(new BasicStroke(this.closeCrossLineWidth));
      g.draw(path);
   }
}
