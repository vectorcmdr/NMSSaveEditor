/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.lang.invoke.MethodHandles;
/*     */ import java.util.Map;
/*     */ import java.util.function.Function;
/*     */ import javax.swing.ButtonModel;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.event.MouseInputListener;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.MenuBarUI;
/*     */ import javax.swing.plaf.basic.BasicMenuItemUI;
/*     */ import javax.swing.plaf.basic.BasicMenuUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @StyleableFields({@StyleableField(cls = BasicMenuItemUI.class, key = "selectionBackground"), @StyleableField(cls = BasicMenuItemUI.class, key = "selectionForeground"), @StyleableField(cls = BasicMenuItemUI.class, key = "disabledForeground"), @StyleableField(cls = BasicMenuItemUI.class, key = "acceleratorForeground"), @StyleableField(cls = BasicMenuItemUI.class, key = "acceleratorSelectionForeground")})
/*     */ public class FlatMenuUI
/*     */   extends BasicMenuUI
/*     */   implements FlatStylingSupport.StyleableUI, FlatStylingSupport.StyleableLookupProvider
/*     */ {
/*     */   private FlatMenuItemRenderer renderer;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/* 108 */     return new FlatMenuUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/* 113 */     super.installUI(c);
/*     */     
/* 115 */     installStyle();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/* 120 */     super.installDefaults();
/*     */     
/* 122 */     LookAndFeel.installProperty(this.menuItem, "iconTextGap", Integer.valueOf(FlatUIUtils.getUIInt("MenuItem.iconTextGap", 4)));
/*     */     
/* 124 */     this.menuItem.setRolloverEnabled(true);
/*     */     
/* 126 */     this.renderer = createRenderer();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults() {
/* 131 */     super.uninstallDefaults();
/*     */     
/* 133 */     FlatMenuItemRenderer.clearClientProperties(this.menuItem.getParent());
/* 134 */     this.renderer = null;
/* 135 */     this.oldStyleValues = null;
/*     */   }
/*     */   
/*     */   protected FlatMenuItemRenderer createRenderer() {
/* 139 */     return new FlatMenuRenderer(this.menuItem, this.checkIcon, this.arrowIcon, this.acceleratorFont, this.acceleratorDelimiter);
/*     */   }
/*     */ 
/*     */   
/*     */   protected MouseInputListener createMouseInputListener(JComponent c) {
/* 144 */     return new BasicMenuUI.MouseInputHandler()
/*     */       {
/*     */         public void mouseEntered(MouseEvent e) {
/* 147 */           super.mouseEntered(e);
/* 148 */           rollover(e, true);
/*     */         }
/*     */ 
/*     */         
/*     */         public void mouseExited(MouseEvent e) {
/* 153 */           super.mouseExited(e);
/* 154 */           rollover(e, false);
/*     */         }
/*     */         
/*     */         private void rollover(MouseEvent e, boolean rollover) {
/* 158 */           JMenu menu = (JMenu)e.getSource();
/* 159 */           if (menu.isTopLevelMenu() && menu.isRolloverEnabled()) {
/* 160 */             menu.getModel().setRollover(rollover);
/* 161 */             menu.repaint();
/*     */           } 
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   protected PropertyChangeListener createPropertyChangeListener(JComponent c) {
/* 169 */     return FlatStylingSupport.createPropertyChangeListener(c, this::installStyle, super.createPropertyChangeListener(c));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle() {
/*     */     try {
/* 175 */       applyStyle(FlatStylingSupport.getResolvedStyle(this.menuItem, "Menu"));
/* 176 */     } catch (RuntimeException ex) {
/* 177 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 183 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 188 */     return FlatMenuItemUI.applyStyleProperty(this.menuItem, this, this.renderer, key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 194 */     return FlatMenuItemUI.getStyleableInfos(this, this.renderer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 200 */     return FlatMenuItemUI.getStyleableValue(this, this.renderer, key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MethodHandles.Lookup getLookupForStyling() {
/* 208 */     return MethodHandles.lookup();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize(JComponent c) {
/* 216 */     return ((JMenu)this.menuItem).isTopLevelMenu() ? c.getPreferredSize() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Dimension getPreferredMenuItemSize(JComponent c, Icon checkIcon, Icon arrowIcon, int defaultTextIconGap) {
/* 221 */     return this.renderer.getPreferredMenuItemSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 226 */     this.renderer.paintMenuItem(g, this.selectionBackground, this.selectionForeground, this.disabledForeground, this.acceleratorForeground, this.acceleratorSelectionForeground);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class FlatMenuRenderer
/*     */     extends FlatMenuItemRenderer
/*     */   {
/* 235 */     protected Insets menuBarSelectionInsets = UIManager.getInsets("MenuBar.selectionInsets");
/* 236 */     protected Insets menuBarSelectionEmbeddedInsets = UIManager.getInsets("MenuBar.selectionEmbeddedInsets");
/* 237 */     protected int menuBarSelectionArc = UIManager.getInt("MenuBar.selectionArc");
/* 238 */     protected Color hoverBackground = UIManager.getColor("MenuBar.hoverBackground");
/* 239 */     protected Color menuBarSelectionBackground = UIManager.getColor("MenuBar.selectionBackground");
/* 240 */     protected Color menuBarSelectionForeground = UIManager.getColor("MenuBar.selectionForeground");
/* 241 */     protected Color menuBarUnderlineSelectionBackground = UIManager.getColor("MenuBar.underlineSelectionBackground");
/* 242 */     protected Color menuBarUnderlineSelectionColor = UIManager.getColor("MenuBar.underlineSelectionColor");
/* 243 */     protected int menuBarUnderlineSelectionHeight = FlatUIUtils.getUIInt("MenuBar.underlineSelectionHeight", -1);
/*     */ 
/*     */ 
/*     */     
/*     */     protected FlatMenuRenderer(JMenuItem menuItem, Icon checkIcon, Icon arrowIcon, Font acceleratorFont, String acceleratorDelimiter) {
/* 248 */       super(menuItem, checkIcon, arrowIcon, acceleratorFont, acceleratorDelimiter);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void paintBackground(Graphics g) {
/* 254 */       super.paintBackground(g);
/*     */       
/* 256 */       if (((JMenu)this.menuItem).isTopLevelMenu() && isHover()) {
/*     */         
/* 258 */         Color color = deriveBackground(getStyleFromMenuBarUI(ui -> ui.hoverBackground, this.hoverBackground));
/* 259 */         if (isUnderlineSelection()) {
/* 260 */           g.setColor(color);
/* 261 */           g.fillRect(0, 0, this.menuItem.getWidth(), this.menuItem.getHeight());
/*     */         } else {
/* 263 */           paintSelection(g, color, this.selectionInsets, this.selectionArc);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected void paintSelection(Graphics g, Color selectionBackground, Insets selectionInsets, int selectionArc) {
/* 270 */       if (((JMenu)this.menuItem).isTopLevelMenu()) {
/* 271 */         if (!isHover()) {
/* 272 */           selectionBackground = getStyleFromMenuBarUI(ui -> ui.selectionBackground, this.menuBarSelectionBackground, selectionBackground);
/*     */         }
/* 274 */         JMenuBar menuBar = (JMenuBar)this.menuItem.getParent();
/* 275 */         JRootPane rootPane = SwingUtilities.getRootPane(menuBar);
/* 276 */         if (rootPane != null && rootPane.getParent() instanceof java.awt.Window && rootPane
/* 277 */           .getJMenuBar() == menuBar && 
/* 278 */           FlatRootPaneUI.isMenuBarEmbedded(rootPane)) {
/*     */           
/* 280 */           selectionInsets = getStyleFromMenuBarUI(ui -> ui.selectionEmbeddedInsets, this.menuBarSelectionEmbeddedInsets);
/*     */         } else {
/* 282 */           selectionInsets = getStyleFromMenuBarUI(ui -> ui.selectionInsets, this.menuBarSelectionInsets);
/*     */         } 
/* 284 */         selectionArc = ((Integer)getStyleFromMenuBarUI(ui -> (ui.selectionArc != -1) ? Integer.valueOf(ui.selectionArc) : null, 
/* 285 */             Integer.valueOf(this.menuBarSelectionArc))).intValue();
/*     */       } 
/*     */       
/* 288 */       super.paintSelection(g, selectionBackground, selectionInsets, selectionArc);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void paintUnderlineSelection(Graphics g, Color underlineSelectionBackground, Color underlineSelectionColor, int underlineSelectionHeight) {
/* 296 */       if (((JMenu)this.menuItem).isTopLevelMenu()) {
/* 297 */         underlineSelectionBackground = getStyleFromMenuBarUI(ui -> ui.underlineSelectionBackground, this.menuBarUnderlineSelectionBackground, underlineSelectionBackground);
/* 298 */         underlineSelectionColor = getStyleFromMenuBarUI(ui -> ui.underlineSelectionColor, this.menuBarUnderlineSelectionColor, underlineSelectionColor);
/* 299 */         underlineSelectionHeight = ((Integer)getStyleFromMenuBarUI(ui -> (ui.underlineSelectionHeight != -1) ? Integer.valueOf(ui.underlineSelectionHeight) : null, 
/* 300 */             Integer.valueOf((this.menuBarUnderlineSelectionHeight != -1) ? this.menuBarUnderlineSelectionHeight : underlineSelectionHeight))).intValue();
/*     */       } 
/*     */       
/* 303 */       super.paintUnderlineSelection(g, underlineSelectionBackground, underlineSelectionColor, underlineSelectionHeight);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void paintText(Graphics g, Rectangle textRect, String text, Color selectionForeground, Color disabledForeground) {
/* 308 */       if (((JMenu)this.menuItem).isTopLevelMenu() && !isUnderlineSelection()) {
/* 309 */         selectionForeground = getStyleFromMenuBarUI(ui -> ui.selectionForeground, this.menuBarSelectionForeground, selectionForeground);
/*     */       }
/* 311 */       super.paintText(g, textRect, text, selectionForeground, disabledForeground);
/*     */     }
/*     */     
/*     */     private boolean isHover() {
/* 315 */       ButtonModel model = this.menuItem.getModel();
/* 316 */       return (model.isRollover() && !model.isArmed() && !model.isSelected() && model.isEnabled());
/*     */     }
/*     */     
/*     */     private <T> T getStyleFromMenuBarUI(Function<FlatMenuBarUI, T> f, T defaultValue, T defaultValue2) {
/* 320 */       return getStyleFromMenuBarUI(f, (defaultValue != null) ? defaultValue : defaultValue2);
/*     */     }
/*     */     
/*     */     private <T> T getStyleFromMenuBarUI(Function<FlatMenuBarUI, T> f, T defaultValue) {
/* 324 */       MenuBarUI ui = ((JMenuBar)this.menuItem.getParent()).getUI();
/* 325 */       if (!(ui instanceof FlatMenuBarUI)) {
/* 326 */         return defaultValue;
/*     */       }
/* 328 */       T value = f.apply((FlatMenuBarUI)ui);
/* 329 */       return (value != null) ? value : defaultValue;
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatMenuUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */