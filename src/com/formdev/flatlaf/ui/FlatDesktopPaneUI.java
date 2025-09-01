package com.formdev.flatlaf.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import javax.swing.JComponent;
import javax.swing.JInternalFrame.JDesktopIcon;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicDesktopPaneUI;

public class FlatDesktopPaneUI extends BasicDesktopPaneUI {
   private FlatDesktopPaneUI.LayoutDockListener layoutDockListener;
   private boolean layoutDockPending;

   public static ComponentUI createUI(JComponent c) {
      return new FlatDesktopPaneUI();
   }

   public void installUI(JComponent c) {
      super.installUI(c);
      this.layoutDockLaterOnce();
   }

   protected void installListeners() {
      super.installListeners();
      this.layoutDockListener = new FlatDesktopPaneUI.LayoutDockListener();
      this.desktop.addContainerListener(this.layoutDockListener);
      this.desktop.addComponentListener(this.layoutDockListener);
   }

   protected void uninstallListeners() {
      super.uninstallListeners();
      this.desktop.removeContainerListener(this.layoutDockListener);
      this.desktop.removeComponentListener(this.layoutDockListener);
      this.layoutDockListener = null;
   }

   private void layoutDockLaterOnce() {
      if (!this.layoutDockPending) {
         this.layoutDockPending = true;
         EventQueue.invokeLater(() -> {
            this.layoutDockPending = false;
            if (this.desktop != null) {
               this.layoutDock();
            }

         });
      }
   }

   protected void layoutDock() {
      Dimension desktopSize = this.desktop.getSize();
      int x = 0;
      int y = desktopSize.height;
      int rowHeight = 0;
      Component[] var5 = this.desktop.getComponents();
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         Component c = var5[var7];
         if (c instanceof JDesktopIcon) {
            JDesktopIcon icon = (JDesktopIcon)c;
            Dimension iconSize = icon.getPreferredSize();
            if (x + iconSize.width > desktopSize.width) {
               x = 0;
               y -= rowHeight;
               rowHeight = 0;
            }

            icon.setLocation(x, y - iconSize.height);
            x += iconSize.width;
            rowHeight = Math.max(iconSize.height, rowHeight);
         }
      }

   }

   private class LayoutDockListener extends ComponentAdapter implements ContainerListener {
      private LayoutDockListener() {
      }

      public void componentAdded(ContainerEvent e) {
         FlatDesktopPaneUI.this.layoutDockLaterOnce();
      }

      public void componentRemoved(ContainerEvent e) {
         FlatDesktopPaneUI.this.layoutDockLaterOnce();
      }

      public void componentResized(ComponentEvent e) {
         FlatDesktopPaneUI.this.layoutDockLaterOnce();
      }

      // $FF: synthetic method
      LayoutDockListener(Object x1) {
         this();
      }
   }
}
