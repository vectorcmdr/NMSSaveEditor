package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;

public class FlatToggleButtonUI extends FlatButtonUI {
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected int tabUnderlineHeight;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color tabUnderlineColor;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color tabDisabledUnderlineColor;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color tabSelectedBackground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color tabSelectedForeground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color tabHoverBackground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color tabHoverForeground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color tabFocusBackground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color tabFocusForeground;
   private boolean defaults_initialized = false;

   public static ComponentUI createUI(JComponent c) {
      return (ComponentUI)(FlatUIUtils.canUseSharedUI(c) ? FlatUIUtils.createSharedUI(FlatToggleButtonUI.class, () -> {
         return new FlatToggleButtonUI(true);
      }) : new FlatToggleButtonUI(false));
   }

   protected FlatToggleButtonUI(boolean shared) {
      super(shared);
   }

   String getStyleType() {
      return "ToggleButton";
   }

   protected String getPropertyPrefix() {
      return "ToggleButton.";
   }

   protected void installDefaults(AbstractButton b) {
      super.installDefaults(b);
      if (!this.defaults_initialized) {
         this.tabUnderlineHeight = UIManager.getInt("ToggleButton.tab.underlineHeight");
         this.tabUnderlineColor = UIManager.getColor("ToggleButton.tab.underlineColor");
         this.tabDisabledUnderlineColor = UIManager.getColor("ToggleButton.tab.disabledUnderlineColor");
         this.tabSelectedBackground = UIManager.getColor("ToggleButton.tab.selectedBackground");
         this.tabSelectedForeground = UIManager.getColor("ToggleButton.tab.selectedForeground");
         this.tabHoverBackground = UIManager.getColor("ToggleButton.tab.hoverBackground");
         this.tabHoverForeground = UIManager.getColor("ToggleButton.tab.hoverForeground");
         this.tabFocusBackground = UIManager.getColor("ToggleButton.tab.focusBackground");
         this.tabFocusForeground = UIManager.getColor("ToggleButton.tab.focusForeground");
         this.defaults_initialized = true;
      }

   }

   protected void uninstallDefaults(AbstractButton b) {
      super.uninstallDefaults(b);
      this.defaults_initialized = false;
   }

   protected void propertyChange(AbstractButton b, PropertyChangeEvent e) {
      super.propertyChange(b, e);
      String var3 = e.getPropertyName();
      byte var4 = -1;
      switch(var3.hashCode()) {
      case -1405676274:
         if (var3.equals("JToggleButton.tab.underlineColor")) {
            var4 = 3;
         }
         break;
      case -1336690752:
         if (var3.equals("JToggleButton.tab.selectedBackground")) {
            var4 = 4;
         }
         break;
      case -698537200:
         if (var3.equals("JToggleButton.tab.underlinePlacement")) {
            var4 = 1;
         }
         break;
      case -492478244:
         if (var3.equals("JToggleButton.tab.underlineHeight")) {
            var4 = 2;
         }
         break;
      case 1428734622:
         if (var3.equals("JButton.buttonType")) {
            var4 = 0;
         }
      }

      switch(var4) {
      case 0:
         if ("tab".equals(e.getOldValue()) || "tab".equals(e.getNewValue())) {
            MigLayoutVisualPadding.uninstall(b);
            MigLayoutVisualPadding.install(b);
            b.revalidate();
         }

         b.repaint();
         break;
      case 1:
      case 2:
      case 3:
      case 4:
         b.repaint();
      }

   }

   protected Object applyStyleProperty(AbstractButton b, String key, Object value) {
      if (key.startsWith("help.")) {
         throw new FlatStylingSupport.UnknownStyleException(key);
      } else {
         return super.applyStyleProperty(b, key, value);
      }
   }

   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
      Map<String, Class<?>> infos = super.getStyleableInfos(c);
      infos.keySet().removeIf((s) -> {
         return s.startsWith("help.");
      });
      return infos;
   }

   static boolean isTabButton(Component c) {
      return c instanceof JToggleButton && "tab".equals(getButtonTypeStr((JToggleButton)c));
   }

   protected void paintBackground(Graphics g, JComponent c) {
      if (isTabButton(c)) {
         int height = c.getHeight();
         int width = c.getWidth();
         boolean selected = ((AbstractButton)c).isSelected();
         Color enabledColor = selected ? FlatClientProperties.clientPropertyColor(c, "JToggleButton.tab.selectedBackground", this.tabSelectedBackground) : null;
         Color background;
         if (enabledColor == null) {
            background = c.getBackground();
            if (this.isCustomBackground(background)) {
               enabledColor = background;
            }
         }

         background = buttonStateColor(c, enabledColor, (Color)null, this.tabFocusBackground, this.tabHoverBackground, (Color)null);
         if (background != null) {
            g.setColor(background);
            g.fillRect(0, 0, width, height);
         }

         if (selected) {
            int underlineThickness = UIScale.scale(FlatClientProperties.clientPropertyInt(c, "JToggleButton.tab.underlineHeight", this.tabUnderlineHeight));
            g.setColor(c.isEnabled() ? FlatClientProperties.clientPropertyColor(c, "JToggleButton.tab.underlineColor", this.tabUnderlineColor) : this.tabDisabledUnderlineColor);
            int placement = FlatClientProperties.clientPropertyInt(c, "JToggleButton.tab.underlinePlacement", 3);
            switch(placement) {
            case 1:
               g.fillRect(0, 0, width, underlineThickness);
               break;
            case 2:
               g.fillRect(0, 0, underlineThickness, height);
               break;
            case 3:
            default:
               g.fillRect(0, height - underlineThickness, width, underlineThickness);
               break;
            case 4:
               g.fillRect(width - underlineThickness, 0, underlineThickness, height);
            }
         }
      } else {
         super.paintBackground(g, c);
      }

   }

   protected Color getForeground(JComponent c) {
      if (isTabButton(c)) {
         if (!c.isEnabled()) {
            return this.disabledText;
         } else {
            return this.tabSelectedForeground != null && ((AbstractButton)c).isSelected() ? this.tabSelectedForeground : buttonStateColor(c, c.getForeground(), this.disabledText, this.tabFocusForeground, this.tabHoverForeground, (Color)null);
         }
      } else {
         return super.getForeground(c);
      }
   }
}
