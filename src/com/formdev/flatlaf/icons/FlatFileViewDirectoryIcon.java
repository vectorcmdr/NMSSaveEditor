/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatUIUtils;
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
/*    */ public class FlatFileViewDirectoryIcon
/*    */   extends FlatAbstractIcon
/*    */ {
/*    */   private Path2D path;
/*    */   
/*    */   public FlatFileViewDirectoryIcon() {
/* 39 */     super(16, 16, UIManager.getColor("Objects.Grey"));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 50 */     g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
/*    */     
/* 52 */     if (this.path == null)
/* 53 */       this.path = createFolderPath(); 
/* 54 */     g.draw(this.path);
/*    */   }
/*    */   
/*    */   static Path2D createFolderPath() {
/* 58 */     double arc = 1.5D;
/* 59 */     double arc2 = 0.5D;
/* 60 */     return FlatUIUtils.createPath(new double[] { 14.5D, 13.5D - arc, -1.000000000002E12D, 14.5D, 13.5D, 14.5D - arc, 13.5D, 1.5D + arc, 13.5D, -1.000000000002E12D, 1.5D, 13.5D, 1.5D, 13.5D - arc, 1.5D, 2.5D + arc, -1.000000000002E12D, 1.5D, 2.5D, 1.5D + arc, 2.5D, 6.5D - arc2, 2.5D, -1.000000000002E12D, 6.5D, 2.5D, 6.5D + arc2, 2.5D + arc2, 8.5D, 4.5D, 14.5D - arc, 4.5D, -1.000000000002E12D, 14.5D, 4.5D, 14.5D, 4.5D + arc });
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatFileViewDirectoryIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */