package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Panel;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolTip;
import javax.swing.JWindow;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboPopup;

public class FlatPopupFactory extends PopupFactory {
   private MethodHandle java8getPopupMethod;
   private MethodHandle java9getPopupMethod;

   public Popup getPopup(Component owner, Component contents, int x, int y) throws IllegalArgumentException {
      Point pt = this.fixToolTipLocation(owner, contents, x, y);
      if (pt != null) {
         x = pt.x;
         y = pt.y;
      }

      boolean forceHeavyWeight = this.isOptionEnabled(owner, contents, "Popup.forceHeavyWeight", "Popup.forceHeavyWeight");
      if (this.isOptionEnabled(owner, contents, "Popup.dropShadowPainted", "Popup.dropShadowPainted") && !SystemInfo.isProjector && !SystemInfo.isWebswing) {
         if (!SystemInfo.isMacOS && !SystemInfo.isLinux) {
            int borderCornerRadius;
            if (isWindows11BorderSupported() && (borderCornerRadius = this.getBorderCornerRadius(owner, contents)) > 0) {
               FlatPopupFactory.NonFlashingPopup popup = new FlatPopupFactory.NonFlashingPopup(this.getPopupForScreenOfOwner(owner, contents, x, y, true), contents);
               if (popup.popupWindow != null) {
                  setupWindows11Border(popup.popupWindow, contents, borderCornerRadius);
               }

               return popup;
            } else {
               return new FlatPopupFactory.DropShadowPopup(this.getPopupForScreenOfOwner(owner, contents, x, y, forceHeavyWeight), owner, contents);
            }
         } else {
            return new FlatPopupFactory.NonFlashingPopup(this.getPopupForScreenOfOwner(owner, contents, x, y, true), contents);
         }
      } else {
         return new FlatPopupFactory.NonFlashingPopup(this.getPopupForScreenOfOwner(owner, contents, x, y, forceHeavyWeight), contents);
      }
   }

   private Popup getPopupForScreenOfOwner(Component owner, Component contents, int x, int y, boolean forceHeavyWeight) throws IllegalArgumentException {
      int count = 0;

      while(true) {
         Popup popup = forceHeavyWeight ? this.getHeavyWeightPopup(owner, contents, x, y) : super.getPopup(owner, contents, x, y);
         Window popupWindow = SwingUtilities.windowForComponent(contents);
         if (popupWindow == null || owner == null || popupWindow.getGraphicsConfiguration() == owner.getGraphicsConfiguration()) {
            return popup;
         }

         ++count;
         if (count > 10) {
            return popup;
         }

         if (popupWindow instanceof JWindow) {
            ((JWindow)popupWindow).getContentPane().removeAll();
         }

         popupWindow.dispose();
      }
   }

   private static void showPopupAndFixLocation(Popup popup, Window popupWindow) {
      if (popupWindow != null) {
         int x = popupWindow.getX();
         int y = popupWindow.getY();
         popup.show();
         if (popupWindow.getX() != x || popupWindow.getY() != y) {
            popupWindow.setLocation(x, y);
         }
      } else {
         popup.show();
      }

   }

   private boolean isOptionEnabled(Component owner, Component contents, String clientKey, String uiKey) {
      Object value = this.getOption(owner, contents, clientKey, uiKey);
      return value instanceof Boolean ? (Boolean)value : false;
   }

   private int getBorderCornerRadius(Component owner, Component contents) {
      String uiKey = contents instanceof BasicComboPopup ? "ComboBox.borderCornerRadius" : (contents instanceof JPopupMenu ? "PopupMenu.borderCornerRadius" : (contents instanceof JToolTip ? "ToolTip.borderCornerRadius" : "Popup.borderCornerRadius"));
      Object value = this.getOption(owner, contents, "Popup.borderCornerRadius", uiKey);
      return value instanceof Integer ? (Integer)value : 0;
   }

   private Object getOption(Component owner, Component contents, String clientKey, String uiKey) {
      Component[] var5 = new Component[]{owner, contents};
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         Component c = var5[var7];
         if (c instanceof JComponent) {
            Object value = ((JComponent)c).getClientProperty(clientKey);
            if (value != null) {
               return value;
            }
         }
      }

      return UIManager.get(uiKey);
   }

   private Popup getHeavyWeightPopup(Component owner, Component contents, int x, int y) throws IllegalArgumentException {
      try {
         if (SystemInfo.isJava_9_orLater) {
            if (this.java9getPopupMethod == null) {
               MethodType mt = MethodType.methodType(Popup.class, Component.class, Component.class, Integer.TYPE, Integer.TYPE, Boolean.TYPE);
               this.java9getPopupMethod = MethodHandles.lookup().findVirtual(PopupFactory.class, "getPopup", mt);
            }

            return this.java9getPopupMethod.invoke(this, owner, contents, x, y, true);
         } else {
            if (this.java8getPopupMethod == null) {
               Method m = PopupFactory.class.getDeclaredMethod("getPopup", Component.class, Component.class, Integer.TYPE, Integer.TYPE, Integer.TYPE);
               m.setAccessible(true);
               this.java8getPopupMethod = MethodHandles.lookup().unreflect(m);
            }

            return this.java8getPopupMethod.invoke(this, owner, contents, x, y, 2);
         }
      } catch (Throwable var6) {
         return super.getPopup(owner, contents, x, y);
      }
   }

   private Point fixToolTipLocation(Component owner, Component contents, int x, int y) {
      if (contents instanceof JToolTip && this.wasInvokedFromToolTipManager() && !this.hasTipLocation(owner)) {
         PointerInfo pointerInfo = MouseInfo.getPointerInfo();
         if (pointerInfo == null) {
            return null;
         } else {
            Point mouseLocation = pointerInfo.getLocation();
            Dimension tipSize = contents.getPreferredSize();
            Rectangle tipBounds = new Rectangle(x, y, tipSize.width, tipSize.height);
            if (!tipBounds.contains(mouseLocation)) {
               return null;
            } else {
               GraphicsConfiguration gc = null;
               GraphicsDevice[] var10 = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
               int var11 = var10.length;

               int screenTop;
               for(screenTop = 0; screenTop < var11; ++screenTop) {
                  GraphicsDevice device = var10[screenTop];
                  GraphicsConfiguration dgc = device.getDefaultConfiguration();
                  if (dgc.getBounds().contains(mouseLocation)) {
                     gc = dgc;
                     break;
                  }
               }

               if (gc == null) {
                  gc = owner.getGraphicsConfiguration();
               }

               if (gc == null) {
                  return null;
               } else {
                  Rectangle screenBounds = gc.getBounds();
                  Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gc);
                  screenTop = screenBounds.y + screenInsets.top;
                  int newY = mouseLocation.y - tipSize.height - UIScale.scale(20);
                  return newY < screenTop ? null : new Point(x, newY);
               }
            }
         }
      } else {
         return null;
      }
   }

   private boolean wasInvokedFromToolTipManager() {
      return StackUtils.wasInvokedFrom(ToolTipManager.class.getName(), "showTipWindow", 8);
   }

   private boolean hasTipLocation(Component owner) {
      if (!(owner instanceof JComponent)) {
         return false;
      } else {
         AWTEvent e = EventQueue.getCurrentEvent();
         MouseEvent me;
         if (e instanceof MouseEvent) {
            me = (MouseEvent)e;
         } else {
            PointerInfo pointerInfo = MouseInfo.getPointerInfo();
            if (pointerInfo == null) {
               return false;
            }

            Point location = new Point(pointerInfo.getLocation());
            SwingUtilities.convertPointFromScreen(location, owner);
            me = new MouseEvent(owner, 503, System.currentTimeMillis(), 0, location.x, location.y, 0, false);
         }

         return me.getSource() == owner && ((JComponent)owner).getToolTipLocation(me) != null;
      }
   }

   private static boolean isWindows11BorderSupported() {
      return SystemInfo.isWindows_11_orLater && FlatNativeWindowsLibrary.isLoaded();
   }

   private static void setupWindows11Border(Window popupWindow, Component contents, int borderCornerRadius) {
      if (!popupWindow.isDisplayable()) {
         popupWindow.addNotify();
      }

      long hwnd = FlatNativeWindowsLibrary.getHWND(popupWindow);
      int cornerPreference = borderCornerRadius <= 4 ? 3 : 2;
      FlatNativeWindowsLibrary.setWindowCornerPreference(hwnd, cornerPreference);
      int red = -1;
      int green = 0;
      int blue = 0;
      if (contents instanceof JComponent) {
         Border border = ((JComponent)contents).getBorder();
         border = FlatUIUtils.unwrapNonUIResourceBorder(border);
         Color borderColor = null;
         if (border instanceof FlatLineBorder) {
            borderColor = ((FlatLineBorder)border).getLineColor();
         } else if (border instanceof LineBorder) {
            borderColor = ((LineBorder)border).getLineColor();
         } else if (border instanceof EmptyBorder) {
            red = -2;
         }

         if (borderColor != null) {
            red = borderColor.getRed();
            green = borderColor.getGreen();
            blue = borderColor.getBlue();
         }
      }

      FlatNativeWindowsLibrary.setWindowBorderColor(hwnd, red, green, blue);
   }

   private static void resetWindows11Border(Window popupWindow) {
      long hwnd = FlatNativeWindowsLibrary.getHWND(popupWindow);
      if (hwnd != 0L) {
         FlatNativeWindowsLibrary.setWindowCornerPreference(hwnd, 1);
      }
   }

   private class DropShadowPopup extends FlatPopupFactory.NonFlashingPopup {
      private final Component owner;
      private JComponent lightComp;
      private Border oldBorder;
      private boolean oldOpaque;
      private boolean mediumWeightShown;
      private Panel mediumWeightPanel;
      private JPanel dropShadowPanel;
      private ComponentListener mediumPanelListener;
      private Popup dropShadowDelegate;
      private Window dropShadowWindow;
      private Color oldDropShadowWindowBackground;

      DropShadowPopup(Popup delegate, Component owner, Component contents) {
         super(delegate, contents);
         this.owner = owner;
         Dimension size = contents.getPreferredSize();
         if (size.width > 0 && size.height > 0) {
            if (this.popupWindow != null) {
               JPanel dropShadowPanel = new JPanel();
               dropShadowPanel.setBorder(this.createDropShadowBorder());
               dropShadowPanel.setOpaque(false);
               Dimension prefSize = this.popupWindow.getPreferredSize();
               Insets insets = dropShadowPanel.getInsets();
               dropShadowPanel.setPreferredSize(new Dimension(prefSize.width + insets.left + insets.right, prefSize.height + insets.top + insets.bottom));
               int x = this.popupWindow.getX() - insets.left;
               int y = this.popupWindow.getY() - insets.top;
               this.dropShadowDelegate = FlatPopupFactory.this.getPopupForScreenOfOwner(owner, dropShadowPanel, x, y, true);
               this.dropShadowWindow = SwingUtilities.windowForComponent(dropShadowPanel);
               if (this.dropShadowWindow != null) {
                  this.oldDropShadowWindowBackground = this.dropShadowWindow.getBackground();
                  this.dropShadowWindow.setBackground(new Color(0, true));
               }

               if (FlatPopupFactory.isWindows11BorderSupported()) {
                  FlatPopupFactory.resetWindows11Border(this.popupWindow);
                  if (this.dropShadowWindow != null) {
                     FlatPopupFactory.resetWindows11Border(this.dropShadowWindow);
                  }
               }
            } else {
               this.mediumWeightPanel = (Panel)SwingUtilities.getAncestorOfClass(Panel.class, contents);
               if (this.mediumWeightPanel != null) {
                  this.dropShadowPanel = new JPanel();
                  this.dropShadowPanel.setBorder(this.createDropShadowBorder());
                  this.dropShadowPanel.setOpaque(false);
                  this.dropShadowPanel.setSize(FlatUIUtils.addInsets(this.mediumWeightPanel.getSize(), this.dropShadowPanel.getInsets()));
               } else {
                  Container p = contents.getParent();
                  if (!(p instanceof JComponent)) {
                     return;
                  }

                  this.lightComp = (JComponent)p;
                  this.oldBorder = this.lightComp.getBorder();
                  this.oldOpaque = this.lightComp.isOpaque();
                  this.lightComp.setBorder(this.createDropShadowBorder());
                  this.lightComp.setOpaque(false);
                  this.lightComp.setSize(this.lightComp.getPreferredSize());
               }
            }

         }
      }

      private Border createDropShadowBorder() {
         return new FlatDropShadowBorder(UIManager.getColor("Popup.dropShadowColor"), UIManager.getInsets("Popup.dropShadowInsets"), FlatUIUtils.getUIFloat("Popup.dropShadowOpacity", 0.5F));
      }

      public void show() {
         if (this.dropShadowDelegate != null) {
            FlatPopupFactory.showPopupAndFixLocation(this.dropShadowDelegate, this.dropShadowWindow);
         }

         if (this.mediumWeightPanel != null) {
            this.showMediumWeightDropShadow();
         }

         super.show();
         if (this.lightComp != null) {
            Insets insets = this.lightComp.getInsets();
            if (insets.left != 0 || insets.top != 0) {
               this.lightComp.setLocation(this.lightComp.getX() - insets.left, this.lightComp.getY() - insets.top);
            }
         }

      }

      public void hide() {
         if (this.dropShadowDelegate != null) {
            this.dropShadowDelegate.hide();
            this.dropShadowDelegate = null;
         }

         if (this.mediumWeightPanel != null) {
            this.hideMediumWeightDropShadow();
            this.dropShadowPanel = null;
            this.mediumWeightPanel = null;
         }

         super.hide();
         if (this.dropShadowWindow != null) {
            this.dropShadowWindow.setBackground(this.oldDropShadowWindowBackground);
            this.dropShadowWindow = null;
         }

         if (this.lightComp != null) {
            this.lightComp.setBorder(this.oldBorder);
            this.lightComp.setOpaque(this.oldOpaque);
            this.lightComp = null;
         }

      }

      private void showMediumWeightDropShadow() {
         if (!this.mediumWeightShown) {
            this.mediumWeightShown = true;
            if (this.owner != null) {
               Window window = SwingUtilities.windowForComponent(this.owner);
               if (window instanceof RootPaneContainer) {
                  this.dropShadowPanel.setVisible(false);
                  JLayeredPane layeredPane = ((RootPaneContainer)window).getLayeredPane();
                  layeredPane.add(this.dropShadowPanel, JLayeredPane.POPUP_LAYER, 0);
                  this.moveMediumWeightDropShadow();
                  this.resizeMediumWeightDropShadow();
                  this.mediumPanelListener = new ComponentListener() {
                     public void componentShown(ComponentEvent e) {
                        if (DropShadowPopup.this.dropShadowPanel != null) {
                           DropShadowPopup.this.dropShadowPanel.setVisible(true);
                        }

                     }

                     public void componentHidden(ComponentEvent e) {
                        if (DropShadowPopup.this.dropShadowPanel != null) {
                           DropShadowPopup.this.dropShadowPanel.setVisible(false);
                        }

                     }

                     public void componentMoved(ComponentEvent e) {
                        DropShadowPopup.this.moveMediumWeightDropShadow();
                     }

                     public void componentResized(ComponentEvent e) {
                        DropShadowPopup.this.resizeMediumWeightDropShadow();
                     }
                  };
                  this.mediumWeightPanel.addComponentListener(this.mediumPanelListener);
               }
            }
         }
      }

      private void hideMediumWeightDropShadow() {
         this.mediumWeightPanel.removeComponentListener(this.mediumPanelListener);
         Container parent = this.dropShadowPanel.getParent();
         if (parent != null) {
            Rectangle bounds = this.dropShadowPanel.getBounds();
            parent.remove(this.dropShadowPanel);
            parent.repaint(bounds.x, bounds.y, bounds.width, bounds.height);
         }

      }

      private void moveMediumWeightDropShadow() {
         if (this.dropShadowPanel != null && this.mediumWeightPanel != null) {
            Point location = this.mediumWeightPanel.getLocation();
            Insets insets = this.dropShadowPanel.getInsets();
            this.dropShadowPanel.setLocation(location.x - insets.left, location.y - insets.top);
         }

      }

      private void resizeMediumWeightDropShadow() {
         if (this.dropShadowPanel != null && this.mediumWeightPanel != null) {
            this.dropShadowPanel.setSize(FlatUIUtils.addInsets(this.mediumWeightPanel.getSize(), this.dropShadowPanel.getInsets()));
         }

      }
   }

   private class NonFlashingPopup extends Popup {
      private Popup delegate;
      private Component contents;
      protected Window popupWindow;
      private Color oldPopupWindowBackground;

      NonFlashingPopup(Popup delegate, Component contents) {
         this.delegate = delegate;
         this.contents = contents;
         this.popupWindow = SwingUtilities.windowForComponent(contents);
         if (this.popupWindow != null) {
            this.oldPopupWindowBackground = this.popupWindow.getBackground();
            this.popupWindow.setBackground(contents.getBackground());
         }

      }

      public void show() {
         if (this.delegate != null) {
            FlatPopupFactory.showPopupAndFixLocation(this.delegate, this.popupWindow);
            if (this.contents instanceof JToolTip && this.popupWindow == null) {
               Container parent = this.contents.getParent();
               if (parent instanceof JPanel) {
                  Dimension prefSize = parent.getPreferredSize();
                  if (!prefSize.equals(parent.getSize())) {
                     Container mediumWeightPanel = SwingUtilities.getAncestorOfClass(Panel.class, parent);
                     Container c = mediumWeightPanel != null ? mediumWeightPanel : parent;
                     c.setSize(prefSize);
                     c.validate();
                  }
               }
            }
         }

      }

      public void hide() {
         if (this.delegate != null) {
            this.delegate.hide();
            this.delegate = null;
            this.contents = null;
         }

         if (this.popupWindow != null) {
            this.popupWindow.setBackground(this.oldPopupWindowBackground);
            this.popupWindow = null;
         }

      }
   }
}
