/*     */ package com.jgoodies.forms.debug;
/*     */ 
/*     */ import com.jgoodies.forms.layout.FormLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.swing.JPanel;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FormDebugPanel
/*     */   extends JPanel
/*     */ {
/*     */   public static boolean paintRowsDefault = true;
/*  69 */   private static final Color DEFAULT_GRID_COLOR = Color.red;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean paintInBackground;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean paintDiagonals;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   private boolean paintRows = paintRowsDefault;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   private Color gridColor = DEFAULT_GRID_COLOR;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FormDebugPanel() {
/* 103 */     this((FormLayout)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FormDebugPanel(FormLayout layout) {
/* 114 */     this(layout, false, false);
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
/*     */   public FormDebugPanel(boolean paintInBackground, boolean paintDiagonals) {
/* 131 */     this((FormLayout)null, paintInBackground, paintDiagonals);
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
/*     */   public FormDebugPanel(FormLayout layout, boolean paintInBackground, boolean paintDiagonals) {
/* 151 */     super((LayoutManager)layout);
/* 152 */     setPaintInBackground(paintInBackground);
/* 153 */     setPaintDiagonals(paintDiagonals);
/* 154 */     setGridColor(DEFAULT_GRID_COLOR);
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
/*     */   public void setPaintInBackground(boolean b) {
/* 166 */     this.paintInBackground = b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPaintDiagonals(boolean b) {
/* 175 */     this.paintDiagonals = b;
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
/*     */   public void setPaintRows(boolean b) {
/* 187 */     this.paintRows = b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGridColor(Color color) {
/* 196 */     this.gridColor = color;
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
/*     */   protected void paintComponent(Graphics g) {
/* 212 */     super.paintComponent(g);
/* 213 */     if (this.paintInBackground) {
/* 214 */       paintGrid(g);
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
/*     */   public void paint(Graphics g) {
/* 230 */     super.paint(g);
/* 231 */     if (!this.paintInBackground) {
/* 232 */       paintGrid(g);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void paintGrid(Graphics g) {
/* 243 */     if (!(getLayout() instanceof FormLayout)) {
/*     */       return;
/*     */     }
/* 246 */     FormLayout.LayoutInfo layoutInfo = FormDebugUtils.getLayoutInfo(this);
/* 247 */     int left = layoutInfo.getX();
/* 248 */     int top = layoutInfo.getY();
/* 249 */     int width = layoutInfo.getWidth();
/* 250 */     int height = layoutInfo.getHeight();
/*     */     
/* 252 */     g.setColor(this.gridColor);
/*     */ 
/*     */     
/* 255 */     int last = layoutInfo.columnOrigins.length - 1;
/* 256 */     for (int col = 0; col <= last; col++) {
/* 257 */       boolean firstOrLast = (col == 0 || col == last);
/* 258 */       int x = layoutInfo.columnOrigins[col];
/* 259 */       int start = firstOrLast ? 0 : top;
/* 260 */       int stop = firstOrLast ? getHeight() : (top + height);
/* 261 */       for (int i = start; i < stop; i += 5) {
/* 262 */         int length = Math.min(3, stop - i);
/* 263 */         g.fillRect(x, i, 1, length);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 268 */     last = layoutInfo.rowOrigins.length - 1;
/* 269 */     for (int row = 0; row <= last; row++) {
/* 270 */       boolean firstOrLast = (row == 0 || row == last);
/* 271 */       int y = layoutInfo.rowOrigins[row];
/* 272 */       int start = firstOrLast ? 0 : left;
/* 273 */       int stop = firstOrLast ? getWidth() : (left + width);
/* 274 */       if (firstOrLast || this.paintRows) {
/* 275 */         for (int i = start; i < stop; i += 5) {
/* 276 */           int length = Math.min(3, stop - i);
/* 277 */           g.fillRect(i, y, length, 1);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 282 */     if (this.paintDiagonals) {
/* 283 */       g.drawLine(left, top, left + width, top + height);
/* 284 */       g.drawLine(left, top + height, left + width, top);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\debug\FormDebugPanel.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */