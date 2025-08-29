/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.Map;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JSplitPane;
/*     */ import javax.swing.ToolTipManager;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicSplitPaneDivider;
/*     */ import javax.swing.plaf.basic.BasicSplitPaneUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatSplitPaneUI
/*     */   extends BasicSplitPaneUI
/*     */   implements FlatStylingSupport.StyleableUI
/*     */ {
/*     */   @Styleable
/*     */   protected String arrowType;
/*     */   @Styleable
/*     */   protected Color oneTouchArrowColor;
/*     */   @Styleable
/*     */   protected Color oneTouchHoverArrowColor;
/*     */   @Styleable
/*     */   protected Color oneTouchPressedArrowColor;
/*     */   private PropertyChangeListener propertyChangeListener;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  94 */     return new FlatSplitPaneUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  99 */     super.installUI(c);
/*     */     
/* 101 */     installStyle();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/* 106 */     this.arrowType = UIManager.getString("Component.arrowType");
/*     */ 
/*     */ 
/*     */     
/* 110 */     this.oneTouchArrowColor = UIManager.getColor("SplitPaneDivider.oneTouchArrowColor");
/* 111 */     this.oneTouchHoverArrowColor = UIManager.getColor("SplitPaneDivider.oneTouchHoverArrowColor");
/* 112 */     this.oneTouchPressedArrowColor = UIManager.getColor("SplitPaneDivider.oneTouchPressedArrowColor");
/*     */     
/* 114 */     super.installDefaults();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults() {
/* 119 */     super.uninstallDefaults();
/*     */     
/* 121 */     this.oneTouchArrowColor = null;
/* 122 */     this.oneTouchHoverArrowColor = null;
/* 123 */     this.oneTouchPressedArrowColor = null;
/*     */     
/* 125 */     this.oldStyleValues = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners() {
/* 130 */     super.installListeners();
/*     */     
/* 132 */     this.propertyChangeListener = FlatStylingSupport.createPropertyChangeListener(this.splitPane, this::installStyle, null);
/* 133 */     this.splitPane.addPropertyChangeListener(this.propertyChangeListener);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners() {
/* 138 */     super.uninstallListeners();
/*     */     
/* 140 */     this.splitPane.removePropertyChangeListener(this.propertyChangeListener);
/* 141 */     this.propertyChangeListener = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public BasicSplitPaneDivider createDefaultDivider() {
/* 146 */     return new FlatSplitPaneDivider(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle() {
/*     */     try {
/* 152 */       applyStyle(FlatStylingSupport.getResolvedStyle(this.splitPane, "SplitPane"));
/* 153 */     } catch (RuntimeException ex) {
/* 154 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 160 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*     */     
/* 162 */     if (this.divider instanceof FlatSplitPaneDivider) {
/* 163 */       ((FlatSplitPaneDivider)this.divider).updateStyle();
/*     */     }
/*     */   }
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/*     */     try {
/* 169 */       if (this.divider instanceof FlatSplitPaneDivider)
/* 170 */         return ((FlatSplitPaneDivider)this.divider).applyStyleProperty(key, value); 
/* 171 */     } catch (UnknownStyleException unknownStyleException) {}
/*     */ 
/*     */     
/* 174 */     return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, this.splitPane, key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 180 */     Map<String, Class<?>> infos = FlatStylingSupport.getAnnotatedStyleableInfos(this);
/* 181 */     if (this.divider instanceof FlatSplitPaneDivider)
/* 182 */       infos.putAll(((FlatSplitPaneDivider)this.divider).getStyleableInfos()); 
/* 183 */     return infos;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 189 */     if (this.divider instanceof FlatSplitPaneDivider) {
/* 190 */       Object value = ((FlatSplitPaneDivider)this.divider).getStyleableValue(key);
/* 191 */       if (value != null)
/* 192 */         return value; 
/*     */     } 
/* 194 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */ 
/*     */   
/*     */   protected class FlatSplitPaneDivider
/*     */     extends BasicSplitPaneDivider
/*     */   {
/*     */     @Styleable
/* 202 */     protected String style = UIManager.getString("SplitPaneDivider.style"); @Styleable
/* 203 */     protected Color gripColor = UIManager.getColor("SplitPaneDivider.gripColor"); @Styleable
/* 204 */     protected int gripDotCount = FlatUIUtils.getUIInt("SplitPaneDivider.gripDotCount", 3); @Styleable
/* 205 */     protected int gripDotSize = FlatUIUtils.getUIInt("SplitPaneDivider.gripDotSize", 3); @Styleable
/* 206 */     protected int gripGap = FlatUIUtils.getUIInt("SplitPaneDivider.gripGap", 2);
/*     */     
/*     */     protected FlatSplitPaneDivider(BasicSplitPaneUI ui) {
/* 209 */       super(ui);
/*     */       
/* 211 */       setLayout(new FlatDividerLayout());
/*     */     }
/*     */ 
/*     */     
/*     */     protected Object applyStyleProperty(String key, Object value) {
/* 216 */       return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
/*     */     }
/*     */ 
/*     */     
/*     */     public Map<String, Class<?>> getStyleableInfos() {
/* 221 */       return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*     */     }
/*     */ 
/*     */     
/*     */     public Object getStyleableValue(String key) {
/* 226 */       return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */     }
/*     */     
/*     */     void updateStyle() {
/* 230 */       if (this.leftButton instanceof FlatOneTouchButton)
/* 231 */         ((FlatOneTouchButton)this.leftButton).updateStyle(); 
/* 232 */       if (this.rightButton instanceof FlatOneTouchButton) {
/* 233 */         ((FlatOneTouchButton)this.rightButton).updateStyle();
/*     */       }
/*     */     }
/*     */     
/*     */     public void setDividerSize(int newSize) {
/* 238 */       super.setDividerSize(UIScale.scale(newSize));
/*     */     }
/*     */ 
/*     */     
/*     */     protected JButton createLeftOneTouchButton() {
/* 243 */       return new FlatOneTouchButton(true);
/*     */     }
/*     */ 
/*     */     
/*     */     protected JButton createRightOneTouchButton() {
/* 248 */       return new FlatOneTouchButton(false);
/*     */     }
/*     */ 
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent e) {
/* 253 */       super.propertyChange(e);
/*     */       
/* 255 */       switch (e.getPropertyName()) {
/*     */         
/*     */         case "dividerLocation":
/* 258 */           doLayout();
/*     */           break;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void paint(Graphics g) {
/* 265 */       super.paint(g);
/*     */       
/* 267 */       if ("plain".equals(this.style)) {
/*     */         return;
/*     */       }
/* 270 */       Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
/*     */       
/* 272 */       g.setColor(this.gripColor);
/* 273 */       paintGrip(g, 0, 0, getWidth(), getHeight());
/*     */       
/* 275 */       FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
/*     */     }
/*     */     
/*     */     protected void paintGrip(Graphics g, int x, int y, int width, int height) {
/* 279 */       FlatUIUtils.paintGrip(g, x, y, width, height, 
/* 280 */           (this.splitPane.getOrientation() == 0), this.gripDotCount, this.gripDotSize, this.gripGap, true);
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean isLeftCollapsed() {
/* 285 */       int location = this.splitPane.getDividerLocation();
/* 286 */       Insets insets = this.splitPane.getInsets();
/* 287 */       return (this.orientation == 0) ? (
/* 288 */         (location == insets.top)) : (
/* 289 */         (location == insets.left));
/*     */     }
/*     */     
/*     */     protected boolean isRightCollapsed() {
/* 293 */       int location = this.splitPane.getDividerLocation();
/* 294 */       Insets insets = this.splitPane.getInsets();
/* 295 */       return (this.orientation == 0) ? (
/* 296 */         (location == this.splitPane.getHeight() - getHeight() - insets.bottom)) : (
/* 297 */         (location == this.splitPane.getWidth() - getWidth() - insets.right));
/*     */     }
/*     */ 
/*     */     
/*     */     protected class FlatOneTouchButton
/*     */       extends FlatArrowButton
/*     */     {
/*     */       protected final boolean left;
/*     */ 
/*     */       
/*     */       protected FlatOneTouchButton(boolean left) {
/* 308 */         super(1, FlatSplitPaneUI.this.arrowType, FlatSplitPaneUI.this.oneTouchArrowColor, (Color)null, FlatSplitPaneUI.this.oneTouchHoverArrowColor, (Color)null, FlatSplitPaneUI.this.oneTouchPressedArrowColor, (Color)null);
/*     */         
/* 310 */         setCursor(Cursor.getPredefinedCursor(0));
/* 311 */         ToolTipManager.sharedInstance().registerComponent(this);
/*     */         
/* 313 */         this.left = left;
/*     */       }
/*     */       
/*     */       protected void updateStyle() {
/* 317 */         updateStyle(FlatSplitPaneUI.this.arrowType, FlatSplitPaneUI.this.oneTouchArrowColor, (Color)null, FlatSplitPaneUI.this.oneTouchHoverArrowColor, (Color)null, FlatSplitPaneUI.this.oneTouchPressedArrowColor, (Color)null);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public int getDirection() {
/* 323 */         return (FlatSplitPaneUI.FlatSplitPaneDivider.this.orientation == 0) ? (
/* 324 */           this.left ? 1 : 5) : (
/* 325 */           this.left ? 7 : 3);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public String getToolTipText(MouseEvent e) {
/* 344 */         String key = (FlatSplitPaneUI.FlatSplitPaneDivider.this.orientation == 0) ? (this.left ? (FlatSplitPaneUI.FlatSplitPaneDivider.this.isRightCollapsed() ? "SplitPaneDivider.expandBottomToolTipText" : "SplitPaneDivider.collapseTopToolTipText") : (FlatSplitPaneUI.FlatSplitPaneDivider.this.isLeftCollapsed() ? "SplitPaneDivider.expandTopToolTipText" : "SplitPaneDivider.collapseBottomToolTipText")) : (this.left ? (FlatSplitPaneUI.FlatSplitPaneDivider.this.isRightCollapsed() ? "SplitPaneDivider.expandRightToolTipText" : "SplitPaneDivider.collapseLeftToolTipText") : (FlatSplitPaneUI.FlatSplitPaneDivider.this.isLeftCollapsed() ? "SplitPaneDivider.expandLeftToolTipText" : "SplitPaneDivider.collapseRightToolTipText"));
/*     */ 
/*     */         
/* 347 */         Object value = FlatSplitPaneUI.FlatSplitPaneDivider.this.splitPane.getClientProperty(key);
/* 348 */         if (value instanceof String) {
/* 349 */           return (String)value;
/*     */         }
/*     */         
/* 352 */         return UIManager.getString(key, getLocale());
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected class FlatDividerLayout
/*     */       extends BasicSplitPaneDivider.DividerLayout
/*     */     {
/*     */       public void layoutContainer(Container c) {
/* 363 */         super.layoutContainer(c);
/*     */         
/* 365 */         if (FlatSplitPaneUI.FlatSplitPaneDivider.this.leftButton == null || FlatSplitPaneUI.FlatSplitPaneDivider.this.rightButton == null || !FlatSplitPaneUI.FlatSplitPaneDivider.this.splitPane.isOneTouchExpandable()) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/* 370 */         int extraSize = UIScale.scale(4);
/* 371 */         if (FlatSplitPaneUI.FlatSplitPaneDivider.this.orientation == 0) {
/* 372 */           FlatSplitPaneUI.FlatSplitPaneDivider.this.leftButton.setSize(FlatSplitPaneUI.FlatSplitPaneDivider.this.leftButton.getWidth() + extraSize, FlatSplitPaneUI.FlatSplitPaneDivider.this.leftButton.getHeight());
/* 373 */           FlatSplitPaneUI.FlatSplitPaneDivider.this.rightButton.setBounds(FlatSplitPaneUI.FlatSplitPaneDivider.this.leftButton.getX() + FlatSplitPaneUI.FlatSplitPaneDivider.this.leftButton.getWidth(), FlatSplitPaneUI.FlatSplitPaneDivider.this.rightButton.getY(), FlatSplitPaneUI.FlatSplitPaneDivider.this
/* 374 */               .rightButton.getWidth() + extraSize, FlatSplitPaneUI.FlatSplitPaneDivider.this.rightButton.getHeight());
/*     */         } else {
/* 376 */           FlatSplitPaneUI.FlatSplitPaneDivider.this.leftButton.setSize(FlatSplitPaneUI.FlatSplitPaneDivider.this.leftButton.getWidth(), FlatSplitPaneUI.FlatSplitPaneDivider.this.leftButton.getHeight() + extraSize);
/* 377 */           FlatSplitPaneUI.FlatSplitPaneDivider.this.rightButton.setBounds(FlatSplitPaneUI.FlatSplitPaneDivider.this.rightButton.getX(), FlatSplitPaneUI.FlatSplitPaneDivider.this.leftButton.getY() + FlatSplitPaneUI.FlatSplitPaneDivider.this.leftButton.getHeight(), FlatSplitPaneUI.FlatSplitPaneDivider.this
/* 378 */               .rightButton.getWidth(), FlatSplitPaneUI.FlatSplitPaneDivider.this.rightButton.getHeight() + extraSize);
/*     */         } 
/*     */ 
/*     */         
/* 382 */         boolean leftCollapsed = FlatSplitPaneUI.FlatSplitPaneDivider.this.isLeftCollapsed();
/* 383 */         boolean rightCollapsed = FlatSplitPaneUI.FlatSplitPaneDivider.this.isRightCollapsed();
/* 384 */         if (leftCollapsed || rightCollapsed) {
/* 385 */           FlatSplitPaneUI.FlatSplitPaneDivider.this.leftButton.setVisible(!leftCollapsed);
/* 386 */           FlatSplitPaneUI.FlatSplitPaneDivider.this.rightButton.setVisible(!rightCollapsed);
/*     */         } else {
/* 388 */           Object expandableSide = FlatSplitPaneUI.FlatSplitPaneDivider.this.splitPane.getClientProperty("JSplitPane.expandableSide");
/* 389 */           FlatSplitPaneUI.FlatSplitPaneDivider.this.leftButton.setVisible((expandableSide == null || !"left".equals(expandableSide)));
/* 390 */           FlatSplitPaneUI.FlatSplitPaneDivider.this.rightButton.setVisible((expandableSide == null || !"right".equals(expandableSide)));
/*     */         } 
/*     */ 
/*     */         
/* 394 */         if (!FlatSplitPaneUI.FlatSplitPaneDivider.this.leftButton.isVisible())
/* 395 */           FlatSplitPaneUI.FlatSplitPaneDivider.this.rightButton.setLocation(FlatSplitPaneUI.FlatSplitPaneDivider.this.leftButton.getLocation()); 
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatSplitPaneUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */