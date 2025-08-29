/*    */ package com.formdev.flatlaf.ui;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.function.Supplier;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JViewport;
/*    */ import javax.swing.plaf.ComponentUI;
/*    */ import javax.swing.plaf.basic.BasicViewportUI;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FlatViewportUI
/*    */   extends BasicViewportUI
/*    */ {
/*    */   public static ComponentUI createUI(JComponent c) {
/* 42 */     return FlatUIUtils.createSharedUI(FlatViewportUI.class, FlatViewportUI::new);
/*    */   }
/*    */ 
/*    */   
/*    */   public void paint(Graphics g, JComponent c) {
/* 47 */     super.paint(g, c);
/*    */     
/* 49 */     Component view = ((JViewport)c).getView();
/* 50 */     if (view instanceof JComponent)
/*    */       try {
/* 52 */         Method m = view.getClass().getMethod("getUI", new Class[0]);
/* 53 */         Object ui = m.invoke(view, new Object[0]);
/* 54 */         if (ui instanceof ViewportPainter)
/* 55 */           ((ViewportPainter)ui).paintViewport(g, (JComponent)view, (JViewport)c); 
/* 56 */       } catch (Exception exception) {} 
/*    */   }
/*    */   
/*    */   public static interface ViewportPainter {
/*    */     void paintViewport(Graphics param1Graphics, JComponent param1JComponent, JViewport param1JViewport);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatViewportUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */