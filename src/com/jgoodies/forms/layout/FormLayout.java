/*      */ package com.jgoodies.forms.layout;
/*      */ 
/*      */ import com.jgoodies.forms.util.FormUtils;
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Insets;
/*      */ import java.awt.LayoutManager2;
/*      */ import java.awt.Rectangle;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import javax.swing.JComponent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class FormLayout
/*      */   implements LayoutManager2, Serializable
/*      */ {
/*      */   private final List colSpecs;
/*      */   private final List rowSpecs;
/*      */   private int[][] colGroupIndices;
/*      */   private int[][] rowGroupIndices;
/*      */   private final Map constraintMap;
/*      */   private boolean honorsVisibility = true;
/*      */   private transient List[] colComponents;
/*      */   private transient List[] rowComponents;
/*      */   private final ComponentSizeCache componentSizeCache;
/*      */   private final Measure minimumWidthMeasure;
/*      */   private final Measure minimumHeightMeasure;
/*      */   private final Measure preferredWidthMeasure;
/*      */   private final Measure preferredHeightMeasure;
/*      */   
/*      */   public FormLayout() {
/*  248 */     this(new ColumnSpec[0], new RowSpec[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FormLayout(String encodedColumnSpecs) {
/*  282 */     this(encodedColumnSpecs, LayoutMap.getRoot());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FormLayout(String encodedColumnSpecs, LayoutMap layoutMap) {
/*  322 */     this(ColumnSpec.decodeSpecs(encodedColumnSpecs, layoutMap), new RowSpec[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FormLayout(String encodedColumnSpecs, String encodedRowSpecs) {
/*  359 */     this(encodedColumnSpecs, encodedRowSpecs, LayoutMap.getRoot());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FormLayout(String encodedColumnSpecs, String encodedRowSpecs, LayoutMap layoutMap) {
/*  402 */     this(ColumnSpec.decodeSpecs(encodedColumnSpecs, layoutMap), RowSpec.decodeSpecs(encodedRowSpecs, layoutMap));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FormLayout(ColumnSpec[] colSpecs) {
/*  418 */     this(colSpecs, new RowSpec[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FormLayout(ColumnSpec[] colSpecs, RowSpec[] rowSpecs) {
/*  430 */     if (colSpecs == null)
/*  431 */       throw new NullPointerException("The column specifications must not be null."); 
/*  432 */     if (rowSpecs == null) {
/*  433 */       throw new NullPointerException("The row specifications must not be null.");
/*      */     }
/*  435 */     this.colSpecs = new ArrayList(Arrays.asList((Object[])colSpecs));
/*  436 */     this.rowSpecs = new ArrayList(Arrays.asList((Object[])rowSpecs));
/*  437 */     this.colGroupIndices = new int[0][];
/*  438 */     this.rowGroupIndices = new int[0][];
/*  439 */     int initialCapacity = colSpecs.length * rowSpecs.length / 4;
/*  440 */     this.constraintMap = new HashMap(initialCapacity);
/*  441 */     this.componentSizeCache = new ComponentSizeCache(initialCapacity);
/*  442 */     this.minimumWidthMeasure = new MinimumWidthMeasure(this.componentSizeCache);
/*  443 */     this.minimumHeightMeasure = new MinimumHeightMeasure(this.componentSizeCache);
/*  444 */     this.preferredWidthMeasure = new PreferredWidthMeasure(this.componentSizeCache);
/*  445 */     this.preferredHeightMeasure = new PreferredHeightMeasure(this.componentSizeCache);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getColumnCount() {
/*  457 */     return this.colSpecs.size();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ColumnSpec getColumnSpec(int columnIndex) {
/*  469 */     return this.colSpecs.get(columnIndex - 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColumnSpec(int columnIndex, ColumnSpec columnSpec) {
/*  482 */     if (columnSpec == null) {
/*  483 */       throw new NullPointerException("The column spec must not be null.");
/*      */     }
/*  485 */     this.colSpecs.set(columnIndex - 1, columnSpec);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void appendColumn(ColumnSpec columnSpec) {
/*  497 */     if (columnSpec == null) {
/*  498 */       throw new NullPointerException("The column spec must not be null.");
/*      */     }
/*  500 */     this.colSpecs.add(columnSpec);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void insertColumn(int columnIndex, ColumnSpec columnSpec) {
/*  521 */     if (columnIndex < 1 || columnIndex > getColumnCount()) {
/*  522 */       throw new IndexOutOfBoundsException("The column index " + columnIndex + "must be in the range [1, " + getColumnCount() + "].");
/*      */     }
/*      */ 
/*      */     
/*  526 */     this.colSpecs.add(columnIndex - 1, columnSpec);
/*  527 */     shiftComponentsHorizontally(columnIndex, false);
/*  528 */     adjustGroupIndices(this.colGroupIndices, columnIndex, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeColumn(int columnIndex) {
/*  566 */     if (columnIndex < 1 || columnIndex > getColumnCount()) {
/*  567 */       throw new IndexOutOfBoundsException("The column index " + columnIndex + " must be in the range [1, " + getColumnCount() + "].");
/*      */     }
/*      */ 
/*      */     
/*  571 */     this.colSpecs.remove(columnIndex - 1);
/*  572 */     shiftComponentsHorizontally(columnIndex, true);
/*  573 */     adjustGroupIndices(this.colGroupIndices, columnIndex, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRowCount() {
/*  583 */     return this.rowSpecs.size();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RowSpec getRowSpec(int rowIndex) {
/*  595 */     return this.rowSpecs.get(rowIndex - 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRowSpec(int rowIndex, RowSpec rowSpec) {
/*  608 */     if (rowSpec == null) {
/*  609 */       throw new NullPointerException("The row spec must not be null.");
/*      */     }
/*  611 */     this.rowSpecs.set(rowIndex - 1, rowSpec);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void appendRow(RowSpec rowSpec) {
/*  622 */     if (rowSpec == null) {
/*  623 */       throw new NullPointerException("The row spec must not be null.");
/*      */     }
/*  625 */     this.rowSpecs.add(rowSpec);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void insertRow(int rowIndex, RowSpec rowSpec) {
/*  646 */     if (rowIndex < 1 || rowIndex > getRowCount()) {
/*  647 */       throw new IndexOutOfBoundsException("The row index " + rowIndex + " must be in the range [1, " + getRowCount() + "].");
/*      */     }
/*      */ 
/*      */     
/*  651 */     this.rowSpecs.add(rowIndex - 1, rowSpec);
/*  652 */     shiftComponentsVertically(rowIndex, false);
/*  653 */     adjustGroupIndices(this.rowGroupIndices, rowIndex, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeRow(int rowIndex) {
/*  688 */     if (rowIndex < 1 || rowIndex > getRowCount()) {
/*  689 */       throw new IndexOutOfBoundsException("The row index " + rowIndex + "must be in the range [1, " + getRowCount() + "].");
/*      */     }
/*      */ 
/*      */     
/*  693 */     this.rowSpecs.remove(rowIndex - 1);
/*  694 */     shiftComponentsVertically(rowIndex, true);
/*  695 */     adjustGroupIndices(this.rowGroupIndices, rowIndex, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void shiftComponentsHorizontally(int columnIndex, boolean remove) {
/*  708 */     int offset = remove ? -1 : 1;
/*  709 */     for (Iterator i = this.constraintMap.entrySet().iterator(); i.hasNext(); ) {
/*  710 */       Map.Entry entry = i.next();
/*  711 */       CellConstraints constraints = (CellConstraints)entry.getValue();
/*  712 */       int x1 = constraints.gridX;
/*  713 */       int w = constraints.gridWidth;
/*  714 */       int x2 = x1 + w - 1;
/*  715 */       if (x1 == columnIndex && remove) {
/*  716 */         throw new IllegalStateException("The removed column " + columnIndex + " must not contain component origins.\n" + "Illegal component=" + entry.getKey());
/*      */       }
/*      */ 
/*      */       
/*  720 */       if (x1 >= columnIndex) {
/*  721 */         constraints.gridX += offset; continue;
/*  722 */       }  if (x2 >= columnIndex) {
/*  723 */         constraints.gridWidth += offset;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void shiftComponentsVertically(int rowIndex, boolean remove) {
/*  737 */     int offset = remove ? -1 : 1;
/*  738 */     for (Iterator i = this.constraintMap.entrySet().iterator(); i.hasNext(); ) {
/*  739 */       Map.Entry entry = i.next();
/*  740 */       CellConstraints constraints = (CellConstraints)entry.getValue();
/*  741 */       int y1 = constraints.gridY;
/*  742 */       int h = constraints.gridHeight;
/*  743 */       int y2 = y1 + h - 1;
/*  744 */       if (y1 == rowIndex && remove) {
/*  745 */         throw new IllegalStateException("The removed row " + rowIndex + " must not contain component origins.\n" + "Illegal component=" + entry.getKey());
/*      */       }
/*      */ 
/*      */       
/*  749 */       if (y1 >= rowIndex) {
/*  750 */         constraints.gridY += offset; continue;
/*  751 */       }  if (y2 >= rowIndex) {
/*  752 */         constraints.gridHeight += offset;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void adjustGroupIndices(int[][] allGroupIndices, int modifiedIndex, boolean remove) {
/*  768 */     int offset = remove ? -1 : 1;
/*  769 */     for (int group = 0; group < allGroupIndices.length; group++) {
/*  770 */       int[] groupIndices = allGroupIndices[group];
/*  771 */       for (int i = 0; i < groupIndices.length; i++) {
/*  772 */         int index = groupIndices[i];
/*  773 */         if (index == modifiedIndex && remove) {
/*  774 */           throw new IllegalStateException("The removed index " + modifiedIndex + " must not be grouped.");
/*      */         }
/*  776 */         if (index >= modifiedIndex) {
/*  777 */           groupIndices[i] = groupIndices[i] + offset;
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CellConstraints getConstraints(Component component) {
/*  796 */     return (CellConstraints)getConstraints0(component).clone();
/*      */   }
/*      */ 
/*      */   
/*      */   private CellConstraints getConstraints0(Component component) {
/*  801 */     if (component == null) {
/*  802 */       throw new NullPointerException("The component must not be null.");
/*      */     }
/*  804 */     CellConstraints constraints = (CellConstraints)this.constraintMap.get(component);
/*  805 */     if (constraints == null) {
/*  806 */       throw new NullPointerException("The component has not been added to the container.");
/*      */     }
/*  808 */     return constraints;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConstraints(Component component, CellConstraints constraints) {
/*  821 */     if (component == null)
/*  822 */       throw new NullPointerException("The component must not be null."); 
/*  823 */     if (constraints == null) {
/*  824 */       throw new NullPointerException("The constraints must not be null.");
/*      */     }
/*  826 */     constraints.ensureValidGridBounds(getColumnCount(), getRowCount());
/*  827 */     this.constraintMap.put(component, constraints.clone());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void removeConstraints(Component component) {
/*  837 */     this.constraintMap.remove(component);
/*  838 */     this.componentSizeCache.removeEntry(component);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[][] getColumnGroups() {
/*  850 */     return deepClone(this.colGroupIndices);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColumnGroups(int[][] colGroupIndices) {
/*  872 */     int maxColumn = getColumnCount();
/*  873 */     boolean[] usedIndices = new boolean[maxColumn + 1];
/*  874 */     for (int group = 0; group < colGroupIndices.length; group++) {
/*  875 */       for (int j = 0; j < (colGroupIndices[group]).length; j++) {
/*  876 */         int colIndex = colGroupIndices[group][j];
/*  877 */         if (colIndex < 1 || colIndex > maxColumn) {
/*  878 */           throw new IndexOutOfBoundsException("Invalid column group index " + colIndex + " in group " + (group + 1));
/*      */         }
/*      */ 
/*      */         
/*  882 */         if (usedIndices[colIndex]) {
/*  883 */           throw new IllegalArgumentException("Column index " + colIndex + " must not be used in multiple column groups.");
/*      */         }
/*      */         
/*  886 */         usedIndices[colIndex] = true;
/*      */       } 
/*      */     } 
/*  889 */     this.colGroupIndices = deepClone(colGroupIndices);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addGroupedColumn(int columnIndex) {
/*  899 */     int[][] newColGroups = getColumnGroups();
/*      */     
/*  901 */     if (newColGroups.length == 0) {
/*  902 */       newColGroups = new int[][] { { columnIndex } };
/*      */     } else {
/*  904 */       int lastGroupIndex = newColGroups.length - 1;
/*  905 */       int[] lastGroup = newColGroups[lastGroupIndex];
/*  906 */       int groupSize = lastGroup.length;
/*  907 */       int[] newLastGroup = new int[groupSize + 1];
/*  908 */       System.arraycopy(lastGroup, 0, newLastGroup, 0, groupSize);
/*  909 */       newLastGroup[groupSize] = columnIndex;
/*  910 */       newColGroups[lastGroupIndex] = newLastGroup;
/*      */     } 
/*  912 */     setColumnGroups(newColGroups);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[][] getRowGroups() {
/*  921 */     return deepClone(this.rowGroupIndices);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRowGroups(int[][] rowGroupIndices) {
/*  942 */     int rowCount = getRowCount();
/*  943 */     boolean[] usedIndices = new boolean[rowCount + 1];
/*  944 */     for (int i = 0; i < rowGroupIndices.length; i++) {
/*  945 */       for (int j = 0; j < (rowGroupIndices[i]).length; j++) {
/*  946 */         int rowIndex = rowGroupIndices[i][j];
/*  947 */         if (rowIndex < 1 || rowIndex > rowCount) {
/*  948 */           throw new IndexOutOfBoundsException("Invalid row group index " + rowIndex + " in group " + (i + 1));
/*      */         }
/*      */ 
/*      */         
/*  952 */         if (usedIndices[rowIndex]) {
/*  953 */           throw new IllegalArgumentException("Row index " + rowIndex + " must not be used in multiple row groups.");
/*      */         }
/*      */         
/*  956 */         usedIndices[rowIndex] = true;
/*      */       } 
/*      */     } 
/*  959 */     this.rowGroupIndices = deepClone(rowGroupIndices);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addGroupedRow(int rowIndex) {
/*  969 */     int[][] newRowGroups = getRowGroups();
/*      */     
/*  971 */     if (newRowGroups.length == 0) {
/*  972 */       newRowGroups = new int[][] { { rowIndex } };
/*      */     } else {
/*  974 */       int lastGroupIndex = newRowGroups.length - 1;
/*  975 */       int[] lastGroup = newRowGroups[lastGroupIndex];
/*  976 */       int groupSize = lastGroup.length;
/*  977 */       int[] newLastGroup = new int[groupSize + 1];
/*  978 */       System.arraycopy(lastGroup, 0, newLastGroup, 0, groupSize);
/*  979 */       newLastGroup[groupSize] = rowIndex;
/*  980 */       newRowGroups[lastGroupIndex] = newLastGroup;
/*      */     } 
/*  982 */     setRowGroups(newRowGroups);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getHonorsVisibility() {
/* 1001 */     return this.honorsVisibility;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHonorsVisibility(boolean b) {
/* 1037 */     boolean oldHonorsVisibility = getHonorsVisibility();
/* 1038 */     if (oldHonorsVisibility == b)
/*      */       return; 
/* 1040 */     this.honorsVisibility = b;
/* 1041 */     Set componentSet = this.constraintMap.keySet();
/* 1042 */     if (componentSet.isEmpty())
/*      */       return; 
/* 1044 */     Component firstComponent = componentSet.iterator().next();
/* 1045 */     Container container = firstComponent.getParent();
/* 1046 */     invalidateAndRepaint(container);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHonorsVisibility(Component component, Boolean b) {
/* 1066 */     CellConstraints constraints = getConstraints0(component);
/* 1067 */     if (FormUtils.equals(b, constraints.honorsVisibility))
/*      */       return; 
/* 1069 */     constraints.honorsVisibility = b;
/* 1070 */     invalidateAndRepaint(component.getParent());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addLayoutComponent(String name, Component component) {
/* 1085 */     throw new UnsupportedOperationException("Use #addLayoutComponent(Component, Object) instead.");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addLayoutComponent(Component comp, Object constraints) {
/* 1102 */     if (constraints instanceof String)
/* 1103 */     { setConstraints(comp, new CellConstraints((String)constraints)); }
/* 1104 */     else if (constraints instanceof CellConstraints)
/* 1105 */     { setConstraints(comp, (CellConstraints)constraints); }
/* 1106 */     else { if (constraints == null) {
/* 1107 */         throw new NullPointerException("The constraints must not be null.");
/*      */       }
/* 1109 */       throw new IllegalArgumentException("Illegal constraint type " + constraints.getClass()); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeLayoutComponent(Component comp) {
/* 1123 */     removeConstraints(comp);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Dimension minimumLayoutSize(Container parent) {
/* 1141 */     return computeLayoutSize(parent, this.minimumWidthMeasure, this.minimumHeightMeasure);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Dimension preferredLayoutSize(Container parent) {
/* 1158 */     return computeLayoutSize(parent, this.preferredWidthMeasure, this.preferredHeightMeasure);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Dimension maximumLayoutSize(Container target) {
/* 1174 */     return new Dimension(2147483647, 2147483647);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getLayoutAlignmentX(Container parent) {
/* 1188 */     return 0.5F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getLayoutAlignmentY(Container parent) {
/* 1202 */     return 0.5F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void invalidateLayout(Container target) {
/* 1213 */     invalidateCaches();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void layoutContainer(Container parent) {
/* 1241 */     synchronized (parent.getTreeLock()) {
/* 1242 */       initializeColAndRowComponentLists();
/* 1243 */       Dimension size = parent.getSize();
/*      */       
/* 1245 */       Insets insets = parent.getInsets();
/* 1246 */       int totalWidth = size.width - insets.left - insets.right;
/* 1247 */       int totalHeight = size.height - insets.top - insets.bottom;
/*      */       
/* 1249 */       int[] x = computeGridOrigins(parent, totalWidth, insets.left, this.colSpecs, this.colComponents, this.colGroupIndices, this.minimumWidthMeasure, this.preferredWidthMeasure);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1257 */       int[] y = computeGridOrigins(parent, totalHeight, insets.top, this.rowSpecs, this.rowComponents, this.rowGroupIndices, this.minimumHeightMeasure, this.preferredHeightMeasure);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1266 */       layoutComponents(x, y);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initializeColAndRowComponentLists() {
/* 1281 */     this.colComponents = new List[getColumnCount()]; int j;
/* 1282 */     for (j = 0; j < getColumnCount(); j++) {
/* 1283 */       this.colComponents[j] = new ArrayList();
/*      */     }
/*      */     
/* 1286 */     this.rowComponents = new List[getRowCount()];
/* 1287 */     for (j = 0; j < getRowCount(); j++) {
/* 1288 */       this.rowComponents[j] = new ArrayList();
/*      */     }
/*      */     
/* 1291 */     for (Iterator i = this.constraintMap.entrySet().iterator(); i.hasNext(); ) {
/* 1292 */       Map.Entry entry = i.next();
/* 1293 */       Component component = (Component)entry.getKey();
/* 1294 */       CellConstraints constraints = (CellConstraints)entry.getValue();
/* 1295 */       if (takeIntoAccount(component, constraints)) {
/* 1296 */         if (constraints.gridWidth == 1) {
/* 1297 */           this.colComponents[constraints.gridX - 1].add(component);
/*      */         }
/* 1299 */         if (constraints.gridHeight == 1) {
/* 1300 */           this.rowComponents[constraints.gridY - 1].add(component);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Dimension computeLayoutSize(Container parent, Measure defaultWidthMeasure, Measure defaultHeightMeasure) {
/* 1318 */     synchronized (parent.getTreeLock()) {
/* 1319 */       initializeColAndRowComponentLists();
/* 1320 */       int[] colWidths = maximumSizes(parent, this.colSpecs, this.colComponents, this.minimumWidthMeasure, this.preferredWidthMeasure, defaultWidthMeasure);
/*      */ 
/*      */ 
/*      */       
/* 1324 */       int[] rowHeights = maximumSizes(parent, this.rowSpecs, this.rowComponents, this.minimumHeightMeasure, this.preferredHeightMeasure, defaultHeightMeasure);
/*      */ 
/*      */ 
/*      */       
/* 1328 */       int[] groupedWidths = groupedSizes(this.colGroupIndices, colWidths);
/* 1329 */       int[] groupedHeights = groupedSizes(this.rowGroupIndices, rowHeights);
/*      */ 
/*      */       
/* 1332 */       int[] xOrigins = computeOrigins(groupedWidths, 0);
/* 1333 */       int[] yOrigins = computeOrigins(groupedHeights, 0);
/*      */       
/* 1335 */       int width1 = sum(groupedWidths);
/* 1336 */       int height1 = sum(groupedHeights);
/* 1337 */       int maxWidth = width1;
/* 1338 */       int maxHeight = height1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1347 */       int[] maxFixedSizeColsTable = computeMaximumFixedSpanTable(this.colSpecs);
/* 1348 */       int[] maxFixedSizeRowsTable = computeMaximumFixedSpanTable(this.rowSpecs);
/*      */       
/* 1350 */       for (Iterator i = this.constraintMap.entrySet().iterator(); i.hasNext(); ) {
/* 1351 */         Map.Entry entry = i.next();
/* 1352 */         Component component = (Component)entry.getKey();
/* 1353 */         CellConstraints constraints = (CellConstraints)entry.getValue();
/* 1354 */         if (!takeIntoAccount(component, constraints)) {
/*      */           continue;
/*      */         }
/* 1357 */         if (constraints.gridWidth > 1 && constraints.gridWidth > maxFixedSizeColsTable[constraints.gridX - 1]) {
/*      */ 
/*      */           
/* 1360 */           int compWidth = defaultWidthMeasure.sizeOf(component);
/*      */           
/* 1362 */           int gridX1 = constraints.gridX - 1;
/* 1363 */           int gridX2 = gridX1 + constraints.gridWidth;
/* 1364 */           int lead = xOrigins[gridX1];
/* 1365 */           int trail = width1 - xOrigins[gridX2];
/* 1366 */           int myWidth = lead + compWidth + trail;
/* 1367 */           if (myWidth > maxWidth) {
/* 1368 */             maxWidth = myWidth;
/*      */           }
/*      */         } 
/*      */         
/* 1372 */         if (constraints.gridHeight > 1 && constraints.gridHeight > maxFixedSizeRowsTable[constraints.gridY - 1]) {
/*      */ 
/*      */           
/* 1375 */           int compHeight = defaultHeightMeasure.sizeOf(component);
/*      */           
/* 1377 */           int gridY1 = constraints.gridY - 1;
/* 1378 */           int gridY2 = gridY1 + constraints.gridHeight;
/* 1379 */           int lead = yOrigins[gridY1];
/* 1380 */           int trail = height1 - yOrigins[gridY2];
/* 1381 */           int myHeight = lead + compHeight + trail;
/* 1382 */           if (myHeight > maxHeight) {
/* 1383 */             maxHeight = myHeight;
/*      */           }
/*      */         } 
/*      */       } 
/* 1387 */       Insets insets = parent.getInsets();
/* 1388 */       int width = maxWidth + insets.left + insets.right;
/* 1389 */       int height = maxHeight + insets.top + insets.bottom;
/* 1390 */       return new Dimension(width, height);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] computeGridOrigins(Container container, int totalSize, int offset, List formSpecs, List[] componentLists, int[][] groupIndices, Measure minMeasure, Measure prefMeasure) {
/* 1417 */     int[] minSizes = maximumSizes(container, formSpecs, componentLists, minMeasure, prefMeasure, minMeasure);
/*      */     
/* 1419 */     int[] prefSizes = maximumSizes(container, formSpecs, componentLists, minMeasure, prefMeasure, prefMeasure);
/*      */ 
/*      */     
/* 1422 */     int[] groupedMinSizes = groupedSizes(groupIndices, minSizes);
/* 1423 */     int[] groupedPrefSizes = groupedSizes(groupIndices, prefSizes);
/* 1424 */     int totalMinSize = sum(groupedMinSizes);
/* 1425 */     int totalPrefSize = sum(groupedPrefSizes);
/* 1426 */     int[] compressedSizes = compressedSizes(formSpecs, totalSize, totalMinSize, totalPrefSize, groupedMinSizes, prefSizes);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1432 */     int[] groupedSizes = groupedSizes(groupIndices, compressedSizes);
/* 1433 */     int totalGroupedSize = sum(groupedSizes);
/* 1434 */     int[] sizes = distributedSizes(formSpecs, totalSize, totalGroupedSize, groupedSizes);
/*      */ 
/*      */ 
/*      */     
/* 1438 */     return computeOrigins(sizes, offset);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] computeOrigins(int[] sizes, int offset) {
/* 1450 */     int count = sizes.length;
/* 1451 */     int[] origins = new int[count + 1];
/* 1452 */     origins[0] = offset;
/* 1453 */     for (int i = 1; i <= count; i++) {
/* 1454 */       origins[i] = origins[i - 1] + sizes[i - 1];
/*      */     }
/* 1456 */     return origins;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void layoutComponents(int[] x, int[] y) {
/* 1476 */     Rectangle cellBounds = new Rectangle();
/* 1477 */     for (Iterator i = this.constraintMap.entrySet().iterator(); i.hasNext(); ) {
/* 1478 */       Map.Entry entry = i.next();
/* 1479 */       Component component = (Component)entry.getKey();
/* 1480 */       CellConstraints constraints = (CellConstraints)entry.getValue();
/*      */       
/* 1482 */       int gridX = constraints.gridX - 1;
/* 1483 */       int gridY = constraints.gridY - 1;
/* 1484 */       int gridWidth = constraints.gridWidth;
/* 1485 */       int gridHeight = constraints.gridHeight;
/* 1486 */       cellBounds.x = x[gridX];
/* 1487 */       cellBounds.y = y[gridY];
/* 1488 */       cellBounds.width = x[gridX + gridWidth] - cellBounds.x;
/* 1489 */       cellBounds.height = y[gridY + gridHeight] - cellBounds.y;
/*      */       
/* 1491 */       constraints.setBounds(component, this, cellBounds, this.minimumWidthMeasure, this.minimumHeightMeasure, this.preferredWidthMeasure, this.preferredHeightMeasure);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void invalidateCaches() {
/* 1502 */     this.componentSizeCache.invalidate();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] maximumSizes(Container container, List formSpecs, List[] componentLists, Measure minMeasure, Measure prefMeasure, Measure defaultMeasure) {
/* 1525 */     int size = formSpecs.size();
/* 1526 */     int[] result = new int[size];
/* 1527 */     for (int i = 0; i < size; i++) {
/* 1528 */       FormSpec formSpec = formSpecs.get(i);
/* 1529 */       result[i] = formSpec.maximumSize(container, componentLists[i], minMeasure, prefMeasure, defaultMeasure);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1535 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] compressedSizes(List formSpecs, int totalSize, int totalMinSize, int totalPrefSize, int[] minSizes, int[] prefSizes) {
/* 1561 */     if (totalSize < totalMinSize) {
/* 1562 */       return minSizes;
/*      */     }
/* 1564 */     if (totalSize >= totalPrefSize) {
/* 1565 */       return prefSizes;
/*      */     }
/* 1567 */     int count = formSpecs.size();
/* 1568 */     int[] sizes = new int[count];
/*      */     
/* 1570 */     double totalCompressionSpace = (totalPrefSize - totalSize);
/* 1571 */     double maxCompressionSpace = (totalPrefSize - totalMinSize);
/* 1572 */     double compressionFactor = totalCompressionSpace / maxCompressionSpace;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1578 */     for (int i = 0; i < count; i++) {
/* 1579 */       FormSpec formSpec = formSpecs.get(i);
/* 1580 */       sizes[i] = prefSizes[i];
/* 1581 */       if (formSpec.getSize().compressible()) {
/* 1582 */         sizes[i] = sizes[i] - (int)Math.round((prefSizes[i] - minSizes[i]) * compressionFactor);
/*      */       }
/*      */     } 
/*      */     
/* 1586 */     return sizes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] groupedSizes(int[][] groups, int[] rawSizes) {
/* 1600 */     if (groups == null || groups.length == 0) {
/* 1601 */       return rawSizes;
/*      */     }
/*      */ 
/*      */     
/* 1605 */     int[] sizes = new int[rawSizes.length];
/* 1606 */     for (int i = 0; i < sizes.length; i++) {
/* 1607 */       sizes[i] = rawSizes[i];
/*      */     }
/*      */ 
/*      */     
/* 1611 */     for (int group = 0; group < groups.length; group++) {
/* 1612 */       int[] groupIndices = groups[group];
/* 1613 */       int groupMaxSize = 0;
/*      */       int j;
/* 1615 */       for (j = 0; j < groupIndices.length; j++) {
/* 1616 */         int index = groupIndices[j] - 1;
/* 1617 */         groupMaxSize = Math.max(groupMaxSize, sizes[index]);
/*      */       } 
/*      */       
/* 1620 */       for (j = 0; j < groupIndices.length; j++) {
/* 1621 */         int index = groupIndices[j] - 1;
/* 1622 */         sizes[index] = groupMaxSize;
/*      */       } 
/*      */     } 
/* 1625 */     return sizes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] distributedSizes(List formSpecs, int totalSize, int totalPrefSize, int[] inputSizes) {
/* 1642 */     double totalFreeSpace = (totalSize - totalPrefSize);
/*      */     
/* 1644 */     if (totalFreeSpace < 0.0D) {
/* 1645 */       return inputSizes;
/*      */     }
/*      */     
/* 1648 */     int count = formSpecs.size();
/* 1649 */     double totalWeight = 0.0D;
/* 1650 */     for (int i = 0; i < count; i++) {
/* 1651 */       FormSpec formSpec = formSpecs.get(i);
/* 1652 */       totalWeight += formSpec.getResizeWeight();
/*      */     } 
/*      */ 
/*      */     
/* 1656 */     if (totalWeight == 0.0D) {
/* 1657 */       return inputSizes;
/*      */     }
/* 1659 */     int[] sizes = new int[count];
/*      */     
/* 1661 */     double restSpace = totalFreeSpace;
/* 1662 */     int roundedRestSpace = (int)totalFreeSpace;
/* 1663 */     for (int j = 0; j < count; j++) {
/* 1664 */       FormSpec formSpec = formSpecs.get(j);
/* 1665 */       double weight = formSpec.getResizeWeight();
/* 1666 */       if (weight == 0.0D) {
/* 1667 */         sizes[j] = inputSizes[j];
/*      */       } else {
/* 1669 */         double roundingCorrection = restSpace - roundedRestSpace;
/* 1670 */         double extraSpace = totalFreeSpace * weight / totalWeight;
/* 1671 */         double correctedExtraSpace = extraSpace - roundingCorrection;
/* 1672 */         int roundedExtraSpace = (int)Math.round(correctedExtraSpace);
/* 1673 */         sizes[j] = inputSizes[j] + roundedExtraSpace;
/* 1674 */         restSpace -= extraSpace;
/* 1675 */         roundedRestSpace -= roundedExtraSpace;
/*      */       } 
/*      */     } 
/* 1678 */     return sizes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] computeMaximumFixedSpanTable(List formSpecs) {
/* 1707 */     int size = formSpecs.size();
/* 1708 */     int[] table = new int[size];
/* 1709 */     int maximumFixedSpan = Integer.MAX_VALUE;
/* 1710 */     for (int i = size - 1; i >= 0; i--) {
/* 1711 */       FormSpec spec = formSpecs.get(i);
/* 1712 */       if (spec.canGrow()) {
/* 1713 */         maximumFixedSpan = 0;
/*      */       }
/* 1715 */       table[i] = maximumFixedSpan;
/* 1716 */       if (maximumFixedSpan < Integer.MAX_VALUE)
/* 1717 */         maximumFixedSpan++; 
/*      */     } 
/* 1719 */     return table;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int sum(int[] sizes) {
/* 1732 */     int sum = 0;
/* 1733 */     for (int i = sizes.length - 1; i >= 0; i--) {
/* 1734 */       sum += sizes[i];
/*      */     }
/* 1736 */     return sum;
/*      */   }
/*      */ 
/*      */   
/*      */   private static void invalidateAndRepaint(Container container) {
/* 1741 */     if (container == null)
/*      */       return; 
/* 1743 */     if (container instanceof JComponent) {
/* 1744 */       ((JComponent)container).revalidate();
/*      */     } else {
/* 1746 */       container.invalidate();
/*      */     } 
/* 1748 */     container.repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean takeIntoAccount(Component component, CellConstraints cc) {
/* 1765 */     return (component.isVisible() || (cc.honorsVisibility == null && !getHonorsVisibility()) || Boolean.FALSE.equals(cc.honorsVisibility));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static abstract class CachingMeasure
/*      */     implements Measure, Serializable
/*      */   {
/*      */     protected final FormLayout.ComponentSizeCache cache;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private CachingMeasure(FormLayout.ComponentSizeCache cache) {
/* 1805 */       this.cache = cache;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class MinimumWidthMeasure
/*      */     extends CachingMeasure
/*      */   {
/*      */     private MinimumWidthMeasure(FormLayout.ComponentSizeCache cache) {
/* 1816 */       super(cache);
/*      */     }
/*      */     public int sizeOf(Component c) {
/* 1819 */       return (this.cache.getMinimumSize(c)).width;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class MinimumHeightMeasure
/*      */     extends CachingMeasure
/*      */   {
/*      */     private MinimumHeightMeasure(FormLayout.ComponentSizeCache cache) {
/* 1829 */       super(cache);
/*      */     }
/*      */     public int sizeOf(Component c) {
/* 1832 */       return (this.cache.getMinimumSize(c)).height;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class PreferredWidthMeasure
/*      */     extends CachingMeasure
/*      */   {
/*      */     private PreferredWidthMeasure(FormLayout.ComponentSizeCache cache) {
/* 1842 */       super(cache);
/*      */     }
/*      */     public int sizeOf(Component c) {
/* 1845 */       return (this.cache.getPreferredSize(c)).width;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class PreferredHeightMeasure
/*      */     extends CachingMeasure
/*      */   {
/*      */     private PreferredHeightMeasure(FormLayout.ComponentSizeCache cache) {
/* 1855 */       super(cache);
/*      */     }
/*      */     public int sizeOf(Component c) {
/* 1858 */       return (this.cache.getPreferredSize(c)).height;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class ComponentSizeCache
/*      */     implements Serializable
/*      */   {
/*      */     private final Map minimumSizes;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final Map preferredSizes;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private ComponentSizeCache(int initialCapacity) {
/* 1883 */       this.minimumSizes = new HashMap(initialCapacity);
/* 1884 */       this.preferredSizes = new HashMap(initialCapacity);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void invalidate() {
/* 1891 */       this.minimumSizes.clear();
/* 1892 */       this.preferredSizes.clear();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Dimension getMinimumSize(Component component) {
/* 1904 */       Dimension size = (Dimension)this.minimumSizes.get(component);
/* 1905 */       if (size == null) {
/* 1906 */         size = component.getMinimumSize();
/* 1907 */         this.minimumSizes.put(component, size);
/*      */       } 
/* 1909 */       return size;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Dimension getPreferredSize(Component component) {
/* 1921 */       Dimension size = (Dimension)this.preferredSizes.get(component);
/* 1922 */       if (size == null) {
/* 1923 */         size = component.getPreferredSize();
/* 1924 */         this.preferredSizes.put(component, size);
/*      */       } 
/* 1926 */       return size;
/*      */     }
/*      */     
/*      */     void removeEntry(Component component) {
/* 1930 */       this.minimumSizes.remove(component);
/* 1931 */       this.preferredSizes.remove(component);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public LayoutInfo getLayoutInfo(Container parent) {
/* 1952 */     synchronized (parent.getTreeLock()) {
/* 1953 */       initializeColAndRowComponentLists();
/* 1954 */       Dimension size = parent.getSize();
/*      */       
/* 1956 */       Insets insets = parent.getInsets();
/* 1957 */       int totalWidth = size.width - insets.left - insets.right;
/* 1958 */       int totalHeight = size.height - insets.top - insets.bottom;
/*      */       
/* 1960 */       int[] x = computeGridOrigins(parent, totalWidth, insets.left, this.colSpecs, this.colComponents, this.colGroupIndices, this.minimumWidthMeasure, this.preferredWidthMeasure);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1968 */       int[] y = computeGridOrigins(parent, totalHeight, insets.top, this.rowSpecs, this.rowComponents, this.rowGroupIndices, this.minimumHeightMeasure, this.preferredHeightMeasure);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1976 */       return new LayoutInfo(x, y);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final class LayoutInfo
/*      */   {
/*      */     public final int[] columnOrigins;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final int[] rowOrigins;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private LayoutInfo(int[] xOrigins, int[] yOrigins) {
/* 1997 */       this.columnOrigins = xOrigins;
/* 1998 */       this.rowOrigins = yOrigins;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getX() {
/* 2007 */       return this.columnOrigins[0];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getY() {
/* 2016 */       return this.rowOrigins[0];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getWidth() {
/* 2026 */       return this.columnOrigins[this.columnOrigins.length - 1] - this.columnOrigins[0];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getHeight() {
/* 2035 */       return this.rowOrigins[this.rowOrigins.length - 1] - this.rowOrigins[0];
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[][] deepClone(int[][] array) {
/* 2054 */     int[][] result = new int[array.length][];
/* 2055 */     for (int i = 0; i < result.length; i++) {
/* 2056 */       result[i] = (int[])array[i].clone();
/*      */     }
/* 2058 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 2073 */     invalidateCaches();
/* 2074 */     out.defaultWriteObject();
/*      */   }
/*      */   
/*      */   public static interface Measure {
/*      */     int sizeOf(Component param1Component);
/*      */   }
/*      */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\layout\FormLayout.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */