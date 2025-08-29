/*     */ package com.jgoodies.forms.builder;
/*     */ 
/*     */ import com.jgoodies.forms.factories.FormFactory;
/*     */ import com.jgoodies.forms.layout.CellConstraints;
/*     */ import com.jgoodies.forms.layout.ColumnSpec;
/*     */ import com.jgoodies.forms.layout.FormLayout;
/*     */ import com.jgoodies.forms.layout.RowSpec;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.ComponentOrientation;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.border.Border;
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
/*     */ public abstract class AbstractButtonPanelBuilder
/*     */ {
/*     */   protected static final String NARROW_KEY = "jgoodies.isNarrow";
/*     */   private final JPanel container;
/*     */   private final FormLayout layout;
/*     */   private final CellConstraints currentCellConstraints;
/*     */   private boolean leftToRight;
/*     */   
/*     */   protected AbstractButtonPanelBuilder(FormLayout layout, JPanel container) {
/* 122 */     if (layout == null) {
/* 123 */       throw new NullPointerException("The layout must not be null.");
/*     */     }
/*     */     
/* 126 */     if (container == null) {
/* 127 */       throw new NullPointerException("The layout container must not be null.");
/*     */     }
/*     */     
/* 130 */     this.container = container;
/* 131 */     this.layout = layout;
/* 132 */     container.setLayout((LayoutManager)layout);
/*     */     
/* 134 */     setOpaque(false);
/*     */     
/* 136 */     this.currentCellConstraints = new CellConstraints();
/* 137 */     ComponentOrientation orientation = container.getComponentOrientation();
/* 138 */     this.leftToRight = (orientation.isLeftToRight() || !orientation.isHorizontal());
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
/*     */   public final JPanel getContainer() {
/* 151 */     return this.container;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final JPanel getPanel() {
/* 161 */     return getContainer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final FormLayout getLayout() {
/* 171 */     return this.layout;
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
/*     */   public final void setBackground(Color background) {
/* 185 */     getPanel().setBackground(background);
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
/*     */   public final void setBorder(Border border) {
/* 197 */     getPanel().setBorder(border);
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
/*     */   public final void setOpaque(boolean b) {
/* 211 */     getPanel().setOpaque(b);
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
/* 229 */     return this.leftToRight;
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
/* 244 */     this.leftToRight = b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void nextColumn() {
/* 254 */     nextColumn(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void nextColumn(int columns) {
/* 264 */     this.currentCellConstraints.gridX += columns * getColumnIncrementSign();
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getColumn() {
/* 269 */     return this.currentCellConstraints.gridX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void nextRow() {
/* 277 */     nextRow(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void nextRow(int rows) {
/* 287 */     this.currentCellConstraints.gridY += rows;
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
/*     */   protected final void appendColumn(ColumnSpec columnSpec) {
/* 299 */     getLayout().appendColumn(columnSpec);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void appendGlueColumn() {
/* 310 */     appendColumn(FormFactory.GLUE_COLSPEC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void appendRelatedComponentsGapColumn() {
/* 321 */     appendColumn(FormFactory.RELATED_GAP_COLSPEC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void appendUnrelatedComponentsGapColumn() {
/* 332 */     appendColumn(FormFactory.UNRELATED_GAP_COLSPEC);
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
/*     */   protected final void appendRow(RowSpec rowSpec) {
/* 344 */     getLayout().appendRow(rowSpec);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void appendGlueRow() {
/* 355 */     appendRow(FormFactory.GLUE_ROWSPEC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void appendRelatedComponentsGapRow() {
/* 366 */     appendRow(FormFactory.RELATED_GAP_ROWSPEC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void appendUnrelatedComponentsGapRow() {
/* 377 */     appendRow(FormFactory.UNRELATED_GAP_ROWSPEC);
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
/*     */   protected final Component add(Component component) {
/* 392 */     this.container.add(component, this.currentCellConstraints);
/* 393 */     return component;
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
/*     */   private int getColumnIncrementSign() {
/* 406 */     return isLeftToRight() ? 1 : -1;
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\builder\AbstractButtonPanelBuilder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */