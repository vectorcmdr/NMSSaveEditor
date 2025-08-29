/*      */ package com.formdev.flatlaf.ui;
/*      */ 
/*      */ import com.formdev.flatlaf.FlatClientProperties;
/*      */ import com.formdev.flatlaf.util.SystemInfo;
/*      */ import com.formdev.flatlaf.util.UIScale;
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dialog;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.EventQueue;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Frame;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.GraphicsConfiguration;
/*      */ import java.awt.Image;
/*      */ import java.awt.Insets;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.Window;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.ComponentEvent;
/*      */ import java.awt.event.ComponentListener;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseListener;
/*      */ import java.awt.event.MouseMotionListener;
/*      */ import java.awt.event.WindowAdapter;
/*      */ import java.awt.event.WindowEvent;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.Objects;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.BoxLayout;
/*      */ import javax.swing.Icon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JMenuBar;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JRootPane;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.border.AbstractBorder;
/*      */ import javax.swing.border.Border;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class FlatTitlePane
/*      */   extends JComponent
/*      */ {
/*      */   private static final String KEY_DEBUG_SHOW_RECTANGLES = "FlatLaf.debug.titlebar.showRectangles";
/*  112 */   protected final Font titleFont = UIManager.getFont("TitlePane.font");
/*  113 */   protected final Color activeBackground = UIManager.getColor("TitlePane.background");
/*  114 */   protected final Color inactiveBackground = UIManager.getColor("TitlePane.inactiveBackground");
/*  115 */   protected final Color activeForeground = UIManager.getColor("TitlePane.foreground");
/*  116 */   protected final Color inactiveForeground = UIManager.getColor("TitlePane.inactiveForeground");
/*  117 */   protected final Color embeddedForeground = UIManager.getColor("TitlePane.embeddedForeground");
/*  118 */   protected final Color borderColor = UIManager.getColor("TitlePane.borderColor");
/*      */   
/*  120 */   protected final boolean showIcon = FlatUIUtils.getUIBoolean("TitlePane.showIcon", true);
/*  121 */   protected final boolean showIconInDialogs = FlatUIUtils.getUIBoolean("TitlePane.showIconInDialogs", true);
/*  122 */   protected final int noIconLeftGap = FlatUIUtils.getUIInt("TitlePane.noIconLeftGap", 8);
/*  123 */   protected final Dimension iconSize = UIManager.getDimension("TitlePane.iconSize");
/*  124 */   protected final int titleMinimumWidth = FlatUIUtils.getUIInt("TitlePane.titleMinimumWidth", 60);
/*  125 */   protected final int buttonMinimumWidth = FlatUIUtils.getUIInt("TitlePane.buttonMinimumWidth", 30);
/*  126 */   protected final int buttonMaximizedHeight = UIManager.getInt("TitlePane.buttonMaximizedHeight");
/*  127 */   protected final boolean centerTitle = UIManager.getBoolean("TitlePane.centerTitle");
/*  128 */   protected final boolean centerTitleIfMenuBarEmbedded = FlatUIUtils.getUIBoolean("TitlePane.centerTitleIfMenuBarEmbedded", true);
/*  129 */   protected final boolean showIconBesideTitle = UIManager.getBoolean("TitlePane.showIconBesideTitle");
/*  130 */   protected final int menuBarTitleGap = FlatUIUtils.getUIInt("TitlePane.menuBarTitleGap", 40);
/*  131 */   protected final int menuBarTitleMinimumGap = FlatUIUtils.getUIInt("TitlePane.menuBarTitleMinimumGap", 12);
/*  132 */   protected final int menuBarResizeHeight = FlatUIUtils.getUIInt("TitlePane.menuBarResizeHeight", 4);
/*      */   
/*      */   protected final JRootPane rootPane;
/*      */   
/*      */   protected JPanel leftPanel;
/*      */   
/*      */   protected JLabel iconLabel;
/*      */   
/*      */   protected JComponent menuBarPlaceholder;
/*      */   
/*      */   protected JLabel titleLabel;
/*      */   
/*      */   protected JPanel buttonPanel;
/*      */   
/*      */   protected JButton iconifyButton;
/*      */   protected JButton maximizeButton;
/*      */   protected JButton restoreButton;
/*      */   
/*      */   public FlatTitlePane(JRootPane rootPane) {
/*  151 */     this.rootPane = rootPane;
/*      */     
/*  153 */     this.handler = createHandler();
/*  154 */     setBorder(createTitlePaneBorder());
/*      */     
/*  156 */     addSubComponents();
/*  157 */     activeChanged(true);
/*      */     
/*  159 */     addMouseListener(this.handler);
/*  160 */     addMouseMotionListener(this.handler);
/*      */ 
/*      */     
/*  163 */     this.iconLabel.addMouseListener(this.handler);
/*      */     
/*  165 */     applyComponentOrientation(rootPane.getComponentOrientation());
/*      */   }
/*      */   protected JButton closeButton; protected Window window; private final Handler handler; private int laterCounter; private int debugTitleBarHeight; private List<Rectangle> debugHitTestSpots; private Rectangle debugAppIconBounds; private Rectangle debugMinimizeButtonBounds; private Rectangle debugMaximizeButtonBounds; private Rectangle debugCloseButtonBounds;
/*      */   protected FlatTitlePaneBorder createTitlePaneBorder() {
/*  169 */     return new FlatTitlePaneBorder();
/*      */   }
/*      */   
/*      */   protected Handler createHandler() {
/*  173 */     return new Handler();
/*      */   }
/*      */   
/*      */   protected void addSubComponents() {
/*  177 */     this.leftPanel = new JPanel();
/*  178 */     this.iconLabel = new JLabel();
/*  179 */     this.titleLabel = new JLabel()
/*      */       {
/*      */         public void updateUI() {
/*  182 */           setUI(new FlatTitlePane.FlatTitleLabelUI());
/*      */         }
/*      */       };
/*  185 */     this.iconLabel.setBorder(new FlatEmptyBorder(UIManager.getInsets("TitlePane.iconMargins")));
/*  186 */     this.titleLabel.setBorder(new FlatEmptyBorder(UIManager.getInsets("TitlePane.titleMargins")));
/*      */     
/*  188 */     this.leftPanel.setLayout(new BoxLayout(this.leftPanel, 2));
/*  189 */     this.leftPanel.setOpaque(false);
/*  190 */     this.leftPanel.add(this.iconLabel);
/*      */     
/*  192 */     this.menuBarPlaceholder = new JComponent()
/*      */       {
/*      */         public Dimension getPreferredSize() {
/*  195 */           JMenuBar menuBar = FlatTitlePane.this.rootPane.getJMenuBar();
/*  196 */           return FlatTitlePane.this.hasVisibleEmbeddedMenuBar(menuBar) ? menuBar.getPreferredSize() : new Dimension();
/*      */         }
/*      */       };
/*  199 */     this.leftPanel.add(this.menuBarPlaceholder);
/*      */     
/*  201 */     createButtons();
/*      */     
/*  203 */     setLayout(new BorderLayout()
/*      */         {
/*      */           public void layoutContainer(Container target)
/*      */           {
/*  207 */             Insets insets = target.getInsets();
/*  208 */             int x = insets.left;
/*  209 */             int y = insets.top;
/*  210 */             int w = target.getWidth() - insets.left - insets.right;
/*  211 */             int h = target.getHeight() - insets.top - insets.bottom;
/*      */ 
/*      */             
/*  214 */             int leftWidth = (FlatTitlePane.this.leftPanel.getPreferredSize()).width;
/*  215 */             int buttonsWidth = (FlatTitlePane.this.buttonPanel.getPreferredSize()).width;
/*  216 */             int titleWidth = w - leftWidth - buttonsWidth;
/*  217 */             int minTitleWidth = UIScale.scale(FlatTitlePane.this.titleMinimumWidth);
/*      */ 
/*      */             
/*  220 */             Icon icon = FlatTitlePane.this.titleLabel.getIcon();
/*  221 */             if (icon != null) {
/*  222 */               Insets iconInsets = FlatTitlePane.this.iconLabel.getInsets();
/*  223 */               int iconTextGap = FlatTitlePane.this.titleLabel.getComponentOrientation().isLeftToRight() ? iconInsets.right : iconInsets.left;
/*  224 */               minTitleWidth += icon.getIconWidth() + iconTextGap;
/*      */             } 
/*      */ 
/*      */             
/*  228 */             if (titleWidth < minTitleWidth) {
/*  229 */               buttonsWidth = Math.max(buttonsWidth - minTitleWidth - titleWidth, (FlatTitlePane.this.buttonPanel.getMinimumSize()).width);
/*  230 */               titleWidth = w - leftWidth - buttonsWidth;
/*      */             } 
/*      */ 
/*      */             
/*  234 */             if (titleWidth < minTitleWidth) {
/*      */ 
/*      */               
/*  237 */               int minLeftWidth = FlatTitlePane.this.iconLabel.isVisible() ? (FlatTitlePane.this.iconLabel.getWidth() - (FlatTitlePane.this.iconLabel.getInsets()).right) : UIScale.scale(FlatTitlePane.this.noIconLeftGap);
/*  238 */               leftWidth = Math.max(leftWidth - minTitleWidth - titleWidth, minLeftWidth);
/*  239 */               titleWidth = w - leftWidth - buttonsWidth;
/*      */             } 
/*      */             
/*  242 */             if (target.getComponentOrientation().isLeftToRight()) {
/*      */               
/*  244 */               FlatTitlePane.this.leftPanel.setBounds(x, y, leftWidth, h);
/*  245 */               FlatTitlePane.this.titleLabel.setBounds(x + leftWidth, y, titleWidth, h);
/*  246 */               FlatTitlePane.this.buttonPanel.setBounds(x + leftWidth + titleWidth, y, buttonsWidth, h);
/*      */             } else {
/*      */               
/*  249 */               FlatTitlePane.this.buttonPanel.setBounds(x, y, buttonsWidth, h);
/*  250 */               FlatTitlePane.this.titleLabel.setBounds(x + buttonsWidth, y, titleWidth, h);
/*  251 */               FlatTitlePane.this.leftPanel.setBounds(x + buttonsWidth + titleWidth, y, leftWidth, h);
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  258 */             JMenuBar menuBar = FlatTitlePane.this.rootPane.getJMenuBar();
/*  259 */             if (FlatTitlePane.this.hasVisibleEmbeddedMenuBar(menuBar)) {
/*  260 */               Component horizontalGlue = FlatTitlePane.this.findHorizontalGlue(menuBar);
/*  261 */               if (horizontalGlue != null) {
/*  262 */                 Point glueLocation = SwingUtilities.convertPoint(horizontalGlue, 0, 0, FlatTitlePane.this.titleLabel);
/*  263 */                 FlatTitlePane.this.titleLabel.setBounds(FlatTitlePane.this.titleLabel.getX() + glueLocation.x, FlatTitlePane.this.titleLabel.getY(), horizontalGlue
/*  264 */                     .getWidth(), FlatTitlePane.this.titleLabel.getHeight());
/*      */               } 
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/*  270 */     add(this.leftPanel, "Before");
/*  271 */     add(this.titleLabel, "Center");
/*  272 */     add(this.buttonPanel, "After");
/*      */   }
/*      */   
/*      */   protected void createButtons() {
/*  276 */     this.iconifyButton = createButton("TitlePane.iconifyIcon", "Iconify", e -> iconify());
/*  277 */     this.maximizeButton = createButton("TitlePane.maximizeIcon", "Maximize", e -> maximize());
/*  278 */     this.restoreButton = createButton("TitlePane.restoreIcon", "Restore", e -> restore());
/*  279 */     this.closeButton = createButton("TitlePane.closeIcon", "Close", e -> close());
/*      */ 
/*      */     
/*  282 */     this.iconifyButton.setVisible(false);
/*  283 */     this.maximizeButton.setVisible(false);
/*  284 */     this.restoreButton.setVisible(false);
/*      */     
/*  286 */     this.buttonPanel = new JPanel()
/*      */       {
/*      */         public Dimension getPreferredSize() {
/*  289 */           Dimension size = super.getPreferredSize();
/*  290 */           if (FlatTitlePane.this.buttonMaximizedHeight > 0 && FlatTitlePane.this.isWindowMaximized() && !FlatTitlePane.this.hasVisibleEmbeddedMenuBar(FlatTitlePane.this.rootPane.getJMenuBar()))
/*      */           {
/*  292 */             size = new Dimension(size.width, Math.min(size.height, UIScale.scale(FlatTitlePane.this.buttonMaximizedHeight)));
/*      */           }
/*  294 */           return size;
/*      */         }
/*      */       };
/*  297 */     this.buttonPanel.setOpaque(false);
/*  298 */     this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, 2));
/*  299 */     if (this.rootPane.getWindowDecorationStyle() == 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  305 */       this.buttonPanel.add(this.iconifyButton);
/*  306 */       this.buttonPanel.add(this.maximizeButton);
/*  307 */       this.buttonPanel.add(this.restoreButton);
/*      */     } 
/*  309 */     this.buttonPanel.add(this.closeButton);
/*      */   }
/*      */   
/*      */   protected JButton createButton(String iconKey, String accessibleName, ActionListener action) {
/*  313 */     JButton button = new JButton(UIManager.getIcon(iconKey))
/*      */       {
/*      */         public Dimension getMinimumSize()
/*      */         {
/*  317 */           return new Dimension(UIScale.scale(FlatTitlePane.this.buttonMinimumWidth), (super.getMinimumSize()).height);
/*      */         }
/*      */       };
/*  320 */     button.setFocusable(false);
/*  321 */     button.setContentAreaFilled(false);
/*  322 */     button.setBorder(BorderFactory.createEmptyBorder());
/*  323 */     button.putClientProperty("AccessibleName", accessibleName);
/*  324 */     button.addActionListener(action);
/*  325 */     return button;
/*      */   }
/*      */   
/*      */   protected void activeChanged(boolean active) {
/*  329 */     Color background = FlatClientProperties.clientPropertyColor(this.rootPane, "JRootPane.titleBarBackground", null);
/*  330 */     Color foreground = FlatClientProperties.clientPropertyColor(this.rootPane, "JRootPane.titleBarForeground", null);
/*  331 */     Color titleForeground = foreground;
/*  332 */     if (background == null)
/*  333 */       background = FlatUIUtils.nonUIResource(active ? this.activeBackground : this.inactiveBackground); 
/*  334 */     if (foreground == null) {
/*  335 */       foreground = FlatUIUtils.nonUIResource(active ? this.activeForeground : this.inactiveForeground);
/*      */ 
/*      */       
/*  338 */       titleForeground = (active && hasVisibleEmbeddedMenuBar(this.rootPane.getJMenuBar())) ? FlatUIUtils.nonUIResource(this.embeddedForeground) : foreground;
/*      */     } 
/*      */     
/*  341 */     setBackground(background);
/*  342 */     this.titleLabel.setForeground(titleForeground);
/*  343 */     this.iconifyButton.setForeground(foreground);
/*  344 */     this.maximizeButton.setForeground(foreground);
/*  345 */     this.restoreButton.setForeground(foreground);
/*  346 */     this.closeButton.setForeground(foreground);
/*      */ 
/*      */     
/*  349 */     this.iconifyButton.setBackground(background);
/*  350 */     this.maximizeButton.setBackground(background);
/*  351 */     this.restoreButton.setBackground(background);
/*  352 */     this.closeButton.setBackground(background);
/*      */   }
/*      */   
/*      */   protected void frameStateChanged() {
/*  356 */     if (this.window == null || this.rootPane.getWindowDecorationStyle() != 1) {
/*      */       return;
/*      */     }
/*  359 */     updateVisibility();
/*      */     
/*  361 */     if (this.window instanceof Frame) {
/*  362 */       Frame frame = (Frame)this.window;
/*      */       
/*  364 */       if (isWindowMaximized() && (!SystemInfo.isLinux || 
/*  365 */         !FlatNativeLinuxLibrary.isWMUtilsSupported(this.window)) && this.rootPane
/*  366 */         .getClientProperty("_flatlaf.maximizedBoundsUpToDate") == null) {
/*      */         
/*  368 */         this.rootPane.putClientProperty("_flatlaf.maximizedBoundsUpToDate", (Object)null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  375 */         Rectangle oldMaximizedBounds = frame.getMaximizedBounds();
/*  376 */         updateMaximizedBounds();
/*  377 */         Rectangle newMaximizedBounds = frame.getMaximizedBounds();
/*  378 */         if (newMaximizedBounds != null && !newMaximizedBounds.equals(oldMaximizedBounds)) {
/*  379 */           int oldExtendedState = frame.getExtendedState();
/*  380 */           frame.setExtendedState(oldExtendedState & 0xFFFFFFF9);
/*  381 */           frame.setExtendedState(oldExtendedState);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void updateVisibility() {
/*  389 */     this.titleLabel.setVisible(FlatClientProperties.clientPropertyBoolean(this.rootPane, "JRootPane.titleBarShowTitle", true));
/*  390 */     this.closeButton.setVisible(FlatClientProperties.clientPropertyBoolean(this.rootPane, "JRootPane.titleBarShowClose", true));
/*      */     
/*  392 */     if (this.window instanceof Frame) {
/*  393 */       Frame frame = (Frame)this.window;
/*  394 */       boolean maximizable = (frame.isResizable() && FlatClientProperties.clientPropertyBoolean(this.rootPane, "JRootPane.titleBarShowMaximize", true));
/*  395 */       boolean maximized = isWindowMaximized();
/*      */       
/*  397 */       this.iconifyButton.setVisible(FlatClientProperties.clientPropertyBoolean(this.rootPane, "JRootPane.titleBarShowIconify", true));
/*  398 */       this.maximizeButton.setVisible((maximizable && !maximized));
/*  399 */       this.restoreButton.setVisible((maximizable && maximized));
/*      */     } else {
/*      */       
/*  402 */       this.iconifyButton.setVisible(false);
/*  403 */       this.maximizeButton.setVisible(false);
/*  404 */       this.restoreButton.setVisible(false);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void updateIcon() {
/*  409 */     boolean defaultShowIcon = this.showIcon;
/*  410 */     if (!this.showIconInDialogs && this.rootPane.getParent() instanceof javax.swing.JDialog) {
/*  411 */       defaultShowIcon = false;
/*      */     }
/*      */     
/*  414 */     List<Image> images = null;
/*  415 */     if (FlatClientProperties.clientPropertyBoolean(this.rootPane, "JRootPane.titleBarShowIcon", defaultShowIcon)) {
/*  416 */       images = this.window.getIconImages();
/*  417 */       if (images.isEmpty())
/*      */       {
/*  419 */         for (Window owner = this.window.getOwner(); owner != null; owner = owner.getOwner()) {
/*  420 */           images = owner.getIconImages();
/*  421 */           if (!images.isEmpty()) {
/*      */             break;
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*  427 */     boolean hasIcon = (images != null && !images.isEmpty());
/*      */ 
/*      */     
/*  430 */     this.iconLabel.setIcon((hasIcon && !this.showIconBesideTitle) ? (Icon)new FlatTitlePaneIcon(images, this.iconSize) : null);
/*  431 */     this.titleLabel.setIcon((hasIcon && this.showIconBesideTitle) ? (Icon)new FlatTitlePaneIcon(images, this.iconSize) : null);
/*      */ 
/*      */     
/*  434 */     this.iconLabel.setVisible((hasIcon && !this.showIconBesideTitle));
/*  435 */     this.leftPanel.setBorder((hasIcon && !this.showIconBesideTitle) ? null : FlatUIUtils.nonUIResource(new FlatEmptyBorder(0, this.noIconLeftGap, 0, 0)));
/*      */     
/*  437 */     updateNativeTitleBarHeightAndHitTestSpotsLater();
/*      */   }
/*      */ 
/*      */   
/*      */   public void addNotify() {
/*  442 */     super.addNotify();
/*      */     
/*  444 */     uninstallWindowListeners();
/*      */     
/*  446 */     this.window = SwingUtilities.getWindowAncestor(this);
/*  447 */     if (this.window != null) {
/*  448 */       frameStateChanged();
/*  449 */       activeChanged(this.window.isActive());
/*  450 */       updateIcon();
/*  451 */       this.titleLabel.setText(getWindowTitle());
/*  452 */       installWindowListeners();
/*      */     } 
/*      */     
/*  455 */     updateNativeTitleBarHeightAndHitTestSpotsLater();
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeNotify() {
/*  460 */     super.removeNotify();
/*      */     
/*  462 */     uninstallWindowListeners();
/*  463 */     this.window = null;
/*      */   }
/*      */   
/*      */   protected String getWindowTitle() {
/*  467 */     if (this.window instanceof Frame)
/*  468 */       return ((Frame)this.window).getTitle(); 
/*  469 */     if (this.window instanceof Dialog)
/*  470 */       return ((Dialog)this.window).getTitle(); 
/*  471 */     return null;
/*      */   }
/*      */   
/*      */   protected void installWindowListeners() {
/*  475 */     if (this.window == null) {
/*      */       return;
/*      */     }
/*  478 */     this.window.addPropertyChangeListener(this.handler);
/*  479 */     this.window.addWindowListener(this.handler);
/*  480 */     this.window.addWindowStateListener(this.handler);
/*  481 */     this.window.addComponentListener(this.handler);
/*      */   }
/*      */   
/*      */   protected void uninstallWindowListeners() {
/*  485 */     if (this.window == null) {
/*      */       return;
/*      */     }
/*  488 */     this.window.removePropertyChangeListener(this.handler);
/*  489 */     this.window.removeWindowListener(this.handler);
/*  490 */     this.window.removeWindowStateListener(this.handler);
/*  491 */     this.window.removeComponentListener(this.handler);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean hasVisibleEmbeddedMenuBar(JMenuBar menuBar) {
/*  498 */     return (menuBar != null && menuBar.isVisible() && isMenuBarEmbedded());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isMenuBarEmbedded() {
/*  506 */     return FlatUIUtils.getBoolean(this.rootPane, "flatlaf.menuBarEmbedded", "JRootPane.menuBarEmbedded", "TitlePane.menuBarEmbedded", false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Rectangle getMenuBarBounds() {
/*  514 */     Insets insets = this.rootPane.getInsets();
/*      */ 
/*      */     
/*  517 */     Rectangle bounds = new Rectangle(SwingUtilities.convertPoint(this.menuBarPlaceholder, -insets.left, -insets.top, this.rootPane), this.menuBarPlaceholder.getSize());
/*      */ 
/*      */ 
/*      */     
/*  521 */     Insets borderInsets = getBorder().getBorderInsets(this);
/*  522 */     bounds.height += borderInsets.bottom;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  529 */     Component horizontalGlue = findHorizontalGlue(this.rootPane.getJMenuBar());
/*  530 */     if (horizontalGlue != null) {
/*  531 */       boolean leftToRight = getComponentOrientation().isLeftToRight();
/*      */ 
/*      */       
/*  534 */       int titleWidth = leftToRight ? (this.buttonPanel.getX() - this.leftPanel.getX() + this.leftPanel.getWidth()) : (this.leftPanel.getX() - this.buttonPanel.getX() + this.buttonPanel.getWidth());
/*  535 */       titleWidth = Math.max(titleWidth, 0);
/*  536 */       bounds.width += titleWidth;
/*  537 */       if (!leftToRight) {
/*  538 */         bounds.x -= titleWidth;
/*      */       }
/*      */     } 
/*  541 */     return bounds;
/*      */   }
/*      */   
/*      */   protected Component findHorizontalGlue(JMenuBar menuBar) {
/*  545 */     if (menuBar == null) {
/*  546 */       return null;
/*      */     }
/*  548 */     int count = menuBar.getComponentCount();
/*  549 */     for (int i = count - 1; i >= 0; i--) {
/*  550 */       Component c = menuBar.getComponent(i);
/*  551 */       if (c instanceof javax.swing.Box.Filler && (c.getMaximumSize()).width >= 32767)
/*  552 */         return c; 
/*      */     } 
/*  554 */     return null;
/*      */   }
/*      */   
/*      */   protected void titleBarColorsChanged() {
/*  558 */     activeChanged((this.window == null || this.window.isActive()));
/*  559 */     repaint();
/*      */   }
/*      */   
/*      */   protected void menuBarChanged() {
/*  563 */     this.menuBarPlaceholder.invalidate();
/*      */ 
/*      */ 
/*      */     
/*  567 */     repaint();
/*      */ 
/*      */     
/*  570 */     EventQueue.invokeLater(() -> activeChanged(
/*  571 */           (this.window == null || this.window.isActive())));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void menuBarLayouted() {
/*  576 */     updateNativeTitleBarHeightAndHitTestSpotsLater();
/*  577 */     doLayout();
/*      */   }
/*      */ 
/*      */   
/*      */   public void paint(Graphics g) {
/*  582 */     super.paint(g);
/*      */     
/*  584 */     if (!UIManager.getBoolean("FlatLaf.debug.titlebar.showRectangles")) {
/*      */       return;
/*      */     }
/*  587 */     if (this.debugTitleBarHeight > 0) {
/*  588 */       g.setColor(Color.green);
/*  589 */       g.drawLine(0, this.debugTitleBarHeight, getWidth(), this.debugTitleBarHeight);
/*      */     } 
/*  591 */     if (this.debugHitTestSpots != null)
/*  592 */       for (Rectangle r : this.debugHitTestSpots) {
/*  593 */         paintRect(g, Color.red, r);
/*      */       } 
/*  595 */     paintRect(g, Color.cyan, this.debugCloseButtonBounds);
/*  596 */     paintRect(g, Color.blue, this.debugAppIconBounds);
/*  597 */     paintRect(g, Color.blue, this.debugMinimizeButtonBounds);
/*  598 */     paintRect(g, Color.magenta, this.debugMaximizeButtonBounds);
/*  599 */     paintRect(g, Color.cyan, this.debugCloseButtonBounds);
/*      */   }
/*      */   
/*      */   private void paintRect(Graphics g, Color color, Rectangle r) {
/*  603 */     if (r == null) {
/*      */       return;
/*      */     }
/*  606 */     g.setColor(color);
/*  607 */     Point offset = SwingUtilities.convertPoint(this, 0, 0, this.window);
/*  608 */     g.drawRect(r.x - offset.x, r.y - offset.y, r.width - 1, r.height - 1);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void paintComponent(Graphics g) {
/*  614 */     g.setColor((UIManager.getBoolean("TitlePane.unifiedBackground") && 
/*  615 */         FlatClientProperties.clientPropertyColor(this.rootPane, "JRootPane.titleBarBackground", null) == null) ? 
/*  616 */         FlatUIUtils.getParentBackground(this) : 
/*  617 */         getBackground());
/*  618 */     g.fillRect(0, 0, getWidth(), getHeight());
/*      */   }
/*      */   
/*      */   protected void repaintWindowBorder() {
/*  622 */     int width = this.rootPane.getWidth();
/*  623 */     int height = this.rootPane.getHeight();
/*  624 */     Insets insets = this.rootPane.getInsets();
/*  625 */     this.rootPane.repaint(0, 0, width, insets.top);
/*  626 */     this.rootPane.repaint(0, 0, insets.left, height);
/*  627 */     this.rootPane.repaint(0, height - insets.bottom, width, insets.bottom);
/*  628 */     this.rootPane.repaint(width - insets.right, 0, insets.right, height);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void iconify() {
/*  635 */     if (!(this.window instanceof Frame)) {
/*      */       return;
/*      */     }
/*  638 */     Frame frame = (Frame)this.window;
/*  639 */     if (!FlatNativeWindowBorder.showWindow(this.window, 6)) {
/*  640 */       frame.setExtendedState(frame.getExtendedState() | 0x1);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isWindowMaximized() {
/*  648 */     return (this.window instanceof Frame && (((Frame)this.window).getExtendedState() & 0x6) == 6);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void maximize() {
/*  655 */     if (!(this.window instanceof Frame)) {
/*      */       return;
/*      */     }
/*  658 */     Frame frame = (Frame)this.window;
/*      */     
/*  660 */     updateMaximizedBounds();
/*      */ 
/*      */     
/*  663 */     this.rootPane.putClientProperty("_flatlaf.maximizedBoundsUpToDate", Boolean.valueOf(true));
/*      */ 
/*      */     
/*  666 */     if (!FlatNativeWindowBorder.showWindow(frame, 3)) {
/*  667 */       int oldState = frame.getExtendedState();
/*  668 */       int newState = oldState | 0x6;
/*      */       
/*  670 */       if (SystemInfo.isLinux)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  684 */         if ((oldState & 0x6) == 4) {
/*  685 */           newState = oldState & 0xFFFFFFF9 | 0x2;
/*      */         }
/*      */       }
/*  688 */       frame.setExtendedState(newState);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void updateMaximizedBounds() {
/*  693 */     Frame frame = (Frame)this.window;
/*      */ 
/*      */ 
/*      */     
/*  697 */     Rectangle oldMaximizedBounds = frame.getMaximizedBounds();
/*  698 */     if (!hasNativeCustomDecoration() && (oldMaximizedBounds == null || 
/*      */       
/*  700 */       Objects.equals(oldMaximizedBounds, this.rootPane.getClientProperty("_flatlaf.maximizedBounds")))) {
/*      */       
/*  702 */       GraphicsConfiguration gc = this.window.getGraphicsConfiguration();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  714 */       Rectangle screenBounds = gc.getBounds();
/*      */       
/*  716 */       int maximizedX = screenBounds.x;
/*  717 */       int maximizedY = screenBounds.y;
/*  718 */       int maximizedWidth = screenBounds.width;
/*  719 */       int maximizedHeight = screenBounds.height;
/*      */       
/*  721 */       if (SystemInfo.isWindows && !isMaximizedBoundsFixed()) {
/*      */         
/*  723 */         maximizedX = 0;
/*  724 */         maximizedY = 0;
/*      */ 
/*      */         
/*  727 */         AffineTransform defaultTransform = gc.getDefaultTransform();
/*  728 */         maximizedWidth = (int)(maximizedWidth * defaultTransform.getScaleX());
/*  729 */         maximizedHeight = (int)(maximizedHeight * defaultTransform.getScaleY());
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  735 */       Insets screenInsets = this.window.getToolkit().getScreenInsets(gc);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  741 */       Rectangle newMaximizedBounds = new Rectangle(maximizedX + screenInsets.left, maximizedY + screenInsets.top, maximizedWidth - screenInsets.left - screenInsets.right, maximizedHeight - screenInsets.top - screenInsets.bottom);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  747 */       if (!Objects.equals(oldMaximizedBounds, newMaximizedBounds)) {
/*      */         
/*  749 */         frame.setMaximizedBounds(newMaximizedBounds);
/*      */ 
/*      */ 
/*      */         
/*  753 */         this.rootPane.putClientProperty("_flatlaf.maximizedBounds", newMaximizedBounds);
/*      */       } 
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
/*      */   private boolean isMaximizedBoundsFixed() {
/*  766 */     return (SystemInfo.isJava_15_orLater || (SystemInfo.javaVersion >= 
/*  767 */       SystemInfo.toVersion(11, 0, 8, 0) && SystemInfo.javaVersion < 
/*  768 */       SystemInfo.toVersion(12, 0, 0, 0)) || (SystemInfo.javaVersion >= 
/*  769 */       SystemInfo.toVersion(13, 0, 4, 0) && SystemInfo.javaVersion < 
/*  770 */       SystemInfo.toVersion(14, 0, 0, 0)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void restore() {
/*  777 */     if (!(this.window instanceof Frame)) {
/*      */       return;
/*      */     }
/*  780 */     Frame frame = (Frame)this.window;
/*  781 */     if (!FlatNativeWindowBorder.showWindow(this.window, 9)) {
/*  782 */       int state = frame.getExtendedState();
/*  783 */       frame.setExtendedState(((state & 0x1) != 0) ? (
/*  784 */           state & 0xFFFFFFFE) : (
/*  785 */           state & 0xFFFFFFF9));
/*      */     } 
/*      */   }
/*      */   
/*      */   private void maximizeOrRestore() {
/*  790 */     if (!(this.window instanceof Frame) || !((Frame)this.window).isResizable()) {
/*      */       return;
/*      */     }
/*  793 */     if (isWindowMaximized()) {
/*  794 */       restore();
/*      */     } else {
/*  796 */       maximize();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void close() {
/*  803 */     if (this.window != null)
/*  804 */       this.window.dispatchEvent(new WindowEvent(this.window, 201)); 
/*      */   }
/*      */   
/*      */   private boolean hasJBRCustomDecoration() {
/*  808 */     return (this.window != null && JBRCustomDecorations.hasCustomDecoration(this.window));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean hasNativeCustomDecoration() {
/*  815 */     return (this.window != null && FlatNativeWindowBorder.hasCustomDecoration(this.window));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateNativeTitleBarHeightAndHitTestSpotsLater() {
/*  822 */     this.laterCounter++;
/*  823 */     EventQueue.invokeLater(() -> {
/*      */           this.laterCounter--;
/*      */           if (this.laterCounter == 0)
/*      */             updateNativeTitleBarHeightAndHitTestSpots(); 
/*      */         });
/*      */   }
/*      */   
/*      */   protected void updateNativeTitleBarHeightAndHitTestSpots() {
/*  831 */     if (!isDisplayable()) {
/*      */       return;
/*      */     }
/*  834 */     if (!hasNativeCustomDecoration()) {
/*      */       return;
/*      */     }
/*  837 */     int titleBarHeight = getHeight();
/*      */     
/*  839 */     if (titleBarHeight > 0) {
/*  840 */       titleBarHeight--;
/*      */     }
/*  842 */     List<Rectangle> hitTestSpots = new ArrayList<>();
/*  843 */     Rectangle appIconBounds = null;
/*      */     
/*  845 */     if (!this.showIconBesideTitle && this.iconLabel.isVisible()) {
/*      */       
/*  847 */       Point location = SwingUtilities.convertPoint(this.iconLabel, 0, 0, this.window);
/*  848 */       Insets iconInsets = this.iconLabel.getInsets();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  853 */       Rectangle iconBounds = new Rectangle(location.x + iconInsets.left - 1, location.y + iconInsets.top - 1, this.iconLabel.getWidth() - iconInsets.left - iconInsets.right + 2, this.iconLabel.getHeight() - iconInsets.top - iconInsets.bottom + 2);
/*      */ 
/*      */ 
/*      */       
/*  857 */       if (isWindowMaximized()) {
/*  858 */         iconBounds.height += iconBounds.y;
/*  859 */         iconBounds.y = 0;
/*      */         
/*  861 */         if (this.window.getComponentOrientation().isLeftToRight()) {
/*  862 */           iconBounds.width += iconBounds.x;
/*  863 */           iconBounds.x = 0;
/*      */         } else {
/*  865 */           iconBounds.width += iconInsets.right;
/*      */         } 
/*      */       } 
/*  868 */       if (hasJBRCustomDecoration())
/*  869 */       { hitTestSpots.add(iconBounds); }
/*      */       else
/*  871 */       { appIconBounds = iconBounds; } 
/*  872 */     } else if (this.showIconBesideTitle && this.titleLabel.getIcon() != null && this.titleLabel.getUI() instanceof FlatTitleLabelUI) {
/*  873 */       FlatTitleLabelUI ui = (FlatTitleLabelUI)this.titleLabel.getUI();
/*      */ 
/*      */       
/*  876 */       Insets insets = this.titleLabel.getInsets();
/*      */ 
/*      */       
/*  879 */       Rectangle viewR = new Rectangle(insets.left, insets.top, this.titleLabel.getWidth() - insets.left - insets.right, this.titleLabel.getHeight() - insets.top - insets.bottom);
/*  880 */       Rectangle iconR = new Rectangle();
/*  881 */       Rectangle textR = new Rectangle();
/*  882 */       ui.layoutCL(this.titleLabel, this.titleLabel.getFontMetrics(this.titleLabel.getFont()), this.titleLabel
/*  883 */           .getText(), this.titleLabel.getIcon(), viewR, iconR, textR);
/*      */ 
/*      */ 
/*      */       
/*  887 */       if (iconR.x == 0) {
/*      */         
/*  889 */         Point location = SwingUtilities.convertPoint(this.titleLabel, 0, 0, this.window);
/*  890 */         iconR.x += location.x;
/*  891 */         iconR.y += location.y;
/*      */ 
/*      */         
/*  894 */         iconR.x--;
/*  895 */         iconR.y--;
/*  896 */         iconR.width += 2;
/*  897 */         iconR.height += 2;
/*      */         
/*  899 */         if (hasJBRCustomDecoration()) {
/*  900 */           hitTestSpots.add(iconR);
/*      */         } else {
/*  902 */           appIconBounds = iconR;
/*      */         } 
/*      */       } 
/*      */     } 
/*  906 */     Rectangle r = getNativeHitTestSpot(this.buttonPanel);
/*  907 */     if (r != null) {
/*  908 */       hitTestSpots.add(r);
/*      */     }
/*  910 */     JMenuBar menuBar = this.rootPane.getJMenuBar();
/*  911 */     if (hasVisibleEmbeddedMenuBar(menuBar)) {
/*  912 */       r = getNativeHitTestSpot(menuBar);
/*  913 */       if (r != null) {
/*      */ 
/*      */         
/*  916 */         if (this.window instanceof Frame && ((Frame)this.window).isResizable() && !isWindowMaximized()) {
/*      */           
/*  918 */           int resizeHeight = UIScale.scale(Math.min(this.menuBarResizeHeight, 8));
/*  919 */           r.y += resizeHeight;
/*  920 */           r.height -= resizeHeight;
/*      */         } 
/*      */         
/*  923 */         int count = menuBar.getComponentCount();
/*  924 */         for (int i = count - 1; i >= 0; i--) {
/*  925 */           Component c = menuBar.getComponent(i);
/*  926 */           if (c instanceof javax.swing.Box.Filler || (c instanceof JComponent && 
/*  927 */             FlatClientProperties.clientPropertyBoolean((JComponent)c, "JComponent.titleBarCaption", false))) {
/*      */             Rectangle r2;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  933 */             Point glueLocation = SwingUtilities.convertPoint(c, 0, 0, this.window);
/*  934 */             int x2 = glueLocation.x + c.getWidth();
/*      */             
/*  936 */             if (getComponentOrientation().isLeftToRight()) {
/*  937 */               r2 = new Rectangle(x2, r.y, r.x + r.width - x2, r.height);
/*      */               
/*  939 */               r.width = glueLocation.x - r.x;
/*      */             } else {
/*  941 */               r2 = new Rectangle(r.x, r.y, glueLocation.x - r.x, r.height);
/*      */               
/*  943 */               r.width = r.x + r.width - x2;
/*  944 */               r.x = x2;
/*      */             } 
/*  946 */             if (r2.width > 0) {
/*  947 */               hitTestSpots.add(r2);
/*      */             }
/*      */           } 
/*      */         } 
/*  951 */         hitTestSpots.add(r);
/*      */       } 
/*      */     } 
/*      */     
/*  955 */     Rectangle minimizeButtonBounds = boundsInWindow(this.iconifyButton);
/*  956 */     Rectangle maximizeButtonBounds = boundsInWindow(this.maximizeButton.isVisible() ? this.maximizeButton : this.restoreButton);
/*  957 */     Rectangle closeButtonBounds = boundsInWindow(this.closeButton);
/*      */     
/*  959 */     FlatNativeWindowBorder.setTitleBarHeightAndHitTestSpots(this.window, titleBarHeight, hitTestSpots, appIconBounds, minimizeButtonBounds, maximizeButtonBounds, closeButtonBounds);
/*      */ 
/*      */     
/*  962 */     this.debugTitleBarHeight = titleBarHeight;
/*  963 */     this.debugHitTestSpots = hitTestSpots;
/*  964 */     this.debugAppIconBounds = appIconBounds;
/*  965 */     this.debugMinimizeButtonBounds = minimizeButtonBounds;
/*  966 */     this.debugMaximizeButtonBounds = maximizeButtonBounds;
/*  967 */     this.debugCloseButtonBounds = closeButtonBounds;
/*  968 */     if (UIManager.getBoolean("FlatLaf.debug.titlebar.showRectangles"))
/*  969 */       repaint(); 
/*      */   }
/*      */   
/*      */   private Rectangle boundsInWindow(JComponent c) {
/*  973 */     return c.isShowing() ? 
/*  974 */       SwingUtilities.convertRectangle(c.getParent(), c.getBounds(), this.window) : 
/*  975 */       null;
/*      */   }
/*      */   
/*      */   protected Rectangle getNativeHitTestSpot(JComponent c) {
/*  979 */     Dimension size = c.getSize();
/*  980 */     if (size.width <= 0 || size.height <= 0) {
/*  981 */       return null;
/*      */     }
/*  983 */     Point location = SwingUtilities.convertPoint(c, 0, 0, this.window);
/*  984 */     Rectangle r = new Rectangle(location, size);
/*  985 */     return r;
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
/*      */   protected class FlatTitlePaneBorder
/*      */     extends AbstractBorder
/*      */   {
/*      */     public Insets getBorderInsets(Component c, Insets insets) {
/* 1002 */       super.getBorderInsets(c, insets);
/*      */       
/* 1004 */       Border menuBarBorder = getMenuBarBorder();
/* 1005 */       if (menuBarBorder != null) {
/*      */         
/* 1007 */         Insets menuBarInsets = menuBarBorder.getBorderInsets(c);
/* 1008 */         insets.bottom += menuBarInsets.bottom;
/* 1009 */       } else if (FlatTitlePane.this.borderColor != null && (FlatTitlePane.this.rootPane.getJMenuBar() == null || !FlatTitlePane.this.rootPane.getJMenuBar().isVisible())) {
/* 1010 */         insets.bottom += UIScale.scale(1);
/*      */       } 
/* 1012 */       if (!SystemInfo.isWindows_11_orLater && FlatTitlePane.this.hasNativeCustomDecoration() && !FlatTitlePane.this.isWindowMaximized()) {
/* 1013 */         insets = FlatUIUtils.addInsets(insets, FlatNativeWindowBorder.WindowTopBorder.getInstance().getBorderInsets());
/*      */       }
/* 1015 */       return insets;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 1021 */       Border menuBarBorder = getMenuBarBorder();
/* 1022 */       if (menuBarBorder != null) {
/*      */         
/* 1024 */         menuBarBorder.paintBorder(FlatTitlePane.this.rootPane.getJMenuBar(), g, x, y, width, height);
/* 1025 */       } else if (FlatTitlePane.this.borderColor != null && (FlatTitlePane.this.rootPane.getJMenuBar() == null || !FlatTitlePane.this.rootPane.getJMenuBar().isVisible())) {
/*      */         
/* 1027 */         float lineHeight = UIScale.scale(1.0F);
/* 1028 */         FlatUIUtils.paintFilledRectangle(g, FlatTitlePane.this.borderColor, x, (y + height) - lineHeight, width, lineHeight);
/*      */       } 
/*      */       
/* 1031 */       if (!SystemInfo.isWindows_11_orLater && FlatTitlePane.this.hasNativeCustomDecoration() && !FlatTitlePane.this.isWindowMaximized())
/* 1032 */         FlatNativeWindowBorder.WindowTopBorder.getInstance().paintBorder(c, g, x, y, width, height); 
/*      */     }
/*      */     
/*      */     protected Border getMenuBarBorder() {
/* 1036 */       JMenuBar menuBar = FlatTitlePane.this.rootPane.getJMenuBar();
/* 1037 */       return FlatTitlePane.this.hasVisibleEmbeddedMenuBar(menuBar) ? menuBar.getBorder() : null;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class FlatTitleLabelUI
/*      */     extends FlatLabelUI
/*      */   {
/*      */     protected FlatTitleLabelUI() {
/* 1048 */       super(false);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void installDefaults(JLabel c) {
/* 1053 */       super.installDefaults(c);
/*      */       
/* 1055 */       if (FlatTitlePane.this.titleFont != null) {
/* 1056 */         c.setFont(FlatTitlePane.this.titleFont);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected String layoutCL(JLabel label, FontMetrics fontMetrics, String text, Icon icon, Rectangle viewR, Rectangle iconR, Rectangle textR) {
/* 1063 */       JMenuBar menuBar = FlatTitlePane.this.rootPane.getJMenuBar();
/* 1064 */       boolean hasEmbeddedMenuBar = FlatTitlePane.this.hasVisibleEmbeddedMenuBar(menuBar);
/* 1065 */       boolean hasEmbeddedLeadingMenus = (hasEmbeddedMenuBar && hasLeadingMenus(menuBar));
/* 1066 */       boolean leftToRight = FlatTitlePane.this.getComponentOrientation().isLeftToRight();
/*      */       
/* 1068 */       if (hasEmbeddedMenuBar) {
/* 1069 */         int minGap = UIScale.scale(FlatTitlePane.this.menuBarTitleMinimumGap);
/*      */ 
/*      */         
/* 1072 */         if (hasEmbeddedLeadingMenus) {
/* 1073 */           if (leftToRight)
/* 1074 */             viewR.x += minGap; 
/* 1075 */           viewR.width -= minGap;
/*      */         } 
/*      */ 
/*      */         
/* 1079 */         Component horizontalGlue = FlatTitlePane.this.findHorizontalGlue(menuBar);
/* 1080 */         if (horizontalGlue != null && menuBar.getComponent(menuBar.getComponentCount() - 1) != horizontalGlue) {
/* 1081 */           if (!leftToRight)
/* 1082 */             viewR.x += minGap; 
/* 1083 */           viewR.width -= minGap;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1088 */       int iconTextGap = 0;
/* 1089 */       int iconWidthAndGap = 0;
/* 1090 */       if (icon != null) {
/* 1091 */         Insets iconInsets = FlatTitlePane.this.iconLabel.getInsets();
/* 1092 */         iconTextGap = leftToRight ? iconInsets.right : iconInsets.left;
/* 1093 */         iconWidthAndGap = icon.getIconWidth() + iconTextGap;
/*      */       } 
/*      */ 
/*      */       
/* 1097 */       String clippedText = SwingUtilities.layoutCompoundLabel(label, fontMetrics, text, icon, label
/* 1098 */           .getVerticalAlignment(), label.getHorizontalAlignment(), label
/* 1099 */           .getVerticalTextPosition(), label.getHorizontalTextPosition(), viewR, iconR, textR, iconTextGap);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1104 */       if (!clippedText.equals(text)) {
/*      */         
/* 1106 */         textR
/*      */           
/* 1108 */           .x = leftToRight ? (viewR.x + iconWidthAndGap) : (viewR.x + viewR.width - iconWidthAndGap - textR.width);
/*      */       } else {
/* 1110 */         int leadingGap = hasEmbeddedLeadingMenus ? UIScale.scale(FlatTitlePane.this.menuBarTitleGap - FlatTitlePane.this.menuBarTitleMinimumGap) : 0;
/*      */         
/* 1112 */         boolean center = hasEmbeddedLeadingMenus ? FlatTitlePane.this.centerTitleIfMenuBarEmbedded : FlatTitlePane.this.centerTitle;
/* 1113 */         if (center) {
/*      */ 
/*      */           
/* 1116 */           Container parent = label.getParent();
/* 1117 */           int centeredTextX = (parent != null) ? ((parent.getWidth() - textR.width - iconWidthAndGap) / 2 + iconWidthAndGap - label.getX()) : -1;
/* 1118 */           textR
/*      */             
/* 1120 */             .x = (centeredTextX >= viewR.x + leadingGap && centeredTextX + textR.width <= viewR.x + viewR.width - leadingGap) ? centeredTextX : (viewR.x + (viewR.width - textR.width - iconWidthAndGap) / 2 + iconWidthAndGap);
/*      */         } else {
/*      */           
/* 1123 */           textR
/*      */             
/* 1125 */             .x = leftToRight ? Math.min(viewR.x + leadingGap + iconWidthAndGap, viewR.x + viewR.width - textR.width) : Math.max(viewR.x + viewR.width - leadingGap - iconWidthAndGap - textR.width, viewR.x);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1130 */       if (icon != null) {
/* 1131 */         iconR
/*      */           
/* 1133 */           .x = leftToRight ? (textR.x - iconWidthAndGap) : (textR.x + textR.width + iconTextGap);
/*      */       }
/*      */       
/* 1136 */       return clippedText;
/*      */     }
/*      */ 
/*      */     
/*      */     private boolean hasLeadingMenus(JMenuBar menuBar) {
/* 1141 */       if (menuBar.getComponentCount() == 0 || menuBar.getWidth() == 0) {
/* 1142 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 1146 */       Component horizontalGlue = FlatTitlePane.this.findHorizontalGlue(menuBar);
/* 1147 */       if (horizontalGlue != null) {
/* 1148 */         boolean leftToRight = FlatTitlePane.this.getComponentOrientation().isLeftToRight();
/* 1149 */         if ((leftToRight && horizontalGlue.getX() == 0) || (!leftToRight && horizontalGlue
/* 1150 */           .getX() + horizontalGlue.getWidth() == menuBar.getWidth())) {
/* 1151 */           return false;
/*      */         }
/*      */       } 
/* 1154 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected class Handler
/*      */     extends WindowAdapter
/*      */     implements PropertyChangeListener, MouseListener, MouseMotionListener, ComponentListener
/*      */   {
/*      */     private Point dragOffset;
/*      */     private boolean linuxNativeMove;
/*      */     private long lastSingleClickWhen;
/*      */     
/*      */     public void propertyChange(PropertyChangeEvent e) {
/* 1168 */       switch (e.getPropertyName()) {
/*      */         case "title":
/* 1170 */           FlatTitlePane.this.titleLabel.setText(FlatTitlePane.this.getWindowTitle());
/*      */           break;
/*      */         
/*      */         case "resizable":
/* 1174 */           if (FlatTitlePane.this.window instanceof Frame) {
/* 1175 */             FlatTitlePane.this.frameStateChanged();
/*      */           }
/*      */           break;
/*      */         case "iconImage":
/* 1179 */           FlatTitlePane.this.updateIcon();
/*      */           break;
/*      */         
/*      */         case "componentOrientation":
/* 1183 */           FlatTitlePane.this.updateNativeTitleBarHeightAndHitTestSpotsLater();
/*      */           break;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void windowActivated(WindowEvent e) {
/* 1192 */       FlatTitlePane.this.activeChanged(true);
/* 1193 */       FlatTitlePane.this.updateNativeTitleBarHeightAndHitTestSpots();
/*      */       
/* 1195 */       if (!SystemInfo.isWindows_11_orLater && FlatTitlePane.this.hasNativeCustomDecoration()) {
/* 1196 */         FlatNativeWindowBorder.WindowTopBorder.getInstance().repaintBorder(FlatTitlePane.this);
/*      */       }
/* 1198 */       FlatTitlePane.this.repaintWindowBorder();
/*      */     }
/*      */ 
/*      */     
/*      */     public void windowDeactivated(WindowEvent e) {
/* 1203 */       FlatTitlePane.this.activeChanged(false);
/* 1204 */       FlatTitlePane.this.updateNativeTitleBarHeightAndHitTestSpots();
/*      */       
/* 1206 */       if (!SystemInfo.isWindows_11_orLater && FlatTitlePane.this.hasNativeCustomDecoration()) {
/* 1207 */         FlatNativeWindowBorder.WindowTopBorder.getInstance().repaintBorder(FlatTitlePane.this);
/*      */       }
/* 1209 */       FlatTitlePane.this.repaintWindowBorder();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void windowStateChanged(WindowEvent e) {
/* 1221 */       FlatTitlePane.this.frameStateChanged();
/* 1222 */       FlatTitlePane.this.updateNativeTitleBarHeightAndHitTestSpots();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void mouseClicked(MouseEvent e) {
/* 1236 */       if (this.linuxNativeMove && SystemInfo.isLinux && FlatNativeLinuxLibrary.isWMUtilsSupported(FlatTitlePane.this.window)) {
/*      */         
/* 1238 */         if (this.lastSingleClickWhen != 0L && e.getWhen() - this.lastSingleClickWhen <= getMultiClickInterval()) {
/* 1239 */           this.lastSingleClickWhen = 0L;
/* 1240 */           FlatTitlePane.this.maximizeOrRestore();
/*      */         } 
/*      */         
/*      */         return;
/*      */       } 
/* 1245 */       if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
/* 1246 */         if (e.getSource() == FlatTitlePane.this.iconLabel) {
/*      */           
/* 1248 */           FlatTitlePane.this.close();
/* 1249 */         } else if (!FlatTitlePane.this.hasNativeCustomDecoration()) {
/*      */           
/* 1251 */           FlatTitlePane.this.maximizeOrRestore();
/*      */         } 
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public void mousePressed(MouseEvent e) {
/* 1258 */       if (FlatTitlePane.this.window == null) {
/*      */         return;
/*      */       }
/*      */       
/* 1262 */       if (SwingUtilities.isRightMouseButton(e) && SystemInfo.isLinux && 
/* 1263 */         FlatNativeLinuxLibrary.isWMUtilsSupported(FlatTitlePane.this.window)) {
/*      */         
/* 1265 */         e.consume();
/* 1266 */         FlatNativeLinuxLibrary.showWindowMenu(FlatTitlePane.this.window, e);
/*      */         
/*      */         return;
/*      */       } 
/* 1270 */       if (!SwingUtilities.isLeftMouseButton(e)) {
/*      */         return;
/*      */       }
/* 1273 */       this.dragOffset = SwingUtilities.convertPoint(FlatTitlePane.this, e.getPoint(), FlatTitlePane.this.window);
/* 1274 */       this.linuxNativeMove = false;
/*      */ 
/*      */       
/* 1277 */       if (SystemInfo.isLinux && FlatNativeLinuxLibrary.isWMUtilsSupported(FlatTitlePane.this.window)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1286 */         int clickCount = e.getClickCount();
/* 1287 */         if (clickCount == 1 && this.lastSingleClickWhen != 0L && e.getWhen() - this.lastSingleClickWhen <= getMultiClickInterval()) {
/* 1288 */           clickCount = 2;
/*      */         }
/* 1290 */         switch (clickCount) {
/*      */           
/*      */           case 1:
/* 1293 */             e.consume();
/* 1294 */             this.linuxNativeMove = FlatNativeLinuxLibrary.moveOrResizeWindow(FlatTitlePane.this.window, e, 8);
/* 1295 */             this.lastSingleClickWhen = e.getWhen();
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 2:
/* 1301 */             this.lastSingleClickWhen = 0L;
/* 1302 */             FlatTitlePane.this.maximizeOrRestore();
/*      */             break;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     private int getMultiClickInterval() {
/* 1309 */       Object value = Toolkit.getDefaultToolkit().getDesktopProperty("awt.multiClickInterval");
/* 1310 */       return (value instanceof Integer) ? ((Integer)value).intValue() : 500;
/*      */     }
/*      */ 
/*      */     
/*      */     public void mouseReleased(MouseEvent e) {}
/*      */     
/*      */     public void mouseEntered(MouseEvent e) {}
/*      */     
/*      */     public void mouseExited(MouseEvent e) {}
/*      */     
/*      */     public void mouseDragged(MouseEvent e) {
/* 1321 */       if (FlatTitlePane.this.window == null || this.dragOffset == null) {
/*      */         return;
/*      */       }
/* 1324 */       if (this.linuxNativeMove) {
/*      */         return;
/*      */       }
/* 1327 */       if (!SwingUtilities.isLeftMouseButton(e)) {
/*      */         return;
/*      */       }
/* 1330 */       if (FlatTitlePane.this.hasNativeCustomDecoration()) {
/*      */         return;
/*      */       }
/*      */       
/* 1334 */       if (FlatTitlePane.this.window instanceof Frame) {
/* 1335 */         Frame frame = (Frame)FlatTitlePane.this.window;
/* 1336 */         int state = frame.getExtendedState();
/* 1337 */         if ((state & 0x6) != 0) {
/* 1338 */           int maximizedWidth = FlatTitlePane.this.window.getWidth();
/*      */ 
/*      */           
/* 1341 */           frame.setExtendedState(state & 0xFFFFFFF9);
/*      */ 
/*      */ 
/*      */           
/* 1345 */           int restoredWidth = FlatTitlePane.this.window.getWidth();
/* 1346 */           int center = restoredWidth / 2;
/* 1347 */           if (this.dragOffset.x > center)
/*      */           {
/* 1349 */             if (this.dragOffset.x > maximizedWidth - center) {
/* 1350 */               this.dragOffset.x = restoredWidth - maximizedWidth - this.dragOffset.x;
/*      */             } else {
/* 1352 */               this.dragOffset.x = center;
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 1358 */       int newX = e.getXOnScreen() - this.dragOffset.x;
/* 1359 */       int newY = e.getYOnScreen() - this.dragOffset.y;
/*      */       
/* 1361 */       if (newX == FlatTitlePane.this.window.getX() && newY == FlatTitlePane.this.window.getY()) {
/*      */         return;
/*      */       }
/*      */       
/* 1365 */       FlatTitlePane.this.window.setLocation(newX, newY);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void mouseMoved(MouseEvent e) {}
/*      */ 
/*      */     
/*      */     public void componentResized(ComponentEvent e) {
/* 1374 */       FlatTitlePane.this.updateNativeTitleBarHeightAndHitTestSpotsLater();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void componentShown(ComponentEvent e) {
/* 1380 */       FlatTitlePane.this.frameStateChanged();
/*      */     }
/*      */     
/*      */     public void componentMoved(ComponentEvent e) {}
/*      */     
/*      */     public void componentHidden(ComponentEvent e) {}
/*      */   }
/*      */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatTitlePane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */