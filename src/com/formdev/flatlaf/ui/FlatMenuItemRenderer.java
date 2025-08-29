/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.FlatClientProperties;
/*     */ import com.formdev.flatlaf.FlatLaf;
/*     */ import com.formdev.flatlaf.icons.FlatCheckBoxMenuItemIcon;
/*     */ import com.formdev.flatlaf.icons.FlatMenuArrowIcon;
/*     */ import com.formdev.flatlaf.util.Graphics2DProxy;
/*     */ import com.formdev.flatlaf.util.HiDPIUtils;
/*     */ import com.formdev.flatlaf.util.SystemInfo;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.InputEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.util.Map;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.text.View;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatMenuItemRenderer
/*     */ {
/*     */   private static final String KEY_MAX_ICONS_WIDTH = "FlatLaf.internal.FlatMenuItemRenderer.maxIconWidth";
/*     */   protected final JMenuItem menuItem;
/*     */   protected Icon checkIcon;
/*     */   protected Icon arrowIcon;
/*     */   @Styleable
/*     */   protected Font acceleratorFont;
/*     */   protected final String acceleratorDelimiter;
/*     */   @Styleable
/*  86 */   protected boolean verticallyAlignText = FlatUIUtils.getUIBoolean("MenuItem.verticallyAlignText", true); @Styleable
/*  87 */   protected int minimumWidth = UIManager.getInt("MenuItem.minimumWidth"); @Styleable
/*     */   protected Dimension minimumIconSize; @Styleable
/*  89 */   protected int textAcceleratorGap = FlatUIUtils.getUIInt("MenuItem.textAcceleratorGap", 28); @Styleable
/*  90 */   protected int textNoAcceleratorGap = FlatUIUtils.getUIInt("MenuItem.textNoAcceleratorGap", 6); @Styleable
/*  91 */   protected int acceleratorArrowGap = FlatUIUtils.getUIInt("MenuItem.acceleratorArrowGap", 2);
/*     */   @Styleable
/*  93 */   protected Color checkBackground = UIManager.getColor("MenuItem.checkBackground"); @Styleable
/*  94 */   protected Insets checkMargins = UIManager.getInsets("MenuItem.checkMargins");
/*     */   @Styleable
/*  96 */   protected Insets selectionInsets = UIManager.getInsets("MenuItem.selectionInsets"); @Styleable
/*  97 */   protected int selectionArc = UIManager.getInt("MenuItem.selectionArc");
/*     */   @Styleable
/*  99 */   protected Color underlineSelectionBackground = UIManager.getColor("MenuItem.underlineSelectionBackground"); @Styleable
/* 100 */   protected Color underlineSelectionCheckBackground = UIManager.getColor("MenuItem.underlineSelectionCheckBackground"); @Styleable
/* 101 */   protected Color underlineSelectionColor = UIManager.getColor("MenuItem.underlineSelectionColor"); @Styleable
/* 102 */   protected int underlineSelectionHeight = UIManager.getInt("MenuItem.underlineSelectionHeight");
/*     */   
/*     */   private boolean iconsShared = true;
/* 105 */   private final Font menuFont = UIManager.getFont("Menu.font"); private KeyStroke cachedAccelerator;
/*     */   private String cachedAcceleratorText;
/*     */   private boolean cachedAcceleratorLeftToRight;
/*     */   
/*     */   protected FlatMenuItemRenderer(JMenuItem menuItem, Icon checkIcon, Icon arrowIcon, Font acceleratorFont, String acceleratorDelimiter) {
/* 110 */     this.menuItem = menuItem;
/* 111 */     this.checkIcon = checkIcon;
/* 112 */     this.arrowIcon = arrowIcon;
/* 113 */     this.acceleratorFont = acceleratorFont;
/* 114 */     this.acceleratorDelimiter = acceleratorDelimiter;
/*     */     
/* 116 */     Dimension minimumIconSize = UIManager.getDimension("MenuItem.minimumIconSize");
/* 117 */     this.minimumIconSize = (minimumIconSize != null) ? minimumIconSize : new Dimension(16, 16);
/*     */   }
/*     */   private static final char controlGlyph = '⌃';
/*     */   private static final char optionGlyph = '⌥';
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 123 */     if (key.startsWith("icon.") || key.equals("selectionForeground")) {
/* 124 */       if (this.iconsShared) {
/* 125 */         if (this.checkIcon instanceof FlatCheckBoxMenuItemIcon)
/* 126 */           this.checkIcon = FlatStylingSupport.cloneIcon(this.checkIcon); 
/* 127 */         if (this.arrowIcon instanceof FlatMenuArrowIcon)
/* 128 */           this.arrowIcon = FlatStylingSupport.cloneIcon(this.arrowIcon); 
/* 129 */         this.iconsShared = false;
/*     */       } 
/*     */       
/* 132 */       if (key.startsWith("icon.")) {
/* 133 */         String key2 = key.substring("icon.".length());
/*     */         
/*     */         try {
/* 136 */           if (this.checkIcon instanceof FlatCheckBoxMenuItemIcon)
/* 137 */             return ((FlatCheckBoxMenuItemIcon)this.checkIcon).applyStyleProperty(key2, value); 
/* 138 */         } catch (UnknownStyleException unknownStyleException) {}
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 143 */           if (this.arrowIcon instanceof FlatMenuArrowIcon)
/* 144 */             return ((FlatMenuArrowIcon)this.arrowIcon).applyStyleProperty(key2, value); 
/* 145 */         } catch (UnknownStyleException unknownStyleException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 150 */         throw new FlatStylingSupport.UnknownStyleException(key);
/* 151 */       }  if (key.equals("selectionForeground")) {
/*     */         
/* 153 */         if (this.checkIcon instanceof FlatCheckBoxMenuItemIcon)
/* 154 */           ((FlatCheckBoxMenuItemIcon)this.checkIcon).applyStyleProperty(key, value); 
/* 155 */         if (this.arrowIcon instanceof FlatMenuArrowIcon) {
/* 156 */           ((FlatMenuArrowIcon)this.arrowIcon).applyStyleProperty(key, value);
/*     */         }
/*     */         
/* 159 */         throw new FlatStylingSupport.UnknownStyleException(key);
/*     */       } 
/*     */     } 
/*     */     
/* 163 */     return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
/*     */   }
/*     */   private static final char shiftGlyph = '⇧'; private static final char commandGlyph = '⌘';
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos() {
/* 168 */     Map<String, Class<?>> infos = FlatStylingSupport.getAnnotatedStyleableInfos(this);
/* 169 */     if (this.checkIcon instanceof FlatCheckBoxMenuItemIcon)
/* 170 */       FlatStylingSupport.putAllPrefixKey(infos, "icon.", ((FlatCheckBoxMenuItemIcon)this.checkIcon).getStyleableInfos()); 
/* 171 */     infos.remove("icon.selectionForeground");
/* 172 */     if (this.arrowIcon instanceof FlatMenuArrowIcon)
/* 173 */       FlatStylingSupport.putAllPrefixKey(infos, "icon.", ((FlatMenuArrowIcon)this.arrowIcon).getStyleableInfos()); 
/* 174 */     infos.remove("icon.selectionForeground");
/* 175 */     return infos;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(String key) {
/* 180 */     if (key.startsWith("icon.")) {
/* 181 */       String key2 = key.substring("icon.".length());
/* 182 */       if (this.checkIcon instanceof FlatCheckBoxMenuItemIcon)
/* 183 */         return ((FlatCheckBoxMenuItemIcon)this.checkIcon).getStyleableValue(key2); 
/* 184 */       if (this.arrowIcon instanceof FlatMenuArrowIcon) {
/* 185 */         return ((FlatMenuArrowIcon)this.arrowIcon).getStyleableValue(key2);
/*     */       }
/*     */     } 
/* 188 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */   
/*     */   protected Dimension getPreferredMenuItemSize() {
/* 192 */     int width = 0;
/* 193 */     int height = 0;
/* 194 */     boolean isTopLevelMenu = isTopLevelMenu(this.menuItem);
/*     */     
/* 196 */     Rectangle viewRect = new Rectangle(0, 0, 2147483647, 2147483647);
/* 197 */     Rectangle iconRect = new Rectangle();
/* 198 */     Rectangle textRect = new Rectangle();
/*     */ 
/*     */     
/* 201 */     SwingUtilities.layoutCompoundLabel(this.menuItem, this.menuItem
/* 202 */         .getFontMetrics(isTopLevelMenu ? getTopLevelFont() : this.menuItem.getFont()), this.menuItem
/* 203 */         .getText(), getIconForLayout(), this.menuItem
/* 204 */         .getVerticalAlignment(), this.menuItem.getHorizontalAlignment(), this.menuItem
/* 205 */         .getVerticalTextPosition(), this.menuItem.getHorizontalTextPosition(), viewRect, iconRect, textRect, 
/* 206 */         UIScale.scale(this.menuItem.getIconTextGap()));
/*     */ 
/*     */     
/* 209 */     Rectangle labelRect = iconRect.union(textRect);
/* 210 */     width += labelRect.width;
/* 211 */     height = Math.max(labelRect.height, height);
/*     */ 
/*     */     
/* 214 */     String accelText = getAcceleratorText();
/* 215 */     if (accelText != null) {
/*     */       
/* 217 */       width += UIScale.scale(!isTopLevelMenu ? this.textAcceleratorGap : this.menuItem.getIconTextGap());
/*     */       
/* 219 */       FontMetrics accelFm = this.menuItem.getFontMetrics(this.acceleratorFont);
/* 220 */       width += SwingUtilities.computeStringWidth(accelFm, accelText);
/* 221 */       height = Math.max(accelFm.getHeight(), height);
/*     */     } 
/*     */ 
/*     */     
/* 225 */     if (!isTopLevelMenu && this.arrowIcon != null) {
/*     */       
/* 227 */       if (accelText == null) {
/* 228 */         width += UIScale.scale(this.textNoAcceleratorGap);
/*     */       }
/*     */       
/* 231 */       width += UIScale.scale(this.acceleratorArrowGap);
/*     */       
/* 233 */       width += this.arrowIcon.getIconWidth();
/* 234 */       height = Math.max(this.arrowIcon.getIconHeight(), height);
/*     */     } 
/*     */ 
/*     */     
/* 238 */     Insets insets = this.menuItem.getInsets();
/* 239 */     width += insets.left + insets.right;
/* 240 */     height += insets.top + insets.bottom;
/*     */ 
/*     */     
/* 243 */     if (!isTopLevelMenu) {
/* 244 */       int minimumWidth = FlatUIUtils.minimumWidth(this.menuItem, this.minimumWidth);
/* 245 */       width = Math.max(width, UIScale.scale(minimumWidth));
/*     */     } 
/*     */     
/* 248 */     return new Dimension(width, height);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void layout(Rectangle viewRect, Rectangle iconRect, Rectangle textRect, Rectangle accelRect, Rectangle arrowRect, Rectangle labelRect) {
/* 254 */     boolean isTopLevelMenu = isTopLevelMenu(this.menuItem);
/*     */ 
/*     */     
/* 257 */     if (!isTopLevelMenu && this.arrowIcon != null) {
/* 258 */       arrowRect.width = this.arrowIcon.getIconWidth();
/* 259 */       arrowRect.height = this.arrowIcon.getIconHeight();
/*     */     } else {
/* 261 */       arrowRect.setSize(0, 0);
/* 262 */     }  viewRect.y += centerOffset(viewRect.height, arrowRect.height);
/*     */ 
/*     */     
/* 265 */     String accelText = getAcceleratorText();
/* 266 */     if (accelText != null) {
/* 267 */       FontMetrics accelFm = this.menuItem.getFontMetrics(this.acceleratorFont);
/* 268 */       accelRect.width = SwingUtilities.computeStringWidth(accelFm, accelText);
/* 269 */       accelRect.height = accelFm.getHeight();
/*     */       
/* 271 */       viewRect.y += centerOffset(viewRect.height, accelRect.height);
/*     */     } else {
/* 273 */       accelRect.setBounds(0, 0, 0, 0);
/*     */     } 
/*     */     
/* 276 */     int accelArrowGap = !isTopLevelMenu ? UIScale.scale(this.acceleratorArrowGap) : 0;
/* 277 */     if (this.menuItem.getComponentOrientation().isLeftToRight()) {
/*     */       
/* 279 */       arrowRect.x = viewRect.x + viewRect.width - arrowRect.width;
/* 280 */       accelRect.x = arrowRect.x - accelArrowGap - accelRect.width;
/*     */     } else {
/*     */       
/* 283 */       arrowRect.x = viewRect.x;
/* 284 */       accelRect.x = arrowRect.x + accelArrowGap + arrowRect.width;
/*     */     } 
/*     */ 
/*     */     
/* 288 */     int accelArrowWidth = accelRect.width + arrowRect.width;
/* 289 */     if (accelText != null)
/* 290 */       accelArrowWidth += UIScale.scale(!isTopLevelMenu ? this.textAcceleratorGap : this.menuItem.getIconTextGap()); 
/* 291 */     if (!isTopLevelMenu && this.arrowIcon != null) {
/* 292 */       if (accelText == null)
/* 293 */         accelArrowWidth += UIScale.scale(this.textNoAcceleratorGap); 
/* 294 */       accelArrowWidth += UIScale.scale(this.acceleratorArrowGap);
/*     */     } 
/*     */ 
/*     */     
/* 298 */     labelRect.setBounds(viewRect);
/* 299 */     labelRect.width -= accelArrowWidth;
/* 300 */     if (!this.menuItem.getComponentOrientation().isLeftToRight()) {
/* 301 */       labelRect.x += accelArrowWidth;
/*     */     }
/*     */     
/* 304 */     SwingUtilities.layoutCompoundLabel(this.menuItem, this.menuItem
/* 305 */         .getFontMetrics(isTopLevelMenu ? getTopLevelFont() : this.menuItem.getFont()), this.menuItem
/* 306 */         .getText(), getIconForLayout(), this.menuItem
/* 307 */         .getVerticalAlignment(), this.menuItem.getHorizontalAlignment(), this.menuItem
/* 308 */         .getVerticalTextPosition(), this.menuItem.getHorizontalTextPosition(), labelRect, iconRect, textRect, 
/* 309 */         UIScale.scale(this.menuItem.getIconTextGap()));
/*     */   }
/*     */   
/*     */   private static int centerOffset(int wh1, int wh2) {
/* 313 */     return wh1 / 2 - wh2 / 2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintMenuItem(Graphics g, Color selectionBackground, Color selectionForeground, Color disabledForeground, Color acceleratorForeground, Color acceleratorSelectionForeground) {
/* 319 */     Rectangle viewRect = new Rectangle(this.menuItem.getWidth(), this.menuItem.getHeight());
/*     */ 
/*     */     
/* 322 */     Insets insets = this.menuItem.getInsets();
/* 323 */     viewRect.x += insets.left;
/* 324 */     viewRect.y += insets.top;
/* 325 */     viewRect.width -= insets.left + insets.right;
/* 326 */     viewRect.height -= insets.top + insets.bottom;
/*     */     
/* 328 */     Rectangle iconRect = new Rectangle();
/* 329 */     Rectangle textRect = new Rectangle();
/* 330 */     Rectangle accelRect = new Rectangle();
/* 331 */     Rectangle arrowRect = new Rectangle();
/* 332 */     Rectangle labelRect = new Rectangle();
/*     */     
/* 334 */     layout(viewRect, iconRect, textRect, accelRect, arrowRect, labelRect);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 345 */     boolean armedOrSelected = isArmedOrSelected(this.menuItem);
/* 346 */     boolean underlineSelection = isUnderlineSelection();
/*     */     
/* 348 */     paintBackground(g);
/* 349 */     if (armedOrSelected)
/* 350 */       if (underlineSelection) {
/* 351 */         paintUnderlineSelection(g, this.underlineSelectionBackground, this.underlineSelectionColor, this.underlineSelectionHeight);
/*     */       } else {
/* 353 */         paintSelection(g, selectionBackground, this.selectionInsets, this.selectionArc);
/*     */       }  
/* 355 */     paintIcon(g, iconRect, getIconForPainting(), underlineSelection ? this.underlineSelectionCheckBackground : this.checkBackground, selectionBackground);
/* 356 */     paintText(g, textRect, this.menuItem.getText(), selectionForeground, disabledForeground);
/* 357 */     paintAccelerator(g, accelRect, getAcceleratorText(), acceleratorForeground, acceleratorSelectionForeground, disabledForeground);
/* 358 */     if (!isTopLevelMenu(this.menuItem)) {
/* 359 */       paintArrowIcon(g, arrowRect, this.arrowIcon);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void paintBackground(Graphics g) {
/* 364 */     if (this.menuItem.isOpaque()) {
/* 365 */       g.setColor(this.menuItem.getBackground());
/* 366 */       g.fillRect(0, 0, this.menuItem.getWidth(), this.menuItem.getHeight());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintSelection(Graphics g, Color selectionBackground, Insets selectionInsets, int selectionArc) {
/* 372 */     float arc = UIScale.scale(selectionArc / 2.0F);
/*     */     
/* 374 */     g.setColor(deriveBackground(selectionBackground));
/* 375 */     FlatUIUtils.paintSelection((Graphics2D)g, 0, 0, this.menuItem.getWidth(), this.menuItem.getHeight(), 
/* 376 */         UIScale.scale(selectionInsets), arc, arc, arc, arc, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintUnderlineSelection(Graphics g, Color underlineSelectionBackground, Color underlineSelectionColor, int underlineSelectionHeight) {
/* 383 */     int width = this.menuItem.getWidth();
/* 384 */     int height = this.menuItem.getHeight();
/*     */ 
/*     */     
/* 387 */     g.setColor(deriveBackground(underlineSelectionBackground));
/* 388 */     g.fillRect(0, 0, width, height);
/*     */ 
/*     */     
/* 391 */     int underlineHeight = UIScale.scale(underlineSelectionHeight);
/* 392 */     g.setColor(underlineSelectionColor);
/* 393 */     if (isTopLevelMenu(this.menuItem)) {
/*     */       
/* 395 */       g.fillRect(0, height - underlineHeight, width, underlineHeight);
/* 396 */     } else if (this.menuItem.getComponentOrientation().isLeftToRight()) {
/*     */       
/* 398 */       g.fillRect(0, 0, underlineHeight, height);
/*     */     } else {
/*     */       
/* 401 */       g.fillRect(width - underlineHeight, 0, underlineHeight, height);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Color deriveBackground(Color background) {
/* 406 */     if (!(background instanceof com.formdev.flatlaf.util.DerivedColor)) {
/* 407 */       return background;
/*     */     }
/*     */ 
/*     */     
/* 411 */     Color baseColor = this.menuItem.isOpaque() ? this.menuItem.getBackground() : FlatUIUtils.getParentBackground(this.menuItem);
/*     */     
/* 413 */     return FlatUIUtils.deriveColor(background, baseColor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintIcon(Graphics g, Rectangle iconRect, Icon icon, Color checkBackground, Color selectionBackground) {
/* 419 */     if (this.menuItem.isSelected() && this.checkIcon != null && icon != this.checkIcon) {
/* 420 */       Rectangle r = FlatUIUtils.addInsets(iconRect, UIScale.scale(this.checkMargins));
/* 421 */       g.setColor(FlatUIUtils.deriveColor(checkBackground, selectionBackground));
/* 422 */       g.fillRect(r.x, r.y, r.width, r.height);
/*     */     } 
/*     */     
/* 425 */     paintIcon(g, this.menuItem, icon, iconRect);
/*     */   }
/*     */   
/*     */   protected void paintText(Graphics g, Rectangle textRect, String text, Color selectionForeground, Color disabledForeground) {
/* 429 */     View htmlView = (View)this.menuItem.getClientProperty("html");
/* 430 */     if (htmlView != null) {
/* 431 */       paintHTMLText(g, this.menuItem, textRect, htmlView, isUnderlineSelection() ? null : selectionForeground);
/*     */       
/*     */       return;
/*     */     } 
/* 435 */     int mnemonicIndex = FlatLaf.isShowMnemonics() ? this.menuItem.getDisplayedMnemonicIndex() : -1;
/* 436 */     boolean isTopLevelMenu = isTopLevelMenu(this.menuItem);
/* 437 */     Color foreground = (isTopLevelMenu ? this.menuItem.getParent() : this.menuItem).getForeground();
/*     */     
/* 439 */     paintText(g, this.menuItem, textRect, text, mnemonicIndex, isTopLevelMenu ? getTopLevelFont() : this.menuItem.getFont(), foreground, 
/* 440 */         isUnderlineSelection() ? foreground : selectionForeground, disabledForeground);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintAccelerator(Graphics g, Rectangle accelRect, String accelText, Color foreground, Color selectionForeground, Color disabledForeground) {
/* 446 */     paintText(g, this.menuItem, accelRect, accelText, -1, this.acceleratorFont, foreground, 
/* 447 */         isUnderlineSelection() ? foreground : selectionForeground, disabledForeground);
/*     */   }
/*     */   
/*     */   protected void paintArrowIcon(Graphics g, Rectangle arrowRect, Icon arrowIcon) {
/* 451 */     paintIcon(g, this.menuItem, arrowIcon, arrowRect);
/*     */   }
/*     */   
/*     */   protected static void paintIcon(Graphics g, JMenuItem menuItem, Icon icon, Rectangle iconRect) {
/* 455 */     if (icon == null) {
/*     */       return;
/*     */     }
/*     */     
/* 459 */     int x = iconRect.x + centerOffset(iconRect.width, icon.getIconWidth());
/* 460 */     int y = iconRect.y + centerOffset(iconRect.height, icon.getIconHeight());
/*     */ 
/*     */     
/* 463 */     icon.paintIcon(menuItem, g, x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void paintText(Graphics g, JMenuItem menuItem, Rectangle textRect, String text, int mnemonicIndex, Font font, Color foreground, Color selectionForeground, Color disabledForeground) {
/* 470 */     if (text == null || text.isEmpty()) {
/*     */       return;
/*     */     }
/* 473 */     FontMetrics fm = menuItem.getFontMetrics(font);
/*     */     
/* 475 */     Font oldFont = g.getFont();
/* 476 */     g.setFont(font);
/* 477 */     g.setColor(!menuItem.isEnabled() ? 
/* 478 */         disabledForeground : (
/* 479 */         isArmedOrSelected(menuItem) ? 
/* 480 */         selectionForeground : 
/* 481 */         foreground));
/*     */     
/* 483 */     FlatUIUtils.drawStringUnderlineCharAt(menuItem, g, text, mnemonicIndex, textRect.x, textRect.y + fm
/* 484 */         .getAscent());
/*     */     
/* 486 */     g.setFont(oldFont);
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
/*     */   protected static void paintHTMLText(Graphics g, JMenuItem menuItem, Rectangle textRect, View htmlView, Color selectionForeground) {
/*     */     GraphicsProxyWithTextColor graphicsProxyWithTextColor;
/* 502 */     textRect = new Rectangle(textRect);
/* 503 */     textRect.width = (int)htmlView.getPreferredSpan(0);
/*     */     
/* 505 */     if (isArmedOrSelected(menuItem) && selectionForeground != null) {
/* 506 */       graphicsProxyWithTextColor = new GraphicsProxyWithTextColor((Graphics2D)g, selectionForeground);
/*     */     }
/* 508 */     htmlView.paint(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)graphicsProxyWithTextColor), textRect);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean isArmedOrSelected(JMenuItem menuItem) {
/* 516 */     return (menuItem.isArmed() || (menuItem instanceof JMenu && menuItem.isSelected()));
/*     */   }
/*     */   
/*     */   protected static boolean isTopLevelMenu(JMenuItem menuItem) {
/* 520 */     return (menuItem instanceof JMenu && ((JMenu)menuItem).isTopLevelMenu());
/*     */   }
/*     */   
/*     */   protected boolean isUnderlineSelection() {
/* 524 */     return "underline".equals(UIManager.getString("MenuItem.selectionType"));
/*     */   }
/*     */   
/*     */   private Font getTopLevelFont() {
/* 528 */     Font font = this.menuItem.getFont();
/*     */ 
/*     */     
/* 531 */     return (font != this.menuFont || this.menuItem.getParent() == null) ? 
/* 532 */       font : 
/* 533 */       this.menuItem.getParent().getFont();
/*     */   }
/*     */   
/*     */   private Icon getIconForPainting() {
/* 537 */     Icon icon = this.menuItem.getIcon();
/*     */     
/* 539 */     if (icon == null && this.checkIcon != null && !isTopLevelMenu(this.menuItem)) {
/* 540 */       return this.checkIcon;
/*     */     }
/* 542 */     if (icon == null) {
/* 543 */       return null;
/*     */     }
/* 545 */     if (!this.menuItem.isEnabled()) {
/* 546 */       return this.menuItem.getDisabledIcon();
/*     */     }
/* 548 */     if (this.menuItem.getModel().isPressed() && this.menuItem.isArmed()) {
/* 549 */       Icon pressedIcon = this.menuItem.getPressedIcon();
/* 550 */       if (pressedIcon != null) {
/* 551 */         return pressedIcon;
/*     */       }
/*     */     } 
/* 554 */     if (isArmedOrSelected(this.menuItem)) {
/* 555 */       Icon selectedIcon = this.menuItem.getSelectedIcon();
/* 556 */       if (selectedIcon != null) {
/* 557 */         return selectedIcon;
/*     */       }
/*     */     } 
/* 560 */     return icon;
/*     */   }
/*     */   
/*     */   private Icon getIconForLayout() {
/* 564 */     Icon icon = this.menuItem.getIcon();
/*     */     
/* 566 */     if (isTopLevelMenu(this.menuItem)) {
/* 567 */       return (icon != null) ? new MinSizeIcon(icon) : null;
/*     */     }
/* 569 */     return new MinSizeIcon((icon != null) ? icon : this.checkIcon);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getAcceleratorText() {
/* 577 */     KeyStroke accelerator = this.menuItem.getAccelerator();
/* 578 */     if (accelerator == null) {
/* 579 */       return null;
/*     */     }
/* 581 */     boolean leftToRight = this.menuItem.getComponentOrientation().isLeftToRight();
/*     */     
/* 583 */     if (accelerator == this.cachedAccelerator && leftToRight == this.cachedAcceleratorLeftToRight) {
/* 584 */       return this.cachedAcceleratorText;
/*     */     }
/* 586 */     this.cachedAccelerator = accelerator;
/* 587 */     this.cachedAcceleratorText = getTextForAccelerator(accelerator);
/* 588 */     this.cachedAcceleratorLeftToRight = leftToRight;
/*     */     
/* 590 */     return this.cachedAcceleratorText;
/*     */   }
/*     */   
/*     */   protected String getTextForAccelerator(KeyStroke accelerator) {
/* 594 */     StringBuilder buf = new StringBuilder();
/* 595 */     boolean leftToRight = this.menuItem.getComponentOrientation().isLeftToRight();
/*     */ 
/*     */     
/* 598 */     int modifiers = accelerator.getModifiers();
/* 599 */     if (modifiers != 0) {
/* 600 */       if (SystemInfo.isMacOS) {
/* 601 */         if (leftToRight)
/* 602 */           buf.append(getMacOSModifiersExText(modifiers, leftToRight)); 
/*     */       } else {
/* 604 */         buf.append(InputEvent.getModifiersExText(modifiers)).append(this.acceleratorDelimiter);
/*     */       } 
/*     */     }
/*     */     
/* 608 */     int keyCode = accelerator.getKeyCode();
/* 609 */     if (keyCode != 0) {
/* 610 */       buf.append(KeyEvent.getKeyText(keyCode));
/*     */     } else {
/* 612 */       buf.append(accelerator.getKeyChar());
/*     */     } 
/*     */     
/* 615 */     if (modifiers != 0 && !leftToRight && SystemInfo.isMacOS) {
/* 616 */       buf.append(getMacOSModifiersExText(modifiers, leftToRight));
/*     */     }
/* 618 */     return buf.toString();
/*     */   }
/*     */   
/*     */   protected String getMacOSModifiersExText(int modifiers, boolean leftToRight) {
/* 622 */     StringBuilder buf = new StringBuilder();
/*     */     
/* 624 */     if ((modifiers & 0x80) != 0)
/* 625 */       buf.append('⌃'); 
/* 626 */     if ((modifiers & 0x2200) != 0)
/* 627 */       buf.append('⌥'); 
/* 628 */     if ((modifiers & 0x40) != 0)
/* 629 */       buf.append('⇧'); 
/* 630 */     if ((modifiers & 0x100) != 0) {
/* 631 */       buf.append('⌘');
/*     */     }
/*     */     
/* 634 */     if (!leftToRight) {
/* 635 */       buf.reverse();
/*     */     }
/* 637 */     return buf.toString();
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
/*     */   private int getMaxIconsWidth() {
/* 650 */     if (!this.verticallyAlignText) {
/* 651 */       return 0;
/*     */     }
/* 653 */     Container parent = this.menuItem.getParent();
/* 654 */     if (!(parent instanceof JComponent)) {
/* 655 */       return 0;
/*     */     }
/* 657 */     int maxWidth = FlatClientProperties.clientPropertyInt((JComponent)parent, "FlatLaf.internal.FlatMenuItemRenderer.maxIconWidth", -1);
/* 658 */     if (maxWidth >= 0) {
/* 659 */       return maxWidth;
/*     */     }
/* 661 */     maxWidth = 0;
/*     */     
/* 663 */     for (Component c : parent.getComponents()) {
/* 664 */       if (c instanceof JMenuItem) {
/*     */ 
/*     */         
/* 667 */         Icon icon = ((JMenuItem)c).getIcon();
/* 668 */         if (icon != null)
/* 669 */           maxWidth = Math.max(maxWidth, icon.getIconWidth()); 
/*     */       } 
/*     */     } 
/* 672 */     ((JComponent)parent).putClientProperty("FlatLaf.internal.FlatMenuItemRenderer.maxIconWidth", Integer.valueOf(maxWidth));
/* 673 */     return maxWidth;
/*     */   }
/*     */   
/*     */   static void clearClientProperties(Component c) {
/* 677 */     if (!(c instanceof JComponent)) {
/*     */       return;
/*     */     }
/* 680 */     JComponent jc = (JComponent)c;
/* 681 */     jc.putClientProperty("FlatLaf.internal.FlatMenuItemRenderer.maxIconWidth", (Object)null);
/*     */   }
/*     */ 
/*     */   
/*     */   private class MinSizeIcon
/*     */     implements Icon
/*     */   {
/*     */     private final Icon delegate;
/*     */ 
/*     */     
/*     */     MinSizeIcon(Icon delegate) {
/* 692 */       this.delegate = delegate;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getIconWidth() {
/* 697 */       int iconWidth = (this.delegate != null) ? this.delegate.getIconWidth() : 0;
/* 698 */       iconWidth = Math.max(iconWidth, FlatMenuItemRenderer.this.getMaxIconsWidth());
/* 699 */       return Math.max(iconWidth, UIScale.scale(FlatMenuItemRenderer.this.minimumIconSize.width));
/*     */     }
/*     */ 
/*     */     
/*     */     public int getIconHeight() {
/* 704 */       int iconHeight = (this.delegate != null) ? this.delegate.getIconHeight() : 0;
/* 705 */       return Math.max(iconHeight, UIScale.scale(FlatMenuItemRenderer.this.minimumIconSize.height));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void paintIcon(Component c, Graphics g, int x, int y) {}
/*     */   }
/*     */ 
/*     */   
/*     */   private static class GraphicsProxyWithTextColor
/*     */     extends Graphics2DProxy
/*     */   {
/*     */     private final Color textColor;
/*     */ 
/*     */     
/*     */     GraphicsProxyWithTextColor(Graphics2D delegate, Color textColor) {
/* 721 */       super(delegate);
/* 722 */       this.textColor = textColor;
/*     */     }
/*     */ 
/*     */     
/*     */     public void drawString(String str, int x, int y) {
/* 727 */       Paint oldPaint = getPaint();
/* 728 */       setPaint(this.textColor);
/* 729 */       super.drawString(str, x, y);
/* 730 */       setPaint(oldPaint);
/*     */     }
/*     */ 
/*     */     
/*     */     public void drawString(String str, float x, float y) {
/* 735 */       Paint oldPaint = getPaint();
/* 736 */       setPaint(this.textColor);
/* 737 */       super.drawString(str, x, y);
/* 738 */       setPaint(oldPaint);
/*     */     }
/*     */ 
/*     */     
/*     */     public void drawString(AttributedCharacterIterator iterator, int x, int y) {
/* 743 */       Paint oldPaint = getPaint();
/* 744 */       setPaint(this.textColor);
/* 745 */       super.drawString(iterator, x, y);
/* 746 */       setPaint(oldPaint);
/*     */     }
/*     */ 
/*     */     
/*     */     public void drawString(AttributedCharacterIterator iterator, float x, float y) {
/* 751 */       Paint oldPaint = getPaint();
/* 752 */       setPaint(this.textColor);
/* 753 */       super.drawString(iterator, x, y);
/* 754 */       setPaint(oldPaint);
/*     */     }
/*     */ 
/*     */     
/*     */     public void drawChars(char[] data, int offset, int length, int x, int y) {
/* 759 */       Paint oldPaint = getPaint();
/* 760 */       setPaint(this.textColor);
/* 761 */       super.drawChars(data, offset, length, x, y);
/* 762 */       setPaint(oldPaint);
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatMenuItemRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */