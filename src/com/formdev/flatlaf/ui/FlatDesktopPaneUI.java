/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.event.ComponentAdapter;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ContainerEvent;
/*     */ import java.awt.event.ContainerListener;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicDesktopPaneUI;
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
/*     */ public class FlatDesktopPaneUI
/*     */   extends BasicDesktopPaneUI
/*     */ {
/*     */   private LayoutDockListener layoutDockListener;
/*     */   private boolean layoutDockPending;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  48 */     return new FlatDesktopPaneUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  53 */     super.installUI(c);
/*     */     
/*  55 */     layoutDockLaterOnce();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners() {
/*  60 */     super.installListeners();
/*     */     
/*  62 */     this.layoutDockListener = new LayoutDockListener();
/*  63 */     this.desktop.addContainerListener(this.layoutDockListener);
/*  64 */     this.desktop.addComponentListener(this.layoutDockListener);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners() {
/*  69 */     super.uninstallListeners();
/*     */     
/*  71 */     this.desktop.removeContainerListener(this.layoutDockListener);
/*  72 */     this.desktop.removeComponentListener(this.layoutDockListener);
/*  73 */     this.layoutDockListener = null;
/*     */   }
/*     */   
/*     */   private void layoutDockLaterOnce() {
/*  77 */     if (this.layoutDockPending)
/*     */       return; 
/*  79 */     this.layoutDockPending = true;
/*     */     
/*  81 */     EventQueue.invokeLater(() -> {
/*     */           this.layoutDockPending = false;
/*     */           if (this.desktop != null)
/*     */             layoutDock(); 
/*     */         });
/*     */   }
/*     */   
/*     */   protected void layoutDock() {
/*  89 */     Dimension desktopSize = this.desktop.getSize();
/*  90 */     int x = 0;
/*  91 */     int y = desktopSize.height;
/*  92 */     int rowHeight = 0;
/*     */     
/*  94 */     for (Component c : this.desktop.getComponents()) {
/*  95 */       if (c instanceof JInternalFrame.JDesktopIcon) {
/*     */ 
/*     */         
/*  98 */         JInternalFrame.JDesktopIcon icon = (JInternalFrame.JDesktopIcon)c;
/*  99 */         Dimension iconSize = icon.getPreferredSize();
/*     */         
/* 101 */         if (x + iconSize.width > desktopSize.width) {
/*     */           
/* 103 */           x = 0;
/* 104 */           y -= rowHeight;
/* 105 */           rowHeight = 0;
/*     */         } 
/*     */         
/* 108 */         icon.setLocation(x, y - iconSize.height);
/*     */         
/* 110 */         x += iconSize.width;
/* 111 */         rowHeight = Math.max(iconSize.height, rowHeight);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private class LayoutDockListener
/*     */     extends ComponentAdapter
/*     */     implements ContainerListener
/*     */   {
/*     */     private LayoutDockListener() {}
/*     */     
/*     */     public void componentAdded(ContainerEvent e) {
/* 123 */       FlatDesktopPaneUI.this.layoutDockLaterOnce();
/*     */     }
/*     */ 
/*     */     
/*     */     public void componentRemoved(ContainerEvent e) {
/* 128 */       FlatDesktopPaneUI.this.layoutDockLaterOnce();
/*     */     }
/*     */ 
/*     */     
/*     */     public void componentResized(ComponentEvent e) {
/* 133 */       FlatDesktopPaneUI.this.layoutDockLaterOnce();
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatDesktopPaneUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */