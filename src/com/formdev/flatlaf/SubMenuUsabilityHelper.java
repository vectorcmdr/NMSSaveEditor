/*     */ package com.formdev.flatlaf;
/*     */ 
/*     */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*     */ import java.awt.AWTEvent;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.MouseInfo;
/*     */ import java.awt.Point;
/*     */ import java.awt.PointerInfo;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLayeredPane;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.MenuElement;
/*     */ import javax.swing.MenuSelectionManager;
/*     */ import javax.swing.RootPaneContainer;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.Timer;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
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
/*     */ class SubMenuUsabilityHelper
/*     */   implements ChangeListener
/*     */ {
/*     */   private static final String KEY_USE_SAFE_TRIANGLE = "Menu.useSafeTriangle";
/*     */   private static final String KEY_SHOW_SAFE_TRIANGLE = "FlatLaf.debug.menu.showSafeTriangle";
/*     */   private static SubMenuUsabilityHelper instance;
/*     */   private SubMenuEventQueue subMenuEventQueue;
/*     */   private SafeTrianglePainter safeTrianglePainter;
/*     */   private boolean changePending;
/*     */   private int mouseX;
/*     */   private int mouseY;
/*     */   private int targetX;
/*     */   private int targetTopY;
/*     */   private int targetBottomY;
/*     */   private Rectangle invokerBounds;
/*     */   
/*     */   static synchronized boolean install() {
/*  83 */     if (instance != null) {
/*  84 */       return false;
/*     */     }
/*  86 */     instance = new SubMenuUsabilityHelper();
/*  87 */     MenuSelectionManager.defaultManager().addChangeListener(instance);
/*  88 */     return true;
/*     */   }
/*     */   
/*     */   static synchronized void uninstall() {
/*  92 */     if (instance == null) {
/*     */       return;
/*     */     }
/*  95 */     MenuSelectionManager.defaultManager().removeChangeListener(instance);
/*  96 */     instance.uninstallEventQueue();
/*  97 */     instance = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void stateChanged(ChangeEvent e) {
/* 102 */     if (!FlatUIUtils.getUIBoolean("Menu.useSafeTriangle", true)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     synchronized (this) {
/* 110 */       if (this.changePending)
/*     */         return; 
/* 112 */       this.changePending = true;
/*     */     } 
/*     */     
/* 115 */     EventQueue.invokeLater(() -> {
/*     */           synchronized (this) {
/*     */             this.changePending = false;
/*     */           } 
/*     */           menuSelectionChanged();
/*     */         });
/*     */   }
/*     */   
/*     */   private void menuSelectionChanged() {
/* 124 */     MenuElement[] path = MenuSelectionManager.defaultManager().getSelectedPath();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 133 */     int subMenuIndex = findSubMenu(path);
/*     */ 
/*     */     
/* 136 */     if (subMenuIndex < 0 || subMenuIndex != path.length - 1) {
/* 137 */       uninstallEventQueue();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 142 */     PointerInfo pointerInfo = MouseInfo.getPointerInfo();
/* 143 */     Point mouseLocation = (pointerInfo != null) ? pointerInfo.getLocation() : new Point();
/* 144 */     this.mouseX = mouseLocation.x;
/* 145 */     this.mouseY = mouseLocation.y;
/*     */ 
/*     */     
/* 148 */     JPopupMenu popup = (JPopupMenu)path[subMenuIndex];
/* 149 */     if (!popup.isShowing()) {
/* 150 */       uninstallEventQueue();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 155 */     Component invoker = popup.getInvoker();
/* 156 */     this
/*     */       
/* 158 */       .invokerBounds = (invoker != null) ? new Rectangle(invoker.getLocationOnScreen(), invoker.getSize()) : null;
/*     */ 
/*     */     
/* 161 */     if (this.invokerBounds != null && !this.invokerBounds.contains(this.mouseX, this.mouseY)) {
/* 162 */       uninstallEventQueue();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 167 */     Point popupLocation = popup.getLocationOnScreen();
/* 168 */     Dimension popupSize = popup.getSize();
/* 169 */     this
/*     */       
/* 171 */       .targetX = (this.mouseX < popupLocation.x + popupSize.width / 2) ? popupLocation.x : (popupLocation.x + popupSize.width);
/* 172 */     this.targetTopY = popupLocation.y;
/* 173 */     this.targetBottomY = popupLocation.y + popupSize.height;
/*     */ 
/*     */     
/* 176 */     if (this.subMenuEventQueue == null) {
/* 177 */       this.subMenuEventQueue = new SubMenuEventQueue();
/*     */     }
/*     */     
/* 180 */     if (this.safeTrianglePainter == null && UIManager.getBoolean("FlatLaf.debug.menu.showSafeTriangle"))
/* 181 */       this.safeTrianglePainter = new SafeTrianglePainter(popup); 
/*     */   }
/*     */   
/*     */   private void uninstallEventQueue() {
/* 185 */     if (this.subMenuEventQueue != null) {
/* 186 */       this.subMenuEventQueue.uninstall();
/* 187 */       this.subMenuEventQueue = null;
/*     */     } 
/*     */     
/* 190 */     if (this.safeTrianglePainter != null) {
/* 191 */       this.safeTrianglePainter.uninstall();
/* 192 */       this.safeTrianglePainter = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private int findSubMenu(MenuElement[] path) {
/* 197 */     for (int i = path.length - 1; i >= 1; i--) {
/* 198 */       if (path[i] instanceof JPopupMenu && path[i - 1] instanceof JMenu && 
/*     */         
/* 200 */         !((JMenu)path[i - 1]).isTopLevelMenu())
/* 201 */         return i; 
/*     */     } 
/* 203 */     return -1;
/*     */   }
/*     */   
/*     */   private Polygon createSafeTriangle() {
/* 207 */     return new Polygon(new int[] { this.mouseX, this.targetX, this.targetX }, new int[] { this.mouseY, this.targetTopY, this.targetBottomY }, 3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private class SubMenuEventQueue
/*     */     extends EventQueue
/*     */   {
/*     */     private Timer mouseUpdateTimer;
/*     */     
/*     */     private Timer timeoutTimer;
/*     */     
/*     */     private int newMouseX;
/*     */     
/*     */     private int newMouseY;
/*     */     
/*     */     private AWTEvent lastMouseEvent;
/*     */ 
/*     */     
/*     */     SubMenuEventQueue() {
/* 227 */       this.mouseUpdateTimer = new Timer(50, e -> {
/*     */             SubMenuUsabilityHelper.this.mouseX = this.newMouseX;
/*     */             SubMenuUsabilityHelper.this.mouseY = this.newMouseY;
/*     */             if (SubMenuUsabilityHelper.this.safeTrianglePainter != null) {
/*     */               SubMenuUsabilityHelper.this.safeTrianglePainter.repaint();
/*     */             }
/*     */           });
/* 234 */       this.mouseUpdateTimer.setRepeats(false);
/*     */ 
/*     */       
/* 237 */       this.timeoutTimer = new Timer(200, e -> {
/*     */             if (SubMenuUsabilityHelper.this.invokerBounds != null && !SubMenuUsabilityHelper.this.invokerBounds.contains(this.newMouseX, this.newMouseY)) {
/*     */               if (this.lastMouseEvent != null) {
/*     */                 postEvent(this.lastMouseEvent);
/*     */                 
/*     */                 this.lastMouseEvent = null;
/*     */               } 
/*     */               
/*     */               SubMenuUsabilityHelper.this.uninstallEventQueue();
/*     */               return;
/*     */             } 
/*     */           });
/* 249 */       this.timeoutTimer.setRepeats(false);
/*     */       
/* 251 */       Toolkit.getDefaultToolkit().getSystemEventQueue().push(this);
/*     */     }
/*     */     
/*     */     void uninstall() {
/* 255 */       this.mouseUpdateTimer.stop();
/* 256 */       this.mouseUpdateTimer = null;
/*     */       
/* 258 */       this.timeoutTimer.stop();
/* 259 */       this.timeoutTimer = null;
/*     */       
/* 261 */       this.lastMouseEvent = null;
/*     */       
/* 263 */       pop();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void dispatchEvent(AWTEvent e) {
/* 268 */       int id = e.getID();
/*     */       
/* 270 */       if (e instanceof MouseEvent && (id == 503 || id == 506)) {
/*     */ 
/*     */         
/* 273 */         this.newMouseX = ((MouseEvent)e).getXOnScreen();
/* 274 */         this.newMouseY = ((MouseEvent)e).getYOnScreen();
/*     */         
/* 276 */         if (SubMenuUsabilityHelper.this.safeTrianglePainter != null) {
/* 277 */           SubMenuUsabilityHelper.this.safeTrianglePainter.repaint();
/*     */         }
/* 279 */         this.mouseUpdateTimer.stop();
/* 280 */         this.timeoutTimer.stop();
/*     */ 
/*     */         
/* 283 */         if (SubMenuUsabilityHelper.this.createSafeTriangle().contains(this.newMouseX, this.newMouseY)) {
/*     */           
/* 285 */           this.mouseUpdateTimer.start();
/*     */           
/* 287 */           this.timeoutTimer.start();
/*     */ 
/*     */           
/* 290 */           this.lastMouseEvent = e;
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */ 
/*     */         
/* 297 */         SubMenuUsabilityHelper.this.mouseX = this.newMouseX;
/* 298 */         SubMenuUsabilityHelper.this.mouseY = this.newMouseY;
/*     */       } 
/*     */       
/* 301 */       super.dispatchEvent(e);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private class SafeTrianglePainter
/*     */     extends JComponent
/*     */   {
/*     */     SafeTrianglePainter(JPopupMenu popup) {
/* 311 */       Window window = SwingUtilities.windowForComponent(popup.getInvoker());
/* 312 */       if (window instanceof RootPaneContainer) {
/* 313 */         JLayeredPane layeredPane = ((RootPaneContainer)window).getLayeredPane();
/* 314 */         setSize(layeredPane.getSize());
/* 315 */         layeredPane.add(this, Integer.valueOf(JLayeredPane.POPUP_LAYER.intValue() + 1));
/*     */       } 
/*     */     }
/*     */     
/*     */     void uninstall() {
/* 320 */       Container parent = getParent();
/* 321 */       if (parent != null) {
/* 322 */         parent.remove(this);
/* 323 */         parent.repaint();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected void paintComponent(Graphics g) {
/* 329 */       Point locationOnScreen = getLocationOnScreen();
/* 330 */       g.translate(-locationOnScreen.x, -locationOnScreen.y);
/*     */       
/* 332 */       g.setColor(Color.red);
/* 333 */       ((Graphics2D)g).draw(SubMenuUsabilityHelper.this.createSafeTriangle());
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\SubMenuUsabilityHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */