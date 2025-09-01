package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultButtonModel;
import javax.swing.InputMap;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.LayoutFocusTraversalPolicy;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToolBarUI;
import javax.swing.plaf.basic.BasicToolBarUI.ToolBarContListener;

public class FlatToolBarUI extends BasicToolBarUI implements FlatStylingSupport.StyleableUI {
   @FlatStylingSupport.Styleable
   protected boolean focusableButtons;
   @FlatStylingSupport.Styleable
   protected boolean arrowKeysOnlyNavigation;
   @FlatStylingSupport.Styleable
   protected int hoverButtonGroupArc;
   @FlatStylingSupport.Styleable
   protected Color hoverButtonGroupBackground;
   @FlatStylingSupport.Styleable
   protected Insets borderMargins;
   @FlatStylingSupport.Styleable
   protected Color gripColor;
   private FocusTraversalPolicy focusTraversalPolicy;
   private Boolean oldFloatable;
   private Map<String, Object> oldStyleValues;

   public static ComponentUI createUI(JComponent c) {
      return new FlatToolBarUI();
   }

   public void installUI(JComponent c) {
      super.installUI(c);
      this.installFocusTraversalPolicy();
      this.installStyle();
      if (!this.focusableButtons) {
         this.setButtonsFocusable(false);
      }

   }

   public void uninstallUI(JComponent c) {
      super.uninstallUI(c);
      if (!this.focusableButtons) {
         this.setButtonsFocusable(true);
      }

      this.uninstallFocusTraversalPolicy();
      this.oldStyleValues = null;
   }

   protected void installDefaults() {
      super.installDefaults();
      this.focusableButtons = UIManager.getBoolean("ToolBar.focusableButtons");
      this.arrowKeysOnlyNavigation = UIManager.getBoolean("ToolBar.arrowKeysOnlyNavigation");
      this.hoverButtonGroupArc = UIManager.getInt("ToolBar.hoverButtonGroupArc");
      this.hoverButtonGroupBackground = UIManager.getColor("ToolBar.hoverButtonGroupBackground");
      if (!UIManager.getBoolean("ToolBar.floatable")) {
         this.oldFloatable = this.toolBar.isFloatable();
         this.toolBar.setFloatable(false);
      } else {
         this.oldFloatable = null;
      }

   }

   protected void uninstallDefaults() {
      super.uninstallDefaults();
      this.hoverButtonGroupBackground = null;
      if (this.oldFloatable != null) {
         this.toolBar.setFloatable(this.oldFloatable);
         this.oldFloatable = null;
      }

   }

   protected ContainerListener createToolBarContListener() {
      return new ToolBarContListener() {
         public void componentAdded(ContainerEvent e) {
            super.componentAdded(e);
            if (!FlatToolBarUI.this.focusableButtons) {
               FlatToolBarUI.this.setButtonFocusable(e.getChild(), false);
            }

         }

         public void componentRemoved(ContainerEvent e) {
            super.componentRemoved(e);
            if (!FlatToolBarUI.this.focusableButtons) {
               FlatToolBarUI.this.setButtonFocusable(e.getChild(), true);
            }

         }
      };
   }

   protected PropertyChangeListener createPropertyListener() {
      return FlatStylingSupport.createPropertyChangeListener(this.toolBar, this::installStyle, super.createPropertyListener());
   }

   protected void installStyle() {
      try {
         this.applyStyle(FlatStylingSupport.getResolvedStyle(this.toolBar, "ToolBar"));
      } catch (RuntimeException var2) {
         LoggingFacade.INSTANCE.logSevere((String)null, var2);
      }

   }

   protected void applyStyle(Object style) {
      boolean oldFocusableButtons = this.focusableButtons;
      boolean oldArrowKeysOnlyNavigation = this.arrowKeysOnlyNavigation;
      this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
      if (this.focusableButtons != oldFocusableButtons) {
         this.setButtonsFocusable(this.focusableButtons);
      }

      if (this.arrowKeysOnlyNavigation != oldArrowKeysOnlyNavigation || this.focusableButtons != oldFocusableButtons) {
         if (this.arrowKeysOnlyNavigation) {
            this.installFocusTraversalPolicy();
         } else {
            this.uninstallFocusTraversalPolicy();
         }
      }

   }

   protected Object applyStyleProperty(String key, Object value) {
      return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, this.toolBar, key, value);
   }

   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
      return FlatStylingSupport.getAnnotatedStyleableInfos(this);
   }

   public Object getStyleableValue(JComponent c, String key) {
      return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
   }

   protected void setButtonsFocusable(boolean focusable) {
      Component[] var2 = this.toolBar.getComponents();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Component c = var2[var4];
         this.setButtonFocusable(c, focusable);
      }

   }

   private void setButtonFocusable(Component c, boolean focusable) {
      if (c instanceof AbstractButton && focusable != c.isFocusable()) {
         c.setFocusable(focusable);
      }

   }

   protected void installFocusTraversalPolicy() {
      if (this.arrowKeysOnlyNavigation && this.focusableButtons && this.toolBar.getFocusTraversalPolicy() == null) {
         this.focusTraversalPolicy = this.createFocusTraversalPolicy();
         if (this.focusTraversalPolicy != null) {
            this.toolBar.setFocusTraversalPolicy(this.focusTraversalPolicy);
            this.toolBar.setFocusTraversalPolicyProvider(true);
         }

      }
   }

   protected void uninstallFocusTraversalPolicy() {
      if (this.focusTraversalPolicy != null && this.toolBar.getFocusTraversalPolicy() == this.focusTraversalPolicy) {
         this.toolBar.setFocusTraversalPolicy((FocusTraversalPolicy)null);
         this.toolBar.setFocusTraversalPolicyProvider(false);
      }

      this.focusTraversalPolicy = null;
   }

   protected FocusTraversalPolicy createFocusTraversalPolicy() {
      return new FlatToolBarUI.FlatToolBarFocusTraversalPolicy();
   }

   protected void navigateFocusedComp(int direction) {
      int count = this.toolBar.getComponentCount();
      if (this.focusedCompIndex >= 0 && this.focusedCompIndex < count) {
         byte add;
         switch(direction) {
         case 1:
         case 7:
            add = -1;
            break;
         case 2:
         case 4:
         case 6:
         default:
            return;
         case 3:
         case 5:
            add = 1;
         }

         int i = this.focusedCompIndex;

         Component c;
         do {
            i += add;
            if (i < 0) {
               i = count - 1;
            } else if (i >= count) {
               i = 0;
            }

            if (i == this.focusedCompIndex) {
               return;
            }

            c = this.toolBar.getComponentAtIndex(i);
         } while(!canBeFocusOwner(c));

         c.requestFocus();
      }
   }

   private static boolean canBeFocusOwner(Component c) {
      if (c != null && c.isEnabled() && c.isVisible() && c.isDisplayable() && c.isFocusable()) {
         if (c instanceof JComboBox) {
            JComboBox<?> comboBox = (JComboBox)c;
            return comboBox.getUI().isFocusTraversable(comboBox);
         } else {
            if (c instanceof JComponent) {
               InputMap inputMap;
               for(inputMap = ((JComponent)c).getInputMap(0); inputMap != null && inputMap.size() == 0; inputMap = inputMap.getParent()) {
               }

               if (inputMap == null) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   protected void setBorderToRollover(Component c) {
   }

   protected void setBorderToNonRollover(Component c) {
   }

   protected void setBorderToNormal(Component c) {
   }

   protected void installRolloverBorders(JComponent c) {
   }

   protected void installNonRolloverBorders(JComponent c) {
   }

   protected void installNormalBorders(JComponent c) {
   }

   protected Border createRolloverBorder() {
      return null;
   }

   protected Border createNonRolloverBorder() {
      return null;
   }

   public void setOrientation(int orientation) {
      if (orientation != this.toolBar.getOrientation()) {
         Insets margin = this.toolBar.getMargin();
         Insets newMargin = new Insets(margin.left, margin.top, margin.right, margin.bottom);
         if (!newMargin.equals(margin)) {
            this.toolBar.setMargin(newMargin);
         }
      }

      super.setOrientation(orientation);
   }

   public void paint(Graphics g, JComponent c) {
      super.paint(g, c);
      this.paintButtonGroup(g);
   }

   protected void paintButtonGroup(Graphics g) {
      if (this.hoverButtonGroupBackground != null) {
         ButtonGroup group = null;
         Component[] var3 = this.toolBar.getComponents();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            Component b = var3[var5];
            if (b instanceof AbstractButton && ((AbstractButton)b).getModel().isRollover()) {
               group = this.getButtonGroup((AbstractButton)b);
               if (group != null) {
                  break;
               }
            }
         }

         if (group != null) {
            ArrayList<Rectangle> rects = new ArrayList();
            Enumeration e = group.getElements();

            while(e.hasMoreElements()) {
               AbstractButton gb = (AbstractButton)e.nextElement();
               if (gb.getParent() == this.toolBar) {
                  rects.add(gb.getBounds());
               }
            }

            boolean horizontal = this.toolBar.getOrientation() == 0;
            rects.sort((r1, r2) -> {
               return horizontal ? r1.x - r2.x : r1.y - r2.y;
            });
            Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
            g.setColor(FlatUIUtils.deriveColor(this.hoverButtonGroupBackground, this.toolBar.getBackground()));
            int maxSepWidth = UIScale.scale(10);
            Rectangle gr = null;
            Iterator var9 = rects.iterator();

            while(true) {
               while(var9.hasNext()) {
                  Rectangle r = (Rectangle)var9.next();
                  if (gr == null) {
                     gr = r;
                  } else {
                     label55: {
                        if (horizontal) {
                           if (gr.x + gr.width + maxSepWidth >= r.x) {
                              break label55;
                           }
                        } else if (gr.y + gr.height + maxSepWidth >= r.y) {
                           break label55;
                        }

                        FlatUIUtils.paintComponentBackground((Graphics2D)g, gr.x, gr.y, gr.width, gr.height, 0.0F, (float)UIScale.scale(this.hoverButtonGroupArc));
                        gr = r;
                        continue;
                     }

                     gr = gr.union(r);
                  }
               }

               if (gr != null) {
                  FlatUIUtils.paintComponentBackground((Graphics2D)g, gr.x, gr.y, gr.width, gr.height, 0.0F, (float)UIScale.scale(this.hoverButtonGroupArc));
               }

               FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
               return;
            }
         }
      }
   }

   protected void repaintButtonGroup(AbstractButton b) {
      if (this.hoverButtonGroupBackground != null) {
         ButtonGroup group = this.getButtonGroup(b);
         if (group != null) {
            Rectangle gr = null;
            Enumeration e = group.getElements();

            while(e.hasMoreElements()) {
               AbstractButton gb = (AbstractButton)e.nextElement();
               Container parent = gb.getParent();
               if (parent == this.toolBar) {
                  gr = gr != null ? gr.union(gb.getBounds()) : gb.getBounds();
               }
            }

            if (gr != null) {
               this.toolBar.repaint(gr);
            }

         }
      }
   }

   private ButtonGroup getButtonGroup(AbstractButton b) {
      ButtonModel model = b.getModel();
      return model instanceof DefaultButtonModel ? ((DefaultButtonModel)model).getGroup() : null;
   }

   protected class FlatToolBarFocusTraversalPolicy extends LayoutFocusTraversalPolicy {
      public Component getComponentAfter(Container aContainer, Component aComponent) {
         if (!(aComponent instanceof AbstractButton)) {
            return super.getComponentAfter(aContainer, aComponent);
         } else {
            Component c = aComponent;

            do {
               if ((c = super.getComponentAfter(aContainer, c)) == null) {
                  return null;
               }
            } while(c instanceof AbstractButton);

            return c;
         }
      }

      public Component getComponentBefore(Container aContainer, Component aComponent) {
         if (!(aComponent instanceof AbstractButton)) {
            return super.getComponentBefore(aContainer, aComponent);
         } else {
            Component c = aComponent;

            do {
               if ((c = super.getComponentBefore(aContainer, c)) == null) {
                  return null;
               }
            } while(c instanceof AbstractButton);

            return c;
         }
      }

      public Component getFirstComponent(Container aContainer) {
         return this.getRecentComponent(aContainer, true);
      }

      public Component getLastComponent(Container aContainer) {
         return this.getRecentComponent(aContainer, false);
      }

      private Component getRecentComponent(Container aContainer, boolean first) {
         if (FlatToolBarUI.this.focusedCompIndex >= 0 && FlatToolBarUI.this.focusedCompIndex < FlatToolBarUI.this.toolBar.getComponentCount()) {
            return FlatToolBarUI.this.toolBar.getComponent(FlatToolBarUI.this.focusedCompIndex);
         } else {
            return first ? super.getFirstComponent(aContainer) : super.getLastComponent(aContainer);
         }
      }
   }
}
