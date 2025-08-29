/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import java.util.function.Function;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.plaf.TableUI;
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
/*     */ public class FlatTableCellBorder
/*     */   extends FlatLineBorder
/*     */ {
/*  41 */   protected boolean showCellFocusIndicator = UIManager.getBoolean("Table.showCellFocusIndicator");
/*     */   
/*     */   private Component c;
/*     */   
/*     */   protected FlatTableCellBorder() {
/*  46 */     super(UIManager.getInsets("Table.cellMargins"), UIManager.getColor("Table.cellFocusColor"));
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getBorderInsets(Component c, Insets insets) {
/*  51 */     Insets m = getStyleFromTableUI(c, ui -> ui.cellMargins);
/*  52 */     if (m != null) {
/*  53 */       return scaleInsets(c, insets, m.top, m.left, m.bottom, m.right);
/*     */     }
/*  55 */     return super.getBorderInsets(c, insets);
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getLineColor() {
/*  60 */     if (this.c != null) {
/*  61 */       Color color = getStyleFromTableUI(this.c, ui -> ui.cellFocusColor);
/*  62 */       if (color != null)
/*  63 */         return color; 
/*     */     } 
/*  65 */     return super.getLineColor();
/*     */   }
/*     */ 
/*     */   
/*     */   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/*  70 */     this.c = c;
/*  71 */     super.paintBorder(c, g, x, y, width, height);
/*  72 */     this.c = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static <T> T getStyleFromTableUI(Component c, Function<FlatTableUI, T> f) {
/*  80 */     JTable table = (JTable)SwingUtilities.getAncestorOfClass(JTable.class, c);
/*  81 */     if (table != null) {
/*  82 */       TableUI ui = table.getUI();
/*  83 */       if (ui instanceof FlatTableUI)
/*  84 */         return f.apply((FlatTableUI)ui); 
/*     */     } 
/*  86 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Default
/*     */     extends FlatTableCellBorder
/*     */   {
/*     */     public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Focused
/*     */     extends FlatTableCellBorder
/*     */   {
/*     */     public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 113 */       if (c != null && c.getClass().getName().equals("javax.swing.JTable$BooleanRenderer")) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 118 */         JTable table = (JTable)SwingUtilities.getAncestorOfClass(JTable.class, c);
/* 119 */         if (table != null && c
/* 120 */           .getForeground() == table.getSelectionForeground() && c
/* 121 */           .getBackground() == table.getSelectionBackground()) {
/*     */           
/* 123 */           Border border = UIManager.getBorder("Table.focusSelectedCellHighlightBorder");
/* 124 */           if (border != null) {
/* 125 */             border.paintBorder(c, g, x, y, width, height);
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/* 131 */       super.paintBorder(c, g, x, y, width, height);
/*     */     }
/*     */   }
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
/*     */   public static class Selected
/*     */     extends FlatTableCellBorder
/*     */   {
/* 158 */     public int maxCheckCellsEditable = 50;
/*     */ 
/*     */     
/*     */     public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 162 */       Boolean b = getStyleFromTableUI(c, ui -> ui.showCellFocusIndicator);
/* 163 */       boolean showCellFocusIndicator = (b != null) ? b.booleanValue() : this.showCellFocusIndicator;
/*     */       
/* 165 */       if (!showCellFocusIndicator) {
/* 166 */         JTable table = (JTable)SwingUtilities.getAncestorOfClass(JTable.class, c);
/* 167 */         if (table != null && !shouldShowCellFocusIndicator(table)) {
/*     */           return;
/*     */         }
/*     */       } 
/* 171 */       super.paintBorder(c, g, x, y, width, height);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean shouldShowCellFocusIndicator(JTable table) {
/* 180 */       boolean rowSelectionAllowed = table.getRowSelectionAllowed();
/* 181 */       boolean columnSelectionAllowed = table.getColumnSelectionAllowed();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 186 */       if (rowSelectionAllowed && columnSelectionAllowed) {
/* 187 */         return false;
/*     */       }
/* 189 */       if (rowSelectionAllowed) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 194 */         if (table.getSelectedRowCount() != 1) {
/* 195 */           return false;
/*     */         }
/*     */         
/* 198 */         int columnCount = table.getColumnCount();
/* 199 */         if (columnCount > this.maxCheckCellsEditable) {
/* 200 */           return true;
/*     */         }
/*     */         
/* 203 */         int selectedRow = table.getSelectedRow();
/* 204 */         for (int column = 0; column < columnCount; column++) {
/* 205 */           if (table.isCellEditable(selectedRow, column))
/* 206 */             return true; 
/*     */         } 
/* 208 */       } else if (columnSelectionAllowed) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 213 */         if (table.getSelectedColumnCount() != 1) {
/* 214 */           return false;
/*     */         }
/*     */         
/* 217 */         int rowCount = table.getRowCount();
/* 218 */         if (rowCount > this.maxCheckCellsEditable) {
/* 219 */           return true;
/*     */         }
/*     */         
/* 222 */         int selectedColumn = table.getSelectedColumn();
/* 223 */         for (int row = 0; row < rowCount; row++) {
/* 224 */           if (table.isCellEditable(row, selectedColumn)) {
/* 225 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/* 229 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatTableCellBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */