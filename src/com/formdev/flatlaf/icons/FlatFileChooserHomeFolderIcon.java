/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.RenderingHints;
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
/*    */ public class FlatFileChooserHomeFolderIcon
/*    */   extends FlatAbstractIcon
/*    */ {
/*    */   public FlatFileChooserHomeFolderIcon() {
/* 37 */     super(16, 16, UIManager.getColor("Actions.Grey"));
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
/* 52 */     g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
/* 53 */     g.setStroke(new BasicStroke(1.0F, 1, 1));
/*    */     
/* 55 */     g.draw(FlatUIUtils.createPath(false, new double[] { 6.5D, 13.0D, 6.5D, 9.5D, 9.5D, 9.5D, 9.5D, 13.0D }));
/* 56 */     g.draw(FlatUIUtils.createPath(false, new double[] { 3.5D, 6.5D, 3.5D, 12.5D, -1.000000000002E12D, 3.5D, 13.5D, 4.5D, 13.5D, 11.5D, 13.5D, -1.000000000002E12D, 12.5D, 13.5D, 12.5D, 12.5D, 12.5D, 6.5D }));
/*    */ 
/*    */ 
/*    */     
/* 60 */     g.draw(FlatUIUtils.createPath(false, new double[] { 1.5D, 8.5D, 8.0D, 2.0D, 14.5D, 8.5D }));
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatFileChooserHomeFolderIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */