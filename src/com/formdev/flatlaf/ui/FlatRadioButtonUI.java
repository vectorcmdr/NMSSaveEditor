package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.icons.FlatCheckBoxIcon;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import javax.swing.AbstractButton;
import javax.swing.CellRendererPane;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonListener;
import javax.swing.plaf.basic.BasicRadioButtonUI;

public class FlatRadioButtonUI extends BasicRadioButtonUI implements FlatStylingSupport.StyleableUI {
   protected int iconTextGap;
   @FlatStylingSupport.Styleable
   protected Color disabledText;
   private Color defaultBackground;
   private final boolean shared;
   private boolean iconShared = true;
   private boolean defaults_initialized = false;
   private Map<String, Object> oldStyleValues;
   private static final Insets tempInsets = new Insets(0, 0, 0, 0);

   public static ComponentUI createUI(JComponent c) {
      return (ComponentUI)(FlatUIUtils.canUseSharedUI(c) && !FlatUIUtils.needsLightAWTPeer(c) ? FlatUIUtils.createSharedUI(FlatRadioButtonUI.class, () -> {
         return new FlatRadioButtonUI(true);
      }) : new FlatRadioButtonUI(false));
   }

   protected FlatRadioButtonUI(boolean shared) {
      this.shared = shared;
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
      if (FlatUIUtils.isAWTPeer(c)) {
         FlatRadioButtonUI.AWTPeerMouseExitedFix.install(c);
      }

      this.installStyle((AbstractButton)c);
   }

   public void uninstallUI(JComponent c) {
      super.uninstallUI(c);
      if (FlatUIUtils.isAWTPeer(c)) {
         FlatRadioButtonUI.AWTPeerMouseExitedFix.uninstall(c);
      }

   }

   public void installDefaults(AbstractButton b) {
      super.installDefaults(b);
      if (!this.defaults_initialized) {
         String prefix = this.getPropertyPrefix();
         this.iconTextGap = FlatUIUtils.getUIInt(prefix + "iconTextGap", 4);
         this.disabledText = UIManager.getColor(prefix + "disabledText");
         this.defaultBackground = UIManager.getColor(prefix + "background");
         this.iconShared = true;
         this.defaults_initialized = true;
      }

      LookAndFeel.installProperty(b, "opaque", false);
      LookAndFeel.installProperty(b, "iconTextGap", UIScale.scale(this.iconTextGap));
      MigLayoutVisualPadding.install(b, (Insets)null);
   }

   protected void uninstallDefaults(AbstractButton b) {
      super.uninstallDefaults(b);
      this.oldStyleValues = null;
      MigLayoutVisualPadding.uninstall(b);
      this.defaults_initialized = false;
   }

   protected BasicButtonListener createButtonListener(AbstractButton b) {
      return new FlatRadioButtonUI.FlatRadioButtonListener(b);
   }

   protected void propertyChange(AbstractButton b, PropertyChangeEvent e) {
      String var3 = e.getPropertyName();
      byte var4 = -1;
      switch(var3.hashCode()) {
      case 1030195901:
         if (var3.equals("FlatLaf.styleClass")) {
            var4 = 1;
         }
         break;
      case 1545413499:
         if (var3.equals("FlatLaf.style")) {
            var4 = 0;
         }
      }

      switch(var4) {
      case 0:
      case 1:
         if (this.shared && FlatStylingSupport.hasStyleProperty(b)) {
            b.updateUI();
         } else {
            this.installStyle(b);
         }

         b.revalidate();
         b.repaint();
      default:
      }
   }

   protected void installStyle(AbstractButton b) {
      try {
         this.applyStyle(b, FlatStylingSupport.getResolvedStyle(b, this.getStyleType()));
      } catch (RuntimeException var3) {
         LoggingFacade.INSTANCE.logSevere((String)null, var3);
      }

   }

   String getStyleType() {
      return "RadioButton";
   }

   protected void applyStyle(AbstractButton b, Object style) {
      this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, (key, value) -> {
         return this.applyStyleProperty(b, key, value);
      });
   }

   protected Object applyStyleProperty(AbstractButton b, String key, Object value) {
      if (key.startsWith("icon.")) {
         if (!(this.icon instanceof FlatCheckBoxIcon)) {
            return new FlatStylingSupport.UnknownStyleException(key);
         } else {
            if (this.iconShared) {
               this.icon = FlatStylingSupport.cloneIcon(this.icon);
               this.iconShared = false;
            }

            key = key.substring("icon.".length());
            return ((FlatCheckBoxIcon)this.icon).applyStyleProperty(key, value);
         }
      } else {
         return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, b, key, value);
      }
   }

   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
      Map<String, Class<?>> infos = FlatStylingSupport.getAnnotatedStyleableInfos(this);
      if (this.icon instanceof FlatCheckBoxIcon) {
         Iterator var3 = ((FlatCheckBoxIcon)this.icon).getStyleableInfos().entrySet().iterator();

         while(var3.hasNext()) {
            Entry<String, Class<?>> e = (Entry)var3.next();
            infos.put("icon.".concat((String)e.getKey()), (Class)e.getValue());
         }
      }

      return infos;
   }

   public Object getStyleableValue(JComponent c, String key) {
      if (key.startsWith("icon.")) {
         return this.icon instanceof FlatCheckBoxIcon ? ((FlatCheckBoxIcon)this.icon).getStyleableValue(key.substring("icon.".length())) : null;
      } else {
         return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
      }
   }

   public Dimension getPreferredSize(JComponent c) {
      Dimension size = super.getPreferredSize(c);
      if (size == null) {
         return null;
      } else {
         int focusWidth = this.getIconFocusWidth(c);
         if (focusWidth > 0) {
            Insets insets = c.getInsets(tempInsets);
            size.width += Math.max(focusWidth - insets.left, 0) + Math.max(focusWidth - insets.right, 0);
            size.height += Math.max(focusWidth - insets.top, 0) + Math.max(focusWidth - insets.bottom, 0);
         }

         return size;
      }
   }

   public void paint(Graphics g, JComponent c) {
      if (!c.isOpaque() && ((AbstractButton)c).isContentAreaFilled() && !Objects.equals(c.getBackground(), this.getDefaultBackground(c))) {
         g.setColor(c.getBackground());
         g.fillRect(0, 0, c.getWidth(), c.getHeight());
      }

      int focusWidth = this.getIconFocusWidth(c);
      if (focusWidth > 0) {
         boolean ltr = c.getComponentOrientation().isLeftToRight();
         Insets insets = c.getInsets(tempInsets);
         int leftOrRightInset = ltr ? insets.left : insets.right;
         if (focusWidth > leftOrRightInset) {
            int offset = focusWidth - leftOrRightInset;
            if (!ltr) {
               offset = -offset;
            }

            g.translate(offset, 0);
            super.paint(g, c);
            g.translate(-offset, 0);
            return;
         }
      }

      super.paint(FlatLabelUI.createGraphicsHTMLTextYCorrection(g, c), c);
   }

   protected void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text) {
      FlatButtonUI.paintText(g, b, textRect, text, b.isEnabled() ? b.getForeground() : this.disabledText);
   }

   private Color getDefaultBackground(JComponent c) {
      Container parent = c.getParent();
      return parent instanceof CellRendererPane && parent.getParent() != null ? parent.getParent().getBackground() : this.defaultBackground;
   }

   private int getIconFocusWidth(JComponent c) {
      AbstractButton b = (AbstractButton)c;
      Icon icon = b.getIcon();
      if (icon == null) {
         icon = this.getDefaultIcon();
      }

      return icon instanceof FlatCheckBoxIcon ? Math.round(UIScale.scale(((FlatCheckBoxIcon)icon).getFocusWidth())) : 0;
   }

   private static class AWTPeerMouseExitedFix extends MouseAdapter implements PropertyChangeListener {
      private final JComponent button;

      static void install(JComponent button) {
         FlatRadioButtonUI.AWTPeerMouseExitedFix l = new FlatRadioButtonUI.AWTPeerMouseExitedFix(button);
         button.addPropertyChangeListener("ancestor", l);
         Container parent = button.getParent();
         if (parent != null) {
            parent.addMouseListener(l);
         }

      }

      static void uninstall(JComponent button) {
         PropertyChangeListener[] var1 = button.getPropertyChangeListeners("ancestor");
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            PropertyChangeListener l = var1[var3];
            if (l instanceof FlatRadioButtonUI.AWTPeerMouseExitedFix) {
               button.removePropertyChangeListener("ancestor", l);
               Container parent = button.getParent();
               if (parent != null) {
                  parent.removeMouseListener((FlatRadioButtonUI.AWTPeerMouseExitedFix)l);
               }
               break;
            }
         }

      }

      AWTPeerMouseExitedFix(JComponent button) {
         this.button = button;
      }

      public void propertyChange(PropertyChangeEvent e) {
         if (e.getOldValue() instanceof Component) {
            ((Component)e.getOldValue()).removeMouseListener(this);
         }

         if (e.getNewValue() instanceof Component) {
            ((Component)e.getNewValue()).removeMouseListener(this);
            ((Component)e.getNewValue()).addMouseListener(this);
         }

      }

      public void mouseExited(MouseEvent e) {
         this.button.dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(), e, this.button));
      }
   }

   protected class FlatRadioButtonListener extends BasicButtonListener {
      private final AbstractButton b;

      protected FlatRadioButtonListener(AbstractButton b) {
         super(b);
         this.b = b;
      }

      public void propertyChange(PropertyChangeEvent e) {
         super.propertyChange(e);
         FlatRadioButtonUI.this.propertyChange(this.b, e);
      }
   }
}
