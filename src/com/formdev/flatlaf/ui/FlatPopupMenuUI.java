/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.GraphicsDevice;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.event.MouseWheelListener;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JViewport;
/*     */ import javax.swing.MenuElement;
/*     */ import javax.swing.MenuSelectionManager;
/*     */ import javax.swing.Popup;
/*     */ import javax.swing.PopupFactory;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.Timer;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.event.MenuKeyEvent;
/*     */ import javax.swing.event.MenuKeyListener;
/*     */ import javax.swing.event.PopupMenuEvent;
/*     */ import javax.swing.event.PopupMenuListener;
/*     */ import javax.swing.plaf.ButtonUI;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicMenuItemUI;
/*     */ import javax.swing.plaf.basic.BasicPopupMenuUI;
/*     */ import javax.swing.plaf.basic.DefaultMenuLayout;
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
/*     */ public class FlatPopupMenuUI
/*     */   extends BasicPopupMenuUI
/*     */   implements FlatStylingSupport.StyleableUI
/*     */ {
/*     */   @Styleable
/*     */   protected String arrowType;
/*     */   @Styleable
/*     */   protected Color scrollArrowColor;
/*     */   @Styleable
/*     */   protected Color hoverScrollArrowBackground;
/*     */   private PropertyChangeListener propertyChangeListener;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   private AtomicBoolean borderShared;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/* 106 */     return new FlatPopupMenuUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/* 111 */     super.installUI(c);
/*     */     
/* 113 */     installStyle();
/*     */   }
/*     */ 
/*     */   
/*     */   public void uninstallUI(JComponent c) {
/* 118 */     super.uninstallUI(c);
/*     */     
/* 120 */     this.oldStyleValues = null;
/* 121 */     this.borderShared = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void installDefaults() {
/* 126 */     super.installDefaults();
/*     */     
/* 128 */     this.arrowType = UIManager.getString("Component.arrowType");
/* 129 */     this.scrollArrowColor = UIManager.getColor("PopupMenu.scrollArrowColor");
/* 130 */     this.hoverScrollArrowBackground = UIManager.getColor("PopupMenu.hoverScrollArrowBackground");
/*     */     
/* 132 */     LayoutManager layout = this.popupMenu.getLayout();
/* 133 */     if (layout == null || layout instanceof javax.swing.plaf.UIResource) {
/* 134 */       this.popupMenu.setLayout(new FlatPopupMenuLayout(this.popupMenu, 1));
/*     */     }
/*     */   }
/*     */   
/*     */   protected void uninstallDefaults() {
/* 139 */     super.uninstallDefaults();
/*     */     
/* 141 */     this.scrollArrowColor = null;
/* 142 */     this.hoverScrollArrowBackground = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners() {
/* 147 */     super.installListeners();
/*     */     
/* 149 */     this.propertyChangeListener = FlatStylingSupport.createPropertyChangeListener(this.popupMenu, this::installStyle, null);
/* 150 */     this.popupMenu.addPropertyChangeListener(this.propertyChangeListener);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners() {
/* 155 */     super.uninstallListeners();
/*     */     
/* 157 */     this.popupMenu.removePropertyChangeListener(this.propertyChangeListener);
/* 158 */     this.propertyChangeListener = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle() {
/*     */     try {
/* 164 */       applyStyle(FlatStylingSupport.getResolvedStyle(this.popupMenu, "PopupMenu"));
/* 165 */     } catch (RuntimeException ex) {
/* 166 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 172 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 177 */     if (this.borderShared == null)
/* 178 */       this.borderShared = new AtomicBoolean(true); 
/* 179 */     return FlatStylingSupport.applyToAnnotatedObjectOrBorder(this, key, value, this.popupMenu, this.borderShared);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 185 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this, this.popupMenu.getBorder());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 191 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, this.popupMenu.getBorder(), key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Popup getPopup(JPopupMenu popup, int x, int y) {
/* 197 */     if (popup instanceof javax.swing.plaf.basic.BasicComboPopup || (popup
/* 198 */       .getComponentCount() > 0 && popup.getComponent(0) instanceof JScrollPane)) {
/* 199 */       return super.getPopup(popup, x, y);
/*     */     }
/*     */     
/* 202 */     Dimension prefSize = popup.getPreferredSize();
/* 203 */     int screenHeight = getScreenHeightAt(x, y);
/* 204 */     if (prefSize.height <= screenHeight) {
/* 205 */       return super.getPopup(popup, x, y);
/*     */     }
/*     */     
/* 208 */     FlatPopupScroller scroller = new FlatPopupScroller(popup);
/* 209 */     scroller.setPreferredSize(new Dimension(prefSize.width, screenHeight));
/*     */ 
/*     */     
/* 212 */     PopupFactory popupFactory = PopupFactory.getSharedInstance();
/* 213 */     return popupFactory.getPopup(popup.getInvoker(), scroller, x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   private int getScreenHeightAt(int x, int y) {
/* 218 */     GraphicsConfiguration gc = null;
/* 219 */     for (GraphicsDevice device : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
/* 220 */       if (device.getType() == 0) {
/* 221 */         GraphicsConfiguration dgc = device.getDefaultConfiguration();
/* 222 */         if (dgc.getBounds().contains(x, y)) {
/* 223 */           gc = dgc;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 228 */     if (gc == null && this.popupMenu.getInvoker() != null) {
/* 229 */       gc = this.popupMenu.getInvoker().getGraphicsConfiguration();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 234 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/* 235 */     Rectangle screenBounds = (gc != null) ? gc.getBounds() : new Rectangle(toolkit.getScreenSize());
/* 236 */     Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gc);
/* 237 */     return screenBounds.height - screenInsets.top - screenInsets.bottom;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class FlatPopupMenuLayout
/*     */     extends DefaultMenuLayout
/*     */   {
/*     */     public FlatPopupMenuLayout(Container target, int axis) {
/* 249 */       super(target, axis);
/*     */     }
/*     */ 
/*     */     
/*     */     public Dimension preferredLayoutSize(Container target) {
/* 254 */       FlatMenuItemRenderer.clearClientProperties(target);
/*     */       
/* 256 */       return super.preferredLayoutSize(target);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private class FlatPopupScroller
/*     */     extends JPanel
/*     */     implements MouseWheelListener, PopupMenuListener, MenuKeyListener
/*     */   {
/*     */     private final JPopupMenu popup;
/*     */     
/*     */     private final JScrollPane scrollPane;
/*     */     
/*     */     private final JButton scrollUpButton;
/*     */     private final JButton scrollDownButton;
/*     */     private int unitIncrement;
/*     */     
/*     */     FlatPopupScroller(JPopupMenu popup) {
/* 274 */       super(new BorderLayout());
/* 275 */       this.popup = popup;
/*     */ 
/*     */ 
/*     */       
/* 279 */       JPanel view = new JPanel(new BorderLayout());
/* 280 */       view.add(popup, "Center");
/*     */       
/* 282 */       this.scrollPane = new JScrollPane(view, 21, 31);
/* 283 */       this.scrollPane.setBorder((Border)null);
/*     */       
/* 285 */       this.scrollUpButton = new ArrowButton(1);
/* 286 */       this.scrollDownButton = new ArrowButton(5);
/*     */       
/* 288 */       add(this.scrollPane, "Center");
/* 289 */       add(this.scrollUpButton, "North");
/* 290 */       add(this.scrollDownButton, "South");
/*     */       
/* 292 */       setBackground(popup.getBackground());
/* 293 */       setBorder(popup.getBorder());
/* 294 */       popup.setBorder((Border)null);
/*     */       
/* 296 */       popup.addPopupMenuListener(this);
/* 297 */       popup.addMouseWheelListener(this);
/* 298 */       popup.addMenuKeyListener(this);
/*     */       
/* 300 */       updateArrowButtons();
/*     */       
/* 302 */       putClientProperty("Popup.borderCornerRadius", 
/* 303 */           Integer.valueOf(UIManager.getInt("PopupMenu.borderCornerRadius")));
/*     */     }
/*     */     
/*     */     void scroll(int unitsToScroll) {
/* 307 */       if (this.unitIncrement == 0) {
/* 308 */         this.unitIncrement = ((new JMenuItem("X")).getPreferredSize()).height;
/*     */       }
/* 310 */       JViewport viewport = this.scrollPane.getViewport();
/* 311 */       Point viewPosition = viewport.getViewPosition();
/* 312 */       int newY = viewPosition.y + this.unitIncrement * unitsToScroll;
/* 313 */       if (newY < 0) {
/* 314 */         newY = 0;
/*     */       } else {
/* 316 */         newY = Math.min(newY, (viewport.getViewSize()).height - (viewport.getExtentSize()).height);
/* 317 */       }  viewport.setViewPosition(new Point(viewPosition.x, newY));
/*     */       
/* 319 */       updateArrowButtons();
/*     */     }
/*     */     
/*     */     void updateArrowButtons() {
/* 323 */       JViewport viewport = this.scrollPane.getViewport();
/* 324 */       Point viewPosition = viewport.getViewPosition();
/*     */       
/* 326 */       this.scrollUpButton.setVisible((viewPosition.y > 0));
/* 327 */       this.scrollDownButton.setVisible((viewPosition.y < (viewport.getViewSize()).height - (viewport.getExtentSize()).height));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
/* 335 */       this.popup.setBorder(getBorder());
/*     */       
/* 337 */       this.popup.removePopupMenuListener(this);
/* 338 */       this.popup.removeMouseWheelListener(this);
/* 339 */       this.popup.removeMenuKeyListener(this);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void popupMenuCanceled(PopupMenuEvent e) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseWheelMoved(MouseWheelEvent e) {
/* 353 */       Point mouseLocation = SwingUtilities.convertPoint((Component)e.getSource(), e.getPoint(), this);
/*     */ 
/*     */       
/* 356 */       scroll(e.getUnitsToScroll());
/*     */ 
/*     */       
/* 359 */       Component c = SwingUtilities.getDeepestComponentAt(this, mouseLocation.x, mouseLocation.y);
/* 360 */       if (c instanceof JMenuItem) {
/* 361 */         ButtonUI ui = ((JMenuItem)c).getUI();
/* 362 */         if (ui instanceof BasicMenuItemUI) {
/* 363 */           MenuSelectionManager.defaultManager().setSelectedPath(((BasicMenuItemUI)ui).getPath());
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 368 */       e.consume();
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
/*     */     public void menuKeyPressed(MenuKeyEvent e) {
/* 380 */       EventQueue.invokeLater(() -> {
/*     */             if (!isDisplayable()) {
/*     */               return;
/*     */             }
/*     */             MenuElement[] path = MenuSelectionManager.defaultManager().getSelectedPath();
/*     */             if (path.length == 0) {
/*     */               return;
/*     */             }
/*     */             Component c = path[path.length - 1].getComponent();
/*     */             JViewport viewport = this.scrollPane.getViewport();
/*     */             Point pt = SwingUtilities.convertPoint(c, 0, 0, viewport);
/*     */             viewport.scrollRectToVisible(new Rectangle(pt, c.getSize()));
/*     */             boolean upVisible = this.scrollUpButton.isVisible();
/*     */             updateArrowButtons();
/*     */             if (!upVisible && this.scrollUpButton.isVisible()) {
/*     */               Point viewPosition = viewport.getViewPosition();
/*     */               int newY = viewPosition.y + (this.scrollUpButton.getPreferredSize()).height;
/*     */               viewport.setViewPosition(new Point(viewPosition.x, newY));
/*     */             } 
/*     */           });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void menuKeyTyped(MenuKeyEvent e) {}
/*     */ 
/*     */     
/*     */     public void menuKeyReleased(MenuKeyEvent e) {}
/*     */ 
/*     */     
/*     */     private class ArrowButton
/*     */       extends FlatArrowButton
/*     */       implements MouseListener, ActionListener
/*     */     {
/*     */       private Timer timer;
/*     */ 
/*     */       
/*     */       ArrowButton(int direction) {
/* 418 */         super(direction, FlatPopupMenuUI.this.arrowType, FlatPopupMenuUI.this.scrollArrowColor, (Color)null, (Color)null, FlatPopupMenuUI.this.hoverScrollArrowBackground, (Color)null, (Color)null);
/*     */         
/* 420 */         addMouseListener(this);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public void paint(Graphics g) {
/* 426 */         g.setColor(FlatPopupMenuUI.FlatPopupScroller.this.popup.getBackground());
/* 427 */         g.fillRect(0, 0, getWidth(), getHeight());
/*     */         
/* 429 */         super.paint(g);
/*     */       }
/*     */ 
/*     */       
/*     */       public void mouseClicked(MouseEvent e) {}
/*     */       
/*     */       public void mousePressed(MouseEvent e) {}
/*     */       
/*     */       public void mouseReleased(MouseEvent e) {}
/*     */       
/*     */       public void mouseEntered(MouseEvent e) {
/* 440 */         if (this.timer == null)
/* 441 */           this.timer = new Timer(50, this); 
/* 442 */         this.timer.start();
/*     */       }
/*     */ 
/*     */       
/*     */       public void mouseExited(MouseEvent e) {
/* 447 */         if (this.timer != null) {
/* 448 */           this.timer.stop();
/*     */         }
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public void actionPerformed(ActionEvent e) {
/* 455 */         if (this.timer != null && !isDisplayable()) {
/* 456 */           this.timer.stop();
/*     */           
/*     */           return;
/*     */         } 
/* 460 */         FlatPopupMenuUI.FlatPopupScroller.this.scroll((this.direction == 1) ? -1 : 1);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatPopupMenuUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */