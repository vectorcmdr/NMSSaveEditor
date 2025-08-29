/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.FlatLaf;
/*     */ import com.formdev.flatlaf.util.HiDPIUtils;
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.SystemInfo;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.HierarchyEvent;
/*     */ import java.awt.event.HierarchyListener;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.BorderUIResource;
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
/*     */ public class JBRCustomDecorations
/*     */ {
/*     */   private static Boolean supported;
/*     */   private static Method Window_hasCustomDecoration;
/*     */   private static Method Window_setHasCustomDecoration;
/*     */   private static Method WWindowPeer_setCustomDecorationTitleBarHeight;
/*     */   private static Method WWindowPeer_setCustomDecorationHitTestSpots;
/*     */   private static Method AWTAccessor_getComponentAccessor;
/*     */   private static Method AWTAccessor_ComponentAccessor_getPeer;
/*     */   
/*     */   public static boolean isSupported() {
/*  64 */     initialize();
/*  65 */     return supported.booleanValue();
/*     */   }
/*     */   
/*     */   static Object install(final JRootPane rootPane) {
/*  69 */     if (!isSupported()) {
/*  70 */       return null;
/*     */     }
/*     */     
/*  73 */     Container parent = rootPane.getParent();
/*  74 */     if (parent != null) {
/*  75 */       if (parent instanceof Window)
/*  76 */         FlatNativeWindowBorder.install((Window)parent); 
/*  77 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  84 */     HierarchyListener addListener = new HierarchyListener()
/*     */       {
/*     */         public void hierarchyChanged(HierarchyEvent e) {
/*  87 */           if (e.getChanged() != rootPane || (e.getChangeFlags() & 0x1L) == 0L) {
/*     */             return;
/*     */           }
/*  90 */           Container parent = e.getChangedParent();
/*  91 */           if (parent instanceof Window) {
/*  92 */             FlatNativeWindowBorder.install((Window)parent);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*  97 */           EventQueue.invokeLater(() -> rootPane.removeHierarchyListener(this));
/*     */         }
/*     */       };
/*     */ 
/*     */     
/* 102 */     rootPane.addHierarchyListener(addListener);
/* 103 */     return addListener;
/*     */   }
/*     */ 
/*     */   
/*     */   static void uninstall(JRootPane rootPane, Object data) {
/* 108 */     if (data instanceof HierarchyListener) {
/* 109 */       rootPane.removeHierarchyListener((HierarchyListener)data);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 114 */     Container parent = rootPane.getParent();
/* 115 */     if (parent instanceof Window)
/* 116 */       setHasCustomDecoration((Window)parent, false); 
/*     */   }
/*     */   
/*     */   static boolean hasCustomDecoration(Window window) {
/* 120 */     if (!isSupported()) {
/* 121 */       return false;
/*     */     }
/*     */     try {
/* 124 */       return ((Boolean)Window_hasCustomDecoration.invoke(window, new Object[0])).booleanValue();
/* 125 */     } catch (Exception ex) {
/* 126 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/* 127 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   static void setHasCustomDecoration(Window window, boolean hasCustomDecoration) {
/* 132 */     if (!isSupported()) {
/*     */       return;
/*     */     }
/*     */     try {
/* 136 */       if (hasCustomDecoration)
/* 137 */       { Window_setHasCustomDecoration.invoke(window, new Object[0]); }
/*     */       else
/* 139 */       { setTitleBarHeightAndHitTestSpots(window, 4, Collections.emptyList()); } 
/* 140 */     } catch (Exception ex) {
/* 141 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   static void setTitleBarHeightAndHitTestSpots(Window window, int titleBarHeight, List<Rectangle> hitTestSpots) {
/* 146 */     if (!isSupported()) {
/*     */       return;
/*     */     }
/*     */     try {
/* 150 */       Object compAccessor = AWTAccessor_getComponentAccessor.invoke((Object)null, new Object[0]);
/* 151 */       Object peer = AWTAccessor_ComponentAccessor_getPeer.invoke(compAccessor, new Object[] { window });
/* 152 */       WWindowPeer_setCustomDecorationTitleBarHeight.invoke(peer, new Object[] { Integer.valueOf(titleBarHeight) });
/* 153 */       WWindowPeer_setCustomDecorationHitTestSpots.invoke(peer, new Object[] { hitTestSpots });
/* 154 */     } catch (Exception ex) {
/* 155 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void initialize() {
/* 160 */     if (supported != null)
/*     */       return; 
/* 162 */     supported = Boolean.valueOf(false);
/*     */ 
/*     */     
/* 165 */     if (!SystemInfo.isJetBrainsJVM_11_orLater || !SystemInfo.isWindows_10_orLater) {
/*     */       return;
/*     */     }
/*     */     try {
/* 169 */       Class<?> awtAcessorClass = Class.forName("sun.awt.AWTAccessor");
/* 170 */       Class<?> compAccessorClass = Class.forName("sun.awt.AWTAccessor$ComponentAccessor");
/* 171 */       AWTAccessor_getComponentAccessor = awtAcessorClass.getDeclaredMethod("getComponentAccessor", new Class[0]);
/* 172 */       AWTAccessor_ComponentAccessor_getPeer = compAccessorClass.getDeclaredMethod("getPeer", new Class[] { Component.class });
/*     */       
/* 174 */       Class<?> peerClass = Class.forName("sun.awt.windows.WWindowPeer");
/* 175 */       WWindowPeer_setCustomDecorationTitleBarHeight = peerClass.getDeclaredMethod("setCustomDecorationTitleBarHeight", new Class[] { int.class });
/* 176 */       WWindowPeer_setCustomDecorationHitTestSpots = peerClass.getDeclaredMethod("setCustomDecorationHitTestSpots", new Class[] { List.class });
/* 177 */       WWindowPeer_setCustomDecorationTitleBarHeight.setAccessible(true);
/* 178 */       WWindowPeer_setCustomDecorationHitTestSpots.setAccessible(true);
/*     */       
/* 180 */       Window_hasCustomDecoration = Window.class.getDeclaredMethod("hasCustomDecoration", new Class[0]);
/* 181 */       Window_setHasCustomDecoration = Window.class.getDeclaredMethod("setHasCustomDecoration", new Class[0]);
/* 182 */       Window_hasCustomDecoration.setAccessible(true);
/* 183 */       Window_setHasCustomDecoration.setAccessible(true);
/*     */       
/* 185 */       supported = Boolean.valueOf(true);
/* 186 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class JBRWindowTopBorder
/*     */     extends BorderUIResource.EmptyBorderUIResource
/*     */   {
/*     */     private static JBRWindowTopBorder instance;
/*     */ 
/*     */     
/* 198 */     private final Color activeLightColor = new Color(7368816);
/* 199 */     private final Color activeDarkColor = new Color(2960943);
/* 200 */     private final Color inactiveLightColor = new Color(11184810);
/* 201 */     private final Color inactiveDarkColor = new Color(4803147);
/*     */     
/*     */     private boolean colorizationAffectsBorders;
/*     */     private Color activeColor;
/*     */     
/*     */     static JBRWindowTopBorder getInstance() {
/* 207 */       if (instance == null)
/* 208 */         instance = new JBRWindowTopBorder(); 
/* 209 */       return instance;
/*     */     }
/*     */     
/*     */     JBRWindowTopBorder() {
/* 213 */       super(1, 0, 0, 0);
/*     */       
/* 215 */       update();
/* 216 */       installListeners();
/*     */     }
/*     */     
/*     */     void update() {
/* 220 */       this.colorizationAffectsBorders = isColorizationColorAffectsBorders();
/* 221 */       this.activeColor = calculateActiveBorderColor();
/*     */     }
/*     */     
/*     */     void installListeners() {
/* 225 */       Toolkit toolkit = Toolkit.getDefaultToolkit();
/* 226 */       toolkit.addPropertyChangeListener("win.dwm.colorizationColor.affects.borders", e -> {
/*     */             this.colorizationAffectsBorders = isColorizationColorAffectsBorders();
/*     */             
/*     */             this.activeColor = calculateActiveBorderColor();
/*     */           });
/* 231 */       PropertyChangeListener l = e -> this.activeColor = calculateActiveBorderColor();
/*     */ 
/*     */       
/* 234 */       toolkit.addPropertyChangeListener("win.dwm.colorizationColor", l);
/* 235 */       toolkit.addPropertyChangeListener("win.dwm.colorizationColorBalance", l);
/* 236 */       toolkit.addPropertyChangeListener("win.frame.activeBorderColor", l);
/*     */     }
/*     */     
/*     */     boolean isColorizationColorAffectsBorders() {
/* 240 */       Object value = Toolkit.getDefaultToolkit().getDesktopProperty("win.dwm.colorizationColor.affects.borders");
/* 241 */       return (value instanceof Boolean) ? ((Boolean)value).booleanValue() : true;
/*     */     }
/*     */     
/*     */     Color getColorizationColor() {
/* 245 */       return (Color)Toolkit.getDefaultToolkit().getDesktopProperty("win.dwm.colorizationColor");
/*     */     }
/*     */     
/*     */     int getColorizationColorBalance() {
/* 249 */       Object value = Toolkit.getDefaultToolkit().getDesktopProperty("win.dwm.colorizationColorBalance");
/* 250 */       return (value instanceof Integer) ? ((Integer)value).intValue() : -1;
/*     */     }
/*     */     
/*     */     private Color calculateActiveBorderColor() {
/* 254 */       if (!this.colorizationAffectsBorders) {
/* 255 */         return null;
/*     */       }
/* 257 */       Color colorizationColor = getColorizationColor();
/* 258 */       if (colorizationColor != null) {
/* 259 */         int colorizationColorBalance = getColorizationColorBalance();
/* 260 */         if (colorizationColorBalance < 0 || colorizationColorBalance > 100) {
/* 261 */           colorizationColorBalance = 100;
/*     */         }
/* 263 */         if (colorizationColorBalance == 0)
/* 264 */           return new Color(14277081); 
/* 265 */         if (colorizationColorBalance == 100) {
/* 266 */           return colorizationColor;
/*     */         }
/* 268 */         float alpha = colorizationColorBalance / 100.0F;
/* 269 */         float remainder = 1.0F - alpha;
/* 270 */         int r = Math.round(colorizationColor.getRed() * alpha + 217.0F * remainder);
/* 271 */         int g = Math.round(colorizationColor.getGreen() * alpha + 217.0F * remainder);
/* 272 */         int b = Math.round(colorizationColor.getBlue() * alpha + 217.0F * remainder);
/*     */ 
/*     */         
/* 275 */         r = Math.min(Math.max(r, 0), 255);
/* 276 */         g = Math.min(Math.max(g, 0), 255);
/* 277 */         b = Math.min(Math.max(b, 0), 255);
/*     */         
/* 279 */         return new Color(r, g, b);
/*     */       } 
/*     */       
/* 282 */       Color activeBorderColor = (Color)Toolkit.getDefaultToolkit().getDesktopProperty("win.frame.activeBorderColor");
/* 283 */       return (activeBorderColor != null) ? activeBorderColor : UIManager.getColor("MenuBar.borderColor");
/*     */     }
/*     */ 
/*     */     
/*     */     public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 288 */       Window window = SwingUtilities.windowForComponent(c);
/* 289 */       boolean active = (window != null && window.isActive());
/* 290 */       boolean dark = FlatLaf.isLafDark();
/*     */       
/* 292 */       g.setColor(active ? (
/* 293 */           (this.activeColor != null) ? this.activeColor : (dark ? this.activeDarkColor : this.activeLightColor)) : (
/* 294 */           dark ? this.inactiveDarkColor : this.inactiveLightColor));
/* 295 */       HiDPIUtils.paintAtScale1x((Graphics2D)g, x, y, width, height, this::paintImpl);
/*     */     }
/*     */     
/*     */     private void paintImpl(Graphics2D g, int x, int y, int width, int height, double scaleFactor) {
/* 299 */       g.fillRect(x, y, width, 1);
/*     */     }
/*     */     
/*     */     void repaintBorder(Component c) {
/* 303 */       c.repaint(0, 0, c.getWidth(), 1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\JBRCustomDecorations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */