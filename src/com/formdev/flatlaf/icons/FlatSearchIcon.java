package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D.Float;
import java.util.Map;
import javax.swing.UIManager;

public class FlatSearchIcon extends FlatAbstractIcon {
   @FlatStylingSupport.Styleable
   protected Color searchIconColor;
   @FlatStylingSupport.Styleable
   protected Color searchIconHoverColor;
   @FlatStylingSupport.Styleable
   protected Color searchIconPressedColor;
   private final boolean ignoreButtonState;
   private Area area;

   public FlatSearchIcon() {
      this(false);
   }

   public FlatSearchIcon(boolean ignoreButtonState) {
      super(16, 16, (Color)null);
      this.searchIconColor = UIManager.getColor("SearchField.searchIconColor");
      this.searchIconHoverColor = UIManager.getColor("SearchField.searchIconHoverColor");
      this.searchIconPressedColor = UIManager.getColor("SearchField.searchIconPressedColor");
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
      g.setColor(this.ignoreButtonState ? this.searchIconColor : FlatButtonUI.buttonStateColor(c, this.searchIconColor, this.searchIconColor, (Color)null, this.searchIconHoverColor, this.searchIconPressedColor));
      if (this.area == null) {
         this.area = new Area(new Float(2.0F, 2.0F, 10.0F, 10.0F));
         this.area.subtract(new Area(new Float(3.0F, 3.0F, 8.0F, 8.0F)));
         this.area.add(new Area(FlatUIUtils.createPath(10.813D, 9.75D, 14.0D, 12.938D, 12.938D, 14.0D, 9.75D, 10.813D)));
      }

      g.fill(this.area);
   }
}
