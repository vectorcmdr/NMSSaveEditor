/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.FlatClientProperties;
/*     */ import com.formdev.flatlaf.util.HiDPIUtils;
/*     */ import com.formdev.flatlaf.util.JavaCompatibility;
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.LayoutManager2;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.function.Consumer;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.event.DocumentEvent;
/*     */ import javax.swing.event.DocumentListener;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.UIResource;
/*     */ import javax.swing.plaf.basic.BasicTextFieldUI;
/*     */ import javax.swing.text.Caret;
/*     */ import javax.swing.text.Document;
/*     */ import javax.swing.text.JTextComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatTextFieldUI
/*     */   extends BasicTextFieldUI
/*     */   implements FlatStylingSupport.StyleableUI
/*     */ {
/*     */   @Styleable
/*     */   protected int minimumWidth;
/*     */   protected boolean isIntelliJTheme;
/*     */   private Color background;
/*     */   @Styleable
/*     */   protected Color disabledBackground;
/*     */   @Styleable
/*     */   protected Color inactiveBackground;
/*     */   @Styleable
/*     */   protected Color placeholderForeground;
/*     */   @Styleable
/*     */   protected Color focusedBackground;
/*     */   @Styleable
/*     */   protected int iconTextGap;
/*     */   @Styleable
/*     */   protected Icon leadingIcon;
/*     */   @Styleable
/*     */   protected Icon trailingIcon;
/*     */   protected JComponent leadingComponent;
/*     */   protected JComponent trailingComponent;
/*     */   protected JComponent clearButton;
/*     */   @Styleable
/*     */   protected boolean showClearButton;
/*     */   private Color oldDisabledBackground;
/*     */   private Color oldInactiveBackground;
/*     */   private Insets defaultMargin;
/*     */   private FocusListener focusListener;
/*     */   private DocumentListener documentListener;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   private AtomicBoolean borderShared;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/* 126 */     return new FlatTextFieldUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/* 131 */     if (FlatUIUtils.needsLightAWTPeer(c)) {
/* 132 */       FlatUIUtils.runWithLightAWTPeerUIDefaults(() -> installUIImpl(c));
/*     */     } else {
/* 134 */       installUIImpl(c);
/*     */     } 
/*     */   }
/*     */   private void installUIImpl(JComponent c) {
/* 138 */     super.installUI(c);
/*     */     
/* 140 */     this.leadingIcon = (Icon)FlatClientProperties.clientProperty(c, "JTextField.leadingIcon", null, Icon.class);
/* 141 */     this.trailingIcon = (Icon)FlatClientProperties.clientProperty(c, "JTextField.trailingIcon", null, Icon.class);
/*     */     
/* 143 */     installLeadingComponent();
/* 144 */     installTrailingComponent();
/* 145 */     installClearButton();
/*     */     
/* 147 */     installStyle();
/*     */   }
/*     */ 
/*     */   
/*     */   public void uninstallUI(JComponent c) {
/* 152 */     uninstallLeadingComponent();
/* 153 */     uninstallTrailingComponent();
/* 154 */     uninstallClearButton();
/*     */     
/* 156 */     super.uninstallUI(c);
/*     */     
/* 158 */     this.leadingIcon = null;
/* 159 */     this.trailingIcon = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/* 164 */     super.installDefaults();
/*     */     
/* 166 */     String prefix = getPropertyPrefix();
/* 167 */     this.minimumWidth = UIManager.getInt("Component.minimumWidth");
/* 168 */     this.isIntelliJTheme = UIManager.getBoolean("Component.isIntelliJTheme");
/* 169 */     this.background = UIManager.getColor(prefix + ".background");
/* 170 */     this.disabledBackground = UIManager.getColor(prefix + ".disabledBackground");
/* 171 */     this.inactiveBackground = UIManager.getColor(prefix + ".inactiveBackground");
/* 172 */     this.placeholderForeground = UIManager.getColor(prefix + ".placeholderForeground");
/* 173 */     this.focusedBackground = UIManager.getColor(prefix + ".focusedBackground");
/* 174 */     this.iconTextGap = FlatUIUtils.getUIInt(prefix + ".iconTextGap", 4);
/*     */     
/* 176 */     this.defaultMargin = UIManager.getInsets(prefix + ".margin");
/*     */     
/* 178 */     LookAndFeel.installProperty(getComponent(), "opaque", Boolean.valueOf(false));
/*     */     
/* 180 */     MigLayoutVisualPadding.install(getComponent());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults() {
/* 185 */     super.uninstallDefaults();
/*     */     
/* 187 */     this.background = null;
/* 188 */     this.disabledBackground = null;
/* 189 */     this.inactiveBackground = null;
/* 190 */     this.placeholderForeground = null;
/* 191 */     this.focusedBackground = null;
/*     */     
/* 193 */     this.oldDisabledBackground = null;
/* 194 */     this.oldInactiveBackground = null;
/*     */     
/* 196 */     this.oldStyleValues = null;
/* 197 */     this.borderShared = null;
/*     */     
/* 199 */     MigLayoutVisualPadding.uninstall(getComponent());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners() {
/* 204 */     super.installListeners();
/*     */ 
/*     */     
/* 207 */     this.focusListener = new FlatUIUtils.RepaintFocusListener(getComponent(), null);
/* 208 */     getComponent().addFocusListener(this.focusListener);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners() {
/* 213 */     super.uninstallListeners();
/*     */     
/* 215 */     getComponent().removeFocusListener(this.focusListener);
/* 216 */     this.focusListener = null;
/*     */     
/* 218 */     if (this.documentListener != null) {
/* 219 */       getComponent().getDocument().removeDocumentListener(this.documentListener);
/* 220 */       this.documentListener = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Caret createCaret() {
/* 226 */     return new FlatCaret(UIManager.getString("TextComponent.selectAllOnFocusPolicy"), 
/* 227 */         UIManager.getBoolean("TextComponent.selectAllOnMouseClick"));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void propertyChange(PropertyChangeEvent e) {
/* 232 */     String propertyName = e.getPropertyName();
/* 233 */     if ("editable".equals(propertyName) || "enabled".equals(propertyName)) {
/* 234 */       updateBackground();
/*     */     } else {
/* 236 */       super.propertyChange(e);
/*     */     } 
/* 238 */     JTextComponent c = getComponent();
/* 239 */     switch (e.getPropertyName()) {
/*     */       case "JTextField.placeholderText":
/*     */       case "JComponent.roundRect":
/*     */       case "JComponent.outline":
/*     */       case "JTextField.padding":
/* 244 */         c.repaint();
/*     */         break;
/*     */       
/*     */       case "JComponent.minimumWidth":
/* 248 */         c.revalidate();
/*     */         break;
/*     */       
/*     */       case "FlatLaf.style":
/*     */       case "FlatLaf.styleClass":
/* 253 */         installStyle();
/* 254 */         c.revalidate();
/* 255 */         c.repaint();
/*     */         break;
/*     */       
/*     */       case "JTextField.leadingIcon":
/* 259 */         this.leadingIcon = (e.getNewValue() instanceof Icon) ? (Icon)e.getNewValue() : null;
/* 260 */         c.repaint();
/*     */         break;
/*     */       
/*     */       case "JTextField.trailingIcon":
/* 264 */         this.trailingIcon = (e.getNewValue() instanceof Icon) ? (Icon)e.getNewValue() : null;
/* 265 */         c.repaint();
/*     */         break;
/*     */       
/*     */       case "JTextField.leadingComponent":
/* 269 */         uninstallLeadingComponent();
/* 270 */         installLeadingComponent();
/* 271 */         c.revalidate();
/* 272 */         c.repaint();
/*     */         break;
/*     */       
/*     */       case "JTextField.trailingComponent":
/* 276 */         uninstallTrailingComponent();
/* 277 */         installTrailingComponent();
/* 278 */         c.revalidate();
/* 279 */         c.repaint();
/*     */         break;
/*     */       
/*     */       case "JTextField.showClearButton":
/* 283 */         uninstallClearButton();
/* 284 */         installClearButton();
/* 285 */         c.revalidate();
/* 286 */         c.repaint();
/*     */         break;
/*     */       
/*     */       case "enabled":
/*     */       case "editable":
/* 291 */         updateClearButton();
/*     */         break;
/*     */       
/*     */       case "document":
/* 295 */         if (this.documentListener != null) {
/* 296 */           if (e.getOldValue() instanceof Document)
/* 297 */             ((Document)e.getOldValue()).removeDocumentListener(this.documentListener); 
/* 298 */           if (e.getNewValue() instanceof Document) {
/* 299 */             ((Document)e.getNewValue()).addDocumentListener(this.documentListener);
/*     */           }
/* 301 */           updateClearButton();
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDocumentListener() {
/* 309 */     if (this.documentListener != null) {
/*     */       return;
/*     */     }
/* 312 */     this.documentListener = new FlatDocumentListener();
/* 313 */     getComponent().getDocument().addDocumentListener(this.documentListener);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void documentChanged(DocumentEvent e) {
/* 318 */     if (this.clearButton != null) {
/* 319 */       updateClearButton();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void installStyle() {
/*     */     try {
/* 325 */       applyStyle(FlatStylingSupport.getResolvedStyle(getComponent(), getStyleType()));
/* 326 */     } catch (RuntimeException ex) {
/* 327 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   String getStyleType() {
/* 333 */     return "TextField";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 338 */     this.oldDisabledBackground = this.disabledBackground;
/* 339 */     this.oldInactiveBackground = this.inactiveBackground;
/* 340 */     boolean oldShowClearButton = this.showClearButton;
/*     */     
/* 342 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*     */     
/* 344 */     updateBackground();
/* 345 */     if (this.showClearButton != oldShowClearButton) {
/* 346 */       uninstallClearButton();
/* 347 */       installClearButton();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 353 */     if (this.borderShared == null)
/* 354 */       this.borderShared = new AtomicBoolean(true); 
/* 355 */     return FlatStylingSupport.applyToAnnotatedObjectOrBorder(this, key, value, getComponent(), this.borderShared);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 361 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this, getComponent().getBorder());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 367 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, getComponent().getBorder(), key);
/*     */   }
/*     */   
/*     */   private void updateBackground() {
/* 371 */     updateBackground(getComponent(), this.background, this.disabledBackground, this.inactiveBackground, this.oldDisabledBackground, this.oldInactiveBackground);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void updateBackground(JTextComponent c, Color background, Color disabledBackground, Color inactiveBackground, Color oldDisabledBackground, Color oldInactiveBackground) {
/* 381 */     Color oldBackground = c.getBackground();
/* 382 */     if (!(oldBackground instanceof UIResource)) {
/*     */       return;
/*     */     }
/*     */     
/* 386 */     if (oldBackground != background && oldBackground != disabledBackground && oldBackground != inactiveBackground && oldBackground != oldDisabledBackground && oldBackground != oldInactiveBackground) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 397 */     Color newBackground = !c.isEnabled() ? disabledBackground : (!c.isEditable() ? inactiveBackground : background);
/*     */     
/* 399 */     if (newBackground != oldBackground) {
/* 400 */       c.setBackground(newBackground);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void paintSafely(Graphics g) {
/* 405 */     paintBackground(g, getComponent(), this.isIntelliJTheme, this.focusedBackground);
/* 406 */     paintPlaceholder(g);
/*     */     
/* 408 */     if (hasLeadingIcon() || hasTrailingIcon()) {
/* 409 */       paintIcons(g, new Rectangle(getIconsRect()));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 417 */     super.paintSafely(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)g));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintBackground(Graphics g) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void paintBackground(Graphics g, JTextComponent c, boolean isIntelliJTheme, Color focusedBackground) {
/* 431 */     if (!c.isOpaque() && FlatUIUtils.getOutsideFlatBorder(c) == null && FlatUIUtils.hasOpaqueBeenExplicitlySet(c)) {
/*     */       return;
/*     */     }
/* 434 */     float focusWidth = FlatUIUtils.getBorderFocusWidth(c);
/* 435 */     float arc = FlatUIUtils.getBorderArc(c);
/*     */ 
/*     */     
/* 438 */     if (c.isOpaque() && (focusWidth > 0.0F || arc > 0.0F)) {
/* 439 */       FlatUIUtils.paintParentBackground(g, c);
/*     */     }
/*     */     
/* 442 */     Graphics2D g2 = (Graphics2D)g.create();
/*     */     try {
/* 444 */       FlatUIUtils.setRenderingHints(g2);
/*     */       
/* 446 */       g2.setColor(getBackground(c, isIntelliJTheme, focusedBackground));
/* 447 */       FlatUIUtils.paintComponentBackground(g2, 0, 0, c.getWidth(), c.getHeight(), focusWidth, arc);
/*     */     } finally {
/* 449 */       g2.dispose();
/*     */     } 
/*     */   }
/*     */   
/*     */   static Color getBackground(JTextComponent c, boolean isIntelliJTheme, Color focusedBackground) {
/* 454 */     Color background = c.getBackground();
/*     */ 
/*     */     
/* 457 */     if (!(background instanceof UIResource)) {
/* 458 */       return background;
/*     */     }
/*     */     
/* 461 */     if (focusedBackground != null && FlatUIUtils.isPermanentFocusOwner(c)) {
/* 462 */       return focusedBackground;
/*     */     }
/*     */     
/* 465 */     if (isIntelliJTheme && (!c.isEnabled() || !c.isEditable())) {
/* 466 */       return FlatUIUtils.getParentBackground(c);
/*     */     }
/* 468 */     return background;
/*     */   }
/*     */   
/*     */   protected void paintPlaceholder(Graphics g) {
/* 472 */     JTextComponent c = getComponent();
/*     */ 
/*     */     
/* 475 */     if (c.getDocument().getLength() > 0) {
/*     */       return;
/*     */     }
/*     */     
/* 479 */     Container parent = c.getParent();
/* 480 */     JComponent jc = (parent instanceof JComboBox) ? (JComboBox)parent : c;
/*     */ 
/*     */     
/* 483 */     String placeholder = (String)FlatClientProperties.clientProperty(jc, "JTextField.placeholderText", null, String.class);
/* 484 */     if (placeholder == null) {
/*     */       return;
/*     */     }
/*     */     
/* 488 */     Rectangle r = getVisibleEditorRect();
/* 489 */     FontMetrics fm = c.getFontMetrics(c.getFont());
/* 490 */     String clippedPlaceholder = JavaCompatibility.getClippedString(c, fm, placeholder, r.width);
/* 491 */     int x = r.x + (isLeftToRight() ? 0 : (r.width - fm.stringWidth(clippedPlaceholder)));
/* 492 */     int y = r.y + fm.getAscent() + (r.height - fm.getHeight()) / 2;
/*     */ 
/*     */     
/* 495 */     g.setColor(this.placeholderForeground);
/* 496 */     FlatUIUtils.drawString(c, g, clippedPlaceholder, x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintIcons(Graphics g, Rectangle r) {
/* 507 */     boolean ltr = isLeftToRight();
/* 508 */     Icon leftIcon = ltr ? this.leadingIcon : this.trailingIcon;
/* 509 */     Icon rightIcon = ltr ? this.trailingIcon : this.leadingIcon;
/*     */ 
/*     */     
/* 512 */     if (leftIcon != null) {
/* 513 */       int x = r.x;
/* 514 */       int y = r.y + Math.round((r.height - leftIcon.getIconHeight()) / 2.0F);
/* 515 */       leftIcon.paintIcon(getComponent(), g, x, y);
/*     */ 
/*     */       
/* 518 */       int w = leftIcon.getIconWidth() + UIScale.scale(this.iconTextGap);
/* 519 */       r.x += w;
/* 520 */       r.width -= w;
/*     */     } 
/*     */ 
/*     */     
/* 524 */     if (rightIcon != null) {
/* 525 */       int iconWidth = rightIcon.getIconWidth();
/* 526 */       int x = r.x + r.width - iconWidth;
/* 527 */       int y = r.y + Math.round((r.height - rightIcon.getIconHeight()) / 2.0F);
/* 528 */       rightIcon.paintIcon(getComponent(), g, x, y);
/*     */ 
/*     */       
/* 531 */       r.width -= iconWidth + UIScale.scale(this.iconTextGap);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(JComponent c) {
/* 537 */     return applyMinimumWidth(c, applyExtraSize(super.getPreferredSize(c)), this.minimumWidth);
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize(JComponent c) {
/* 542 */     return applyMinimumWidth(c, applyExtraSize(super.getMinimumSize(c)), this.minimumWidth);
/*     */   }
/*     */ 
/*     */   
/*     */   private Dimension applyExtraSize(Dimension size) {
/* 547 */     size.width += getLeadingIconWidth() + getTrailingIconWidth();
/*     */ 
/*     */     
/* 550 */     for (JComponent comp : getLeadingComponents()) {
/* 551 */       if (comp != null && comp.isVisible())
/* 552 */         size.width += (comp.getPreferredSize()).width; 
/*     */     } 
/* 554 */     for (JComponent comp : getTrailingComponents()) {
/* 555 */       if (comp != null && comp.isVisible()) {
/* 556 */         size.width += (comp.getPreferredSize()).width;
/*     */       }
/*     */     } 
/* 559 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   private Dimension applyMinimumWidth(JComponent c, Dimension size, int minimumWidth) {
/* 564 */     if (c instanceof JTextField && ((JTextField)c).getColumns() > 0) {
/* 565 */       return size;
/*     */     }
/*     */     
/* 568 */     if (!hasDefaultMargins(c, this.defaultMargin)) {
/* 569 */       return size;
/*     */     }
/*     */     
/* 572 */     Container parent = c.getParent();
/* 573 */     if (parent instanceof JComboBox || parent instanceof javax.swing.JSpinner || (parent != null && parent
/*     */       
/* 575 */       .getParent() instanceof javax.swing.JSpinner)) {
/* 576 */       return size;
/*     */     }
/* 578 */     minimumWidth = FlatUIUtils.minimumWidth(c, minimumWidth);
/* 579 */     float focusWidth = FlatUIUtils.getBorderFocusWidth(c);
/* 580 */     size.width = Math.max(size.width, UIScale.scale(minimumWidth) + Math.round(focusWidth * 2.0F));
/* 581 */     return size;
/*     */   }
/*     */   
/*     */   static boolean hasDefaultMargins(JComponent c, Insets defaultMargin) {
/* 585 */     Insets margin = ((JTextComponent)c).getMargin();
/* 586 */     return (margin instanceof UIResource && Objects.equals(margin, defaultMargin));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Rectangle getVisibleEditorRect() {
/* 595 */     Rectangle r = getIconsRect();
/* 596 */     if (r == null) {
/* 597 */       return null;
/*     */     }
/*     */     
/* 600 */     int leading = getLeadingIconWidth();
/* 601 */     int trailing = getTrailingIconWidth();
/* 602 */     if (leading != 0 || trailing != 0) {
/* 603 */       boolean ltr = isLeftToRight();
/* 604 */       int left = ltr ? leading : trailing;
/* 605 */       int right = ltr ? trailing : leading;
/* 606 */       r.x += left;
/* 607 */       r.width -= left + right;
/*     */     } 
/*     */ 
/*     */     
/* 611 */     Insets padding = getPadding();
/* 612 */     if (padding != null) {
/* 613 */       r = FlatUIUtils.subtractInsets(r, padding);
/*     */     }
/*     */     
/* 616 */     r.width = Math.max(r.width, 0);
/* 617 */     r.height = Math.max(r.height, 0);
/*     */     
/* 619 */     return r;
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
/*     */   protected Rectangle getIconsRect() {
/* 631 */     Rectangle r = super.getVisibleEditorRect();
/* 632 */     if (r == null) {
/* 633 */       return null;
/*     */     }
/* 635 */     boolean ltr = isLeftToRight();
/*     */ 
/*     */     
/* 638 */     JComponent[] leftComponents = ltr ? getLeadingComponents() : getTrailingComponents();
/* 639 */     JComponent[] rightComponents = ltr ? getTrailingComponents() : getLeadingComponents();
/* 640 */     boolean leftVisible = false;
/* 641 */     boolean rightVisible = false;
/* 642 */     for (JComponent leftComponent : leftComponents) {
/* 643 */       if (leftComponent != null && leftComponent.isVisible()) {
/* 644 */         int w = (leftComponent.getPreferredSize()).width;
/* 645 */         r.x += w;
/* 646 */         r.width -= w;
/* 647 */         leftVisible = true;
/*     */       } 
/*     */     } 
/* 650 */     for (JComponent rightComponent : rightComponents) {
/* 651 */       if (rightComponent != null && rightComponent.isVisible()) {
/* 652 */         r.width -= (rightComponent.getPreferredSize()).width;
/* 653 */         rightVisible = true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 659 */     if (leftVisible || (ltr ? hasLeadingIcon() : hasTrailingIcon())) {
/*     */       
/* 661 */       Insets margin = getComponent().getMargin();
/* 662 */       int newLeftMargin = Math.min(margin.left, margin.top);
/* 663 */       if (newLeftMargin < margin.left) {
/* 664 */         int diff = UIScale.scale(margin.left - newLeftMargin);
/* 665 */         r.x -= diff;
/* 666 */         r.width += diff;
/*     */       } 
/*     */     } 
/* 669 */     if (rightVisible || (ltr ? hasTrailingIcon() : hasLeadingIcon())) {
/*     */       
/* 671 */       Insets margin = getComponent().getMargin();
/* 672 */       int newRightMargin = Math.min(margin.right, margin.top);
/* 673 */       if (newRightMargin < margin.left) {
/* 674 */         r.width += UIScale.scale(margin.right - newRightMargin);
/*     */       }
/*     */     } 
/*     */     
/* 678 */     r.width = Math.max(r.width, 0);
/* 679 */     r.height = Math.max(r.height, 0);
/*     */     
/* 681 */     return r;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean hasLeadingIcon() {
/* 686 */     return (this.leadingIcon != null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean hasTrailingIcon() {
/* 691 */     return (this.trailingIcon != null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getLeadingIconWidth() {
/* 696 */     return (this.leadingIcon != null) ? (this.leadingIcon.getIconWidth() + UIScale.scale(this.iconTextGap)) : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getTrailingIconWidth() {
/* 701 */     return (this.trailingIcon != null) ? (this.trailingIcon.getIconWidth() + UIScale.scale(this.iconTextGap)) : 0;
/*     */   }
/*     */   
/*     */   boolean isLeftToRight() {
/* 705 */     return getComponent().getComponentOrientation().isLeftToRight();
/*     */   }
/*     */ 
/*     */   
/*     */   protected Insets getPadding() {
/* 710 */     return UIScale.scale((Insets)FlatClientProperties.clientProperty(getComponent(), "JTextField.padding", null, Insets.class));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void scrollCaretToVisible() {
/* 715 */     Caret caret = getComponent().getCaret();
/* 716 */     if (caret instanceof FlatCaret) {
/* 717 */       ((FlatCaret)caret).scrollCaretToVisible();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void installLeadingComponent() {
/* 722 */     JTextComponent c = getComponent();
/* 723 */     this.leadingComponent = (JComponent)FlatClientProperties.clientProperty(c, "JTextField.leadingComponent", null, JComponent.class);
/* 724 */     if (this.leadingComponent != null) {
/* 725 */       prepareLeadingOrTrailingComponent(this.leadingComponent);
/* 726 */       installLayout();
/* 727 */       c.add(this.leadingComponent);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installTrailingComponent() {
/* 733 */     JTextComponent c = getComponent();
/* 734 */     this.trailingComponent = (JComponent)FlatClientProperties.clientProperty(c, "JTextField.trailingComponent", null, JComponent.class);
/* 735 */     if (this.trailingComponent != null) {
/* 736 */       prepareLeadingOrTrailingComponent(this.trailingComponent);
/* 737 */       installLayout();
/* 738 */       c.add(this.trailingComponent);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallLeadingComponent() {
/* 744 */     if (this.leadingComponent != null) {
/* 745 */       getComponent().remove(this.leadingComponent);
/* 746 */       this.leadingComponent = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallTrailingComponent() {
/* 752 */     if (this.trailingComponent != null) {
/* 753 */       getComponent().remove(this.trailingComponent);
/* 754 */       this.trailingComponent = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installClearButton() {
/* 760 */     JTextComponent c = getComponent();
/* 761 */     if (FlatClientProperties.clientPropertyBoolean(c, "JTextField.showClearButton", this.showClearButton)) {
/* 762 */       this.clearButton = createClearButton();
/* 763 */       updateClearButton();
/* 764 */       installDocumentListener();
/* 765 */       installLayout();
/* 766 */       c.add(this.clearButton);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallClearButton() {
/* 772 */     if (this.clearButton != null) {
/* 773 */       getComponent().remove(this.clearButton);
/* 774 */       this.clearButton = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected JComponent createClearButton() {
/* 780 */     JButton button = new JButton();
/* 781 */     button.setName("TextField.clearButton");
/* 782 */     button.putClientProperty("FlatLaf.styleClass", "clearButton");
/* 783 */     button.putClientProperty("JButton.buttonType", "toolBarButton");
/* 784 */     button.setCursor(Cursor.getDefaultCursor());
/* 785 */     button.addActionListener(e -> clearButtonClicked());
/* 786 */     return button;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void clearButtonClicked() {
/* 792 */     JTextComponent c = getComponent();
/* 793 */     Object callback = c.getClientProperty("JTextField.clearCallback");
/* 794 */     if (callback instanceof Runnable) {
/* 795 */       ((Runnable)callback).run();
/* 796 */     } else if (callback instanceof Consumer) {
/* 797 */       ((Consumer<JTextComponent>)callback).accept(c);
/*     */     } else {
/* 799 */       c.setText("");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void updateClearButton() {
/* 804 */     if (this.clearButton == null) {
/*     */       return;
/*     */     }
/* 807 */     JTextComponent c = getComponent();
/* 808 */     boolean visible = (c.isEnabled() && c.isEditable() && c.getDocument().getLength() > 0);
/* 809 */     if (visible != this.clearButton.isVisible()) {
/* 810 */       this.clearButton.setVisible(visible);
/* 811 */       c.revalidate();
/* 812 */       c.repaint();
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
/*     */   protected JComponent[] getLeadingComponents() {
/* 824 */     return new JComponent[] { this.leadingComponent };
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
/*     */   protected JComponent[] getTrailingComponents() {
/* 837 */     return new JComponent[] { this.trailingComponent, this.clearButton };
/*     */   }
/*     */ 
/*     */   
/*     */   protected void prepareLeadingOrTrailingComponent(JComponent c) {
/* 842 */     c.putClientProperty("FlatLaf.styleClass", "inTextField");
/*     */     
/* 844 */     if (c instanceof JButton || c instanceof javax.swing.JToggleButton) {
/* 845 */       c.putClientProperty("JButton.buttonType", "toolBarButton");
/*     */       
/* 847 */       if (!c.isCursorSet())
/* 848 */         c.setCursor(Cursor.getDefaultCursor()); 
/* 849 */     } else if (c instanceof javax.swing.JToolBar) {
/* 850 */       for (Component child : c.getComponents()) {
/* 851 */         if (child instanceof JComponent) {
/* 852 */           ((JComponent)child).putClientProperty("FlatLaf.styleClass", "inTextField");
/*     */         }
/*     */       } 
/* 855 */       if (!c.isCursorSet()) {
/* 856 */         c.setCursor(Cursor.getDefaultCursor());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void installLayout() {
/* 862 */     JTextComponent c = getComponent();
/* 863 */     LayoutManager oldLayout = c.getLayout();
/* 864 */     if (!(oldLayout instanceof FlatTextFieldLayout)) {
/* 865 */       c.setLayout(new FlatTextFieldLayout(oldLayout));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private class FlatTextFieldLayout
/*     */     implements LayoutManager2, UIResource
/*     */   {
/*     */     private final LayoutManager delegate;
/*     */     
/*     */     FlatTextFieldLayout(LayoutManager delegate) {
/* 876 */       this.delegate = delegate;
/*     */     }
/*     */ 
/*     */     
/*     */     public void addLayoutComponent(String name, Component comp) {
/* 881 */       if (this.delegate != null) {
/* 882 */         this.delegate.addLayoutComponent(name, comp);
/*     */       }
/*     */     }
/*     */     
/*     */     public void removeLayoutComponent(Component comp) {
/* 887 */       if (this.delegate != null) {
/* 888 */         this.delegate.removeLayoutComponent(comp);
/*     */       }
/*     */     }
/*     */     
/*     */     public Dimension preferredLayoutSize(Container parent) {
/* 893 */       return (this.delegate != null) ? this.delegate.preferredLayoutSize(parent) : null;
/*     */     }
/*     */ 
/*     */     
/*     */     public Dimension minimumLayoutSize(Container parent) {
/* 898 */       return (this.delegate != null) ? this.delegate.minimumLayoutSize(parent) : null;
/*     */     }
/*     */ 
/*     */     
/*     */     public void layoutContainer(Container parent) {
/* 903 */       if (this.delegate != null) {
/* 904 */         this.delegate.layoutContainer(parent);
/*     */       }
/* 906 */       int ow = FlatUIUtils.getBorderFocusAndLineWidth(FlatTextFieldUI.this.getComponent());
/* 907 */       int h = parent.getHeight() - ow - ow;
/* 908 */       boolean ltr = FlatTextFieldUI.this.isLeftToRight();
/* 909 */       JComponent[] leftComponents = ltr ? FlatTextFieldUI.this.getLeadingComponents() : FlatTextFieldUI.this.getTrailingComponents();
/* 910 */       JComponent[] rightComponents = ltr ? FlatTextFieldUI.this.getTrailingComponents() : FlatTextFieldUI.this.getLeadingComponents();
/*     */ 
/*     */       
/* 913 */       int x = ow;
/* 914 */       for (JComponent leftComponent : leftComponents) {
/* 915 */         if (leftComponent != null && leftComponent.isVisible()) {
/* 916 */           int cw = (leftComponent.getPreferredSize()).width;
/* 917 */           leftComponent.setBounds(x, ow, cw, h);
/* 918 */           x += cw;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 923 */       x = parent.getWidth() - ow;
/* 924 */       for (JComponent rightComponent : rightComponents) {
/* 925 */         if (rightComponent != null && rightComponent.isVisible()) {
/* 926 */           int cw = (rightComponent.getPreferredSize()).width;
/* 927 */           x -= cw;
/* 928 */           rightComponent.setBounds(x, ow, cw, h);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void addLayoutComponent(Component comp, Object constraints) {
/* 935 */       if (this.delegate instanceof LayoutManager2) {
/* 936 */         ((LayoutManager2)this.delegate).addLayoutComponent(comp, constraints);
/*     */       }
/*     */     }
/*     */     
/*     */     public Dimension maximumLayoutSize(Container target) {
/* 941 */       return (this.delegate instanceof LayoutManager2) ? ((LayoutManager2)this.delegate).maximumLayoutSize(target) : null;
/*     */     }
/*     */ 
/*     */     
/*     */     public float getLayoutAlignmentX(Container target) {
/* 946 */       return (this.delegate instanceof LayoutManager2) ? ((LayoutManager2)this.delegate).getLayoutAlignmentX(target) : 0.5F;
/*     */     }
/*     */ 
/*     */     
/*     */     public float getLayoutAlignmentY(Container target) {
/* 951 */       return (this.delegate instanceof LayoutManager2) ? ((LayoutManager2)this.delegate).getLayoutAlignmentY(target) : 0.5F;
/*     */     }
/*     */ 
/*     */     
/*     */     public void invalidateLayout(Container target) {
/* 956 */       if (this.delegate instanceof LayoutManager2) {
/* 957 */         ((LayoutManager2)this.delegate).invalidateLayout(target);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private class FlatDocumentListener
/*     */     implements DocumentListener
/*     */   {
/*     */     private FlatDocumentListener() {}
/*     */     
/*     */     public void insertUpdate(DocumentEvent e) {
/* 968 */       FlatTextFieldUI.this.documentChanged(e);
/*     */     }
/*     */ 
/*     */     
/*     */     public void removeUpdate(DocumentEvent e) {
/* 973 */       FlatTextFieldUI.this.documentChanged(e);
/*     */     }
/*     */ 
/*     */     
/*     */     public void changedUpdate(DocumentEvent e) {
/* 978 */       FlatTextFieldUI.this.documentChanged(e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatTextFieldUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */