package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.LoggingFacade;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.PropertyChangeListener;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuItemUI;
import javax.swing.plaf.basic.BasicRadioButtonMenuItemUI;

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
public class FlatRadioButtonMenuItemUI extends BasicRadioButtonMenuItemUI implements FlatStylingSupport.StyleableUI, FlatStylingSupport.StyleableLookupProvider {
   private FlatMenuItemRenderer renderer;
   private Map<String, Object> oldStyleValues;

   public static ComponentUI createUI(JComponent c) {
      return new FlatRadioButtonMenuItemUI();
   }

   public void installUI(JComponent c) {
      super.installUI(c);
      this.installStyle();
   }

   protected void installDefaults() {
      super.installDefaults();
      LookAndFeel.installProperty(this.menuItem, "iconTextGap", FlatUIUtils.getUIInt("MenuItem.iconTextGap", 4));
      this.renderer = this.createRenderer();
   }

   protected void uninstallDefaults() {
      super.uninstallDefaults();
      FlatMenuItemRenderer.clearClientProperties(this.menuItem.getParent());
      this.renderer = null;
      this.oldStyleValues = null;
   }

   protected FlatMenuItemRenderer createRenderer() {
      return new FlatMenuItemRenderer(this.menuItem, this.checkIcon, this.arrowIcon, this.acceleratorFont, this.acceleratorDelimiter);
   }

   protected PropertyChangeListener createPropertyChangeListener(JComponent c) {
      return FlatStylingSupport.createPropertyChangeListener(c, this::installStyle, super.createPropertyChangeListener(c));
   }

   protected void installStyle() {
      try {
         this.applyStyle(FlatStylingSupport.getResolvedStyle(this.menuItem, "RadioButtonMenuItem"));
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

   protected Dimension getPreferredMenuItemSize(JComponent c, Icon checkIcon, Icon arrowIcon, int defaultTextIconGap) {
      return this.renderer.getPreferredMenuItemSize();
   }

   public void paint(Graphics g, JComponent c) {
      this.renderer.paintMenuItem(g, this.selectionBackground, this.selectionForeground, this.disabledForeground, this.acceleratorForeground, this.acceleratorSelectionForeground);
   }
}
