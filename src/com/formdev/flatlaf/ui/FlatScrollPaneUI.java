/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import java.awt.KeyboardFocusManager;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ContainerEvent;
/*     */ import java.awt.event.ContainerListener;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.event.MouseWheelListener;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.JTree;
/*     */ import javax.swing.JViewport;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.Scrollable;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicScrollPaneUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatScrollPaneUI
/*     */   extends BasicScrollPaneUI
/*     */   implements FlatStylingSupport.StyleableUI
/*     */ {
/*     */   @Styleable
/*     */   protected Boolean showButtons;
/*     */   private Handler handler;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   private AtomicBoolean borderShared;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  85 */     return new FlatScrollPaneUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  90 */     if (FlatUIUtils.needsLightAWTPeer(c)) {
/*  91 */       FlatUIUtils.runWithLightAWTPeerUIDefaults(() -> installUIImpl(c));
/*     */     } else {
/*  93 */       installUIImpl(c);
/*     */     } 
/*     */   }
/*     */   private void installUIImpl(JComponent c) {
/*  97 */     super.installUI(c);
/*     */     
/*  99 */     int focusWidth = UIManager.getInt("Component.focusWidth");
/* 100 */     LookAndFeel.installProperty(c, "opaque", Boolean.valueOf((focusWidth == 0)));
/*     */     
/* 102 */     installStyle();
/*     */     
/* 104 */     MigLayoutVisualPadding.install(this.scrollpane);
/*     */   }
/*     */ 
/*     */   
/*     */   public void uninstallUI(JComponent c) {
/* 109 */     MigLayoutVisualPadding.uninstall(this.scrollpane);
/*     */     
/* 111 */     super.uninstallUI(c);
/*     */     
/* 113 */     this.oldStyleValues = null;
/* 114 */     this.borderShared = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners(JScrollPane c) {
/* 119 */     super.installListeners(c);
/*     */     
/* 121 */     addViewportListeners(this.scrollpane.getViewport());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners(JComponent c) {
/* 126 */     super.uninstallListeners(c);
/*     */     
/* 128 */     removeViewportListeners(this.scrollpane.getViewport());
/*     */     
/* 130 */     this.handler = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected MouseWheelListener createMouseWheelListener() {
/* 135 */     MouseWheelListener superListener = super.createMouseWheelListener();
/* 136 */     return e -> {
/*     */         if (isSmoothScrollingEnabled() && this.scrollpane.isWheelScrollingEnabled() && e.getScrollType() == 0 && e.getPreciseWheelRotation() != 0.0D && e.getPreciseWheelRotation() != e.getWheelRotation()) {
/*     */           mouseWheelMovedSmooth(e);
/*     */         } else {
/*     */           superListener.mouseWheelMoved(e);
/*     */         } 
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSmoothScrollingEnabled() {
/* 150 */     Object smoothScrolling = this.scrollpane.getClientProperty("JScrollPane.smoothScrolling");
/* 151 */     if (smoothScrolling instanceof Boolean) {
/* 152 */       return ((Boolean)smoothScrolling).booleanValue();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 157 */     return UIManager.getBoolean("ScrollPane.smoothScrolling");
/*     */   }
/*     */   
/*     */   private void mouseWheelMovedSmooth(MouseWheelEvent e) {
/*     */     int unitIncrement;
/* 162 */     JViewport viewport = this.scrollpane.getViewport();
/* 163 */     if (viewport == null) {
/*     */       return;
/*     */     }
/*     */     
/* 167 */     JScrollBar scrollbar = this.scrollpane.getVerticalScrollBar();
/* 168 */     if (scrollbar == null || !scrollbar.isVisible() || e.isShiftDown()) {
/* 169 */       scrollbar = this.scrollpane.getHorizontalScrollBar();
/* 170 */       if (scrollbar == null || !scrollbar.isVisible()) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     
/* 175 */     e.consume();
/*     */ 
/*     */     
/* 178 */     double rotation = e.getPreciseWheelRotation();
/*     */ 
/*     */ 
/*     */     
/* 182 */     int orientation = scrollbar.getOrientation();
/* 183 */     Component view = viewport.getView();
/* 184 */     if (view instanceof Scrollable) {
/* 185 */       Scrollable scrollable = (Scrollable)view;
/*     */ 
/*     */ 
/*     */       
/* 189 */       Rectangle visibleRect = new Rectangle(viewport.getViewSize());
/* 190 */       unitIncrement = scrollable.getScrollableUnitIncrement(visibleRect, orientation, 1);
/*     */       
/* 192 */       if (unitIncrement > 0) {
/*     */ 
/*     */ 
/*     */         
/* 196 */         if (orientation == 1) {
/* 197 */           visibleRect.y += unitIncrement;
/* 198 */           visibleRect.height -= unitIncrement;
/*     */         } else {
/* 200 */           visibleRect.x += unitIncrement;
/* 201 */           visibleRect.width -= unitIncrement;
/*     */         } 
/* 203 */         int unitIncrement2 = scrollable.getScrollableUnitIncrement(visibleRect, orientation, 1);
/* 204 */         if (unitIncrement2 > 0)
/* 205 */           unitIncrement = Math.min(unitIncrement, unitIncrement2); 
/*     */       } 
/*     */     } else {
/* 208 */       int direction = (rotation < 0.0D) ? -1 : 1;
/* 209 */       unitIncrement = scrollbar.getUnitIncrement(direction);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 215 */     int viewportWH = (orientation == 1) ? viewport.getHeight() : viewport.getWidth();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 220 */     int scrollIncrement = Math.min(unitIncrement * e.getScrollAmount(), viewportWH);
/*     */ 
/*     */     
/* 223 */     double delta = rotation * scrollIncrement;
/* 224 */     int idelta = (int)Math.round(delta);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 230 */     if (idelta == 0) {
/* 231 */       if (rotation > 0.0D) {
/* 232 */         idelta = 1;
/* 233 */       } else if (rotation < 0.0D) {
/* 234 */         idelta = -1;
/*     */       } 
/*     */     }
/*     */     
/* 238 */     int value = scrollbar.getValue();
/* 239 */     int minValue = scrollbar.getMinimum();
/* 240 */     int maxValue = scrollbar.getMaximum() - scrollbar.getModel().getExtent();
/* 241 */     int newValue = Math.max(minValue, Math.min(value + idelta, maxValue));
/*     */ 
/*     */     
/* 244 */     if (newValue != value) {
/* 245 */       scrollbar.setValue(newValue);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected PropertyChangeListener createPropertyChangeListener() {
/* 267 */     PropertyChangeListener superListener = super.createPropertyChangeListener();
/* 268 */     return e -> {
/*     */         JScrollBar vsb;
/*     */         JScrollBar hsb;
/*     */         Object corner;
/*     */         superListener.propertyChange(e);
/*     */         switch (e.getPropertyName()) {
/*     */           case "JScrollBar.showButtons":
/*     */             vsb = this.scrollpane.getVerticalScrollBar();
/*     */             hsb = this.scrollpane.getHorizontalScrollBar();
/*     */             if (vsb != null) {
/*     */               vsb.revalidate();
/*     */               vsb.repaint();
/*     */             } 
/*     */             if (hsb != null) {
/*     */               hsb.revalidate();
/*     */               hsb.repaint();
/*     */             } 
/*     */             break;
/*     */           case "LOWER_LEFT_CORNER":
/*     */           case "LOWER_RIGHT_CORNER":
/*     */           case "UPPER_LEFT_CORNER":
/*     */           case "UPPER_RIGHT_CORNER":
/*     */             corner = e.getNewValue();
/*     */             if (corner instanceof JButton && ((JButton)corner).getBorder() instanceof FlatButtonBorder && this.scrollpane.getViewport() != null && this.scrollpane.getViewport().getView() instanceof JTable) {
/*     */               ((JButton)corner).setBorder(BorderFactory.createEmptyBorder());
/*     */               ((JButton)corner).setFocusable(false);
/*     */             } 
/*     */             break;
/*     */           case "JComponent.outline":
/*     */             this.scrollpane.repaint();
/*     */             break;
/*     */           case "FlatLaf.style":
/*     */           case "FlatLaf.styleClass":
/*     */             installStyle();
/*     */             this.scrollpane.revalidate();
/*     */             this.scrollpane.repaint();
/*     */             break;
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
/*     */   private Handler getHandler() {
/* 316 */     if (this.handler == null)
/* 317 */       this.handler = new Handler(); 
/* 318 */     return this.handler;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle() {
/*     */     try {
/* 324 */       applyStyle(FlatStylingSupport.getResolvedStyle(this.scrollpane, "ScrollPane"));
/* 325 */     } catch (RuntimeException ex) {
/* 326 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 332 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 337 */     if (key.equals("focusWidth")) {
/* 338 */       int focusWidth = (value instanceof Integer) ? ((Integer)value).intValue() : UIManager.getInt("Component.focusWidth");
/* 339 */       LookAndFeel.installProperty(this.scrollpane, "opaque", Boolean.valueOf((focusWidth == 0)));
/*     */     } 
/*     */     
/* 342 */     if (this.borderShared == null)
/* 343 */       this.borderShared = new AtomicBoolean(true); 
/* 344 */     return FlatStylingSupport.applyToAnnotatedObjectOrBorder(this, key, value, this.scrollpane, this.borderShared);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 350 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this, this.scrollpane.getBorder());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 356 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, this.scrollpane.getBorder(), key);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateViewport(PropertyChangeEvent e) {
/* 361 */     super.updateViewport(e);
/*     */     
/* 363 */     JViewport oldViewport = (JViewport)e.getOldValue();
/* 364 */     JViewport newViewport = (JViewport)e.getNewValue();
/*     */     
/* 366 */     removeViewportListeners(oldViewport);
/* 367 */     addViewportListeners(newViewport);
/*     */   }
/*     */   
/*     */   private void addViewportListeners(JViewport viewport) {
/* 371 */     if (viewport == null) {
/*     */       return;
/*     */     }
/* 374 */     viewport.addContainerListener(getHandler());
/*     */     
/* 376 */     Component view = viewport.getView();
/* 377 */     if (view != null)
/* 378 */       view.addFocusListener(getHandler()); 
/*     */   }
/*     */   
/*     */   private void removeViewportListeners(JViewport viewport) {
/* 382 */     if (viewport == null) {
/*     */       return;
/*     */     }
/* 385 */     viewport.removeContainerListener(getHandler());
/*     */     
/* 387 */     Component view = viewport.getView();
/* 388 */     if (view != null) {
/* 389 */       view.removeFocusListener(getHandler());
/*     */     }
/*     */   }
/*     */   
/*     */   public void update(Graphics g, JComponent c) {
/* 394 */     if (c.isOpaque()) {
/* 395 */       FlatUIUtils.paintParentBackground(g, c);
/*     */ 
/*     */       
/* 398 */       Insets insets = c.getInsets();
/* 399 */       g.setColor(c.getBackground());
/* 400 */       g.fillRect(insets.left, insets.top, c
/* 401 */           .getWidth() - insets.left - insets.right, c
/* 402 */           .getHeight() - insets.top - insets.bottom);
/*     */     } 
/*     */     
/* 405 */     paint(g, c);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isPermanentFocusOwner(JScrollPane scrollPane) {
/* 410 */     JViewport viewport = scrollPane.getViewport();
/* 411 */     Component view = (viewport != null) ? viewport.getView() : null;
/* 412 */     if (view == null) {
/* 413 */       return false;
/*     */     }
/*     */     
/* 416 */     if (FlatUIUtils.isPermanentFocusOwner(view)) {
/* 417 */       return true;
/*     */     }
/*     */     
/* 420 */     if ((view instanceof JTable && ((JTable)view).isEditing()) || (view instanceof JTree && ((JTree)view)
/* 421 */       .isEditing())) {
/*     */       
/* 423 */       Component focusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
/* 424 */       if (focusOwner != null) {
/* 425 */         return SwingUtilities.isDescendingFrom(focusOwner, view);
/*     */       }
/*     */     } 
/* 428 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private class Handler
/*     */     implements ContainerListener, FocusListener
/*     */   {
/*     */     private Handler() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void componentAdded(ContainerEvent e) {
/* 442 */       e.getChild().addFocusListener(this);
/*     */     }
/*     */ 
/*     */     
/*     */     public void componentRemoved(ContainerEvent e) {
/* 447 */       e.getChild().removeFocusListener(this);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void focusGained(FocusEvent e) {
/* 453 */       FlatScrollPaneUI.this.scrollpane.repaint();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void focusLost(FocusEvent e) {
/* 459 */       FlatScrollPaneUI.this.scrollpane.repaint();
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatScrollPaneUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */