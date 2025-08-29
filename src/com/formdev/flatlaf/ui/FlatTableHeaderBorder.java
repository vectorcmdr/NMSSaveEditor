/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JViewport;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.table.JTableHeader;
/*     */ import javax.swing.table.TableColumn;
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
/*     */ public class FlatTableHeaderBorder
/*     */   extends FlatEmptyBorder
/*     */ {
/*  47 */   protected Color separatorColor = UIManager.getColor("TableHeader.separatorColor");
/*  48 */   protected Color bottomSeparatorColor = UIManager.getColor("TableHeader.bottomSeparatorColor");
/*  49 */   protected boolean showTrailingVerticalLine = UIManager.getBoolean("TableHeader.showTrailingVerticalLine");
/*     */   
/*     */   public FlatTableHeaderBorder() {
/*  52 */     super(UIManager.getInsets("TableHeader.cellMargins"));
/*     */   }
/*     */ 
/*     */   
/*     */   public Insets getBorderInsets(Component c, Insets insets) {
/*  57 */     JTableHeader header = (JTableHeader)SwingUtilities.getAncestorOfClass(JTableHeader.class, c);
/*  58 */     if (header != null && 
/*  59 */       header.getUI() instanceof FlatTableHeaderUI) {
/*  60 */       FlatTableHeaderUI ui = (FlatTableHeaderUI)header.getUI();
/*  61 */       if (ui.cellMargins != null) {
/*  62 */         Insets m = ui.cellMargins;
/*  63 */         return scaleInsets(c, insets, m.top, m.left, m.bottom, m.right);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  68 */     return super.getBorderInsets(c, insets);
/*     */   }
/*     */ 
/*     */   
/*     */   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/*  73 */     JTableHeader header = (JTableHeader)SwingUtilities.getAncestorOfClass(JTableHeader.class, c);
/*  74 */     boolean leftToRight = ((header != null) ? header : c).getComponentOrientation().isLeftToRight();
/*  75 */     boolean paintLeft = !leftToRight;
/*  76 */     boolean paintRight = leftToRight;
/*  77 */     Color separatorColor = this.separatorColor;
/*  78 */     Color bottomSeparatorColor = this.bottomSeparatorColor;
/*     */     
/*  80 */     if (header != null) {
/*  81 */       int hx = (SwingUtilities.convertPoint(c, x, y, header)).x;
/*  82 */       if (isDraggedColumn(header, hx)) {
/*  83 */         paintLeft = paintRight = true;
/*     */       } else {
/*  85 */         if (hx <= 0 && !leftToRight && hideTrailingVerticalLine(header))
/*  86 */           paintLeft = false; 
/*  87 */         if (hx + width >= header.getWidth() && leftToRight && hideTrailingVerticalLine(header)) {
/*  88 */           paintRight = false;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  93 */       if (header.getUI() instanceof FlatTableHeaderUI) {
/*  94 */         FlatTableHeaderUI ui = (FlatTableHeaderUI)header.getUI();
/*  95 */         if (ui.separatorColor != null)
/*  96 */           separatorColor = ui.separatorColor; 
/*  97 */         if (ui.bottomSeparatorColor != null) {
/*  98 */           bottomSeparatorColor = ui.bottomSeparatorColor;
/*     */         }
/*     */       } 
/*     */     } 
/* 102 */     float lineWidth = UIScale.scale(1.0F);
/*     */     
/* 104 */     Graphics2D g2 = (Graphics2D)g.create();
/*     */     try {
/* 106 */       FlatUIUtils.setRenderingHints(g2);
/*     */ 
/*     */       
/* 109 */       g2.setColor(separatorColor);
/* 110 */       if (paintLeft)
/* 111 */         g2.fill(new Rectangle2D.Float(x, y, lineWidth, height - lineWidth)); 
/* 112 */       if (paintRight) {
/* 113 */         g2.fill(new Rectangle2D.Float((x + width) - lineWidth, y, lineWidth, height - lineWidth));
/*     */       }
/*     */       
/* 116 */       g2.setColor(bottomSeparatorColor);
/* 117 */       g2.fill(new Rectangle2D.Float(x, (y + height) - lineWidth, width, lineWidth));
/*     */     } finally {
/* 119 */       g2.dispose();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean isDraggedColumn(JTableHeader header, int x) {
/* 124 */     TableColumn draggedColumn = header.getDraggedColumn();
/* 125 */     if (draggedColumn == null) {
/* 126 */       return false;
/*     */     }
/* 128 */     int draggedDistance = header.getDraggedDistance();
/* 129 */     if (draggedDistance == 0) {
/* 130 */       return false;
/*     */     }
/* 132 */     int columnCount = header.getColumnModel().getColumnCount();
/* 133 */     for (int i = 0; i < columnCount; i++) {
/* 134 */       if ((header.getHeaderRect(i)).x + draggedDistance == x) {
/* 135 */         return true;
/*     */       }
/*     */     } 
/* 138 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean hideTrailingVerticalLine(JTableHeader header) {
/* 142 */     if (header.getUI() instanceof FlatTableHeaderUI) {
/* 143 */       FlatTableHeaderUI ui = (FlatTableHeaderUI)header.getUI();
/* 144 */       if (ui.showTrailingVerticalLine != null) {
/* 145 */         return !ui.showTrailingVerticalLine.booleanValue();
/*     */       }
/*     */     } 
/* 148 */     if (this.showTrailingVerticalLine) {
/* 149 */       return false;
/*     */     }
/*     */     
/* 152 */     Container viewport = header.getParent();
/* 153 */     Container viewportParent = (viewport != null) ? viewport.getParent() : null;
/* 154 */     if (!(viewportParent instanceof JScrollPane)) {
/* 155 */       return false;
/*     */     }
/*     */     
/* 158 */     JScrollPane scrollPane = (JScrollPane)viewportParent;
/* 159 */     JViewport columnHeader = scrollPane.getColumnHeader();
/* 160 */     if (viewport != columnHeader) {
/* 161 */       return false;
/*     */     }
/*     */     
/* 164 */     JScrollBar vsb = scrollPane.getVerticalScrollBar();
/* 165 */     if (vsb == null || !vsb.isVisible()) {
/* 166 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 170 */     return (vsb.getY() == viewport.getY());
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatTableHeaderBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */