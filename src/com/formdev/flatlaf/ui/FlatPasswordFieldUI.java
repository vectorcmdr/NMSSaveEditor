/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.FlatClientProperties;
/*     */ import com.formdev.flatlaf.icons.FlatCapsLockIcon;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.util.Map;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.ActionMap;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPasswordField;
/*     */ import javax.swing.JToggleButton;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.text.Element;
/*     */ import javax.swing.text.JTextComponent;
/*     */ import javax.swing.text.PasswordView;
/*     */ import javax.swing.text.View;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatPasswordFieldUI
/*     */   extends FlatTextFieldUI
/*     */ {
/*     */   private static final String KEY_REVEAL_SELECTED = "FlatLaf.internal.FlatPasswordFieldUI.revealSelected";
/*     */   private Character echoChar;
/*     */   @Styleable
/*     */   protected boolean showCapsLock;
/*     */   @Styleable
/*     */   protected boolean showRevealButton;
/*     */   protected Icon capsLockIcon;
/*     */   protected Icon revealIcon;
/*     */   private KeyListener capsLockListener;
/*     */   private boolean capsLockIconShared = true;
/*     */   private JToggleButton revealButton;
/*     */   private boolean uninstallEchoChar;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/* 105 */     return new FlatPasswordFieldUI();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getPropertyPrefix() {
/* 110 */     return "PasswordField";
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/* 115 */     super.installUI(c);
/*     */     
/* 117 */     installRevealButton();
/*     */   }
/*     */ 
/*     */   
/*     */   public void uninstallUI(JComponent c) {
/* 122 */     uninstallRevealButton();
/*     */     
/* 124 */     super.uninstallUI(c);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/* 129 */     super.installDefaults();
/*     */     
/* 131 */     String prefix = getPropertyPrefix();
/* 132 */     this.echoChar = (Character)UIManager.get(prefix + ".echoChar");
/* 133 */     if (this.echoChar != null) {
/* 134 */       LookAndFeel.installProperty(getComponent(), "echoChar", this.echoChar);
/*     */     }
/* 136 */     this.showCapsLock = UIManager.getBoolean("PasswordField.showCapsLock");
/* 137 */     this.showRevealButton = UIManager.getBoolean("PasswordField.showRevealButton");
/* 138 */     this.capsLockIcon = UIManager.getIcon("PasswordField.capsLockIcon");
/* 139 */     this.revealIcon = UIManager.getIcon("PasswordField.revealIcon");
/* 140 */     this.capsLockIconShared = true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults() {
/* 145 */     super.uninstallDefaults();
/*     */     
/* 147 */     this.capsLockIcon = null;
/* 148 */     this.revealIcon = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners() {
/* 153 */     super.installListeners();
/*     */ 
/*     */     
/* 156 */     this.capsLockListener = new KeyAdapter()
/*     */       {
/*     */         public void keyPressed(KeyEvent e) {
/* 159 */           repaint(e);
/*     */         }
/*     */         
/*     */         public void keyReleased(KeyEvent e) {
/* 163 */           repaint(e);
/*     */         }
/*     */         private void repaint(KeyEvent e) {
/* 166 */           if (e.getKeyCode() == 20) {
/* 167 */             e.getComponent().repaint();
/* 168 */             FlatPasswordFieldUI.this.scrollCaretToVisible();
/*     */           } 
/*     */         }
/*     */       };
/*     */     
/* 173 */     getComponent().addKeyListener(this.capsLockListener);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners() {
/* 178 */     super.uninstallListeners();
/*     */     
/* 180 */     getComponent().removeKeyListener(this.capsLockListener);
/* 181 */     this.capsLockListener = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installKeyboardActions() {
/* 186 */     super.installKeyboardActions();
/*     */ 
/*     */     
/* 189 */     ActionMap map = SwingUtilities.getUIActionMap(getComponent());
/* 190 */     if (map != null && map.get("select-word") != null) {
/* 191 */       Action selectLineAction = map.get("select-line");
/* 192 */       if (selectLineAction != null) {
/* 193 */         map.put("select-word", selectLineAction);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   String getStyleType() {
/* 200 */     return "PasswordField";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 205 */     boolean oldShowRevealButton = this.showRevealButton;
/*     */     
/* 207 */     super.applyStyle(style);
/*     */     
/* 209 */     if (this.showRevealButton != oldShowRevealButton) {
/* 210 */       uninstallRevealButton();
/* 211 */       installRevealButton();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 218 */     if (key.equals("capsLockIconColor") && this.capsLockIcon instanceof FlatCapsLockIcon) {
/* 219 */       if (this.capsLockIconShared) {
/* 220 */         this.capsLockIcon = FlatStylingSupport.cloneIcon(this.capsLockIcon);
/* 221 */         this.capsLockIconShared = false;
/*     */       } 
/* 223 */       return ((FlatCapsLockIcon)this.capsLockIcon).applyStyleProperty(key, value);
/*     */     } 
/*     */     
/* 226 */     return super.applyStyleProperty(key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 232 */     Map<String, Class<?>> infos = super.getStyleableInfos(c);
/* 233 */     infos.put("capsLockIconColor", Color.class);
/* 234 */     return infos;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 239 */     if (key.equals("capsLockIconColor") && this.capsLockIcon instanceof FlatCapsLockIcon) {
/* 240 */       return ((FlatCapsLockIcon)this.capsLockIcon).getStyleableValue(key);
/*     */     }
/* 242 */     return super.getStyleableValue(c, key);
/*     */   }
/*     */ 
/*     */   
/*     */   public View create(Element elem) {
/* 247 */     return new PasswordView(elem);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintIcons(Graphics g, Rectangle r) {
/* 253 */     super.paintIcons(g, r);
/*     */     
/* 255 */     if (isCapsLockVisible()) {
/* 256 */       paintCapsLock(g, r);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void paintCapsLock(Graphics g, Rectangle r) {
/* 261 */     JTextComponent c = getComponent();
/*     */ 
/*     */     
/* 264 */     int x = c.getComponentOrientation().isLeftToRight() ? (r.x + r.width - this.capsLockIcon.getIconWidth()) : r.x;
/* 265 */     int y = r.y + Math.round((r.height - this.capsLockIcon.getIconHeight()) / 2.0F);
/* 266 */     this.capsLockIcon.paintIcon(c, g, x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean hasTrailingIcon() {
/* 272 */     return (super.hasTrailingIcon() || isCapsLockVisible());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getTrailingIconWidth() {
/* 278 */     return super.getTrailingIconWidth() + (
/* 279 */       isCapsLockVisible() ? (this.capsLockIcon.getIconWidth() + UIScale.scale(this.iconTextGap)) : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isCapsLockVisible() {
/* 284 */     if (!this.showCapsLock) {
/* 285 */       return false;
/*     */     }
/* 287 */     return (FlatUIUtils.isPermanentFocusOwner(getComponent()) && 
/* 288 */       Toolkit.getDefaultToolkit().getLockingKeyState(20));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installRevealButton() {
/* 293 */     if (this.showRevealButton) {
/* 294 */       this.revealButton = createRevealButton();
/* 295 */       updateRevealButton();
/* 296 */       installLayout();
/* 297 */       getComponent().add(this.revealButton);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected JToggleButton createRevealButton() {
/* 303 */     JPasswordField c = (JPasswordField)getComponent();
/* 304 */     JToggleButton button = new JToggleButton(this.revealIcon, !c.echoCharIsSet());
/* 305 */     button.setName("PasswordField.revealButton");
/* 306 */     prepareLeadingOrTrailingComponent(button);
/* 307 */     button.putClientProperty("FlatLaf.styleClass", "inTextField revealButton");
/* 308 */     if (FlatClientProperties.clientPropertyBoolean(c, "FlatLaf.internal.FlatPasswordFieldUI.revealSelected", false)) {
/* 309 */       button.setSelected(true);
/* 310 */       updateEchoChar(true);
/*     */     } 
/* 312 */     button.addActionListener(e -> {
/*     */           boolean selected = button.isSelected();
/*     */           updateEchoChar(selected);
/*     */           c.putClientProperty("FlatLaf.internal.FlatPasswordFieldUI.revealSelected", Boolean.valueOf(selected));
/*     */         });
/* 317 */     return button;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateRevealButton() {
/* 322 */     if (this.revealButton == null) {
/*     */       return;
/*     */     }
/* 325 */     JTextComponent c = getComponent();
/* 326 */     boolean visible = c.isEnabled();
/* 327 */     if (visible != this.revealButton.isVisible()) {
/* 328 */       this.revealButton.setVisible(visible);
/* 329 */       c.revalidate();
/* 330 */       c.repaint();
/*     */       
/* 332 */       if (!visible) {
/* 333 */         this.revealButton.setSelected(false);
/* 334 */         updateEchoChar(false);
/* 335 */         getComponent().putClientProperty("FlatLaf.internal.FlatPasswordFieldUI.revealSelected", (Object)null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void propertyChange(PropertyChangeEvent e) {
/* 342 */     super.propertyChange(e);
/*     */     
/* 344 */     switch (e.getPropertyName()) {
/*     */       case "enabled":
/* 346 */         updateRevealButton();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateEchoChar(boolean selected) {
/* 354 */     char newEchoChar = selected ? Character.MIN_VALUE : ((this.echoChar != null) ? this.echoChar.charValue() : '*');
/*     */     
/* 356 */     JPasswordField c = (JPasswordField)getComponent();
/* 357 */     if (newEchoChar == c.getEchoChar()) {
/*     */       return;
/*     */     }
/*     */     
/* 361 */     LookAndFeel.installProperty(c, "echoChar", Character.valueOf(newEchoChar));
/*     */ 
/*     */ 
/*     */     
/* 365 */     char actualEchoChar = c.getEchoChar();
/* 366 */     if (actualEchoChar != newEchoChar) {
/* 367 */       if (selected && actualEchoChar != '\000') {
/*     */         
/* 369 */         this.echoChar = Character.valueOf(actualEchoChar);
/* 370 */         this.uninstallEchoChar = true;
/*     */       } 
/*     */       
/* 373 */       c.setEchoChar(newEchoChar);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallRevealButton() {
/* 379 */     if (this.revealButton != null) {
/* 380 */       if (this.uninstallEchoChar && this.revealButton.isSelected()) {
/* 381 */         ((JPasswordField)getComponent()).setEchoChar(this.echoChar.charValue());
/*     */       }
/* 383 */       getComponent().remove(this.revealButton);
/* 384 */       this.revealButton = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected JComponent[] getTrailingComponents() {
/* 390 */     return new JComponent[] { this.trailingComponent, this.revealButton, this.clearButton };
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatPasswordFieldUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */