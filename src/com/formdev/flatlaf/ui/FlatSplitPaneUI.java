package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider.DividerLayout;

public class FlatSplitPaneUI extends BasicSplitPaneUI implements FlatStylingSupport.StyleableUI {
   @FlatStylingSupport.Styleable
   protected String arrowType;
   @FlatStylingSupport.Styleable
   protected Color oneTouchArrowColor;
   @FlatStylingSupport.Styleable
   protected Color oneTouchHoverArrowColor;
   @FlatStylingSupport.Styleable
   protected Color oneTouchPressedArrowColor;
   private PropertyChangeListener propertyChangeListener;
   private Map<String, Object> oldStyleValues;

   public static ComponentUI createUI(JComponent c) {
      return new FlatSplitPaneUI();
   }

   public void installUI(JComponent c) {
      super.installUI(c);
      this.installStyle();
   }

   protected void installDefaults() {
      this.arrowType = UIManager.getString("Component.arrowType");
      this.oneTouchArrowColor = UIManager.getColor("SplitPaneDivider.oneTouchArrowColor");
      this.oneTouchHoverArrowColor = UIManager.getColor("SplitPaneDivider.oneTouchHoverArrowColor");
      this.oneTouchPressedArrowColor = UIManager.getColor("SplitPaneDivider.oneTouchPressedArrowColor");
      super.installDefaults();
   }

   protected void uninstallDefaults() {
      super.uninstallDefaults();
      this.oneTouchArrowColor = null;
      this.oneTouchHoverArrowColor = null;
      this.oneTouchPressedArrowColor = null;
      this.oldStyleValues = null;
   }

   protected void installListeners() {
      super.installListeners();
      this.propertyChangeListener = FlatStylingSupport.createPropertyChangeListener(this.splitPane, this::installStyle, (PropertyChangeListener)null);
      this.splitPane.addPropertyChangeListener(this.propertyChangeListener);
   }

   protected void uninstallListeners() {
      super.uninstallListeners();
      this.splitPane.removePropertyChangeListener(this.propertyChangeListener);
      this.propertyChangeListener = null;
   }

   public BasicSplitPaneDivider createDefaultDivider() {
      return new FlatSplitPaneUI.FlatSplitPaneDivider(this);
   }

   protected void installStyle() {
      try {
         this.applyStyle(FlatStylingSupport.getResolvedStyle(this.splitPane, "SplitPane"));
      } catch (RuntimeException var2) {
         LoggingFacade.INSTANCE.logSevere((String)null, var2);
      }

   }

   protected void applyStyle(Object style) {
      this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
      if (this.divider instanceof FlatSplitPaneUI.FlatSplitPaneDivider) {
         ((FlatSplitPaneUI.FlatSplitPaneDivider)this.divider).updateStyle();
      }

   }

   protected Object applyStyleProperty(String key, Object value) {
      try {
         if (this.divider instanceof FlatSplitPaneUI.FlatSplitPaneDivider) {
            return ((FlatSplitPaneUI.FlatSplitPaneDivider)this.divider).applyStyleProperty(key, value);
         }
      } catch (FlatStylingSupport.UnknownStyleException var4) {
      }

      return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, this.splitPane, key, value);
   }

   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
      Map<String, Class<?>> infos = FlatStylingSupport.getAnnotatedStyleableInfos(this);
      if (this.divider instanceof FlatSplitPaneUI.FlatSplitPaneDivider) {
         infos.putAll(((FlatSplitPaneUI.FlatSplitPaneDivider)this.divider).getStyleableInfos());
      }

      return infos;
   }

   public Object getStyleableValue(JComponent c, String key) {
      if (this.divider instanceof FlatSplitPaneUI.FlatSplitPaneDivider) {
         Object value = ((FlatSplitPaneUI.FlatSplitPaneDivider)this.divider).getStyleableValue(key);
         if (value != null) {
            return value;
         }
      }

      return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
   }

   protected class FlatSplitPaneDivider extends BasicSplitPaneDivider {
      @FlatStylingSupport.Styleable
      protected String style = UIManager.getString("SplitPaneDivider.style");
      @FlatStylingSupport.Styleable
      protected Color gripColor = UIManager.getColor("SplitPaneDivider.gripColor");
      @FlatStylingSupport.Styleable
      protected int gripDotCount = FlatUIUtils.getUIInt("SplitPaneDivider.gripDotCount", 3);
      @FlatStylingSupport.Styleable
      protected int gripDotSize = FlatUIUtils.getUIInt("SplitPaneDivider.gripDotSize", 3);
      @FlatStylingSupport.Styleable
      protected int gripGap = FlatUIUtils.getUIInt("SplitPaneDivider.gripGap", 2);

      protected FlatSplitPaneDivider(BasicSplitPaneUI ui) {
         super(ui);
         this.setLayout(new FlatSplitPaneUI.FlatSplitPaneDivider.FlatDividerLayout());
      }

      protected Object applyStyleProperty(String key, Object value) {
         return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
      }

      public Map<String, Class<?>> getStyleableInfos() {
         return FlatStylingSupport.getAnnotatedStyleableInfos(this);
      }

      public Object getStyleableValue(String key) {
         return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
      }

      void updateStyle() {
         if (this.leftButton instanceof FlatSplitPaneUI.FlatSplitPaneDivider.FlatOneTouchButton) {
            ((FlatSplitPaneUI.FlatSplitPaneDivider.FlatOneTouchButton)this.leftButton).updateStyle();
         }

         if (this.rightButton instanceof FlatSplitPaneUI.FlatSplitPaneDivider.FlatOneTouchButton) {
            ((FlatSplitPaneUI.FlatSplitPaneDivider.FlatOneTouchButton)this.rightButton).updateStyle();
         }

      }

      public void setDividerSize(int newSize) {
         super.setDividerSize(UIScale.scale(newSize));
      }

      protected JButton createLeftOneTouchButton() {
         return new FlatSplitPaneUI.FlatSplitPaneDivider.FlatOneTouchButton(true);
      }

      protected JButton createRightOneTouchButton() {
         return new FlatSplitPaneUI.FlatSplitPaneDivider.FlatOneTouchButton(false);
      }

      public void propertyChange(PropertyChangeEvent e) {
         super.propertyChange(e);
         String var2 = e.getPropertyName();
         byte var3 = -1;
         switch(var2.hashCode()) {
         case -605950482:
            if (var2.equals("dividerLocation")) {
               var3 = 0;
            }
         default:
            switch(var3) {
            case 0:
               this.doLayout();
            default:
            }
         }
      }

      public void paint(Graphics g) {
         super.paint(g);
         if (!"plain".equals(this.style)) {
            Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
            g.setColor(this.gripColor);
            this.paintGrip(g, 0, 0, this.getWidth(), this.getHeight());
            FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
         }
      }

      protected void paintGrip(Graphics g, int x, int y, int width, int height) {
         FlatUIUtils.paintGrip(g, x, y, width, height, this.splitPane.getOrientation() == 0, this.gripDotCount, this.gripDotSize, this.gripGap, true);
      }

      protected boolean isLeftCollapsed() {
         int location = this.splitPane.getDividerLocation();
         Insets insets = this.splitPane.getInsets();
         return this.orientation == 0 ? location == insets.top : location == insets.left;
      }

      protected boolean isRightCollapsed() {
         int location = this.splitPane.getDividerLocation();
         Insets insets = this.splitPane.getInsets();
         return this.orientation == 0 ? location == this.splitPane.getHeight() - this.getHeight() - insets.bottom : location == this.splitPane.getWidth() - this.getWidth() - insets.right;
      }

      protected class FlatDividerLayout extends DividerLayout {
         protected FlatDividerLayout() {
            super(FlatSplitPaneDivider.this);
         }

         public void layoutContainer(Container c) {
            super.layoutContainer(c);
            if (FlatSplitPaneDivider.this.leftButton != null && FlatSplitPaneDivider.this.rightButton != null && FlatSplitPaneDivider.this.splitPane.isOneTouchExpandable()) {
               int extraSize = UIScale.scale(4);
               if (FlatSplitPaneDivider.this.orientation == 0) {
                  FlatSplitPaneDivider.this.leftButton.setSize(FlatSplitPaneDivider.this.leftButton.getWidth() + extraSize, FlatSplitPaneDivider.this.leftButton.getHeight());
                  FlatSplitPaneDivider.this.rightButton.setBounds(FlatSplitPaneDivider.this.leftButton.getX() + FlatSplitPaneDivider.this.leftButton.getWidth(), FlatSplitPaneDivider.this.rightButton.getY(), FlatSplitPaneDivider.this.rightButton.getWidth() + extraSize, FlatSplitPaneDivider.this.rightButton.getHeight());
               } else {
                  FlatSplitPaneDivider.this.leftButton.setSize(FlatSplitPaneDivider.this.leftButton.getWidth(), FlatSplitPaneDivider.this.leftButton.getHeight() + extraSize);
                  FlatSplitPaneDivider.this.rightButton.setBounds(FlatSplitPaneDivider.this.rightButton.getX(), FlatSplitPaneDivider.this.leftButton.getY() + FlatSplitPaneDivider.this.leftButton.getHeight(), FlatSplitPaneDivider.this.rightButton.getWidth(), FlatSplitPaneDivider.this.rightButton.getHeight() + extraSize);
               }

               boolean leftCollapsed = FlatSplitPaneDivider.this.isLeftCollapsed();
               boolean rightCollapsed = FlatSplitPaneDivider.this.isRightCollapsed();
               if (!leftCollapsed && !rightCollapsed) {
                  Object expandableSide = FlatSplitPaneDivider.this.splitPane.getClientProperty("JSplitPane.expandableSide");
                  FlatSplitPaneDivider.this.leftButton.setVisible(expandableSide == null || !"left".equals(expandableSide));
                  FlatSplitPaneDivider.this.rightButton.setVisible(expandableSide == null || !"right".equals(expandableSide));
               } else {
                  FlatSplitPaneDivider.this.leftButton.setVisible(!leftCollapsed);
                  FlatSplitPaneDivider.this.rightButton.setVisible(!rightCollapsed);
               }

               if (!FlatSplitPaneDivider.this.leftButton.isVisible()) {
                  FlatSplitPaneDivider.this.rightButton.setLocation(FlatSplitPaneDivider.this.leftButton.getLocation());
               }

            }
         }
      }

      protected class FlatOneTouchButton extends FlatArrowButton {
         protected final boolean left;

         protected FlatOneTouchButton(boolean left) {
            super(1, FlatSplitPaneUI.this.arrowType, FlatSplitPaneUI.this.oneTouchArrowColor, (Color)null, FlatSplitPaneUI.this.oneTouchHoverArrowColor, (Color)null, FlatSplitPaneUI.this.oneTouchPressedArrowColor, (Color)null);
            this.setCursor(Cursor.getPredefinedCursor(0));
            ToolTipManager.sharedInstance().registerComponent(this);
            this.left = left;
         }

         protected void updateStyle() {
            this.updateStyle(FlatSplitPaneUI.this.arrowType, FlatSplitPaneUI.this.oneTouchArrowColor, (Color)null, FlatSplitPaneUI.this.oneTouchHoverArrowColor, (Color)null, FlatSplitPaneUI.this.oneTouchPressedArrowColor, (Color)null);
         }

         public int getDirection() {
            return FlatSplitPaneDivider.this.orientation == 0 ? (this.left ? 1 : 5) : (this.left ? 7 : 3);
         }

         public String getToolTipText(MouseEvent e) {
            String key = FlatSplitPaneDivider.this.orientation == 0 ? (this.left ? (FlatSplitPaneDivider.this.isRightCollapsed() ? "SplitPaneDivider.expandBottomToolTipText" : "SplitPaneDivider.collapseTopToolTipText") : (FlatSplitPaneDivider.this.isLeftCollapsed() ? "SplitPaneDivider.expandTopToolTipText" : "SplitPaneDivider.collapseBottomToolTipText")) : (this.left ? (FlatSplitPaneDivider.this.isRightCollapsed() ? "SplitPaneDivider.expandRightToolTipText" : "SplitPaneDivider.collapseLeftToolTipText") : (FlatSplitPaneDivider.this.isLeftCollapsed() ? "SplitPaneDivider.expandLeftToolTipText" : "SplitPaneDivider.collapseRightToolTipText"));
            Object value = FlatSplitPaneDivider.this.splitPane.getClientProperty(key);
            return value instanceof String ? (String)value : UIManager.getString(key, this.getLocale());
         }
      }
   }
}
