/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.FlatClientProperties;
/*     */ import com.formdev.flatlaf.FlatLaf;
/*     */ import com.formdev.flatlaf.icons.FlatHelpButtonIcon;
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.RoundRectangle2D;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.ButtonModel;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.JToolBar;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.plaf.ButtonUI;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.ToolBarUI;
/*     */ import javax.swing.plaf.basic.BasicButtonListener;
/*     */ import javax.swing.plaf.basic.BasicButtonUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatButtonUI
/*     */   extends BasicButtonUI
/*     */   implements FlatStylingSupport.StyleableUI
/*     */ {
/*     */   @Styleable
/*     */   protected int minimumWidth;
/*     */   protected int iconTextGap;
/*     */   protected Color background;
/*     */   protected Color foreground;
/*     */   protected Color startBackground;
/*     */   protected Color endBackground;
/*     */   @Styleable
/*     */   protected Color focusedBackground;
/*     */   @Styleable
/*     */   protected Color focusedForeground;
/*     */   @Styleable
/*     */   protected Color hoverBackground;
/*     */   @Styleable
/*     */   protected Color hoverForeground;
/*     */   @Styleable
/*     */   protected Color pressedBackground;
/*     */   @Styleable
/*     */   protected Color pressedForeground;
/*     */   @Styleable
/*     */   protected Color selectedBackground;
/*     */   @Styleable
/*     */   protected Color selectedForeground;
/*     */   @Styleable
/*     */   protected Color disabledBackground;
/*     */   @Styleable
/*     */   protected Color disabledText;
/*     */   @Styleable
/*     */   protected Color disabledSelectedBackground;
/*     */   @Styleable
/*     */   protected Color disabledSelectedForeground;
/*     */   @Styleable(dot = true)
/*     */   protected Color defaultBackground;
/*     */   protected Color defaultEndBackground;
/*     */   @Styleable(dot = true)
/*     */   protected Color defaultForeground;
/*     */   @Styleable(dot = true)
/*     */   protected Color defaultFocusedBackground;
/*     */   @Styleable(dot = true)
/*     */   protected Color defaultFocusedForeground;
/*     */   @Styleable(dot = true)
/*     */   protected Color defaultHoverBackground;
/*     */   @Styleable(dot = true)
/*     */   protected Color defaultHoverForeground;
/*     */   @Styleable(dot = true)
/*     */   protected Color defaultPressedBackground;
/*     */   @Styleable(dot = true)
/*     */   protected Color defaultPressedForeground;
/*     */   @Styleable(dot = true)
/*     */   protected boolean defaultBoldText;
/*     */   @Styleable
/*     */   protected boolean paintShadow;
/*     */   @Styleable
/*     */   protected int shadowWidth;
/*     */   @Styleable
/*     */   protected Color shadowColor;
/*     */   @Styleable(dot = true)
/*     */   protected Color defaultShadowColor;
/*     */   @Styleable(dot = true)
/*     */   protected Color toolbarHoverBackground;
/*     */   @Styleable(dot = true)
/*     */   protected Color toolbarHoverForeground;
/*     */   @Styleable(dot = true)
/*     */   protected Color toolbarPressedBackground;
/*     */   @Styleable(dot = true)
/*     */   protected Color toolbarPressedForeground;
/*     */   @Styleable(dot = true)
/*     */   protected Color toolbarSelectedBackground;
/*     */   @Styleable(dot = true)
/*     */   protected Color toolbarSelectedForeground;
/*     */   @Styleable(dot = true)
/*     */   protected Color toolbarDisabledSelectedBackground;
/*     */   @Styleable(dot = true)
/*     */   protected Color toolbarDisabledSelectedForeground;
/*     */   @Styleable
/*     */   protected String buttonType;
/*     */   @Styleable
/*     */   protected boolean squareSize;
/*     */   @Styleable
/*     */   protected int minimumHeight;
/*     */   private Icon helpButtonIcon;
/*     */   private Insets defaultMargin;
/*     */   private final boolean shared;
/*     */   private boolean helpButtonIconShared = true;
/*     */   private boolean defaults_initialized = false;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   private AtomicBoolean borderShared;
/*     */   static final int TYPE_OTHER = -1;
/*     */   static final int TYPE_SQUARE = 0;
/*     */   static final int TYPE_ROUND_RECT = 1;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/* 187 */     return (FlatUIUtils.canUseSharedUI(c) && !FlatUIUtils.needsLightAWTPeer(c)) ? 
/* 188 */       FlatUIUtils.createSharedUI(FlatButtonUI.class, () -> new FlatButtonUI(true)) : 
/* 189 */       new FlatButtonUI(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected FlatButtonUI(boolean shared) {
/* 194 */     this.shared = shared;
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/* 199 */     if (FlatUIUtils.needsLightAWTPeer(c)) {
/* 200 */       FlatUIUtils.runWithLightAWTPeerUIDefaults(() -> installUIImpl(c));
/*     */     } else {
/* 202 */       installUIImpl(c);
/*     */     } 
/*     */   }
/*     */   private void installUIImpl(JComponent c) {
/* 206 */     super.installUI(c);
/*     */     
/* 208 */     installStyle((AbstractButton)c);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults(AbstractButton b) {
/* 213 */     super.installDefaults(b);
/*     */     
/* 215 */     if (!this.defaults_initialized) {
/* 216 */       String prefix = getPropertyPrefix();
/*     */       
/* 218 */       this.minimumWidth = UIManager.getInt(prefix + "minimumWidth");
/* 219 */       this.iconTextGap = FlatUIUtils.getUIInt(prefix + "iconTextGap", 4);
/*     */       
/* 221 */       this.background = UIManager.getColor(prefix + "background");
/* 222 */       this.foreground = UIManager.getColor(prefix + "foreground");
/*     */       
/* 224 */       this.startBackground = UIManager.getColor(prefix + "startBackground");
/* 225 */       this.endBackground = UIManager.getColor(prefix + "endBackground");
/* 226 */       this.focusedBackground = UIManager.getColor(prefix + "focusedBackground");
/* 227 */       this.focusedForeground = UIManager.getColor(prefix + "focusedForeground");
/* 228 */       this.hoverBackground = UIManager.getColor(prefix + "hoverBackground");
/* 229 */       this.hoverForeground = UIManager.getColor(prefix + "hoverForeground");
/* 230 */       this.pressedBackground = UIManager.getColor(prefix + "pressedBackground");
/* 231 */       this.pressedForeground = UIManager.getColor(prefix + "pressedForeground");
/* 232 */       this.selectedBackground = UIManager.getColor(prefix + "selectedBackground");
/* 233 */       this.selectedForeground = UIManager.getColor(prefix + "selectedForeground");
/* 234 */       this.disabledBackground = UIManager.getColor(prefix + "disabledBackground");
/* 235 */       this.disabledText = UIManager.getColor(prefix + "disabledText");
/* 236 */       this.disabledSelectedBackground = UIManager.getColor(prefix + "disabledSelectedBackground");
/* 237 */       this.disabledSelectedForeground = UIManager.getColor(prefix + "disabledSelectedForeground");
/*     */       
/* 239 */       this.defaultBackground = FlatUIUtils.getUIColor("Button.default.startBackground", "Button.default.background");
/* 240 */       this.defaultEndBackground = UIManager.getColor("Button.default.endBackground");
/* 241 */       this.defaultForeground = UIManager.getColor("Button.default.foreground");
/* 242 */       this.defaultFocusedBackground = UIManager.getColor("Button.default.focusedBackground");
/* 243 */       this.defaultFocusedForeground = UIManager.getColor("Button.default.focusedForeground");
/* 244 */       this.defaultHoverBackground = UIManager.getColor("Button.default.hoverBackground");
/* 245 */       this.defaultHoverForeground = UIManager.getColor("Button.default.hoverForeground");
/* 246 */       this.defaultPressedBackground = UIManager.getColor("Button.default.pressedBackground");
/* 247 */       this.defaultPressedForeground = UIManager.getColor("Button.default.pressedForeground");
/* 248 */       this.defaultBoldText = UIManager.getBoolean("Button.default.boldText");
/*     */       
/* 250 */       this.paintShadow = UIManager.getBoolean("Button.paintShadow");
/* 251 */       this.shadowWidth = FlatUIUtils.getUIInt("Button.shadowWidth", 2);
/* 252 */       this.shadowColor = UIManager.getColor("Button.shadowColor");
/* 253 */       this.defaultShadowColor = UIManager.getColor("Button.default.shadowColor");
/*     */       
/* 255 */       this.toolbarHoverBackground = UIManager.getColor(prefix + "toolbar.hoverBackground");
/* 256 */       this.toolbarHoverForeground = UIManager.getColor(prefix + "toolbar.hoverForeground");
/* 257 */       this.toolbarPressedBackground = UIManager.getColor(prefix + "toolbar.pressedBackground");
/* 258 */       this.toolbarPressedForeground = UIManager.getColor(prefix + "toolbar.pressedForeground");
/* 259 */       this.toolbarSelectedBackground = UIManager.getColor(prefix + "toolbar.selectedBackground");
/* 260 */       this.toolbarSelectedForeground = UIManager.getColor(prefix + "toolbar.selectedForeground");
/* 261 */       this.toolbarDisabledSelectedBackground = UIManager.getColor(prefix + "toolbar.disabledSelectedBackground");
/* 262 */       this.toolbarDisabledSelectedForeground = UIManager.getColor(prefix + "toolbar.disabledSelectedForeground");
/*     */       
/* 264 */       this.helpButtonIcon = UIManager.getIcon("HelpButton.icon");
/* 265 */       this.defaultMargin = UIManager.getInsets(prefix + "margin");
/*     */       
/* 267 */       this.helpButtonIconShared = true;
/* 268 */       this.defaults_initialized = true;
/*     */     } 
/*     */     
/* 271 */     if (this.startBackground != null) {
/* 272 */       Color bg = b.getBackground();
/* 273 */       if (bg == null || bg instanceof javax.swing.plaf.UIResource) {
/* 274 */         b.setBackground(this.startBackground);
/*     */       }
/*     */     } 
/* 277 */     LookAndFeel.installProperty(b, "opaque", Boolean.valueOf(false));
/* 278 */     LookAndFeel.installProperty(b, "iconTextGap", Integer.valueOf(UIScale.scale(this.iconTextGap)));
/*     */     
/* 280 */     MigLayoutVisualPadding.install(b);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults(AbstractButton b) {
/* 285 */     super.uninstallDefaults(b);
/*     */     
/* 287 */     this.oldStyleValues = null;
/* 288 */     this.borderShared = null;
/*     */     
/* 290 */     MigLayoutVisualPadding.uninstall(b);
/* 291 */     this.defaults_initialized = false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BasicButtonListener createButtonListener(AbstractButton b) {
/* 296 */     return new FlatButtonListener(b);
/*     */   }
/*     */   
/*     */   protected void propertyChange(AbstractButton b, PropertyChangeEvent e) {
/* 300 */     switch (e.getPropertyName()) {
/*     */       case "JButton.squareSize":
/*     */       case "JComponent.minimumWidth":
/*     */       case "JComponent.minimumHeight":
/* 304 */         b.revalidate();
/*     */         break;
/*     */       
/*     */       case "JButton.buttonType":
/* 308 */         b.revalidate();
/* 309 */         b.repaint();
/*     */         break;
/*     */       
/*     */       case "JComponent.outline":
/* 313 */         b.repaint();
/*     */         break;
/*     */       
/*     */       case "FlatLaf.style":
/*     */       case "FlatLaf.styleClass":
/* 318 */         if (this.shared && FlatStylingSupport.hasStyleProperty(b)) {
/*     */ 
/*     */           
/* 321 */           b.updateUI();
/*     */         } else {
/* 323 */           installStyle(b);
/* 324 */         }  b.revalidate();
/* 325 */         b.repaint();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle(AbstractButton b) {
/*     */     try {
/* 333 */       applyStyle(b, FlatStylingSupport.getResolvedStyle(b, getStyleType()));
/* 334 */     } catch (RuntimeException ex) {
/* 335 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   String getStyleType() {
/* 341 */     return "Button";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(AbstractButton b, Object style) {
/* 346 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, (key, value) -> applyStyleProperty(b, key, value));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(AbstractButton b, String key, Object value) {
/* 352 */     if (key.startsWith("help.")) {
/* 353 */       if (!(this.helpButtonIcon instanceof FlatHelpButtonIcon)) {
/* 354 */         return new FlatStylingSupport.UnknownStyleException(key);
/*     */       }
/* 356 */       if (this.helpButtonIconShared) {
/* 357 */         this.helpButtonIcon = FlatStylingSupport.cloneIcon(this.helpButtonIcon);
/* 358 */         this.helpButtonIconShared = false;
/*     */       } 
/*     */       
/* 361 */       key = key.substring("help.".length());
/* 362 */       return ((FlatHelpButtonIcon)this.helpButtonIcon).applyStyleProperty(key, value);
/*     */     } 
/*     */     
/* 365 */     if (this.borderShared == null)
/* 366 */       this.borderShared = new AtomicBoolean(true); 
/* 367 */     return FlatStylingSupport.applyToAnnotatedObjectOrBorder(this, key, value, b, this.borderShared);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 373 */     Map<String, Class<?>> infos = FlatStylingSupport.getAnnotatedStyleableInfos(this, c.getBorder());
/* 374 */     if (this.helpButtonIcon instanceof FlatHelpButtonIcon)
/* 375 */       FlatStylingSupport.putAllPrefixKey(infos, "help.", ((FlatHelpButtonIcon)this.helpButtonIcon).getStyleableInfos()); 
/* 376 */     return infos;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 382 */     if (key.startsWith("help.")) {
/* 383 */       return (this.helpButtonIcon instanceof FlatHelpButtonIcon) ? (
/* 384 */         (FlatHelpButtonIcon)this.helpButtonIcon).getStyleableValue(key.substring("help.".length())) : 
/* 385 */         null;
/*     */     }
/*     */     
/* 388 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, c.getBorder(), key);
/*     */   }
/*     */   
/*     */   static boolean isContentAreaFilled(Component c) {
/* 392 */     return (!(c instanceof AbstractButton) || ((AbstractButton)c).isContentAreaFilled());
/*     */   }
/*     */   
/*     */   public static boolean isFocusPainted(Component c) {
/* 396 */     return (!(c instanceof AbstractButton) || ((AbstractButton)c).isFocusPainted());
/*     */   }
/*     */   
/*     */   static boolean isDefaultButton(Component c) {
/* 400 */     return (c instanceof JButton && ((JButton)c).isDefaultButton());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean isIconOnlyOrSingleCharacterButton(Component c) {
/* 408 */     if (!(c instanceof JButton) && !(c instanceof javax.swing.JToggleButton)) {
/* 409 */       return false;
/*     */     }
/* 411 */     Icon icon = ((AbstractButton)c).getIcon();
/* 412 */     String text = ((AbstractButton)c).getText();
/* 413 */     return ((icon != null && (text == null || text.isEmpty())) || (icon == null && text != null && ("..."
/*     */       
/* 415 */       .equals(text) || text
/* 416 */       .length() == 1 || (text
/* 417 */       .length() == 2 && Character.isSurrogatePair(text.charAt(0), text.charAt(1))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int getButtonType(Component c) {
/* 425 */     if (!(c instanceof AbstractButton)) {
/* 426 */       return -1;
/*     */     }
/* 428 */     String value = getButtonTypeStr((AbstractButton)c);
/* 429 */     if (value == null) {
/* 430 */       return -1;
/*     */     }
/* 432 */     switch (value) { case "square":
/* 433 */         return 0;
/* 434 */       case "roundRect": return 1; }
/* 435 */      return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   static boolean isHelpButton(Component c) {
/* 440 */     return (c instanceof JButton && "help".equals(getButtonTypeStr((JButton)c)));
/*     */   }
/*     */   
/*     */   static boolean isToolBarButton(Component c) {
/* 444 */     return (c.getParent() instanceof JToolBar || (c instanceof AbstractButton && "toolBarButton"
/* 445 */       .equals(getButtonTypeStr((AbstractButton)c))));
/*     */   }
/*     */   
/*     */   static boolean isBorderlessButton(Component c) {
/* 449 */     return (c instanceof AbstractButton && "borderless".equals(getButtonTypeStr((AbstractButton)c)));
/*     */   }
/*     */ 
/*     */   
/*     */   static String getButtonTypeStr(AbstractButton c) {
/* 454 */     Object value = c.getClientProperty("JButton.buttonType");
/* 455 */     if (value instanceof String) {
/* 456 */       return (String)value;
/*     */     }
/*     */     
/* 459 */     ButtonUI ui = c.getUI();
/* 460 */     return (ui instanceof FlatButtonUI) ? ((FlatButtonUI)ui).buttonType : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(Graphics g, JComponent c) {
/* 466 */     if (c.isOpaque()) {
/* 467 */       FlatUIUtils.paintParentBackground(g, c);
/*     */     }
/* 469 */     if (isHelpButton(c)) {
/* 470 */       this.helpButtonIcon.paintIcon(c, g, 0, 0);
/*     */       
/*     */       return;
/*     */     } 
/* 474 */     if (isContentAreaFilled(c)) {
/* 475 */       paintBackground(g, c);
/*     */     }
/* 477 */     paint(g, c);
/*     */   }
/*     */   
/*     */   protected void paintBackground(Graphics g, JComponent c) {
/* 481 */     Color background = getBackground(c);
/* 482 */     if (background == null) {
/*     */       return;
/*     */     }
/* 485 */     Graphics2D g2 = (Graphics2D)g.create();
/*     */     try {
/* 487 */       FlatUIUtils.setRenderingHints(g2);
/*     */       
/* 489 */       boolean def = isDefaultButton(c);
/* 490 */       boolean isToolBarButton = isToolBarButton(c);
/* 491 */       float focusWidth = isToolBarButton ? 0.0F : FlatUIUtils.getBorderFocusWidth(c);
/* 492 */       float arc = FlatUIUtils.getBorderArc(c);
/* 493 */       float textFieldArc = 0.0F;
/*     */ 
/*     */ 
/*     */       
/* 497 */       if (isToolBarButton && (
/* 498 */         (String)FlatClientProperties.clientProperty(c, "FlatLaf.styleClass", "", String.class)).contains("inTextField")) {
/*     */         
/* 500 */         JTextField textField = (JTextField)SwingUtilities.getAncestorOfClass(JTextField.class, c);
/* 501 */         if (textField != null) {
/* 502 */           textFieldArc = FlatUIUtils.getBorderArc(textField);
/*     */         }
/*     */       } 
/* 505 */       int x = 0;
/* 506 */       int y = 0;
/* 507 */       int width = c.getWidth();
/* 508 */       int height = c.getHeight();
/*     */       
/* 510 */       if (isToolBarButton && c.getBorder() instanceof FlatButtonBorder) {
/* 511 */         Insets spacing = UIScale.scale(((FlatButtonBorder)c.getBorder()).toolbarSpacingInsets);
/* 512 */         x += spacing.left;
/* 513 */         y += spacing.top;
/* 514 */         width -= spacing.left + spacing.right;
/* 515 */         height -= spacing.top + spacing.bottom;
/*     */ 
/*     */         
/* 518 */         textFieldArc -= (spacing.top + spacing.bottom);
/*     */       } 
/*     */ 
/*     */       
/* 522 */       if (arc < textFieldArc) {
/* 523 */         arc = textFieldArc;
/*     */       }
/*     */       
/* 526 */       Color shadowColor = def ? this.defaultShadowColor : this.shadowColor;
/* 527 */       if (this.paintShadow && shadowColor != null && this.shadowWidth > 0 && focusWidth > 0.0F && c
/* 528 */         .isEnabled() && !isToolBarButton && 
/* 529 */         !isBorderlessButton(c) && (
/* 530 */         !isFocusPainted(c) || !FlatUIUtils.isPermanentFocusOwner(c))) {
/*     */         
/* 532 */         g2.setColor(shadowColor);
/* 533 */         g2.fill(new RoundRectangle2D.Float(focusWidth, focusWidth + UIScale.scale(this.shadowWidth), width - focusWidth * 2.0F, height - focusWidth * 2.0F, arc, arc));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 538 */       Color startBg = def ? this.defaultBackground : this.startBackground;
/* 539 */       Color endBg = def ? this.defaultEndBackground : this.endBackground;
/* 540 */       if (background == startBg && endBg != null && !startBg.equals(endBg)) {
/* 541 */         g2.setPaint(new GradientPaint(0.0F, 0.0F, startBg, 0.0F, height, endBg));
/*     */       } else {
/* 543 */         g2.setColor(FlatUIUtils.deriveColor(background, getBackgroundBase(c, def)));
/*     */       } 
/* 545 */       FlatUIUtils.paintComponentBackground(g2, x, y, width, height, focusWidth, arc);
/*     */     } finally {
/* 547 */       g2.dispose();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 553 */     super.paint(FlatLabelUI.createGraphicsHTMLTextYCorrection(g, c), c);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintIcon(Graphics g, JComponent c, Rectangle iconRect) {
/* 559 */     int xOffset = defaultBoldPlainWidthDiff(c) / 2;
/* 560 */     if (xOffset > 0) {
/* 561 */       boolean ltr = c.getComponentOrientation().isLeftToRight();
/* 562 */       switch (((AbstractButton)c).getHorizontalTextPosition()) { case 4:
/* 563 */           iconRect.x -= xOffset; break;
/* 564 */         case 2: iconRect.x += xOffset; break;
/* 565 */         case 11: iconRect.x -= ltr ? xOffset : -xOffset; break;
/* 566 */         case 10: iconRect.x += ltr ? xOffset : -xOffset;
/*     */           break; }
/*     */     
/*     */     } 
/* 570 */     super.paintIcon(g, c, iconRect);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text) {
/* 575 */     if (isHelpButton(b)) {
/*     */       return;
/*     */     }
/* 578 */     if (this.defaultBoldText && isDefaultButton(b) && b.getFont() instanceof javax.swing.plaf.UIResource) {
/* 579 */       Font boldFont = g.getFont().deriveFont(1);
/* 580 */       g.setFont(boldFont);
/*     */       
/* 582 */       int boldWidth = b.getFontMetrics(boldFont).stringWidth(text);
/* 583 */       if (boldWidth > textRect.width) {
/* 584 */         textRect.x -= (boldWidth - textRect.width) / 2;
/* 585 */         textRect.width = boldWidth;
/*     */       } 
/*     */     } 
/*     */     
/* 589 */     paintText(g, b, textRect, text, getForeground(b));
/*     */   }
/*     */   
/*     */   public static void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text, Color foreground) {
/* 593 */     if (foreground == null)
/* 594 */       foreground = Color.red; 
/* 595 */     FontMetrics fm = b.getFontMetrics(b.getFont());
/* 596 */     int mnemonicIndex = FlatLaf.isShowMnemonics() ? b.getDisplayedMnemonicIndex() : -1;
/*     */     
/* 598 */     g.setColor(foreground);
/* 599 */     FlatUIUtils.drawStringUnderlineCharAt(b, g, text, mnemonicIndex, textRect.x, textRect.y + fm
/* 600 */         .getAscent());
/*     */   }
/*     */   
/*     */   protected Color getBackground(JComponent c) {
/* 604 */     boolean toolBarButton = (isToolBarButton(c) || isBorderlessButton(c));
/*     */ 
/*     */     
/* 607 */     if (((AbstractButton)c).isSelected())
/*     */     {
/*     */ 
/*     */       
/* 611 */       return buttonStateColor(c, 
/* 612 */           toolBarButton ? this.toolbarSelectedBackground : this.selectedBackground, 
/* 613 */           toolBarButton ? (
/* 614 */           (this.toolbarDisabledSelectedBackground != null) ? this.toolbarDisabledSelectedBackground : this.toolbarSelectedBackground) : 
/* 615 */           this.disabledSelectedBackground, null, null, 
/*     */ 
/*     */           
/* 618 */           toolBarButton ? this.toolbarPressedBackground : this.pressedBackground);
/*     */     }
/*     */ 
/*     */     
/* 622 */     if (toolBarButton) {
/* 623 */       Color bg = c.getBackground();
/* 624 */       return buttonStateColor(c, 
/* 625 */           isCustomBackground(bg) ? bg : null, null, null, this.toolbarHoverBackground, this.toolbarPressedBackground);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 632 */     boolean def = isDefaultButton(c);
/* 633 */     return buttonStateColor(c, 
/* 634 */         getBackgroundBase(c, def), this.disabledBackground, 
/*     */         
/* 636 */         isCustomBackground(c.getBackground()) ? null : (def ? this.defaultFocusedBackground : this.focusedBackground), 
/* 637 */         def ? this.defaultHoverBackground : this.hoverBackground, 
/* 638 */         def ? this.defaultPressedBackground : this.pressedBackground);
/*     */   }
/*     */   
/*     */   protected Color getBackgroundBase(JComponent c, boolean def) {
/* 642 */     if (FlatUIUtils.isAWTPeer(c)) {
/* 643 */       return this.background;
/*     */     }
/*     */     
/* 646 */     Color bg = c.getBackground();
/* 647 */     if (isCustomBackground(bg)) {
/* 648 */       return bg;
/*     */     }
/* 650 */     return def ? this.defaultBackground : bg;
/*     */   }
/*     */   
/*     */   protected boolean isCustomBackground(Color bg) {
/* 654 */     return (bg != this.background && (this.startBackground == null || bg != this.startBackground));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Color buttonStateColor(Component c, Color enabledColor, Color disabledColor, Color focusedColor, Color hoverColor, Color pressedColor) {
/* 660 */     if (c == null) {
/* 661 */       return enabledColor;
/*     */     }
/* 663 */     if (!c.isEnabled()) {
/* 664 */       return disabledColor;
/*     */     }
/* 666 */     if (c instanceof AbstractButton) {
/* 667 */       ButtonModel model = ((AbstractButton)c).getModel();
/*     */       
/* 669 */       if (pressedColor != null && model.isPressed()) {
/* 670 */         return pressedColor;
/*     */       }
/* 672 */       if (hoverColor != null && model.isRollover()) {
/* 673 */         return hoverColor;
/*     */       }
/*     */     } 
/* 676 */     if (focusedColor != null && isFocusPainted(c) && FlatUIUtils.isPermanentFocusOwner(c)) {
/* 677 */       return focusedColor;
/*     */     }
/* 679 */     return enabledColor;
/*     */   }
/*     */   
/*     */   protected Color getForeground(JComponent c) {
/* 683 */     boolean toolBarButton = (isToolBarButton(c) || isBorderlessButton(c));
/*     */ 
/*     */     
/* 686 */     if (((AbstractButton)c).isSelected()) {
/* 687 */       return buttonStateColor(c, 
/* 688 */           toolBarButton ? (
/* 689 */           (this.toolbarSelectedForeground != null) ? this.toolbarSelectedForeground : c.getForeground()) : 
/* 690 */           this.selectedForeground, 
/* 691 */           toolBarButton ? (
/* 692 */           (this.toolbarDisabledSelectedForeground != null) ? this.toolbarDisabledSelectedForeground : this.disabledText) : (
/* 693 */           (this.disabledSelectedForeground != null) ? this.disabledSelectedForeground : this.disabledText), null, null, 
/*     */ 
/*     */           
/* 696 */           toolBarButton ? this.toolbarPressedForeground : this.pressedForeground);
/*     */     }
/*     */ 
/*     */     
/* 700 */     if (toolBarButton) {
/* 701 */       return buttonStateColor(c, c
/* 702 */           .getForeground(), this.disabledText, null, this.toolbarHoverForeground, this.toolbarPressedForeground);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 709 */     boolean def = isDefaultButton(c);
/* 710 */     return buttonStateColor(c, 
/* 711 */         getForegroundBase(c, def), this.disabledText, 
/*     */         
/* 713 */         isCustomForeground(c.getForeground()) ? null : (def ? this.defaultFocusedForeground : this.focusedForeground), 
/* 714 */         def ? this.defaultHoverForeground : this.hoverForeground, 
/* 715 */         def ? this.defaultPressedForeground : this.pressedForeground);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Color getForegroundBase(JComponent c, boolean def) {
/* 721 */     Color fg = c.getForeground();
/* 722 */     if (isCustomForeground(fg)) {
/* 723 */       return fg;
/*     */     }
/* 725 */     return def ? this.defaultForeground : fg;
/*     */   }
/*     */   
/*     */   protected boolean isCustomForeground(Color fg) {
/* 729 */     return (fg != this.foreground);
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(JComponent c) {
/* 734 */     if (isHelpButton(c)) {
/* 735 */       return new Dimension(this.helpButtonIcon.getIconWidth(), this.helpButtonIcon.getIconHeight());
/*     */     }
/* 737 */     Dimension prefSize = super.getPreferredSize(c);
/* 738 */     if (prefSize == null) {
/* 739 */       return null;
/*     */     }
/*     */     
/* 742 */     prefSize.width += defaultBoldPlainWidthDiff(c);
/*     */ 
/*     */     
/* 745 */     boolean isIconOnlyOrSingleCharacter = isIconOnlyOrSingleCharacterButton(c);
/* 746 */     if (FlatClientProperties.clientPropertyBoolean(c, "JButton.squareSize", this.squareSize)) {
/*     */       
/* 748 */       prefSize.width = prefSize.height = Math.max(prefSize.width, prefSize.height);
/* 749 */     } else if (isIconOnlyOrSingleCharacter && ((AbstractButton)c).getIcon() == null) {
/*     */       
/* 751 */       prefSize.width = Math.max(prefSize.width, prefSize.height);
/* 752 */     } else if (!isIconOnlyOrSingleCharacter && !isToolBarButton(c) && c
/* 753 */       .getBorder() instanceof FlatButtonBorder && hasDefaultMargins(c)) {
/*     */ 
/*     */       
/* 756 */       int fw = Math.round(FlatUIUtils.getBorderFocusWidth(c) * 2.0F);
/* 757 */       prefSize.width = Math.max(prefSize.width, UIScale.scale(FlatUIUtils.minimumWidth(c, this.minimumWidth)) + fw);
/* 758 */       prefSize.height = Math.max(prefSize.height, UIScale.scale(FlatUIUtils.minimumHeight(c, this.minimumHeight)) + fw);
/*     */     } 
/*     */     
/* 761 */     return prefSize;
/*     */   }
/*     */   
/*     */   private int defaultBoldPlainWidthDiff(JComponent c) {
/* 765 */     if (this.defaultBoldText && isDefaultButton(c) && c.getFont() instanceof javax.swing.plaf.UIResource) {
/* 766 */       String text = ((AbstractButton)c).getText();
/* 767 */       if (text == null || text.isEmpty()) {
/* 768 */         return 0;
/*     */       }
/* 770 */       Font font = c.getFont();
/* 771 */       Font boldFont = font.deriveFont(1);
/* 772 */       int boldWidth = c.getFontMetrics(boldFont).stringWidth(text);
/* 773 */       int plainWidth = c.getFontMetrics(font).stringWidth(text);
/* 774 */       if (boldWidth > plainWidth) {
/* 775 */         return boldWidth - plainWidth;
/*     */       }
/*     */     } 
/* 778 */     return 0;
/*     */   }
/*     */   
/*     */   private boolean hasDefaultMargins(JComponent c) {
/* 782 */     Insets margin = ((AbstractButton)c).getMargin();
/* 783 */     return (margin instanceof javax.swing.plaf.UIResource && Objects.equals(margin, this.defaultMargin));
/*     */   }
/*     */ 
/*     */   
/*     */   protected class FlatButtonListener
/*     */     extends BasicButtonListener
/*     */   {
/*     */     private final AbstractButton b;
/*     */ 
/*     */     
/*     */     protected FlatButtonListener(AbstractButton b) {
/* 794 */       super(b);
/* 795 */       this.b = b;
/*     */     }
/*     */ 
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent e) {
/* 800 */       super.propertyChange(e);
/* 801 */       FlatButtonUI.this.propertyChange(this.b, e);
/*     */     }
/*     */ 
/*     */     
/*     */     public void stateChanged(ChangeEvent e) {
/* 806 */       super.stateChanged(e);
/*     */ 
/*     */       
/* 809 */       AbstractButton b = (AbstractButton)e.getSource();
/* 810 */       Container parent = b.getParent();
/* 811 */       if (parent instanceof JToolBar) {
/* 812 */         JToolBar toolBar = (JToolBar)parent;
/* 813 */         ToolBarUI ui = toolBar.getUI();
/* 814 */         if (ui instanceof FlatToolBarUI)
/* 815 */           ((FlatToolBarUI)ui).repaintButtonGroup(b); 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatButtonUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */