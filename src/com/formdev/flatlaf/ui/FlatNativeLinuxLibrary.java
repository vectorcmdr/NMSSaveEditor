/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.SystemInfo;
/*     */ import java.awt.Point;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
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
/*     */ class FlatNativeLinuxLibrary
/*     */ {
/*     */   static final int MOVE = 8;
/*     */   private static Boolean isXWindowSystem;
/*     */   
/*     */   static boolean isLoaded() {
/*  39 */     return (SystemInfo.isLinux && FlatNativeLibrary.isLoaded());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isXWindowSystem() {
/*  49 */     if (isXWindowSystem == null)
/*  50 */       isXWindowSystem = Boolean.valueOf(Toolkit.getDefaultToolkit().getClass().getName().endsWith(".XToolkit")); 
/*  51 */     return isXWindowSystem.booleanValue();
/*     */   }
/*     */   
/*     */   static boolean isWMUtilsSupported(Window window) {
/*  55 */     return (hasCustomDecoration(window) && isXWindowSystem() && isLoaded());
/*     */   }
/*     */   
/*     */   static boolean moveOrResizeWindow(Window window, MouseEvent e, int direction) {
/*  59 */     Point pt = scale(window, e.getLocationOnScreen());
/*  60 */     return xMoveOrResizeWindow(window, pt.x, pt.y, direction);
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
/*     */   static boolean showWindowMenu(Window window, MouseEvent e) {
/*  75 */     Point pt = scale(window, e.getLocationOnScreen());
/*  76 */     return xShowWindowMenu(window, pt.x, pt.y);
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
/*     */   private static Point scale(Window window, Point pt) {
/*  91 */     AffineTransform transform = window.getGraphicsConfiguration().getDefaultTransform();
/*  92 */     int x = (int)Math.round(pt.x * transform.getScaleX());
/*  93 */     int y = (int)Math.round(pt.y * transform.getScaleY());
/*  94 */     return new Point(x, y);
/*     */   }
/*     */   
/*     */   private static native boolean xMoveOrResizeWindow(Window paramWindow, int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */   private static native boolean xShowWindowMenu(Window paramWindow, int paramInt1, int paramInt2);
/*     */   
/*     */   private static boolean hasCustomDecoration(Window window) {
/* 102 */     return ((window instanceof JFrame && JFrame.isDefaultLookAndFeelDecorated() && ((JFrame)window).isUndecorated()) || (window instanceof JDialog && 
/* 103 */       JDialog.isDefaultLookAndFeelDecorated() && ((JDialog)window).isUndecorated()));
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatNativeLinuxLibrary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */