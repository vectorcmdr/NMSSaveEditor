package com.jgoodies.forms.builder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;

public abstract class AbstractFormBuilder {
   private final Container container;
   private final FormLayout layout;
   private final CellConstraints currentCellConstraints;
   private boolean leftToRight;

   public AbstractFormBuilder(FormLayout layout, Container container) {
      if (layout == null) {
         throw new NullPointerException("The layout must not be null.");
      } else if (container == null) {
         throw new NullPointerException("The layout container must not be null.");
      } else {
         this.container = container;
         this.layout = layout;
         container.setLayout(layout);
         this.currentCellConstraints = new CellConstraints();
         ComponentOrientation orientation = container.getComponentOrientation();
         this.leftToRight = orientation.isLeftToRight() || !orientation.isHorizontal();
      }
   }

   public final Container getContainer() {
      return this.container;
   }

   public final FormLayout getLayout() {
      return this.layout;
   }

   public final int getColumnCount() {
      return this.getLayout().getColumnCount();
   }

   public final int getRowCount() {
      return this.getLayout().getRowCount();
   }

   public final boolean isLeftToRight() {
      return this.leftToRight;
   }

   public final void setLeftToRight(boolean b) {
      this.leftToRight = b;
   }

   public final int getColumn() {
      return this.currentCellConstraints.gridX;
   }

   public final void setColumn(int column) {
      this.currentCellConstraints.gridX = column;
   }

   public final int getRow() {
      return this.currentCellConstraints.gridY;
   }

   public final void setRow(int row) {
      this.currentCellConstraints.gridY = row;
   }

   public final void setColumnSpan(int columnSpan) {
      this.currentCellConstraints.gridWidth = columnSpan;
   }

   public final void setRowSpan(int rowSpan) {
      this.currentCellConstraints.gridHeight = rowSpan;
   }

   public final void setOrigin(int column, int row) {
      this.setColumn(column);
      this.setRow(row);
   }

   public final void setExtent(int columnSpan, int rowSpan) {
      this.setColumnSpan(columnSpan);
      this.setRowSpan(rowSpan);
   }

   public final void setBounds(int column, int row, int columnSpan, int rowSpan) {
      this.setColumn(column);
      this.setRow(row);
      this.setColumnSpan(columnSpan);
      this.setRowSpan(rowSpan);
   }

   public final void nextColumn() {
      this.nextColumn(1);
   }

   public final void nextColumn(int columns) {
      CellConstraints var10000 = this.currentCellConstraints;
      var10000.gridX += columns * this.getColumnIncrementSign();
   }

   public final void nextRow() {
      this.nextRow(1);
   }

   public final void nextRow(int rows) {
      CellConstraints var10000 = this.currentCellConstraints;
      var10000.gridY += rows;
   }

   public final void nextLine() {
      this.nextLine(1);
   }

   public final void nextLine(int lines) {
      this.nextRow(lines);
      this.setColumn(this.getLeadingColumn());
   }

   public final void setHAlignment(CellConstraints.Alignment alignment) {
      this.currentCellConstraints.hAlign = alignment;
   }

   public final void setVAlignment(CellConstraints.Alignment alignment) {
      this.currentCellConstraints.vAlign = alignment;
   }

   public final void setAlignment(CellConstraints.Alignment hAlign, CellConstraints.Alignment vAlign) {
      this.setHAlignment(hAlign);
      this.setVAlignment(vAlign);
   }

   public final void appendColumn(ColumnSpec columnSpec) {
      this.getLayout().appendColumn(columnSpec);
   }

   public final void appendColumn(String encodedColumnSpec) {
      this.appendColumn(ColumnSpec.decode(encodedColumnSpec));
   }

   public final void appendGlueColumn() {
      this.appendColumn(FormFactory.GLUE_COLSPEC);
   }

   public final void appendLabelComponentsGapColumn() {
      this.appendColumn(FormFactory.LABEL_COMPONENT_GAP_COLSPEC);
   }

   public final void appendRelatedComponentsGapColumn() {
      this.appendColumn(FormFactory.RELATED_GAP_COLSPEC);
   }

   public final void appendUnrelatedComponentsGapColumn() {
      this.appendColumn(FormFactory.UNRELATED_GAP_COLSPEC);
   }

   public final void appendRow(RowSpec rowSpec) {
      this.getLayout().appendRow(rowSpec);
   }

   public final void appendRow(String encodedRowSpec) {
      this.appendRow(RowSpec.decode(encodedRowSpec));
   }

   public final void appendGlueRow() {
      this.appendRow(FormFactory.GLUE_ROWSPEC);
   }

   public final void appendRelatedComponentsGapRow() {
      this.appendRow(FormFactory.RELATED_GAP_ROWSPEC);
   }

   public final void appendUnrelatedComponentsGapRow() {
      this.appendRow(FormFactory.UNRELATED_GAP_ROWSPEC);
   }

   public final void appendParagraphGapRow() {
      this.appendRow(FormFactory.PARAGRAPH_GAP_ROWSPEC);
   }

   public Component add(Component component, CellConstraints cellConstraints) {
      this.container.add(component, cellConstraints);
      return component;
   }

   public final Component add(Component component, String encodedCellConstraints) {
      this.container.add(component, new CellConstraints(encodedCellConstraints));
      return component;
   }

   public final Component add(Component component) {
      this.add(component, this.currentCellConstraints);
      return component;
   }

   protected final CellConstraints cellConstraints() {
      return this.currentCellConstraints;
   }

   protected int getLeadingColumn() {
      return this.isLeftToRight() ? 1 : this.getColumnCount();
   }

   protected final int getColumnIncrementSign() {
      return this.isLeftToRight() ? 1 : -1;
   }

   protected final CellConstraints createLeftAdjustedConstraints(int columnSpan) {
      int firstColumn = this.isLeftToRight() ? this.getColumn() : this.getColumn() + 1 - columnSpan;
      return new CellConstraints(firstColumn, this.getRow(), columnSpan, this.cellConstraints().gridHeight);
   }
}
