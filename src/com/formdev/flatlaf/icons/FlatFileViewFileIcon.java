/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*    */ import java.awt.BasicStroke;
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
/*    */ 
/*    */ 
/*    */ public class FlatFileViewFileIcon
/*    */   extends FlatAbstractIcon
/*    */ {
/*    */   private Path2D path;
/*    */   
/*    */   public FlatFileViewFileIcon() {
/* 40 */     super(16, 16, UIManager.getColor("Objects.Grey"));
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
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 54 */     g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
/* 55 */     g.setStroke(new BasicStroke(1.0F, 1, 1));
/*    */     
/* 57 */     if (this.path == null) {
/* 58 */       double arc = 1.5D;
/* 59 */       this.path = FlatUIUtils.createPath(false, new double[] { 2.5D, 1.5D + arc, -1.000000000002E12D, 2.5D, 1.5D, 2.5D + arc, 1.5D, 8.8D, 1.5D, 13.5D, 6.2D, 13.5D, 14.5D - arc, -1.000000000002E12D, 13.5D, 14.5D, 13.5D - arc, 14.5D, 2.5D + arc, 14.5D, -1.000000000002E12D, 2.5D, 14.5D, 2.5D, 14.5D - arc, -1.000000000005E12D, -1.000000000001E12D, 8.5D, 2.0D, 8.5D, 6.5D - arc, -1.000000000002E12D, 8.5D, 6.5D, 8.5D + arc, 6.5D, 13.0D, 6.5D });
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
/* 74 */     g.draw(this.path);
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatFileViewFileIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */