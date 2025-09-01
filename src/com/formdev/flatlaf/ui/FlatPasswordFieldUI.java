package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.icons.FlatCapsLockIcon;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.util.Map;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.PasswordView;
import javax.swing.text.View;

public class FlatPasswordFieldUI extends FlatTextFieldUI {
   private static final String KEY_REVEAL_SELECTED = "FlatLaf.internal.FlatPasswordFieldUI.revealSelected";
   private Character echoChar;
   @FlatStylingSupport.Styleable
   protected boolean showCapsLock;
   @FlatStylingSupport.Styleable
   protected boolean showRevealButton;
   protected Icon capsLockIcon;
   protected Icon revealIcon;
   private KeyListener capsLockListener;
   private boolean capsLockIconShared = true;
   private JToggleButton revealButton;
   private boolean uninstallEchoChar;

   public static ComponentUI createUI(JComponent c) {
      return new FlatPasswordFieldUI();
   }

   protected String getPropertyPrefix() {
      return "PasswordField";
   }

   public void installUI(JComponent c) {
      super.installUI(c);
      this.installRevealButton();
   }

   public void uninstallUI(JComponent c) {
      this.uninstallRevealButton();
      super.uninstallUI(c);
   }

   protected void installDefaults() {
      super.installDefaults();
      String prefix = this.getPropertyPrefix();
      this.echoChar = (Character)UIManager.get(prefix + ".echoChar");
      if (this.echoChar != null) {
         LookAndFeel.installProperty(this.getComponent(), "echoChar", this.echoChar);
      }

      this.showCapsLock = UIManager.getBoolean("PasswordField.showCapsLock");
      this.showRevealButton = UIManager.getBoolean("PasswordField.showRevealButton");
      this.capsLockIcon = UIManager.getIcon("PasswordField.capsLockIcon");
      this.revealIcon = UIManager.getIcon("PasswordField.revealIcon");
      this.capsLockIconShared = true;
   }

   protected void uninstallDefaults() {
      super.uninstallDefaults();
      this.capsLockIcon = null;
      this.revealIcon = null;
   }

   protected void installListeners() {
      super.installListeners();
      this.capsLockListener = new KeyAdapter() {
         public void keyPressed(KeyEvent e) {
            this.repaint(e);
         }

         public void keyReleased(KeyEvent e) {
            this.repaint(e);
         }

         private void repaint(KeyEvent e) {
            if (e.getKeyCode() == 20) {
               e.getComponent().repaint();
               FlatPasswordFieldUI.this.scrollCaretToVisible();
            }

         }
      };
      this.getComponent().addKeyListener(this.capsLockListener);
   }

   protected void uninstallListeners() {
      super.uninstallListeners();
      this.getComponent().removeKeyListener(this.capsLockListener);
      this.capsLockListener = null;
   }

   protected void installKeyboardActions() {
      super.installKeyboardActions();
      ActionMap map = SwingUtilities.getUIActionMap(this.getComponent());
      if (map != null && map.get("select-word") != null) {
         Action selectLineAction = map.get("select-line");
         if (selectLineAction != null) {
            map.put("select-word", selectLineAction);
         }
      }

   }

   String getStyleType() {
      return "PasswordField";
   }

   protected void applyStyle(Object style) {
      boolean oldShowRevealButton = this.showRevealButton;
      super.applyStyle(style);
      if (this.showRevealButton != oldShowRevealButton) {
         this.uninstallRevealButton();
         this.installRevealButton();
      }

   }

   protected Object applyStyleProperty(String key, Object value) {
      if (key.equals("capsLockIconColor") && this.capsLockIcon instanceof FlatCapsLockIcon) {
         if (this.capsLockIconShared) {
            this.capsLockIcon = FlatStylingSupport.cloneIcon(this.capsLockIcon);
            this.capsLockIconShared = false;
         }

         return ((FlatCapsLockIcon)this.capsLockIcon).applyStyleProperty(key, value);
      } else {
         return super.applyStyleProperty(key, value);
      }
   }

   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
      Map<String, Class<?>> infos = super.getStyleableInfos(c);
      infos.put("capsLockIconColor", Color.class);
      return infos;
   }

   public Object getStyleableValue(JComponent c, String key) {
      return key.equals("capsLockIconColor") && this.capsLockIcon instanceof FlatCapsLockIcon ? ((FlatCapsLockIcon)this.capsLockIcon).getStyleableValue(key) : super.getStyleableValue(c, key);
   }

   public View create(Element elem) {
      return new PasswordView(elem);
   }

   protected void paintIcons(Graphics g, Rectangle r) {
      super.paintIcons(g, r);
      if (this.isCapsLockVisible()) {
         this.paintCapsLock(g, r);
      }

   }

   protected void paintCapsLock(Graphics g, Rectangle r) {
      JTextComponent c = this.getComponent();
      int x = c.getComponentOrientation().isLeftToRight() ? r.x + r.width - this.capsLockIcon.getIconWidth() : r.x;
      int y = r.y + Math.round((float)(r.height - this.capsLockIcon.getIconHeight()) / 2.0F);
      this.capsLockIcon.paintIcon(c, g, x, y);
   }

   protected boolean hasTrailingIcon() {
      return super.hasTrailingIcon() || this.isCapsLockVisible();
   }

   protected int getTrailingIconWidth() {
      return super.getTrailingIconWidth() + (this.isCapsLockVisible() ? this.capsLockIcon.getIconWidth() + UIScale.scale(this.iconTextGap) : 0);
   }

   protected boolean isCapsLockVisible() {
      if (!this.showCapsLock) {
         return false;
      } else {
         return FlatUIUtils.isPermanentFocusOwner(this.getComponent()) && Toolkit.getDefaultToolkit().getLockingKeyState(20);
      }
   }

   protected void installRevealButton() {
      if (this.showRevealButton) {
         this.revealButton = this.createRevealButton();
         this.updateRevealButton();
         this.installLayout();
         this.getComponent().add(this.revealButton);
      }

   }

   protected JToggleButton createRevealButton() {
      JPasswordField c = (JPasswordField)this.getComponent();
      JToggleButton button = new JToggleButton(this.revealIcon, !c.echoCharIsSet());
      button.setName("PasswordField.revealButton");
      this.prepareLeadingOrTrailingComponent(button);
      button.putClientProperty("FlatLaf.styleClass", "inTextField revealButton");
      if (FlatClientProperties.clientPropertyBoolean(c, "FlatLaf.internal.FlatPasswordFieldUI.revealSelected", false)) {
         button.setSelected(true);
         this.updateEchoChar(true);
      }

      button.addActionListener((e) -> {
         boolean selected = button.isSelected();
         this.updateEchoChar(selected);
         c.putClientProperty("FlatLaf.internal.FlatPasswordFieldUI.revealSelected", selected);
      });
      return button;
   }

   protected void updateRevealButton() {
      if (this.revealButton != null) {
         JTextComponent c = this.getComponent();
         boolean visible = c.isEnabled();
         if (visible != this.revealButton.isVisible()) {
            this.revealButton.setVisible(visible);
            c.revalidate();
            c.repaint();
            if (!visible) {
               this.revealButton.setSelected(false);
               this.updateEchoChar(false);
               this.getComponent().putClientProperty("FlatLaf.internal.FlatPasswordFieldUI.revealSelected", (Object)null);
            }
         }

      }
   }

   protected void propertyChange(PropertyChangeEvent e) {
      super.propertyChange(e);
      String var2 = e.getPropertyName();
      byte var3 = -1;
      switch(var2.hashCode()) {
      case -1609594047:
         if (var2.equals("enabled")) {
            var3 = 0;
         }
      default:
         switch(var3) {
         case 0:
            this.updateRevealButton();
         default:
         }
      }
   }

   private void updateEchoChar(boolean selected) {
      char newEchoChar = selected ? 0 : (this.echoChar != null ? this.echoChar : 42);
      JPasswordField c = (JPasswordField)this.getComponent();
      if (newEchoChar != c.getEchoChar()) {
         LookAndFeel.installProperty(c, "echoChar", newEchoChar);
         char actualEchoChar = c.getEchoChar();
         if (actualEchoChar != newEchoChar) {
            if (selected && actualEchoChar != 0) {
               this.echoChar = actualEchoChar;
               this.uninstallEchoChar = true;
            }

            c.setEchoChar(newEchoChar);
         }

      }
   }

   protected void uninstallRevealButton() {
      if (this.revealButton != null) {
         if (this.uninstallEchoChar && this.revealButton.isSelected()) {
            ((JPasswordField)this.getComponent()).setEchoChar(this.echoChar);
         }

         this.getComponent().remove(this.revealButton);
         this.revealButton = null;
      }

   }

   protected JComponent[] getTrailingComponents() {
      return new JComponent[]{this.trailingComponent, this.revealButton, this.clearButton};
   }
}
