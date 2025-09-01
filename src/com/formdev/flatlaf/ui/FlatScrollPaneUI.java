package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.LoggingFacade;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.LookAndFeel;
import javax.swing.Scrollable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollPaneUI;

public class FlatScrollPaneUI extends BasicScrollPaneUI implements FlatStylingSupport.StyleableUI {
   @FlatStylingSupport.Styleable
   protected Boolean showButtons;
   private FlatScrollPaneUI.Handler handler;
   private Map<String, Object> oldStyleValues;
   private AtomicBoolean borderShared;

   public static ComponentUI createUI(JComponent c) {
      return new FlatScrollPaneUI();
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
      int focusWidth = UIManager.getInt("Component.focusWidth");
      LookAndFeel.installProperty(c, "opaque", focusWidth == 0);
      this.installStyle();
      MigLayoutVisualPadding.install(this.scrollpane);
   }

   public void uninstallUI(JComponent c) {
      MigLayoutVisualPadding.uninstall(this.scrollpane);
      super.uninstallUI(c);
      this.oldStyleValues = null;
      this.borderShared = null;
   }

   protected void installListeners(JScrollPane c) {
      super.installListeners(c);
      this.addViewportListeners(this.scrollpane.getViewport());
   }

   protected void uninstallListeners(JComponent c) {
      super.uninstallListeners(c);
      this.removeViewportListeners(this.scrollpane.getViewport());
      this.handler = null;
   }

   protected MouseWheelListener createMouseWheelListener() {
      MouseWheelListener superListener = super.createMouseWheelListener();
      return (e) -> {
         if (this.isSmoothScrollingEnabled() && this.scrollpane.isWheelScrollingEnabled() && e.getScrollType() == 0 && e.getPreciseWheelRotation() != 0.0D && e.getPreciseWheelRotation() != (double)e.getWheelRotation()) {
            this.mouseWheelMovedSmooth(e);
         } else {
            superListener.mouseWheelMoved(e);
         }

      };
   }

   protected boolean isSmoothScrollingEnabled() {
      Object smoothScrolling = this.scrollpane.getClientProperty("JScrollPane.smoothScrolling");
      return smoothScrolling instanceof Boolean ? (Boolean)smoothScrolling : UIManager.getBoolean("ScrollPane.smoothScrolling");
   }

   private void mouseWheelMovedSmooth(MouseWheelEvent e) {
      JViewport viewport = this.scrollpane.getViewport();
      if (viewport != null) {
         JScrollBar scrollbar = this.scrollpane.getVerticalScrollBar();
         if (scrollbar == null || !scrollbar.isVisible() || e.isShiftDown()) {
            scrollbar = this.scrollpane.getHorizontalScrollBar();
            if (scrollbar == null || !scrollbar.isVisible()) {
               return;
            }
         }

         e.consume();
         double rotation = e.getPreciseWheelRotation();
         int orientation = scrollbar.getOrientation();
         Component view = viewport.getView();
         int unitIncrement;
         int viewportWH;
         if (view instanceof Scrollable) {
            Scrollable scrollable = (Scrollable)view;
            Rectangle visibleRect = new Rectangle(viewport.getViewSize());
            unitIncrement = scrollable.getScrollableUnitIncrement(visibleRect, orientation, 1);
            if (unitIncrement > 0) {
               if (orientation == 1) {
                  visibleRect.y += unitIncrement;
                  visibleRect.height -= unitIncrement;
               } else {
                  visibleRect.x += unitIncrement;
                  visibleRect.width -= unitIncrement;
               }

               int unitIncrement2 = scrollable.getScrollableUnitIncrement(visibleRect, orientation, 1);
               if (unitIncrement2 > 0) {
                  unitIncrement = Math.min(unitIncrement, unitIncrement2);
               }
            }
         } else {
            viewportWH = rotation < 0.0D ? -1 : 1;
            unitIncrement = scrollbar.getUnitIncrement(viewportWH);
         }

         viewportWH = orientation == 1 ? viewport.getHeight() : viewport.getWidth();
         int scrollIncrement = Math.min(unitIncrement * e.getScrollAmount(), viewportWH);
         double delta = rotation * (double)scrollIncrement;
         int idelta = (int)Math.round(delta);
         if (idelta == 0) {
            if (rotation > 0.0D) {
               idelta = 1;
            } else if (rotation < 0.0D) {
               idelta = -1;
            }
         }

         int value = scrollbar.getValue();
         int minValue = scrollbar.getMinimum();
         int maxValue = scrollbar.getMaximum() - scrollbar.getModel().getExtent();
         int newValue = Math.max(minValue, Math.min(value + idelta, maxValue));
         if (newValue != value) {
            scrollbar.setValue(newValue);
         }

      }
   }

   protected PropertyChangeListener createPropertyChangeListener() {
      PropertyChangeListener superListener = super.createPropertyChangeListener();
      return (e) -> {
         superListener.propertyChange(e);
         String var3 = e.getPropertyName();
         byte var4 = -1;
         switch(var3.hashCode()) {
         case -1979886954:
            if (var3.equals("LOWER_RIGHT_CORNER")) {
               var4 = 2;
            }
            break;
         case -1731521899:
            if (var3.equals("UPPER_RIGHT_CORNER")) {
               var4 = 4;
            }
            break;
         case -691370713:
            if (var3.equals("JComponent.outline")) {
               var4 = 5;
            }
            break;
         case -29602513:
            if (var3.equals("LOWER_LEFT_CORNER")) {
               var4 = 1;
            }
            break;
         case 193872818:
            if (var3.equals("JScrollBar.showButtons")) {
               var4 = 0;
            }
            break;
         case 1030195901:
            if (var3.equals("FlatLaf.styleClass")) {
               var4 = 7;
            }
            break;
         case 1086787920:
            if (var3.equals("UPPER_LEFT_CORNER")) {
               var4 = 3;
            }
            break;
         case 1545413499:
            if (var3.equals("FlatLaf.style")) {
               var4 = 6;
            }
         }

         switch(var4) {
         case 0:
            JScrollBar vsb = this.scrollpane.getVerticalScrollBar();
            JScrollBar hsb = this.scrollpane.getHorizontalScrollBar();
            if (vsb != null) {
               vsb.revalidate();
               vsb.repaint();
            }

            if (hsb != null) {
               hsb.revalidate();
               hsb.repaint();
            }
            break;
         case 1:
         case 2:
         case 3:
         case 4:
            Object corner = e.getNewValue();
            if (corner instanceof JButton && ((JButton)corner).getBorder() instanceof FlatButtonBorder && this.scrollpane.getViewport() != null && this.scrollpane.getViewport().getView() instanceof JTable) {
               ((JButton)corner).setBorder(BorderFactory.createEmptyBorder());
               ((JButton)corner).setFocusable(false);
            }
            break;
         case 5:
            this.scrollpane.repaint();
            break;
         case 6:
         case 7:
            this.installStyle();
            this.scrollpane.revalidate();
            this.scrollpane.repaint();
         }

      };
   }

   private FlatScrollPaneUI.Handler getHandler() {
      if (this.handler == null) {
         this.handler = new FlatScrollPaneUI.Handler();
      }

      return this.handler;
   }

   protected void installStyle() {
      try {
         this.applyStyle(FlatStylingSupport.getResolvedStyle(this.scrollpane, "ScrollPane"));
      } catch (RuntimeException var2) {
         LoggingFacade.INSTANCE.logSevere((String)null, var2);
      }

   }

   protected void applyStyle(Object style) {
      this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
   }

   protected Object applyStyleProperty(String key, Object value) {
      if (key.equals("focusWidth")) {
         int focusWidth = value instanceof Integer ? (Integer)value : UIManager.getInt("Component.focusWidth");
         LookAndFeel.installProperty(this.scrollpane, "opaque", focusWidth == 0);
      }

      if (this.borderShared == null) {
         this.borderShared = new AtomicBoolean(true);
      }

      return FlatStylingSupport.applyToAnnotatedObjectOrBorder(this, key, value, this.scrollpane, this.borderShared);
   }

   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
      return FlatStylingSupport.getAnnotatedStyleableInfos(this, this.scrollpane.getBorder());
   }

   public Object getStyleableValue(JComponent c, String key) {
      return FlatStylingSupport.getAnnotatedStyleableValue(this, this.scrollpane.getBorder(), key);
   }

   protected void updateViewport(PropertyChangeEvent e) {
      super.updateViewport(e);
      JViewport oldViewport = (JViewport)e.getOldValue();
      JViewport newViewport = (JViewport)e.getNewValue();
      this.removeViewportListeners(oldViewport);
      this.addViewportListeners(newViewport);
   }

   private void addViewportListeners(JViewport viewport) {
      if (viewport != null) {
         viewport.addContainerListener(this.getHandler());
         Component view = viewport.getView();
         if (view != null) {
            view.addFocusListener(this.getHandler());
         }

      }
   }

   private void removeViewportListeners(JViewport viewport) {
      if (viewport != null) {
         viewport.removeContainerListener(this.getHandler());
         Component view = viewport.getView();
         if (view != null) {
            view.removeFocusListener(this.getHandler());
         }

      }
   }

   public void update(Graphics g, JComponent c) {
      if (c.isOpaque()) {
         FlatUIUtils.paintParentBackground(g, c);
         Insets insets = c.getInsets();
         g.setColor(c.getBackground());
         g.fillRect(insets.left, insets.top, c.getWidth() - insets.left - insets.right, c.getHeight() - insets.top - insets.bottom);
      }

      this.paint(g, c);
   }

   public static boolean isPermanentFocusOwner(JScrollPane scrollPane) {
      JViewport viewport = scrollPane.getViewport();
      Component view = viewport != null ? viewport.getView() : null;
      if (view == null) {
         return false;
      } else if (FlatUIUtils.isPermanentFocusOwner(view)) {
         return true;
      } else {
         if (view instanceof JTable && ((JTable)view).isEditing() || view instanceof JTree && ((JTree)view).isEditing()) {
            Component focusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
            if (focusOwner != null) {
               return SwingUtilities.isDescendingFrom(focusOwner, view);
            }
         }

         return false;
      }
   }

   private class Handler implements ContainerListener, FocusListener {
      private Handler() {
      }

      public void componentAdded(ContainerEvent e) {
         e.getChild().addFocusListener(this);
      }

      public void componentRemoved(ContainerEvent e) {
         e.getChild().removeFocusListener(this);
      }

      public void focusGained(FocusEvent e) {
         FlatScrollPaneUI.this.scrollpane.repaint();
      }

      public void focusLost(FocusEvent e) {
         FlatScrollPaneUI.this.scrollpane.repaint();
      }

      // $FF: synthetic method
      Handler(Object x1) {
         this();
      }
   }
}
