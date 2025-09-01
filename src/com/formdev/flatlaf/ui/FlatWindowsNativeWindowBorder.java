package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.geom.AffineTransform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

class FlatWindowsNativeWindowBorder implements FlatNativeWindowBorder.Provider {
   private final Map<Window, FlatWindowsNativeWindowBorder.WndProc> windowsMap = Collections.synchronizedMap(new IdentityHashMap());
   private final EventListenerList listenerList = new EventListenerList();
   private Timer fireStateChangedTimer;
   private boolean colorizationUpToDate;
   private boolean colorizationColorAffectsBorders;
   private Color colorizationColor;
   private int colorizationColorBalance;
   private static FlatWindowsNativeWindowBorder instance;

   static FlatNativeWindowBorder.Provider getInstance() {
      if (!SystemInfo.isWindows_10_orLater) {
         return null;
      } else if (!SystemInfo.isX86 && !SystemInfo.isX86_64) {
         return null;
      } else if (!FlatNativeLibrary.isLoaded()) {
         return null;
      } else {
         if (instance == null) {
            instance = new FlatWindowsNativeWindowBorder();
         }

         return instance;
      }
   }

   private FlatWindowsNativeWindowBorder() {
   }

   public boolean hasCustomDecoration(Window window) {
      return this.windowsMap.containsKey(window);
   }

   public void setHasCustomDecoration(Window window, boolean hasCustomDecoration) {
      if (hasCustomDecoration) {
         this.install(window);
      } else {
         this.uninstall(window);
      }

   }

   private void install(Window window) {
      if (SystemInfo.isWindows_10_orLater) {
         if (window instanceof JFrame || window instanceof JDialog) {
            if ((!(window instanceof Frame) || !((Frame)window).isUndecorated()) && (!(window instanceof Dialog) || !((Dialog)window).isUndecorated())) {
               if (!this.windowsMap.containsKey(window)) {
                  try {
                     FlatWindowsNativeWindowBorder.WndProc wndProc = new FlatWindowsNativeWindowBorder.WndProc(window);
                     if (wndProc.hwnd == 0L) {
                        return;
                     }

                     this.windowsMap.put(window, wndProc);
                  } catch (UnsatisfiedLinkError var3) {
                     LoggingFacade.INSTANCE.logSevere((String)null, var3);
                  }

               }
            }
         }
      }
   }

   private void uninstall(Window window) {
      FlatWindowsNativeWindowBorder.WndProc wndProc = (FlatWindowsNativeWindowBorder.WndProc)this.windowsMap.remove(window);
      if (wndProc != null) {
         wndProc.uninstall();
      }

   }

   public void updateTitleBarInfo(Window window, int titleBarHeight, List<Rectangle> hitTestSpots, Rectangle appIconBounds, Rectangle minimizeButtonBounds, Rectangle maximizeButtonBounds, Rectangle closeButtonBounds) {
      FlatWindowsNativeWindowBorder.WndProc wndProc = (FlatWindowsNativeWindowBorder.WndProc)this.windowsMap.get(window);
      if (wndProc != null) {
         wndProc.titleBarHeight = titleBarHeight;
         wndProc.hitTestSpots = (Rectangle[])hitTestSpots.toArray(new Rectangle[hitTestSpots.size()]);
         wndProc.appIconBounds = cloneRectange(appIconBounds);
         wndProc.minimizeButtonBounds = cloneRectange(minimizeButtonBounds);
         wndProc.maximizeButtonBounds = cloneRectange(maximizeButtonBounds);
         wndProc.closeButtonBounds = cloneRectange(closeButtonBounds);
      }
   }

   private static Rectangle cloneRectange(Rectangle rect) {
      return rect != null ? new Rectangle(rect) : null;
   }

   public boolean showWindow(Window window, int cmd) {
      FlatWindowsNativeWindowBorder.WndProc wndProc = (FlatWindowsNativeWindowBorder.WndProc)this.windowsMap.get(window);
      if (wndProc == null) {
         return false;
      } else {
         wndProc.showWindow(wndProc.hwnd, cmd);
         return true;
      }
   }

   public boolean isColorizationColorAffectsBorders() {
      this.updateColorization();
      return this.colorizationColorAffectsBorders;
   }

   public Color getColorizationColor() {
      this.updateColorization();
      return this.colorizationColor;
   }

   public int getColorizationColorBalance() {
      this.updateColorization();
      return this.colorizationColorBalance;
   }

   private void updateColorization() {
      if (!this.colorizationUpToDate) {
         this.colorizationUpToDate = true;
         String subKey = "SOFTWARE\\Microsoft\\Windows\\DWM";
         int value = registryGetIntValue(subKey, "ColorPrevalence", -1);
         this.colorizationColorAffectsBorders = value > 0;
         value = registryGetIntValue(subKey, "ColorizationColor", -1);
         this.colorizationColor = value != -1 ? new Color(value) : null;
         this.colorizationColorBalance = registryGetIntValue(subKey, "ColorizationColorBalance", -1);
      }
   }

   private static native int registryGetIntValue(String var0, String var1, int var2);

   public void addChangeListener(ChangeListener l) {
      this.listenerList.add(ChangeListener.class, l);
   }

   public void removeChangeListener(ChangeListener l) {
      this.listenerList.remove(ChangeListener.class, l);
   }

   private void fireStateChanged() {
      Object[] listeners = this.listenerList.getListenerList();
      if (listeners.length != 0) {
         ChangeEvent e = new ChangeEvent(this);

         for(int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == ChangeListener.class) {
               ((ChangeListener)listeners[i + 1]).stateChanged(e);
            }
         }

      }
   }

   void fireStateChangedLaterOnce() {
      EventQueue.invokeLater(() -> {
         if (this.fireStateChangedTimer != null) {
            this.fireStateChangedTimer.restart();
         } else {
            this.fireStateChangedTimer = new Timer(300, (e) -> {
               this.fireStateChangedTimer = null;
               this.colorizationUpToDate = false;
               this.fireStateChanged();
            });
            this.fireStateChangedTimer.setRepeats(false);
            this.fireStateChangedTimer.start();
         }
      });
   }

   private class WndProc implements PropertyChangeListener {
      private static final int HTCLIENT = 1;
      private static final int HTCAPTION = 2;
      private static final int HTSYSMENU = 3;
      private static final int HTMINBUTTON = 8;
      private static final int HTMAXBUTTON = 9;
      private static final int HTTOP = 12;
      private static final int HTCLOSE = 20;
      private Window window;
      private final long hwnd;
      private int titleBarHeight;
      private Rectangle[] hitTestSpots;
      private Rectangle appIconBounds;
      private Rectangle minimizeButtonBounds;
      private Rectangle maximizeButtonBounds;
      private Rectangle closeButtonBounds;

      WndProc(Window window) {
         this.window = window;
         this.hwnd = this.installImpl(window);
         if (this.hwnd != 0L) {
            this.updateFrame(this.hwnd, window instanceof JFrame ? ((JFrame)window).getExtendedState() : 0);
            this.updateWindowBackground();
            window.addPropertyChangeListener("background", this);
         }
      }

      void uninstall() {
         this.window.removePropertyChangeListener("background", this);
         this.uninstallImpl(this.hwnd);
         this.window = null;
      }

      public void propertyChange(PropertyChangeEvent e) {
         this.updateWindowBackground();
      }

      private void updateWindowBackground() {
         Color bg = this.window.getBackground();
         if (bg != null) {
            this.setWindowBackground(this.hwnd, bg.getRed(), bg.getGreen(), bg.getBlue());
         }

      }

      private native long installImpl(Window var1);

      private native void uninstallImpl(long var1);

      private native void updateFrame(long var1, int var3);

      private native void setWindowBackground(long var1, int var3, int var4, int var5);

      private native void showWindow(long var1, int var3);

      private int onNcHitTest(int x, int y, boolean isOnResizeBorder) {
         Point pt = this.scaleDown(x, y);
         int sx = pt.x;
         int sy = pt.y;
         if (this.contains(this.appIconBounds, sx, sy)) {
            return 3;
         } else if (this.contains(this.minimizeButtonBounds, sx, sy)) {
            return 8;
         } else if (this.contains(this.maximizeButtonBounds, sx, sy)) {
            return 9;
         } else if (this.contains(this.closeButtonBounds, sx, sy)) {
            return 20;
         } else {
            boolean isOnTitleBar = sy < this.titleBarHeight;
            if (isOnTitleBar) {
               Rectangle[] hitTestSpots2 = this.hitTestSpots;
               Rectangle[] var9 = hitTestSpots2;
               int var10 = hitTestSpots2.length;

               for(int var11 = 0; var11 < var10; ++var11) {
                  Rectangle spot = var9[var11];
                  if (spot.contains(sx, sy)) {
                     return 1;
                  }
               }

               return isOnResizeBorder ? 12 : 2;
            } else {
               return isOnResizeBorder ? 12 : 1;
            }
         }
      }

      private boolean contains(Rectangle rect, int x, int y) {
         return rect != null && rect.contains(x, y);
      }

      private Point scaleDown(int x, int y) {
         GraphicsConfiguration gc = this.window.getGraphicsConfiguration();
         if (gc == null) {
            return new Point(x, y);
         } else {
            AffineTransform t = gc.getDefaultTransform();
            return new Point(this.clipRound((double)x / t.getScaleX()), this.clipRound((double)y / t.getScaleY()));
         }
      }

      private int clipRound(double value) {
         value -= 0.5D;
         if (value < -2.147483648E9D) {
            return Integer.MIN_VALUE;
         } else {
            return value > 2.147483647E9D ? Integer.MAX_VALUE : (int)Math.ceil(value);
         }
      }

      private boolean isFullscreen() {
         GraphicsConfiguration gc = this.window.getGraphicsConfiguration();
         if (gc == null) {
            return false;
         } else {
            return gc.getDevice().getFullScreenWindow() == this.window;
         }
      }

      private void fireStateChangedLaterOnce() {
         FlatWindowsNativeWindowBorder.this.fireStateChangedLaterOnce();
      }
   }
}
