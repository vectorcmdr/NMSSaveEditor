/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.FlatSystemProperties;
/*     */ import com.formdev.flatlaf.util.SystemInfo;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Window;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.List;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JRootPane;
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
/*     */ public class FlatNativeWindowBorder
/*     */ {
/*  49 */   private static final boolean canUseWindowDecorations = (SystemInfo.isWindows_10_orLater && (SystemInfo.isWindows_11_orLater || 
/*     */     
/*  51 */     !FlatSystemProperties.getBoolean("sun.java2d.opengl", false)) && !SystemInfo.isProjector && !SystemInfo.isWebswing && !SystemInfo.isWinPE && 
/*     */ 
/*     */ 
/*     */     
/*  55 */     FlatSystemProperties.getBoolean("flatlaf.useWindowDecorations", true));
/*     */ 
/*     */   
/*  58 */   private static final boolean canUseJBRCustomDecorations = (canUseWindowDecorations && SystemInfo.isJetBrainsJVM_11_orLater && 
/*     */ 
/*     */     
/*  61 */     FlatSystemProperties.getBoolean("flatlaf.useJetBrainsCustomDecorations", false));
/*     */   
/*     */   private static Boolean supported;
/*     */   private static Provider nativeProvider;
/*     */   
/*     */   public static boolean isSupported() {
/*  67 */     if (canUseJBRCustomDecorations) {
/*  68 */       return JBRCustomDecorations.isSupported();
/*     */     }
/*  70 */     initialize();
/*  71 */     return supported.booleanValue();
/*     */   }
/*     */   
/*     */   static Object install(JRootPane rootPane) {
/*  75 */     if (canUseJBRCustomDecorations) {
/*  76 */       return JBRCustomDecorations.install(rootPane);
/*     */     }
/*  78 */     if (!isSupported()) {
/*  79 */       return null;
/*     */     }
/*     */     
/*  82 */     Container parent = rootPane.getParent();
/*  83 */     if (parent != null && !(parent instanceof Window)) {
/*  84 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     if (parent instanceof Window && parent.isDisplayable()) {
/*  94 */       install((Window)parent);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     PropertyChangeListener ancestorListener = e -> {
/*     */         Object newValue = e.getNewValue(); if (newValue instanceof Window) {
/*     */           install((Window)newValue);
/*     */         } else if (newValue == null && e.getOldValue() instanceof Window) {
/*     */           uninstall((Window)e.getOldValue());
/*     */         } 
/*     */       };
/* 107 */     rootPane.addPropertyChangeListener("ancestor", ancestorListener);
/* 108 */     return ancestorListener;
/*     */   }
/*     */   
/*     */   static void install(Window window) {
/* 112 */     if (hasCustomDecoration(window)) {
/*     */       return;
/*     */     }
/*     */     
/* 116 */     if (UIManager.getLookAndFeel().getSupportsWindowDecorations()) {
/*     */       return;
/*     */     }
/* 119 */     if (window instanceof JFrame) {
/* 120 */       JFrame frame = (JFrame)window;
/* 121 */       JRootPane rootPane = frame.getRootPane();
/*     */ 
/*     */       
/* 124 */       if (!useWindowDecorations(rootPane)) {
/*     */         return;
/*     */       }
/*     */       
/* 128 */       if (frame.isUndecorated()) {
/*     */         return;
/*     */       }
/*     */       
/* 132 */       setHasCustomDecoration(frame, true);
/*     */ 
/*     */       
/* 135 */       if (!hasCustomDecoration(frame)) {
/*     */         return;
/*     */       }
/*     */       
/* 139 */       rootPane.setWindowDecorationStyle(1);
/*     */     }
/* 141 */     else if (window instanceof JDialog) {
/* 142 */       JDialog dialog = (JDialog)window;
/* 143 */       JRootPane rootPane = dialog.getRootPane();
/*     */ 
/*     */       
/* 146 */       if (!useWindowDecorations(rootPane)) {
/*     */         return;
/*     */       }
/*     */       
/* 150 */       if (dialog.isUndecorated()) {
/*     */         return;
/*     */       }
/*     */       
/* 154 */       setHasCustomDecoration(dialog, true);
/*     */ 
/*     */       
/* 157 */       if (!hasCustomDecoration(dialog)) {
/*     */         return;
/*     */       }
/*     */       
/* 161 */       rootPane.setWindowDecorationStyle(2);
/*     */     } 
/*     */   }
/*     */   
/*     */   static void uninstall(JRootPane rootPane, Object data) {
/* 166 */     if (canUseJBRCustomDecorations) {
/* 167 */       JBRCustomDecorations.uninstall(rootPane, data);
/*     */       
/*     */       return;
/*     */     } 
/* 171 */     if (!isSupported()) {
/*     */       return;
/*     */     }
/*     */     
/* 175 */     if (data instanceof PropertyChangeListener) {
/* 176 */       rootPane.removePropertyChangeListener("ancestor", (PropertyChangeListener)data);
/*     */     }
/*     */     
/* 179 */     if (UIManager.getLookAndFeel() instanceof com.formdev.flatlaf.FlatLaf && useWindowDecorations(rootPane)) {
/*     */       return;
/*     */     }
/*     */     
/* 183 */     Container parent = rootPane.getParent();
/* 184 */     if (parent instanceof Window)
/* 185 */       uninstall((Window)parent); 
/*     */   }
/*     */   
/*     */   private static void uninstall(Window window) {
/* 189 */     if (!hasCustomDecoration(window)) {
/*     */       return;
/*     */     }
/*     */     
/* 193 */     setHasCustomDecoration(window, false);
/*     */     
/* 195 */     if (window instanceof JFrame) {
/* 196 */       JFrame frame = (JFrame)window;
/*     */ 
/*     */       
/* 199 */       frame.getRootPane().setWindowDecorationStyle(0);
/*     */     }
/* 201 */     else if (window instanceof JDialog) {
/* 202 */       JDialog dialog = (JDialog)window;
/*     */ 
/*     */       
/* 205 */       dialog.getRootPane().setWindowDecorationStyle(0);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean useWindowDecorations(JRootPane rootPane) {
/* 210 */     return FlatUIUtils.getBoolean(rootPane, "flatlaf.useWindowDecorations", "JRootPane.useWindowDecorations", "TitlePane.useWindowDecorations", false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasCustomDecoration(Window window) {
/* 218 */     if (canUseJBRCustomDecorations) {
/* 219 */       return JBRCustomDecorations.hasCustomDecoration(window);
/*     */     }
/* 221 */     if (!isSupported()) {
/* 222 */       return false;
/*     */     }
/* 224 */     return nativeProvider.hasCustomDecoration(window);
/*     */   }
/*     */   
/*     */   public static void setHasCustomDecoration(Window window, boolean hasCustomDecoration) {
/* 228 */     if (canUseJBRCustomDecorations) {
/* 229 */       JBRCustomDecorations.setHasCustomDecoration(window, hasCustomDecoration);
/*     */       
/*     */       return;
/*     */     } 
/* 233 */     if (!isSupported()) {
/*     */       return;
/*     */     }
/* 236 */     nativeProvider.setHasCustomDecoration(window, hasCustomDecoration);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void setTitleBarHeightAndHitTestSpots(Window window, int titleBarHeight, List<Rectangle> hitTestSpots, Rectangle appIconBounds, Rectangle minimizeButtonBounds, Rectangle maximizeButtonBounds, Rectangle closeButtonBounds) {
/* 243 */     if (canUseJBRCustomDecorations) {
/* 244 */       JBRCustomDecorations.setTitleBarHeightAndHitTestSpots(window, titleBarHeight, hitTestSpots);
/*     */       
/*     */       return;
/*     */     } 
/* 248 */     if (!isSupported()) {
/*     */       return;
/*     */     }
/* 251 */     nativeProvider.updateTitleBarInfo(window, titleBarHeight, hitTestSpots, appIconBounds, minimizeButtonBounds, maximizeButtonBounds, closeButtonBounds);
/*     */   }
/*     */ 
/*     */   
/*     */   static boolean showWindow(Window window, int cmd) {
/* 256 */     if (canUseJBRCustomDecorations || !isSupported()) {
/* 257 */       return false;
/*     */     }
/* 259 */     return nativeProvider.showWindow(window, cmd);
/*     */   }
/*     */   
/*     */   private static void initialize() {
/* 263 */     if (supported != null)
/*     */       return; 
/* 265 */     supported = Boolean.valueOf(false);
/*     */     
/* 267 */     if (!canUseWindowDecorations) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 276 */       setNativeProvider(FlatWindowsNativeWindowBorder.getInstance());
/* 277 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setNativeProvider(Provider provider) {
/* 284 */     if (nativeProvider != null) {
/* 285 */       throw new IllegalStateException();
/*     */     }
/* 287 */     nativeProvider = provider;
/* 288 */     supported = Boolean.valueOf((nativeProvider != null));
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
/*     */   static class WindowTopBorder
/*     */     extends JBRCustomDecorations.JBRWindowTopBorder
/*     */   {
/*     */     private static WindowTopBorder instance;
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
/*     */     static JBRCustomDecorations.JBRWindowTopBorder getInstance() {
/* 328 */       if (FlatNativeWindowBorder.canUseJBRCustomDecorations) {
/* 329 */         return JBRCustomDecorations.JBRWindowTopBorder.getInstance();
/*     */       }
/* 331 */       if (instance == null)
/* 332 */         instance = new WindowTopBorder(); 
/* 333 */       return instance;
/*     */     }
/*     */ 
/*     */     
/*     */     void installListeners() {
/* 338 */       FlatNativeWindowBorder.nativeProvider.addChangeListener(e -> {
/*     */             update();
/*     */             for (Window window : Window.getWindows()) {
/*     */               if (window.isDisplayable()) {
/*     */                 window.repaint(0, 0, window.getWidth(), 1);
/*     */               }
/*     */             } 
/*     */           });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     boolean isColorizationColorAffectsBorders() {
/* 351 */       return FlatNativeWindowBorder.nativeProvider.isColorizationColorAffectsBorders();
/*     */     }
/*     */ 
/*     */     
/*     */     Color getColorizationColor() {
/* 356 */       return FlatNativeWindowBorder.nativeProvider.getColorizationColor();
/*     */     }
/*     */ 
/*     */     
/*     */     int getColorizationColorBalance() {
/* 361 */       return FlatNativeWindowBorder.nativeProvider.getColorizationColorBalance();
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface Provider {
/*     */     public static final int SW_MAXIMIZE = 3;
/*     */     public static final int SW_MINIMIZE = 6;
/*     */     public static final int SW_RESTORE = 9;
/*     */     
/*     */     boolean hasCustomDecoration(Window param1Window);
/*     */     
/*     */     void setHasCustomDecoration(Window param1Window, boolean param1Boolean);
/*     */     
/*     */     void updateTitleBarInfo(Window param1Window, int param1Int, List<Rectangle> param1List, Rectangle param1Rectangle1, Rectangle param1Rectangle2, Rectangle param1Rectangle3, Rectangle param1Rectangle4);
/*     */     
/*     */     boolean showWindow(Window param1Window, int param1Int);
/*     */     
/*     */     boolean isColorizationColorAffectsBorders();
/*     */     
/*     */     Color getColorizationColor();
/*     */     
/*     */     int getColorizationColorBalance();
/*     */     
/*     */     void addChangeListener(ChangeListener param1ChangeListener);
/*     */     
/*     */     void removeChangeListener(ChangeListener param1ChangeListener);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatNativeWindowBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */