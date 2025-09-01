package com.jgoodies.forms.layout;

import com.jgoodies.forms.util.FormUtils;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.swing.JComponent;

public final class FormLayout implements LayoutManager2, Serializable {
   private final List colSpecs;
   private final List rowSpecs;
   private int[][] colGroupIndices;
   private int[][] rowGroupIndices;
   private final Map constraintMap;
   private boolean honorsVisibility;
   private transient List[] colComponents;
   private transient List[] rowComponents;
   private final FormLayout.ComponentSizeCache componentSizeCache;
   private final FormLayout.Measure minimumWidthMeasure;
   private final FormLayout.Measure minimumHeightMeasure;
   private final FormLayout.Measure preferredWidthMeasure;
   private final FormLayout.Measure preferredHeightMeasure;

   public FormLayout() {
      this(new ColumnSpec[0], new RowSpec[0]);
   }

   public FormLayout(String encodedColumnSpecs) {
      this(encodedColumnSpecs, LayoutMap.getRoot());
   }

   public FormLayout(String encodedColumnSpecs, LayoutMap layoutMap) {
      this(ColumnSpec.decodeSpecs(encodedColumnSpecs, layoutMap), new RowSpec[0]);
   }

   public FormLayout(String encodedColumnSpecs, String encodedRowSpecs) {
      this(encodedColumnSpecs, encodedRowSpecs, LayoutMap.getRoot());
   }

   public FormLayout(String encodedColumnSpecs, String encodedRowSpecs, LayoutMap layoutMap) {
      this(ColumnSpec.decodeSpecs(encodedColumnSpecs, layoutMap), RowSpec.decodeSpecs(encodedRowSpecs, layoutMap));
   }

   public FormLayout(ColumnSpec[] colSpecs) {
      this(colSpecs, new RowSpec[0]);
   }

   public FormLayout(ColumnSpec[] colSpecs, RowSpec[] rowSpecs) {
      this.honorsVisibility = true;
      if (colSpecs == null) {
         throw new NullPointerException("The column specifications must not be null.");
      } else if (rowSpecs == null) {
         throw new NullPointerException("The row specifications must not be null.");
      } else {
         this.colSpecs = new ArrayList(Arrays.asList(colSpecs));
         this.rowSpecs = new ArrayList(Arrays.asList(rowSpecs));
         this.colGroupIndices = new int[0][];
         this.rowGroupIndices = new int[0][];
         int initialCapacity = colSpecs.length * rowSpecs.length / 4;
         this.constraintMap = new HashMap(initialCapacity);
         this.componentSizeCache = new FormLayout.ComponentSizeCache(initialCapacity);
         this.minimumWidthMeasure = new FormLayout.MinimumWidthMeasure(this.componentSizeCache);
         this.minimumHeightMeasure = new FormLayout.MinimumHeightMeasure(this.componentSizeCache);
         this.preferredWidthMeasure = new FormLayout.PreferredWidthMeasure(this.componentSizeCache);
         this.preferredHeightMeasure = new FormLayout.PreferredHeightMeasure(this.componentSizeCache);
      }
   }

   public int getColumnCount() {
      return this.colSpecs.size();
   }

   public ColumnSpec getColumnSpec(int columnIndex) {
      return (ColumnSpec)this.colSpecs.get(columnIndex - 1);
   }

   public void setColumnSpec(int columnIndex, ColumnSpec columnSpec) {
      if (columnSpec == null) {
         throw new NullPointerException("The column spec must not be null.");
      } else {
         this.colSpecs.set(columnIndex - 1, columnSpec);
      }
   }

   public void appendColumn(ColumnSpec columnSpec) {
      if (columnSpec == null) {
         throw new NullPointerException("The column spec must not be null.");
      } else {
         this.colSpecs.add(columnSpec);
      }
   }

   public void insertColumn(int columnIndex, ColumnSpec columnSpec) {
      if (columnIndex >= 1 && columnIndex <= this.getColumnCount()) {
         this.colSpecs.add(columnIndex - 1, columnSpec);
         this.shiftComponentsHorizontally(columnIndex, false);
         this.adjustGroupIndices(this.colGroupIndices, columnIndex, false);
      } else {
         throw new IndexOutOfBoundsException("The column index " + columnIndex + "must be in the range [1, " + this.getColumnCount() + "].");
      }
   }

   public void removeColumn(int columnIndex) {
      if (columnIndex >= 1 && columnIndex <= this.getColumnCount()) {
         this.colSpecs.remove(columnIndex - 1);
         this.shiftComponentsHorizontally(columnIndex, true);
         this.adjustGroupIndices(this.colGroupIndices, columnIndex, true);
      } else {
         throw new IndexOutOfBoundsException("The column index " + columnIndex + " must be in the range [1, " + this.getColumnCount() + "].");
      }
   }

   public int getRowCount() {
      return this.rowSpecs.size();
   }

   public RowSpec getRowSpec(int rowIndex) {
      return (RowSpec)this.rowSpecs.get(rowIndex - 1);
   }

   public void setRowSpec(int rowIndex, RowSpec rowSpec) {
      if (rowSpec == null) {
         throw new NullPointerException("The row spec must not be null.");
      } else {
         this.rowSpecs.set(rowIndex - 1, rowSpec);
      }
   }

   public void appendRow(RowSpec rowSpec) {
      if (rowSpec == null) {
         throw new NullPointerException("The row spec must not be null.");
      } else {
         this.rowSpecs.add(rowSpec);
      }
   }

   public void insertRow(int rowIndex, RowSpec rowSpec) {
      if (rowIndex >= 1 && rowIndex <= this.getRowCount()) {
         this.rowSpecs.add(rowIndex - 1, rowSpec);
         this.shiftComponentsVertically(rowIndex, false);
         this.adjustGroupIndices(this.rowGroupIndices, rowIndex, false);
      } else {
         throw new IndexOutOfBoundsException("The row index " + rowIndex + " must be in the range [1, " + this.getRowCount() + "].");
      }
   }

   public void removeRow(int rowIndex) {
      if (rowIndex >= 1 && rowIndex <= this.getRowCount()) {
         this.rowSpecs.remove(rowIndex - 1);
         this.shiftComponentsVertically(rowIndex, true);
         this.adjustGroupIndices(this.rowGroupIndices, rowIndex, true);
      } else {
         throw new IndexOutOfBoundsException("The row index " + rowIndex + "must be in the range [1, " + this.getRowCount() + "].");
      }
   }

   private void shiftComponentsHorizontally(int columnIndex, boolean remove) {
      int offset = remove ? -1 : 1;
      Iterator i = this.constraintMap.entrySet().iterator();

      while(i.hasNext()) {
         Entry entry = (Entry)i.next();
         CellConstraints constraints = (CellConstraints)entry.getValue();
         int x1 = constraints.gridX;
         int w = constraints.gridWidth;
         int x2 = x1 + w - 1;
         if (x1 == columnIndex && remove) {
            throw new IllegalStateException("The removed column " + columnIndex + " must not contain component origins.\n" + "Illegal component=" + entry.getKey());
         }

         if (x1 >= columnIndex) {
            constraints.gridX += offset;
         } else if (x2 >= columnIndex) {
            constraints.gridWidth += offset;
         }
      }

   }

   private void shiftComponentsVertically(int rowIndex, boolean remove) {
      int offset = remove ? -1 : 1;
      Iterator i = this.constraintMap.entrySet().iterator();

      while(i.hasNext()) {
         Entry entry = (Entry)i.next();
         CellConstraints constraints = (CellConstraints)entry.getValue();
         int y1 = constraints.gridY;
         int h = constraints.gridHeight;
         int y2 = y1 + h - 1;
         if (y1 == rowIndex && remove) {
            throw new IllegalStateException("The removed row " + rowIndex + " must not contain component origins.\n" + "Illegal component=" + entry.getKey());
         }

         if (y1 >= rowIndex) {
            constraints.gridY += offset;
         } else if (y2 >= rowIndex) {
            constraints.gridHeight += offset;
         }
      }

   }

   private void adjustGroupIndices(int[][] allGroupIndices, int modifiedIndex, boolean remove) {
      int offset = remove ? -1 : 1;

      for(int group = 0; group < allGroupIndices.length; ++group) {
         int[] groupIndices = allGroupIndices[group];

         for(int i = 0; i < groupIndices.length; ++i) {
            int index = groupIndices[i];
            if (index == modifiedIndex && remove) {
               throw new IllegalStateException("The removed index " + modifiedIndex + " must not be grouped.");
            }

            if (index >= modifiedIndex) {
               groupIndices[i] += offset;
            }
         }
      }

   }

   public CellConstraints getConstraints(Component component) {
      return (CellConstraints)this.getConstraints0(component).clone();
   }

   private CellConstraints getConstraints0(Component component) {
      if (component == null) {
         throw new NullPointerException("The component must not be null.");
      } else {
         CellConstraints constraints = (CellConstraints)this.constraintMap.get(component);
         if (constraints == null) {
            throw new NullPointerException("The component has not been added to the container.");
         } else {
            return constraints;
         }
      }
   }

   public void setConstraints(Component component, CellConstraints constraints) {
      if (component == null) {
         throw new NullPointerException("The component must not be null.");
      } else if (constraints == null) {
         throw new NullPointerException("The constraints must not be null.");
      } else {
         constraints.ensureValidGridBounds(this.getColumnCount(), this.getRowCount());
         this.constraintMap.put(component, constraints.clone());
      }
   }

   private void removeConstraints(Component component) {
      this.constraintMap.remove(component);
      this.componentSizeCache.removeEntry(component);
   }

   public int[][] getColumnGroups() {
      return this.deepClone(this.colGroupIndices);
   }

   public void setColumnGroups(int[][] colGroupIndices) {
      int maxColumn = this.getColumnCount();
      boolean[] usedIndices = new boolean[maxColumn + 1];

      for(int group = 0; group < colGroupIndices.length; ++group) {
         for(int j = 0; j < colGroupIndices[group].length; ++j) {
            int colIndex = colGroupIndices[group][j];
            if (colIndex < 1 || colIndex > maxColumn) {
               throw new IndexOutOfBoundsException("Invalid column group index " + colIndex + " in group " + (group + 1));
            }

            if (usedIndices[colIndex]) {
               throw new IllegalArgumentException("Column index " + colIndex + " must not be used in multiple column groups.");
            }

            usedIndices[colIndex] = true;
         }
      }

      this.colGroupIndices = this.deepClone(colGroupIndices);
   }

   public void addGroupedColumn(int columnIndex) {
      int[][] newColGroups = this.getColumnGroups();
      if (newColGroups.length == 0) {
         newColGroups = new int[][]{{columnIndex}};
      } else {
         int lastGroupIndex = newColGroups.length - 1;
         int[] lastGroup = newColGroups[lastGroupIndex];
         int groupSize = lastGroup.length;
         int[] newLastGroup = new int[groupSize + 1];
         System.arraycopy(lastGroup, 0, newLastGroup, 0, groupSize);
         newLastGroup[groupSize] = columnIndex;
         newColGroups[lastGroupIndex] = newLastGroup;
      }

      this.setColumnGroups(newColGroups);
   }

   public int[][] getRowGroups() {
      return this.deepClone(this.rowGroupIndices);
   }

   public void setRowGroups(int[][] rowGroupIndices) {
      int rowCount = this.getRowCount();
      boolean[] usedIndices = new boolean[rowCount + 1];

      for(int i = 0; i < rowGroupIndices.length; ++i) {
         for(int j = 0; j < rowGroupIndices[i].length; ++j) {
            int rowIndex = rowGroupIndices[i][j];
            if (rowIndex < 1 || rowIndex > rowCount) {
               throw new IndexOutOfBoundsException("Invalid row group index " + rowIndex + " in group " + (i + 1));
            }

            if (usedIndices[rowIndex]) {
               throw new IllegalArgumentException("Row index " + rowIndex + " must not be used in multiple row groups.");
            }

            usedIndices[rowIndex] = true;
         }
      }

      this.rowGroupIndices = this.deepClone(rowGroupIndices);
   }

   public void addGroupedRow(int rowIndex) {
      int[][] newRowGroups = this.getRowGroups();
      if (newRowGroups.length == 0) {
         newRowGroups = new int[][]{{rowIndex}};
      } else {
         int lastGroupIndex = newRowGroups.length - 1;
         int[] lastGroup = newRowGroups[lastGroupIndex];
         int groupSize = lastGroup.length;
         int[] newLastGroup = new int[groupSize + 1];
         System.arraycopy(lastGroup, 0, newLastGroup, 0, groupSize);
         newLastGroup[groupSize] = rowIndex;
         newRowGroups[lastGroupIndex] = newLastGroup;
      }

      this.setRowGroups(newRowGroups);
   }

   public boolean getHonorsVisibility() {
      return this.honorsVisibility;
   }

   public void setHonorsVisibility(boolean b) {
      boolean oldHonorsVisibility = this.getHonorsVisibility();
      if (oldHonorsVisibility != b) {
         this.honorsVisibility = b;
         Set componentSet = this.constraintMap.keySet();
         if (!componentSet.isEmpty()) {
            Component firstComponent = (Component)componentSet.iterator().next();
            Container container = firstComponent.getParent();
            invalidateAndRepaint(container);
         }
      }
   }

   public void setHonorsVisibility(Component component, Boolean b) {
      CellConstraints constraints = this.getConstraints0(component);
      if (!FormUtils.equals(b, constraints.honorsVisibility)) {
         constraints.honorsVisibility = b;
         invalidateAndRepaint(component.getParent());
      }
   }

   public void addLayoutComponent(String name, Component component) {
      throw new UnsupportedOperationException("Use #addLayoutComponent(Component, Object) instead.");
   }

   public void addLayoutComponent(Component comp, Object constraints) {
      if (constraints instanceof String) {
         this.setConstraints(comp, new CellConstraints((String)constraints));
      } else {
         if (!(constraints instanceof CellConstraints)) {
            if (constraints == null) {
               throw new NullPointerException("The constraints must not be null.");
            }

            throw new IllegalArgumentException("Illegal constraint type " + constraints.getClass());
         }

         this.setConstraints(comp, (CellConstraints)constraints);
      }

   }

   public void removeLayoutComponent(Component comp) {
      this.removeConstraints(comp);
   }

   public Dimension minimumLayoutSize(Container parent) {
      return this.computeLayoutSize(parent, this.minimumWidthMeasure, this.minimumHeightMeasure);
   }

   public Dimension preferredLayoutSize(Container parent) {
      return this.computeLayoutSize(parent, this.preferredWidthMeasure, this.preferredHeightMeasure);
   }

   public Dimension maximumLayoutSize(Container target) {
      return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
   }

   public float getLayoutAlignmentX(Container parent) {
      return 0.5F;
   }

   public float getLayoutAlignmentY(Container parent) {
      return 0.5F;
   }

   public void invalidateLayout(Container target) {
      this.invalidateCaches();
   }

   public void layoutContainer(Container parent) {
      synchronized(parent.getTreeLock()) {
         this.initializeColAndRowComponentLists();
         Dimension size = parent.getSize();
         Insets insets = parent.getInsets();
         int totalWidth = size.width - insets.left - insets.right;
         int totalHeight = size.height - insets.top - insets.bottom;
         int[] x = this.computeGridOrigins(parent, totalWidth, insets.left, this.colSpecs, this.colComponents, this.colGroupIndices, this.minimumWidthMeasure, this.preferredWidthMeasure);
         int[] y = this.computeGridOrigins(parent, totalHeight, insets.top, this.rowSpecs, this.rowComponents, this.rowGroupIndices, this.minimumHeightMeasure, this.preferredHeightMeasure);
         this.layoutComponents(x, y);
      }
   }

   private void initializeColAndRowComponentLists() {
      this.colComponents = new List[this.getColumnCount()];

      int i;
      for(i = 0; i < this.getColumnCount(); ++i) {
         this.colComponents[i] = new ArrayList();
      }

      this.rowComponents = new List[this.getRowCount()];

      for(i = 0; i < this.getRowCount(); ++i) {
         this.rowComponents[i] = new ArrayList();
      }

      Iterator i = this.constraintMap.entrySet().iterator();

      while(i.hasNext()) {
         Entry entry = (Entry)i.next();
         Component component = (Component)entry.getKey();
         CellConstraints constraints = (CellConstraints)entry.getValue();
         if (this.takeIntoAccount(component, constraints)) {
            if (constraints.gridWidth == 1) {
               this.colComponents[constraints.gridX - 1].add(component);
            }

            if (constraints.gridHeight == 1) {
               this.rowComponents[constraints.gridY - 1].add(component);
            }
         }
      }

   }

   private Dimension computeLayoutSize(Container parent, FormLayout.Measure defaultWidthMeasure, FormLayout.Measure defaultHeightMeasure) {
      synchronized(parent.getTreeLock()) {
         this.initializeColAndRowComponentLists();
         int[] colWidths = this.maximumSizes(parent, this.colSpecs, this.colComponents, this.minimumWidthMeasure, this.preferredWidthMeasure, defaultWidthMeasure);
         int[] rowHeights = this.maximumSizes(parent, this.rowSpecs, this.rowComponents, this.minimumHeightMeasure, this.preferredHeightMeasure, defaultHeightMeasure);
         int[] groupedWidths = this.groupedSizes(this.colGroupIndices, colWidths);
         int[] groupedHeights = this.groupedSizes(this.rowGroupIndices, rowHeights);
         int[] xOrigins = this.computeOrigins(groupedWidths, 0);
         int[] yOrigins = this.computeOrigins(groupedHeights, 0);
         int width1 = sum(groupedWidths);
         int height1 = sum(groupedHeights);
         int maxWidth = width1;
         int maxHeight = height1;
         int[] maxFixedSizeColsTable = this.computeMaximumFixedSpanTable(this.colSpecs);
         int[] maxFixedSizeRowsTable = this.computeMaximumFixedSpanTable(this.rowSpecs);
         Iterator i = this.constraintMap.entrySet().iterator();

         while(i.hasNext()) {
            Entry entry = (Entry)i.next();
            Component component = (Component)entry.getKey();
            CellConstraints constraints = (CellConstraints)entry.getValue();
            if (this.takeIntoAccount(component, constraints)) {
               int compHeight;
               int gridY1;
               int gridY2;
               int lead;
               int trail;
               int myHeight;
               if (constraints.gridWidth > 1 && constraints.gridWidth > maxFixedSizeColsTable[constraints.gridX - 1]) {
                  compHeight = defaultWidthMeasure.sizeOf(component);
                  gridY1 = constraints.gridX - 1;
                  gridY2 = gridY1 + constraints.gridWidth;
                  lead = xOrigins[gridY1];
                  trail = width1 - xOrigins[gridY2];
                  myHeight = lead + compHeight + trail;
                  if (myHeight > maxWidth) {
                     maxWidth = myHeight;
                  }
               }

               if (constraints.gridHeight > 1 && constraints.gridHeight > maxFixedSizeRowsTable[constraints.gridY - 1]) {
                  compHeight = defaultHeightMeasure.sizeOf(component);
                  gridY1 = constraints.gridY - 1;
                  gridY2 = gridY1 + constraints.gridHeight;
                  lead = yOrigins[gridY1];
                  trail = height1 - yOrigins[gridY2];
                  myHeight = lead + compHeight + trail;
                  if (myHeight > maxHeight) {
                     maxHeight = myHeight;
                  }
               }
            }
         }

         Insets insets = parent.getInsets();
         int width = maxWidth + insets.left + insets.right;
         int height = maxHeight + insets.top + insets.bottom;
         return new Dimension(width, height);
      }
   }

   private int[] computeGridOrigins(Container container, int totalSize, int offset, List formSpecs, List[] componentLists, int[][] groupIndices, FormLayout.Measure minMeasure, FormLayout.Measure prefMeasure) {
      int[] minSizes = this.maximumSizes(container, formSpecs, componentLists, minMeasure, prefMeasure, minMeasure);
      int[] prefSizes = this.maximumSizes(container, formSpecs, componentLists, minMeasure, prefMeasure, prefMeasure);
      int[] groupedMinSizes = this.groupedSizes(groupIndices, minSizes);
      int[] groupedPrefSizes = this.groupedSizes(groupIndices, prefSizes);
      int totalMinSize = sum(groupedMinSizes);
      int totalPrefSize = sum(groupedPrefSizes);
      int[] compressedSizes = this.compressedSizes(formSpecs, totalSize, totalMinSize, totalPrefSize, groupedMinSizes, prefSizes);
      int[] groupedSizes = this.groupedSizes(groupIndices, compressedSizes);
      int totalGroupedSize = sum(groupedSizes);
      int[] sizes = this.distributedSizes(formSpecs, totalSize, totalGroupedSize, groupedSizes);
      return this.computeOrigins(sizes, offset);
   }

   private int[] computeOrigins(int[] sizes, int offset) {
      int count = sizes.length;
      int[] origins = new int[count + 1];
      origins[0] = offset;

      for(int i = 1; i <= count; ++i) {
         origins[i] = origins[i - 1] + sizes[i - 1];
      }

      return origins;
   }

   private void layoutComponents(int[] x, int[] y) {
      Rectangle cellBounds = new Rectangle();
      Iterator i = this.constraintMap.entrySet().iterator();

      while(i.hasNext()) {
         Entry entry = (Entry)i.next();
         Component component = (Component)entry.getKey();
         CellConstraints constraints = (CellConstraints)entry.getValue();
         int gridX = constraints.gridX - 1;
         int gridY = constraints.gridY - 1;
         int gridWidth = constraints.gridWidth;
         int gridHeight = constraints.gridHeight;
         cellBounds.x = x[gridX];
         cellBounds.y = y[gridY];
         cellBounds.width = x[gridX + gridWidth] - cellBounds.x;
         cellBounds.height = y[gridY + gridHeight] - cellBounds.y;
         constraints.setBounds(component, this, cellBounds, this.minimumWidthMeasure, this.minimumHeightMeasure, this.preferredWidthMeasure, this.preferredHeightMeasure);
      }

   }

   private void invalidateCaches() {
      this.componentSizeCache.invalidate();
   }

   private int[] maximumSizes(Container container, List formSpecs, List[] componentLists, FormLayout.Measure minMeasure, FormLayout.Measure prefMeasure, FormLayout.Measure defaultMeasure) {
      int size = formSpecs.size();
      int[] result = new int[size];

      for(int i = 0; i < size; ++i) {
         FormSpec formSpec = (FormSpec)formSpecs.get(i);
         result[i] = formSpec.maximumSize(container, componentLists[i], minMeasure, prefMeasure, defaultMeasure);
      }

      return result;
   }

   private int[] compressedSizes(List formSpecs, int totalSize, int totalMinSize, int totalPrefSize, int[] minSizes, int[] prefSizes) {
      if (totalSize < totalMinSize) {
         return minSizes;
      } else if (totalSize >= totalPrefSize) {
         return prefSizes;
      } else {
         int count = formSpecs.size();
         int[] sizes = new int[count];
         double totalCompressionSpace = (double)(totalPrefSize - totalSize);
         double maxCompressionSpace = (double)(totalPrefSize - totalMinSize);
         double compressionFactor = totalCompressionSpace / maxCompressionSpace;

         for(int i = 0; i < count; ++i) {
            FormSpec formSpec = (FormSpec)formSpecs.get(i);
            sizes[i] = prefSizes[i];
            if (formSpec.getSize().compressible()) {
               sizes[i] -= (int)Math.round((double)(prefSizes[i] - minSizes[i]) * compressionFactor);
            }
         }

         return sizes;
      }
   }

   private int[] groupedSizes(int[][] groups, int[] rawSizes) {
      if (groups != null && groups.length != 0) {
         int[] sizes = new int[rawSizes.length];

         int group;
         for(group = 0; group < sizes.length; ++group) {
            sizes[group] = rawSizes[group];
         }

         for(group = 0; group < groups.length; ++group) {
            int[] groupIndices = groups[group];
            int groupMaxSize = 0;

            int i;
            int index;
            for(i = 0; i < groupIndices.length; ++i) {
               index = groupIndices[i] - 1;
               groupMaxSize = Math.max(groupMaxSize, sizes[index]);
            }

            for(i = 0; i < groupIndices.length; ++i) {
               index = groupIndices[i] - 1;
               sizes[index] = groupMaxSize;
            }
         }

         return sizes;
      } else {
         return rawSizes;
      }
   }

   private int[] distributedSizes(List formSpecs, int totalSize, int totalPrefSize, int[] inputSizes) {
      double totalFreeSpace = (double)(totalSize - totalPrefSize);
      if (totalFreeSpace < 0.0D) {
         return inputSizes;
      } else {
         int count = formSpecs.size();
         double totalWeight = 0.0D;

         for(int i = 0; i < count; ++i) {
            FormSpec formSpec = (FormSpec)formSpecs.get(i);
            totalWeight += formSpec.getResizeWeight();
         }

         if (totalWeight == 0.0D) {
            return inputSizes;
         } else {
            int[] sizes = new int[count];
            double restSpace = totalFreeSpace;
            int roundedRestSpace = (int)totalFreeSpace;

            for(int i = 0; i < count; ++i) {
               FormSpec formSpec = (FormSpec)formSpecs.get(i);
               double weight = formSpec.getResizeWeight();
               if (weight == 0.0D) {
                  sizes[i] = inputSizes[i];
               } else {
                  double roundingCorrection = restSpace - (double)roundedRestSpace;
                  double extraSpace = totalFreeSpace * weight / totalWeight;
                  double correctedExtraSpace = extraSpace - roundingCorrection;
                  int roundedExtraSpace = (int)Math.round(correctedExtraSpace);
                  sizes[i] = inputSizes[i] + roundedExtraSpace;
                  restSpace -= extraSpace;
                  roundedRestSpace -= roundedExtraSpace;
               }
            }

            return sizes;
         }
      }
   }

   private int[] computeMaximumFixedSpanTable(List formSpecs) {
      int size = formSpecs.size();
      int[] table = new int[size];
      int maximumFixedSpan = Integer.MAX_VALUE;

      for(int i = size - 1; i >= 0; --i) {
         FormSpec spec = (FormSpec)formSpecs.get(i);
         if (spec.canGrow()) {
            maximumFixedSpan = 0;
         }

         table[i] = maximumFixedSpan;
         if (maximumFixedSpan < Integer.MAX_VALUE) {
            ++maximumFixedSpan;
         }
      }

      return table;
   }

   private static int sum(int[] sizes) {
      int sum = 0;

      for(int i = sizes.length - 1; i >= 0; --i) {
         sum += sizes[i];
      }

      return sum;
   }

   private static void invalidateAndRepaint(Container container) {
      if (container != null) {
         if (container instanceof JComponent) {
            ((JComponent)container).revalidate();
         } else {
            container.invalidate();
         }

         container.repaint();
      }
   }

   private boolean takeIntoAccount(Component component, CellConstraints cc) {
      return component.isVisible() || cc.honorsVisibility == null && !this.getHonorsVisibility() || Boolean.FALSE.equals(cc.honorsVisibility);
   }

   public FormLayout.LayoutInfo getLayoutInfo(Container parent) {
      synchronized(parent.getTreeLock()) {
         this.initializeColAndRowComponentLists();
         Dimension size = parent.getSize();
         Insets insets = parent.getInsets();
         int totalWidth = size.width - insets.left - insets.right;
         int totalHeight = size.height - insets.top - insets.bottom;
         int[] x = this.computeGridOrigins(parent, totalWidth, insets.left, this.colSpecs, this.colComponents, this.colGroupIndices, this.minimumWidthMeasure, this.preferredWidthMeasure);
         int[] y = this.computeGridOrigins(parent, totalHeight, insets.top, this.rowSpecs, this.rowComponents, this.rowGroupIndices, this.minimumHeightMeasure, this.preferredHeightMeasure);
         return new FormLayout.LayoutInfo(x, y);
      }
   }

   private int[][] deepClone(int[][] array) {
      int[][] result = new int[array.length][];

      for(int i = 0; i < result.length; ++i) {
         result[i] = (int[])((int[])array[i].clone());
      }

      return result;
   }

   private void writeObject(ObjectOutputStream out) throws IOException {
      this.invalidateCaches();
      out.defaultWriteObject();
   }

   private abstract static class CachingMeasure implements FormLayout.Measure, Serializable {
      protected final FormLayout.ComponentSizeCache cache;

      private CachingMeasure(FormLayout.ComponentSizeCache cache) {
         this.cache = cache;
      }

      // $FF: synthetic method
      CachingMeasure(FormLayout.ComponentSizeCache x0, Object x1) {
         this(x0);
      }
   }

   private static final class ComponentSizeCache implements Serializable {
      private final Map minimumSizes;
      private final Map preferredSizes;

      private ComponentSizeCache(int initialCapacity) {
         this.minimumSizes = new HashMap(initialCapacity);
         this.preferredSizes = new HashMap(initialCapacity);
      }

      void invalidate() {
         this.minimumSizes.clear();
         this.preferredSizes.clear();
      }

      Dimension getMinimumSize(Component component) {
         Dimension size = (Dimension)this.minimumSizes.get(component);
         if (size == null) {
            size = component.getMinimumSize();
            this.minimumSizes.put(component, size);
         }

         return size;
      }

      Dimension getPreferredSize(Component component) {
         Dimension size = (Dimension)this.preferredSizes.get(component);
         if (size == null) {
            size = component.getPreferredSize();
            this.preferredSizes.put(component, size);
         }

         return size;
      }

      void removeEntry(Component component) {
         this.minimumSizes.remove(component);
         this.preferredSizes.remove(component);
      }

      // $FF: synthetic method
      ComponentSizeCache(int x0, Object x1) {
         this(x0);
      }
   }

   public static final class LayoutInfo {
      public final int[] columnOrigins;
      public final int[] rowOrigins;

      private LayoutInfo(int[] xOrigins, int[] yOrigins) {
         this.columnOrigins = xOrigins;
         this.rowOrigins = yOrigins;
      }

      public int getX() {
         return this.columnOrigins[0];
      }

      public int getY() {
         return this.rowOrigins[0];
      }

      public int getWidth() {
         return this.columnOrigins[this.columnOrigins.length - 1] - this.columnOrigins[0];
      }

      public int getHeight() {
         return this.rowOrigins[this.rowOrigins.length - 1] - this.rowOrigins[0];
      }

      // $FF: synthetic method
      LayoutInfo(int[] x0, int[] x1, Object x2) {
         this(x0, x1);
      }
   }

   public interface Measure {
      int sizeOf(Component var1);
   }

   private static final class MinimumHeightMeasure extends FormLayout.CachingMeasure {
      private MinimumHeightMeasure(FormLayout.ComponentSizeCache cache) {
         super(cache, null);
      }

      public int sizeOf(Component c) {
         return this.cache.getMinimumSize(c).height;
      }

      // $FF: synthetic method
      MinimumHeightMeasure(FormLayout.ComponentSizeCache x0, Object x1) {
         this(x0);
      }
   }

   private static final class MinimumWidthMeasure extends FormLayout.CachingMeasure {
      private MinimumWidthMeasure(FormLayout.ComponentSizeCache cache) {
         super(cache, null);
      }

      public int sizeOf(Component c) {
         return this.cache.getMinimumSize(c).width;
      }

      // $FF: synthetic method
      MinimumWidthMeasure(FormLayout.ComponentSizeCache x0, Object x1) {
         this(x0);
      }
   }

   private static final class PreferredHeightMeasure extends FormLayout.CachingMeasure {
      private PreferredHeightMeasure(FormLayout.ComponentSizeCache cache) {
         super(cache, null);
      }

      public int sizeOf(Component c) {
         return this.cache.getPreferredSize(c).height;
      }

      // $FF: synthetic method
      PreferredHeightMeasure(FormLayout.ComponentSizeCache x0, Object x1) {
         this(x0);
      }
   }

   private static final class PreferredWidthMeasure extends FormLayout.CachingMeasure {
      private PreferredWidthMeasure(FormLayout.ComponentSizeCache cache) {
         super(cache, null);
      }

      public int sizeOf(Component c) {
         return this.cache.getPreferredSize(c).width;
      }

      // $FF: synthetic method
      PreferredWidthMeasure(FormLayout.ComponentSizeCache x0, Object x1) {
         this(x0);
      }
   }
}
