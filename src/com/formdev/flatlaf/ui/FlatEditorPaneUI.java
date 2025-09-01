package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicEditorPaneUI;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;

public class FlatEditorPaneUI extends BasicEditorPaneUI implements FlatStylingSupport.StyleableUI {
   @FlatStylingSupport.Styleable
   protected int minimumWidth;
   protected boolean isIntelliJTheme;
   private Color background;
   @FlatStylingSupport.Styleable
   protected Color disabledBackground;
   @FlatStylingSupport.Styleable
   protected Color inactiveBackground;
   @FlatStylingSupport.Styleable
   protected Color focusedBackground;
   private Color oldDisabledBackground;
   private Color oldInactiveBackground;
   private Insets defaultMargin;
   private Object oldHonorDisplayProperties;
   private FocusListener focusListener;
   private Map<String, Object> oldStyleValues;

   public static ComponentUI createUI(JComponent c) {
      return new FlatEditorPaneUI();
   }

   public void installUI(JComponent c) {
      super.installUI(c);
      this.installStyle();
   }

   protected void installDefaults() {
      super.installDefaults();
      String prefix = this.getPropertyPrefix();
      this.minimumWidth = UIManager.getInt("Component.minimumWidth");
      this.isIntelliJTheme = UIManager.getBoolean("Component.isIntelliJTheme");
      this.background = UIManager.getColor(prefix + ".background");
      this.disabledBackground = UIManager.getColor(prefix + ".disabledBackground");
      this.inactiveBackground = UIManager.getColor(prefix + ".inactiveBackground");
      this.focusedBackground = UIManager.getColor(prefix + ".focusedBackground");
      this.defaultMargin = UIManager.getInsets(prefix + ".margin");
      this.oldHonorDisplayProperties = this.getComponent().getClientProperty("JEditorPane.honorDisplayProperties");
      this.getComponent().putClientProperty("JEditorPane.honorDisplayProperties", true);
   }

   protected void uninstallDefaults() {
      super.uninstallDefaults();
      this.background = null;
      this.disabledBackground = null;
      this.inactiveBackground = null;
      this.focusedBackground = null;
      this.oldDisabledBackground = null;
      this.oldInactiveBackground = null;
      this.oldStyleValues = null;
      this.getComponent().putClientProperty("JEditorPane.honorDisplayProperties", this.oldHonorDisplayProperties);
   }

   protected void installListeners() {
      super.installListeners();
      this.focusListener = new FlatUIUtils.RepaintFocusListener(this.getComponent(), (c) -> {
         return this.focusedBackground != null;
      });
      this.getComponent().addFocusListener(this.focusListener);
   }

   protected void uninstallListeners() {
      super.uninstallListeners();
      this.getComponent().removeFocusListener(this.focusListener);
      this.focusListener = null;
   }

   protected Caret createCaret() {
      return new FlatCaret((String)null, false);
   }

   protected void propertyChange(PropertyChangeEvent e) {
      String propertyName = e.getPropertyName();
      if ("editable".equals(propertyName) || "enabled".equals(propertyName)) {
         this.updateBackground();
      }

      super.propertyChange(e);
      propertyChange(this.getComponent(), e, this::installStyle);
   }

   static void propertyChange(JTextComponent c, PropertyChangeEvent e, Runnable installStyle) {
      String var3 = e.getPropertyName();
      byte var4 = -1;
      switch(var3.hashCode()) {
      case -1302441837:
         if (var3.equals("JComponent.minimumWidth")) {
            var4 = 0;
         }
         break;
      case 1030195901:
         if (var3.equals("FlatLaf.styleClass")) {
            var4 = 2;
         }
         break;
      case 1545413499:
         if (var3.equals("FlatLaf.style")) {
            var4 = 1;
         }
      }

      switch(var4) {
      case 0:
         c.revalidate();
         break;
      case 1:
      case 2:
         installStyle.run();
         c.revalidate();
         c.repaint();
      }

   }

   protected void installStyle() {
      try {
         this.applyStyle(FlatStylingSupport.getResolvedStyle(this.getComponent(), "EditorPane"));
      } catch (RuntimeException var2) {
         LoggingFacade.INSTANCE.logSevere((String)null, var2);
      }

   }

   protected void applyStyle(Object style) {
      this.oldDisabledBackground = this.disabledBackground;
      this.oldInactiveBackground = this.inactiveBackground;
      this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
      this.updateBackground();
   }

   protected Object applyStyleProperty(String key, Object value) {
      return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, this.getComponent(), key, value);
   }

   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
      return FlatStylingSupport.getAnnotatedStyleableInfos(this);
   }

   public Object getStyleableValue(JComponent c, String key) {
      return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
   }

   private void updateBackground() {
      FlatTextFieldUI.updateBackground(this.getComponent(), this.background, this.disabledBackground, this.inactiveBackground, this.oldDisabledBackground, this.oldInactiveBackground);
   }

   public Dimension getPreferredSize(JComponent c) {
      return applyMinimumWidth(c, super.getPreferredSize(c), this.minimumWidth, this.defaultMargin);
   }

   public Dimension getMinimumSize(JComponent c) {
      return applyMinimumWidth(c, super.getMinimumSize(c), this.minimumWidth, this.defaultMargin);
   }

   static Dimension applyMinimumWidth(JComponent c, Dimension size, int minimumWidth, Insets defaultMargin) {
      if (!FlatTextFieldUI.hasDefaultMargins(c, defaultMargin)) {
         return size;
      } else {
         minimumWidth = FlatUIUtils.minimumWidth(c, minimumWidth);
         size.width = Math.max(size.width, UIScale.scale(minimumWidth) - UIScale.scale(1) * 2);
         return size;
      }
   }

   protected void paintSafely(Graphics g) {
      super.paintSafely(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)g));
   }

   protected void paintBackground(Graphics g) {
      paintBackground(g, this.getComponent(), this.isIntelliJTheme, this.focusedBackground);
   }

   static void paintBackground(Graphics g, JTextComponent c, boolean isIntelliJTheme, Color focusedBackground) {
      g.setColor(FlatTextFieldUI.getBackground(c, isIntelliJTheme, focusedBackground));
      g.fillRect(0, 0, c.getWidth(), c.getHeight());
   }
}
