package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Rectangle2D.Float;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicSpinnerUI;

public class FlatSpinnerUI extends BasicSpinnerUI implements FlatStylingSupport.StyleableUI {
   private FlatSpinnerUI.Handler handler;
   @FlatStylingSupport.Styleable
   protected int minimumWidth;
   @FlatStylingSupport.Styleable
   protected String buttonStyle;
   @FlatStylingSupport.Styleable
   protected String arrowType;
   protected boolean isIntelliJTheme;
   @FlatStylingSupport.Styleable
   protected Color disabledBackground;
   @FlatStylingSupport.Styleable
   protected Color disabledForeground;
   @FlatStylingSupport.Styleable
   protected Color focusedBackground;
   @FlatStylingSupport.Styleable
   protected Color buttonBackground;
   @FlatStylingSupport.Styleable
   protected float buttonSeparatorWidth;
   @FlatStylingSupport.Styleable
   protected Color buttonSeparatorColor;
   @FlatStylingSupport.Styleable
   protected Color buttonDisabledSeparatorColor;
   @FlatStylingSupport.Styleable
   protected Color buttonArrowColor;
   @FlatStylingSupport.Styleable
   protected Color buttonDisabledArrowColor;
   @FlatStylingSupport.Styleable
   protected Color buttonHoverArrowColor;
   @FlatStylingSupport.Styleable
   protected Color buttonPressedArrowColor;
   @FlatStylingSupport.Styleable
   protected Insets padding;
   private Map<String, Object> oldStyleValues;
   private AtomicBoolean borderShared;
   private static final int MAC_STEPPER_WIDTH = 15;
   private static final int MAC_STEPPER_GAP = 3;

   public static ComponentUI createUI(JComponent c) {
      return new FlatSpinnerUI();
   }

   public void installUI(JComponent c) {
      super.installUI(c);
      this.installStyle();
   }

   protected void installDefaults() {
      super.installDefaults();
      LookAndFeel.installProperty(this.spinner, "opaque", false);
      this.minimumWidth = UIManager.getInt("Component.minimumWidth");
      this.buttonStyle = UIManager.getString("Spinner.buttonStyle");
      this.arrowType = UIManager.getString("Component.arrowType");
      this.isIntelliJTheme = UIManager.getBoolean("Component.isIntelliJTheme");
      this.disabledBackground = UIManager.getColor("Spinner.disabledBackground");
      this.disabledForeground = UIManager.getColor("Spinner.disabledForeground");
      this.focusedBackground = UIManager.getColor("Spinner.focusedBackground");
      this.buttonBackground = UIManager.getColor("Spinner.buttonBackground");
      this.buttonSeparatorWidth = FlatUIUtils.getUIFloat("Spinner.buttonSeparatorWidth", FlatUIUtils.getUIFloat("Component.borderWidth", 1.0F));
      this.buttonSeparatorColor = UIManager.getColor("Spinner.buttonSeparatorColor");
      this.buttonDisabledSeparatorColor = UIManager.getColor("Spinner.buttonDisabledSeparatorColor");
      this.buttonArrowColor = UIManager.getColor("Spinner.buttonArrowColor");
      this.buttonDisabledArrowColor = UIManager.getColor("Spinner.buttonDisabledArrowColor");
      this.buttonHoverArrowColor = UIManager.getColor("Spinner.buttonHoverArrowColor");
      this.buttonPressedArrowColor = UIManager.getColor("Spinner.buttonPressedArrowColor");
      this.padding = UIManager.getInsets("Spinner.padding");
      MigLayoutVisualPadding.install(this.spinner);
   }

   protected void uninstallDefaults() {
      super.uninstallDefaults();
      this.disabledBackground = null;
      this.disabledForeground = null;
      this.focusedBackground = null;
      this.buttonBackground = null;
      this.buttonSeparatorColor = null;
      this.buttonDisabledSeparatorColor = null;
      this.buttonArrowColor = null;
      this.buttonDisabledArrowColor = null;
      this.buttonHoverArrowColor = null;
      this.buttonPressedArrowColor = null;
      this.padding = null;
      this.oldStyleValues = null;
      this.borderShared = null;
      MigLayoutVisualPadding.uninstall(this.spinner);
   }

   protected void installListeners() {
      super.installListeners();
      this.addEditorFocusListener(this.spinner.getEditor());
      this.spinner.addFocusListener(this.getHandler());
      this.spinner.addPropertyChangeListener(this.getHandler());
   }

   protected void uninstallListeners() {
      super.uninstallListeners();
      this.removeEditorFocusListener(this.spinner.getEditor());
      this.spinner.removeFocusListener(this.getHandler());
      this.spinner.removePropertyChangeListener(this.getHandler());
      this.handler = null;
   }

   private FlatSpinnerUI.Handler getHandler() {
      if (this.handler == null) {
         this.handler = new FlatSpinnerUI.Handler();
      }

      return this.handler;
   }

   protected void installStyle() {
      try {
         this.applyStyle(FlatStylingSupport.getResolvedStyle(this.spinner, "Spinner"));
      } catch (RuntimeException var2) {
         LoggingFacade.INSTANCE.logSevere((String)null, var2);
      }

   }

   protected void applyStyle(Object style) {
      this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
      this.updateEditorPadding();
      this.updateArrowButtonsStyle();
   }

   protected Object applyStyleProperty(String key, Object value) {
      if (this.borderShared == null) {
         this.borderShared = new AtomicBoolean(true);
      }

      return FlatStylingSupport.applyToAnnotatedObjectOrBorder(this, key, value, this.spinner, this.borderShared);
   }

   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
      return FlatStylingSupport.getAnnotatedStyleableInfos(this, this.spinner.getBorder());
   }

   public Object getStyleableValue(JComponent c, String key) {
      return FlatStylingSupport.getAnnotatedStyleableValue(this, this.spinner.getBorder(), key);
   }

   protected JComponent createEditor() {
      JComponent editor = super.createEditor();
      this.configureEditor(editor);
      return editor;
   }

   protected void replaceEditor(JComponent oldEditor, JComponent newEditor) {
      super.replaceEditor(oldEditor, newEditor);
      this.configureEditor(newEditor);
      this.removeEditorFocusListener(oldEditor);
      this.addEditorFocusListener(newEditor);
   }

   protected void configureEditor(JComponent editor) {
      editor.setOpaque(false);
      JTextField textField = getEditorTextField(editor);
      if (textField != null) {
         textField.setOpaque(false);
      }

      this.updateEditorPadding();
      this.updateEditorColors();
   }

   private void addEditorFocusListener(JComponent editor) {
      JTextField textField = getEditorTextField(editor);
      if (textField != null) {
         textField.addFocusListener(this.getHandler());
      }

   }

   private void removeEditorFocusListener(JComponent editor) {
      JTextField textField = getEditorTextField(editor);
      if (textField != null) {
         textField.removeFocusListener(this.getHandler());
      }

   }

   private void updateEditorPadding() {
      JTextField textField = getEditorTextField(this.spinner.getEditor());
      if (textField != null) {
         textField.putClientProperty("JTextField.padding", this.padding);
      }

   }

   private void updateEditorColors() {
      JTextField textField = getEditorTextField(this.spinner.getEditor());
      if (textField != null) {
         textField.setForeground(FlatUIUtils.nonUIResource(this.getForeground(true)));
         textField.setDisabledTextColor(FlatUIUtils.nonUIResource(this.getForeground(false)));
      }

   }

   private static JTextField getEditorTextField(JComponent editor) {
      return editor instanceof DefaultEditor ? ((DefaultEditor)editor).getTextField() : null;
   }

   public static boolean isPermanentFocusOwner(JSpinner spinner) {
      if (FlatUIUtils.isPermanentFocusOwner(spinner)) {
         return true;
      } else {
         JTextField textField = getEditorTextField(spinner.getEditor());
         return textField != null && FlatUIUtils.isPermanentFocusOwner(textField);
      }
   }

   protected Color getBackground(boolean enabled) {
      if (enabled) {
         Color background = this.spinner.getBackground();
         if (!(background instanceof UIResource)) {
            return background;
         } else {
            return this.focusedBackground != null && isPermanentFocusOwner(this.spinner) ? this.focusedBackground : background;
         }
      } else {
         return this.isIntelliJTheme ? FlatUIUtils.getParentBackground(this.spinner) : this.disabledBackground;
      }
   }

   protected Color getForeground(boolean enabled) {
      return enabled ? this.spinner.getForeground() : this.disabledForeground;
   }

   protected LayoutManager createLayout() {
      return this.getHandler();
   }

   protected Component createNextButton() {
      return this.createArrowButton(1, "Spinner.nextButton");
   }

   protected Component createPreviousButton() {
      return this.createArrowButton(5, "Spinner.previousButton");
   }

   private Component createArrowButton(int direction, String name) {
      FlatArrowButton button = new FlatArrowButton(direction, this.arrowType, this.buttonArrowColor, this.buttonDisabledArrowColor, this.buttonHoverArrowColor, (Color)null, this.buttonPressedArrowColor, (Color)null) {
         public int getArrowWidth() {
            return FlatSpinnerUI.this.isMacStyle() ? 7 : super.getArrowWidth();
         }

         public float getArrowThickness() {
            return FlatSpinnerUI.this.isMacStyle() ? 1.5F : super.getArrowThickness();
         }

         public float getYOffset() {
            return FlatSpinnerUI.this.isMacStyle() ? 0.0F : super.getYOffset();
         }

         public boolean isRoundBorderAutoXOffset() {
            return FlatSpinnerUI.this.isMacStyle() ? false : super.isRoundBorderAutoXOffset();
         }
      };
      button.setName(name);
      button.setYOffset(direction == 1 ? 1.25F : -1.25F);
      if (direction == 1) {
         this.installNextButtonListeners(button);
      } else {
         this.installPreviousButtonListeners(button);
      }

      return button;
   }

   private void updateArrowButtonsStyle() {
      Component[] var1 = this.spinner.getComponents();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Component c = var1[var3];
         if (c instanceof FlatArrowButton) {
            ((FlatArrowButton)c).updateStyle(this.arrowType, this.buttonArrowColor, this.buttonDisabledArrowColor, this.buttonHoverArrowColor, (Color)null, this.buttonPressedArrowColor, (Color)null);
         }
      }

   }

   public void update(Graphics g, JComponent c) {
      float focusWidth = FlatUIUtils.getBorderFocusWidth(c);
      float arc = FlatUIUtils.getBorderArc(c);
      if (c.isOpaque() && (focusWidth > 0.0F || arc > 0.0F)) {
         FlatUIUtils.paintParentBackground(g, c);
      }

      Graphics2D g2 = (Graphics2D)g;
      Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g2);
      int width = c.getWidth();
      int height = c.getHeight();
      boolean enabled = this.spinner.isEnabled();
      boolean ltr = this.spinner.getComponentOrientation().isLeftToRight();
      boolean isMacStyle = this.isMacStyle();
      int macStyleButtonsWidth = isMacStyle ? this.getMacStyleButtonsWidth() : 0;
      g2.setColor(this.getBackground(enabled));
      FlatUIUtils.paintComponentBackground(g2, ltr ? 0 : macStyleButtonsWidth, 0, width - macStyleButtonsWidth, height, focusWidth, arc);
      boolean paintButton = !"none".equals(this.buttonStyle);
      FlatSpinnerUI.Handler handler = this.getHandler();
      if (paintButton && (handler.nextButton != null || handler.previousButton != null)) {
         Component button = handler.nextButton != null ? handler.nextButton : handler.previousButton;
         int arrowX = button.getX();
         int arrowWidth = button.getWidth();
         Color separatorColor = enabled ? this.buttonSeparatorColor : this.buttonDisabledSeparatorColor;
         if (isMacStyle) {
            Insets insets = this.spinner.getInsets();
            int lineWidth = Math.round(FlatUIUtils.getBorderLineWidth(this.spinner));
            int by = insets.top - lineWidth;
            int bh = height - insets.top - insets.bottom + lineWidth * 2;
            float lw = UIScale.scale(this.buttonSeparatorWidth);
            FlatUIUtils.paintOutlinedComponent(g2, arrowX, by, arrowWidth, bh, 0.0F, 0.0F, 0.0F, lw, (float)UIScale.scale(12), (Paint)null, separatorColor, this.buttonBackground);
            if (separatorColor != null) {
               int thickness = UIScale.scale(1);
               g2.setColor(separatorColor);
               g2.fill(new Float((float)arrowX + lw, (float)by + (float)(bh - thickness) / 2.0F, (float)arrowWidth - lw * 2.0F, (float)thickness));
            }
         } else {
            if (enabled && this.buttonBackground != null) {
               g2.setColor(this.buttonBackground);
               Shape oldClip = g2.getClip();
               if (ltr) {
                  g2.clipRect(arrowX, 0, width - arrowX, height);
               } else {
                  g2.clipRect(0, 0, arrowX + arrowWidth, height);
               }

               FlatUIUtils.paintComponentBackground(g2, 0, 0, width, height, focusWidth, arc);
               g2.setClip(oldClip);
            }

            if (separatorColor != null && this.buttonSeparatorWidth > 0.0F) {
               g2.setColor(separatorColor);
               float lw = UIScale.scale(this.buttonSeparatorWidth);
               float lx = ltr ? (float)arrowX : (float)(arrowX + arrowWidth) - lw;
               g2.fill(new Float(lx, focusWidth, lw, (float)(height - 1) - focusWidth * 2.0F));
            }
         }
      }

      this.paint(g, c);
      FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
   }

   boolean isMacStyle() {
      return "mac".equals(this.buttonStyle);
   }

   int getMacStyleButtonsWidth() {
      return this.handler.nextButton == null && this.handler.previousButton == null ? 0 : UIScale.scale(3) + UIScale.scale(15);
   }

   private class Handler implements LayoutManager, FocusListener, PropertyChangeListener {
      private Component editor;
      private Component nextButton;
      private Component previousButton;

      private Handler() {
         this.editor = null;
      }

      public void addLayoutComponent(String name, Component c) {
         byte var4 = -1;
         switch(name.hashCode()) {
         case -1209131241:
            if (name.equals("Previous")) {
               var4 = 2;
            }
            break;
         case 2424595:
            if (name.equals("Next")) {
               var4 = 1;
            }
            break;
         case 2071006605:
            if (name.equals("Editor")) {
               var4 = 0;
            }
         }

         switch(var4) {
         case 0:
            this.editor = c;
            break;
         case 1:
            this.nextButton = c;
            break;
         case 2:
            this.previousButton = c;
         }

      }

      public void removeLayoutComponent(Component c) {
         if (c == this.editor) {
            this.editor = null;
         } else if (c == this.nextButton) {
            this.nextButton = null;
         } else if (c == this.previousButton) {
            this.previousButton = null;
         }

      }

      public Dimension preferredLayoutSize(Container parent) {
         Insets insets = parent.getInsets();
         Insets padding = UIScale.scale(FlatSpinnerUI.this.padding);
         Dimension editorSize = this.editor != null ? this.editor.getPreferredSize() : new Dimension(0, 0);
         int minimumWidth = FlatUIUtils.minimumWidth(FlatSpinnerUI.this.spinner, FlatSpinnerUI.this.minimumWidth);
         int innerHeight = editorSize.height + padding.top + padding.bottom;
         float focusWidth = FlatUIUtils.getBorderFocusWidth(FlatSpinnerUI.this.spinner);
         return new Dimension(Math.max(insets.left + insets.right + editorSize.width + padding.left + padding.right + innerHeight, UIScale.scale(minimumWidth) + Math.round(focusWidth * 2.0F)), insets.top + insets.bottom + innerHeight);
      }

      public Dimension minimumLayoutSize(Container parent) {
         return this.preferredLayoutSize(parent);
      }

      public void layoutContainer(Container parent) {
         Dimension size = parent.getSize();
         Insets insets = parent.getInsets();
         Rectangle r = FlatUIUtils.subtractInsets(new Rectangle(size), insets);
         if (this.nextButton == null && this.previousButton == null) {
            if (this.editor != null) {
               this.editor.setBounds(r);
            }

         } else {
            Rectangle editorRect = new Rectangle(r);
            Rectangle buttonsRect = new Rectangle(r);
            FontMetrics fm = FlatSpinnerUI.this.spinner.getFontMetrics(FlatSpinnerUI.this.spinner.getFont());
            int maxButtonWidth = fm.getHeight() + UIScale.scale(FlatSpinnerUI.this.padding.top) + UIScale.scale(FlatSpinnerUI.this.padding.bottom);
            int minButtonWidth = maxButtonWidth * 3 / 4;
            boolean isMacStyle = FlatSpinnerUI.this.isMacStyle();
            int buttonsGap = isMacStyle ? UIScale.scale(3) : 0;
            int prefButtonWidth = isMacStyle ? UIScale.scale(15) : buttonsRect.height;
            int buttonsWidth = Math.min(Math.max(prefButtonWidth, minButtonWidth), maxButtonWidth);
            buttonsRect.width = buttonsWidth;
            editorRect.width -= buttonsWidth + buttonsGap;
            boolean ltr = parent.getComponentOrientation().isLeftToRight();
            if (ltr) {
               buttonsRect.x += editorRect.width + buttonsGap;
            } else {
               editorRect.x += buttonsWidth + buttonsGap;
            }

            int nextHeight;
            if (isMacStyle) {
               nextHeight = Math.round(FlatUIUtils.getBorderLineWidth(FlatSpinnerUI.this.spinner));
               if (nextHeight > 0) {
                  buttonsRect.x += ltr ? nextHeight : -nextHeight;
                  buttonsRect.y -= nextHeight;
                  buttonsRect.height += nextHeight * 2;
               }
            }

            if (this.editor != null) {
               this.editor.setBounds(editorRect);
            }

            nextHeight = buttonsRect.height / 2 + buttonsRect.height % 2;
            if (this.nextButton != null) {
               this.nextButton.setBounds(buttonsRect.x, buttonsRect.y, buttonsRect.width, nextHeight);
            }

            if (this.previousButton != null) {
               int previousY = buttonsRect.y + buttonsRect.height - nextHeight;
               this.previousButton.setBounds(buttonsRect.x, previousY, buttonsRect.width, nextHeight);
            }

         }
      }

      public void focusGained(FocusEvent e) {
         FlatSpinnerUI.this.spinner.repaint();
         if (e.getComponent() == FlatSpinnerUI.this.spinner) {
            JTextField textField = FlatSpinnerUI.getEditorTextField(FlatSpinnerUI.this.spinner.getEditor());
            if (textField != null) {
               textField.requestFocusInWindow();
            }
         }

      }

      public void focusLost(FocusEvent e) {
         FlatSpinnerUI.this.spinner.repaint();
      }

      public void propertyChange(PropertyChangeEvent e) {
         String var2 = e.getPropertyName();
         byte var3 = -1;
         switch(var2.hashCode()) {
         case -1609594047:
            if (var2.equals("enabled")) {
               var3 = 1;
            }
            break;
         case -1302441837:
            if (var2.equals("JComponent.minimumWidth")) {
               var3 = 4;
            }
            break;
         case -742334409:
            if (var2.equals("JComponent.roundRect")) {
               var3 = 2;
            }
            break;
         case -691370713:
            if (var2.equals("JComponent.outline")) {
               var3 = 3;
            }
            break;
         case 1030195901:
            if (var2.equals("FlatLaf.styleClass")) {
               var3 = 6;
            }
            break;
         case 1545413499:
            if (var2.equals("FlatLaf.style")) {
               var3 = 5;
            }
            break;
         case 1984457027:
            if (var2.equals("foreground")) {
               var3 = 0;
            }
         }

         switch(var3) {
         case 0:
         case 1:
            FlatSpinnerUI.this.updateEditorColors();
            break;
         case 2:
         case 3:
            FlatSpinnerUI.this.spinner.repaint();
            break;
         case 4:
            FlatSpinnerUI.this.spinner.revalidate();
            break;
         case 5:
         case 6:
            FlatSpinnerUI.this.installStyle();
            FlatSpinnerUI.this.spinner.revalidate();
            FlatSpinnerUI.this.spinner.repaint();
         }

      }

      // $FF: synthetic method
      Handler(Object x1) {
         this();
      }
   }
}
