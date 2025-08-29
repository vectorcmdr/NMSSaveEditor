/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.SystemInfo;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Container;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.event.WindowStateListener;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.function.Supplier;
/*     */ import javax.swing.DesktopManager;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JLayeredPane;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class FlatWindowResizer
/*     */   implements PropertyChangeListener, ComponentListener
/*     */ {
/*  62 */   protected static final Integer WINDOW_RESIZER_LAYER = Integer.valueOf(JLayeredPane.DRAG_LAYER.intValue() + 1);
/*     */   
/*     */   protected final JComponent resizeComp;
/*     */   
/*  66 */   protected final int borderDragThickness = FlatUIUtils.getUIInt("RootPane.borderDragThickness", 5);
/*  67 */   protected final int cornerDragWidth = FlatUIUtils.getUIInt("RootPane.cornerDragWidth", 16);
/*  68 */   protected final boolean honorFrameMinimumSizeOnResize = UIManager.getBoolean("RootPane.honorFrameMinimumSizeOnResize");
/*  69 */   protected final boolean honorDialogMinimumSizeOnResize = UIManager.getBoolean("RootPane.honorDialogMinimumSizeOnResize");
/*     */   
/*     */   protected final DragBorderComponent topDragComp;
/*     */   protected final DragBorderComponent bottomDragComp;
/*     */   protected final DragBorderComponent leftDragComp;
/*     */   protected final DragBorderComponent rightDragComp;
/*     */   
/*     */   protected FlatWindowResizer(JComponent resizeComp) {
/*  77 */     this.resizeComp = resizeComp;
/*     */     
/*  79 */     this.topDragComp = createDragBorderComponent(6, 8, 7);
/*  80 */     this.bottomDragComp = createDragBorderComponent(4, 9, 5);
/*  81 */     this.leftDragComp = createDragBorderComponent(6, 10, 4);
/*  82 */     this.rightDragComp = createDragBorderComponent(7, 11, 5);
/*     */     
/*  84 */     Container cont = (resizeComp instanceof JRootPane) ? ((JRootPane)resizeComp).getLayeredPane() : resizeComp;
/*  85 */     Object cons = (cont instanceof JLayeredPane) ? WINDOW_RESIZER_LAYER : null;
/*  86 */     cont.add(this.topDragComp, cons, 0);
/*  87 */     cont.add(this.bottomDragComp, cons, 1);
/*  88 */     cont.add(this.leftDragComp, cons, 2);
/*  89 */     cont.add(this.rightDragComp, cons, 3);
/*     */     
/*  91 */     resizeComp.addComponentListener(this);
/*  92 */     resizeComp.addPropertyChangeListener("ancestor", this);
/*     */     
/*  94 */     if (resizeComp.isDisplayable())
/*  95 */       addNotify(); 
/*     */   }
/*     */   
/*     */   protected DragBorderComponent createDragBorderComponent(int leadingResizeDir, int centerResizeDir, int trailingResizeDir) {
/*  99 */     return new DragBorderComponent(leadingResizeDir, centerResizeDir, trailingResizeDir);
/*     */   }
/*     */   
/*     */   public void uninstall() {
/* 103 */     removeNotify();
/*     */     
/* 105 */     this.resizeComp.removeComponentListener(this);
/* 106 */     this.resizeComp.removePropertyChangeListener("ancestor", this);
/*     */     
/* 108 */     Container cont = this.topDragComp.getParent();
/* 109 */     cont.remove(this.topDragComp);
/* 110 */     cont.remove(this.bottomDragComp);
/* 111 */     cont.remove(this.leftDragComp);
/* 112 */     cont.remove(this.rightDragComp);
/*     */   }
/*     */   
/*     */   public void doLayout() {
/* 116 */     if (!this.topDragComp.isVisible()) {
/*     */       return;
/*     */     }
/* 119 */     int x = 0;
/* 120 */     int y = 0;
/* 121 */     int width = this.resizeComp.getWidth();
/* 122 */     int height = this.resizeComp.getHeight();
/* 123 */     if (width == 0 || height == 0) {
/*     */       return;
/*     */     }
/* 126 */     Insets resizeInsets = getResizeInsets();
/* 127 */     int thickness = UIScale.scale(this.borderDragThickness);
/* 128 */     int topThickness = Math.max(resizeInsets.top, thickness);
/* 129 */     int bottomThickness = Math.max(resizeInsets.bottom, thickness);
/* 130 */     int leftThickness = Math.max(resizeInsets.left, thickness);
/* 131 */     int rightThickness = Math.max(resizeInsets.right, thickness);
/* 132 */     int y2 = y + topThickness;
/* 133 */     int height2 = height - topThickness - bottomThickness;
/*     */ 
/*     */     
/* 136 */     this.topDragComp.setBounds(x, y, width, topThickness);
/* 137 */     this.bottomDragComp.setBounds(x, y + height - bottomThickness, width, bottomThickness);
/* 138 */     this.leftDragComp.setBounds(x, y2, leftThickness, height2);
/* 139 */     this.rightDragComp.setBounds(x + width - rightThickness, y2, rightThickness, height2);
/*     */ 
/*     */     
/* 142 */     int cornerDelta = UIScale.scale(this.cornerDragWidth - this.borderDragThickness);
/* 143 */     this.topDragComp.setCornerDragWidths(leftThickness + cornerDelta, rightThickness + cornerDelta);
/* 144 */     this.bottomDragComp.setCornerDragWidths(leftThickness + cornerDelta, rightThickness + cornerDelta);
/* 145 */     this.leftDragComp.setCornerDragWidths(cornerDelta, cornerDelta);
/* 146 */     this.rightDragComp.setCornerDragWidths(cornerDelta, cornerDelta);
/*     */   }
/*     */   
/*     */   protected Insets getResizeInsets() {
/* 150 */     return new Insets(0, 0, 0, 0);
/*     */   }
/*     */   
/*     */   protected void addNotify() {
/* 154 */     updateVisibility();
/*     */   }
/*     */   
/*     */   protected void removeNotify() {
/* 158 */     updateVisibility();
/*     */   }
/*     */   
/*     */   protected void updateVisibility() {
/* 162 */     boolean visible = isWindowResizable();
/* 163 */     if (visible == this.topDragComp.isVisible()) {
/*     */       return;
/*     */     }
/* 166 */     this.topDragComp.setVisible(visible);
/* 167 */     this.bottomDragComp.setVisible(visible);
/* 168 */     this.leftDragComp.setVisible(visible);
/*     */ 
/*     */ 
/*     */     
/* 172 */     this.rightDragComp.setEnabled(visible);
/* 173 */     if (visible) {
/* 174 */       this.rightDragComp.setVisible(true);
/* 175 */       doLayout();
/*     */     } else {
/* 177 */       this.rightDragComp.setBounds(0, 0, 1, 1);
/*     */     } 
/*     */   }
/*     */   boolean isDialog() {
/* 181 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract boolean isWindowResizable();
/*     */ 
/*     */   
/*     */   protected abstract Rectangle getWindowBounds();
/*     */ 
/*     */   
/*     */   protected abstract void setWindowBounds(Rectangle paramRectangle);
/*     */ 
/*     */   
/*     */   protected abstract boolean limitToParentBounds();
/*     */ 
/*     */   
/*     */   protected abstract Rectangle getParentBounds();
/*     */ 
/*     */   
/*     */   public void propertyChange(PropertyChangeEvent e) {
/* 201 */     switch (e.getPropertyName()) {
/*     */       case "ancestor":
/* 203 */         if (e.getNewValue() != null) {
/* 204 */           addNotify(); break;
/*     */         } 
/* 206 */         removeNotify();
/*     */         break;
/*     */       
/*     */       case "resizable":
/* 210 */         updateVisibility();
/*     */         break;
/*     */     } 
/*     */   } protected abstract boolean honorMinimumSizeOnResize(); protected abstract boolean honorMaximumSizeOnResize();
/*     */   protected abstract Dimension getWindowMinimumSize();
/*     */   protected abstract Dimension getWindowMaximumSize();
/*     */   protected void beginResizing(int direction) {}
/*     */   protected void endResizing() {}
/*     */   public void componentResized(ComponentEvent e) {
/* 219 */     doLayout();
/*     */   }
/*     */ 
/*     */   
/*     */   public void componentMoved(ComponentEvent e) {}
/*     */ 
/*     */   
/*     */   public void componentShown(ComponentEvent e) {}
/*     */ 
/*     */   
/*     */   public void componentHidden(ComponentEvent e) {}
/*     */ 
/*     */   
/*     */   public static class WindowResizer
/*     */     extends FlatWindowResizer
/*     */     implements WindowStateListener
/*     */   {
/*     */     protected Window window;
/*     */     private final boolean limitResizeToScreenBounds;
/*     */     
/*     */     public WindowResizer(JRootPane rootPane) {
/* 240 */       super(rootPane);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 245 */       this.limitResizeToScreenBounds = SystemInfo.isLinux;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void addNotify() {
/* 250 */       Container parent = this.resizeComp.getParent();
/* 251 */       this.window = (parent instanceof Window) ? (Window)parent : null;
/* 252 */       if (this.window instanceof Frame) {
/* 253 */         this.window.addPropertyChangeListener("resizable", this);
/* 254 */         this.window.addWindowStateListener(this);
/*     */       } 
/*     */       
/* 257 */       super.addNotify();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void removeNotify() {
/* 262 */       if (this.window instanceof Frame) {
/* 263 */         this.window.removePropertyChangeListener("resizable", this);
/* 264 */         this.window.removeWindowStateListener(this);
/*     */       } 
/* 266 */       this.window = null;
/*     */       
/* 268 */       super.removeNotify();
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean isWindowResizable() {
/* 273 */       if (FlatUIUtils.isFullScreen(this.resizeComp))
/* 274 */         return false; 
/* 275 */       if (this.window instanceof Frame)
/* 276 */         return (((Frame)this.window).isResizable() && (((Frame)this.window).getExtendedState() & 0x6) == 0); 
/* 277 */       if (this.window instanceof Dialog)
/* 278 */         return ((Dialog)this.window).isResizable(); 
/* 279 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     protected Rectangle getWindowBounds() {
/* 284 */       return this.window.getBounds();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void setWindowBounds(Rectangle r) {
/* 289 */       this.window.setBounds(r);
/*     */ 
/*     */       
/* 292 */       doLayout();
/*     */       
/* 294 */       if (Toolkit.getDefaultToolkit().isDynamicLayoutActive()) {
/* 295 */         this.window.validate();
/* 296 */         this.resizeComp.repaint();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean limitToParentBounds() {
/* 302 */       return (this.limitResizeToScreenBounds && this.window != null);
/*     */     }
/*     */ 
/*     */     
/*     */     protected Rectangle getParentBounds() {
/* 307 */       if (this.limitResizeToScreenBounds && this.window != null) {
/* 308 */         GraphicsConfiguration gc = this.window.getGraphicsConfiguration();
/* 309 */         Rectangle bounds = gc.getBounds();
/* 310 */         Insets insets = this.window.getToolkit().getScreenInsets(gc);
/* 311 */         return new Rectangle(bounds.x + insets.left, bounds.y + insets.top, bounds.width - insets.left - insets.right, bounds.height - insets.top - insets.bottom);
/*     */       } 
/*     */ 
/*     */       
/* 315 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean honorMinimumSizeOnResize() {
/* 320 */       return ((this.honorFrameMinimumSizeOnResize && this.window instanceof Frame) || (this.honorDialogMinimumSizeOnResize && this.window instanceof Dialog));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean honorMaximumSizeOnResize() {
/* 327 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     protected Dimension getWindowMinimumSize() {
/* 332 */       return this.window.getMinimumSize();
/*     */     }
/*     */ 
/*     */     
/*     */     protected Dimension getWindowMaximumSize() {
/* 337 */       return this.window.getMaximumSize();
/*     */     }
/*     */ 
/*     */     
/*     */     boolean isDialog() {
/* 342 */       return this.window instanceof Dialog;
/*     */     }
/*     */ 
/*     */     
/*     */     public void windowStateChanged(WindowEvent e) {
/* 347 */       updateVisibility();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class InternalFrameResizer
/*     */     extends FlatWindowResizer
/*     */   {
/*     */     protected final Supplier<DesktopManager> desktopManager;
/*     */ 
/*     */ 
/*     */     
/*     */     public InternalFrameResizer(JInternalFrame frame, Supplier<DesktopManager> desktopManager) {
/* 362 */       super(frame);
/* 363 */       this.desktopManager = desktopManager;
/*     */       
/* 365 */       frame.addPropertyChangeListener("resizable", this);
/*     */     }
/*     */ 
/*     */     
/*     */     public void uninstall() {
/* 370 */       getFrame().removePropertyChangeListener("resizable", this);
/*     */       
/* 372 */       super.uninstall();
/*     */     }
/*     */     
/*     */     private JInternalFrame getFrame() {
/* 376 */       return (JInternalFrame)this.resizeComp;
/*     */     }
/*     */ 
/*     */     
/*     */     protected Insets getResizeInsets() {
/* 381 */       return getFrame().getInsets();
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean isWindowResizable() {
/* 386 */       return getFrame().isResizable();
/*     */     }
/*     */ 
/*     */     
/*     */     protected Rectangle getWindowBounds() {
/* 391 */       return getFrame().getBounds();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void setWindowBounds(Rectangle r) {
/* 396 */       ((DesktopManager)this.desktopManager.get()).resizeFrame(getFrame(), r.x, r.y, r.width, r.height);
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean limitToParentBounds() {
/* 401 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     protected Rectangle getParentBounds() {
/* 406 */       return new Rectangle(getFrame().getParent().getSize());
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean honorMinimumSizeOnResize() {
/* 411 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean honorMaximumSizeOnResize() {
/* 416 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     protected Dimension getWindowMinimumSize() {
/* 421 */       return getFrame().getMinimumSize();
/*     */     }
/*     */ 
/*     */     
/*     */     protected Dimension getWindowMaximumSize() {
/* 426 */       return getFrame().getMaximumSize();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void beginResizing(int direction) {
/* 431 */       ((DesktopManager)this.desktopManager.get()).beginResizingFrame(getFrame(), direction);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void endResizing() {
/* 436 */       ((DesktopManager)this.desktopManager.get()).endResizingFrame(getFrame());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class DragBorderComponent
/*     */     extends JComponent
/*     */     implements MouseListener, MouseMotionListener
/*     */   {
/*     */     private final int leadingResizeDir;
/*     */     
/*     */     private final int centerResizeDir;
/*     */     
/*     */     private final int trailingResizeDir;
/* 450 */     private int resizeDir = -1;
/*     */     
/*     */     private int leadingCornerDragWidth;
/*     */     
/*     */     private int trailingCornerDragWidth;
/*     */     
/*     */     private int dragLeftOffset;
/*     */     private int dragRightOffset;
/*     */     private int dragTopOffset;
/*     */     private int dragBottomOffset;
/*     */     
/*     */     protected DragBorderComponent(int leadingResizeDir, int centerResizeDir, int trailingResizeDir) {
/* 462 */       this.leadingResizeDir = leadingResizeDir;
/* 463 */       this.centerResizeDir = centerResizeDir;
/* 464 */       this.trailingResizeDir = trailingResizeDir;
/*     */       
/* 466 */       setResizeDir(centerResizeDir);
/* 467 */       setVisible(false);
/*     */       
/* 469 */       addMouseListener(this);
/* 470 */       addMouseMotionListener(this);
/*     */     }
/*     */     
/*     */     void setCornerDragWidths(int leading, int trailing) {
/* 474 */       this.leadingCornerDragWidth = leading;
/* 475 */       this.trailingCornerDragWidth = trailing;
/*     */     }
/*     */     
/*     */     protected void setResizeDir(int resizeDir) {
/* 479 */       if (this.resizeDir == resizeDir)
/*     */         return; 
/* 481 */       this.resizeDir = resizeDir;
/*     */       
/* 483 */       setCursor(Cursor.getPredefinedCursor(resizeDir));
/*     */     }
/*     */ 
/*     */     
/*     */     public Dimension getPreferredSize() {
/* 488 */       int thickness = UIScale.scale(FlatWindowResizer.this.borderDragThickness);
/* 489 */       return new Dimension(thickness, thickness);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void paintComponent(Graphics g) {
/* 494 */       paintChildren(g);
/*     */ 
/*     */ 
/*     */       
/* 498 */       FlatWindowResizer.this.updateVisibility();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseClicked(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mousePressed(MouseEvent e) {
/* 525 */       if (!SwingUtilities.isLeftMouseButton(e) || !FlatWindowResizer.this.isWindowResizable()) {
/*     */         return;
/*     */       }
/* 528 */       int xOnScreen = e.getXOnScreen();
/* 529 */       int yOnScreen = e.getYOnScreen();
/* 530 */       Rectangle windowBounds = FlatWindowResizer.this.getWindowBounds();
/*     */ 
/*     */       
/* 533 */       this.dragLeftOffset = xOnScreen - windowBounds.x;
/* 534 */       this.dragTopOffset = yOnScreen - windowBounds.y;
/* 535 */       this.dragRightOffset = windowBounds.x + windowBounds.width - xOnScreen;
/* 536 */       this.dragBottomOffset = windowBounds.y + windowBounds.height - yOnScreen;
/*     */       
/* 538 */       int direction = 0;
/* 539 */       switch (this.resizeDir) { case 8:
/* 540 */           direction = 1; break;
/* 541 */         case 9: direction = 5; break;
/* 542 */         case 10: direction = 7; break;
/* 543 */         case 11: direction = 3; break;
/* 544 */         case 6: direction = 8; break;
/* 545 */         case 7: direction = 2; break;
/* 546 */         case 4: direction = 6; break;
/* 547 */         case 5: direction = 4; break; }
/*     */       
/* 549 */       FlatWindowResizer.this.beginResizing(direction);
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseReleased(MouseEvent e) {
/* 554 */       if (!SwingUtilities.isLeftMouseButton(e) || !FlatWindowResizer.this.isWindowResizable()) {
/*     */         return;
/*     */       }
/* 557 */       this.dragLeftOffset = this.dragRightOffset = this.dragTopOffset = this.dragBottomOffset = 0;
/*     */       
/* 559 */       FlatWindowResizer.this.endResizing();
/*     */     }
/*     */     
/*     */     public void mouseEntered(MouseEvent e) {}
/*     */     
/*     */     public void mouseExited(MouseEvent e) {}
/*     */     
/*     */     public void mouseMoved(MouseEvent e) {
/* 567 */       boolean topOrBottom = (this.centerResizeDir == 8 || this.centerResizeDir == 9);
/* 568 */       int xy = topOrBottom ? e.getX() : e.getY();
/* 569 */       int wh = topOrBottom ? getWidth() : getHeight();
/*     */       
/* 571 */       setResizeDir((xy <= this.leadingCornerDragWidth) ? 
/* 572 */           this.leadingResizeDir : (
/* 573 */           (xy >= wh - this.trailingCornerDragWidth) ? 
/* 574 */           this.trailingResizeDir : 
/* 575 */           this.centerResizeDir));
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseDragged(MouseEvent e) {
/* 580 */       if (!SwingUtilities.isLeftMouseButton(e) || !FlatWindowResizer.this.isWindowResizable()) {
/*     */         return;
/*     */       }
/* 583 */       int xOnScreen = e.getXOnScreen();
/* 584 */       int yOnScreen = e.getYOnScreen();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 592 */       Rectangle oldBounds = FlatWindowResizer.this.getWindowBounds();
/* 593 */       Rectangle newBounds = new Rectangle(oldBounds);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 598 */       if (this.resizeDir == 8 || this.resizeDir == 6 || this.resizeDir == 7) {
/* 599 */         newBounds.y = yOnScreen - this.dragTopOffset;
/* 600 */         if (FlatWindowResizer.this.limitToParentBounds())
/* 601 */           newBounds.y = Math.max(newBounds.y, (FlatWindowResizer.this.getParentBounds()).y); 
/* 602 */         newBounds.height += oldBounds.y - newBounds.y;
/*     */       } 
/*     */ 
/*     */       
/* 606 */       if (this.resizeDir == 9 || this.resizeDir == 4 || this.resizeDir == 5) {
/* 607 */         newBounds.height = yOnScreen + this.dragBottomOffset - newBounds.y;
/* 608 */         if (FlatWindowResizer.this.limitToParentBounds()) {
/* 609 */           Rectangle parentBounds = FlatWindowResizer.this.getParentBounds();
/* 610 */           int parentBottomY = parentBounds.y + parentBounds.height;
/* 611 */           if (newBounds.y + newBounds.height > parentBottomY) {
/* 612 */             newBounds.height = parentBottomY - newBounds.y;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 617 */       if (this.resizeDir == 10 || this.resizeDir == 6 || this.resizeDir == 4) {
/* 618 */         newBounds.x = xOnScreen - this.dragLeftOffset;
/* 619 */         if (FlatWindowResizer.this.limitToParentBounds())
/* 620 */           newBounds.x = Math.max(newBounds.x, (FlatWindowResizer.this.getParentBounds()).x); 
/* 621 */         newBounds.width += oldBounds.x - newBounds.x;
/*     */       } 
/*     */ 
/*     */       
/* 625 */       if (this.resizeDir == 11 || this.resizeDir == 7 || this.resizeDir == 5) {
/* 626 */         newBounds.width = xOnScreen + this.dragRightOffset - newBounds.x;
/* 627 */         if (FlatWindowResizer.this.limitToParentBounds()) {
/* 628 */           Rectangle parentBounds = FlatWindowResizer.this.getParentBounds();
/* 629 */           int parentRightX = parentBounds.x + parentBounds.width;
/* 630 */           if (newBounds.x + newBounds.width > parentRightX) {
/* 631 */             newBounds.width = parentRightX - newBounds.x;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 636 */       Dimension minimumSize = FlatWindowResizer.this.honorMinimumSizeOnResize() ? FlatWindowResizer.this.getWindowMinimumSize() : null;
/* 637 */       if (minimumSize == null)
/* 638 */         minimumSize = UIScale.scale(new Dimension(150, 50)); 
/* 639 */       if (newBounds.width < minimumSize.width)
/* 640 */         changeWidth(oldBounds, newBounds, minimumSize.width); 
/* 641 */       if (newBounds.height < minimumSize.height) {
/* 642 */         changeHeight(oldBounds, newBounds, minimumSize.height);
/*     */       }
/*     */       
/* 645 */       if (FlatWindowResizer.this.honorMaximumSizeOnResize()) {
/* 646 */         Dimension maximumSize = FlatWindowResizer.this.getWindowMaximumSize();
/* 647 */         if (newBounds.width > maximumSize.width)
/* 648 */           changeWidth(oldBounds, newBounds, maximumSize.width); 
/* 649 */         if (newBounds.height > maximumSize.height) {
/* 650 */           changeHeight(oldBounds, newBounds, maximumSize.height);
/*     */         }
/*     */       } 
/*     */       
/* 654 */       if (!newBounds.equals(oldBounds))
/* 655 */         FlatWindowResizer.this.setWindowBounds(newBounds); 
/*     */     }
/*     */     
/*     */     private void changeWidth(Rectangle oldBounds, Rectangle newBounds, int width) {
/* 659 */       if (newBounds.x != oldBounds.x)
/* 660 */         newBounds.x -= width - newBounds.width; 
/* 661 */       newBounds.width = width;
/*     */     }
/*     */     
/*     */     private void changeHeight(Rectangle oldBounds, Rectangle newBounds, int height) {
/* 665 */       if (newBounds.y != oldBounds.y)
/* 666 */         newBounds.y -= height - newBounds.height; 
/* 667 */       newBounds.height = height;
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatWindowResizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */