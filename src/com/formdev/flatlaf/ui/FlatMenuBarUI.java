/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.FlatLaf;
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.SystemInfo;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.ActionMap;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.MenuSelectionManager;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ActionMapUIResource;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicMenuBarUI;
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
/*     */ public class FlatMenuBarUI
/*     */   extends BasicMenuBarUI
/*     */   implements FlatStylingSupport.StyleableUI
/*     */ {
/*     */   @Styleable
/*     */   protected Insets itemMargins;
/*     */   @Styleable
/*     */   protected Insets selectionInsets;
/*     */   @Styleable
/*     */   protected Insets selectionEmbeddedInsets;
/*     */   @Styleable
/*  80 */   protected int selectionArc = -1;
/*     */   @Styleable
/*     */   protected Color hoverBackground;
/*     */   @Styleable
/*     */   protected Color selectionBackground;
/*     */   @Styleable
/*  86 */   protected int underlineSelectionHeight = -1;
/*     */   
/*     */   @Styleable
/*     */   protected Color selectionForeground;
/*     */ 
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  93 */     return new FlatMenuBarUI();
/*     */   }
/*     */   @Styleable
/*     */   protected Color underlineSelectionBackground; @Styleable
/*     */   protected Color underlineSelectionColor;
/*     */   private PropertyChangeListener propertyChangeListener;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   private AtomicBoolean borderShared;
/*     */   
/*     */   public void installUI(JComponent c) {
/* 103 */     super.installUI(c);
/*     */     
/* 105 */     installStyle();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/* 110 */     super.installDefaults();
/*     */     
/* 112 */     LookAndFeel.installProperty(this.menuBar, "opaque", Boolean.valueOf(false));
/*     */     
/* 114 */     LayoutManager layout = this.menuBar.getLayout();
/* 115 */     if (layout == null || layout instanceof javax.swing.plaf.UIResource) {
/* 116 */       this.menuBar.setLayout(new FlatMenuBarLayout(this.menuBar));
/*     */     }
/*     */   }
/*     */   
/*     */   protected void uninstallDefaults() {
/* 121 */     super.uninstallDefaults();
/*     */     
/* 123 */     this.oldStyleValues = null;
/* 124 */     this.borderShared = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners() {
/* 129 */     super.installListeners();
/*     */     
/* 131 */     this.propertyChangeListener = FlatStylingSupport.createPropertyChangeListener(this.menuBar, this::installStyle, null);
/* 132 */     this.menuBar.addPropertyChangeListener(this.propertyChangeListener);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners() {
/* 137 */     super.uninstallListeners();
/*     */     
/* 139 */     this.menuBar.removePropertyChangeListener(this.propertyChangeListener);
/* 140 */     this.propertyChangeListener = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installKeyboardActions() {
/* 145 */     super.installKeyboardActions();
/*     */     
/* 147 */     ActionMap map = SwingUtilities.getUIActionMap(this.menuBar);
/* 148 */     if (map == null) {
/* 149 */       map = new ActionMapUIResource();
/* 150 */       SwingUtilities.replaceUIActionMap(this.menuBar, map);
/*     */     } 
/* 152 */     map.put("takeFocus", new TakeFocus());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle() {
/*     */     try {
/* 158 */       applyStyle(FlatStylingSupport.getResolvedStyle(this.menuBar, "MenuBar"));
/* 159 */     } catch (RuntimeException ex) {
/* 160 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 166 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 171 */     if (this.borderShared == null)
/* 172 */       this.borderShared = new AtomicBoolean(true); 
/* 173 */     return FlatStylingSupport.applyToAnnotatedObjectOrBorder(this, key, value, this.menuBar, this.borderShared);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 179 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this, this.menuBar.getBorder());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 185 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, this.menuBar.getBorder(), key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(Graphics g, JComponent c) {
/* 191 */     Color background = getBackground(c);
/* 192 */     if (background != null) {
/* 193 */       g.setColor(background);
/* 194 */       g.fillRect(0, 0, c.getWidth(), c.getHeight());
/*     */     } 
/*     */     
/* 197 */     paint(g, c);
/*     */   }
/*     */   
/*     */   protected Color getBackground(JComponent c) {
/* 201 */     Color background = c.getBackground();
/*     */ 
/*     */     
/* 204 */     if (c.isOpaque()) {
/* 205 */       return background;
/*     */     }
/*     */     
/* 208 */     if (!(background instanceof javax.swing.plaf.UIResource)) {
/* 209 */       return null;
/*     */     }
/*     */     
/* 212 */     JRootPane rootPane = SwingUtilities.getRootPane(c);
/* 213 */     if (rootPane == null || !(rootPane.getParent() instanceof java.awt.Window) || rootPane.getJMenuBar() != c) {
/* 214 */       return background;
/*     */     }
/*     */     
/* 217 */     if (useUnifiedBackground(c)) {
/* 218 */       background = FlatUIUtils.getParentBackground(c);
/*     */     }
/*     */     
/* 221 */     if (FlatUIUtils.isFullScreen(rootPane)) {
/* 222 */       return background;
/*     */     }
/*     */     
/* 225 */     return FlatRootPaneUI.isMenuBarEmbedded(rootPane) ? null : background;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean useUnifiedBackground(Component c) {
/*     */     JRootPane rootPane;
/* 237 */     return (UIManager.getBoolean("TitlePane.unifiedBackground") && (
/* 238 */       rootPane = SwingUtilities.getRootPane(c)) != null && rootPane
/* 239 */       .getParent() instanceof java.awt.Window && rootPane
/* 240 */       .getJMenuBar() == c && rootPane
/* 241 */       .getWindowDecorationStyle() != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class FlatMenuBarLayout
/*     */     extends DefaultMenuLayout
/*     */   {
/*     */     public FlatMenuBarLayout(Container target) {
/* 253 */       super(target, 2);
/*     */     }
/*     */ 
/*     */     
/*     */     public void layoutContainer(Container target) {
/* 258 */       super.layoutContainer(target);
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
/* 269 */       JRootPane rootPane = SwingUtilities.getRootPane(target);
/* 270 */       if (rootPane == null || rootPane.getJMenuBar() != target) {
/*     */         return;
/*     */       }
/*     */       
/* 274 */       FlatTitlePane titlePane = FlatRootPaneUI.getTitlePane(rootPane);
/* 275 */       if (titlePane == null || !titlePane.isMenuBarEmbedded()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 280 */       Component horizontalGlue = titlePane.findHorizontalGlue((JMenuBar)target);
/* 281 */       int minTitleWidth = UIScale.scale(titlePane.titleMinimumWidth);
/* 282 */       if (horizontalGlue != null && horizontalGlue.getWidth() < minTitleWidth) {
/*     */         
/* 284 */         int glueIndex = -1;
/* 285 */         Component[] components = target.getComponents();
/* 286 */         for (int i = components.length - 1; i >= 0; i--) {
/* 287 */           if (components[i] == horizontalGlue) {
/* 288 */             glueIndex = i;
/*     */             break;
/*     */           } 
/*     */         } 
/* 292 */         if (glueIndex < 0) {
/*     */           return;
/*     */         }
/* 295 */         if (target.getComponentOrientation().isLeftToRight()) {
/*     */ 
/*     */ 
/*     */           
/* 299 */           int offset = minTitleWidth - horizontalGlue.getWidth();
/* 300 */           horizontalGlue.setSize(minTitleWidth, horizontalGlue.getHeight());
/*     */ 
/*     */           
/* 303 */           int minGlueX = target.getWidth() - (target.getInsets()).right - minTitleWidth;
/* 304 */           if (minGlueX < horizontalGlue.getX()) {
/*     */             
/* 306 */             offset -= horizontalGlue.getX() - minGlueX;
/* 307 */             horizontalGlue.setLocation(minGlueX, horizontalGlue.getY());
/*     */ 
/*     */             
/* 310 */             for (int k = glueIndex - 1; k >= 0; k--) {
/* 311 */               Component c = components[k];
/* 312 */               if (c.getX() > minGlueX) {
/*     */                 
/* 314 */                 c.setBounds(minGlueX, c.getY(), 0, c.getHeight());
/*     */               } else {
/*     */                 
/* 317 */                 c.setSize(minGlueX - c.getX(), c.getHeight());
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 324 */           for (int j = glueIndex + 1; j < components.length; j++) {
/* 325 */             Component c = components[j];
/* 326 */             c.setLocation(c.getX() + offset, c.getY());
/*     */           }
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 332 */           int offset = minTitleWidth - horizontalGlue.getWidth();
/* 333 */           horizontalGlue.setBounds(horizontalGlue.getX() - offset, horizontalGlue.getY(), minTitleWidth, horizontalGlue
/* 334 */               .getHeight());
/*     */ 
/*     */           
/* 337 */           int minGlueX = (target.getInsets()).left;
/* 338 */           if (minGlueX > horizontalGlue.getX()) {
/*     */             
/* 340 */             offset -= horizontalGlue.getX() - minGlueX;
/* 341 */             horizontalGlue.setLocation(minGlueX, horizontalGlue.getY());
/*     */ 
/*     */             
/* 344 */             int x = horizontalGlue.getX() + horizontalGlue.getWidth();
/* 345 */             for (int k = glueIndex - 1; k >= 0; k--) {
/* 346 */               Component c = components[k];
/* 347 */               if (c.getX() + c.getWidth() < x) {
/*     */                 
/* 349 */                 c.setBounds(x, c.getY(), 0, c.getHeight());
/*     */               } else {
/*     */                 
/* 352 */                 c.setBounds(x, c.getY(), c.getWidth() - x - c.getX(), c.getHeight());
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 359 */           for (int j = glueIndex + 1; j < components.length; j++) {
/* 360 */             Component c = components[j];
/* 361 */             c.setLocation(c.getX() - offset, c.getY());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class TakeFocus
/*     */     extends AbstractAction
/*     */   {
/*     */     private TakeFocus() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void actionPerformed(ActionEvent e) {
/* 380 */       JMenuBar menuBar = (JMenuBar)e.getSource();
/* 381 */       JMenu menu = menuBar.getMenu(0);
/* 382 */       if (menu != null) {
/*     */         
/* 384 */         (new javax.swing.MenuElement[2])[0] = menuBar; (new javax.swing.MenuElement[2])[1] = menu;
/* 385 */         (new javax.swing.MenuElement[3])[0] = menuBar; (new javax.swing.MenuElement[3])[1] = menu; (new javax.swing.MenuElement[3])[2] = menu.getPopupMenu(); MenuSelectionManager.defaultManager().setSelectedPath(SystemInfo.isWindows ? new javax.swing.MenuElement[2] : new javax.swing.MenuElement[3]);
/*     */         
/* 387 */         FlatLaf.showMnemonics(menuBar);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatMenuBarUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */