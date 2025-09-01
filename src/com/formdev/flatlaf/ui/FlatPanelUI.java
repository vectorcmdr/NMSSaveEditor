package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPanelUI;

public class FlatPanelUI extends BasicPanelUI implements FlatStylingSupport.StyleableUI, PropertyChangeListener {
   @FlatStylingSupport.Styleable
   protected int arc = -1;
   private final boolean shared;
   private Map<String, Object> oldStyleValues;

   public static ComponentUI createUI(JComponent c) {
      return (ComponentUI)(FlatUIUtils.canUseSharedUI(c) ? FlatUIUtils.createSharedUI(FlatPanelUI.class, () -> {
         return new FlatPanelUI(true);
      }) : new FlatPanelUI(false));
   }

   protected FlatPanelUI(boolean shared) {
      this.shared = shared;
   }

   public void installUI(JComponent c) {
      super.installUI(c);
      c.addPropertyChangeListener(this);
      this.installStyle((JPanel)c);
   }

   public void uninstallUI(JComponent c) {
      super.uninstallUI(c);
      c.removePropertyChangeListener(this);
      this.oldStyleValues = null;
   }

   public void propertyChange(PropertyChangeEvent e) {
      String var2 = e.getPropertyName();
      byte var3 = -1;
      switch(var2.hashCode()) {
      case 1030195901:
         if (var2.equals("FlatLaf.styleClass")) {
            var3 = 1;
         }
         break;
      case 1545413499:
         if (var2.equals("FlatLaf.style")) {
            var3 = 0;
         }
      }

      switch(var3) {
      case 0:
      case 1:
         JPanel c = (JPanel)e.getSource();
         if (this.shared && FlatStylingSupport.hasStyleProperty(c)) {
            c.updateUI();
         } else {
            this.installStyle(c);
         }

         c.revalidate();
         c.repaint();
      default:
      }
   }

   protected void installStyle(JPanel c) {
      try {
         this.applyStyle(c, FlatStylingSupport.getResolvedStyle(c, "Panel"));
      } catch (RuntimeException var3) {
         LoggingFacade.INSTANCE.logSevere((String)null, var3);
      }

   }

   protected void applyStyle(JPanel c, Object style) {
      this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, (key, value) -> {
         return this.applyStyleProperty(c, key, value);
      });
   }

   protected Object applyStyleProperty(JPanel c, String key, Object value) {
      return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, c, key, value);
   }

   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
      return FlatStylingSupport.getAnnotatedStyleableInfos(this);
   }

   public Object getStyleableValue(JComponent c, String key) {
      return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
   }

   public void update(Graphics g, JComponent c) {
      if (c.isOpaque()) {
         int width = c.getWidth();
         int height = c.getHeight();
         int arc = this.arc >= 0 ? this.arc : (c.getBorder() instanceof FlatLineBorder ? ((FlatLineBorder)c.getBorder()).getArc() : 0);
         if (arc > 0) {
            FlatUIUtils.paintParentBackground(g, c);
         }

         g.setColor(c.getBackground());
         if (arc > 0) {
            Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
            FlatUIUtils.paintComponentBackground((Graphics2D)g, 0, 0, width, height, 0.0F, (float)UIScale.scale(arc));
            FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
         } else {
            g.fillRect(0, 0, width, height);
         }
      }

      this.paint(g, c);
   }
}
