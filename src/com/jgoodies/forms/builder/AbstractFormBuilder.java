/*     */ package com.jgoodies.forms.builder;
/*     */ 
/*     */ import com.jgoodies.forms.factories.FormFactory;
/*     */ import com.jgoodies.forms.layout.CellConstraints;
/*     */ import com.jgoodies.forms.layout.ColumnSpec;
/*     */ import com.jgoodies.forms.layout.FormLayout;
/*     */ import com.jgoodies.forms.layout.RowSpec;
/*     */ import java.awt.Component;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.Container;
/*     */ import java.awt.LayoutManager;
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
/*     */ public abstract class AbstractFormBuilder
/*     */ {
/*     */   private final Container container;
/*     */   private final FormLayout layout;
/*     */   private final CellConstraints currentCellConstraints;
/*     */   private boolean leftToRight;
/*     */   
/*     */   public AbstractFormBuilder(FormLayout layout, Container container) {
/* 107 */     if (layout == null) {
/* 108 */       throw new NullPointerException("The layout must not be null.");
/*     */     }
/* 110 */     if (container == null) {
/* 111 */       throw new NullPointerException("The layout container must not be null.");
/*     */     }
/* 113 */     this.container = container;
/* 114 */     this.layout = layout;
/*     */     
/* 116 */     container.setLayout((LayoutManager)layout);
/* 117 */     this.currentCellConstraints = new CellConstraints();
/* 118 */     ComponentOrientation orientation = container.getComponentOrientation();
/* 119 */     this.leftToRight = (orientation.isLeftToRight() || !orientation.isHorizontal());
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
/*     */   public final Container getContainer() {
/* 132 */     return this.container;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final FormLayout getLayout() {
/* 142 */     return this.layout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getColumnCount() {
/* 152 */     return getLayout().getColumnCount();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getRowCount() {
/* 162 */     return getLayout().getRowCount();
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
/*     */   public final boolean isLeftToRight() {
/* 180 */     return this.leftToRight;
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
/*     */   public final void setLeftToRight(boolean b) {
/* 195 */     this.leftToRight = b;
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
/*     */   public final int getColumn() {
/* 207 */     return this.currentCellConstraints.gridX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setColumn(int column) {
/* 217 */     this.currentCellConstraints.gridX = column;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getRow() {
/* 227 */     return this.currentCellConstraints.gridY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setRow(int row) {
/* 237 */     this.currentCellConstraints.gridY = row;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setColumnSpan(int columnSpan) {
/* 247 */     this.currentCellConstraints.gridWidth = columnSpan;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setRowSpan(int rowSpan) {
/* 257 */     this.currentCellConstraints.gridHeight = rowSpan;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setOrigin(int column, int row) {
/* 268 */     setColumn(column);
/* 269 */     setRow(row);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setExtent(int columnSpan, int rowSpan) {
/* 280 */     setColumnSpan(columnSpan);
/* 281 */     setRowSpan(rowSpan);
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
/*     */   public final void setBounds(int column, int row, int columnSpan, int rowSpan) {
/* 295 */     setColumn(column);
/* 296 */     setRow(row);
/* 297 */     setColumnSpan(columnSpan);
/* 298 */     setRowSpan(rowSpan);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void nextColumn() {
/* 306 */     nextColumn(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void nextColumn(int columns) {
/* 316 */     this.currentCellConstraints.gridX += columns * getColumnIncrementSign();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void nextRow() {
/* 324 */     nextRow(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void nextRow(int rows) {
/* 334 */     this.currentCellConstraints.gridY += rows;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void nextLine() {
/* 343 */     nextLine(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void nextLine(int lines) {
/* 354 */     nextRow(lines);
/* 355 */     setColumn(getLeadingColumn());
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
/*     */   public final void setHAlignment(CellConstraints.Alignment alignment) {
/* 367 */     this.currentCellConstraints.hAlign = alignment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setVAlignment(CellConstraints.Alignment alignment) {
/* 376 */     this.currentCellConstraints.vAlign = alignment;
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
/*     */   public final void setAlignment(CellConstraints.Alignment hAlign, CellConstraints.Alignment vAlign) {
/* 388 */     setHAlignment(hAlign);
/* 389 */     setVAlignment(vAlign);
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
/*     */   public final void appendColumn(ColumnSpec columnSpec) {
/* 403 */     getLayout().appendColumn(columnSpec);
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
/*     */   public final void appendColumn(String encodedColumnSpec) {
/* 416 */     appendColumn(ColumnSpec.decode(encodedColumnSpec));
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
/*     */   public final void appendGlueColumn() {
/* 428 */     appendColumn(FormFactory.GLUE_COLSPEC);
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
/*     */   public final void appendLabelComponentsGapColumn() {
/* 443 */     appendColumn(FormFactory.LABEL_COMPONENT_GAP_COLSPEC);
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
/*     */   public final void appendRelatedComponentsGapColumn() {
/* 455 */     appendColumn(FormFactory.RELATED_GAP_COLSPEC);
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
/*     */   public final void appendUnrelatedComponentsGapColumn() {
/* 467 */     appendColumn(FormFactory.UNRELATED_GAP_COLSPEC);
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
/*     */   public final void appendRow(RowSpec rowSpec) {
/* 481 */     getLayout().appendRow(rowSpec);
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
/*     */   public final void appendRow(String encodedRowSpec) {
/* 494 */     appendRow(RowSpec.decode(encodedRowSpec));
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
/*     */   public final void appendGlueRow() {
/* 506 */     appendRow(FormFactory.GLUE_ROWSPEC);
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
/*     */   public final void appendRelatedComponentsGapRow() {
/* 518 */     appendRow(FormFactory.RELATED_GAP_ROWSPEC);
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
/*     */   public final void appendUnrelatedComponentsGapRow() {
/* 530 */     appendRow(FormFactory.UNRELATED_GAP_ROWSPEC);
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
/*     */   public final void appendParagraphGapRow() {
/* 544 */     appendRow(FormFactory.PARAGRAPH_GAP_ROWSPEC);
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
/*     */   public Component add(Component component, CellConstraints cellConstraints) {
/* 558 */     this.container.add(component, cellConstraints);
/* 559 */     return component;
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
/*     */   public final Component add(Component component, String encodedCellConstraints) {
/* 571 */     this.container.add(component, new CellConstraints(encodedCellConstraints));
/* 572 */     return component;
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
/*     */   public final Component add(Component component) {
/* 590 */     add(component, this.currentCellConstraints);
/* 591 */     return component;
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
/*     */   protected final CellConstraints cellConstraints() {
/* 604 */     return this.currentCellConstraints;
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
/*     */   protected int getLeadingColumn() {
/* 617 */     return isLeftToRight() ? 1 : getColumnCount();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final int getColumnIncrementSign() {
/* 628 */     return isLeftToRight() ? 1 : -1;
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
/*     */   protected final CellConstraints createLeftAdjustedConstraints(int columnSpan) {
/* 641 */     int firstColumn = isLeftToRight() ? getColumn() : (getColumn() + 1 - columnSpan);
/*     */ 
/*     */     
/* 644 */     return new CellConstraints(firstColumn, getRow(), columnSpan, (cellConstraints()).gridHeight);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\builder\AbstractFormBuilder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */