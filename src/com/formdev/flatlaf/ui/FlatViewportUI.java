package com.formdev.flatlaf.ui;

import java.awt.Component;
import java.awt.Graphics;
import java.lang.reflect.Method;
import javax.swing.JComponent;
import javax.swing.JViewport;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicViewportUI;

public class FlatViewportUI extends BasicViewportUI {
   public static ComponentUI createUI(JComponent c) {
      return FlatUIUtils.createSharedUI(FlatViewportUI.class, FlatViewportUI::new);
   }

   public void paint(Graphics g, JComponent c) {
      super.paint(g, c);
      Component view = ((JViewport)c).getView();
      if (view instanceof JComponent) {
         try {
            Method m = view.getClass().getMethod("getUI");
            Object ui = m.invoke(view);
            if (ui instanceof FlatViewportUI.ViewportPainter) {
               ((FlatViewportUI.ViewportPainter)ui).paintViewport(g, (JComponent)view, (JViewport)c);
            }
         } catch (Exception var6) {
         }
      }

   }

   public interface ViewportPainter {
      void paintViewport(Graphics var1, JComponent var2, JViewport var3);
   }
}
