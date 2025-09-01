package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.LoggingFacade;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTextAreaUI;
import javax.swing.text.Caret;

public class FlatTextAreaUI extends BasicTextAreaUI implements FlatStylingSupport.StyleableUI {
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
   private FocusListener focusListener;
   private Map<String, Object> oldStyleValues;

   public static ComponentUI createUI(JComponent c) {
      return new FlatTextAreaUI();
   }

   public void installUI(JComponent c) {
      if (FlatUIUtils.needsLightAWTPeer(c)) {
         FlatUIUtils.runWithLightAWTPeerUIDefaults(() -> {
            this.installUIImpl(c);
         });
      } else {
         this.installUIImpl(c);
      }

   }

   private void installUIImpl(JComponent c) {
      super.installUI(c);
      this.installStyle();
   }

   protected void installDefaults() {
      super.installDefaults();
      this.minimumWidth = UIManager.getInt("Component.minimumWidth");
      this.isIntelliJTheme = UIManager.getBoolean("Component.isIntelliJTheme");
      this.background = UIManager.getColor("TextArea.background");
      this.disabledBackground = UIManager.getColor("TextArea.disabledBackground");
      this.inactiveBackground = UIManager.getColor("TextArea.inactiveBackground");
      this.focusedBackground = UIManager.getColor("TextArea.focusedBackground");
      this.defaultMargin = UIManager.getInsets("TextArea.margin");
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
      FlatEditorPaneUI.propertyChange(this.getComponent(), e, this::installStyle);
   }

   protected void installStyle() {
      try {
         this.applyStyle(FlatStylingSupport.getResolvedStyle(this.getComponent(), "TextArea"));
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
      return this.applyMinimumWidth(c, super.getPreferredSize(c));
   }

   public Dimension getMinimumSize(JComponent c) {
      return this.applyMinimumWidth(c, super.getMinimumSize(c));
   }

   private Dimension applyMinimumWidth(JComponent c, Dimension size) {
      return c instanceof JTextArea && ((JTextArea)c).getColumns() > 0 ? size : FlatEditorPaneUI.applyMinimumWidth(c, size, this.minimumWidth, this.defaultMargin);
   }

   protected void paintSafely(Graphics g) {
      super.paintSafely(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)g));
   }

   protected void paintBackground(Graphics g) {
      FlatEditorPaneUI.paintBackground(g, this.getComponent(), this.isIntelliJTheme, this.focusedBackground);
   }
}
