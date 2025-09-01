package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.BorderUIResource.EmptyBorderUIResource;

public class JBRCustomDecorations {
   private static Boolean supported;
   private static Method Window_hasCustomDecoration;
   private static Method Window_setHasCustomDecoration;
   private static Method WWindowPeer_setCustomDecorationTitleBarHeight;
   private static Method WWindowPeer_setCustomDecorationHitTestSpots;
   private static Method AWTAccessor_getComponentAccessor;
   private static Method AWTAccessor_ComponentAccessor_getPeer;

   public static boolean isSupported() {
      initialize();
      return supported;
   }

   static Object install(final JRootPane rootPane) {
      if (!isSupported()) {
         return null;
      } else {
         Container parent = rootPane.getParent();
         if (parent != null) {
            if (parent instanceof Window) {
               FlatNativeWindowBorder.install((Window)parent);
            }

            return null;
         } else {
            HierarchyListener addListener = new HierarchyListener() {
               public void hierarchyChanged(HierarchyEvent e) {
                  if (e.getChanged() == rootPane && (e.getChangeFlags() & 1L) != 0L) {
                     Container parent = e.getChangedParent();
                     if (parent instanceof Window) {
                        FlatNativeWindowBorder.install((Window)parent);
                     }

                     EventQueue.invokeLater(() -> {
                        rootPane.removeHierarchyListener(this);
                     });
                  }
               }
            };
            rootPane.addHierarchyListener(addListener);
            return addListener;
         }
      }
   }

   static void uninstall(JRootPane rootPane, Object data) {
      if (data instanceof HierarchyListener) {
         rootPane.removeHierarchyListener((HierarchyListener)data);
      }

      Container parent = rootPane.getParent();
      if (parent instanceof Window) {
         setHasCustomDecoration((Window)parent, false);
      }

   }

   static boolean hasCustomDecoration(Window window) {
      if (!isSupported()) {
         return false;
      } else {
         try {
            return (Boolean)Window_hasCustomDecoration.invoke(window);
         } catch (Exception var2) {
            LoggingFacade.INSTANCE.logSevere((String)null, var2);
            return false;
         }
      }
   }

   static void setHasCustomDecoration(Window window, boolean hasCustomDecoration) {
      if (isSupported()) {
         try {
            if (hasCustomDecoration) {
               Window_setHasCustomDecoration.invoke(window);
            } else {
               setTitleBarHeightAndHitTestSpots(window, 4, Collections.emptyList());
            }
         } catch (Exception var3) {
            LoggingFacade.INSTANCE.logSevere((String)null, var3);
         }

      }
   }

   static void setTitleBarHeightAndHitTestSpots(Window window, int titleBarHeight, List<Rectangle> hitTestSpots) {
      if (isSupported()) {
         try {
            Object compAccessor = AWTAccessor_getComponentAccessor.invoke((Object)null);
            Object peer = AWTAccessor_ComponentAccessor_getPeer.invoke(compAccessor, window);
            WWindowPeer_setCustomDecorationTitleBarHeight.invoke(peer, titleBarHeight);
            WWindowPeer_setCustomDecorationHitTestSpots.invoke(peer, hitTestSpots);
         } catch (Exception var5) {
            LoggingFacade.INSTANCE.logSevere((String)null, var5);
         }

      }
   }

   private static void initialize() {
      if (supported == null) {
         supported = false;
         if (SystemInfo.isJetBrainsJVM_11_orLater && SystemInfo.isWindows_10_orLater) {
            try {
               Class<?> awtAcessorClass = Class.forName("sun.awt.AWTAccessor");
               Class<?> compAccessorClass = Class.forName("sun.awt.AWTAccessor$ComponentAccessor");
               AWTAccessor_getComponentAccessor = awtAcessorClass.getDeclaredMethod("getComponentAccessor");
               AWTAccessor_ComponentAccessor_getPeer = compAccessorClass.getDeclaredMethod("getPeer", Component.class);
               Class<?> peerClass = Class.forName("sun.awt.windows.WWindowPeer");
               WWindowPeer_setCustomDecorationTitleBarHeight = peerClass.getDeclaredMethod("setCustomDecorationTitleBarHeight", Integer.TYPE);
               WWindowPeer_setCustomDecorationHitTestSpots = peerClass.getDeclaredMethod("setCustomDecorationHitTestSpots", List.class);
               WWindowPeer_setCustomDecorationTitleBarHeight.setAccessible(true);
               WWindowPeer_setCustomDecorationHitTestSpots.setAccessible(true);
               Window_hasCustomDecoration = Window.class.getDeclaredMethod("hasCustomDecoration");
               Window_setHasCustomDecoration = Window.class.getDeclaredMethod("setHasCustomDecoration");
               Window_hasCustomDecoration.setAccessible(true);
               Window_setHasCustomDecoration.setAccessible(true);
               supported = true;
            } catch (Exception var3) {
            }

         }
      }
   }

   static class JBRWindowTopBorder extends EmptyBorderUIResource {
      private static JBRCustomDecorations.JBRWindowTopBorder instance;
      private final Color activeLightColor = new Color(7368816);
      private final Color activeDarkColor = new Color(2960943);
      private final Color inactiveLightColor = new Color(11184810);
      private final Color inactiveDarkColor = new Color(4803147);
      private boolean colorizationAffectsBorders;
      private Color activeColor;

      static JBRCustomDecorations.JBRWindowTopBorder getInstance() {
         if (instance == null) {
            instance = new JBRCustomDecorations.JBRWindowTopBorder();
         }

         return instance;
      }

      JBRWindowTopBorder() {
         super(1, 0, 0, 0);
         this.update();
         this.installListeners();
      }

      void update() {
         this.colorizationAffectsBorders = this.isColorizationColorAffectsBorders();
         this.activeColor = this.calculateActiveBorderColor();
      }

      void installListeners() {
         Toolkit toolkit = Toolkit.getDefaultToolkit();
         toolkit.addPropertyChangeListener("win.dwm.colorizationColor.affects.borders", (e) -> {
            this.colorizationAffectsBorders = this.isColorizationColorAffectsBorders();
            this.activeColor = this.calculateActiveBorderColor();
         });
         PropertyChangeListener l = (e) -> {
            this.activeColor = this.calculateActiveBorderColor();
         };
         toolkit.addPropertyChangeListener("win.dwm.colorizationColor", l);
         toolkit.addPropertyChangeListener("win.dwm.colorizationColorBalance", l);
         toolkit.addPropertyChangeListener("win.frame.activeBorderColor", l);
      }

      boolean isColorizationColorAffectsBorders() {
         Object value = Toolkit.getDefaultToolkit().getDesktopProperty("win.dwm.colorizationColor.affects.borders");
         return value instanceof Boolean ? (Boolean)value : true;
      }

      Color getColorizationColor() {
         return (Color)Toolkit.getDefaultToolkit().getDesktopProperty("win.dwm.colorizationColor");
      }

      int getColorizationColorBalance() {
         Object value = Toolkit.getDefaultToolkit().getDesktopProperty("win.dwm.colorizationColorBalance");
         return value instanceof Integer ? (Integer)value : -1;
      }

      private Color calculateActiveBorderColor() {
         if (!this.colorizationAffectsBorders) {
            return null;
         } else {
            Color colorizationColor = this.getColorizationColor();
            if (colorizationColor == null) {
               Color activeBorderColor = (Color)Toolkit.getDefaultToolkit().getDesktopProperty("win.frame.activeBorderColor");
               return activeBorderColor != null ? activeBorderColor : UIManager.getColor("MenuBar.borderColor");
            } else {
               int colorizationColorBalance = this.getColorizationColorBalance();
               if (colorizationColorBalance < 0 || colorizationColorBalance > 100) {
                  colorizationColorBalance = 100;
               }

               if (colorizationColorBalance == 0) {
                  return new Color(14277081);
               } else if (colorizationColorBalance == 100) {
                  return colorizationColor;
               } else {
                  float alpha = (float)colorizationColorBalance / 100.0F;
                  float remainder = 1.0F - alpha;
                  int r = Math.round((float)colorizationColor.getRed() * alpha + 217.0F * remainder);
                  int g = Math.round((float)colorizationColor.getGreen() * alpha + 217.0F * remainder);
                  int b = Math.round((float)colorizationColor.getBlue() * alpha + 217.0F * remainder);
                  r = Math.min(Math.max(r, 0), 255);
                  g = Math.min(Math.max(g, 0), 255);
                  b = Math.min(Math.max(b, 0), 255);
                  return new Color(r, g, b);
               }
            }
         }
      }

      public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
         Window window = SwingUtilities.windowForComponent(c);
         boolean active = window != null && window.isActive();
         boolean dark = FlatLaf.isLafDark();
         g.setColor(active ? (this.activeColor != null ? this.activeColor : (dark ? this.activeDarkColor : this.activeLightColor)) : (dark ? this.inactiveDarkColor : this.inactiveLightColor));
         HiDPIUtils.paintAtScale1x((Graphics2D)g, x, y, width, height, this::paintImpl);
      }

      private void paintImpl(Graphics2D g, int x, int y, int width, int height, double scaleFactor) {
         g.fillRect(x, y, width, 1);
      }

      void repaintBorder(Component c) {
         c.repaint(0, 0, c.getWidth(), 1);
      }
   }
}
