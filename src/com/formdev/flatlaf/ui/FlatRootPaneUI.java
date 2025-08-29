/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.FlatClientProperties;
/*     */ import com.formdev.flatlaf.FlatLaf;
/*     */ import com.formdev.flatlaf.util.HiDPIUtils;
/*     */ import com.formdev.flatlaf.util.SystemInfo;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.LayoutManager2;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.ComponentAdapter;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.function.Function;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLayeredPane;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.plaf.BorderUIResource;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.RootPaneUI;
/*     */ import javax.swing.plaf.basic.BasicRootPaneUI;
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
/*     */ public class FlatRootPaneUI
/*     */   extends BasicRootPaneUI
/*     */ {
/*  82 */   protected final Color borderColor = UIManager.getColor("TitlePane.borderColor");
/*     */   
/*     */   protected JRootPane rootPane;
/*     */   
/*     */   protected FlatTitlePane titlePane;
/*     */   protected FlatWindowResizer windowResizer;
/*     */   private Object nativeWindowBorderData;
/*     */   private LayoutManager oldLayout;
/*     */   private PropertyChangeListener ancestorListener;
/*     */   private ComponentListener componentListener;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  94 */     return new FlatRootPaneUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  99 */     super.installUI(c);
/*     */     
/* 101 */     this.rootPane = (JRootPane)c;
/*     */     
/* 103 */     if (this.rootPane.getWindowDecorationStyle() != 0) {
/* 104 */       installClientDecorations();
/*     */     } else {
/* 106 */       installBorder();
/*     */     } 
/* 108 */     installNativeWindowBorder();
/*     */   }
/*     */   
/*     */   protected void installBorder() {
/* 112 */     if (this.borderColor != null) {
/* 113 */       Border b = this.rootPane.getBorder();
/* 114 */       if (b == null || b instanceof javax.swing.plaf.UIResource) {
/* 115 */         this.rootPane.setBorder(new FlatWindowTitleBorder(this.borderColor));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void uninstallUI(JComponent c) {
/* 121 */     super.uninstallUI(c);
/*     */     
/* 123 */     uninstallNativeWindowBorder();
/* 124 */     uninstallClientDecorations();
/* 125 */     this.rootPane = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults(JRootPane c) {
/* 130 */     super.installDefaults(c);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 138 */     if (!c.isBackgroundSet() || c.getBackground() instanceof javax.swing.plaf.UIResource)
/* 139 */       c.setBackground(UIManager.getColor("RootPane.background")); 
/* 140 */     if (!c.isForegroundSet() || c.getForeground() instanceof javax.swing.plaf.UIResource)
/* 141 */       c.setForeground(UIManager.getColor("RootPane.foreground")); 
/* 142 */     if (!c.isFontSet() || c.getFont() instanceof javax.swing.plaf.UIResource) {
/* 143 */       c.setFont(UIManager.getFont("RootPane.font"));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 152 */     Container parent = c.getParent();
/* 153 */     if (parent instanceof JFrame || parent instanceof JDialog) {
/* 154 */       Color background = parent.getBackground();
/* 155 */       if (background == null || background instanceof javax.swing.plaf.UIResource) {
/* 156 */         parent.setBackground(UIManager.getColor("control"));
/*     */       }
/*     */     } 
/*     */     
/* 160 */     if (SystemInfo.isJetBrainsJVM && SystemInfo.isMacOS_10_14_Mojave_orLater) {
/* 161 */       c.putClientProperty("jetbrains.awt.windowDarkAppearance", Boolean.valueOf(FlatLaf.isLafDark()));
/*     */     }
/*     */   }
/*     */   
/*     */   protected void uninstallDefaults(JRootPane c) {
/* 166 */     super.uninstallDefaults(c);
/*     */ 
/*     */     
/* 169 */     if (c.isBackgroundSet() && c.getBackground() instanceof javax.swing.plaf.UIResource)
/* 170 */       c.setBackground((Color)null); 
/* 171 */     if (c.isForegroundSet() && c.getForeground() instanceof javax.swing.plaf.UIResource)
/* 172 */       c.setForeground((Color)null); 
/* 173 */     if (c.isFontSet() && c.getFont() instanceof javax.swing.plaf.UIResource) {
/* 174 */       c.setFont((Font)null);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void installListeners(final JRootPane root) {
/* 179 */     super.installListeners(root);
/*     */     
/* 181 */     if (SystemInfo.isJava_9_orLater) {
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
/* 193 */       this.ancestorListener = (e -> {
/*     */           Object oldValue = e.getOldValue();
/*     */           Object newValue = e.getNewValue();
/*     */           if (newValue instanceof Window) {
/*     */             if (this.componentListener == null) {
/*     */               this.componentListener = new ComponentAdapter()
/*     */                 {
/*     */                   public void componentShown(ComponentEvent e)
/*     */                   {
/* 202 */                     root.getParent().repaint(root.getX(), root.getY(), root.getWidth(), root.getHeight());
/*     */                   }
/*     */                 };
/*     */             }
/*     */             
/*     */             ((Window)newValue).addComponentListener(this.componentListener);
/*     */           } else if (newValue == null && oldValue instanceof Window && this.componentListener != null) {
/*     */             ((Window)oldValue).removeComponentListener(this.componentListener);
/*     */           } 
/*     */         });
/* 212 */       root.addPropertyChangeListener("ancestor", this.ancestorListener);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners(JRootPane root) {
/* 218 */     super.uninstallListeners(root);
/*     */     
/* 220 */     if (SystemInfo.isJava_9_orLater) {
/* 221 */       if (this.componentListener != null) {
/* 222 */         Window window = SwingUtilities.windowForComponent(root);
/* 223 */         if (window != null)
/* 224 */           window.removeComponentListener(this.componentListener); 
/* 225 */         this.componentListener = null;
/*     */       } 
/* 227 */       root.removePropertyChangeListener("ancestor", this.ancestorListener);
/* 228 */       this.ancestorListener = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installNativeWindowBorder() {
/* 234 */     this.nativeWindowBorderData = FlatNativeWindowBorder.install(this.rootPane);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallNativeWindowBorder() {
/* 239 */     FlatNativeWindowBorder.uninstall(this.rootPane, this.nativeWindowBorderData);
/* 240 */     this.nativeWindowBorderData = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void updateNativeWindowBorder(JRootPane rootPane) {
/* 245 */     RootPaneUI rui = rootPane.getUI();
/* 246 */     if (!(rui instanceof FlatRootPaneUI)) {
/*     */       return;
/*     */     }
/* 249 */     FlatRootPaneUI ui = (FlatRootPaneUI)rui;
/* 250 */     ui.uninstallNativeWindowBorder();
/* 251 */     ui.installNativeWindowBorder();
/*     */   }
/*     */   
/*     */   protected void installClientDecorations() {
/* 255 */     boolean isNativeWindowBorderSupported = FlatNativeWindowBorder.isSupported();
/*     */ 
/*     */     
/* 258 */     if (this.rootPane.getWindowDecorationStyle() != 0 && !isNativeWindowBorderSupported) {
/* 259 */       LookAndFeel.installBorder(this.rootPane, "RootPane.border");
/*     */     } else {
/* 261 */       LookAndFeel.uninstallBorder(this.rootPane);
/*     */     } 
/*     */     
/* 264 */     setTitlePane(createTitlePane());
/*     */ 
/*     */     
/* 267 */     this.oldLayout = this.rootPane.getLayout();
/* 268 */     this.rootPane.setLayout(createRootLayout());
/*     */ 
/*     */     
/* 271 */     if (!isNativeWindowBorderSupported)
/* 272 */       this.windowResizer = createWindowResizer(); 
/*     */   }
/*     */   
/*     */   protected void uninstallClientDecorations() {
/* 276 */     LookAndFeel.uninstallBorder(this.rootPane);
/* 277 */     setTitlePane(null);
/*     */     
/* 279 */     if (this.windowResizer != null) {
/* 280 */       this.windowResizer.uninstall();
/* 281 */       this.windowResizer = null;
/*     */     } 
/*     */     
/* 284 */     if (this.oldLayout != null) {
/* 285 */       this.rootPane.setLayout(this.oldLayout);
/* 286 */       this.oldLayout = null;
/*     */     } 
/*     */     
/* 289 */     if (this.rootPane.getWindowDecorationStyle() == 0) {
/* 290 */       this.rootPane.revalidate();
/* 291 */       this.rootPane.repaint();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected FlatRootLayout createRootLayout() {
/* 296 */     return new FlatRootLayout();
/*     */   }
/*     */   
/*     */   protected FlatWindowResizer createWindowResizer() {
/* 300 */     return new FlatWindowResizer.WindowResizer(this.rootPane);
/*     */   }
/*     */   
/*     */   protected FlatTitlePane createTitlePane() {
/* 304 */     return new FlatTitlePane(this.rootPane);
/*     */   }
/*     */ 
/*     */   
/* 308 */   protected static final Integer TITLE_PANE_LAYER = Integer.valueOf(JLayeredPane.FRAME_CONTENT_LAYER.intValue() - 1);
/*     */   
/*     */   protected void setTitlePane(FlatTitlePane newTitlePane) {
/* 311 */     JLayeredPane layeredPane = this.rootPane.getLayeredPane();
/*     */     
/* 313 */     if (this.titlePane != null) {
/* 314 */       layeredPane.remove(this.titlePane);
/*     */     }
/* 316 */     if (newTitlePane != null) {
/* 317 */       layeredPane.add(newTitlePane, TITLE_PANE_LAYER);
/*     */     }
/* 319 */     this.titlePane = newTitlePane;
/*     */   }
/*     */ 
/*     */   
/*     */   public void propertyChange(PropertyChangeEvent e) {
/* 324 */     super.propertyChange(e);
/*     */     
/* 326 */     switch (e.getPropertyName()) {
/*     */       case "windowDecorationStyle":
/* 328 */         uninstallClientDecorations();
/* 329 */         if (this.rootPane.getWindowDecorationStyle() != 0) {
/* 330 */           installClientDecorations(); break;
/*     */         } 
/* 332 */         installBorder();
/*     */         break;
/*     */       
/*     */       case "JRootPane.useWindowDecorations":
/* 336 */         updateNativeWindowBorder(this.rootPane);
/*     */         break;
/*     */       
/*     */       case "JRootPane.menuBarEmbedded":
/* 340 */         if (this.titlePane != null) {
/* 341 */           this.titlePane.menuBarChanged();
/* 342 */           this.rootPane.revalidate();
/* 343 */           this.rootPane.repaint();
/*     */         } 
/*     */         break;
/*     */       
/*     */       case "JRootPane.titleBarShowIcon":
/* 348 */         if (this.titlePane != null) {
/* 349 */           this.titlePane.updateIcon();
/*     */         }
/*     */         break;
/*     */       case "JRootPane.titleBarShowTitle":
/*     */       case "JRootPane.titleBarShowIconify":
/*     */       case "JRootPane.titleBarShowMaximize":
/*     */       case "JRootPane.titleBarShowClose":
/* 356 */         if (this.titlePane != null) {
/* 357 */           this.titlePane.updateVisibility();
/*     */         }
/*     */         break;
/*     */       case "JRootPane.titleBarBackground":
/*     */       case "JRootPane.titleBarForeground":
/* 362 */         if (this.titlePane != null) {
/* 363 */           this.titlePane.titleBarColorsChanged();
/*     */         }
/*     */         break;
/*     */       case "JRootPane.glassPaneFullHeight":
/* 367 */         this.rootPane.revalidate();
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected static boolean isMenuBarEmbedded(JRootPane rootPane) {
/* 373 */     RootPaneUI ui = rootPane.getUI();
/* 374 */     return (ui instanceof FlatRootPaneUI && ((FlatRootPaneUI)ui).titlePane != null && ((FlatRootPaneUI)ui).titlePane
/*     */       
/* 376 */       .isMenuBarEmbedded());
/*     */   }
/*     */ 
/*     */   
/*     */   protected static FlatTitlePane getTitlePane(JRootPane rootPane) {
/* 381 */     RootPaneUI ui = rootPane.getUI();
/* 382 */     return (ui instanceof FlatRootPaneUI) ? ((FlatRootPaneUI)ui).titlePane : null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected class FlatRootLayout
/*     */     implements LayoutManager2
/*     */   {
/*     */     public void addLayoutComponent(String name, Component comp) {}
/*     */     
/*     */     public void addLayoutComponent(Component comp, Object constraints) {}
/*     */     
/*     */     public void removeLayoutComponent(Component comp) {}
/*     */     
/*     */     public Dimension preferredLayoutSize(Container parent) {
/* 396 */       return computeLayoutSize(parent, c -> c.getPreferredSize());
/*     */     }
/*     */ 
/*     */     
/*     */     public Dimension minimumLayoutSize(Container parent) {
/* 401 */       return computeLayoutSize(parent, c -> c.getMinimumSize());
/*     */     }
/*     */ 
/*     */     
/*     */     public Dimension maximumLayoutSize(Container parent) {
/* 406 */       return new Dimension(2147483647, 2147483647);
/*     */     }
/*     */     
/*     */     private Dimension computeLayoutSize(Container parent, Function<Component, Dimension> getSizeFunc) {
/* 410 */       JRootPane rootPane = (JRootPane)parent;
/*     */ 
/*     */ 
/*     */       
/* 414 */       Dimension titlePaneSize = (FlatRootPaneUI.this.titlePane != null) ? getSizeFunc.apply(FlatRootPaneUI.this.titlePane) : new Dimension();
/*     */ 
/*     */       
/* 417 */       Dimension contentSize = (rootPane.getContentPane() != null) ? getSizeFunc.apply(rootPane.getContentPane()) : rootPane.getSize();
/*     */       
/* 419 */       int width = contentSize.width;
/* 420 */       int height = titlePaneSize.height + contentSize.height;
/* 421 */       if (FlatRootPaneUI.this.titlePane == null || !FlatRootPaneUI.this.titlePane.isMenuBarEmbedded()) {
/* 422 */         JMenuBar menuBar = rootPane.getJMenuBar();
/*     */ 
/*     */         
/* 425 */         Dimension menuBarSize = (menuBar != null && menuBar.isVisible()) ? getSizeFunc.apply(menuBar) : new Dimension();
/*     */         
/* 427 */         width = Math.max(width, menuBarSize.width);
/* 428 */         height += menuBarSize.height;
/*     */       } 
/*     */       
/* 431 */       Insets insets = rootPane.getInsets();
/*     */       
/* 433 */       return new Dimension(width + insets.left + insets.right, height + insets.top + insets.bottom);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void layoutContainer(Container parent) {
/* 440 */       JRootPane rootPane = (JRootPane)parent;
/* 441 */       boolean isFullScreen = FlatUIUtils.isFullScreen(rootPane);
/*     */       
/* 443 */       Insets insets = rootPane.getInsets();
/* 444 */       int x = insets.left;
/* 445 */       int y = insets.top;
/* 446 */       int width = rootPane.getWidth() - insets.left - insets.right;
/* 447 */       int height = rootPane.getHeight() - insets.top - insets.bottom;
/*     */ 
/*     */       
/* 450 */       if (rootPane.getLayeredPane() != null) {
/* 451 */         rootPane.getLayeredPane().setBounds(x, y, width, height);
/*     */       }
/*     */       
/* 454 */       int nextY = 0;
/* 455 */       if (FlatRootPaneUI.this.titlePane != null) {
/* 456 */         int prefHeight = !isFullScreen ? (FlatRootPaneUI.this.titlePane.getPreferredSize()).height : 0;
/* 457 */         FlatRootPaneUI.this.titlePane.setBounds(0, 0, width, prefHeight);
/* 458 */         nextY += prefHeight;
/*     */       } 
/*     */ 
/*     */       
/* 462 */       if (rootPane.getGlassPane() != null) {
/* 463 */         boolean fullHeight = FlatClientProperties.clientPropertyBoolean(rootPane, "JRootPane.glassPaneFullHeight", false);
/*     */         
/* 465 */         int offset = fullHeight ? 0 : nextY;
/* 466 */         rootPane.getGlassPane().setBounds(x, y + offset, width, height - offset);
/*     */       } 
/*     */ 
/*     */       
/* 470 */       JMenuBar menuBar = rootPane.getJMenuBar();
/* 471 */       if (menuBar != null && menuBar.isVisible()) {
/* 472 */         boolean embedded = (!isFullScreen && FlatRootPaneUI.this.titlePane != null && FlatRootPaneUI.this.titlePane.isMenuBarEmbedded());
/* 473 */         if (embedded) {
/* 474 */           FlatRootPaneUI.this.titlePane.validate();
/* 475 */           menuBar.setBounds(FlatRootPaneUI.this.titlePane.getMenuBarBounds());
/*     */         } else {
/* 477 */           Dimension prefSize = menuBar.getPreferredSize();
/* 478 */           menuBar.setBounds(0, nextY, width, prefSize.height);
/* 479 */           nextY += prefSize.height;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 484 */       Container contentPane = rootPane.getContentPane();
/* 485 */       if (contentPane != null) {
/* 486 */         contentPane.setBounds(0, nextY, width, Math.max(height - nextY, 0));
/*     */       }
/*     */       
/* 489 */       if (FlatRootPaneUI.this.titlePane != null) {
/* 490 */         FlatRootPaneUI.this.titlePane.menuBarLayouted();
/*     */       }
/*     */     }
/*     */     
/*     */     public void invalidateLayout(Container parent) {
/* 495 */       if (FlatRootPaneUI.this.titlePane != null) {
/* 496 */         FlatRootPaneUI.this.titlePane.menuBarChanged();
/*     */       }
/*     */     }
/*     */     
/*     */     public float getLayoutAlignmentX(Container target) {
/* 501 */       return 0.0F;
/*     */     }
/*     */ 
/*     */     
/*     */     public float getLayoutAlignmentY(Container target) {
/* 506 */       return 0.0F;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class FlatWindowBorder
/*     */     extends BorderUIResource.EmptyBorderUIResource
/*     */   {
/* 518 */     protected final Color activeBorderColor = UIManager.getColor("RootPane.activeBorderColor");
/* 519 */     protected final Color inactiveBorderColor = UIManager.getColor("RootPane.inactiveBorderColor");
/* 520 */     protected final Color baseBorderColor = UIManager.getColor("Panel.background");
/*     */     
/*     */     public FlatWindowBorder() {
/* 523 */       super(1, 1, 1, 1);
/*     */     }
/*     */ 
/*     */     
/*     */     public Insets getBorderInsets(Component c, Insets insets) {
/* 528 */       if (isWindowMaximized(c) || FlatUIUtils.isFullScreen(c)) {
/*     */         
/* 530 */         insets.top = insets.left = insets.bottom = insets.right = 0;
/* 531 */         return insets;
/*     */       } 
/* 533 */       return super.getBorderInsets(c, insets);
/*     */     }
/*     */ 
/*     */     
/*     */     public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 538 */       if (isWindowMaximized(c) || FlatUIUtils.isFullScreen(c)) {
/*     */         return;
/*     */       }
/* 541 */       Container parent = c.getParent();
/* 542 */       boolean active = (parent instanceof Window && ((Window)parent).isActive());
/*     */       
/* 544 */       g.setColor(FlatUIUtils.deriveColor(active ? this.activeBorderColor : this.inactiveBorderColor, this.baseBorderColor));
/* 545 */       HiDPIUtils.paintAtScale1x((Graphics2D)g, x, y, width, height, this::paintImpl);
/*     */     }
/*     */     
/*     */     private void paintImpl(Graphics2D g, int x, int y, int width, int height, double scaleFactor) {
/* 549 */       g.drawRect(x, y, width - 1, height - 1);
/*     */     }
/*     */     
/*     */     protected boolean isWindowMaximized(Component c) {
/* 553 */       Container parent = c.getParent();
/* 554 */       return (parent instanceof Frame && (((Frame)parent).getExtendedState() & 0x6) == 6);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static class FlatWindowTitleBorder
/*     */     extends BorderUIResource.EmptyBorderUIResource
/*     */   {
/*     */     private final Color borderColor;
/*     */ 
/*     */     
/*     */     FlatWindowTitleBorder(Color borderColor) {
/* 566 */       super(0, 0, 0, 0);
/* 567 */       this.borderColor = borderColor;
/*     */     }
/*     */ 
/*     */     
/*     */     public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 572 */       if (showBorder(c)) {
/* 573 */         float lineHeight = UIScale.scale(1.0F);
/* 574 */         FlatUIUtils.paintFilledRectangle(g, this.borderColor, x, y, width, lineHeight);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public Insets getBorderInsets(Component c, Insets insets) {
/* 580 */       insets.set(showBorder(c) ? 1 : 0, 0, 0, 0);
/* 581 */       return insets;
/*     */     }
/*     */     
/*     */     private boolean showBorder(Component c) {
/* 585 */       Container parent = c.getParent();
/* 586 */       return ((parent instanceof JFrame && (((JFrame)parent)
/*     */         
/* 588 */         .getJMenuBar() == null || 
/* 589 */         !((JFrame)parent).getJMenuBar().isVisible())) || (parent instanceof JDialog && (((JDialog)parent)
/*     */         
/* 591 */         .getJMenuBar() == null || 
/* 592 */         !((JDialog)parent).getJMenuBar().isVisible())));
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatRootPaneUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */