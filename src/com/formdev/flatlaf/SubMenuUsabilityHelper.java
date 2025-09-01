package com.formdev.flatlaf;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class SubMenuUsabilityHelper implements ChangeListener {
   private static final String KEY_USE_SAFE_TRIANGLE = "Menu.useSafeTriangle";
   private static final String KEY_SHOW_SAFE_TRIANGLE = "FlatLaf.debug.menu.showSafeTriangle";
   private static SubMenuUsabilityHelper instance;
   private SubMenuUsabilityHelper.SubMenuEventQueue subMenuEventQueue;
   private SubMenuUsabilityHelper.SafeTrianglePainter safeTrianglePainter;
   private boolean changePending;
   private int mouseX;
   private int mouseY;
   private int targetX;
   private int targetTopY;
   private int targetBottomY;
   private Rectangle invokerBounds;

   static synchronized boolean install() {
      if (instance != null) {
         return false;
      } else {
         instance = new SubMenuUsabilityHelper();
         MenuSelectionManager.defaultManager().addChangeListener(instance);
         return true;
      }
   }

   static synchronized void uninstall() {
      if (instance != null) {
         MenuSelectionManager.defaultManager().removeChangeListener(instance);
         instance.uninstallEventQueue();
         instance = null;
      }
   }

   public void stateChanged(ChangeEvent e) {
      if (FlatUIUtils.getUIBoolean("Menu.useSafeTriangle", true)) {
         synchronized(this) {
            if (this.changePending) {
               return;
            }

            this.changePending = true;
         }

         EventQueue.invokeLater(() -> {
            synchronized(this) {
               this.changePending = false;
            }

            this.menuSelectionChanged();
         });
      }
   }

   private void menuSelectionChanged() {
      MenuElement[] path = MenuSelectionManager.defaultManager().getSelectedPath();
      int subMenuIndex = this.findSubMenu(path);
      if (subMenuIndex >= 0 && subMenuIndex == path.length - 1) {
         PointerInfo pointerInfo = MouseInfo.getPointerInfo();
         Point mouseLocation = pointerInfo != null ? pointerInfo.getLocation() : new Point();
         this.mouseX = mouseLocation.x;
         this.mouseY = mouseLocation.y;
         JPopupMenu popup = (JPopupMenu)path[subMenuIndex];
         if (!popup.isShowing()) {
            this.uninstallEventQueue();
         } else {
            Component invoker = popup.getInvoker();
            this.invokerBounds = invoker != null ? new Rectangle(invoker.getLocationOnScreen(), invoker.getSize()) : null;
            if (this.invokerBounds != null && !this.invokerBounds.contains(this.mouseX, this.mouseY)) {
               this.uninstallEventQueue();
            } else {
               Point popupLocation = popup.getLocationOnScreen();
               Dimension popupSize = popup.getSize();
               this.targetX = this.mouseX < popupLocation.x + popupSize.width / 2 ? popupLocation.x : popupLocation.x + popupSize.width;
               this.targetTopY = popupLocation.y;
               this.targetBottomY = popupLocation.y + popupSize.height;
               if (this.subMenuEventQueue == null) {
                  this.subMenuEventQueue = new SubMenuUsabilityHelper.SubMenuEventQueue();
               }

               if (this.safeTrianglePainter == null && UIManager.getBoolean("FlatLaf.debug.menu.showSafeTriangle")) {
                  this.safeTrianglePainter = new SubMenuUsabilityHelper.SafeTrianglePainter(popup);
               }

            }
         }
      } else {
         this.uninstallEventQueue();
      }
   }

   private void uninstallEventQueue() {
      if (this.subMenuEventQueue != null) {
         this.subMenuEventQueue.uninstall();
         this.subMenuEventQueue = null;
      }

      if (this.safeTrianglePainter != null) {
         this.safeTrianglePainter.uninstall();
         this.safeTrianglePainter = null;
      }

   }

   private int findSubMenu(MenuElement[] path) {
      for(int i = path.length - 1; i >= 1; --i) {
         if (path[i] instanceof JPopupMenu && path[i - 1] instanceof JMenu && !((JMenu)path[i - 1]).isTopLevelMenu()) {
            return i;
         }
      }

      return -1;
   }

   private Polygon createSafeTriangle() {
      return new Polygon(new int[]{this.mouseX, this.targetX, this.targetX}, new int[]{this.mouseY, this.targetTopY, this.targetBottomY}, 3);
   }

   private class SafeTrianglePainter extends JComponent {
      SafeTrianglePainter(JPopupMenu popup) {
         Window window = SwingUtilities.windowForComponent(popup.getInvoker());
         if (window instanceof RootPaneContainer) {
            JLayeredPane layeredPane = ((RootPaneContainer)window).getLayeredPane();
            this.setSize(layeredPane.getSize());
            layeredPane.add(this, JLayeredPane.POPUP_LAYER + 1);
         }

      }

      void uninstall() {
         Container parent = this.getParent();
         if (parent != null) {
            parent.remove(this);
            parent.repaint();
         }

      }

      protected void paintComponent(Graphics g) {
         Point locationOnScreen = this.getLocationOnScreen();
         g.translate(-locationOnScreen.x, -locationOnScreen.y);
         g.setColor(Color.red);
         ((Graphics2D)g).draw(SubMenuUsabilityHelper.this.createSafeTriangle());
      }
   }

   private class SubMenuEventQueue extends EventQueue {
      private Timer mouseUpdateTimer = new Timer(50, (e) -> {
         SubMenuUsabilityHelper.this.mouseX = this.newMouseX;
         SubMenuUsabilityHelper.this.mouseY = this.newMouseY;
         if (SubMenuUsabilityHelper.this.safeTrianglePainter != null) {
            SubMenuUsabilityHelper.this.safeTrianglePainter.repaint();
         }

      });
      private Timer timeoutTimer;
      private int newMouseX;
      private int newMouseY;
      private AWTEvent lastMouseEvent;

      SubMenuEventQueue() {
         this.mouseUpdateTimer.setRepeats(false);
         this.timeoutTimer = new Timer(200, (e) -> {
            if (SubMenuUsabilityHelper.this.invokerBounds != null && !SubMenuUsabilityHelper.this.invokerBounds.contains(this.newMouseX, this.newMouseY)) {
               if (this.lastMouseEvent != null) {
                  this.postEvent(this.lastMouseEvent);
                  this.lastMouseEvent = null;
               }

               SubMenuUsabilityHelper.this.uninstallEventQueue();
            }
         });
         this.timeoutTimer.setRepeats(false);
         Toolkit.getDefaultToolkit().getSystemEventQueue().push(this);
      }

      void uninstall() {
         this.mouseUpdateTimer.stop();
         this.mouseUpdateTimer = null;
         this.timeoutTimer.stop();
         this.timeoutTimer = null;
         this.lastMouseEvent = null;
         super.pop();
      }

      protected void dispatchEvent(AWTEvent e) {
         int id = e.getID();
         if (e instanceof MouseEvent && (id == 503 || id == 506)) {
            this.newMouseX = ((MouseEvent)e).getXOnScreen();
            this.newMouseY = ((MouseEvent)e).getYOnScreen();
            if (SubMenuUsabilityHelper.this.safeTrianglePainter != null) {
               SubMenuUsabilityHelper.this.safeTrianglePainter.repaint();
            }

            this.mouseUpdateTimer.stop();
            this.timeoutTimer.stop();
            if (SubMenuUsabilityHelper.this.createSafeTriangle().contains(this.newMouseX, this.newMouseY)) {
               this.mouseUpdateTimer.start();
               this.timeoutTimer.start();
               this.lastMouseEvent = e;
               return;
            }

            SubMenuUsabilityHelper.this.mouseX = this.newMouseX;
            SubMenuUsabilityHelper.this.mouseY = this.newMouseY;
         }

         super.dispatchEvent(e);
      }
   }
}
