/*     */ package com.jgoodies.forms.builder;
/*     */ 
/*     */ import com.jgoodies.forms.factories.FormFactory;
/*     */ import com.jgoodies.forms.layout.ConstantSize;
/*     */ import com.jgoodies.forms.layout.FormLayout;
/*     */ import com.jgoodies.forms.layout.RowSpec;
/*     */ import java.awt.Component;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class DefaultFormBuilder
/*     */   extends I15dPanelBuilder
/*     */ {
/* 226 */   private RowSpec defaultRowSpec = FormFactory.PREF_ROWSPEC;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 234 */   private RowSpec lineGapSpec = FormFactory.LINE_GAP_ROWSPEC;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 242 */   private RowSpec paragraphGapSpec = FormFactory.PARAGRAPH_GAP_ROWSPEC;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 251 */   private int leadingColumnOffset = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean rowGroupingEnabled = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultFormBuilder(FormLayout layout) {
/* 271 */     this(layout, new JPanel(null));
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
/*     */   public DefaultFormBuilder(FormLayout layout, JPanel panel) {
/* 283 */     this(layout, (ResourceBundle)null, panel);
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
/*     */   public DefaultFormBuilder(FormLayout layout, ResourceBundle bundle) {
/* 296 */     this(layout, bundle, new JPanel(null));
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
/*     */   public DefaultFormBuilder(FormLayout layout, ResourceBundle bundle, JPanel panel) {
/* 310 */     super(layout, bundle, panel);
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
/*     */   public RowSpec getDefaultRowSpec() {
/* 324 */     return this.defaultRowSpec;
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
/*     */   public void setDefaultRowSpec(RowSpec defaultRowSpec) {
/* 337 */     this.defaultRowSpec = defaultRowSpec;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RowSpec getLineGapSpec() {
/* 347 */     return this.lineGapSpec;
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
/*     */   public void setLineGapSize(ConstantSize lineGapSize) {
/* 365 */     RowSpec rowSpec = RowSpec.createGap(lineGapSize);
/* 366 */     this.lineGapSpec = rowSpec;
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
/*     */   public void setParagraphGapSize(ConstantSize paragraphGapSize) {
/* 384 */     RowSpec rowSpec = RowSpec.createGap(paragraphGapSize);
/* 385 */     this.paragraphGapSpec = rowSpec;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLeadingColumnOffset() {
/* 395 */     return this.leadingColumnOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLeadingColumnOffset(int columnOffset) {
/* 405 */     this.leadingColumnOffset = columnOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRowGroupingEnabled() {
/* 415 */     return this.rowGroupingEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRowGroupingEnabled(boolean enabled) {
/* 425 */     this.rowGroupingEnabled = enabled;
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
/*     */   public void append(Component component) {
/* 438 */     append(component, 1);
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
/*     */   public void append(Component component, int columnSpan) {
/* 450 */     ensureCursorColumnInGrid();
/* 451 */     ensureHasGapRow(this.lineGapSpec);
/* 452 */     ensureHasComponentLine();
/*     */     
/* 454 */     add(component, createLeftAdjustedConstraints(columnSpan));
/* 455 */     nextColumn(columnSpan + 1);
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
/*     */   public void append(Component c1, Component c2) {
/* 467 */     append(c1);
/* 468 */     append(c2);
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
/*     */   public void append(Component c1, Component c2, Component c3) {
/* 481 */     append(c1);
/* 482 */     append(c2);
/* 483 */     append(c3);
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
/*     */   public JLabel append(String textWithMnemonic) {
/* 496 */     JLabel label = getComponentFactory().createLabel(textWithMnemonic);
/* 497 */     append(label);
/* 498 */     return label;
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
/*     */   public JLabel append(String textWithMnemonic, Component component) {
/* 514 */     return append(textWithMnemonic, component, 1);
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
/*     */   public JLabel append(String textWithMnemonic, Component c, boolean nextLine) {
/* 533 */     JLabel label = append(textWithMnemonic, c);
/* 534 */     if (nextLine) {
/* 535 */       nextLine();
/*     */     }
/* 537 */     return label;
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
/*     */   public JLabel append(String textWithMnemonic, Component c, int columnSpan) {
/* 555 */     JLabel label = append(textWithMnemonic);
/* 556 */     label.setLabelFor(c);
/* 557 */     append(c, columnSpan);
/* 558 */     return label;
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
/*     */   public JLabel append(String textWithMnemonic, Component c1, Component c2) {
/* 575 */     JLabel label = append(textWithMnemonic, c1);
/* 576 */     append(c2);
/* 577 */     return label;
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
/*     */   public JLabel append(String textWithMnemonic, Component c1, Component c2, int colSpan) {
/* 595 */     JLabel label = append(textWithMnemonic, c1);
/* 596 */     append(c2, colSpan);
/* 597 */     return label;
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
/*     */   public JLabel append(String textWithMnemonic, Component c1, Component c2, Component c3) {
/* 615 */     JLabel label = append(textWithMnemonic, c1, c2);
/* 616 */     append(c3);
/* 617 */     return label;
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
/*     */   public JLabel append(String textWithMnemonic, Component c1, Component c2, Component c3, Component c4) {
/* 636 */     JLabel label = append(textWithMnemonic, c1, c2, c3);
/* 637 */     append(c4);
/* 638 */     return label;
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
/*     */   public JLabel appendI15d(String resourceKey) {
/* 652 */     return append(getI15dString(resourceKey));
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
/*     */   public JLabel appendI15d(String resourceKey, Component component) {
/* 668 */     return append(getI15dString(resourceKey), component, 1);
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
/*     */   public JLabel appendI15d(String resourceKey, Component component, boolean nextLine) {
/* 686 */     return append(getI15dString(resourceKey), component, nextLine);
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
/*     */   public JLabel appendI15d(String resourceKey, Component c, int columnSpan) {
/* 705 */     return append(getI15dString(resourceKey), c, columnSpan);
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
/*     */   public JLabel appendI15d(String resourceKey, Component c1, Component c2) {
/* 723 */     return append(getI15dString(resourceKey), c1, c2);
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
/*     */   public JLabel appendI15d(String resourceKey, Component c1, Component c2, int colSpan) {
/* 742 */     return append(getI15dString(resourceKey), c1, c2, colSpan);
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
/*     */   public JLabel appendI15d(String resourceKey, Component c1, Component c2, Component c3) {
/* 761 */     return append(getI15dString(resourceKey), c1, c2, c3);
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
/*     */   public JLabel appendI15d(String resourceKey, Component c1, Component c2, Component c3, Component c4) {
/* 781 */     return append(getI15dString(resourceKey), c1, c2, c3, c4);
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
/*     */   public JLabel appendTitle(String textWithMnemonic) {
/* 794 */     JLabel titleLabel = getComponentFactory().createTitle(textWithMnemonic);
/* 795 */     append(titleLabel);
/* 796 */     return titleLabel;
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
/*     */   public JLabel appendI15dTitle(String resourceKey) {
/* 808 */     return appendTitle(getI15dString(resourceKey));
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
/*     */   public JComponent appendSeparator() {
/* 820 */     return appendSeparator("");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JComponent appendSeparator(String text) {
/* 831 */     ensureCursorColumnInGrid();
/* 832 */     ensureHasGapRow(this.paragraphGapSpec);
/* 833 */     ensureHasComponentLine();
/*     */     
/* 835 */     setColumn(super.getLeadingColumn());
/* 836 */     int columnSpan = getColumnCount();
/* 837 */     setColumnSpan(getColumnCount());
/* 838 */     JComponent titledSeparator = addSeparator(text);
/* 839 */     setColumnSpan(1);
/* 840 */     nextColumn(columnSpan);
/* 841 */     return titledSeparator;
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
/*     */   public JComponent appendI15dSeparator(String resourceKey) {
/* 853 */     return appendSeparator(getI15dString(resourceKey));
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
/* 866 */     int column = super.getLeadingColumn();
/* 867 */     return column + getLeadingColumnOffset() * getColumnIncrementSign();
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
/*     */   private void ensureCursorColumnInGrid() {
/* 879 */     if ((isLeftToRight() && getColumn() > getColumnCount()) || (!isLeftToRight() && getColumn() < 1))
/*     */     {
/* 881 */       nextLine();
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
/*     */   private void ensureHasGapRow(RowSpec gapRowSpec) {
/* 894 */     if (getRow() == 1 || getRow() <= getRowCount()) {
/*     */       return;
/*     */     }
/* 897 */     if (getRow() <= getRowCount()) {
/* 898 */       RowSpec rowSpec = getCursorRowSpec();
/* 899 */       if (rowSpec == gapRowSpec)
/*     */         return; 
/*     */     } 
/* 902 */     appendRow(gapRowSpec);
/* 903 */     nextLine();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ensureHasComponentLine() {
/* 912 */     if (getRow() <= getRowCount())
/* 913 */       return;  appendRow(getDefaultRowSpec());
/* 914 */     if (isRowGroupingEnabled()) {
/* 915 */       getLayout().addGroupedRow(getRow());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private RowSpec getCursorRowSpec() {
/* 926 */     return getLayout().getRowSpec(getRow());
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\builder\DefaultFormBuilder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */