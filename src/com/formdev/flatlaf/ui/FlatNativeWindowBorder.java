package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatSystemProperties;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Window;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;

public class FlatNativeWindowBorder {
   private static final boolean canUseWindowDecorations;
   private static final boolean canUseJBRCustomDecorations;
   private static Boolean supported;
   private static FlatNativeWindowBorder.Provider nativeProvider;

   public static boolean isSupported() {
      if (canUseJBRCustomDecorations) {
         return JBRCustomDecorations.isSupported();
      } else {
         initialize();
         return supported;
      }
   }

   static Object install(JRootPane rootPane) {
      if (canUseJBRCustomDecorations) {
         return JBRCustomDecorations.install(rootPane);
      } else if (!isSupported()) {
         return null;
      } else {
         Container parent = rootPane.getParent();
         if (parent != null && !(parent instanceof Window)) {
            return null;
         } else {
            if (parent instanceof Window && parent.isDisplayable()) {
               install((Window)parent);
            }

            PropertyChangeListener ancestorListener = (e) -> {
               Object newValue = e.getNewValue();
               if (newValue instanceof Window) {
                  install((Window)newValue);
               } else if (newValue == null && e.getOldValue() instanceof Window) {
                  uninstall((Window)e.getOldValue());
               }

            };
            rootPane.addPropertyChangeListener("ancestor", ancestorListener);
            return ancestorListener;
         }
      }
   }

   static void install(Window window) {
      if (!hasCustomDecoration(window)) {
         if (!UIManager.getLookAndFeel().getSupportsWindowDecorations()) {
            JRootPane rootPane;
            if (window instanceof JFrame) {
               JFrame frame = (JFrame)window;
               rootPane = frame.getRootPane();
               if (!useWindowDecorations(rootPane)) {
                  return;
               }

               if (frame.isUndecorated()) {
                  return;
               }

               setHasCustomDecoration(frame, true);
               if (!hasCustomDecoration(frame)) {
                  return;
               }

               rootPane.setWindowDecorationStyle(1);
            } else if (window instanceof JDialog) {
               JDialog dialog = (JDialog)window;
               rootPane = dialog.getRootPane();
               if (!useWindowDecorations(rootPane)) {
                  return;
               }

               if (dialog.isUndecorated()) {
                  return;
               }

               setHasCustomDecoration(dialog, true);
               if (!hasCustomDecoration(dialog)) {
                  return;
               }

               rootPane.setWindowDecorationStyle(2);
            }

         }
      }
   }

   static void uninstall(JRootPane rootPane, Object data) {
      if (canUseJBRCustomDecorations) {
         JBRCustomDecorations.uninstall(rootPane, data);
      } else if (isSupported()) {
         if (data instanceof PropertyChangeListener) {
            rootPane.removePropertyChangeListener("ancestor", (PropertyChangeListener)data);
         }

         if (!(UIManager.getLookAndFeel() instanceof FlatLaf) || !useWindowDecorations(rootPane)) {
            Container parent = rootPane.getParent();
            if (parent instanceof Window) {
               uninstall((Window)parent);
            }

         }
      }
   }

   private static void uninstall(Window window) {
      if (hasCustomDecoration(window)) {
         setHasCustomDecoration(window, false);
         if (window instanceof JFrame) {
            JFrame frame = (JFrame)window;
            frame.getRootPane().setWindowDecorationStyle(0);
         } else if (window instanceof JDialog) {
            JDialog dialog = (JDialog)window;
            dialog.getRootPane().setWindowDecorationStyle(0);
         }

      }
   }

   private static boolean useWindowDecorations(JRootPane rootPane) {
      return FlatUIUtils.getBoolean(rootPane, "flatlaf.useWindowDecorations", "JRootPane.useWindowDecorations", "TitlePane.useWindowDecorations", false);
   }

   public static boolean hasCustomDecoration(Window window) {
      if (canUseJBRCustomDecorations) {
         return JBRCustomDecorations.hasCustomDecoration(window);
      } else {
         return !isSupported() ? false : nativeProvider.hasCustomDecoration(window);
      }
   }

   public static void setHasCustomDecoration(Window window, boolean hasCustomDecoration) {
      if (canUseJBRCustomDecorations) {
         JBRCustomDecorations.setHasCustomDecoration(window, hasCustomDecoration);
      } else if (isSupported()) {
         nativeProvider.setHasCustomDecoration(window, hasCustomDecoration);
      }
   }

   static void setTitleBarHeightAndHitTestSpots(Window window, int titleBarHeight, List<Rectangle> hitTestSpots, Rectangle appIconBounds, Rectangle minimizeButtonBounds, Rectangle maximizeButtonBounds, Rectangle closeButtonBounds) {
      if (canUseJBRCustomDecorations) {
         JBRCustomDecorations.setTitleBarHeightAndHitTestSpots(window, titleBarHeight, hitTestSpots);
      } else if (isSupported()) {
         nativeProvider.updateTitleBarInfo(window, titleBarHeight, hitTestSpots, appIconBounds, minimizeButtonBounds, maximizeButtonBounds, closeButtonBounds);
      }
   }

   static boolean showWindow(Window window, int cmd) {
      return !canUseJBRCustomDecorations && isSupported() ? nativeProvider.showWindow(window, cmd) : false;
   }

   private static void initialize() {
      if (supported == null) {
         supported = false;
         if (canUseWindowDecorations) {
            try {
               setNativeProvider(FlatWindowsNativeWindowBorder.getInstance());
            } catch (Exception var1) {
            }

         }
      }
   }

   public static void setNativeProvider(FlatNativeWindowBorder.Provider provider) {
      if (nativeProvider != null) {
         throw new IllegalStateException();
      } else {
         nativeProvider = provider;
         supported = nativeProvider != null;
      }
   }

   static {
      canUseWindowDecorations = SystemInfo.isWindows_10_orLater && (SystemInfo.isWindows_11_orLater || !FlatSystemProperties.getBoolean("sun.java2d.opengl", false)) && !SystemInfo.isProjector && !SystemInfo.isWebswing && !SystemInfo.isWinPE && FlatSystemProperties.getBoolean("flatlaf.useWindowDecorations", true);
      canUseJBRCustomDecorations = canUseWindowDecorations && SystemInfo.isJetBrainsJVM_11_orLater && FlatSystemProperties.getBoolean("flatlaf.useJetBrainsCustomDecorations", false);
   }

   static class WindowTopBorder extends JBRCustomDecorations.JBRWindowTopBorder {
      private static FlatNativeWindowBorder.WindowTopBorder instance;

      static JBRCustomDecorations.JBRWindowTopBorder getInstance() {
         if (FlatNativeWindowBorder.canUseJBRCustomDecorations) {
            return JBRCustomDecorations.JBRWindowTopBorder.getInstance();
         } else {
            if (instance == null) {
               instance = new FlatNativeWindowBorder.WindowTopBorder();
            }

            return instance;
         }
      }

      void installListeners() {
         FlatNativeWindowBorder.nativeProvider.addChangeListener((e) -> {
            this.update();
            Window[] var2 = Window.getWindows();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
               Window window = var2[var4];
               if (window.isDisplayable()) {
                  window.repaint(0, 0, window.getWidth(), 1);
               }
            }

         });
      }

      boolean isColorizationColorAffectsBorders() {
         return FlatNativeWindowBorder.nativeProvider.isColorizationColorAffectsBorders();
      }

      Color getColorizationColor() {
         return FlatNativeWindowBorder.nativeProvider.getColorizationColor();
      }

      int getColorizationColorBalance() {
         return FlatNativeWindowBorder.nativeProvider.getColorizationColorBalance();
      }
   }

   public interface Provider {
      int SW_MAXIMIZE = 3;
      int SW_MINIMIZE = 6;
      int SW_RESTORE = 9;

      boolean hasCustomDecoration(Window var1);

      void setHasCustomDecoration(Window var1, boolean var2);

      void updateTitleBarInfo(Window var1, int var2, List<Rectangle> var3, Rectangle var4, Rectangle var5, Rectangle var6, Rectangle var7);

      boolean showWindow(Window var1, int var2);

      boolean isColorizationColorAffectsBorders();

      Color getColorizationColor();

      int getColorizationColorBalance();

      void addChangeListener(ChangeListener var1);

      void removeChangeListener(ChangeListener var1);
   }
}
