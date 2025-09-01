package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Float;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.UIManager;

public class FlatClearIcon extends FlatAbstractIcon {
   @FlatStylingSupport.Styleable
   protected Color clearIconColor;
   @FlatStylingSupport.Styleable
   protected Color clearIconHoverColor;
   @FlatStylingSupport.Styleable
   protected Color clearIconPressedColor;
   private final boolean ignoreButtonState;

   public FlatClearIcon() {
      this(false);
   }

   public FlatClearIcon(boolean ignoreButtonState) {
      super(16, 16, (Color)null);
      this.clearIconColor = UIManager.getColor("SearchField.clearIconColor");
      this.clearIconHoverColor = UIManager.getColor("SearchField.clearIconHoverColor");
      this.clearIconPressedColor = UIManager.getColor("SearchField.clearIconPressedColor");
      this.ignoreButtonState = ignoreButtonState;
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
      if (!this.ignoreButtonState && c instanceof AbstractButton) {
         ButtonModel model = ((AbstractButton)c).getModel();
         if (model.isPressed() || model.isRollover()) {
            g.setColor(model.isPressed() ? this.clearIconPressedColor : this.clearIconHoverColor);
            Path2D path = new Float(0);
            path.append(new java.awt.geom.Ellipse2D.Float(1.75F, 1.75F, 12.5F, 12.5F), false);
            path.append(FlatUIUtils.createPath(4.5D, 5.5D, 5.5D, 4.5D, 8.0D, 7.0D, 10.5D, 4.5D, 11.5D, 5.5D, 9.0D, 8.0D, 11.5D, 10.5D, 10.5D, 11.5D, 8.0D, 9.0D, 5.5D, 11.5D, 4.5D, 10.5D, 7.0D, 8.0D), false);
            g.fill(path);
            return;
         }
      }

      g.setColor(this.clearIconColor);
      Path2D path = new Float(0, 4);
      path.moveTo(5.0D, 5.0D);
      path.lineTo(11.0D, 11.0D);
      path.moveTo(5.0D, 11.0D);
      path.lineTo(11.0D, 5.0D);
      g.draw(path);
   }
}
