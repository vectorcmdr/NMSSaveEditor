package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.MultiResolutionImageSupport;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JRootPane;
import javax.swing.JToolTip;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicDesktopIconUI;

public class FlatDesktopIconUI extends BasicDesktopIconUI {
   private Dimension iconSize;
   private Dimension closeSize;
   private JLabel dockIcon;
   private JButton closeButton;
   private JToolTip titleTip;
   private ActionListener closeListener;
   private MouseInputListener mouseInputListener;
   private PropertyChangeListener ancestorListener;

   public static ComponentUI createUI(JComponent c) {
      return new FlatDesktopIconUI();
   }

   public void installUI(JComponent c) {
      super.installUI(c);
      if (c.isDisplayable()) {
         this.updateDockIconPreviewLater();
      }

   }

   public void uninstallUI(JComponent c) {
      super.uninstallUI(c);
      this.dockIcon = null;
      this.closeButton = null;
   }

   protected void installComponents() {
      this.dockIcon = new JLabel();
      this.dockIcon.setHorizontalAlignment(0);
      this.closeButton = new JButton();
      this.closeButton.setIcon(UIManager.getIcon("DesktopIcon.closeIcon"));
      this.closeButton.setFocusable(false);
      this.closeButton.setBorder(BorderFactory.createEmptyBorder());
      this.closeButton.setOpaque(true);
      this.closeButton.setBackground(FlatUIUtils.nonUIResource(this.desktopIcon.getBackground()));
      this.closeButton.setForeground(FlatUIUtils.nonUIResource(this.desktopIcon.getForeground()));
      this.closeButton.setVisible(false);
      this.desktopIcon.setLayout(new FlatDesktopIconUI.FlatDesktopIconLayout());
      this.desktopIcon.add(this.closeButton);
      this.desktopIcon.add(this.dockIcon);
   }

   protected void uninstallComponents() {
      this.hideTitleTip();
      this.desktopIcon.remove(this.dockIcon);
      this.desktopIcon.remove(this.closeButton);
      this.desktopIcon.setLayout((LayoutManager)null);
   }

   protected void installDefaults() {
      super.installDefaults();
      LookAndFeel.installColors(this.desktopIcon, "DesktopIcon.background", "DesktopIcon.foreground");
      this.iconSize = UIManager.getDimension("DesktopIcon.iconSize");
      this.closeSize = UIManager.getDimension("DesktopIcon.closeSize");
   }

   protected void installListeners() {
      super.installListeners();
      this.closeListener = (e) -> {
         if (this.frame.isClosable()) {
            this.frame.doDefaultCloseAction();
         }

      };
      this.closeButton.addActionListener(this.closeListener);
      this.closeButton.addMouseListener(this.mouseInputListener);
      this.ancestorListener = (e) -> {
         if (e.getNewValue() != null) {
            this.updateDockIconPreviewLater();
         } else {
            this.dockIcon.setIcon((Icon)null);
         }

      };
      this.desktopIcon.addPropertyChangeListener("ancestor", this.ancestorListener);
   }

   protected void uninstallListeners() {
      super.uninstallListeners();
      this.closeButton.removeActionListener(this.closeListener);
      this.closeButton.removeMouseListener(this.mouseInputListener);
      this.closeListener = null;
      this.mouseInputListener = null;
      this.desktopIcon.removePropertyChangeListener("ancestor", this.ancestorListener);
      this.ancestorListener = null;
   }

   protected MouseInputListener createMouseInputListener() {
      this.mouseInputListener = new MouseInputAdapter() {
         public void mouseReleased(MouseEvent e) {
            if (FlatDesktopIconUI.this.frame.isIcon() && FlatDesktopIconUI.this.desktopIcon.contains(e.getX(), e.getY())) {
               FlatDesktopIconUI.this.hideTitleTip();
               FlatDesktopIconUI.this.closeButton.setVisible(false);

               try {
                  FlatDesktopIconUI.this.frame.setIcon(false);
               } catch (PropertyVetoException var3) {
               }
            }

         }

         public void mouseEntered(MouseEvent e) {
            FlatDesktopIconUI.this.showTitleTip();
            if (FlatDesktopIconUI.this.frame.isClosable()) {
               FlatDesktopIconUI.this.closeButton.setVisible(true);
            }

         }

         public void mouseExited(MouseEvent e) {
            FlatDesktopIconUI.this.hideTitleTip();
            FlatDesktopIconUI.this.closeButton.setVisible(false);
         }
      };
      return this.mouseInputListener;
   }

   private void showTitleTip() {
      JRootPane rootPane = SwingUtilities.getRootPane(this.desktopIcon);
      if (rootPane != null) {
         if (this.titleTip == null) {
            this.titleTip = new JToolTip();
            rootPane.getLayeredPane().add(this.titleTip, JLayeredPane.POPUP_LAYER);
         }

         this.titleTip.setTipText(this.frame.getTitle());
         this.titleTip.setSize(this.titleTip.getPreferredSize());
         int tx = (this.desktopIcon.getWidth() - this.titleTip.getWidth()) / 2;
         int ty = -(this.titleTip.getHeight() + UIScale.scale(4));
         Point pt = SwingUtilities.convertPoint(this.desktopIcon, tx, ty, this.titleTip.getParent());
         if (pt.x + this.titleTip.getWidth() > rootPane.getWidth()) {
            pt.x = rootPane.getWidth() - this.titleTip.getWidth();
         }

         if (pt.x < 0) {
            pt.x = 0;
         }

         this.titleTip.setLocation(pt);
         this.titleTip.repaint();
      }
   }

   private void hideTitleTip() {
      if (this.titleTip != null) {
         this.titleTip.setVisible(false);
         this.titleTip.getParent().remove(this.titleTip);
         this.titleTip = null;
      }
   }

   public Dimension getPreferredSize(JComponent c) {
      return UIScale.scale(this.iconSize);
   }

   public Dimension getMinimumSize(JComponent c) {
      return this.getPreferredSize(c);
   }

   public Dimension getMaximumSize(JComponent c) {
      return this.getPreferredSize(c);
   }

   public void update(Graphics g, JComponent c) {
      if (c.isOpaque()) {
         Color background = c.getBackground();
         JDesktopPane desktopPane = this.desktopIcon.getDesktopPane();
         g.setColor(desktopPane != null ? FlatUIUtils.deriveColor(background, desktopPane.getBackground()) : background);
         g.fillRect(0, 0, c.getWidth(), c.getHeight());
      }

      this.paint(g, c);
   }

   private void updateDockIconPreviewLater() {
      EventQueue.invokeLater(() -> {
         if (this.dockIcon != null) {
            this.updateDockIconPreview();
         }

      });
   }

   protected void updateDockIconPreview() {
      if (this.frame.isSelected()) {
         try {
            this.frame.setSelected(false);
         } catch (PropertyVetoException var17) {
         }
      }

      if (!this.frame.isValid()) {
         this.frame.doLayout();
      }

      Component[] var1 = this.frame.getComponents();
      int frameHeight = var1.length;

      for(int var3 = 0; var3 < frameHeight; ++var3) {
         Component c = var1[var3];
         if (!c.isValid()) {
            c.doLayout();
         }
      }

      int frameWidth = Math.max(this.frame.getWidth(), 1);
      frameHeight = Math.max(this.frame.getHeight(), 1);
      BufferedImage frameImage = new BufferedImage(frameWidth, frameHeight, 2);
      Graphics2D g = frameImage.createGraphics();

      try {
         this.frame.paint(g);
      } finally {
         g.dispose();
      }

      Insets insets = this.desktopIcon.getInsets();
      int previewWidth = UIScale.scale(this.iconSize.width) - insets.left - insets.right;
      int previewHeight = UIScale.scale(this.iconSize.height) - insets.top - insets.bottom;
      float frameRatio = (float)frameHeight / (float)frameWidth;
      if ((float)previewWidth / (float)frameWidth > (float)previewHeight / (float)frameHeight) {
         previewWidth = Math.round((float)previewHeight / frameRatio);
      } else {
         previewHeight = Math.round((float)previewWidth * frameRatio);
      }

      Image previewImage = frameImage.getScaledInstance(previewWidth, previewHeight, 4);
      if (MultiResolutionImageSupport.isAvailable()) {
         Image previewImage2x = frameImage.getScaledInstance(previewWidth * 2, previewHeight * 2, 4);
         double scaleFactor = UIScale.getSystemScaleFactor(this.desktopIcon.getGraphicsConfiguration());
         if (scaleFactor != 1.0D && scaleFactor != 2.0D) {
            Image previewImageCurrent = frameImage.getScaledInstance((int)Math.round((double)previewWidth * scaleFactor), (int)Math.round((double)previewHeight * scaleFactor), 4);
            previewImage = scaleFactor < 2.0D ? MultiResolutionImageSupport.create(0, previewImage, previewImageCurrent, previewImage2x) : MultiResolutionImageSupport.create(0, previewImage, previewImage2x, previewImageCurrent);
         } else {
            previewImage = MultiResolutionImageSupport.create(0, previewImage, previewImage2x);
         }
      }

      this.dockIcon.setIcon(new ImageIcon(previewImage));
   }

   private class FlatDesktopIconLayout implements LayoutManager {
      private FlatDesktopIconLayout() {
      }

      public void addLayoutComponent(String name, Component comp) {
      }

      public void removeLayoutComponent(Component comp) {
      }

      public Dimension preferredLayoutSize(Container parent) {
         return FlatDesktopIconUI.this.dockIcon.getPreferredSize();
      }

      public Dimension minimumLayoutSize(Container parent) {
         return FlatDesktopIconUI.this.dockIcon.getMinimumSize();
      }

      public void layoutContainer(Container parent) {
         Insets insets = parent.getInsets();
         FlatDesktopIconUI.this.dockIcon.setBounds(insets.left, insets.top, parent.getWidth() - insets.left - insets.right, parent.getHeight() - insets.top - insets.bottom);
         Dimension cSize = UIScale.scale(FlatDesktopIconUI.this.closeSize);
         FlatDesktopIconUI.this.closeButton.setBounds(parent.getWidth() - cSize.width, 0, cSize.width, cSize.height);
      }

      // $FF: synthetic method
      FlatDesktopIconLayout(Object x1) {
         this();
      }
   }
}
