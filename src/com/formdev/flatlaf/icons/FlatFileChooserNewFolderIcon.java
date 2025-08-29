/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.geom.Line2D;
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
/*    */ 
/*    */ 
/*    */ public class FlatFileChooserNewFolderIcon
/*    */   extends FlatAbstractIcon
/*    */ {
/* 37 */   private final Color greenColor = UIManager.getColor("Actions.Green");
/*    */   
/*    */   public FlatFileChooserNewFolderIcon() {
/* 40 */     super(16, 16, UIManager.getColor("Actions.Grey"));
/*    */   }
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
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 55 */     g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
/* 56 */     g.setStroke(new BasicStroke(1.0F, 1, 1));
/*    */     
/* 58 */     g.draw(FlatFileViewDirectoryIcon.createFolderPath());
/*    */     
/* 60 */     g.setColor(this.greenColor);
/* 61 */     g.draw(new Line2D.Float(5.5F, 9.0F, 10.5F, 9.0F));
/* 62 */     g.draw(new Line2D.Float(8.0F, 6.5F, 8.0F, 11.5F));
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatFileChooserNewFolderIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */