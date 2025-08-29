/*    */ package com.formdev.flatlaf.icons;
/*    */ 
/*    */ import com.formdev.flatlaf.ui.FlatTableHeaderUI;
/*    */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.geom.Path2D;
/*    */ import javax.swing.SwingUtilities;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.plaf.TableHeaderUI;
/*    */ import javax.swing.table.JTableHeader;
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
/*    */ public class FlatAscendingSortIcon
/*    */   extends FlatAbstractIcon
/*    */ {
/* 42 */   protected boolean chevron = FlatUIUtils.isChevron(UIManager.getString("Component.arrowType"));
/* 43 */   protected Color sortIconColor = UIManager.getColor("Table.sortIconColor");
/*    */   
/*    */   public FlatAscendingSortIcon() {
/* 46 */     super(10, 5, null);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paintIcon(Component c, Graphics2D g) {
/* 51 */     boolean chevron = this.chevron;
/* 52 */     Color sortIconColor = this.sortIconColor;
/*    */ 
/*    */ 
/*    */     
/* 56 */     JTableHeader tableHeader = (JTableHeader)SwingUtilities.getAncestorOfClass(JTableHeader.class, c);
/* 57 */     if (tableHeader != null) {
/* 58 */       TableHeaderUI ui = tableHeader.getUI();
/* 59 */       if (ui instanceof FlatTableHeaderUI) {
/* 60 */         FlatTableHeaderUI fui = (FlatTableHeaderUI)ui;
/* 61 */         if (fui.arrowType != null)
/* 62 */           chevron = FlatUIUtils.isChevron(fui.arrowType); 
/* 63 */         if (fui.sortIconColor != null) {
/* 64 */           sortIconColor = fui.sortIconColor;
/*    */         }
/*    */       } 
/*    */     } 
/* 68 */     g.setColor(sortIconColor);
/* 69 */     paintArrow(c, g, chevron);
/*    */   }
/*    */   
/*    */   protected void paintArrow(Component c, Graphics2D g, boolean chevron) {
/* 73 */     if (chevron) {
/*    */       
/* 75 */       Path2D path = FlatUIUtils.createPath(false, new double[] { 1.0D, 4.0D, 5.0D, 0.0D, 9.0D, 4.0D });
/* 76 */       g.setStroke(new BasicStroke(1.0F));
/* 77 */       g.draw(path);
/*    */     } else {
/*    */       
/* 80 */       g.fill(FlatUIUtils.createPath(new double[] { 0.5D, 5.0D, 5.0D, 0.0D, 9.5D, 5.0D }));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\icons\FlatAscendingSortIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */