/*      */ package com.formdev.flatlaf.ui;
/*      */ 
/*      */ import com.formdev.flatlaf.FlatClientProperties;
/*      */ import com.formdev.flatlaf.FlatLaf;
/*      */ import com.formdev.flatlaf.icons.FlatTabbedPaneCloseIcon;
/*      */ import com.formdev.flatlaf.util.Animator;
/*      */ import com.formdev.flatlaf.util.CubicBezierEasing;
/*      */ import com.formdev.flatlaf.util.JavaCompatibility;
/*      */ import com.formdev.flatlaf.util.LoggingFacade;
/*      */ import com.formdev.flatlaf.util.StringUtils;
/*      */ import com.formdev.flatlaf.util.UIScale;
/*      */ import java.awt.AWTKeyStroke;
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.EventQueue;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Insets;
/*      */ import java.awt.KeyboardFocusManager;
/*      */ import java.awt.LayoutManager;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Shape;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.ComponentEvent;
/*      */ import java.awt.event.ComponentListener;
/*      */ import java.awt.event.ContainerEvent;
/*      */ import java.awt.event.ContainerListener;
/*      */ import java.awt.event.FocusEvent;
/*      */ import java.awt.event.FocusListener;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseListener;
/*      */ import java.awt.event.MouseMotionListener;
/*      */ import java.awt.event.MouseWheelEvent;
/*      */ import java.awt.geom.Path2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.util.Collections;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.function.BiConsumer;
/*      */ import java.util.function.IntConsumer;
/*      */ import java.util.function.Predicate;
/*      */ import javax.accessibility.Accessible;
/*      */ import javax.accessibility.AccessibleContext;
/*      */ import javax.swing.Action;
/*      */ import javax.swing.ActionMap;
/*      */ import javax.swing.ButtonModel;
/*      */ import javax.swing.Icon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JPopupMenu;
/*      */ import javax.swing.JTabbedPane;
/*      */ import javax.swing.JViewport;
/*      */ import javax.swing.KeyStroke;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.Timer;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.event.ChangeEvent;
/*      */ import javax.swing.event.ChangeListener;
/*      */ import javax.swing.event.PopupMenuEvent;
/*      */ import javax.swing.event.PopupMenuListener;
/*      */ import javax.swing.plaf.ComponentUI;
/*      */ import javax.swing.plaf.TabbedPaneUI;
/*      */ import javax.swing.plaf.UIResource;
/*      */ import javax.swing.plaf.basic.BasicTabbedPaneUI;
/*      */ import javax.swing.text.JTextComponent;
/*      */ import javax.swing.text.View;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class FlatTabbedPaneUI
/*      */   extends BasicTabbedPaneUI
/*      */   implements FlatStylingSupport.StyleableUI
/*      */ {
/*      */   protected static final int TAB_TYPE_UNDERLINED = 0;
/*      */   protected static final int TAB_TYPE_CARD = 1;
/*      */   protected static final int NEVER = 0;
/*      */   protected static final int AS_NEEDED = 2;
/*      */   protected static final int AS_NEEDED_SINGLE = 3;
/*      */   protected static final int BOTH = 100;
/*      */   protected static final int FILL = 100;
/*      */   protected static final int WIDTH_MODE_PREFERRED = 0;
/*      */   protected static final int WIDTH_MODE_EQUAL = 1;
/*      */   protected static final int WIDTH_MODE_COMPACT = 2;
/*      */   private static Set<KeyStroke> focusForwardTraversalKeys;
/*      */   private static Set<KeyStroke> focusBackwardTraversalKeys;
/*      */   protected Color foreground;
/*      */   @Styleable
/*      */   protected Color disabledForeground;
/*      */   @Styleable
/*      */   protected Color selectedBackground;
/*      */   @Styleable
/*      */   protected Color selectedForeground;
/*      */   @Styleable
/*      */   protected Color underlineColor;
/*      */   @Styleable
/*      */   protected Color inactiveUnderlineColor;
/*      */   @Styleable
/*      */   protected Color disabledUnderlineColor;
/*      */   @Styleable
/*      */   protected Color hoverColor;
/*      */   @Styleable
/*      */   protected Color hoverForeground;
/*      */   @Styleable
/*      */   protected Color focusColor;
/*      */   @Styleable
/*      */   protected Color focusForeground;
/*      */   @Styleable
/*      */   protected Color tabSeparatorColor;
/*      */   @Styleable
/*      */   protected Color contentAreaColor;
/*      */   private int textIconGapUnscaled;
/*      */   @Styleable
/*      */   protected int minimumTabWidth;
/*      */   @Styleable
/*      */   protected int maximumTabWidth;
/*      */   @Styleable
/*      */   protected int tabHeight;
/*      */   @Styleable
/*      */   protected int tabSelectionHeight;
/*      */   @Styleable
/*      */   protected int cardTabSelectionHeight;
/*      */   @Styleable
/*      */   protected int contentSeparatorHeight;
/*      */   @Styleable
/*      */   protected boolean showTabSeparators;
/*      */   @Styleable
/*      */   protected boolean tabSeparatorsFullHeight;
/*      */   @Styleable
/*      */   protected boolean hasFullBorder;
/*      */   @Styleable
/*      */   protected boolean tabsOpaque = true;
/*      */   @Styleable
/*      */   protected boolean rotateTabRuns = true;
/*      */   @Styleable(type = String.class)
/*      */   private int tabType;
/*      */   @Styleable(type = String.class)
/*      */   private int tabsPopupPolicy;
/*      */   @Styleable(type = String.class)
/*      */   private int scrollButtonsPolicy;
/*      */   @Styleable(type = String.class)
/*      */   private int scrollButtonsPlacement;
/*      */   @Styleable(type = String.class)
/*      */   private int tabAreaAlignment;
/*      */   @Styleable(type = String.class)
/*      */   private int tabAlignment;
/*      */   @Styleable(type = String.class)
/*      */   private int tabWidthMode;
/*      */   protected Icon closeIcon;
/*      */   @Styleable
/*      */   protected String arrowType;
/*      */   @Styleable
/*      */   protected Insets buttonInsets;
/*      */   @Styleable
/*      */   protected int buttonArc;
/*      */   @Styleable
/*      */   protected Color buttonHoverBackground;
/*      */   @Styleable
/*      */   protected Color buttonPressedBackground;
/*      */   @Styleable
/*      */   protected String moreTabsButtonToolTipText;
/*      */   @Styleable
/*      */   protected String tabCloseToolTipText;
/*      */   @Styleable
/*      */   protected boolean showContentSeparator = true;
/*      */   @Styleable
/*      */   protected boolean hideTabAreaWithOneTab;
/*      */   @Styleable
/*      */   protected boolean tabClosable;
/*      */   @Styleable
/*  252 */   protected int tabIconPlacement = 10;
/*      */   
/*      */   protected JViewport tabViewport;
/*      */   
/*      */   protected FlatWheelTabScroller wheelTabScroller;
/*      */   
/*      */   private JButton tabCloseButton;
/*      */   
/*      */   private JButton moreTabsButton;
/*      */   private Container leadingComponent;
/*      */   private Container trailingComponent;
/*      */   private Dimension scrollBackwardButtonPrefSize;
/*      */   private Handler handler;
/*      */   private boolean blockRollover;
/*      */   private boolean rolloverTabClose;
/*      */   private boolean pressedTabClose;
/*      */   private Object[] oldRenderingHints;
/*      */   private Map<String, Object> oldStyleValues;
/*      */   private boolean closeIconShared = true;
/*      */   private boolean inCalculateEqual;
/*      */   
/*      */   public static ComponentUI createUI(JComponent c) {
/*  274 */     return new FlatTabbedPaneUI();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void installUI(JComponent c) {
/*  280 */     String tabLayoutPolicyStr = UIManager.getString("TabbedPane.tabLayoutPolicy");
/*  281 */     if (tabLayoutPolicyStr != null) {
/*      */       int tabLayoutPolicy;
/*  283 */       switch (tabLayoutPolicyStr) {
/*      */         default:
/*  285 */           tabLayoutPolicy = 0; break;
/*  286 */         case "scroll": tabLayoutPolicy = 1; break;
/*      */       } 
/*  288 */       ((JTabbedPane)c).setTabLayoutPolicy(tabLayoutPolicy);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  293 */     this.arrowType = UIManager.getString("TabbedPane.arrowType");
/*  294 */     this.foreground = UIManager.getColor("TabbedPane.foreground");
/*  295 */     this.disabledForeground = UIManager.getColor("TabbedPane.disabledForeground");
/*  296 */     this.buttonHoverBackground = UIManager.getColor("TabbedPane.buttonHoverBackground");
/*  297 */     this.buttonPressedBackground = UIManager.getColor("TabbedPane.buttonPressedBackground");
/*      */     
/*  299 */     super.installUI(c);
/*      */     
/*  301 */     FlatSelectedTabRepainter.install();
/*  302 */     installStyle();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void installDefaults() {
/*  307 */     if (UIManager.getBoolean("TabbedPane.tabsOverlapBorder")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  323 */       Object oldValue = UIManager.put("TabbedPane.tabsOverlapBorder", Boolean.valueOf(false));
/*  324 */       super.installDefaults();
/*  325 */       UIManager.put("TabbedPane.tabsOverlapBorder", oldValue);
/*      */     } else {
/*  327 */       super.installDefaults();
/*      */     } 
/*  329 */     this.selectedBackground = UIManager.getColor("TabbedPane.selectedBackground");
/*  330 */     this.selectedForeground = UIManager.getColor("TabbedPane.selectedForeground");
/*  331 */     this.underlineColor = UIManager.getColor("TabbedPane.underlineColor");
/*  332 */     this.inactiveUnderlineColor = FlatUIUtils.getUIColor("TabbedPane.inactiveUnderlineColor", this.underlineColor);
/*  333 */     this.disabledUnderlineColor = UIManager.getColor("TabbedPane.disabledUnderlineColor");
/*  334 */     this.hoverColor = UIManager.getColor("TabbedPane.hoverColor");
/*  335 */     this.hoverForeground = UIManager.getColor("TabbedPane.hoverForeground");
/*  336 */     this.focusColor = UIManager.getColor("TabbedPane.focusColor");
/*  337 */     this.focusForeground = UIManager.getColor("TabbedPane.focusForeground");
/*  338 */     this.tabSeparatorColor = UIManager.getColor("TabbedPane.tabSeparatorColor");
/*  339 */     this.contentAreaColor = UIManager.getColor("TabbedPane.contentAreaColor");
/*      */     
/*  341 */     this.textIconGapUnscaled = UIManager.getInt("TabbedPane.textIconGap");
/*  342 */     this.minimumTabWidth = UIManager.getInt("TabbedPane.minimumTabWidth");
/*  343 */     this.maximumTabWidth = UIManager.getInt("TabbedPane.maximumTabWidth");
/*  344 */     this.tabHeight = UIManager.getInt("TabbedPane.tabHeight");
/*  345 */     this.tabSelectionHeight = UIManager.getInt("TabbedPane.tabSelectionHeight");
/*  346 */     this.cardTabSelectionHeight = UIManager.getInt("TabbedPane.cardTabSelectionHeight");
/*  347 */     this.contentSeparatorHeight = UIManager.getInt("TabbedPane.contentSeparatorHeight");
/*  348 */     this.showTabSeparators = UIManager.getBoolean("TabbedPane.showTabSeparators");
/*  349 */     this.tabSeparatorsFullHeight = UIManager.getBoolean("TabbedPane.tabSeparatorsFullHeight");
/*  350 */     this.hasFullBorder = UIManager.getBoolean("TabbedPane.hasFullBorder");
/*  351 */     this.tabsOpaque = UIManager.getBoolean("TabbedPane.tabsOpaque");
/*  352 */     this.rotateTabRuns = FlatUIUtils.getUIBoolean("TabbedPane.rotateTabRuns", true);
/*      */     
/*  354 */     this.tabType = parseTabType(UIManager.getString("TabbedPane.tabType"));
/*  355 */     this.tabsPopupPolicy = parseTabsPopupPolicy(UIManager.getString("TabbedPane.tabsPopupPolicy"));
/*  356 */     this.scrollButtonsPolicy = parseScrollButtonsPolicy(UIManager.getString("TabbedPane.scrollButtonsPolicy"));
/*  357 */     this.scrollButtonsPlacement = parseScrollButtonsPlacement(UIManager.getString("TabbedPane.scrollButtonsPlacement"));
/*      */     
/*  359 */     this.tabAreaAlignment = parseAlignment(UIManager.getString("TabbedPane.tabAreaAlignment"), 10);
/*  360 */     this.tabAlignment = parseAlignment(UIManager.getString("TabbedPane.tabAlignment"), 0);
/*  361 */     this.tabWidthMode = parseTabWidthMode(UIManager.getString("TabbedPane.tabWidthMode"));
/*  362 */     this.closeIcon = UIManager.getIcon("TabbedPane.closeIcon");
/*  363 */     this.closeIconShared = true;
/*      */     
/*  365 */     this.buttonInsets = UIManager.getInsets("TabbedPane.buttonInsets");
/*  366 */     this.buttonArc = UIManager.getInt("TabbedPane.buttonArc");
/*      */     
/*  368 */     Locale l = this.tabPane.getLocale();
/*  369 */     this.moreTabsButtonToolTipText = UIManager.getString("TabbedPane.moreTabsButtonToolTipText", l);
/*  370 */     this.tabCloseToolTipText = UIManager.getString("TabbedPane.tabCloseToolTipText", l);
/*      */ 
/*      */     
/*  373 */     this.textIconGap = UIScale.scale(this.textIconGapUnscaled);
/*      */ 
/*      */ 
/*      */     
/*  377 */     if (focusForwardTraversalKeys == null) {
/*  378 */       focusForwardTraversalKeys = Collections.singleton(KeyStroke.getKeyStroke(9, 0));
/*  379 */       focusBackwardTraversalKeys = Collections.singleton(KeyStroke.getKeyStroke(9, 64));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  384 */     this.tabPane.setFocusTraversalKeys(0, (Set)focusForwardTraversalKeys);
/*  385 */     this.tabPane.setFocusTraversalKeys(1, (Set)focusBackwardTraversalKeys);
/*      */     
/*  387 */     MigLayoutVisualPadding.install(this.tabPane, null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void uninstallDefaults() {
/*  393 */     this.tabPane.setFocusTraversalKeys(0, (Set<? extends AWTKeyStroke>)null);
/*  394 */     this.tabPane.setFocusTraversalKeys(1, (Set<? extends AWTKeyStroke>)null);
/*      */     
/*  396 */     super.uninstallDefaults();
/*      */     
/*  398 */     this.foreground = null;
/*  399 */     this.disabledForeground = null;
/*  400 */     this.selectedBackground = null;
/*  401 */     this.selectedForeground = null;
/*  402 */     this.underlineColor = null;
/*  403 */     this.inactiveUnderlineColor = null;
/*  404 */     this.disabledUnderlineColor = null;
/*  405 */     this.hoverColor = null;
/*  406 */     this.hoverForeground = null;
/*  407 */     this.focusColor = null;
/*  408 */     this.focusForeground = null;
/*  409 */     this.tabSeparatorColor = null;
/*  410 */     this.contentAreaColor = null;
/*  411 */     this.closeIcon = null;
/*      */     
/*  413 */     this.buttonHoverBackground = null;
/*  414 */     this.buttonPressedBackground = null;
/*      */     
/*  416 */     this.oldStyleValues = null;
/*      */     
/*  418 */     MigLayoutVisualPadding.uninstall(this.tabPane);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void installComponents() {
/*  423 */     super.installComponents();
/*      */ 
/*      */     
/*  426 */     this.tabViewport = null;
/*  427 */     if (isScrollTabLayout()) {
/*  428 */       for (Component c : this.tabPane.getComponents()) {
/*  429 */         if (c instanceof JViewport && c.getClass().getName().equals("javax.swing.plaf.basic.BasicTabbedPaneUI$ScrollableTabViewport")) {
/*  430 */           this.tabViewport = (JViewport)c;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     }
/*  436 */     installHiddenTabsNavigation();
/*  437 */     installLeadingComponent();
/*  438 */     installTrailingComponent();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void uninstallComponents() {
/*  445 */     uninstallHiddenTabsNavigation();
/*  446 */     uninstallLeadingComponent();
/*  447 */     uninstallTrailingComponent();
/*      */     
/*  449 */     super.uninstallComponents();
/*      */     
/*  451 */     this.tabCloseButton = null;
/*  452 */     this.tabViewport = null;
/*      */   }
/*      */   
/*      */   protected void installHiddenTabsNavigation() {
/*  456 */     if (!isScrollTabLayout() || this.tabViewport == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  465 */     this.tabPane.setLayout(createScrollLayoutManager((BasicTabbedPaneUI.TabbedPaneLayout)this.tabPane.getLayout()));
/*      */ 
/*      */     
/*  468 */     this.moreTabsButton = createMoreTabsButton();
/*  469 */     this.tabPane.add(this.moreTabsButton);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void uninstallHiddenTabsNavigation() {
/*  475 */     if (this.tabPane.getLayout() instanceof FlatTabbedPaneScrollLayout) {
/*  476 */       this.tabPane.setLayout(((FlatTabbedPaneScrollLayout)this.tabPane.getLayout()).delegate);
/*      */     }
/*  478 */     if (this.moreTabsButton != null) {
/*  479 */       this.tabPane.remove(this.moreTabsButton);
/*  480 */       this.moreTabsButton = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void installLeadingComponent() {
/*  485 */     Object c = this.tabPane.getClientProperty("JTabbedPane.leadingComponent");
/*  486 */     if (c instanceof Component) {
/*  487 */       this.leadingComponent = new ContainerUIResource((Component)c);
/*  488 */       this.tabPane.add(this.leadingComponent);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void uninstallLeadingComponent() {
/*  493 */     if (this.leadingComponent != null) {
/*  494 */       this.tabPane.remove(this.leadingComponent);
/*  495 */       this.leadingComponent = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void installTrailingComponent() {
/*  500 */     Object c = this.tabPane.getClientProperty("JTabbedPane.trailingComponent");
/*  501 */     if (c instanceof Component) {
/*  502 */       this.trailingComponent = new ContainerUIResource((Component)c);
/*  503 */       this.tabPane.add(this.trailingComponent);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void uninstallTrailingComponent() {
/*  508 */     if (this.trailingComponent != null) {
/*  509 */       this.tabPane.remove(this.trailingComponent);
/*  510 */       this.trailingComponent = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void installListeners() {
/*  516 */     super.installListeners();
/*      */     
/*  518 */     getHandler().installListeners();
/*      */     
/*  520 */     if (this.tabViewport != null && (this.wheelTabScroller = createWheelTabScroller()) != null) {
/*      */ 
/*      */ 
/*      */       
/*  524 */       this.tabPane.addMouseWheelListener(this.wheelTabScroller);
/*  525 */       this.tabPane.addMouseMotionListener(this.wheelTabScroller);
/*  526 */       this.tabPane.addMouseListener(this.wheelTabScroller);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void uninstallListeners() {
/*  532 */     super.uninstallListeners();
/*      */     
/*  534 */     if (this.handler != null) {
/*  535 */       this.handler.uninstallListeners();
/*  536 */       this.handler = null;
/*      */     } 
/*      */     
/*  539 */     if (this.wheelTabScroller != null) {
/*  540 */       this.wheelTabScroller.uninstall();
/*      */       
/*  542 */       this.tabPane.removeMouseWheelListener(this.wheelTabScroller);
/*  543 */       this.tabPane.removeMouseMotionListener(this.wheelTabScroller);
/*  544 */       this.tabPane.removeMouseListener(this.wheelTabScroller);
/*  545 */       this.wheelTabScroller = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void installKeyboardActions() {
/*  551 */     super.installKeyboardActions();
/*      */ 
/*      */     
/*  554 */     ActionMap map = SwingUtilities.getUIActionMap(this.tabPane);
/*  555 */     if (map != null) {
/*      */ 
/*      */       
/*  558 */       RunWithOriginalLayoutManagerDelegateAction.install(map, "scrollTabsForwardAction");
/*  559 */       RunWithOriginalLayoutManagerDelegateAction.install(map, "scrollTabsBackwardAction");
/*      */     } 
/*      */   }
/*      */   
/*      */   private Handler getHandler() {
/*  564 */     if (this.handler == null)
/*  565 */       this.handler = new Handler(); 
/*  566 */     return this.handler;
/*      */   }
/*      */   
/*      */   protected FlatWheelTabScroller createWheelTabScroller() {
/*  570 */     return new FlatWheelTabScroller();
/*      */   }
/*      */ 
/*      */   
/*      */   protected MouseListener createMouseListener() {
/*  575 */     Handler handler = getHandler();
/*  576 */     handler.mouseDelegate = super.createMouseListener();
/*  577 */     return handler;
/*      */   }
/*      */ 
/*      */   
/*      */   protected PropertyChangeListener createPropertyChangeListener() {
/*  582 */     Handler handler = getHandler();
/*  583 */     handler.propertyChangeDelegate = super.createPropertyChangeListener();
/*  584 */     return handler;
/*      */   }
/*      */ 
/*      */   
/*      */   protected ChangeListener createChangeListener() {
/*  589 */     Handler handler = getHandler();
/*  590 */     handler.changeDelegate = super.createChangeListener();
/*  591 */     return handler;
/*      */   }
/*      */ 
/*      */   
/*      */   protected FocusListener createFocusListener() {
/*  596 */     Handler handler = getHandler();
/*  597 */     handler.focusDelegate = super.createFocusListener();
/*  598 */     return handler;
/*      */   }
/*      */ 
/*      */   
/*      */   protected LayoutManager createLayoutManager() {
/*  603 */     if (this.tabPane.getTabLayoutPolicy() == 0) {
/*  604 */       return new FlatTabbedPaneLayout();
/*      */     }
/*  606 */     return super.createLayoutManager();
/*      */   }
/*      */   
/*      */   protected LayoutManager createScrollLayoutManager(BasicTabbedPaneUI.TabbedPaneLayout delegate) {
/*  610 */     return new FlatTabbedPaneScrollLayout(delegate);
/*      */   }
/*      */   
/*      */   protected JButton createMoreTabsButton() {
/*  614 */     return new FlatMoreTabsButton();
/*      */   }
/*      */ 
/*      */   
/*      */   protected JButton createScrollButton(int direction) {
/*  619 */     return new FlatScrollableTabButton(direction);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void installStyle() {
/*      */     try {
/*  625 */       applyStyle(FlatStylingSupport.getResolvedStyle(this.tabPane, "TabbedPane"));
/*  626 */     } catch (RuntimeException ex) {
/*  627 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void applyStyle(Object style) {
/*  633 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*      */ 
/*      */     
/*  636 */     for (Component c : this.tabPane.getComponents()) {
/*  637 */       if (c instanceof FlatTabAreaButton) {
/*  638 */         ((FlatTabAreaButton)c).updateStyle();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected Object applyStyleProperty(String key, Object value) {
/*  645 */     if (key.startsWith("close")) {
/*  646 */       if (!(this.closeIcon instanceof FlatTabbedPaneCloseIcon)) {
/*  647 */         return new FlatStylingSupport.UnknownStyleException(key);
/*      */       }
/*  649 */       if (this.closeIconShared) {
/*  650 */         this.closeIcon = FlatStylingSupport.cloneIcon(this.closeIcon);
/*  651 */         this.closeIconShared = false;
/*      */       } 
/*      */       
/*  654 */       return ((FlatTabbedPaneCloseIcon)this.closeIcon).applyStyleProperty(key, value);
/*      */     } 
/*      */     
/*  657 */     if (value instanceof String) {
/*  658 */       switch (key) { case "tabType":
/*  659 */           value = Integer.valueOf(parseTabType((String)value)); break;
/*  660 */         case "tabsPopupPolicy": value = Integer.valueOf(parseTabsPopupPolicy((String)value)); break;
/*  661 */         case "scrollButtonsPolicy": value = Integer.valueOf(parseScrollButtonsPolicy((String)value)); break;
/*  662 */         case "scrollButtonsPlacement": value = Integer.valueOf(parseScrollButtonsPlacement((String)value)); break;
/*      */         case "tabAreaAlignment":
/*  664 */           value = Integer.valueOf(parseAlignment((String)value, 10)); break;
/*  665 */         case "tabAlignment": value = Integer.valueOf(parseAlignment((String)value, 0)); break;
/*  666 */         case "tabWidthMode": value = Integer.valueOf(parseTabWidthMode((String)value)); break;
/*      */         case "tabIconPlacement":
/*  668 */           value = Integer.valueOf(parseTabIconPlacement((String)value)); break; }
/*      */     
/*      */     } else {
/*      */       Object oldValue;
/*  672 */       switch (key) {
/*      */         case "tabInsets":
/*  674 */           oldValue = this.tabInsets; this.tabInsets = (Insets)value; return oldValue;
/*  675 */         case "tabAreaInsets": oldValue = this.tabAreaInsets; this.tabAreaInsets = (Insets)value; return oldValue;
/*      */         case "textIconGap":
/*  677 */           oldValue = Integer.valueOf(this.textIconGapUnscaled);
/*  678 */           this.textIconGapUnscaled = ((Integer)value).intValue();
/*  679 */           this.textIconGap = UIScale.scale(this.textIconGapUnscaled);
/*  680 */           return oldValue;
/*      */       } 
/*      */     
/*      */     } 
/*  684 */     return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, this.tabPane, key, value);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/*  690 */     Map<String, Class<?>> infos = new FlatStylingSupport.StyleableInfosMap<>();
/*  691 */     infos.put("tabInsets", Insets.class);
/*  692 */     infos.put("tabAreaInsets", Insets.class);
/*  693 */     infos.put("textIconGap", int.class);
/*  694 */     FlatStylingSupport.collectAnnotatedStyleableInfos(this, infos);
/*  695 */     if (this.closeIcon instanceof FlatTabbedPaneCloseIcon)
/*  696 */       infos.putAll(((FlatTabbedPaneCloseIcon)this.closeIcon).getStyleableInfos()); 
/*  697 */     return infos;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getStyleableValue(JComponent c, String key) {
/*  704 */     if (key.startsWith("close")) {
/*  705 */       return (this.closeIcon instanceof FlatTabbedPaneCloseIcon) ? (
/*  706 */         (FlatTabbedPaneCloseIcon)this.closeIcon).getStyleableValue(key) : 
/*  707 */         null;
/*      */     }
/*      */     
/*  710 */     switch (key) {
/*      */       case "tabInsets":
/*  712 */         return this.tabInsets;
/*  713 */       case "tabAreaInsets": return this.tabAreaInsets;
/*  714 */       case "textIconGap": return Integer.valueOf(this.textIconGapUnscaled);
/*      */ 
/*      */       
/*      */       case "tabType":
/*  718 */         switch (this.tabType)
/*      */         { default:
/*  720 */             return "underlined";
/*  721 */           case 1: break; }  return "card";
/*      */ 
/*      */       
/*      */       case "tabsPopupPolicy":
/*  725 */         switch (this.tabsPopupPolicy)
/*      */         { default:
/*  727 */             return "asNeeded";
/*  728 */           case 0: break; }  return "never";
/*      */ 
/*      */       
/*      */       case "scrollButtonsPolicy":
/*  732 */         switch (this.scrollButtonsPolicy)
/*      */         { default:
/*  734 */             return "asNeededSingle";
/*  735 */           case 2: return "asNeeded";
/*  736 */           case 0: break; }  return "never";
/*      */ 
/*      */       
/*      */       case "scrollButtonsPlacement":
/*  740 */         switch (this.scrollButtonsPlacement)
/*      */         { default:
/*  742 */             return "both";
/*  743 */           case 11: break; }  return "trailing";
/*      */       
/*      */       case "tabAreaAlignment":
/*  746 */         return alignmentToString(this.tabAreaAlignment, "leading");
/*  747 */       case "tabAlignment": return alignmentToString(this.tabAlignment, "center");
/*      */       
/*      */       case "tabWidthMode":
/*  750 */         switch (this.tabWidthMode)
/*      */         { default:
/*  752 */             return "preferred";
/*  753 */           case 1: return "equal";
/*  754 */           case 2: break; }  return "compact";
/*      */ 
/*      */       
/*      */       case "tabIconPlacement":
/*  758 */         switch (this.tabIconPlacement)
/*      */         { default:
/*  760 */             return "leading";
/*  761 */           case 11: return "trailing";
/*  762 */           case 1: return "top";
/*  763 */           case 3: break; }  return "bottom";
/*      */     } 
/*      */ 
/*      */     
/*  767 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*      */   }
/*      */   
/*      */   protected void setRolloverTab(int x, int y) {
/*  771 */     setRolloverTab(tabForCoordinate(this.tabPane, x, y));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setRolloverTab(int index) {
/*  776 */     if (this.blockRollover) {
/*      */       return;
/*      */     }
/*  779 */     int oldIndex = getRolloverTab();
/*  780 */     super.setRolloverTab(index);
/*      */     
/*  782 */     if (index == oldIndex) {
/*      */       return;
/*      */     }
/*      */     
/*  786 */     repaintTab(oldIndex);
/*  787 */     repaintTab(index);
/*      */   }
/*      */   
/*      */   protected boolean isRolloverTabClose() {
/*  791 */     return this.rolloverTabClose;
/*      */   }
/*      */   
/*      */   protected void setRolloverTabClose(boolean rollover) {
/*  795 */     if (this.rolloverTabClose == rollover) {
/*      */       return;
/*      */     }
/*  798 */     this.rolloverTabClose = rollover;
/*  799 */     repaintTab(getRolloverTab());
/*      */   }
/*      */   
/*      */   protected boolean isPressedTabClose() {
/*  803 */     return this.pressedTabClose;
/*      */   }
/*      */   
/*      */   protected void setPressedTabClose(boolean pressed) {
/*  807 */     if (this.pressedTabClose == pressed) {
/*      */       return;
/*      */     }
/*  810 */     this.pressedTabClose = pressed;
/*  811 */     repaintTab(getRolloverTab());
/*      */   }
/*      */   
/*      */   private void repaintTab(int tabIndex) {
/*  815 */     if (tabIndex < 0 || tabIndex >= this.tabPane.getTabCount()) {
/*      */       return;
/*      */     }
/*  818 */     Rectangle r = getTabBounds(this.tabPane, tabIndex);
/*  819 */     if (r == null) {
/*      */       return;
/*      */     }
/*      */     
/*  823 */     if (this.contentSeparatorHeight > 0 && 
/*  824 */       FlatClientProperties.clientPropertyBoolean(this.tabPane, "JTabbedPane.showContentSeparator", true)) {
/*      */       
/*  826 */       int sh = UIScale.scale(this.contentSeparatorHeight);
/*  827 */       switch (this.tabPane.getTabPlacement()) {
/*      */         default:
/*  829 */           r.height += sh; break;
/*  830 */         case 3: r.height += sh; r.y -= sh; break;
/*  831 */         case 2: r.width += sh; break;
/*  832 */         case 4: r.width += sh; r.x -= sh;
/*      */           break;
/*      */       } 
/*      */     } 
/*  836 */     this.tabPane.repaint(r);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
/*  843 */     int tabWidth, tabWidthMode = getTabWidthMode();
/*  844 */     if (tabWidthMode == 1 && isHorizontalTabPlacement() && !this.inCalculateEqual) {
/*  845 */       this.inCalculateEqual = true;
/*      */       try {
/*  847 */         tabWidth = calculateMaxTabWidth(tabPlacement); return tabWidth;
/*      */       } finally {
/*  849 */         this.inCalculateEqual = false;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  854 */     this.textIconGap = UIScale.scale(this.textIconGapUnscaled);
/*      */     
/*      */     Icon icon;
/*      */     
/*  858 */     if (tabWidthMode == 2 && tabIndex != this.tabPane
/*  859 */       .getSelectedIndex() && 
/*  860 */       isHorizontalTabPlacement() && this.tabPane
/*  861 */       .getTabComponentAt(tabIndex) == null && (
/*  862 */       icon = getIconForTab(tabIndex)) != null) {
/*      */       
/*  864 */       Insets tabInsets = getTabInsets(tabPlacement, tabIndex);
/*  865 */       tabWidth = icon.getIconWidth() + tabInsets.left + tabInsets.right;
/*      */     } else {
/*  867 */       int iconPlacement = FlatClientProperties.clientPropertyInt(this.tabPane, "JTabbedPane.tabIconPlacement", this.tabIconPlacement);
/*  868 */       if ((iconPlacement == 1 || iconPlacement == 3) && this.tabPane
/*  869 */         .getTabComponentAt(tabIndex) == null && (
/*  870 */         icon = getIconForTab(tabIndex)) != null) {
/*      */ 
/*      */         
/*  873 */         tabWidth = icon.getIconWidth();
/*      */         
/*  875 */         View view = getTextViewForTab(tabIndex);
/*  876 */         if (view != null) {
/*  877 */           tabWidth = Math.max(tabWidth, (int)view.getPreferredSpan(0));
/*      */         } else {
/*  879 */           String title = this.tabPane.getTitleAt(tabIndex);
/*  880 */           if (title != null) {
/*  881 */             tabWidth = Math.max(tabWidth, metrics.stringWidth(title));
/*      */           }
/*      */         } 
/*  884 */         Insets tabInsets = getTabInsets(tabPlacement, tabIndex);
/*  885 */         tabWidth += tabInsets.left + tabInsets.right;
/*      */       } else {
/*  887 */         tabWidth = super.calculateTabWidth(tabPlacement, tabIndex, metrics) - 3;
/*      */       } 
/*      */     } 
/*      */     
/*  891 */     if (isTabClosable(tabIndex)) {
/*  892 */       tabWidth += this.closeIcon.getIconWidth();
/*      */     }
/*      */     
/*  895 */     int min = getTabClientPropertyInt(tabIndex, "JTabbedPane.minimumTabWidth", this.minimumTabWidth);
/*  896 */     int max = getTabClientPropertyInt(tabIndex, "JTabbedPane.maximumTabWidth", this.maximumTabWidth);
/*  897 */     if (min > 0)
/*  898 */       tabWidth = Math.max(tabWidth, UIScale.scale(min)); 
/*  899 */     if (max > 0 && this.tabPane.getTabComponentAt(tabIndex) == null) {
/*  900 */       tabWidth = Math.min(tabWidth, UIScale.scale(max));
/*      */     }
/*  902 */     return tabWidth;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
/*  910 */     int tabHeight, iconPlacement = FlatClientProperties.clientPropertyInt(this.tabPane, "JTabbedPane.tabIconPlacement", this.tabIconPlacement); Icon icon;
/*  911 */     if ((iconPlacement == 1 || iconPlacement == 3) && this.tabPane
/*  912 */       .getTabComponentAt(tabIndex) == null && (
/*  913 */       icon = getIconForTab(tabIndex)) != null) {
/*      */ 
/*      */       
/*  916 */       tabHeight = icon.getIconHeight();
/*      */       
/*  918 */       View view = getTextViewForTab(tabIndex);
/*  919 */       if (view != null) {
/*  920 */         tabHeight += (int)view.getPreferredSpan(1) + UIScale.scale(this.textIconGapUnscaled);
/*  921 */       } else if (this.tabPane.getTitleAt(tabIndex) != null) {
/*  922 */         tabHeight += fontHeight + UIScale.scale(this.textIconGapUnscaled);
/*      */       } 
/*  924 */       Insets tabInsets = getTabInsets(tabPlacement, tabIndex);
/*  925 */       tabHeight += tabInsets.top + tabInsets.bottom;
/*      */     } else {
/*  927 */       tabHeight = super.calculateTabHeight(tabPlacement, tabIndex, fontHeight) - 2;
/*      */     } 
/*  929 */     return Math.max(tabHeight, UIScale.scale(FlatClientProperties.clientPropertyInt(this.tabPane, "JTabbedPane.tabHeight", this.tabHeight)));
/*      */   }
/*      */ 
/*      */   
/*      */   protected int calculateMaxTabWidth(int tabPlacement) {
/*  934 */     return hideTabArea() ? 0 : super.calculateMaxTabWidth(tabPlacement);
/*      */   }
/*      */ 
/*      */   
/*      */   protected int calculateMaxTabHeight(int tabPlacement) {
/*  939 */     return hideTabArea() ? 0 : super.calculateMaxTabHeight(tabPlacement);
/*      */   }
/*      */ 
/*      */   
/*      */   protected int calculateTabAreaWidth(int tabPlacement, int vertRunCount, int maxTabWidth) {
/*  944 */     return hideTabArea() ? 0 : super.calculateTabAreaWidth(tabPlacement, vertRunCount, maxTabWidth);
/*      */   }
/*      */ 
/*      */   
/*      */   protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {
/*  949 */     return hideTabArea() ? 0 : super.calculateTabAreaHeight(tabPlacement, horizRunCount, maxTabHeight);
/*      */   }
/*      */ 
/*      */   
/*      */   protected Insets getTabInsets(int tabPlacement, int tabIndex) {
/*  954 */     Object value = getTabClientProperty(tabIndex, "JTabbedPane.tabInsets");
/*  955 */     return UIScale.scale((value instanceof Insets) ? 
/*  956 */         (Insets)value : 
/*  957 */         super.getTabInsets(tabPlacement, tabIndex));
/*      */   }
/*      */ 
/*      */   
/*      */   protected Insets getSelectedTabPadInsets(int tabPlacement) {
/*  962 */     return new Insets(0, 0, 0, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Insets getRealTabAreaInsets(int tabPlacement) {
/*  970 */     if (this.tabAreaInsets == null) {
/*  971 */       this.tabAreaInsets = new Insets(0, 0, 0, 0);
/*      */     }
/*  973 */     Insets currentTabAreaInsets = super.getTabAreaInsets(tabPlacement);
/*  974 */     Insets insets = (Insets)currentTabAreaInsets.clone();
/*      */     
/*  976 */     Object value = this.tabPane.getClientProperty("JTabbedPane.tabAreaInsets");
/*  977 */     if (value instanceof Insets) {
/*  978 */       rotateInsets((Insets)value, insets, tabPlacement);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  985 */     currentTabAreaInsets.top = currentTabAreaInsets.left = -10000;
/*      */ 
/*      */     
/*  988 */     insets = UIScale.scale(insets);
/*      */     
/*  990 */     return insets;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Insets getTabAreaInsets(int tabPlacement) {
/*  995 */     Insets insets = getRealTabAreaInsets(tabPlacement);
/*      */ 
/*      */     
/*  998 */     if (this.tabPane.getTabLayoutPolicy() == 0) {
/*  999 */       if (isHorizontalTabPlacement()) {
/* 1000 */         insets.left += getLeadingPreferredWidth();
/* 1001 */         insets.right += getTrailingPreferredWidth();
/*      */       } else {
/* 1003 */         insets.top += getLeadingPreferredHeight();
/* 1004 */         insets.bottom += getTrailingPreferredHeight();
/*      */       } 
/*      */     }
/*      */     
/* 1008 */     return insets;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Insets getContentBorderInsets(int tabPlacement) {
/* 1018 */     if (hideTabArea() || this.contentSeparatorHeight == 0 || !FlatClientProperties.clientPropertyBoolean(this.tabPane, "JTabbedPane.showContentSeparator", this.showContentSeparator)) {
/* 1019 */       return new Insets(0, 0, 0, 0);
/*      */     }
/* 1021 */     boolean hasFullBorder = FlatClientProperties.clientPropertyBoolean(this.tabPane, "JTabbedPane.hasFullBorder", this.hasFullBorder);
/* 1022 */     int sh = UIScale.scale(this.contentSeparatorHeight);
/* 1023 */     Insets insets = hasFullBorder ? new Insets(sh, sh, sh, sh) : new Insets(sh, 0, 0, 0);
/*      */     
/* 1025 */     Insets contentBorderInsets = new Insets(0, 0, 0, 0);
/* 1026 */     rotateInsets(insets, contentBorderInsets, tabPlacement);
/* 1027 */     return contentBorderInsets;
/*      */   }
/*      */ 
/*      */   
/*      */   protected int getTabLabelShiftX(int tabPlacement, int tabIndex, boolean isSelected) {
/* 1032 */     if (isTabClosable(tabIndex)) {
/* 1033 */       int shift = this.closeIcon.getIconWidth() / 2;
/* 1034 */       return isLeftToRight() ? -shift : shift;
/*      */     } 
/* 1036 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   protected int getTabLabelShiftY(int tabPlacement, int tabIndex, boolean isSelected) {
/* 1041 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public void update(Graphics g, JComponent c) {
/* 1046 */     this.oldRenderingHints = FlatUIUtils.setRenderingHints(g);
/*      */     
/* 1048 */     super.update(g, c);
/*      */     
/* 1050 */     FlatUIUtils.resetRenderingHints(g, this.oldRenderingHints);
/* 1051 */     this.oldRenderingHints = null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void paint(Graphics g, JComponent c) {
/* 1056 */     if (hideTabArea()) {
/*      */       return;
/*      */     }
/* 1059 */     ensureCurrentLayout();
/*      */     
/* 1061 */     int tabPlacement = this.tabPane.getTabPlacement();
/* 1062 */     int selectedIndex = this.tabPane.getSelectedIndex();
/*      */     
/* 1064 */     paintContentBorder(g, tabPlacement, selectedIndex);
/*      */     
/* 1066 */     if (!isScrollTabLayout()) {
/* 1067 */       paintTabArea(g, tabPlacement, selectedIndex);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
/* 1074 */     Object[] oldHints = FlatUIUtils.setRenderingHints(g);
/*      */     
/* 1076 */     super.paintTabArea(g, tabPlacement, selectedIndex);
/*      */     
/* 1078 */     FlatUIUtils.resetRenderingHints(g, oldHints);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect) {
/* 1085 */     Rectangle tabRect = rects[tabIndex];
/* 1086 */     int x = tabRect.x;
/* 1087 */     int y = tabRect.y;
/* 1088 */     int w = tabRect.width;
/* 1089 */     int h = tabRect.height;
/* 1090 */     boolean isSelected = (tabIndex == this.tabPane.getSelectedIndex());
/*      */ 
/*      */     
/* 1093 */     if (this.tabsOpaque || this.tabPane.isOpaque()) {
/* 1094 */       paintTabBackground(g, tabPlacement, tabIndex, x, y, w, h, isSelected);
/*      */     }
/*      */     
/* 1097 */     paintTabBorder(g, tabPlacement, tabIndex, x, y, w, h, isSelected);
/*      */ 
/*      */     
/* 1100 */     if (isTabClosable(tabIndex)) {
/* 1101 */       paintTabCloseButton(g, tabIndex, x, y, w, h);
/*      */     }
/*      */     
/* 1104 */     if (isSelected) {
/* 1105 */       paintTabSelection(g, tabPlacement, tabIndex, x, y, w, h);
/*      */     }
/* 1107 */     if (this.tabPane.getTabComponentAt(tabIndex) != null) {
/*      */       return;
/*      */     }
/*      */     
/* 1111 */     String title = this.tabPane.getTitleAt(tabIndex);
/* 1112 */     Icon icon = getIconForTab(tabIndex);
/* 1113 */     Font font = this.tabPane.getFont();
/* 1114 */     FontMetrics metrics = this.tabPane.getFontMetrics(font);
/* 1115 */     boolean isCompact = (icon != null && !isSelected && getTabWidthMode() == 2 && isHorizontalTabPlacement());
/* 1116 */     if (isCompact)
/* 1117 */       title = null; 
/* 1118 */     String clippedTitle = layoutAndClipLabel(tabPlacement, metrics, tabIndex, title, icon, tabRect, iconRect, textRect, isSelected);
/*      */ 
/*      */     
/* 1121 */     if (this.tabViewport != null && (tabPlacement == 1 || tabPlacement == 3)) {
/* 1122 */       Rectangle viewRect = this.tabViewport.getViewRect();
/* 1123 */       viewRect.width -= 4;
/* 1124 */       if (!viewRect.contains(textRect)) {
/* 1125 */         Rectangle r = viewRect.intersection(textRect);
/* 1126 */         if (r.x > viewRect.x) {
/* 1127 */           clippedTitle = JavaCompatibility.getClippedString(null, metrics, title, r.width);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1132 */     if (!isCompact)
/* 1133 */       paintText(g, tabPlacement, font, metrics, tabIndex, clippedTitle, textRect, isSelected); 
/* 1134 */     paintIcon(g, tabPlacement, tabIndex, icon, iconRect, isSelected);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
/* 1141 */     g.setFont(font);
/*      */     
/* 1143 */     FlatUIUtils.runWithoutRenderingHints(g, this.oldRenderingHints, () -> {
/*      */           View view = getTextViewForTab(tabIndex);
/*      */           if (view != null) {
/*      */             view.paint(g, textRect);
/*      */             return;
/*      */           } 
/*      */           int mnemIndex = FlatLaf.isShowMnemonics() ? this.tabPane.getDisplayedMnemonicIndexAt(tabIndex) : -1;
/*      */           g.setColor(getTabForeground(tabPlacement, tabIndex, isSelected));
/*      */           FlatUIUtils.drawStringUnderlineCharAt(this.tabPane, g, title, mnemIndex, textRect.x, textRect.y + metrics.getAscent());
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Color getTabForeground(int tabPlacement, int tabIndex, boolean isSelected) {
/* 1162 */     if (!this.tabPane.isEnabled() || !this.tabPane.isEnabledAt(tabIndex)) {
/* 1163 */       return this.disabledForeground;
/*      */     }
/*      */     
/* 1166 */     if (this.hoverForeground != null && getRolloverTab() == tabIndex) {
/* 1167 */       return this.hoverForeground;
/*      */     }
/*      */     
/* 1170 */     Color foreground = this.tabPane.getForegroundAt(tabIndex);
/* 1171 */     if (foreground != this.tabPane.getForeground()) {
/* 1172 */       return foreground;
/*      */     }
/*      */     
/* 1175 */     if (this.focusForeground != null && isSelected && FlatUIUtils.isPermanentFocusOwner(this.tabPane))
/* 1176 */       return this.focusForeground; 
/* 1177 */     if (this.selectedForeground != null && isSelected) {
/* 1178 */       return this.selectedForeground;
/*      */     }
/* 1180 */     return foreground;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
/* 1188 */     Color background = getTabBackground(tabPlacement, tabIndex, isSelected);
/* 1189 */     g.setColor(FlatUIUtils.deriveColor(background, this.tabPane.getBackground()));
/* 1190 */     g.fillRect(x, y, w, h);
/*      */   }
/*      */ 
/*      */   
/*      */   protected Color getTabBackground(int tabPlacement, int tabIndex, boolean isSelected) {
/* 1195 */     Color background = this.tabPane.getBackgroundAt(tabIndex);
/*      */ 
/*      */     
/* 1198 */     if (!this.tabPane.isEnabled() || !this.tabPane.isEnabledAt(tabIndex)) {
/* 1199 */       return background;
/*      */     }
/*      */     
/* 1202 */     if (this.hoverColor != null && getRolloverTab() == tabIndex) {
/* 1203 */       return this.hoverColor;
/*      */     }
/*      */     
/* 1206 */     if (background != this.tabPane.getBackground()) {
/* 1207 */       return background;
/*      */     }
/*      */     
/* 1210 */     if (this.focusColor != null && isSelected && FlatUIUtils.isPermanentFocusOwner(this.tabPane))
/* 1211 */       return this.focusColor; 
/* 1212 */     if (this.selectedBackground != null && isSelected) {
/* 1213 */       return this.selectedBackground;
/*      */     }
/* 1215 */     return background;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
/* 1223 */     if (FlatClientProperties.clientPropertyBoolean(this.tabPane, "JTabbedPane.showTabSeparators", this.showTabSeparators) && 
/* 1224 */       !isLastInRun(tabIndex))
/*      */     {
/* 1226 */       if (getTabType() == 1) {
/*      */         
/* 1228 */         int selectedIndex = this.tabPane.getSelectedIndex();
/* 1229 */         if (tabIndex != selectedIndex - 1 && tabIndex != selectedIndex)
/* 1230 */           paintTabSeparator(g, tabPlacement, x, y, w, h); 
/*      */       } else {
/* 1232 */         paintTabSeparator(g, tabPlacement, x, y, w, h);
/*      */       } 
/*      */     }
/*      */     
/* 1236 */     if (isSelected && getTabType() == 1) {
/* 1237 */       paintCardTabBorder(g, tabPlacement, tabIndex, x, y, w, h);
/*      */     }
/*      */   }
/*      */   
/*      */   protected void paintCardTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h) {
/* 1242 */     Graphics2D g2 = (Graphics2D)g;
/*      */     
/* 1244 */     float borderWidth = UIScale.scale(this.contentSeparatorHeight);
/* 1245 */     g.setColor((this.tabSeparatorColor != null) ? this.tabSeparatorColor : this.contentAreaColor);
/*      */     
/* 1247 */     switch (tabPlacement) {
/*      */ 
/*      */ 
/*      */       
/*      */       default:
/* 1252 */         g2.fill(new Rectangle2D.Float(x, y, borderWidth, h));
/* 1253 */         g2.fill(new Rectangle2D.Float((x + w) - borderWidth, y, borderWidth, h));
/*      */         break;
/*      */       
/*      */       case 2:
/*      */       case 4:
/* 1258 */         g2.fill(new Rectangle2D.Float(x, y, w, borderWidth));
/* 1259 */         g2.fill(new Rectangle2D.Float(x, (y + h) - borderWidth, w, borderWidth));
/*      */         break;
/*      */     } 
/*      */     
/* 1263 */     if (this.cardTabSelectionHeight <= 0) {
/*      */       
/* 1265 */       switch (tabPlacement) {
/*      */         
/*      */         default:
/* 1268 */           g2.fill(new Rectangle2D.Float(x, y, w, borderWidth));
/*      */           return;
/*      */         case 3:
/* 1271 */           g2.fill(new Rectangle2D.Float(x, (y + h) - borderWidth, w, borderWidth));
/*      */           return;
/*      */         case 2:
/* 1274 */           g2.fill(new Rectangle2D.Float(x, y, borderWidth, h)); return;
/*      */         case 4:
/*      */           break;
/* 1277 */       }  g2.fill(new Rectangle2D.Float((x + w) - borderWidth, y, borderWidth, h));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void paintTabCloseButton(Graphics g, int tabIndex, int x, int y, int w, int h) {
/* 1285 */     if (this.tabCloseButton == null) {
/* 1286 */       this.tabCloseButton = new TabCloseButton();
/* 1287 */       this.tabCloseButton.setVisible(false);
/*      */     } 
/*      */ 
/*      */     
/* 1291 */     boolean rollover = (tabIndex == getRolloverTab());
/* 1292 */     ButtonModel bm = this.tabCloseButton.getModel();
/* 1293 */     bm.setRollover((rollover && isRolloverTabClose()));
/* 1294 */     bm.setPressed((rollover && isPressedTabClose()));
/*      */ 
/*      */     
/* 1297 */     this.tabCloseButton.setBackground(this.tabPane.getBackground());
/* 1298 */     this.tabCloseButton.setForeground(this.tabPane.getForeground());
/*      */ 
/*      */     
/* 1301 */     Rectangle tabCloseRect = getTabCloseBounds(tabIndex, x, y, w, h, this.calcRect);
/* 1302 */     this.closeIcon.paintIcon(this.tabCloseButton, g, tabCloseRect.x, tabCloseRect.y);
/*      */   }
/*      */   
/*      */   protected void paintTabSeparator(Graphics g, int tabPlacement, int x, int y, int w, int h) {
/* 1306 */     float sepWidth = UIScale.scale(1.0F);
/* 1307 */     float offset = this.tabSeparatorsFullHeight ? 0.0F : UIScale.scale(5.0F);
/*      */     
/* 1309 */     g.setColor((this.tabSeparatorColor != null) ? this.tabSeparatorColor : this.contentAreaColor);
/* 1310 */     if (tabPlacement == 2 || tabPlacement == 4) {
/*      */       
/* 1312 */       ((Graphics2D)g).fill(new Rectangle2D.Float(x + offset, (y + h) - sepWidth, w - offset * 2.0F, sepWidth));
/* 1313 */     } else if (isLeftToRight()) {
/*      */       
/* 1315 */       ((Graphics2D)g).fill(new Rectangle2D.Float((x + w) - sepWidth, y + offset, sepWidth, h - offset * 2.0F));
/*      */     } else {
/*      */       
/* 1318 */       ((Graphics2D)g).fill(new Rectangle2D.Float(x, y + offset, sepWidth, h - offset * 2.0F));
/*      */     } 
/*      */   }
/*      */   protected void paintTabSelection(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h) {
/*      */     int sy;
/* 1323 */     g.setColor(this.tabPane.isEnabled() ? (
/* 1324 */         isTabbedPaneOrChildFocused() ? this.underlineColor : this.inactiveUnderlineColor) : 
/* 1325 */         this.disabledUnderlineColor);
/*      */ 
/*      */     
/* 1328 */     boolean atBottom = (getTabType() != 1);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1333 */     Insets contentInsets = atBottom ? ((!this.rotateTabRuns && this.runCount > 1 && !isScrollTabLayout() && getRunForTab(this.tabPane.getTabCount(), tabIndex) > 0) ? new Insets(0, 0, 0, 0) : getContentBorderInsets(tabPlacement)) : null;
/*      */     
/* 1335 */     int tabSelectionHeight = UIScale.scale(atBottom ? this.tabSelectionHeight : this.cardTabSelectionHeight);
/*      */     
/* 1337 */     switch (tabPlacement) {
/*      */       
/*      */       default:
/* 1340 */         sy = atBottom ? (y + h + contentInsets.top - tabSelectionHeight) : y;
/* 1341 */         g.fillRect(x, sy, w, tabSelectionHeight);
/*      */         return;
/*      */       
/*      */       case 3:
/* 1345 */         sy = atBottom ? (y - contentInsets.bottom) : (y + h - tabSelectionHeight);
/* 1346 */         g.fillRect(x, sy, w, tabSelectionHeight);
/*      */         return;
/*      */       
/*      */       case 2:
/* 1350 */         sx = atBottom ? (x + w + contentInsets.left - tabSelectionHeight) : x;
/* 1351 */         g.fillRect(sx, y, tabSelectionHeight, h); return;
/*      */       case 4:
/*      */         break;
/*      */     } 
/* 1355 */     int sx = atBottom ? (x - contentInsets.right) : (x + w - tabSelectionHeight);
/* 1356 */     g.fillRect(sx, y, tabSelectionHeight, h);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isTabbedPaneOrChildFocused() {
/* 1364 */     KeyboardFocusManager keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
/*      */     
/* 1366 */     Object value = this.tabPane.getClientProperty("JComponent.focusOwner");
/* 1367 */     if (value instanceof Predicate) {
/* 1368 */       return (((Predicate<JTabbedPane>)value).test(this.tabPane) && 
/* 1369 */         FlatUIUtils.isInActiveWindow(this.tabPane, keyboardFocusManager.getActiveWindow()));
/*      */     }
/*      */     
/* 1372 */     Component focusOwner = keyboardFocusManager.getPermanentFocusOwner();
/* 1373 */     return (focusOwner != null && 
/* 1374 */       SwingUtilities.isDescendingFrom(focusOwner, this.tabPane) && 
/* 1375 */       FlatUIUtils.isInActiveWindow(focusOwner, keyboardFocusManager.getActiveWindow()));
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
/*      */   protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
/* 1389 */     if (this.tabPane.getTabCount() <= 0 || this.contentSeparatorHeight == 0 || 
/*      */       
/* 1391 */       !FlatClientProperties.clientPropertyBoolean(this.tabPane, "JTabbedPane.showContentSeparator", this.showContentSeparator)) {
/*      */       return;
/*      */     }
/* 1394 */     Insets insets = this.tabPane.getInsets();
/* 1395 */     Insets tabAreaInsets = getTabAreaInsets(tabPlacement);
/*      */     
/* 1397 */     int x = insets.left;
/* 1398 */     int y = insets.top;
/* 1399 */     int w = this.tabPane.getWidth() - insets.right - insets.left;
/* 1400 */     int h = this.tabPane.getHeight() - insets.top - insets.bottom;
/*      */ 
/*      */     
/* 1403 */     switch (tabPlacement) {
/*      */       
/*      */       default:
/* 1406 */         y += calculateTabAreaHeight(tabPlacement, this.runCount, this.maxTabHeight);
/* 1407 */         y -= tabAreaInsets.bottom;
/* 1408 */         h -= y - insets.top;
/*      */         break;
/*      */       
/*      */       case 3:
/* 1412 */         h -= calculateTabAreaHeight(tabPlacement, this.runCount, this.maxTabHeight);
/* 1413 */         h += tabAreaInsets.top;
/*      */         break;
/*      */       
/*      */       case 2:
/* 1417 */         x += calculateTabAreaWidth(tabPlacement, this.runCount, this.maxTabWidth);
/* 1418 */         x -= tabAreaInsets.right;
/* 1419 */         w -= x - insets.left;
/*      */         break;
/*      */       
/*      */       case 4:
/* 1423 */         w -= calculateTabAreaWidth(tabPlacement, this.runCount, this.maxTabWidth);
/* 1424 */         w += tabAreaInsets.left;
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/* 1429 */     boolean hasFullBorder = FlatClientProperties.clientPropertyBoolean(this.tabPane, "JTabbedPane.hasFullBorder", this.hasFullBorder);
/* 1430 */     int sh = UIScale.scale(this.contentSeparatorHeight * 100);
/* 1431 */     Insets ci = new Insets(0, 0, 0, 0);
/* 1432 */     rotateInsets(hasFullBorder ? new Insets(sh, sh, sh, sh) : new Insets(sh, 0, 0, 0), ci, tabPlacement);
/*      */ 
/*      */     
/* 1435 */     Path2D path = new Path2D.Float(0);
/* 1436 */     path.append(new Rectangle2D.Float(x, y, w, h), false);
/* 1437 */     path.append(new Rectangle2D.Float(x + ci.left / 100.0F, y + ci.top / 100.0F, w - ci.left / 100.0F - ci.right / 100.0F, h - ci.top / 100.0F - ci.bottom / 100.0F), false);
/*      */ 
/*      */ 
/*      */     
/* 1441 */     if (getTabType() == 1) {
/* 1442 */       float csh = UIScale.scale(this.contentSeparatorHeight);
/*      */       
/* 1444 */       Rectangle tabRect = getTabBounds(this.tabPane, selectedIndex);
/* 1445 */       Rectangle2D.Float innerTabRect = new Rectangle2D.Float(tabRect.x + csh, tabRect.y + csh, tabRect.width - csh * 2.0F, tabRect.height - csh * 2.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1450 */       if (this.tabViewport != null) {
/* 1451 */         Rectangle2D.intersect(this.tabViewport.getBounds(), innerTabRect, innerTabRect);
/*      */       }
/* 1453 */       Rectangle2D.Float gap = null;
/* 1454 */       if (isHorizontalTabPlacement()) {
/* 1455 */         if (innerTabRect.width > 0.0F) {
/* 1456 */           float y2 = (tabPlacement == 1) ? y : ((y + h) - csh);
/* 1457 */           gap = new Rectangle2D.Float(innerTabRect.x, y2, innerTabRect.width, csh);
/*      */         }
/*      */       
/* 1460 */       } else if (innerTabRect.height > 0.0F) {
/* 1461 */         float x2 = (tabPlacement == 2) ? x : ((x + w) - csh);
/* 1462 */         gap = new Rectangle2D.Float(x2, innerTabRect.y, csh, innerTabRect.height);
/*      */       } 
/*      */ 
/*      */       
/* 1466 */       if (gap != null) {
/* 1467 */         path.append(gap, false);
/*      */ 
/*      */         
/* 1470 */         Color background = getTabBackground(tabPlacement, selectedIndex, true);
/* 1471 */         g.setColor(FlatUIUtils.deriveColor(background, this.tabPane.getBackground()));
/* 1472 */         ((Graphics2D)g).fill(gap);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1477 */     g.setColor(this.contentAreaColor);
/* 1478 */     ((Graphics2D)g).fill(path);
/*      */ 
/*      */ 
/*      */     
/* 1482 */     if (isScrollTabLayout() && selectedIndex >= 0 && this.tabViewport != null) {
/* 1483 */       Rectangle tabRect = getTabBounds(this.tabPane, selectedIndex);
/*      */ 
/*      */ 
/*      */       
/* 1487 */       Shape oldClip = g.getClip();
/* 1488 */       Rectangle vr = this.tabViewport.getBounds();
/* 1489 */       if (isHorizontalTabPlacement()) {
/* 1490 */         g.clipRect(vr.x, 0, vr.width, this.tabPane.getHeight());
/*      */       } else {
/* 1492 */         g.clipRect(0, vr.y, this.tabPane.getWidth(), vr.height);
/*      */       } 
/* 1494 */       paintTabSelection(g, tabPlacement, selectedIndex, tabRect.x, tabRect.y, tabRect.width, tabRect.height);
/* 1495 */       g.setClip(oldClip);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String layoutAndClipLabel(int tabPlacement, FontMetrics metrics, int tabIndex, String title, Icon icon, Rectangle tabRect, Rectangle iconRect, Rectangle textRect, boolean isSelected) {
/*      */     int verticalTextPosition, horizontalTextPosition;
/* 1510 */     tabRect = FlatUIUtils.subtractInsets(tabRect, getTabInsets(tabPlacement, tabIndex));
/* 1511 */     if (isTabClosable(tabIndex)) {
/* 1512 */       tabRect.width -= this.closeIcon.getIconWidth();
/* 1513 */       if (!isLeftToRight()) {
/* 1514 */         tabRect.x += this.closeIcon.getIconWidth();
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1520 */     switch (FlatClientProperties.clientPropertyInt(this.tabPane, "JTabbedPane.tabIconPlacement", this.tabIconPlacement)) {
/*      */       default:
/* 1522 */         verticalTextPosition = 0; horizontalTextPosition = 11; break;
/* 1523 */       case 11: verticalTextPosition = 0; horizontalTextPosition = 10; break;
/* 1524 */       case 1: verticalTextPosition = 3; horizontalTextPosition = 0; break;
/* 1525 */       case 3: verticalTextPosition = 1; horizontalTextPosition = 0;
/*      */         break;
/*      */     } 
/*      */     
/* 1529 */     textRect.setBounds(0, 0, 0, 0);
/* 1530 */     iconRect.setBounds(0, 0, 0, 0);
/*      */ 
/*      */     
/* 1533 */     View view = getTextViewForTab(tabIndex);
/* 1534 */     if (view != null) {
/* 1535 */       this.tabPane.putClientProperty("html", view);
/*      */     }
/*      */     
/* 1538 */     String clippedTitle = SwingUtilities.layoutCompoundLabel(this.tabPane, metrics, title, icon, 0, 
/* 1539 */         getTabAlignment(tabIndex), verticalTextPosition, horizontalTextPosition, tabRect, iconRect, textRect, 
/* 1540 */         UIScale.scale(this.textIconGapUnscaled));
/*      */ 
/*      */     
/* 1543 */     this.tabPane.putClientProperty("html", (Object)null);
/*      */     
/* 1545 */     return clippedTitle;
/*      */   }
/*      */ 
/*      */   
/*      */   public int tabForCoordinate(JTabbedPane pane, int x, int y) {
/* 1550 */     if (this.moreTabsButton != null) {
/*      */       
/* 1552 */       Point viewPosition = this.tabViewport.getViewPosition();
/* 1553 */       x = x - this.tabViewport.getX() + viewPosition.x;
/* 1554 */       y = y - this.tabViewport.getY() + viewPosition.y;
/*      */ 
/*      */       
/* 1557 */       if (!this.tabViewport.getViewRect().contains(x, y)) {
/* 1558 */         return -1;
/*      */       }
/*      */     } 
/* 1561 */     return super.tabForCoordinate(pane, x, y);
/*      */   }
/*      */ 
/*      */   
/*      */   protected Rectangle getTabBounds(int tabIndex, Rectangle dest) {
/* 1566 */     if (this.moreTabsButton != null) {
/*      */       
/* 1568 */       dest.setBounds(this.rects[tabIndex]);
/*      */ 
/*      */       
/* 1571 */       Point viewPosition = this.tabViewport.getViewPosition();
/* 1572 */       dest.x = dest.x + this.tabViewport.getX() - viewPosition.x;
/* 1573 */       dest.y = dest.y + this.tabViewport.getY() - viewPosition.y;
/* 1574 */       return dest;
/*      */     } 
/* 1576 */     return super.getTabBounds(tabIndex, dest);
/*      */   }
/*      */   
/*      */   protected Rectangle getTabCloseBounds(int tabIndex, int x, int y, int w, int h, Rectangle dest) {
/* 1580 */     int iconWidth = this.closeIcon.getIconWidth();
/* 1581 */     int iconHeight = this.closeIcon.getIconHeight();
/* 1582 */     Insets tabInsets = getTabInsets(this.tabPane.getTabPlacement(), tabIndex);
/*      */ 
/*      */     
/* 1585 */     dest
/*      */       
/* 1587 */       .x = isLeftToRight() ? (x + w - tabInsets.right / 3 * 2 - iconWidth) : (x + tabInsets.left / 3 * 2);
/* 1588 */     dest.y = y + (h - iconHeight) / 2;
/* 1589 */     dest.width = iconWidth;
/* 1590 */     dest.height = iconHeight;
/* 1591 */     return dest;
/*      */   }
/*      */   
/*      */   protected Rectangle getTabCloseHitArea(int tabIndex) {
/* 1595 */     Rectangle tabRect = getTabBounds(this.tabPane, tabIndex);
/* 1596 */     Rectangle tabCloseRect = getTabCloseBounds(tabIndex, tabRect.x, tabRect.y, tabRect.width, tabRect.height, this.calcRect);
/* 1597 */     return new Rectangle(tabCloseRect.x, tabRect.y, tabCloseRect.width, tabRect.height);
/*      */   }
/*      */   
/*      */   protected boolean isTabClosable(int tabIndex) {
/* 1601 */     if (tabIndex < 0) {
/* 1602 */       return false;
/*      */     }
/* 1604 */     Object value = getTabClientProperty(tabIndex, "JTabbedPane.tabClosable");
/* 1605 */     return (value instanceof Boolean) ? ((Boolean)value).booleanValue() : this.tabClosable;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void closeTab(int tabIndex) {
/* 1610 */     Object callback = getTabClientProperty(tabIndex, "JTabbedPane.tabCloseCallback");
/* 1611 */     if (callback instanceof IntConsumer) {
/* 1612 */       ((IntConsumer)callback).accept(tabIndex);
/* 1613 */     } else if (callback instanceof BiConsumer) {
/* 1614 */       ((BiConsumer<JTabbedPane, Integer>)callback).accept(this.tabPane, Integer.valueOf(tabIndex));
/*      */     } else {
/* 1616 */       throw new RuntimeException("Missing tab close callback. Set client property 'JTabbedPane.tabCloseCallback' to a 'java.util.function.IntConsumer' or 'java.util.function.BiConsumer<JTabbedPane, Integer>'");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object getTabClientProperty(int tabIndex, String key) {
/* 1624 */     if (tabIndex < 0) {
/* 1625 */       return null;
/*      */     }
/* 1627 */     Component c = this.tabPane.getComponentAt(tabIndex);
/* 1628 */     if (c instanceof JComponent) {
/* 1629 */       Object value = ((JComponent)c).getClientProperty(key);
/* 1630 */       if (value != null)
/* 1631 */         return value; 
/*      */     } 
/* 1633 */     return this.tabPane.getClientProperty(key);
/*      */   }
/*      */   
/*      */   protected int getTabClientPropertyInt(int tabIndex, String key, int defaultValue) {
/* 1637 */     Object value = getTabClientProperty(tabIndex, key);
/* 1638 */     return (value instanceof Integer) ? ((Integer)value).intValue() : defaultValue;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void ensureCurrentLayout() {
/* 1644 */     getTabRunCount(this.tabPane);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean shouldRotateTabRuns(int tabPlacement) {
/* 1649 */     return this.rotateTabRuns;
/*      */   }
/*      */   
/*      */   private boolean isLastInRun(int tabIndex) {
/* 1653 */     int run = getRunForTab(this.tabPane.getTabCount(), tabIndex);
/* 1654 */     return (lastTabInRun(this.tabPane.getTabCount(), run) == tabIndex);
/*      */   }
/*      */   
/*      */   private boolean isScrollTabLayout() {
/* 1658 */     return (this.tabPane.getTabLayoutPolicy() == 1);
/*      */   }
/*      */   
/*      */   private boolean isLeftToRight() {
/* 1662 */     return this.tabPane.getComponentOrientation().isLeftToRight();
/*      */   }
/*      */   
/*      */   protected boolean isHorizontalTabPlacement() {
/* 1666 */     int tabPlacement = this.tabPane.getTabPlacement();
/* 1667 */     return (tabPlacement == 1 || tabPlacement == 3);
/*      */   }
/*      */   
/*      */   protected boolean isSmoothScrollingEnabled() {
/* 1671 */     if (!Animator.useAnimation()) {
/* 1672 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1677 */     return UIManager.getBoolean("ScrollPane.smoothScrolling");
/*      */   }
/*      */   
/*      */   protected boolean hideTabArea() {
/* 1681 */     return (this.tabPane.getTabCount() == 1 && this.leadingComponent == null && this.trailingComponent == null && 
/*      */ 
/*      */       
/* 1684 */       FlatClientProperties.clientPropertyBoolean(this.tabPane, "JTabbedPane.hideTabAreaWithOneTab", this.hideTabAreaWithOneTab));
/*      */   }
/*      */ 
/*      */   
/*      */   protected int getTabType() {
/* 1689 */     Object value = this.tabPane.getClientProperty("JTabbedPane.tabType");
/*      */     
/* 1691 */     return (value instanceof String) ? 
/* 1692 */       parseTabType((String)value) : 
/* 1693 */       this.tabType;
/*      */   }
/*      */   
/*      */   protected int getTabsPopupPolicy() {
/* 1697 */     Object value = this.tabPane.getClientProperty("JTabbedPane.tabsPopupPolicy");
/*      */     
/* 1699 */     return (value instanceof String) ? 
/* 1700 */       parseTabsPopupPolicy((String)value) : 
/* 1701 */       this.tabsPopupPolicy;
/*      */   }
/*      */   
/*      */   protected int getScrollButtonsPolicy() {
/* 1705 */     Object value = this.tabPane.getClientProperty("JTabbedPane.scrollButtonsPolicy");
/*      */     
/* 1707 */     return (value instanceof String) ? 
/* 1708 */       parseScrollButtonsPolicy((String)value) : 
/* 1709 */       this.scrollButtonsPolicy;
/*      */   }
/*      */   
/*      */   protected int getScrollButtonsPlacement() {
/* 1713 */     Object value = this.tabPane.getClientProperty("JTabbedPane.scrollButtonsPlacement");
/*      */     
/* 1715 */     return (value instanceof String) ? 
/* 1716 */       parseScrollButtonsPlacement((String)value) : 
/* 1717 */       this.scrollButtonsPlacement;
/*      */   }
/*      */   
/*      */   protected int getTabAreaAlignment() {
/* 1721 */     Object value = this.tabPane.getClientProperty("JTabbedPane.tabAreaAlignment");
/* 1722 */     if (value instanceof Integer) {
/* 1723 */       return ((Integer)value).intValue();
/*      */     }
/* 1725 */     return (value instanceof String) ? 
/* 1726 */       parseAlignment((String)value, 10) : 
/* 1727 */       this.tabAreaAlignment;
/*      */   }
/*      */   
/*      */   protected int getTabAlignment(int tabIndex) {
/* 1731 */     Object value = getTabClientProperty(tabIndex, "JTabbedPane.tabAlignment");
/* 1732 */     if (value instanceof Integer) {
/* 1733 */       return ((Integer)value).intValue();
/*      */     }
/* 1735 */     return (value instanceof String) ? 
/* 1736 */       parseAlignment((String)value, 0) : 
/* 1737 */       this.tabAlignment;
/*      */   }
/*      */   
/*      */   protected int getTabWidthMode() {
/* 1741 */     Object value = this.tabPane.getClientProperty("JTabbedPane.tabWidthMode");
/*      */     
/* 1743 */     return (value instanceof String) ? 
/* 1744 */       parseTabWidthMode((String)value) : 
/* 1745 */       this.tabWidthMode;
/*      */   }
/*      */ 
/*      */   
/*      */   protected static int parseTabType(String str) {
/* 1750 */     if (str == null) {
/* 1751 */       return 0;
/*      */     }
/* 1753 */     switch (str)
/*      */     { default:
/* 1755 */         return 0;
/* 1756 */       case "card": break; }  return 1;
/*      */   }
/*      */ 
/*      */   
/*      */   protected static int parseTabsPopupPolicy(String str) {
/* 1761 */     if (str == null) {
/* 1762 */       return 2;
/*      */     }
/* 1764 */     switch (str)
/*      */     { default:
/* 1766 */         return 2;
/* 1767 */       case "never": break; }  return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   protected static int parseScrollButtonsPolicy(String str) {
/* 1772 */     if (str == null) {
/* 1773 */       return 3;
/*      */     }
/* 1775 */     switch (str)
/*      */     { default:
/* 1777 */         return 3;
/* 1778 */       case "asNeeded": return 2;
/* 1779 */       case "never": break; }  return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   protected static int parseScrollButtonsPlacement(String str) {
/* 1784 */     if (str == null) {
/* 1785 */       return 100;
/*      */     }
/* 1787 */     switch (str)
/*      */     { default:
/* 1789 */         return 100;
/* 1790 */       case "trailing": break; }  return 11;
/*      */   }
/*      */ 
/*      */   
/*      */   protected static int parseAlignment(String str, int defaultValue) {
/* 1795 */     if (str == null) {
/* 1796 */       return defaultValue;
/*      */     }
/* 1798 */     switch (str) { case "leading":
/* 1799 */         return 10;
/* 1800 */       case "trailing": return 11;
/* 1801 */       case "center": return 0;
/* 1802 */       case "fill": return 100; }
/* 1803 */      return defaultValue;
/*      */   }
/*      */ 
/*      */   
/*      */   private static String alignmentToString(int value, String defaultValue) {
/* 1808 */     switch (value) { case 10:
/* 1809 */         return "leading";
/* 1810 */       case 11: return "trailing";
/* 1811 */       case 0: return "center";
/* 1812 */       case 100: return "fill"; }
/* 1813 */      return defaultValue;
/*      */   }
/*      */ 
/*      */   
/*      */   protected static int parseTabWidthMode(String str) {
/* 1818 */     if (str == null) {
/* 1819 */       return 0;
/*      */     }
/* 1821 */     switch (str)
/*      */     { default:
/* 1823 */         return 0;
/* 1824 */       case "equal": return 1;
/* 1825 */       case "compact": break; }  return 2;
/*      */   }
/*      */ 
/*      */   
/*      */   protected static int parseTabIconPlacement(String str) {
/* 1830 */     if (str == null) {
/* 1831 */       return 10;
/*      */     }
/* 1833 */     switch (str)
/*      */     { default:
/* 1835 */         return 10;
/* 1836 */       case "trailing": return 11;
/* 1837 */       case "top": return 1;
/* 1838 */       case "bottom": break; }  return 3;
/*      */   }
/*      */ 
/*      */   
/*      */   private void runWithOriginalLayoutManager(Runnable runnable) {
/* 1843 */     LayoutManager layout = this.tabPane.getLayout();
/* 1844 */     if (layout instanceof FlatTabbedPaneScrollLayout) {
/*      */ 
/*      */       
/* 1847 */       this.tabPane.setLayout(((FlatTabbedPaneScrollLayout)layout).delegate);
/* 1848 */       runnable.run();
/* 1849 */       this.tabPane.setLayout(layout);
/*      */     } else {
/* 1851 */       runnable.run();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void ensureSelectedTabIsVisibleLater() {
/* 1857 */     if (!this.tabPane.isDisplayable() || !EventQueue.isDispatchThread()) {
/*      */       return;
/*      */     }
/* 1860 */     EventQueue.invokeLater(() -> ensureSelectedTabIsVisible());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void ensureSelectedTabIsVisible() {
/* 1866 */     if (this.tabPane == null || this.tabViewport == null || !this.tabPane.isDisplayable()) {
/*      */       return;
/*      */     }
/* 1869 */     ensureCurrentLayout();
/*      */     
/* 1871 */     int selectedIndex = this.tabPane.getSelectedIndex();
/* 1872 */     if (selectedIndex < 0 || selectedIndex >= this.rects.length) {
/*      */       return;
/*      */     }
/* 1875 */     ((JComponent)this.tabViewport.getView()).scrollRectToVisible((Rectangle)this.rects[selectedIndex].clone());
/*      */   }
/*      */   
/*      */   private int getLeadingPreferredWidth() {
/* 1879 */     return (this.leadingComponent != null) ? (this.leadingComponent.getPreferredSize()).width : 0;
/*      */   }
/*      */   
/*      */   private int getLeadingPreferredHeight() {
/* 1883 */     return (this.leadingComponent != null) ? (this.leadingComponent.getPreferredSize()).height : 0;
/*      */   }
/*      */   
/*      */   private int getTrailingPreferredWidth() {
/* 1887 */     return (this.trailingComponent != null) ? (this.trailingComponent.getPreferredSize()).width : 0;
/*      */   }
/*      */   
/*      */   private int getTrailingPreferredHeight() {
/* 1891 */     return (this.trailingComponent != null) ? (this.trailingComponent.getPreferredSize()).height : 0;
/*      */   }
/*      */   
/*      */   private void shiftTabs(int sx, int sy) {
/* 1895 */     if (sx == 0 && sy == 0) {
/*      */       return;
/*      */     }
/* 1898 */     for (int i = 0; i < this.rects.length; i++) {
/*      */       
/* 1900 */       (this.rects[i]).x += sx;
/* 1901 */       (this.rects[i]).y += sy;
/*      */ 
/*      */       
/* 1904 */       Component c = this.tabPane.getTabComponentAt(i);
/* 1905 */       if (c != null)
/* 1906 */         c.setLocation(c.getX() + sx, c.getY() + sy); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void stretchTabsWidth(int sw, boolean leftToRight) {
/* 1911 */     int rsw = sw / this.rects.length;
/* 1912 */     int x = (this.rects[0]).x - (leftToRight ? 0 : rsw);
/* 1913 */     for (int i = 0; i < this.rects.length; i++) {
/*      */       
/* 1915 */       Component c = this.tabPane.getTabComponentAt(i);
/* 1916 */       if (c != null) {
/* 1917 */         c.setLocation(x + c.getX() - (this.rects[i]).x + rsw / 2, c.getY());
/*      */       }
/*      */       
/* 1920 */       (this.rects[i]).x = x;
/* 1921 */       (this.rects[i]).width += rsw;
/*      */       
/* 1923 */       if (leftToRight) {
/* 1924 */         x += (this.rects[i]).width;
/* 1925 */       } else if (i + 1 < this.rects.length) {
/* 1926 */         x = (this.rects[i]).x - (this.rects[i + 1]).width - rsw;
/*      */       } 
/*      */     } 
/*      */     
/* 1930 */     int diff = sw - rsw * this.rects.length;
/* 1931 */     (this.rects[this.rects.length - 1]).width += diff;
/* 1932 */     if (!leftToRight)
/* 1933 */       (this.rects[this.rects.length - 1]).x -= diff; 
/*      */   }
/*      */   
/*      */   private void stretchTabsHeight(int sh) {
/* 1937 */     int rsh = sh / this.rects.length;
/* 1938 */     int y = (this.rects[0]).y;
/* 1939 */     for (int i = 0; i < this.rects.length; i++) {
/*      */       
/* 1941 */       Component c = this.tabPane.getTabComponentAt(i);
/* 1942 */       if (c != null) {
/* 1943 */         c.setLocation(c.getX(), y + c.getY() - (this.rects[i]).y + rsh / 2);
/*      */       }
/*      */       
/* 1946 */       (this.rects[i]).y = y;
/* 1947 */       (this.rects[i]).height += rsh;
/*      */       
/* 1949 */       y += (this.rects[i]).height;
/*      */     } 
/*      */ 
/*      */     
/* 1953 */     (this.rects[this.rects.length - 1]).height += sh - rsh * this.rects.length;
/*      */   }
/*      */   
/*      */   private int rectsTotalWidth(boolean leftToRight) {
/* 1957 */     int last = this.rects.length - 1;
/* 1958 */     return leftToRight ? (
/* 1959 */       (this.rects[last]).x + (this.rects[last]).width - (this.rects[0]).x) : (
/* 1960 */       (this.rects[0]).x + (this.rects[0]).width - (this.rects[last]).x);
/*      */   }
/*      */   
/*      */   private int rectsTotalHeight() {
/* 1964 */     int last = this.rects.length - 1;
/* 1965 */     return (this.rects[last]).y + (this.rects[last]).height - (this.rects[0]).y;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class TabCloseButton
/*      */     extends JButton
/*      */     implements UIResource
/*      */   {
/*      */     private TabCloseButton() {}
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private class ContainerUIResource
/*      */     extends JPanel
/*      */     implements UIResource
/*      */   {
/*      */     private ContainerUIResource(Component c) {
/* 1985 */       super(new BorderLayout());
/* 1986 */       add(c);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected class FlatTabAreaButton
/*      */     extends FlatArrowButton
/*      */   {
/*      */     public FlatTabAreaButton(int direction) {
/* 1996 */       super(direction, FlatTabbedPaneUI.this.arrowType, FlatTabbedPaneUI.this.foreground, FlatTabbedPaneUI.this.disabledForeground, (Color)null, FlatTabbedPaneUI.this.buttonHoverBackground, (Color)null, FlatTabbedPaneUI.this.buttonPressedBackground);
/*      */ 
/*      */       
/* 1999 */       setArrowWidth(11);
/*      */     }
/*      */     
/*      */     protected void updateStyle() {
/* 2003 */       updateStyle(FlatTabbedPaneUI.this.arrowType, FlatTabbedPaneUI.this.foreground, FlatTabbedPaneUI.this.disabledForeground, (Color)null, FlatTabbedPaneUI.this.buttonHoverBackground, (Color)null, FlatTabbedPaneUI.this.buttonPressedBackground);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Color deriveBackground(Color background) {
/* 2010 */       return FlatUIUtils.deriveColor(background, FlatTabbedPaneUI.this.tabPane.getBackground());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void paint(Graphics g) {
/* 2016 */       if (FlatTabbedPaneUI.this.tabsOpaque || FlatTabbedPaneUI.this.tabPane.isOpaque()) {
/* 2017 */         g.setColor(FlatTabbedPaneUI.this.tabPane.getBackground());
/* 2018 */         g.fillRect(0, 0, getWidth(), getHeight());
/*      */       } 
/*      */       
/* 2021 */       super.paint(g);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void paintBackground(Graphics2D g) {
/* 2027 */       Insets insets = new Insets(0, 0, 0, 0);
/* 2028 */       FlatTabbedPaneUI.rotateInsets(FlatTabbedPaneUI.this.buttonInsets, insets, FlatTabbedPaneUI.this.tabPane.getTabPlacement());
/*      */ 
/*      */       
/* 2031 */       int top = UIScale.scale2(insets.top);
/* 2032 */       int left = UIScale.scale2(insets.left);
/* 2033 */       int bottom = UIScale.scale2(insets.bottom);
/* 2034 */       int right = UIScale.scale2(insets.right);
/*      */       
/* 2036 */       FlatUIUtils.paintComponentBackground(g, left, top, 
/* 2037 */           getWidth() - left - right, 
/* 2038 */           getHeight() - top - bottom, 0.0F, 
/* 2039 */           UIScale.scale(FlatTabbedPaneUI.this.buttonArc));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected class FlatMoreTabsButton
/*      */     extends FlatTabAreaButton
/*      */     implements ActionListener, PopupMenuListener
/*      */   {
/*      */     private boolean popupVisible;
/*      */ 
/*      */     
/*      */     public FlatMoreTabsButton() {
/* 2052 */       super(5);
/*      */       
/* 2054 */       updateDirection();
/* 2055 */       setToolTipText(FlatTabbedPaneUI.this.moreTabsButtonToolTipText);
/* 2056 */       addActionListener(this);
/*      */     }
/*      */     
/*      */     protected void updateDirection() {
/*      */       int direction;
/* 2061 */       switch (FlatTabbedPaneUI.this.tabPane.getTabPlacement()) {
/*      */         default:
/* 2063 */           direction = 5; break;
/* 2064 */         case 3: direction = 1; break;
/* 2065 */         case 2: direction = 3; break;
/* 2066 */         case 4: direction = 7;
/*      */           break;
/*      */       } 
/* 2069 */       setDirection(direction);
/*      */     }
/*      */ 
/*      */     
/*      */     public Dimension getPreferredSize() {
/* 2074 */       Dimension size = super.getPreferredSize();
/* 2075 */       boolean horizontal = (this.direction == 5 || this.direction == 1);
/* 2076 */       int margin = UIScale.scale(8);
/* 2077 */       return new Dimension(size.width + (
/* 2078 */           horizontal ? margin : 0), size.height + (
/* 2079 */           horizontal ? 0 : margin));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void paint(Graphics g) {
/* 2085 */       if (this.direction == 3 || this.direction == 7) {
/* 2086 */         int xoffset = Math.max(UIScale.unscale((getWidth() - getHeight()) / 2) - 4, 0);
/* 2087 */         setXOffset((this.direction == 3) ? xoffset : -xoffset);
/*      */       } else {
/* 2089 */         setXOffset(0.0F);
/*      */       } 
/* 2091 */       super.paint(g);
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean isHover() {
/* 2096 */       return (super.isHover() || this.popupVisible);
/*      */     }
/*      */ 
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/* 2101 */       if (FlatTabbedPaneUI.this.tabViewport == null) {
/*      */         return;
/*      */       }
/*      */       
/* 2105 */       JPopupMenu popupMenu = new JPopupMenu();
/* 2106 */       popupMenu.addPopupMenuListener(this);
/* 2107 */       Rectangle viewRect = FlatTabbedPaneUI.this.tabViewport.getViewRect();
/* 2108 */       int lastIndex = -1;
/* 2109 */       for (int i = 0; i < FlatTabbedPaneUI.this.rects.length; i++) {
/* 2110 */         if (!viewRect.contains(FlatTabbedPaneUI.this.rects[i])) {
/*      */           
/* 2112 */           if (lastIndex >= 0 && lastIndex + 1 != i)
/* 2113 */             popupMenu.addSeparator(); 
/* 2114 */           lastIndex = i;
/*      */ 
/*      */           
/* 2117 */           popupMenu.add(createTabMenuItem(i));
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2122 */       int buttonWidth = getWidth();
/* 2123 */       int buttonHeight = getHeight();
/* 2124 */       Dimension popupSize = popupMenu.getPreferredSize();
/*      */       
/* 2126 */       int x = FlatTabbedPaneUI.this.isLeftToRight() ? (buttonWidth - popupSize.width) : 0;
/* 2127 */       int y = buttonHeight - popupSize.height;
/* 2128 */       switch (FlatTabbedPaneUI.this.tabPane.getTabPlacement()) {
/*      */         default:
/* 2130 */           y = buttonHeight; break;
/* 2131 */         case 3: y = -popupSize.height; break;
/* 2132 */         case 2: x = buttonWidth; break;
/* 2133 */         case 4: x = -popupSize.width;
/*      */           break;
/*      */       } 
/*      */       
/* 2137 */       popupMenu.show(this, x, y);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected JMenuItem createTabMenuItem(int tabIndex) {
/* 2147 */       String title = FlatTabbedPaneUI.this.tabPane.getTitleAt(tabIndex);
/* 2148 */       if (StringUtils.isEmpty(title)) {
/* 2149 */         Component tabComp = FlatTabbedPaneUI.this.tabPane.getTabComponentAt(tabIndex);
/* 2150 */         if (tabComp != null)
/* 2151 */           title = findTabTitle(tabComp); 
/* 2152 */         if (StringUtils.isEmpty(title))
/* 2153 */           title = FlatTabbedPaneUI.this.tabPane.getAccessibleContext().getAccessibleChild(tabIndex).getAccessibleContext().getAccessibleName(); 
/* 2154 */         if (StringUtils.isEmpty(title) && tabComp instanceof Accessible)
/* 2155 */           title = findTabTitleInAccessible((Accessible)tabComp); 
/* 2156 */         if (StringUtils.isEmpty(title)) {
/* 2157 */           title = (tabIndex + 1) + ". Tab";
/*      */         }
/*      */       } 
/* 2160 */       JMenuItem menuItem = new JMenuItem(title, FlatTabbedPaneUI.this.tabPane.getIconAt(tabIndex));
/* 2161 */       menuItem.setDisabledIcon(FlatTabbedPaneUI.this.tabPane.getDisabledIconAt(tabIndex));
/* 2162 */       menuItem.setToolTipText(FlatTabbedPaneUI.this.tabPane.getToolTipTextAt(tabIndex));
/*      */       
/* 2164 */       Color foregroundAt = FlatTabbedPaneUI.this.tabPane.getForegroundAt(tabIndex);
/* 2165 */       if (foregroundAt != FlatTabbedPaneUI.this.tabPane.getForeground()) {
/* 2166 */         menuItem.setForeground(foregroundAt);
/*      */       }
/* 2168 */       Color backgroundAt = FlatTabbedPaneUI.this.tabPane.getBackgroundAt(tabIndex);
/* 2169 */       if (backgroundAt != FlatTabbedPaneUI.this.tabPane.getBackground()) {
/* 2170 */         menuItem.setBackground(backgroundAt);
/* 2171 */         menuItem.setOpaque(true);
/*      */       } 
/*      */       
/* 2174 */       if (!FlatTabbedPaneUI.this.tabPane.isEnabled() || !FlatTabbedPaneUI.this.tabPane.isEnabledAt(tabIndex)) {
/* 2175 */         menuItem.setEnabled(false);
/*      */       }
/* 2177 */       menuItem.addActionListener(e -> selectTab(tabIndex));
/* 2178 */       return menuItem;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private String findTabTitle(Component c) {
/* 2185 */       String title = null;
/* 2186 */       if (c instanceof JLabel) {
/* 2187 */         title = ((JLabel)c).getText();
/* 2188 */       } else if (c instanceof JTextComponent) {
/* 2189 */         title = ((JTextComponent)c).getText();
/*      */       } 
/* 2191 */       if (!StringUtils.isEmpty(title)) {
/* 2192 */         return title;
/*      */       }
/* 2194 */       if (c instanceof Container) {
/* 2195 */         for (Component child : ((Container)c).getComponents()) {
/* 2196 */           title = findTabTitle(child);
/* 2197 */           if (title != null) {
/* 2198 */             return title;
/*      */           }
/*      */         } 
/*      */       }
/* 2202 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private String findTabTitleInAccessible(Accessible accessible) {
/* 2209 */       AccessibleContext context = accessible.getAccessibleContext();
/* 2210 */       if (context == null) {
/* 2211 */         return null;
/*      */       }
/* 2213 */       String title = context.getAccessibleName();
/* 2214 */       if (!StringUtils.isEmpty(title)) {
/* 2215 */         return title;
/*      */       }
/* 2217 */       int childrenCount = context.getAccessibleChildrenCount();
/* 2218 */       for (int i = 0; i < childrenCount; i++) {
/* 2219 */         title = findTabTitleInAccessible(context.getAccessibleChild(i));
/* 2220 */         if (title != null) {
/* 2221 */           return title;
/*      */         }
/*      */       } 
/* 2224 */       return null;
/*      */     }
/*      */     
/*      */     protected void selectTab(int tabIndex) {
/* 2228 */       FlatTabbedPaneUI.this.tabPane.setSelectedIndex(tabIndex);
/* 2229 */       FlatTabbedPaneUI.this.ensureSelectedTabIsVisible();
/*      */     }
/*      */ 
/*      */     
/*      */     public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
/* 2234 */       this.popupVisible = true;
/* 2235 */       repaint();
/*      */     }
/*      */ 
/*      */     
/*      */     public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
/* 2240 */       this.popupVisible = false;
/* 2241 */       repaint();
/*      */     }
/*      */ 
/*      */     
/*      */     public void popupMenuCanceled(PopupMenuEvent e) {
/* 2246 */       this.popupVisible = false;
/* 2247 */       repaint();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected class FlatScrollableTabButton
/*      */     extends FlatTabAreaButton
/*      */     implements MouseListener
/*      */   {
/*      */     private Timer autoRepeatTimer;
/*      */ 
/*      */     
/*      */     protected FlatScrollableTabButton(int direction) {
/* 2260 */       super(direction);
/*      */       
/* 2262 */       addMouseListener(this);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void fireActionPerformed(ActionEvent event) {
/* 2267 */       FlatTabbedPaneUI.this.runWithOriginalLayoutManager(() -> super.fireActionPerformed(event));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void mousePressed(MouseEvent e) {
/* 2274 */       if (SwingUtilities.isLeftMouseButton(e) && isEnabled()) {
/* 2275 */         if (this.autoRepeatTimer == null) {
/*      */           
/* 2277 */           this.autoRepeatTimer = new Timer(60, e2 -> {
/*      */                 if (isEnabled())
/*      */                   doClick(); 
/*      */               });
/* 2281 */           this.autoRepeatTimer.setInitialDelay(300);
/*      */         } 
/*      */         
/* 2284 */         this.autoRepeatTimer.start();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void mouseReleased(MouseEvent e) {
/* 2290 */       if (this.autoRepeatTimer != null) {
/* 2291 */         this.autoRepeatTimer.stop();
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public void mouseClicked(MouseEvent e) {}
/*      */ 
/*      */     
/*      */     public void mouseEntered(MouseEvent e) {
/* 2300 */       if (this.autoRepeatTimer != null && isPressed()) {
/* 2301 */         this.autoRepeatTimer.start();
/*      */       }
/*      */     }
/*      */     
/*      */     public void mouseExited(MouseEvent e) {
/* 2306 */       if (this.autoRepeatTimer != null) {
/* 2307 */         this.autoRepeatTimer.stop();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected class FlatWheelTabScroller
/*      */     extends MouseAdapter
/*      */   {
/*      */     private int lastMouseX;
/*      */     
/*      */     private int lastMouseY;
/*      */     
/*      */     private boolean inViewport;
/*      */     private boolean scrolled;
/*      */     private Timer rolloverTimer;
/*      */     private Timer exitedTimer;
/*      */     private Animator animator;
/*      */     private Point startViewPosition;
/*      */     private Point targetViewPosition;
/*      */     
/*      */     protected void uninstall() {
/* 2329 */       if (this.rolloverTimer != null)
/* 2330 */         this.rolloverTimer.stop(); 
/* 2331 */       if (this.exitedTimer != null)
/* 2332 */         this.exitedTimer.stop(); 
/* 2333 */       if (this.animator != null) {
/* 2334 */         this.animator.cancel();
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public void mouseWheelMoved(MouseWheelEvent e) {
/* 2340 */       if ((FlatTabbedPaneUI.this.tabPane.getMouseWheelListeners()).length > 1) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/* 2345 */       if (!isInViewport(e.getX(), e.getY())) {
/*      */         return;
/*      */       }
/* 2348 */       this.lastMouseX = e.getX();
/* 2349 */       this.lastMouseY = e.getY();
/*      */       
/* 2351 */       double preciseWheelRotation = e.getPreciseWheelRotation();
/* 2352 */       boolean isPreciseWheel = (preciseWheelRotation != 0.0D && preciseWheelRotation != e.getWheelRotation());
/* 2353 */       int amount = (int)(FlatTabbedPaneUI.this.maxTabHeight * preciseWheelRotation);
/*      */ 
/*      */       
/* 2356 */       if (amount == 0) {
/* 2357 */         if (preciseWheelRotation > 0.0D) {
/* 2358 */           amount = 1;
/* 2359 */         } else if (preciseWheelRotation < 0.0D) {
/* 2360 */           amount = -1;
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2366 */       Point viewPosition = (this.targetViewPosition != null) ? this.targetViewPosition : FlatTabbedPaneUI.this.tabViewport.getViewPosition();
/* 2367 */       Dimension viewSize = FlatTabbedPaneUI.this.tabViewport.getViewSize();
/* 2368 */       boolean horizontal = FlatTabbedPaneUI.this.isHorizontalTabPlacement();
/* 2369 */       int x = viewPosition.x;
/* 2370 */       int y = viewPosition.y;
/* 2371 */       if (horizontal) {
/* 2372 */         x += FlatTabbedPaneUI.this.isLeftToRight() ? amount : -amount;
/*      */       } else {
/* 2374 */         y += amount;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2382 */       if ((isPreciseWheel && FlatTabbedPaneUI.this
/* 2383 */         .getScrollButtonsPlacement() == 100 && FlatTabbedPaneUI.this
/* 2384 */         .getScrollButtonsPolicy() == 3 && (FlatTabbedPaneUI.this
/* 2385 */         .isLeftToRight() || !horizontal)) || FlatTabbedPaneUI.this
/* 2386 */         .scrollBackwardButtonPrefSize != null)
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
/* 2397 */         if (horizontal) {
/*      */           
/* 2399 */           if (viewPosition.x == 0 && x > 0) {
/* 2400 */             x += FlatTabbedPaneUI.this.scrollBackwardButtonPrefSize.width;
/* 2401 */           } else if (amount < 0 && x <= FlatTabbedPaneUI.this.scrollBackwardButtonPrefSize.width) {
/* 2402 */             x = 0;
/*      */           } 
/* 2404 */         } else if (viewPosition.y == 0 && y > 0) {
/* 2405 */           y += FlatTabbedPaneUI.this.scrollBackwardButtonPrefSize.height;
/* 2406 */         } else if (amount < 0 && y <= FlatTabbedPaneUI.this.scrollBackwardButtonPrefSize.height) {
/* 2407 */           y = 0;
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/* 2412 */       if (horizontal) {
/* 2413 */         x = Math.min(Math.max(x, 0), viewSize.width - FlatTabbedPaneUI.this.tabViewport.getWidth());
/*      */       } else {
/* 2415 */         y = Math.min(Math.max(y, 0), viewSize.height - FlatTabbedPaneUI.this.tabViewport.getHeight());
/*      */       } 
/*      */       
/* 2418 */       Point newViewPosition = new Point(x, y);
/* 2419 */       if (newViewPosition.equals(viewPosition)) {
/*      */         return;
/*      */       }
/*      */       
/* 2423 */       if (isPreciseWheel) {
/*      */ 
/*      */ 
/*      */         
/* 2427 */         if (this.animator != null) {
/* 2428 */           this.animator.stop();
/*      */         }
/* 2430 */         FlatTabbedPaneUI.this.tabViewport.setViewPosition(newViewPosition);
/* 2431 */         updateRolloverDelayed();
/*      */       } else {
/* 2433 */         setViewPositionAnimated(newViewPosition);
/*      */       } 
/* 2435 */       this.scrolled = true;
/*      */     }
/*      */ 
/*      */     
/*      */     protected void setViewPositionAnimated(Point viewPosition) {
/* 2440 */       if (viewPosition.equals(FlatTabbedPaneUI.this.tabViewport.getViewPosition())) {
/*      */         return;
/*      */       }
/*      */       
/* 2444 */       if (!FlatTabbedPaneUI.this.isSmoothScrollingEnabled()) {
/* 2445 */         FlatTabbedPaneUI.this.tabViewport.setViewPosition(viewPosition);
/* 2446 */         updateRolloverDelayed();
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 2451 */       this.startViewPosition = FlatTabbedPaneUI.this.tabViewport.getViewPosition();
/* 2452 */       this.targetViewPosition = viewPosition;
/*      */ 
/*      */       
/* 2455 */       if (this.animator == null) {
/*      */         
/* 2457 */         int duration = 200;
/* 2458 */         int resolution = 10;
/*      */         
/* 2460 */         this.animator = new Animator(duration, fraction -> {
/*      */               if (FlatTabbedPaneUI.this.tabViewport == null || !FlatTabbedPaneUI.this.tabViewport.isShowing()) {
/*      */                 this.animator.stop();
/*      */                 
/*      */                 return;
/*      */               } 
/*      */               
/*      */               int x = this.startViewPosition.x + Math.round((this.targetViewPosition.x - this.startViewPosition.x) * fraction);
/*      */               
/*      */               int y = this.startViewPosition.y + Math.round((this.targetViewPosition.y - this.startViewPosition.y) * fraction);
/*      */               FlatTabbedPaneUI.this.tabViewport.setViewPosition(new Point(x, y));
/*      */             }() -> {
/*      */               this.startViewPosition = this.targetViewPosition = null;
/*      */               if (FlatTabbedPaneUI.this.tabPane != null) {
/*      */                 FlatTabbedPaneUI.this.setRolloverTab(this.lastMouseX, this.lastMouseY);
/*      */               }
/*      */             });
/* 2477 */         this.animator.setResolution(resolution);
/* 2478 */         this.animator.setInterpolator((Animator.Interpolator)new CubicBezierEasing(0.5F, 0.5F, 0.5F, 1.0F));
/*      */       } 
/*      */ 
/*      */       
/* 2482 */       this.animator.restart();
/*      */     }
/*      */     
/*      */     protected void updateRolloverDelayed() {
/* 2486 */       FlatTabbedPaneUI.this.blockRollover = true;
/*      */ 
/*      */       
/* 2489 */       int oldIndex = FlatTabbedPaneUI.this.getRolloverTab();
/* 2490 */       if (oldIndex >= 0) {
/* 2491 */         int index = FlatTabbedPaneUI.this.tabForCoordinate(FlatTabbedPaneUI.this.tabPane, this.lastMouseX, this.lastMouseY);
/* 2492 */         if (index >= 0 && index != oldIndex) {
/*      */           
/* 2494 */           FlatTabbedPaneUI.this.blockRollover = false;
/* 2495 */           FlatTabbedPaneUI.this.setRolloverTab(-1);
/* 2496 */           FlatTabbedPaneUI.this.blockRollover = true;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2501 */       if (this.rolloverTimer == null) {
/* 2502 */         this.rolloverTimer = new Timer(150, e -> {
/*      */               FlatTabbedPaneUI.this.blockRollover = false;
/*      */               
/*      */               if (FlatTabbedPaneUI.this.tabPane != null) {
/*      */                 FlatTabbedPaneUI.this.setRolloverTab(this.lastMouseX, this.lastMouseY);
/*      */               }
/*      */             });
/* 2509 */         this.rolloverTimer.setRepeats(false);
/*      */       } 
/*      */ 
/*      */       
/* 2513 */       this.rolloverTimer.restart();
/*      */     }
/*      */ 
/*      */     
/*      */     public void mouseMoved(MouseEvent e) {
/* 2518 */       checkViewportExited(e.getX(), e.getY());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void mouseExited(MouseEvent e) {
/* 2525 */       checkViewportExited(e.getX(), e.getY());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void mousePressed(MouseEvent e) {
/* 2531 */       FlatTabbedPaneUI.this.setRolloverTab(e.getX(), e.getY());
/*      */     }
/*      */     
/*      */     protected boolean isInViewport(int x, int y) {
/* 2535 */       return (FlatTabbedPaneUI.this.tabViewport != null && FlatTabbedPaneUI.this.tabViewport.getBounds().contains(x, y));
/*      */     }
/*      */     
/*      */     protected void checkViewportExited(int x, int y) {
/* 2539 */       this.lastMouseX = x;
/* 2540 */       this.lastMouseY = y;
/*      */       
/* 2542 */       boolean wasInViewport = this.inViewport;
/* 2543 */       this.inViewport = isInViewport(x, y);
/*      */       
/* 2545 */       if (this.inViewport != wasInViewport)
/* 2546 */         if (!this.inViewport) {
/* 2547 */           viewportExited();
/* 2548 */         } else if (this.exitedTimer != null) {
/* 2549 */           this.exitedTimer.stop();
/*      */         }  
/*      */     }
/*      */     
/*      */     protected void viewportExited() {
/* 2554 */       if (!this.scrolled) {
/*      */         return;
/*      */       }
/* 2557 */       if (this.exitedTimer == null) {
/* 2558 */         this.exitedTimer = new Timer(500, e -> ensureSelectedTabVisible());
/* 2559 */         this.exitedTimer.setRepeats(false);
/*      */       } 
/*      */       
/* 2562 */       this.exitedTimer.start();
/*      */     }
/*      */ 
/*      */     
/*      */     protected void ensureSelectedTabVisible() {
/* 2567 */       if (FlatTabbedPaneUI.this.tabPane == null || FlatTabbedPaneUI.this.tabViewport == null) {
/*      */         return;
/*      */       }
/* 2570 */       if (!this.scrolled)
/*      */         return; 
/* 2572 */       this.scrolled = false;
/*      */ 
/*      */       
/* 2575 */       FlatTabbedPaneUI.this.ensureSelectedTabIsVisible();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private class Handler
/*      */     implements MouseListener, MouseMotionListener, PropertyChangeListener, ChangeListener, ComponentListener, ContainerListener, FocusListener
/*      */   {
/*      */     MouseListener mouseDelegate;
/*      */     
/*      */     PropertyChangeListener propertyChangeDelegate;
/*      */     
/*      */     ChangeListener changeDelegate;
/*      */     
/*      */     FocusListener focusDelegate;
/* 2590 */     private final PropertyChangeListener contentListener = this::contentPropertyChange;
/*      */     
/* 2592 */     private int pressedTabIndex = -1;
/* 2593 */     private int lastTipTabIndex = -1;
/*      */     private String lastTip;
/*      */     
/*      */     void installListeners() {
/* 2597 */       FlatTabbedPaneUI.this.tabPane.addMouseMotionListener(this);
/* 2598 */       FlatTabbedPaneUI.this.tabPane.addComponentListener(this);
/* 2599 */       FlatTabbedPaneUI.this.tabPane.addContainerListener(this);
/*      */       
/* 2601 */       for (Component c : FlatTabbedPaneUI.this.tabPane.getComponents()) {
/* 2602 */         if (!(c instanceof UIResource))
/* 2603 */           c.addPropertyChangeListener(this.contentListener); 
/*      */       } 
/*      */     }
/*      */     
/*      */     void uninstallListeners() {
/* 2608 */       FlatTabbedPaneUI.this.tabPane.removeMouseMotionListener(this);
/* 2609 */       FlatTabbedPaneUI.this.tabPane.removeComponentListener(this);
/* 2610 */       FlatTabbedPaneUI.this.tabPane.removeContainerListener(this);
/*      */       
/* 2612 */       for (Component c : FlatTabbedPaneUI.this.tabPane.getComponents()) {
/* 2613 */         if (!(c instanceof UIResource)) {
/* 2614 */           c.removePropertyChangeListener(this.contentListener);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void mouseClicked(MouseEvent e) {
/* 2622 */       this.mouseDelegate.mouseClicked(e);
/*      */     }
/*      */ 
/*      */     
/*      */     public void mousePressed(MouseEvent e) {
/* 2627 */       updateRollover(e);
/*      */       
/* 2629 */       if (!FlatTabbedPaneUI.this.isPressedTabClose() && SwingUtilities.isLeftMouseButton(e)) {
/* 2630 */         this.mouseDelegate.mousePressed(e);
/*      */       }
/*      */     }
/*      */     
/*      */     public void mouseReleased(MouseEvent e) {
/* 2635 */       if (FlatTabbedPaneUI.this.isPressedTabClose()) {
/* 2636 */         updateRollover(e);
/* 2637 */         if (this.pressedTabIndex >= 0 && this.pressedTabIndex == FlatTabbedPaneUI.this.getRolloverTab()) {
/* 2638 */           restoreTabToolTip();
/* 2639 */           FlatTabbedPaneUI.this.closeTab(this.pressedTabIndex);
/*      */         } 
/*      */       } else {
/* 2642 */         this.mouseDelegate.mouseReleased(e);
/*      */       } 
/* 2644 */       this.pressedTabIndex = -1;
/* 2645 */       updateRollover(e);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void mouseEntered(MouseEvent e) {
/* 2651 */       updateRollover(e);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void mouseExited(MouseEvent e) {
/* 2659 */       updateRollover(e);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void mouseDragged(MouseEvent e) {
/* 2666 */       updateRollover(e);
/*      */     }
/*      */ 
/*      */     
/*      */     public void mouseMoved(MouseEvent e) {
/* 2671 */       updateRollover(e);
/*      */     }
/*      */     
/*      */     private void updateRollover(MouseEvent e) {
/* 2675 */       int x = e.getX();
/* 2676 */       int y = e.getY();
/*      */       
/* 2678 */       int tabIndex = FlatTabbedPaneUI.this.tabForCoordinate(FlatTabbedPaneUI.this.tabPane, x, y);
/*      */       
/* 2680 */       FlatTabbedPaneUI.this.setRolloverTab(tabIndex);
/*      */ 
/*      */       
/* 2683 */       boolean hitClose = (FlatTabbedPaneUI.this.isTabClosable(tabIndex) && FlatTabbedPaneUI.this.getTabCloseHitArea(tabIndex).contains(x, y));
/* 2684 */       if (e.getID() == 501 && SwingUtilities.isLeftMouseButton(e))
/* 2685 */         this.pressedTabIndex = hitClose ? tabIndex : -1; 
/* 2686 */       FlatTabbedPaneUI.this.setRolloverTabClose(hitClose);
/* 2687 */       FlatTabbedPaneUI.this.setPressedTabClose((hitClose && tabIndex == this.pressedTabIndex));
/*      */ 
/*      */       
/* 2690 */       if (tabIndex >= 0 && hitClose)
/* 2691 */       { Object closeTip = FlatTabbedPaneUI.this.getTabClientProperty(tabIndex, "JTabbedPane.tabCloseToolTipText");
/* 2692 */         if (closeTip == null)
/* 2693 */           closeTip = FlatTabbedPaneUI.this.tabCloseToolTipText; 
/* 2694 */         if (closeTip instanceof String) {
/* 2695 */           setCloseToolTip(tabIndex, (String)closeTip);
/*      */         } else {
/* 2697 */           restoreTabToolTip();
/*      */         }  }
/* 2699 */       else { restoreTabToolTip(); }
/*      */     
/*      */     }
/*      */     private void setCloseToolTip(int tabIndex, String closeTip) {
/* 2703 */       if (tabIndex == this.lastTipTabIndex) {
/*      */         return;
/*      */       }
/* 2706 */       restoreTabToolTip();
/*      */       
/* 2708 */       this.lastTipTabIndex = tabIndex;
/* 2709 */       this.lastTip = FlatTabbedPaneUI.this.tabPane.getToolTipTextAt(this.lastTipTabIndex);
/* 2710 */       FlatTabbedPaneUI.this.tabPane.setToolTipTextAt(this.lastTipTabIndex, closeTip);
/*      */     }
/*      */     
/*      */     private void restoreTabToolTip() {
/* 2714 */       if (this.lastTipTabIndex < 0) {
/*      */         return;
/*      */       }
/* 2717 */       if (this.lastTipTabIndex < FlatTabbedPaneUI.this.tabPane.getTabCount())
/* 2718 */         FlatTabbedPaneUI.this.tabPane.setToolTipTextAt(this.lastTipTabIndex, this.lastTip); 
/* 2719 */       this.lastTip = null;
/* 2720 */       this.lastTipTabIndex = -1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void propertyChange(PropertyChangeEvent e) {
/* 2728 */       switch (e.getPropertyName()) {
/*      */         case "tabPlacement":
/*      */         case "opaque":
/*      */         case "background":
/*      */         case "indexForTabComponent":
/* 2733 */           FlatTabbedPaneUI.this.runWithOriginalLayoutManager(() -> this.propertyChangeDelegate.propertyChange(e));
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/* 2739 */           this.propertyChangeDelegate.propertyChange(e);
/*      */           break;
/*      */       } 
/*      */ 
/*      */       
/* 2744 */       switch (e.getPropertyName()) {
/*      */         case "tabPlacement":
/* 2746 */           if (FlatTabbedPaneUI.this.moreTabsButton instanceof FlatTabbedPaneUI.FlatMoreTabsButton) {
/* 2747 */             ((FlatTabbedPaneUI.FlatMoreTabsButton)FlatTabbedPaneUI.this.moreTabsButton).updateDirection();
/*      */           }
/*      */           break;
/*      */         case "componentOrientation":
/* 2751 */           FlatTabbedPaneUI.this.ensureSelectedTabIsVisibleLater();
/*      */           break;
/*      */         
/*      */         case "JTabbedPane.showTabSeparators":
/*      */         case "JTabbedPane.tabType":
/* 2756 */           FlatTabbedPaneUI.this.tabPane.repaint();
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case "JTabbedPane.showContentSeparator":
/*      */         case "JTabbedPane.hasFullBorder":
/*      */         case "JTabbedPane.hideTabAreaWithOneTab":
/*      */         case "JTabbedPane.minimumTabWidth":
/*      */         case "JTabbedPane.maximumTabWidth":
/*      */         case "JTabbedPane.tabHeight":
/*      */         case "JTabbedPane.tabInsets":
/*      */         case "JTabbedPane.tabAreaInsets":
/*      */         case "JTabbedPane.tabsPopupPolicy":
/*      */         case "JTabbedPane.scrollButtonsPolicy":
/*      */         case "JTabbedPane.scrollButtonsPlacement":
/*      */         case "JTabbedPane.tabAreaAlignment":
/*      */         case "JTabbedPane.tabAlignment":
/*      */         case "JTabbedPane.tabWidthMode":
/*      */         case "JTabbedPane.tabIconPlacement":
/*      */         case "JTabbedPane.tabClosable":
/* 2777 */           FlatTabbedPaneUI.this.tabPane.revalidate();
/* 2778 */           FlatTabbedPaneUI.this.tabPane.repaint();
/*      */           break;
/*      */         
/*      */         case "JTabbedPane.leadingComponent":
/* 2782 */           FlatTabbedPaneUI.this.uninstallLeadingComponent();
/* 2783 */           FlatTabbedPaneUI.this.installLeadingComponent();
/* 2784 */           FlatTabbedPaneUI.this.tabPane.revalidate();
/* 2785 */           FlatTabbedPaneUI.this.tabPane.repaint();
/* 2786 */           FlatTabbedPaneUI.this.ensureSelectedTabIsVisibleLater();
/*      */           break;
/*      */         
/*      */         case "JTabbedPane.trailingComponent":
/* 2790 */           FlatTabbedPaneUI.this.uninstallTrailingComponent();
/* 2791 */           FlatTabbedPaneUI.this.installTrailingComponent();
/* 2792 */           FlatTabbedPaneUI.this.tabPane.revalidate();
/* 2793 */           FlatTabbedPaneUI.this.tabPane.repaint();
/* 2794 */           FlatTabbedPaneUI.this.ensureSelectedTabIsVisibleLater();
/*      */           break;
/*      */         
/*      */         case "FlatLaf.style":
/*      */         case "FlatLaf.styleClass":
/* 2799 */           FlatTabbedPaneUI.this.installStyle();
/* 2800 */           FlatTabbedPaneUI.this.tabPane.revalidate();
/* 2801 */           FlatTabbedPaneUI.this.tabPane.repaint();
/*      */           break;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void stateChanged(ChangeEvent e) {
/* 2810 */       this.changeDelegate.stateChanged(e);
/*      */ 
/*      */       
/* 2813 */       if (FlatTabbedPaneUI.this.moreTabsButton != null)
/* 2814 */         FlatTabbedPaneUI.this.ensureSelectedTabIsVisible(); 
/*      */     }
/*      */     
/*      */     protected void contentPropertyChange(PropertyChangeEvent e) {
/* 2818 */       switch (e.getPropertyName()) {
/*      */         case "JTabbedPane.minimumTabWidth":
/*      */         case "JTabbedPane.maximumTabWidth":
/*      */         case "JTabbedPane.tabInsets":
/*      */         case "JTabbedPane.tabAlignment":
/*      */         case "JTabbedPane.tabClosable":
/* 2824 */           FlatTabbedPaneUI.this.tabPane.revalidate();
/* 2825 */           FlatTabbedPaneUI.this.tabPane.repaint();
/*      */           break;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void componentResized(ComponentEvent e) {
/* 2835 */       FlatTabbedPaneUI.this.ensureSelectedTabIsVisibleLater();
/*      */     }
/*      */ 
/*      */     
/*      */     public void componentMoved(ComponentEvent e) {}
/*      */     
/*      */     public void componentShown(ComponentEvent e) {}
/*      */     
/*      */     public void componentHidden(ComponentEvent e) {}
/*      */     
/*      */     public void componentAdded(ContainerEvent e) {
/* 2846 */       Component c = e.getChild();
/* 2847 */       if (!(c instanceof UIResource)) {
/* 2848 */         c.addPropertyChangeListener(this.contentListener);
/*      */       }
/*      */     }
/*      */     
/*      */     public void componentRemoved(ContainerEvent e) {
/* 2853 */       Component c = e.getChild();
/* 2854 */       if (!(c instanceof UIResource)) {
/* 2855 */         c.removePropertyChangeListener(this.contentListener);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void focusGained(FocusEvent e) {
/* 2862 */       this.focusDelegate.focusGained(e);
/* 2863 */       FlatTabbedPaneUI.this.repaintTab(FlatTabbedPaneUI.this.tabPane.getSelectedIndex());
/*      */     }
/*      */ 
/*      */     
/*      */     public void focusLost(FocusEvent e) {
/* 2868 */       this.focusDelegate.focusLost(e);
/* 2869 */       FlatTabbedPaneUI.this.repaintTab(FlatTabbedPaneUI.this.tabPane.getSelectedIndex());
/*      */     }
/*      */ 
/*      */     
/*      */     private Handler() {}
/*      */   }
/*      */   
/*      */   protected class FlatTabbedPaneLayout
/*      */     extends BasicTabbedPaneUI.TabbedPaneLayout
/*      */   {
/*      */     protected Dimension calculateSize(boolean minimum) {
/* 2880 */       if (isContentEmpty()) {
/* 2881 */         return calculateTabAreaSize();
/*      */       }
/* 2883 */       return super.calculateSize(minimum);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isContentEmpty() {
/* 2893 */       int tabCount = FlatTabbedPaneUI.this.tabPane.getTabCount();
/* 2894 */       if (tabCount == 0) {
/* 2895 */         return false;
/*      */       }
/* 2897 */       for (int i = 0; i < tabCount; i++) {
/* 2898 */         Component c = FlatTabbedPaneUI.this.tabPane.getComponentAt(i);
/* 2899 */         if (c != null) {
/* 2900 */           Dimension cs = c.getPreferredSize();
/* 2901 */           if (cs.width != 0 || cs.height != 0) {
/* 2902 */             return false;
/*      */           }
/*      */         } 
/*      */       } 
/* 2906 */       return true;
/*      */     }
/*      */     
/*      */     protected Dimension calculateTabAreaSize() {
/* 2910 */       boolean horizontal = FlatTabbedPaneUI.this.isHorizontalTabPlacement();
/* 2911 */       int tabPlacement = FlatTabbedPaneUI.this.tabPane.getTabPlacement();
/* 2912 */       FontMetrics metrics = FlatTabbedPaneUI.this.getFontMetrics();
/* 2913 */       int fontHeight = metrics.getHeight();
/*      */ 
/*      */       
/* 2916 */       int width = 0;
/* 2917 */       int height = 0;
/* 2918 */       int tabCount = FlatTabbedPaneUI.this.tabPane.getTabCount();
/* 2919 */       for (int i = 0; i < tabCount; i++) {
/* 2920 */         if (horizontal) {
/* 2921 */           width += FlatTabbedPaneUI.this.calculateTabWidth(tabPlacement, i, metrics);
/* 2922 */           height = Math.max(height, FlatTabbedPaneUI.this.calculateTabHeight(tabPlacement, i, fontHeight));
/*      */         } else {
/* 2924 */           width = Math.max(width, FlatTabbedPaneUI.this.calculateTabWidth(tabPlacement, i, metrics));
/* 2925 */           height += FlatTabbedPaneUI.this.calculateTabHeight(tabPlacement, i, fontHeight);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2930 */       if (horizontal) {
/* 2931 */         height += UIScale.scale(FlatTabbedPaneUI.this.contentSeparatorHeight);
/*      */       } else {
/* 2933 */         width += UIScale.scale(FlatTabbedPaneUI.this.contentSeparatorHeight);
/*      */       } 
/*      */       
/* 2936 */       Insets insets = FlatTabbedPaneUI.this.tabPane.getInsets();
/* 2937 */       Insets tabAreaInsets = FlatTabbedPaneUI.this.getTabAreaInsets(tabPlacement);
/* 2938 */       return new Dimension(width + insets.left + insets.right + tabAreaInsets.left + tabAreaInsets.right, height + insets.bottom + insets.top + tabAreaInsets.top + tabAreaInsets.bottom);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void layoutContainer(Container parent) {
/* 2945 */       super.layoutContainer(parent);
/*      */       
/* 2947 */       Rectangle bounds = FlatTabbedPaneUI.this.tabPane.getBounds();
/* 2948 */       Insets insets = FlatTabbedPaneUI.this.tabPane.getInsets();
/* 2949 */       int tabPlacement = FlatTabbedPaneUI.this.tabPane.getTabPlacement();
/* 2950 */       int tabAreaAlignment = FlatTabbedPaneUI.this.getTabAreaAlignment();
/* 2951 */       Insets tabAreaInsets = FlatTabbedPaneUI.this.getRealTabAreaInsets(tabPlacement);
/* 2952 */       boolean leftToRight = FlatTabbedPaneUI.this.isLeftToRight();
/*      */ 
/*      */       
/* 2955 */       if (tabPlacement == 1 || tabPlacement == 3) {
/*      */         
/* 2957 */         if (!leftToRight) {
/* 2958 */           FlatTabbedPaneUI.this.shiftTabs(insets.left + tabAreaInsets.right + FlatTabbedPaneUI.this.getTrailingPreferredWidth(), 0);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2963 */         int tabAreaHeight = (FlatTabbedPaneUI.this.maxTabHeight > 0) ? FlatTabbedPaneUI.this.maxTabHeight : Math.max(
/* 2964 */             Math.max(FlatTabbedPaneUI.this.getLeadingPreferredHeight(), FlatTabbedPaneUI.this.getTrailingPreferredHeight()), 
/* 2965 */             UIScale.scale(FlatClientProperties.clientPropertyInt(FlatTabbedPaneUI.this.tabPane, "JTabbedPane.tabHeight", FlatTabbedPaneUI.this.tabHeight)));
/*      */ 
/*      */         
/* 2968 */         int tx = insets.left;
/*      */ 
/*      */         
/* 2971 */         int ty = (tabPlacement == 1) ? (insets.top + tabAreaInsets.top) : (bounds.height - insets.bottom - tabAreaInsets.bottom - tabAreaHeight);
/* 2972 */         int tw = bounds.width - insets.left - insets.right;
/* 2973 */         int th = tabAreaHeight;
/*      */         
/* 2975 */         int leadingWidth = FlatTabbedPaneUI.this.getLeadingPreferredWidth();
/* 2976 */         int trailingWidth = FlatTabbedPaneUI.this.getTrailingPreferredWidth();
/*      */ 
/*      */         
/* 2979 */         if (FlatTabbedPaneUI.this.runCount == 1 && FlatTabbedPaneUI.this.rects.length > 0) {
/* 2980 */           int availWidth = tw - leadingWidth - trailingWidth - tabAreaInsets.left - tabAreaInsets.right;
/* 2981 */           int totalTabWidth = FlatTabbedPaneUI.this.rectsTotalWidth(leftToRight);
/* 2982 */           int diff = availWidth - totalTabWidth;
/*      */           
/* 2984 */           switch (tabAreaAlignment) {
/*      */             case 10:
/* 2986 */               trailingWidth += diff;
/*      */               break;
/*      */             
/*      */             case 11:
/* 2990 */               FlatTabbedPaneUI.this.shiftTabs(leftToRight ? diff : -diff, 0);
/* 2991 */               leadingWidth += diff;
/*      */               break;
/*      */             
/*      */             case 0:
/* 2995 */               FlatTabbedPaneUI.this.shiftTabs((leftToRight ? diff : -diff) / 2, 0);
/* 2996 */               leadingWidth += diff / 2;
/* 2997 */               trailingWidth += diff - diff / 2;
/*      */               break;
/*      */             
/*      */             case 100:
/* 3001 */               FlatTabbedPaneUI.this.stretchTabsWidth(diff, leftToRight);
/*      */               break;
/*      */           } 
/* 3004 */         } else if (FlatTabbedPaneUI.this.rects.length == 0) {
/* 3005 */           trailingWidth = tw - leadingWidth;
/*      */         } 
/*      */         
/* 3008 */         Container leftComponent = leftToRight ? FlatTabbedPaneUI.this.leadingComponent : FlatTabbedPaneUI.this.trailingComponent;
/* 3009 */         if (leftComponent != null) {
/* 3010 */           int leftWidth = leftToRight ? leadingWidth : trailingWidth;
/* 3011 */           leftComponent.setBounds(tx, ty, leftWidth, th);
/*      */         } 
/*      */ 
/*      */         
/* 3015 */         Container rightComponent = leftToRight ? FlatTabbedPaneUI.this.trailingComponent : FlatTabbedPaneUI.this.leadingComponent;
/* 3016 */         if (rightComponent != null) {
/* 3017 */           int rightWidth = leftToRight ? trailingWidth : leadingWidth;
/* 3018 */           rightComponent.setBounds(tx + tw - rightWidth, ty, rightWidth, th);
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 3024 */         int tabAreaWidth = (FlatTabbedPaneUI.this.maxTabWidth > 0) ? FlatTabbedPaneUI.this.maxTabWidth : Math.max(FlatTabbedPaneUI.this.getLeadingPreferredWidth(), FlatTabbedPaneUI.this.getTrailingPreferredWidth());
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3029 */         int tx = (tabPlacement == 2) ? (insets.left + tabAreaInsets.left) : (bounds.width - insets.right - tabAreaInsets.right - tabAreaWidth);
/* 3030 */         int ty = insets.top;
/* 3031 */         int tw = tabAreaWidth;
/* 3032 */         int th = bounds.height - insets.top - insets.bottom;
/*      */         
/* 3034 */         int topHeight = FlatTabbedPaneUI.this.getLeadingPreferredHeight();
/* 3035 */         int bottomHeight = FlatTabbedPaneUI.this.getTrailingPreferredHeight();
/*      */ 
/*      */         
/* 3038 */         if (FlatTabbedPaneUI.this.runCount == 1 && FlatTabbedPaneUI.this.rects.length > 0) {
/* 3039 */           int availHeight = th - topHeight - bottomHeight - tabAreaInsets.top - tabAreaInsets.bottom;
/* 3040 */           int totalTabHeight = FlatTabbedPaneUI.this.rectsTotalHeight();
/* 3041 */           int diff = availHeight - totalTabHeight;
/*      */           
/* 3043 */           switch (tabAreaAlignment) {
/*      */             case 10:
/* 3045 */               bottomHeight += diff;
/*      */               break;
/*      */             
/*      */             case 11:
/* 3049 */               FlatTabbedPaneUI.this.shiftTabs(0, diff);
/* 3050 */               topHeight += diff;
/*      */               break;
/*      */             
/*      */             case 0:
/* 3054 */               FlatTabbedPaneUI.this.shiftTabs(0, diff / 2);
/* 3055 */               topHeight += diff / 2;
/* 3056 */               bottomHeight += diff - diff / 2;
/*      */               break;
/*      */             
/*      */             case 100:
/* 3060 */               FlatTabbedPaneUI.this.stretchTabsHeight(diff);
/*      */               break;
/*      */           } 
/* 3063 */         } else if (FlatTabbedPaneUI.this.rects.length == 0) {
/* 3064 */           bottomHeight = th - topHeight;
/*      */         } 
/*      */         
/* 3067 */         if (FlatTabbedPaneUI.this.leadingComponent != null) {
/* 3068 */           FlatTabbedPaneUI.this.leadingComponent.setBounds(tx, ty, tw, topHeight);
/*      */         }
/*      */         
/* 3071 */         if (FlatTabbedPaneUI.this.trailingComponent != null) {
/* 3072 */           FlatTabbedPaneUI.this.trailingComponent.setBounds(tx, ty + th - bottomHeight, tw, bottomHeight);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class FlatTabbedPaneScrollLayout
/*      */     extends FlatTabbedPaneLayout
/*      */     implements LayoutManager
/*      */   {
/*      */     private final BasicTabbedPaneUI.TabbedPaneLayout delegate;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected FlatTabbedPaneScrollLayout(BasicTabbedPaneUI.TabbedPaneLayout delegate) {
/* 3095 */       this.delegate = delegate;
/*      */     }
/*      */ 
/*      */     
/*      */     public void calculateLayoutInfo() {
/* 3100 */       this.delegate.calculateLayoutInfo();
/*      */     }
/*      */ 
/*      */     
/*      */     protected Dimension calculateTabAreaSize() {
/* 3105 */       Dimension size = super.calculateTabAreaSize();
/*      */ 
/*      */       
/* 3108 */       if (FlatTabbedPaneUI.this.isHorizontalTabPlacement()) {
/* 3109 */         size.width = Math.min(size.width, UIScale.scale(100));
/*      */       } else {
/* 3111 */         size.height = Math.min(size.height, UIScale.scale(100));
/* 3112 */       }  return size;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Dimension preferredLayoutSize(Container parent) {
/* 3119 */       if (isContentEmpty()) {
/* 3120 */         return calculateTabAreaSize();
/*      */       }
/* 3122 */       return this.delegate.preferredLayoutSize(parent);
/*      */     }
/*      */ 
/*      */     
/*      */     public Dimension minimumLayoutSize(Container parent) {
/* 3127 */       if (isContentEmpty()) {
/* 3128 */         return calculateTabAreaSize();
/*      */       }
/* 3130 */       return this.delegate.minimumLayoutSize(parent);
/*      */     }
/*      */ 
/*      */     
/*      */     public void addLayoutComponent(String name, Component comp) {
/* 3135 */       this.delegate.addLayoutComponent(name, comp);
/*      */     }
/*      */ 
/*      */     
/*      */     public void removeLayoutComponent(Component comp) {
/* 3140 */       this.delegate.removeLayoutComponent(comp);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void layoutContainer(Container parent) {
/* 3149 */       FlatTabbedPaneUI.this.runWithOriginalLayoutManager(() -> this.delegate.layoutContainer(parent));
/*      */ 
/*      */ 
/*      */       
/* 3153 */       int tabsPopupPolicy = FlatTabbedPaneUI.this.getTabsPopupPolicy();
/* 3154 */       int scrollButtonsPolicy = FlatTabbedPaneUI.this.getScrollButtonsPolicy();
/* 3155 */       int scrollButtonsPlacement = FlatTabbedPaneUI.this.getScrollButtonsPlacement();
/*      */       
/* 3157 */       boolean useMoreTabsButton = (tabsPopupPolicy == 2);
/* 3158 */       boolean useScrollButtons = (scrollButtonsPolicy == 2 || scrollButtonsPolicy == 3);
/* 3159 */       boolean hideDisabledScrollButtons = (scrollButtonsPolicy == 3 && scrollButtonsPlacement == 100);
/* 3160 */       boolean trailingScrollButtons = (scrollButtonsPlacement == 11);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3165 */       boolean leftToRight = FlatTabbedPaneUI.this.isLeftToRight();
/* 3166 */       if (!leftToRight && FlatTabbedPaneUI.this.isHorizontalTabPlacement()) {
/* 3167 */         useMoreTabsButton = true;
/* 3168 */         useScrollButtons = false;
/*      */       } 
/*      */ 
/*      */       
/* 3172 */       JButton backwardButton = null;
/* 3173 */       JButton forwardButton = null;
/* 3174 */       for (Component c : FlatTabbedPaneUI.this.tabPane.getComponents()) {
/* 3175 */         if (c instanceof FlatTabbedPaneUI.FlatScrollableTabButton) {
/* 3176 */           int direction = ((FlatTabbedPaneUI.FlatScrollableTabButton)c).getDirection();
/* 3177 */           if (direction == 7 || direction == 1) {
/* 3178 */             backwardButton = (JButton)c;
/* 3179 */           } else if (direction == 3 || direction == 5) {
/* 3180 */             forwardButton = (JButton)c;
/*      */           } 
/*      */         } 
/*      */       } 
/* 3184 */       if (backwardButton == null || forwardButton == null) {
/*      */         return;
/*      */       }
/* 3187 */       Rectangle bounds = FlatTabbedPaneUI.this.tabPane.getBounds();
/* 3188 */       Insets insets = FlatTabbedPaneUI.this.tabPane.getInsets();
/* 3189 */       int tabPlacement = FlatTabbedPaneUI.this.tabPane.getTabPlacement();
/* 3190 */       int tabAreaAlignment = FlatTabbedPaneUI.this.getTabAreaAlignment();
/* 3191 */       Insets tabAreaInsets = FlatTabbedPaneUI.this.getRealTabAreaInsets(tabPlacement);
/* 3192 */       boolean moreTabsButtonVisible = false;
/* 3193 */       boolean backwardButtonVisible = false;
/* 3194 */       boolean forwardButtonVisible = false;
/*      */ 
/*      */ 
/*      */       
/* 3198 */       if (tabAreaInsets.left != 0 || tabAreaInsets.top != 0) {
/*      */         
/* 3200 */         FlatTabbedPaneUI.this.shiftTabs(-tabAreaInsets.left, -tabAreaInsets.top);
/*      */ 
/*      */         
/* 3203 */         Component view = FlatTabbedPaneUI.this.tabViewport.getView();
/* 3204 */         Dimension viewSize = view.getPreferredSize();
/* 3205 */         boolean horizontal = (tabPlacement == 1 || tabPlacement == 3);
/* 3206 */         view.setPreferredSize(new Dimension(viewSize.width - (
/* 3207 */               horizontal ? tabAreaInsets.left : 0), viewSize.height - (
/* 3208 */               horizontal ? 0 : tabAreaInsets.top)));
/*      */       } 
/*      */ 
/*      */       
/* 3212 */       if (tabPlacement == 1 || tabPlacement == 3) {
/*      */         
/* 3214 */         if (useScrollButtons && hideDisabledScrollButtons) {
/* 3215 */           Point viewPosition = FlatTabbedPaneUI.this.tabViewport.getViewPosition();
/* 3216 */           if (viewPosition.x <= (backwardButton.getPreferredSize()).width) {
/* 3217 */             FlatTabbedPaneUI.this.tabViewport.setViewPosition(new Point(0, viewPosition.y));
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 3223 */         int tabAreaHeight = (FlatTabbedPaneUI.this.maxTabHeight > 0) ? FlatTabbedPaneUI.this.maxTabHeight : Math.max(
/* 3224 */             Math.max(FlatTabbedPaneUI.this.getLeadingPreferredHeight(), FlatTabbedPaneUI.this.getTrailingPreferredHeight()), 
/* 3225 */             UIScale.scale(FlatClientProperties.clientPropertyInt(FlatTabbedPaneUI.this.tabPane, "JTabbedPane.tabHeight", FlatTabbedPaneUI.this.tabHeight)));
/*      */ 
/*      */         
/* 3228 */         int tx = insets.left;
/*      */ 
/*      */         
/* 3231 */         int ty = (tabPlacement == 1) ? (insets.top + tabAreaInsets.top) : (bounds.height - insets.bottom - tabAreaInsets.bottom - tabAreaHeight);
/* 3232 */         int tw = bounds.width - insets.left - insets.right;
/* 3233 */         int th = tabAreaHeight;
/*      */         
/* 3235 */         int leadingWidth = FlatTabbedPaneUI.this.getLeadingPreferredWidth();
/* 3236 */         int trailingWidth = FlatTabbedPaneUI.this.getTrailingPreferredWidth();
/* 3237 */         int availWidth = tw - leadingWidth - trailingWidth - tabAreaInsets.left - tabAreaInsets.right;
/* 3238 */         int totalTabWidth = (FlatTabbedPaneUI.this.rects.length > 0) ? FlatTabbedPaneUI.this.rectsTotalWidth(leftToRight) : 0;
/*      */ 
/*      */         
/* 3241 */         if (totalTabWidth < availWidth && FlatTabbedPaneUI.this.rects.length > 0) {
/* 3242 */           int diff = availWidth - totalTabWidth;
/* 3243 */           switch (tabAreaAlignment) {
/*      */             case 10:
/* 3245 */               trailingWidth += diff;
/*      */               break;
/*      */             
/*      */             case 11:
/* 3249 */               leadingWidth += diff;
/*      */               break;
/*      */             
/*      */             case 0:
/* 3253 */               leadingWidth += diff / 2;
/* 3254 */               trailingWidth += diff - diff / 2;
/*      */               break;
/*      */             
/*      */             case 100:
/* 3258 */               FlatTabbedPaneUI.this.stretchTabsWidth(diff, leftToRight);
/* 3259 */               totalTabWidth = FlatTabbedPaneUI.this.rectsTotalWidth(leftToRight);
/*      */               break;
/*      */           } 
/* 3262 */         } else if (FlatTabbedPaneUI.this.rects.length == 0) {
/* 3263 */           trailingWidth = tw - leadingWidth;
/*      */         } 
/*      */         
/* 3266 */         Container leftComponent = leftToRight ? FlatTabbedPaneUI.this.leadingComponent : FlatTabbedPaneUI.this.trailingComponent;
/* 3267 */         int leftWidth = leftToRight ? leadingWidth : trailingWidth;
/* 3268 */         if (leftComponent != null) {
/* 3269 */           leftComponent.setBounds(tx, ty, leftWidth, th);
/*      */         }
/*      */         
/* 3272 */         Container rightComponent = leftToRight ? FlatTabbedPaneUI.this.trailingComponent : FlatTabbedPaneUI.this.leadingComponent;
/* 3273 */         int rightWidth = leftToRight ? trailingWidth : leadingWidth;
/* 3274 */         if (rightComponent != null) {
/* 3275 */           rightComponent.setBounds(tx + tw - rightWidth, ty, rightWidth, th);
/*      */         }
/*      */         
/* 3278 */         if (FlatTabbedPaneUI.this.rects.length > 0) {
/* 3279 */           int txi = tx + leftWidth + (leftToRight ? tabAreaInsets.left : tabAreaInsets.right);
/* 3280 */           int twi = tw - leftWidth - rightWidth - tabAreaInsets.left - tabAreaInsets.right;
/*      */ 
/*      */           
/* 3283 */           int x = txi;
/* 3284 */           int w = twi;
/*      */           
/* 3286 */           if (w < totalTabWidth) {
/*      */ 
/*      */ 
/*      */             
/* 3290 */             if (useMoreTabsButton) {
/* 3291 */               int buttonWidth = (FlatTabbedPaneUI.this.moreTabsButton.getPreferredSize()).width;
/* 3292 */               FlatTabbedPaneUI.this.moreTabsButton.setBounds(leftToRight ? (x + w - buttonWidth) : x, ty, buttonWidth, th);
/* 3293 */               x += leftToRight ? 0 : buttonWidth;
/* 3294 */               w -= buttonWidth;
/* 3295 */               moreTabsButtonVisible = true;
/*      */             } 
/* 3297 */             if (useScrollButtons) {
/*      */               
/* 3299 */               if (!hideDisabledScrollButtons || forwardButton.isEnabled()) {
/* 3300 */                 int buttonWidth = (forwardButton.getPreferredSize()).width;
/* 3301 */                 forwardButton.setBounds(leftToRight ? (x + w - buttonWidth) : x, ty, buttonWidth, th);
/* 3302 */                 x += leftToRight ? 0 : buttonWidth;
/* 3303 */                 w -= buttonWidth;
/* 3304 */                 forwardButtonVisible = true;
/*      */               } 
/*      */ 
/*      */               
/* 3308 */               if (!hideDisabledScrollButtons || backwardButton.isEnabled()) {
/* 3309 */                 int buttonWidth = (backwardButton.getPreferredSize()).width;
/* 3310 */                 if (trailingScrollButtons) {
/*      */                   
/* 3312 */                   backwardButton.setBounds(leftToRight ? (x + w - buttonWidth) : x, ty, buttonWidth, th);
/* 3313 */                   x += leftToRight ? 0 : buttonWidth;
/*      */                 } else {
/*      */                   
/* 3316 */                   backwardButton.setBounds(leftToRight ? x : (x + w - buttonWidth), ty, buttonWidth, th);
/* 3317 */                   x += leftToRight ? buttonWidth : 0;
/*      */                 } 
/* 3319 */                 w -= buttonWidth;
/* 3320 */                 backwardButtonVisible = true;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */           
/* 3325 */           FlatTabbedPaneUI.this.tabViewport.setBounds(x, ty, w, th);
/*      */           
/* 3327 */           if (!leftToRight) {
/*      */             
/* 3329 */             FlatTabbedPaneUI.this.tabViewport.doLayout();
/*      */ 
/*      */             
/* 3332 */             FlatTabbedPaneUI.this.shiftTabs(FlatTabbedPaneUI.this.tabViewport.getView().getWidth() - (FlatTabbedPaneUI.this.rects[0]).x + (FlatTabbedPaneUI.this.rects[0]).width, 0);
/*      */           } 
/*      */         } 
/*      */       } else {
/*      */         
/* 3337 */         if (useScrollButtons && hideDisabledScrollButtons) {
/* 3338 */           Point viewPosition = FlatTabbedPaneUI.this.tabViewport.getViewPosition();
/* 3339 */           if (viewPosition.y <= (backwardButton.getPreferredSize()).height) {
/* 3340 */             FlatTabbedPaneUI.this.tabViewport.setViewPosition(new Point(viewPosition.x, 0));
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 3346 */         int tabAreaWidth = (FlatTabbedPaneUI.this.maxTabWidth > 0) ? FlatTabbedPaneUI.this.maxTabWidth : Math.max(FlatTabbedPaneUI.this.getLeadingPreferredWidth(), FlatTabbedPaneUI.this.getTrailingPreferredWidth());
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3351 */         int tx = (tabPlacement == 2) ? (insets.left + tabAreaInsets.left) : (bounds.width - insets.right - tabAreaInsets.right - tabAreaWidth);
/* 3352 */         int ty = insets.top;
/* 3353 */         int tw = tabAreaWidth;
/* 3354 */         int th = bounds.height - insets.top - insets.bottom;
/*      */         
/* 3356 */         int topHeight = FlatTabbedPaneUI.this.getLeadingPreferredHeight();
/* 3357 */         int bottomHeight = FlatTabbedPaneUI.this.getTrailingPreferredHeight();
/* 3358 */         int availHeight = th - topHeight - bottomHeight - tabAreaInsets.top - tabAreaInsets.bottom;
/* 3359 */         int totalTabHeight = (FlatTabbedPaneUI.this.rects.length > 0) ? FlatTabbedPaneUI.this.rectsTotalHeight() : 0;
/*      */ 
/*      */         
/* 3362 */         if (totalTabHeight < availHeight && FlatTabbedPaneUI.this.rects.length > 0) {
/* 3363 */           int diff = availHeight - totalTabHeight;
/* 3364 */           switch (tabAreaAlignment) {
/*      */             case 10:
/* 3366 */               bottomHeight += diff;
/*      */               break;
/*      */             
/*      */             case 11:
/* 3370 */               topHeight += diff;
/*      */               break;
/*      */             
/*      */             case 0:
/* 3374 */               topHeight += diff / 2;
/* 3375 */               bottomHeight += diff - diff / 2;
/*      */               break;
/*      */             
/*      */             case 100:
/* 3379 */               FlatTabbedPaneUI.this.stretchTabsHeight(diff);
/* 3380 */               totalTabHeight = FlatTabbedPaneUI.this.rectsTotalHeight();
/*      */               break;
/*      */           } 
/* 3383 */         } else if (FlatTabbedPaneUI.this.rects.length == 0) {
/* 3384 */           bottomHeight = th - topHeight;
/*      */         } 
/*      */         
/* 3387 */         if (FlatTabbedPaneUI.this.leadingComponent != null) {
/* 3388 */           FlatTabbedPaneUI.this.leadingComponent.setBounds(tx, ty, tw, topHeight);
/*      */         }
/*      */         
/* 3391 */         if (FlatTabbedPaneUI.this.trailingComponent != null) {
/* 3392 */           FlatTabbedPaneUI.this.trailingComponent.setBounds(tx, ty + th - bottomHeight, tw, bottomHeight);
/*      */         }
/*      */         
/* 3395 */         if (FlatTabbedPaneUI.this.rects.length > 0) {
/* 3396 */           int tyi = ty + topHeight + tabAreaInsets.top;
/* 3397 */           int thi = th - topHeight - bottomHeight - tabAreaInsets.top - tabAreaInsets.bottom;
/*      */ 
/*      */           
/* 3400 */           int y = tyi;
/* 3401 */           int h = thi;
/*      */           
/* 3403 */           if (h < totalTabHeight) {
/*      */ 
/*      */ 
/*      */             
/* 3407 */             if (useMoreTabsButton) {
/* 3408 */               int buttonHeight = (FlatTabbedPaneUI.this.moreTabsButton.getPreferredSize()).height;
/* 3409 */               FlatTabbedPaneUI.this.moreTabsButton.setBounds(tx, y + h - buttonHeight, tw, buttonHeight);
/* 3410 */               h -= buttonHeight;
/* 3411 */               moreTabsButtonVisible = true;
/*      */             } 
/* 3413 */             if (useScrollButtons) {
/*      */               
/* 3415 */               if (!hideDisabledScrollButtons || forwardButton.isEnabled()) {
/* 3416 */                 int buttonHeight = (forwardButton.getPreferredSize()).height;
/* 3417 */                 forwardButton.setBounds(tx, y + h - buttonHeight, tw, buttonHeight);
/* 3418 */                 h -= buttonHeight;
/* 3419 */                 forwardButtonVisible = true;
/*      */               } 
/*      */ 
/*      */               
/* 3423 */               if (!hideDisabledScrollButtons || backwardButton.isEnabled()) {
/* 3424 */                 int buttonHeight = (backwardButton.getPreferredSize()).height;
/* 3425 */                 if (trailingScrollButtons) {
/*      */                   
/* 3427 */                   backwardButton.setBounds(tx, y + h - buttonHeight, tw, buttonHeight);
/*      */                 } else {
/*      */                   
/* 3430 */                   backwardButton.setBounds(tx, y, tw, buttonHeight);
/* 3431 */                   y += buttonHeight;
/*      */                 } 
/* 3433 */                 h -= buttonHeight;
/* 3434 */                 backwardButtonVisible = true;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */           
/* 3439 */           FlatTabbedPaneUI.this.tabViewport.setBounds(tx, y, tw, h);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 3444 */       FlatTabbedPaneUI.this.tabViewport.setVisible((FlatTabbedPaneUI.this.rects.length > 0));
/* 3445 */       FlatTabbedPaneUI.this.moreTabsButton.setVisible(moreTabsButtonVisible);
/* 3446 */       backwardButton.setVisible(backwardButtonVisible);
/* 3447 */       forwardButton.setVisible(forwardButtonVisible);
/*      */       
/* 3449 */       FlatTabbedPaneUI.this.scrollBackwardButtonPrefSize = backwardButton.getPreferredSize();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static class RunWithOriginalLayoutManagerDelegateAction
/*      */     implements Action
/*      */   {
/*      */     private final Action delegate;
/*      */ 
/*      */     
/*      */     static void install(ActionMap map, String key) {
/* 3461 */       Action oldAction = map.get(key);
/* 3462 */       if (oldAction == null || oldAction instanceof RunWithOriginalLayoutManagerDelegateAction) {
/*      */         return;
/*      */       }
/* 3465 */       map.put(key, new RunWithOriginalLayoutManagerDelegateAction(oldAction));
/*      */     }
/*      */     
/*      */     private RunWithOriginalLayoutManagerDelegateAction(Action delegate) {
/* 3469 */       this.delegate = delegate;
/*      */     }
/*      */ 
/*      */     
/*      */     public Object getValue(String key) {
/* 3474 */       return this.delegate.getValue(key);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isEnabled() {
/* 3479 */       return this.delegate.isEnabled();
/*      */     }
/*      */     public void putValue(String key, Object value) {}
/*      */     public void setEnabled(boolean b) {}
/*      */     
/*      */     public void addPropertyChangeListener(PropertyChangeListener listener) {}
/*      */     
/*      */     public void removePropertyChangeListener(PropertyChangeListener listener) {}
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/* 3489 */       JTabbedPane tabbedPane = (JTabbedPane)e.getSource();
/* 3490 */       ComponentUI ui = tabbedPane.getUI();
/* 3491 */       if (ui instanceof FlatTabbedPaneUI) {
/* 3492 */         ((FlatTabbedPaneUI)ui).runWithOriginalLayoutManager(() -> this.delegate.actionPerformed(e));
/*      */       }
/*      */       else {
/*      */         
/* 3496 */         this.delegate.actionPerformed(e);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static class FlatSelectedTabRepainter
/*      */     implements PropertyChangeListener
/*      */   {
/*      */     private static FlatSelectedTabRepainter instance;
/*      */     
/*      */     private KeyboardFocusManager keyboardFocusManager;
/*      */     
/*      */     static void install() {
/* 3510 */       synchronized (FlatSelectedTabRepainter.class) {
/* 3511 */         if (instance != null) {
/*      */           return;
/*      */         }
/* 3514 */         instance = new FlatSelectedTabRepainter();
/*      */       } 
/*      */     }
/*      */     
/*      */     FlatSelectedTabRepainter() {
/* 3519 */       this.keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
/* 3520 */       this.keyboardFocusManager.addPropertyChangeListener(this);
/*      */     }
/*      */     
/*      */     private void uninstall() {
/* 3524 */       synchronized (FlatSelectedTabRepainter.class) {
/* 3525 */         if (instance == null) {
/*      */           return;
/*      */         }
/* 3528 */         this.keyboardFocusManager.removePropertyChangeListener(this);
/* 3529 */         this.keyboardFocusManager = null;
/* 3530 */         instance = null;
/*      */       } 
/*      */     }
/*      */     
/*      */     public void propertyChange(PropertyChangeEvent e) {
/*      */       Object oldValue;
/*      */       Object newValue;
/* 3537 */       if (!(UIManager.getLookAndFeel() instanceof FlatLaf)) {
/* 3538 */         uninstall();
/*      */         
/*      */         return;
/*      */       } 
/* 3542 */       switch (e.getPropertyName()) {
/*      */         case "permanentFocusOwner":
/* 3544 */           oldValue = e.getOldValue();
/* 3545 */           newValue = e.getNewValue();
/* 3546 */           if (oldValue instanceof Component)
/* 3547 */             repaintSelectedTabs((Component)oldValue); 
/* 3548 */           if (newValue instanceof Component) {
/* 3549 */             repaintSelectedTabs((Component)newValue);
/*      */           }
/*      */           break;
/*      */         case "activeWindow":
/* 3553 */           repaintSelectedTabs(this.keyboardFocusManager.getPermanentFocusOwner());
/*      */           break;
/*      */       } 
/*      */     }
/*      */     
/*      */     private void repaintSelectedTabs(Component c) {
/* 3559 */       if (c instanceof JTabbedPane) {
/* 3560 */         repaintSelectedTab((JTabbedPane)c);
/*      */       }
/* 3562 */       while ((c = SwingUtilities.getAncestorOfClass(JTabbedPane.class, c)) != null)
/* 3563 */         repaintSelectedTab((JTabbedPane)c); 
/*      */     }
/*      */     
/*      */     private void repaintSelectedTab(JTabbedPane tabbedPane) {
/* 3567 */       TabbedPaneUI ui = tabbedPane.getUI();
/* 3568 */       if (ui instanceof FlatTabbedPaneUI)
/* 3569 */         ((FlatTabbedPaneUI)ui).repaintTab(tabbedPane.getSelectedIndex()); 
/*      */     }
/*      */   }
/*      */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatTabbedPaneUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */