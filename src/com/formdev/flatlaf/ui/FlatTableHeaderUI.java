/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.Map;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.event.MouseInputListener;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.UIResource;
/*     */ import javax.swing.plaf.basic.BasicTableHeaderUI;
/*     */ import javax.swing.table.JTableHeader;
/*     */ import javax.swing.table.TableCellRenderer;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatTableHeaderUI
/*     */   extends BasicTableHeaderUI
/*     */   implements FlatStylingSupport.StyleableUI
/*     */ {
/*     */   @Styleable
/*     */   protected Color hoverBackground;
/*     */   @Styleable
/*     */   protected Color hoverForeground;
/*     */   @Styleable
/*     */   protected Color pressedBackground;
/*     */   @Styleable
/*     */   protected Color pressedForeground;
/*     */   @Styleable
/*     */   protected Color bottomSeparatorColor;
/*     */   @Styleable
/*     */   protected int height;
/*     */   @Styleable(type = String.class)
/*     */   protected int sortIconPosition;
/*     */   @Styleable
/*     */   protected Insets cellMargins;
/*     */   @Styleable
/*     */   protected Color separatorColor;
/*     */   @Styleable
/*     */   protected Boolean showTrailingVerticalLine;
/*     */   @Styleable
/*     */   public String arrowType;
/*     */   @Styleable
/*     */   public Color sortIconColor;
/*     */   private PropertyChangeListener propertyChangeListener;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/* 110 */     return new FlatTableHeaderUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/* 115 */     super.installUI(c);
/*     */     
/* 117 */     installStyle();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/* 122 */     super.installDefaults();
/*     */     
/* 124 */     this.hoverBackground = UIManager.getColor("TableHeader.hoverBackground");
/* 125 */     this.hoverForeground = UIManager.getColor("TableHeader.hoverForeground");
/* 126 */     this.pressedBackground = UIManager.getColor("TableHeader.pressedBackground");
/* 127 */     this.pressedForeground = UIManager.getColor("TableHeader.pressedForeground");
/* 128 */     this.bottomSeparatorColor = UIManager.getColor("TableHeader.bottomSeparatorColor");
/* 129 */     this.height = UIManager.getInt("TableHeader.height");
/* 130 */     this.sortIconPosition = parseSortIconPosition(UIManager.getString("TableHeader.sortIconPosition"));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults() {
/* 135 */     super.uninstallDefaults();
/*     */     
/* 137 */     this.hoverBackground = null;
/* 138 */     this.hoverForeground = null;
/* 139 */     this.pressedBackground = null;
/* 140 */     this.pressedForeground = null;
/* 141 */     this.bottomSeparatorColor = null;
/*     */     
/* 143 */     this.oldStyleValues = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners() {
/* 148 */     super.installListeners();
/*     */     
/* 150 */     this.propertyChangeListener = FlatStylingSupport.createPropertyChangeListener(this.header, this::installStyle, null);
/* 151 */     this.header.addPropertyChangeListener(this.propertyChangeListener);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners() {
/* 156 */     super.uninstallListeners();
/*     */     
/* 158 */     this.header.removePropertyChangeListener(this.propertyChangeListener);
/* 159 */     this.propertyChangeListener = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle() {
/*     */     try {
/* 165 */       applyStyle(FlatStylingSupport.getResolvedStyle(this.header, "TableHeader"));
/* 166 */     } catch (RuntimeException ex) {
/* 167 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 173 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 178 */     if (key.equals("sortIconPosition") && value instanceof String) {
/* 179 */       value = Integer.valueOf(parseSortIconPosition((String)value));
/*     */     }
/* 181 */     return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, this.header, key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 187 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 193 */     if (key.equals("sortIconPosition")) {
/* 194 */       switch (this.sortIconPosition)
/*     */       { default:
/* 196 */           return "right";
/* 197 */         case 2: return "left";
/* 198 */         case 1: return "top";
/* 199 */         case 3: break; }  return "bottom";
/*     */     } 
/*     */ 
/*     */     
/* 203 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */   
/*     */   private static int parseSortIconPosition(String str) {
/* 207 */     if (str == null) {
/* 208 */       str = "";
/*     */     }
/* 210 */     switch (str)
/*     */     { default:
/* 212 */         return 4;
/* 213 */       case "left": return 2;
/* 214 */       case "top": return 1;
/* 215 */       case "bottom": break; }  return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected MouseInputListener createMouseInputListener() {
/* 221 */     return new FlatMouseInputHandler();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRolloverColumn() {
/* 227 */     return super.getRolloverColumn();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void rolloverColumnUpdated(int oldColumn, int newColumn) {
/* 232 */     this.header.repaint(this.header.getHeaderRect(oldColumn));
/* 233 */     this.header.repaint(this.header.getHeaderRect(newColumn));
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 238 */     fixDraggedAndResizingColumns(this.header);
/*     */     
/* 240 */     TableColumnModel columnModel = this.header.getColumnModel();
/* 241 */     if (columnModel.getColumnCount() <= 0) {
/*     */       return;
/*     */     }
/*     */     
/* 245 */     int columnCount = columnModel.getColumnCount();
/* 246 */     int totalWidth = 0;
/* 247 */     for (int i = 0; i < columnCount; i++) {
/* 248 */       totalWidth += columnModel.getColumn(i).getWidth();
/*     */     }
/* 250 */     if (totalWidth < this.header.getWidth()) {
/*     */       
/* 252 */       TableCellRenderer defaultRenderer = this.header.getDefaultRenderer();
/* 253 */       boolean paintBottomSeparator = isSystemDefaultRenderer(defaultRenderer);
/* 254 */       if (!paintBottomSeparator && this.header.getTable() != null) {
/*     */         
/* 256 */         Component rendererComponent = defaultRenderer.getTableCellRendererComponent(this.header
/* 257 */             .getTable(), "", false, false, -1, 0);
/* 258 */         paintBottomSeparator = isSystemDefaultRenderer(rendererComponent);
/*     */       } 
/*     */       
/* 261 */       if (paintBottomSeparator) {
/* 262 */         int w = c.getWidth() - totalWidth;
/* 263 */         int x = this.header.getComponentOrientation().isLeftToRight() ? (c.getWidth() - w) : 0;
/* 264 */         paintBottomSeparator(g, c, x, w);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 269 */     FlatTableCellHeaderRenderer tempRenderer = new FlatTableCellHeaderRenderer(this.header.getDefaultRenderer());
/* 270 */     this.header.setDefaultRenderer(tempRenderer);
/*     */ 
/*     */     
/* 273 */     super.paint(g, c);
/*     */ 
/*     */     
/* 276 */     tempRenderer.reset();
/* 277 */     this.header.setDefaultRenderer(tempRenderer.delegate);
/*     */   }
/*     */   
/*     */   private boolean isSystemDefaultRenderer(Object headerRenderer) {
/* 281 */     String rendererClassName = headerRenderer.getClass().getName();
/* 282 */     return (rendererClassName.equals("sun.swing.table.DefaultTableCellHeaderRenderer") || rendererClassName
/* 283 */       .equals("sun.swing.FilePane$AlignableTableHeaderRenderer"));
/*     */   }
/*     */   
/*     */   protected void paintBottomSeparator(Graphics g, JComponent c, int x, int w) {
/* 287 */     float lineWidth = UIScale.scale(1.0F);
/*     */     
/* 289 */     Graphics2D g2 = (Graphics2D)g.create();
/*     */     try {
/* 291 */       FlatUIUtils.setRenderingHints(g2);
/*     */ 
/*     */       
/* 294 */       g2.setColor(this.bottomSeparatorColor);
/* 295 */       g2.fill(new Rectangle2D.Float(x, c.getHeight() - lineWidth, w, lineWidth));
/*     */     } finally {
/* 297 */       g2.dispose();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(JComponent c) {
/* 303 */     Dimension size = super.getPreferredSize(c);
/* 304 */     if (size.height > 0)
/* 305 */       size.height = Math.max(size.height, UIScale.scale(this.height)); 
/* 306 */     return size;
/*     */   }
/*     */   
/*     */   static void fixDraggedAndResizingColumns(JTableHeader header) {
/* 310 */     if (header == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 316 */     TableColumn draggedColumn = header.getDraggedColumn();
/* 317 */     if (draggedColumn != null && !isValidColumn(header.getColumnModel(), draggedColumn)) {
/* 318 */       header.setDraggedColumn((TableColumn)null);
/*     */     }
/*     */     
/* 321 */     TableColumn resizingColumn = header.getResizingColumn();
/* 322 */     if (resizingColumn != null && !isValidColumn(header.getColumnModel(), resizingColumn))
/* 323 */       header.setResizingColumn((TableColumn)null); 
/*     */   }
/*     */   
/*     */   private static boolean isValidColumn(TableColumnModel cm, TableColumn column) {
/* 327 */     int count = cm.getColumnCount();
/* 328 */     for (int i = 0; i < count; i++) {
/* 329 */       if (cm.getColumn(i) == column)
/* 330 */         return true; 
/*     */     } 
/* 332 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private class FlatTableCellHeaderRenderer
/*     */     implements TableCellRenderer, Border, UIResource
/*     */   {
/*     */     private final TableCellRenderer delegate;
/*     */     
/*     */     private JLabel l;
/*     */     
/*     */     private Color oldBackground;
/*     */     
/*     */     private Color oldForeground;
/*     */     
/*     */     private Boolean oldOpaque;
/*     */     
/* 350 */     private int oldHorizontalTextPosition = -1;
/*     */     private Border origBorder;
/*     */     private Icon sortIcon;
/*     */     
/*     */     FlatTableCellHeaderRenderer(TableCellRenderer delegate) {
/* 355 */       this.delegate = delegate;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
/* 362 */       Component c = this.delegate.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
/* 363 */       if (!(c instanceof JLabel)) {
/* 364 */         return c;
/*     */       }
/* 366 */       this.l = (JLabel)c;
/*     */ 
/*     */       
/* 369 */       TableColumn draggedColumn = FlatTableHeaderUI.this.header.getDraggedColumn();
/* 370 */       Color background = null;
/* 371 */       Color foreground = null;
/* 372 */       if (draggedColumn != null && FlatTableHeaderUI.this.header.getTable().convertColumnIndexToView(draggedColumn.getModelIndex()) == column) {
/* 373 */         background = FlatTableHeaderUI.this.pressedBackground;
/* 374 */         foreground = FlatTableHeaderUI.this.pressedForeground;
/* 375 */       } else if (FlatTableHeaderUI.this.getRolloverColumn() == column) {
/* 376 */         background = FlatTableHeaderUI.this.hoverBackground;
/* 377 */         foreground = FlatTableHeaderUI.this.hoverForeground;
/*     */       } 
/* 379 */       if (background != null) {
/* 380 */         if (this.oldBackground == null)
/* 381 */           this.oldBackground = this.l.getBackground(); 
/* 382 */         if (this.oldOpaque == null)
/* 383 */           this.oldOpaque = Boolean.valueOf(this.l.isOpaque()); 
/* 384 */         this.l.setBackground(FlatUIUtils.deriveColor(background, FlatTableHeaderUI.this.header.getBackground()));
/* 385 */         this.l.setOpaque(true);
/*     */       } 
/* 387 */       if (foreground != null) {
/* 388 */         if (this.oldForeground == null)
/* 389 */           this.oldForeground = this.l.getForeground(); 
/* 390 */         this.l.setForeground(FlatUIUtils.deriveColor(foreground, FlatTableHeaderUI.this.header.getForeground()));
/*     */       } 
/*     */ 
/*     */       
/* 394 */       if (FlatTableHeaderUI.this.sortIconPosition == 2) {
/*     */         
/* 396 */         if (this.oldHorizontalTextPosition < 0)
/* 397 */           this.oldHorizontalTextPosition = this.l.getHorizontalTextPosition(); 
/* 398 */         this.l.setHorizontalTextPosition(4);
/* 399 */       } else if (FlatTableHeaderUI.this.sortIconPosition == 1 || FlatTableHeaderUI.this.sortIconPosition == 3) {
/*     */         
/* 401 */         this.sortIcon = this.l.getIcon();
/* 402 */         this.origBorder = this.l.getBorder();
/* 403 */         this.l.setIcon((Icon)null);
/* 404 */         this.l.setBorder(this);
/*     */       } 
/*     */       
/* 407 */       return this.l;
/*     */     }
/*     */     
/*     */     void reset() {
/* 411 */       if (this.l == null) {
/*     */         return;
/*     */       }
/* 414 */       if (this.oldBackground != null)
/* 415 */         this.l.setBackground(this.oldBackground); 
/* 416 */       if (this.oldForeground != null)
/* 417 */         this.l.setForeground(this.oldForeground); 
/* 418 */       if (this.oldOpaque != null)
/* 419 */         this.l.setOpaque(this.oldOpaque.booleanValue()); 
/* 420 */       if (this.oldHorizontalTextPosition >= 0) {
/* 421 */         this.l.setHorizontalTextPosition(this.oldHorizontalTextPosition);
/*     */       }
/*     */     }
/*     */     
/*     */     public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 426 */       if (this.origBorder != null) {
/* 427 */         this.origBorder.paintBorder(c, g, x, y, width, height);
/*     */       }
/* 429 */       if (this.sortIcon != null) {
/* 430 */         int xi = x + (width - this.sortIcon.getIconWidth()) / 2;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 435 */         int yi = (FlatTableHeaderUI.this.sortIconPosition == 1) ? (y + UIScale.scale(1)) : (y + height - this.sortIcon.getIconHeight() - 1 - (int)(1.0F * UIScale.getUserScaleFactor()));
/* 436 */         this.sortIcon.paintIcon(c, g, xi, yi);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public Insets getBorderInsets(Component c) {
/* 442 */       return (this.origBorder != null) ? this.origBorder.getBorderInsets(c) : new Insets(0, 0, 0, 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isBorderOpaque() {
/* 447 */       return (this.origBorder != null) ? this.origBorder.isBorderOpaque() : false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class FlatMouseInputHandler
/*     */     extends BasicTableHeaderUI.MouseInputHandler
/*     */   {
/*     */     Cursor oldCursor;
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseMoved(MouseEvent e) {
/* 462 */       if (this.oldCursor != null) {
/* 463 */         FlatTableHeaderUI.this.header.setCursor(this.oldCursor);
/* 464 */         this.oldCursor = null;
/*     */       } 
/*     */       
/* 467 */       super.mouseMoved(e);
/*     */       
/*     */       JTable table;
/*     */       
/*     */       int column;
/*     */       
/* 473 */       if (FlatTableHeaderUI.this.header.isEnabled() && (
/* 474 */         table = FlatTableHeaderUI.this.header.getTable()) != null && table
/* 475 */         .getAutoResizeMode() != 0 && FlatTableHeaderUI.this
/* 476 */         .header.getCursor() == Cursor.getPredefinedCursor(11) && (
/* 477 */         column = FlatTableHeaderUI.this.header.columnAtPoint(e.getPoint())) >= 0 && column == FlatTableHeaderUI.this
/* 478 */         .header.getColumnModel().getColumnCount() - 1) {
/*     */ 
/*     */         
/* 481 */         Rectangle r = FlatTableHeaderUI.this.header.getHeaderRect(column);
/* 482 */         r.grow(-3, 0);
/* 483 */         if (!r.contains(e.getX(), e.getY())) {
/*     */           
/* 485 */           boolean isResizeLastColumn = (e.getX() >= r.x + r.width / 2);
/* 486 */           if (!FlatTableHeaderUI.this.header.getComponentOrientation().isLeftToRight()) {
/* 487 */             isResizeLastColumn = !isResizeLastColumn;
/*     */           }
/* 489 */           if (isResizeLastColumn) {
/*     */             
/* 491 */             this.oldCursor = FlatTableHeaderUI.this.header.getCursor();
/* 492 */             FlatTableHeaderUI.this.header.setCursor(Cursor.getDefaultCursor());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatTableHeaderUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */