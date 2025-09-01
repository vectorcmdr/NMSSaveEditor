package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.icons.FlatCheckBoxMenuItemIcon;
import com.formdev.flatlaf.icons.FlatMenuArrowIcon;
import com.formdev.flatlaf.util.DerivedColor;
import com.formdev.flatlaf.util.Graphics2DProxy;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.View;

public class FlatMenuItemRenderer {
   private static final String KEY_MAX_ICONS_WIDTH = "FlatLaf.internal.FlatMenuItemRenderer.maxIconWidth";
   protected final JMenuItem menuItem;
   protected Icon checkIcon;
   protected Icon arrowIcon;
   @FlatStylingSupport.Styleable
   protected Font acceleratorFont;
   protected final String acceleratorDelimiter;
   @FlatStylingSupport.Styleable
   protected boolean verticallyAlignText = FlatUIUtils.getUIBoolean("MenuItem.verticallyAlignText", true);
   @FlatStylingSupport.Styleable
   protected int minimumWidth = UIManager.getInt("MenuItem.minimumWidth");
   @FlatStylingSupport.Styleable
   protected Dimension minimumIconSize;
   @FlatStylingSupport.Styleable
   protected int textAcceleratorGap = FlatUIUtils.getUIInt("MenuItem.textAcceleratorGap", 28);
   @FlatStylingSupport.Styleable
   protected int textNoAcceleratorGap = FlatUIUtils.getUIInt("MenuItem.textNoAcceleratorGap", 6);
   @FlatStylingSupport.Styleable
   protected int acceleratorArrowGap = FlatUIUtils.getUIInt("MenuItem.acceleratorArrowGap", 2);
   @FlatStylingSupport.Styleable
   protected Color checkBackground = UIManager.getColor("MenuItem.checkBackground");
   @FlatStylingSupport.Styleable
   protected Insets checkMargins = UIManager.getInsets("MenuItem.checkMargins");
   @FlatStylingSupport.Styleable
   protected Insets selectionInsets = UIManager.getInsets("MenuItem.selectionInsets");
   @FlatStylingSupport.Styleable
   protected int selectionArc = UIManager.getInt("MenuItem.selectionArc");
   @FlatStylingSupport.Styleable
   protected Color underlineSelectionBackground = UIManager.getColor("MenuItem.underlineSelectionBackground");
   @FlatStylingSupport.Styleable
   protected Color underlineSelectionCheckBackground = UIManager.getColor("MenuItem.underlineSelectionCheckBackground");
   @FlatStylingSupport.Styleable
   protected Color underlineSelectionColor = UIManager.getColor("MenuItem.underlineSelectionColor");
   @FlatStylingSupport.Styleable
   protected int underlineSelectionHeight = UIManager.getInt("MenuItem.underlineSelectionHeight");
   private boolean iconsShared = true;
   private final Font menuFont = UIManager.getFont("Menu.font");
   private KeyStroke cachedAccelerator;
   private String cachedAcceleratorText;
   private boolean cachedAcceleratorLeftToRight;
   private static final char controlGlyph = '⌃';
   private static final char optionGlyph = '⌥';
   private static final char shiftGlyph = '⇧';
   private static final char commandGlyph = '⌘';

   protected FlatMenuItemRenderer(JMenuItem menuItem, Icon checkIcon, Icon arrowIcon, Font acceleratorFont, String acceleratorDelimiter) {
      this.menuItem = menuItem;
      this.checkIcon = checkIcon;
      this.arrowIcon = arrowIcon;
      this.acceleratorFont = acceleratorFont;
      this.acceleratorDelimiter = acceleratorDelimiter;
      Dimension minimumIconSize = UIManager.getDimension("MenuItem.minimumIconSize");
      this.minimumIconSize = minimumIconSize != null ? minimumIconSize : new Dimension(16, 16);
   }

   protected Object applyStyleProperty(String key, Object value) {
      if (key.startsWith("icon.") || key.equals("selectionForeground")) {
         if (this.iconsShared) {
            if (this.checkIcon instanceof FlatCheckBoxMenuItemIcon) {
               this.checkIcon = FlatStylingSupport.cloneIcon(this.checkIcon);
            }

            if (this.arrowIcon instanceof FlatMenuArrowIcon) {
               this.arrowIcon = FlatStylingSupport.cloneIcon(this.arrowIcon);
            }

            this.iconsShared = false;
         }

         if (key.startsWith("icon.")) {
            String key2 = key.substring("icon.".length());

            try {
               if (this.checkIcon instanceof FlatCheckBoxMenuItemIcon) {
                  return ((FlatCheckBoxMenuItemIcon)this.checkIcon).applyStyleProperty(key2, value);
               }
            } catch (FlatStylingSupport.UnknownStyleException var6) {
            }

            try {
               if (this.arrowIcon instanceof FlatMenuArrowIcon) {
                  return ((FlatMenuArrowIcon)this.arrowIcon).applyStyleProperty(key2, value);
               }
            } catch (FlatStylingSupport.UnknownStyleException var5) {
            }

            throw new FlatStylingSupport.UnknownStyleException(key);
         }

         if (key.equals("selectionForeground")) {
            if (this.checkIcon instanceof FlatCheckBoxMenuItemIcon) {
               ((FlatCheckBoxMenuItemIcon)this.checkIcon).applyStyleProperty(key, value);
            }

            if (this.arrowIcon instanceof FlatMenuArrowIcon) {
               ((FlatMenuArrowIcon)this.arrowIcon).applyStyleProperty(key, value);
            }

            throw new FlatStylingSupport.UnknownStyleException(key);
         }
      }

      return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
   }

   public Map<String, Class<?>> getStyleableInfos() {
      Map<String, Class<?>> infos = FlatStylingSupport.getAnnotatedStyleableInfos(this);
      if (this.checkIcon instanceof FlatCheckBoxMenuItemIcon) {
         FlatStylingSupport.putAllPrefixKey(infos, "icon.", ((FlatCheckBoxMenuItemIcon)this.checkIcon).getStyleableInfos());
      }

      infos.remove("icon.selectionForeground");
      if (this.arrowIcon instanceof FlatMenuArrowIcon) {
         FlatStylingSupport.putAllPrefixKey(infos, "icon.", ((FlatMenuArrowIcon)this.arrowIcon).getStyleableInfos());
      }

      infos.remove("icon.selectionForeground");
      return infos;
   }

   public Object getStyleableValue(String key) {
      if (key.startsWith("icon.")) {
         String key2 = key.substring("icon.".length());
         if (this.checkIcon instanceof FlatCheckBoxMenuItemIcon) {
            return ((FlatCheckBoxMenuItemIcon)this.checkIcon).getStyleableValue(key2);
         }

         if (this.arrowIcon instanceof FlatMenuArrowIcon) {
            return ((FlatMenuArrowIcon)this.arrowIcon).getStyleableValue(key2);
         }
      }

      return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
   }

   protected Dimension getPreferredMenuItemSize() {
      int width = 0;
      int height = 0;
      boolean isTopLevelMenu = isTopLevelMenu(this.menuItem);
      Rectangle viewRect = new Rectangle(0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
      Rectangle iconRect = new Rectangle();
      Rectangle textRect = new Rectangle();
      SwingUtilities.layoutCompoundLabel(this.menuItem, this.menuItem.getFontMetrics(isTopLevelMenu ? this.getTopLevelFont() : this.menuItem.getFont()), this.menuItem.getText(), this.getIconForLayout(), this.menuItem.getVerticalAlignment(), this.menuItem.getHorizontalAlignment(), this.menuItem.getVerticalTextPosition(), this.menuItem.getHorizontalTextPosition(), viewRect, iconRect, textRect, UIScale.scale(this.menuItem.getIconTextGap()));
      Rectangle labelRect = iconRect.union(textRect);
      int width = width + labelRect.width;
      int height = Math.max(labelRect.height, height);
      String accelText = this.getAcceleratorText();
      if (accelText != null) {
         width += UIScale.scale(!isTopLevelMenu ? this.textAcceleratorGap : this.menuItem.getIconTextGap());
         FontMetrics accelFm = this.menuItem.getFontMetrics(this.acceleratorFont);
         width += SwingUtilities.computeStringWidth(accelFm, accelText);
         height = Math.max(accelFm.getHeight(), height);
      }

      if (!isTopLevelMenu && this.arrowIcon != null) {
         if (accelText == null) {
            width += UIScale.scale(this.textNoAcceleratorGap);
         }

         width += UIScale.scale(this.acceleratorArrowGap);
         width += this.arrowIcon.getIconWidth();
         height = Math.max(this.arrowIcon.getIconHeight(), height);
      }

      Insets insets = this.menuItem.getInsets();
      width += insets.left + insets.right;
      height += insets.top + insets.bottom;
      if (!isTopLevelMenu) {
         int minimumWidth = FlatUIUtils.minimumWidth(this.menuItem, this.minimumWidth);
         width = Math.max(width, UIScale.scale(minimumWidth));
      }

      return new Dimension(width, height);
   }

   private void layout(Rectangle viewRect, Rectangle iconRect, Rectangle textRect, Rectangle accelRect, Rectangle arrowRect, Rectangle labelRect) {
      boolean isTopLevelMenu = isTopLevelMenu(this.menuItem);
      if (!isTopLevelMenu && this.arrowIcon != null) {
         arrowRect.width = this.arrowIcon.getIconWidth();
         arrowRect.height = this.arrowIcon.getIconHeight();
      } else {
         arrowRect.setSize(0, 0);
      }

      arrowRect.y = viewRect.y + centerOffset(viewRect.height, arrowRect.height);
      String accelText = this.getAcceleratorText();
      if (accelText != null) {
         FontMetrics accelFm = this.menuItem.getFontMetrics(this.acceleratorFont);
         accelRect.width = SwingUtilities.computeStringWidth(accelFm, accelText);
         accelRect.height = accelFm.getHeight();
         accelRect.y = viewRect.y + centerOffset(viewRect.height, accelRect.height);
      } else {
         accelRect.setBounds(0, 0, 0, 0);
      }

      int accelArrowGap = !isTopLevelMenu ? UIScale.scale(this.acceleratorArrowGap) : 0;
      if (this.menuItem.getComponentOrientation().isLeftToRight()) {
         arrowRect.x = viewRect.x + viewRect.width - arrowRect.width;
         accelRect.x = arrowRect.x - accelArrowGap - accelRect.width;
      } else {
         arrowRect.x = viewRect.x;
         accelRect.x = arrowRect.x + accelArrowGap + arrowRect.width;
      }

      int accelArrowWidth = accelRect.width + arrowRect.width;
      if (accelText != null) {
         accelArrowWidth += UIScale.scale(!isTopLevelMenu ? this.textAcceleratorGap : this.menuItem.getIconTextGap());
      }

      if (!isTopLevelMenu && this.arrowIcon != null) {
         if (accelText == null) {
            accelArrowWidth += UIScale.scale(this.textNoAcceleratorGap);
         }

         accelArrowWidth += UIScale.scale(this.acceleratorArrowGap);
      }

      labelRect.setBounds(viewRect);
      labelRect.width -= accelArrowWidth;
      if (!this.menuItem.getComponentOrientation().isLeftToRight()) {
         labelRect.x += accelArrowWidth;
      }

      SwingUtilities.layoutCompoundLabel(this.menuItem, this.menuItem.getFontMetrics(isTopLevelMenu ? this.getTopLevelFont() : this.menuItem.getFont()), this.menuItem.getText(), this.getIconForLayout(), this.menuItem.getVerticalAlignment(), this.menuItem.getHorizontalAlignment(), this.menuItem.getVerticalTextPosition(), this.menuItem.getHorizontalTextPosition(), labelRect, iconRect, textRect, UIScale.scale(this.menuItem.getIconTextGap()));
   }

   private static int centerOffset(int wh1, int wh2) {
      return wh1 / 2 - wh2 / 2;
   }

   protected void paintMenuItem(Graphics g, Color selectionBackground, Color selectionForeground, Color disabledForeground, Color acceleratorForeground, Color acceleratorSelectionForeground) {
      Rectangle viewRect = new Rectangle(this.menuItem.getWidth(), this.menuItem.getHeight());
      Insets insets = this.menuItem.getInsets();
      viewRect.x += insets.left;
      viewRect.y += insets.top;
      viewRect.width -= insets.left + insets.right;
      viewRect.height -= insets.top + insets.bottom;
      Rectangle iconRect = new Rectangle();
      Rectangle textRect = new Rectangle();
      Rectangle accelRect = new Rectangle();
      Rectangle arrowRect = new Rectangle();
      Rectangle labelRect = new Rectangle();
      this.layout(viewRect, iconRect, textRect, accelRect, arrowRect, labelRect);
      boolean armedOrSelected = isArmedOrSelected(this.menuItem);
      boolean underlineSelection = this.isUnderlineSelection();
      this.paintBackground(g);
      if (armedOrSelected) {
         if (underlineSelection) {
            this.paintUnderlineSelection(g, this.underlineSelectionBackground, this.underlineSelectionColor, this.underlineSelectionHeight);
         } else {
            this.paintSelection(g, selectionBackground, this.selectionInsets, this.selectionArc);
         }
      }

      this.paintIcon(g, iconRect, this.getIconForPainting(), underlineSelection ? this.underlineSelectionCheckBackground : this.checkBackground, selectionBackground);
      this.paintText(g, textRect, this.menuItem.getText(), selectionForeground, disabledForeground);
      this.paintAccelerator(g, accelRect, this.getAcceleratorText(), acceleratorForeground, acceleratorSelectionForeground, disabledForeground);
      if (!isTopLevelMenu(this.menuItem)) {
         this.paintArrowIcon(g, arrowRect, this.arrowIcon);
      }

   }

   protected void paintBackground(Graphics g) {
      if (this.menuItem.isOpaque()) {
         g.setColor(this.menuItem.getBackground());
         g.fillRect(0, 0, this.menuItem.getWidth(), this.menuItem.getHeight());
      }

   }

   protected void paintSelection(Graphics g, Color selectionBackground, Insets selectionInsets, int selectionArc) {
      float arc = UIScale.scale((float)selectionArc / 2.0F);
      g.setColor(this.deriveBackground(selectionBackground));
      FlatUIUtils.paintSelection((Graphics2D)g, 0, 0, this.menuItem.getWidth(), this.menuItem.getHeight(), UIScale.scale(selectionInsets), arc, arc, arc, arc, 0);
   }

   protected void paintUnderlineSelection(Graphics g, Color underlineSelectionBackground, Color underlineSelectionColor, int underlineSelectionHeight) {
      int width = this.menuItem.getWidth();
      int height = this.menuItem.getHeight();
      g.setColor(this.deriveBackground(underlineSelectionBackground));
      g.fillRect(0, 0, width, height);
      int underlineHeight = UIScale.scale(underlineSelectionHeight);
      g.setColor(underlineSelectionColor);
      if (isTopLevelMenu(this.menuItem)) {
         g.fillRect(0, height - underlineHeight, width, underlineHeight);
      } else if (this.menuItem.getComponentOrientation().isLeftToRight()) {
         g.fillRect(0, 0, underlineHeight, height);
      } else {
         g.fillRect(width - underlineHeight, 0, underlineHeight, height);
      }

   }

   protected Color deriveBackground(Color background) {
      if (!(background instanceof DerivedColor)) {
         return background;
      } else {
         Color baseColor = this.menuItem.isOpaque() ? this.menuItem.getBackground() : FlatUIUtils.getParentBackground(this.menuItem);
         return FlatUIUtils.deriveColor(background, baseColor);
      }
   }

   protected void paintIcon(Graphics g, Rectangle iconRect, Icon icon, Color checkBackground, Color selectionBackground) {
      if (this.menuItem.isSelected() && this.checkIcon != null && icon != this.checkIcon) {
         Rectangle r = FlatUIUtils.addInsets(iconRect, UIScale.scale(this.checkMargins));
         g.setColor(FlatUIUtils.deriveColor(checkBackground, selectionBackground));
         g.fillRect(r.x, r.y, r.width, r.height);
      }

      paintIcon(g, this.menuItem, icon, iconRect);
   }

   protected void paintText(Graphics g, Rectangle textRect, String text, Color selectionForeground, Color disabledForeground) {
      View htmlView = (View)this.menuItem.getClientProperty("html");
      if (htmlView != null) {
         paintHTMLText(g, this.menuItem, textRect, htmlView, this.isUnderlineSelection() ? null : selectionForeground);
      } else {
         int mnemonicIndex = FlatLaf.isShowMnemonics() ? this.menuItem.getDisplayedMnemonicIndex() : -1;
         boolean isTopLevelMenu = isTopLevelMenu(this.menuItem);
         Color foreground = ((Container)(isTopLevelMenu ? this.menuItem.getParent() : this.menuItem)).getForeground();
         paintText(g, this.menuItem, textRect, text, mnemonicIndex, isTopLevelMenu ? this.getTopLevelFont() : this.menuItem.getFont(), foreground, this.isUnderlineSelection() ? foreground : selectionForeground, disabledForeground);
      }
   }

   protected void paintAccelerator(Graphics g, Rectangle accelRect, String accelText, Color foreground, Color selectionForeground, Color disabledForeground) {
      paintText(g, this.menuItem, accelRect, accelText, -1, this.acceleratorFont, foreground, this.isUnderlineSelection() ? foreground : selectionForeground, disabledForeground);
   }

   protected void paintArrowIcon(Graphics g, Rectangle arrowRect, Icon arrowIcon) {
      paintIcon(g, this.menuItem, arrowIcon, arrowRect);
   }

   protected static void paintIcon(Graphics g, JMenuItem menuItem, Icon icon, Rectangle iconRect) {
      if (icon != null) {
         int x = iconRect.x + centerOffset(iconRect.width, icon.getIconWidth());
         int y = iconRect.y + centerOffset(iconRect.height, icon.getIconHeight());
         icon.paintIcon(menuItem, g, x, y);
      }
   }

   protected static void paintText(Graphics g, JMenuItem menuItem, Rectangle textRect, String text, int mnemonicIndex, Font font, Color foreground, Color selectionForeground, Color disabledForeground) {
      if (text != null && !text.isEmpty()) {
         FontMetrics fm = menuItem.getFontMetrics(font);
         Font oldFont = g.getFont();
         g.setFont(font);
         g.setColor(!menuItem.isEnabled() ? disabledForeground : (isArmedOrSelected(menuItem) ? selectionForeground : foreground));
         FlatUIUtils.drawStringUnderlineCharAt(menuItem, g, text, mnemonicIndex, textRect.x, textRect.y + fm.getAscent());
         g.setFont(oldFont);
      }
   }

   protected static void paintHTMLText(Graphics g, JMenuItem menuItem, Rectangle textRect, View htmlView, Color selectionForeground) {
      textRect = new Rectangle(textRect);
      textRect.width = (int)htmlView.getPreferredSpan(0);
      if (isArmedOrSelected(menuItem) && selectionForeground != null) {
         g = new FlatMenuItemRenderer.GraphicsProxyWithTextColor((Graphics2D)g, selectionForeground);
      }

      htmlView.paint(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)g), textRect);
   }

   protected static boolean isArmedOrSelected(JMenuItem menuItem) {
      return menuItem.isArmed() || menuItem instanceof JMenu && menuItem.isSelected();
   }

   protected static boolean isTopLevelMenu(JMenuItem menuItem) {
      return menuItem instanceof JMenu && ((JMenu)menuItem).isTopLevelMenu();
   }

   protected boolean isUnderlineSelection() {
      return "underline".equals(UIManager.getString("MenuItem.selectionType"));
   }

   private Font getTopLevelFont() {
      Font font = this.menuItem.getFont();
      return font == this.menuFont && this.menuItem.getParent() != null ? this.menuItem.getParent().getFont() : font;
   }

   private Icon getIconForPainting() {
      Icon icon = this.menuItem.getIcon();
      if (icon == null && this.checkIcon != null && !isTopLevelMenu(this.menuItem)) {
         return this.checkIcon;
      } else if (icon == null) {
         return null;
      } else if (!this.menuItem.isEnabled()) {
         return this.menuItem.getDisabledIcon();
      } else {
         Icon selectedIcon;
         if (this.menuItem.getModel().isPressed() && this.menuItem.isArmed()) {
            selectedIcon = this.menuItem.getPressedIcon();
            if (selectedIcon != null) {
               return selectedIcon;
            }
         }

         if (isArmedOrSelected(this.menuItem)) {
            selectedIcon = this.menuItem.getSelectedIcon();
            if (selectedIcon != null) {
               return selectedIcon;
            }
         }

         return icon;
      }
   }

   private Icon getIconForLayout() {
      Icon icon = this.menuItem.getIcon();
      if (isTopLevelMenu(this.menuItem)) {
         return icon != null ? new FlatMenuItemRenderer.MinSizeIcon(icon) : null;
      } else {
         return new FlatMenuItemRenderer.MinSizeIcon(icon != null ? icon : this.checkIcon);
      }
   }

   private String getAcceleratorText() {
      KeyStroke accelerator = this.menuItem.getAccelerator();
      if (accelerator == null) {
         return null;
      } else {
         boolean leftToRight = this.menuItem.getComponentOrientation().isLeftToRight();
         if (accelerator == this.cachedAccelerator && leftToRight == this.cachedAcceleratorLeftToRight) {
            return this.cachedAcceleratorText;
         } else {
            this.cachedAccelerator = accelerator;
            this.cachedAcceleratorText = this.getTextForAccelerator(accelerator);
            this.cachedAcceleratorLeftToRight = leftToRight;
            return this.cachedAcceleratorText;
         }
      }
   }

   protected String getTextForAccelerator(KeyStroke accelerator) {
      StringBuilder buf = new StringBuilder();
      boolean leftToRight = this.menuItem.getComponentOrientation().isLeftToRight();
      int modifiers = accelerator.getModifiers();
      if (modifiers != 0) {
         if (SystemInfo.isMacOS) {
            if (leftToRight) {
               buf.append(this.getMacOSModifiersExText(modifiers, leftToRight));
            }
         } else {
            buf.append(InputEvent.getModifiersExText(modifiers)).append(this.acceleratorDelimiter);
         }
      }

      int keyCode = accelerator.getKeyCode();
      if (keyCode != 0) {
         buf.append(KeyEvent.getKeyText(keyCode));
      } else {
         buf.append(accelerator.getKeyChar());
      }

      if (modifiers != 0 && !leftToRight && SystemInfo.isMacOS) {
         buf.append(this.getMacOSModifiersExText(modifiers, leftToRight));
      }

      return buf.toString();
   }

   protected String getMacOSModifiersExText(int modifiers, boolean leftToRight) {
      StringBuilder buf = new StringBuilder();
      if ((modifiers & 128) != 0) {
         buf.append('⌃');
      }

      if ((modifiers & 8704) != 0) {
         buf.append('⌥');
      }

      if ((modifiers & 64) != 0) {
         buf.append('⇧');
      }

      if ((modifiers & 256) != 0) {
         buf.append('⌘');
      }

      if (!leftToRight) {
         buf.reverse();
      }

      return buf.toString();
   }

   private int getMaxIconsWidth() {
      if (!this.verticallyAlignText) {
         return 0;
      } else {
         Container parent = this.menuItem.getParent();
         if (!(parent instanceof JComponent)) {
            return 0;
         } else {
            int maxWidth = FlatClientProperties.clientPropertyInt((JComponent)parent, "FlatLaf.internal.FlatMenuItemRenderer.maxIconWidth", -1);
            if (maxWidth >= 0) {
               return maxWidth;
            } else {
               maxWidth = 0;
               Component[] var3 = parent.getComponents();
               int var4 = var3.length;

               for(int var5 = 0; var5 < var4; ++var5) {
                  Component c = var3[var5];
                  if (c instanceof JMenuItem) {
                     Icon icon = ((JMenuItem)c).getIcon();
                     if (icon != null) {
                        maxWidth = Math.max(maxWidth, icon.getIconWidth());
                     }
                  }
               }

               ((JComponent)parent).putClientProperty("FlatLaf.internal.FlatMenuItemRenderer.maxIconWidth", maxWidth);
               return maxWidth;
            }
         }
      }
   }

   static void clearClientProperties(Component c) {
      if (c instanceof JComponent) {
         JComponent jc = (JComponent)c;
         jc.putClientProperty("FlatLaf.internal.FlatMenuItemRenderer.maxIconWidth", (Object)null);
      }
   }

   private static class GraphicsProxyWithTextColor extends Graphics2DProxy {
      private final Color textColor;

      GraphicsProxyWithTextColor(Graphics2D delegate, Color textColor) {
         super(delegate);
         this.textColor = textColor;
      }

      public void drawString(String str, int x, int y) {
         Paint oldPaint = this.getPaint();
         this.setPaint(this.textColor);
         super.drawString(str, x, y);
         this.setPaint(oldPaint);
      }

      public void drawString(String str, float x, float y) {
         Paint oldPaint = this.getPaint();
         this.setPaint(this.textColor);
         super.drawString(str, x, y);
         this.setPaint(oldPaint);
      }

      public void drawString(AttributedCharacterIterator iterator, int x, int y) {
         Paint oldPaint = this.getPaint();
         this.setPaint(this.textColor);
         super.drawString(iterator, x, y);
         this.setPaint(oldPaint);
      }

      public void drawString(AttributedCharacterIterator iterator, float x, float y) {
         Paint oldPaint = this.getPaint();
         this.setPaint(this.textColor);
         super.drawString(iterator, x, y);
         this.setPaint(oldPaint);
      }

      public void drawChars(char[] data, int offset, int length, int x, int y) {
         Paint oldPaint = this.getPaint();
         this.setPaint(this.textColor);
         super.drawChars(data, offset, length, x, y);
         this.setPaint(oldPaint);
      }
   }

   private class MinSizeIcon implements Icon {
      private final Icon delegate;

      MinSizeIcon(Icon delegate) {
         this.delegate = delegate;
      }

      public int getIconWidth() {
         int iconWidth = this.delegate != null ? this.delegate.getIconWidth() : 0;
         iconWidth = Math.max(iconWidth, FlatMenuItemRenderer.this.getMaxIconsWidth());
         return Math.max(iconWidth, UIScale.scale(FlatMenuItemRenderer.this.minimumIconSize.width));
      }

      public int getIconHeight() {
         int iconHeight = this.delegate != null ? this.delegate.getIconHeight() : 0;
         return Math.max(iconHeight, UIScale.scale(FlatMenuItemRenderer.this.minimumIconSize.height));
      }

      public void paintIcon(Component c, Graphics g, int x, int y) {
      }
   }
}
