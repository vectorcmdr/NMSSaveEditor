package com.jgoodies.forms.builder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ConstantSize;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.ResourceBundle;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class DefaultFormBuilder extends I15dPanelBuilder {
   private RowSpec defaultRowSpec;
   private RowSpec lineGapSpec;
   private RowSpec paragraphGapSpec;
   private int leadingColumnOffset;
   private boolean rowGroupingEnabled;

   public DefaultFormBuilder(FormLayout layout) {
      this(layout, new JPanel((LayoutManager)null));
   }

   public DefaultFormBuilder(FormLayout layout, JPanel panel) {
      this(layout, (ResourceBundle)null, panel);
   }

   public DefaultFormBuilder(FormLayout layout, ResourceBundle bundle) {
      this(layout, bundle, new JPanel((LayoutManager)null));
   }

   public DefaultFormBuilder(FormLayout layout, ResourceBundle bundle, JPanel panel) {
      super(layout, bundle, panel);
      this.defaultRowSpec = FormFactory.PREF_ROWSPEC;
      this.lineGapSpec = FormFactory.LINE_GAP_ROWSPEC;
      this.paragraphGapSpec = FormFactory.PARAGRAPH_GAP_ROWSPEC;
      this.leadingColumnOffset = 0;
      this.rowGroupingEnabled = false;
   }

   public RowSpec getDefaultRowSpec() {
      return this.defaultRowSpec;
   }

   public void setDefaultRowSpec(RowSpec defaultRowSpec) {
      this.defaultRowSpec = defaultRowSpec;
   }

   public RowSpec getLineGapSpec() {
      return this.lineGapSpec;
   }

   public void setLineGapSize(ConstantSize lineGapSize) {
      RowSpec rowSpec = RowSpec.createGap(lineGapSize);
      this.lineGapSpec = rowSpec;
   }

   public void setParagraphGapSize(ConstantSize paragraphGapSize) {
      RowSpec rowSpec = RowSpec.createGap(paragraphGapSize);
      this.paragraphGapSpec = rowSpec;
   }

   public int getLeadingColumnOffset() {
      return this.leadingColumnOffset;
   }

   public void setLeadingColumnOffset(int columnOffset) {
      this.leadingColumnOffset = columnOffset;
   }

   public boolean isRowGroupingEnabled() {
      return this.rowGroupingEnabled;
   }

   public void setRowGroupingEnabled(boolean enabled) {
      this.rowGroupingEnabled = enabled;
   }

   public void append(Component component) {
      this.append(component, 1);
   }

   public void append(Component component, int columnSpan) {
      this.ensureCursorColumnInGrid();
      this.ensureHasGapRow(this.lineGapSpec);
      this.ensureHasComponentLine();
      this.add(component, this.createLeftAdjustedConstraints(columnSpan));
      this.nextColumn(columnSpan + 1);
   }

   public void append(Component c1, Component c2) {
      this.append(c1);
      this.append(c2);
   }

   public void append(Component c1, Component c2, Component c3) {
      this.append(c1);
      this.append(c2);
      this.append(c3);
   }

   public JLabel append(String textWithMnemonic) {
      JLabel label = this.getComponentFactory().createLabel(textWithMnemonic);
      this.append((Component)label);
      return label;
   }

   public JLabel append(String textWithMnemonic, Component component) {
      return this.append(textWithMnemonic, component, 1);
   }

   public JLabel append(String textWithMnemonic, Component c, boolean nextLine) {
      JLabel label = this.append(textWithMnemonic, c);
      if (nextLine) {
         this.nextLine();
      }

      return label;
   }

   public JLabel append(String textWithMnemonic, Component c, int columnSpan) {
      JLabel label = this.append(textWithMnemonic);
      label.setLabelFor(c);
      this.append(c, columnSpan);
      return label;
   }

   public JLabel append(String textWithMnemonic, Component c1, Component c2) {
      JLabel label = this.append(textWithMnemonic, c1);
      this.append(c2);
      return label;
   }

   public JLabel append(String textWithMnemonic, Component c1, Component c2, int colSpan) {
      JLabel label = this.append(textWithMnemonic, c1);
      this.append(c2, colSpan);
      return label;
   }

   public JLabel append(String textWithMnemonic, Component c1, Component c2, Component c3) {
      JLabel label = this.append(textWithMnemonic, c1, c2);
      this.append(c3);
      return label;
   }

   public JLabel append(String textWithMnemonic, Component c1, Component c2, Component c3, Component c4) {
      JLabel label = this.append(textWithMnemonic, c1, c2, c3);
      this.append(c4);
      return label;
   }

   public JLabel appendI15d(String resourceKey) {
      return this.append(this.getI15dString(resourceKey));
   }

   public JLabel appendI15d(String resourceKey, Component component) {
      return this.append(this.getI15dString(resourceKey), component, 1);
   }

   public JLabel appendI15d(String resourceKey, Component component, boolean nextLine) {
      return this.append(this.getI15dString(resourceKey), component, nextLine);
   }

   public JLabel appendI15d(String resourceKey, Component c, int columnSpan) {
      return this.append(this.getI15dString(resourceKey), c, columnSpan);
   }

   public JLabel appendI15d(String resourceKey, Component c1, Component c2) {
      return this.append(this.getI15dString(resourceKey), c1, c2);
   }

   public JLabel appendI15d(String resourceKey, Component c1, Component c2, int colSpan) {
      return this.append(this.getI15dString(resourceKey), c1, c2, colSpan);
   }

   public JLabel appendI15d(String resourceKey, Component c1, Component c2, Component c3) {
      return this.append(this.getI15dString(resourceKey), c1, c2, c3);
   }

   public JLabel appendI15d(String resourceKey, Component c1, Component c2, Component c3, Component c4) {
      return this.append(this.getI15dString(resourceKey), c1, c2, c3, c4);
   }

   public JLabel appendTitle(String textWithMnemonic) {
      JLabel titleLabel = this.getComponentFactory().createTitle(textWithMnemonic);
      this.append((Component)titleLabel);
      return titleLabel;
   }

   public JLabel appendI15dTitle(String resourceKey) {
      return this.appendTitle(this.getI15dString(resourceKey));
   }

   public JComponent appendSeparator() {
      return this.appendSeparator("");
   }

   public JComponent appendSeparator(String text) {
      this.ensureCursorColumnInGrid();
      this.ensureHasGapRow(this.paragraphGapSpec);
      this.ensureHasComponentLine();
      this.setColumn(super.getLeadingColumn());
      int columnSpan = this.getColumnCount();
      this.setColumnSpan(this.getColumnCount());
      JComponent titledSeparator = this.addSeparator(text);
      this.setColumnSpan(1);
      this.nextColumn(columnSpan);
      return titledSeparator;
   }

   public JComponent appendI15dSeparator(String resourceKey) {
      return this.appendSeparator(this.getI15dString(resourceKey));
   }

   protected int getLeadingColumn() {
      int column = super.getLeadingColumn();
      return column + this.getLeadingColumnOffset() * this.getColumnIncrementSign();
   }

   private void ensureCursorColumnInGrid() {
      if (this.isLeftToRight() && this.getColumn() > this.getColumnCount() || !this.isLeftToRight() && this.getColumn() < 1) {
         this.nextLine();
      }

   }

   private void ensureHasGapRow(RowSpec gapRowSpec) {
      if (this.getRow() != 1 && this.getRow() > this.getRowCount()) {
         if (this.getRow() <= this.getRowCount()) {
            RowSpec rowSpec = this.getCursorRowSpec();
            if (rowSpec == gapRowSpec) {
               return;
            }
         }

         this.appendRow(gapRowSpec);
         this.nextLine();
      }
   }

   private void ensureHasComponentLine() {
      if (this.getRow() > this.getRowCount()) {
         this.appendRow(this.getDefaultRowSpec());
         if (this.isRowGroupingEnabled()) {
            this.getLayout().addGroupedRow(this.getRow());
         }

      }
   }

   private RowSpec getCursorRowSpec() {
      return this.getLayout().getRowSpec(this.getRow());
   }
}
