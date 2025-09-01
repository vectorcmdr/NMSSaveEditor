package com.jgoodies.forms.layout;

import java.awt.Component;
import java.awt.Insets;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Locale;
import java.util.StringTokenizer;

public final class CellConstraints implements Cloneable, Serializable {
   public static final CellConstraints.Alignment DEFAULT = new CellConstraints.Alignment("default", 2);
   public static final CellConstraints.Alignment FILL = new CellConstraints.Alignment("fill", 2);
   public static final CellConstraints.Alignment LEFT = new CellConstraints.Alignment("left", 0);
   public static final CellConstraints.Alignment RIGHT = new CellConstraints.Alignment("right", 0);
   public static final CellConstraints.Alignment CENTER = new CellConstraints.Alignment("center", 2);
   public static final CellConstraints.Alignment TOP = new CellConstraints.Alignment("top", 1);
   public static final CellConstraints.Alignment BOTTOM = new CellConstraints.Alignment("bottom", 1);
   private static final CellConstraints.Alignment[] VALUES;
   private static final Insets EMPTY_INSETS;
   public int gridX;
   public int gridY;
   public int gridWidth;
   public int gridHeight;
   public CellConstraints.Alignment hAlign;
   public CellConstraints.Alignment vAlign;
   public Insets insets;
   public Boolean honorsVisibility;

   public CellConstraints() {
      this(1, 1);
   }

   public CellConstraints(int gridX, int gridY) {
      this(gridX, gridY, 1, 1);
   }

   public CellConstraints(int gridX, int gridY, CellConstraints.Alignment hAlign, CellConstraints.Alignment vAlign) {
      this(gridX, gridY, 1, 1, hAlign, vAlign, EMPTY_INSETS);
   }

   public CellConstraints(int gridX, int gridY, int gridWidth, int gridHeight) {
      this(gridX, gridY, gridWidth, gridHeight, DEFAULT, DEFAULT);
   }

   public CellConstraints(int gridX, int gridY, int gridWidth, int gridHeight, CellConstraints.Alignment hAlign, CellConstraints.Alignment vAlign) {
      this(gridX, gridY, gridWidth, gridHeight, hAlign, vAlign, EMPTY_INSETS);
   }

   public CellConstraints(int gridX, int gridY, int gridWidth, int gridHeight, CellConstraints.Alignment hAlign, CellConstraints.Alignment vAlign, Insets insets) {
      this.gridX = gridX;
      this.gridY = gridY;
      this.gridWidth = gridWidth;
      this.gridHeight = gridHeight;
      this.hAlign = hAlign;
      this.vAlign = vAlign;
      this.insets = insets;
      if (gridX <= 0) {
         throw new IndexOutOfBoundsException("The grid x must be a positive number.");
      } else if (gridY <= 0) {
         throw new IndexOutOfBoundsException("The grid y must be a positive number.");
      } else if (gridWidth <= 0) {
         throw new IndexOutOfBoundsException("The grid width must be a positive number.");
      } else if (gridHeight <= 0) {
         throw new IndexOutOfBoundsException("The grid height must be a positive number.");
      } else if (hAlign == null) {
         throw new NullPointerException("The horizontal alignment must not be null.");
      } else if (vAlign == null) {
         throw new NullPointerException("The vertical alignment must not be null.");
      } else {
         this.ensureValidOrientations(hAlign, vAlign);
      }
   }

   public CellConstraints(String encodedConstraints) {
      this();
      this.initFromConstraints(encodedConstraints);
   }

   public CellConstraints xy(int col, int row) {
      return this.xywh(col, row, 1, 1);
   }

   public CellConstraints xy(int col, int row, String encodedAlignments) {
      return this.xywh(col, row, 1, 1, encodedAlignments);
   }

   public CellConstraints xy(int col, int row, CellConstraints.Alignment colAlign, CellConstraints.Alignment rowAlign) {
      return this.xywh(col, row, 1, 1, colAlign, rowAlign);
   }

   public CellConstraints xyw(int col, int row, int colSpan) {
      return this.xywh(col, row, colSpan, 1, DEFAULT, DEFAULT);
   }

   public CellConstraints xyw(int col, int row, int colSpan, String encodedAlignments) {
      return this.xywh(col, row, colSpan, 1, encodedAlignments);
   }

   public CellConstraints xyw(int col, int row, int colSpan, CellConstraints.Alignment colAlign, CellConstraints.Alignment rowAlign) {
      return this.xywh(col, row, colSpan, 1, colAlign, rowAlign);
   }

   public CellConstraints xywh(int col, int row, int colSpan, int rowSpan) {
      return this.xywh(col, row, colSpan, rowSpan, DEFAULT, DEFAULT);
   }

   public CellConstraints xywh(int col, int row, int colSpan, int rowSpan, String encodedAlignments) {
      CellConstraints result = this.xywh(col, row, colSpan, rowSpan);
      result.setAlignments(encodedAlignments, true);
      return result;
   }

   public CellConstraints xywh(int col, int row, int colSpan, int rowSpan, CellConstraints.Alignment colAlign, CellConstraints.Alignment rowAlign) {
      this.gridX = col;
      this.gridY = row;
      this.gridWidth = colSpan;
      this.gridHeight = rowSpan;
      this.hAlign = colAlign;
      this.vAlign = rowAlign;
      this.ensureValidOrientations(this.hAlign, this.vAlign);
      return this;
   }

   public CellConstraints rc(int row, int col) {
      return this.rchw(row, col, 1, 1);
   }

   public CellConstraints rc(int row, int col, String encodedAlignments) {
      return this.rchw(row, col, 1, 1, encodedAlignments);
   }

   public CellConstraints rc(int row, int col, CellConstraints.Alignment rowAlign, CellConstraints.Alignment colAlign) {
      return this.rchw(row, col, 1, 1, rowAlign, colAlign);
   }

   public CellConstraints rcw(int row, int col, int colSpan) {
      return this.rchw(row, col, 1, colSpan, DEFAULT, DEFAULT);
   }

   public CellConstraints rcw(int row, int col, int colSpan, String encodedAlignments) {
      return this.rchw(row, col, 1, colSpan, encodedAlignments);
   }

   public CellConstraints rcw(int row, int col, int colSpan, CellConstraints.Alignment rowAlign, CellConstraints.Alignment colAlign) {
      return this.rchw(row, col, 1, colSpan, rowAlign, colAlign);
   }

   public CellConstraints rchw(int row, int col, int rowSpan, int colSpan) {
      return this.rchw(row, col, rowSpan, colSpan, DEFAULT, DEFAULT);
   }

   public CellConstraints rchw(int row, int col, int rowSpan, int colSpan, String encodedAlignments) {
      CellConstraints result = this.rchw(row, col, rowSpan, colSpan);
      result.setAlignments(encodedAlignments, false);
      return result;
   }

   public CellConstraints rchw(int row, int col, int rowSpan, int colSpan, CellConstraints.Alignment rowAlign, CellConstraints.Alignment colAlign) {
      return this.xywh(col, row, colSpan, rowSpan, colAlign, rowAlign);
   }

   private void initFromConstraints(String encodedConstraints) {
      StringTokenizer tokenizer = new StringTokenizer(encodedConstraints, " ,");
      int argCount = tokenizer.countTokens();
      if (argCount != 2 && argCount != 4 && argCount != 6) {
         throw new IllegalArgumentException("You must provide 2, 4 or 6 arguments.");
      } else {
         Integer nextInt = this.decodeInt(tokenizer.nextToken());
         if (nextInt == null) {
            throw new IllegalArgumentException("First cell constraint element must be a number.");
         } else {
            this.gridX = nextInt;
            if (this.gridX <= 0) {
               throw new IndexOutOfBoundsException("The grid x must be a positive number.");
            } else {
               nextInt = this.decodeInt(tokenizer.nextToken());
               if (nextInt == null) {
                  throw new IllegalArgumentException("Second cell constraint element must be a number.");
               } else {
                  this.gridY = nextInt;
                  if (this.gridY <= 0) {
                     throw new IndexOutOfBoundsException("The grid y must be a positive number.");
                  } else if (tokenizer.hasMoreTokens()) {
                     String token = tokenizer.nextToken();
                     nextInt = this.decodeInt(token);
                     if (nextInt != null) {
                        this.gridWidth = nextInt;
                        if (this.gridWidth <= 0) {
                           throw new IndexOutOfBoundsException("The grid width must be a positive number.");
                        }

                        nextInt = this.decodeInt(tokenizer.nextToken());
                        if (nextInt == null) {
                           throw new IllegalArgumentException("Fourth cell constraint element must be like third.");
                        }

                        this.gridHeight = nextInt;
                        if (this.gridHeight <= 0) {
                           throw new IndexOutOfBoundsException("The grid height must be a positive number.");
                        }

                        if (!tokenizer.hasMoreTokens()) {
                           return;
                        }

                        token = tokenizer.nextToken();
                     }

                     this.hAlign = this.decodeAlignment(token);
                     this.vAlign = this.decodeAlignment(tokenizer.nextToken());
                     this.ensureValidOrientations(this.hAlign, this.vAlign);
                  }
               }
            }
         }
      }
   }

   private void setAlignments(String encodedAlignments, boolean horizontalThenVertical) {
      StringTokenizer tokenizer = new StringTokenizer(encodedAlignments, " ,");
      CellConstraints.Alignment first = this.decodeAlignment(tokenizer.nextToken());
      CellConstraints.Alignment second = this.decodeAlignment(tokenizer.nextToken());
      this.hAlign = horizontalThenVertical ? first : second;
      this.vAlign = horizontalThenVertical ? second : first;
      this.ensureValidOrientations(this.hAlign, this.vAlign);
   }

   private Integer decodeInt(String token) {
      try {
         return Integer.decode(token);
      } catch (NumberFormatException var3) {
         return null;
      }
   }

   private CellConstraints.Alignment decodeAlignment(String encodedAlignment) {
      return CellConstraints.Alignment.valueOf(encodedAlignment);
   }

   void ensureValidGridBounds(int colCount, int rowCount) {
      if (this.gridX <= 0) {
         throw new IndexOutOfBoundsException("The column index " + this.gridX + " must be positive.");
      } else if (this.gridX > colCount) {
         throw new IndexOutOfBoundsException("The column index " + this.gridX + " must be less than or equal to " + colCount + ".");
      } else if (this.gridX + this.gridWidth - 1 > colCount) {
         throw new IndexOutOfBoundsException("The grid width " + this.gridWidth + " must be less than or equal to " + (colCount - this.gridX + 1) + ".");
      } else if (this.gridY <= 0) {
         throw new IndexOutOfBoundsException("The row index " + this.gridY + " must be positive.");
      } else if (this.gridY > rowCount) {
         throw new IndexOutOfBoundsException("The row index " + this.gridY + " must be less than or equal to " + rowCount + ".");
      } else if (this.gridY + this.gridHeight - 1 > rowCount) {
         throw new IndexOutOfBoundsException("The grid height " + this.gridHeight + " must be less than or equal to " + (rowCount - this.gridY + 1) + ".");
      }
   }

   private void ensureValidOrientations(CellConstraints.Alignment horizontalAlignment, CellConstraints.Alignment verticalAlignment) {
      if (!horizontalAlignment.isHorizontal()) {
         throw new IllegalArgumentException("The horizontal alignment must be one of: left, center, right, fill, default.");
      } else if (!verticalAlignment.isVertical()) {
         throw new IllegalArgumentException("The vertical alignment must be one of: top, center, botto, fill, default.");
      }
   }

   void setBounds(Component c, FormLayout layout, Rectangle cellBounds, FormLayout.Measure minWidthMeasure, FormLayout.Measure minHeightMeasure, FormLayout.Measure prefWidthMeasure, FormLayout.Measure prefHeightMeasure) {
      ColumnSpec colSpec = this.gridWidth == 1 ? layout.getColumnSpec(this.gridX) : null;
      RowSpec rowSpec = this.gridHeight == 1 ? layout.getRowSpec(this.gridY) : null;
      CellConstraints.Alignment concreteHAlign = this.concreteAlignment(this.hAlign, colSpec);
      CellConstraints.Alignment concreteVAlign = this.concreteAlignment(this.vAlign, rowSpec);
      Insets concreteInsets = this.insets != null ? this.insets : EMPTY_INSETS;
      int cellX = cellBounds.x + concreteInsets.left;
      int cellY = cellBounds.y + concreteInsets.top;
      int cellW = cellBounds.width - concreteInsets.left - concreteInsets.right;
      int cellH = cellBounds.height - concreteInsets.top - concreteInsets.bottom;
      int compW = this.componentSize(c, colSpec, cellW, minWidthMeasure, prefWidthMeasure);
      int compH = this.componentSize(c, rowSpec, cellH, minHeightMeasure, prefHeightMeasure);
      int x = this.origin(concreteHAlign, cellX, cellW, compW);
      int y = this.origin(concreteVAlign, cellY, cellH, compH);
      int w = this.extent(concreteHAlign, cellW, compW);
      int h = this.extent(concreteVAlign, cellH, compH);
      c.setBounds(x, y, w, h);
   }

   private CellConstraints.Alignment concreteAlignment(CellConstraints.Alignment cellAlignment, FormSpec formSpec) {
      return formSpec == null ? (cellAlignment == DEFAULT ? FILL : cellAlignment) : this.usedAlignment(cellAlignment, formSpec);
   }

   private CellConstraints.Alignment usedAlignment(CellConstraints.Alignment cellAlignment, FormSpec formSpec) {
      if (cellAlignment != DEFAULT) {
         return cellAlignment;
      } else {
         FormSpec.DefaultAlignment defaultAlignment = formSpec.getDefaultAlignment();
         if (defaultAlignment == FormSpec.FILL_ALIGN) {
            return FILL;
         } else if (defaultAlignment == ColumnSpec.LEFT) {
            return LEFT;
         } else if (defaultAlignment == FormSpec.CENTER_ALIGN) {
            return CENTER;
         } else if (defaultAlignment == ColumnSpec.RIGHT) {
            return RIGHT;
         } else {
            return defaultAlignment == RowSpec.TOP ? TOP : BOTTOM;
         }
      }
   }

   private int componentSize(Component component, FormSpec formSpec, int cellSize, FormLayout.Measure minMeasure, FormLayout.Measure prefMeasure) {
      if (formSpec == null) {
         return prefMeasure.sizeOf(component);
      } else if (formSpec.getSize() == Sizes.MINIMUM) {
         return minMeasure.sizeOf(component);
      } else {
         return formSpec.getSize() == Sizes.PREFERRED ? prefMeasure.sizeOf(component) : Math.min(cellSize, prefMeasure.sizeOf(component));
      }
   }

   private int origin(CellConstraints.Alignment alignment, int cellOrigin, int cellSize, int componentSize) {
      if (alignment != RIGHT && alignment != BOTTOM) {
         return alignment == CENTER ? cellOrigin + (cellSize - componentSize) / 2 : cellOrigin;
      } else {
         return cellOrigin + cellSize - componentSize;
      }
   }

   private int extent(CellConstraints.Alignment alignment, int cellSize, int componentSize) {
      return alignment == FILL ? cellSize : componentSize;
   }

   public Object clone() {
      try {
         CellConstraints c = (CellConstraints)super.clone();
         c.insets = (Insets)this.insets.clone();
         return c;
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
   }

   public String toString() {
      StringBuffer buffer = new StringBuffer("CellConstraints");
      buffer.append("[x=");
      buffer.append(this.gridX);
      buffer.append("; y=");
      buffer.append(this.gridY);
      buffer.append("; w=");
      buffer.append(this.gridWidth);
      buffer.append("; h=");
      buffer.append(this.gridHeight);
      buffer.append("; hAlign=");
      buffer.append(this.hAlign);
      buffer.append("; vAlign=");
      buffer.append(this.vAlign);
      if (!EMPTY_INSETS.equals(this.insets)) {
         buffer.append("; insets=");
         buffer.append(this.insets);
      }

      buffer.append("; honorsVisibility=");
      buffer.append(this.honorsVisibility);
      buffer.append(']');
      return buffer.toString();
   }

   public String toShortString() {
      return this.toShortString((FormLayout)null);
   }

   public String toShortString(FormLayout layout) {
      StringBuffer buffer = new StringBuffer("(");
      buffer.append(this.formatInt(this.gridX));
      buffer.append(", ");
      buffer.append(this.formatInt(this.gridY));
      buffer.append(", ");
      buffer.append(this.formatInt(this.gridWidth));
      buffer.append(", ");
      buffer.append(this.formatInt(this.gridHeight));
      buffer.append(", \"");
      buffer.append(this.hAlign.abbreviation());
      if (this.hAlign == DEFAULT && layout != null) {
         buffer.append('=');
         ColumnSpec colSpec = this.gridWidth == 1 ? layout.getColumnSpec(this.gridX) : null;
         buffer.append(this.concreteAlignment(this.hAlign, colSpec).abbreviation());
      }

      buffer.append(", ");
      buffer.append(this.vAlign.abbreviation());
      if (this.vAlign == DEFAULT && layout != null) {
         buffer.append('=');
         RowSpec rowSpec = this.gridHeight == 1 ? layout.getRowSpec(this.gridY) : null;
         buffer.append(this.concreteAlignment(this.vAlign, rowSpec).abbreviation());
      }

      buffer.append("\"");
      if (!EMPTY_INSETS.equals(this.insets)) {
         buffer.append(", ");
         buffer.append(this.insets);
      }

      if (this.honorsVisibility != null) {
         buffer.append(this.honorsVisibility ? "honors visibility" : "ignores visibility");
      }

      buffer.append(')');
      return buffer.toString();
   }

   private String formatInt(int number) {
      String str = Integer.toString(number);
      return number < 10 ? " " + str : str;
   }

   static {
      VALUES = new CellConstraints.Alignment[]{DEFAULT, FILL, LEFT, RIGHT, CENTER, TOP, BOTTOM};
      EMPTY_INSETS = new Insets(0, 0, 0, 0);
   }

   public static final class Alignment implements Serializable {
      private static final int HORIZONTAL = 0;
      private static final int VERTICAL = 1;
      private static final int BOTH = 2;
      private final transient String name;
      private final transient int orientation;
      private static int nextOrdinal = 0;
      private final int ordinal;

      private Alignment(String name, int orientation) {
         this.ordinal = nextOrdinal++;
         this.name = name;
         this.orientation = orientation;
      }

      static CellConstraints.Alignment valueOf(String nameOrAbbreviation) {
         String str = nameOrAbbreviation.toLowerCase(Locale.ENGLISH);
         if (!str.equals("d") && !str.equals("default")) {
            if (!str.equals("f") && !str.equals("fill")) {
               if (!str.equals("c") && !str.equals("center")) {
                  if (!str.equals("l") && !str.equals("left")) {
                     if (!str.equals("r") && !str.equals("right")) {
                        if (!str.equals("t") && !str.equals("top")) {
                           if (!str.equals("b") && !str.equals("bottom")) {
                              throw new IllegalArgumentException("Invalid alignment " + nameOrAbbreviation + ". Must be one of: left, center, right, top, bottom, " + "fill, default, l, c, r, t, b, f, d.");
                           } else {
                              return CellConstraints.BOTTOM;
                           }
                        } else {
                           return CellConstraints.TOP;
                        }
                     } else {
                        return CellConstraints.RIGHT;
                     }
                  } else {
                     return CellConstraints.LEFT;
                  }
               } else {
                  return CellConstraints.CENTER;
               }
            } else {
               return CellConstraints.FILL;
            }
         } else {
            return CellConstraints.DEFAULT;
         }
      }

      public String toString() {
         return this.name;
      }

      public char abbreviation() {
         return this.name.charAt(0);
      }

      private boolean isHorizontal() {
         return this.orientation != 1;
      }

      private boolean isVertical() {
         return this.orientation != 0;
      }

      private Object readResolve() {
         return CellConstraints.VALUES[this.ordinal];
      }

      // $FF: synthetic method
      Alignment(String x0, int x1, Object x2) {
         this(x0, x1);
      }
   }
}
