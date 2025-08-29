/*      */ package com.jgoodies.forms.layout;
/*      */ 
/*      */ import java.awt.Component;
/*      */ import java.awt.Insets;
/*      */ import java.awt.Rectangle;
/*      */ import java.io.Serializable;
/*      */ import java.util.Locale;
/*      */ import java.util.StringTokenizer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class CellConstraints
/*      */   implements Cloneable, Serializable
/*      */ {
/*  101 */   public static final Alignment DEFAULT = new Alignment("default", 2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  107 */   public static final Alignment FILL = new Alignment("fill", 2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  113 */   public static final Alignment LEFT = new Alignment("left", 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  119 */   public static final Alignment RIGHT = new Alignment("right", 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  125 */   public static final Alignment CENTER = new Alignment("center", 2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  131 */   public static final Alignment TOP = new Alignment("top", 1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  137 */   public static final Alignment BOTTOM = new Alignment("bottom", 1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  144 */   private static final Alignment[] VALUES = new Alignment[] { DEFAULT, FILL, LEFT, RIGHT, CENTER, TOP, BOTTOM };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  150 */   private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int gridX;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int gridY;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int gridWidth;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int gridHeight;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Alignment hAlign;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Alignment vAlign;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Insets insets;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Boolean honorsVisibility;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CellConstraints() {
/*  209 */     this(1, 1);
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
/*      */   public CellConstraints(int gridX, int gridY) {
/*  226 */     this(gridX, gridY, 1, 1);
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
/*      */   public CellConstraints(int gridX, int gridY, Alignment hAlign, Alignment vAlign) {
/*  246 */     this(gridX, gridY, 1, 1, hAlign, vAlign, EMPTY_INSETS);
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
/*      */   public CellConstraints(int gridX, int gridY, int gridWidth, int gridHeight) {
/*  265 */     this(gridX, gridY, gridWidth, gridHeight, DEFAULT, DEFAULT);
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
/*      */   public CellConstraints(int gridX, int gridY, int gridWidth, int gridHeight, Alignment hAlign, Alignment vAlign) {
/*  287 */     this(gridX, gridY, gridWidth, gridHeight, hAlign, vAlign, EMPTY_INSETS);
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
/*      */   public CellConstraints(int gridX, int gridY, int gridWidth, int gridHeight, Alignment hAlign, Alignment vAlign, Insets insets) {
/*  313 */     this.gridX = gridX;
/*  314 */     this.gridY = gridY;
/*  315 */     this.gridWidth = gridWidth;
/*  316 */     this.gridHeight = gridHeight;
/*  317 */     this.hAlign = hAlign;
/*  318 */     this.vAlign = vAlign;
/*  319 */     this.insets = insets;
/*  320 */     if (gridX <= 0)
/*  321 */       throw new IndexOutOfBoundsException("The grid x must be a positive number."); 
/*  322 */     if (gridY <= 0)
/*  323 */       throw new IndexOutOfBoundsException("The grid y must be a positive number."); 
/*  324 */     if (gridWidth <= 0)
/*  325 */       throw new IndexOutOfBoundsException("The grid width must be a positive number."); 
/*  326 */     if (gridHeight <= 0)
/*  327 */       throw new IndexOutOfBoundsException("The grid height must be a positive number."); 
/*  328 */     if (hAlign == null)
/*  329 */       throw new NullPointerException("The horizontal alignment must not be null."); 
/*  330 */     if (vAlign == null)
/*  331 */       throw new NullPointerException("The vertical alignment must not be null."); 
/*  332 */     ensureValidOrientations(hAlign, vAlign);
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
/*      */   public CellConstraints(String encodedConstraints) {
/*  350 */     this();
/*  351 */     initFromConstraints(encodedConstraints);
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
/*      */   public CellConstraints xy(int col, int row) {
/*  371 */     return xywh(col, row, 1, 1);
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
/*      */   public CellConstraints xy(int col, int row, String encodedAlignments) {
/*  394 */     return xywh(col, row, 1, 1, encodedAlignments);
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
/*      */   public CellConstraints xy(int col, int row, Alignment colAlign, Alignment rowAlign) {
/*  414 */     return xywh(col, row, 1, 1, colAlign, rowAlign);
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
/*      */   public CellConstraints xyw(int col, int row, int colSpan) {
/*  433 */     return xywh(col, row, colSpan, 1, DEFAULT, DEFAULT);
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
/*      */   public CellConstraints xyw(int col, int row, int colSpan, String encodedAlignments) {
/*  458 */     return xywh(col, row, colSpan, 1, encodedAlignments);
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
/*      */   public CellConstraints xyw(int col, int row, int colSpan, Alignment colAlign, Alignment rowAlign) {
/*  482 */     return xywh(col, row, colSpan, 1, colAlign, rowAlign);
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
/*      */   public CellConstraints xywh(int col, int row, int colSpan, int rowSpan) {
/*  501 */     return xywh(col, row, colSpan, rowSpan, DEFAULT, DEFAULT);
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
/*      */   public CellConstraints xywh(int col, int row, int colSpan, int rowSpan, String encodedAlignments) {
/*  526 */     CellConstraints result = xywh(col, row, colSpan, rowSpan);
/*  527 */     result.setAlignments(encodedAlignments, true);
/*  528 */     return result;
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
/*      */   public CellConstraints xywh(int col, int row, int colSpan, int rowSpan, Alignment colAlign, Alignment rowAlign) {
/*  552 */     this.gridX = col;
/*  553 */     this.gridY = row;
/*  554 */     this.gridWidth = colSpan;
/*  555 */     this.gridHeight = rowSpan;
/*  556 */     this.hAlign = colAlign;
/*  557 */     this.vAlign = rowAlign;
/*  558 */     ensureValidOrientations(this.hAlign, this.vAlign);
/*  559 */     return this;
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
/*      */   public CellConstraints rc(int row, int col) {
/*  581 */     return rchw(row, col, 1, 1);
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
/*      */   public CellConstraints rc(int row, int col, String encodedAlignments) {
/*  606 */     return rchw(row, col, 1, 1, encodedAlignments);
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
/*      */   public CellConstraints rc(int row, int col, Alignment rowAlign, Alignment colAlign) {
/*  629 */     return rchw(row, col, 1, 1, rowAlign, colAlign);
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
/*      */   public CellConstraints rcw(int row, int col, int colSpan) {
/*  650 */     return rchw(row, col, 1, colSpan, DEFAULT, DEFAULT);
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
/*      */   public CellConstraints rcw(int row, int col, int colSpan, String encodedAlignments) {
/*  678 */     return rchw(row, col, 1, colSpan, encodedAlignments);
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
/*      */   public CellConstraints rcw(int row, int col, int colSpan, Alignment rowAlign, Alignment colAlign) {
/*  705 */     return rchw(row, col, 1, colSpan, rowAlign, colAlign);
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
/*      */   public CellConstraints rchw(int row, int col, int rowSpan, int colSpan) {
/*  726 */     return rchw(row, col, rowSpan, colSpan, DEFAULT, DEFAULT);
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
/*      */   public CellConstraints rchw(int row, int col, int rowSpan, int colSpan, String encodedAlignments) {
/*  753 */     CellConstraints result = rchw(row, col, rowSpan, colSpan);
/*  754 */     result.setAlignments(encodedAlignments, false);
/*  755 */     return result;
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
/*      */   public CellConstraints rchw(int row, int col, int rowSpan, int colSpan, Alignment rowAlign, Alignment colAlign) {
/*  782 */     return xywh(col, row, colSpan, rowSpan, colAlign, rowAlign);
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
/*      */   private void initFromConstraints(String encodedConstraints) {
/*  805 */     StringTokenizer tokenizer = new StringTokenizer(encodedConstraints, " ,");
/*  806 */     int argCount = tokenizer.countTokens();
/*  807 */     if (argCount != 2 && argCount != 4 && argCount != 6) {
/*  808 */       throw new IllegalArgumentException("You must provide 2, 4 or 6 arguments.");
/*      */     }
/*      */     
/*  811 */     Integer nextInt = decodeInt(tokenizer.nextToken());
/*  812 */     if (nextInt == null) {
/*  813 */       throw new IllegalArgumentException("First cell constraint element must be a number.");
/*      */     }
/*      */     
/*  816 */     this.gridX = nextInt.intValue();
/*  817 */     if (this.gridX <= 0) {
/*  818 */       throw new IndexOutOfBoundsException("The grid x must be a positive number.");
/*      */     }
/*  820 */     nextInt = decodeInt(tokenizer.nextToken());
/*  821 */     if (nextInt == null) {
/*  822 */       throw new IllegalArgumentException("Second cell constraint element must be a number.");
/*      */     }
/*      */     
/*  825 */     this.gridY = nextInt.intValue();
/*  826 */     if (this.gridY <= 0) {
/*  827 */       throw new IndexOutOfBoundsException("The grid y must be a positive number.");
/*      */     }
/*      */     
/*  830 */     if (!tokenizer.hasMoreTokens()) {
/*      */       return;
/*      */     }
/*  833 */     String token = tokenizer.nextToken();
/*  834 */     nextInt = decodeInt(token);
/*  835 */     if (nextInt != null) {
/*      */ 
/*      */       
/*  838 */       this.gridWidth = nextInt.intValue();
/*  839 */       if (this.gridWidth <= 0) {
/*  840 */         throw new IndexOutOfBoundsException("The grid width must be a positive number.");
/*      */       }
/*  842 */       nextInt = decodeInt(tokenizer.nextToken());
/*  843 */       if (nextInt == null) {
/*  844 */         throw new IllegalArgumentException("Fourth cell constraint element must be like third.");
/*      */       }
/*  846 */       this.gridHeight = nextInt.intValue();
/*  847 */       if (this.gridHeight <= 0) {
/*  848 */         throw new IndexOutOfBoundsException("The grid height must be a positive number.");
/*      */       }
/*      */       
/*  851 */       if (!tokenizer.hasMoreTokens())
/*      */         return; 
/*  853 */       token = tokenizer.nextToken();
/*      */     } 
/*      */     
/*  856 */     this.hAlign = decodeAlignment(token);
/*  857 */     this.vAlign = decodeAlignment(tokenizer.nextToken());
/*  858 */     ensureValidOrientations(this.hAlign, this.vAlign);
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
/*      */   private void setAlignments(String encodedAlignments, boolean horizontalThenVertical) {
/*  883 */     StringTokenizer tokenizer = new StringTokenizer(encodedAlignments, " ,");
/*  884 */     Alignment first = decodeAlignment(tokenizer.nextToken());
/*  885 */     Alignment second = decodeAlignment(tokenizer.nextToken());
/*  886 */     this.hAlign = horizontalThenVertical ? first : second;
/*  887 */     this.vAlign = horizontalThenVertical ? second : first;
/*  888 */     ensureValidOrientations(this.hAlign, this.vAlign);
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
/*      */   private Integer decodeInt(String token) {
/*      */     try {
/*  901 */       return Integer.decode(token);
/*  902 */     } catch (NumberFormatException e) {
/*  903 */       return null;
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
/*      */   private Alignment decodeAlignment(String encodedAlignment) {
/*  916 */     return Alignment.valueOf(encodedAlignment);
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
/*      */   void ensureValidGridBounds(int colCount, int rowCount) {
/*  930 */     if (this.gridX <= 0) {
/*  931 */       throw new IndexOutOfBoundsException("The column index " + this.gridX + " must be positive.");
/*      */     }
/*      */     
/*  934 */     if (this.gridX > colCount) {
/*  935 */       throw new IndexOutOfBoundsException("The column index " + this.gridX + " must be less than or equal to " + colCount + ".");
/*      */     }
/*      */ 
/*      */     
/*  939 */     if (this.gridX + this.gridWidth - 1 > colCount) {
/*  940 */       throw new IndexOutOfBoundsException("The grid width " + this.gridWidth + " must be less than or equal to " + (colCount - this.gridX + 1) + ".");
/*      */     }
/*      */ 
/*      */     
/*  944 */     if (this.gridY <= 0) {
/*  945 */       throw new IndexOutOfBoundsException("The row index " + this.gridY + " must be positive.");
/*      */     }
/*      */     
/*  948 */     if (this.gridY > rowCount) {
/*  949 */       throw new IndexOutOfBoundsException("The row index " + this.gridY + " must be less than or equal to " + rowCount + ".");
/*      */     }
/*      */ 
/*      */     
/*  953 */     if (this.gridY + this.gridHeight - 1 > rowCount) {
/*  954 */       throw new IndexOutOfBoundsException("The grid height " + this.gridHeight + " must be less than or equal to " + (rowCount - this.gridY + 1) + ".");
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
/*      */   private void ensureValidOrientations(Alignment horizontalAlignment, Alignment verticalAlignment) {
/*  970 */     if (!horizontalAlignment.isHorizontal())
/*  971 */       throw new IllegalArgumentException("The horizontal alignment must be one of: left, center, right, fill, default."); 
/*  972 */     if (!verticalAlignment.isVertical()) {
/*  973 */       throw new IllegalArgumentException("The vertical alignment must be one of: top, center, botto, fill, default.");
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
/*      */   void setBounds(Component c, FormLayout layout, Rectangle cellBounds, FormLayout.Measure minWidthMeasure, FormLayout.Measure minHeightMeasure, FormLayout.Measure prefWidthMeasure, FormLayout.Measure prefHeightMeasure) {
/*  996 */     ColumnSpec colSpec = (this.gridWidth == 1) ? layout.getColumnSpec(this.gridX) : null;
/*  997 */     RowSpec rowSpec = (this.gridHeight == 1) ? layout.getRowSpec(this.gridY) : null;
/*  998 */     Alignment concreteHAlign = concreteAlignment(this.hAlign, colSpec);
/*  999 */     Alignment concreteVAlign = concreteAlignment(this.vAlign, rowSpec);
/* 1000 */     Insets concreteInsets = (this.insets != null) ? this.insets : EMPTY_INSETS;
/* 1001 */     int cellX = cellBounds.x + concreteInsets.left;
/* 1002 */     int cellY = cellBounds.y + concreteInsets.top;
/* 1003 */     int cellW = cellBounds.width - concreteInsets.left - concreteInsets.right;
/* 1004 */     int cellH = cellBounds.height - concreteInsets.top - concreteInsets.bottom;
/* 1005 */     int compW = componentSize(c, colSpec, cellW, minWidthMeasure, prefWidthMeasure);
/*      */     
/* 1007 */     int compH = componentSize(c, rowSpec, cellH, minHeightMeasure, prefHeightMeasure);
/*      */     
/* 1009 */     int x = origin(concreteHAlign, cellX, cellW, compW);
/* 1010 */     int y = origin(concreteVAlign, cellY, cellH, compH);
/* 1011 */     int w = extent(concreteHAlign, cellW, compW);
/* 1012 */     int h = extent(concreteVAlign, cellH, compH);
/* 1013 */     c.setBounds(x, y, w, h);
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
/*      */   private Alignment concreteAlignment(Alignment cellAlignment, FormSpec formSpec) {
/* 1035 */     return (formSpec == null) ? ((cellAlignment == DEFAULT) ? FILL : cellAlignment) : usedAlignment(cellAlignment, formSpec);
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
/*      */   private Alignment usedAlignment(Alignment cellAlignment, FormSpec formSpec) {
/* 1052 */     if (cellAlignment != DEFAULT)
/*      */     {
/* 1054 */       return cellAlignment;
/*      */     }
/* 1056 */     FormSpec.DefaultAlignment defaultAlignment = formSpec.getDefaultAlignment();
/* 1057 */     if (defaultAlignment == FormSpec.FILL_ALIGN)
/* 1058 */       return FILL; 
/* 1059 */     if (defaultAlignment == ColumnSpec.LEFT)
/* 1060 */       return LEFT; 
/* 1061 */     if (defaultAlignment == FormSpec.CENTER_ALIGN)
/* 1062 */       return CENTER; 
/* 1063 */     if (defaultAlignment == ColumnSpec.RIGHT)
/* 1064 */       return RIGHT; 
/* 1065 */     if (defaultAlignment == RowSpec.TOP) {
/* 1066 */       return TOP;
/*      */     }
/* 1068 */     return BOTTOM;
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
/*      */   private int componentSize(Component component, FormSpec formSpec, int cellSize, FormLayout.Measure minMeasure, FormLayout.Measure prefMeasure) {
/* 1088 */     if (formSpec == null)
/* 1089 */       return prefMeasure.sizeOf(component); 
/* 1090 */     if (formSpec.getSize() == Sizes.MINIMUM)
/* 1091 */       return minMeasure.sizeOf(component); 
/* 1092 */     if (formSpec.getSize() == Sizes.PREFERRED) {
/* 1093 */       return prefMeasure.sizeOf(component);
/*      */     }
/* 1095 */     return Math.min(cellSize, prefMeasure.sizeOf(component));
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
/*      */   private int origin(Alignment alignment, int cellOrigin, int cellSize, int componentSize) {
/* 1113 */     if (alignment == RIGHT || alignment == BOTTOM)
/* 1114 */       return cellOrigin + cellSize - componentSize; 
/* 1115 */     if (alignment == CENTER) {
/* 1116 */       return cellOrigin + (cellSize - componentSize) / 2;
/*      */     }
/* 1118 */     return cellOrigin;
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
/*      */   private int extent(Alignment alignment, int cellSize, int componentSize) {
/* 1132 */     return (alignment == FILL) ? cellSize : componentSize;
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
/*      */   public Object clone() {
/*      */     try {
/* 1147 */       CellConstraints c = (CellConstraints)super.clone();
/* 1148 */       c.insets = (Insets)this.insets.clone();
/* 1149 */       return c;
/* 1150 */     } catch (CloneNotSupportedException e) {
/*      */       
/* 1152 */       throw new InternalError();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1163 */     StringBuffer buffer = new StringBuffer("CellConstraints");
/* 1164 */     buffer.append("[x=");
/* 1165 */     buffer.append(this.gridX);
/* 1166 */     buffer.append("; y=");
/* 1167 */     buffer.append(this.gridY);
/* 1168 */     buffer.append("; w=");
/* 1169 */     buffer.append(this.gridWidth);
/* 1170 */     buffer.append("; h=");
/* 1171 */     buffer.append(this.gridHeight);
/* 1172 */     buffer.append("; hAlign=");
/* 1173 */     buffer.append(this.hAlign);
/* 1174 */     buffer.append("; vAlign=");
/* 1175 */     buffer.append(this.vAlign);
/* 1176 */     if (!EMPTY_INSETS.equals(this.insets)) {
/* 1177 */       buffer.append("; insets=");
/* 1178 */       buffer.append(this.insets);
/*      */     } 
/* 1180 */     buffer.append("; honorsVisibility=");
/* 1181 */     buffer.append(this.honorsVisibility);
/*      */     
/* 1183 */     buffer.append(']');
/* 1184 */     return buffer.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toShortString() {
/* 1194 */     return toShortString(null);
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
/*      */   public String toShortString(FormLayout layout) {
/* 1209 */     StringBuffer buffer = new StringBuffer("(");
/* 1210 */     buffer.append(formatInt(this.gridX));
/* 1211 */     buffer.append(", ");
/* 1212 */     buffer.append(formatInt(this.gridY));
/* 1213 */     buffer.append(", ");
/* 1214 */     buffer.append(formatInt(this.gridWidth));
/* 1215 */     buffer.append(", ");
/* 1216 */     buffer.append(formatInt(this.gridHeight));
/* 1217 */     buffer.append(", \"");
/* 1218 */     buffer.append(this.hAlign.abbreviation());
/* 1219 */     if (this.hAlign == DEFAULT && layout != null) {
/* 1220 */       buffer.append('=');
/* 1221 */       ColumnSpec colSpec = (this.gridWidth == 1) ? layout.getColumnSpec(this.gridX) : null;
/*      */ 
/*      */       
/* 1224 */       buffer.append(concreteAlignment(this.hAlign, colSpec).abbreviation());
/*      */     } 
/* 1226 */     buffer.append(", ");
/* 1227 */     buffer.append(this.vAlign.abbreviation());
/* 1228 */     if (this.vAlign == DEFAULT && layout != null) {
/* 1229 */       buffer.append('=');
/* 1230 */       RowSpec rowSpec = (this.gridHeight == 1) ? layout.getRowSpec(this.gridY) : null;
/*      */ 
/*      */       
/* 1233 */       buffer.append(concreteAlignment(this.vAlign, rowSpec).abbreviation());
/*      */     } 
/* 1235 */     buffer.append("\"");
/* 1236 */     if (!EMPTY_INSETS.equals(this.insets)) {
/* 1237 */       buffer.append(", ");
/* 1238 */       buffer.append(this.insets);
/*      */     } 
/* 1240 */     if (this.honorsVisibility != null) {
/* 1241 */       buffer.append(this.honorsVisibility.booleanValue() ? "honors visibility" : "ignores visibility");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1246 */     buffer.append(')');
/* 1247 */     return buffer.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final class Alignment
/*      */     implements Serializable
/*      */   {
/*      */     private static final int HORIZONTAL = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final int VERTICAL = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final int BOTH = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final transient String name;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final transient int orientation;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     static Alignment valueOf(String nameOrAbbreviation) {
/*      */       String str = nameOrAbbreviation.toLowerCase(Locale.ENGLISH);
/*      */       if (str.equals("d") || str.equals("default")) {
/*      */         return CellConstraints.DEFAULT;
/*      */       }
/*      */       if (str.equals("f") || str.equals("fill")) {
/*      */         return CellConstraints.FILL;
/*      */       }
/*      */       if (str.equals("c") || str.equals("center")) {
/*      */         return CellConstraints.CENTER;
/*      */       }
/*      */       if (str.equals("l") || str.equals("left")) {
/*      */         return CellConstraints.LEFT;
/*      */       }
/*      */       if (str.equals("r") || str.equals("right")) {
/*      */         return CellConstraints.RIGHT;
/*      */       }
/*      */       if (str.equals("t") || str.equals("top")) {
/*      */         return CellConstraints.TOP;
/*      */       }
/*      */       if (str.equals("b") || str.equals("bottom")) {
/*      */         return CellConstraints.BOTTOM;
/*      */       }
/*      */       throw new IllegalArgumentException("Invalid alignment " + nameOrAbbreviation + ". Must be one of: left, center, right, top, bottom, " + "fill, default, l, c, r, t, b, f, d.");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/*      */       return this.name;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public char abbreviation() {
/*      */       return this.name.charAt(0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Alignment(String name, int orientation) {
/* 1326 */       this.ordinal = nextOrdinal++;
/*      */       this.name = name;
/*      */       this.orientation = orientation; } private Object readResolve() {
/* 1329 */       return CellConstraints.VALUES[this.ordinal];
/*      */     }
/*      */     private boolean isHorizontal() {
/*      */       return (this.orientation != 1);
/*      */     }
/*      */     private boolean isVertical() {
/*      */       return (this.orientation != 0);
/*      */     }
/*      */     
/*      */     private static int nextOrdinal = 0;
/*      */     private final int ordinal; }
/*      */   
/*      */   private String formatInt(int number) {
/* 1342 */     String str = Integer.toString(number);
/* 1343 */     return (number < 10) ? (" " + str) : str;
/*      */   }
/*      */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\jgoodies\forms\layout\CellConstraints.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */