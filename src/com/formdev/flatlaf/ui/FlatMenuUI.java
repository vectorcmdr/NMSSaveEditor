package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.LoggingFacade;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.Map;
import java.util.function.Function;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRootPane;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.MenuBarUI;
import javax.swing.plaf.basic.BasicMenuItemUI;
import javax.swing.plaf.basic.BasicMenuUI;
import javax.swing.plaf.basic.BasicMenuUI.MouseInputHandler;

@FlatStylingSupport.StyleableFields({@FlatStylingSupport.StyleableField(
   cls = BasicMenuItemUI.class,
   key = "selectionBackground"
), @FlatStylingSupport.StyleableField(
   cls = BasicMenuItemUI.class,
   key = "selectionForeground"
), @FlatStylingSupport.StyleableField(
   cls = BasicMenuItemUI.class,
   key = "disabledForeground"
), @FlatStylingSupport.StyleableField(
   cls = BasicMenuItemUI.class,
   key = "acceleratorForeground"
), @FlatStylingSupport.StyleableField(
   cls = BasicMenuItemUI.class,
   key = "acceleratorSelectionForeground"
)})
public class FlatMenuUI extends BasicMenuUI implements FlatStylingSupport.StyleableUI, FlatStylingSupport.StyleableLookupProvider {
   private FlatMenuItemRenderer renderer;
   private Map<String, Object> oldStyleValues;

   public static ComponentUI createUI(JComponent c) {
      return new FlatMenuUI();
   }

   public void installUI(JComponent c) {
      super.installUI(c);
      this.installStyle();
   }

   protected void installDefaults() {
      super.installDefaults();
      LookAndFeel.installProperty(this.menuItem, "iconTextGap", FlatUIUtils.getUIInt("MenuItem.iconTextGap", 4));
      this.menuItem.setRolloverEnabled(true);
      this.renderer = this.createRenderer();
   }

   protected void uninstallDefaults() {
      super.uninstallDefaults();
      FlatMenuItemRenderer.clearClientProperties(this.menuItem.getParent());
      this.renderer = null;
      this.oldStyleValues = null;
   }

   protected FlatMenuItemRenderer createRenderer() {
      return new FlatMenuUI.FlatMenuRenderer(this.menuItem, this.checkIcon, this.arrowIcon, this.acceleratorFont, this.acceleratorDelimiter);
   }

   protected MouseInputListener createMouseInputListener(JComponent c) {
      return new MouseInputHandler() {
         public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            this.rollover(e, true);
         }

         public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            this.rollover(e, false);
         }

         private void rollover(MouseEvent e, boolean rollover) {
            JMenu menu = (JMenu)e.getSource();
            if (menu.isTopLevelMenu() && menu.isRolloverEnabled()) {
               menu.getModel().setRollover(rollover);
               menu.repaint();
            }

         }
      };
   }

   protected PropertyChangeListener createPropertyChangeListener(JComponent c) {
      return FlatStylingSupport.createPropertyChangeListener(c, this::installStyle, super.createPropertyChangeListener(c));
   }

   protected void installStyle() {
      try {
         this.applyStyle(FlatStylingSupport.getResolvedStyle(this.menuItem, "Menu"));
      } catch (RuntimeException var2) {
         LoggingFacade.INSTANCE.logSevere((String)null, var2);
      }

   }

   protected void applyStyle(Object style) {
      this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
   }

   protected Object applyStyleProperty(String key, Object value) {
      return FlatMenuItemUI.applyStyleProperty(this.menuItem, this, this.renderer, key, value);
   }

   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
      return FlatMenuItemUI.getStyleableInfos(this, this.renderer);
   }

   public Object getStyleableValue(JComponent c, String key) {
      return FlatMenuItemUI.getStyleableValue(this, this.renderer, key);
   }

   public Lookup getLookupForStyling() {
      return MethodHandles.lookup();
   }

   public Dimension getMinimumSize(JComponent c) {
      return ((JMenu)this.menuItem).isTopLevelMenu() ? c.getPreferredSize() : null;
   }

   protected Dimension getPreferredMenuItemSize(JComponent c, Icon checkIcon, Icon arrowIcon, int defaultTextIconGap) {
      return this.renderer.getPreferredMenuItemSize();
   }

   public void paint(Graphics g, JComponent c) {
      this.renderer.paintMenuItem(g, this.selectionBackground, this.selectionForeground, this.disabledForeground, this.acceleratorForeground, this.acceleratorSelectionForeground);
   }

   protected class FlatMenuRenderer extends FlatMenuItemRenderer {
      protected Insets menuBarSelectionInsets = UIManager.getInsets("MenuBar.selectionInsets");
      protected Insets menuBarSelectionEmbeddedInsets = UIManager.getInsets("MenuBar.selectionEmbeddedInsets");
      protected int menuBarSelectionArc = UIManager.getInt("MenuBar.selectionArc");
      protected Color hoverBackground = UIManager.getColor("MenuBar.hoverBackground");
      protected Color menuBarSelectionBackground = UIManager.getColor("MenuBar.selectionBackground");
      protected Color menuBarSelectionForeground = UIManager.getColor("MenuBar.selectionForeground");
      protected Color menuBarUnderlineSelectionBackground = UIManager.getColor("MenuBar.underlineSelectionBackground");
      protected Color menuBarUnderlineSelectionColor = UIManager.getColor("MenuBar.underlineSelectionColor");
      protected int menuBarUnderlineSelectionHeight = FlatUIUtils.getUIInt("MenuBar.underlineSelectionHeight", -1);

      protected FlatMenuRenderer(JMenuItem menuItem, Icon checkIcon, Icon arrowIcon, Font acceleratorFont, String acceleratorDelimiter) {
         super(menuItem, checkIcon, arrowIcon, acceleratorFont, acceleratorDelimiter);
      }

      protected void paintBackground(Graphics g) {
         super.paintBackground(g);
         if (((JMenu)this.menuItem).isTopLevelMenu() && this.isHover()) {
            Color color = this.deriveBackground((Color)this.getStyleFromMenuBarUI((ui) -> {
               return ui.hoverBackground;
            }, this.hoverBackground));
            if (this.isUnderlineSelection()) {
               g.setColor(color);
               g.fillRect(0, 0, this.menuItem.getWidth(), this.menuItem.getHeight());
            } else {
               this.paintSelection(g, color, this.selectionInsets, this.selectionArc);
            }
         }

      }

      protected void paintSelection(Graphics g, Color selectionBackground, Insets selectionInsets, int selectionArc) {
         if (((JMenu)this.menuItem).isTopLevelMenu()) {
            if (!this.isHover()) {
               selectionBackground = (Color)this.getStyleFromMenuBarUI((ui) -> {
                  return ui.selectionBackground;
               }, this.menuBarSelectionBackground, selectionBackground);
            }

            JMenuBar menuBar = (JMenuBar)this.menuItem.getParent();
            JRootPane rootPane = SwingUtilities.getRootPane(menuBar);
            if (rootPane != null && rootPane.getParent() instanceof Window && rootPane.getJMenuBar() == menuBar && FlatRootPaneUI.isMenuBarEmbedded(rootPane)) {
               selectionInsets = (Insets)this.getStyleFromMenuBarUI((ui) -> {
                  return ui.selectionEmbeddedInsets;
               }, this.menuBarSelectionEmbeddedInsets);
            } else {
               selectionInsets = (Insets)this.getStyleFromMenuBarUI((ui) -> {
                  return ui.selectionInsets;
               }, this.menuBarSelectionInsets);
            }

            selectionArc = (Integer)this.getStyleFromMenuBarUI((ui) -> {
               return ui.selectionArc != -1 ? ui.selectionArc : null;
            }, this.menuBarSelectionArc);
         }

         super.paintSelection(g, selectionBackground, selectionInsets, selectionArc);
      }

      protected void paintUnderlineSelection(Graphics g, Color underlineSelectionBackground, Color underlineSelectionColor, int underlineSelectionHeight) {
         if (((JMenu)this.menuItem).isTopLevelMenu()) {
            underlineSelectionBackground = (Color)this.getStyleFromMenuBarUI((ui) -> {
               return ui.underlineSelectionBackground;
            }, this.menuBarUnderlineSelectionBackground, underlineSelectionBackground);
            underlineSelectionColor = (Color)this.getStyleFromMenuBarUI((ui) -> {
               return ui.underlineSelectionColor;
            }, this.menuBarUnderlineSelectionColor, underlineSelectionColor);
            underlineSelectionHeight = (Integer)this.getStyleFromMenuBarUI((ui) -> {
               return ui.underlineSelectionHeight != -1 ? ui.underlineSelectionHeight : null;
            }, this.menuBarUnderlineSelectionHeight != -1 ? this.menuBarUnderlineSelectionHeight : underlineSelectionHeight);
         }

         super.paintUnderlineSelection(g, underlineSelectionBackground, underlineSelectionColor, underlineSelectionHeight);
      }

      protected void paintText(Graphics g, Rectangle textRect, String text, Color selectionForeground, Color disabledForeground) {
         if (((JMenu)this.menuItem).isTopLevelMenu() && !this.isUnderlineSelection()) {
            selectionForeground = (Color)this.getStyleFromMenuBarUI((ui) -> {
               return ui.selectionForeground;
            }, this.menuBarSelectionForeground, selectionForeground);
         }

         super.paintText(g, textRect, text, selectionForeground, disabledForeground);
      }

      private boolean isHover() {
         ButtonModel model = this.menuItem.getModel();
         return model.isRollover() && !model.isArmed() && !model.isSelected() && model.isEnabled();
      }

      private <T> T getStyleFromMenuBarUI(Function<FlatMenuBarUI, T> f, T defaultValue, T defaultValue2) {
         return this.getStyleFromMenuBarUI(f, defaultValue != null ? defaultValue : defaultValue2);
      }

      private <T> T getStyleFromMenuBarUI(Function<FlatMenuBarUI, T> f, T defaultValue) {
         MenuBarUI ui = ((JMenuBar)this.menuItem.getParent()).getUI();
         if (!(ui instanceof FlatMenuBarUI)) {
            return defaultValue;
         } else {
            T value = f.apply((FlatMenuBarUI)ui);
            return value != null ? value : defaultValue;
         }
      }
   }
}
