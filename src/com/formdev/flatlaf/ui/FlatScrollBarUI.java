/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.FlatLaf;
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.SystemInfo;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.lang.invoke.MethodHandles;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import javax.swing.InputMap;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicScrollBarUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @StyleableFields({@StyleableField(cls = BasicScrollBarUI.class, key = "track", fieldName = "trackColor"), @StyleableField(cls = BasicScrollBarUI.class, key = "thumb", fieldName = "thumbColor"), @StyleableField(cls = BasicScrollBarUI.class, key = "width", fieldName = "scrollBarWidth"), @StyleableField(cls = BasicScrollBarUI.class, key = "minimumThumbSize"), @StyleableField(cls = BasicScrollBarUI.class, key = "maximumThumbSize")})
/*     */ public class FlatScrollBarUI
/*     */   extends BasicScrollBarUI
/*     */   implements FlatStylingSupport.StyleableUI, FlatStylingSupport.StyleableLookupProvider
/*     */ {
/*     */   @Styleable
/*     */   protected boolean allowsAbsolutePositioning;
/*     */   @Styleable
/*     */   protected Dimension minimumButtonSize;
/*     */   @Styleable
/*     */   protected Insets trackInsets;
/*     */   @Styleable
/*     */   protected Insets thumbInsets;
/*     */   @Styleable
/*     */   protected int trackArc;
/*     */   @Styleable
/*     */   protected int thumbArc;
/*     */   @Styleable
/*     */   protected Color hoverTrackColor;
/*     */   @Styleable
/*     */   protected Color hoverThumbColor;
/*     */   @Styleable
/*     */   protected boolean hoverThumbWithTrack;
/*     */   @Styleable
/*     */   protected Color pressedTrackColor;
/*     */   @Styleable
/*     */   protected Color pressedThumbColor;
/*     */   @Styleable
/*     */   protected boolean pressedThumbWithTrack;
/*     */   @Styleable
/*     */   protected boolean showButtons;
/*     */   @Styleable
/*     */   protected String arrowType;
/*     */   @Styleable
/*     */   protected Color buttonArrowColor;
/*     */   @Styleable
/*     */   protected Color buttonDisabledArrowColor;
/*     */   @Styleable
/*     */   protected Color hoverButtonBackground;
/*     */   @Styleable
/*     */   protected Color pressedButtonBackground;
/*     */   private MouseAdapter hoverListener;
/*     */   protected boolean hoverTrack;
/*     */   protected boolean hoverThumb;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   private boolean isAWTPeer;
/*     */   private static boolean isPressed;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/* 126 */     return new FlatScrollBarUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/* 131 */     super.installUI(c);
/*     */     
/* 133 */     installStyle();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners() {
/* 138 */     super.installListeners();
/*     */     
/* 140 */     this.hoverListener = new ScrollBarHoverListener();
/* 141 */     this.scrollbar.addMouseListener(this.hoverListener);
/* 142 */     this.scrollbar.addMouseMotionListener(this.hoverListener);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners() {
/* 147 */     super.uninstallListeners();
/*     */     
/* 149 */     this.scrollbar.removeMouseListener(this.hoverListener);
/* 150 */     this.scrollbar.removeMouseMotionListener(this.hoverListener);
/* 151 */     this.hoverListener = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/* 156 */     super.installDefaults();
/*     */     
/* 158 */     this.allowsAbsolutePositioning = super.getSupportsAbsolutePositioning();
/*     */     
/* 160 */     this.minimumButtonSize = UIManager.getDimension("ScrollBar.minimumButtonSize");
/* 161 */     this.trackInsets = UIManager.getInsets("ScrollBar.trackInsets");
/* 162 */     this.thumbInsets = UIManager.getInsets("ScrollBar.thumbInsets");
/* 163 */     this.trackArc = UIManager.getInt("ScrollBar.trackArc");
/* 164 */     this.thumbArc = UIManager.getInt("ScrollBar.thumbArc");
/* 165 */     this.hoverTrackColor = UIManager.getColor("ScrollBar.hoverTrackColor");
/* 166 */     this.hoverThumbColor = UIManager.getColor("ScrollBar.hoverThumbColor");
/* 167 */     this.hoverThumbWithTrack = UIManager.getBoolean("ScrollBar.hoverThumbWithTrack");
/* 168 */     this.pressedTrackColor = UIManager.getColor("ScrollBar.pressedTrackColor");
/* 169 */     this.pressedThumbColor = UIManager.getColor("ScrollBar.pressedThumbColor");
/* 170 */     this.pressedThumbWithTrack = UIManager.getBoolean("ScrollBar.pressedThumbWithTrack");
/*     */     
/* 172 */     this.showButtons = UIManager.getBoolean("ScrollBar.showButtons");
/* 173 */     this.arrowType = UIManager.getString("Component.arrowType");
/* 174 */     this.buttonArrowColor = UIManager.getColor("ScrollBar.buttonArrowColor");
/* 175 */     this.buttonDisabledArrowColor = UIManager.getColor("ScrollBar.buttonDisabledArrowColor");
/* 176 */     this.hoverButtonBackground = UIManager.getColor("ScrollBar.hoverButtonBackground");
/* 177 */     this.pressedButtonBackground = UIManager.getColor("ScrollBar.pressedButtonBackground");
/*     */ 
/*     */     
/* 180 */     if (this.trackInsets == null)
/* 181 */       this.trackInsets = new Insets(0, 0, 0, 0); 
/* 182 */     if (this.thumbInsets == null) {
/* 183 */       this.thumbInsets = new Insets(0, 0, 0, 0);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void uninstallDefaults() {
/* 188 */     super.uninstallDefaults();
/*     */     
/* 190 */     this.minimumButtonSize = null;
/* 191 */     this.trackInsets = null;
/* 192 */     this.thumbInsets = null;
/* 193 */     this.hoverTrackColor = null;
/* 194 */     this.hoverThumbColor = null;
/* 195 */     this.pressedTrackColor = null;
/* 196 */     this.pressedThumbColor = null;
/*     */     
/* 198 */     this.buttonArrowColor = null;
/* 199 */     this.buttonDisabledArrowColor = null;
/* 200 */     this.hoverButtonBackground = null;
/* 201 */     this.pressedButtonBackground = null;
/*     */     
/* 203 */     this.oldStyleValues = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected PropertyChangeListener createPropertyChangeListener() {
/* 208 */     PropertyChangeListener superListener = super.createPropertyChangeListener();
/* 209 */     return e -> {
/*     */         InputMap inputMap;
/*     */         superListener.propertyChange(e);
/*     */         switch (e.getPropertyName()) {
/*     */           case "JScrollBar.showButtons":
/*     */             this.scrollbar.revalidate();
/*     */             this.scrollbar.repaint();
/*     */             break;
/*     */           case "FlatLaf.style":
/*     */           case "FlatLaf.styleClass":
/*     */             installStyle();
/*     */             this.scrollbar.revalidate();
/*     */             this.scrollbar.repaint();
/*     */             break;
/*     */           case "componentOrientation":
/*     */             inputMap = (InputMap)UIManager.get("ScrollBar.ancestorInputMap");
/*     */             if (!this.scrollbar.getComponentOrientation().isLeftToRight()) {
/*     */               InputMap rtlInputMap = (InputMap)UIManager.get("ScrollBar.ancestorInputMap.RightToLeft");
/*     */               if (rtlInputMap != null) {
/*     */                 rtlInputMap.setParent(inputMap);
/*     */                 inputMap = rtlInputMap;
/*     */               } 
/*     */             } 
/*     */             SwingUtilities.replaceUIInputMap(this.scrollbar, 1, inputMap);
/*     */             break;
/*     */           case "ancestor":
/*     */             if (SystemInfo.isMacOS) {
/*     */               Container p = this.scrollbar.getParent();
/*     */               int i = 0;
/*     */               while (i < 2 && p != null) {
/*     */                 if (FlatUIUtils.isAWTPeer(p)) {
/*     */                   this.isAWTPeer = true;
/*     */                   if (FlatLaf.isLafDark()) {
/*     */                     FlatUIUtils.runWithLightAWTPeerUIDefaults(());
/*     */                   }
/*     */                   break;
/*     */                 } 
/*     */                 i++;
/*     */                 p = p.getParent();
/*     */               } 
/*     */             } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void installStyle() {
/*     */     try {
/* 275 */       applyStyle(FlatStylingSupport.getResolvedStyle(this.scrollbar, "ScrollBar"));
/* 276 */     } catch (RuntimeException ex) {
/* 277 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 283 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*     */     
/* 285 */     if (this.incrButton instanceof FlatScrollBarButton)
/* 286 */       ((FlatScrollBarButton)this.incrButton).updateStyle(); 
/* 287 */     if (this.decrButton instanceof FlatScrollBarButton) {
/* 288 */       ((FlatScrollBarButton)this.decrButton).updateStyle();
/*     */     }
/*     */   }
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 293 */     return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, this.scrollbar, key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 299 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 305 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MethodHandles.Lookup getLookupForStyling() {
/* 313 */     return MethodHandles.lookup();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(JComponent c) {
/* 318 */     return UIScale.scale(super.getPreferredSize(c));
/*     */   }
/*     */ 
/*     */   
/*     */   protected JButton createDecreaseButton(int orientation) {
/* 323 */     return new FlatScrollBarButton(orientation);
/*     */   }
/*     */ 
/*     */   
/*     */   protected JButton createIncreaseButton(int orientation) {
/* 328 */     return new FlatScrollBarButton(orientation);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isShowButtons() {
/* 333 */     Object showButtons = this.scrollbar.getClientProperty("JScrollBar.showButtons");
/* 334 */     if (showButtons == null && this.scrollbar.getParent() instanceof JScrollPane) {
/* 335 */       JScrollPane scrollPane = (JScrollPane)this.scrollbar.getParent();
/*     */       
/* 337 */       showButtons = scrollPane.getClientProperty("JScrollBar.showButtons");
/* 338 */       if (showButtons == null && scrollPane.getUI() instanceof FlatScrollPaneUI)
/*     */       {
/* 340 */         showButtons = ((FlatScrollPaneUI)scrollPane.getUI()).showButtons;
/*     */       }
/*     */     } 
/* 343 */     return (showButtons != null) ? Objects.equals(showButtons, Boolean.valueOf(true)) : this.showButtons;
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 348 */     Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
/* 349 */     super.paint(g, c);
/* 350 */     FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
/* 355 */     if (trackBounds.isEmpty() || !this.scrollbar.isEnabled()) {
/*     */       return;
/*     */     }
/* 358 */     g.setColor(getTrackColor(c, this.hoverTrack, (isPressed && this.hoverTrack && !this.hoverThumb)));
/* 359 */     paintTrackOrThumb(g, c, trackBounds, this.trackInsets, this.trackArc);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
/* 364 */     if (thumbBounds.isEmpty() || !this.scrollbar.isEnabled()) {
/*     */       return;
/*     */     }
/* 367 */     g.setColor(getThumbColor(c, (this.hoverThumb || (this.hoverThumbWithTrack && this.hoverTrack)), (isPressed && (this.hoverThumb || (this.pressedThumbWithTrack && this.hoverTrack)))));
/*     */     
/* 369 */     paintTrackOrThumb(g, c, thumbBounds, this.thumbInsets, this.thumbArc);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintTrackOrThumb(Graphics g, JComponent c, Rectangle bounds, Insets insets, int arc) {
/* 374 */     if (this.scrollbar.getOrientation() == 0) {
/* 375 */       insets = new Insets(insets.right, insets.top, insets.left, insets.bottom);
/*     */     }
/*     */     
/* 378 */     bounds = FlatUIUtils.subtractInsets(bounds, UIScale.scale(insets));
/*     */     
/* 380 */     if (arc <= 0) {
/*     */       
/* 382 */       g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
/*     */     } else {
/*     */       
/* 385 */       arc = Math.min(UIScale.scale(arc), Math.min(bounds.width, bounds.height));
/* 386 */       g.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, arc, arc);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintDecreaseHighlight(Graphics g) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintIncreaseHighlight(Graphics g) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected Color getTrackColor(JComponent c, boolean hover, boolean pressed) {
/* 401 */     Color trackColor = FlatUIUtils.deriveColor(this.trackColor, c.getBackground());
/* 402 */     return (pressed && this.pressedTrackColor != null) ? 
/* 403 */       FlatUIUtils.deriveColor(this.pressedTrackColor, trackColor) : (
/* 404 */       (hover && this.hoverTrackColor != null && !this.isAWTPeer) ? 
/* 405 */       FlatUIUtils.deriveColor(this.hoverTrackColor, trackColor) : 
/* 406 */       trackColor);
/*     */   }
/*     */   
/*     */   protected Color getThumbColor(JComponent c, boolean hover, boolean pressed) {
/* 410 */     Color trackColor = FlatUIUtils.deriveColor(this.trackColor, c.getBackground());
/* 411 */     Color thumbColor = FlatUIUtils.deriveColor(this.thumbColor, trackColor);
/* 412 */     return (pressed && this.pressedThumbColor != null) ? 
/* 413 */       FlatUIUtils.deriveColor(this.pressedThumbColor, thumbColor) : (
/* 414 */       (hover && this.hoverThumbColor != null && !this.isAWTPeer) ? 
/* 415 */       FlatUIUtils.deriveColor(this.hoverThumbColor, thumbColor) : 
/* 416 */       thumbColor);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Dimension getMinimumThumbSize() {
/* 421 */     return UIScale.scale(FlatUIUtils.addInsets(super.getMinimumThumbSize(), this.thumbInsets));
/*     */   }
/*     */ 
/*     */   
/*     */   protected Dimension getMaximumThumbSize() {
/* 426 */     return UIScale.scale(FlatUIUtils.addInsets(super.getMaximumThumbSize(), this.thumbInsets));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getSupportsAbsolutePositioning() {
/* 431 */     return this.allowsAbsolutePositioning;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private class ScrollBarHoverListener
/*     */     extends MouseAdapter
/*     */   {
/*     */     private ScrollBarHoverListener() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseExited(MouseEvent e) {
/* 444 */       if (!FlatScrollBarUI.isPressed) {
/* 445 */         FlatScrollBarUI.this.hoverTrack = FlatScrollBarUI.this.hoverThumb = false;
/* 446 */         repaint();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseMoved(MouseEvent e) {
/* 452 */       if (!FlatScrollBarUI.isPressed) {
/* 453 */         update(e.getX(), e.getY());
/*     */       }
/*     */     }
/*     */     
/*     */     public void mousePressed(MouseEvent e) {
/* 458 */       if (SwingUtilities.isLeftMouseButton(e) || isAbsolutePositioning(e)) {
/* 459 */         FlatScrollBarUI.isPressed = true;
/* 460 */         repaint();
/*     */ 
/*     */ 
/*     */         
/* 464 */         if (isAbsolutePositioning(e)) {
/* 465 */           update(e.getX(), e.getY());
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*     */     public void mouseReleased(MouseEvent e) {
/* 471 */       if (SwingUtilities.isLeftMouseButton(e) || isAbsolutePositioning(e)) {
/* 472 */         FlatScrollBarUI.isPressed = false;
/* 473 */         repaint();
/*     */       } 
/*     */       
/* 476 */       update(e.getX(), e.getY());
/*     */     }
/*     */     
/*     */     private boolean isAbsolutePositioning(MouseEvent e) {
/* 480 */       return (FlatScrollBarUI.this.getSupportsAbsolutePositioning() && SwingUtilities.isMiddleMouseButton(e));
/*     */     }
/*     */     
/*     */     private void update(int x, int y) {
/* 484 */       boolean inTrack = FlatScrollBarUI.this.getTrackBounds().contains(x, y);
/* 485 */       boolean inThumb = FlatScrollBarUI.this.getThumbBounds().contains(x, y);
/* 486 */       if (inTrack != FlatScrollBarUI.this.hoverTrack || inThumb != FlatScrollBarUI.this.hoverThumb) {
/* 487 */         FlatScrollBarUI.this.hoverTrack = inTrack;
/* 488 */         FlatScrollBarUI.this.hoverThumb = inThumb;
/* 489 */         repaint();
/*     */       } 
/*     */     }
/*     */     
/*     */     private void repaint() {
/* 494 */       if (FlatScrollBarUI.this.scrollbar.isEnabled()) {
/* 495 */         FlatScrollBarUI.this.scrollbar.repaint();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class FlatScrollBarButton
/*     */     extends FlatArrowButton
/*     */   {
/*     */     protected FlatScrollBarButton(int direction) {
/* 505 */       this(direction, FlatScrollBarUI.this.arrowType, FlatScrollBarUI.this.buttonArrowColor, FlatScrollBarUI.this.buttonDisabledArrowColor, (Color)null, FlatScrollBarUI.this.hoverButtonBackground, (Color)null, FlatScrollBarUI.this.pressedButtonBackground);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected FlatScrollBarButton(int direction, String type, Color foreground, Color disabledForeground, Color hoverForeground, Color hoverBackground, Color pressedForeground, Color pressedBackground) {
/* 512 */       super(direction, type, foreground, disabledForeground, hoverForeground, hoverBackground, pressedForeground, pressedBackground);
/*     */ 
/*     */       
/* 515 */       setFocusable(false);
/* 516 */       setRequestFocusEnabled(false);
/*     */     }
/*     */     
/*     */     protected void updateStyle() {
/* 520 */       updateStyle(FlatScrollBarUI.this.arrowType, FlatScrollBarUI.this.buttonArrowColor, FlatScrollBarUI.this.buttonDisabledArrowColor, (Color)null, FlatScrollBarUI.this.hoverButtonBackground, (Color)null, FlatScrollBarUI.this.pressedButtonBackground);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getArrowWidth() {
/* 528 */       int arrowWidth = Math.round(6.0F * FlatScrollBarUI.this.scrollBarWidth / 10.0F);
/*     */ 
/*     */       
/* 531 */       arrowWidth = FlatScrollBarUI.this.scrollBarWidth - (FlatScrollBarUI.this.scrollBarWidth - arrowWidth) / 2 * 2;
/*     */       
/* 533 */       return arrowWidth;
/*     */     }
/*     */ 
/*     */     
/*     */     protected Color deriveBackground(Color background) {
/* 538 */       return FlatUIUtils.deriveColor(background, FlatScrollBarUI.this.scrollbar.getBackground());
/*     */     }
/*     */ 
/*     */     
/*     */     public Dimension getPreferredSize() {
/* 543 */       if (FlatScrollBarUI.this.isShowButtons()) {
/* 544 */         int w = UIScale.scale(Math.max(FlatScrollBarUI.this.scrollBarWidth, (FlatScrollBarUI.this.minimumButtonSize != null) ? FlatScrollBarUI.this.minimumButtonSize.width : 0));
/* 545 */         int h = UIScale.scale(Math.max(FlatScrollBarUI.this.scrollBarWidth, (FlatScrollBarUI.this.minimumButtonSize != null) ? FlatScrollBarUI.this.minimumButtonSize.height : 0));
/* 546 */         return new Dimension(w, h);
/*     */       } 
/* 548 */       return new Dimension();
/*     */     }
/*     */ 
/*     */     
/*     */     public Dimension getMinimumSize() {
/* 553 */       return FlatScrollBarUI.this.isShowButtons() ? super.getMinimumSize() : new Dimension();
/*     */     }
/*     */ 
/*     */     
/*     */     public Dimension getMaximumSize() {
/* 558 */       return FlatScrollBarUI.this.isShowButtons() ? super.getMaximumSize() : new Dimension();
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatScrollBarUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */