/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.SystemInfo;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.Collections;
/*     */ import java.util.IdentityHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.Timer;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import javax.swing.event.EventListenerList;
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
/*     */ class FlatWindowsNativeWindowBorder
/*     */   implements FlatNativeWindowBorder.Provider
/*     */ {
/*  75 */   private final Map<Window, WndProc> windowsMap = Collections.synchronizedMap(new IdentityHashMap<>());
/*  76 */   private final EventListenerList listenerList = new EventListenerList();
/*     */   
/*     */   private Timer fireStateChangedTimer;
/*     */   
/*     */   private boolean colorizationUpToDate;
/*     */   
/*     */   private boolean colorizationColorAffectsBorders;
/*     */   private Color colorizationColor;
/*     */   private int colorizationColorBalance;
/*     */   private static FlatWindowsNativeWindowBorder instance;
/*     */   
/*     */   static FlatNativeWindowBorder.Provider getInstance() {
/*  88 */     if (!SystemInfo.isWindows_10_orLater) {
/*  89 */       return null;
/*     */     }
/*     */     
/*  92 */     if (!SystemInfo.isX86 && !SystemInfo.isX86_64) {
/*  93 */       return null;
/*     */     }
/*     */     
/*  96 */     if (!FlatNativeLibrary.isLoaded()) {
/*  97 */       return null;
/*     */     }
/*     */     
/* 100 */     if (instance == null)
/* 101 */       instance = new FlatWindowsNativeWindowBorder(); 
/* 102 */     return instance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomDecoration(Window window) {
/* 110 */     return this.windowsMap.containsKey(window);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHasCustomDecoration(Window window, boolean hasCustomDecoration) {
/* 120 */     if (hasCustomDecoration) {
/* 121 */       install(window);
/*     */     } else {
/* 123 */       uninstall(window);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void install(Window window) {
/* 128 */     if (!SystemInfo.isWindows_10_orLater) {
/*     */       return;
/*     */     }
/*     */     
/* 132 */     if (!(window instanceof JFrame) && !(window instanceof javax.swing.JDialog)) {
/*     */       return;
/*     */     }
/*     */     
/* 136 */     if ((window instanceof Frame && ((Frame)window).isUndecorated()) || (window instanceof Dialog && ((Dialog)window)
/* 137 */       .isUndecorated())) {
/*     */       return;
/*     */     }
/*     */     
/* 141 */     if (this.windowsMap.containsKey(window)) {
/*     */       return;
/*     */     }
/*     */     
/*     */     try {
/* 146 */       WndProc wndProc = new WndProc(window);
/* 147 */       if (wndProc.hwnd == 0L) {
/*     */         return;
/*     */       }
/* 150 */       this.windowsMap.put(window, wndProc);
/* 151 */     } catch (UnsatisfiedLinkError ex) {
/*     */ 
/*     */ 
/*     */       
/* 155 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void uninstall(Window window) {
/* 160 */     WndProc wndProc = this.windowsMap.remove(window);
/* 161 */     if (wndProc != null) {
/* 162 */       wndProc.uninstall();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTitleBarInfo(Window window, int titleBarHeight, List<Rectangle> hitTestSpots, Rectangle appIconBounds, Rectangle minimizeButtonBounds, Rectangle maximizeButtonBounds, Rectangle closeButtonBounds) {
/* 170 */     WndProc wndProc = this.windowsMap.get(window);
/* 171 */     if (wndProc == null) {
/*     */       return;
/*     */     }
/* 174 */     wndProc.titleBarHeight = titleBarHeight;
/* 175 */     wndProc.hitTestSpots = hitTestSpots.<Rectangle>toArray(new Rectangle[hitTestSpots.size()]);
/* 176 */     wndProc.appIconBounds = cloneRectange(appIconBounds);
/* 177 */     wndProc.minimizeButtonBounds = cloneRectange(minimizeButtonBounds);
/* 178 */     wndProc.maximizeButtonBounds = cloneRectange(maximizeButtonBounds);
/* 179 */     wndProc.closeButtonBounds = cloneRectange(closeButtonBounds);
/*     */   }
/*     */   
/*     */   private static Rectangle cloneRectange(Rectangle rect) {
/* 183 */     return (rect != null) ? new Rectangle(rect) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean showWindow(Window window, int cmd) {
/* 188 */     WndProc wndProc = this.windowsMap.get(window);
/* 189 */     if (wndProc == null) {
/* 190 */       return false;
/*     */     }
/* 192 */     wndProc.showWindow(wndProc.hwnd, cmd);
/* 193 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isColorizationColorAffectsBorders() {
/* 198 */     updateColorization();
/* 199 */     return this.colorizationColorAffectsBorders;
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getColorizationColor() {
/* 204 */     updateColorization();
/* 205 */     return this.colorizationColor;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColorizationColorBalance() {
/* 210 */     updateColorization();
/* 211 */     return this.colorizationColorBalance;
/*     */   }
/*     */   
/*     */   private void updateColorization() {
/* 215 */     if (this.colorizationUpToDate)
/*     */       return; 
/* 217 */     this.colorizationUpToDate = true;
/*     */     
/* 219 */     String subKey = "SOFTWARE\\Microsoft\\Windows\\DWM";
/*     */     
/* 221 */     int value = registryGetIntValue(subKey, "ColorPrevalence", -1);
/* 222 */     this.colorizationColorAffectsBorders = (value > 0);
/*     */     
/* 224 */     value = registryGetIntValue(subKey, "ColorizationColor", -1);
/* 225 */     this.colorizationColor = (value != -1) ? new Color(value) : null;
/*     */     
/* 227 */     this.colorizationColorBalance = registryGetIntValue(subKey, "ColorizationColorBalance", -1);
/*     */   }
/*     */ 
/*     */   
/*     */   private static native int registryGetIntValue(String paramString1, String paramString2, int paramInt);
/*     */   
/*     */   public void addChangeListener(ChangeListener l) {
/* 234 */     this.listenerList.add(ChangeListener.class, l);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeChangeListener(ChangeListener l) {
/* 239 */     this.listenerList.remove(ChangeListener.class, l);
/*     */   }
/*     */   
/*     */   private void fireStateChanged() {
/* 243 */     Object[] listeners = this.listenerList.getListenerList();
/* 244 */     if (listeners.length == 0) {
/*     */       return;
/*     */     }
/* 247 */     ChangeEvent e = new ChangeEvent(this);
/* 248 */     for (int i = 0; i < listeners.length; i += 2) {
/* 249 */       if (listeners[i] == ChangeListener.class) {
/* 250 */         ((ChangeListener)listeners[i + 1]).stateChanged(e);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void fireStateChangedLaterOnce() {
/* 259 */     EventQueue.invokeLater(() -> {
/*     */           if (this.fireStateChangedTimer != null) {
/*     */             this.fireStateChangedTimer.restart();
/*     */             return;
/*     */           } 
/*     */           this.fireStateChangedTimer = new Timer(300, ());
/*     */           this.fireStateChangedTimer.setRepeats(false);
/*     */           this.fireStateChangedTimer.start();
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private class WndProc
/*     */     implements PropertyChangeListener
/*     */   {
/*     */     private static final int HTCLIENT = 1;
/*     */     
/*     */     private static final int HTCAPTION = 2;
/*     */     
/*     */     private static final int HTSYSMENU = 3;
/*     */     
/*     */     private static final int HTMINBUTTON = 8;
/*     */     
/*     */     private static final int HTMAXBUTTON = 9;
/*     */     
/*     */     private static final int HTTOP = 12;
/*     */     
/*     */     private static final int HTCLOSE = 20;
/*     */     
/*     */     private Window window;
/*     */     
/*     */     private final long hwnd;
/*     */     
/*     */     private int titleBarHeight;
/*     */     
/*     */     private Rectangle[] hitTestSpots;
/*     */     
/*     */     private Rectangle appIconBounds;
/*     */     
/*     */     private Rectangle minimizeButtonBounds;
/*     */     private Rectangle maximizeButtonBounds;
/*     */     private Rectangle closeButtonBounds;
/*     */     
/*     */     WndProc(Window window) {
/* 303 */       this.window = window;
/*     */       
/* 305 */       this.hwnd = installImpl(window);
/* 306 */       if (this.hwnd == 0L) {
/*     */         return;
/*     */       }
/*     */       
/* 310 */       updateFrame(this.hwnd, (window instanceof JFrame) ? ((JFrame)window).getExtendedState() : 0);
/*     */ 
/*     */       
/* 313 */       updateWindowBackground();
/* 314 */       window.addPropertyChangeListener("background", this);
/*     */     }
/*     */     
/*     */     void uninstall() {
/* 318 */       this.window.removePropertyChangeListener("background", this);
/*     */       
/* 320 */       uninstallImpl(this.hwnd);
/*     */ 
/*     */       
/* 323 */       this.window = null;
/*     */     }
/*     */ 
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent e) {
/* 328 */       updateWindowBackground();
/*     */     }
/*     */     
/*     */     private void updateWindowBackground() {
/* 332 */       Color bg = this.window.getBackground();
/* 333 */       if (bg != null) {
/* 334 */         setWindowBackground(this.hwnd, bg.getRed(), bg.getGreen(), bg.getBlue());
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int onNcHitTest(int x, int y, boolean isOnResizeBorder) {
/* 346 */       Point pt = scaleDown(x, y);
/* 347 */       int sx = pt.x;
/* 348 */       int sy = pt.y;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 353 */       if (contains(this.appIconBounds, sx, sy)) {
/* 354 */         return 3;
/*     */       }
/*     */ 
/*     */       
/* 358 */       if (contains(this.minimizeButtonBounds, sx, sy)) {
/* 359 */         return 8;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 365 */       if (contains(this.maximizeButtonBounds, sx, sy)) {
/* 366 */         return 9;
/*     */       }
/*     */ 
/*     */       
/* 370 */       if (contains(this.closeButtonBounds, sx, sy)) {
/* 371 */         return 20;
/*     */       }
/* 373 */       boolean isOnTitleBar = (sy < this.titleBarHeight);
/*     */       
/* 375 */       if (isOnTitleBar) {
/*     */ 
/*     */         
/* 378 */         Rectangle[] hitTestSpots2 = this.hitTestSpots;
/* 379 */         for (Rectangle spot : hitTestSpots2) {
/* 380 */           if (spot.contains(sx, sy))
/* 381 */             return 1; 
/*     */         } 
/* 383 */         return isOnResizeBorder ? 12 : 2;
/*     */       } 
/*     */       
/* 386 */       return isOnResizeBorder ? 12 : 1;
/*     */     }
/*     */     
/*     */     private boolean contains(Rectangle rect, int x, int y) {
/* 390 */       return (rect != null && rect.contains(x, y));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private Point scaleDown(int x, int y) {
/* 398 */       GraphicsConfiguration gc = this.window.getGraphicsConfiguration();
/* 399 */       if (gc == null) {
/* 400 */         return new Point(x, y);
/*     */       }
/* 402 */       AffineTransform t = gc.getDefaultTransform();
/* 403 */       return new Point(clipRound(x / t.getScaleX()), clipRound(y / t.getScaleY()));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int clipRound(double value) {
/* 411 */       value -= 0.5D;
/* 412 */       if (value < -2.147483648E9D)
/* 413 */         return Integer.MIN_VALUE; 
/* 414 */       if (value > 2.147483647E9D)
/* 415 */         return Integer.MAX_VALUE; 
/* 416 */       return (int)Math.ceil(value);
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean isFullscreen() {
/* 421 */       GraphicsConfiguration gc = this.window.getGraphicsConfiguration();
/* 422 */       if (gc == null)
/* 423 */         return false; 
/* 424 */       return (gc.getDevice().getFullScreenWindow() == this.window);
/*     */     }
/*     */ 
/*     */     
/*     */     private void fireStateChangedLaterOnce() {
/* 429 */       FlatWindowsNativeWindowBorder.this.fireStateChangedLaterOnce();
/*     */     }
/*     */     
/*     */     private native long installImpl(Window param1Window);
/*     */     
/*     */     private native void uninstallImpl(long param1Long);
/*     */     
/*     */     private native void updateFrame(long param1Long, int param1Int);
/*     */     
/*     */     private native void setWindowBackground(long param1Long, int param1Int1, int param1Int2, int param1Int3);
/*     */     
/*     */     private native void showWindow(long param1Long, int param1Int);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatWindowsNativeWindowBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */