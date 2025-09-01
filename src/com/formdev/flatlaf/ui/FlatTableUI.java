package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.Graphics2DProxy;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Rectangle2D.Double;
import java.beans.PropertyChangeListener;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.plaf.basic.BasicTableUI.FocusHandler;
import javax.swing.table.JTableHeader;

public class FlatTableUI extends BasicTableUI implements FlatStylingSupport.StyleableUI, FlatViewportUI.ViewportPainter {
   protected boolean showHorizontalLines;
   protected boolean showVerticalLines;
   @FlatStylingSupport.Styleable
   protected boolean showTrailingVerticalLine;
   protected Dimension intercellSpacing;
   @FlatStylingSupport.Styleable
   protected Color selectionBackground;
   @FlatStylingSupport.Styleable
   protected Color selectionForeground;
   @FlatStylingSupport.Styleable
   protected Color selectionInactiveBackground;
   @FlatStylingSupport.Styleable
   protected Color selectionInactiveForeground;
   @FlatStylingSupport.Styleable
   protected Insets cellMargins;
   @FlatStylingSupport.Styleable
   protected Color cellFocusColor;
   @FlatStylingSupport.Styleable
   protected Boolean showCellFocusIndicator;
   private boolean oldShowHorizontalLines;
   private boolean oldShowVerticalLines;
   private Dimension oldIntercellSpacing;
   private PropertyChangeListener propertyChangeListener;
   private Map<String, Object> oldStyleValues;

   public static ComponentUI createUI(JComponent c) {
      return new FlatTableUI();
   }

   public void installUI(JComponent c) {
      super.installUI(c);
      this.installStyle();
   }

   protected void installDefaults() {
      super.installDefaults();
      this.showHorizontalLines = UIManager.getBoolean("Table.showHorizontalLines");
      this.showVerticalLines = UIManager.getBoolean("Table.showVerticalLines");
      this.showTrailingVerticalLine = UIManager.getBoolean("Table.showTrailingVerticalLine");
      this.intercellSpacing = UIManager.getDimension("Table.intercellSpacing");
      this.selectionBackground = UIManager.getColor("Table.selectionBackground");
      this.selectionForeground = UIManager.getColor("Table.selectionForeground");
      this.selectionInactiveBackground = UIManager.getColor("Table.selectionInactiveBackground");
      this.selectionInactiveForeground = UIManager.getColor("Table.selectionInactiveForeground");
      this.toggleSelectionColors();
      int rowHeight = FlatUIUtils.getUIInt("Table.rowHeight", 16);
      if (rowHeight > 0) {
         LookAndFeel.installProperty(this.table, "rowHeight", UIScale.scale(rowHeight));
      }

      if (!this.showHorizontalLines) {
         this.oldShowHorizontalLines = this.table.getShowHorizontalLines();
         this.table.setShowHorizontalLines(false);
      }

      if (!this.showVerticalLines) {
         this.oldShowVerticalLines = this.table.getShowVerticalLines();
         this.table.setShowVerticalLines(false);
      }

      if (this.intercellSpacing != null) {
         this.oldIntercellSpacing = this.table.getIntercellSpacing();
         this.table.setIntercellSpacing(this.intercellSpacing);
      }

   }

   protected void uninstallDefaults() {
      super.uninstallDefaults();
      this.selectionBackground = null;
      this.selectionForeground = null;
      this.selectionInactiveBackground = null;
      this.selectionInactiveForeground = null;
      this.oldStyleValues = null;
      if (!this.showHorizontalLines && this.oldShowHorizontalLines && !this.table.getShowHorizontalLines()) {
         this.table.setShowHorizontalLines(true);
      }

      if (!this.showVerticalLines && this.oldShowVerticalLines && !this.table.getShowVerticalLines()) {
         this.table.setShowVerticalLines(true);
      }

      if (this.intercellSpacing != null && this.table.getIntercellSpacing().equals(this.intercellSpacing)) {
         this.table.setIntercellSpacing(this.oldIntercellSpacing);
      }

   }

   protected void installListeners() {
      super.installListeners();
      this.propertyChangeListener = (e) -> {
         String var2 = e.getPropertyName();
         byte var3 = -1;
         switch(var2.hashCode()) {
         case 1030195901:
            if (var2.equals("FlatLaf.styleClass")) {
               var3 = 2;
            }
            break;
         case 1545413499:
            if (var2.equals("FlatLaf.style")) {
               var3 = 1;
            }
            break;
         case 1859588534:
            if (var2.equals("JComponent.focusOwner")) {
               var3 = 0;
            }
         }

         switch(var3) {
         case 0:
            this.toggleSelectionColors();
            break;
         case 1:
         case 2:
            this.installStyle();
            this.table.revalidate();
            this.table.repaint();
         }

      };
      this.table.addPropertyChangeListener(this.propertyChangeListener);
   }

   protected void uninstallListeners() {
      super.uninstallListeners();
      this.table.removePropertyChangeListener(this.propertyChangeListener);
      this.propertyChangeListener = null;
   }

   protected FocusListener createFocusListener() {
      return new FocusHandler() {
         public void focusGained(FocusEvent e) {
            super.focusGained(e);
            FlatTableUI.this.toggleSelectionColors();
         }

         public void focusLost(FocusEvent e) {
            super.focusLost(e);
            EventQueue.invokeLater(() -> {
               FlatTableUI.this.toggleSelectionColors();
            });
         }
      };
   }

   protected void installStyle() {
      try {
         this.applyStyle(FlatStylingSupport.getResolvedStyle(this.table, "Table"));
      } catch (RuntimeException var2) {
         LoggingFacade.INSTANCE.logSevere((String)null, var2);
      }

   }

   protected void applyStyle(Object style) {
      Color oldSelectionBackground = this.selectionBackground;
      Color oldSelectionForeground = this.selectionForeground;
      Color oldSelectionInactiveBackground = this.selectionInactiveBackground;
      Color oldSelectionInactiveForeground = this.selectionInactiveForeground;
      this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
      Color selFg;
      if (this.selectionBackground != oldSelectionBackground) {
         selFg = this.table.getSelectionBackground();
         if (selFg == oldSelectionBackground) {
            this.table.setSelectionBackground(this.selectionBackground);
         } else if (selFg == oldSelectionInactiveBackground) {
            this.table.setSelectionBackground(this.selectionInactiveBackground);
         }
      }

      if (this.selectionForeground != oldSelectionForeground) {
         selFg = this.table.getSelectionForeground();
         if (selFg == oldSelectionForeground) {
            this.table.setSelectionForeground(this.selectionForeground);
         } else if (selFg == oldSelectionInactiveForeground) {
            this.table.setSelectionForeground(this.selectionInactiveForeground);
         }
      }

   }

   protected Object applyStyleProperty(String key, Object value) {
      return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, this.table, key, value);
   }

   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
      return FlatStylingSupport.getAnnotatedStyleableInfos(this);
   }

   public Object getStyleableValue(JComponent c, String key) {
      return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
   }

   private void toggleSelectionColors() {
      if (this.table != null) {
         if (FlatUIUtils.isPermanentFocusOwner(this.table)) {
            if (this.table.getSelectionBackground() == this.selectionInactiveBackground) {
               this.table.setSelectionBackground(this.selectionBackground);
            }

            if (this.table.getSelectionForeground() == this.selectionInactiveForeground) {
               this.table.setSelectionForeground(this.selectionForeground);
            }
         } else {
            if (this.table.getSelectionBackground() == this.selectionBackground) {
               this.table.setSelectionBackground(this.selectionInactiveBackground);
            }

            if (this.table.getSelectionForeground() == this.selectionForeground) {
               this.table.setSelectionForeground(this.selectionInactiveForeground);
            }
         }

      }
   }

   public void paint(Graphics g, JComponent c) {
      FlatTableHeaderUI.fixDraggedAndResizingColumns(this.table.getTableHeader());
      final boolean horizontalLines = this.table.getShowHorizontalLines();
      final boolean verticalLines = this.table.getShowVerticalLines();
      if (horizontalLines || verticalLines) {
         final boolean hideLastVerticalLine = this.hideLastVerticalLine();
         final int tableWidth = this.table.getWidth();
         JTableHeader header = this.table.getTableHeader();
         final boolean isDragging = header != null && header.getDraggedColumn() != null;
         double systemScaleFactor = UIScale.getSystemScaleFactor((Graphics2D)g);
         final double lineThickness = 1.0D / systemScaleFactor * (double)((int)systemScaleFactor);
         g = new Graphics2DProxy((Graphics2D)g) {
            public void drawLine(int x1, int y1, int x2, int y2) {
               if (!hideLastVerticalLine || !verticalLines || x1 != x2 || y1 != 0 || x1 != tableWidth - 1 || !this.wasInvokedFromPaintGrid()) {
                  if (isDragging && SystemInfo.isJava_9_orLater && (horizontalLines && y1 == y2 || verticalLines && x1 == x2) && this.wasInvokedFromMethod("paintDraggedArea")) {
                     if (y1 == y2) {
                        super.fill(new Double((double)x1, (double)y1, (double)(x2 - x1 + 1), lineThickness));
                     } else if (x1 == x2) {
                        super.fill(new Double((double)x1, (double)y1, lineThickness, (double)(y2 - y1 + 1)));
                     }

                  } else {
                     super.drawLine(x1, y1, x2, y2);
                  }
               }
            }

            public void fillRect(int x, int y, int width, int height) {
               if (!hideLastVerticalLine || !verticalLines || width != 1 || y != 0 || x != tableWidth - 1 || !this.wasInvokedFromPaintGrid()) {
                  if (lineThickness != 1.0D) {
                     if (horizontalLines && height == 1 && this.wasInvokedFromPaintGrid()) {
                        super.fill(new Double((double)x, (double)y, (double)width, lineThickness));
                        return;
                     }

                     if (verticalLines && width == 1 && y == 0 && this.wasInvokedFromPaintGrid()) {
                        super.fill(new Double((double)x, (double)y, lineThickness, (double)height));
                        return;
                     }
                  }

                  super.fillRect(x, y, width, height);
               }
            }

            private boolean wasInvokedFromPaintGrid() {
               return this.wasInvokedFromMethod("paintGrid");
            }

            private boolean wasInvokedFromMethod(String methodName) {
               return StackUtils.wasInvokedFrom(BasicTableUI.class.getName(), methodName, 8);
            }
         };
      }

      super.paint((Graphics)g, c);
   }

   protected boolean hideLastVerticalLine() {
      if (this.showTrailingVerticalLine) {
         return false;
      } else {
         Container viewport = SwingUtilities.getUnwrappedParent(this.table);
         Container viewportParent = viewport != null ? viewport.getParent() : null;
         if (!(viewportParent instanceof JScrollPane)) {
            return false;
         } else if (this.table.getX() + this.table.getWidth() < viewport.getWidth()) {
            return false;
         } else {
            JScrollPane scrollPane = (JScrollPane)viewportParent;
            JViewport rowHeader = scrollPane.getRowHeader();
            return scrollPane.getComponentOrientation().isLeftToRight() ? viewport != rowHeader : viewport == rowHeader || rowHeader == null;
         }
      }
   }

   public void paintViewport(Graphics g, JComponent c, JViewport viewport) {
      int viewportWidth = viewport.getWidth();
      int viewportHeight = viewport.getHeight();
      if (viewport.isOpaque()) {
         g.setColor(this.table.getBackground());
         g.fillRect(0, 0, viewportWidth, viewportHeight);
      }

      boolean paintOutside = UIManager.getBoolean("Table.paintOutsideAlternateRows");
      Color alternateColor;
      if (paintOutside && (alternateColor = UIManager.getColor("Table.alternateRowColor")) != null) {
         g.setColor(alternateColor);
         int rowCount = this.table.getRowCount();
         int tableHeight = this.table.getHeight();
         if (tableHeight < viewportHeight) {
            int tableWidth = this.table.getWidth();
            int rowHeight = this.table.getRowHeight();
            int y = tableHeight;

            for(int row = rowCount; y < viewportHeight; ++row) {
               if (row % 2 != 0) {
                  g.fillRect(0, y, tableWidth, rowHeight);
               }

               y += rowHeight;
            }
         }
      }

   }
}
