/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.FlatClientProperties;
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JTree;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.event.TreeSelectionEvent;
/*     */ import javax.swing.event.TreeSelectionListener;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicTreeUI;
/*     */ import javax.swing.tree.DefaultTreeCellRenderer;
/*     */ import javax.swing.tree.TreePath;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatTreeUI
/*     */   extends BasicTreeUI
/*     */   implements FlatStylingSupport.StyleableUI
/*     */ {
/*     */   @Styleable
/*     */   protected Color selectionBackground;
/*     */   @Styleable
/*     */   protected Color selectionForeground;
/*     */   @Styleable
/*     */   protected Color selectionInactiveBackground;
/*     */   @Styleable
/*     */   protected Color selectionInactiveForeground;
/*     */   @Styleable
/*     */   protected Color selectionBorderColor;
/*     */   @Styleable
/*     */   protected Insets selectionInsets;
/*     */   @Styleable
/*     */   protected int selectionArc;
/*     */   @Styleable
/*     */   protected boolean wideSelection;
/*     */   @Styleable
/*     */   protected boolean showCellFocusIndicator;
/*     */   protected boolean showDefaultIcons;
/*     */   @Styleable(dot = true)
/*     */   public String iconArrowType;
/*     */   @Styleable(dot = true)
/*     */   public Color iconExpandedColor;
/*     */   @Styleable(dot = true)
/*     */   public Color iconCollapsedColor;
/*     */   @Styleable(dot = true)
/*     */   public Color iconLeafColor;
/*     */   @Styleable(dot = true)
/*     */   public Color iconClosedColor;
/*     */   @Styleable(dot = true)
/*     */   public Color iconOpenColor;
/*     */   @Styleable
/*     */   protected boolean paintSelection = true;
/*     */   private Icon defaultLeafIcon;
/*     */   private Icon defaultClosedIcon;
/*     */   private Icon defaultOpenIcon;
/*     */   private boolean paintLines;
/*     */   private Color defaultCellNonSelectionBackground;
/*     */   private Color defaultSelectionBackground;
/*     */   private Color defaultSelectionForeground;
/*     */   private Color defaultSelectionBorderColor;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/* 173 */     return new FlatTreeUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/* 178 */     super.installUI(c);
/*     */     
/* 180 */     installStyle();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/* 185 */     super.installDefaults();
/*     */     
/* 187 */     LookAndFeel.installBorder(this.tree, "Tree.border");
/*     */     
/* 189 */     this.selectionBackground = UIManager.getColor("Tree.selectionBackground");
/* 190 */     this.selectionForeground = UIManager.getColor("Tree.selectionForeground");
/* 191 */     this.selectionInactiveBackground = UIManager.getColor("Tree.selectionInactiveBackground");
/* 192 */     this.selectionInactiveForeground = UIManager.getColor("Tree.selectionInactiveForeground");
/* 193 */     this.selectionBorderColor = UIManager.getColor("Tree.selectionBorderColor");
/* 194 */     this.selectionInsets = UIManager.getInsets("Tree.selectionInsets");
/* 195 */     this.selectionArc = UIManager.getInt("Tree.selectionArc");
/* 196 */     this.wideSelection = UIManager.getBoolean("Tree.wideSelection");
/* 197 */     this.showCellFocusIndicator = UIManager.getBoolean("Tree.showCellFocusIndicator");
/* 198 */     this.showDefaultIcons = UIManager.getBoolean("Tree.showDefaultIcons");
/*     */     
/* 200 */     this.defaultLeafIcon = UIManager.getIcon("Tree.leafIcon");
/* 201 */     this.defaultClosedIcon = UIManager.getIcon("Tree.closedIcon");
/* 202 */     this.defaultOpenIcon = UIManager.getIcon("Tree.openIcon");
/*     */     
/* 204 */     this.paintLines = UIManager.getBoolean("Tree.paintLines");
/* 205 */     this.defaultCellNonSelectionBackground = UIManager.getColor("Tree.textBackground");
/* 206 */     this.defaultSelectionBackground = this.selectionBackground;
/* 207 */     this.defaultSelectionForeground = this.selectionForeground;
/* 208 */     this.defaultSelectionBorderColor = this.selectionBorderColor;
/*     */ 
/*     */     
/* 211 */     int rowHeight = FlatUIUtils.getUIInt("Tree.rowHeight", 16);
/* 212 */     if (rowHeight > 0)
/* 213 */       LookAndFeel.installProperty(this.tree, "rowHeight", Integer.valueOf(UIScale.scale(rowHeight))); 
/* 214 */     setLeftChildIndent(UIScale.scale(getLeftChildIndent()));
/* 215 */     setRightChildIndent(UIScale.scale(getRightChildIndent()));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults() {
/* 220 */     super.uninstallDefaults();
/*     */     
/* 222 */     LookAndFeel.uninstallBorder(this.tree);
/*     */     
/* 224 */     this.selectionBackground = null;
/* 225 */     this.selectionForeground = null;
/* 226 */     this.selectionInactiveBackground = null;
/* 227 */     this.selectionInactiveForeground = null;
/* 228 */     this.selectionBorderColor = null;
/*     */     
/* 230 */     this.defaultLeafIcon = null;
/* 231 */     this.defaultClosedIcon = null;
/* 232 */     this.defaultOpenIcon = null;
/*     */     
/* 234 */     this.defaultCellNonSelectionBackground = null;
/* 235 */     this.defaultSelectionBackground = null;
/* 236 */     this.defaultSelectionForeground = null;
/* 237 */     this.defaultSelectionBorderColor = null;
/* 238 */     this.oldStyleValues = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateRenderer() {
/* 243 */     super.updateRenderer();
/*     */ 
/*     */     
/* 246 */     if (!this.showDefaultIcons && this.currentCellRenderer instanceof DefaultTreeCellRenderer) {
/* 247 */       DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer)this.currentCellRenderer;
/* 248 */       if (renderer.getLeafIcon() == this.defaultLeafIcon && renderer
/* 249 */         .getClosedIcon() == this.defaultClosedIcon && renderer
/* 250 */         .getOpenIcon() == this.defaultOpenIcon) {
/*     */         
/* 252 */         renderer.setLeafIcon(null);
/* 253 */         renderer.setClosedIcon(null);
/* 254 */         renderer.setOpenIcon(null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected MouseListener createMouseListener() {
/* 261 */     return new BasicTreeUI.MouseHandler()
/*     */       {
/*     */         public void mousePressed(MouseEvent e) {
/* 264 */           super.mousePressed(handleWideMouseEvent(e));
/*     */         }
/*     */ 
/*     */         
/*     */         public void mouseReleased(MouseEvent e) {
/* 269 */           super.mouseReleased(handleWideMouseEvent(e));
/*     */         }
/*     */ 
/*     */         
/*     */         public void mouseDragged(MouseEvent e) {
/* 274 */           super.mouseDragged(handleWideMouseEvent(e));
/*     */         }
/*     */         
/*     */         private MouseEvent handleWideMouseEvent(MouseEvent e) {
/* 278 */           if (!FlatTreeUI.this.isWideSelection() || !FlatTreeUI.this.tree.isEnabled() || !SwingUtilities.isLeftMouseButton(e) || e.isConsumed()) {
/* 279 */             return e;
/*     */           }
/* 281 */           int x = e.getX();
/* 282 */           int y = e.getY();
/* 283 */           TreePath path = FlatTreeUI.this.getClosestPathForLocation(FlatTreeUI.this.tree, x, y);
/* 284 */           if (path == null || FlatTreeUI.this.isLocationInExpandControl(path, x, y)) {
/* 285 */             return e;
/*     */           }
/* 287 */           Rectangle bounds = FlatTreeUI.this.getPathBounds(FlatTreeUI.this.tree, path);
/* 288 */           if (bounds == null || y < bounds.y || y >= bounds.y + bounds.height) {
/* 289 */             return e;
/*     */           }
/* 291 */           int newX = Math.max(bounds.x, Math.min(x, bounds.x + bounds.width - 1));
/* 292 */           if (newX == x) {
/* 293 */             return e;
/*     */           }
/*     */           
/* 296 */           return new MouseEvent(e.getComponent(), e.getID(), e.getWhen(), e
/* 297 */               .getModifiers() | e.getModifiersEx(), newX, e.getY(), e
/* 298 */               .getClickCount(), e.isPopupTrigger(), e.getButton());
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   protected PropertyChangeListener createPropertyChangeListener() {
/* 305 */     PropertyChangeListener superListener = super.createPropertyChangeListener();
/* 306 */     return e -> {
/*     */         superListener.propertyChange(e);
/*     */         if (e.getSource() == this.tree) {
/*     */           switch (e.getPropertyName()) {
/*     */             case "JTree.wideSelection":
/*     */             case "JTree.paintSelection":
/*     */               this.tree.repaint();
/*     */               break;
/*     */             case "dropLocation":
/*     */               if (isWideSelection()) {
/*     */                 JTree.DropLocation oldValue = (JTree.DropLocation)e.getOldValue();
/*     */                 repaintWideDropLocation(oldValue);
/*     */                 repaintWideDropLocation(this.tree.getDropLocation());
/*     */               } 
/*     */               break;
/*     */             case "FlatLaf.style":
/*     */             case "FlatLaf.styleClass":
/*     */               installStyle();
/*     */               this.tree.revalidate();
/*     */               this.tree.repaint();
/*     */               break;
/*     */             case "enabled":
/*     */               if (!this.showDefaultIcons && this.currentCellRenderer instanceof DefaultTreeCellRenderer && this.currentCellRenderer.getClass() != DefaultTreeCellRenderer.class && this.treeState != null) {
/*     */                 this.treeState.invalidateSizes();
/*     */                 updateSize();
/*     */               } 
/*     */               break;
/*     */           } 
/*     */         }
/*     */       };
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
/*     */   private void repaintWideDropLocation(JTree.DropLocation loc) {
/* 351 */     if (loc == null || isDropLine(loc)) {
/*     */       return;
/*     */     }
/* 354 */     Rectangle r = this.tree.getPathBounds(loc.getPath());
/* 355 */     if (r != null) {
/* 356 */       this.tree.repaint(0, r.y, this.tree.getWidth(), r.height);
/*     */     }
/*     */   }
/*     */   
/*     */   protected TreeSelectionListener createTreeSelectionListener() {
/* 361 */     TreeSelectionListener superListener = super.createTreeSelectionListener();
/* 362 */     return e -> {
/*     */         superListener.valueChanged(e);
/*     */         TreePath[] changedPaths;
/*     */         if (useUnitedRoundedSelection() && this.tree.getSelectionCount() > 1 && (changedPaths = e.getPaths()) != null) {
/*     */           if (changedPaths.length > 4) {
/*     */             this.tree.repaint();
/*     */           } else {
/*     */             int arc = (int)Math.ceil(UIScale.scale(this.selectionArc / 2.0F));
/*     */             for (TreePath path : changedPaths) {
/*     */               Rectangle r = getPathBounds(this.tree, path);
/*     */               if (r != null) {
/*     */                 this.tree.repaint(r.x, r.y - arc, r.width, r.height + arc * 2);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle getPathBounds(JTree tree, TreePath path) {
/* 389 */     Rectangle bounds = super.getPathBounds(tree, path);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 396 */     if (bounds != null && 
/* 397 */       isWideSelection() && 
/* 398 */       UIManager.getBoolean("FlatLaf.experimental.tree.widePathForLocation") && 
/* 399 */       StackUtils.wasInvokedFrom(JTree.class.getName(), "getPathForLocation", 5)) {
/*     */       
/* 401 */       bounds.x = 0;
/* 402 */       bounds.width = tree.getWidth();
/*     */     } 
/* 404 */     return bounds;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle() {
/*     */     try {
/* 410 */       applyStyle(FlatStylingSupport.getResolvedStyle(this.tree, "Tree"));
/* 411 */     } catch (RuntimeException ex) {
/* 412 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 418 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 423 */     return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, this.tree, key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 429 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 435 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 440 */     if (this.treeState == null) {
/*     */       return;
/*     */     }
/*     */     
/* 444 */     Rectangle clipBounds = g.getClipBounds();
/* 445 */     TreePath firstPath = getClosestPathForLocation(this.tree, 0, clipBounds.y);
/* 446 */     Enumeration<TreePath> visiblePaths = this.treeState.getVisiblePathsFrom(firstPath);
/*     */     
/* 448 */     if (visiblePaths != null) {
/* 449 */       Insets insets = this.tree.getInsets();
/*     */       
/* 451 */       HashSet<TreePath> verticalLinePaths = this.paintLines ? new HashSet<>() : null;
/* 452 */       ArrayList<Runnable> paintLinesLater = this.paintLines ? new ArrayList<>() : null;
/* 453 */       ArrayList<Runnable> paintExpandControlsLater = this.paintLines ? new ArrayList<>() : null;
/*     */ 
/*     */       
/* 456 */       if (this.paintLines) {
/* 457 */         for (TreePath path = firstPath.getParentPath(); path != null; path = path.getParentPath()) {
/* 458 */           verticalLinePaths.add(path);
/*     */         }
/*     */       }
/* 461 */       Rectangle boundsBuffer = new Rectangle();
/* 462 */       boolean rootVisible = isRootVisible();
/* 463 */       int row = this.treeState.getRowForPath(firstPath);
/* 464 */       boolean leftToRight = this.tree.getComponentOrientation().isLeftToRight();
/* 465 */       int treeWidth = this.tree.getWidth();
/*     */ 
/*     */       
/* 468 */       while (visiblePaths.hasMoreElements()) {
/* 469 */         TreePath path = visiblePaths.nextElement();
/* 470 */         if (path == null) {
/*     */           break;
/*     */         }
/*     */         
/* 474 */         Rectangle bounds = this.treeState.getBounds(path, boundsBuffer);
/* 475 */         if (bounds == null) {
/*     */           break;
/*     */         }
/*     */         
/* 479 */         if (leftToRight) {
/* 480 */           bounds.x += insets.left;
/*     */         } else {
/* 482 */           bounds.x = treeWidth - insets.right - bounds.x + bounds.width;
/* 483 */         }  bounds.y += insets.top;
/*     */         
/* 485 */         boolean isLeaf = this.treeModel.isLeaf(path.getLastPathComponent());
/* 486 */         boolean isExpanded = isLeaf ? false : this.treeState.getExpandedState(path);
/* 487 */         boolean hasBeenExpanded = isLeaf ? false : this.tree.hasBeenExpanded(path);
/*     */ 
/*     */         
/* 490 */         paintRow(g, clipBounds, insets, bounds, path, row, isExpanded, hasBeenExpanded, isLeaf);
/*     */ 
/*     */         
/* 493 */         if (this.paintLines) {
/* 494 */           TreePath parentPath = path.getParentPath();
/*     */ 
/*     */           
/* 497 */           if (parentPath != null) {
/* 498 */             verticalLinePaths.add(parentPath);
/*     */           }
/*     */           
/* 501 */           if (parentPath != null || (rootVisible && row == 0)) {
/* 502 */             Rectangle bounds2 = new Rectangle(bounds);
/* 503 */             int row2 = row;
/* 504 */             paintLinesLater.add(() -> paintHorizontalPartOfLeg(g, clipBounds, insets, bounds2, path, row2, isExpanded, hasBeenExpanded, isLeaf));
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 511 */         if (shouldPaintExpandControl(path, row, isExpanded, hasBeenExpanded, isLeaf)) {
/* 512 */           if (this.paintLines) {
/*     */             
/* 514 */             Rectangle bounds2 = new Rectangle(bounds);
/* 515 */             int row2 = row;
/* 516 */             paintExpandControlsLater.add(() -> paintExpandControl(g, clipBounds, insets, bounds2, path, row2, isExpanded, hasBeenExpanded, isLeaf));
/*     */           }
/*     */           else {
/*     */             
/* 520 */             paintExpandControl(g, clipBounds, insets, bounds, path, row, isExpanded, hasBeenExpanded, isLeaf);
/*     */           } 
/*     */         }
/* 523 */         if (bounds.y + bounds.height >= clipBounds.y + clipBounds.height) {
/*     */           break;
/*     */         }
/* 526 */         row++;
/*     */       } 
/*     */       
/* 529 */       if (this.paintLines) {
/*     */         
/* 531 */         Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
/*     */ 
/*     */         
/* 534 */         for (Runnable r : paintLinesLater) {
/* 535 */           r.run();
/*     */         }
/*     */         
/* 538 */         g.setColor(Color.green);
/* 539 */         for (TreePath path : verticalLinePaths) {
/* 540 */           paintVerticalPartOfLeg(g, clipBounds, insets, path);
/*     */         }
/*     */         
/* 543 */         if (oldRenderingHints != null) {
/* 544 */           FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
/*     */         }
/*     */         
/* 547 */         for (Runnable r : paintExpandControlsLater) {
/* 548 */           r.run();
/*     */         }
/*     */       } 
/*     */     } 
/* 552 */     paintDropLine(g);
/*     */     
/* 554 */     this.rendererPane.removeAll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintRow(Graphics g, Rectangle clipBounds, Insets insets, Rectangle bounds, TreePath path, int row, boolean isExpanded, boolean hasBeenExpanded, boolean isLeaf) {
/* 565 */     boolean isEditing = (this.editingComponent != null && this.editingRow == row);
/* 566 */     boolean isSelected = this.tree.isRowSelected(row);
/* 567 */     boolean isDropRow = isDropRow(row);
/* 568 */     boolean needsSelectionPainting = ((isSelected || isDropRow) && isPaintSelection());
/*     */ 
/*     */     
/* 571 */     if (isEditing) {
/*     */ 
/*     */ 
/*     */       
/* 575 */       if (isSelected && isWideSelection()) {
/* 576 */         Color oldColor = g.getColor();
/* 577 */         g.setColor(this.selectionInactiveBackground);
/* 578 */         paintWideSelection(g, clipBounds, insets, bounds, path, row, isExpanded, hasBeenExpanded, isLeaf);
/* 579 */         g.setColor(oldColor);
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 584 */     boolean hasFocus = FlatUIUtils.isPermanentFocusOwner(this.tree);
/* 585 */     boolean cellHasFocus = (hasFocus && row == getLeadSelectionRow());
/*     */ 
/*     */ 
/*     */     
/* 589 */     if (!hasFocus && isSelected && this.tree.getParent() instanceof javax.swing.CellRendererPane) {
/* 590 */       hasFocus = FlatUIUtils.isPermanentFocusOwner(this.tree.getParent().getParent());
/*     */     }
/*     */     
/* 593 */     Component rendererComponent = this.currentCellRenderer.getTreeCellRendererComponent(this.tree, path
/* 594 */         .getLastPathComponent(), isSelected, isExpanded, isLeaf, row, cellHasFocus);
/*     */ 
/*     */     
/* 597 */     Color oldBackgroundSelectionColor = null;
/* 598 */     if (isSelected && !hasFocus && !isDropRow) {
/*     */       
/* 600 */       oldBackgroundSelectionColor = setRendererBackgroundSelectionColor(rendererComponent, this.selectionInactiveBackground);
/* 601 */       setRendererForeground(rendererComponent, this.selectionInactiveForeground);
/*     */     }
/* 603 */     else if (isSelected) {
/*     */       
/* 605 */       if (this.selectionBackground != this.defaultSelectionBackground)
/* 606 */         oldBackgroundSelectionColor = setRendererBackgroundSelectionColor(rendererComponent, this.selectionBackground); 
/* 607 */       if (this.selectionForeground != this.defaultSelectionForeground) {
/* 608 */         setRendererForeground(rendererComponent, this.selectionForeground);
/*     */       }
/*     */     } 
/*     */     
/* 612 */     Color oldBorderSelectionColor = null;
/* 613 */     if (isSelected && hasFocus && (!this.showCellFocusIndicator || this.tree
/* 614 */       .getMinSelectionRow() == this.tree.getMaxSelectionRow())) {
/*     */ 
/*     */       
/* 617 */       oldBorderSelectionColor = setRendererBorderSelectionColor(rendererComponent, (Color)null);
/*     */     }
/* 619 */     else if (hasFocus && this.selectionBorderColor != this.defaultSelectionBorderColor) {
/*     */       
/* 621 */       oldBorderSelectionColor = setRendererBorderSelectionColor(rendererComponent, this.selectionBorderColor);
/*     */     } 
/*     */ 
/*     */     
/* 625 */     if (needsSelectionPainting) {
/*     */       
/* 627 */       Color oldColor = g.getColor();
/* 628 */       g.setColor(isDropRow ? 
/* 629 */           UIManager.getColor("Tree.dropCellBackground") : (
/* 630 */           (rendererComponent instanceof DefaultTreeCellRenderer) ? (
/* 631 */           (DefaultTreeCellRenderer)rendererComponent).getBackgroundSelectionColor() : (
/* 632 */           hasFocus ? this.selectionBackground : this.selectionInactiveBackground)));
/*     */       
/* 634 */       if (isWideSelection()) {
/*     */         
/* 636 */         paintWideSelection(g, clipBounds, insets, bounds, path, row, isExpanded, hasBeenExpanded, isLeaf);
/*     */       } else {
/*     */         
/* 639 */         paintCellBackground(g, rendererComponent, bounds, row, true);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 644 */       g.setColor(oldColor);
/*     */     
/*     */     }
/* 647 */     else if (rendererComponent instanceof DefaultTreeCellRenderer) {
/* 648 */       DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer)rendererComponent;
/* 649 */       Color bg = renderer.getBackgroundNonSelectionColor();
/* 650 */       if (bg != null && !bg.equals(this.defaultCellNonSelectionBackground)) {
/* 651 */         Color oldColor = g.getColor();
/* 652 */         g.setColor(bg);
/* 653 */         paintCellBackground(g, rendererComponent, bounds, row, false);
/* 654 */         g.setColor(oldColor);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 660 */     this.rendererPane.paintComponent(g, rendererComponent, this.tree, bounds.x, bounds.y, bounds.width, bounds.height, true);
/*     */ 
/*     */     
/* 663 */     if (oldBackgroundSelectionColor != null)
/* 664 */       ((DefaultTreeCellRenderer)rendererComponent).setBackgroundSelectionColor(oldBackgroundSelectionColor); 
/* 665 */     if (oldBorderSelectionColor != null)
/* 666 */       ((DefaultTreeCellRenderer)rendererComponent).setBorderSelectionColor(oldBorderSelectionColor); 
/*     */   }
/*     */   
/*     */   private Color setRendererBackgroundSelectionColor(Component rendererComponent, Color color) {
/* 670 */     Color oldColor = null;
/*     */     
/* 672 */     if (rendererComponent instanceof DefaultTreeCellRenderer) {
/* 673 */       DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer)rendererComponent;
/* 674 */       if (renderer.getBackgroundSelectionColor() == this.defaultSelectionBackground) {
/* 675 */         oldColor = renderer.getBackgroundSelectionColor();
/* 676 */         renderer.setBackgroundSelectionColor(color);
/*     */       }
/*     */     
/* 679 */     } else if (rendererComponent.getBackground() == this.defaultSelectionBackground) {
/* 680 */       rendererComponent.setBackground(color);
/*     */     } 
/*     */     
/* 683 */     return oldColor;
/*     */   }
/*     */   
/*     */   private void setRendererForeground(Component rendererComponent, Color color) {
/* 687 */     if (rendererComponent.getForeground() == this.defaultSelectionForeground)
/* 688 */       rendererComponent.setForeground(color); 
/*     */   }
/*     */   
/*     */   private Color setRendererBorderSelectionColor(Component rendererComponent, Color color) {
/* 692 */     Color oldColor = null;
/*     */     
/* 694 */     if (rendererComponent instanceof DefaultTreeCellRenderer) {
/* 695 */       DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer)rendererComponent;
/* 696 */       if (renderer.getBorderSelectionColor() == this.defaultSelectionBorderColor) {
/* 697 */         oldColor = renderer.getBorderSelectionColor();
/* 698 */         renderer.setBorderSelectionColor(color);
/*     */       } 
/*     */     } 
/*     */     
/* 702 */     return oldColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void paintWideSelection(Graphics g, Rectangle clipBounds, Insets insets, Rectangle bounds, TreePath path, int row, boolean isExpanded, boolean hasBeenExpanded, boolean isLeaf) {
/* 709 */     float arcBottom = UIScale.scale(this.selectionArc / 2.0F), arcTop = arcBottom;
/*     */     
/* 711 */     if (useUnitedRoundedSelection()) {
/* 712 */       if (row > 0 && this.tree.isRowSelected(row - 1))
/* 713 */         arcTop = 0.0F; 
/* 714 */       if (row < this.tree.getRowCount() - 1 && this.tree.isRowSelected(row + 1)) {
/* 715 */         arcBottom = 0.0F;
/*     */       }
/*     */     } 
/* 718 */     FlatUIUtils.paintSelection((Graphics2D)g, 0, bounds.y, this.tree.getWidth(), bounds.height, 
/* 719 */         UIScale.scale(this.selectionInsets), arcTop, arcTop, arcBottom, arcBottom, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void paintCellBackground(Graphics g, Component rendererComponent, Rectangle bounds, int row, boolean paintSelection) {
/* 725 */     int xOffset = 0;
/* 726 */     int imageOffset = 0;
/*     */     
/* 728 */     if (rendererComponent instanceof JLabel) {
/* 729 */       JLabel label = (JLabel)rendererComponent;
/* 730 */       Icon icon = label.isEnabled() ? label.getIcon() : label.getDisabledIcon();
/*     */ 
/*     */       
/* 733 */       imageOffset = (icon != null && label.getText() != null) ? (icon.getIconWidth() + Math.max(label.getIconTextGap() - 1, 0)) : 0;
/* 734 */       xOffset = label.getComponentOrientation().isLeftToRight() ? imageOffset : 0;
/*     */     } 
/*     */     
/* 737 */     if (paintSelection) {
/*     */       
/* 739 */       float arcBottomRight = UIScale.scale(this.selectionArc / 2.0F), arcBottomLeft = arcBottomRight, arcTopRight = arcBottomLeft, arcTopLeft = arcTopRight;
/*     */       
/* 741 */       if (useUnitedRoundedSelection()) {
/* 742 */         if (row > 0 && this.tree.isRowSelected(row - 1)) {
/* 743 */           Rectangle r = getPathBounds(this.tree, this.tree.getPathForRow(row - 1));
/* 744 */           arcTopLeft = Math.min(arcTopLeft, (r.x - bounds.x));
/* 745 */           arcTopRight = Math.min(arcTopRight, (bounds.x + bounds.width - r.x + r.width));
/*     */         } 
/* 747 */         if (row < this.tree.getRowCount() - 1 && this.tree.isRowSelected(row + 1)) {
/* 748 */           Rectangle r = getPathBounds(this.tree, this.tree.getPathForRow(row + 1));
/* 749 */           arcBottomLeft = Math.min(arcBottomLeft, (r.x - bounds.x));
/* 750 */           arcBottomRight = Math.min(arcBottomRight, (bounds.x + bounds.width - r.x + r.width));
/*     */         } 
/*     */       } 
/*     */       
/* 754 */       FlatUIUtils.paintSelection((Graphics2D)g, bounds.x + xOffset, bounds.y, bounds.width - imageOffset, bounds.height, 
/* 755 */           UIScale.scale(this.selectionInsets), arcTopLeft, arcTopRight, arcBottomLeft, arcBottomRight, 0);
/*     */     } else {
/* 757 */       g.fillRect(bounds.x + xOffset, bounds.y, bounds.width - imageOffset, bounds.height);
/*     */     } 
/*     */   }
/*     */   private boolean useUnitedRoundedSelection() {
/* 761 */     return (this.selectionArc > 0 && (this.selectionInsets == null || (this.selectionInsets.top == 0 && this.selectionInsets.bottom == 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintVerticalLine(Graphics g, JComponent c, int x, int top, int bottom) {
/* 767 */     ((Graphics2D)g).fill(new Rectangle2D.Float(x, top, UIScale.scale(1.0F), (bottom - top)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintHorizontalLine(Graphics g, JComponent c, int y, int left, int right) {
/* 772 */     ((Graphics2D)g).fill(new Rectangle2D.Float(left, y, (right - left), UIScale.scale(1.0F)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isDropRow(int row) {
/* 780 */     JTree.DropLocation dropLocation = this.tree.getDropLocation();
/* 781 */     return (dropLocation != null && dropLocation
/* 782 */       .getChildIndex() == -1 && this.tree
/* 783 */       .getRowForPath(dropLocation.getPath()) == row);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Rectangle getDropLineRect(JTree.DropLocation loc) {
/* 788 */     Rectangle r = super.getDropLineRect(loc);
/* 789 */     return isWideSelection() ? new Rectangle(0, r.y, this.tree.getWidth(), r.height) : r;
/*     */   }
/*     */   
/*     */   protected boolean isWideSelection() {
/* 793 */     return FlatClientProperties.clientPropertyBoolean(this.tree, "JTree.wideSelection", this.wideSelection);
/*     */   }
/*     */   
/*     */   protected boolean isPaintSelection() {
/* 797 */     return FlatClientProperties.clientPropertyBoolean(this.tree, "JTree.paintSelection", this.paintSelection);
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatTreeUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */