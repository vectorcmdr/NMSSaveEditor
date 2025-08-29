/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.SystemInfo;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.AWTEvent;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.GraphicsDevice;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Insets;
/*     */ import java.awt.MouseInfo;
/*     */ import java.awt.Panel;
/*     */ import java.awt.Point;
/*     */ import java.awt.PointerInfo;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.lang.invoke.MethodHandle;
/*     */ import java.lang.invoke.MethodHandles;
/*     */ import java.lang.invoke.MethodType;
/*     */ import java.lang.reflect.Method;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLayeredPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JWindow;
/*     */ import javax.swing.Popup;
/*     */ import javax.swing.PopupFactory;
/*     */ import javax.swing.RootPaneContainer;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.ToolTipManager;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.border.LineBorder;
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
/*     */ public class FlatPopupFactory
/*     */   extends PopupFactory
/*     */ {
/*     */   private MethodHandle java8getPopupMethod;
/*     */   private MethodHandle java9getPopupMethod;
/*     */   
/*     */   public Popup getPopup(Component owner, Component contents, int x, int y) throws IllegalArgumentException {
/*  80 */     Point pt = fixToolTipLocation(owner, contents, x, y);
/*  81 */     if (pt != null) {
/*  82 */       x = pt.x;
/*  83 */       y = pt.y;
/*     */     } 
/*     */     
/*  86 */     boolean forceHeavyWeight = isOptionEnabled(owner, contents, "Popup.forceHeavyWeight", "Popup.forceHeavyWeight");
/*     */     
/*  88 */     if (!isOptionEnabled(owner, contents, "Popup.dropShadowPainted", "Popup.dropShadowPainted") || SystemInfo.isProjector || SystemInfo.isWebswing) {
/*  89 */       return new NonFlashingPopup(getPopupForScreenOfOwner(owner, contents, x, y, forceHeavyWeight), contents);
/*     */     }
/*     */     
/*  92 */     if (SystemInfo.isMacOS || SystemInfo.isLinux) {
/*  93 */       return new NonFlashingPopup(getPopupForScreenOfOwner(owner, contents, x, y, true), contents);
/*     */     }
/*     */     
/*     */     int borderCornerRadius;
/*  97 */     if (isWindows11BorderSupported() && (
/*  98 */       borderCornerRadius = getBorderCornerRadius(owner, contents)) > 0) {
/*     */       
/* 100 */       NonFlashingPopup popup = new NonFlashingPopup(getPopupForScreenOfOwner(owner, contents, x, y, true), contents);
/* 101 */       if (popup.popupWindow != null)
/* 102 */         setupWindows11Border(popup.popupWindow, contents, borderCornerRadius); 
/* 103 */       return popup;
/*     */     } 
/*     */ 
/*     */     
/* 107 */     return new DropShadowPopup(getPopupForScreenOfOwner(owner, contents, x, y, forceHeavyWeight), owner, contents);
/*     */   }
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
/*     */   private Popup getPopupForScreenOfOwner(Component owner, Component contents, int x, int y, boolean forceHeavyWeight) throws IllegalArgumentException {
/* 127 */     int count = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     while (true) {
/* 133 */       Popup popup = forceHeavyWeight ? getHeavyWeightPopup(owner, contents, x, y) : super.getPopup(owner, contents, x, y);
/*     */ 
/*     */       
/* 136 */       Window popupWindow = SwingUtilities.windowForComponent(contents);
/*     */ 
/*     */       
/* 139 */       if (popupWindow == null || owner == null || popupWindow
/*     */         
/* 141 */         .getGraphicsConfiguration() == owner.getGraphicsConfiguration()) {
/* 142 */         return popup;
/*     */       }
/*     */       
/* 145 */       if (++count > 10) {
/* 146 */         return popup;
/*     */       }
/*     */       
/* 149 */       if (popupWindow instanceof JWindow) {
/* 150 */         ((JWindow)popupWindow).getContentPane().removeAll();
/*     */       }
/*     */ 
/*     */       
/* 154 */       popupWindow.dispose();
/*     */     } 
/*     */   }
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
/*     */   private static void showPopupAndFixLocation(Popup popup, Window popupWindow) {
/* 168 */     if (popupWindow != null) {
/*     */       
/* 170 */       int x = popupWindow.getX();
/* 171 */       int y = popupWindow.getY();
/*     */       
/* 173 */       popup.show();
/*     */ 
/*     */ 
/*     */       
/* 177 */       if (popupWindow.getX() != x || popupWindow.getY() != y)
/* 178 */         popupWindow.setLocation(x, y); 
/*     */     } else {
/* 180 */       popup.show();
/*     */     } 
/*     */   }
/*     */   private boolean isOptionEnabled(Component owner, Component contents, String clientKey, String uiKey) {
/* 184 */     Object value = getOption(owner, contents, clientKey, uiKey);
/* 185 */     return (value instanceof Boolean) ? ((Boolean)value).booleanValue() : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getBorderCornerRadius(Component owner, Component contents) {
/* 193 */     String uiKey = (contents instanceof javax.swing.plaf.basic.BasicComboPopup) ? "ComboBox.borderCornerRadius" : ((contents instanceof javax.swing.JPopupMenu) ? "PopupMenu.borderCornerRadius" : ((contents instanceof javax.swing.JToolTip) ? "ToolTip.borderCornerRadius" : "Popup.borderCornerRadius"));
/*     */     
/* 195 */     Object value = getOption(owner, contents, "Popup.borderCornerRadius", uiKey);
/* 196 */     return (value instanceof Integer) ? ((Integer)value).intValue() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object getOption(Component owner, Component contents, String clientKey, String uiKey) {
/* 208 */     for (Component c : new Component[] { owner, contents }) {
/* 209 */       if (c instanceof JComponent) {
/* 210 */         Object value = ((JComponent)c).getClientProperty(clientKey);
/* 211 */         if (value != null) {
/* 212 */           return value;
/*     */         }
/*     */       } 
/*     */     } 
/* 216 */     return UIManager.get(uiKey);
/*     */   }
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
/*     */   private Popup getHeavyWeightPopup(Component owner, Component contents, int x, int y) throws IllegalArgumentException {
/*     */     try {
/* 231 */       if (SystemInfo.isJava_9_orLater) {
/*     */         
/* 233 */         if (this.java9getPopupMethod == null) {
/* 234 */           MethodType mt = MethodType.methodType(Popup.class, Component.class, new Class[] { Component.class, int.class, int.class, boolean.class });
/* 235 */           this.java9getPopupMethod = MethodHandles.lookup().findVirtual(PopupFactory.class, "getPopup", mt);
/*     */         } 
/* 237 */         return this.java9getPopupMethod.invoke(this, owner, contents, x, y, true);
/*     */       } 
/*     */       
/* 240 */       if (this.java8getPopupMethod == null) {
/* 241 */         Method m = PopupFactory.class.getDeclaredMethod("getPopup", new Class[] { Component.class, Component.class, int.class, int.class, int.class });
/*     */         
/* 243 */         m.setAccessible(true);
/* 244 */         this.java8getPopupMethod = MethodHandles.lookup().unreflect(m);
/*     */       } 
/* 246 */       return this.java8getPopupMethod.invoke(this, owner, contents, x, y, 2);
/*     */     }
/* 248 */     catch (Throwable ex) {
/*     */       
/* 250 */       return super.getPopup(owner, contents, x, y);
/*     */     } 
/*     */   }
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
/*     */   private Point fixToolTipLocation(Component owner, Component contents, int x, int y) {
/* 264 */     if (!(contents instanceof javax.swing.JToolTip) || !wasInvokedFromToolTipManager() || hasTipLocation(owner)) {
/* 265 */       return null;
/*     */     }
/* 267 */     PointerInfo pointerInfo = MouseInfo.getPointerInfo();
/* 268 */     if (pointerInfo == null) {
/* 269 */       return null;
/*     */     }
/* 271 */     Point mouseLocation = pointerInfo.getLocation();
/* 272 */     Dimension tipSize = contents.getPreferredSize();
/*     */ 
/*     */     
/* 275 */     Rectangle tipBounds = new Rectangle(x, y, tipSize.width, tipSize.height);
/* 276 */     if (!tipBounds.contains(mouseLocation)) {
/* 277 */       return null;
/*     */     }
/*     */     
/* 280 */     GraphicsConfiguration gc = null;
/* 281 */     for (GraphicsDevice device : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
/* 282 */       GraphicsConfiguration dgc = device.getDefaultConfiguration();
/* 283 */       if (dgc.getBounds().contains(mouseLocation)) {
/* 284 */         gc = dgc;
/*     */         break;
/*     */       } 
/*     */     } 
/* 288 */     if (gc == null)
/* 289 */       gc = owner.getGraphicsConfiguration(); 
/* 290 */     if (gc == null) {
/* 291 */       return null;
/*     */     }
/* 293 */     Rectangle screenBounds = gc.getBounds();
/* 294 */     Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gc);
/* 295 */     int screenTop = screenBounds.y + screenInsets.top;
/*     */ 
/*     */     
/* 298 */     int newY = mouseLocation.y - tipSize.height - UIScale.scale(20);
/* 299 */     if (newY < screenTop) {
/* 300 */       return null;
/*     */     }
/* 302 */     return new Point(x, newY);
/*     */   }
/*     */   
/*     */   private boolean wasInvokedFromToolTipManager() {
/* 306 */     return StackUtils.wasInvokedFrom(ToolTipManager.class.getName(), "showTipWindow", 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasTipLocation(Component owner) {
/*     */     MouseEvent me;
/* 314 */     if (!(owner instanceof JComponent)) {
/* 315 */       return false;
/*     */     }
/* 317 */     AWTEvent e = EventQueue.getCurrentEvent();
/*     */     
/* 319 */     if (e instanceof MouseEvent) {
/* 320 */       me = (MouseEvent)e;
/*     */     }
/*     */     else {
/*     */       
/* 324 */       PointerInfo pointerInfo = MouseInfo.getPointerInfo();
/* 325 */       if (pointerInfo == null) {
/* 326 */         return false;
/*     */       }
/* 328 */       Point location = new Point(pointerInfo.getLocation());
/* 329 */       SwingUtilities.convertPointFromScreen(location, owner);
/* 330 */       me = new MouseEvent(owner, 503, System.currentTimeMillis(), 0, location.x, location.y, 0, false);
/*     */     } 
/*     */ 
/*     */     
/* 334 */     return (me.getSource() == owner && ((JComponent)owner)
/* 335 */       .getToolTipLocation(me) != null);
/*     */   }
/*     */   
/*     */   private static boolean isWindows11BorderSupported() {
/* 339 */     return (SystemInfo.isWindows_11_orLater && FlatNativeWindowsLibrary.isLoaded());
/*     */   }
/*     */ 
/*     */   
/*     */   private static void setupWindows11Border(Window popupWindow, Component contents, int borderCornerRadius) {
/* 344 */     if (!popupWindow.isDisplayable()) {
/* 345 */       popupWindow.addNotify();
/*     */     }
/*     */     
/* 348 */     long hwnd = FlatNativeWindowsLibrary.getHWND(popupWindow);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 353 */     int cornerPreference = (borderCornerRadius <= 4) ? 3 : 2;
/* 354 */     FlatNativeWindowsLibrary.setWindowCornerPreference(hwnd, cornerPreference);
/*     */ 
/*     */     
/* 357 */     int red = -1;
/* 358 */     int green = 0;
/* 359 */     int blue = 0;
/* 360 */     if (contents instanceof JComponent) {
/* 361 */       Border border = ((JComponent)contents).getBorder();
/* 362 */       border = FlatUIUtils.unwrapNonUIResourceBorder(border);
/*     */ 
/*     */       
/* 365 */       Color borderColor = null;
/* 366 */       if (border instanceof FlatLineBorder) {
/* 367 */         borderColor = ((FlatLineBorder)border).getLineColor();
/* 368 */       } else if (border instanceof LineBorder) {
/* 369 */         borderColor = ((LineBorder)border).getLineColor();
/* 370 */       } else if (border instanceof javax.swing.border.EmptyBorder) {
/* 371 */         red = -2;
/*     */       } 
/* 373 */       if (borderColor != null) {
/* 374 */         red = borderColor.getRed();
/* 375 */         green = borderColor.getGreen();
/* 376 */         blue = borderColor.getBlue();
/*     */       } 
/*     */     } 
/* 379 */     FlatNativeWindowsLibrary.setWindowBorderColor(hwnd, red, green, blue);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void resetWindows11Border(Window popupWindow) {
/* 384 */     long hwnd = FlatNativeWindowsLibrary.getHWND(popupWindow);
/* 385 */     if (hwnd == 0L) {
/*     */       return;
/*     */     }
/*     */     
/* 389 */     FlatNativeWindowsLibrary.setWindowCornerPreference(hwnd, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   private class NonFlashingPopup
/*     */     extends Popup
/*     */   {
/*     */     private Popup delegate;
/*     */     
/*     */     private Component contents;
/*     */     
/*     */     protected Window popupWindow;
/*     */     
/*     */     private Color oldPopupWindowBackground;
/*     */     
/*     */     NonFlashingPopup(Popup delegate, Component contents) {
/* 405 */       this.delegate = delegate;
/* 406 */       this.contents = contents;
/*     */       
/* 408 */       this.popupWindow = SwingUtilities.windowForComponent(contents);
/* 409 */       if (this.popupWindow != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 414 */         this.oldPopupWindowBackground = this.popupWindow.getBackground();
/* 415 */         this.popupWindow.setBackground(contents.getBackground());
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void show() {
/* 421 */       if (this.delegate != null) {
/* 422 */         FlatPopupFactory.showPopupAndFixLocation(this.delegate, this.popupWindow);
/*     */ 
/*     */ 
/*     */         
/* 426 */         if (this.contents instanceof javax.swing.JToolTip && this.popupWindow == null) {
/* 427 */           Container parent = this.contents.getParent();
/* 428 */           if (parent instanceof JPanel) {
/* 429 */             Dimension prefSize = parent.getPreferredSize();
/* 430 */             if (!prefSize.equals(parent.getSize())) {
/* 431 */               Container mediumWeightPanel = SwingUtilities.getAncestorOfClass(Panel.class, parent);
/*     */ 
/*     */               
/* 434 */               Container c = (mediumWeightPanel != null) ? mediumWeightPanel : parent;
/* 435 */               c.setSize(prefSize);
/* 436 */               c.validate();
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void hide() {
/* 445 */       if (this.delegate != null) {
/* 446 */         this.delegate.hide();
/* 447 */         this.delegate = null;
/* 448 */         this.contents = null;
/*     */       } 
/*     */       
/* 451 */       if (this.popupWindow != null) {
/*     */ 
/*     */         
/* 454 */         this.popupWindow.setBackground(this.oldPopupWindowBackground);
/* 455 */         this.popupWindow = null;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private class DropShadowPopup
/*     */     extends NonFlashingPopup
/*     */   {
/*     */     private final Component owner;
/*     */     
/*     */     private JComponent lightComp;
/*     */     
/*     */     private Border oldBorder;
/*     */     
/*     */     private boolean oldOpaque;
/*     */     
/*     */     private boolean mediumWeightShown;
/*     */     
/*     */     private Panel mediumWeightPanel;
/*     */     
/*     */     private JPanel dropShadowPanel;
/*     */     
/*     */     private ComponentListener mediumPanelListener;
/*     */     private Popup dropShadowDelegate;
/*     */     private Window dropShadowWindow;
/*     */     private Color oldDropShadowWindowBackground;
/*     */     
/*     */     DropShadowPopup(Popup delegate, Component owner, Component contents) {
/* 484 */       super(delegate, contents);
/* 485 */       this.owner = owner;
/*     */       
/* 487 */       Dimension size = contents.getPreferredSize();
/* 488 */       if (size.width <= 0 || size.height <= 0) {
/*     */         return;
/*     */       }
/* 491 */       if (this.popupWindow != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 501 */         JPanel dropShadowPanel = new JPanel();
/* 502 */         dropShadowPanel.setBorder(createDropShadowBorder());
/* 503 */         dropShadowPanel.setOpaque(false);
/*     */ 
/*     */         
/* 506 */         Dimension prefSize = this.popupWindow.getPreferredSize();
/* 507 */         Insets insets = dropShadowPanel.getInsets();
/* 508 */         dropShadowPanel.setPreferredSize(new Dimension(prefSize.width + insets.left + insets.right, prefSize.height + insets.top + insets.bottom));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 513 */         int x = this.popupWindow.getX() - insets.left;
/* 514 */         int y = this.popupWindow.getY() - insets.top;
/* 515 */         this.dropShadowDelegate = FlatPopupFactory.this.getPopupForScreenOfOwner(owner, dropShadowPanel, x, y, true);
/*     */ 
/*     */         
/* 518 */         this.dropShadowWindow = SwingUtilities.windowForComponent(dropShadowPanel);
/* 519 */         if (this.dropShadowWindow != null) {
/* 520 */           this.oldDropShadowWindowBackground = this.dropShadowWindow.getBackground();
/* 521 */           this.dropShadowWindow.setBackground(new Color(0, true));
/*     */         } 
/*     */ 
/*     */         
/* 525 */         if (FlatPopupFactory.isWindows11BorderSupported()) {
/* 526 */           FlatPopupFactory.resetWindows11Border(this.popupWindow);
/* 527 */           if (this.dropShadowWindow != null) {
/* 528 */             FlatPopupFactory.resetWindows11Border(this.dropShadowWindow);
/*     */           }
/*     */         } 
/*     */       } else {
/* 532 */         this.mediumWeightPanel = (Panel)SwingUtilities.getAncestorOfClass(Panel.class, contents);
/* 533 */         if (this.mediumWeightPanel != null) {
/*     */           
/* 535 */           this.dropShadowPanel = new JPanel();
/* 536 */           this.dropShadowPanel.setBorder(createDropShadowBorder());
/* 537 */           this.dropShadowPanel.setOpaque(false);
/* 538 */           this.dropShadowPanel.setSize(FlatUIUtils.addInsets(this.mediumWeightPanel.getSize(), this.dropShadowPanel.getInsets()));
/*     */         } else {
/*     */           
/* 541 */           Container p = contents.getParent();
/* 542 */           if (!(p instanceof JComponent)) {
/*     */             return;
/*     */           }
/* 545 */           this.lightComp = (JComponent)p;
/* 546 */           this.oldBorder = this.lightComp.getBorder();
/* 547 */           this.oldOpaque = this.lightComp.isOpaque();
/* 548 */           this.lightComp.setBorder(createDropShadowBorder());
/* 549 */           this.lightComp.setOpaque(false);
/* 550 */           this.lightComp.setSize(this.lightComp.getPreferredSize());
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private Border createDropShadowBorder() {
/* 556 */       return new FlatDropShadowBorder(
/* 557 */           UIManager.getColor("Popup.dropShadowColor"), 
/* 558 */           UIManager.getInsets("Popup.dropShadowInsets"), 
/* 559 */           FlatUIUtils.getUIFloat("Popup.dropShadowOpacity", 0.5F));
/*     */     }
/*     */ 
/*     */     
/*     */     public void show() {
/* 564 */       if (this.dropShadowDelegate != null) {
/* 565 */         FlatPopupFactory.showPopupAndFixLocation(this.dropShadowDelegate, this.dropShadowWindow);
/*     */       }
/* 567 */       if (this.mediumWeightPanel != null) {
/* 568 */         showMediumWeightDropShadow();
/*     */       }
/* 570 */       super.show();
/*     */ 
/*     */       
/* 573 */       if (this.lightComp != null) {
/* 574 */         Insets insets = this.lightComp.getInsets();
/* 575 */         if (insets.left != 0 || insets.top != 0) {
/* 576 */           this.lightComp.setLocation(this.lightComp.getX() - insets.left, this.lightComp.getY() - insets.top);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*     */     public void hide() {
/* 582 */       if (this.dropShadowDelegate != null) {
/* 583 */         this.dropShadowDelegate.hide();
/* 584 */         this.dropShadowDelegate = null;
/*     */       } 
/*     */       
/* 587 */       if (this.mediumWeightPanel != null) {
/* 588 */         hideMediumWeightDropShadow();
/* 589 */         this.dropShadowPanel = null;
/* 590 */         this.mediumWeightPanel = null;
/*     */       } 
/*     */       
/* 593 */       super.hide();
/*     */       
/* 595 */       if (this.dropShadowWindow != null) {
/* 596 */         this.dropShadowWindow.setBackground(this.oldDropShadowWindowBackground);
/* 597 */         this.dropShadowWindow = null;
/*     */       } 
/*     */       
/* 600 */       if (this.lightComp != null) {
/* 601 */         this.lightComp.setBorder(this.oldBorder);
/* 602 */         this.lightComp.setOpaque(this.oldOpaque);
/* 603 */         this.lightComp = null;
/*     */       } 
/*     */     }
/*     */     
/*     */     private void showMediumWeightDropShadow() {
/* 608 */       if (this.mediumWeightShown) {
/*     */         return;
/*     */       }
/* 611 */       this.mediumWeightShown = true;
/*     */       
/* 613 */       if (this.owner == null) {
/*     */         return;
/*     */       }
/* 616 */       Window window = SwingUtilities.windowForComponent(this.owner);
/* 617 */       if (!(window instanceof RootPaneContainer)) {
/*     */         return;
/*     */       }
/* 620 */       this.dropShadowPanel.setVisible(false);
/*     */       
/* 622 */       JLayeredPane layeredPane = ((RootPaneContainer)window).getLayeredPane();
/* 623 */       layeredPane.add(this.dropShadowPanel, JLayeredPane.POPUP_LAYER, 0);
/*     */       
/* 625 */       moveMediumWeightDropShadow();
/* 626 */       resizeMediumWeightDropShadow();
/*     */       
/* 628 */       this.mediumPanelListener = new ComponentListener()
/*     */         {
/*     */           public void componentShown(ComponentEvent e) {
/* 631 */             if (FlatPopupFactory.DropShadowPopup.this.dropShadowPanel != null) {
/* 632 */               FlatPopupFactory.DropShadowPopup.this.dropShadowPanel.setVisible(true);
/*     */             }
/*     */           }
/*     */           
/*     */           public void componentHidden(ComponentEvent e) {
/* 637 */             if (FlatPopupFactory.DropShadowPopup.this.dropShadowPanel != null) {
/* 638 */               FlatPopupFactory.DropShadowPopup.this.dropShadowPanel.setVisible(false);
/*     */             }
/*     */           }
/*     */           
/*     */           public void componentMoved(ComponentEvent e) {
/* 643 */             FlatPopupFactory.DropShadowPopup.this.moveMediumWeightDropShadow();
/*     */           }
/*     */ 
/*     */           
/*     */           public void componentResized(ComponentEvent e) {
/* 648 */             FlatPopupFactory.DropShadowPopup.this.resizeMediumWeightDropShadow();
/*     */           }
/*     */         };
/* 651 */       this.mediumWeightPanel.addComponentListener(this.mediumPanelListener);
/*     */     }
/*     */     
/*     */     private void hideMediumWeightDropShadow() {
/* 655 */       this.mediumWeightPanel.removeComponentListener(this.mediumPanelListener);
/*     */       
/* 657 */       Container parent = this.dropShadowPanel.getParent();
/* 658 */       if (parent != null) {
/* 659 */         Rectangle bounds = this.dropShadowPanel.getBounds();
/* 660 */         parent.remove(this.dropShadowPanel);
/* 661 */         parent.repaint(bounds.x, bounds.y, bounds.width, bounds.height);
/*     */       } 
/*     */     }
/*     */     
/*     */     private void moveMediumWeightDropShadow() {
/* 666 */       if (this.dropShadowPanel != null && this.mediumWeightPanel != null) {
/* 667 */         Point location = this.mediumWeightPanel.getLocation();
/* 668 */         Insets insets = this.dropShadowPanel.getInsets();
/* 669 */         this.dropShadowPanel.setLocation(location.x - insets.left, location.y - insets.top);
/*     */       } 
/*     */     }
/*     */     
/*     */     private void resizeMediumWeightDropShadow() {
/* 674 */       if (this.dropShadowPanel != null && this.mediumWeightPanel != null)
/* 675 */         this.dropShadowPanel.setSize(FlatUIUtils.addInsets(this.mediumWeightPanel.getSize(), this.dropShadowPanel.getInsets())); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatPopupFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */