package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D.Float;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSeparatorUI;

public class FlatSeparatorUI extends BasicSeparatorUI implements FlatStylingSupport.StyleableUI, PropertyChangeListener {
   @FlatStylingSupport.Styleable
   protected int height;
   @FlatStylingSupport.Styleable
   protected int stripeWidth;
   @FlatStylingSupport.Styleable
   protected int stripeIndent;
   private final boolean shared;
   private boolean defaults_initialized = false;
   private Map<String, Object> oldStyleValues;

   public static ComponentUI createUI(JComponent c) {
      return (ComponentUI)(FlatUIUtils.canUseSharedUI(c) ? FlatUIUtils.createSharedUI(FlatSeparatorUI.class, () -> {
         return new FlatSeparatorUI(true);
      }) : new FlatSeparatorUI(false));
   }

   protected FlatSeparatorUI(boolean shared) {
      this.shared = shared;
   }

   protected String getPropertyPrefix() {
      return "Separator";
   }

   public void installUI(JComponent c) {
      super.installUI(c);
      this.installStyle((JSeparator)c);
   }

   protected void installDefaults(JSeparator s) {
      super.installDefaults(s);
      if (!this.defaults_initialized) {
         String prefix = this.getPropertyPrefix();
         this.height = UIManager.getInt(prefix + ".height");
         this.stripeWidth = UIManager.getInt(prefix + ".stripeWidth");
         this.stripeIndent = UIManager.getInt(prefix + ".stripeIndent");
         this.defaults_initialized = true;
      }

   }

   protected void uninstallDefaults(JSeparator s) {
      super.uninstallDefaults(s);
      this.defaults_initialized = false;
      this.oldStyleValues = null;
   }

   protected void installListeners(JSeparator s) {
      super.installListeners(s);
      s.addPropertyChangeListener(this);
   }

   protected void uninstallListeners(JSeparator s) {
      super.uninstallListeners(s);
      s.removePropertyChangeListener(this);
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
         JSeparator s = (JSeparator)e.getSource();
         if (this.shared && FlatStylingSupport.hasStyleProperty(s)) {
            s.updateUI();
         } else {
            this.installStyle(s);
         }

         s.revalidate();
         s.repaint();
      default:
      }
   }

   protected void installStyle(JSeparator s) {
      try {
         this.applyStyle(s, FlatStylingSupport.getResolvedStyle(s, this.getStyleType()));
      } catch (RuntimeException var3) {
         LoggingFacade.INSTANCE.logSevere((String)null, var3);
      }

   }

   String getStyleType() {
      return "Separator";
   }

   protected void applyStyle(JSeparator s, Object style) {
      this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, (key, value) -> {
         return this.applyStyleProperty(s, key, value);
      });
   }

   protected Object applyStyleProperty(JSeparator s, String key, Object value) {
      return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, s, key, value);
   }

   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
      return FlatStylingSupport.getAnnotatedStyleableInfos(this);
   }

   public Object getStyleableValue(JComponent c, String key) {
      return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
   }

   public void paint(Graphics g, JComponent c) {
      Graphics2D g2 = (Graphics2D)g.create();

      try {
         FlatUIUtils.setRenderingHints(g2);
         g2.setColor(c.getForeground());
         float width = UIScale.scale((float)this.stripeWidth);
         float indent = UIScale.scale((float)this.stripeIndent);
         if (((JSeparator)c).getOrientation() == 1) {
            g2.fill(new Float(indent, 0.0F, width, (float)c.getHeight()));
         } else {
            g2.fill(new Float(0.0F, indent, (float)c.getWidth(), width));
         }
      } finally {
         g2.dispose();
      }

   }

   public Dimension getPreferredSize(JComponent c) {
      return ((JSeparator)c).getOrientation() == 1 ? new Dimension(UIScale.scale(this.height), 0) : new Dimension(0, UIScale.scale(this.height));
   }
}
