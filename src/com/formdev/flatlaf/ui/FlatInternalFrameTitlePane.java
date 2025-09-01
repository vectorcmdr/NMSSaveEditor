package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.ScaledImageIcon;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.PropertyChangeHandler;

public class FlatInternalFrameTitlePane extends BasicInternalFrameTitlePane {
   private JLabel titleLabel;
   private JPanel buttonPanel;

   public FlatInternalFrameTitlePane(JInternalFrame f) {
      super(f);
   }

   protected void installDefaults() {
      super.installDefaults();
      LookAndFeel.installBorder(this, "InternalFrameTitlePane.border");
   }

   protected PropertyChangeListener createPropertyChangeListener() {
      return new FlatInternalFrameTitlePane.FlatPropertyChangeHandler();
   }

   protected LayoutManager createLayout() {
      return new BorderLayout(UIScale.scale(4), 0);
   }

   protected void createButtons() {
      super.createButtons();
      this.iconButton.setContentAreaFilled(false);
      this.maxButton.setContentAreaFilled(false);
      this.closeButton.setContentAreaFilled(false);
      Border emptyBorder = BorderFactory.createEmptyBorder();
      this.iconButton.setBorder(emptyBorder);
      this.maxButton.setBorder(emptyBorder);
      this.closeButton.setBorder(emptyBorder);
      this.updateButtonsVisibility();
   }

   protected void addSubComponents() {
      this.titleLabel = new JLabel(this.frame.getTitle());
      this.titleLabel.setFont(FlatUIUtils.nonUIResource(this.getFont()));
      this.titleLabel.setMinimumSize(new Dimension(UIScale.scale(32), 1));
      this.updateFrameIcon();
      this.updateColors();
      this.buttonPanel = new JPanel() {
         public Dimension getPreferredSize() {
            Dimension size = super.getPreferredSize();
            int height = size.height;
            if (!FlatInternalFrameTitlePane.this.iconButton.isVisible()) {
               height = Math.max(height, FlatInternalFrameTitlePane.this.iconButton.getPreferredSize().height);
            }

            if (!FlatInternalFrameTitlePane.this.maxButton.isVisible()) {
               height = Math.max(height, FlatInternalFrameTitlePane.this.maxButton.getPreferredSize().height);
            }

            if (!FlatInternalFrameTitlePane.this.closeButton.isVisible()) {
               height = Math.max(height, FlatInternalFrameTitlePane.this.closeButton.getPreferredSize().height);
            }

            return new Dimension(size.width, height);
         }
      };
      this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, 2));
      this.buttonPanel.setOpaque(false);
      this.buttonPanel.add(this.iconButton);
      this.buttonPanel.add(this.maxButton);
      this.buttonPanel.add(this.closeButton);
      this.add(this.titleLabel, "Center");
      this.add(this.buttonPanel, "After");
   }

   protected void updateFrameIcon() {
      Icon frameIcon = this.frame.getFrameIcon();
      if (frameIcon == null || ((Icon)frameIcon).getIconWidth() != 0 && ((Icon)frameIcon).getIconHeight() != 0) {
         if (frameIcon instanceof ImageIcon) {
            frameIcon = new ScaledImageIcon((ImageIcon)frameIcon);
         }
      } else {
         frameIcon = null;
      }

      this.titleLabel.setIcon((Icon)frameIcon);
   }

   protected void updateColors() {
      Color background = FlatUIUtils.nonUIResource(this.frame.isSelected() ? this.selectedTitleColor : this.notSelectedTitleColor);
      Color foreground = FlatUIUtils.nonUIResource(this.frame.isSelected() ? this.selectedTextColor : this.notSelectedTextColor);
      this.titleLabel.setForeground(foreground);
      this.iconButton.setBackground(background);
      this.iconButton.setForeground(foreground);
      this.maxButton.setBackground(background);
      this.maxButton.setForeground(foreground);
      this.closeButton.setBackground(background);
      this.closeButton.setForeground(foreground);
   }

   protected void updateButtonsVisibility() {
      this.iconButton.setVisible(this.frame.isIconifiable());
      this.maxButton.setVisible(this.frame.isMaximizable());
      this.closeButton.setVisible(this.frame.isClosable());
   }

   Rectangle getFrameIconBounds() {
      Icon icon = this.titleLabel.getIcon();
      if (icon == null) {
         return null;
      } else {
         int iconWidth = icon.getIconWidth();
         int iconHeight = icon.getIconHeight();
         boolean leftToRight = this.titleLabel.getComponentOrientation().isLeftToRight();
         int x = this.titleLabel.getX() + (leftToRight ? 0 : this.titleLabel.getWidth() - iconWidth);
         int y = this.titleLabel.getY() + (this.titleLabel.getHeight() - iconHeight) / 2;
         return new Rectangle(x, y, iconWidth, iconHeight);
      }
   }

   protected void assembleSystemMenu() {
   }

   protected void showSystemMenu() {
   }

   public void paintComponent(Graphics g) {
      this.paintTitleBackground(g);
   }

   protected class FlatPropertyChangeHandler extends PropertyChangeHandler {
      protected FlatPropertyChangeHandler() {
         super(FlatInternalFrameTitlePane.this);
      }

      public void propertyChange(PropertyChangeEvent e) {
         String var2 = e.getPropertyName();
         byte var3 = -1;
         switch(var2.hashCode()) {
         case -1010695135:
            if (var2.equals("opaque")) {
               var3 = 7;
            }
            break;
         case -737546925:
            if (var2.equals("iconable")) {
               var3 = 3;
            }
            break;
         case 110371416:
            if (var2.equals("title")) {
               var3 = 0;
            }
            break;
         case 544791430:
            if (var2.equals("frameIcon")) {
               var3 = 1;
            }
            break;
         case 1092709095:
            if (var2.equals("closable")) {
               var3 = 5;
            }
            break;
         case 1191572123:
            if (var2.equals("selected")) {
               var3 = 2;
            }
            break;
         case 1247047827:
            if (var2.equals("componentOrientation")) {
               var3 = 6;
            }
            break;
         case 1354515859:
            if (var2.equals("maximizable")) {
               var3 = 4;
            }
         }

         switch(var3) {
         case 0:
            FlatInternalFrameTitlePane.this.titleLabel.setText(FlatInternalFrameTitlePane.this.frame.getTitle());
            break;
         case 1:
            FlatInternalFrameTitlePane.this.updateFrameIcon();
            break;
         case 2:
            FlatInternalFrameTitlePane.this.updateColors();
            break;
         case 3:
         case 4:
         case 5:
            FlatInternalFrameTitlePane.this.updateButtonsVisibility();
            FlatInternalFrameTitlePane.this.enableActions();
            FlatInternalFrameTitlePane.this.revalidate();
            FlatInternalFrameTitlePane.this.repaint();
            return;
         case 6:
            FlatInternalFrameTitlePane.this.applyComponentOrientation(FlatInternalFrameTitlePane.this.frame.getComponentOrientation());
            break;
         case 7:
            return;
         }

         super.propertyChange(e);
      }
   }
}
