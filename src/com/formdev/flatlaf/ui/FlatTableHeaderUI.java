package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D.Float;
import java.beans.PropertyChangeListener;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.plaf.basic.BasicTableHeaderUI.MouseInputHandler;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class FlatTableHeaderUI extends BasicTableHeaderUI implements FlatStylingSupport.StyleableUI {
   @FlatStylingSupport.Styleable
   protected Color hoverBackground;
   @FlatStylingSupport.Styleable
   protected Color hoverForeground;
   @FlatStylingSupport.Styleable
   protected Color pressedBackground;
   @FlatStylingSupport.Styleable
   protected Color pressedForeground;
   @FlatStylingSupport.Styleable
   protected Color bottomSeparatorColor;
   @FlatStylingSupport.Styleable
   protected int height;
   @FlatStylingSupport.Styleable(
      type = String.class
   )
   protected int sortIconPosition;
   @FlatStylingSupport.Styleable
   protected Insets cellMargins;
   @FlatStylingSupport.Styleable
   protected Color separatorColor;
   @FlatStylingSupport.Styleable
   protected Boolean showTrailingVerticalLine;
   @FlatStylingSupport.Styleable
   public String arrowType;
   @FlatStylingSupport.Styleable
   public Color sortIconColor;
   private PropertyChangeListener propertyChangeListener;
   private Map<String, Object> oldStyleValues;

   public static ComponentUI createUI(JComponent c) {
      return new FlatTableHeaderUI();
   }

   public void installUI(JComponent c) {
      super.installUI(c);
      this.installStyle();
   }

   protected void installDefaults() {
      super.installDefaults();
      this.hoverBackground = UIManager.getColor("TableHeader.hoverBackground");
      this.hoverForeground = UIManager.getColor("TableHeader.hoverForeground");
      this.pressedBackground = UIManager.getColor("TableHeader.pressedBackground");
      this.pressedForeground = UIManager.getColor("TableHeader.pressedForeground");
      this.bottomSeparatorColor = UIManager.getColor("TableHeader.bottomSeparatorColor");
      this.height = UIManager.getInt("TableHeader.height");
      this.sortIconPosition = parseSortIconPosition(UIManager.getString("TableHeader.sortIconPosition"));
   }

   protected void uninstallDefaults() {
      super.uninstallDefaults();
      this.hoverBackground = null;
      this.hoverForeground = null;
      this.pressedBackground = null;
      this.pressedForeground = null;
      this.bottomSeparatorColor = null;
      this.oldStyleValues = null;
   }

   protected void installListeners() {
      super.installListeners();
      this.propertyChangeListener = FlatStylingSupport.createPropertyChangeListener(this.header, this::installStyle, (PropertyChangeListener)null);
      this.header.addPropertyChangeListener(this.propertyChangeListener);
   }

   protected void uninstallListeners() {
      super.uninstallListeners();
      this.header.removePropertyChangeListener(this.propertyChangeListener);
      this.propertyChangeListener = null;
   }

   protected void installStyle() {
      try {
         this.applyStyle(FlatStylingSupport.getResolvedStyle(this.header, "TableHeader"));
      } catch (RuntimeException var2) {
         LoggingFacade.INSTANCE.logSevere((String)null, var2);
      }

   }

   protected void applyStyle(Object style) {
      this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
   }

   protected Object applyStyleProperty(String key, Object value) {
      if (key.equals("sortIconPosition") && value instanceof String) {
         value = parseSortIconPosition((String)value);
      }

      return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, this.header, key, value);
   }

   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
      return FlatStylingSupport.getAnnotatedStyleableInfos(this);
   }

   public Object getStyleableValue(JComponent c, String key) {
      if (key.equals("sortIconPosition")) {
         switch(this.sortIconPosition) {
         case 1:
            return "top";
         case 2:
            return "left";
         case 3:
            return "bottom";
         case 4:
         default:
            return "right";
         }
      } else {
         return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
      }
   }

   private static int parseSortIconPosition(String str) {
      if (str == null) {
         str = "";
      }

      byte var2 = -1;
      switch(str.hashCode()) {
      case -1383228885:
         if (str.equals("bottom")) {
            var2 = 4;
         }
         break;
      case 115029:
         if (str.equals("top")) {
            var2 = 3;
         }
         break;
      case 3317767:
         if (str.equals("left")) {
            var2 = 2;
         }
         break;
      case 108511772:
         if (str.equals("right")) {
            var2 = 1;
         }
      }

      switch(var2) {
      case 1:
      default:
         return 4;
      case 2:
         return 2;
      case 3:
         return 1;
      case 4:
         return 3;
      }
   }

   protected MouseInputListener createMouseInputListener() {
      return new FlatTableHeaderUI.FlatMouseInputHandler();
   }

   public int getRolloverColumn() {
      return super.getRolloverColumn();
   }

   protected void rolloverColumnUpdated(int oldColumn, int newColumn) {
      this.header.repaint(this.header.getHeaderRect(oldColumn));
      this.header.repaint(this.header.getHeaderRect(newColumn));
   }

   public void paint(Graphics g, JComponent c) {
      fixDraggedAndResizingColumns(this.header);
      TableColumnModel columnModel = this.header.getColumnModel();
      if (columnModel.getColumnCount() > 0) {
         int columnCount = columnModel.getColumnCount();
         int totalWidth = 0;

         for(int i = 0; i < columnCount; ++i) {
            totalWidth += columnModel.getColumn(i).getWidth();
         }

         if (totalWidth < this.header.getWidth()) {
            TableCellRenderer defaultRenderer = this.header.getDefaultRenderer();
            boolean paintBottomSeparator = this.isSystemDefaultRenderer(defaultRenderer);
            if (!paintBottomSeparator && this.header.getTable() != null) {
               Component rendererComponent = defaultRenderer.getTableCellRendererComponent(this.header.getTable(), "", false, false, -1, 0);
               paintBottomSeparator = this.isSystemDefaultRenderer(rendererComponent);
            }

            if (paintBottomSeparator) {
               int w = c.getWidth() - totalWidth;
               int x = this.header.getComponentOrientation().isLeftToRight() ? c.getWidth() - w : 0;
               this.paintBottomSeparator(g, c, x, w);
            }
         }

         FlatTableHeaderUI.FlatTableCellHeaderRenderer tempRenderer = new FlatTableHeaderUI.FlatTableCellHeaderRenderer(this.header.getDefaultRenderer());
         this.header.setDefaultRenderer(tempRenderer);
         super.paint(g, c);
         tempRenderer.reset();
         this.header.setDefaultRenderer(tempRenderer.delegate);
      }
   }

   private boolean isSystemDefaultRenderer(Object headerRenderer) {
      String rendererClassName = headerRenderer.getClass().getName();
      return rendererClassName.equals("sun.swing.table.DefaultTableCellHeaderRenderer") || rendererClassName.equals("sun.swing.FilePane$AlignableTableHeaderRenderer");
   }

   protected void paintBottomSeparator(Graphics g, JComponent c, int x, int w) {
      float lineWidth = UIScale.scale(1.0F);
      Graphics2D g2 = (Graphics2D)g.create();

      try {
         FlatUIUtils.setRenderingHints(g2);
         g2.setColor(this.bottomSeparatorColor);
         g2.fill(new Float((float)x, (float)c.getHeight() - lineWidth, (float)w, lineWidth));
      } finally {
         g2.dispose();
      }

   }

   public Dimension getPreferredSize(JComponent c) {
      Dimension size = super.getPreferredSize(c);
      if (size.height > 0) {
         size.height = Math.max(size.height, UIScale.scale(this.height));
      }

      return size;
   }

   static void fixDraggedAndResizingColumns(JTableHeader header) {
      if (header != null) {
         TableColumn draggedColumn = header.getDraggedColumn();
         if (draggedColumn != null && !isValidColumn(header.getColumnModel(), draggedColumn)) {
            header.setDraggedColumn((TableColumn)null);
         }

         TableColumn resizingColumn = header.getResizingColumn();
         if (resizingColumn != null && !isValidColumn(header.getColumnModel(), resizingColumn)) {
            header.setResizingColumn((TableColumn)null);
         }

      }
   }

   private static boolean isValidColumn(TableColumnModel cm, TableColumn column) {
      int count = cm.getColumnCount();

      for(int i = 0; i < count; ++i) {
         if (cm.getColumn(i) == column) {
            return true;
         }
      }

      return false;
   }

   protected class FlatMouseInputHandler extends MouseInputHandler {
      Cursor oldCursor;

      protected FlatMouseInputHandler() {
         super(FlatTableHeaderUI.this);
      }

      public void mouseMoved(MouseEvent e) {
         if (this.oldCursor != null) {
            FlatTableHeaderUI.this.header.setCursor(this.oldCursor);
            this.oldCursor = null;
         }

         super.mouseMoved(e);
         JTable table;
         int column;
         if (FlatTableHeaderUI.this.header.isEnabled() && (table = FlatTableHeaderUI.this.header.getTable()) != null && table.getAutoResizeMode() != 0 && FlatTableHeaderUI.this.header.getCursor() == Cursor.getPredefinedCursor(11) && (column = FlatTableHeaderUI.this.header.columnAtPoint(e.getPoint())) >= 0 && column == FlatTableHeaderUI.this.header.getColumnModel().getColumnCount() - 1) {
            Rectangle r = FlatTableHeaderUI.this.header.getHeaderRect(column);
            r.grow(-3, 0);
            if (!r.contains(e.getX(), e.getY())) {
               boolean isResizeLastColumn = e.getX() >= r.x + r.width / 2;
               if (!FlatTableHeaderUI.this.header.getComponentOrientation().isLeftToRight()) {
                  isResizeLastColumn = !isResizeLastColumn;
               }

               if (isResizeLastColumn) {
                  this.oldCursor = FlatTableHeaderUI.this.header.getCursor();
                  FlatTableHeaderUI.this.header.setCursor(Cursor.getDefaultCursor());
               }
            }
         }

      }
   }

   private class FlatTableCellHeaderRenderer implements TableCellRenderer, Border, UIResource {
      private final TableCellRenderer delegate;
      private JLabel l;
      private Color oldBackground;
      private Color oldForeground;
      private Boolean oldOpaque;
      private int oldHorizontalTextPosition = -1;
      private Border origBorder;
      private Icon sortIcon;

      FlatTableCellHeaderRenderer(TableCellRenderer delegate) {
         this.delegate = delegate;
      }

      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
         Component c = this.delegate.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
         if (!(c instanceof JLabel)) {
            return c;
         } else {
            this.l = (JLabel)c;
            TableColumn draggedColumn = FlatTableHeaderUI.this.header.getDraggedColumn();
            Color background = null;
            Color foreground = null;
            if (draggedColumn != null && FlatTableHeaderUI.this.header.getTable().convertColumnIndexToView(draggedColumn.getModelIndex()) == column) {
               background = FlatTableHeaderUI.this.pressedBackground;
               foreground = FlatTableHeaderUI.this.pressedForeground;
            } else if (FlatTableHeaderUI.this.getRolloverColumn() == column) {
               background = FlatTableHeaderUI.this.hoverBackground;
               foreground = FlatTableHeaderUI.this.hoverForeground;
            }

            if (background != null) {
               if (this.oldBackground == null) {
                  this.oldBackground = this.l.getBackground();
               }

               if (this.oldOpaque == null) {
                  this.oldOpaque = this.l.isOpaque();
               }

               this.l.setBackground(FlatUIUtils.deriveColor(background, FlatTableHeaderUI.this.header.getBackground()));
               this.l.setOpaque(true);
            }

            if (foreground != null) {
               if (this.oldForeground == null) {
                  this.oldForeground = this.l.getForeground();
               }

               this.l.setForeground(FlatUIUtils.deriveColor(foreground, FlatTableHeaderUI.this.header.getForeground()));
            }

            if (FlatTableHeaderUI.this.sortIconPosition == 2) {
               if (this.oldHorizontalTextPosition < 0) {
                  this.oldHorizontalTextPosition = this.l.getHorizontalTextPosition();
               }

               this.l.setHorizontalTextPosition(4);
            } else if (FlatTableHeaderUI.this.sortIconPosition == 1 || FlatTableHeaderUI.this.sortIconPosition == 3) {
               this.sortIcon = this.l.getIcon();
               this.origBorder = this.l.getBorder();
               this.l.setIcon((Icon)null);
               this.l.setBorder(this);
            }

            return this.l;
         }
      }

      void reset() {
         if (this.l != null) {
            if (this.oldBackground != null) {
               this.l.setBackground(this.oldBackground);
            }

            if (this.oldForeground != null) {
               this.l.setForeground(this.oldForeground);
            }

            if (this.oldOpaque != null) {
               this.l.setOpaque(this.oldOpaque);
            }

            if (this.oldHorizontalTextPosition >= 0) {
               this.l.setHorizontalTextPosition(this.oldHorizontalTextPosition);
            }

         }
      }

      public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
         if (this.origBorder != null) {
            this.origBorder.paintBorder(c, g, x, y, width, height);
         }

         if (this.sortIcon != null) {
            int xi = x + (width - this.sortIcon.getIconWidth()) / 2;
            int yi = FlatTableHeaderUI.this.sortIconPosition == 1 ? y + UIScale.scale(1) : y + height - this.sortIcon.getIconHeight() - 1 - (int)(1.0F * UIScale.getUserScaleFactor());
            this.sortIcon.paintIcon(c, g, xi, yi);
         }

      }

      public Insets getBorderInsets(Component c) {
         return this.origBorder != null ? this.origBorder.getBorderInsets(c) : new Insets(0, 0, 0, 0);
      }

      public boolean isBorderOpaque() {
         return this.origBorder != null ? this.origBorder.isBorderOpaque() : false;
      }
   }
}
