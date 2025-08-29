/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatTreeUI;
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
/*    */ public class FlatTreeClosedIcon
/*    */   extends FlatAbstractIcon
/*    */ {
/*    */   private Path2D path;
/*    */   
/*    */   public FlatTreeClosedIcon() {
/* 38 */     super(16, 16, UIManager.getColor("Tree.icon.closedColor"));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 43 */     FlatTreeCollapsedIcon.setStyleColorFromTreeUI(c, g, ui -> ui.iconClosedColor);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 51 */     g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
/*    */     
/* 53 */     if (this.path == null)
/* 54 */       this.path = FlatFileViewDirectoryIcon.createFolderPath(); 
/* 55 */     g.draw(this.path);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatTreeClosedIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */