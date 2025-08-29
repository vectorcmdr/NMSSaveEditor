/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatTreeUI;
/*    */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.geom.Path2D;
/*    */ import javax.swing.UIManager;
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
/*    */ public class FlatTreeOpenIcon
/*    */   extends FlatAbstractIcon
/*    */ {
/*    */   private Path2D path;
/*    */   
/*    */   public FlatTreeOpenIcon() {
/* 40 */     super(16, 16, UIManager.getColor("Tree.icon.openColor"));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 45 */     FlatTreeCollapsedIcon.setStyleColorFromTreeUI(c, g, ui -> ui.iconOpenColor);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 53 */     g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
/* 54 */     g.setStroke(new BasicStroke(1.0F, 0, 0));
/*    */     
/* 56 */     if (this.path == null) {
/* 57 */       double arc = 1.5D;
/* 58 */       double arc2 = 0.5D;
/* 59 */       this.path = FlatUIUtils.createPath(false, new double[] { 2.0D, 13.5D, -1.000000000004E12D, 4.5D, 7.5D, arc, -1.000000000004E12D, 15.5D, 7.5D, arc2, -1.000000000004E12D, 13.0D, 13.5D, arc, 1.5D + arc, 13.5D, -1.000000000002E12D, 1.5D, 13.5D, 1.5D, 13.5D - arc, 1.5D, 2.5D + arc, -1.000000000002E12D, 1.5D, 2.5D, 1.5D + arc, 2.5D, 6.5D - arc2, 2.5D, -1.000000000002E12D, 6.5D, 2.5D, 6.5D + arc2, 2.5D + arc2, 8.5D, 4.5D, 13.5D - arc, 4.5D, -1.000000000002E12D, 13.5D, 4.5D, 13.5D, 4.5D + arc, 13.5D, 6.5D });
/*    */     } 
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
/* 81 */     g.draw(this.path);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatTreeOpenIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */