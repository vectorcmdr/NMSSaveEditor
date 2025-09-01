package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.SwingUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.beans.PropertyChangeListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonAreaLayout;

public class FlatOptionPaneUI extends BasicOptionPaneUI {
   protected boolean showIcon;
   protected int iconMessageGap;
   protected int messagePadding;
   protected int maxCharactersPerLine;
   private int focusWidth;
   private boolean sameSizeButtons;

   public static ComponentUI createUI(JComponent c) {
      return new FlatOptionPaneUI();
   }

   protected void installDefaults() {
      super.installDefaults();
      this.showIcon = UIManager.getBoolean("OptionPane.showIcon");
      this.iconMessageGap = UIManager.getInt("OptionPane.iconMessageGap");
      this.messagePadding = UIManager.getInt("OptionPane.messagePadding");
      this.maxCharactersPerLine = UIManager.getInt("OptionPane.maxCharactersPerLine");
      this.focusWidth = UIManager.getInt("Component.focusWidth");
      this.sameSizeButtons = FlatUIUtils.getUIBoolean("OptionPane.sameSizeButtons", true);
   }

   protected void installComponents() {
      super.installComponents();
      this.updateChildPanels(this.optionPane);
   }

   protected PropertyChangeListener createPropertyChangeListener() {
      PropertyChangeListener superListener = super.createPropertyChangeListener();
      return (e) -> {
         superListener.propertyChange(e);
         if (!this.showIcon && "ancestor".equals(e.getPropertyName()) && e.getNewValue() != null) {
            JRootPane rootPane = SwingUtilities.getRootPane(this.optionPane);
            if (rootPane != null && rootPane.getContentPane().getComponentCount() > 0 && rootPane.getContentPane().getComponent(0) == this.optionPane) {
               rootPane.putClientProperty("JRootPane.titleBarShowIcon", false);
            }
         }

      };
   }

   public Dimension getMinimumOptionPaneSize() {
      return UIScale.scale(super.getMinimumOptionPaneSize());
   }

   protected int getMaxCharactersPerLineCount() {
      int max = super.getMaxCharactersPerLineCount();
      return this.maxCharactersPerLine > 0 && max == Integer.MAX_VALUE ? this.maxCharactersPerLine : max;
   }

   protected Container createMessageArea() {
      Container messageArea = super.createMessageArea();
      if (this.iconMessageGap > 0) {
         Component iconMessageSeparator = SwingUtils.getComponentByName(messageArea, "OptionPane.separator");
         if (iconMessageSeparator != null) {
            iconMessageSeparator.setPreferredSize(new Dimension(UIScale.scale(this.iconMessageGap), 1));
         }
      }

      return messageArea;
   }

   protected Container createButtonArea() {
      Container buttonArea = super.createButtonArea();
      if (buttonArea.getLayout() instanceof ButtonAreaLayout) {
         ButtonAreaLayout layout = (ButtonAreaLayout)buttonArea.getLayout();
         layout.setPadding(UIScale.scale(layout.getPadding() - this.focusWidth * 2));
      }

      return buttonArea;
   }

   protected void addMessageComponents(Container container, GridBagConstraints cons, Object msg, int maxll, boolean internallyCreated) {
      if (this.messagePadding > 0) {
         cons.insets.bottom = UIScale.scale(this.messagePadding);
      }

      if (msg != null && !(msg instanceof Component) && !(msg instanceof Object[]) && !(msg instanceof Icon)) {
         msg = msg.toString();
         if (BasicHTML.isHTMLString((String)msg)) {
            maxll = Integer.MAX_VALUE;
         }
      }

      if (msg instanceof Box) {
         Box box = (Box)msg;
         if ("OptionPane.verticalBox".equals(box.getName()) && box.getLayout() instanceof BoxLayout && ((BoxLayout)box.getLayout()).getAxis() == 1) {
            box.addPropertyChangeListener("componentOrientation", (e) -> {
               float alignX = box.getComponentOrientation().isLeftToRight() ? 0.0F : 1.0F;
               Component[] var3 = box.getComponents();
               int var4 = var3.length;

               for(int var5 = 0; var5 < var4; ++var5) {
                  Component c = var3[var5];
                  if (c instanceof JLabel && "OptionPane.label".equals(c.getName())) {
                     ((JLabel)c).setAlignmentX(alignX);
                  }
               }

            });
         }
      }

      super.addMessageComponents(container, cons, msg, maxll, internallyCreated);
   }

   private void updateChildPanels(Container c) {
      Component[] var2 = c.getComponents();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Component child = var2[var4];
         if (child.getClass() == JPanel.class) {
            JPanel panel = (JPanel)child;
            panel.setOpaque(false);
            Border border = panel.getBorder();
            if (border instanceof UIResource) {
               panel.setBorder(FlatUIUtils.nonUIResource(border));
            }
         }

         if (child instanceof Container) {
            this.updateChildPanels((Container)child);
         }
      }

   }

   protected boolean getSizeButtonsToSameWidth() {
      return this.sameSizeButtons;
   }
}
