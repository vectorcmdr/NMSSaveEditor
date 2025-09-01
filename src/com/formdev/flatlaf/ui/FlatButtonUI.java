package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.icons.FlatHelpButtonIcon;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D.Float;
import java.beans.PropertyChangeEvent;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.ToolBarUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicButtonListener;
import javax.swing.plaf.basic.BasicButtonUI;

public class FlatButtonUI extends BasicButtonUI implements FlatStylingSupport.StyleableUI {
   @FlatStylingSupport.Styleable
   protected int minimumWidth;
   protected int iconTextGap;
   protected Color background;
   protected Color foreground;
   protected Color startBackground;
   protected Color endBackground;
   @FlatStylingSupport.Styleable
   protected Color focusedBackground;
   @FlatStylingSupport.Styleable
   protected Color focusedForeground;
   @FlatStylingSupport.Styleable
   protected Color hoverBackground;
   @FlatStylingSupport.Styleable
   protected Color hoverForeground;
   @FlatStylingSupport.Styleable
   protected Color pressedBackground;
   @FlatStylingSupport.Styleable
   protected Color pressedForeground;
   @FlatStylingSupport.Styleable
   protected Color selectedBackground;
   @FlatStylingSupport.Styleable
   protected Color selectedForeground;
   @FlatStylingSupport.Styleable
   protected Color disabledBackground;
   @FlatStylingSupport.Styleable
   protected Color disabledText;
   @FlatStylingSupport.Styleable
   protected Color disabledSelectedBackground;
   @FlatStylingSupport.Styleable
   protected Color disabledSelectedForeground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color defaultBackground;
   protected Color defaultEndBackground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color defaultForeground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color defaultFocusedBackground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color defaultFocusedForeground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color defaultHoverBackground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color defaultHoverForeground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color defaultPressedBackground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color defaultPressedForeground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected boolean defaultBoldText;
   @FlatStylingSupport.Styleable
   protected boolean paintShadow;
   @FlatStylingSupport.Styleable
   protected int shadowWidth;
   @FlatStylingSupport.Styleable
   protected Color shadowColor;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color defaultShadowColor;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color toolbarHoverBackground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color toolbarHoverForeground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color toolbarPressedBackground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color toolbarPressedForeground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color toolbarSelectedBackground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color toolbarSelectedForeground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color toolbarDisabledSelectedBackground;
   @FlatStylingSupport.Styleable(
      dot = true
   )
   protected Color toolbarDisabledSelectedForeground;
   @FlatStylingSupport.Styleable
   protected String buttonType;
   @FlatStylingSupport.Styleable
   protected boolean squareSize;
   @FlatStylingSupport.Styleable
   protected int minimumHeight;
   private Icon helpButtonIcon;
   private Insets defaultMargin;
   private final boolean shared;
   private boolean helpButtonIconShared = true;
   private boolean defaults_initialized = false;
   private Map<String, Object> oldStyleValues;
   private AtomicBoolean borderShared;
   static final int TYPE_OTHER = -1;
   static final int TYPE_SQUARE = 0;
   static final int TYPE_ROUND_RECT = 1;

   public static ComponentUI createUI(JComponent c) {
      return (ComponentUI)(FlatUIUtils.canUseSharedUI(c) && !FlatUIUtils.needsLightAWTPeer(c) ? FlatUIUtils.createSharedUI(FlatButtonUI.class, () -> {
         return new FlatButtonUI(true);
      }) : new FlatButtonUI(false));
   }

   protected FlatButtonUI(boolean shared) {
      this.shared = shared;
   }

   public void installUI(JComponent c) {
      if (FlatUIUtils.needsLightAWTPeer(c)) {
         FlatUIUtils.runWithLightAWTPeerUIDefaults(() -> {
            this.installUIImpl(c);
         });
      } else {
         this.installUIImpl(c);
      }

   }

   private void installUIImpl(JComponent c) {
      super.installUI(c);
      this.installStyle((AbstractButton)c);
   }

   protected void installDefaults(AbstractButton b) {
      super.installDefaults(b);
      if (!this.defaults_initialized) {
         String prefix = this.getPropertyPrefix();
         this.minimumWidth = UIManager.getInt(prefix + "minimumWidth");
         this.iconTextGap = FlatUIUtils.getUIInt(prefix + "iconTextGap", 4);
         this.background = UIManager.getColor(prefix + "background");
         this.foreground = UIManager.getColor(prefix + "foreground");
         this.startBackground = UIManager.getColor(prefix + "startBackground");
         this.endBackground = UIManager.getColor(prefix + "endBackground");
         this.focusedBackground = UIManager.getColor(prefix + "focusedBackground");
         this.focusedForeground = UIManager.getColor(prefix + "focusedForeground");
         this.hoverBackground = UIManager.getColor(prefix + "hoverBackground");
         this.hoverForeground = UIManager.getColor(prefix + "hoverForeground");
         this.pressedBackground = UIManager.getColor(prefix + "pressedBackground");
         this.pressedForeground = UIManager.getColor(prefix + "pressedForeground");
         this.selectedBackground = UIManager.getColor(prefix + "selectedBackground");
         this.selectedForeground = UIManager.getColor(prefix + "selectedForeground");
         this.disabledBackground = UIManager.getColor(prefix + "disabledBackground");
         this.disabledText = UIManager.getColor(prefix + "disabledText");
         this.disabledSelectedBackground = UIManager.getColor(prefix + "disabledSelectedBackground");
         this.disabledSelectedForeground = UIManager.getColor(prefix + "disabledSelectedForeground");
         this.defaultBackground = FlatUIUtils.getUIColor("Button.default.startBackground", "Button.default.background");
         this.defaultEndBackground = UIManager.getColor("Button.default.endBackground");
         this.defaultForeground = UIManager.getColor("Button.default.foreground");
         this.defaultFocusedBackground = UIManager.getColor("Button.default.focusedBackground");
         this.defaultFocusedForeground = UIManager.getColor("Button.default.focusedForeground");
         this.defaultHoverBackground = UIManager.getColor("Button.default.hoverBackground");
         this.defaultHoverForeground = UIManager.getColor("Button.default.hoverForeground");
         this.defaultPressedBackground = UIManager.getColor("Button.default.pressedBackground");
         this.defaultPressedForeground = UIManager.getColor("Button.default.pressedForeground");
         this.defaultBoldText = UIManager.getBoolean("Button.default.boldText");
         this.paintShadow = UIManager.getBoolean("Button.paintShadow");
         this.shadowWidth = FlatUIUtils.getUIInt("Button.shadowWidth", 2);
         this.shadowColor = UIManager.getColor("Button.shadowColor");
         this.defaultShadowColor = UIManager.getColor("Button.default.shadowColor");
         this.toolbarHoverBackground = UIManager.getColor(prefix + "toolbar.hoverBackground");
         this.toolbarHoverForeground = UIManager.getColor(prefix + "toolbar.hoverForeground");
         this.toolbarPressedBackground = UIManager.getColor(prefix + "toolbar.pressedBackground");
         this.toolbarPressedForeground = UIManager.getColor(prefix + "toolbar.pressedForeground");
         this.toolbarSelectedBackground = UIManager.getColor(prefix + "toolbar.selectedBackground");
         this.toolbarSelectedForeground = UIManager.getColor(prefix + "toolbar.selectedForeground");
         this.toolbarDisabledSelectedBackground = UIManager.getColor(prefix + "toolbar.disabledSelectedBackground");
         this.toolbarDisabledSelectedForeground = UIManager.getColor(prefix + "toolbar.disabledSelectedForeground");
         this.helpButtonIcon = UIManager.getIcon("HelpButton.icon");
         this.defaultMargin = UIManager.getInsets(prefix + "margin");
         this.helpButtonIconShared = true;
         this.defaults_initialized = true;
      }

      if (this.startBackground != null) {
         Color bg = b.getBackground();
         if (bg == null || bg instanceof UIResource) {
            b.setBackground(this.startBackground);
         }
      }

      LookAndFeel.installProperty(b, "opaque", false);
      LookAndFeel.installProperty(b, "iconTextGap", UIScale.scale(this.iconTextGap));
      MigLayoutVisualPadding.install(b);
   }

   protected void uninstallDefaults(AbstractButton b) {
      super.uninstallDefaults(b);
      this.oldStyleValues = null;
      this.borderShared = null;
      MigLayoutVisualPadding.uninstall(b);
      this.defaults_initialized = false;
   }

   protected BasicButtonListener createButtonListener(AbstractButton b) {
      return new FlatButtonUI.FlatButtonListener(b);
   }

   protected void propertyChange(AbstractButton b, PropertyChangeEvent e) {
      String var3 = e.getPropertyName();
      byte var4 = -1;
      switch(var3.hashCode()) {
      case -1302441837:
         if (var3.equals("JComponent.minimumWidth")) {
            var4 = 1;
         }
         break;
      case -1134471216:
         if (var3.equals("JButton.squareSize")) {
            var4 = 0;
         }
         break;
      case -691370713:
         if (var3.equals("JComponent.outline")) {
            var4 = 4;
         }
         break;
      case 1030195901:
         if (var3.equals("FlatLaf.styleClass")) {
            var4 = 6;
         }
         break;
      case 1428734622:
         if (var3.equals("JButton.buttonType")) {
            var4 = 3;
         }
         break;
      case 1545413499:
         if (var3.equals("FlatLaf.style")) {
            var4 = 5;
         }
         break;
      case 2140981242:
         if (var3.equals("JComponent.minimumHeight")) {
            var4 = 2;
         }
      }

      switch(var4) {
      case 0:
      case 1:
      case 2:
         b.revalidate();
         break;
      case 3:
         b.revalidate();
         b.repaint();
         break;
      case 4:
         b.repaint();
         break;
      case 5:
      case 6:
         if (this.shared && FlatStylingSupport.hasStyleProperty(b)) {
            b.updateUI();
         } else {
            this.installStyle(b);
         }

         b.revalidate();
         b.repaint();
      }

   }

   protected void installStyle(AbstractButton b) {
      try {
         this.applyStyle(b, FlatStylingSupport.getResolvedStyle(b, this.getStyleType()));
      } catch (RuntimeException var3) {
         LoggingFacade.INSTANCE.logSevere((String)null, var3);
      }

   }

   String getStyleType() {
      return "Button";
   }

   protected void applyStyle(AbstractButton b, Object style) {
      this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, (key, value) -> {
         return this.applyStyleProperty(b, key, value);
      });
   }

   protected Object applyStyleProperty(AbstractButton b, String key, Object value) {
      if (key.startsWith("help.")) {
         if (!(this.helpButtonIcon instanceof FlatHelpButtonIcon)) {
            return new FlatStylingSupport.UnknownStyleException(key);
         } else {
            if (this.helpButtonIconShared) {
               this.helpButtonIcon = FlatStylingSupport.cloneIcon(this.helpButtonIcon);
               this.helpButtonIconShared = false;
            }

            key = key.substring("help.".length());
            return ((FlatHelpButtonIcon)this.helpButtonIcon).applyStyleProperty(key, value);
         }
      } else {
         if (this.borderShared == null) {
            this.borderShared = new AtomicBoolean(true);
         }

         return FlatStylingSupport.applyToAnnotatedObjectOrBorder(this, key, value, b, this.borderShared);
      }
   }

   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
      Map<String, Class<?>> infos = FlatStylingSupport.getAnnotatedStyleableInfos(this, c.getBorder());
      if (this.helpButtonIcon instanceof FlatHelpButtonIcon) {
         FlatStylingSupport.putAllPrefixKey(infos, "help.", ((FlatHelpButtonIcon)this.helpButtonIcon).getStyleableInfos());
      }

      return infos;
   }

   public Object getStyleableValue(JComponent c, String key) {
      if (key.startsWith("help.")) {
         return this.helpButtonIcon instanceof FlatHelpButtonIcon ? ((FlatHelpButtonIcon)this.helpButtonIcon).getStyleableValue(key.substring("help.".length())) : null;
      } else {
         return FlatStylingSupport.getAnnotatedStyleableValue(this, c.getBorder(), key);
      }
   }

   static boolean isContentAreaFilled(Component c) {
      return !(c instanceof AbstractButton) || ((AbstractButton)c).isContentAreaFilled();
   }

   public static boolean isFocusPainted(Component c) {
      return !(c instanceof AbstractButton) || ((AbstractButton)c).isFocusPainted();
   }

   static boolean isDefaultButton(Component c) {
      return c instanceof JButton && ((JButton)c).isDefaultButton();
   }

   static boolean isIconOnlyOrSingleCharacterButton(Component c) {
      if (!(c instanceof JButton) && !(c instanceof JToggleButton)) {
         return false;
      } else {
         Icon icon = ((AbstractButton)c).getIcon();
         String text = ((AbstractButton)c).getText();
         return icon != null && (text == null || text.isEmpty()) || icon == null && text != null && ("...".equals(text) || text.length() == 1 || text.length() == 2 && Character.isSurrogatePair(text.charAt(0), text.charAt(1)));
      }
   }

   static int getButtonType(Component c) {
      if (!(c instanceof AbstractButton)) {
         return -1;
      } else {
         String value = getButtonTypeStr((AbstractButton)c);
         if (value == null) {
            return -1;
         } else {
            byte var3 = -1;
            switch(value.hashCode()) {
            case -894674659:
               if (value.equals("square")) {
                  var3 = 0;
               }
               break;
            case -5109614:
               if (value.equals("roundRect")) {
                  var3 = 1;
               }
            }

            switch(var3) {
            case 0:
               return 0;
            case 1:
               return 1;
            default:
               return -1;
            }
         }
      }
   }

   static boolean isHelpButton(Component c) {
      return c instanceof JButton && "help".equals(getButtonTypeStr((JButton)c));
   }

   static boolean isToolBarButton(Component c) {
      return c.getParent() instanceof JToolBar || c instanceof AbstractButton && "toolBarButton".equals(getButtonTypeStr((AbstractButton)c));
   }

   static boolean isBorderlessButton(Component c) {
      return c instanceof AbstractButton && "borderless".equals(getButtonTypeStr((AbstractButton)c));
   }

   static String getButtonTypeStr(AbstractButton c) {
      Object value = c.getClientProperty("JButton.buttonType");
      if (value instanceof String) {
         return (String)value;
      } else {
         ButtonUI ui = c.getUI();
         return ui instanceof FlatButtonUI ? ((FlatButtonUI)ui).buttonType : null;
      }
   }

   public void update(Graphics g, JComponent c) {
      if (c.isOpaque()) {
         FlatUIUtils.paintParentBackground(g, c);
      }

      if (isHelpButton(c)) {
         this.helpButtonIcon.paintIcon(c, g, 0, 0);
      } else {
         if (isContentAreaFilled(c)) {
            this.paintBackground(g, c);
         }

         this.paint(g, c);
      }
   }

   protected void paintBackground(Graphics g, JComponent c) {
      Color background = this.getBackground(c);
      if (background != null) {
         Graphics2D g2 = (Graphics2D)g.create();

         try {
            FlatUIUtils.setRenderingHints(g2);
            boolean def = isDefaultButton(c);
            boolean isToolBarButton = isToolBarButton(c);
            float focusWidth = isToolBarButton ? 0.0F : FlatUIUtils.getBorderFocusWidth(c);
            float arc = FlatUIUtils.getBorderArc(c);
            float textFieldArc = 0.0F;
            if (isToolBarButton && ((String)FlatClientProperties.clientProperty(c, "FlatLaf.styleClass", "", String.class)).contains("inTextField")) {
               JTextField textField = (JTextField)SwingUtilities.getAncestorOfClass(JTextField.class, c);
               if (textField != null) {
                  textFieldArc = FlatUIUtils.getBorderArc(textField);
               }
            }

            int x = 0;
            int y = 0;
            int width = c.getWidth();
            int height = c.getHeight();
            if (isToolBarButton && c.getBorder() instanceof FlatButtonBorder) {
               Insets spacing = UIScale.scale(((FlatButtonBorder)c.getBorder()).toolbarSpacingInsets);
               x += spacing.left;
               y += spacing.top;
               width -= spacing.left + spacing.right;
               height -= spacing.top + spacing.bottom;
               textFieldArc -= (float)(spacing.top + spacing.bottom);
            }

            if (arc < textFieldArc) {
               arc = textFieldArc;
            }

            Color shadowColor = def ? this.defaultShadowColor : this.shadowColor;
            if (this.paintShadow && shadowColor != null && this.shadowWidth > 0 && focusWidth > 0.0F && c.isEnabled() && !isToolBarButton && !isBorderlessButton(c) && (!isFocusPainted(c) || !FlatUIUtils.isPermanentFocusOwner(c))) {
               g2.setColor(shadowColor);
               g2.fill(new Float(focusWidth, focusWidth + UIScale.scale((float)this.shadowWidth), (float)width - focusWidth * 2.0F, (float)height - focusWidth * 2.0F, arc, arc));
            }

            Color startBg = def ? this.defaultBackground : this.startBackground;
            Color endBg = def ? this.defaultEndBackground : this.endBackground;
            if (background == startBg && endBg != null && !startBg.equals(endBg)) {
               g2.setPaint(new GradientPaint(0.0F, 0.0F, startBg, 0.0F, (float)height, endBg));
            } else {
               g2.setColor(FlatUIUtils.deriveColor(background, this.getBackgroundBase(c, def)));
            }

            FlatUIUtils.paintComponentBackground(g2, x, y, width, height, focusWidth, arc);
         } finally {
            g2.dispose();
         }

      }
   }

   public void paint(Graphics g, JComponent c) {
      super.paint(FlatLabelUI.createGraphicsHTMLTextYCorrection(g, c), c);
   }

   protected void paintIcon(Graphics g, JComponent c, Rectangle iconRect) {
      int xOffset = this.defaultBoldPlainWidthDiff(c) / 2;
      if (xOffset > 0) {
         boolean ltr = c.getComponentOrientation().isLeftToRight();
         switch(((AbstractButton)c).getHorizontalTextPosition()) {
         case 2:
            iconRect.x += xOffset;
         case 3:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         default:
            break;
         case 4:
            iconRect.x -= xOffset;
            break;
         case 10:
            iconRect.x += ltr ? xOffset : -xOffset;
            break;
         case 11:
            iconRect.x -= ltr ? xOffset : -xOffset;
         }
      }

      super.paintIcon(g, c, iconRect);
   }

   protected void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text) {
      if (!isHelpButton(b)) {
         if (this.defaultBoldText && isDefaultButton(b) && b.getFont() instanceof UIResource) {
            Font boldFont = g.getFont().deriveFont(1);
            g.setFont(boldFont);
            int boldWidth = b.getFontMetrics(boldFont).stringWidth(text);
            if (boldWidth > textRect.width) {
               textRect.x -= (boldWidth - textRect.width) / 2;
               textRect.width = boldWidth;
            }
         }

         paintText(g, b, textRect, text, this.getForeground(b));
      }
   }

   public static void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text, Color foreground) {
      if (foreground == null) {
         foreground = Color.red;
      }

      FontMetrics fm = b.getFontMetrics(b.getFont());
      int mnemonicIndex = FlatLaf.isShowMnemonics() ? b.getDisplayedMnemonicIndex() : -1;
      g.setColor(foreground);
      FlatUIUtils.drawStringUnderlineCharAt(b, g, text, mnemonicIndex, textRect.x, textRect.y + fm.getAscent());
   }

   protected Color getBackground(JComponent c) {
      boolean toolBarButton = isToolBarButton(c) || isBorderlessButton(c);
      if (((AbstractButton)c).isSelected()) {
         return buttonStateColor(c, toolBarButton ? this.toolbarSelectedBackground : this.selectedBackground, toolBarButton ? (this.toolbarDisabledSelectedBackground != null ? this.toolbarDisabledSelectedBackground : this.toolbarSelectedBackground) : this.disabledSelectedBackground, (Color)null, (Color)null, toolBarButton ? this.toolbarPressedBackground : this.pressedBackground);
      } else if (toolBarButton) {
         Color bg = c.getBackground();
         return buttonStateColor(c, this.isCustomBackground(bg) ? bg : null, (Color)null, (Color)null, this.toolbarHoverBackground, this.toolbarPressedBackground);
      } else {
         boolean def = isDefaultButton(c);
         return buttonStateColor(c, this.getBackgroundBase(c, def), this.disabledBackground, this.isCustomBackground(c.getBackground()) ? null : (def ? this.defaultFocusedBackground : this.focusedBackground), def ? this.defaultHoverBackground : this.hoverBackground, def ? this.defaultPressedBackground : this.pressedBackground);
      }
   }

   protected Color getBackgroundBase(JComponent c, boolean def) {
      if (FlatUIUtils.isAWTPeer(c)) {
         return this.background;
      } else {
         Color bg = c.getBackground();
         if (this.isCustomBackground(bg)) {
            return bg;
         } else {
            return def ? this.defaultBackground : bg;
         }
      }
   }

   protected boolean isCustomBackground(Color bg) {
      return bg != this.background && (this.startBackground == null || bg != this.startBackground);
   }

   public static Color buttonStateColor(Component c, Color enabledColor, Color disabledColor, Color focusedColor, Color hoverColor, Color pressedColor) {
      if (c == null) {
         return enabledColor;
      } else if (!c.isEnabled()) {
         return disabledColor;
      } else {
         if (c instanceof AbstractButton) {
            ButtonModel model = ((AbstractButton)c).getModel();
            if (pressedColor != null && model.isPressed()) {
               return pressedColor;
            }

            if (hoverColor != null && model.isRollover()) {
               return hoverColor;
            }
         }

         return focusedColor != null && isFocusPainted(c) && FlatUIUtils.isPermanentFocusOwner(c) ? focusedColor : enabledColor;
      }
   }

   protected Color getForeground(JComponent c) {
      boolean toolBarButton = isToolBarButton(c) || isBorderlessButton(c);
      if (((AbstractButton)c).isSelected()) {
         return buttonStateColor(c, toolBarButton ? (this.toolbarSelectedForeground != null ? this.toolbarSelectedForeground : c.getForeground()) : this.selectedForeground, toolBarButton ? (this.toolbarDisabledSelectedForeground != null ? this.toolbarDisabledSelectedForeground : this.disabledText) : (this.disabledSelectedForeground != null ? this.disabledSelectedForeground : this.disabledText), (Color)null, (Color)null, toolBarButton ? this.toolbarPressedForeground : this.pressedForeground);
      } else if (toolBarButton) {
         return buttonStateColor(c, c.getForeground(), this.disabledText, (Color)null, this.toolbarHoverForeground, this.toolbarPressedForeground);
      } else {
         boolean def = isDefaultButton(c);
         return buttonStateColor(c, this.getForegroundBase(c, def), this.disabledText, this.isCustomForeground(c.getForeground()) ? null : (def ? this.defaultFocusedForeground : this.focusedForeground), def ? this.defaultHoverForeground : this.hoverForeground, def ? this.defaultPressedForeground : this.pressedForeground);
      }
   }

   protected Color getForegroundBase(JComponent c, boolean def) {
      Color fg = c.getForeground();
      if (this.isCustomForeground(fg)) {
         return fg;
      } else {
         return def ? this.defaultForeground : fg;
      }
   }

   protected boolean isCustomForeground(Color fg) {
      return fg != this.foreground;
   }

   public Dimension getPreferredSize(JComponent c) {
      if (isHelpButton(c)) {
         return new Dimension(this.helpButtonIcon.getIconWidth(), this.helpButtonIcon.getIconHeight());
      } else {
         Dimension prefSize = super.getPreferredSize(c);
         if (prefSize == null) {
            return null;
         } else {
            prefSize.width += this.defaultBoldPlainWidthDiff(c);
            boolean isIconOnlyOrSingleCharacter = isIconOnlyOrSingleCharacterButton(c);
            if (FlatClientProperties.clientPropertyBoolean(c, "JButton.squareSize", this.squareSize)) {
               prefSize.width = prefSize.height = Math.max(prefSize.width, prefSize.height);
            } else if (isIconOnlyOrSingleCharacter && ((AbstractButton)c).getIcon() == null) {
               prefSize.width = Math.max(prefSize.width, prefSize.height);
            } else if (!isIconOnlyOrSingleCharacter && !isToolBarButton(c) && c.getBorder() instanceof FlatButtonBorder && this.hasDefaultMargins(c)) {
               int fw = Math.round(FlatUIUtils.getBorderFocusWidth(c) * 2.0F);
               prefSize.width = Math.max(prefSize.width, UIScale.scale(FlatUIUtils.minimumWidth(c, this.minimumWidth)) + fw);
               prefSize.height = Math.max(prefSize.height, UIScale.scale(FlatUIUtils.minimumHeight(c, this.minimumHeight)) + fw);
            }

            return prefSize;
         }
      }
   }

   private int defaultBoldPlainWidthDiff(JComponent c) {
      if (this.defaultBoldText && isDefaultButton(c) && c.getFont() instanceof UIResource) {
         String text = ((AbstractButton)c).getText();
         if (text == null || text.isEmpty()) {
            return 0;
         }

         Font font = c.getFont();
         Font boldFont = font.deriveFont(1);
         int boldWidth = c.getFontMetrics(boldFont).stringWidth(text);
         int plainWidth = c.getFontMetrics(font).stringWidth(text);
         if (boldWidth > plainWidth) {
            return boldWidth - plainWidth;
         }
      }

      return 0;
   }

   private boolean hasDefaultMargins(JComponent c) {
      Insets margin = ((AbstractButton)c).getMargin();
      return margin instanceof UIResource && Objects.equals(margin, this.defaultMargin);
   }

   protected class FlatButtonListener extends BasicButtonListener {
      private final AbstractButton b;

      protected FlatButtonListener(AbstractButton b) {
         super(b);
         this.b = b;
      }

      public void propertyChange(PropertyChangeEvent e) {
         super.propertyChange(e);
         FlatButtonUI.this.propertyChange(this.b, e);
      }

      public void stateChanged(ChangeEvent e) {
         super.stateChanged(e);
         AbstractButton b = (AbstractButton)e.getSource();
         Container parent = b.getParent();
         if (parent instanceof JToolBar) {
            JToolBar toolBar = (JToolBar)parent;
            ToolBarUI ui = toolBar.getUI();
            if (ui instanceof FlatToolBarUI) {
               ((FlatToolBarUI)ui).repaintButtonGroup(b);
            }
         }

      }
   }
}
