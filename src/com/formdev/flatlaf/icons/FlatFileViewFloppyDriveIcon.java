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
/*    */ public class FlatFileViewFloppyDriveIcon
/*    */   extends FlatAbstractIcon
/*    */ {
/*    */   public FlatFileViewFloppyDriveIcon() {
/* 37 */     super(16, 16, UIManager.getColor("Objects.Grey"));
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
/* 55 */     g.draw(FlatUIUtils.createPath(new double[] { 3.5D, 2.5D, 11.5D, 2.5D, 11.5D, 2.5D, 13.5D, 4.5D, 13.5D, 12.5D, -1.000000000002E12D, 13.5D, 13.5D, 12.5D, 13.5D, 3.5D, 13.5D, -1.000000000002E12D, 2.5D, 13.5D, 2.5D, 12.5D, 2.5D, 3.5D, -1.000000000002E12D, 2.5D, 2.5D, 3.5D, 2.5D }));
/*    */ 
/*    */ 
/*    */     
/* 59 */     g.draw(FlatUIUtils.createPath(false, new double[] { 4.5D, 13.0D, 4.5D, 9.5D, 11.5D, 9.5D, 11.5D, 13.0D }));
/* 60 */     g.draw(FlatUIUtils.createPath(false, new double[] { 5.5D, 3.0D, 5.5D, 5.5D, 10.5D, 5.5D, 10.5D, 3.0D }));
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatFileViewFloppyDriveIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */